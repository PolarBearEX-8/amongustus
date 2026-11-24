/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=283 entry=18013a200 blocks=34 maxInstructions=100000 */

/* cut edge: 0x18013a200 -> 0x18014d3db */
/* cut edge: 0x18013a2de -> 0x18013e949 */
/* cut edge: 0x18013a305 -> 0x18014d3db */
/* cut edge: 0x18013a336 -> 0x18014d3db */
/* cut edge: 0x18013a34c -> 0x18014d3db */
/* cut edge: 0x18013a53c -> 0x18014d3db */
/* cut edge: 0x18013a66a -> 0x18014d3db */
/* cut edge: 0x18013a6a2 -> 0x18014d3db */
/* cut edge: 0x18013a6b8 -> 0x18014d3db */
/* cut edge: 0x18013a892 -> 0x18014d3db */
/* cut edge: 0x18013a970 -> 0x18014d3db */
/* cut edge: 0x18013a9a6 -> 0x18014d3db */
/* cut edge: 0x18013a9bc -> 0x18014d3db */
/* cut edge: 0x18013ab96 -> 0x18014d3db */
/* cut edge: 0x18013ac7a -> 0x18013e984 */
/* cut edge: 0x18013ac99 -> 0x18013e9d9 */
/* cut edge: 0x18013aca9 -> 0x18014d3db */
/* cut edge: 0x18013acdd -> 0x18014d3db */
/* cut edge: 0x18013ad13 -> 0x18014d3db */
/* cut edge: 0x18013ad29 -> 0x18014d3db */
/* cut edge: 0x18013af10 -> 0x18014d3db */
/* cut edge: 0x18013aff4 -> 0x18014d3db */
/* cut edge: 0x18013b02a -> 0x18014d3db */
/* cut edge: 0x18013b040 -> 0x18014d3db */
/* cut edge: 0x18013b056 -> 0x18013b21f */
/* cut edge: 0x18013b0ab -> 0x18013b21f */
/* target 0x18013b21f original byte 0x48 replaced by RET */
/* target 0x18013e949 original byte 0x48 replaced by RET */
/* target 0x18013e984 original byte 0x48 replaced by RET */
/* target 0x18013e9d9 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


longlong * region_00283_18013a200(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong *plVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  longlong *in_stack_00000048;
  longlong in_stack_00000078;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_00000138;
  undefined8 in_stack_000001d0;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x20f1);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
  plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  lVar5 = DAT_1804b5030;
  if ((char)plVar9 == '\0') {
    if (DAT_1804b5030 == 0) {
      return (longlong *)0x0;
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048);
    plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar9 == '\0') {
      plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)plVar9 == '\0') {
        plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)plVar9 == '\0') {
          if (DAT_1804acb68 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar8 = FUN_18001e7d0(in_stack_00000048);
            uVar7 = DAT_1804b5208;
            uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000962,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar11 = FUN_1800024f0(in_stack_00000048);
            uVar12 = FUN_180002550(in_stack_00000048);
            uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00007350,0x29);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar14 = FUN_1800025a0(in_stack_00000048);
            uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
            (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar10);
            DAT_1804acb68 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804acb68,in_stack_000000b0);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_000000a0,0)
          ;
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000138);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                              in_stack_000001d0);
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                              in_stack_00000078 % 0x67ea1 ^ 0xe9d78abf5e424c49);
          (*pcVar1)(in_stack_00000048,uVar8,2,uVar10);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                              in_stack_000000c0);
          (*pcVar1)(in_stack_00000048,uVar8,3,uVar10);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
          plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)plVar9 == '\0') {
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,lVar5,uVar7);
            plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)plVar9 == '\0') {
              plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)plVar9 == '\0') {
                plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)plVar9 == '\0') {
                  if (DAT_1804acb70 == 0) {
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar8 = FUN_18001ed20(in_stack_00000048);
                    uVar7 = DAT_1804b5208;
                    uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000960,1);
                    lVar2 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar11 = FUN_1800024f0(in_stack_00000048);
                    uVar12 = FUN_180002550(in_stack_00000048);
                    uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00006b80,0x16);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar14 = FUN_1800025a0(in_stack_00000048);
                    uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
                    (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                    uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar10);
                    DAT_1804acb70 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                  }
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804acb70,in_stack_000000b0);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5a14);
                  (*pcVar1)(in_stack_00000048,uVar8,0,uVar10);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                      in_stack_00000078 % 0x1a367 ^ in_stack_000000c0 ^
                                      0xd9d75de4373d6fb6);
                  (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                  plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)plVar9 == '\0') {
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,lVar5,uVar7);
                    plVar9 = (longlong *)
                             (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)plVar9 == '\0') {
                      plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)plVar9 == '\0') {
                        plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)plVar9 == '\0') {
                          if (DAT_1804acb78 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar8 = FUN_18001ed20(in_stack_00000048);
                            uVar7 = DAT_1804b5208;
                            uVar10 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x0000095e,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar11 = FUN_1800024f0(in_stack_00000048);
                            uVar12 = FUN_180002550(in_stack_00000048);
                            uVar13 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00006b50,0x16);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar14 = FUN_1800025a0(in_stack_00000048);
                            uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
                            (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar10
                                             );
                            DAT_1804acb78 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804acb78,in_stack_000000b0);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                              0x6d7b);
                          (*pcVar1)(in_stack_00000048,uVar8,0,uVar10);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                              in_stack_00000078 % 0xb8e5f ^ in_stack_000000c0 ^
                                              0x91bbcfa039acdac7);
                          (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                          plVar9 = (longlong *)
                                   (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)plVar9 == '\0') {
                            if (DAT_1804b5290 == 0) {
                              return in_stack_00000048;
                            }
                            if (DAT_1804b5298 == 0) {
                              return (longlong *)0x0;
                            }
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,DAT_1804b5290,DAT_1804b5298,uVar7);
                            plVar9 = (longlong *)
                                     (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)plVar9 == '\0') {
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar6,lVar5,uVar7);
                              plVar9 = (longlong *)
                                       (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if ((char)plVar9 == '\0') {
                                plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)plVar9 == '\0') {
                                  plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)plVar9 == '\0') {
                                    if (DAT_1804acb80 == 0) {
                                      lVar2 = *in_stack_00000048;
                                      pcVar1 = *(code **)(lVar2 + 0x390);
                                      uVar8 = FUN_18001ed20(in_stack_00000048);
                                      uVar7 = DAT_1804b5208;
                                      uVar10 = (**(code **)(lVar2 + 0x518))
                                                         (in_stack_00000048,&stack0x0000095c,1);
                                      lVar2 = *in_stack_00000048;
                                      pcVar3 = *(code **)(lVar2 + 0x390);
                                      uVar11 = FUN_1800024f0(in_stack_00000048);
                                      uVar12 = FUN_180002550(in_stack_00000048);
                                      uVar13 = (**(code **)(lVar2 + 0x518))
                                                         (in_stack_00000048,&stack0x00006b20,0x16);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar14 = FUN_1800025a0(in_stack_00000048);
                                      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14)
                                      ;
                                      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,
                                                        uVar7,uVar10);
                                      DAT_1804acb80 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar7);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar7);
                                    }
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,DAT_1804acb80,
                                                       in_stack_000000b0);
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,2,in_stack_000000a0,0);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                       (in_stack_00000048,in_stack_000000c8,
                                                        in_stack_000000d0,0x3af8);
                                    (*pcVar1)(in_stack_00000048,uVar8,0,uVar10);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                       (in_stack_00000048,in_stack_00000098,
                                                        in_stack_000000b8,
                                                        in_stack_00000078 % 0xe894 ^
                                                        in_stack_000000c0 ^ 0xbf12e7425eb7c3d5);
                                    (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,uVar7,in_stack_000000a8,
                                                       uVar8);
                                    plVar9 = (longlong *)
                                             (**(code **)(*in_stack_00000048 + 0x720))
                                                       (in_stack_00000048);
                                    if ((char)plVar9 == '\0') {
                                      (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar6,lVar5,uVar7);
                                      plVar9 = (longlong *)
                                               (**(code **)(*in_stack_00000048 + 0x720))
                                                         (in_stack_00000048);
                                      if ((char)plVar9 == '\0') {
                                        plVar9 = (longlong *)
                                                 (**(code **)(*in_stack_00000048 + 0x720))();
                                        if ((char)plVar9 == '\0') {
                                          plVar9 = (longlong *)
                                                   (**(code **)(*in_stack_00000048 + 0x720))();
                                          if ((char)plVar9 == '\0') {
                                            plVar9 = (longlong *)0xf723560f84174980;
                                            if (DAT_1804acb88 == 0) {
                                              lVar5 = *in_stack_00000048;
                                              pcVar1 = *(code **)(lVar5 + 0x390);
                                              uVar7 = FUN_18001ed20(in_stack_00000048);
                                              uVar6 = DAT_1804b5208;
                                              uVar8 = (**(code **)(lVar5 + 0x518))
                                                                (in_stack_00000048,&stack0x0000095a,
                                                                 1);
                                              lVar5 = *in_stack_00000048;
                                              pcVar3 = *(code **)(lVar5 + 0x390);
                                              uVar10 = FUN_1800024f0(in_stack_00000048);
                                              uVar11 = FUN_180002550(in_stack_00000048);
                                              uVar12 = (**(code **)(lVar5 + 0x518))
                                                                 (in_stack_00000048,&stack0x00006af0
                                                                  ,0x16);
                                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                              uVar13 = FUN_1800025a0(in_stack_00000048);
                                              uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090
                                                                 ,uVar13);
                                              (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,
                                                        uVar13);
                                              uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,
                                                                uVar7,uVar6,uVar8);
                                              DAT_1804acb88 =
                                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                                             (in_stack_00000048,uVar6);
                                              plVar9 = (longlong *)
                                                       (**(code **)(*in_stack_00000048 + 0xb8))
                                                                 (in_stack_00000048,uVar6);
                                            }
                                            return plVar9;
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
  return plVar9;
}

