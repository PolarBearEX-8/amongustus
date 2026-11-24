/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=136 entry=180463603 blocks=28 maxInstructions=50000 */

/* cut edge: 0x180463603 -> 0x180473ae8 */
/* cut edge: 0x180463619 -> 0x180473ae8 */
/* cut edge: 0x1804637e0 -> 0x180473ae8 */
/* cut edge: 0x1804638ea -> 0x180473ae8 */
/* cut edge: 0x18046391a -> 0x180473ae8 */
/* cut edge: 0x180463930 -> 0x180473ae8 */
/* cut edge: 0x180463b0f -> 0x180473ae8 */
/* cut edge: 0x180463cce -> 0x180473ae8 */
/* cut edge: 0x180463ce4 -> 0x180473ae8 */
/* cut edge: 0x180463ebd -> 0x180473d29 */
/* cut edge: 0x180463f7e -> 0x180473d7b */
/* cut edge: 0x180463f91 -> 0x180473ae8 */
/* cut edge: 0x18046405a -> 0x180473ae8 */
/* cut edge: 0x180464070 -> 0x180473ae8 */
/* cut edge: 0x180464271 -> 0x180473ae8 */
/* cut edge: 0x1804643bf -> 0x180473ae8 */
/* cut edge: 0x1804643d5 -> 0x180473ae8 */
/* cut edge: 0x1804643eb -> 0x1804645aa */
/* cut edge: 0x180464438 -> 0x1804645aa */
/* target 0x1804645aa original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180473d29 original byte 0x48 replaced by RET */
/* target 0x180473d7b original byte 0x48 replaced by RET */


longlong region_00136_180463603(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  int iVar4;
  longlong lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong lVar13;
  undefined8 uVar14;
  ulonglong unaff_R12;
  undefined8 unaff_R13;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_00000180;
  undefined8 in_stack_00000740;
  undefined8 in_stack_00000768;
  undefined8 in_stack_00002f50;
  undefined8 in_stack_00002f58;
  short in_stack_00002f60;
  undefined8 in_stack_00002f68;
  undefined8 in_stack_00002f70;
  
  lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)lVar5 == '\0') {
    lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar5 == '\0') {
      if (DAT_1804b4688 == 0) {
        lVar5 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar5 + 0x390);
        uVar6 = FUN_1803335f0(in_stack_00000048);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x0000032c,1);
        lVar5 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar5 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00000ee0,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
        DAT_1804b4688 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b4688,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0xec4);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)in_stack_00000068 % 0x1c808 ^ in_stack_000000c8 ^
                         0xd0e87204304df704);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
      lVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8)
      ;
      lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)lVar5 == '\0') {
        lVar13 = (unaff_R12 ^ in_stack_00000068) - lVar13;
        iVar4 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,unaff_R13);
        lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)lVar5 == '\0') {
          lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)lVar5 == '\0') {
            lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)lVar5 == '\0') {
              if (DAT_1804b4690 == 0) {
                lVar5 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar5 + 0x390);
                uVar6 = FUN_180333500(in_stack_00000048);
                uVar12 = DAT_1804b59f0;
                uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x0000032a,1);
                lVar5 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar5 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000048);
                uVar9 = FUN_180002550(in_stack_00000048);
                uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000030d0,0x12);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000048);
                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
                (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
                DAT_1804b4690 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
              }
              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b4690,in_stack_000000a0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,6,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,iVar4 + 1);
              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000180,in_stack_00000078,
                                 in_stack_00002f68);
              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                 in_stack_00002f70);
              (*pcVar1)(in_stack_00000048,uVar6,2,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000768,in_stack_00000740,
                                 (int)in_stack_00002f60);
              (*pcVar1)(in_stack_00000048,uVar6,3,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 lVar13 % 0x61938 ^ 0x5029b7ae6fb847cf);
              (*pcVar1)(in_stack_00000048,uVar6,4,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar6,5,uVar7);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
              lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)lVar5 == '\0') {
                lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)lVar5 == '\0') {
                  lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)lVar5 == '\0') {
                    if (DAT_1804b4698 == 0) {
                      lVar5 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar5 + 0x390);
                      uVar7 = FUN_180333500(in_stack_00000048);
                      uVar6 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00000328,1);
                      lVar5 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar5 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00003900,0x22)
                      ;
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar14 = FUN_1800025a0(in_stack_00000048);
                      uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar14);
                      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar14);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                      DAT_1804b4698 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b4698,in_stack_000000a0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,5,in_stack_00000090,0);
                    (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,uVar12);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_00002f58);
                    lVar5 = (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    if (DAT_1804b5328 == 0) {
                      return lVar5;
                    }
                    if (DAT_1804b5330 == 0) {
                      return lVar5;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,DAT_1804b5328,DAT_1804b5330,1);
                    (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       lVar13 % 0xe0e56 ^ 0x503d99426a951f86);
                    (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_000000c8);
                    (*pcVar1)(in_stack_00000048,uVar7,4,uVar8);
                    (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)lVar5 == '\0') {
                      lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)lVar5 == '\0') {
                        lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)lVar5 == '\0') {
                          if (DAT_1804b46a0 == 0) {
                            lVar5 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar5 + 0x390);
                            uVar7 = FUN_180333500(in_stack_00000048);
                            uVar6 = DAT_1804b59f0;
                            uVar8 = (**(code **)(lVar5 + 0x518))
                                              (in_stack_00000048,&stack0x00000326,1);
                            lVar5 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar5 + 0x390);
                            uVar9 = FUN_1800024f0(in_stack_00000048);
                            uVar10 = FUN_180002550(in_stack_00000048);
                            uVar11 = (**(code **)(lVar5 + 0x518))
                                               (in_stack_00000048,&stack0x00003f40,0x33);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar14 = FUN_1800025a0(in_stack_00000048);
                            uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar14);
                            (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar14);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8)
                            ;
                            DAT_1804b46a0 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b46a0,in_stack_000000a0);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,5,in_stack_00000090,0);
                          (**(code **)(*in_stack_00000048 + 0x570))
                                    (in_stack_00000048,uVar7,0,uVar12);
                          (**(code **)(*in_stack_00000048 + 0x570))
                                    (in_stack_00000048,uVar7,1,unaff_R13);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                              in_stack_00002f50);
                          (*pcVar1)(in_stack_00000048,uVar7,2,uVar12);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                              lVar13 % 0xf3435 ^ 0x5026acea69e5fe67);
                          (*pcVar1)(in_stack_00000048,uVar7,3,uVar12);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                              in_stack_000000c8);
                          (*pcVar1)(in_stack_00000048,uVar7,4,uVar12);
                          (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                          lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)lVar5 == '\0') {
                            lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)lVar5 == '\0') {
                              lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)lVar5 == '\0') {
                                lVar5 = (lVar13 / 0xa0434) * 0xa0434;
                                if (DAT_1804b46a8 == 0) {
                                  lVar5 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar5 + 0x390);
                                  uVar6 = FUN_180333500(in_stack_00000048);
                                  uVar12 = DAT_1804b59f0;
                                  uVar7 = (**(code **)(lVar5 + 0x518))
                                                    (in_stack_00000048,&stack0x00000324,1);
                                  lVar5 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar5 + 0x390);
                                  uVar8 = FUN_1800024f0(in_stack_00000048);
                                  uVar9 = FUN_180002550(in_stack_00000048);
                                  uVar10 = (**(code **)(lVar5 + 0x518))
                                                     (in_stack_00000048,&stack0x000031c0,0x19);
                                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar11 = FUN_1800025a0(in_stack_00000048);
                                  uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
                                  (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                  uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,
                                                     uVar12,uVar7);
                                  DAT_1804b46a8 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar12);
                                  lVar5 = (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar12);
                                }
                                return lVar5;
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
  return lVar5;
}

