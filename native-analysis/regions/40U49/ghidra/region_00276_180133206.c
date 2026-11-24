/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=276 entry=180133206 blocks=32 maxInstructions=100000 */

/* cut edge: 0x180133206 -> 0x18014d3db */
/* cut edge: 0x18013321c -> 0x18014d3db */
/* cut edge: 0x1801333f3 -> 0x18014d3db */
/* cut edge: 0x180133521 -> 0x18014d3db */
/* cut edge: 0x180133537 -> 0x18014d3db */
/* cut edge: 0x1801336e0 -> 0x18014d3db */
/* cut edge: 0x1801337da -> 0x18014d3db */
/* cut edge: 0x1801337f0 -> 0x18014d3db */
/* cut edge: 0x18013399c -> 0x18014d3db */
/* cut edge: 0x180133a93 -> 0x18014d3db */
/* cut edge: 0x180133aa9 -> 0x18014d3db */
/* cut edge: 0x180133c5d -> 0x18014d3db */
/* cut edge: 0x180133d52 -> 0x18014d3db */
/* cut edge: 0x180133d68 -> 0x18014d3db */
/* cut edge: 0x180133f22 -> 0x18014d3db */
/* cut edge: 0x180134020 -> 0x180136ca4 */
/* cut edge: 0x18013404b -> 0x18013cefa */
/* cut edge: 0x18013405a -> 0x18014d3db */
/* cut edge: 0x180134078 -> 0x18014d3db */
/* cut edge: 0x18013408e -> 0x18014d3db */
/* cut edge: 0x1801340a4 -> 0x180134242 */
/* cut edge: 0x1801340f6 -> 0x180134242 */
/* target 0x180134242 original byte 0x48 replaced by RET */
/* target 0x180136ca4 original byte 0x48 replaced by RET */
/* target 0x18013cefa original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


undefined8 region_00276_180133206(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong *in_stack_00000048;
  longlong in_stack_00000058;
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
  undefined8 in_stack_000001c8;
  
  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)uVar5 == '\0') {
    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar5 == '\0') {
      if (DAT_1804aca40 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_18001e7d0(in_stack_00000048);
        uVar5 = DAT_1804b5208;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000009b0,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00005cb0,0x19);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
        (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar5 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
        DAT_1804aca40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804aca40,in_stack_000000b0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_000000a0,0);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000001c8);
      (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         in_stack_00000058 % 0xaba2b ^ 0xe9e87d23014ac79e);
      (*pcVar2)(in_stack_00000048,uVar6,2,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
      (*pcVar2)(in_stack_00000048,uVar6,3,uVar7);
      (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)uVar5 == '\0') {
        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)uVar5 == '\0') {
          uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)uVar5 == '\0') {
            if (DAT_1804aca48 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar6 = FUN_18014d730(in_stack_00000048);
              uVar5 = DAT_1804b5208;
              uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000009ae,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004178,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
              (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar5 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
              DAT_1804aca48 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
            }
            uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804aca48,in_stack_000000b0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0xc3f);
            (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_00000058 % 0xafb93 ^ in_stack_000000c0 ^ 0xf331212ae49d99ac)
            ;
            (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)uVar5 == '\0') {
              uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)uVar5 == '\0') {
                uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)uVar5 == '\0') {
                  if (DAT_1804aca50 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar6 = FUN_18014d730(in_stack_00000048);
                    uVar5 = DAT_1804b5208;
                    uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000009ac,1);
                    lVar1 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004168,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
                    (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar5 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
                    DAT_1804aca50 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                  }
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804aca50,in_stack_000000b0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x158f);
                  (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_00000058 % 0xc83c5 ^ in_stack_000000c0 ^
                                     0xdc242f7b0f6c5a14);
                  (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                  (**(code **)(*in_stack_00000048 + 0x1a0))
                            (in_stack_00000048,uVar5,in_stack_000000d8);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)uVar5 == '\0') {
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)uVar5 == '\0') {
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)uVar5 == '\0') {
                        if (DAT_1804aca58 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar6 = FUN_18014d730(in_stack_00000048);
                          uVar5 = DAT_1804b5208;
                          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000009aa,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar8 = FUN_1800024f0(in_stack_00000048);
                          uVar9 = FUN_180002550(in_stack_00000048);
                          uVar10 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00004158,5);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar11 = FUN_1800025a0(in_stack_00000048);
                          uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
                          (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                          uVar5 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
                          DAT_1804aca58 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                        }
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804aca58,in_stack_000000b0);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x4bb1);
                        (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_00000058 % 0x606f0 ^ in_stack_000000c0 ^
                                           0x94f966cd504de68d);
                        (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                        (**(code **)(*in_stack_00000048 + 0x1a0))
                                  (in_stack_00000048,uVar5,in_stack_000000d8);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)uVar5 == '\0') {
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)uVar5 == '\0') {
                            uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)uVar5 == '\0') {
                              if (DAT_1804aca60 == 0) {
                                lVar1 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar1 + 0x390);
                                uVar6 = FUN_18014d730(in_stack_00000048);
                                uVar5 = DAT_1804b5208;
                                uVar7 = (**(code **)(lVar1 + 0x518))
                                                  (in_stack_00000048,&stack0x000009a8,1);
                                lVar1 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar1 + 0x390);
                                uVar8 = FUN_1800024f0(in_stack_00000048);
                                uVar9 = FUN_180002550(in_stack_00000048);
                                uVar10 = (**(code **)(lVar1 + 0x518))
                                                   (in_stack_00000048,&stack0x00004148,5);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar11 = FUN_1800025a0(in_stack_00000048);
                                uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
                                (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                uVar5 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,
                                                  uVar7);
                                DAT_1804aca60 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar5);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                              }
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804aca60,in_stack_000000b0);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_000000a0,0);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000c8,
                                                 in_stack_000000d0,0x5b0);
                              (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,
                                                 in_stack_00000058 % 0x2616d ^ in_stack_000000c0 ^
                                                 0xc4396e9792bb2c57);
                              (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar5,in_stack_000000d8);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if ((char)uVar5 == '\0') {
                                if ((longlong)in_stack_000000c0 < 1) {
                                  return uVar5;
                                }
                                if (in_stack_000000f8 == 0) {
                                  return uVar5;
                                }
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)uVar5 == '\0') {
                                  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)uVar5 == '\0') {
                                    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if ((char)uVar5 == '\0') {
                                      uVar5 = 0x81762fefd16e267f;
                                      if (DAT_1804aca68 == 0) {
                                        lVar1 = *in_stack_00000048;
                                        pcVar2 = *(code **)(lVar1 + 0x390);
                                        uVar6 = FUN_18001ed20(in_stack_00000048);
                                        uVar5 = DAT_1804b5208;
                                        uVar7 = (**(code **)(lVar1 + 0x518))
                                                          (in_stack_00000048,&stack0x000009a6,1);
                                        lVar1 = *in_stack_00000048;
                                        pcVar3 = *(code **)(lVar1 + 0x390);
                                        uVar8 = FUN_1800024f0(in_stack_00000048);
                                        uVar9 = FUN_180002550(in_stack_00000048);
                                        uVar10 = (**(code **)(lVar1 + 0x518))
                                                           (in_stack_00000048,&stack0x00006be0,0x16)
                                        ;
                                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar11 = FUN_1800025a0(in_stack_00000048);
                                        uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,
                                                           uVar11);
                                        (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                        uVar5 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,
                                                          uVar5,uVar7);
                                        DAT_1804aca68 =
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
  return uVar5;
}

