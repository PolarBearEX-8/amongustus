# Compile evidence

Twelve recovered source/class pairs in this repository have successful focused compile evidence recorded in `compiled-recovered-files.json`. Six `cc` classes were compiled from their committed sources with OpenJDK 21 `javac --release 9`; that focused compile exited 0 and emitted all six class files. Earlier focused compile results for the other six files are documented in the same manifest.

These focused results do not mean the complete recovered project builds. The latest full-tree compile status is documented in [full-tree-status-2026-10-22.md](full-tree-status-2026-10-22.md): 51,023 compiler errors across 2,369 files, 67 warnings, exit code 1, and no emitted class files. The source recovery worktree currently reports zero `** GOTO` markers and zero `return outside method` diagnostics after the latest control-flow repairs.
