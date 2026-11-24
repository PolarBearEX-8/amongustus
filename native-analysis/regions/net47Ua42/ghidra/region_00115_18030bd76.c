/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=115 entry=18030bd76 blocks=30 maxInstructions=50000 */

/* cut edge: 0x18030bf0e -> 0x180333186 */
/* cut edge: 0x18030c005 -> 0x180333186 */
/* cut edge: 0x18030c01b -> 0x180333186 */
/* cut edge: 0x18030c1ce -> 0x180333186 */
/* cut edge: 0x18030c2c3 -> 0x180333186 */
/* cut edge: 0x18030c2d9 -> 0x180333186 */
/* cut edge: 0x18030c494 -> 0x180333186 */
/* cut edge: 0x18030c589 -> 0x180308142 */
/* cut edge: 0x18030c5ae -> 0x180317ded */
/* cut edge: 0x18030c5b8 -> 0x180333186 */
/* cut edge: 0x18030c5ce -> 0x180333186 */
/* cut edge: 0x18030c77a -> 0x180333186 */
/* cut edge: 0x18030c871 -> 0x180333186 */
/* cut edge: 0x18030c887 -> 0x180333186 */
/* cut edge: 0x18030ca34 -> 0x180333186 */
/* cut edge: 0x18030cb2b -> 0x180333186 */
/* cut edge: 0x18030cb41 -> 0x180333186 */
/* cut edge: 0x18030ccef -> 0x180333186 */
/* cut edge: 0x18030ccef -> 0x18030cde4 */
/* target 0x180308142 original byte 0x48 replaced by RET */
/* target 0x18030cde4 original byte 0x48 replaced by RET */
/* target 0x180317ded original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


ulonglong region_00115_18030bd76(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  ulonglong uVar12;
  longlong *in_stack_00000040;
  ulonglong in_stack_00000048;
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
  ulonglong in_stack_000000d8;
  
  if (DAT_1804b0dd8 == 0) {
    lVar1 = *in_stack_00000040;
    pcVar2 = *(code **)(lVar1 + 0x390);
    uVar5 = FUN_1803335f0(in_stack_00000040);
    uVar11 = DAT_1804b59f0;
    uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000005da,1);
    lVar1 = *in_stack_00000040;
    pcVar3 = *(code **)(lVar1 + 0x390);
    uVar7 = FUN_1800024f0(in_stack_00000040);
    uVar8 = FUN_180002550(in_stack_00000040);
    uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002768,5);
    pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
    uVar10 = FUN_1800025a0(in_stack_00000040);
    uVar10 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar10);
    (*pcVar3)(in_stack_00000040,uVar7,uVar8,uVar9,uVar10);
    uVar11 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar5,uVar11,uVar6);
    DAT_1804b0dd8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar11);
    (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar11);
  }
  uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                     (in_stack_00000040,DAT_1804b0dd8,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x17ba);
  (*pcVar2)(in_stack_00000040,uVar5,0,uVar6);
  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                     in_stack_00000070 % 0x7872a ^ in_stack_000000b0 ^ 0x1e61981245d45681);
  (*pcVar2)(in_stack_00000040,uVar5,1,uVar6);
  uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                     (in_stack_00000040,uVar11,in_stack_000000a8,uVar5);
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar11,in_stack_000000d0);
  uVar12 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if ((char)uVar12 == '\0') {
    uVar12 = (**(code **)(*in_stack_00000040 + 0x720))();
    if ((char)uVar12 == '\0') {
      uVar12 = (**(code **)(*in_stack_00000040 + 0x720))();
      if ((char)uVar12 == '\0') {
        if (DAT_1804b0de0 == 0) {
          lVar1 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar5 = FUN_1803335f0(in_stack_00000040);
          uVar11 = DAT_1804b59f0;
          uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000005d8,1);
          lVar1 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_1800024f0(in_stack_00000040);
          uVar8 = FUN_180002550(in_stack_00000040);
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002758,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar10 = FUN_1800025a0(in_stack_00000040);
          uVar10 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar10);
          (*pcVar3)(in_stack_00000040,uVar7,uVar8,uVar9,uVar10);
          uVar11 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar5,uVar11,uVar6);
          DAT_1804b0de0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar11);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b0de0,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x38a9);
        (*pcVar2)(in_stack_00000040,uVar5,0,uVar6);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0xe67df ^ in_stack_000000b0 ^ 0x4e32a629c26c3c58);
        (*pcVar2)(in_stack_00000040,uVar5,1,uVar6);
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar11,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar11,in_stack_000000d0);
        uVar12 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if ((char)uVar12 == '\0') {
          uVar12 = (**(code **)(*in_stack_00000040 + 0x720))();
          if ((char)uVar12 == '\0') {
            uVar12 = (**(code **)(*in_stack_00000040 + 0x720))();
            if ((char)uVar12 == '\0') {
              if (DAT_1804b0de8 == 0) {
                lVar1 = *in_stack_00000040;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar5 = FUN_1803335f0(in_stack_00000040);
                uVar11 = DAT_1804b59f0;
                uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000005d6,1);
                lVar1 = *in_stack_00000040;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar7 = FUN_1800024f0(in_stack_00000040);
                uVar8 = FUN_180002550(in_stack_00000040);
                uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002748,5);
                pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                uVar10 = FUN_1800025a0(in_stack_00000040);
                uVar10 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar10);
                (*pcVar3)(in_stack_00000040,uVar7,uVar8,uVar9,uVar10);
                uVar11 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar5,uVar11,uVar6);
                DAT_1804b0de8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar11);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar11);
              }
              uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,DAT_1804b0de8,in_stack_000000a0);
              uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6ce1);
              (*pcVar2)(in_stack_00000040,uVar5,0,uVar6);
              pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000070 % 0xb2e51 ^ in_stack_000000b0 ^
                                 0x45c16c47a85d6951);
              (*pcVar2)(in_stack_00000040,uVar5,1,uVar6);
              uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,uVar11,in_stack_000000a8,uVar5);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar11,in_stack_000000d0);
              uVar12 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if ((char)uVar12 == '\0') {
                return in_stack_00000048 ^ in_stack_000000d8;
              }
            }
          }
        }
      }
    }
  }
  return uVar12;
}

