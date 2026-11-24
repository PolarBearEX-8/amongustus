/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=149 entry=1804705f8 blocks=34 maxInstructions=50000 */

/* cut edge: 0x1804705f8 -> 0x180473ae8 */
/* cut edge: 0x180470633 -> 0x180473ae8 */
/* cut edge: 0x180470649 -> 0x180473ae8 */
/* cut edge: 0x18047082a -> 0x180473ae8 */
/* cut edge: 0x180470908 -> 0x180474324 */
/* cut edge: 0x180470927 -> 0x180473ae8 */
/* cut edge: 0x18047095e -> 0x180473ae8 */
/* cut edge: 0x180470999 -> 0x180473ae8 */
/* cut edge: 0x1804709af -> 0x180473ae8 */
/* cut edge: 0x180470b83 -> 0x180473ae8 */
/* cut edge: 0x180470c61 -> 0x180473ae8 */
/* cut edge: 0x180470c9c -> 0x180473ae8 */
/* cut edge: 0x180470cb2 -> 0x180473ae8 */
/* cut edge: 0x180470e92 -> 0x180473ae8 */
/* cut edge: 0x180470f70 -> 0x180473ae8 */
/* cut edge: 0x180470fab -> 0x180473ae8 */
/* cut edge: 0x180470fe1 -> 0x180473ae8 */
/* cut edge: 0x180471024 -> 0x180473ae8 */
/* cut edge: 0x18047105a -> 0x180473ae8 */
/* cut edge: 0x180471070 -> 0x180473ae8 */
/* cut edge: 0x180471220 -> 0x180473ae8 */
/* cut edge: 0x180471309 -> 0x180473ae8 */
/* cut edge: 0x18047131f -> 0x180473ae8 */
/* cut edge: 0x1804714fc -> 0x180473ae8 */
/* cut edge: 0x1804714fc -> 0x1804715f7 */
/* target 0x1804715f7 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180474324 original byte 0x48 replaced by RET */


void region_00149_1804705f8(void)

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
  undefined8 uVar14;
  undefined8 unaff_R12;
  longlong *in_stack_00000048;
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
  undefined8 in_stack_000000e8;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00000148;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_000000f8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar6 == '\0') {
        if (DAT_1804b4898 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_1803335a0(in_stack_00000048);
          uVar14 = DAT_1804b59f0;
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002a4,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000048);
          uVar11 = FUN_180002550(in_stack_00000048);
          uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003540,0x16);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
          uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar14,uVar9);
          DAT_1804b4898 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
          unaff_R12 = in_stack_000000e8;
        }
        uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b4898,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4ad3);
        (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0xa1b47 ^ in_stack_000000c8 ^ 0x8039e9e1816afb6c);
        (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
        uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar14,in_stack_000000a8,uVar8);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        lVar1 = DAT_1804b5298;
        if (cVar6 == '\0') {
          if (DAT_1804b5298 == 0) {
            return;
          }
          uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000148,DAT_1804b5298,uVar14);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar7,unaff_R12,uVar14);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar6 == '\0') {
                  if (DAT_1804b48a0 == 0) {
                    lVar5 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar5 + 0x390);
                    uVar8 = FUN_1803335a0(in_stack_00000048);
                    uVar14 = DAT_1804b59f0;
                    uVar9 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000002a2,1);
                    lVar5 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar5 + 0x390);
                    uVar10 = FUN_1800024f0(in_stack_00000048);
                    uVar11 = FUN_180002550(in_stack_00000048);
                    uVar12 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00003510,0x16);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar13 = FUN_1800025a0(in_stack_00000048);
                    uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                    uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar14,uVar9);
                    DAT_1804b48a0 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
                    unaff_R12 = in_stack_000000e8;
                  }
                  uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b48a0,in_stack_000000a0);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4b59);
                  (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     in_stack_00000068 % 0xe4e64 ^ in_stack_000000c8 ^
                                     0x880630b219522814);
                  (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                  uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar14,in_stack_000000a8,uVar8);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar7,unaff_R12,uVar14);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar6 == '\0') {
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar6 == '\0') {
                          if (DAT_1804b48a8 == 0) {
                            lVar5 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar5 + 0x390);
                            uVar8 = FUN_1803335a0(in_stack_00000048);
                            uVar14 = DAT_1804b59f0;
                            uVar9 = (**(code **)(lVar5 + 0x518))
                                              (in_stack_00000048,&stack0x000002a0,1);
                            lVar5 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar5 + 0x390);
                            uVar10 = FUN_1800024f0(in_stack_00000048);
                            uVar11 = FUN_180002550(in_stack_00000048);
                            uVar12 = (**(code **)(lVar5 + 0x518))
                                               (in_stack_00000048,&stack0x000034e0,0x16);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar13 = FUN_1800025a0(in_stack_00000048);
                            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
                            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                            uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar14,
                                               uVar9);
                            DAT_1804b48a8 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
                            unaff_R12 = in_stack_000000e8;
                          }
                          uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804b48a8,in_stack_000000a0);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000090,0);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                             0x30dd);
                          (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             in_stack_00000068 % 0xa4866 ^ in_stack_000000c8 ^
                                             0xbbec241e4a183835);
                          (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                          uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,uVar14,in_stack_000000a8,uVar8);
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar6 == '\0') {
                            uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000148,lVar1,uVar14);
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar6 == '\0') {
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar7,unaff_R12,uVar14);
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar6 == '\0') {
                                uVar14 = (**(code **)(*in_stack_00000048 + 0x518))
                                                   (in_stack_00000048,&stack0x0000029e,1);
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar6 == '\0') {
                                  (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,unaff_R12,uVar14);
                                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar6 == '\0') {
                                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if (cVar6 == '\0') {
                                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar6 == '\0') {
                                        if (DAT_1804b48b0 == 0) {
                                          lVar1 = *in_stack_00000048;
                                          pcVar2 = *(code **)(lVar1 + 0x390);
                                          uVar14 = FUN_180333500(in_stack_00000048);
                                          uVar7 = DAT_1804b59f0;
                                          uVar8 = (**(code **)(lVar1 + 0x518))
                                                            (in_stack_00000048,&stack0x0000029c,1);
                                          lVar1 = *in_stack_00000048;
                                          pcVar3 = *(code **)(lVar1 + 0x390);
                                          uVar9 = FUN_1800024f0(in_stack_00000048);
                                          uVar10 = FUN_180002550(in_stack_00000048);
                                          uVar11 = (**(code **)(lVar1 + 0x518))
                                                             (in_stack_00000048,&stack0x00003010,0xd
                                                             );
                                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar12 = FUN_1800025a0(in_stack_00000048);
                                          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,
                                                             uVar12);
                                          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                          uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000088,
                                                            uVar14,uVar7,uVar8);
                                          DAT_1804b48b0 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar7);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar7);
                                        }
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,DAT_1804b48b0,
                                                           in_stack_000000a0);
                                        uVar14 = (**(code **)(*in_stack_00000048 + 0x560))
                                                           (in_stack_00000048,2,in_stack_00000090,0)
                                        ;
                                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000080,
                                                           in_stack_00000098,
                                                           in_stack_00000068 % 0x7b3d0 ^
                                                           0x50217b0755d1fc8d);
                                        (*pcVar2)(in_stack_00000048,uVar14,0,uVar8);
                                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000080,
                                                           in_stack_00000098,in_stack_000000c8);
                                        (*pcVar2)(in_stack_00000048,uVar14,1,uVar8);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,uVar7,in_stack_000000a8
                                                           ,uVar14);
                                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
                                        if (cVar6 == '\0') {
                                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                          if (cVar6 == '\0') {
                                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                            if (cVar6 == '\0') {
                                              if (DAT_1804b48b8 == 0) {
                                                lVar1 = *in_stack_00000048;
                                                pcVar2 = *(code **)(lVar1 + 0x390);
                                                uVar8 = FUN_180333500(in_stack_00000048);
                                                uVar14 = DAT_1804b59f0;
                                                uVar9 = (**(code **)(lVar1 + 0x518))
                                                                  (in_stack_00000048,
                                                                   &stack0x0000029a,1);
                                                lVar1 = *in_stack_00000048;
                                                pcVar3 = *(code **)(lVar1 + 0x390);
                                                uVar10 = FUN_1800024f0(in_stack_00000048);
                                                uVar11 = FUN_180002550(in_stack_00000048);
                                                uVar12 = (**(code **)(lVar1 + 0x518))
                                                                   (in_stack_00000048,
                                                                    &stack0x00004090,0x28);
                                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                                uVar13 = FUN_1800025a0(in_stack_00000048);
                                                uVar13 = (*pcVar4)(in_stack_00000048,
                                                                   in_stack_00000088,uVar13);
                                                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,
                                                          uVar13);
                                                uVar14 = (*pcVar2)(in_stack_00000048,
                                                                   in_stack_00000088,uVar8,uVar14,
                                                                   uVar9);
                                                DAT_1804b48b8 =
                                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                                               (in_stack_00000048,uVar14);
                                                (**(code **)(*in_stack_00000048 + 0xb8))
                                                          (in_stack_00000048,uVar14);
                                              }
                                              uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                                                 (in_stack_00000048,DAT_1804b48b8,
                                                                  in_stack_000000a0);
                                              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                                (in_stack_00000048,3,
                                                                 in_stack_00000090,0);
                                              (**(code **)(*in_stack_00000048 + 0x570))
                                                        (in_stack_00000048,uVar8,0,uVar7);
                                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                                (in_stack_00000048,in_stack_00000080
                                                                 ,in_stack_00000098,
                                                                 in_stack_00000068 % 0x8777c ^
                                                                 0x500da4d11260ce05);
                                              (*pcVar2)(in_stack_00000048,uVar8,1,uVar7);
                                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                                (in_stack_00000048,in_stack_00000080
                                                                 ,in_stack_00000098,
                                                                 in_stack_000000c8);
                                              (*pcVar2)(in_stack_00000048,uVar8,2,uVar7);
                                              (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,uVar14,in_stack_000000a8,
                                                         uVar8);
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
    }
  }
  return;
}

