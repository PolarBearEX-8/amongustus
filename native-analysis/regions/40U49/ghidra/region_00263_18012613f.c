/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=263 entry=18012613f blocks=29 maxInstructions=100000 */

/* cut edge: 0x180126280 -> 0x18014d3db */
/* cut edge: 0x18012637a -> 0x18014d3db */
/* cut edge: 0x180126390 -> 0x18014d3db */
/* cut edge: 0x18012653e -> 0x18014d3db */
/* cut edge: 0x180126635 -> 0x18014d3db */
/* cut edge: 0x18012664b -> 0x18014d3db */
/* cut edge: 0x1801267fd -> 0x18014d3db */
/* cut edge: 0x1801268f2 -> 0x18014d3db */
/* cut edge: 0x180126908 -> 0x18014d3db */
/* cut edge: 0x180126ac2 -> 0x18014d3db */
/* cut edge: 0x180126bbe -> 0x180130018 */
/* cut edge: 0x180126bea -> 0x18014d3db */
/* cut edge: 0x180126c05 -> 0x18014d3db */
/* cut edge: 0x180126dbb -> 0x18013d480 */
/* cut edge: 0x180126e47 -> 0x18013d4d1 */
/* cut edge: 0x180126e5a -> 0x18014d3db */
/* cut edge: 0x180126f71 -> 0x18014d3db */
/* cut edge: 0x180126f87 -> 0x18014d3db */
/* cut edge: 0x180126f9d -> 0x180127133 */
/* cut edge: 0x180126ff0 -> 0x180127133 */
/* target 0x180127133 original byte 0x48 replaced by RET */
/* target 0x180130018 original byte 0x4c replaced by RET */
/* target 0x18013d480 original byte 0x48 replaced by RET */
/* target 0x18013d4d1 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


ulonglong region_00263_18012613f(void)

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
  ulonglong uVar13;
  ulonglong uVar14;
  longlong lVar15;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
  ulonglong in_stack_00000068;
  code *pcStack0000000000000078;
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
  undefined8 in_stack_000001d8;
  undefined8 in_stack_00000c70;
  undefined8 in_stack_00000c78;
  
  lVar15 = *in_stack_00000048;
  pcStack0000000000000078 = *(code **)(lVar15 + 0x390);
  uVar5 = FUN_18014d730(in_stack_00000048);
  uVar11 = DAT_1804b5208;
  uVar6 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000a04,1);
  lVar15 = *in_stack_00000048;
  pcVar1 = *(code **)(lVar15 + 0x390);
  uVar7 = FUN_1800024f0(in_stack_00000048);
  uVar8 = FUN_180002550(in_stack_00000048);
  uVar9 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00004338,5);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
  uVar10 = FUN_1800025a0(in_stack_00000048);
  uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar10);
  (*pcVar1)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
  uVar11 = (*pcStack0000000000000078)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6);
  DAT_1804ac8f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804ac8f8,in_stack_000000b0);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x419);
  (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
  uVar13 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)uVar13 == '\0') {
    uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar13 == '\0') {
      uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)uVar13 == '\0') {
        if (DAT_1804ac900 == 0) {
          lVar15 = *in_stack_00000048;
          pcStack0000000000000078 = *(code **)(lVar15 + 0x390);
          uVar5 = FUN_18014d730(in_stack_00000048);
          uVar11 = DAT_1804b5208;
          uVar6 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000a02,1);
          lVar15 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar15 + 0x390);
          uVar7 = FUN_1800024f0(in_stack_00000048);
          uVar8 = FUN_180002550(in_stack_00000048);
          uVar9 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00004328,5);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
          uVar10 = FUN_1800025a0(in_stack_00000048);
          uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar10);
          (*pcVar1)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
          uVar11 = (*pcStack0000000000000078)
                             (in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6);
          DAT_1804ac900 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac900,in_stack_000000b0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7fca);
        (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000058 % 0x8fe34 ^ in_stack_000000c0 ^
                           0x25c3115cd8aca83d);
        (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
        pcStack0000000000000078 =
             (code *)(**(code **)(*in_stack_00000048 + 0x1a0))
                               (in_stack_00000048,uVar11,in_stack_000000d8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)uVar13 == '\0') {
          uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)uVar13 == '\0') {
            uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)uVar13 == '\0') {
              if (DAT_1804ac908 == 0) {
                lVar15 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar15 + 0x390);
                uVar5 = FUN_18014d730(in_stack_00000048);
                uVar11 = DAT_1804b5208;
                uVar6 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000a00,1);
                lVar15 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar15 + 0x390);
                uVar7 = FUN_1800024f0(in_stack_00000048);
                uVar8 = FUN_180002550(in_stack_00000048);
                uVar9 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00004318,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar10 = FUN_1800025a0(in_stack_00000048);
                uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar10);
                (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6);
                DAT_1804ac908 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
              }
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804ac908,in_stack_000000b0);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x203);
              (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000058 % 0xd8a7b ^ in_stack_000000c0 ^
                                 0x696fe842528660ed);
              (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
              uVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                 (in_stack_00000048,uVar11,in_stack_000000d8);
              uVar13 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)uVar13 == '\0') {
                uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)uVar13 == '\0') {
                  uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)uVar13 == '\0') {
                    if (DAT_1804ac910 == 0) {
                      lVar15 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar15 + 0x390);
                      uVar5 = FUN_18014d730(in_stack_00000048);
                      uVar11 = DAT_1804b5208;
                      uVar6 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x000009fe,1);
                      lVar15 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar15 + 0x390);
                      uVar7 = FUN_1800024f0(in_stack_00000048);
                      uVar8 = FUN_180002550(in_stack_00000048);
                      uVar9 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00004308,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar10 = FUN_1800025a0(in_stack_00000048);
                      uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar10);
                      (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                      uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6);
                      DAT_1804ac910 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                    }
                    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804ac910,in_stack_000000b0);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x56f);
                    (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)in_stack_00000058 % 0x5fc83 ^ in_stack_000000c0 ^
                                       0x762f5cebc702a91f);
                    (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
                    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
                    lVar15 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                       (in_stack_00000048,uVar11,in_stack_000000d8);
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar13 == '\0') {
                      pcStack0000000000000078 =
                           (code *)((longlong)pcStack0000000000000078 + (uVar12 ^ in_stack_00000068)
                                   );
                      if (in_stack_000000f8 == 0) {
                        return uVar12 ^ in_stack_00000068;
                      }
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)uVar13 == '\0') {
                        uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)uVar13 == '\0') {
                          if (DAT_1804ac918 == 0) {
                            lVar4 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar4 + 0x390);
                            uVar5 = FUN_18001e7d0(in_stack_00000048);
                            uVar11 = DAT_1804b5208;
                            uVar6 = (**(code **)(lVar4 + 0x518))
                                              (in_stack_00000048,&stack0x000009fc,1);
                            lVar4 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar4 + 0x390);
                            uVar7 = FUN_1800024f0(in_stack_00000048);
                            uVar8 = FUN_180002550(in_stack_00000048);
                            uVar9 = (**(code **)(lVar4 + 0x518))
                                              (in_stack_00000048,&stack0x00005d30,0x19);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar10 = FUN_1800025a0(in_stack_00000048);
                            uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar10);
                            (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                            uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,
                                               uVar6);
                            DAT_1804ac918 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                          }
                          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804ac918,in_stack_000000b0);
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,5,in_stack_000000a0,0);
                          uVar12 = (**(code **)(*in_stack_00000048 + 0x570))
                                             (in_stack_00000048,uVar5,0,in_stack_000001d8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          if (DAT_1804b5328 == 0) {
                            return uVar12;
                          }
                          if (DAT_1804b5330 == 0) {
                            return uVar12;
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,DAT_1804b5328,DAT_1804b5330,
                                             in_stack_00000c70);
                          (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00000c78);
                          (*pcVar1)(in_stack_00000048,uVar5,2,uVar6);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             (longlong)(lVar15 + (uVar14 ^ in_stack_00000058)) %
                                             0xe1e5e ^ 0xe9ebfdf6241db8d5);
                          (*pcVar1)(in_stack_00000048,uVar5,3,uVar6);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_000000c0);
                          (*pcVar1)(in_stack_00000048,uVar5,4,uVar6);
                          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar11,in_stack_000000d8);
                          uVar13 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)uVar13 == '\0') {
                            uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)uVar13 == '\0') {
                              uVar13 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)uVar13 == '\0') {
                                uVar12 = 0xb1c93fa2126f48ea;
                                if (DAT_1804ac920 == 0) {
                                  lVar15 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar15 + 0x390);
                                  uVar5 = FUN_18014d730(in_stack_00000048);
                                  uVar11 = DAT_1804b5208;
                                  uVar6 = (**(code **)(lVar15 + 0x518))
                                                    (in_stack_00000048,&stack0x000009fa,1);
                                  lVar15 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar15 + 0x390);
                                  uVar7 = FUN_1800024f0(in_stack_00000048);
                                  uVar8 = FUN_180002550(in_stack_00000048);
                                  uVar9 = (**(code **)(lVar15 + 0x518))
                                                    (in_stack_00000048,&stack0x000042f8,5);
                                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar10 = FUN_1800025a0(in_stack_00000048);
                                  uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar10);
                                  (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                                  uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,
                                                     uVar11,uVar6);
                                  DAT_1804ac920 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar11);
                                  uVar12 = (**(code **)(*in_stack_00000048 + 0xb8))
                                                     (in_stack_00000048,uVar11);
                                }
                                return uVar12;
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
  return uVar13;
}

