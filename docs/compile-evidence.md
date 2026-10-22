# Compile evidence

Six recovered `cc` classes were compiled from the source files in this repository with OpenJDK 21 `javac --release 9`. The focused compile completed with exit code 0 and emitted all six class files:

- `Class_415`
- `Class_41I`
- `Class_45O`
- `Class_45S`
- `Class_45w`
- `Class_47`

The compile used the mapped readable client, launcher, and protected jars plus `dependencies.jar`, `original-payload.jar`, and `ElectricLauncher.jar` from the recovery workspace. The committed `.class` outputs were emitted by this successful compile. SHA-256 hashes are recorded in `compiled-recovered-files.json`.

This focused result does not mean the complete 5,721-unit recovered source tree builds: its latest full compile still reports 116 errors.
