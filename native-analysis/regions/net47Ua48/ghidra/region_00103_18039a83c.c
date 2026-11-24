/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=103 entry=18039a83c blocks=35 maxInstructions=50000 */

/* cut edge: 0x18039a83c -> 0x1803351d6 */
/* cut edge: 0x18039a971 -> 0x1803351d6 */
/* cut edge: 0x18039a9ac -> 0x1803351d6 */
/* cut edge: 0x18039ab4f -> 0x1803351d6 */
/* cut edge: 0x18039ac2a -> 0x1803351d6 */
/* cut edge: 0x18039ac65 -> 0x1803351d6 */
/* cut edge: 0x18039ae08 -> 0x1803351d6 */
/* cut edge: 0x18039aee3 -> 0x1803d2150 */
/* cut edge: 0x18039af02 -> 0x1803d2198 */
/* cut edge: 0x18039af12 -> 0x1803351d6 */
/* cut edge: 0x18039af4d -> 0x1803351d6 */
/* cut edge: 0x18039af88 -> 0x1803351d6 */
/* cut edge: 0x18039b12a -> 0x1803351d6 */
/* cut edge: 0x18039b1ff -> 0x1803351d6 */
/* cut edge: 0x18039b23a -> 0x1803351d6 */
/* cut edge: 0x18039b3dc -> 0x1803351d6 */
/* cut edge: 0x18039b4b7 -> 0x1803351d6 */
/* cut edge: 0x18039b4f2 -> 0x1803351d6 */
/* cut edge: 0x18039b528 -> 0x1803351d6 */
/* cut edge: 0x18039b56b -> 0x1803351d6 */
/* cut edge: 0x18039b5a1 -> 0x1803351d6 */
/* cut edge: 0x18039b715 -> 0x1803351d6 */
/* cut edge: 0x18039b7f7 -> 0x1803351d6 */
/* cut edge: 0x18039b80d -> 0x18039b9a6 */
/* cut edge: 0x18039b81d -> 0x18039b9a6 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18039b9a6 original byte 0x48 replaced by RET */
/* target 0x1803d2150 original byte 0x48 replaced by RET */
/* target 0x1803d2198 original byte 0x48 replaced by RET */


void region_00103_18039a83c(undefined8 param_1,undefined8 param_2)

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
  undefined8 unaff_R15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_00000208;
  
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000208);
  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfb22bab6c698c7f);
  (*pcVar1)(in_stack_00000048,uVar9,2,uVar10);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar9,3,uVar10);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,in_stack_00000080,uVar9);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 == '\0') {
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_000000a0);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 == '\0') {
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar7 == '\0') {
        if (DAT_1804b32c0 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1803335a0(in_stack_00000048);
          uVar9 = DAT_1804b59f0;
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000032c,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar12 = FUN_1800024f0(in_stack_00000048);
          uVar13 = FUN_180002550(in_stack_00000048);
          uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000031b0,0x16);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar15 = FUN_1800025a0(in_stack_00000048);
          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
          (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
          uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11);
          DAT_1804b32c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
          unaff_R15 = in_stack_000000a8;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b32c0,in_stack_00000078);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1019);
        (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                            in_stack_000000c8 ^ 0x4dd1571d1c070a6e);
        (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 == '\0') {
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,unaff_R15,uVar9)
          ;
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 == '\0') {
            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar7 == '\0') {
              if (DAT_1804b32c8 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1803335a0(in_stack_00000048);
                uVar9 = DAT_1804b59f0;
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000032a,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar12 = FUN_1800024f0(in_stack_00000048);
                uVar13 = FUN_180002550(in_stack_00000048);
                uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003180,0x16);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar15 = FUN_1800025a0(in_stack_00000048);
                uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11);
                DAT_1804b32c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                unaff_R15 = in_stack_000000a8;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b32c8,in_stack_00000078);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                 (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4ad3);
              (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                  in_stack_000000c8 ^ 0x7fc613bd16cd50b4);
              (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              lVar6 = DAT_1804b5298;
              lVar2 = DAT_1804b5290;
              if (cVar7 == '\0') {
                if (DAT_1804b5290 == 0) {
                  return;
                }
                if (DAT_1804b5298 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,DAT_1804b5290,DAT_1804b5298,uVar9);
                cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar7 == '\0') {
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar8,unaff_R15,uVar9);
                  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar7 == '\0') {
                    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar7 == '\0') {
                      if (DAT_1804b32d0 == 0) {
                        lVar5 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar5 + 0x390);
                        uVar10 = FUN_1803335a0(in_stack_00000048);
                        uVar9 = DAT_1804b59f0;
                        uVar11 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00000328,1);
                        lVar5 = *in_stack_00000048;
                        pcVar3 = *(code **)(lVar5 + 0x390);
                        uVar12 = FUN_1800024f0(in_stack_00000048);
                        uVar13 = FUN_180002550(in_stack_00000048);
                        uVar14 = (**(code **)(lVar5 + 0x518))
                                           (in_stack_00000048,&stack0x00003150,0x16);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar15 = FUN_1800025a0(in_stack_00000048);
                        uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                        (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                        uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11);
                        DAT_1804b32d0 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                        unaff_R15 = in_stack_000000a8;
                      }
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b32d0,in_stack_00000078);
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                         (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                          0x4b59);
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
                                          (in_stack_00000048,uVar8,unaff_R15,uVar9);
                        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar7 == '\0') {
                          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar7 == '\0') {
                            if (DAT_1804b32d8 == 0) {
                              lVar5 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar5 + 0x390);
                              uVar10 = FUN_1803335a0(in_stack_00000048);
                              uVar9 = DAT_1804b59f0;
                              uVar11 = (**(code **)(lVar5 + 0x518))
                                                 (in_stack_00000048,&stack0x00000326,1);
                              lVar5 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar5 + 0x390);
                              uVar12 = FUN_1800024f0(in_stack_00000048);
                              uVar13 = FUN_180002550(in_stack_00000048);
                              uVar14 = (**(code **)(lVar5 + 0x518))
                                                 (in_stack_00000048,&stack0x00003120,0x16);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar15 = FUN_1800025a0(in_stack_00000048);
                              uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                              (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                              uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,
                                                uVar11);
                              DAT_1804b32d8 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                              unaff_R15 = in_stack_000000a8;
                            }
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b32d8,in_stack_00000078);
                            uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                               (in_stack_00000048,2,in_stack_00000088,0);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000068,
                                                in_stack_00000060,0x30dd);
                            (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000098,
                                                in_stack_00000090,
                                                in_stack_000000c8 ^ 0x4413de42ddbf2af2);
                            (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
                            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar7 == '\0') {
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,lVar2,lVar6,uVar9);
                              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar7 == '\0') {
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar8,unaff_R15,uVar9);
                                cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar7 == '\0') {
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x00000324,1);
                                  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar7 == '\0') {
                                    (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar8,unaff_R15,uVar9);
                                    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar7 == '\0') {
                                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar7 == '\0') {
                                        if (DAT_1804b32e0 == 0) {
                                          lVar2 = *in_stack_00000048;
                                          pcVar1 = *(code **)(lVar2 + 0x390);
                                          uVar9 = FUN_180333500(in_stack_00000048);
                                          uVar8 = DAT_1804b59f0;
                                          uVar10 = (**(code **)(lVar2 + 0x518))
                                                             (in_stack_00000048,&stack0x00000322,1);
                                          lVar2 = *in_stack_00000048;
                                          pcVar3 = *(code **)(lVar2 + 0x390);
                                          uVar11 = FUN_1800024f0(in_stack_00000048);
                                          uVar12 = FUN_180002550(in_stack_00000048);
                                          uVar13 = (**(code **)(lVar2 + 0x518))
                                                             (in_stack_00000048,&stack0x00002610,0xd
                                                             );
                                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar14 = FUN_1800025a0(in_stack_00000048);
                                          uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,
                                                             uVar14);
                                          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                          uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,
                                                            uVar9,uVar8,uVar10);
                                          DAT_1804b32e0 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar8);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar8);
                                        }
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,DAT_1804b32e0,
                                                           in_stack_00000078);
                                        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                                          (in_stack_00000048,2,in_stack_00000088,0);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                           (in_stack_00000048,in_stack_00000098,
                                                            in_stack_00000090,0xdfc5815bc278527a);
                                        (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                           (in_stack_00000048,in_stack_00000098,
                                                            in_stack_00000090,in_stack_000000c8);
                                        (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                                        (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
                                        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
                                        if (cVar7 == '\0') {
                                          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                                          if (cVar7 == '\0') {
                                            if (DAT_1804b32e8 == 0) {
                                              lVar2 = *in_stack_00000048;
                                              pcVar1 = *(code **)(lVar2 + 0x390);
                                              uVar9 = FUN_180333500(in_stack_00000048);
                                              uVar8 = DAT_1804b59f0;
                                              uVar10 = (**(code **)(lVar2 + 0x518))
                                                                 (in_stack_00000048,&stack0x00000320
                                                                  ,1);
                                              lVar2 = *in_stack_00000048;
                                              pcVar3 = *(code **)(lVar2 + 0x390);
                                              uVar11 = FUN_1800024f0(in_stack_00000048);
                                              uVar12 = FUN_180002550(in_stack_00000048);
                                              uVar13 = (**(code **)(lVar2 + 0x518))
                                                                 (in_stack_00000048,&stack0x00007280
                                                                  ,0x28);
                                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                              uVar14 = FUN_1800025a0(in_stack_00000048);
                                              uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058
                                                                 ,uVar14);
                                              (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,
                                                        uVar14);
                                              uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,
                                                                uVar9,uVar8,uVar10);
                                              DAT_1804b32e8 =
                                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                                             (in_stack_00000048,uVar8);
                                              (**(code **)(*in_stack_00000048 + 0xb8))
                                                        (in_stack_00000048,uVar8);
                                            }
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

