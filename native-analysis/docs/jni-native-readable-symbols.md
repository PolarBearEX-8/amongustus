# Readable names for registered native functions

`augustus-jnic.readable.c` is generated from the raw Ghidra output by `tools/name_registered_native_functions.py`. It renames each registered native function using its semantic owner name, obfuscated Java method name, and a descriptor hash, then places the exact owner/name/descriptor beside the Ghidra function marker. The mapping table contains 173 unique registrations and all 173 readable symbols occur in the generated C file.

The raw `augustus-jnic.ghidra.c` is preserved unchanged. The readable file remains decompiler pseudocode: it uses Ghidra types such as `undefined8`, JNI temporaries, and recovered control-flow artifacts, so the rename pass does not claim that the native source compiles or is behaviorally equivalent.
