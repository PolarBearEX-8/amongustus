#!/usr/bin/env python3
"""Patch a temporary copy of the launcher JNICLoader to skip System.load only."""
from pathlib import Path
from zipfile import ZipFile
import argparse
import struct

def patch(jar: Path, out_dir: Path) -> Path:
    entry = "dev/jnic/rNBrJa/JNICLoader.class"
    data = bytearray(ZipFile(jar).read(entry))
    pos = 8
    count = struct.unpack_from(">H", data, pos)[0]
    pos += 2
    cp = [None] * count
    i = 1
    while i < count:
        tag = data[pos]
        pos += 1
        if tag == 1:
            size = struct.unpack_from(">H", data, pos)[0]
            pos += 2
            cp[i] = ("utf8", data[pos:pos + size].decode("utf-8", "replace"))
            pos += size
        elif tag in (3, 4):
            cp[i] = (tag,)
            pos += 4
        elif tag in (5, 6):
            cp[i] = (tag,)
            pos += 8
            i += 1
        elif tag in (7, 8, 16, 19, 20):
            cp[i] = (tag, struct.unpack_from(">H", data, pos)[0])
            pos += 2
        elif tag in (9, 10, 11, 12, 17, 18):
            cp[i] = (tag, *struct.unpack_from(">HH", data, pos))
            pos += 4
        elif tag == 15:
            cp[i] = (tag, data[pos], struct.unpack_from(">H", data, pos + 1)[0])
            pos += 3
        else:
            raise ValueError(f"unknown constant-pool tag {tag}")
        i += 1
    cp_end = pos

    def utf8(index):
        return cp[index][1]

    def is_system_load(index):
        entry = cp[index]
        if not entry or entry[0] != 10:
            return False
        owner = cp[entry[1]]
        name_type = cp[entry[2]]
        return (
            utf8(owner[1]) == "java/lang/System"
            and utf8(name_type[1]) == "load"
            and utf8(name_type[2]) == "(Ljava/lang/String;)V"
        )

    pos = cp_end + 6
    interfaces = struct.unpack_from(">H", data, pos)[0]
    pos += 2 + 2 * interfaces
    fields = struct.unpack_from(">H", data, pos)[0]
    pos += 2
    for _ in range(fields):
        pos += 6
        attrs = struct.unpack_from(">H", data, pos)[0]
        pos += 2
        for _ in range(attrs):
            size = struct.unpack_from(">I", data, pos + 2)[0]
            pos += 6 + size

    methods = struct.unpack_from(">H", data, pos)[0]
    pos += 2
    replaced = False
    for _ in range(methods):
        _, name_index, _, attrs = struct.unpack_from(">HHHH", data, pos)
        pos += 8
        name = utf8(name_index)
        for _ in range(attrs):
            attr_index, size = struct.unpack_from(">HI", data, pos)
            payload = pos + 6
            if name == "<clinit>" and utf8(attr_index) == "Code":
                code_size = struct.unpack_from(">I", data, payload + 4)[0]
                code_start = payload + 8
                for offset in range(code_size - 2):
                    if data[code_start + offset] != 0xB8:
                        continue
                    ref = struct.unpack_from(">H", data, code_start + offset + 1)[0]
                    if is_system_load(ref):
                        # Replace invokestatic System.load with pop; nop; nop.
                        data[code_start + offset:code_start + offset + 3] = b"\x57\x00\x00"
                        replaced = True
                        break
            pos += 6 + size
    if not replaced:
        raise RuntimeError("System.load(String) was not found in JNICLoader.<clinit>")
    target = out_dir / entry
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_bytes(data)
    return target

if __name__ == "__main__":
    ap = argparse.ArgumentParser()
    ap.add_argument("jar", type=Path)
    ap.add_argument("output_dir", type=Path)
    args = ap.parse_args()
    print(patch(args.jar, args.output_dir))
