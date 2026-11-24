/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=126 entry=180459547 blocks=29 maxInstructions=50000 */

/* cut edge: 0x180459547 -> 0x180473ae8 */
/* cut edge: 0x180459712 -> 0x180473ae8 */
/* cut edge: 0x180459854 -> 0x180473ae8 */
/* cut edge: 0x18045986a -> 0x180473ae8 */
/* cut edge: 0x180459a50 -> 0x180473ae8 */
/* cut edge: 0x180459b44 -> 0x180473ae8 */
/* cut edge: 0x180459b87 -> 0x180473ae8 */
/* cut edge: 0x180459b9d -> 0x180473ae8 */
/* cut edge: 0x180459dc2 -> 0x180473ae8 */
/* cut edge: 0x180459f02 -> 0x180473bbb */
/* cut edge: 0x180459f29 -> 0x180473ae8 */
/* cut edge: 0x180459f57 -> 0x180473c24 */
/* cut edge: 0x180459f83 -> 0x180473ae8 */
/* cut edge: 0x180459fce -> 0x180473ae8 */
/* cut edge: 0x180459fe4 -> 0x180473ae8 */
/* cut edge: 0x18045a1aa -> 0x180473ae8 */
/* cut edge: 0x18045a2ec -> 0x180473ae8 */
/* cut edge: 0x18045a302 -> 0x180473ae8 */
/* cut edge: 0x18045a4d6 -> 0x180473ae8 */
/* cut edge: 0x18045a4d6 -> 0x18045a5cf */
/* target 0x18045a5cf original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180473bbb original byte 0x48 replaced by RET */
/* target 0x180473c24 original byte 0x48 replaced by RET */


void region_00126_180459547(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  char cVar6;
  uint uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  ulonglong unaff_R12;
  uint unaff_R13D;
  ulonglong uVar16;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  uint in_stack_000000e8;
  uint in_stack_000000f8;
  undefined8 in_stack_00000120;
  undefined8 in_stack_000001b8;
  undefined8 in_stack_000007a8;
  undefined8 in_stack_000007b0;
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    uVar16 = in_stack_00000068 % 0xe888b ^ unaff_R12;
    if (DAT_1804b4528 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_180333500(in_stack_00000048);
      uVar14 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000384,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003e80,0x18);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      unaff_R12 = 0x5035e9d91624950a;
      uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar14,uVar9);
      DAT_1804b4528 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
    }
    uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b4528,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000090,0);
    (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000058);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000001b8);
    (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,uVar16);
    (*pcVar2)(in_stack_00000048,uVar8,2,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000000c8);
    (*pcVar2)(in_stack_00000048,uVar8,3,uVar9);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar14,in_stack_000000a8,uVar8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x140))(in_stack_00000048,uVar14,in_stack_000000b0);
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          if (DAT_1804b4530 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_180333550(in_stack_00000048);
            uVar14 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000382,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000010f0,5);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
            unaff_R12 = 0x5035e9d91624950a;
            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar14,uVar9);
            DAT_1804b4530 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
          }
          uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b4530,in_stack_000000a0);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5a30);
          (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                             in_stack_00000068 % 0x125bd ^ in_stack_000000c8 ^ 0xfd1683012c90a4d3);
          (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
          uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar14,in_stack_000000a8,uVar8);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar14,in_stack_00000120);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            uVar14 = (**(code **)(*in_stack_00000048 + 0x580))
                               (in_stack_00000048,
                                uVar7 & (int)cVar5 |
                                in_stack_000000f8 | in_stack_000000e8 | unaff_R13D);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b4538 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar9 = FUN_180333500(in_stack_00000048);
                    uVar8 = DAT_1804b59f0;
                    uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000380,1);
                    lVar1 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar11 = FUN_1800024f0(in_stack_00000048);
                    uVar12 = FUN_180002550(in_stack_00000048);
                    uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004020,0x33);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar15 = FUN_1800025a0(in_stack_00000048);
                    uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar15);
                    (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar15);
                    uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar9,uVar8,uVar10);
                    DAT_1804b4538 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                    unaff_R12 = 0x5035e9d91624950a;
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                  }
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b4538,in_stack_000000a0);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,5,in_stack_00000090,0);
                  (**(code **)(*in_stack_00000048 + 0x570))
                            (in_stack_00000048,uVar9,0,in_stack_00000058);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                      in_stack_000007b0);
                  (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
                  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,2,uVar14);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                      in_stack_00000068 % 0x4cc31 ^ 0x502c6d1e2ec79a93);
                  (*pcVar2)(in_stack_00000048,uVar9,3,uVar14);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                      in_stack_000000c8);
                  (*pcVar2)(in_stack_00000048,uVar9,4,uVar14);
                  (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  lVar1 = DAT_1804b5318;
                  if (cVar5 == '\0') {
                    if (DAT_1804b5318 == 0) {
                      return;
                    }
                    uVar14 = (**(code **)(*in_stack_00000048 + 0xd8))
                                       (in_stack_00000048,DAT_1804b5318);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      if (DAT_1804b5aa8 == 0) {
                        return;
                      }
                      (**(code **)(*in_stack_00000048 + 0x2d8))
                                (in_stack_00000048,uVar14,lVar1,DAT_1804b5aa8,in_stack_000007a8);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar5 == '\0') {
                            if (DAT_1804b4540 == 0) {
                              lVar1 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar1 + 0x390);
                              uVar8 = FUN_180333500(in_stack_00000048);
                              uVar14 = DAT_1804b59f0;
                              uVar9 = (**(code **)(lVar1 + 0x518))
                                                (in_stack_00000048,&stack0x0000037e,1);
                              lVar1 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar1 + 0x390);
                              uVar10 = FUN_1800024f0(in_stack_00000048);
                              uVar11 = FUN_180002550(in_stack_00000048);
                              uVar12 = (**(code **)(lVar1 + 0x518))
                                                 (in_stack_00000048,&stack0x00003e50,0x18);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar13 = FUN_1800025a0(in_stack_00000048);
                              uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
                              (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                              uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar14,
                                                 uVar9);
                              DAT_1804b4540 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                             (in_stack_00000048,uVar14);
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
                            }
                            uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,DAT_1804b4540,in_stack_000000a0);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,4,in_stack_00000090,0);
                            (**(code **)(*in_stack_00000048 + 0x570))
                                      (in_stack_00000048,uVar8,0,in_stack_00000058);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000080,in_stack_00000098
                                               ,in_stack_000001b8);
                            (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000080,in_stack_00000098
                                               ,unaff_R12 + 0xb4a4d ^ in_stack_00000068 % 0x200c9);
                            (*pcVar2)(in_stack_00000048,uVar8,2,uVar9);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000080,in_stack_00000098
                                               ,in_stack_000000c8);
                            (*pcVar2)(in_stack_00000048,uVar8,3,uVar9);
                            uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,uVar14,in_stack_000000a8,uVar8);
                            (**(code **)(*in_stack_00000048 + 0x140))
                                      (in_stack_00000048,uVar14,in_stack_000000b0);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  if (DAT_1804b4548 == 0) {
                                    lVar1 = *in_stack_00000048;
                                    pcVar2 = *(code **)(lVar1 + 0x390);
                                    uVar8 = FUN_180333550(in_stack_00000048);
                                    uVar14 = DAT_1804b59f0;
                                    uVar9 = (**(code **)(lVar1 + 0x518))
                                                      (in_stack_00000048,&stack0x0000037c,1);
                                    lVar1 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar1 + 0x390);
                                    uVar10 = FUN_1800024f0(in_stack_00000048);
                                    uVar11 = FUN_180002550(in_stack_00000048);
                                    uVar12 = (**(code **)(lVar1 + 0x518))
                                                       (in_stack_00000048,&stack0x000010e0,5);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar13 = FUN_1800025a0(in_stack_00000048);
                                    uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
                                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                    uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,
                                                       uVar14,uVar9);
                                    DAT_1804b4548 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar14);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar14);
                                  }
                                  uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,DAT_1804b4548,
                                                      in_stack_000000a0);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_00000090,0);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_000000b8,
                                                     in_stack_000000c0,0x5a30);
                                  (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000080,
                                                     in_stack_00000098,
                                                     in_stack_00000068 % 0x1e93d ^ in_stack_000000c8
                                                     ^ 0xfd1683012c917109);
                                  (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                                  uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,uVar14,in_stack_000000a8,
                                                      uVar8);
                                  (**(code **)(*in_stack_00000048 + 0x188))
                                            (in_stack_00000048,uVar14,in_stack_00000120);
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
  return;
}

