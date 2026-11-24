/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=134 entry=180461566 blocks=35 maxInstructions=50000 */

/* cut edge: 0x180461566 -> 0x180457548 */
/* cut edge: 0x180461579 -> 0x180473ae8 */
/* cut edge: 0x18046158f -> 0x180473ae8 */
/* cut edge: 0x180461747 -> 0x180473ae8 */
/* cut edge: 0x180461839 -> 0x180473ae8 */
/* cut edge: 0x18046184f -> 0x180473ae8 */
/* cut edge: 0x180461a04 -> 0x180473ae8 */
/* cut edge: 0x180461af6 -> 0x180473ae8 */
/* cut edge: 0x180461b0c -> 0x180473ae8 */
/* cut edge: 0x180461cc1 -> 0x180473ae8 */
/* cut edge: 0x180461db6 -> 0x180473ae8 */
/* cut edge: 0x180461dcc -> 0x180473ae8 */
/* cut edge: 0x180461f92 -> 0x180473ae8 */
/* cut edge: 0x180462087 -> 0x180457663 */
/* cut edge: 0x1804620a2 -> 0x180473ae8 */
/* cut edge: 0x1804620da -> 0x180473ae8 */
/* cut edge: 0x180462101 -> 0x180461485 */
/* cut edge: 0x18046210b -> 0x180473ae8 */
/* cut edge: 0x18046211e -> 0x180473ae8 */
/* cut edge: 0x1804622e3 -> 0x180473ae8 */
/* cut edge: 0x1804623d5 -> 0x180473ae8 */
/* cut edge: 0x1804623eb -> 0x180473ae8 */
/* cut edge: 0x180462401 -> 0x1804625b0 */
/* cut edge: 0x180462453 -> 0x1804625b0 */
/* target 0x180457548 original byte 0x48 replaced by RET */
/* target 0x180457663 original byte 0x48 replaced by RET */
/* target 0x180461485 original byte 0x29 replaced by RET */
/* target 0x1804625b0 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00134_180461566(void)

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
  ulonglong unaff_RSI;
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
  
  if ((unaff_RSI & 1) != 0) {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b44b8 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_1803335f0(in_stack_00000048);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003a0,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001190,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
        (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
        DAT_1804b44b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b44b8,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0xc97);
      (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         in_stack_00000068 % 0xd7fa2 ^ in_stack_000000c8 ^ 0xb0fc3bcdda54db7c);
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
            if (DAT_1804b44c0 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar6 = FUN_1803335f0(in_stack_00000048);
              uVar12 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000039e,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001180,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
              (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
              DAT_1804b44c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b44c0,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x647e);
            (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_00000068 % 0x1a6b8 ^ in_stack_000000c8 ^ 0xc0286161c7d3f06d)
            ;
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
                  if (DAT_1804b44c8 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar6 = FUN_1803335f0(in_stack_00000048);
                    uVar12 = DAT_1804b59f0;
                    uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000039c,1);
                    lVar1 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001170,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
                    (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
                    DAT_1804b44c8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                  }
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b44c8,in_stack_000000a0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4407);
                  (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     in_stack_00000068 % 0x4417e ^ in_stack_000000c8 ^
                                     0xe4611fa10420a1ea);
                  (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                  (**(code **)(*in_stack_00000048 + 0x1a0))
                            (in_stack_00000048,uVar12,in_stack_000000d8);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        if (DAT_1804b44d0 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar6 = FUN_1803335f0(in_stack_00000048);
                          uVar12 = DAT_1804b59f0;
                          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000039a,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar8 = FUN_1800024f0(in_stack_00000048);
                          uVar9 = FUN_180002550(in_stack_00000048);
                          uVar10 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00001160,5);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar11 = FUN_1800025a0(in_stack_00000048);
                          uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
                          (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                          uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7)
                          ;
                          DAT_1804b44d0 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                        }
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804b44d0,in_stack_000000a0);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x4c30);
                        (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           in_stack_00000068 % 0x9bc52 ^ in_stack_000000c8 ^
                                           0xa852afa3dab735b7);
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
        }
      }
    }
  }
  return;
}

