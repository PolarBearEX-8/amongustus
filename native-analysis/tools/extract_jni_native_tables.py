#!/usr/bin/env python3
"""Extract RegisterNatives name/descriptor/function triples from a PE DLL."""

import argparse
import csv
import re
import struct
from pathlib import Path


TABLES = {
    # Tables and counts come from the matching Java_*jnicLoader Ghidra bodies.
    "net/47R3": (0x180494160, 28),
    "net/40U": (0x18049D5C0, 78),
    "net/47bj": (0x1804A18F0, 45),
    "net/47Uj": (0x1804A3B90, 19),
}

# This loader builds its three JNINativeMethod records on the stack instead of
# copying a contiguous static table. Values are read from its Ghidra body.
INLINE_TABLES = {
    "net/47Ua": [
        (0x18049D283, 0x180493CCD, 0x180298D70),
        (0x18049D2DE, "(JLnet/47d7;)V", 0x180333830),
        (0x180493D7A, "(CIC)V", 0x1803DB540),
    ]
}


class PEImage:
    def __init__(self, path: Path):
        self.data = path.read_bytes()
        pe = struct.unpack_from("<I", self.data, 0x3C)[0]
        if self.data[pe : pe + 4] != b"PE\0\0":
            raise ValueError(f"not a PE image: {path}")
        section_count = struct.unpack_from("<H", self.data, pe + 6)[0]
        optional_size = struct.unpack_from("<H", self.data, pe + 20)[0]
        optional = pe + 24
        magic = struct.unpack_from("<H", self.data, optional)[0]
        if magic != 0x20B:
            raise ValueError("expected PE32+ image")
        self.image_base = struct.unpack_from("<Q", self.data, optional + 24)[0]
        section_table = optional + optional_size
        self.sections = []
        for i in range(section_count):
            off = section_table + i * 40
            virtual_size, virtual_address, raw_size, raw_offset = struct.unpack_from(
                "<IIII", self.data, off + 8
            )
            self.sections.append(
                (virtual_address, max(virtual_size, raw_size), raw_offset)
            )

    def offset(self, va: int) -> int:
        rva = va - self.image_base
        for start, size, raw_offset in self.sections:
            if start <= rva < start + size:
                return raw_offset + rva - start
        raise ValueError(f"VA is not backed by a PE section: 0x{va:x}")

    def c_string(self, va: int) -> str:
        start = self.offset(va)
        end = self.data.find(b"\0", start, start + 4096)
        if end < 0:
            raise ValueError(f"unterminated string at 0x{va:x}")
        return self.data[start:end].decode("utf-8", "replace")

    def registrations(self):
        for owner, table in TABLES.items():
            table_va, count = table
            table_offset = self.offset(table_va)
            for index in range(count):
                name_va, descriptor_va, function_va = struct.unpack_from(
                    "<QQQ", self.data, table_offset + index * 24
                )
                yield {
                    "owner": owner,
                    "index": index,
                    "method_name": self.c_string(name_va),
                    "descriptor": self.c_string(descriptor_va),
                    "native_va": f"0x{function_va:x}",
                    "table_va": f"0x{table_va:x}",
                }
        for owner, entries in INLINE_TABLES.items():
            for index, (name_va, descriptor, function_va) in enumerate(entries):
                descriptor_value = (
                    self.c_string(descriptor) if isinstance(descriptor, int) else descriptor
                )
                yield {
                    "owner": owner,
                    "index": index,
                    "method_name": self.c_string(name_va),
                    "descriptor": descriptor_value,
                    "native_va": f"0x{function_va:x}",
                    "table_va": "stack-built in Java_net_47Ua__00024jnicLoader",
                }


def main():
    root = Path(__file__).resolve().parents[1]
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("dll", type=Path)
    parser.add_argument("output", type=Path)
    parser.add_argument(
        "--functions",
        type=Path,
        default=root / "metadata" / "augustus.functions.tsv",
        help="Ghidra function inventory used to attach pseudocode names",
    )
    parser.add_argument(
        "--ghidra-c",
        type=Path,
        default=root / "pseudocode" / "augustus-jnic.jni-api-annotated.c",
        help="Ghidra pseudocode used to attach line numbers",
    )
    parser.add_argument(
        "--crosscheck",
        type=Path,
        default=root / "metadata" / "function-address-crosscheck.csv",
        help="also attach JNI signatures to the native function cross-check",
    )
    args = parser.parse_args()

    function_names = {}
    if args.functions.exists():
        for line in args.functions.read_text(encoding="utf-8").splitlines():
            fields = line.split("\t")
            if len(fields) >= 2:
                function_names[int(fields[0], 16)] = fields[1]

    function_lines = {}
    if args.ghidra_c.exists():
        marker = re.compile(r"/\* Function (\S+) at ([0-9a-fA-F]+) \*/")
        for line_number, line in enumerate(
            args.ghidra_c.read_text(encoding="utf-8", errors="replace").splitlines(), 1
        ):
            match = marker.search(line)
            if match:
                function_lines[int(match.group(2), 16)] = line_number

    registrations = list(PEImage(args.dll).registrations())
    for row in registrations:
        address = int(row["native_va"], 16)
        row["ghidra_function"] = function_names.get(address, "")
        row["ghidra_c_line"] = function_lines.get(address, "")
    args.output.parent.mkdir(parents=True, exist_ok=True)
    with args.output.open("w", newline="", encoding="utf-8") as stream:
        writer = csv.DictWriter(stream, fieldnames=registrations[0].keys(), lineterminator="\n")
        writer.writeheader()
        writer.writerows(registrations)
    print(f"wrote {len(registrations)} JNI registrations to {args.output}")

    if args.crosscheck.exists():
        with args.crosscheck.open(newline="", encoding="utf-8") as stream:
            reader = csv.DictReader(stream)
            fieldnames = list(reader.fieldnames or [])
            rows = list(reader)
        jni_columns = ["jni_owner", "jni_method", "jni_descriptor", "jni_table_index"]
        for column in jni_columns:
            if column not in fieldnames:
                fieldnames.append(column)
        by_address = {row["native_va"].lower(): row for row in registrations}
        for row in rows:
            registration = (
                by_address.get(row["address"].lower())
                if row["binary"] == "augustus-jnic.dll"
                else None
            )
            row["jni_owner"] = registration["owner"] if registration else ""
            row["jni_method"] = registration["method_name"] if registration else ""
            row["jni_descriptor"] = registration["descriptor"] if registration else ""
            row["jni_table_index"] = registration["index"] if registration else ""
        with args.crosscheck.open("w", newline="", encoding="utf-8") as stream:
            writer = csv.DictWriter(stream, fieldnames=fieldnames, lineterminator="\n")
            writer.writeheader()
            writer.writerows(rows)
        linked = sum(bool(row["jni_owner"]) for row in rows)
        print(f"attached JNI signatures to {linked} rows in {args.crosscheck}")


if __name__ == "__main__":
    main()
