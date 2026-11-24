#!/usr/bin/env python3
"""Rebuild the status TSV for Ghidra/Rizin bounded-region decompilation output."""
import argparse
import csv
from pathlib import Path


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("blocks_csv", type=Path)
    parser.add_argument("output_dir", type=Path)
    args = parser.parse_args()

    regions: dict[int, list[dict[str, str]]] = {}
    with args.blocks_csv.open(newline="") as source:
        for row in csv.DictReader(source):
            regions.setdefault(int(row["region_index"]), []).append(row)

    summary = args.output_dir / "summary.tsv"
    with summary.open("w", newline="") as output:
        writer = csv.writer(output, delimiter="\t", lineterminator="\n")
        writer.writerow(["region", "entry", "blocks", "status", "message", "file"])
        for region_id, blocks in sorted(regions.items()):
            entry = min(int(block["address"], 0) for block in blocks)
            entry_text = f"0x{entry:x}"
            filename = f"region_{region_id:05d}_{entry:x}.c"
            path = args.output_dir / filename
            status, message = "MISSING", "output file missing"
            if path.exists():
                content = path.read_text(errors="replace")
                if "/* SLICE FAILED:" in content:
                    status, message = "SLICE_FAILED", content.split("/* SLICE FAILED:", 1)[1].split("*/", 1)[0].strip()
                elif "/* DECOMPILATION FAILED:" in content:
                    status, message = "DECOMPILE_FAILED", content.split("/* DECOMPILATION FAILED:", 1)[1].split("*/", 1)[0].strip()
                else:
                    status, message = "OK", "decompiled"
            writer.writerow([region_id, entry_text, len(blocks), status, message, filename])
    print(f"wrote {summary} ({len(regions)} regions)")


if __name__ == "__main__":
    main()
