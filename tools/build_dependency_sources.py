"""Compile explicit dependency method sources and retain their original class contracts."""
from pathlib import Path
import argparse,concurrent.futures,csv,hashlib,json,subprocess,zipfile

parser=argparse.ArgumentParser();parser.add_argument('--project',type=Path,required=True);parser.add_argument('--recovery',type=Path,required=True);parser.add_argument('--build',type=Path,required=True);parser.add_argument('--jdk',type=Path,required=True);parser.add_argument('--overlay',type=Path,required=True);parser.add_argument('--output',type=Path,required=True);parser.add_argument('--jobs',type=int,default=2);args=parser.parse_args()
root=args.project;build=args.build;output=build/'dependencies';output.mkdir(exist_ok=True)
class_map={}
for line in (root/'tools/readable-symbols.tsv').read_text(encoding='utf-8').splitlines():
    parts=line.split('\t')
    if len(parts)==5 and parts[0]=='CLASS':class_map[parts[1]]=parts[4]
def map_dep_source(src_rel,leg_cls):
    new_cls=class_map.get(leg_cls,leg_cls)
    if src_rel.startswith('src/dependency/java/complete/'):return 'src/dependency/java/complete/'+new_cls+'.java',new_cls
    return 'src/dependency/java/patches/'+new_cls+'.java',new_cls
manifest=root/'lib/dependency-methods.tsv';rows=list(csv.DictReader(manifest.open(encoding='utf-8'),delimiter='\t'))
groups={};seen=set()
for row in rows:
    identity=(row['runtime_class'],row['runtime_method'],row['runtime_descriptor'])
    if identity in seen:raise RuntimeError('Duplicate dependency source method')
    row['_src_file'],row['_read_cls']=map_dep_source(row['source_file'],row['readable_class'])
    seen.add(identity);groups.setdefault(row['readable_class'],[]).append(row)
    relative=Path(row['_src_file']);source=(root/relative).resolve();allowed=(root/'src/dependency/java').resolve()
    if relative.is_absolute() or not source.is_relative_to(allowed) or not source.is_file():raise RuntimeError('Invalid dependency source file: '+str(relative))
headers=';'.join(str(path) for path in [build/'support',build/'headers-readable/compile-headers.jar',build/'headers-readable/live-headers.jar',build/'headers-readable/dependency-headers.jar',build/'headers-readable/captured-compile-headers.jar'] if path.exists())
hierarchy=';'.join(str(path) for path in [root/'lib/compile-headers.jar',root/'lib/live-headers.jar',root/'lib/dependency-headers.jar',root/'lib/captured-compile-headers.jar',root/'lib/retained-templates.jar'] if path.exists())
toolcp=';'.join(map(str,[build/'tools',root/'lib/asm/*']))
def run(label,command):
    with (output/(label+'.log')).open('w',encoding='utf-8') as log:result=subprocess.run(list(map(str,command)),stdout=log,stderr=subprocess.STDOUT)
    if result.returncode:raise RuntimeError('Dependency '+label+' failed: '+str(output/(label+'.log')))
def compile_class(item):
    owner,methods=item;folder=output/'classes'/methods[0]['runtime_class'].encode('utf-8').hex();folder.mkdir(parents=True,exist_ok=True)
    files={row['_src_file'] for row in methods}
    if len(files)!=1:raise RuntimeError('One dependency compilation unit per class is required')
    read_cls=methods[0]['_read_cls']
    run('javac-'+methods[0]['runtime_class'].encode('utf-8').hex(),[args.jdk/'bin/javac.exe','-J-Xmx900m','--release','8','-encoding','UTF-8','-proc:none','-cp',headers,'-d',folder,root/methods[0]['_src_file']])
    return read_cls,folder/(read_cls+'.class')
with concurrent.futures.ThreadPoolExecutor(max_workers=max(1,min(args.jobs,4))) as pool:compiled=list(pool.map(compile_class,groups.items()))
with zipfile.ZipFile(output/'compiled-readable-new.jar','w',zipfile.ZIP_DEFLATED) as jar:
    for read_cls,file in compiled:jar.writestr(read_cls+'.class',file.read_bytes())
java=args.jdk/'bin/java.exe'
run('reverse-methods',[java,'-Xmx1800m','-cp',toolcp,'ReadableNamespaceMapper','reverse',root/'tools/readable-symbols.tsv','--hierarchy',hierarchy,'--zkm-spec',root/'tools/zkm-abi-spec.tsv',output/'compiled-readable-new.jar',output/'compiled-readable.jar'])
run('restore',[java,'-Xmx1800m','-cp',toolcp,'RestoreOriginalNames',root/'lib/symbol-map.tsv',output/'compiled-readable.jar',output/'compiled-runtime.jar'])
merge=[java,'-Xmx2000m','-cp',toolcp,'MergeDependencyMethods',args.recovery/'zkm27-original-core.jar',args.overlay,output/'compiled-runtime.jar',manifest,args.output,output/'installed-methods.tsv']
if (root/'lib/dependency-retained-bridges.jar').exists():merge+=['--retained-bridges',root/'lib/dependency-retained-bridges.jar']
run('merge',merge)
full_path=root/'lib/dependency-full-classes.json'
complete=json.loads(full_path.read_text(encoding='utf-8')) if full_path.exists() else []
if complete:
    for entry in complete:
        entry['_src_file'],entry['_read_cls']=map_dep_source(entry['source_file'],entry['readable_class'])
        source=(root/entry['_src_file']).resolve()
        if not source.is_relative_to((root/'src/dependency/java').resolve()) or not source.is_file() or entry['readable_class'] in groups:raise RuntimeError('Invalid or overlapping complete dependency source')
    run('full-javac',[args.jdk/'bin/javac.exe','--release','8','-encoding','UTF-8','-proc:none','-cp',headers,'-d',output/'full-readable-new',*[root/entry['_src_file'] for entry in complete]])
    with zipfile.ZipFile(output/'full-readable-new.jar','w',zipfile.ZIP_DEFLATED) as jar:
        for entry in complete:
            name=entry['_read_cls']+'.class';jar.writestr(name,(output/'full-readable-new'/name).read_bytes())
    run('full-reverse',[java,'-Xmx1800m','-cp',toolcp,'ReadableNamespaceMapper','reverse',root/'tools/readable-symbols.tsv','--hierarchy',hierarchy,'--zkm-spec',root/'tools/zkm-abi-spec.tsv',output/'full-readable-new.jar',output/'full-readable.jar'])
    run('full-restore',[java,'-Xmx1800m','-cp',toolcp,'RestoreOriginalNames',root/'lib/symbol-map.tsv',output/'full-readable.jar',output/'full-runtime.jar'])
    run('full-abi',[java,'-Xmx1800m','-cp',toolcp,'AuditSourceFlow',args.recovery/'tools/java-deobfuscator/analysis-headers-only.jar',build/'analysis-original-core.jar',output/'full-runtime.jar',output/'full-abi.txt',root/'lib/runtime-additions.jar'])
    with zipfile.ZipFile(args.output) as jar:combined={name:jar.read(name) for name in jar.namelist()}
    with zipfile.ZipFile(output/'full-runtime.jar') as jar:
        if set(jar.namelist())!={entry['runtime_class']+'.class' for entry in complete}:raise RuntimeError('Complete dependency runtime identity mismatch')
        for name in jar.namelist():
            if name in combined:raise RuntimeError('Complete source cannot also be a method patch')
            combined[name]=jar.read(name)
    with zipfile.ZipFile(args.output,'w',zipfile.ZIP_DEFLATED) as jar:
        for name,data in combined.items():jar.writestr(name,data)
report={'source_classes':len(groups)+len(complete),'source_methods':len(rows)+sum(entry['ordinary_methods'] for entry in complete),'full_source_classes':len(complete),'full_source_constructors':sum(entry['constructors'] for entry in complete),'class_contracts_retained':True,'manifest_sha256':hashlib.sha256(manifest.read_bytes()).hexdigest(),'source_hashes':{row['_src_file']:hashlib.sha256((root/row['_src_file']).read_bytes()).hexdigest() for row in [*rows,*complete]}}
(output/'report.json').write_text(json.dumps(report,ensure_ascii=False,indent=2),encoding='utf-8');print('DEPENDENCY_SOURCE_BUILD_PASSED classes='+str(len(groups))+' methods='+str(len(rows)),flush=True)
