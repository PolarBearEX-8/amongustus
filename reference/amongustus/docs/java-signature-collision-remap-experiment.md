# Java return-only overload remap experiment — R836

Java source cannot declare two methods with the same name and parameter types that differ only by return type, although the JVM class-file format permits them. The collision inventory contains 4,445 descriptors in 1,876 groups across 893 owners.

## Result

The existing ASM bytecode remapper was tested with aliases keyed by owner, method name, and full descriptor. It updates both declarations and bytecode call sites. In a focused check of `net/Class_47jl`, five `method_46()` variants received distinct Java-safe names (`method_46__returns_long`, `method_46__returns_void`, `method_46__returns_Class_472L`, `method_46__returns_boolean`, and `method_46__returns_Class_47d5`); `javap` confirmed the call site was renamed according to its return descriptor.

Applying the temporary collision map to the client bytecode reduced detected JVM signature collisions from 2,073 to 12. All 12 remaining client collisions are in the bundled ViaLegacy dependency (`net/raphimc/vialegacy/...`), outside Augustus source. On the launcher bytecode, collisions in the launcher source package fell from 150 to zero; remaining collisions are in bundled dependencies. The temporary whole-jar scan still reports dependency collisions and does not claim those libraries are Java source inputs for this project.

## Limits and next step

This was a bytecode remap prototype only. The temporary map and remapped jars live under `/tmp`; the production source-generation pipeline does not yet consume the aliases, and the generated CFR source for the focused class still had unrelated parse and identifier errors. No full-tree compile was run from R836, and the latest confirmed full compile remains R834: 50,603 errors, 67 warnings, and no emitted class files. Next, integrate descriptor aliases into source generation and validate a small class set before replacing any recovered sources.
