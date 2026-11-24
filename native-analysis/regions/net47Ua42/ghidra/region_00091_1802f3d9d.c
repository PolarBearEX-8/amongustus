/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=91 entry=1802f3d9d blocks=46 maxInstructions=50000 */

/* cut edge: 0x1802f3d9d -> 0x180333186 */
/* cut edge: 0x1802f3db3 -> 0x180333186 */
/* cut edge: 0x1802f3e23 -> 0x180302f96 */
/* cut edge: 0x1802f3e4f -> 0x180302fce */
/* cut edge: 0x1802f3eb6 -> 0x180303031 */
/* cut edge: 0x1802f3ec6 -> 0x18030306a */
/* cut edge: 0x1802f3fa9 -> 0x180333186 */
/* cut edge: 0x1802f40b8 -> 0x18029ad3c */
/* cut edge: 0x1802f40c5 -> 0x18029ad3c */
/* cut edge: 0x1802f40d7 -> 0x180333186 */
/* cut edge: 0x1802f40df -> 0x180333186 */
/* cut edge: 0x1802f428e -> 0x180333186 */
/* cut edge: 0x1802f4385 -> 0x180333186 */
/* cut edge: 0x1802f439b -> 0x180333186 */
/* cut edge: 0x1802f454e -> 0x180333186 */
/* cut edge: 0x1802f4643 -> 0x180333186 */
/* cut edge: 0x1802f4659 -> 0x180333186 */
/* cut edge: 0x1802f4817 -> 0x180333186 */
/* cut edge: 0x1802f490c -> 0x180333186 */
/* cut edge: 0x1802f4922 -> 0x180333186 */
/* cut edge: 0x1802f4ae6 -> 0x180333186 */
/* cut edge: 0x1802f4be2 -> 0x18029ad3c */
/* cut edge: 0x1802f4c04 -> 0x180333186 */
/* cut edge: 0x1802f4c0c -> 0x180333186 */
/* cut edge: 0x1802f4c22 -> 0x1802f4dea */
/* cut edge: 0x1802f4c77 -> 0x180303397 */
/* cut edge: 0x1802f4ca0 -> 0x1803033cf */
/* cut edge: 0x1802f4cff -> 0x180303420 */
/* cut edge: 0x1802f4d0f -> 0x180303459 */
/* cut edge: 0x1802f4d67 -> 0x1802f4dea */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x1802f4dea original byte 0x48 replaced by RET */
/* target 0x180302f96 original byte 0x48 replaced by RET */
/* target 0x180302fce original byte 0x48 replaced by RET */
/* target 0x180303031 original byte 0x48 replaced by RET */
/* target 0x18030306a original byte 0x48 replaced by RET */
/* target 0x180303397 original byte 0x48 replaced by RET */
/* target 0x1803033cf original byte 0x48 replaced by RET */
/* target 0x180303420 original byte 0x48 replaced by RET */
/* target 0x180303459 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


longlong region_00091_1802f3d9d(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  longlong lVar6;
  undefined8 uVar7;
  longlong lVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  longlong unaff_R12;
  longlong *in_stack_00000040;
  longlong in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  
  lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
  if ((char)lVar6 == '\0') {
    lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    lVar4 = DAT_1804b5a08;
    uVar10 = DAT_1804b59f0;
    if ((char)lVar6 == '\0') {
      if (DAT_1804b1058 == 0) {
        pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return 0;
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000538,1);
        lVar3 = DAT_1804b4d38;
        lVar6 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return 0;
        }
        if (DAT_1804b4d38 == 0) {
          return DAT_1804b4d30;
        }
        lVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000022b8,5);
        if (DAT_1804b4d40 == 0) {
          return lVar8;
        }
        uVar9 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
        (*pcVar2)(in_stack_00000040,lVar6,lVar3,lVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar4,uVar10,uVar7);
        DAT_1804b1058 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar10);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b1058,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x97f);
      (*pcVar1)(in_stack_00000040,uVar7,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         in_stack_00000070 % 0x2a237 ^ in_stack_000000b0 ^ 0xdb06c2f4235a029d);
      (*pcVar1)(in_stack_00000040,uVar7,1,uVar9);
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,uVar10,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar10,in_stack_000000d0);
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      lVar6 = unaff_R12;
      if (cVar5 == '\0') {
        return unaff_R12;
      }
    }
  }
  return lVar6;
}

