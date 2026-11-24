/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=147 entry=18046e547 blocks=28 maxInstructions=50000 */

/* cut edge: 0x18046e6a4 -> 0x180473ae8 */
/* cut edge: 0x18046e796 -> 0x180473ae8 */
/* cut edge: 0x18046e7ac -> 0x180473ae8 */
/* cut edge: 0x18046e971 -> 0x180473ae8 */
/* cut edge: 0x18046ea66 -> 0x180473ae8 */
/* cut edge: 0x18046ea7c -> 0x180473ae8 */
/* cut edge: 0x18046ec4f -> 0x180473ae8 */
/* cut edge: 0x18046ed44 -> 0x18046f8ab */
/* cut edge: 0x18046ed56 -> 0x180473ae8 */
/* cut edge: 0x18046ed69 -> 0x180473ae8 */
/* cut edge: 0x18046ef2e -> 0x180473ae8 */
/* cut edge: 0x18046f020 -> 0x180473ae8 */
/* cut edge: 0x18046f036 -> 0x180473ae8 */
/* cut edge: 0x18046f1fe -> 0x180473ae8 */
/* cut edge: 0x18046f2f0 -> 0x180473ae8 */
/* cut edge: 0x18046f306 -> 0x180473ae8 */
/* cut edge: 0x18046f4cb -> 0x180473ae8 */
/* cut edge: 0x18046f4cb -> 0x18046f5c0 */
/* target 0x18046f5c0 original byte 0x48 replaced by RET */
/* target 0x18046f8ab original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00147_18046e547(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong *in_stack_00000048;
  longlong in_stack_00000068;
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
  code *pcStack0000000000000130;
  
  lVar1 = *in_stack_00000048;
  pcStack0000000000000130 = *(code **)(lVar1 + 0x390);
  uVar5 = FUN_1803335f0(in_stack_00000048);
  uVar11 = DAT_1804b59f0;
  uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002c2,1);
  lVar1 = *in_stack_00000048;
  pcVar2 = *(code **)(lVar1 + 0x390);
  uVar7 = FUN_1800024f0(in_stack_00000048);
  uVar8 = FUN_180002550(in_stack_00000048);
  uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000dc0,5);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
  uVar10 = FUN_1800025a0(in_stack_00000048);
  uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
  (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
  uVar11 = (*pcStack0000000000000130)(in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
  DAT_1804b4820 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b4820,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x43f6);
  (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar4 == '\0') {
        if (DAT_1804b4828 == 0) {
          lVar1 = *in_stack_00000048;
          pcStack0000000000000130 = *(code **)(lVar1 + 0x390);
          uVar5 = FUN_1803335f0(in_stack_00000048);
          uVar11 = DAT_1804b59f0;
          uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002c0,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_1800024f0(in_stack_00000048);
          uVar8 = FUN_180002550(in_stack_00000048);
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000db0,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar10 = FUN_1800025a0(in_stack_00000048);
          uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
          (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
          uVar11 = (*pcStack0000000000000130)
                             (in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
          DAT_1804b4828 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b4828,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x15bc);
        (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0xdc2a8 ^ in_stack_000000c8 ^ 0xb7663fa7235caffa);
        (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 == '\0') {
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar4 == '\0') {
              if (DAT_1804b4830 == 0) {
                lVar1 = *in_stack_00000048;
                pcStack0000000000000130 = *(code **)(lVar1 + 0x390);
                uVar5 = FUN_1803335f0(in_stack_00000048);
                uVar11 = DAT_1804b59f0;
                uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002be,1);
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar7 = FUN_1800024f0(in_stack_00000048);
                uVar8 = FUN_180002550(in_stack_00000048);
                uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000da0,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar10 = FUN_1800025a0(in_stack_00000048);
                uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
                (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                uVar11 = (*pcStack0000000000000130)
                                   (in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
                DAT_1804b4830 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
              }
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b4830,in_stack_000000a0);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x347);
              (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_00000068 % 0x2f6e7 ^ in_stack_000000c8 ^
                                 0xdbf02ead59c48c3d);
              (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                return;
              }
            }
          }
        }
      }
    }
  }
  return;
}

