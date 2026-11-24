# Per-function recovery evidence: `net/47Uj.43(Ljava/lang/String;)Z`

- Registration: `net/47Uj.43(Ljava/lang/String;)Z`; row index `0` in `augustus-jni-native-registrations.csv`.
- Native address: `0x180474b50`; Ghidra marker: `/* Function FUN_180474b50 at 180474b50 */`.
- Artifact: `net_47Uj_43_string_to_boolean_180474b50.c`.
- Source of body: `augustus-jnic.jni-api-annotated.c`; the registration marker, function marker, and body were copied without changing its pseudocode.
- Body SHA-256 (excluding artifact header): `1d69e635bebe13616d5010ea6e3537d335e39be40673003391d7d8b3e55882f8`.
- Structural checks: one function definition found; braces balanced; no `DECOMPILATION FAILED` marker.
- Direct behavior visible in pseudocode: for a non-null `jstring`, call `GetStringLength`, check `ExceptionCheck`, and return true only when there is no pending exception and the string length is nonzero; otherwise return false.
- Limits: inferred Ghidra pseudocode with decompiler types/identifiers; not original C, not compiled, and no runtime equivalence claim.
