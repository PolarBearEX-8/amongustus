#!/usr/bin/env python3
"""Audit registration-to-C mapping, renamed definitions, and decompile gaps."""

import argparse
import csv
import re
from collections import Counter
from pathlib import Path

from rename_registered_native_functions import (
    DEFAULT_MAP,
    DEFAULT_OUTPUT,
    DEFAULT_REGISTRATIONS,
    FUNCTION_MARKER,
    function_blocks,
    has_function_definition,
    identifier,
)


def read_csv(path: Path) -> list[dict[str, str]]:
    with path.open(encoding="utf-8", newline="") as stream:
        return list(csv.DictReader(stream))


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--registrations", type=Path, default=DEFAULT_REGISTRATIONS)
    parser.add_argument("--map", dest="map_path", type=Path, default=DEFAULT_MAP)
    parser.add_argument("--source", type=Path, default=DEFAULT_OUTPUT)
    args = parser.parse_args()

    registrations = read_csv(args.registrations)
    mapped = read_csv(args.map_path)
    output = args.source.read_text(encoding="utf-8", errors="replace")
    blocks = function_blocks(output)
    markers = list(FUNCTION_MARKER.finditer(output))
    errors: list[str] = []

    if len(registrations) != len(mapped):
        errors.append(f"registration/map row count differs: {len(registrations)} != {len(mapped)}")

    by_address = {int(match.group(2), 16): match.group(1) for match in markers}
    if len(by_address) != len(markers):
        errors.append("duplicate function address markers in the C view")

    expected_names = set()
    expected_references: dict[str, int] = {}
    for index, registration in enumerate(registrations):
        if index >= len(mapped):
            break
        row = mapped[index]
        address = int(registration["native_va"], 16)
        symbol = registration["ghidra_function"]
        expected_name = identifier(
            registration["owner"], registration["method_name"], registration["native_va"]
        )
        expected_names.add(expected_name)
        if row["native_va"].lower() != registration["native_va"].lower():
            errors.append(f"row {index}: address differs between registration and map")
        for field in ("owner", "method_name", "descriptor", "ghidra_function"):
            if row[field] != registration[field]:
                errors.append(f"row {index} ({registration['native_va']}): {field} differs")
        if row["semantic_function"] != expected_name:
            errors.append(f"row {index} ({registration['native_va']}): unstable semantic name")
        if by_address.get(address) != symbol:
            errors.append(f"{registration['native_va']}: matching Ghidra address marker absent")

        source_block = blocks.get(symbol, "")
        body_present = has_function_definition(source_block, expected_name)
        mapped_body_present = row["body_present_in_source"] == "yes"
        if body_present != mapped_body_present:
            errors.append(f"{registration['native_va']}: map body flag disagrees with C definition")

        expected_references[symbol] = int(row["renamed_references"])

    if len(expected_names) != len(registrations):
        errors.append("semantic function names are not unique")

    # Markers intentionally retain FUN_ addresses; code and comments outside
    # those marker lines must use the reversible registration-derived names.
    non_marker_lines = "\n".join(
        line for line in output.splitlines() if not FUNCTION_MARKER.match(line)
    )
    original_pattern = re.compile(
        r"\b(?:" + "|".join(map(re.escape, expected_references)) + r")\b"
    )
    remaining_symbols = Counter(match.group(0) for match in original_pattern.finditer(non_marker_lines))
    for symbol, count in remaining_symbols.items():
        errors.append(f"{symbol}: {count} original symbol references remain")

    semantic_to_symbol = {row["semantic_function"]: row["ghidra_function"] for row in mapped}
    semantic_pattern = re.compile(
        r"\b(?:" + "|".join(map(re.escape, semantic_to_symbol)) + r")\b"
    )
    renamed_counts = Counter(match.group(0) for match in semantic_pattern.finditer(non_marker_lines))
    for semantic_name, symbol in semantic_to_symbol.items():
        renamed_count = renamed_counts[semantic_name]
        count = expected_references[symbol]
        if renamed_count != count:
            errors.append(
                f"{symbol}: map records {count} renamed references, source has {renamed_count}"
            )

    body_count = sum(row["body_present_in_source"] == "yes" for row in mapped)
    missing = [
        f"{row['owner']}.{row['method_name']}{row['descriptor']}@{row['native_va']}"
        for row in mapped
        if row["body_present_in_source"] != "yes"
    ]
    print(f"registrations: {len(registrations)}; mapped bodies: {body_count}; missing bodies: {len(missing)}")
    for entry in missing:
        print(f"missing: {entry}")
    if errors:
        for error in errors:
            print(f"ERROR: {error}")
        return 1
    print("registration-name C view: VERIFIED")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
