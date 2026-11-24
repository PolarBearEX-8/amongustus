/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=96 entry=1803938e4 blocks=28 maxInstructions=50000 */

/* cut edge: 0x1803938e4 -> 0x1803351d6 */
/* cut edge: 0x18039391f -> 0x1803351d6 */
/* cut edge: 0x18039395d -> 0x1803351d6 */
/* cut edge: 0x180393b0f -> 0x1803351d6 */
/* cut edge: 0x180393c64 -> 0x1803351d6 */
/* cut edge: 0x180393ca7 -> 0x1803351d6 */
/* cut edge: 0x180393e47 -> 0x1803351d6 */
/* cut edge: 0x180393f26 -> 0x1803351d6 */
/* cut edge: 0x1803940b9 -> 0x1803351d6 */
/* cut edge: 0x180394193 -> 0x1803351d6 */
/* cut edge: 0x1803941d9 -> 0x1803351d6 */
/* cut edge: 0x180394217 -> 0x1803351d6 */
/* cut edge: 0x1803943ae -> 0x1803351d6 */
/* cut edge: 0x1803944db -> 0x1803351d6 */
/* cut edge: 0x180394519 -> 0x1803351d6 */
/* cut edge: 0x180394553 -> 0x1803351d6 */
/* cut edge: 0x1803945b2 -> 0x1803351d6 */
/* cut edge: 0x180394763 -> 0x1803351d6 */
/* cut edge: 0x180394763 -> 0x1803948af */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803948af original byte 0x48 replaced by RET */


void region_00096_1803938e4(void)

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
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000f0;
  ulonglong in_stack_00000140;
  ulonglong in_stack_00000158;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_000001c8;
  undefined8 in_stack_00000248;
  undefined8 in_stack_00000250;
  undefined8 in_stack_00000838;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,in_stack_000000d0,in_stack_000000a8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
    uVar7 = FUN_180010980(in_stack_00000048);
    uVar6 = (*pcVar1)(in_stack_00000048,uVar6,uVar7);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b2de8 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar8 = FUN_180333500(in_stack_00000048);
          uVar7 = DAT_1804b59f0;
          uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000046e,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000048);
          uVar11 = FUN_180002550(in_stack_00000048);
          uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00006090,0x2a);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
          DAT_1804b2de8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b2de8,in_stack_00000078);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000088,0);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_000000f0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001c8);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,2,uVar6);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc0096f727d872c)
        ;
        (*pcVar1)(in_stack_00000048,uVar8,3,uVar6);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
        (*pcVar1)(in_stack_00000048,uVar8,4,uVar6);
        (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
          uVar6 = FUN_18001ee70(in_stack_00000048);
          uVar7 = (*pcVar1)(in_stack_00000048,uVar6);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b2df0 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1803335a0(in_stack_00000048);
                uVar8 = DAT_1804b59f0;
                uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000046c,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar11 = FUN_1800024f0(in_stack_00000048);
                uVar12 = FUN_180002550(in_stack_00000048);
                uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003b70,0x16);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar14 = FUN_1800025a0(in_stack_00000048);
                uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
                DAT_1804b2df0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2df0,in_stack_00000078);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x50e0);
              (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                  in_stack_00000158 ^ in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b2df8 == 0) {
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar10 = FUN_1803335a0(in_stack_00000048);
                    uVar9 = DAT_1804b59f0;
                    uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000046a,1);
                    lVar2 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar12 = FUN_1800024f0(in_stack_00000048);
                    uVar13 = FUN_180002550(in_stack_00000048);
                    uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003b40,0x16);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar15 = FUN_1800025a0(in_stack_00000048);
                    uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                    (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                    uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11);
                    DAT_1804b2df8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                  }
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b2df8,in_stack_00000078);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                     (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2a89);
                  (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                      in_stack_00000140 ^ in_stack_000000c8);
                  (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
                    uVar10 = FUN_18001eed0(in_stack_00000048);
                    uVar11 = FUN_18001ef30(in_stack_00000048);
                    uVar10 = (*pcVar1)(in_stack_00000048,uVar10,uVar11);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar11 = FUN_18001ef80(in_stack_00000048);
                      uVar9 = (*pcVar1)(in_stack_00000048,uVar9,uVar11,uVar10);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804b2e00 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar11 = FUN_180333500(in_stack_00000048);
                            uVar10 = DAT_1804b59f0;
                            uVar12 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00000468,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar13 = FUN_1800024f0(in_stack_00000048);
                            uVar14 = FUN_180002550(in_stack_00000048);
                            uVar15 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00002c20,0x19);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar16 = FUN_1800025a0(in_stack_00000048);
                            uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar16);
                            (*pcVar3)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
                            uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar11,uVar10,
                                               uVar12);
                            DAT_1804b2e00 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                          }
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804b2e00,in_stack_00000078);
                          uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                             (in_stack_00000048,4,in_stack_00000088,0);
                          (**(code **)(*in_stack_00000048 + 0x570))
                                    (in_stack_00000048,uVar11,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_00000248);
                          (*pcVar1)(in_stack_00000048,uVar11,1,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             0xdfcc108d1c27cf45);
                          (*pcVar1)(in_stack_00000048,uVar11,2,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_000000c8);
                          (*pcVar1)(in_stack_00000048,uVar11,3,uVar9);
                          (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
                            uVar9 = FUN_18001f030(in_stack_00000048);
                            (*pcVar1)(in_stack_00000048,in_stack_00000838,uVar9);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              (**(code **)(*in_stack_00000048 + 0x420))
                                        (in_stack_00000048,in_stack_000001a0,in_stack_00000250);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                                uVar9 = FUN_18001f0d0(in_stack_00000048);
                                (*pcVar1)(in_stack_00000048,uVar7,uVar6,uVar9,uVar8);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    if (DAT_1804b2e08 == 0) {
                                      lVar2 = *in_stack_00000048;
                                      pcVar1 = *(code **)(lVar2 + 0x390);
                                      uVar8 = FUN_180333500(in_stack_00000048);
                                      uVar6 = DAT_1804b59f0;
                                      uVar9 = (**(code **)(lVar2 + 0x518))
                                                        (in_stack_00000048,&stack0x00000466,1);
                                      lVar2 = *in_stack_00000048;
                                      pcVar3 = *(code **)(lVar2 + 0x390);
                                      uVar10 = FUN_1800024f0(in_stack_00000048);
                                      uVar11 = FUN_180002550(in_stack_00000048);
                                      uVar12 = (**(code **)(lVar2 + 0x518))
                                                         (in_stack_00000048,&stack0x00006030,0x2a);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar13 = FUN_1800025a0(in_stack_00000048);
                                      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13)
                                      ;
                                      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,
                                                        uVar6,uVar9);
                                      DAT_1804b2e08 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar6);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar6);
                                    }
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,DAT_1804b2e08,
                                                       in_stack_00000078);
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,5,in_stack_00000088,0);
                                    (**(code **)(*in_stack_00000048 + 0x570))
                                              (in_stack_00000048,uVar8,0,in_stack_00000168);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,in_stack_00000170);
                                    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                                    (**(code **)(*in_stack_00000048 + 0x570))
                                              (in_stack_00000048,uVar8,2,uVar7);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,0xdfc79c58f7839374);
                                    (*pcVar1)(in_stack_00000048,uVar8,3,uVar7);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,in_stack_000000c8);
                                    (*pcVar1)(in_stack_00000048,uVar8,4,uVar7);
                                    (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar6,in_stack_00000080,uVar8);
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
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
      }
    }
  }
  return;
}

