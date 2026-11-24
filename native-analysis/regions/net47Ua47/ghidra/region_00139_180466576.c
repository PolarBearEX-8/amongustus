/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=139 entry=180466576 blocks=33 maxInstructions=50000 */

/* cut edge: 0x180466576 -> 0x180473ae8 */
/* cut edge: 0x1804665b1 -> 0x180473ae8 */
/* cut edge: 0x1804665c7 -> 0x180473ae8 */
/* cut edge: 0x180466790 -> 0x180473ae8 */
/* cut edge: 0x18046686e -> 0x180473ae8 */
/* cut edge: 0x1804668a9 -> 0x180473ae8 */
/* cut edge: 0x1804668bf -> 0x180473ae8 */
/* cut edge: 0x180466a90 -> 0x180473ae8 */
/* cut edge: 0x180466b6e -> 0x180473ae8 */
/* cut edge: 0x180466ba9 -> 0x180473ae8 */
/* cut edge: 0x180466bdf -> 0x180473ae8 */
/* cut edge: 0x180466c22 -> 0x180473ae8 */
/* cut edge: 0x180466c58 -> 0x180473ae8 */
/* cut edge: 0x180466c6e -> 0x180473ae8 */
/* cut edge: 0x180466e22 -> 0x180473ae8 */
/* cut edge: 0x180466f0b -> 0x180473ae8 */
/* cut edge: 0x180466f21 -> 0x180473ae8 */
/* cut edge: 0x180467107 -> 0x180473ae8 */
/* cut edge: 0x180467202 -> 0x180473ae8 */
/* cut edge: 0x180467238 -> 0x180473ae8 */
/* cut edge: 0x180467273 -> 0x180473ae8 */
/* cut edge: 0x180467296 -> 0x180473ae8 */
/* cut edge: 0x18046748b -> 0x180473ae8 */
/* cut edge: 0x18046748b -> 0x1804675da */
/* target 0x1804675da original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00139_180466576(void)

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
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 unaff_R12;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
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
  undefined8 in_stack_000000f0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00000148;
  undefined8 in_stack_00000738;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_000000f8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b4700 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_1803335a0(in_stack_00000048);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000030e,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000037b0,0x16);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,uVar8);
          DAT_1804b4700 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
          unaff_R12 = in_stack_00000058;
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b4700,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4b59);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0xd0b89 ^ in_stack_000000c8 ^ 0x880630b21950cee2);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar6,unaff_R12,uVar13);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                if (DAT_1804b4708 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_1803335a0(in_stack_00000048);
                  uVar13 = DAT_1804b59f0;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000030c,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000048);
                  uVar10 = FUN_180002550(in_stack_00000048);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003780,0x16);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000048);
                  uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                  (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,uVar8);
                  DAT_1804b4708 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                  unaff_R12 = in_stack_00000058;
                }
                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b4708,in_stack_000000a0);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x30dd);
                (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                   in_stack_00000068 % 0x8458f ^ in_stack_000000c8 ^
                                   0xbbec241e4a153a67);
                (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  uVar13 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000148,in_stack_000000f0,uVar13);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,unaff_R12,uVar13);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x0000030a,1);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar6,unaff_R12,uVar13);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              if (DAT_1804b4710 == 0) {
                                lVar1 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar1 + 0x390);
                                uVar7 = FUN_180333500(in_stack_00000048);
                                uVar13 = DAT_1804b59f0;
                                uVar8 = (**(code **)(lVar1 + 0x518))
                                                  (in_stack_00000048,&stack0x00000308,1);
                                lVar1 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar1 + 0x390);
                                uVar9 = FUN_1800024f0(in_stack_00000048);
                                uVar10 = FUN_180002550(in_stack_00000048);
                                uVar11 = (**(code **)(lVar1 + 0x518))
                                                   (in_stack_00000048,&stack0x00003090,0xd);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar12 = FUN_1800025a0(in_stack_00000048);
                                uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,
                                                   uVar8);
                                DAT_1804b4710 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar13);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                                unaff_R12 = in_stack_00000058;
                              }
                              uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b4710,in_stack_000000a0)
                              ;
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000090,0);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 in_stack_00000068 % 0xc25b6 ^ 0x50217b0755d5c513);
                              (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,in_stack_000000c8);
                              (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                              uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    if (DAT_1804b4718 == 0) {
                                      lVar1 = *in_stack_00000048;
                                      pcVar2 = *(code **)(lVar1 + 0x390);
                                      uVar8 = FUN_180333500(in_stack_00000048);
                                      uVar7 = DAT_1804b59f0;
                                      uVar9 = (**(code **)(lVar1 + 0x518))
                                                        (in_stack_00000048,&stack0x00000306,1);
                                      lVar1 = *in_stack_00000048;
                                      pcVar3 = *(code **)(lVar1 + 0x390);
                                      uVar10 = FUN_1800024f0(in_stack_00000048);
                                      uVar11 = FUN_180002550(in_stack_00000048);
                                      uVar12 = (**(code **)(lVar1 + 0x518))
                                                         (in_stack_00000048,&stack0x00004130,0x28);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar14 = FUN_1800025a0(in_stack_00000048);
                                      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar14)
                                      ;
                                      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar14);
                                      uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,
                                                        uVar7,uVar9);
                                      DAT_1804b4718 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar7);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar7);
                                      unaff_R12 = in_stack_00000058;
                                    }
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,DAT_1804b4718,
                                                       in_stack_000000a0);
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,3,in_stack_00000090,0);
                                    (**(code **)(*in_stack_00000048 + 0x570))
                                              (in_stack_00000048,uVar8,0,uVar13);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar13 = (**(code **)(*in_stack_00000048 + 0x390))
                                                       (in_stack_00000048,in_stack_00000080,
                                                        in_stack_00000098,
                                                        in_stack_00000068 % 0x684b1 ^
                                                        0x500da4d11261c7bd);
                                    (*pcVar2)(in_stack_00000048,uVar8,1,uVar13);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar13 = (**(code **)(*in_stack_00000048 + 0x390))
                                                       (in_stack_00000048,in_stack_00000080,
                                                        in_stack_00000098,in_stack_000000c8);
                                    (*pcVar2)(in_stack_00000048,uVar8,2,uVar13);
                                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,uVar7,in_stack_000000a8,
                                                        uVar8);
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar5 == '\0') {
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,uVar6,unaff_R12,uVar13);
                                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                                      if (cVar5 == '\0') {
                                        pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar13 = FUN_180010980(in_stack_00000048);
                                        uVar6 = (*pcVar2)(in_stack_00000048,uVar6,uVar13);
                                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
                                        if (cVar5 == '\0') {
                                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                          if (cVar5 == '\0') {
                                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                            if (cVar5 == '\0') {
                                              if (DAT_1804b4720 == 0) {
                                                lVar1 = *in_stack_00000048;
                                                pcVar2 = *(code **)(lVar1 + 0x390);
                                                uVar7 = FUN_180333500(in_stack_00000048);
                                                uVar13 = DAT_1804b59f0;
                                                uVar8 = (**(code **)(lVar1 + 0x518))
                                                                  (in_stack_00000048,
                                                                   &stack0x00000304,1);
                                                lVar1 = *in_stack_00000048;
                                                pcVar3 = *(code **)(lVar1 + 0x390);
                                                uVar9 = FUN_1800024f0(in_stack_00000048);
                                                uVar10 = FUN_180002550(in_stack_00000048);
                                                uVar11 = (**(code **)(lVar1 + 0x518))
                                                                   (in_stack_00000048,
                                                                    &stack0x00003d60,0x2a);
                                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                                uVar12 = FUN_1800025a0(in_stack_00000048);
                                                uVar12 = (*pcVar4)(in_stack_00000048,
                                                                   in_stack_00000088,uVar12);
                                                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,
                                                          uVar12);
                                                uVar13 = (*pcVar2)(in_stack_00000048,
                                                                   in_stack_00000088,uVar7,uVar13,
                                                                   uVar8);
                                                DAT_1804b4720 =
                                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                                               (in_stack_00000048,uVar13);
                                                (**(code **)(*in_stack_00000048 + 0xb8))
                                                          (in_stack_00000048,uVar13);
                                              }
                                              uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                                 (in_stack_00000048,DAT_1804b4720,
                                                                  in_stack_000000a0);
                                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                                (in_stack_00000048,5,
                                                                 in_stack_00000090,0);
                                              (**(code **)(*in_stack_00000048 + 0x570))
                                                        (in_stack_00000048,uVar7,0,in_stack_000000e8
                                                        );
                                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                                (in_stack_00000048,in_stack_00000080
                                                                 ,in_stack_00000098,
                                                                 in_stack_00000738);
                                              (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                                              (**(code **)(*in_stack_00000048 + 0x570))
                                                        (in_stack_00000048,uVar7,2,uVar6);
                                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                                (in_stack_00000048,in_stack_00000080
                                                                 ,in_stack_00000098,
                                                                 in_stack_00000068 % 0x7c07a ^
                                                                 0x5024f333e5d351b1);
                                              (*pcVar2)(in_stack_00000048,uVar7,3,uVar6);
                                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                                (in_stack_00000048,in_stack_00000080
                                                                 ,in_stack_00000098,
                                                                 in_stack_000000c8);
                                              (*pcVar2)(in_stack_00000048,uVar7,4,uVar6);
                                              (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,uVar13,in_stack_000000a8,
                                                         uVar7);
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
  }
  return;
}

