/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=138 entry=180465560 blocks=32 maxInstructions=50000 */

/* cut edge: 0x180465560 -> 0x180473ae8 */
/* cut edge: 0x1804655a6 -> 0x180473ae8 */
/* cut edge: 0x1804655df -> 0x180473ae8 */
/* cut edge: 0x18046561d -> 0x180473ae8 */
/* cut edge: 0x180465633 -> 0x180473ae8 */
/* cut edge: 0x180465811 -> 0x180473ae8 */
/* cut edge: 0x1804658ef -> 0x180473ae8 */
/* cut edge: 0x180465933 -> 0x180473ae8 */
/* cut edge: 0x180465949 -> 0x180473ae8 */
/* cut edge: 0x180465b0f -> 0x180473ae8 */
/* cut edge: 0x180465bf8 -> 0x180473ae8 */
/* cut edge: 0x180465c0e -> 0x180473ae8 */
/* cut edge: 0x180465df0 -> 0x180473ae8 */
/* cut edge: 0x180465f1e -> 0x180473ae8 */
/* cut edge: 0x180465f59 -> 0x180473ae8 */
/* cut edge: 0x180465f6f -> 0x180473ae8 */
/* cut edge: 0x180466143 -> 0x180473ae8 */
/* cut edge: 0x180466227 -> 0x180473ae8 */
/* cut edge: 0x180466262 -> 0x180473ae8 */
/* cut edge: 0x180466278 -> 0x180473ae8 */
/* cut edge: 0x180466449 -> 0x180473ae8 */
/* cut edge: 0x18046652d -> 0x180473ae8 */
/* cut edge: 0x18046652d -> 0x180466576 */
/* target 0x180466576 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00138_180465560(void)

{
  code *pcVar1;
  longlong lVar2;
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
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  ulonglong unaff_R15;
  longlong *in_stack_00000048;
  undefined8 uStack0000000000000050;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_00000148;
  undefined8 in_stack_00000788;
  
  uStack0000000000000050 = in_stack_000000e8;
  pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
  FUN_18001ec80(in_stack_00000048);
  (*pcVar1)(in_stack_00000048,in_stack_000000e8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
    uVar6 = FUN_180010830(in_stack_00000048);
    uVar7 = (*pcVar1)(in_stack_00000048,uVar6);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
      uStack0000000000000050 = uVar7;
      uVar8 = FUN_180010890(in_stack_00000048);
      (*pcVar1)(in_stack_00000048,uVar7,uVar6,uVar8);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b46d8 == 0) {
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar8 = FUN_1803335a0(in_stack_00000048);
              uVar6 = DAT_1804b59f0;
              uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000318,1);
              lVar2 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar2 + 0x390);
              uVar10 = FUN_1800024f0(in_stack_00000048);
              uVar11 = FUN_180002550(in_stack_00000048);
              uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003840,0x16);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar13 = FUN_1800025a0(in_stack_00000048);
              uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
              unaff_R15 = 0x5056d1f7fbc1aef6;
              (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
              uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar8,uVar6,uVar9);
              DAT_1804b46d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
            }
            uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b46d8,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1b5c);
            (*pcVar1)(in_stack_00000048,uVar6,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_00000068 % 0x6c992 ^ in_stack_000000c8 ^ 0xf7b4c4dfa4e3d278)
            ;
            (*pcVar1)(in_stack_00000048,uVar6,1,uVar8);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uStack0000000000000050,in_stack_000000a8,uVar6);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
              uVar8 = FUN_1800108e0(in_stack_00000048);
              uVar6 = (*pcVar1)(in_stack_00000048,uVar7,uVar8,uVar6);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b46e0 == 0) {
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar9 = FUN_180333500(in_stack_00000048);
                      uVar7 = DAT_1804b59f0;
                      uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000316,1);
                      lVar2 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000048);
                      uVar12 = FUN_180002550(in_stack_00000048);
                      uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000030b0,0xd);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar14 = FUN_1800025a0(in_stack_00000048);
                      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar14);
                      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar9,uVar7,uVar10);
                      DAT_1804b46e0 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      unaff_R15 = 0x5056d1f7fbc1aef6;
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    uStack0000000000000050 =
                         (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b46e0,in_stack_000000a0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_00000068 % 0x3f861 ^ 0x50217b0755d55c3c);
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar9);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_000000c8);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar9);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uStack0000000000000050,in_stack_000000a8,
                                       uVar7);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804b46e8 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar10 = FUN_180333500(in_stack_00000048);
                            uVar9 = DAT_1804b59f0;
                            uVar11 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00000314,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar12 = FUN_1800024f0(in_stack_00000048);
                            uVar13 = FUN_180002550(in_stack_00000048);
                            uVar14 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00003ac0,0x29);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar15 = FUN_1800025a0(in_stack_00000048);
                            uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar15);
                            (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                            uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar10,uVar9,
                                              uVar11);
                            DAT_1804b46e8 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                          }
                          uStack0000000000000050 =
                               (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,DAT_1804b46e8,in_stack_000000a0);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,4,in_stack_00000090,0);
                          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,uVar7)
                          ;
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             in_stack_00000788);
                          (*pcVar1)(in_stack_00000048,uVar9,1,uVar7);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             in_stack_00000068 % 0x85f9e ^ unaff_R15);
                          (*pcVar1)(in_stack_00000048,uVar9,2,uVar7);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             in_stack_000000c8);
                          (*pcVar1)(in_stack_00000048,uVar9,3,uVar7);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uStack0000000000000050,
                                             in_stack_000000a8,uVar9);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar6,uVar8,uVar7);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  if (DAT_1804b46f0 == 0) {
                                    lVar2 = *in_stack_00000048;
                                    pcVar1 = *(code **)(lVar2 + 0x390);
                                    uVar9 = FUN_1803335a0(in_stack_00000048);
                                    uVar7 = DAT_1804b59f0;
                                    uVar10 = (**(code **)(lVar2 + 0x518))
                                                       (in_stack_00000048,&stack0x00000312,1);
                                    lVar2 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar2 + 0x390);
                                    uVar11 = FUN_1800024f0(in_stack_00000048);
                                    uVar12 = FUN_180002550(in_stack_00000048);
                                    uVar13 = (**(code **)(lVar2 + 0x518))
                                                       (in_stack_00000048,&stack0x00003810,0x16);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar14 = FUN_1800025a0(in_stack_00000048);
                                    uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar14);
                                    (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                    uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar9,
                                                      uVar7,uVar10);
                                    DAT_1804b46f0 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar7);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar7);
                                  }
                                  uStack0000000000000050 =
                                       (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b46f0,in_stack_000000a0)
                                  ;
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_00000090,0);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_000000b8,
                                                     in_stack_000000c0,0x1019);
                                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar9);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000080,
                                                     in_stack_00000098,
                                                     in_stack_00000068 % 0x5ea5c ^ in_stack_000000c8
                                                     ^ 0xb22ead418baad965);
                                  (*pcVar1)(in_stack_00000048,uVar7,1,uVar9);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uStack0000000000000050,
                                                     in_stack_000000a8,uVar7);
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar5 == '\0') {
                                    (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar6,uVar8,uVar7);
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar5 == '\0') {
                                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar5 == '\0') {
                                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                        if (cVar5 == '\0') {
                                          if (DAT_1804b46f8 == 0) {
                                            lVar2 = *in_stack_00000048;
                                            pcVar1 = *(code **)(lVar2 + 0x390);
                                            uVar7 = FUN_1803335a0(in_stack_00000048);
                                            uVar6 = DAT_1804b59f0;
                                            uVar8 = (**(code **)(lVar2 + 0x518))
                                                              (in_stack_00000048,&stack0x00000310,1)
                                            ;
                                            lVar2 = *in_stack_00000048;
                                            pcVar3 = *(code **)(lVar2 + 0x390);
                                            uVar9 = FUN_1800024f0(in_stack_00000048);
                                            uVar10 = FUN_180002550(in_stack_00000048);
                                            uVar11 = (**(code **)(lVar2 + 0x518))
                                                               (in_stack_00000048,&stack0x000037e0,
                                                                0x16);
                                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                            uVar12 = FUN_1800025a0(in_stack_00000048);
                                            uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,
                                                               uVar12);
                                            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,
                                                              uVar7,uVar6,uVar8);
                                            DAT_1804b46f8 =
                                                 (**(code **)(*in_stack_00000048 + 0xa8))
                                                           (in_stack_00000048,uVar6);
                                            (**(code **)(*in_stack_00000048 + 0xb8))
                                                      (in_stack_00000048,uVar6);
                                          }
                                          uStack0000000000000050 =
                                               (**(code **)(*in_stack_00000048 + 0x110))
                                                         (in_stack_00000048,DAT_1804b46f8,
                                                          in_stack_000000a0);
                                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                            (in_stack_00000048,2,in_stack_00000090,0
                                                            );
                                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                            (in_stack_00000048,in_stack_000000b8,
                                                             in_stack_000000c0,0x4ad3);
                                          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                            (in_stack_00000048,in_stack_00000080,
                                                             in_stack_00000098,
                                                             in_stack_00000068 % 0x4f50d ^
                                                             in_stack_000000c8 ^ 0x8039e9e181631d4a)
                                          ;
                                          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                            (in_stack_00000048,
                                                             uStack0000000000000050,
                                                             in_stack_000000a8,uVar6);
                                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                            (in_stack_00000048);
                                          if (cVar5 == '\0') {
                                            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                            uVar7 = FUN_18001edd0(in_stack_00000048);
                                            (*pcVar1)(in_stack_00000048,in_stack_00000148,uVar7,
                                                      uVar6);
                                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                              (in_stack_00000048);
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
    }
  }
  return;
}

