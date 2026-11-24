#!/usr/bin/env python3
"""Decompile selected PE exports with angr without whole-image CFG discovery."""
import json
import sys

import angr

binary, output, *raw_addresses = sys.argv[1:]
addresses = [int(value, 0) for value in raw_addresses]
project = angr.Project(binary, auto_load_libs=False)
cfg = project.analyses.CFGFast(
    function_starts=addresses,
    normalize=True,
    data_references=False,
    resolve_indirect_jumps=False,
    force_complete_scan=False,
)
stats = {"binary": binary, "angr_version": angr.__version__, "requested_exports": [hex(a) for a in addresses], "decompiled": 0, "failed": []}

with open(output, "w", encoding="utf-8") as out:
    out.write(f"/* angr {angr.__version__} export-focused C-like decompilation of {binary} */\n")
    out.write("/* CFG discovery starts at named PE exports; output is best effort. */\n")
    for address in addresses:
        out.write(f"\n/* Export at 0x{address:x} */\n")
        function = cfg.kb.functions.get(address)
        if function is None:
            out.write("/* angr did not discover a function at this address */\n")
            stats["failed"].append({"address": hex(address), "error": "function missing from CFG"})
            continue
        try:
            result = project.analyses.Decompiler(function, cfg=cfg, fail_fast=False)
            code = result.codegen.text if result.codegen is not None else None
            if code:
                out.write(code + ("" if code.endswith("\n") else "\n"))
                stats["decompiled"] += 1
            else:
                out.write("/* angr did not produce C for this function */\n")
                stats["failed"].append({"address": hex(address), "error": "no codegen"})
        except Exception as exc:
            out.write(f"/* decompilation error: {type(exc).__name__}: {exc} */\n")
            stats["failed"].append({"address": hex(address), "error": f"{type(exc).__name__}: {exc}"})
        out.flush()

with open(output + ".json", "w", encoding="utf-8") as report:
    json.dump(stats, report, indent=2)
print(json.dumps(stats, indent=2))
