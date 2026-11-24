/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=281 entry=1801381db blocks=34 maxInstructions=100000 */

/* cut edge: 0x1801381db -> 0x18014d3db */
/* cut edge: 0x1801381f1 -> 0x18014d3db */
/* cut edge: 0x18013839f -> 0x18014d3db */
/* cut edge: 0x180138494 -> 0x18014d3db */
/* cut edge: 0x1801384aa -> 0x18014d3db */
/* cut edge: 0x180138664 -> 0x18014d3db */
/* cut edge: 0x180138787 -> 0x1801253d7 */
/* cut edge: 0x1801387ad -> 0x18014d3db */
/* cut edge: 0x1801387dc -> 0x18013dca7 */
/* cut edge: 0x180138804 -> 0x18014d3db */
/* cut edge: 0x18013882f -> 0x18014d3db */
/* cut edge: 0x180138845 -> 0x18014d3db */
/* cut edge: 0x1801389e8 -> 0x18014d3db */
/* cut edge: 0x180138adf -> 0x18014d3db */
/* cut edge: 0x180138af5 -> 0x18014d3db */
/* cut edge: 0x180138ca2 -> 0x18014d3db */
/* cut edge: 0x180138d99 -> 0x18014d3db */
/* cut edge: 0x180138daf -> 0x18014d3db */
/* cut edge: 0x180138f65 -> 0x18014d3db */
/* cut edge: 0x18013905a -> 0x18014d3db */
/* cut edge: 0x180139070 -> 0x18014d3db */
/* cut edge: 0x180139086 -> 0x18013922f */
/* cut edge: 0x1801390d8 -> 0x18013922f */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x18013922f original byte 0x48 replaced by RET */
/* target 0x18013dca7 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


longlong * region_00281_1801381db(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  int iVar5;
  longlong *plVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  ulonglong uVar14;
  longlong lVar15;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
  ulonglong in_stack_00000068;
  ulonglong in_stack_00000078;
  longlong in_stack_00000080;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  longlong in_stack_000000f8;
  undefined8 in_stack_00000c28;
  
  plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)plVar6 == '\0') {
    plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar6 == '\0') {
      if (DAT_1804acb10 == 0) {
        lVar15 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar15 + 0x390);
        uVar7 = FUN_18014d730(in_stack_00000048);
        uVar13 = DAT_1804b5208;
        uVar8 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000978,1);
        lVar15 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar15 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00004038,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
        (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        uVar13 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
        DAT_1804acb10 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
      }
      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804acb10,in_stack_000000b0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x626b);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         (longlong)in_stack_00000058 % 0x97d75 ^ in_stack_000000c0 ^
                         0x4b1f98c816ab3bff);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
      uVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar13,in_stack_000000d8)
      ;
      plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)plVar6 == '\0') {
        plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)plVar6 == '\0') {
          plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)plVar6 == '\0') {
            if (DAT_1804acb18 == 0) {
              lVar15 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar15 + 0x390);
              uVar7 = FUN_18014d730(in_stack_00000048);
              uVar13 = DAT_1804b5208;
              uVar8 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000976,1);
              lVar15 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar15 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00004028,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
              (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
              DAT_1804acb18 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804acb18,in_stack_000000b0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x19c9);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               (longlong)in_stack_00000058 % 0x828b3 ^ in_stack_000000c0 ^
                               0x58fe9cadbe54793e);
            (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
            lVar15 = (**(code **)(*in_stack_00000048 + 0x1a0))
                               (in_stack_00000048,uVar13,in_stack_000000d8);
            plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)plVar6 == '\0') {
              lVar15 = (uVar14 ^ in_stack_00000058) - lVar15;
              if (in_stack_000000f8 == 0) {
                return (longlong *)(in_stack_00000080 + (in_stack_00000078 ^ in_stack_00000068));
              }
              iVar5 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000c28)
              ;
              plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)plVar6 == '\0') {
                if (DAT_1804b52f0 == 0) {
                  return in_stack_00000048;
                }
                (**(code **)(*in_stack_00000048 + 0x560))
                          (in_stack_00000048,iVar5 / 2,DAT_1804b52f0,0);
                plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)plVar6 == '\0') {
                  plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)plVar6 == '\0') {
                    plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)plVar6 == '\0') {
                      if (DAT_1804acb20 == 0) {
                        lVar4 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar4 + 0x390);
                        uVar7 = FUN_18014d730(in_stack_00000048);
                        uVar13 = DAT_1804b5208;
                        uVar8 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000974,1);
                        lVar4 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar4 + 0x390);
                        uVar9 = FUN_1800024f0(in_stack_00000048);
                        uVar10 = FUN_180002550(in_stack_00000048);
                        uVar11 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00004018,5);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar12 = FUN_1800025a0(in_stack_00000048);
                        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                        (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                        uVar13 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
                        DAT_1804acb20 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                      }
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,DAT_1804acb20,in_stack_000000b0);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_000000a0,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                         0x72c1);
                      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         lVar15 % 0xd23ef ^ in_stack_000000c0 ^ 0x719fd2e3f9c40b9b);
                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                      (**(code **)(*in_stack_00000048 + 0x1a0))
                                (in_stack_00000048,uVar13,in_stack_000000d8);
                      plVar6 = (longlong *)
                               (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)plVar6 == '\0') {
                        plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)plVar6 == '\0') {
                          plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)plVar6 == '\0') {
                            if (DAT_1804acb28 == 0) {
                              lVar4 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar4 + 0x390);
                              uVar7 = FUN_18014d730(in_stack_00000048);
                              uVar13 = DAT_1804b5208;
                              uVar8 = (**(code **)(lVar4 + 0x518))
                                                (in_stack_00000048,&stack0x00000972,1);
                              lVar4 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar4 + 0x390);
                              uVar9 = FUN_1800024f0(in_stack_00000048);
                              uVar10 = FUN_180002550(in_stack_00000048);
                              uVar11 = (**(code **)(lVar4 + 0x518))
                                                 (in_stack_00000048,&stack0x00004008,5);
                              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar12 = FUN_1800025a0(in_stack_00000048);
                              uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                              (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                              uVar13 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,
                                                 uVar8);
                              DAT_1804acb28 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                             (in_stack_00000048,uVar13);
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                            }
                            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,DAT_1804acb28,in_stack_000000b0);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,2,in_stack_000000a0,0);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0
                                               ,0x4752);
                            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8
                                               ,lVar15 % 0x15461 ^ in_stack_000000c0 ^
                                                0x4804491150ca0095);
                            (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                            (**(code **)(*in_stack_00000048 + 0x1a0))
                                      (in_stack_00000048,uVar13,in_stack_000000d8);
                            plVar6 = (longlong *)
                                     (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)plVar6 == '\0') {
                              plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)plVar6 == '\0') {
                                plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)plVar6 == '\0') {
                                  if (DAT_1804acb30 == 0) {
                                    lVar4 = *in_stack_00000048;
                                    pcVar1 = *(code **)(lVar4 + 0x390);
                                    uVar7 = FUN_18014d730(in_stack_00000048);
                                    uVar13 = DAT_1804b5208;
                                    uVar8 = (**(code **)(lVar4 + 0x518))
                                                      (in_stack_00000048,&stack0x00000970,1);
                                    lVar4 = *in_stack_00000048;
                                    pcVar2 = *(code **)(lVar4 + 0x390);
                                    uVar9 = FUN_1800024f0(in_stack_00000048);
                                    uVar10 = FUN_180002550(in_stack_00000048);
                                    uVar11 = (**(code **)(lVar4 + 0x518))
                                                       (in_stack_00000048,&stack0x00003ff8,5);
                                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar12 = FUN_1800025a0(in_stack_00000048);
                                    uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                                    (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                    uVar13 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,
                                                       uVar13,uVar8);
                                    DAT_1804acb30 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar13);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar13);
                                  }
                                  uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,DAT_1804acb30,
                                                      in_stack_000000b0);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_000000a0,0);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_000000c8,
                                                     in_stack_000000d0,0xd6);
                                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000098,
                                                     in_stack_000000b8,
                                                     lVar15 % 0xe135b ^ in_stack_000000c0 ^
                                                     0x3fae20748447831e);
                                  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                  uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,uVar13,in_stack_000000a8,
                                                      uVar7);
                                  (**(code **)(*in_stack_00000048 + 0x1a0))
                                            (in_stack_00000048,uVar13,in_stack_000000d8);
                                  plVar6 = (longlong *)
                                           (**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                                  if ((char)plVar6 == '\0') {
                                    plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))()
                                    ;
                                    if ((char)plVar6 == '\0') {
                                      plVar6 = (longlong *)
                                               (**(code **)(*in_stack_00000048 + 0x720))();
                                      if ((char)plVar6 == '\0') {
                                        plVar6 = (longlong *)0x1c480c2c6d55ed35;
                                        if (DAT_1804acb38 == 0) {
                                          lVar15 = *in_stack_00000048;
                                          pcVar1 = *(code **)(lVar15 + 0x390);
                                          uVar7 = FUN_18014d730(in_stack_00000048);
                                          uVar13 = DAT_1804b5208;
                                          uVar8 = (**(code **)(lVar15 + 0x518))
                                                            (in_stack_00000048,&stack0x0000096e,1);
                                          lVar15 = *in_stack_00000048;
                                          pcVar2 = *(code **)(lVar15 + 0x390);
                                          uVar9 = FUN_1800024f0(in_stack_00000048);
                                          uVar10 = FUN_180002550(in_stack_00000048);
                                          uVar11 = (**(code **)(lVar15 + 0x518))
                                                             (in_stack_00000048,&stack0x00003fe8,5);
                                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar12 = FUN_1800025a0(in_stack_00000048);
                                          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,
                                                             uVar12);
                                          (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                          uVar13 = (*pcVar1)(in_stack_00000048,in_stack_00000090,
                                                             uVar7,uVar13,uVar8);
                                          DAT_1804acb38 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar13);
                                          plVar6 = (longlong *)
                                                   (**(code **)(*in_stack_00000048 + 0xb8))
                                                             (in_stack_00000048,uVar13);
                                        }
                                        return plVar6;
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
  return plVar6;
}

