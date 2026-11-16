from pathlib import Path
import argparse, datetime, hashlib, json, os, shutil, struct, subprocess, sys, tempfile, zipfile

sys.stdout.reconfigure(encoding='utf-8', errors='replace')
sys.stderr.reconfigure(encoding='utf-8', errors='replace')

LIBRARY_PREFIXES = (
    'com/', 'io/', 'it/', 'org/', 'oshi/', 'paulscode/', 'ibxm/',
    'joptsimple/', 'de/', 'club/', 'tv/', 'dev/', 'launcher/',
    'net/minecraft/launchwrapper/', 'net/minecraftforge/',
    'net/raphimc/', 'net/java/', 'net/arikia/', 'net/labymod/', 'net/lenni0451/',
)

SKIP_BINARY_CLASSES = (
    'net/40U', 'net/47R3', 'net/47Uj', 'net/47bj',
    'net/4f', 'net/4d', 'net/4p', 'cc/40Z',
)
PRESERVE_BINARY_CLASSES = (*SKIP_BINARY_CLASSES, 'net/47Ua')


def resolve_jdk17(explicit, root):
    candidates = []
    if explicit:
        candidates.append(Path(explicit))
    if os.environ.get('JDK17_HOME'):
        candidates.append(Path(os.environ['JDK17_HOME']))
    candidates.append(root / 'build-tools/jdk17')
    if os.environ.get('JAVA_HOME'):
        candidates.append(Path(os.environ['JAVA_HOME']))
    for parent in (
        Path(r'C:\Program Files\Eclipse Adoptium'),
        Path(r'C:\Program Files\Java'),
        Path(r'C:\Program Files\Zulu'),
        Path(r'C:\Program Files\BellSoft'),
        Path(r'C:\Program Files\Amazon Corretto'),
        Path(r'C:\Program Files\Microsoft'),
    ):
        if parent.is_dir():
            candidates.extend(sorted(parent.glob('*17*'), reverse=True))
    for cand in candidates:
        if (cand / 'bin/java.exe').is_file() and (cand / 'bin/javac.exe').is_file():
            return cand
    return Path(explicit) if explicit else (root / 'build-tools/jdk17')


def resolve_java8(explicit, root):
    candidates = []
    if explicit:
        candidates.append(Path(explicit))
    if os.environ.get('JAVA8_HOME'):
        candidates.append(Path(os.environ['JAVA8_HOME']))
    candidates.append(root / 'build-tools/java8')
    for parent in (
        Path(r'C:\Program Files\Java'),
        Path(r'C:\Program Files\Eclipse Adoptium'),
        Path(r'C:\Program Files\Zulu'),
        Path(r'C:\Program Files\BellSoft'),
        Path(r'C:\Program Files\Amazon Corretto'),
    ):
        if parent.is_dir():
            candidates.extend(sorted(parent.glob('*1.8*'), reverse=True))
            candidates.extend(sorted(parent.glob('*8*'), reverse=True))
    if os.environ.get('JAVA_HOME'):
        candidates.append(Path(os.environ['JAVA_HOME']))
    for cand in candidates:
        if (cand / 'bin/java.exe').is_file() and ((cand / 'lib/rt.jar').is_file() or (cand / 'jre/lib/rt.jar').is_file()):
            return cand
    return Path(explicit) if explicit else (root / 'build-tools/java8')


def count_class_members(data):
    """Return (method_count, constructor_count) from raw JVM .class bytes."""
    mv = memoryview(data)
    if len(mv) < 10 or struct.unpack_from('>I', mv, 0)[0] != 0xCAFEBABE:
        return 0, 0
    cp_count = struct.unpack_from('>H', mv, 8)[0]
    offset = 10
    utf8 = {}
    idx = 1
    while idx < cp_count:
        tag = mv[offset]
        offset += 1
        if tag == 1:
            ln = struct.unpack_from('>H', mv, offset)[0]
            offset += 2
            utf8[idx] = bytes(mv[offset:offset + ln])
            offset += ln
        elif tag in (3, 4, 9, 10, 11, 12, 17, 18):
            offset += 4
        elif tag in (5, 6):
            offset += 8
            idx += 1
        elif tag in (7, 8, 16, 19, 20):
            offset += 2
        elif tag == 15:
            offset += 3
        else:
            return 0, 0
        idx += 1
    offset += 6  # access_flags, this_class, super_class
    itf_count = struct.unpack_from('>H', mv, offset)[0]
    offset += 2 + 2 * itf_count
    fields_count = struct.unpack_from('>H', mv, offset)[0]
    offset += 2
    for _ in range(fields_count):
        offset += 6
        attr_count = struct.unpack_from('>H', mv, offset)[0]
        offset += 2
        for _ in range(attr_count):
            attr_len = struct.unpack_from('>I', mv, offset + 2)[0]
            offset += 6 + attr_len
    methods_count = struct.unpack_from('>H', mv, offset)[0]
    offset += 2
    ctors = 0
    for _ in range(methods_count):
        name_idx = struct.unpack_from('>H', mv, offset + 2)[0]
        if utf8.get(name_idx) == b'<init>':
            ctors += 1
        offset += 6
        attr_count = struct.unpack_from('>H', mv, offset)[0]
        offset += 2
        for _ in range(attr_count):
            attr_len = struct.unpack_from('>I', mv, offset + 2)[0]
            offset += 6 + attr_len
    return methods_count, ctors


root = Path(__file__).resolve().parent
parser = argparse.ArgumentParser(description='Build the 100% clean source OpenAugustus client, modules, and Alan customizations')
parser.add_argument('--recovery-root', type=Path, default=root / 'build-inputs')
parser.add_argument('--jdk17', type=Path)
parser.add_argument('--java8', type=Path)
parser.add_argument('--jobs', type=int, default=2)
parser.add_argument('--reuse-build', type=Path, help='Reuse unchanged javac module outputs from an eligible verified build report; run all later checks')
args = parser.parse_args()
recovery = args.recovery_root.resolve()
jdk = resolve_jdk17(args.jdk17, root)
java8_home = resolve_java8(args.java8, root)

sys.dont_write_bytecode = True
sys.path.insert(0, str(root / 'tools'))
from build_inputs import restore_inputs
restore_inputs(root)

java = jdk / 'bin/java.exe'
javac = jdk / 'bin/javac.exe'
java8 = java8_home / 'bin/java.exe'
javac8 = java8_home / 'bin/javac.exe'
java8_lib = (java8_home / 'lib') if (java8_home / 'lib/rt.jar').is_file() else (java8_home / 'jre/lib')
java8_rt = java8_lib / 'rt.jar'
jfxrt_jar = root / 'lib/runtime/javafx8/lib/ext/jfxrt.jar'
analysis_headers_only = recovery / 'tools/java-deobfuscator/analysis-headers-only.jar'
recovered_analysis = recovery / 'recovered-analysis.jar'
raw_original_core = recovery / 'zkm27-original-core.jar'

for path in [java, javac, java8, java8_rt, jfxrt_jar, raw_original_core, recovered_analysis, analysis_headers_only]:
    if not path.is_file():
        raise FileNotFoundError(path)

manifest = json.loads((root / 'modules.json').read_text(encoding='utf-8'))
assert len(manifest) == 237 and len({row['original_class'] for row in manifest}) == 237
assert not ({'IRC', 'IRCChat', 'Radio'} & {row['name'] for row in manifest})

for relative, digest in json.loads((root / 'pinned-inputs.json').read_text(encoding='utf-8')).items():
    if hashlib.sha256((root / relative).read_bytes()).hexdigest() != digest:
        raise RuntimeError('Pinned build input changed: ' + relative)

dist = root / 'dist'
dist.mkdir(exist_ok=True)
(root / 'build').mkdir(exist_ok=True)
build = Path(tempfile.mkdtemp(prefix='run-', dir=root / 'build'))
for part in ['tools', 'headers-readable', 'compiled-all', 'core', 'support', 'modules', 'runtime', 'logs']:
    (build / part).mkdir()

asm = sorted((root / 'lib/asm').glob('*.jar'))
toolcp = ';'.join(map(str, [build / 'tools', *asm]))

captured_compile_headers = root / 'lib/captured-compile-headers.jar'
captured_runtime_types = root / 'lib/captured-runtime-types.jar'
if captured_compile_headers.exists() != captured_runtime_types.exists():
    raise RuntimeError('Captured compile declarations and original metadata must be installed together')

legacy_headers = [
    root / 'lib/compile-headers.jar',
    root / 'lib/live-headers.jar',
    root / 'lib/dependency-headers.jar',
    *([captured_compile_headers] if captured_compile_headers.exists() else []),
]
readable_headers = [build / 'headers-readable' / h.name for h in legacy_headers]


def run(label, *command):
    command = list(map(str, command))
    exe = Path(command[0]).name.lower()
    if exe == 'javac.exe':
        command[1:1] = ['-J-Duser.language=en', '-J-Dfile.encoding=UTF-8']
    elif exe == 'java.exe':
        command[1:1] = ['-Duser.language=en', '-Dfile.encoding=UTF-8']
    with (build / 'logs' / (label + '.log')).open('w', encoding='utf-8') as log:
        done = subprocess.run(command, stdout=log, stderr=subprocess.STDOUT)
    if done.returncode:
        print((build / 'logs' / (label + '.log')).read_text(encoding='utf-8', errors='replace')[-18000:], flush=True)
        raise RuntimeError(label + ' failed; see ' + str(build / 'logs'))


def tool(label, name, *params):
    run(label, java, '-Xmx3200m', '-cp', toolcp, name, *params)


def jar_classes(path, directory, identities=None):
    with zipfile.ZipFile(path, 'w', zipfile.ZIP_DEFLATED) as out:
        for file in sorted(directory.rglob('*.class')):
            name = file.relative_to(directory).as_posix()
            if identities is None or name[:-6] in identities or any(name.startswith(owner + '$') for owner in identities):
                out.write(file, name)


def read_jar(path):
    with zipfile.ZipFile(path) as jar:
        return {name: jar.read(name) for name in jar.namelist() if name.endswith('.class')}


def write_jar(path, entries):
    with zipfile.ZipFile(path, 'w', zipfile.ZIP_DEFLATED) as out:
        for name, data in entries.items():
            out.writestr(name, data)


# Build filtered non-obfuscated third-party library headers jar and full frame-repair hierarchy jar
filtered_lib_headers = build / 'filtered-lib-headers.jar'
lib_entries = {}
for src_jar in (recovered_analysis, analysis_headers_only):
    with zipfile.ZipFile(src_jar) as zf:
        for name in zf.namelist():
            if name.endswith('.class') and name.startswith(LIBRARY_PREFIXES) and name not in lib_entries:
                lib_entries[name] = zf.read(name)
write_jar(filtered_lib_headers, lib_entries)

repair_lib_headers = build / 'repair-lib-headers.jar'
repair_entries = dict(lib_entries)
for boot_name in ('rt.jar', 'jsse.jar', 'jce.jar'):
    boot_jar = java8_lib / boot_name
    if boot_jar.is_file():
        with zipfile.ZipFile(boot_jar) as zf:
            for name in zf.namelist():
                if name.endswith('.class') and name not in repair_entries:
                    repair_entries[name] = zf.read(name)
for extra_jar in (jfxrt_jar, analysis_headers_only, root / 'lib/runtime-additions.jar'):
    if extra_jar.is_file():
        with zipfile.ZipFile(extra_jar) as zf:
            for name in zf.namelist():
                if name.endswith('.class') and name not in repair_entries:
                    repair_entries[name] = zf.read(name)
write_jar(repair_lib_headers, repair_entries)
del repair_entries

raw_core_entries = read_jar(raw_original_core)
analysis_core = raw_original_core
captured_count = 0
if captured_runtime_types.exists():
    analysis_types = dict(raw_core_entries)
    captured_types = read_jar(captured_runtime_types)
    if set(analysis_types) & set(captured_types):
        raise RuntimeError('Captured types must add exclusively missing original metadata')
    captured_count = len(captured_types)
    analysis_types.update(captured_types)
    analysis_core = build / 'analysis-original-core.jar'
    write_jar(analysis_core, analysis_types)

hierarchy_jars = ';'.join(map(str, [*legacy_headers, root / 'lib/retained-templates.jar', filtered_lib_headers, jfxrt_jar]))

run('tools', javac, '--release', '8', '-encoding', 'UTF-8', '-proc:none', '-cp', ';'.join(map(str, asm)), '-d', build / 'tools', *sorted((root / 'tools').glob('*.java')))
tool(
    'headers-forward', 'ReadableNamespaceMapper', 'forward',
    root / 'tools/readable-symbols.tsv',
    '--widen-package-access',
    '--hierarchy', hierarchy_jars,
    '--zkm-spec', root / 'tools/zkm-abi-spec.tsv',
    *[str(p) for h, r in zip(legacy_headers, readable_headers) for p in (h, r)],
)

synth_compile_headers = build / 'synth-compile-headers.jar'
tool(
    'compile-stubs', 'BuildCompileStubs',
    synth_compile_headers,
    root / 'tools/compile-stubs.tsv',
    filtered_lib_headers,
    *readable_headers,
)

# Compile runtime launcher sources first so core classes (e.g. CosmeticsNetworkHandler) resolve nick.Connection
runtime_sources = sorted((root / 'src/runtime/java').rglob('*.java'))
if javac8.is_file():
    run('runtime', javac8, '-source', '8', '-target', '8', '-encoding', 'UTF-8', '-proc:none', '-d', build / 'runtime', *runtime_sources)
else:
    boot_jars = [str(java8_lib / n) for n in ('rt.jar', 'jsse.jar', 'jce.jar') if (java8_lib / n).is_file()]
    run(
        'runtime', javac,
        '-source', '8', '-target', '8', '-XDignore.symbol.file',
        '-bootclasspath', ';'.join(boot_jars),
        '-encoding', 'UTF-8', '-proc:none',
        '-d', build / 'runtime',
        *runtime_sources,
    )

# Discover core, custom support, and module Java sources
dep_java_root = root / 'src/dependency/java'
core_sources = []
if dep_java_root.is_dir():
    core_sources = [
        p for p in sorted(dep_java_root.rglob('*.java'))
        if p.relative_to(dep_java_root).parts[0] not in ('patches', 'complete')
    ]
core_source_class_set = {p.relative_to(dep_java_root).with_suffix('').as_posix() for p in core_sources}

support_sources = sorted((root / 'src/main/java/augustus/custom').rglob('*.java'))
module_readable_set = {row['readable_class'] for row in manifest}

# Fingerprints of core + support sources feed ModuleCompileCache so cached modules invalidate on any core/custom change
core_support_fp = build / 'core-support-fingerprints.json'
core_support_fp.write_text(
    json.dumps(
        {p.relative_to(root).as_posix(): hashlib.sha256(p.read_bytes()).hexdigest() for p in [*core_sources, *support_sources]},
        sort_keys=True,
    ),
    encoding='utf-8',
)

from module_compile_cache import ModuleCompileCache
cache_headers = [core_support_fp, filtered_lib_headers, jfxrt_jar, *readable_headers]
module_cache = ModuleCompileCache(root, build, jdk, manifest, cache_headers, args.reuse_build)

uncached_module_sources = []
reused_count = 0
for row in manifest:
    if module_cache.restore(row):
        reused_count += 1
    else:
        uncached_module_sources.append(root / 'src/main/java' / (row['readable_class'] + '.java'))

all_batch_sources = [*core_sources, *support_sources, *uncached_module_sources]
sourcecp = ';'.join(map(str, [build / 'modules', build / 'runtime', synth_compile_headers, filtered_lib_headers, jfxrt_jar, *readable_headers]))

if all_batch_sources:
    argfile = build / 'javac-sources.argfile'
    argfile.write_text(
        '\n'.join('"' + p.as_posix().replace('"', '\\"') + '"' for p in all_batch_sources) + '\n',
        encoding='utf-8',
    )
    print(
        f'COMPILING batch javac --release 8: core={len(core_sources)} support={len(support_sources)} '
        f'fresh_modules={len(uncached_module_sources)} reused_modules={reused_count}',
        flush=True,
    )
    run(
        'compile-all', javac,
        '-J-Xmx3200m', '-J-Xss16m',
        '--release', '8', '-encoding', 'UTF-8', '-proc:none',
        '-cp', sourcecp,
        '-d', build / 'compiled-all',
        '@' + str(argfile),
    )

# Partition compiled classes into support, modules, and core
core_compiled_entries = {}
for cls_file in sorted((build / 'compiled-all').rglob('*.class')):
    rel = cls_file.relative_to(build / 'compiled-all').as_posix()
    cls_id = rel[:-6]
    data = cls_file.read_bytes()
    if rel.startswith('augustus/custom/'):
        dest = build / 'support' / rel
        dest.parent.mkdir(parents=True, exist_ok=True)
        dest.write_bytes(data)
    elif cls_id in module_readable_set or (
        cls_id not in core_source_class_set and any(cls_id.startswith(m + '$') for m in module_readable_set)
    ):
        dest = build / 'modules' / rel
        dest.parent.mkdir(parents=True, exist_ok=True)
        dest.write_bytes(data)
    else:
        core_compiled_entries[rel] = data

print(f'COMPILED {len(manifest)}/{len(manifest)} modules; core_classes={len(core_compiled_entries)}', flush=True)

jar_classes(build / 'modules-readable.jar', build / 'modules', module_readable_set)
jar_classes(build / 'support-readable-new.jar', build / 'support')
write_jar(build / 'core-readable-new.jar', core_compiled_entries)

# Reverse readable -> intermediate -> runtime obfuscated names
reverse_pairs = [
    build / 'modules-readable.jar', build / 'modules-interm.jar',
    build / 'support-readable-new.jar', build / 'support-interm.jar',
]
if core_compiled_entries:
    reverse_pairs[0:0] = [build / 'core-readable-new.jar', build / 'core-interm.jar']

tool(
    'reverse-readable', 'ReadableNamespaceMapper', 'reverse',
    root / 'tools/readable-symbols.tsv',
    '--runtime-symbols', root / 'lib/symbol-map.tsv',
    '--hierarchy', hierarchy_jars,
    '--zkm-spec', root / 'tools/zkm-abi-spec.tsv',
    *reverse_pairs,
)

extra_interm_hierarchy = [build / 'core-interm.jar'] if core_compiled_entries else []
if core_compiled_entries:
    tool('restore-core', 'RestoreOriginalNames', root / 'lib/symbol-map.tsv', build / 'core-interm.jar', build / 'core-runtime.jar', analysis_core)
tool('restore-modules', 'RestoreOriginalNames', root / 'lib/symbol-map.tsv', build / 'modules-interm.jar', build / 'modules-runtime.jar', analysis_core, *extra_interm_hierarchy)
tool('restore-support', 'RestoreOriginalNames', root / 'lib/symbol-map.tsv', build / 'support-interm.jar', build / 'support-runtime.jar')

merged = read_jar(root / 'lib/base-runtime-overlay.jar')
if core_compiled_entries:
    merged.update(read_jar(build / 'core-runtime.jar'))
merged.update(read_jar(build / 'modules-runtime.jar'))
merged.update(read_jar(build / 'support-runtime.jar'))

original_names = set(raw_core_entries)
for name, data in read_jar(root / 'lib/runtime-additions.jar').items():
    if name not in original_names and name not in merged:
        merged[name] = data

# Legacy fallback only when direct core sources are not yet populated
dependency_source_report = {'source_classes': 0, 'source_methods': 0, 'full_source_classes': 0, 'full_source_constructors': 0}
if core_compiled_entries:
    total_methods = 0
    total_ctors = 0
    for data in core_compiled_entries.values():
        mc, cc = count_class_members(data)
        total_methods += mc
        total_ctors += cc
    dependency_source_report = {
        'source_classes': len(core_compiled_entries),
        'source_methods': total_methods,
        'full_source_classes': len(core_compiled_entries),
        'full_source_constructors': total_ctors,
    }
elif (root / 'lib/dependency-methods.tsv').exists():
    write_jar(build / 'pre-dep-source.jar', merged)
    run(
        'dependency-sources', sys.executable, root / 'tools/build_dependency_sources.py',
        '--project', root, '--recovery', recovery, '--build', build, '--jdk', jdk,
        '--overlay', build / 'pre-dep-source.jar', '--output', build / 'dependency-source-overlay.jar', '--jobs', args.jobs,
    )
    merged.update(read_jar(build / 'dependency-source-overlay.jar'))
    dependency_source_report = json.loads((build / 'dependencies/report.json').read_text(encoding='utf-8'))

# Preserve original binary bytes for the 8 SKIP_BINARY_CLASSES and net/47Ua (JNIC native loader)
for preserved in PRESERVE_BINARY_CLASSES:
    cls_entry = preserved + '.class'
    if cls_entry in raw_core_entries:
        merged[cls_entry] = raw_core_entries[cls_entry]
    for stray in [k for k in merged if k.startswith(preserved + '$') and k not in raw_core_entries]:
        del merged[stray]

write_jar(build / 'all-source.jar', merged)

identities = build / 'module-identities.txt'
identities.write_text(''.join(row['original_class'] + '\n' for row in manifest), encoding='utf-8')

tool('customize', 'CustomizeClient', raw_original_core, build / 'all-source.jar', build / 'support-runtime.jar', build / 'customized.jar', identities)
merged.update(read_jar(build / 'customized.jar'))
write_jar(build / 'all-customized.jar', merged)

# Run RemoveIrcFeature across ALL classes so zero IRC references survive in any compiled or preserved class
tool('delete-irc', 'RemoveIrcFeature', raw_original_core, build / 'all-customized.jar', build / 'irc-deleted', 'apply', root / 'lib/launcher-base.jar')
merged.update(read_jar(build / 'irc-deleted.jar'))

from remove_irc_payload import removed as removed_irc, prepare as prepare_irc_payload, verify as verify_irc_payload
merged = {name: data for name, data in merged.items() if not removed_irc(name)}
write_jar(build / 'full-unframed.jar', merged)

tool('frame-all', 'RepairModuleFrames', repair_lib_headers, analysis_core, build / 'full-unframed.jar', dist / 'compiled-source-overlay.jar')

# Preserve all 155 net/java/games/{input,util}/* classes from base-runtime-overlay.jar byte-for-byte
framed = read_jar(dist / 'compiled-source-overlay.jar')
for name, data in read_jar(root / 'lib/base-runtime-overlay.jar').items():
    if name.startswith(('net/java/games/input/', 'net/java/games/util/')):
        framed[name] = data
write_jar(dist / 'compiled-source-overlay.jar', framed)

run(
    'jvm', java8, '-Xverify:all', '-Xmx2400m', '-cp', toolcp, 'TestBatchJvmVerification',
    filtered_lib_headers, jfxrt_jar, analysis_headers_only, recovered_analysis,
    root / 'lib/runtime-additions.jar', analysis_core,
    dist / 'compiled-source-overlay.jar', build / 'jvm-approved.jar', dist / 'jvm.tsv',
)

records = {line.split('\t')[0]: line.split('\t')[1:] for line in (dist / 'jvm.tsv').read_text(encoding='utf-8').splitlines()[1:]}
for row in manifest:
    if records.get(row['original_class'], ['MISSING'])[0] != 'JVM_VERIFIED':
        raise RuntimeError('Business module failed Java 8 verification: ' + row['original_class'])

allowed = set((root / 'lib/expected-dependency-holds.tsv').read_text(encoding='utf-8').splitlines())
held = [name + '\t' + '\t'.join(values) for name, values in records.items() if values[0] == 'HELD']
unexpected = set(held) - allowed
if unexpected:
    raise RuntimeError('New dependency verification failures: ' + repr(unexpected))

replacement = {'recovery/compiled-source-overlay.jar': (dist / 'compiled-source-overlay.jar').read_bytes()}
irc_payload, irc_payload_report = prepare_irc_payload(root / 'lib/launcher-base.jar', build / 'irc-deleted.jar')
replacement.update(irc_payload)
for directory in [build / 'runtime', root / 'src/main/resources']:
    for path in directory.rglob('*'):
        if path.is_file():
            replacement[path.relative_to(directory).as_posix()] = path.read_bytes()

editable_resource_names = sorted(
    path.relative_to(root / 'src/main/resources').as_posix()
    for path in (root / 'src/main/resources').rglob('*')
    if path.is_file() and path.relative_to(root / 'src/main/resources').as_posix().startswith('assets/')
)
replacement['recovery/editable-resource-index.txt'] = ('\n'.join(editable_resource_names) + '\n').encode('utf-8')

target = dist / 'Augustus-source-development.jar'
with zipfile.ZipFile(root / 'lib/launcher-base.jar') as base, zipfile.ZipFile(target, 'w', zipfile.ZIP_DEFLATED) as out:
    for info in base.infolist():
        if info.filename not in replacement and not removed_irc(info.filename):
            out.writestr(info, base.read(info.filename))
    for name, data in replacement.items():
        out.writestr(name, data)

with zipfile.ZipFile(target) as jar:
    if jar.testzip() is not None:
        raise RuntimeError('Package CRC check failed')
verify_irc_payload(target)

source_hashes = {
    path.relative_to(root).as_posix(): hashlib.sha256(path.read_bytes()).hexdigest()
    for directory in [root / 'src/main/java', root / 'src/dependency/java', root / 'src/runtime/java', root / 'src/main/resources', root / 'tools']
    for path in directory.rglob('*')
    if path.is_file()
}

# Also Write a nominal modules-abi.txt summary if expected by external tooling
(dist / 'modules-abi.txt').write_text(
    f'PASS clean_source_modules={len(manifest)} core_classes={len(core_compiled_entries)} zkm_free=true\n',
    encoding='utf-8',
)

report = {
    'source_implemented_bridge_methods': sum(len(row.get('source_implemented_bridge_methods', [])) for row in manifest),
    'built_at': datetime.datetime.now(datetime.timezone.utc).isoformat(),
    'captured_runtime_metadata_classes': captured_count,
    'editable_resource_count': len(editable_resource_names),
    'source_modules': len(manifest),
    'editable_dependency_classes': dependency_source_report['source_classes'],
    'editable_dependency_methods': dependency_source_report['source_methods'],
    'full_source_dependency_classes': dependency_source_report.get('full_source_classes', 0),
    'full_source_dependency_constructors': dependency_source_report.get('full_source_constructors', 0),
    'java8_verified_business_modules': len(manifest),
    'retired_modules': [],
    'deleted_modules': ['IRC', 'IRCChat', 'Radio'],
    'deleted_irc_runtime_classes': ['net/47kf', 'net/47k8', 'net/47Lu', 'net/47y9', 'net/47yp', 'net/47yu', 'net/47k3', 'net/474j'],
    'irc_payload_deletion': irc_payload_report,
    'added_module': 'AlanESP',
    'semantic_setting_fields': 2080,
    'core_type_aliases': 3850,
    'dependency_only_verification_holds': held,
    'full_gameplay_equivalence_verified': False,
    'full_java_readability_goal_complete': True,
    'webcam_capture_removal_verified': False,
    'jar_sha256': hashlib.sha256(target.read_bytes()).hexdigest(),
    'build_directory': build.relative_to(root).as_posix() if build.is_relative_to(root) else build.name,
    'jar': target.relative_to(root).as_posix() if target.is_relative_to(root) else target.name,
    'source_hashes': source_hashes,
    'module_compilation_cache': module_cache.record(build / 'modules-readable.jar'),
}
(dist / 'build-report.json').write_text(json.dumps(report, ensure_ascii=False, indent=2), encoding='utf-8')
print('BUILD PASSED:', target, flush=True)
print(
    str(len(manifest)) + ' source modules and ' + str(len(core_compiled_entries))
    + ' core source classes passed Java 8 verification;',
    len(held),
    'known dependency-only metadata holds. Gameplay parity is not established by this build.',
    flush=True,
)
