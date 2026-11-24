#!/usr/bin/env python3
"""Decode launcher/41 and launcher/46z JNI rows from JNI_OnLoad output."""
import argparse
import csv
import re
import struct
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
DEFAULT_STATE = ROOT / "metadata" / "electric-launcher-jnic-state.bin"
DEFAULT_C = ROOT / "pseudocode" / "electric-launcher-jnic.ghidra.c"
DEFAULT_METHODS = ROOT / "metadata" / "launcher-jni-methods.csv"
DEFAULT_OUTPUT = ROOT / "metadata" / "registrations" / "electric-launcher-jni-native-registrations.csv"

def u16(n): return struct.pack("<H", n)
def u32(n): return struct.pack("<I", n)
def u64(n): return struct.pack("<Q", n)
def words(*items): return b"".join(items)
def mask(state, offset, key):
    return bytes(state[offset + i] ^ value for i, value in enumerate(key))

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--state", type=Path, default=DEFAULT_STATE)
    ap.add_argument("--ghidra-c", type=Path, default=DEFAULT_C)
    ap.add_argument("--methods", type=Path, default=DEFAULT_METHODS)
    ap.add_argument("--output", type=Path, default=DEFAULT_OUTPUT)
    args = ap.parse_args()
    state = args.state.read_bytes()
    if len(state) != 0x4c92:
        raise SystemExit(f"unexpected state size: {len(state)}")

    # Each row's masks and function pointer are taken from its loader stack table.
    spec = [
        ("launcher/41", 0, 0x32d4, bytes([0x73,0xd0]), 0x32d6,
         words(u64(0xdaea76d39a786e83),u32(0xdfc57802),u32(0xaecee31c),u64(0x9d799224a0be4761)),
         "(J[B)Ljava/util/HashMap;", "0x18004a8c0"),
        ("launcher/41", 1, 0x32ee, bytes([0x8c,0xe4]), 0x32f0,
         words(u64(0x8c0a4a25d9bdf968),u32(0x5b0d57e7),u32(0x6d5488f2),
               bytes.fromhex("f5b07b97ed17"),u16(0xc92e),bytes.fromhex("a4b98df3a900")),
         "(SLjava/io/InputStream;ISI)[B", "0x180068f20"),
        ("launcher/41", 2, 0x330d, bytes([0x6a,0xef]), 0x330f,
         u32(0x73479736), "(J)V", "0x1800a1420"),
        ("launcher/41", 3, 0x3313, bytes([0xf7,0x0b]), 0x3315,
         words(u64(0x5a3e60508f93a978),u32(0x86ebb4bf),u32(0xf61dc8e1),bytes([0xfe,0x54])),
         "(Ljava/io/File;J)V", "0x1800eba90"),
        ("launcher/41", 4, 0x3327, bytes([0xcd,0xa6,0xaa]), 0x332a,
         bytes([0xfe,0xfe,0xd6]), "()V", "0x1800fa7c0"),
        ("launcher/41", 5, 0x332d, bytes([0xa8,0x76]), 0x332f,
         words(u64(0x9ffe8bc566e7f8e4),u64(0x8629828f0e773d5c),
               u64(0x6c0e587ce5280ec4),u64(0x861ecd24067d693d),
               u64(0x870c260a38fdd1f5),u32(0xf66e803c),u32(0x8afd853f),
               bytes.fromhex("ffa676"),u32(0x9f79811a),u32(0x1d11ef4f),
               bytes.fromhex("9b6be5")),
         "([Ljava/lang/Object;[Ljava/lang/Object;ISS)[Ljava/lang/Object;", "0x1800fb230"),
        ("launcher/46z", 0, 0x1484, bytes([0x1f,0x32]), 0x1486,
         words(u64(0x92de54c1ad421a5f),u64(0x20dda1000fd7fc12),
               u64(0x9d85f69c86905d7f),u64(0x12d9079acb6411dd),
               u64(0x1daedf76d7f7267a),u64(0x5e1464fac4986cc5)),
         "(Llauncher/44y;SLlauncher/44y;IIC)Llauncher/44y;", "0x18002ac50"),
        ("launcher/46z", 1, 0x14b6, bytes([0x3d,0x84]), 0x14b8,
         words(u64(0x7d0d3a8fbf63a821),u64(0xdbf338c24b8b58a9),
               u64(0x778ff09b44477d76),u64(0xb25ffef5a12dc976),bytes([0x16])),
         "(JLlauncher/46Y;Llauncher/44y;I)V", "0x180030d00"),
        ("launcher/46z", 2, 0x14d9, bytes([0x57,0x8d]), 0x14db,
         words(u64(0x32bf0896e437ad1e),u64(0xeee45e1d01effbc8),
               u64(0xf641a97af2844048),u32(0x14cb224b),
               u16(0x1909),u16(0x5da2),u16(0x48b6),u32(0x31e0096b),
               u16(0x1ca5),u16(0x7e94),bytes.fromhex("04b6a4")),
         "(Ljava/util/ArrayList;J)Ljava/util/ArrayList;", "0x180033360"),
        ("launcher/44p", 0, 0x58c, bytes([0x1f,0x07]), 0x58e,
         words(u64(0x85d0020b0545e6b4),u32(0x3c14acf7),u32(0xd5788995),
               bytes.fromhex("7488e3af8fff")),
         "(JLjava/lang/String;)V", "0x180001000"),
        ("launcher/44p", 1, 0x5a4, bytes([0x41,0xb7]), 0x5a6,
         words(u32(0x2c9a84bc),u32(0xd897d40d),u32(0x47eba9d3),
               u32(0x80e6ddd6),bytes.fromhex("0799e6a9784c")),
         "(JLjava/lang/String;)V", "0x180029b20"),
        ("launcher/46e", 0, 0x3949, bytes([0x39,0xf4]), 0x394b,
         words(u32(0xff6177be),u32(0x168b06fd),u32(0xa646bfff),
               u32(0x58650d76),u64(0x9fb339724ca48620),
               u64(0xb9383ee499fa4de8)),
         "(JLlauncher/463;)[Llauncher/46g;", "0x180103600"),
    ]
    c_text=args.ghidra_c.read_text()
    declared={(r["class"].replace(".","/"),r["method"],r["descriptor"])
              for r in csv.DictReader(args.methods.open())}
    output=[]
    for owner,order,noff,nmask,doff,dmask,expected,va in spec:
        name=mask(state,noff,nmask).split(b"\0",1)[0].decode("ascii")
        raw_desc=mask(state,doff,dmask)
        descriptor=expected
        if raw_desc[:len(expected)] != expected.encode("ascii"):
            raise SystemExit(f"descriptor mismatch at {owner} row {order}: {raw_desc!r}")
        if (owner,name,descriptor) not in declared:
            raise SystemExit(f"no bytecode declaration for {owner}.{name}{descriptor}")
        fn="FUN_"+va[2:]
        marker=f"/* Function {fn} at {va[2:]} */"
        line=c_text[:c_text.index(marker)].count("\n")+1
        output.append(dict(owner=owner,table_order=order,method_name=name,
                           descriptor=descriptor,native_va=va,ghidra_function=fn,
                           ghidra_c_line=line,mapping_status="verified against release bytecode"))
    pending=[]
    with args.output.open("w",newline="") as f:
        w=csv.DictWriter(f,fieldnames=["owner","table_order","method_name","descriptor",
          "native_va","ghidra_function","ghidra_c_line","mapping_status"],lineterminator="\n")
        w.writeheader(); w.writerows(output)
    print(f"wrote {len(output)-len(pending)} mapped rows and {len(pending)} pending rows to {args.output}")

if __name__=="__main__": main()
