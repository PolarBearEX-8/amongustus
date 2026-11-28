# Curated native analysis set

This directory collects the small, useful native-analysis artifacts from the larger local decode workspace so the work can continue from GitHub without carrying the 20+ GiB of temporary tool output.

## Included

- Registration tables mapping Java owner, method, descriptor, and native address for Augustus and ElectricLauncher.
- JNI-annotated and registration-named Ghidra pseudocode, plus one function with focused recovery evidence.
- Ghidra and RetDec bounded C-like views for four oversized Augustus functions. There are 779 selected windows from each decompiler; each window is still a fragment with cut edges.
- Rizin block/range maps, crosswalks, and edge maps to support control-flow stitching.
- Python utilities and Ghidra Java scripts for auditing registrations and continuing the decompilation.
- A small derived ElectricLauncher JNIC state dump and its bytecode declaration table, which let the launcher registration decoder be replayed without redistributing the original JAR or DLL.

## Intentionally omitted

The original DLL/JAR inputs, Ghidra database, build outputs, caches, logs, and RetDec `.bc`, `.ll`, and `.dsm` intermediate files are not included. The `.dsm` files alone account for most of the local 20 GiB footprint. Retained artifacts are text except for the small derived JNIC state dump. The largest individual file is about 14 MB, below GitHub's 100 MiB per-file limit.

Original native input hashes, for matching local copies:

| Input | SHA-256 |
| --- | --- |
| `augustus-jnic.dll` | `9b752e0703591c219778129a48999444f4e53b611caca2b7ccd5d2e063bac966` |
| `electric-launcher-jnic.dll` | `ac29d670d92ee76a6a5940a2ded424ad35d66a38f7c74505de2fd90c71c309a5` |

## Quick checks

From this directory, verify the registration-name view:

```sh
python3 tools/verify_registered_native_view.py
```

Regenerate the registration-name view and its reversible map:

```sh
python3 tools/rename_registered_native_functions.py
```

Replay the ElectricLauncher registration decode from the included state dump:

```sh
python3 tools/decode_electric_launcher_jni_tables.py
```

The checked-in scripts do not need the original DLL for the audit above. Re-running decompilation does require the matching DLL and a local RetDec 5.0 installation. For example, supply the DLL, a range map from `metadata/cfg/`, and an output directory outside the repository:

```sh
python3 tools/decompile_retdec_regions.py /path/to/augustus-jnic.dll \
  metadata/cfg/40U49.regions-4k.csv /tmp/amongustus-40U49-retdec \
  --retdec /path/to/retdec-decompiler --skip-existing
```

Other scripts have their required binary, JDK, Ghidra, or angr inputs documented in their argument help. Keep regenerated binary/intermediate files outside this curated tree.

## Layout

- `pseudocode/`: whole-function views and the small evidence-backed example.
- `regions/<method>/ghidra/` and `regions/<method>/retdec/`: bounded C outputs and status manifests.
- `metadata/`: registration, function, JNI API, CFG, and crosswalk tables.
- `tools/`: analysis and extraction scripts.
- `docs/native-recovery-status.md`: current coverage and limits.

Decompiler output is analysis evidence, not recovered original C. Successful bounded output does not prove that the whole method is reconstructed, compiles, or behaves equivalently at runtime.
