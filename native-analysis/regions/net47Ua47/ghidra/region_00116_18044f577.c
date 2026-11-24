/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=116 entry=18044f577 blocks=33 maxInstructions=50000 */

/* cut edge: 0x18044f577 -> 0x180473ae8 */
/* cut edge: 0x18044f58d -> 0x180473ae8 */
/* cut edge: 0x18044f7ac -> 0x180473ae8 */
/* cut edge: 0x18044f86b -> 0x180473ae8 */
/* cut edge: 0x18044f873 -> 0x180473ae8 */
/* cut edge: 0x18044fa25 -> 0x180473ae8 */
/* cut edge: 0x18044fb17 -> 0x180473ae8 */
/* cut edge: 0x18044fb2d -> 0x180473ae8 */
/* cut edge: 0x18044fcdf -> 0x180473ae8 */
/* cut edge: 0x18044fdd1 -> 0x180473ae8 */
/* cut edge: 0x18044fde7 -> 0x180473ae8 */
/* cut edge: 0x18044ff99 -> 0x180473ae8 */
/* cut edge: 0x180450090 -> 0x180473ae8 */
/* cut edge: 0x1804500a6 -> 0x180473ae8 */
/* cut edge: 0x1804502ca -> 0x180450e34 */
/* cut edge: 0x1804502df -> 0x180473ae8 */
/* cut edge: 0x1804502f5 -> 0x180473ae8 */
/* cut edge: 0x1804504a7 -> 0x180473ae8 */
/* cut edge: 0x1804504a7 -> 0x180450599 */
/* target 0x180450599 original byte 0x48 replaced by RET */
/* target 0x180450e34 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00116_18044f577(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong lVar13;
  ulonglong uVar14;
  ulonglong unaff_R15;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000060;
  ulonglong in_stack_00000068;
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
  int in_stack_00000140;
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      uVar14 = (longlong)unaff_R15 % 0x41574;
      if (DAT_1804b42a0 == 0) {
        lVar13 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar13 + 0x390);
        uVar6 = FUN_1803335f0(in_stack_00000048);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x00000426,1);
        lVar13 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar13 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x00001580,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
        DAT_1804b42a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        unaff_R15 = in_stack_00000060;
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b42a0,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x45f1);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         uVar14 ^ in_stack_000000c8 ^ 0xfe613a2e8a6f91d9);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
      lVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8)
      ;
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        if (0 < in_stack_00000140) {
          return;
        }
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b43b8 == 0) {
              lVar4 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar4 + 0x390);
              uVar6 = FUN_1803335f0(in_stack_00000048);
              uVar12 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x000003e0,1);
              lVar4 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar4 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00001370,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
              DAT_1804b43b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b43b8,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1218);
            (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               (longlong)((in_stack_00000068 ^ unaff_R15) - lVar13) % 0x72d3f ^
                               in_stack_000000c8 ^ 0x9c3fe87d83e3656c);
            (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
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

