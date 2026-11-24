#!/usr/bin/env python3
"""Extract 64-bit JNIEnv function-table offsets from a JDK jni.h."""

import argparse
import csv
import re
from pathlib import Path


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("jni_header", type=Path)
    parser.add_argument("output", type=Path)
    parser.add_argument("--pointer-size", type=int, default=8)
    args = parser.parse_args()

    header = args.jni_header.read_text(encoding="utf-8", errors="replace")
    start = header.index("struct JNINativeInterface_ {")
    end = header.index("\n};", start)
    interface = header[start:end]
    names = ["reserved0", "reserved1", "reserved2", "reserved3"]
    names.extend(re.findall(r"JNICALL\s+\*([A-Za-z_][A-Za-z0-9_]*)", interface))

    args.output.parent.mkdir(parents=True, exist_ok=True)
    with args.output.open("w", newline="", encoding="utf-8") as stream:
        writer = csv.writer(stream, lineterminator="\n")
        writer.writerow(("slot", "byte_offset", "jni_function"))
        for slot, name in enumerate(names):
            writer.writerow((slot, f"0x{slot * args.pointer_size:x}", name))
    print(f"wrote {len(names)} JNIEnv slots to {args.output}")


if __name__ == "__main__":
    main()
