/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=119 entry=180452549 blocks=31 maxInstructions=50000 */

/* cut edge: 0x180452549 -> 0x180473ae8 */
/* cut edge: 0x18045255f -> 0x180473ae8 */
/* cut edge: 0x18045270b -> 0x180473ae8 */
/* cut edge: 0x1804527fd -> 0x180473ae8 */
/* cut edge: 0x180452813 -> 0x180473ae8 */
/* cut edge: 0x1804529bf -> 0x180473ae8 */
/* cut edge: 0x180452ab4 -> 0x180473ae8 */
/* cut edge: 0x180452aca -> 0x180473ae8 */
/* cut edge: 0x180452c83 -> 0x180473ae8 */
/* cut edge: 0x180452d84 -> 0x180473ae8 */
/* cut edge: 0x180452d9f -> 0x180473ae8 */
/* cut edge: 0x180452f6a -> 0x180473ae8 */
/* cut edge: 0x18045305c -> 0x180473ae8 */
/* cut edge: 0x180453072 -> 0x180473ae8 */
/* cut edge: 0x18045322d -> 0x180473ae8 */
/* cut edge: 0x18045331f -> 0x180473ae8 */
/* cut edge: 0x180453335 -> 0x180473ae8 */
/* cut edge: 0x1804534ed -> 0x180473ae8 */
/* cut edge: 0x1804534ed -> 0x1804535e4 */
/* target 0x1804535e4 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00119_180452549(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
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
  ulonglong in_stack_00000068;
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
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b43e8 == 0) {
        lVar14 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar14 + 0x390);
        uVar6 = FUN_1803335f0(in_stack_00000048);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000003d4,1);
        lVar14 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar14 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00001320,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
        DAT_1804b43e8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b43e8,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4324);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)in_stack_00000068 % 0x8fd42 ^ in_stack_000000c8 ^
                         0xdb9600671417e09d);
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
            if (DAT_1804b43f0 == 0) {
              lVar14 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar14 + 0x390);
              uVar6 = FUN_1803335f0(in_stack_00000048);
              uVar12 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000003d2,1);
              lVar14 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar14 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00001310,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
              DAT_1804b43f0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b43f0,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x58ae);
            (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               (longlong)in_stack_00000068 % 0x2d2cd ^ in_stack_000000c8 ^
                               0x91272e8f18b98959);
            (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
            uVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))
                               (in_stack_00000048,uVar12,in_stack_000000d8);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b43f8 == 0) {
                    lVar14 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar14 + 0x390);
                    uVar6 = FUN_1803335f0(in_stack_00000048);
                    uVar12 = DAT_1804b59f0;
                    uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000003d0,1);
                    lVar14 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar14 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00001300,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
                    (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
                    DAT_1804b43f8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                  }
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b43f8,in_stack_000000a0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4c33);
                  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     (longlong)in_stack_00000068 % 0x559de ^ in_stack_000000c8 ^
                                     0xa34c2ce3bbb5814e);
                  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                  lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                     (in_stack_00000048,uVar12,in_stack_000000d8);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    lVar14 = lVar14 + (uVar13 ^ in_stack_00000068);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        if (DAT_1804b4428 == 0) {
                          lVar4 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar4 + 0x390);
                          uVar6 = FUN_1803335f0(in_stack_00000048);
                          uVar12 = DAT_1804b59f0;
                          uVar7 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x000003c4,1)
                          ;
                          lVar4 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar4 + 0x390);
                          uVar8 = FUN_1800024f0(in_stack_00000048);
                          uVar9 = FUN_180002550(in_stack_00000048);
                          uVar10 = (**(code **)(lVar4 + 0x518))
                                             (in_stack_00000048,&stack0x000012b0,5);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar11 = FUN_1800025a0(in_stack_00000048);
                          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
                          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                          uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7)
                          ;
                          DAT_1804b4428 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                        }
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804b4428,in_stack_000000a0);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x7898);
                        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           lVar14 % 0xd32ee ^ in_stack_000000c8 ^ 0xb5ee4668c334f5ba
                                          );
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
                              if (DAT_1804b4430 == 0) {
                                lVar4 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar4 + 0x390);
                                uVar6 = FUN_1803335f0(in_stack_00000048);
                                uVar12 = DAT_1804b59f0;
                                uVar7 = (**(code **)(lVar4 + 0x518))
                                                  (in_stack_00000048,&stack0x000003c2,1);
                                lVar4 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar4 + 0x390);
                                uVar8 = FUN_1800024f0(in_stack_00000048);
                                uVar9 = FUN_180002550(in_stack_00000048);
                                uVar10 = (**(code **)(lVar4 + 0x518))
                                                   (in_stack_00000048,&stack0x000012a0,5);
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar11 = FUN_1800025a0(in_stack_00000048);
                                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
                                (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,
                                                   uVar7);
                                DAT_1804b4430 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar12);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                              }
                              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b4430,in_stack_000000a0)
                              ;
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000090,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000b8,
                                                 in_stack_000000c0,0xdf9);
                              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 lVar14 % 0x6831e ^ in_stack_000000c8 ^
                                                 0xf9144dfed31a597d);
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
                                    if (DAT_1804b4438 == 0) {
                                      lVar4 = *in_stack_00000048;
                                      pcVar1 = *(code **)(lVar4 + 0x390);
                                      uVar6 = FUN_1803335f0(in_stack_00000048);
                                      uVar12 = DAT_1804b59f0;
                                      uVar7 = (**(code **)(lVar4 + 0x518))
                                                        (in_stack_00000048,&stack0x000003c0,1);
                                      lVar4 = *in_stack_00000048;
                                      pcVar2 = *(code **)(lVar4 + 0x390);
                                      uVar8 = FUN_1800024f0(in_stack_00000048);
                                      uVar9 = FUN_180002550(in_stack_00000048);
                                      uVar10 = (**(code **)(lVar4 + 0x518))
                                                         (in_stack_00000048,&stack0x00001290,5);
                                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar11 = FUN_1800025a0(in_stack_00000048);
                                      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11)
                                      ;
                                      (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                      uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,
                                                         uVar12,uVar7);
                                      DAT_1804b4438 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar12);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar12);
                                    }
                                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,DAT_1804b4438,
                                                        in_stack_000000a0);
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,2,in_stack_00000090,0);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_000000b8,
                                                       in_stack_000000c0,0x836);
                                    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000080,
                                                       in_stack_00000098,
                                                       lVar14 % 0x913ad ^ in_stack_000000c8 ^
                                                       0x9d77005c2dd9dfe0);
                                    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,uVar12,in_stack_000000a8,
                                                        uVar6);
                                    (**(code **)(*in_stack_00000048 + 0x1a0))
                                              (in_stack_00000048,uVar12,in_stack_000000d8);
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

