/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=94 entry=180391973 blocks=31 maxInstructions=50000 */

/* cut edge: 0x180391973 -> 0x1803351d6 */
/* cut edge: 0x180391aa0 -> 0x1803351d6 */
/* cut edge: 0x180391ade -> 0x1803351d6 */
/* cut edge: 0x180391b18 -> 0x1803351d6 */
/* cut edge: 0x180391b77 -> 0x1803351d6 */
/* cut edge: 0x180391d28 -> 0x1803351d6 */
/* cut edge: 0x180391e74 -> 0x1803351d6 */
/* cut edge: 0x180391ec1 -> 0x1803351d6 */
/* cut edge: 0x180391ef9 -> 0x1803948af */
/* cut edge: 0x180391f02 -> 0x1803351d6 */
/* cut edge: 0x180391f40 -> 0x1803351d6 */
/* cut edge: 0x180391f86 -> 0x1803351d6 */
/* cut edge: 0x180391fbf -> 0x1803351d6 */
/* cut edge: 0x180391ffd -> 0x1803351d6 */
/* cut edge: 0x1803921a9 -> 0x1803351d6 */
/* cut edge: 0x18039227e -> 0x1803351d6 */
/* cut edge: 0x1803922c7 -> 0x1803351d6 */
/* cut edge: 0x180392433 -> 0x1803351d6 */
/* cut edge: 0x180392515 -> 0x1803351d6 */
/* cut edge: 0x1803926c5 -> 0x1803351d6 */
/* cut edge: 0x1803927f2 -> 0x1803351d6 */
/* cut edge: 0x1803927f2 -> 0x180392832 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180392832 original byte 0x48 replaced by RET */
/* target 0x1803948af original byte 0x48 replaced by RET */


void region_00094_180391973(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
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
  longlong lVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
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
  undefined8 in_stack_000000f0;
  undefined8 in_stack_000000f8;
  longlong in_stack_00000148;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_00000208;
  undefined8 in_stack_00000248;
  undefined8 in_stack_00000250;
  undefined8 in_stack_00000838;
  
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000248);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfcc108d1c27cf45);
  (*pcVar1)(in_stack_00000048,uVar6,2,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar6,3,uVar7);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_00000080,uVar6);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
    uVar5 = FUN_18001f030(in_stack_00000048);
    (*pcVar1)(in_stack_00000048,in_stack_00000838,uVar5);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 == '\0') {
      (**(code **)(*in_stack_00000048 + 0x420))
                (in_stack_00000048,in_stack_000001a0,in_stack_00000250);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
        uVar5 = FUN_18001f0d0(in_stack_00000048);
        (*pcVar1)(in_stack_00000048,in_stack_000000a8,in_stack_000000f0,uVar5,in_stack_000000f8);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar4 == '\0') && (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0'))
        {
          if (DAT_1804b2d98 == 0) {
            lVar12 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar12 + 0x390);
            uVar6 = FUN_180333500(in_stack_00000048);
            uVar5 = DAT_1804b59f0;
            uVar7 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000484,1);
            lVar12 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar12 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x000060f0,0x2a);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
            (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar6,uVar5,uVar7);
            DAT_1804b2d98 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
          }
          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2d98,in_stack_00000078);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000088,0)
          ;
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,0,in_stack_00000168);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000170
                            );
          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,2,in_stack_000000a8);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                             0xdfc79c58f7839374);
          (*pcVar1)(in_stack_00000048,uVar6,3,uVar7);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8
                            );
          (*pcVar1)(in_stack_00000048,uVar6,4,uVar7);
          (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_00000080,uVar6)
          ;
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            lVar12 = (**(code **)(*in_stack_00000048 + 0x420))
                               (in_stack_00000048,in_stack_000001a0,in_stack_00000250);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar4 == '\0') {
              if ((0 < in_stack_00000148) && (in_stack_00000148 < lVar12)) {
                lVar12 = (**(code **)(*in_stack_00000048 + 0x420))
                                   (in_stack_00000048,in_stack_000001a0,in_stack_00000250);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar4 != '\0') {
                  return;
                }
                if (0 < lVar12) {
                  return;
                }
              }
              pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
              uVar5 = FUN_18001ec20(in_stack_00000048);
              uVar6 = (*pcVar1)(in_stack_00000048,uVar5);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                uVar7 = FUN_18001ec80(in_stack_00000048);
                (*pcVar1)(in_stack_00000048,uVar6,uVar5,uVar7);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar4 == '\0') {
                  pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
                  uVar5 = FUN_180010830(in_stack_00000048);
                  uVar6 = (*pcVar1)(in_stack_00000048,uVar5);
                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar4 == '\0') {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                    uVar7 = FUN_180010890(in_stack_00000048);
                    (*pcVar1)(in_stack_00000048,uVar6,uVar5,uVar7);
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((cVar4 == '\0') &&
                       (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
                      if (DAT_1804b2da0 == 0) {
                        lVar12 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar12 + 0x390);
                        uVar7 = FUN_1803335a0(in_stack_00000048);
                        uVar5 = DAT_1804b59f0;
                        uVar8 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000482,1);
                        lVar12 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar12 + 0x390);
                        uVar9 = FUN_1800024f0(in_stack_00000048);
                        uVar10 = FUN_180002550(in_stack_00000048);
                        uVar11 = (**(code **)(lVar12 + 0x518))
                                           (in_stack_00000048,&stack0x00003c60,0x16);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar13 = FUN_1800025a0(in_stack_00000048);
                        uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
                        (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
                        uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar5,uVar8);
                        DAT_1804b2da0 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                      }
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b2da0,in_stack_00000078);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                         0x74b3);
                      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_000000c8 ^ 0x34e74066bd31ee87);
                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar5,in_stack_00000080,uVar7);
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar4 == '\0') {
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar7 = FUN_1800108e0(in_stack_00000048);
                        uVar5 = (*pcVar1)(in_stack_00000048,uVar6,uVar7,uVar5);
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((cVar4 == '\0') &&
                           (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
                          if (DAT_1804b2da8 == 0) {
                            lVar12 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar12 + 0x390);
                            uVar8 = FUN_180333500(in_stack_00000048);
                            uVar6 = DAT_1804b59f0;
                            uVar9 = (**(code **)(lVar12 + 0x518))
                                              (in_stack_00000048,&stack0x00000480,1);
                            lVar12 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar12 + 0x390);
                            uVar10 = FUN_1800024f0(in_stack_00000048);
                            uVar11 = FUN_180002550(in_stack_00000048);
                            uVar13 = (**(code **)(lVar12 + 0x518))
                                               (in_stack_00000048,&stack0x00002950,0xd);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar14 = FUN_1800025a0(in_stack_00000048);
                            uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                            (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar13,uVar14);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar6,uVar9)
                            ;
                            DAT_1804b2da8 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b2da8,in_stack_00000078);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000088,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             0xdfc5815bc278527a);
                          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_000000c8);
                          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,in_stack_00000080,uVar8);
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((cVar4 == '\0') &&
                             (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
                            if (DAT_1804b2db0 == 0) {
                              lVar12 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar12 + 0x390);
                              uVar9 = FUN_180333500(in_stack_00000048);
                              uVar8 = DAT_1804b59f0;
                              uVar10 = (**(code **)(lVar12 + 0x518))
                                                 (in_stack_00000048,&stack0x0000047e,1);
                              lVar12 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar12 + 0x390);
                              uVar11 = FUN_1800024f0(in_stack_00000048);
                              uVar13 = FUN_180002550(in_stack_00000048);
                              uVar14 = (**(code **)(lVar12 + 0x518))
                                                 (in_stack_00000048,&stack0x00004fb0,0x29);
                              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar15 = FUN_1800025a0(in_stack_00000048);
                              uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
                              (*pcVar2)(in_stack_00000048,uVar11,uVar13,uVar14,uVar15);
                              uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,
                                                uVar10);
                              DAT_1804b2db0 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                            }
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b2db0,in_stack_00000078);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,4,in_stack_00000088,0);
                            (**(code **)(*in_stack_00000048 + 0x570))
                                      (in_stack_00000048,uVar9,0,uVar6);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,in_stack_00000208);
                            (*pcVar1)(in_stack_00000048,uVar9,1,uVar6);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,0xdfb22bab6c698c7f);
                            (*pcVar1)(in_stack_00000048,uVar9,2,uVar6);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,in_stack_000000c8);
                            (*pcVar1)(in_stack_00000048,uVar9,3,uVar6);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar4 == '\0') {
                              (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar5,uVar7,uVar6);
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

