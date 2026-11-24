/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=96 entry=18043b5fb blocks=33 maxInstructions=50000 */

/* cut edge: 0x18043b5fb -> 0x180473ae8 */
/* cut edge: 0x18043b6f2 -> 0x1803dd67b */
/* cut edge: 0x18043b723 -> 0x180473ae8 */
/* cut edge: 0x18043b739 -> 0x180473ae8 */
/* cut edge: 0x18043b8e7 -> 0x180473ae8 */
/* cut edge: 0x18043b9de -> 0x180473ae8 */
/* cut edge: 0x18043b9f4 -> 0x180473ae8 */
/* cut edge: 0x18043bba7 -> 0x180473ae8 */
/* cut edge: 0x18043bc9e -> 0x180473ae8 */
/* cut edge: 0x18043bcb4 -> 0x180473ae8 */
/* cut edge: 0x18043be67 -> 0x180473ae8 */
/* cut edge: 0x18043bf63 -> 0x180473ae8 */
/* cut edge: 0x18043bf79 -> 0x180473ae8 */
/* cut edge: 0x18043c13b -> 0x180473ae8 */
/* cut edge: 0x18043c230 -> 0x1803dd67b */
/* cut edge: 0x18043c25f -> 0x1803dd6ca */
/* cut edge: 0x18043c297 -> 0x1803dd717 */
/* cut edge: 0x18043c2ce -> 0x1803dd717 */
/* cut edge: 0x18043c2ea -> 0x1803dd734 */
/* cut edge: 0x18043c323 -> 0x1803dd78d */
/* cut edge: 0x18043c35b -> 0x180473ae8 */
/* cut edge: 0x18043c371 -> 0x180473ae8 */
/* cut edge: 0x18043c522 -> 0x180473ae8 */
/* cut edge: 0x18043c522 -> 0x18043c619 */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x1803dd6ca original byte 0x4c replaced by RET */
/* target 0x1803dd717 original byte 0x48 replaced by RET */
/* target 0x1803dd734 original byte 0x48 replaced by RET */
/* target 0x1803dd78d original byte 0x48 replaced by RET */
/* target 0x18043c619 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00096_18043b5fb(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong lVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  ulonglong unaff_R12;
  ulonglong unaff_R13;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  longlong in_stack_00000118;
  undefined8 in_stack_000001e0;
  undefined2 in_stack_000001e8;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x573f);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  lVar9 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    lVar9 = lVar9 + (unaff_R12 ^ unaff_R13);
    if (in_stack_00000118 == 0) {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
      if (DAT_1804b4b80 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar7 = FUN_1803335f0(in_stack_00000048);
        uVar6 = DAT_1804b59f0;
        in_stack_000001e8 = 0x7a;
        uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000001e8,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000007f0,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
        DAT_1804b4b80 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b4b80,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x40b);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         lVar9 % 0x1956e ^ in_stack_000000c8 ^ 0x392f3637da3a3115);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0'))
         && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
        if (DAT_1804b4b88 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar7 = FUN_1803335f0(in_stack_00000048);
          uVar6 = DAT_1804b59f0;
          in_stack_000001e0._6_2_ = 0x7a;
          uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,(longlong)&stack0x000001e0 + 6,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000048);
          uVar11 = FUN_180002550(in_stack_00000048);
          uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000007e0,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
          DAT_1804b4b88 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b4b88,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x62ad);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           lVar9 % 0xa6618 ^ in_stack_000000c8 ^ 0x596c7d6c03d8ca17);
        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')
            ) && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
          if (DAT_1804b4b90 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar7 = FUN_1803335f0(in_stack_00000048);
            uVar6 = DAT_1804b59f0;
            in_stack_000001e0._4_2_ = 0x7a;
            uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,(longlong)&stack0x000001e0 + 4,1)
            ;
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000007d0,5);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
            DAT_1804b4b90 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b4b90,in_stack_000000a0);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5c5);
          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                             lVar9 % 0x409e9 ^ in_stack_000000c8 ^ 0x846d1bd32593927);
          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
          (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (((cVar5 == '\0') &&
              (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) &&
             (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
            if (DAT_1804b4b98 == 0) {
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar7 = FUN_1803335f0(in_stack_00000048);
              uVar6 = DAT_1804b59f0;
              in_stack_000001e0._2_2_ = 0x7a;
              uVar8 = (**(code **)(lVar2 + 0x518))
                                (in_stack_00000048,(longlong)&stack0x000001e0 + 2,1);
              lVar2 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar2 + 0x390);
              uVar10 = FUN_1800024f0(in_stack_00000048);
              uVar11 = FUN_180002550(in_stack_00000048);
              uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000007c0,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar13 = FUN_1800025a0(in_stack_00000048);
              uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
              (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
              uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
              DAT_1804b4b98 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
            }
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b4b98,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x274c);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               lVar9 % 0x30865 ^ in_stack_000000c8 ^ 0x6d08a5f5e28f2b9a);
            (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              return;
            }
          }
        }
      }
    }
  }
  return;
}

