/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=115 entry=18044e54d blocks=29 maxInstructions=50000 */

/* cut edge: 0x18044e699 -> 0x180473ae8 */
/* cut edge: 0x18044e78b -> 0x180473ae8 */
/* cut edge: 0x18044e7a1 -> 0x180473ae8 */
/* cut edge: 0x18044e956 -> 0x180473ae8 */
/* cut edge: 0x18044ea4b -> 0x180473ae8 */
/* cut edge: 0x18044ea61 -> 0x180473ae8 */
/* cut edge: 0x18044ec24 -> 0x180473ae8 */
/* cut edge: 0x18044ed25 -> 0x1804502ca */
/* cut edge: 0x18044ed3c -> 0x1804538c4 */
/* cut edge: 0x18044ed41 -> 0x180473ae8 */
/* cut edge: 0x18044ed57 -> 0x180473ae8 */
/* cut edge: 0x18044ef09 -> 0x180473ae8 */
/* cut edge: 0x18044effb -> 0x180473ae8 */
/* cut edge: 0x18044f011 -> 0x180473ae8 */
/* cut edge: 0x18044f1c3 -> 0x180473ae8 */
/* cut edge: 0x18044f2b5 -> 0x180473ae8 */
/* cut edge: 0x18044f2cb -> 0x180473ae8 */
/* cut edge: 0x18044f480 -> 0x180473ae8 */
/* cut edge: 0x18044f480 -> 0x18044f577 */
/* target 0x18044f577 original byte 0x48 replaced by RET */
/* target 0x1804502ca original byte 0x48 replaced by RET */
/* target 0x1804538c4 original byte 0x8b replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00115_18044e54d(void)

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
  longlong in_stack_00000060;
  code *pcStack0000000000000068;
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
  
  lVar1 = *in_stack_00000048;
  pcStack0000000000000068 = *(code **)(lVar1 + 0x390);
  uVar5 = FUN_1803335f0(in_stack_00000048);
  uVar11 = DAT_1804b59f0;
  uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003e6,1);
  lVar1 = *in_stack_00000048;
  pcVar2 = *(code **)(lVar1 + 0x390);
  uVar7 = FUN_1800024f0(in_stack_00000048);
  uVar8 = FUN_180002550(in_stack_00000048);
  uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000013a0,5);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
  uVar10 = FUN_1800025a0(in_stack_00000048);
  uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
  (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
  uVar11 = (*pcStack0000000000000068)(in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
  DAT_1804b43a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b43a0,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x334a);
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
        if (DAT_1804b43a8 == 0) {
          lVar1 = *in_stack_00000048;
          pcStack0000000000000068 = *(code **)(lVar1 + 0x390);
          uVar5 = FUN_1803335f0(in_stack_00000048);
          uVar11 = DAT_1804b59f0;
          uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003e4,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_1800024f0(in_stack_00000048);
          uVar8 = FUN_180002550(in_stack_00000048);
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001390,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar10 = FUN_1800025a0(in_stack_00000048);
          uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
          (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
          uVar11 = (*pcStack0000000000000068)
                             (in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
          DAT_1804b43a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b43a8,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x6bde);
        (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000060 % 0x276a8 ^ in_stack_000000c8 ^ 0xb066440545fe96bb);
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
              if (DAT_1804b43b0 == 0) {
                lVar1 = *in_stack_00000048;
                pcStack0000000000000068 = *(code **)(lVar1 + 0x390);
                uVar5 = FUN_1803335f0(in_stack_00000048);
                uVar11 = DAT_1804b59f0;
                uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003e2,1);
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar7 = FUN_1800024f0(in_stack_00000048);
                uVar8 = FUN_180002550(in_stack_00000048);
                uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001380,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar10 = FUN_1800025a0(in_stack_00000048);
                uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
                (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                uVar11 = (*pcStack0000000000000068)
                                   (in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
                DAT_1804b43b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
              }
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b43b0,in_stack_000000a0);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x816);
              (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_00000060 % 0x2e795 ^ in_stack_000000c8 ^
                                 0x8a1742bc86f30ac8);
              (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
              pcStack0000000000000068 =
                   (code *)(**(code **)(*in_stack_00000048 + 0x1a0))
                                     (in_stack_00000048,uVar11,in_stack_000000d8);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                if (in_stack_00000118 == 0) {
                  return;
                }
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

