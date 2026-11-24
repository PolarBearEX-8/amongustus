#!/usr/bin/env python3
"""Add verified Java JNI signatures above their native C-like function bodies."""

import argparse
import csv
import re
from pathlib import Path


FUNCTION_MARKER = re.compile(r"/\* Function (\S+) at ([0-9a-fA-F]+) \*/")
JNI_CALL = re.compile(r"\(\*\*\(code \*\*\)\(\*param_1 \+ (0x[0-9a-fA-F]+)\)\)")


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("ghidra_c", type=Path)
    parser.add_argument("registrations", type=Path)
    parser.add_argument("output", type=Path)
    parser.add_argument(
        "--jni-offsets",
        type=Path,
        help="JNIEnv function-table byte offsets; when omitted only JNI registration signatures are added",
    )
    parser.add_argument(
        "--include-helper-calls",
        action="store_true",
        help="also label matching JNI table calls in non-registered helper functions",
    )
    args = parser.parse_args()

    registrations = {}
    with args.registrations.open(newline="", encoding="utf-8") as stream:
        for row in csv.DictReader(stream):
            address = int(row["native_va"], 16)
            registrations[address] = row

    jni_names = {}
    if args.jni_offsets and args.jni_offsets.exists():
        with args.jni_offsets.open(newline="", encoding="utf-8") as stream:
            for row in csv.DictReader(stream):
                jni_names[int(row["byte_offset"], 16)] = row["jni_function"]

    args.output.parent.mkdir(parents=True, exist_ok=True)
    annotated = 0
    annotated_calls = 0
    in_jni_function = False
    with args.ghidra_c.open(encoding="utf-8", errors="replace") as source, args.output.open(
        "w", encoding="utf-8"
    ) as target:
        for line in source:
            match = FUNCTION_MARKER.search(line)
            if match:
                row = registrations.get(int(match.group(2), 16))
                in_jni_function = row is not None
                if row:
                    target.write(
                        "/* JNI registration: "
                        f"{row['owner']}.{row['method_name']}{row['descriptor']} "
                        f"(index {row['index']}) */\n"
                    )
                    annotated += 1
            if (in_jni_function or args.include_helper_calls) and jni_names:
                def label_call(call):
                    nonlocal annotated_calls
                    offset = int(call.group(1), 16)
                    name = jni_names.get(offset)
                    if name is None:
                        return call.group(0)
                    annotated_calls += 1
                    return f"/* JNI {name} at +{offset:#x} */ {call.group(0)}"

                line = JNI_CALL.sub(label_call, line)
            target.write(line)

    print(
        f"annotated {annotated}/{len(registrations)} JNI function bodies and "
        f"{annotated_calls} JNIEnv calls -> {args.output}"
    )


if __name__ == "__main__":
    main()
