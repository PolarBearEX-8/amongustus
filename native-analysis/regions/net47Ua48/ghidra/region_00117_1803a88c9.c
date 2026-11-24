/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=117 entry=1803a88c9 blocks=33 maxInstructions=50000 */

/* cut edge: 0x1803a88c9 -> 0x1803d86a8 */
/* cut edge: 0x1803a89d6 -> 0x1803d7971 */
/* cut edge: 0x1803a89f5 -> 0x1803351d6 */
/* cut edge: 0x1803a8a1e -> 0x1803d79dd */
/* cut edge: 0x1803a8a3d -> 0x1803351d6 */
/* cut edge: 0x1803a8a61 -> 0x1803d7a49 */
/* cut edge: 0x1803a8a80 -> 0x1803351d6 */
/* cut edge: 0x1803a8aa4 -> 0x1803d7a9a */
/* cut edge: 0x1803a8ac8 -> 0x1803351d6 */
/* cut edge: 0x1803a8aec -> 0x1803351d6 */
/* cut edge: 0x1803a8c98 -> 0x1803351d6 */
/* cut edge: 0x1803a8d6d -> 0x1803d7bc8 */
/* cut edge: 0x1803a8d8c -> 0x1803351d6 */
/* cut edge: 0x1803a8dbb -> 0x1803351d6 */
/* cut edge: 0x1803a8f37 -> 0x1803351d6 */
/* cut edge: 0x1803a901f -> 0x1803351d6 */
/* cut edge: 0x1803a91dd -> 0x1803351d6 */
/* cut edge: 0x1803a9312 -> 0x1803351d6 */
/* cut edge: 0x1803a934d -> 0x1803351d6 */
/* cut edge: 0x1803a94f5 -> 0x1803351d6 */
/* cut edge: 0x1803a95d0 -> 0x1803351d6 */
/* cut edge: 0x1803a960b -> 0x1803351d6 */
/* cut edge: 0x1803a97b3 -> 0x1803351d6 */
/* cut edge: 0x1803a97b3 -> 0x1803a988e */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803a988e original byte 0x48 replaced by RET */
/* target 0x1803d7971 original byte 0x48 replaced by RET */
/* target 0x1803d79dd original byte 0x48 replaced by RET */
/* target 0x1803d7a49 original byte 0x48 replaced by RET */
/* target 0x1803d7a9a original byte 0x48 replaced by RET */
/* target 0x1803d7bc8 original byte 0x48 replaced by RET */
/* target 0x1803d86a8 original byte 0x48 replaced by RET */


void region_00117_1803a88c9(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_00000140;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000208;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000140);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc8365f39a67fb1);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090);
  (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 != '\0') {
    return;
  }
  if (DAT_1804b53e0 != 0) {
    uVar7 = (**(code **)(*in_stack_00000048 + 0x2f8))(in_stack_00000048,in_stack_00000168);
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar6 == '\0') {
      if (DAT_1804b5b58 == 0) {
        return;
      }
      (**(code **)(*in_stack_00000048 + 0x1e8))(in_stack_00000048,uVar7,DAT_1804b5b58,0);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      lVar2 = DAT_1804b5020;
      if (cVar6 == '\0') {
        if (DAT_1804b5020 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,DAT_1804b5020);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          if (DAT_1804b5028 == 0) {
            return;
          }
          (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar7,lVar2);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar6 == '\0') {
              if (DAT_1804b30f0 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1803335a0(in_stack_00000048);
                uVar8 = DAT_1804b59f0;
                uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000003a4,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar11 = FUN_1800024f0(in_stack_00000048);
                uVar12 = FUN_180002550(in_stack_00000048);
                uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003600,0x16);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar14 = FUN_1800025a0(in_stack_00000048);
                uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
                DAT_1804b30f0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b30f0,in_stack_00000078);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000060,in_stack_00000070,0x6768);
              (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                  in_stack_000000c8 ^ 0x7a10e8e3563cfd01);
              (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              lVar2 = DAT_1804b5030;
              if (cVar6 == '\0') {
                if (DAT_1804b5030 == 0) {
                  return;
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar7,DAT_1804b5030,uVar8);
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar6 == '\0') {
                    if (DAT_1804b30f8 == 0) {
                      lVar5 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar5 + 0x390);
                      uVar9 = FUN_180333500(in_stack_00000048);
                      uVar8 = DAT_1804b59f0;
                      uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000003a2,1);
                      lVar5 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar5 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000048);
                      uVar12 = FUN_180002550(in_stack_00000048);
                      uVar13 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000026d0,0xd);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar14 = FUN_1800025a0(in_stack_00000048);
                      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                      uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
                      DAT_1804b30f8 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b30f8,in_stack_00000078);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                        0xdfc5815bc278527a);
                    (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                        in_stack_000000c8);
                    (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar6 == '\0') {
                        if (DAT_1804b3100 == 0) {
                          lVar5 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar5 + 0x390);
                          uVar10 = FUN_180333500(in_stack_00000048);
                          uVar9 = DAT_1804b59f0;
                          uVar11 = (**(code **)(lVar5 + 0x518))
                                             (in_stack_00000048,&stack0x000003a0,1);
                          lVar5 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar5 + 0x390);
                          uVar12 = FUN_1800024f0(in_stack_00000048);
                          uVar13 = FUN_180002550(in_stack_00000048);
                          uVar14 = (**(code **)(lVar5 + 0x518))
                                             (in_stack_00000048,&stack0x00004ad0,0x29);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar15 = FUN_1800025a0(in_stack_00000048);
                          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                          (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                          uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11)
                          ;
                          DAT_1804b3100 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                        }
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b3100,in_stack_00000078);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                           (in_stack_00000048,4,in_stack_00000088,0);
                        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar10,0,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_00000208);
                        (*pcVar1)(in_stack_00000048,uVar10,1,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           0xdfb22bab6c698c7f);
                        (*pcVar1)(in_stack_00000048,uVar10,2,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_000000c8);
                        (*pcVar1)(in_stack_00000048,uVar10,3,uVar8);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 == '\0') {
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,lVar2,uVar8);
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar6 == '\0') {
                              if (DAT_1804b3108 == 0) {
                                lVar5 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar5 + 0x390);
                                uVar9 = FUN_1803335a0(in_stack_00000048);
                                uVar8 = DAT_1804b59f0;
                                uVar10 = (**(code **)(lVar5 + 0x518))
                                                   (in_stack_00000048,&stack0x0000039e,1);
                                lVar5 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar5 + 0x390);
                                uVar11 = FUN_1800024f0(in_stack_00000048);
                                uVar12 = FUN_180002550(in_stack_00000048);
                                uVar13 = (**(code **)(lVar5 + 0x518))
                                                   (in_stack_00000048,&stack0x000035d0,0x16);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar14 = FUN_1800025a0(in_stack_00000048);
                                uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                                (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,
                                                  uVar10);
                                DAT_1804b3108 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar8);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                              }
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b3108,in_stack_00000078);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                 (in_stack_00000048,in_stack_00000060,
                                                  in_stack_00000070,0x1019);
                              (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                 (in_stack_00000048,in_stack_00000098,
                                                  in_stack_00000090,
                                                  in_stack_000000c8 ^ 0x4dd1571d1c070a6e);
                              (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar6 == '\0') {
                                (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar7,lVar2,uVar8);
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar6 == '\0') {
                                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar6 == '\0') {
                                    if (DAT_1804b3110 == 0) {
                                      lVar2 = *in_stack_00000048;
                                      pcVar1 = *(code **)(lVar2 + 0x390);
                                      uVar8 = FUN_1803335a0(in_stack_00000048);
                                      uVar7 = DAT_1804b59f0;
                                      uVar9 = (**(code **)(lVar2 + 0x518))
                                                        (in_stack_00000048,&stack0x0000039c,1);
                                      lVar2 = *in_stack_00000048;
                                      pcVar3 = *(code **)(lVar2 + 0x390);
                                      uVar10 = FUN_1800024f0(in_stack_00000048);
                                      uVar11 = FUN_180002550(in_stack_00000048);
                                      uVar12 = (**(code **)(lVar2 + 0x518))
                                                         (in_stack_00000048,&stack0x000035a0,0x16);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar13 = FUN_1800025a0(in_stack_00000048);
                                      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13)
                                      ;
                                      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,
                                                        uVar7,uVar9);
                                      DAT_1804b3110 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar7);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar7);
                                    }
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,DAT_1804b3110,
                                                       in_stack_00000078);
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,2,in_stack_00000088,0);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000060,
                                                       in_stack_00000070,0x4ad3);
                                    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,
                                                       in_stack_000000c8 ^ 0x7fc613bd16cd50b4);
                                    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                                    (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar6 == '\0') {
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
    return;
  }
  return;
}

