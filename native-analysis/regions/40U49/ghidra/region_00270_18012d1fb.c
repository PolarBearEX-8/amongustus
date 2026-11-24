/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=270 entry=18012d1fb blocks=33 maxInstructions=100000 */

/* cut edge: 0x18012d1fb -> 0x18012f2f7 */
/* cut edge: 0x18012d224 -> 0x18012ffa6 */
/* cut edge: 0x18012d233 -> 0x18014d3db */
/* cut edge: 0x18012d249 -> 0x18014d3db */
/* cut edge: 0x18012d3ee -> 0x18014d3db */
/* cut edge: 0x18012d503 -> 0x18014d3db */
/* cut edge: 0x18012d53c -> 0x18014d3db */
/* cut edge: 0x18012d589 -> 0x18014d3db */
/* cut edge: 0x18012d5c7 -> 0x18014d3db */
/* cut edge: 0x18012d5dd -> 0x18014d3db */
/* cut edge: 0x18012d78a -> 0x18014d3db */
/* cut edge: 0x18012d881 -> 0x18014d3db */
/* cut edge: 0x18012d897 -> 0x18014d3db */
/* cut edge: 0x18012da4d -> 0x18014d3db */
/* cut edge: 0x18012db47 -> 0x18014d3db */
/* cut edge: 0x18012db5d -> 0x18014d3db */
/* cut edge: 0x18012dd0b -> 0x18014d3db */
/* cut edge: 0x18012de00 -> 0x18014d3db */
/* cut edge: 0x18012de16 -> 0x18014d3db */
/* cut edge: 0x18012dfd0 -> 0x18014d3db */
/* cut edge: 0x18012e0ce -> 0x1801253d7 */
/* cut edge: 0x18012e0fe -> 0x18014d3db */
/* cut edge: 0x18012e114 -> 0x18014d3db */
/* cut edge: 0x18012e114 -> 0x18012e12a */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x18012e12a original byte 0x48 replaced by RET */
/* target 0x18012f2f7 original byte 0x48 replaced by RET */
/* target 0x18012ffa6 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00270_18012d1fb(void)

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
  ulonglong unaff_R13;
  ulonglong unaff_R15;
  longlong *in_stack_00000048;
  longlong in_stack_00000060;
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
  undefined8 in_stack_00000138;
  undefined8 in_stack_00000200;
  undefined2 uStack0000000000000208;
  undefined2 uStack000000000000020a;
  undefined2 uStack000000000000020c;
  undefined2 uStack000000000000020e;
  undefined8 in_stack_000055c0;
  undefined8 in_stack_000055c8;
  
  in_stack_00000060 = in_stack_00000060 + (unaff_R13 ^ unaff_R15);
  if ((longlong)in_stack_000000c0 < 1) {
    return;
  }
  if (in_stack_000000f8 != 0) {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804ae870 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar6 = FUN_18001e7d0(in_stack_00000048);
          uVar12 = DAT_1804b5208;
          uStack000000000000020e = 0x4c;
          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,(longlong)&stack0x00000208 + 6,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00005750,6);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
          (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
          DAT_1804ae870 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ae870,in_stack_000000b0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000055c8);
        (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           in_stack_00000060 % 0xf2900 ^ 0xe9e0e63d7ceafc3c);
        (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
        (*pcVar2)(in_stack_00000048,uVar6,2,uVar7);
        (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          pcVar2 = *(code **)(*in_stack_00000048 + 0xd8);
          uVar12 = FUN_18014e340(in_stack_00000048);
          uVar6 = (*pcVar2)(in_stack_00000048,uVar12);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            pcVar2 = *(code **)(*in_stack_00000048 + 0x2d8);
            uVar7 = FUN_18014e3a0(in_stack_00000048);
            (*pcVar2)(in_stack_00000048,uVar6,uVar12,uVar7,in_stack_000055c0);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              pcVar2 = *(code **)(*in_stack_00000048 + 0x340);
              uVar12 = FUN_18014e3f0(in_stack_00000048);
              (*pcVar2)(in_stack_00000048,in_stack_00000138,uVar12,uVar6);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804ae878 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar6 = FUN_18014d730(in_stack_00000048);
                      uVar12 = DAT_1804b5208;
                      uStack000000000000020c = 0x6a;
                      uVar7 = (**(code **)(lVar1 + 0x518))
                                        (in_stack_00000048,(longlong)&stack0x00000208 + 4,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar8 = FUN_1800024f0(in_stack_00000048);
                      uVar9 = FUN_180002550(in_stack_00000048);
                      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000e98,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar11 = FUN_1800025a0(in_stack_00000048);
                      uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
                      (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                      uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
                      DAT_1804ae878 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                    }
                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804ae878,in_stack_000000b0);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0xf4f);
                    (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       in_stack_00000060 % 0x18db4 ^ in_stack_000000c0 ^
                                       0xdf9ee7b0412f9ff8);
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
                          if (DAT_1804ae880 == 0) {
                            lVar1 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar1 + 0x390);
                            uVar6 = FUN_18014d730(in_stack_00000048);
                            uVar12 = DAT_1804b5208;
                            uStack000000000000020a = 0x6a;
                            uVar7 = (**(code **)(lVar1 + 0x518))
                                              (in_stack_00000048,(longlong)&stack0x00000208 + 2,1);
                            lVar1 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar1 + 0x390);
                            uVar8 = FUN_1800024f0(in_stack_00000048);
                            uVar9 = FUN_180002550(in_stack_00000048);
                            uVar10 = (**(code **)(lVar1 + 0x518))
                                               (in_stack_00000048,&stack0x00000e88,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar11 = FUN_1800025a0(in_stack_00000048);
                            uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
                            (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                            uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,
                                               uVar7);
                            DAT_1804ae880 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                          }
                          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804ae880,in_stack_000000b0);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x658);
                          (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00000060 % 0x3e311 ^ in_stack_000000c0 ^
                                             0x8b14534e04aac045);
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
                                if (DAT_1804ae888 == 0) {
                                  lVar1 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar1 + 0x390);
                                  uVar6 = FUN_18014d730(in_stack_00000048);
                                  uVar12 = DAT_1804b5208;
                                  uStack0000000000000208 = 0x6a;
                                  uVar7 = (**(code **)(lVar1 + 0x518))
                                                    (in_stack_00000048,&stack0x00000208,1);
                                  lVar1 = *in_stack_00000048;
                                  pcVar3 = *(code **)(lVar1 + 0x390);
                                  uVar8 = FUN_1800024f0(in_stack_00000048);
                                  uVar9 = FUN_180002550(in_stack_00000048);
                                  uVar10 = (**(code **)(lVar1 + 0x518))
                                                     (in_stack_00000048,&stack0x00000e78,5);
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar11 = FUN_1800025a0(in_stack_00000048);
                                  uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
                                  (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                  uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,
                                                     uVar12,uVar7);
                                  DAT_1804ae888 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar12);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12)
                                  ;
                                }
                                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,DAT_1804ae888,
                                                    in_stack_000000b0);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x44d9);
                                (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   in_stack_00000060 % 0xbf092 ^ in_stack_000000c0 ^
                                                   0xa9abbd57d748b23f);
                                (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,uVar12,in_stack_000000a8,uVar6
                                                   );
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar12,in_stack_000000d8);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if (cVar5 == '\0') {
                                      if (DAT_1804ae890 == 0) {
                                        lVar1 = *in_stack_00000048;
                                        pcVar2 = *(code **)(lVar1 + 0x390);
                                        uVar6 = FUN_18014d730(in_stack_00000048);
                                        uVar12 = DAT_1804b5208;
                                        in_stack_00000200._6_2_ = 0x6a;
                                        uVar7 = (**(code **)(lVar1 + 0x518))
                                                          (in_stack_00000048,
                                                           (longlong)&stack0x00000200 + 6,1);
                                        lVar1 = *in_stack_00000048;
                                        pcVar3 = *(code **)(lVar1 + 0x390);
                                        uVar8 = FUN_1800024f0(in_stack_00000048);
                                        uVar9 = FUN_180002550(in_stack_00000048);
                                        uVar10 = (**(code **)(lVar1 + 0x518))
                                                           (in_stack_00000048,&stack0x00000e68,5);
                                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar11 = FUN_1800025a0(in_stack_00000048);
                                        uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,
                                                           uVar11);
                                        (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                        uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6
                                                           ,uVar12,uVar7);
                                        DAT_1804ae890 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar12);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar12);
                                      }
                                      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                         (in_stack_00000048,DAT_1804ae890,
                                                          in_stack_000000b0);
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,2,in_stack_000000a0,0);
                                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_000000c8,
                                                         in_stack_000000d0,0x1d8f);
                                      (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_000000b8,
                                                         in_stack_00000060 % 0x9c3c5 ^
                                                         in_stack_000000c0 ^ 0x84561fa2816d11fe);
                                      (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                                      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                         (in_stack_00000048,uVar12,in_stack_000000a8
                                                          ,uVar6);
                                      (**(code **)(*in_stack_00000048 + 0x1a0))
                                                (in_stack_00000048,uVar12,in_stack_000000d8);
                                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                                      if (cVar5 == '\0') {
                                        if (in_stack_000000f8 == 0) {
                                          return;
                                        }
                                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                        if (cVar5 == '\0') {
                                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
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
  return;
}

