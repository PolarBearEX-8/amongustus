/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=89 entry=18038c887 blocks=29 maxInstructions=50000 */

/* cut edge: 0x18038c887 -> 0x1803351d6 */
/* cut edge: 0x18038c983 -> 0x18038f3ec */
/* cut edge: 0x18038c9fe -> 0x1803351d6 */
/* cut edge: 0x18038ca3c -> 0x1803351d6 */
/* cut edge: 0x18038ca82 -> 0x1803351d6 */
/* cut edge: 0x18038cabb -> 0x1803351d6 */
/* cut edge: 0x18038caf9 -> 0x1803351d6 */
/* cut edge: 0x18038cca5 -> 0x1803351d6 */
/* cut edge: 0x18038cd7a -> 0x1803351d6 */
/* cut edge: 0x18038cdc3 -> 0x1803351d6 */
/* cut edge: 0x18038cf2f -> 0x1803351d6 */
/* cut edge: 0x18038d011 -> 0x1803351d6 */
/* cut edge: 0x18038d1c7 -> 0x1803351d6 */
/* cut edge: 0x18038d2f4 -> 0x1803351d6 */
/* cut edge: 0x18038d334 -> 0x1803351d6 */
/* cut edge: 0x18038d4d7 -> 0x1803351d6 */
/* cut edge: 0x18038d5ac -> 0x1803351d6 */
/* cut edge: 0x18038d5ec -> 0x1803351d6 */
/* cut edge: 0x18038d78f -> 0x1803351d6 */
/* cut edge: 0x18038d78f -> 0x18038d86a */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18038d86a original byte 0x48 replaced by RET */
/* target 0x18038f3ec original byte 0x48 replaced by RET */


void region_00089_18038c887(undefined8 param_1,undefined8 param_2)

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
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  longlong in_stack_000000a0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000208;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_00000168);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfdeed89a1a8735f);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_00000080,uVar7);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    if (in_stack_000000a0 != 0) {
      return;
    }
    pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
    uVar6 = FUN_18001ec20(in_stack_00000048);
    uVar7 = (*pcVar1)(in_stack_00000048,uVar6);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
      uVar8 = FUN_18001ec80(in_stack_00000048);
      (*pcVar1)(in_stack_00000048,uVar7,uVar6,uVar8);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
        uVar6 = FUN_180010830(in_stack_00000048);
        uVar7 = (*pcVar1)(in_stack_00000048,uVar6);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
          uVar8 = FUN_180010890(in_stack_00000048);
          (*pcVar1)(in_stack_00000048,uVar7,uVar6,uVar8);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b2cc0 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_1803335a0(in_stack_00000048);
                uVar6 = DAT_1804b59f0;
                uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000004be,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003f00,0x16);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar6,uVar9);
                DAT_1804b2cc0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2cc0,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x494f);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8 ^ 0x2819aeb190ad365);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_00000080,uVar8);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                uVar8 = FUN_1800108e0(in_stack_00000048);
                uVar6 = (*pcVar1)(in_stack_00000048,uVar7,uVar8,uVar6);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b2cc8 == 0) {
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar9 = FUN_180333500(in_stack_00000048);
                      uVar7 = DAT_1804b59f0;
                      uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000004bc,1);
                      lVar2 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000048);
                      uVar12 = FUN_180002550(in_stack_00000048);
                      uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000029d0,0xd);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar14 = FUN_1800025a0(in_stack_00000048);
                      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar7,uVar10);
                      DAT_1804b2cc8 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b2cc8,in_stack_00000078);
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
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar7,in_stack_00000080,uVar9);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        if (DAT_1804b2cd0 == 0) {
                          lVar2 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar2 + 0x390);
                          uVar10 = FUN_180333500(in_stack_00000048);
                          uVar9 = DAT_1804b59f0;
                          uVar11 = (**(code **)(lVar2 + 0x518))
                                             (in_stack_00000048,&stack0x000004ba,1);
                          lVar2 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar2 + 0x390);
                          uVar12 = FUN_1800024f0(in_stack_00000048);
                          uVar13 = FUN_180002550(in_stack_00000048);
                          uVar14 = (**(code **)(lVar2 + 0x518))
                                             (in_stack_00000048,&stack0x00005070,0x29);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar15 = FUN_1800025a0(in_stack_00000048);
                          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                          (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                          uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11)
                          ;
                          DAT_1804b2cd0 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                        }
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b2cd0,in_stack_00000078);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                           (in_stack_00000048,4,in_stack_00000088,0);
                        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar10,0,uVar7);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_00000208);
                        (*pcVar1)(in_stack_00000048,uVar10,1,uVar7);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           0xdfb22bab6c698c7f);
                        (*pcVar1)(in_stack_00000048,uVar10,2,uVar7);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_000000c8);
                        (*pcVar1)(in_stack_00000048,uVar10,3,uVar7);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,uVar8,uVar7);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              if (DAT_1804b2cd8 == 0) {
                                lVar2 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar2 + 0x390);
                                uVar9 = FUN_1803335a0(in_stack_00000048);
                                uVar7 = DAT_1804b59f0;
                                uVar10 = (**(code **)(lVar2 + 0x518))
                                                   (in_stack_00000048,&stack0x000004b8,1);
                                lVar2 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar2 + 0x390);
                                uVar11 = FUN_1800024f0(in_stack_00000048);
                                uVar12 = FUN_180002550(in_stack_00000048);
                                uVar13 = (**(code **)(lVar2 + 0x518))
                                                   (in_stack_00000048,&stack0x00003ed0,0x16);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar14 = FUN_1800025a0(in_stack_00000048);
                                uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                                (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar7,
                                                  uVar10);
                                DAT_1804b2cd8 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar7);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                              }
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b2cd8,in_stack_00000078);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                 (in_stack_00000048,in_stack_00000068,
                                                  in_stack_00000060,0x1019);
                              (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                 (in_stack_00000048,in_stack_00000098,
                                                  in_stack_00000090,
                                                  in_stack_000000c8 ^ 0x4dd1571d1c070a6e);
                              (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar7,in_stack_00000080,uVar9);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar6,uVar8,uVar7);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    if (DAT_1804b2ce0 == 0) {
                                      lVar2 = *in_stack_00000048;
                                      pcVar1 = *(code **)(lVar2 + 0x390);
                                      uVar7 = FUN_1803335a0(in_stack_00000048);
                                      uVar6 = DAT_1804b59f0;
                                      uVar8 = (**(code **)(lVar2 + 0x518))
                                                        (in_stack_00000048,&stack0x000004b6,1);
                                      lVar2 = *in_stack_00000048;
                                      pcVar3 = *(code **)(lVar2 + 0x390);
                                      uVar9 = FUN_1800024f0(in_stack_00000048);
                                      uVar10 = FUN_180002550(in_stack_00000048);
                                      uVar11 = (**(code **)(lVar2 + 0x518))
                                                         (in_stack_00000048,&stack0x00003ea0,0x16);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar12 = FUN_1800025a0(in_stack_00000048);
                                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12)
                                      ;
                                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,
                                                        uVar6,uVar8);
                                      DAT_1804b2ce0 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar6);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar6);
                                    }
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,DAT_1804b2ce0,
                                                       in_stack_00000078);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,2,in_stack_00000088,0);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000068,
                                                       in_stack_00000060,0x4ad3);
                                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,
                                                       in_stack_000000c8 ^ 0x7fc613bd16cd50b4);
                                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                    (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
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
  return;
}

