/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=141 entry=180325db7 blocks=34 maxInstructions=50000 */

/* cut edge: 0x180325db7 -> 0x180333186 */
/* cut edge: 0x180325eac -> 0x180333186 */
/* cut edge: 0x180325ec2 -> 0x180333186 */
/* cut edge: 0x18032607b -> 0x18032bcc0 */
/* cut edge: 0x1803260e0 -> 0x180333186 */
/* cut edge: 0x1803260f6 -> 0x180333186 */
/* cut edge: 0x1803262a2 -> 0x180333186 */
/* cut edge: 0x180326399 -> 0x180333186 */
/* cut edge: 0x1803263af -> 0x180333186 */
/* cut edge: 0x18032655c -> 0x180333186 */
/* cut edge: 0x180326653 -> 0x180333186 */
/* cut edge: 0x180326669 -> 0x180333186 */
/* cut edge: 0x180326817 -> 0x180333186 */
/* cut edge: 0x18032690c -> 0x180333186 */
/* cut edge: 0x180326922 -> 0x180333186 */
/* cut edge: 0x180326ad6 -> 0x180333186 */
/* cut edge: 0x180326bcd -> 0x18030854c */
/* cut edge: 0x180326c0c -> 0x180333186 */
/* cut edge: 0x180326c14 -> 0x180333186 */
/* cut edge: 0x180326c2a -> 0x180326df6 */
/* cut edge: 0x180326c83 -> 0x180330e04 */
/* cut edge: 0x180326cac -> 0x180330e3c */
/* cut edge: 0x180326d0b -> 0x180330e8d */
/* cut edge: 0x180326d1b -> 0x180330ec6 */
/* cut edge: 0x180326d1b -> 0x180326d73 */
/* target 0x18030854c original byte 0x48 replaced by RET */
/* target 0x180326d73 original byte 0x48 replaced by RET */
/* target 0x180326df6 original byte 0x48 replaced by RET */
/* target 0x18032bcc0 original byte 0xff replaced by RET */
/* target 0x180330e04 original byte 0x48 replaced by RET */
/* target 0x180330e3c original byte 0x48 replaced by RET */
/* target 0x180330e8d original byte 0x48 replaced by RET */
/* target 0x180330ec6 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00141_180325db7(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
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
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,param_2,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0xc94);
  (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar6,in_stack_000000a8,uVar7)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b0e38 == 0) {
          lVar2 = *in_stack_00000040;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar7 = FUN_1803335f0(in_stack_00000040);
          uVar6 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000005c2,1);
          lVar2 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000040);
          uVar10 = FUN_180002550(in_stack_00000040);
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000026a8,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000040);
          uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
          (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
          DAT_1804b0e38 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
        }
        (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,DAT_1804b0e38,in_stack_000000a0)
        ;
        (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        return;
      }
    }
  }
  return;
}

