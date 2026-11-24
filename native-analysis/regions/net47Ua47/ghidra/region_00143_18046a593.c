/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=143 entry=18046a593 blocks=38 maxInstructions=50000 */

/* cut edge: 0x18046a593 -> 0x180473ae8 */
/* cut edge: 0x18046a695 -> 0x180473ded */
/* cut edge: 0x18046a6b4 -> 0x180473ae8 */
/* cut edge: 0x18046a6dd -> 0x180473e44 */
/* cut edge: 0x18046a709 -> 0x180473ae8 */
/* cut edge: 0x18046a732 -> 0x180473e7a */
/* cut edge: 0x18046a751 -> 0x180473ae8 */
/* cut edge: 0x18046a775 -> 0x180473ed1 */
/* cut edge: 0x18046a799 -> 0x180473ae8 */
/* cut edge: 0x18046a7bd -> 0x180473ae8 */
/* cut edge: 0x18046a7d3 -> 0x180473ae8 */
/* cut edge: 0x18046a9ae -> 0x180473ae8 */
/* cut edge: 0x18046aa8c -> 0x180473f07 */
/* cut edge: 0x18046aaab -> 0x180473ae8 */
/* cut edge: 0x18046aada -> 0x180473ae8 */
/* cut edge: 0x18046aaf0 -> 0x180473ae8 */
/* cut edge: 0x18046acae -> 0x180473ae8 */
/* cut edge: 0x18046ad97 -> 0x180473ae8 */
/* cut edge: 0x18046adad -> 0x180473ae8 */
/* cut edge: 0x18046af8c -> 0x180473ae8 */
/* cut edge: 0x18046b0bf -> 0x180473ae8 */
/* cut edge: 0x18046b0fa -> 0x180473ae8 */
/* cut edge: 0x18046b110 -> 0x180473ae8 */
/* cut edge: 0x18046b2e1 -> 0x180473ae8 */
/* cut edge: 0x18046b3c5 -> 0x180473ae8 */
/* cut edge: 0x18046b400 -> 0x180473ae8 */
/* cut edge: 0x18046b416 -> 0x180473ae8 */
/* cut edge: 0x18046b42c -> 0x18046b5e0 */
/* cut edge: 0x18046b47e -> 0x18046b5e0 */
/* target 0x18046b5e0 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180473ded original byte 0x48 replaced by RET */
/* target 0x180473e44 original byte 0x48 replaced by RET */
/* target 0x180473e7a original byte 0x48 replaced by RET */
/* target 0x180473ed1 original byte 0x48 replaced by RET */
/* target 0x180473f07 original byte 0x48 replaced by RET */


longlong * region_00143_18046a593(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong lVar9;
  longlong *plVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
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
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x7abe);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  lVar9 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
  plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  lVar2 = DAT_1804b5270;
  if ((char)plVar10 == '\0') {
    lVar9 = lVar9 + (unaff_R14 ^ in_stack_00000068);
    if (DAT_1804b5270 == 0) {
      return in_stack_00000048;
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,DAT_1804b5270);
    plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar10 == '\0') {
      if (DAT_1804b5278 == 0) {
        return in_stack_00000048;
      }
      (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar6,lVar2);
      plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      lVar2 = DAT_1804b5020;
      if ((char)plVar10 == '\0') {
        if (DAT_1804b5020 == 0) {
          return in_stack_00000048;
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,DAT_1804b5020);
        plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)plVar10 == '\0') {
          if (DAT_1804b5028 == 0) {
            return in_stack_00000048;
          }
          (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar6,lVar2);
          plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)plVar10 == '\0') {
            plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)plVar10 == '\0') {
              plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)plVar10 == '\0') {
                if (DAT_1804b47a8 == 0) {
                  lVar2 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar2 + 0x390);
                  uVar8 = FUN_1803335a0(in_stack_00000048);
                  uVar7 = DAT_1804b59f0;
                  uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000002e2,1);
                  lVar2 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar2 + 0x390);
                  uVar12 = FUN_1800024f0(in_stack_00000048);
                  uVar13 = FUN_180002550(in_stack_00000048);
                  uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000036f0,0x16);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar15 = FUN_1800025a0(in_stack_00000048);
                  uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar15);
                  unaff_R15 = 0x5056d1f7fbc1aef6;
                  (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                  uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar8,uVar7,uVar11);
                  DAT_1804b47a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b47a8,in_stack_000000a0);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000090,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0xc10);
                (*pcVar1)(in_stack_00000048,uVar8,0,uVar11);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                    lVar9 % 0xf288b ^ in_stack_000000c8 ^ 0xcf11bf0eb07c3bb9);
                (*pcVar1)(in_stack_00000048,uVar8,1,uVar11);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                lVar2 = DAT_1804b5030;
                if ((char)plVar10 == '\0') {
                  if (DAT_1804b5030 == 0) {
                    return in_stack_00000048;
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,DAT_1804b5030,uVar7);
                  plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                  ;
                  if ((char)plVar10 == '\0') {
                    plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)plVar10 == '\0') {
                      plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)plVar10 == '\0') {
                        if (DAT_1804b47b0 == 0) {
                          lVar5 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar5 + 0x390);
                          uVar8 = FUN_180333500(in_stack_00000048);
                          uVar7 = DAT_1804b59f0;
                          uVar11 = (**(code **)(lVar5 + 0x518))
                                             (in_stack_00000048,&stack0x000002e0,1);
                          lVar5 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar5 + 0x390);
                          uVar12 = FUN_1800024f0(in_stack_00000048);
                          uVar13 = FUN_180002550(in_stack_00000048);
                          uVar14 = (**(code **)(lVar5 + 0x518))
                                             (in_stack_00000048,&stack0x00003070,0xd);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar15 = FUN_1800025a0(in_stack_00000048);
                          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar15);
                          (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar8,uVar7,uVar11);
                          DAT_1804b47b0 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                          unaff_R15 = 0x5056d1f7fbc1aef6;
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                        }
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b47b0,in_stack_000000a0);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                           (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                            lVar9 % 0x23cf1 ^ 0x50217b0755d6ca42);
                        (*pcVar1)(in_stack_00000048,uVar8,0,uVar11);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                           (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                            in_stack_000000c8);
                        (*pcVar1)(in_stack_00000048,uVar8,1,uVar11);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                        plVar10 = (longlong *)
                                  (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)plVar10 == '\0') {
                          plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)plVar10 == '\0') {
                            plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)plVar10 == '\0') {
                              if (DAT_1804b47b8 == 0) {
                                lVar5 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar5 + 0x390);
                                uVar11 = FUN_180333500(in_stack_00000048);
                                uVar8 = DAT_1804b59f0;
                                uVar12 = (**(code **)(lVar5 + 0x518))
                                                   (in_stack_00000048,&stack0x000002de,1);
                                lVar5 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar5 + 0x390);
                                uVar13 = FUN_1800024f0(in_stack_00000048);
                                uVar14 = FUN_180002550(in_stack_00000048);
                                uVar15 = (**(code **)(lVar5 + 0x518))
                                                   (in_stack_00000048,&stack0x00003a60,0x29);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar16 = FUN_1800025a0(in_stack_00000048);
                                uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar16);
                                (*pcVar3)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
                                uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar11,uVar8,
                                                  uVar12);
                                DAT_1804b47b8 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar8);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                              }
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b47b8,in_stack_000000a0);
                              uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                                 (in_stack_00000048,4,in_stack_00000090,0);
                              (**(code **)(*in_stack_00000048 + 0x570))
                                        (in_stack_00000048,uVar11,0,uVar7);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,in_stack_00000788);
                              (*pcVar1)(in_stack_00000048,uVar11,1,uVar7);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 unaff_R15 + 0x5d77eU ^ lVar9 % 0xc56a3);
                              (*pcVar1)(in_stack_00000048,uVar11,2,uVar7);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,in_stack_000000c8);
                              (*pcVar1)(in_stack_00000048,uVar11,3,uVar7);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar8,in_stack_000000a8,uVar11);
                              plVar10 = (longlong *)
                                        (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                              ;
                              if ((char)plVar10 == '\0') {
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar6,lVar2,uVar7);
                                plVar10 = (longlong *)
                                          (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                if ((char)plVar10 == '\0') {
                                  plVar10 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)plVar10 == '\0') {
                                    plVar10 = (longlong *)
                                              (**(code **)(*in_stack_00000048 + 0x720))();
                                    if ((char)plVar10 == '\0') {
                                      if (DAT_1804b47c0 == 0) {
                                        lVar5 = *in_stack_00000048;
                                        pcVar1 = *(code **)(lVar5 + 0x390);
                                        uVar8 = FUN_1803335a0(in_stack_00000048);
                                        uVar7 = DAT_1804b59f0;
                                        uVar11 = (**(code **)(lVar5 + 0x518))
                                                           (in_stack_00000048,&stack0x000002dc,1);
                                        lVar5 = *in_stack_00000048;
                                        pcVar3 = *(code **)(lVar5 + 0x390);
                                        uVar12 = FUN_1800024f0(in_stack_00000048);
                                        uVar13 = FUN_180002550(in_stack_00000048);
                                        uVar14 = (**(code **)(lVar5 + 0x518))
                                                           (in_stack_00000048,&stack0x000036c0,0x16)
                                        ;
                                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar15 = FUN_1800025a0(in_stack_00000048);
                                        uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,
                                                           uVar15);
                                        (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                                        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar8,
                                                          uVar7,uVar11);
                                        DAT_1804b47c0 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar7);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar7);
                                      }
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,DAT_1804b47c0,
                                                         in_stack_000000a0);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,2,in_stack_00000090,0);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                                         (in_stack_00000048,in_stack_000000b8,
                                                          in_stack_000000c0,0x1019);
                                      (*pcVar1)(in_stack_00000048,uVar8,0,uVar11);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                                         (in_stack_00000048,in_stack_00000080,
                                                          in_stack_00000098,
                                                          lVar9 % 0x1411e ^ in_stack_000000c8 ^
                                                          0xb22ead418ba9ed59);
                                      (*pcVar1)(in_stack_00000048,uVar8,1,uVar11);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,uVar7,in_stack_000000a8,
                                                         uVar8);
                                      plVar10 = (longlong *)
                                                (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
                                      if ((char)plVar10 == '\0') {
                                        (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar6,lVar2,uVar7);
                                        plVar10 = (longlong *)
                                                  (**(code **)(*in_stack_00000048 + 0x720))
                                                            (in_stack_00000048);
                                        if ((char)plVar10 == '\0') {
                                          plVar10 = (longlong *)
                                                    (**(code **)(*in_stack_00000048 + 0x720))();
                                          if ((char)plVar10 == '\0') {
                                            plVar10 = (longlong *)
                                                      (**(code **)(*in_stack_00000048 + 0x720))();
                                            if ((char)plVar10 == '\0') {
                                              plVar10 = (longlong *)0x8039e9e181619116;
                                              if (DAT_1804b47c8 == 0) {
                                                lVar2 = *in_stack_00000048;
                                                pcVar1 = *(code **)(lVar2 + 0x390);
                                                uVar7 = FUN_1803335a0(in_stack_00000048);
                                                uVar6 = DAT_1804b59f0;
                                                uVar8 = (**(code **)(lVar2 + 0x518))
                                                                  (in_stack_00000048,
                                                                   &stack0x000002da,1);
                                                lVar2 = *in_stack_00000048;
                                                pcVar3 = *(code **)(lVar2 + 0x390);
                                                uVar11 = FUN_1800024f0(in_stack_00000048);
                                                uVar12 = FUN_180002550(in_stack_00000048);
                                                uVar13 = (**(code **)(lVar2 + 0x518))
                                                                   (in_stack_00000048,
                                                                    &stack0x00003690,0x16);
                                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                                uVar14 = FUN_1800025a0(in_stack_00000048);
                                                uVar14 = (*pcVar4)(in_stack_00000048,
                                                                   in_stack_00000088,uVar14);
                                                (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,
                                                          uVar14);
                                                uVar6 = (*pcVar1)(in_stack_00000048,
                                                                  in_stack_00000088,uVar7,uVar6,
                                                                  uVar8);
                                                DAT_1804b47c8 =
                                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                                               (in_stack_00000048,uVar6);
                                                plVar10 = (longlong *)
                                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                                    (in_stack_00000048,uVar6);
                                              }
                                              return plVar10;
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
  return plVar10;
}

