/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=138 entry=1803bd8d7 blocks=30 maxInstructions=50000 */

/* cut edge: 0x1803bd8d7 -> 0x1803351d6 */
/* cut edge: 0x1803bd9b2 -> 0x1803351d6 */
/* cut edge: 0x1803bda09 -> 0x1803351d6 */
/* cut edge: 0x1803bda44 -> 0x1803351d6 */
/* cut edge: 0x1803bdbe8 -> 0x1803351d6 */
/* cut edge: 0x1803bdcc3 -> 0x1803351d6 */
/* cut edge: 0x1803bdcfe -> 0x1803351d6 */
/* cut edge: 0x1803bdea3 -> 0x1803351d6 */
/* cut edge: 0x1803bdf7e -> 0x1803351d6 */
/* cut edge: 0x1803bdfb9 -> 0x1803351d6 */
/* cut edge: 0x1803bdfef -> 0x1803351d6 */
/* cut edge: 0x1803be032 -> 0x1803351d6 */
/* cut edge: 0x1803be068 -> 0x1803351d6 */
/* cut edge: 0x1803be1d9 -> 0x1803351d6 */
/* cut edge: 0x1803be2bb -> 0x1803351d6 */
/* cut edge: 0x1803be46c -> 0x1803351d6 */
/* cut edge: 0x1803be56d -> 0x1803351d6 */
/* cut edge: 0x1803be5a3 -> 0x1803351d6 */
/* cut edge: 0x1803be5de -> 0x1803351d6 */
/* cut edge: 0x1803be790 -> 0x1803351d6 */
/* cut edge: 0x1803be790 -> 0x1803be8e2 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803be8e2 original byte 0x48 replaced by RET */


void region_00138_1803bd8d7(undefined8 param_1,undefined8 param_2)

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
  undefined8 unaff_R15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_000001c8;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4ad3);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_00000080,uVar7)
  ;
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
    uVar7 = FUN_18001ed70(in_stack_00000048);
    uVar8 = FUN_18001edd0(in_stack_00000048);
    (*pcVar1)(in_stack_00000048,uVar7,uVar8,uVar6);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_000000e8);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804b31d8 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar9 = FUN_1803335a0(in_stack_00000048);
            uVar15 = DAT_1804b59f0;
            uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000368,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar11 = FUN_1800024f0(in_stack_00000048);
            uVar12 = FUN_180002550(in_stack_00000048);
            uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003300,0x16);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar14 = FUN_1800025a0(in_stack_00000048);
            uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
            (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
            uVar15 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar15,uVar10);
            DAT_1804b31d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
            unaff_R15 = in_stack_00000070;
          }
          uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b31d8,in_stack_00000078);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4b59);
          (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000c8 ^ 0x77f9caee8efb510b);
          (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
          uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar15,in_stack_00000080,uVar9);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar6,unaff_R15,uVar15);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                if (DAT_1804b31e0 == 0) {
                  lVar2 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar2 + 0x390);
                  uVar9 = FUN_1803335a0(in_stack_00000048);
                  uVar15 = DAT_1804b59f0;
                  uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000366,1);
                  lVar2 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar2 + 0x390);
                  uVar11 = FUN_1800024f0(in_stack_00000048);
                  uVar12 = FUN_180002550(in_stack_00000048);
                  uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000032d0,0x16);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar14 = FUN_1800025a0(in_stack_00000048);
                  uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                  (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                  uVar15 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar15,uVar10);
                  DAT_1804b31e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
                  unaff_R15 = in_stack_00000070;
                }
                uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b31e0,in_stack_00000078);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000088,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x30dd);
                (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                    in_stack_000000c8 ^ 0x4413de42ddbf2af2);
                (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar15,in_stack_00000080,uVar9);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,uVar7,uVar8,uVar15);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,unaff_R15,uVar7);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00000364,1);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar6,unaff_R15,uVar7);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar5 == '\0') {
                            if (DAT_1804b31e8 == 0) {
                              lVar2 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar2 + 0x390);
                              uVar8 = FUN_180333500(in_stack_00000048);
                              uVar7 = DAT_1804b59f0;
                              uVar15 = (**(code **)(lVar2 + 0x518))
                                                 (in_stack_00000048,&stack0x00000362,1);
                              lVar2 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar2 + 0x390);
                              uVar9 = FUN_1800024f0(in_stack_00000048);
                              uVar10 = FUN_180002550(in_stack_00000048);
                              uVar11 = (**(code **)(lVar2 + 0x518))
                                                 (in_stack_00000048,&stack0x00002650,0xd);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar12 = FUN_1800025a0(in_stack_00000048);
                              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                              uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,
                                                uVar15);
                              DAT_1804b31e8 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                              unaff_R15 = in_stack_00000070;
                            }
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b31e8,in_stack_00000078);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,2,in_stack_00000088,0);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000098,
                                                in_stack_00000090,0xdfc5815bc278527a);
                            (*pcVar1)(in_stack_00000048,uVar8,0,uVar15);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000098,
                                                in_stack_00000090,in_stack_000000c8);
                            (*pcVar1)(in_stack_00000048,uVar8,1,uVar15);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                if (DAT_1804b31f0 == 0) {
                                  lVar2 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar2 + 0x390);
                                  uVar15 = FUN_180333500(in_stack_00000048);
                                  uVar8 = DAT_1804b59f0;
                                  uVar9 = (**(code **)(lVar2 + 0x518))
                                                    (in_stack_00000048,&stack0x00000360,1);
                                  lVar2 = *in_stack_00000048;
                                  pcVar3 = *(code **)(lVar2 + 0x390);
                                  uVar10 = FUN_1800024f0(in_stack_00000048);
                                  uVar11 = FUN_180002550(in_stack_00000048);
                                  uVar12 = (**(code **)(lVar2 + 0x518))
                                                     (in_stack_00000048,&stack0x000073c0,0x28);
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar13 = FUN_1800025a0(in_stack_00000048);
                                  uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                                  (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                  uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar15,uVar8
                                                    ,uVar9);
                                  DAT_1804b31f0 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar8);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                                  unaff_R15 = in_stack_00000070;
                                }
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804b31f0,in_stack_00000078
                                                  );
                                uVar15 = (**(code **)(*in_stack_00000048 + 0x560))
                                                   (in_stack_00000048,3,in_stack_00000088,0);
                                (**(code **)(*in_stack_00000048 + 0x570))
                                          (in_stack_00000048,uVar15,0,uVar7);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_00000090,0xdfe95e8d85cdd102);
                                (*pcVar1)(in_stack_00000048,uVar15,1,uVar7);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_00000090,in_stack_000000c8);
                                (*pcVar1)(in_stack_00000048,uVar15,2,uVar7);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar8,in_stack_00000080,uVar15)
                                ;
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar6,unaff_R15,uVar7);
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar5 == '\0') {
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar7 = FUN_180010980(in_stack_00000048);
                                    uVar6 = (*pcVar1)(in_stack_00000048,uVar6,uVar7);
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar5 == '\0') {
                                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar5 == '\0') {
                                        if (DAT_1804b31f8 == 0) {
                                          lVar2 = *in_stack_00000048;
                                          pcVar1 = *(code **)(lVar2 + 0x390);
                                          uVar8 = FUN_180333500(in_stack_00000048);
                                          uVar7 = DAT_1804b59f0;
                                          uVar15 = (**(code **)(lVar2 + 0x518))
                                                             (in_stack_00000048,&stack0x0000035e,1);
                                          lVar2 = *in_stack_00000048;
                                          pcVar3 = *(code **)(lVar2 + 0x390);
                                          uVar9 = FUN_1800024f0(in_stack_00000048);
                                          uVar10 = FUN_180002550(in_stack_00000048);
                                          uVar11 = (**(code **)(lVar2 + 0x518))
                                                             (in_stack_00000048,&stack0x000059a0,
                                                              0x2a);
                                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar12 = FUN_1800025a0(in_stack_00000048);
                                          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,
                                                             uVar12);
                                          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,
                                                            uVar8,uVar7,uVar15);
                                          DAT_1804b31f8 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar7);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar7);
                                        }
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,DAT_1804b31f8,
                                                           in_stack_00000078);
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                          (in_stack_00000048,5,in_stack_00000088,0);
                                        (**(code **)(*in_stack_00000048 + 0x570))
                                                  (in_stack_00000048,uVar8,0,in_stack_000000a8);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                                                           (in_stack_00000048,in_stack_00000098,
                                                            in_stack_00000090,in_stack_000001c8);
                                        (*pcVar1)(in_stack_00000048,uVar8,1,uVar15);
                                        (**(code **)(*in_stack_00000048 + 0x570))
                                                  (in_stack_00000048,uVar8,2,uVar6);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000098,
                                                           in_stack_00000090,0xdfc0096f727d872c);
                                        (*pcVar1)(in_stack_00000048,uVar8,3,uVar6);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000098,
                                                           in_stack_00000090,in_stack_000000c8);
                                        (*pcVar1)(in_stack_00000048,uVar8,4,uVar6);
                                        (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
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
    }
  }
  return;
}

