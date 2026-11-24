#!/usr/bin/env python3
"""Recover direct name/descriptor strings passed to JNI GetMethodID calls."""

import argparse
import csv
import re
import subprocess
import sys
from collections import Counter, defaultdict
from pathlib import Path


REGISTER = r"(?:ax|bx|cx|dx|si|di|bp|sp|[89]|1[0-5])"
CALL_RE = re.compile(rf"call\s+\*0x108\(%r{REGISTER}\)")
LEA_RE = re.compile(r"lea\s+.*,%(r8|r9)\s+#\s*(0x[0-9a-f]+)")
DIRECT_CALL_RE = re.compile(r"call\s+(0x[0-9a-f]+)")
SECTION_RE = re.compile(
    r"^\s*\d+\s+\.rdata\s+([0-9a-f]+)\s+([0-9a-f]+)\s+[0-9a-f]+\s+([0-9a-f]+)", re.M
)
ADDRESS_RE = re.compile(r"^\s*([0-9a-f]+):")


def objdump(*args):
    return subprocess.run(["objdump", *args], check=True, capture_output=True, text=True).stdout


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("binary", type=Path)
    parser.add_argument("start", type=lambda value: int(value, 0))
    parser.add_argument("stop", type=lambda value: int(value, 0))
    parser.add_argument(
        "--ghidra-c",
        type=Path,
        default=Path(__file__).resolve().parents[1] / "pseudocode" / "augustus-jnic.jni-api-all-annotated.c",
        help="Ghidra C output used to map class-ref helpers to FindClass names",
    )
    parser.add_argument(
        "--unresolved-out",
        type=Path,
        help="optionally write unresolved callsites with any recovered name/descriptor",
    )
    args = parser.parse_args()

    section = SECTION_RE.search(objdump("-h", str(args.binary)))
    if not section:
        parser.error("could not locate the PE .rdata section")
    section_size, section_va, section_offset = (int(value, 16) for value in section.groups())
    binary = args.binary.read_bytes()

    def read_c_string(address):
        offset = section_offset + address - section_va
        if not section_offset <= offset < section_offset + section_size:
            return None
        end = binary.find(b"\0", offset, min(section_offset + section_size, offset + 2048))
        if end < 0:
            return None
        return binary[offset:end].decode("ascii", "backslashreplace")

    listing = objdump(
        "-d", f"--start-address={args.start:#x}", f"--stop-address={args.stop:#x}", str(args.binary)
    )
    listing_lines = listing.splitlines()
    ghidra = args.ghidra_c.read_text(encoding="utf-8", errors="replace")
    functions = list(re.finditer(r"/\* Function FUN_([0-9a-f]+) at ([0-9a-f]+) \*/", ghidra))
    helper_classes = {}
    for index, function in enumerate(functions):
        end = functions[index + 1].start() if index + 1 < len(functions) else len(ghidra)
        body = ghidra[function.start():end]
        class_match = re.search(r'FindClass[^\n]*?"([^"]+)"', body)
        if class_match:
            helper_classes[int(function.group(1), 16)] = class_match.group(1)

    registers = {"r8": None, "r9": None}
    counts = Counter()
    samples = defaultdict(list)
    unresolved = 0
    unresolved_rows = []
    for line_index, line in enumerate(listing_lines):
        match = LEA_RE.search(line)
        if match:
            registers[match.group(1)] = int(match.group(2), 16)
        if not CALL_RE.search(line):
            continue
        name = read_c_string(registers["r8"]) if registers["r8"] is not None else None
        descriptor = read_c_string(registers["r9"]) if registers["r9"] is not None else None
        address_match = ADDRESS_RE.match(line)
        helper_address = None
        for previous_line in reversed(listing_lines[max(0, line_index - 12):line_index]):
            helper_match = DIRECT_CALL_RE.search(previous_line)
            if helper_match:
                helper_address = int(helper_match.group(1), 16)
                break
        lookup_class = helper_classes.get(helper_address)
        if name is None or descriptor is None or address_match is None or lookup_class is None:
            unresolved += 1
            if address_match is not None:
                unresolved_rows.append(
                    [
                        f"0x{int(address_match.group(1), 16):x}",
                        lookup_class or "",
                        f"0x{helper_address:x}" if helper_address is not None else "",
                        name or "",
                        descriptor or "",
                    ]
                )
        else:
            key = (lookup_class, name, descriptor, helper_address)
            counts[key] += 1
            if len(samples[key]) < 4:
                samples[key].append(f"0x{int(address_match.group(1), 16):x}")
        registers = {"r8": None, "r9": None}

    writer = csv.writer(sys.stdout, lineterminator="\n")
    writer.writerow(["lookup_class", "method_name", "descriptor", "class_helper_va", "callsite_count", "sample_callsite_addresses"])
    for (lookup_class, name, descriptor, helper_address), count in counts.most_common():
        key = (lookup_class, name, descriptor, helper_address)
        writer.writerow([lookup_class, name, descriptor, f"0x{helper_address:x}", count, ";".join(samples[key])])
    if args.unresolved_out:
        with args.unresolved_out.open("w", newline="", encoding="utf-8") as stream:
            unresolved_writer = csv.writer(stream, lineterminator="\n")
            unresolved_writer.writerow(
                ["callsite_va", "lookup_class", "class_helper_va", "method_name", "descriptor"]
            )
            unresolved_writer.writerows(unresolved_rows)
    print(f"resolved GetMethodID callsites={sum(counts.values())}; unresolved={unresolved}", file=sys.stderr)


if __name__ == "__main__":
    main()
