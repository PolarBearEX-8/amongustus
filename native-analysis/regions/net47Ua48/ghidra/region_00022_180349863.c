/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=22 entry=180349863 blocks=27 maxInstructions=50000 */

/* cut edge: 0x180349863 -> 0x1803351d6 */
/* cut edge: 0x1803499f7 -> 0x1803351d6 */
/* cut edge: 0x180349ae4 -> 0x1803351d6 */
/* cut edge: 0x180349c91 -> 0x1803351d6 */
/* cut edge: 0x180349d7e -> 0x1803351d6 */
/* cut edge: 0x180349dc1 -> 0x1803351d6 */
/* cut edge: 0x180349f5a -> 0x1803351d6 */
/* cut edge: 0x18034a047 -> 0x1803351d6 */
/* cut edge: 0x18034a086 -> 0x1803351d6 */
/* cut edge: 0x18034a20f -> 0x1803351d6 */
/* cut edge: 0x18034a2fc -> 0x1803351d6 */
/* cut edge: 0x18034a494 -> 0x1803351d6 */
/* cut edge: 0x18034a581 -> 0x1803351d6 */
/* cut edge: 0x18034a5c9 -> 0x1803351d6 */
/* cut edge: 0x18034a75e -> 0x1803351d6 */
/* cut edge: 0x18034a75e -> 0x18034a84b */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18034a84b original byte 0x89 replaced by RET */


void region_00022_180349863(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  byte bVar6;
  uint uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  undefined8 unaff_R12;
  uint uVar17;
  uint unaff_R15D;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000050;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  uint in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_00000158;
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804b2200 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_180333550(in_stack_00000048);
      uVar14 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000076e,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001ee8,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar15 = in_stack_00000058;
      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      unaff_R15D = in_stack_000000b0;
      uVar14 = (*pcVar2)(in_stack_00000048,uVar15,uVar8,uVar14,uVar9);
      DAT_1804b2200 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
    }
    in_stack_00000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2200,in_stack_00000078);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
    (*pcVar2)(in_stack_00000048,uVar14,0,uVar15);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
    (*pcVar2)(in_stack_00000048,uVar14,1,uVar15);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar14);
    bVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar14,in_stack_00000070);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      uVar17 = unaff_R15D >> (bVar6 & 0x1f);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b2208 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_180333550(in_stack_00000048);
          uVar14 = DAT_1804b59f0;
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000076c,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000048);
          uVar11 = FUN_180002550(in_stack_00000048);
          uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001ed8,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar15 = in_stack_00000058;
          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
          unaff_R15D = in_stack_000000b0;
          uVar14 = (*pcVar2)(in_stack_00000048,uVar15,uVar8,uVar14,uVar9);
          DAT_1804b2208 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
        }
        in_stack_00000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2208,in_stack_00000078);
        uVar14 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
        (*pcVar2)(in_stack_00000048,uVar14,0,uVar15);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8)
        ;
        (*pcVar2)(in_stack_00000048,uVar14,1,uVar15);
        uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar14);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar14,in_stack_00000070);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          (**(code **)(*in_stack_00000048 + 0x658))
                    (in_stack_00000048,unaff_R12,uVar7 & uVar17,1,&stack0x00000050);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b2210 == 0) {
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar8 = FUN_180333550(in_stack_00000048);
                uVar14 = DAT_1804b59f0;
                uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000076a,1);
                lVar1 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001ec8,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar15 = in_stack_00000058;
                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                unaff_R15D = in_stack_000000b0;
                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                uVar14 = (*pcVar2)(in_stack_00000048,uVar15,uVar8,uVar14,uVar9);
                DAT_1804b2210 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
              }
              in_stack_00000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2210,in_stack_00000078);
              uVar14 = (**(code **)(*in_stack_00000048 + 0x560))
                                 (in_stack_00000048,2,in_stack_00000088,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
              (*pcVar2)(in_stack_00000048,uVar14,0,uVar15);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                  in_stack_000000e0);
              (*pcVar2)(in_stack_00000048,uVar14,1,uVar15);
              uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar14);
              (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar14,in_stack_00000070);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                uVar14 = (**(code **)(*in_stack_00000048 + 0x488))
                                   (in_stack_00000048,in_stack_00000058,in_stack_000000b8);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b2218 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_180333550(in_stack_00000048);
                      uVar15 = DAT_1804b59f0;
                      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000768,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000048);
                      uVar12 = FUN_180002550(in_stack_00000048);
                      uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001eb8,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar16 = FUN_1800025a0(in_stack_00000048);
                      uVar8 = in_stack_00000058;
                      uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar16);
                      unaff_R15D = in_stack_000000b0;
                      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar16);
                      uVar15 = (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar15,uVar10);
                      DAT_1804b2218 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
                    }
                    in_stack_00000050 =
                         (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b2218,in_stack_00000078);
                    uVar15 = (**(code **)(*in_stack_00000048 + 0x560))
                                       (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1193)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar15,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       in_stack_00000158);
                    (*pcVar2)(in_stack_00000048,uVar15,1,uVar8);
                    uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar15
                                       );
                    bVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                      (in_stack_00000048,uVar15,in_stack_00000070);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        if (DAT_1804b2220 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar9 = FUN_180333550(in_stack_00000048);
                          uVar15 = DAT_1804b59f0;
                          uVar10 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00000766,1);
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar11 = FUN_1800024f0(in_stack_00000048);
                          uVar12 = FUN_180002550(in_stack_00000048);
                          uVar13 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00001ea8,5);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar16 = FUN_1800025a0(in_stack_00000048);
                          uVar8 = in_stack_00000058;
                          uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar16);
                          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar16);
                          uVar15 = (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar15,uVar10);
                          DAT_1804b2220 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
                        }
                        in_stack_00000050 =
                             (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b2220,in_stack_00000078);
                        uVar15 = (**(code **)(*in_stack_00000048 + 0x560))
                                           (in_stack_00000048,2,in_stack_00000088,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                           0x5a30);
                        (*pcVar2)(in_stack_00000048,uVar15,0,uVar8);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_000000a8);
                        (*pcVar2)(in_stack_00000048,uVar15,1,uVar8);
                        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,in_stack_00000050,in_stack_00000080,
                                            uVar15);
                        uVar17 = (**(code **)(*in_stack_00000048 + 0x188))
                                           (in_stack_00000048,uVar15,in_stack_00000070);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          (**(code **)(*in_stack_00000048 + 0x658))
                                    (in_stack_00000048,uVar14,uVar17 & unaff_R15D >> (bVar6 & 0x1f),
                                     1,&stack0x00000050);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              if (DAT_1804b2228 == 0) {
                                lVar1 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar1 + 0x390);
                                uVar8 = FUN_180333550(in_stack_00000048);
                                uVar14 = DAT_1804b59f0;
                                uVar9 = (**(code **)(lVar1 + 0x518))
                                                  (in_stack_00000048,&stack0x00000764,1);
                                lVar1 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar1 + 0x390);
                                uVar10 = FUN_1800024f0(in_stack_00000048);
                                uVar11 = FUN_180002550(in_stack_00000048);
                                uVar12 = (**(code **)(lVar1 + 0x518))
                                                   (in_stack_00000048,&stack0x00001e98,5);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar13 = FUN_1800025a0(in_stack_00000048);
                                uVar15 = in_stack_00000058;
                                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                uVar14 = (*pcVar2)(in_stack_00000048,uVar15,uVar8,uVar14,uVar9);
                                DAT_1804b2228 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar14);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
                              }
                              in_stack_00000050 =
                                   (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804b2228,in_stack_00000078);
                              uVar14 = (**(code **)(*in_stack_00000048 + 0x560))
                                                 (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                                                 (in_stack_00000048,in_stack_00000068,
                                                  in_stack_00000060,0x1193);
                              (*pcVar2)(in_stack_00000048,uVar14,0,uVar15);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                                                 (in_stack_00000048,in_stack_00000098,
                                                  in_stack_00000090,in_stack_00000158);
                              (*pcVar2)(in_stack_00000048,uVar14,1,uVar15);
                              uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,in_stack_00000050,
                                                  in_stack_00000080,uVar14);
                              (**(code **)(*in_stack_00000048 + 0x188))
                                        (in_stack_00000048,uVar14,in_stack_00000070);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
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
  return;
}

