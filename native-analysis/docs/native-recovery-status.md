# Native recovery status

Status carried forward from the source audit dated 2026-10-24. The checked-in verifier is included so the registration-name mapping can be replayed locally.

## Coverage

- Augustus has 173 `RegisterNatives` entries at 173 unique native addresses.
- 169 registered methods have whole-function Ghidra pseudocode. Four do not:
  - `net/40U.49(J)V` at `0x18001f120`
  - `net/47Ua.42(J)V` at `0x180298d70`
  - `net/47Ua.48(JLnet/47d7;)V` at `0x180333830`
  - `net/47Ua.47(CIC)V` at `0x1803db540`
- Each of the four has 4 KiB bounded views from Ghidra and RetDec: 302 windows for `40U.49`, then 155, 168, and 154 for the three `47Ua` methods. Each tool's outputs cover 42,263 mapped Rizin blocks in total.
- All four remain whole-function gaps. Cross-window control flow and values still need to be stitched and checked against the machine code.

The registration audit previously reported a passing consistency check for registration rows, addresses, names, and body counts. Re-run `python3 tools/verify_registered_native_view.py` to check the curated copy.

## Useful lead

Several `net/47bj` methods call Java `URL` and `HttpsURLConnection` APIs to set request properties/methods, write request data, read responses, and disconnect. This indicates HTTPS capability through the JVM; the endpoint is supplied or constructed at runtime and is not established by the native import table alone. Trace the Java call sites and string-decoding path to identify destinations and payload semantics.

## Limits

- Ghidra and RetDec C output is pseudocode, not original C.
- The four large methods exceed decompiler whole-function flow limits; slice success is not semantic recovery.
- The native recovery build is not a rebuilt DLL and does not establish runtime equivalence.
- Native DLLs, large disassembly intermediates, and the Ghidra database were intentionally excluded from this public project.
