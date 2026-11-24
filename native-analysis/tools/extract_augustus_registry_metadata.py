#!/usr/bin/env python3
"""Extract UTF-16 strings initialized by native net/47R3.a()V."""

import argparse
import csv
import re
import struct
from pathlib import Path


FUNCTION = "/* Function FUN_1800110e0 at 1800110e0 */"
STACK_NAME = r"(?:uStack|local)_[0-9a-f]+"
DECLARATION = re.compile(
    rf"^\s*(undefined(?:2|4|6|8)?|longlong)\s+({STACK_NAME});", re.MULTILINE
)
ASSIGNMENT = re.compile(rf"\b({STACK_NAME})\s*=\s*(0x[0-9a-fA-F]+|\d+)\s*;")
NEW_STRING = re.compile(
    r"(\w+)\s*=\s*\(\*\*\(code \*\*\)\(\*param_1\s*\+\s*0x518\)\s*\)\s*\("
)
ARRAY_STORE = re.compile(
    r"\(\*\*\(code \*\*\)\(\*param_1\s*\+\s*0x570\)\)\s*"
    r"\(\s*param_1,\s*(\w+),\s*(0x[0-9a-fA-F]+|\d+),\s*(\w+)\)",
    re.DOTALL,
)
MASK_TABLE_VA = 0x1804A6488
REGISTRY_SLOT_COUNT = 38


def extract_mask_offsets(image: bytes) -> list[dict[str, int | str]]:
    pe_offset = struct.unpack_from("<I", image, 0x3C)[0]
    if image[pe_offset:pe_offset + 4] != b"PE\0\0":
        raise ValueError("input is not a PE image")
    section_count = struct.unpack_from("<H", image, pe_offset + 6)[0]
    optional_size = struct.unpack_from("<H", image, pe_offset + 20)[0]
    optional = pe_offset + 24
    if struct.unpack_from("<H", image, optional)[0] != 0x20B:
        raise ValueError("expected a PE32+ image")
    image_base = struct.unpack_from("<Q", image, optional + 24)[0]
    section_table = optional + optional_size
    rva = MASK_TABLE_VA - image_base
    file_offset = None
    for section_index in range(section_count):
        section = section_table + section_index * 40
        virtual_size, virtual_address, raw_size, raw_offset = struct.unpack_from(
            "<IIII", image, section + 8
        )
        if virtual_address <= rva < virtual_address + max(virtual_size, raw_size):
            file_offset = raw_offset + rva - virtual_address
            break
    if file_offset is None or file_offset + 63 * 8 > len(image):
        raise ValueError(f"could not map mask table VA {MASK_TABLE_VA:#x} to file data")
    stored_values = struct.unpack_from("<63Q", image, file_offset)
    offsets = [
        (value & 0xFFFFFFFF) - 0x100000000
        if value & 0x80000000
        else value & 0xFFFFFFFF
        for value in stored_values
    ]
    return [
        {"native_va": f"{MASK_TABLE_VA + index * 8:#x}", "table_index": index, "signed_offset_i32": value}
        for index, value in enumerate(offsets)
    ]


def extract(source: str) -> list[dict[str, str | int]]:
    start = source.find(FUNCTION)
    if start < 0:
        raise ValueError(f"missing native function marker: {FUNCTION}")
    end = source.find("/* Function FUN_", start + len(FUNCTION))
    function = source[start:end if end >= 0 else None]
    declaration_end = function.find("pcVar1 =")
    if declaration_end < 0:
        raise ValueError("could not locate function body after local declarations")

    sizes: dict[str, int] = {}
    for type_name, local_name in DECLARATION.findall(function[:declaration_end]):
        size = int(type_name.removeprefix("undefined")) if type_name.startswith("undefined") else 8
        sizes[local_name] = size
    offsets = {name: -int(name.split("_")[1], 16) for name in sizes}

    events: list[tuple[int, str, re.Match[str]]] = []
    events.extend((m.start(), "assignment", m) for m in ASSIGNMENT.finditer(function))
    events.extend((m.start(), "new_string", m) for m in NEW_STRING.finditer(function))
    events.sort(key=lambda event: event[0])

    memory: dict[int, int] = {}
    new_strings: list[tuple[int, str, list[int]]] = []
    for position, kind, match in events:
        if kind == "assignment":
            name, value_text = match.groups()
            if name not in sizes:
                raise ValueError(f"unknown stack local at offset {position}: {name}")
            size = sizes[name]
            value = int(value_text, 0) & ((1 << (size * 8)) - 1)
            for byte_index, byte in enumerate(value.to_bytes(size, "little")):
                memory[offsets[name] + byte_index] = byte
            continue

        output_name = match.group(1)
        semicolon = function.find(";", match.end())
        call = function[match.start():semicolon + 1]
        arguments = re.search(
            rf"\(\s*param_1,\s*&({STACK_NAME}),\s*(0x[0-9a-fA-F]+|\d+)\s*\)",
            call,
            re.DOTALL,
        )
        if not arguments:
            raise ValueError(f"could not parse NewString arguments at offset {position}")
        local_name, length_text = arguments.groups()
        length = int(length_text, 0)
        if local_name not in offsets:
            raise ValueError(f"unknown NewString buffer at offset {position}: {local_name}")
        raw = bytes(memory.get(offsets[local_name] + i, 0) for i in range(length * 2))
        code_units = [int.from_bytes(raw[i:i + 2], "little") for i in range(0, len(raw), 2)]
        new_strings.append((position, output_name, code_units))

    rows: list[dict[str, str | int]] = []
    for index, (position, output_name, code_units) in enumerate(new_strings):
        next_position = new_strings[index + 1][0] if index + 1 < len(new_strings) else len(function)
        tail = function[position:next_position]
        store = next(
            (m for m in ARRAY_STORE.finditer(tail) if m.group(3) == output_name),
            None,
        )
        if store is None:
            raise ValueError(f"NewString result {output_name} at {position} has no array store")
        array_name, index_text, _ = store.groups()
        field = {"uVar4": "e", "uVar5": "f"}.get(array_name)
        if field is None:
            raise ValueError(f"unmapped metadata array {array_name} at {position}")
        rows.append({
            "native_function": "net/47R3.a()V",
            "array_field": field,
            "index": int(index_text, 0),
            "related_e_class_index": "",
            "utf16_code_units_hex": " ".join(f"{unit:04x}" for unit in code_units),
            "length": len(code_units),
            "initializer_offset_in_decompiler_text": position,
        })

    stores = []
    for match in ARRAY_STORE.finditer(function):
        array_name, index_text, _ = match.groups()
        field = {"uVar4": "e", "uVar5": "f"}.get(array_name)
        if field is None:
            raise ValueError(f"unmapped metadata array {array_name} at {match.start()}")
        stores.append((field, int(index_text, 0)))
    e_slots = {index for field, index in stores if field == "e"}
    f_slots = {index for field, index in stores if field == "f"}
    e_string_slots = {int(row["index"]) for row in rows if row["array_field"] == "e"}
    e_class_slots = set(range(REGISTRY_SLOT_COUNT)) - e_string_slots
    if e_slots != set(range(REGISTRY_SLOT_COUNT)):
        raise ValueError(f"expected all {REGISTRY_SLOT_COUNT} e slots, found {sorted(e_slots)}")
    if f_slots != e_class_slots:
        raise ValueError(
            f"f class-name slots {sorted(f_slots)} do not match non-string e slots {sorted(e_class_slots)}"
        )
    for row in rows:
        if row["array_field"] == "f":
            row["related_e_class_index"] = row["index"]
    return rows


def main() -> None:
    script = Path(__file__).resolve()
    root = script.parents[1]
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--ghidra-c", type=Path, default=root / "pseudocode/augustus-jnic.jni-api-all-annotated.c")
    parser.add_argument("--dll", type=Path, required=True, help="matching Augustus native DLL; input is not included")
    parser.add_argument("--output", type=Path, default=root / "metadata/registry-native-metadata-strings.csv")
    parser.add_argument("--mask-output", type=Path, default=root / "metadata/registry-native-key-mask-offsets.csv")
    args = parser.parse_args()

    rows = extract(args.ghidra_c.read_text(encoding="utf-8"))
    args.output.parent.mkdir(parents=True, exist_ok=True)
    with args.output.open("w", newline="", encoding="utf-8") as stream:
        writer = csv.DictWriter(stream, fieldnames=rows[0].keys(), lineterminator="\n")
        writer.writeheader()
        writer.writerows(rows)
    print(f"wrote {len(rows)} initialized strings to {args.output}")

    offsets = extract_mask_offsets(args.dll.read_bytes())
    args.mask_output.parent.mkdir(parents=True, exist_ok=True)
    with args.mask_output.open("w", newline="", encoding="utf-8") as stream:
        writer = csv.DictWriter(stream, fieldnames=offsets[0].keys(), lineterminator="\n")
        writer.writeheader()
        writer.writerows(offsets)
    print(f"wrote {len(offsets)} key-mask offsets to {args.mask_output}")


if __name__ == "__main__":
    main()
