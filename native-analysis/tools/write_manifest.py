#!/usr/bin/env python3
"""Write a deterministic size and SHA-256 inventory for this curated set."""

import csv
import hashlib
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
MANIFEST = ROOT / "MANIFEST.csv"


def main() -> None:
    files = sorted(
        path for path in ROOT.rglob("*")
        if path.is_file()
        and path != MANIFEST
        and "__pycache__" not in path.parts
    )
    with MANIFEST.open("w", newline="", encoding="utf-8") as stream:
        writer = csv.writer(stream, lineterminator="\n")
        writer.writerow(("path", "bytes", "sha256"))
        for path in files:
            digest = hashlib.sha256(path.read_bytes()).hexdigest()
            writer.writerow((path.relative_to(ROOT).as_posix(), path.stat().st_size, digest))
    print(f"wrote {len(files)} entries to {MANIFEST}")


if __name__ == "__main__":
    main()
