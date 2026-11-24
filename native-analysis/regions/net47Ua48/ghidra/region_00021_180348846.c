/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=21 entry=180348846 blocks=48 maxInstructions=50000 */

/* cut edge: 0x1803488c8 -> 0x1803351d6 */
/* cut edge: 0x180348a8a -> 0x1803351d6 */
/* cut edge: 0x180348ac2 -> 0x1803351d6 */
/* cut edge: 0x180348afc -> 0x1803351d6 */
/* cut edge: 0x180348b36 -> 0x1803351d6 */
/* cut edge: 0x180348b73 -> 0x180348702 */
/* cut edge: 0x180348ba9 -> 0x180348754 */
/* cut edge: 0x180348be4 -> 0x1803487a1 */
/* cut edge: 0x180348c1a -> 0x1803487a1 */
/* cut edge: 0x180348c33 -> 0x1803487c9 */
/* cut edge: 0x180348ca3 -> 0x1803486bf */
/* cut edge: 0x180348cb0 -> 0x1803486bf */
/* cut edge: 0x180348cce -> 0x18034d6a6 */
/* cut edge: 0x180348cd7 -> 0x18034d6b0 */
/* cut edge: 0x180348ce4 -> 0x1803351d6 */
/* cut edge: 0x180348d32 -> 0x18034ab5b */
/* cut edge: 0x180348d63 -> 0x18034ab5b */
/* cut edge: 0x180348d73 -> 0x1803351d6 */
/* cut edge: 0x180348d9c -> 0x18036650c */
/* cut edge: 0x180348dcb -> 0x180366547 */
/* cut edge: 0x180348e2d -> 0x180366598 */
/* cut edge: 0x180348e3d -> 0x1803665d1 */
/* cut edge: 0x180348f26 -> 0x1803351d6 */
/* cut edge: 0x180349013 -> 0x18034d5e1 */
/* cut edge: 0x180349020 -> 0x18034d5e1 */
/* cut edge: 0x18034902a -> 0x1803664d1 */
/* cut edge: 0x180349051 -> 0x1803351d6 */
/* cut edge: 0x18034907f -> 0x1803351d6 */
/* cut edge: 0x1803491ff -> 0x1803351d6 */
/* cut edge: 0x1803492ec -> 0x1803351d6 */
/* cut edge: 0x180349489 -> 0x1803351d6 */
/* cut edge: 0x180349576 -> 0x1803351d6 */
/* cut edge: 0x1803495b9 -> 0x1803351d6 */
/* cut edge: 0x18034973c -> 0x1803351d6 */
/* cut edge: 0x180349829 -> 0x1803351d6 */
/* cut edge: 0x180349829 -> 0x180349863 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803486bf original byte 0x48 replaced by RET */
/* target 0x180348702 original byte 0x45 replaced by RET */
/* target 0x180348754 original byte 0x48 replaced by RET */
/* target 0x1803487a1 original byte 0x4c replaced by RET */
/* target 0x1803487c9 original byte 0x48 replaced by RET */
/* target 0x180349863 original byte 0x48 replaced by RET */
/* target 0x18034ab5b original byte 0x88 replaced by RET */
/* target 0x18034d5e1 original byte 0x48 replaced by RET */
/* target 0x18034d6a6 original byte 0x48 replaced by RET */
/* target 0x18034d6b0 original byte 0xb8 replaced by RET */
/* target 0x1803664d1 original byte 0x48 replaced by RET */
/* target 0x18036650c original byte 0x48 replaced by RET */
/* target 0x180366547 original byte 0x48 replaced by RET */
/* target 0x180366598 original byte 0x48 replaced by RET */
/* target 0x1803665d1 original byte 0x48 replaced by RET */


ulonglong region_00021_180348846(void)

{
  code *pcVar1;
  undefined4 uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  ulonglong uVar8;
  undefined8 uVar9;
  code *unaff_R13;
  code *unaff_R14;
  int unaff_R15D;
  longlong *in_stack_00000048;
  undefined4 uStack0000000000000050;
  undefined4 uStack0000000000000054;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000c0;
  longlong in_stack_000000c8;
  undefined8 in_stack_000000d0;
  code *in_stack_000000d8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000140;
  
  uVar4 = in_stack_00000058;
  (*unaff_R14)(in_stack_00000048,in_stack_00000058);
  (*in_stack_000000d8)(in_stack_00000048,in_stack_00000100);
  uVar4 = (*unaff_R13)(in_stack_00000048,uVar4,in_stack_000000c0,in_stack_00000140);
  DAT_1804b1f98 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b1f98,in_stack_00000078);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,6,in_stack_00000088,0);
  uVar9 = in_stack_00000068;
  uVar4 = in_stack_00000060;
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,in_stack_000000e0);
  (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar5,1,in_stack_000000e8);
  uVar3 = in_stack_00000098;
  uVar6 = in_stack_00000090;
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000b0);
  (*pcVar1)(in_stack_00000048,uVar5,2,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x390))(in_stack_00000048,uVar9,uVar4,in_stack_000000a8)
  ;
  (*pcVar1)(in_stack_00000048,uVar5,3,uVar4);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,uVar3,uVar6,0xdfc06d1e310f3299);
  (*pcVar1)(in_stack_00000048,uVar5,4,uVar4);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x390))(in_stack_00000048,uVar3,uVar6,in_stack_000000c8)
  ;
  (*pcVar1)(in_stack_00000048,uVar5,5,uVar4);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar5);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)uVar8 == '\0') {
    uVar9 = (**(code **)(*in_stack_00000048 + 0x568))
                      (in_stack_00000048,in_stack_000000a0,in_stack_000000d0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar8 == '\0') {
      (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048,uVar4,0,1,&stack0x00000050);
      uVar2 = uStack0000000000000050;
      uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)uVar8 == '\0') {
        _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar2);
        (**(code **)(*in_stack_00000048 + 0x698))
                  (in_stack_00000048,uVar9,unaff_R15D,1,&stack0x00000050);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)uVar8 == '\0') {
          (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048,uVar4,1,1,&stack0x00000050);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)uVar8 == '\0') {
            if (in_stack_000000c8 < 1) {
              return (ulonglong)(unaff_R15D + 1);
            }
            return (ulonglong)(unaff_R15D + 1);
          }
        }
      }
    }
  }
  return uVar8;
}

