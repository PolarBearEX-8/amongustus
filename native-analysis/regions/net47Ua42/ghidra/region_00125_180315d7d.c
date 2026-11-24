/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=125 entry=180315d7d blocks=36 maxInstructions=50000 */

/* cut edge: 0x180315d7d -> 0x18032f248 */
/* cut edge: 0x180315e55 -> 0x180333186 */
/* cut edge: 0x180315f4a -> 0x180333186 */
/* cut edge: 0x180315f60 -> 0x180333186 */
/* cut edge: 0x180315fcb -> 0x18032f27e */
/* cut edge: 0x180315ff7 -> 0x18032f2b9 */
/* cut edge: 0x18031605b -> 0x18032f313 */
/* cut edge: 0x18031606b -> 0x18032f34c */
/* cut edge: 0x18031614a -> 0x180300bf0 */
/* cut edge: 0x1803161af -> 0x180333186 */
/* cut edge: 0x1803161b7 -> 0x180333186 */
/* cut edge: 0x180316363 -> 0x180333186 */
/* cut edge: 0x18031645c -> 0x180333186 */
/* cut edge: 0x180316472 -> 0x180333186 */
/* cut edge: 0x18031661e -> 0x180333186 */
/* cut edge: 0x180316715 -> 0x180333186 */
/* cut edge: 0x18031672b -> 0x180333186 */
/* cut edge: 0x1803168d9 -> 0x180333186 */
/* cut edge: 0x1803169d0 -> 0x180333186 */
/* cut edge: 0x1803169e6 -> 0x180333186 */
/* cut edge: 0x180316b9a -> 0x180333186 */
/* cut edge: 0x180316c8f -> 0x180333186 */
/* cut edge: 0x180316ca5 -> 0x180333186 */
/* cut edge: 0x180316cbb -> 0x180316e5e */
/* cut edge: 0x180316d0d -> 0x180316e5e */
/* target 0x180300bf0 original byte 0xff replaced by RET */
/* target 0x180316e5e original byte 0x48 replaced by RET */
/* target 0x18032f248 original byte 0x48 replaced by RET */
/* target 0x18032f27e original byte 0x48 replaced by RET */
/* target 0x18032f2b9 original byte 0x48 replaced by RET */
/* target 0x18032f313 original byte 0x48 replaced by RET */
/* target 0x18032f34c original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00125_180315d7d(void)

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
  longlong unaff_RBX;
  longlong *in_stack_00000040;
  code *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  code *in_stack_000000e0;
  
  (**(code **)(unaff_RBX + 0x518))(in_stack_00000040,&stack0x00001978,5);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
  (*in_stack_000000e0)(in_stack_00000040);
  uVar7 = (*in_stack_00000048)(in_stack_00000040,in_stack_00000080);
  DAT_1804b1558 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b1558,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                    (in_stack_00000040,2,in_stack_00000090,0,in_stack_00000058);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x22b1);
  (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar7,in_stack_000000a8,uVar8)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
      lVar5 = DAT_1804b5a08;
      uVar7 = DAT_1804b59f0;
      if (cVar6 == '\0') {
        if (DAT_1804b1560 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000003f4,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001968,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
          DAT_1804b1560 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
        }
        (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,DAT_1804b1560,in_stack_000000a0)
        ;
        (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        return;
      }
    }
  }
  return;
}

