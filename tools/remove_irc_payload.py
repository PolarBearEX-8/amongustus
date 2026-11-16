"""Repack loader maps so deleted IRC bytecode cannot survive in fallback payloads."""
import io,json,struct,zlib,zipfile

REMOVED={'net/47kf','net/47k8','net/47Lu','net/47y9','net/47yp','net/47yu','net/47k3','net/474j'}
def removed(name):
    name=name.removesuffix('.class').replace('.','/')
    return any(name==owner or name.startswith(owner+'$') for owner in REMOVED)

def repack(data,kind,overrides):
    compressed=kind=='classes'
    stream=io.BytesIO(zlib.decompress(data) if compressed else data)
    out=io.BytesIO();deleted=[];replaced=[];count=0
    def integer():
        raw=stream.read(4)
        if len(raw)!=4:raise ValueError('Truncated loader record')
        n=struct.unpack('>i',raw)[0]
        if not 0<=n<=128*1024*1024:raise ValueError('Invalid loader record length')
        return raw,n
    while stream.tell()<len(stream.getbuffer()):
        start=stream.tell()
        if compressed:
            marker=stream.read(1);_,n=integer();key=stream.read(n*2).decode('utf-16le',errors='surrogatepass')
        else:
            _,n=integer();key=stream.read(n).decode('utf-8')
        key_end=stream.tell();_,n=integer();value=stream.read(n)
        if len(value)!=n:raise ValueError('Truncated loader value')
        if removed(key):deleted.append(key);continue
        internal=key.removesuffix('.class').replace('.','/')+'.class'
        if kind!='keys' and internal in overrides:
            value=overrides[internal];replaced.append(key)
        encoded=stream.getbuffer()[start:key_end]
        out.write(encoded);out.write(struct.pack('>i',len(value)));out.write(value);count+=1
    result=out.getvalue()
    return (zlib.compress(result) if compressed else result),dict(kind=kind,records=count,deleted=deleted,replaced=replaced)

def prepare(base,patch):
    with zipfile.ZipFile(patch) as jar:overrides={n:jar.read(n) for n in jar.namelist() if n.endswith('.class')}
    replacements={};reports=[]
    with zipfile.ZipFile(base) as jar:
        for kind in ('cache','classes','keys'):
            name='data/'+kind+'.bin'
            replacements[name],report=repack(jar.read(name),kind,overrides);reports.append(report)
    return replacements,reports

def verify(jar_path):
    reports=[]
    with zipfile.ZipFile(jar_path) as jar:
        for name in jar.namelist():
            if name.endswith('.class') and removed(name):raise ValueError('Deleted IRC class remains: '+name)
        for kind in ('cache','classes','keys'):
            _,report=repack(jar.read('data/'+kind+'.bin'),kind,{})
            if report['deleted']:raise ValueError('Deleted IRC payload remains: '+str(report))
            reports.append(report)
        with zipfile.ZipFile(io.BytesIO(jar.read('recovery/compiled-source-overlay.jar'))) as overlay:
            for name in overlay.namelist():
                if removed(name):raise ValueError('Deleted IRC overlay entry remains: '+name)
                if name.endswith('.class') and any(owner.encode() in overlay.read(name) for owner in REMOVED):raise ValueError('Direct deleted-type reference remains: '+name)
    return reports

if __name__=='__main__':
    import sys
    print(json.dumps(verify(sys.argv[1]),indent=2))
