"""Optional javac cache; assembly, ABI checks and real Java 8 verification always run."""
from pathlib import Path,PurePosixPath
import hashlib,json,os,threading,zipfile

def file_hash(path):
    value=hashlib.sha256()
    with Path(path).open('rb') as stream:
        for chunk in iter(lambda:stream.read(1024*1024),b''):value.update(chunk)
    return value.hexdigest()

class ModuleCompileCache:
    def __init__(self,root,build,jdk,manifest,headers,previous_report=None):
        self.root=Path(root);self.build=Path(build);self.rows=manifest;self.lock=threading.Lock();self.reused=0;self.entries={};self.previous_sources={};self.held=None
        inputs={}
        for path in [self.root/'build.py',Path(__file__),Path(jdk)/'bin/javac.exe',Path(jdk)/'lib/modules',Path(jdk)/'release',*headers]:
            inputs[str(Path(path).resolve())]=file_hash(path)
        for path in sorted((self.build/'support').rglob('*.class')):inputs['support/'+path.relative_to(self.build/'support').as_posix()]=file_hash(path)
        inputs['javac_flags']=['--release','8','-encoding','UTF-8','-proc:none','-J-Xmx1100m','-J-Xss16m','-J-Duser.language=en','-J-Dfile.encoding=UTF-8']
        inputs['environment']={name:os.environ.get(name) for name in ['JAVA_TOOL_OPTIONS','_JAVA_OPTIONS','JDK_JAVA_OPTIONS','CLASSPATH']}
        self.input_key=hashlib.sha256(json.dumps(inputs,sort_keys=True,separators=(',',':')).encode('utf-8')).hexdigest()
        self.sources={row['readable_class']:file_hash(self.root/'src/main/java'/(row['readable_class']+'.java')) for row in manifest}
        if previous_report is not None:
            try:
                previous=json.loads(Path(previous_report).read_text(encoding='utf-8'));cache=previous.get('module_compilation_cache',{})
                if cache.get('schema')!=1 or cache.get('compiler_inputs_sha256')!=self.input_key:
                    self.held='Compiler, headers, support classes, build script or environment differ; compile all modules.'
                else:
                    artifact=Path(cache['compiled_modules_jar'])
                    if not artifact.is_absolute():artifact=self.root/artifact
                    if file_hash(artifact)!=cache['compiled_modules_jar_sha256']:raise ValueError('Cached compiled JAR digest changed')
                    owners={row['readable_class'] for row in manifest}
                    with zipfile.ZipFile(artifact) as jar:
                        if jar.testzip() is not None:raise ValueError('Cached compiled JAR CRC failure')
                        for name in jar.namelist():
                            if not name.endswith('.class'):continue
                            owner=next((owner for owner in owners if name==owner+'.class' or name.startswith(owner+'$')),None)
                            if owner is not None:self.entries.setdefault(owner,{})[name]=jar.read(name)
                    self.previous_sources=cache['module_source_sha256']
            except (OSError,ValueError,KeyError,zipfile.BadZipFile) as error:
                self.entries={};self.previous_sources={};self.held='Cached inputs/artifacts rejected: '+type(error).__name__+'; compile all modules.'
        if self.held:print('MODULE_CACHE_HELD',self.held,flush=True)

    def restore(self,row):
        owner=row['readable_class'];entries=self.entries.get(owner,{})
        if self.sources[owner]!=self.previous_sources.get(owner) or owner+'.class' not in entries:return False
        output=(self.build/'modules').resolve(strict=True)
        try:
            for name,data in entries.items():
                relative=PurePosixPath(name)
                if relative.is_absolute() or '..' in relative.parts or '\\' in name or ':' in name:raise ValueError('Invalid cached class entry')
                target=output.joinpath(*relative.parts)
                target.parent.mkdir(parents=True,exist_ok=True)
                parent=target.parent.resolve(strict=True)
                if not parent.is_relative_to(output):raise ValueError('Cached class parent escaped module output')
                (parent/relative.name).write_bytes(data)
        except OSError as error:
            # A cache filesystem miss can safely use fresh javac for this exact source.
            # Previously copied entries belong to the identical source/compiler key.
            with self.lock:print('MODULE_CACHE_IO_MISS',row['name'],type(error).__name__,'fresh_javac=true',flush=True)
            return False
        with self.lock:self.reused+=1
        return True

    def record(self,compiled_modules_jar):
        # Reject source edits made during either fresh compilation or cache restoration.
        for owner,digest in self.sources.items():
            if file_hash(self.root/'src/main/java'/(owner+'.java'))!=digest:raise RuntimeError('Module source changed during build: '+owner)
        jar_path=Path(compiled_modules_jar)
        rel_jar=jar_path.relative_to(self.root).as_posix() if jar_path.is_relative_to(self.root) else jar_path.name
        result={'schema':1,'compiler_inputs_sha256':self.input_key,'compiled_modules_jar':rel_jar,'compiled_modules_jar_sha256':file_hash(compiled_modules_jar),'module_source_sha256':self.sources,'reused_javac_modules':self.reused,'fresh_javac_modules':len(self.sources)-self.reused,'assembly_abi_and_java8_checks_skipped':False}
        if self.held:result['held_reason']=self.held
        print('MODULE_COMPILE_CACHE fresh='+str(result['fresh_javac_modules'])+' reused='+str(self.reused)+' all_runtime_checks_run=true',flush=True)
        return result
