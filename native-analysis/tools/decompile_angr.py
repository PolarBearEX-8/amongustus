#!/usr/bin/env python3
"""Best-effort whole-file C-like decompilation with angr."""
import json
import sys
import traceback

import angr

binary, output = sys.argv[1:3]
project = angr.Project(binary, auto_load_libs=False)
cfg = project.analyses.CFGFast(normalize=True, data_references=True)
functions = sorted(
    (f for f in cfg.kb.functions.values() if not f.is_plt and not f.is_simprocedure),
    key=lambda f: f.addr,
)
stats = {"binary": binary, "angr_version": angr.__version__, "functions_found": len(functions), "decompiled": 0, "failed": []}

with open(output, "w", encoding="utf-8") as out:
    out.write(f"/* angr {angr.__version__} C-like decompilation of {binary} */\n")
    out.write("/* Best effort: inferred types and control flow may be inaccurate. */\n")
    for index, function in enumerate(functions, 1):
        out.write(f"\n/* {function.name} at 0x{function.addr:x} */\n")
        try:
            result = project.analyses.Decompiler(function, cfg=cfg, fail_fast=False)
            code = result.codegen.text if result.codegen is not None else None
            if code:
                out.write(code)
                if not code.endswith("\n"):
                    out.write("\n")
                stats["decompiled"] += 1
            else:
                out.write("/* angr did not produce C for this function */\n")
                stats["failed"].append({"address": hex(function.addr), "name": function.name, "error": "no codegen"})
        except Exception as exc:  # keep going through the remaining functions
            out.write(f"/* decompilation error: {type(exc).__name__}: {exc} */\n")
            stats["failed"].append({"address": hex(function.addr), "name": function.name, "error": f"{type(exc).__name__}: {exc}"})
        if index % 50 == 0 or index == len(functions):
            out.flush()
            print(f"{index}/{len(functions)} functions; decompiled={stats['decompiled']}", flush=True)

with open(output + ".json", "w", encoding="utf-8") as report:
    json.dump(stats, report, indent=2)
print(json.dumps({k: v for k, v in stats.items() if k != "failed"}, indent=2))
