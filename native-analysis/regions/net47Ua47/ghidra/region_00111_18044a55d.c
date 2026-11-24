/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=111 entry=18044a55d blocks=34 maxInstructions=50000 */

/* cut edge: 0x18044a6af -> 0x180473ae8 */
/* cut edge: 0x18044a7a4 -> 0x180473ae8 */
/* cut edge: 0x18044a7ba -> 0x180473ae8 */
/* cut edge: 0x18044a977 -> 0x180473ae8 */
/* cut edge: 0x18044aa85 -> 0x1804493b1 */
/* cut edge: 0x18044aad5 -> 0x18044b602 */
/* cut edge: 0x18044aadc -> 0x180473ae8 */
/* cut edge: 0x18044aaf7 -> 0x180473ae8 */
/* cut edge: 0x18044acae -> 0x180473ae8 */
/* cut edge: 0x18044ada0 -> 0x180473ae8 */
/* cut edge: 0x18044adb6 -> 0x180473ae8 */
/* cut edge: 0x18044af68 -> 0x180473ae8 */
/* cut edge: 0x18044b05a -> 0x180473ae8 */
/* cut edge: 0x18044b070 -> 0x180473ae8 */
/* cut edge: 0x18044b222 -> 0x180473ae8 */
/* cut edge: 0x18044b317 -> 0x180473ae8 */
/* cut edge: 0x18044b32d -> 0x180473ae8 */
/* cut edge: 0x18044b4f3 -> 0x180473ae8 */
/* cut edge: 0x18044b4f3 -> 0x18044b5e8 */
/* target 0x1804493b1 original byte 0x48 replaced by RET */
/* target 0x18044b5e8 original byte 0x4d replaced by RET */
/* target 0x18044b602 original byte 0x4d replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00111_18044a55d(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  ulonglong uVar13;
  longlong lVar14;
  longlong *in_stack_00000048;
  code *pcStack0000000000000058;
  ulonglong in_stack_00000060;
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
  longlong in_stack_00000118;
  int in_stack_00000130;
  int in_stack_00000140;
  
  lVar14 = *in_stack_00000048;
  pcStack0000000000000058 = *(code **)(lVar14 + 0x390);
  uVar6 = FUN_1803335f0(in_stack_00000048);
  uVar12 = DAT_1804b59f0;
  uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000416,1);
  lVar14 = *in_stack_00000048;
  pcVar1 = *(code **)(lVar14 + 0x390);
  uVar8 = FUN_1800024f0(in_stack_00000048);
  uVar9 = FUN_180002550(in_stack_00000048);
  uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00001510,5);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
  uVar11 = FUN_1800025a0(in_stack_00000048);
  uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar11);
  (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
  uVar12 = (*pcStack0000000000000058)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
  DAT_1804b42e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b42e0,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x38a5);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
  uVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b42e8 == 0) {
          lVar14 = *in_stack_00000048;
          pcStack0000000000000058 = *(code **)(lVar14 + 0x390);
          uVar6 = FUN_1803335f0(in_stack_00000048);
          uVar12 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000414,1);
          lVar14 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar14 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00001500,5);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar11);
          (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*pcStack0000000000000058)
                             (in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
          DAT_1804b42e8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b42e8,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x7c01);
        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           (longlong)in_stack_00000060 % 0x9f934 ^ in_stack_000000c8 ^
                           0x802915f309276814);
        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
        lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))
                           (in_stack_00000048,uVar12,in_stack_000000d8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          lVar14 = (uVar13 ^ in_stack_00000060) - lVar14;
          do {
            if ((in_stack_00000118 != 0) && (in_stack_00000118 == 0)) {
              return;
            }
          } while (in_stack_00000130 < 0);
          if (0 < in_stack_00000140) {
            return;
          }
          pcStack0000000000000058 = (code *)0x0;
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b4310 == 0) {
                lVar3 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar3 + 0x390);
                uVar6 = FUN_1803335f0(in_stack_00000048);
                uVar12 = DAT_1804b59f0;
                uVar7 = (**(code **)(lVar3 + 0x518))(in_stack_00000048,&stack0x0000040a,1);
                lVar3 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar3 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000048);
                uVar9 = FUN_180002550(in_stack_00000048);
                uVar10 = (**(code **)(lVar3 + 0x518))(in_stack_00000048,&stack0x000014b0,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000048);
                uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
                (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
                DAT_1804b4310 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
              }
              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b4310,in_stack_000000a0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2b84);
              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 lVar14 % 0x2a171 ^ in_stack_000000c8 ^ 0xf0b99a1b5bb1ade7);
              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b4318 == 0) {
                      lVar3 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar3 + 0x390);
                      uVar6 = FUN_1803335f0(in_stack_00000048);
                      uVar12 = DAT_1804b59f0;
                      uVar7 = (**(code **)(lVar3 + 0x518))(in_stack_00000048,&stack0x00000408,1);
                      lVar3 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar3 + 0x390);
                      uVar8 = FUN_1800024f0(in_stack_00000048);
                      uVar9 = FUN_180002550(in_stack_00000048);
                      uVar10 = (**(code **)(lVar3 + 0x518))(in_stack_00000048,&stack0x000014a0,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar11 = FUN_1800025a0(in_stack_00000048);
                      uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
                      (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                      uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
                      DAT_1804b4318 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                    }
                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b4318,in_stack_000000a0);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1b96)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       lVar14 % 0xcee48 ^ in_stack_000000c8 ^ 0xa1fbd68f57bd7b6d);
                    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar12,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804b4320 == 0) {
                            lVar3 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar3 + 0x390);
                            uVar6 = FUN_1803335f0(in_stack_00000048);
                            uVar12 = DAT_1804b59f0;
                            uVar7 = (**(code **)(lVar3 + 0x518))
                                              (in_stack_00000048,&stack0x00000406,1);
                            lVar3 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar3 + 0x390);
                            uVar8 = FUN_1800024f0(in_stack_00000048);
                            uVar9 = FUN_180002550(in_stack_00000048);
                            uVar10 = (**(code **)(lVar3 + 0x518))
                                               (in_stack_00000048,&stack0x00001490,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar11 = FUN_1800025a0(in_stack_00000048);
                            uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
                            (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                            uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,
                                               uVar7);
                            DAT_1804b4320 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                          }
                          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804b4320,in_stack_000000a0);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000090,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                             0x1e8d);
                          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             lVar14 % 0x54885 ^ in_stack_000000c8 ^
                                             0xe97a07cd5eed2a4b);
                          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar12,in_stack_000000d8);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                if (DAT_1804b4328 == 0) {
                                  lVar3 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar3 + 0x390);
                                  uVar6 = FUN_1803335f0(in_stack_00000048);
                                  uVar12 = DAT_1804b59f0;
                                  uVar7 = (**(code **)(lVar3 + 0x518))
                                                    (in_stack_00000048,&stack0x00000404,1);
                                  lVar3 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar3 + 0x390);
                                  uVar8 = FUN_1800024f0(in_stack_00000048);
                                  uVar9 = FUN_180002550(in_stack_00000048);
                                  uVar10 = (**(code **)(lVar3 + 0x518))
                                                     (in_stack_00000048,&stack0x00001480,5);
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar11 = FUN_1800025a0(in_stack_00000048);
                                  uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar11);
                                  (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                  uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,
                                                     uVar12,uVar7);
                                  DAT_1804b4328 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar12);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12)
                                  ;
                                }
                                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,DAT_1804b4328,
                                                    in_stack_000000a0);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_00000090,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000b8,
                                                   in_stack_000000c0,0x7b9c);
                                (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000080,
                                                   in_stack_00000098,
                                                   lVar14 % 0xca77f ^ in_stack_000000c8 ^
                                                   0x966cb904879e47a2);
                                (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,uVar12,in_stack_000000a8,uVar6
                                                   );
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar12,in_stack_000000d8);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
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
  return;
}

