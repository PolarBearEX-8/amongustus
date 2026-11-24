#!/usr/bin/env python3
"""Generate independent RetDec C comparison views for bounded Rizin CFG ranges."""
from __future__ import annotations

import argparse
import csv
import subprocess
import time
from pathlib import Path

DEFAULT_RETDEC = Path.home() / ".local/share/retdec-5.0-layout/bin/retdec-decompiler"


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("binary", type=Path)
    parser.add_argument("regions_csv", type=Path)
    parser.add_argument("output_dir", type=Path)
    parser.add_argument("--retdec", type=Path, default=DEFAULT_RETDEC)
    parser.add_argument("--max-memory", type=int, default=4 * 1024**3)
    parser.add_argument("--timeout", type=int, default=1800)
    parser.add_argument("--start-index", type=int, default=0)
    parser.add_argument("--end-index", type=int)
    parser.add_argument("--skip-existing", action="store_true")
    args = parser.parse_args()

    args.output_dir.mkdir(parents=True, exist_ok=True)
    with args.regions_csv.open(newline="") as handle:
        rows = list(csv.DictReader(handle))
    selected = [row for row in rows if int(row["region_index"]) >= args.start_index
                and (args.end_index is None or int(row["region_index"]) <= args.end_index)]
    manifest = args.output_dir / "summary.tsv"
    with manifest.open("w", newline="") as out:
        writer = csv.writer(out, delimiter="\t", lineterminator="\n")
        writer.writerow(["region", "start", "end", "blocks", "instructions", "status",
                         "exit_code", "seconds", "bytes", "failure_markers", "source", "log"])
        for row in selected:
            index = int(row["region_index"])
            start = row["start_address"]
            end = row["end_address"]
            stem = f"region_{index:05d}_{int(start, 16):x}"
            source = args.output_dir / f"{stem}.c"
            log = args.output_dir / f"{stem}.log"
            if args.skip_existing and source.is_file() and source.stat().st_size:
                status, code, elapsed = "EXISTING", 0, 0.0
            else:
                command = [str(args.retdec), str(args.binary), "--select-ranges", f"{start}-{end}",
                           "--select-decode-only", "--max-memory", str(args.max_memory),
                           "--timeout", str(args.timeout), "--output", str(source)]
                began = time.monotonic()
                try:
                    result = subprocess.run(command, stdout=subprocess.PIPE, stderr=subprocess.STDOUT,
                                            text=True, timeout=args.timeout + 60, check=False)
                    elapsed = time.monotonic() - began
                    log.write_text(result.stdout, errors="replace")
                    code = result.returncode
                except subprocess.TimeoutExpired as exc:
                    elapsed = time.monotonic() - began
                    log.write_text((exc.stdout or "") + "\nTIMED OUT\n", errors="replace")
                    code = 124
                if code != 0:
                    status = "FAILED"
                elif not source.is_file() or not source.stat().st_size:
                    status = "NO_OUTPUT"
                elif "DECOMPILATION FAILED" in source.read_text(errors="replace"):
                    status = "OUTPUT_WITH_FAILURE_MARKER"
                else:
                    status = "C_OUTPUT"
            size = source.stat().st_size if source.is_file() else 0
            failures = source.read_text(errors="replace").count("DECOMPILATION FAILED") if size else 0
            writer.writerow([index, start, end, row["block_count"], row["instruction_count"],
                             status, code, f"{elapsed:.2f}", size, failures, source.name, log.name])
            out.flush()
            print(f"{index:05d} {start}-{end}: {status}, {size} bytes, {elapsed:.2f}s", flush=True)
    print(f"Wrote {len(selected)} region results to {args.output_dir}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
