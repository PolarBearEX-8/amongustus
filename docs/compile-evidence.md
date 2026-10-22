# Compile evidence

Twelve recovered source/class pairs in this repository have successful focused compile evidence recorded in `compiled-recovered-files.json`. Six `cc` classes were compiled from their committed sources with OpenJDK 21 `javac --release 9`; that focused compile exited 0 and emitted all six class files. Earlier focused compile results for the other six files are documented in the same manifest.

These focused results do not mean the complete recovered project builds. The latest full-tree compile status is documented in [full-tree-status-2026-10-22.md](full-tree-status-2026-10-22.md): 50,603 compiler errors across 2,367 files, 67 warnings, exit code 4, and no emitted class files. The source recovery worktree currently reports zero `** GOTO` markers and zero `return outside method` diagnostics after the latest control-flow repairs.

After R834, an R836 temporary bytecode-remapping experiment confirmed that descriptor-aware aliases can remove Java-incompatible return-only overloads from recovered classes. The experiment and its limits are documented in [java-signature-collision-remap-experiment.md](java-signature-collision-remap-experiment.md). It has not yet been integrated into source generation and did not produce a new full-tree compile result.
