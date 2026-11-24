#!/usr/bin/env python3
"""Add registration-derived names to native functions in Ghidra C output."""

import argparse
import csv
import re
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
DEFAULT_SOURCE = ROOT / "pseudocode" / "augustus-jnic.jni-api-annotated.c"
DEFAULT_REGISTRATIONS = ROOT / "metadata" / "registrations" / "augustus-jni-native-registrations.csv"
DEFAULT_OUTPUT = ROOT / "pseudocode" / "augustus-jnic.jni-api-registered-symbols.c"
DEFAULT_MAP = ROOT / "metadata" / "registrations" / "augustus-jni-registered-symbols.csv"
FUNCTION_MARKER = re.compile(
    r"^\s*/\* Function (FUN_[0-9a-fA-F]+) at ([0-9a-fA-F]+) \*/", re.MULTILINE
)


def identifier(owner: str, method: str, address: str) -> str:
    owner_part = re.sub(r"[^A-Za-z0-9_]", "_", owner)
    method_part = re.sub(r"[^A-Za-z0-9_]", "_", method).strip("_") or "method"
    address_part = re.sub(r"^0x", "", address, flags=re.IGNORECASE)
    return f"jni_{owner_part}_{method_part}_{address_part}"


def function_blocks(source: str) -> dict[str, str]:
    """Return each Ghidra function marker and the text up to the next marker."""
    markers = list(FUNCTION_MARKER.finditer(source))
    return {
        marker.group(1): source[marker.end():markers[index + 1].start() if index + 1 < len(markers) else len(source)]
        for index, marker in enumerate(markers)
    }


def has_function_definition(source_block: str, symbol: str) -> bool:
    """Distinguish a function definition from references in calls or comments."""
    # Ghidra often places the return type on one line and the function name
    # on the next. A declaration has a parameter list followed directly by
    # an opening brace; ordinary call sites end in an operator or semicolon.
    return re.search(
        rf"\b{re.escape(symbol)}\s*\([^;]*?\)\s*\{{", source_block, re.DOTALL
    ) is not None


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--source", type=Path, default=DEFAULT_SOURCE)
    parser.add_argument("--registrations", type=Path, default=DEFAULT_REGISTRATIONS)
    parser.add_argument("--output", type=Path, default=DEFAULT_OUTPUT)
    parser.add_argument("--map-output", type=Path, default=DEFAULT_MAP)
    args = parser.parse_args()

    with args.registrations.open(encoding="utf-8", newline="") as stream:
        registrations = list(csv.DictReader(stream))
    if not registrations:
        raise SystemExit("native registration table is empty")

    source_text = args.source.read_text(encoding="utf-8", errors="replace")
    blocks = function_blocks(source_text)

    symbol_to_name: dict[str, str] = {}
    rename_rows = []
    for row in registrations:
        symbol = row["ghidra_function"]
        name = identifier(row["owner"], row["method_name"], row["native_va"])
        if symbol in symbol_to_name:
            raise SystemExit(f"duplicate Ghidra function in registration map: {symbol}")
        symbol_to_name[symbol] = name
        rename_rows.append({
            "owner": row["owner"],
            "method_name": row["method_name"],
            "descriptor": row["descriptor"],
            "table_index": row["index"],
            "native_va": row["native_va"],
            "ghidra_function": symbol,
            "semantic_function": name,
            "body_present_in_source": "pending",
            "renamed_references": "pending",
        })

    pattern = re.compile(r"\b(?:" + "|".join(map(re.escape, symbol_to_name)) + r")\b")
    counts = {symbol: 0 for symbol in symbol_to_name}
    args.output.parent.mkdir(parents=True, exist_ok=True)
    with args.source.open(encoding="utf-8") as source, args.output.open(
        "w", encoding="utf-8", newline=""
    ) as output:
        output.write(
            "/* Generated registration-name view. Bodies remain Ghidra C-like pseudocode. */\n"
        )
        for line in source:
            if FUNCTION_MARKER.match(line):
                output.write(line)
                continue

            def replace(match: re.Match[str]) -> str:
                symbol = match.group(0)
                counts[symbol] += 1
                return symbol_to_name[symbol]

            output.write(pattern.sub(replace, line))

    row_by_symbol = {row["ghidra_function"]: row for row in rename_rows}
    for symbol, count in counts.items():
        row_by_symbol[symbol]["body_present_in_source"] = (
            "yes" if has_function_definition(blocks.get(symbol, ""), symbol) else "no"
        )
        row_by_symbol[symbol]["renamed_references"] = count

    with args.map_output.open("w", encoding="utf-8", newline="") as stream:
        writer = csv.DictWriter(stream, fieldnames=rename_rows[0].keys(), lineterminator="\n")
        writer.writeheader()
        writer.writerows(rename_rows)
    print(
        f"mapped {len(rename_rows)} registered native functions; "
        f"recovered {sum(row['body_present_in_source'] == 'yes' for row in rename_rows)} function bodies; "
        f"updated {sum(counts.values())} symbol references"
    )
    print(f"wrote registration-name pseudocode to {args.output}")
    print(f"wrote reversible name map to {args.map_output}")


if __name__ == "__main__":
    main()
