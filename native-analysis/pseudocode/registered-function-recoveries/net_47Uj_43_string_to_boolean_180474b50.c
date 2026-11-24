/*
 * Registered native pseudocode: net/47Uj.43(Ljava/lang/String;)Z
 * Address: 0x180474b50
 * Source: augustus-jnic.jni-api-annotated.c (Ghidra output with verified JNI labels)
 * This is decompiler pseudocode, not original or build-ready C.
 */

/* JNI registration: net/47Uj.43(Ljava/lang/String;)Z (index 0) */
/* Function FUN_180474b50 at 180474b50 */

undefined8 FUN_180474b50(longlong *param_1,undefined8 param_2,longlong param_3)

{
  char cVar1;
  int iVar2;
  
  if (param_3 != 0) {
    iVar2 = /* JNI GetStringLength at +0x520 */ (**(code **)(*param_1 + 0x520))(param_1,param_3);
    cVar1 = /* JNI ExceptionCheck at +0x720 */ (**(code **)(*param_1 + 0x720))(param_1);
    if ((cVar1 == '\0') && (iVar2 != 0)) {
      return 1;
    }
  }
  return 0;
}
