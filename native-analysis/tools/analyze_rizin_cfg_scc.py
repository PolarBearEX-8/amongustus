#!/usr/bin/env python3
"""Summarize strongly connected regions in a Rizin function CFG."""

import argparse
import csv
import json
import subprocess
from pathlib import Path


def strongly_connected_components(adjacency, reverse):
    seen = set()
    order = []
    for root in adjacency:
        if root in seen:
            continue
        stack = [(root, False)]
        while stack:
            node, visited = stack.pop()
            if visited:
                order.append(node)
                continue
            if node in seen:
                continue
            seen.add(node)
            stack.append((node, True))
            for successor in adjacency[node]:
                if successor not in seen:
                    stack.append((successor, False))

    seen.clear()
    components = []
    for root in reversed(order):
        if root in seen:
            continue
        component = []
        stack = [root]
        seen.add(root)
        while stack:
            node = stack.pop()
            component.append(node)
            for predecessor in reverse[node]:
                if predecessor not in seen:
                    seen.add(predecessor)
                    stack.append(predecessor)
        components.append(component)
    return components


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("rizin", type=Path, help="path to the Rizin executable")
    parser.add_argument("binary", type=Path)
    parser.add_argument("address", type=lambda value: int(value, 0))
    parser.add_argument("output", type=Path)
    parser.add_argument("--blocks-output", type=Path)
    parser.add_argument("--blocks-csv-output", type=Path)
    parser.add_argument("--regions-output", type=Path)
    parser.add_argument("--region-size", type=lambda value: int(value, 0), default=0x1000)
    args = parser.parse_args()

    command = f"af @ {args.address:#x}; afbj @ {args.address:#x}"
    result = subprocess.run(
        [str(args.rizin), "-q", "-c", command, str(args.binary)],
        check=True,
        capture_output=True,
        text=True,
    )
    blocks = json.loads(result.stdout)
    by_address = {block["addr"]: block for block in blocks}
    adjacency = {
        address: [target for target in (block.get("jump"), block.get("fail")) if target in by_address]
        for address, block in by_address.items()
    }
    reverse = {address: [] for address in by_address}
    for source, targets in adjacency.items():
        for target in targets:
            reverse[target].append(source)

    components = strongly_connected_components(adjacency, reverse)
    components.sort(key=len, reverse=True)

    if args.blocks_output:
        args.blocks_output.parent.mkdir(parents=True, exist_ok=True)
        args.blocks_output.write_text(json.dumps(blocks, indent=2) + "\n", encoding="utf-8")

    if args.blocks_csv_output:
        args.blocks_csv_output.parent.mkdir(parents=True, exist_ok=True)
        entry = min(by_address)
        with args.blocks_csv_output.open("w", newline="", encoding="utf-8") as stream:
            fields = ["region_index", "address", "size", "instruction_count", "jump", "fail"]
            writer = csv.DictWriter(stream, fieldnames=fields, lineterminator="\n")
            writer.writeheader()
            for block in blocks:
                address = block["addr"]
                writer.writerow({
                    "region_index": (address - entry) // args.region_size,
                    "address": f"{address:#x}",
                    "size": block["size"],
                    "instruction_count": block.get("ninstr", 0),
                    "jump": f"{block['jump']:#x}" if block.get("jump") in by_address else "",
                    "fail": f"{block['fail']:#x}" if block.get("fail") in by_address else "",
                })

    if args.regions_output:
        if args.region_size <= 0:
            raise SystemExit("--region-size must be greater than zero")
        entry = min(by_address)
        region_stats = {}
        for address, targets in adjacency.items():
            region = (address - entry) // args.region_size
            item = region_stats.setdefault(region, {
                "block_count": 0,
                "instruction_count": 0,
                "internal_edges": 0,
                "outgoing_edges": 0,
                "incoming_edges": 0,
            })
            item["block_count"] += 1
            item["instruction_count"] += by_address[address].get("ninstr", 0)
            for target in targets:
                target_region = (target - entry) // args.region_size
                if target_region == region:
                    item["internal_edges"] += 1
                else:
                    item["outgoing_edges"] += 1
                    region_stats.setdefault(target_region, {
                        "block_count": 0,
                        "instruction_count": 0,
                        "internal_edges": 0,
                        "outgoing_edges": 0,
                        "incoming_edges": 0,
                    })["incoming_edges"] += 1
        args.regions_output.parent.mkdir(parents=True, exist_ok=True)
        fields = ["region_index", "start_address", "end_address", *next(iter(region_stats.values())).keys()]
        with args.regions_output.open("w", newline="", encoding="utf-8") as stream:
            writer = csv.DictWriter(stream, fieldnames=fields, lineterminator="\n")
            writer.writeheader()
            for region, stats in sorted(region_stats.items()):
                start = entry + region * args.region_size
                writer.writerow({
                    "region_index": region,
                    "start_address": f"{start:#x}",
                    "end_address": f"{start + args.region_size:#x}",
                    **stats,
                })

    report = {
        "binary": str(args.binary),
        "function_address": f"{args.address:#x}",
        "blocks": len(blocks),
        "edges": sum(len(targets) for targets in adjacency.values()),
        "scc_count": len(components),
        "singleton_sccs": sum(len(component) == 1 for component in components),
        "largest_sccs": [
            {
                "blocks": len(component),
                "min_address": f"{min(component):#x}",
                "max_address": f"{max(component):#x}",
            }
            for component in components[:20]
        ],
    }
    args.output.parent.mkdir(parents=True, exist_ok=True)
    args.output.write_text(json.dumps(report, indent=2) + "\n", encoding="utf-8")
    print(json.dumps(report, indent=2))


if __name__ == "__main__":
    main()
