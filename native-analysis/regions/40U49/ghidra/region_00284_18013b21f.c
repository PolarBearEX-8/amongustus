/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=284 entry=18013b21f blocks=34 maxInstructions=100000 */

/* cut edge: 0x18013b21f -> 0x18014d3db */
/* cut edge: 0x18013b2fd -> 0x18014d3db */
/* cut edge: 0x18013b332 -> 0x18014d3db */
/* cut edge: 0x18013b368 -> 0x18014d3db */
/* cut edge: 0x18013b3ab -> 0x18014d3db */
/* cut edge: 0x18013b3e1 -> 0x18013ea10 */
/* cut edge: 0x18013b400 -> 0x18014d3db */
/* cut edge: 0x18013b429 -> 0x18014d3db */
/* cut edge: 0x18013b45f -> 0x18013ea46 */
/* cut edge: 0x18013b47e -> 0x18014d3db */
/* cut edge: 0x18013b4a2 -> 0x18014d3db */
/* cut edge: 0x18013b4c5 -> 0x18014d3db */
/* cut edge: 0x18013b6bb -> 0x18014d3db */
/* cut edge: 0x18013b7fb -> 0x18014d3db */
/* cut edge: 0x18013b811 -> 0x18014d3db */
/* cut edge: 0x18013b9bf -> 0x18014d3db */
/* cut edge: 0x18013bab6 -> 0x18014d3db */
/* cut edge: 0x18013bacc -> 0x18014d3db */
/* cut edge: 0x18013bc83 -> 0x18014d3db */
/* cut edge: 0x18013bd7d -> 0x18014d3db */
/* cut edge: 0x18013bd93 -> 0x18014d3db */
/* cut edge: 0x18013bf49 -> 0x18014d3db */
/* cut edge: 0x18013c03e -> 0x18014d3db */
/* cut edge: 0x18013c054 -> 0x18014d3db */
/* cut edge: 0x18013c06a -> 0x18013c213 */
/* cut edge: 0x18013c0bc -> 0x18013c213 */
/* target 0x18013c213 original byte 0x48 replaced by RET */
/* target 0x18013ea10 original byte 0x48 replaced by RET */
/* target 0x18013ea46 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


longlong * region_00284_18013b21f(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  longlong *plVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  longlong in_stack_00000078;
  undefined8 in_stack_00000080;
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
  undefined8 in_stack_00000138;
  undefined8 in_stack_00000198;
  
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5eff);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_000000a8,uVar6)
  ;
  plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)plVar8 == '\0') {
    (**(code **)(*in_stack_00000048 + 0x390))
              (in_stack_00000048,in_stack_00000068,in_stack_00000070,uVar5);
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar8 == '\0') {
      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048);
      plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)plVar8 == '\0') {
        uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000958,1);
        plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)plVar8 == '\0') {
          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar5,in_stack_00000080,uVar6);
          plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)plVar8 == '\0') {
            if (DAT_1804b52a0 == 0) {
              return in_stack_00000048;
            }
            uVar6 = (**(code **)(*in_stack_00000048 + 0x2f8))(in_stack_00000048,in_stack_00000138);
            plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)plVar8 == '\0') {
              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar5,in_stack_00000080,uVar6);
              plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)plVar8 == '\0') {
                if (DAT_1804b5040 == 0) {
                  return in_stack_00000048;
                }
                uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5);
                plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)plVar8 == '\0') {
                  plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)plVar8 == '\0') {
                    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)plVar8 == '\0') {
                      if (DAT_1804acb90 == 0) {
                        lVar2 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar7 = FUN_18001e7d0(in_stack_00000048);
                        uVar6 = DAT_1804b5208;
                        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000956,1);
                        lVar2 = *in_stack_00000048;
                        pcVar3 = *(code **)(lVar2 + 0x390);
                        uVar10 = FUN_1800024f0(in_stack_00000048);
                        uVar11 = FUN_180002550(in_stack_00000048);
                        uVar12 = (**(code **)(lVar2 + 0x518))
                                           (in_stack_00000048,&stack0x00007bc0,0x2a);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar13 = FUN_1800025a0(in_stack_00000048);
                        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar9);
                        DAT_1804acb90 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804acb90,in_stack_000000b0);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,5,in_stack_000000a0,0);
                      (**(code **)(*in_stack_00000048 + 0x570))
                                (in_stack_00000048,uVar7,0,in_stack_00000058);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         in_stack_00000198);
                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar9);
                      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,2,uVar5);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         in_stack_00000078 % 0x70197 ^ 0xe9e40b3e6916c603);
                      (*pcVar1)(in_stack_00000048,uVar7,3,uVar5);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         in_stack_000000c0);
                      (*pcVar1)(in_stack_00000048,uVar7,4,uVar5);
                      (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                      plVar8 = (longlong *)
                               (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)plVar8 == '\0') {
                        plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)plVar8 == '\0') {
                          plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)plVar8 == '\0') {
                            if (DAT_1804acb98 == 0) {
                              lVar2 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar2 + 0x390);
                              uVar6 = FUN_18014d730(in_stack_00000048);
                              uVar5 = DAT_1804b5208;
                              uVar7 = (**(code **)(lVar2 + 0x518))
                                                (in_stack_00000048,&stack0x00000954,1);
                              lVar2 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar2 + 0x390);
                              uVar9 = FUN_1800024f0(in_stack_00000048);
                              uVar10 = FUN_180002550(in_stack_00000048);
                              uVar11 = (**(code **)(lVar2 + 0x518))
                                                 (in_stack_00000048,&stack0x00003f98,5);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar12 = FUN_1800025a0(in_stack_00000048);
                              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                              uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,
                                                uVar7);
                              DAT_1804acb98 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                            }
                            uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804acb98,in_stack_000000b0);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,2,in_stack_000000a0,0);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0
                                               ,0x6c53);
                            (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8
                                               ,in_stack_00000078 % 0x510db ^ in_stack_000000c0 ^
                                                0xa086e089301427dd);
                            (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                            uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                            (**(code **)(*in_stack_00000048 + 0x1a0))
                                      (in_stack_00000048,uVar5,in_stack_000000d8);
                            plVar8 = (longlong *)
                                     (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)plVar8 == '\0') {
                              plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)plVar8 == '\0') {
                                plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)plVar8 == '\0') {
                                  if (DAT_1804acba0 == 0) {
                                    lVar2 = *in_stack_00000048;
                                    pcVar1 = *(code **)(lVar2 + 0x390);
                                    uVar6 = FUN_18014d730(in_stack_00000048);
                                    uVar5 = DAT_1804b5208;
                                    uVar7 = (**(code **)(lVar2 + 0x518))
                                                      (in_stack_00000048,&stack0x00000952,1);
                                    lVar2 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar2 + 0x390);
                                    uVar9 = FUN_1800024f0(in_stack_00000048);
                                    uVar10 = FUN_180002550(in_stack_00000048);
                                    uVar11 = (**(code **)(lVar2 + 0x518))
                                                       (in_stack_00000048,&stack0x00003f88,5);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar12 = FUN_1800025a0(in_stack_00000048);
                                    uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                                    (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                    uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,
                                                      uVar5,uVar7);
                                    DAT_1804acba0 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar5);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar5);
                                  }
                                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,DAT_1804acba0,
                                                     in_stack_000000b0);
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_000000a0,0);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_000000c8,
                                                     in_stack_000000d0,0x2cd2);
                                  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000098,
                                                     in_stack_000000b8,
                                                     in_stack_00000078 % 0x53a87 ^ in_stack_000000c0
                                                     ^ 0xbfa0fd00e8b2dda2);
                                  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar5,in_stack_000000a8,uVar6
                                                    );
                                  (**(code **)(*in_stack_00000048 + 0x1a0))
                                            (in_stack_00000048,uVar5,in_stack_000000d8);
                                  plVar8 = (longlong *)
                                           (**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                                  if ((char)plVar8 == '\0') {
                                    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))()
                                    ;
                                    if ((char)plVar8 == '\0') {
                                      plVar8 = (longlong *)
                                               (**(code **)(*in_stack_00000048 + 0x720))();
                                      if ((char)plVar8 == '\0') {
                                        if (DAT_1804acba8 == 0) {
                                          lVar2 = *in_stack_00000048;
                                          pcVar1 = *(code **)(lVar2 + 0x390);
                                          uVar6 = FUN_18014d730(in_stack_00000048);
                                          uVar5 = DAT_1804b5208;
                                          uVar7 = (**(code **)(lVar2 + 0x518))
                                                            (in_stack_00000048,&stack0x00000950,1);
                                          lVar2 = *in_stack_00000048;
                                          pcVar3 = *(code **)(lVar2 + 0x390);
                                          uVar9 = FUN_1800024f0(in_stack_00000048);
                                          uVar10 = FUN_180002550(in_stack_00000048);
                                          uVar11 = (**(code **)(lVar2 + 0x518))
                                                             (in_stack_00000048,&stack0x00003f78,5);
                                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar12 = FUN_1800025a0(in_stack_00000048);
                                          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,
                                                             uVar12);
                                          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                          uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,
                                                            uVar6,uVar5,uVar7);
                                          DAT_1804acba8 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar5);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar5);
                                        }
                                        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,DAT_1804acba8,
                                                           in_stack_000000b0);
                                        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                          (in_stack_00000048,2,in_stack_000000a0,0);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_000000c8,
                                                           in_stack_000000d0,0x6e8c);
                                        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000098,
                                                           in_stack_000000b8,
                                                           in_stack_00000078 % 0xe03ea ^
                                                           in_stack_000000c0 ^ 0xa3ef3f5d4fd0d34e);
                                        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                                        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,uVar5,in_stack_000000a8
                                                           ,uVar6);
                                        (**(code **)(*in_stack_00000048 + 0x1a0))
                                                  (in_stack_00000048,uVar5,in_stack_000000d8);
                                        plVar8 = (longlong *)
                                                 (**(code **)(*in_stack_00000048 + 0x720))
                                                           (in_stack_00000048);
                                        if ((char)plVar8 == '\0') {
                                          plVar8 = (longlong *)
                                                   (**(code **)(*in_stack_00000048 + 0x720))();
                                          if ((char)plVar8 == '\0') {
                                            plVar8 = (longlong *)
                                                     (**(code **)(*in_stack_00000048 + 0x720))();
                                            if ((char)plVar8 == '\0') {
                                              plVar8 = (longlong *)0x9204096f9814073e;
                                              if (DAT_1804acbb0 == 0) {
                                                lVar2 = *in_stack_00000048;
                                                pcVar1 = *(code **)(lVar2 + 0x390);
                                                uVar6 = FUN_18014d730(in_stack_00000048);
                                                uVar5 = DAT_1804b5208;
                                                uVar7 = (**(code **)(lVar2 + 0x518))
                                                                  (in_stack_00000048,
                                                                   &stack0x0000094e,1);
                                                lVar2 = *in_stack_00000048;
                                                pcVar3 = *(code **)(lVar2 + 0x390);
                                                uVar9 = FUN_1800024f0(in_stack_00000048);
                                                uVar10 = FUN_180002550(in_stack_00000048);
                                                uVar11 = (**(code **)(lVar2 + 0x518))
                                                                   (in_stack_00000048,
                                                                    &stack0x00003f68,5);
                                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                                uVar12 = FUN_1800025a0(in_stack_00000048);
                                                uVar12 = (*pcVar4)(in_stack_00000048,
                                                                   in_stack_00000090,uVar12);
                                                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,
                                                          uVar12);
                                                uVar5 = (*pcVar1)(in_stack_00000048,
                                                                  in_stack_00000090,uVar6,uVar5,
                                                                  uVar7);
                                                DAT_1804acbb0 =
                                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                                               (in_stack_00000048,uVar5);
                                                plVar8 = (longlong *)
                                                         (**(code **)(*in_stack_00000048 + 0xb8))
                                                                   (in_stack_00000048,uVar5);
                                              }
                                              return plVar8;
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
  }
  return plVar8;
}

