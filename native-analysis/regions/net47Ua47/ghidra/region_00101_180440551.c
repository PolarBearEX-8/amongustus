/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=101 entry=180440551 blocks=41 maxInstructions=50000 */

/* cut edge: 0x180440551 -> 0x180473ae8 */
/* cut edge: 0x180440704 -> 0x180473ae8 */
/* cut edge: 0x1804407fb -> 0x180473ae8 */
/* cut edge: 0x180440811 -> 0x180473ae8 */
/* cut edge: 0x1804409c4 -> 0x180473ae8 */
/* cut edge: 0x180440ac0 -> 0x180473ae8 */
/* cut edge: 0x180440ad6 -> 0x180473ae8 */
/* cut edge: 0x180440c93 -> 0x180473ae8 */
/* cut edge: 0x180440d8f -> 0x1803dd67b */
/* cut edge: 0x180440dbe -> 0x180473ae8 */
/* cut edge: 0x180440dd4 -> 0x180473ae8 */
/* cut edge: 0x180440e3c -> 0x18044603e */
/* cut edge: 0x180440e65 -> 0x180446076 */
/* cut edge: 0x180440ec4 -> 0x1804460c7 */
/* cut edge: 0x180440ed4 -> 0x180446100 */
/* cut edge: 0x180440fb0 -> 0x180473ae8 */
/* cut edge: 0x1804410a7 -> 0x180473ae8 */
/* cut edge: 0x1804410bd -> 0x180473ae8 */
/* cut edge: 0x180441125 -> 0x180446138 */
/* cut edge: 0x180441151 -> 0x180446170 */
/* cut edge: 0x1804411ad -> 0x1804461c1 */
/* cut edge: 0x1804411bd -> 0x1804461fb */
/* cut edge: 0x180441298 -> 0x180473ae8 */
/* cut edge: 0x18044138f -> 0x180473ae8 */
/* cut edge: 0x1804413a5 -> 0x180473ae8 */
/* cut edge: 0x1804413bb -> 0x180441589 */
/* cut edge: 0x18044140d -> 0x180446232 */
/* cut edge: 0x180441439 -> 0x18044626d */
/* cut edge: 0x180441498 -> 0x1804462c0 */
/* cut edge: 0x1804414a8 -> 0x1804462fb */
/* cut edge: 0x180441501 -> 0x180441589 */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x180441589 original byte 0x48 replaced by RET */
/* target 0x18044603e original byte 0x48 replaced by RET */
/* target 0x180446076 original byte 0x48 replaced by RET */
/* target 0x1804460c7 original byte 0x48 replaced by RET */
/* target 0x180446100 original byte 0x48 replaced by RET */
/* target 0x180446138 original byte 0x48 replaced by RET */
/* target 0x180446170 original byte 0x48 replaced by RET */
/* target 0x1804461c1 original byte 0x48 replaced by RET */
/* target 0x1804461fb original byte 0x48 replaced by RET */
/* target 0x180446232 original byte 0x48 replaced by RET */
/* target 0x18044626d original byte 0x48 replaced by RET */
/* target 0x1804462c0 original byte 0x48 replaced by RET */
/* target 0x1804462fb original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00101_180440551(void)

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
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804b4940 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar6 = FUN_1803335f0(in_stack_00000048);
      uVar12 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000278,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000c70,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
      (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
      DAT_1804b4940 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b4940,in_stack_000000a0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x47b9);
    (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       in_stack_00000068 % 0x26421 ^ in_stack_000000c8 ^ 0x2bd351c136b73030);
    (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804b4948 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar6 = FUN_1803335f0(in_stack_00000048);
            uVar12 = DAT_1804b59f0;
            uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000276,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000c60,5);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
            (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
            DAT_1804b4948 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b4948,in_stack_000000a0);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x7460);
          (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                             in_stack_00000068 % 0xd1518 ^ in_stack_000000c8 ^ 0x7b30fa93f88b6e61);
          (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
          (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                if (DAT_1804b4950 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar6 = FUN_1803335f0(in_stack_00000048);
                  uVar12 = DAT_1804b59f0;
                  uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000274,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar8 = FUN_1800024f0(in_stack_00000048);
                  uVar9 = FUN_180002550(in_stack_00000048);
                  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000c50,5);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar11 = FUN_1800025a0(in_stack_00000048);
                  uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
                  (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                  uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
                  DAT_1804b4950 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                }
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b4950,in_stack_000000a0);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x54fd);
                (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                   in_stack_00000068 % 0xcc44a ^ in_stack_000000c8 ^
                                   0x6b8dc06ba3472efe);
                (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                (**(code **)(*in_stack_00000048 + 0x1a0))
                          (in_stack_00000048,uVar12,in_stack_000000d8);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  return;
                }
              }
            }
          }
        }
      }
    }
  }
  return;
}

