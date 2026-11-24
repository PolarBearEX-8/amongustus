/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=265 entry=180128232 blocks=40 maxInstructions=100000 */

/* cut edge: 0x180128232 -> 0x18014d3db */
/* cut edge: 0x180128263 -> 0x18014d3db */
/* cut edge: 0x180128279 -> 0x18014d3db */
/* cut edge: 0x180128457 -> 0x18014d3db */
/* cut edge: 0x180128535 -> 0x18014d3db */
/* cut edge: 0x180128566 -> 0x18014d3db */
/* cut edge: 0x18012857c -> 0x18014d3db */
/* cut edge: 0x180128757 -> 0x18014d3db */
/* cut edge: 0x180128835 -> 0x18013d64e */
/* cut edge: 0x180128854 -> 0x18013d696 */
/* cut edge: 0x180128864 -> 0x18014d3db */
/* cut edge: 0x180128898 -> 0x18014d3db */
/* cut edge: 0x1801288c9 -> 0x18014d3db */
/* cut edge: 0x1801288df -> 0x18014d3db */
/* cut edge: 0x180128ac8 -> 0x18014d3db */
/* cut edge: 0x180128ba6 -> 0x18014d3db */
/* cut edge: 0x180128bd7 -> 0x18014d3db */
/* cut edge: 0x180128bed -> 0x18014d3db */
/* cut edge: 0x180128ddc -> 0x18014d3db */
/* cut edge: 0x180128eba -> 0x18014d3db */
/* cut edge: 0x180128eef -> 0x18014d3db */
/* cut edge: 0x180128f20 -> 0x18014d3db */
/* cut edge: 0x180128f63 -> 0x18014d3db */
/* cut edge: 0x180128f94 -> 0x18013d6cd */
/* cut edge: 0x180128fb3 -> 0x18014d3db */
/* cut edge: 0x180128fdc -> 0x18014d3db */
/* cut edge: 0x18012900d -> 0x18013d703 */
/* cut edge: 0x18012902c -> 0x18014d3db */
/* cut edge: 0x180129050 -> 0x18014d3db */
/* cut edge: 0x180129073 -> 0x18014d3db */
/* cut edge: 0x180129089 -> 0x180129260 */
/* cut edge: 0x1801290d3 -> 0x180129260 */
/* target 0x180129260 original byte 0x48 replaced by RET */
/* target 0x18013d64e original byte 0x48 replaced by RET */
/* target 0x18013d696 original byte 0x48 replaced by RET */
/* target 0x18013d6cd original byte 0x48 replaced by RET */
/* target 0x18013d703 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


longlong * region_00265_180128232(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  longlong lVar6;
  undefined8 uVar7;
  longlong *plVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 unaff_R13;
  longlong *in_stack_00000048;
  longlong in_stack_00000058;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_00000138;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048);
  plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)plVar8 == '\0') {
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar8 == '\0') {
      plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)plVar8 == '\0') {
        if (DAT_1804ac950 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_18001ed20(in_stack_00000048);
          uVar15 = DAT_1804b5208;
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000009ee,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00006d30,0x16);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar9,uVar15,uVar10);
          DAT_1804ac950 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
          unaff_R13 = in_stack_00000080;
        }
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac950,in_stack_000000b0);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5a14);
        (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                            in_stack_00000058 % 0x74d81 ^ in_stack_000000c0 ^ 0xd9d75de4373e73bb);
        (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar15,in_stack_000000a8,uVar9);
        plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)plVar8 == '\0') {
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar7,unaff_R13,uVar15);
          plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)plVar8 == '\0') {
            plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)plVar8 == '\0') {
              plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)plVar8 == '\0') {
                if (DAT_1804ac958 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_18001ed20(in_stack_00000048);
                  uVar15 = DAT_1804b5208;
                  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000009ec,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar11 = FUN_1800024f0(in_stack_00000048);
                  uVar12 = FUN_180002550(in_stack_00000048);
                  uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00006d00,0x16);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar14 = FUN_1800025a0(in_stack_00000048);
                  uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
                  (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                  uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar9,uVar15,uVar10);
                  DAT_1804ac958 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
                  unaff_R13 = in_stack_00000080;
                }
                uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804ac958,in_stack_000000b0);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_000000a0,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6d7b);
                (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                    in_stack_00000058 % 0xce834 ^ in_stack_000000c0 ^
                                    0x91bbcfa039aceb36);
                (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
                uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar15,in_stack_000000a8,uVar9);
                plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                lVar6 = DAT_1804b5298;
                lVar1 = DAT_1804b5290;
                if ((char)plVar8 == '\0') {
                  if (DAT_1804b5290 == 0) {
                    return in_stack_00000048;
                  }
                  if (DAT_1804b5298 == 0) {
                    return (longlong *)0x0;
                  }
                  uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,DAT_1804b5290,DAT_1804b5298,uVar15);
                  plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)plVar8 == '\0') {
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar7,unaff_R13,uVar15);
                    plVar8 = (longlong *)
                             (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)plVar8 == '\0') {
                      plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)plVar8 == '\0') {
                        plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)plVar8 == '\0') {
                          if (DAT_1804ac960 == 0) {
                            lVar5 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar5 + 0x390);
                            uVar9 = FUN_18001ed20(in_stack_00000048);
                            uVar15 = DAT_1804b5208;
                            uVar10 = (**(code **)(lVar5 + 0x518))
                                               (in_stack_00000048,&stack0x000009ea,1);
                            lVar5 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar5 + 0x390);
                            uVar11 = FUN_1800024f0(in_stack_00000048);
                            uVar12 = FUN_180002550(in_stack_00000048);
                            uVar13 = (**(code **)(lVar5 + 0x518))
                                               (in_stack_00000048,&stack0x00006cd0,0x16);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar14 = FUN_1800025a0(in_stack_00000048);
                            uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
                            (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                            uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar9,uVar15,
                                               uVar10);
                            DAT_1804ac960 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
                            unaff_R13 = in_stack_00000080;
                          }
                          uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804ac960,in_stack_000000b0);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                              0x3af8);
                          (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                              in_stack_00000058 % 0x11b7e ^ in_stack_000000c0 ^
                                              0xbf12e7425eb78649);
                          (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
                          uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,uVar15,in_stack_000000a8,uVar9);
                          plVar8 = (longlong *)
                                   (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)plVar8 == '\0') {
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar7,unaff_R13,uVar15);
                            plVar8 = (longlong *)
                                     (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)plVar8 == '\0') {
                              plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)plVar8 == '\0') {
                                plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)plVar8 == '\0') {
                                  if (DAT_1804ac968 == 0) {
                                    lVar5 = *in_stack_00000048;
                                    pcVar2 = *(code **)(lVar5 + 0x390);
                                    uVar9 = FUN_18001ed20(in_stack_00000048);
                                    uVar15 = DAT_1804b5208;
                                    uVar10 = (**(code **)(lVar5 + 0x518))
                                                       (in_stack_00000048,&stack0x000009e8,1);
                                    lVar5 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar5 + 0x390);
                                    uVar11 = FUN_1800024f0(in_stack_00000048);
                                    uVar12 = FUN_180002550(in_stack_00000048);
                                    uVar13 = (**(code **)(lVar5 + 0x518))
                                                       (in_stack_00000048,&stack0x00006ca0,0x16);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar14 = FUN_1800025a0(in_stack_00000048);
                                    uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
                                    (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                    uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar9,
                                                       uVar15,uVar10);
                                    DAT_1804ac968 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar15);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar15);
                                    unaff_R13 = in_stack_00000080;
                                  }
                                  uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,DAT_1804ac968,
                                                      in_stack_000000b0);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_000000a0,0);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                     (in_stack_00000048,in_stack_000000c8,
                                                      in_stack_000000d0,0x5eff);
                                  (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                     (in_stack_00000048,in_stack_00000098,
                                                      in_stack_000000b8,
                                                      in_stack_00000058 % 0xdb01a ^
                                                      in_stack_000000c0 ^ 0xf723560f841cdd86);
                                  (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
                                  uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,uVar15,in_stack_000000a8,
                                                      uVar9);
                                  plVar8 = (longlong *)
                                           (**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                                  if ((char)plVar8 == '\0') {
                                    uVar15 = (**(code **)(*in_stack_00000048 + 0x390))
                                                       (in_stack_00000048,lVar1,lVar6,uVar15);
                                    plVar8 = (longlong *)
                                             (**(code **)(*in_stack_00000048 + 0x720))
                                                       (in_stack_00000048);
                                    if ((char)plVar8 == '\0') {
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,uVar7,unaff_R13,uVar15);
                                      plVar8 = (longlong *)
                                               (**(code **)(*in_stack_00000048 + 0x720))
                                                         (in_stack_00000048);
                                      if ((char)plVar8 == '\0') {
                                        uVar15 = (**(code **)(*in_stack_00000048 + 0x518))
                                                           (in_stack_00000048,&stack0x000009e6,1);
                                        plVar8 = (longlong *)
                                                 (**(code **)(*in_stack_00000048 + 0x720))
                                                           (in_stack_00000048);
                                        if ((char)plVar8 == '\0') {
                                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                            (in_stack_00000048,uVar7,unaff_R13,
                                                             uVar15);
                                          plVar8 = (longlong *)
                                                   (**(code **)(*in_stack_00000048 + 0x720))
                                                             (in_stack_00000048);
                                          if ((char)plVar8 == '\0') {
                                            if (DAT_1804b52a0 == 0) {
                                              return in_stack_00000048;
                                            }
                                            uVar15 = (**(code **)(*in_stack_00000048 + 0x2f8))
                                                               (in_stack_00000048,in_stack_00000138)
                                            ;
                                            plVar8 = (longlong *)
                                                     (**(code **)(*in_stack_00000048 + 0x720))
                                                               (in_stack_00000048);
                                            if ((char)plVar8 == '\0') {
                                              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                                (in_stack_00000048,uVar7,unaff_R13,
                                                                 uVar15);
                                              plVar8 = (longlong *)
                                                       (**(code **)(*in_stack_00000048 + 0x720))
                                                                 (in_stack_00000048);
                                              if ((char)plVar8 == '\0') {
                                                if (DAT_1804b5040 == 0) {
                                                  return in_stack_00000048;
                                                }
                                                (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,uVar7);
                                                plVar8 = (longlong *)
                                                         (**(code **)(*in_stack_00000048 + 0x720))
                                                                   (in_stack_00000048);
                                                if ((char)plVar8 == '\0') {
                                                  plVar8 = (longlong *)
                                                           (**(code **)(*in_stack_00000048 + 0x720))
                                                                     ();
                                                  if ((char)plVar8 == '\0') {
                                                    plVar8 = (longlong *)
                                                             (**(code **)(*in_stack_00000048 + 0x720
                                                                         ))();
                                                    if ((char)plVar8 == '\0') {
                                                      plVar8 = (longlong *)0xe9e40b3e69158a57;
                                                      if (DAT_1804ac970 == 0) {
                                                        lVar1 = *in_stack_00000048;
                                                        pcVar2 = *(code **)(lVar1 + 0x390);
                                                        uVar15 = FUN_18001e7d0(in_stack_00000048);
                                                        uVar7 = DAT_1804b5208;
                                                        uVar9 = (**(code **)(lVar1 + 0x518))
                                                                          (in_stack_00000048,
                                                                           &stack0x000009e4,1);
                                                        lVar1 = *in_stack_00000048;
                                                        pcVar3 = *(code **)(lVar1 + 0x390);
                                                        uVar10 = FUN_1800024f0(in_stack_00000048);
                                                        uVar11 = FUN_180002550(in_stack_00000048);
                                                        uVar12 = (**(code **)(lVar1 + 0x518))
                                                                           (in_stack_00000048,
                                                                            &stack0x00007c80,0x2a);
                                                        pcVar4 = *(code **)(*in_stack_00000048 +
                                                                           0x110);
                                                        uVar13 = FUN_1800025a0(in_stack_00000048);
                                                        uVar13 = (*pcVar4)(in_stack_00000048,
                                                                           in_stack_00000090,uVar13)
                                                        ;
                                                        (*pcVar3)(in_stack_00000048,uVar10,uVar11,
                                                                  uVar12,uVar13);
                                                        uVar7 = (*pcVar2)(in_stack_00000048,
                                                                          in_stack_00000090,uVar15,
                                                                          uVar7,uVar9);
                                                        DAT_1804ac970 =
                                                             (**(code **)(*in_stack_00000048 + 0xa8)
                                                             )(in_stack_00000048,uVar7);
                                                        plVar8 = (longlong *)
                                                                 (**(code **)(*in_stack_00000048 +
                                                                             0xb8))(
                                                  in_stack_00000048,uVar7);
                                                  }
                                                  return plVar8;
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
      }
    }
  }
  return plVar8;
}

