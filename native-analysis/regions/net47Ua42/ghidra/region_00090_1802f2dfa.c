/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=90 entry=1802f2dfa blocks=55 maxInstructions=50000 */

/* cut edge: 0x1802f2dfa -> 0x180333186 */
/* cut edge: 0x1802f2e10 -> 0x180333186 */
/* cut edge: 0x1802f2e7b -> 0x180303293 */
/* cut edge: 0x1802f2ea7 -> 0x1803032cb */
/* cut edge: 0x1802f2f13 -> 0x180303326 */
/* cut edge: 0x1802f2f23 -> 0x180303360 */
/* cut edge: 0x1802f300c -> 0x180333186 */
/* cut edge: 0x1802f3108 -> 0x18029ad3c */
/* cut edge: 0x1802f3127 -> 0x180333186 */
/* cut edge: 0x1802f313d -> 0x180333186 */
/* cut edge: 0x1802f319d -> 0x180302b94 */
/* cut edge: 0x1802f31c9 -> 0x180302bcf */
/* cut edge: 0x1802f3225 -> 0x180302c20 */
/* cut edge: 0x1802f323a -> 0x180302c5f */
/* cut edge: 0x1802f335e -> 0x180333186 */
/* cut edge: 0x1802f34b7 -> 0x180333186 */
/* cut edge: 0x1802f34cd -> 0x180333186 */
/* cut edge: 0x1802f3538 -> 0x180302c96 */
/* cut edge: 0x1802f3569 -> 0x180302cd6 */
/* cut edge: 0x1802f35c5 -> 0x180302d27 */
/* cut edge: 0x1802f35d5 -> 0x180302d60 */
/* cut edge: 0x1802f36b5 -> 0x180333186 */
/* cut edge: 0x1802f37ac -> 0x180333186 */
/* cut edge: 0x1802f37c2 -> 0x180333186 */
/* cut edge: 0x1802f382d -> 0x180302d97 */
/* cut edge: 0x1802f3859 -> 0x180302dcf */
/* cut edge: 0x1802f38b8 -> 0x180302e20 */
/* cut edge: 0x1802f38cd -> 0x180302e5e */
/* cut edge: 0x1802f39b0 -> 0x180333186 */
/* cut edge: 0x1802f3aa7 -> 0x180333186 */
/* cut edge: 0x1802f3abd -> 0x180333186 */
/* cut edge: 0x1802f3b25 -> 0x180302e95 */
/* cut edge: 0x1802f3b54 -> 0x180302ed0 */
/* cut edge: 0x1802f3bb0 -> 0x180302f21 */
/* cut edge: 0x1802f3bc5 -> 0x180302f5f */
/* cut edge: 0x1802f3ca8 -> 0x180333186 */
/* cut edge: 0x1802f3ca8 -> 0x1802f3d9d */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x1802f3d9d original byte 0x48 replaced by RET */
/* target 0x180302b94 original byte 0x48 replaced by RET */
/* target 0x180302bcf original byte 0x48 replaced by RET */
/* target 0x180302c20 original byte 0x4c replaced by RET */
/* target 0x180302c5f original byte 0x48 replaced by RET */
/* target 0x180302c96 original byte 0x4c replaced by RET */
/* target 0x180302cd6 original byte 0x48 replaced by RET */
/* target 0x180302d27 original byte 0x48 replaced by RET */
/* target 0x180302d60 original byte 0x48 replaced by RET */
/* target 0x180302d97 original byte 0x48 replaced by RET */
/* target 0x180302dcf original byte 0x48 replaced by RET */
/* target 0x180302e20 original byte 0x4c replaced by RET */
/* target 0x180302e5e original byte 0x48 replaced by RET */
/* target 0x180302e95 original byte 0x48 replaced by RET */
/* target 0x180302ed0 original byte 0x48 replaced by RET */
/* target 0x180302f21 original byte 0x4c replaced by RET */
/* target 0x180302f5f original byte 0x48 replaced by RET */
/* target 0x180303293 original byte 0x48 replaced by RET */
/* target 0x1803032cb original byte 0x48 replaced by RET */
/* target 0x180303326 original byte 0x48 replaced by RET */
/* target 0x180303360 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00090_1802f2dfa(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  longlong *in_stack_00000040;
  longlong in_stack_00000060;
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
  
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    lVar5 = DAT_1804b5a08;
    uVar10 = DAT_1804b59f0;
    if (cVar6 == '\0') {
      if (DAT_1804b16b8 == 0) {
        pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x0000039e,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000016b8,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
        (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar10,uVar7);
        DAT_1804b16b8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar10);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b16b8,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x2bfc);
      (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         in_stack_00000060 % 0xd5a6b ^ in_stack_000000b0 ^ 0xb24606c8f7929d7f);
      (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,uVar10,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar10,in_stack_000000d0);
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (cVar6 == '\0') {
        return;
      }
    }
  }
  return;
}

