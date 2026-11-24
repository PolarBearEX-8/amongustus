/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=277 entry=180134242 blocks=33 maxInstructions=100000 */

/* cut edge: 0x180134242 -> 0x18014d3db */
/* cut edge: 0x180134326 -> 0x18013e805 */
/* cut edge: 0x180134345 -> 0x18014d3db */
/* cut edge: 0x180134376 -> 0x18014d3db */
/* cut edge: 0x18013438c -> 0x18014d3db */
/* cut edge: 0x180134534 -> 0x18014d3db */
/* cut edge: 0x18013462b -> 0x18014d3db */
/* cut edge: 0x180134641 -> 0x18014d3db */
/* cut edge: 0x1801347ec -> 0x18014d3db */
/* cut edge: 0x1801348e3 -> 0x18014d3db */
/* cut edge: 0x1801348f9 -> 0x18014d3db */
/* cut edge: 0x180134aa7 -> 0x18014d3db */
/* cut edge: 0x180134b9c -> 0x18014d3db */
/* cut edge: 0x180134bb2 -> 0x18014d3db */
/* cut edge: 0x180134d6c -> 0x18014d3db */
/* cut edge: 0x180134e6a -> 0x1801253d7 */
/* cut edge: 0x180134e95 -> 0x18014d3db */
/* cut edge: 0x180134ed2 -> 0x18014d3db */
/* cut edge: 0x180134f1a -> 0x18014d3db */
/* cut edge: 0x180134f60 -> 0x18014d3db */
/* cut edge: 0x180134f76 -> 0x18014d3db */
/* cut edge: 0x180134f8c -> 0x18014d3db */
/* cut edge: 0x180134fb7 -> 0x18014d3db */
/* cut edge: 0x180134fcd -> 0x18013517f */
/* cut edge: 0x180135022 -> 0x18013517f */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x18013517f original byte 0x48 replaced by RET */
/* target 0x18013e805 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


longlong * region_00277_180134242(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  longlong *plVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  ulonglong uVar12;
  ulonglong uVar13;
  longlong lVar14;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000078;
  ulonglong in_stack_00000080;
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
  
  uVar4 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x59f);
  (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar4,in_stack_000000a8,uVar5)
  ;
  plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)plVar7 == '\0') {
    if (DAT_1804b5340 == 0) {
      return in_stack_00000048;
    }
    (**(code **)(*in_stack_00000048 + 0x110))
              (in_stack_00000048,in_stack_00000090,DAT_1804b5340,uVar4);
    plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar7 == '\0') {
      plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)plVar7 == '\0') {
        plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)plVar7 == '\0') {
          if (DAT_1804aca70 == 0) {
            lVar14 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar14 + 0x390);
            uVar5 = FUN_18014d730(in_stack_00000048);
            uVar4 = DAT_1804b5208;
            uVar6 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009a4,1);
            lVar14 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar14 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004138,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
            (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar4,uVar6);
            DAT_1804aca70 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
          }
          uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804aca70,in_stack_000000b0);
          uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x24a0);
          (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                             (longlong)in_stack_00000078 % 0x42f97 ^ in_stack_000000c0 ^
                             0xd8df7ad99f0db8f9);
          (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
          uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                             (in_stack_00000048,uVar4,in_stack_000000d8);
          plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)plVar7 == '\0') {
            plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)plVar7 == '\0') {
              plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)plVar7 == '\0') {
                if (DAT_1804aca78 == 0) {
                  lVar14 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar14 + 0x390);
                  uVar5 = FUN_18014d730(in_stack_00000048);
                  uVar4 = DAT_1804b5208;
                  uVar6 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009a2,1);
                  lVar14 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar14 + 0x390);
                  uVar8 = FUN_1800024f0(in_stack_00000048);
                  uVar9 = FUN_180002550(in_stack_00000048);
                  uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004128,5);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar11 = FUN_1800025a0(in_stack_00000048);
                  uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                  (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                  uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar4,uVar6);
                  DAT_1804aca78 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
                }
                uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804aca78,in_stack_000000b0);
                uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_000000a0,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6ab);
                (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                   (longlong)in_stack_00000078 % 0x25ceb ^ in_stack_000000c0 ^
                                   0x90ee17dfdb84bc17);
                (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
                uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
                (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar4,in_stack_000000d8)
                ;
                plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)plVar7 == '\0') {
                  plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)plVar7 == '\0') {
                    plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)plVar7 == '\0') {
                      if (DAT_1804aca80 == 0) {
                        lVar14 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar14 + 0x390);
                        uVar5 = FUN_18014d730(in_stack_00000048);
                        uVar4 = DAT_1804b5208;
                        uVar6 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009a0,1);
                        lVar14 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar14 + 0x390);
                        uVar8 = FUN_1800024f0(in_stack_00000048);
                        uVar9 = FUN_180002550(in_stack_00000048);
                        uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004118,5)
                        ;
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar11 = FUN_1800025a0(in_stack_00000048);
                        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                        uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar4,uVar6);
                        DAT_1804aca80 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
                      }
                      uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804aca80,in_stack_000000b0);
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_000000a0,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                         0x60ba);
                      (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         (longlong)in_stack_00000078 % 0x83187 ^ in_stack_000000c0 ^
                                         0x99c8e061fc96f8db);
                      (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
                      uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                         (in_stack_00000048,uVar4,in_stack_000000d8);
                      plVar7 = (longlong *)
                               (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)plVar7 == '\0') {
                        plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)plVar7 == '\0') {
                          plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)plVar7 == '\0') {
                            if (DAT_1804aca88 == 0) {
                              lVar14 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar14 + 0x390);
                              uVar5 = FUN_18014d730(in_stack_00000048);
                              uVar4 = DAT_1804b5208;
                              uVar6 = (**(code **)(lVar14 + 0x518))
                                                (in_stack_00000048,&stack0x0000099e,1);
                              lVar14 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar14 + 0x390);
                              uVar8 = FUN_1800024f0(in_stack_00000048);
                              uVar9 = FUN_180002550(in_stack_00000048);
                              uVar10 = (**(code **)(lVar14 + 0x518))
                                                 (in_stack_00000048,&stack0x00004108,5);
                              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar11 = FUN_1800025a0(in_stack_00000048);
                              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                              uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar4,
                                                uVar6);
                              DAT_1804aca88 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
                            }
                            uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804aca88,in_stack_000000b0);
                            uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,2,in_stack_000000a0,0);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0
                                               ,0x6553);
                            (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8
                                               ,(longlong)in_stack_00000078 % 0x497c0 ^
                                                in_stack_000000c0 ^ 0xf801c92f5f46b818);
                            (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
                            uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
                            lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                               (in_stack_00000048,uVar4,in_stack_000000d8);
                            plVar7 = (longlong *)
                                     (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)plVar7 == '\0') {
                              if (in_stack_000000f8 == 0) {
                                return (longlong *)(uVar12 ^ in_stack_00000080);
                              }
                              pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
                              uVar4 = FUN_18014d9a0(in_stack_00000048);
                              (*pcVar1)(in_stack_00000048,uVar4);
                              plVar7 = (longlong *)
                                       (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if ((char)plVar7 == '\0') {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
                                uVar4 = FUN_180010830(in_stack_00000048);
                                uVar5 = (*pcVar1)(in_stack_00000048,uVar4);
                                plVar7 = (longlong *)
                                         (**(code **)(*in_stack_00000048 + 0x720))
                                                   (in_stack_00000048);
                                if ((char)plVar7 == '\0') {
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                                  uVar6 = FUN_180010890(in_stack_00000048);
                                  (*pcVar1)(in_stack_00000048,uVar5,uVar4,uVar6);
                                  plVar7 = (longlong *)
                                           (**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                                  if ((char)plVar7 == '\0') {
                                    plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))()
                                    ;
                                    if ((char)plVar7 == '\0') {
                                      plVar7 = (longlong *)
                                               (**(code **)(*in_stack_00000048 + 0x720))();
                                      if ((char)plVar7 == '\0') {
                                        plVar7 = (longlong *)
                                                 (**(code **)(*in_stack_00000048 + 0x720))
                                                           (in_stack_00000048,
                                                            SUB168(SEXT816((longlong)
                                                                           (lVar14 + (uVar13 ^ 
                                                  in_stack_00000078))) * SEXT816(0x504bc33c3a77faed)
                                                  ,8));
                                        if ((char)plVar7 == '\0') {
                                          plVar7 = (longlong *)
                                                   (**(code **)(*in_stack_00000048 + 0x720))();
                                          if ((char)plVar7 == '\0') {
                                            plVar7 = (longlong *)0xe8b1a6df268d0908;
                                            if (DAT_1804aca90 == 0) {
                                              lVar14 = *in_stack_00000048;
                                              pcVar1 = *(code **)(lVar14 + 0x390);
                                              uVar5 = FUN_18014d730(in_stack_00000048);
                                              uVar4 = DAT_1804b5208;
                                              uVar6 = (**(code **)(lVar14 + 0x518))
                                                                (in_stack_00000048,&stack0x0000099c,
                                                                 1);
                                              lVar14 = *in_stack_00000048;
                                              pcVar2 = *(code **)(lVar14 + 0x390);
                                              uVar8 = FUN_1800024f0(in_stack_00000048);
                                              uVar9 = FUN_180002550(in_stack_00000048);
                                              uVar10 = (**(code **)(lVar14 + 0x518))
                                                                 (in_stack_00000048,&stack0x000040f8
                                                                  ,5);
                                              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                              uVar11 = FUN_1800025a0(in_stack_00000048);
                                              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090
                                                                 ,uVar11);
                                              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11)
                                              ;
                                              uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000090,
                                                                uVar5,uVar4,uVar6);
                                              DAT_1804aca90 =
                                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                                             (in_stack_00000048,uVar4);
                                              plVar7 = (longlong *)
                                                       (**(code **)(*in_stack_00000048 + 0xb8))
                                                                 (in_stack_00000048,uVar4);
                                            }
                                            return plVar7;
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
  }
  return plVar7;
}

