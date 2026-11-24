# Amongustus

Recovery and analysis workspace for the Augustus Minecraft client. The repository contains selected recovered Java files, compile evidence, and a curated native-analysis set intended for continued decoding and review.

## Project areas

- `src/main/java/` contains recovered Java sources that have focused compile evidence.
- `docs/` records the source-recovery and build status. A successful hybrid build uses original bytecode for most classes; the complete recovered source tree is not buildable yet.
- [`native-analysis/`](native-analysis/README.md) contains registration maps, decompiler pseudocode, bounded function slices, CFG metadata, and scripts. It does not include the original DLLs or large temporary decompiler outputs.

## Current limitations

Native pseudocode is inferred output, not original C. Four large registered native methods still lack whole-function bodies; their bounded slices preserve useful analysis evidence but have cut control-flow edges. See [`native-analysis/docs/native-recovery-status.md`](native-analysis/docs/native-recovery-status.md) for coverage and caveats.

This repository does not include the original Augustus or ElectricLauncher binaries. Re-running extraction or decompilation requires the matching input files and the tools listed in `native-analysis/README.md`.
