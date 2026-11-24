/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=111 entry=1803a288f blocks=33 maxInstructions=50000 */

/* cut edge: 0x1803a288f -> 0x1803351d6 */
/* cut edge: 0x1803a296a -> 0x1803d3ce1 */
/* cut edge: 0x1803a2989 -> 0x1803d3d3a */
/* cut edge: 0x1803a2999 -> 0x1803351d6 */
/* cut edge: 0x1803a29d3 -> 0x1803351d6 */
/* cut edge: 0x1803a2a0e -> 0x1803351d6 */
/* cut edge: 0x1803a2bb0 -> 0x1803351d6 */
/* cut edge: 0x1803a2c85 -> 0x1803351d6 */
/* cut edge: 0x1803a2cc0 -> 0x1803351d6 */
/* cut edge: 0x1803a2e67 -> 0x1803351d6 */
/* cut edge: 0x1803a2f3c -> 0x1803351d6 */
/* cut edge: 0x1803a2f77 -> 0x1803351d6 */
/* cut edge: 0x1803a2fad -> 0x1803351d6 */
/* cut edge: 0x1803a2ff0 -> 0x1803351d6 */
/* cut edge: 0x1803a3026 -> 0x1803351d6 */
/* cut edge: 0x1803a319a -> 0x1803351d6 */
/* cut edge: 0x1803a3282 -> 0x1803351d6 */
/* cut edge: 0x1803a3436 -> 0x1803351d6 */
/* cut edge: 0x1803a3537 -> 0x1803351d6 */
/* cut edge: 0x1803a356d -> 0x1803d3d71 */
/* cut edge: 0x1803a358c -> 0x1803351d6 */
/* cut edge: 0x1803a35b5 -> 0x1803351d6 */
/* cut edge: 0x1803a3767 -> 0x1803351d6 */
/* cut edge: 0x1803a3767 -> 0x1803a38b9 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803a38b9 original byte 0x48 replaced by RET */
/* target 0x1803d3ce1 original byte 0x48 replaced by RET */
/* target 0x1803d3d3a original byte 0x48 replaced by RET */
/* target 0x1803d3d71 original byte 0x48 replaced by RET */


void region_00111_1803a288f(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  longlong lVar6;
  char cVar7;
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
  longlong *in_stack_00000048;
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
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_000001c8;
  
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4ad3);
  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090);
  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,in_stack_00000080,uVar9)
  ;
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  lVar6 = DAT_1804b5298;
  lVar5 = DAT_1804b5290;
  if (cVar7 == '\0') {
    if (DAT_1804b5290 == 0) {
      return;
    }
    if (DAT_1804b5298 == 0) {
      return;
    }
    (**(code **)(*in_stack_00000048 + 0x390))(in_stack_00000048,DAT_1804b5290,DAT_1804b5298,uVar8);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 == '\0') {
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_000000e8);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 == '\0') {
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar7 == '\0') {
          if (DAT_1804b2f58 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1803335a0(in_stack_00000048);
            uVar9 = DAT_1804b59f0;
            uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000040e,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar12 = FUN_1800024f0(in_stack_00000048);
            uVar13 = FUN_180002550(in_stack_00000048);
            uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003960,0x16);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar15 = FUN_1800025a0(in_stack_00000048);
            uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
            (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
            uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11);
            DAT_1804b2f58 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            unaff_R12 = in_stack_000000a8;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2f58,in_stack_00000078);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4b59);
          (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000c8 ^ 0x77f9caee8efb510b);
          (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 == '\0') {
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar8,unaff_R12,uVar9);
            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar7 == '\0') {
              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar7 == '\0') {
                if (DAT_1804b2f60 == 0) {
                  lVar2 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar2 + 0x390);
                  uVar10 = FUN_1803335a0(in_stack_00000048);
                  uVar9 = DAT_1804b59f0;
                  uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000040c,1);
                  lVar2 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar2 + 0x390);
                  uVar12 = FUN_1800024f0(in_stack_00000048);
                  uVar13 = FUN_180002550(in_stack_00000048);
                  uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003930,0x16);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar15 = FUN_1800025a0(in_stack_00000048);
                  uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                  (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                  uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11);
                  DAT_1804b2f60 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                  unaff_R12 = in_stack_000000a8;
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b2f60,in_stack_00000078);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                   (in_stack_00000048,2,in_stack_00000088,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x30dd);
                (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                    in_stack_000000c8 ^ 0x4413de42ddbf2af2);
                (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
                cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar7 == '\0') {
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,lVar5,lVar6,uVar9);
                  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar7 == '\0') {
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar8,unaff_R12,uVar9);
                    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar7 == '\0') {
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x0000040a,1);
                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar7 == '\0') {
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar8,unaff_R12,uVar9);
                        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar7 == '\0') {
                          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar7 == '\0') {
                            if (DAT_1804b2f68 == 0) {
                              lVar5 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar5 + 0x390);
                              uVar10 = FUN_180333500(in_stack_00000048);
                              uVar9 = DAT_1804b59f0;
                              uVar11 = (**(code **)(lVar5 + 0x518))
                                                 (in_stack_00000048,&stack0x00000408,1);
                              lVar5 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar5 + 0x390);
                              uVar12 = FUN_1800024f0(in_stack_00000048);
                              uVar13 = FUN_180002550(in_stack_00000048);
                              uVar14 = (**(code **)(lVar5 + 0x518))
                                                 (in_stack_00000048,&stack0x00002830,0xd);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar15 = FUN_1800025a0(in_stack_00000048);
                              uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                              (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                              uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,
                                                uVar11);
                              DAT_1804b2f68 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                              unaff_R12 = in_stack_000000a8;
                            }
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b2f68,in_stack_00000078);
                            uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                               (in_stack_00000048,2,in_stack_00000088,0);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000098,
                                                in_stack_00000090,0xdfc5815bc278527a);
                            (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000098,
                                                in_stack_00000090,in_stack_000000c8);
                            (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
                            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar7 == '\0') {
                              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar7 == '\0') {
                                if (DAT_1804b2f70 == 0) {
                                  lVar5 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar5 + 0x390);
                                  uVar11 = FUN_180333500(in_stack_00000048);
                                  uVar10 = DAT_1804b59f0;
                                  uVar12 = (**(code **)(lVar5 + 0x518))
                                                     (in_stack_00000048,&stack0x00000406,1);
                                  lVar5 = *in_stack_00000048;
                                  pcVar3 = *(code **)(lVar5 + 0x390);
                                  uVar13 = FUN_1800024f0(in_stack_00000048);
                                  uVar14 = FUN_180002550(in_stack_00000048);
                                  uVar15 = (**(code **)(lVar5 + 0x518))
                                                     (in_stack_00000048,&stack0x000074b0,0x28);
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar16 = FUN_1800025a0(in_stack_00000048);
                                  uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar16);
                                  (*pcVar3)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
                                  uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar11,
                                                     uVar10,uVar12);
                                  DAT_1804b2f70 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar10);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10)
                                  ;
                                  unaff_R12 = in_stack_000000a8;
                                }
                                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,DAT_1804b2f70,
                                                    in_stack_00000078);
                                uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                                   (in_stack_00000048,3,in_stack_00000088,0);
                                (**(code **)(*in_stack_00000048 + 0x570))
                                          (in_stack_00000048,uVar11,0,uVar9);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_00000090,0xdfe95e8d85cdd102);
                                (*pcVar1)(in_stack_00000048,uVar11,1,uVar9);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_00000090,in_stack_000000c8);
                                (*pcVar1)(in_stack_00000048,uVar11,2,uVar9);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar10,in_stack_00000080,uVar11
                                                  );
                                cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar7 == '\0') {
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar8,unaff_R12,uVar9);
                                  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar7 == '\0') {
                                    if (DAT_1804b5040 == 0) {
                                      return;
                                    }
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,uVar8);
                                    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar7 == '\0') {
                                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar7 == '\0') {
                                        if (DAT_1804b2f78 == 0) {
                                          lVar5 = *in_stack_00000048;
                                          pcVar1 = *(code **)(lVar5 + 0x390);
                                          uVar10 = FUN_180333500(in_stack_00000048);
                                          uVar9 = DAT_1804b59f0;
                                          uVar11 = (**(code **)(lVar5 + 0x518))
                                                             (in_stack_00000048,&stack0x00000404,1);
                                          lVar5 = *in_stack_00000048;
                                          pcVar3 = *(code **)(lVar5 + 0x390);
                                          uVar12 = FUN_1800024f0(in_stack_00000048);
                                          uVar13 = FUN_180002550(in_stack_00000048);
                                          uVar14 = (**(code **)(lVar5 + 0x518))
                                                             (in_stack_00000048,&stack0x00005c70,
                                                              0x2a);
                                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar15 = FUN_1800025a0(in_stack_00000048);
                                          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,
                                                             uVar15);
                                          (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                                          uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,
                                                            uVar10,uVar9,uVar11);
                                          DAT_1804b2f78 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar9);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar9);
                                        }
                                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,DAT_1804b2f78,
                                                           in_stack_00000078);
                                        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                                           (in_stack_00000048,5,in_stack_00000088,0)
                                        ;
                                        (**(code **)(*in_stack_00000048 + 0x570))
                                                  (in_stack_00000048,uVar10,0,in_stack_00000070);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                                           (in_stack_00000048,in_stack_00000098,
                                                            in_stack_00000090,in_stack_000001c8);
                                        (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
                                        (**(code **)(*in_stack_00000048 + 0x570))
                                                  (in_stack_00000048,uVar10,2,uVar8);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000098,
                                                           in_stack_00000090,0xdfc0096f727d872c);
                                        (*pcVar1)(in_stack_00000048,uVar10,3,uVar8);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000098,
                                                           in_stack_00000090,in_stack_000000c8);
                                        (*pcVar1)(in_stack_00000048,uVar10,4,uVar8);
                                        (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar9,in_stack_00000080,uVar10)
                                        ;
                                        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
                                        if (cVar7 == '\0') {
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
  return;
}

