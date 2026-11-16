from pathlib import Path
import hashlib,io,json,zipfile,sys
sys.dont_write_bytecode=True

root=Path(__file__).resolve().parent
digest=lambda data:hashlib.sha256(data).hexdigest()
report=json.loads((root/'dist/build-report.json').read_text(encoding='utf-8'))
assert report['source_modules']==report['java8_verified_business_modules']==len(json.loads((root/'modules.json').read_text(encoding='utf-8')))==237
for name,expected in json.loads((root/'pinned-inputs.json').read_text(encoding='utf-8')).items():
    assert digest((root/name).read_bytes())==expected,'Pinned input changed: '+name
assert report.get('source_hashes'),'No source fingerprints; build this project before verifying'
current_sources={path.relative_to(root).as_posix() for directory in [root/'src/main/java',root/'src/dependency/java',root/'src/runtime/java',root/'src/main/resources',root/'tools'] for path in directory.rglob('*') if path.is_file()}
assert set(report['source_hashes'])==current_sources,'Source file inventory changed since build'
for name,expected in report['source_hashes'].items():
    assert digest((root/name).read_bytes())==expected,'Source changed since build: '+name
target=root/'dist/Augustus-source-development.jar'
assert digest(target.read_bytes())==report['jar_sha256'],'Package changed since build'
with zipfile.ZipFile(target) as jar:
    assert jar.testzip() is None
    overlay=jar.read('recovery/compiled-source-overlay.jar')
    assert overlay==(root/'dist/compiled-source-overlay.jar').read_bytes()
    resource='assets/augustus/alan/portrait.png'
    assert jar.read(resource)==(root/'src/main/resources'/resource).read_bytes()
    expected_resources=sorted(path.relative_to(root/'src/main/resources').as_posix() for path in (root/'src/main/resources').rglob('*') if path.is_file() and path.relative_to(root/'src/main/resources').as_posix().startswith('assets/'))
    assert jar.read('recovery/editable-resource-index.txt').decode('utf-8').splitlines()==expected_resources,'Resource override index mismatch'
    assert report.get('editable_resource_count')==len(expected_resources)
    for name in expected_resources:assert jar.read(name)==(root/'src/main/resources'/name).read_bytes(),'Editable resource differs from source: '+name
with zipfile.ZipFile(io.BytesIO(overlay)) as actual,zipfile.ZipFile(root/'lib/base-runtime-overlay.jar') as base:
    library=[name for name in base.namelist() if name.endswith('.class') and name.startswith(('net/java/games/input/','net/java/games/util/'))]
    assert len(library)==155,len(library)
    for name in library:assert actual.read(name)==base.read(name),'JInput/JUtils distribution mixed: '+name
    for row in json.loads((root/'modules.json').read_text(encoding='utf-8')):assert row['original_class']+'.class' in actual.namelist()
records={line.split('\t')[0]:line.split('\t')[1:] for line in (root/'dist/jvm.tsv').read_text(encoding='utf-8').splitlines()[1:]}
for row in json.loads((root/'modules.json').read_text(encoding='utf-8')):assert records[row['original_class']][0]=='JVM_VERIFIED',row['name']
held={name+'\t'+'\t'.join(values) for name,values in records.items() if values[0]=='HELD'}
allowed=set((root/'lib/expected-dependency-holds.tsv').read_text(encoding='utf-8').splitlines())
assert not held-allowed,held-allowed
print('VERIFY PASSED: 237 business modules; source fingerprints, package CRC, embedded overlay, Alan resource and all 155 JInput/JUtils classes match.')
print('Known dependency-only metadata holds:',len(held),'Full gameplay equivalence remains unverified.')

import sys
sys.path.insert(0,str(root/'tools'))
from remove_irc_payload import verify as verify_irc_payload
verify_irc_payload(target)
assert report.get('deleted_modules')==['IRC','IRCChat','Radio']
print('IRC deletion verified in module manifest, overlay, and all three embedded class/key payload maps.')
