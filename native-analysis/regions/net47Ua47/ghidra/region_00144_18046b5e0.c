/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=144 entry=18046b5e0 blocks=36 maxInstructions=50000 */

/* cut edge: 0x18046b5e0 -> 0x180473ae8 */
/* cut edge: 0x18046b6c4 -> 0x180473f3d */
/* cut edge: 0x18046b6e3 -> 0x180473ae8 */
/* cut edge: 0x18046b71a -> 0x180473ae8 */
/* cut edge: 0x18046b755 -> 0x180473ae8 */
/* cut edge: 0x18046b76b -> 0x180473ae8 */
/* cut edge: 0x18046b936 -> 0x180473ae8 */
/* cut edge: 0x18046ba14 -> 0x180473ae8 */
/* cut edge: 0x18046ba4f -> 0x180473ae8 */
/* cut edge: 0x18046ba65 -> 0x180473ae8 */
/* cut edge: 0x18046bc32 -> 0x180473ae8 */
/* cut edge: 0x18046bd10 -> 0x180473ae8 */
/* cut edge: 0x18046bd4b -> 0x180473ae8 */
/* cut edge: 0x18046bd81 -> 0x180473ae8 */
/* cut edge: 0x18046bdc4 -> 0x180473ae8 */
/* cut edge: 0x18046bdfa -> 0x180473ae8 */
/* cut edge: 0x18046be10 -> 0x180473ae8 */
/* cut edge: 0x18046bfc4 -> 0x180473ae8 */
/* cut edge: 0x18046c0ad -> 0x180473ae8 */
/* cut edge: 0x18046c0c3 -> 0x180473ae8 */
/* cut edge: 0x18046c2a9 -> 0x180473ae8 */
/* cut edge: 0x18046c3a4 -> 0x180473ae8 */
/* cut edge: 0x18046c3da -> 0x180473f70 */
/* cut edge: 0x18046c3f9 -> 0x180473ae8 */
/* cut edge: 0x18046c41f -> 0x180473ae8 */
/* cut edge: 0x18046c442 -> 0x180473ae8 */
/* cut edge: 0x18046c458 -> 0x18046c637 */
/* cut edge: 0x18046c4ac -> 0x18046c637 */
/* target 0x18046c637 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180473f3d original byte 0x48 replaced by RET */
/* target 0x180473f70 original byte 0x48 replaced by RET */


longlong * region_00144_18046b5e0(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong *plVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 unaff_R12;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00000148;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4ad3);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  lVar5 = DAT_1804b5298;
  if ((char)plVar9 == '\0') {
    if (DAT_1804b5298 == 0) {
      return (longlong *)0x0;
    }
    (**(code **)(*in_stack_00000048 + 0x390))
              (in_stack_00000048,in_stack_00000148,DAT_1804b5298,uVar6);
    plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar9 == '\0') {
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_000000f8);
      plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)plVar9 == '\0') {
        plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)plVar9 == '\0') {
          plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)plVar9 == '\0') {
            if (DAT_1804b47d0 == 0) {
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar8 = FUN_1803335a0(in_stack_00000048);
              uVar7 = DAT_1804b59f0;
              uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000002d8,1);
              lVar2 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar2 + 0x390);
              uVar11 = FUN_1800024f0(in_stack_00000048);
              uVar12 = FUN_180002550(in_stack_00000048);
              uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003660,0x16);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar14 = FUN_1800025a0(in_stack_00000048);
              uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar14);
              (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
              uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar8,uVar7,uVar10);
              DAT_1804b47d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              unaff_R12 = in_stack_00000058;
            }
            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b47d0,in_stack_000000a0);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4b59);
            (*pcVar1)(in_stack_00000048,uVar8,0,uVar10);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                in_stack_00000068 % 0xe9dce ^ in_stack_000000c8 ^ 0x880630b2195c1960
                               );
            (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
            plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)plVar9 == '\0') {
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,unaff_R12,uVar7);
              plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)plVar9 == '\0') {
                plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)plVar9 == '\0') {
                  plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)plVar9 == '\0') {
                    if (DAT_1804b47d8 == 0) {
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar8 = FUN_1803335a0(in_stack_00000048);
                      uVar7 = DAT_1804b59f0;
                      uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000002d6,1);
                      lVar2 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000048);
                      uVar12 = FUN_180002550(in_stack_00000048);
                      uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003630,0x16)
                      ;
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar14 = FUN_1800025a0(in_stack_00000048);
                      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar14);
                      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar8,uVar7,uVar10);
                      DAT_1804b47d8 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                      unaff_R12 = in_stack_00000058;
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b47d8,in_stack_000000a0);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x30dd
                                       );
                    (*pcVar1)(in_stack_00000048,uVar8,0,uVar10);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                        in_stack_00000068 % 0xed86 ^ in_stack_000000c8 ^
                                        0xbbec241e4a118f71);
                    (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                    plVar9 = (longlong *)
                             (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)plVar9 == '\0') {
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000148,lVar5,uVar7);
                      plVar9 = (longlong *)
                               (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)plVar9 == '\0') {
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar6,unaff_R12,uVar7);
                        plVar9 = (longlong *)
                                 (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)plVar9 == '\0') {
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x000002d4,1);
                          plVar9 = (longlong *)
                                   (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)plVar9 == '\0') {
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar6,unaff_R12,uVar7);
                            plVar9 = (longlong *)
                                     (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)plVar9 == '\0') {
                              plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)plVar9 == '\0') {
                                plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)plVar9 == '\0') {
                                  if (DAT_1804b47e0 == 0) {
                                    lVar5 = *in_stack_00000048;
                                    pcVar1 = *(code **)(lVar5 + 0x390);
                                    uVar8 = FUN_180333500(in_stack_00000048);
                                    uVar7 = DAT_1804b59f0;
                                    uVar10 = (**(code **)(lVar5 + 0x518))
                                                       (in_stack_00000048,&stack0x000002d2,1);
                                    lVar5 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar5 + 0x390);
                                    uVar11 = FUN_1800024f0(in_stack_00000048);
                                    uVar12 = FUN_180002550(in_stack_00000048);
                                    uVar13 = (**(code **)(lVar5 + 0x518))
                                                       (in_stack_00000048,&stack0x00003050,0xd);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar14 = FUN_1800025a0(in_stack_00000048);
                                    uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar14);
                                    (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                    uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar8,
                                                      uVar7,uVar10);
                                    DAT_1804b47e0 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar7);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar7);
                                    unaff_R12 = in_stack_00000058;
                                  }
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,DAT_1804b47e0,
                                                     in_stack_000000a0);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_00000090,0);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                     (in_stack_00000048,in_stack_00000080,
                                                      in_stack_00000098,
                                                      in_stack_00000068 % 0x1a523 ^
                                                      0x50217b0755d7ef40);
                                  (*pcVar1)(in_stack_00000048,uVar8,0,uVar10);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                     (in_stack_00000048,in_stack_00000080,
                                                      in_stack_00000098,in_stack_000000c8);
                                  (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar7,in_stack_000000a8,uVar8
                                                    );
                                  plVar9 = (longlong *)
                                           (**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                                  if ((char)plVar9 == '\0') {
                                    plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))()
                                    ;
                                    if ((char)plVar9 == '\0') {
                                      plVar9 = (longlong *)
                                               (**(code **)(*in_stack_00000048 + 0x720))();
                                      if ((char)plVar9 == '\0') {
                                        if (DAT_1804b47e8 == 0) {
                                          lVar5 = *in_stack_00000048;
                                          pcVar1 = *(code **)(lVar5 + 0x390);
                                          uVar10 = FUN_180333500(in_stack_00000048);
                                          uVar8 = DAT_1804b59f0;
                                          uVar11 = (**(code **)(lVar5 + 0x518))
                                                             (in_stack_00000048,&stack0x000002d0,1);
                                          lVar5 = *in_stack_00000048;
                                          pcVar3 = *(code **)(lVar5 + 0x390);
                                          uVar12 = FUN_1800024f0(in_stack_00000048);
                                          uVar13 = FUN_180002550(in_stack_00000048);
                                          uVar14 = (**(code **)(lVar5 + 0x518))
                                                             (in_stack_00000048,&stack0x000040e0,
                                                              0x28);
                                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar15 = FUN_1800025a0(in_stack_00000048);
                                          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,
                                                             uVar15);
                                          (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                                          uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,
                                                            uVar10,uVar8,uVar11);
                                          DAT_1804b47e8 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar8);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar8);
                                          unaff_R12 = in_stack_00000058;
                                        }
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,DAT_1804b47e8,
                                                           in_stack_000000a0);
                                        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                                           (in_stack_00000048,3,in_stack_00000090,0)
                                        ;
                                        (**(code **)(*in_stack_00000048 + 0x570))
                                                  (in_stack_00000048,uVar10,0,uVar7);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000080,
                                                           in_stack_00000098,
                                                           in_stack_00000068 % 0x6fedd ^
                                                           0x500da4d11262f1a4);
                                        (*pcVar1)(in_stack_00000048,uVar10,1,uVar7);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000080,
                                                           in_stack_00000098,in_stack_000000c8);
                                        (*pcVar1)(in_stack_00000048,uVar10,2,uVar7);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,uVar8,in_stack_000000a8
                                                           ,uVar10);
                                        plVar9 = (longlong *)
                                                 (**(code **)(*in_stack_00000048 + 0x720))
                                                           (in_stack_00000048);
                                        if ((char)plVar9 == '\0') {
                                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                            (in_stack_00000048,uVar6,unaff_R12,uVar7
                                                            );
                                          plVar9 = (longlong *)
                                                   (**(code **)(*in_stack_00000048 + 0x720))
                                                             (in_stack_00000048);
                                          if ((char)plVar9 == '\0') {
                                            if (DAT_1804b5040 == 0) {
                                              return in_stack_00000048;
                                            }
                                            (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,uVar6);
                                            plVar9 = (longlong *)
                                                     (**(code **)(*in_stack_00000048 + 0x720))
                                                               (in_stack_00000048);
                                            if ((char)plVar9 == '\0') {
                                              plVar9 = (longlong *)
                                                       (**(code **)(*in_stack_00000048 + 0x720))();
                                              if ((char)plVar9 == '\0') {
                                                plVar9 = (longlong *)
                                                         (**(code **)(*in_stack_00000048 + 0x720))()
                                                ;
                                                if ((char)plVar9 == '\0') {
                                                  plVar9 = (longlong *)0x5024f333e5d073a0;
                                                  if (DAT_1804b47f0 == 0) {
                                                    lVar5 = *in_stack_00000048;
                                                    pcVar1 = *(code **)(lVar5 + 0x390);
                                                    uVar7 = FUN_180333500(in_stack_00000048);
                                                    uVar6 = DAT_1804b59f0;
                                                    uVar8 = (**(code **)(lVar5 + 0x518))
                                                                      (in_stack_00000048,
                                                                       &stack0x000002ce,1);
                                                    lVar5 = *in_stack_00000048;
                                                    pcVar3 = *(code **)(lVar5 + 0x390);
                                                    uVar10 = FUN_1800024f0(in_stack_00000048);
                                                    uVar11 = FUN_180002550(in_stack_00000048);
                                                    uVar12 = (**(code **)(lVar5 + 0x518))
                                                                       (in_stack_00000048,
                                                                        &stack0x00003ca0,0x2a);
                                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                                    uVar13 = FUN_1800025a0(in_stack_00000048);
                                                    uVar13 = (*pcVar4)(in_stack_00000048,
                                                                       in_stack_00000088,uVar13);
                                                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12
                                                              ,uVar13);
                                                    uVar6 = (*pcVar1)(in_stack_00000048,
                                                                      in_stack_00000088,uVar7,uVar6,
                                                                      uVar8);
                                                    DAT_1804b47f0 =
                                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                                   (in_stack_00000048,uVar6);
                                                    plVar9 = (longlong *)
                                                             (**(code **)(*in_stack_00000048 + 0xb8)
                                                             )(in_stack_00000048,uVar6);
                                                  }
                                                  return plVar9;
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
  return plVar9;
}

