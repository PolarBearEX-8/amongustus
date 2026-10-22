# Full source compile status — 2026-10-22

The recovered project has 5,721 Java compilation units. The latest full compile used OpenJDK 21.0.12.1 with annotation processing disabled and a 16 MiB Java thread stack. It exited with code 1 and reported **65,806 errors across 2,495 files**, with 67 warnings and **0 emitted class files**.

The current source tree has no `** GOTO` markers and the full compile reports no `return outside method` diagnostics. Since the earlier r809 compile, bytecode-backed corrections removed synthetic string tags from calls whose class has a matching `(int, long)` helper. The diagnostic count dropped from 74,236 to 65,806. The full project remains uncompilable.

Compile evidence was recorded in the recovery workspace at `build/generated/reports/full-readable-regeneration-r813.md`; raw log SHA-256: `3d4a42c139deb4be6ef510a4f41da3c85ce402395b05cc40bf15b396596b03c8`.

The repository continues to contain only the 12 source/class pairs already individually compiled and recorded in `compiled-recovered-files.json`. This run produced no new compiled source to add.
