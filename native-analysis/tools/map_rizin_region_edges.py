#!/usr/bin/env python3
"""Map Rizin basic-block jump/fail edges between bounded address windows."""
import argparse
import csv
from collections import Counter
from pathlib import Path


def read_csv(path):
    with path.open(newline="", encoding="utf-8") as f:
        return list(csv.DictReader(f))


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("regions_csv", type=Path)
    parser.add_argument("blocks_csv", type=Path)
    parser.add_argument("output_csv", type=Path)
    args = parser.parse_args()

    regions = read_csv(args.regions_csv)
    blocks = read_csv(args.blocks_csv)
    spans = [(int(r["start_address"], 16), int(r["end_address"], 16), r["region_index"]) for r in regions]
    spans.sort()

    def locate(address):
        for start, end, region in spans:
            if start <= address < end:
                return region
        return "OUTSIDE"

    block_starts = {int(b["address"], 16) for b in blocks}
    rows = []
    totals = Counter()
    for block in blocks:
        source = int(block["address"], 16)
        src_region = block["region_index"]
        for edge_name in ("jump", "fail"):
            raw_target = block.get(edge_name, "")
            if not raw_target:
                continue
            target = int(raw_target, 16)
            dst_region = locate(target)
            if dst_region == src_region:
                kind = "intra-window"
            elif dst_region == "OUTSIDE":
                kind = "outside-mapped-windows"
            elif target not in block_starts:
                kind = "window-target-not-block-start"
            else:
                kind = "cross-window"
            rows.append({
                "source_region": src_region,
                "source_address": f"0x{source:x}",
                "edge": edge_name,
                "target_address": f"0x{target:x}",
                "target_region": dst_region,
                "target_is_block_start": str(target in block_starts).lower(),
                "classification": kind,
            })
            totals[kind] += 1

    args.output_csv.parent.mkdir(parents=True, exist_ok=True)
    with args.output_csv.open("w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=list(rows[0]) if rows else [], lineterminator="\n")
        writer.writeheader()
        writer.writerows(rows)

    pairs = Counter(
        (r["source_region"], r["target_region"], r["classification"])
        for r in rows
        if r["source_region"] != r["target_region"]
    )
    pairs_path = args.output_csv.with_name(args.output_csv.stem + "-region-pairs.csv")
    with pairs_path.open("w", newline="", encoding="utf-8") as f:
        writer = csv.writer(f, lineterminator="\n")
        writer.writerow(("source_region", "target_region", "classification", "edge_count"))
        for (source, target, kind), count in sorted(pairs.items(), key=lambda item: (int(item[0][0]), item[0][1], item[0][2])):
            writer.writerow((source, target, kind, count))

    print(f"regions={len(regions)} blocks={len(blocks)} edges={len(rows)}")
    for kind, count in sorted(totals.items()):
        print(f"{kind}={count}")
    print(f"output={args.output_csv}")
    print(f"region_pairs={pairs_path}")


if __name__ == "__main__":
    main()
