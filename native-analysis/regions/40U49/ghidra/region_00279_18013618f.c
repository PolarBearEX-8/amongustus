/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=279 entry=18013618f blocks=35 maxInstructions=100000 */

/* cut edge: 0x18013618f -> 0x18014d3db */
/* cut edge: 0x1801361a5 -> 0x18014d3db */
/* cut edge: 0x18013634e -> 0x18014d3db */
/* cut edge: 0x180136448 -> 0x18014d3db */
/* cut edge: 0x18013645e -> 0x18014d3db */
/* cut edge: 0x18013660a -> 0x18014d3db */
/* cut edge: 0x180136701 -> 0x18014d3db */
/* cut edge: 0x180136717 -> 0x18014d3db */
/* cut edge: 0x1801368cb -> 0x18014d3db */
/* cut edge: 0x1801369c0 -> 0x18014d3db */
/* cut edge: 0x1801369d6 -> 0x18014d3db */
/* cut edge: 0x180136b90 -> 0x18014d3db */
/* cut edge: 0x180136ca4 -> 0x18013cefa */
/* cut edge: 0x180136cb3 -> 0x18014d3db */
/* cut edge: 0x180136cd1 -> 0x18014d3db */
/* cut edge: 0x180136e79 -> 0x18014d3db */
/* cut edge: 0x180136f5d -> 0x18014d3db */
/* cut edge: 0x180136f96 -> 0x18014d3db */
/* cut edge: 0x180136fe1 -> 0x18014d3db */
/* cut edge: 0x18013702d -> 0x18014d3db */
/* cut edge: 0x180137066 -> 0x18014d3db */
/* cut edge: 0x18013707c -> 0x18014d3db */
/* cut edge: 0x180137092 -> 0x180137224 */
/* cut edge: 0x1801370e4 -> 0x180137224 */
/* target 0x180137224 original byte 0x48 replaced by RET */
/* target 0x18013cefa original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


undefined8 region_00279_18013618f(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  ulonglong uVar12;
  longlong lVar13;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
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
  undefined8 in_stack_00000c68;
  
  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)uVar5 == '\0') {
    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar5 == '\0') {
      if (DAT_1804acab8 == 0) {
        lVar13 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar13 + 0x390);
        uVar6 = FUN_18014d730(in_stack_00000048);
        uVar5 = DAT_1804b5208;
        uVar7 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x00000990,1);
        lVar13 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar13 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000040d8,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
        DAT_1804acab8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804acab8,in_stack_000000b0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7ce8);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         (longlong)in_stack_00000058 % 0x64dc3 ^ in_stack_000000c0 ^
                         0xb504b8a3e2a7ffcc);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)uVar5 == '\0') {
        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)uVar5 == '\0') {
          uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)uVar5 == '\0') {
            if (DAT_1804acac0 == 0) {
              lVar13 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar13 + 0x390);
              uVar6 = FUN_18014d730(in_stack_00000048);
              uVar5 = DAT_1804b5208;
              uVar7 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x0000098e,1);
              lVar13 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar13 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000040c8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
              DAT_1804acac0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
            }
            uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804acac0,in_stack_000000b0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x26b);
            (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               (longlong)in_stack_00000058 % 0xd3f53 ^ in_stack_000000c0 ^
                               0xeb76ac3e98b40bb2);
            (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)uVar5 == '\0') {
              uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)uVar5 == '\0') {
                uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)uVar5 == '\0') {
                  if (DAT_1804acac8 == 0) {
                    lVar13 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar13 + 0x390);
                    uVar6 = FUN_18014d730(in_stack_00000048);
                    uVar5 = DAT_1804b5208;
                    uVar7 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x0000098c,1);
                    lVar13 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar13 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000040b8,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                    (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
                    DAT_1804acac8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                  }
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804acac8,in_stack_000000b0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6476);
                  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     (longlong)in_stack_00000058 % 0x11201 ^ in_stack_000000c0 ^
                                     0x8b43acdc62d44eb6);
                  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                     (in_stack_00000048,uVar5,in_stack_000000d8);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)uVar5 == '\0') {
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)uVar5 == '\0') {
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)uVar5 == '\0') {
                        if (DAT_1804acad0 == 0) {
                          lVar13 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar13 + 0x390);
                          uVar6 = FUN_18014d730(in_stack_00000048);
                          uVar5 = DAT_1804b5208;
                          uVar7 = (**(code **)(lVar13 + 0x518))
                                            (in_stack_00000048,&stack0x0000098a,1);
                          lVar13 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar13 + 0x390);
                          uVar8 = FUN_1800024f0(in_stack_00000048);
                          uVar9 = FUN_180002550(in_stack_00000048);
                          uVar10 = (**(code **)(lVar13 + 0x518))
                                             (in_stack_00000048,&stack0x000040a8,5);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar11 = FUN_1800025a0(in_stack_00000048);
                          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                          uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
                          DAT_1804acad0 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                        }
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804acad0,in_stack_000000b0);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x5b5a);
                        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           (longlong)in_stack_00000058 % 0x7d76b ^ in_stack_000000c0
                                           ^ 0xb92d47ee8b305ebb);
                        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                        lVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                           (in_stack_00000048,uVar5,in_stack_000000d8);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)uVar5 == '\0') {
                          if (in_stack_000000f8 == 0) {
                            return uVar5;
                          }
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)uVar5 == '\0') {
                            uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)uVar5 == '\0') {
                              if (DAT_1804acad8 == 0) {
                                lVar4 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar4 + 0x390);
                                uVar6 = FUN_18001e7d0(in_stack_00000048);
                                uVar5 = DAT_1804b5208;
                                uVar7 = (**(code **)(lVar4 + 0x518))
                                                  (in_stack_00000048,&stack0x00000988,1);
                                lVar4 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar4 + 0x390);
                                uVar8 = FUN_1800024f0(in_stack_00000048);
                                uVar9 = FUN_180002550(in_stack_00000048);
                                uVar10 = (**(code **)(lVar4 + 0x518))
                                                   (in_stack_00000048,&stack0x00005a30,0xe);
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar11 = FUN_1800025a0(in_stack_00000048);
                                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                                (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,
                                                  uVar7);
                                DAT_1804acad8 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar5);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                              }
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804acad8,in_stack_000000b0);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_000000a0,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,
                                                 (longlong)((uVar12 ^ in_stack_00000058) - lVar13) %
                                                 0x1cd1a ^ 0x161a617f9159648e);
                              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,in_stack_000000c0);
                              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if ((char)uVar5 == '\0') {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
                                uVar7 = FUN_18014d840(in_stack_00000048);
                                uVar8 = (*pcVar1)(in_stack_00000048,uVar7);
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if ((char)uVar5 == '\0') {
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                  uVar5 = FUN_18014daa0(in_stack_00000048);
                                  uVar9 = (*pcVar1)(in_stack_00000048,in_stack_000000c8,uVar5,
                                                    in_stack_00000c68);
                                  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if ((char)uVar5 == '\0') {
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                                    uVar5 = FUN_18014d8a0(in_stack_00000048);
                                    (*pcVar1)(in_stack_00000048,uVar8,uVar7,uVar5,uVar9);
                                    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if ((char)uVar5 == '\0') {
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x340);
                                      uVar5 = FUN_18014daf0(in_stack_00000048);
                                      (*pcVar1)(in_stack_00000048,uVar6,uVar5,uVar8);
                                      uVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                                      if ((char)uVar5 == '\0') {
                                        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                        if ((char)uVar5 == '\0') {
                                          uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                          if ((char)uVar5 == '\0') {
                                            uVar5 = 0x694506d1b0d14c47;
                                            if (DAT_1804acae0 == 0) {
                                              lVar13 = *in_stack_00000048;
                                              pcVar1 = *(code **)(lVar13 + 0x390);
                                              uVar6 = FUN_18014d730(in_stack_00000048);
                                              uVar5 = DAT_1804b5208;
                                              uVar7 = (**(code **)(lVar13 + 0x518))
                                                                (in_stack_00000048,&stack0x00000986,
                                                                 1);
                                              lVar13 = *in_stack_00000048;
                                              pcVar2 = *(code **)(lVar13 + 0x390);
                                              uVar8 = FUN_1800024f0(in_stack_00000048);
                                              uVar9 = FUN_180002550(in_stack_00000048);
                                              uVar10 = (**(code **)(lVar13 + 0x518))
                                                                 (in_stack_00000048,&stack0x00004098
                                                                  ,5);
                                              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                              uVar11 = FUN_1800025a0(in_stack_00000048);
                                              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090
                                                                 ,uVar11);
                                              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11)
                                              ;
                                              uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,
                                                                uVar6,uVar5,uVar7);
                                              DAT_1804acae0 =
                                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                                             (in_stack_00000048,uVar5);
                                              uVar5 = (**(code **)(*in_stack_00000048 + 0xb8))
                                                                (in_stack_00000048,uVar5);
                                            }
                                            return uVar5;
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
  return uVar5;
}

