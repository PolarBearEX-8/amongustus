/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=144 entry=180328e42 blocks=49 maxInstructions=50000 */

/* cut edge: 0x180328e42 -> 0x180333186 */
/* cut edge: 0x180328e58 -> 0x180333186 */
/* cut edge: 0x180329011 -> 0x18032bcc0 */
/* cut edge: 0x180329076 -> 0x180333186 */
/* cut edge: 0x18032907e -> 0x180333186 */
/* cut edge: 0x1803290e6 -> 0x1803322a2 */
/* cut edge: 0x18032910f -> 0x1803322da */
/* cut edge: 0x18032916b -> 0x18033232b */
/* cut edge: 0x18032917b -> 0x180332364 */
/* cut edge: 0x180329253 -> 0x180333186 */
/* cut edge: 0x18032934a -> 0x180333186 */
/* cut edge: 0x180329360 -> 0x180333186 */
/* cut edge: 0x1803293c8 -> 0x18033239b */
/* cut edge: 0x1803293f1 -> 0x1803323d3 */
/* cut edge: 0x180329450 -> 0x180332424 */
/* cut edge: 0x180329460 -> 0x18033245d */
/* cut edge: 0x18032953b -> 0x180333186 */
/* cut edge: 0x180329632 -> 0x180333186 */
/* cut edge: 0x180329648 -> 0x180333186 */
/* cut edge: 0x1803296b3 -> 0x180332494 */
/* cut edge: 0x1803296df -> 0x1803324cc */
/* cut edge: 0x18032973b -> 0x18033251d */
/* cut edge: 0x18032974b -> 0x180332556 */
/* cut edge: 0x180329826 -> 0x180333186 */
/* cut edge: 0x18032991b -> 0x180333186 */
/* cut edge: 0x180329931 -> 0x180333186 */
/* cut edge: 0x180329999 -> 0x18033258d */
/* cut edge: 0x1803299c5 -> 0x1803325c8 */
/* cut edge: 0x180329a29 -> 0x180332622 */
/* cut edge: 0x180329a39 -> 0x18033265b */
/* cut edge: 0x180329b78 -> 0x180333186 */
/* cut edge: 0x180329c1f -> 0x18029ad3c */
/* cut edge: 0x180329c31 -> 0x1802f24e4 */
/* cut edge: 0x180329c3e -> 0x180333186 */
/* cut edge: 0x180329c46 -> 0x180333186 */
/* cut edge: 0x180329c5c -> 0x180329def */
/* cut edge: 0x180329cae -> 0x180329def */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x1802f24e4 original byte 0x48 replaced by RET */
/* target 0x180329def original byte 0x48 replaced by RET */
/* target 0x18032bcc0 original byte 0xff replaced by RET */
/* target 0x1803322a2 original byte 0x48 replaced by RET */
/* target 0x1803322da original byte 0x48 replaced by RET */
/* target 0x18033232b original byte 0x48 replaced by RET */
/* target 0x180332364 original byte 0x48 replaced by RET */
/* target 0x18033239b original byte 0x48 replaced by RET */
/* target 0x1803323d3 original byte 0x48 replaced by RET */
/* target 0x180332424 original byte 0x48 replaced by RET */
/* target 0x18033245d original byte 0x48 replaced by RET */
/* target 0x180332494 original byte 0x48 replaced by RET */
/* target 0x1803324cc original byte 0x48 replaced by RET */
/* target 0x18033251d original byte 0x48 replaced by RET */
/* target 0x180332556 original byte 0x48 replaced by RET */
/* target 0x18033258d original byte 0x48 replaced by RET */
/* target 0x1803325c8 original byte 0x48 replaced by RET */
/* target 0x180332622 original byte 0x48 replaced by RET */
/* target 0x18033265b original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00144_180328e42(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000090;
  undefined8 in_stack_000000a0;
  
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b0ec0 == 0) {
        lVar1 = *in_stack_00000040;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_1803335f0(in_stack_00000040);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000005a0,1);
        lVar1 = *in_stack_00000040;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000040);
        uVar9 = FUN_180002550(in_stack_00000040);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002598,5);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000040);
        uVar11 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar11);
        (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar12,uVar7);
        DAT_1804b0ec0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
      }
      (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,DAT_1804b0ec0,in_stack_000000a0);
      (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      return;
    }
  }
  return;
}

