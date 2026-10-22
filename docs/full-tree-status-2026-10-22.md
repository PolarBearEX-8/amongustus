# Full source compile status — 2026-10-22

The recovered project has 5,721 Java compilation units. The latest full compile (R828) used OpenJDK 21.0.12.1 with annotation processing disabled and a 16 MiB Java thread stack. It exited with code 4 and reported **51,023 errors across 2,369 files**, with 67 warnings and **0 emitted class files**.

The current source tree has no `** GOTO` markers and the full compile reports no `return outside method` diagnostics. Since R817, invokedynamic source adapters matched to bootstrap dispatchers reduced diagnostics from 63,671 to 51,023. Since R809, bytecode-backed invokedynamic and MethodHandle corrections reduced the count from 74,236 to 51,023. The full project remains uncompilable.

Compile evidence is in the recovery workspace at `build/generated/reports/readable-full-source9-r828.args` and `build/generated/reports/readable-full-source9-r828.log`. The R828 log ends with 51,023 errors and 67 warnings. No class files were emitted, so this run adds no compiled source/class pairs to the repository.

The repository continues to contain only the 12 source/class pairs already individually compiled and recorded in `compiled-recovered-files.json`. This run produced no new compiled source to add.
