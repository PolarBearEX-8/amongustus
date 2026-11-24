#!/usr/bin/env python3
"""Count JNI function-table calls in an address range of a PE binary."""

import argparse
import csv
import re
import subprocess
import sys
from collections import Counter
from pathlib import Path


REG = r"(?:ax|bx|cx|dx|si|di|bp|sp|[89]|1[0-5])"
DIRECT_CALL_RE = re.compile(rf"call\s+\*0x([0-9a-f]+)\(%r{REG}\)")
REGISTER_CALL_RE = re.compile(rf"call\s+\*%r({REG})\b")
TABLE_LOAD_RE = re.compile(rf"mov\s+0x([0-9a-f]+)\(%r{REG}\),%r({REG})\b")
REGISTER_COPY_RE = re.compile(rf"mov\s+%r({REG}),%r({REG})\b")


def load_offsets(path):
    with path.open(newline="", encoding="utf-8") as stream:
        return {int(row["byte_offset"], 0): row["jni_function"] for row in csv.DictReader(stream)}


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("binary", type=Path)
    parser.add_argument("start", type=lambda value: int(value, 0))
    parser.add_argument("stop", type=lambda value: int(value, 0))
    parser.add_argument("--offsets", type=Path, default=Path(__file__).resolve().parents[1] / "metadata" / "jni-function-offsets.csv")
    args = parser.parse_args()
    result = subprocess.run(
        ["objdump", "-d", f"--start-address={args.start:#x}", f"--stop-address={args.stop:#x}", str(args.binary)],
        check=True,
        capture_output=True,
        text=True,
    )
    offsets = load_offsets(args.offsets)
    counts = Counter()
    loaded_table_offsets = {}
    for line in result.stdout.splitlines():
        table_load = TABLE_LOAD_RE.search(line)
        register_copy = REGISTER_COPY_RE.search(line)
        if table_load:
            offset, destination = table_load.groups()
            loaded_table_offsets[destination] = int(offset, 16) if int(offset, 16) in offsets else None
        elif register_copy:
            source, destination = register_copy.groups()
            loaded_table_offsets[destination] = loaded_table_offsets.get(source)

        direct_call = DIRECT_CALL_RE.search(line)
        if direct_call:
            counts[int(direct_call.group(1), 16)] += 1
            continue
        register_call = REGISTER_CALL_RE.search(line)
        if register_call:
            offset = loaded_table_offsets.get(register_call.group(1))
            if offset is not None:
                counts[offset] += 1
            loaded_table_offsets.pop(register_call.group(1), None)
    writer = csv.writer(sys.stdout, lineterminator="\n")
    writer.writerow(["table_offset", "jni_function", "calls"])
    for offset, count in counts.most_common():
        writer.writerow([f"+0x{offset:x}", offsets.get(offset, "unmapped"), count])


if __name__ == "__main__":
    main()
