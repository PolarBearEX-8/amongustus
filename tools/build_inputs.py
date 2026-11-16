"""Restore pinned build inputs stored in Git-compatible binary parts."""
import hashlib
import json
import os
from pathlib import Path

def digest(path):
    value = hashlib.sha256()
    with path.open('rb') as source:
        for data in iter(lambda: source.read(1024 * 1024), b''):
            value.update(data)
    return value.hexdigest()

def restore_inputs(root):
    root = Path(root).resolve()
    records = json.loads((root / 'build-inputs/split-inputs.json').read_text(encoding='utf-8'))
    def inside(relative):
        path = Path(relative)
        if path.is_absolute() or '..' in path.parts:
            raise ValueError('Invalid build input path')
        resolved = (root / path).resolve()
        if not resolved.is_relative_to(root):
            raise ValueError('Build input path escaped the project')
        return resolved
    for record in records:
        target = inside(record['target'])
        if target.exists():
            if digest(target) != record['sha256']:
                raise RuntimeError('Build input checksum mismatch: ' + record['target'])
            continue
        temporary = target.with_name(target.name + '.tmp')
        target.parent.mkdir(parents=True, exist_ok=True)
        try:
            with temporary.open('xb') as output:
                for part in record['parts']:
                    path = inside(part['path'])
                    if path.stat().st_size != part['bytes'] or digest(path) != part['sha256']:
                        raise RuntimeError('Build input part checksum mismatch: ' + part['path'])
                    with path.open('rb') as source:
                        for data in iter(lambda: source.read(1024 * 1024), b''):
                            output.write(data)
            if digest(temporary) != record['sha256']:
                raise RuntimeError('Reassembled build input checksum mismatch')
            os.rename(temporary, target)
        except BaseException:
            if created and temporary.is_file():
                temporary.unlink()
            raise
