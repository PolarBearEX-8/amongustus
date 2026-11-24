/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=14 entry=1803e95e1 blocks=33 maxInstructions=50000 */

/* cut edge: 0x1803e95e1 -> 0x1803efb51 */
/* cut edge: 0x1803e95ee -> 0x180473ae8 */
/* cut edge: 0x1803e9747 -> 0x180473ae8 */
/* cut edge: 0x1803e9849 -> 0x1803f3d11 */
/* cut edge: 0x1803e9856 -> 0x18043f65a */
/* cut edge: 0x1803e9864 -> 0x180473ae8 */
/* cut edge: 0x1803e99bd -> 0x180473ae8 */
/* cut edge: 0x1803e9ab6 -> 0x18041bba7 */
/* cut edge: 0x1803e9ac3 -> 0x180473ae8 */
/* cut edge: 0x1803e9c1c -> 0x180473ae8 */
/* cut edge: 0x1803e9d15 -> 0x1803ea842 */
/* cut edge: 0x1803e9d30 -> 0x180473ae8 */
/* cut edge: 0x1803e9d38 -> 0x180473ae8 */
/* cut edge: 0x1803e9ee3 -> 0x180473ae8 */
/* cut edge: 0x1803e9fda -> 0x180473ae8 */
/* cut edge: 0x1803e9ff0 -> 0x180473ae8 */
/* cut edge: 0x1803ea19d -> 0x180473ae8 */
/* cut edge: 0x1803ea294 -> 0x180473ae8 */
/* cut edge: 0x1803ea2aa -> 0x180473ae8 */
/* cut edge: 0x1803ea45d -> 0x180473ae8 */
/* cut edge: 0x1803ea45d -> 0x1803ea552 */
/* target 0x1803ea552 original byte 0x48 replaced by RET */
/* target 0x1803ea842 original byte 0x84 replaced by RET */
/* target 0x1803efb51 original byte 0x48 replaced by RET */
/* target 0x1803f3d11 original byte 0x48 replaced by RET */
/* target 0x18041bba7 original byte 0x48 replaced by RET */
/* target 0x18043f65a original byte 0x4c replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00014_1803e95e1(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  int iVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  int unaff_ESI;
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
  int in_stack_000000e0;
  int in_stack_00000110;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00002db0;
  undefined8 in_stack_00002db8;
  undefined8 in_stack_00002dc0;
  
  if (unaff_ESI == in_stack_000000e0) {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804b3858 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_180333550(in_stack_00000048);
      uVar13 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006b8,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000029f0,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,uVar8);
      DAT_1804b3858 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b3858,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x45ce);
    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002dc0);
    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
    iVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar13,in_stack_00000120);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      if (iVar6 == in_stack_000000e0) {
        return;
      }
      if (in_stack_00000110 == 1) {
        return;
      }
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b3860 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000048);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006b6,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000029e0,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,uVar8);
          DAT_1804b3860 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b3860,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0xc61);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002db8);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
        iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar13,in_stack_00000120);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          if (iVar6 == in_stack_000000e0) {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b3868 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_180333550(in_stack_00000048);
              uVar13 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006b4,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000029d0,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,uVar8);
              DAT_1804b3868 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b3868,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0xc63);
            (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_00002db0);
            (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
            iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar13,in_stack_00000120);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (iVar6 != in_stack_000000e0) {
                return;
              }
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b38a0 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar7 = FUN_1803335f0(in_stack_00000048);
                    uVar13 = DAT_1804b59f0;
                    uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006a6,1);
                    lVar1 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar9 = FUN_1800024f0(in_stack_00000048);
                    uVar10 = FUN_180002550(in_stack_00000048);
                    uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002960,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar12 = FUN_1800025a0(in_stack_00000048);
                    uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                    (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                    uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,uVar8);
                    DAT_1804b38a0 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                  }
                  uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b38a0,in_stack_000000a0);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x26b3);
                  (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     in_stack_00000068 % 0x1ba48 ^ in_stack_000000c8 ^
                                     0x59cc14e8ca99cec7);
                  (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                  uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                  (**(code **)(*in_stack_00000048 + 0x1a0))
                            (in_stack_00000048,uVar13,in_stack_000000d8);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        if (DAT_1804b38a8 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar7 = FUN_1803335f0(in_stack_00000048);
                          uVar13 = DAT_1804b59f0;
                          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006a4,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar9 = FUN_1800024f0(in_stack_00000048);
                          uVar10 = FUN_180002550(in_stack_00000048);
                          uVar11 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00002950,5);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar12 = FUN_1800025a0(in_stack_00000048);
                          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,uVar8)
                          ;
                          DAT_1804b38a8 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                        }
                        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804b38a8,in_stack_000000a0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x31d0);
                        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           in_stack_00000068 % 0xd61d3 ^ in_stack_000000c8 ^
                                           0x190e73d3fc5f8996);
                        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                        (**(code **)(*in_stack_00000048 + 0x1a0))
                                  (in_stack_00000048,uVar13,in_stack_000000d8);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              if (DAT_1804b38b0 == 0) {
                                lVar1 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar1 + 0x390);
                                uVar7 = FUN_1803335f0(in_stack_00000048);
                                uVar13 = DAT_1804b59f0;
                                uVar8 = (**(code **)(lVar1 + 0x518))
                                                  (in_stack_00000048,&stack0x000006a2,1);
                                lVar1 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar1 + 0x390);
                                uVar9 = FUN_1800024f0(in_stack_00000048);
                                uVar10 = FUN_180002550(in_stack_00000048);
                                uVar11 = (**(code **)(lVar1 + 0x518))
                                                   (in_stack_00000048,&stack0x00002940,5);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar12 = FUN_1800025a0(in_stack_00000048);
                                uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,
                                                   uVar8);
                                DAT_1804b38b0 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar13);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                              }
                              uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b38b0,in_stack_000000a0)
                              ;
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000090,0);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000b8,
                                                 in_stack_000000c0,0x26f);
                              (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 in_stack_00000068 % 0xb1889 ^ in_stack_000000c8 ^
                                                 0x77c6de28378373bf);
                              (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                              uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar13,in_stack_000000d8);
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
      }
    }
  }
  return;
}

