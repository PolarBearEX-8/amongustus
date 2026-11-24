/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=148 entry=18046f5c0 blocks=37 maxInstructions=50000 */

/* cut edge: 0x18046f5c0 -> 0x180473ae8 */
/* cut edge: 0x18046f5d6 -> 0x180473ae8 */
/* cut edge: 0x18046f7a9 -> 0x180473ae8 */
/* cut edge: 0x18046f8ab -> 0x1804741d4 */
/* cut edge: 0x18046f8ca -> 0x180473ae8 */
/* cut edge: 0x18046f8f3 -> 0x18047422b */
/* cut edge: 0x18046f91f -> 0x180473ae8 */
/* cut edge: 0x18046f948 -> 0x180474261 */
/* cut edge: 0x18046f967 -> 0x180473ae8 */
/* cut edge: 0x18046f98b -> 0x1804742b8 */
/* cut edge: 0x18046f9af -> 0x180473ae8 */
/* cut edge: 0x18046f9d3 -> 0x180473ae8 */
/* cut edge: 0x18046f9e9 -> 0x180473ae8 */
/* cut edge: 0x18046fbca -> 0x180473ae8 */
/* cut edge: 0x18046fca8 -> 0x1804742ee */
/* cut edge: 0x18046fcc7 -> 0x180473ae8 */
/* cut edge: 0x18046fcf6 -> 0x180473ae8 */
/* cut edge: 0x18046fd0c -> 0x180473ae8 */
/* cut edge: 0x18046fed4 -> 0x180473ae8 */
/* cut edge: 0x18046ffb8 -> 0x180473ae8 */
/* cut edge: 0x18046ffce -> 0x180473ae8 */
/* cut edge: 0x1804701c6 -> 0x180473ae8 */
/* cut edge: 0x1804702f7 -> 0x180473ae8 */
/* cut edge: 0x180470332 -> 0x180473ae8 */
/* cut edge: 0x180470348 -> 0x180473ae8 */
/* cut edge: 0x180470514 -> 0x180473ae8 */
/* cut edge: 0x180470514 -> 0x1804705f8 */
/* target 0x1804705f8 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x1804741d4 original byte 0x48 replaced by RET */
/* target 0x18047422b original byte 0x48 replaced by RET */
/* target 0x180474261 original byte 0x48 replaced by RET */
/* target 0x1804742b8 original byte 0x48 replaced by RET */
/* target 0x1804742ee original byte 0x48 replaced by RET */


void region_00148_18046f5c0(void)

{
  longlong lVar1;
  code *pcVar2;
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
  longlong lVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  ulonglong unaff_R14;
  longlong unaff_R15;
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
  undefined8 in_stack_00000788;
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      if (DAT_1804b4850 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar7 = FUN_1803335f0(in_stack_00000048);
        uVar13 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002b6,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000d60,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
        unaff_R15 = 0x5056d1f7fbc1aef6;
        (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar13,uVar8);
        DAT_1804b4850 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
      }
      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b4850,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x17e8);
      (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)in_stack_00000068 % 0xc7f11 ^ in_stack_000000c8 ^
                         0x965879e959f513a8);
      (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
      lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar13,in_stack_000000d8)
      ;
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      lVar1 = DAT_1804b5270;
      if (cVar6 == '\0') {
        lVar14 = lVar14 + (unaff_R14 ^ in_stack_00000068);
        if (DAT_1804b5270 == 0) {
          return;
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,DAT_1804b5270);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          if (DAT_1804b5278 == 0) {
            return;
          }
          (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar13,lVar1);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          lVar1 = DAT_1804b5020;
          if (cVar6 == '\0') {
            if (DAT_1804b5020 == 0) {
              return;
            }
            uVar13 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,DAT_1804b5020);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              if (DAT_1804b5028 == 0) {
                return;
              }
              (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar13,lVar1);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar6 == '\0') {
                    if (DAT_1804b4878 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar8 = FUN_1803335a0(in_stack_00000048);
                      uVar7 = DAT_1804b59f0;
                      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002ac,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar10 = FUN_1800024f0(in_stack_00000048);
                      uVar11 = FUN_180002550(in_stack_00000048);
                      uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000035a0,0x16)
                      ;
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar15 = FUN_1800025a0(in_stack_00000048);
                      uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar15);
                      unaff_R15 = 0x5056d1f7fbc1aef6;
                      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar15);
                      uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar7,uVar9);
                      DAT_1804b4878 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b4878,in_stack_000000a0);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2a60)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       lVar14 % 0xd527a ^ in_stack_000000c8 ^ 0xfba95fba5ff343b4);
                    (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    lVar1 = DAT_1804b5030;
                    if (cVar6 == '\0') {
                      if (DAT_1804b5030 == 0) {
                        return;
                      }
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,uVar13,DAT_1804b5030,uVar7);
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar6 == '\0') {
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar6 == '\0') {
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar6 == '\0') {
                            if (DAT_1804b4880 == 0) {
                              lVar5 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar5 + 0x390);
                              uVar8 = FUN_180333500(in_stack_00000048);
                              uVar7 = DAT_1804b59f0;
                              uVar9 = (**(code **)(lVar5 + 0x518))
                                                (in_stack_00000048,&stack0x000002aa,1);
                              lVar5 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar5 + 0x390);
                              uVar10 = FUN_1800024f0(in_stack_00000048);
                              uVar11 = FUN_180002550(in_stack_00000048);
                              uVar12 = (**(code **)(lVar5 + 0x518))
                                                 (in_stack_00000048,&stack0x00003030,0xd);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar15 = FUN_1800025a0(in_stack_00000048);
                              uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar15);
                              (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar15);
                              uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar7,
                                                uVar9);
                              DAT_1804b4880 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7)
                              ;
                              unaff_R15 = 0x5056d1f7fbc1aef6;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                            }
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b4880,in_stack_000000a0);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,2,in_stack_00000090,0);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000080,in_stack_00000098
                                               ,lVar14 % 0xafde0 ^ 0x50217b0755d6626d);
                            (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000080,in_stack_00000098
                                               ,in_stack_000000c8);
                            (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar6 == '\0') {
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar6 == '\0') {
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar6 == '\0') {
                                  if (DAT_1804b4888 == 0) {
                                    lVar5 = *in_stack_00000048;
                                    pcVar2 = *(code **)(lVar5 + 0x390);
                                    uVar9 = FUN_180333500(in_stack_00000048);
                                    uVar8 = DAT_1804b59f0;
                                    uVar10 = (**(code **)(lVar5 + 0x518))
                                                       (in_stack_00000048,&stack0x000002a8,1);
                                    lVar5 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar5 + 0x390);
                                    uVar11 = FUN_1800024f0(in_stack_00000048);
                                    uVar12 = FUN_180002550(in_stack_00000048);
                                    uVar15 = (**(code **)(lVar5 + 0x518))
                                                       (in_stack_00000048,&stack0x00003a00,0x29);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar16 = FUN_1800025a0(in_stack_00000048);
                                    uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar16);
                                    (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar15,uVar16);
                                    uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar9,
                                                      uVar8,uVar10);
                                    DAT_1804b4888 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar8);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar8);
                                  }
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,DAT_1804b4888,
                                                     in_stack_000000a0);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,4,in_stack_00000090,0);
                                  (**(code **)(*in_stack_00000048 + 0x570))
                                            (in_stack_00000048,uVar9,0,uVar7);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000080,
                                                     in_stack_00000098,in_stack_00000788);
                                  (*pcVar2)(in_stack_00000048,uVar9,1,uVar7);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000080,
                                                     in_stack_00000098,
                                                     unaff_R15 + 0xabb4cU ^ lVar14 % 0xc2929);
                                  (*pcVar2)(in_stack_00000048,uVar9,2,uVar7);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000080,
                                                     in_stack_00000098,in_stack_000000c8);
                                  (*pcVar2)(in_stack_00000048,uVar9,3,uVar7);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar8,in_stack_000000a8,uVar9
                                                    );
                                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar6 == '\0') {
                                    (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar13,lVar1,uVar7);
                                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar6 == '\0') {
                                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar6 == '\0') {
                                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                        if (cVar6 == '\0') {
                                          if (DAT_1804b4890 == 0) {
                                            lVar1 = *in_stack_00000048;
                                            pcVar2 = *(code **)(lVar1 + 0x390);
                                            uVar7 = FUN_1803335a0(in_stack_00000048);
                                            uVar13 = DAT_1804b59f0;
                                            uVar8 = (**(code **)(lVar1 + 0x518))
                                                              (in_stack_00000048,&stack0x000002a6,1)
                                            ;
                                            lVar1 = *in_stack_00000048;
                                            pcVar3 = *(code **)(lVar1 + 0x390);
                                            uVar9 = FUN_1800024f0(in_stack_00000048);
                                            uVar10 = FUN_180002550(in_stack_00000048);
                                            uVar11 = (**(code **)(lVar1 + 0x518))
                                                               (in_stack_00000048,&stack0x00003570,
                                                                0x16);
                                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                            uVar12 = FUN_1800025a0(in_stack_00000048);
                                            uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,
                                                               uVar12);
                                            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                            uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000088,
                                                               uVar7,uVar13,uVar8);
                                            DAT_1804b4890 =
                                                 (**(code **)(*in_stack_00000048 + 0xa8))
                                                           (in_stack_00000048,uVar13);
                                            (**(code **)(*in_stack_00000048 + 0xb8))
                                                      (in_stack_00000048,uVar13);
                                          }
                                          uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                             (in_stack_00000048,DAT_1804b4890,
                                                              in_stack_000000a0);
                                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                            (in_stack_00000048,2,in_stack_00000090,0
                                                            );
                                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                            (in_stack_00000048,in_stack_000000b8,
                                                             in_stack_000000c0,0x1019);
                                          (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                            (in_stack_00000048,in_stack_00000080,
                                                             in_stack_00000098,
                                                             lVar14 % 0xcab43 ^ in_stack_000000c8 ^
                                                             0xb22ead418ba2b785);
                                          (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                                          (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar13,in_stack_000000a8,
                                                     uVar7);
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
        }
      }
    }
  }
  return;
}

