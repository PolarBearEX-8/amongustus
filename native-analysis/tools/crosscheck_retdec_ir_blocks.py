#!/usr/bin/env python3
"""Match Rizin basic-block starts against RetDec LLVM IR address labels."""

import argparse
import csv
import json
import re
from pathlib import Path


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--rizin-blocks", required=True, type=Path)
    parser.add_argument("--ghidra-summary", required=True, type=Path)
    parser.add_argument("--llvm-ir", required=True, type=Path)
    parser.add_argument("--function-address", required=True)
    parser.add_argument("--out", required=True, type=Path)
    parser.add_argument("--summary", required=True, type=Path)
    args = parser.parse_args()

    address = int(args.function_address, 0)
    address_hex = f"{address:x}"
    ir = args.llvm_ir.read_text(errors="replace")
    function = re.search(
        rf"^define .*@function_{address_hex}\([^\n]*\)[^\n]*\{{\n(.*?)^\}}",
        ir,
        re.MULTILINE | re.DOTALL,
    )
    if function is None:
        raise SystemExit(f"LLVM IR function function_{address_hex} not found")

    body = function.group(1)
    all_label_defs = set(re.findall(r"^(?:; <label>:)?([\w.$-]+):", body, re.MULTILINE))
    all_label_refs = set(re.findall(r"label %([\w.$-]+)", body))
    labels = {
        int(match.group(1), 16): (match.start(), match.end())
        for match in re.finditer(r"^dec_label_pc_(180[0-9a-f]+):", body, re.MULTILINE)
    }
    ghidra = {
        int(row["region"]): row["file"]
        for row in csv.DictReader(args.ghidra_summary.open(newline=""), delimiter="\t")
    }
    blocks = list(csv.DictReader(args.rizin_blocks.open(newline="")))
    rows = []
    for block in blocks:
        block_address = int(block["address"], 16)
        ir_label = labels.get(block_address)
        ir_offset = ir_label[0] if ir_label is not None else None
        ir_line = body.count("\n", 0, ir_offset) + 1 if ir_offset is not None else ""
        rows.append({
            "region_index": block["region_index"],
            "rizin_address": f"0x{block_address:x}",
            "ghidra_slice": ghidra.get(int(block["region_index"]), ""),
            "size": block["size"],
            "instruction_count": block["instruction_count"],
            "ir_label_present": "yes" if ir_offset is not None else "no",
            "ir_body_line": ir_line,
        })

    args.out.parent.mkdir(parents=True, exist_ok=True)
    with args.out.open("w", newline="") as output:
        writer = csv.DictWriter(output, fieldnames=rows[0].keys(), lineterminator="\n")
        writer.writeheader()
        writer.writerows(rows)

    present = sum(row["ir_label_present"] == "yes" for row in rows)
    slices_present = sum(
        bool(row["ghidra_slice"])
        and (args.ghidra_summary.parent / row["ghidra_slice"]).is_file()
        for row in rows
    )
    summary = {
        "function_address": f"0x{address:x}",
        "llvm_ir_function_bytes": len(body.encode()),
        "llvm_ir_function_lines": body.count("\n"),
        "llvm_ir_label_definitions": len(all_label_defs),
        "llvm_ir_label_references": len(all_label_refs),
        "llvm_ir_unresolved_label_references": sorted(all_label_refs - all_label_defs),
        "llvm_ir_call_instructions": len(re.findall(r"\bcall\b", body)),
        "llvm_ir_phi_nodes": len(re.findall(r"\bphi\b", body)),
        "rizin_basic_blocks": len(rows),
        "retdec_ir_address_labels": len(labels),
        "matched_rizin_block_starts": present,
        "unmatched_rizin_block_starts": len(rows) - present,
        "match_ratio": round(present / len(rows), 6) if rows else 1.0,
        "rizin_blocks_with_ghidra_slice_file": slices_present,
        "csv": str(args.out),
    }
    args.summary.write_text(json.dumps(summary, indent=2) + "\n")
    print(json.dumps(summary, indent=2))


if __name__ == "__main__":
    main()
