/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=145 entry=18046c637 blocks=35 maxInstructions=50000 */

/* cut edge: 0x18046c637 -> 0x180473ae8 */
/* cut edge: 0x18046c786 -> 0x180473fa6 */
/* cut edge: 0x18046c7a5 -> 0x180473ae8 */
/* cut edge: 0x18046c7ce -> 0x180473ae8 */
/* cut edge: 0x18046c7e9 -> 0x180473ae8 */
/* cut edge: 0x18046c9c3 -> 0x180473ae8 */
/* cut edge: 0x18046caac -> 0x180473ae8 */
/* cut edge: 0x18046cac2 -> 0x180473ae8 */
/* cut edge: 0x18046cc91 -> 0x180473ae8 */
/* cut edge: 0x18046cd75 -> 0x180473ffd */
/* cut edge: 0x18046cd94 -> 0x180474059 */
/* cut edge: 0x18046cda4 -> 0x180473ae8 */
/* cut edge: 0x18046cdc8 -> 0x180474093 */
/* cut edge: 0x18046cde7 -> 0x180473ae8 */
/* cut edge: 0x18046ce14 -> 0x180473ae8 */
/* cut edge: 0x18046ce2a -> 0x180473ae8 */
/* cut edge: 0x18046d003 -> 0x180473ae8 */
/* cut edge: 0x18046d131 -> 0x1804740c9 */
/* cut edge: 0x18046d150 -> 0x180474125 */
/* cut edge: 0x18046d160 -> 0x180473ae8 */
/* cut edge: 0x18046d184 -> 0x18047415e */
/* cut edge: 0x18046d1a3 -> 0x180473ae8 */
/* cut edge: 0x18046d1cc -> 0x180474194 */
/* cut edge: 0x18046d1eb -> 0x180473ae8 */
/* cut edge: 0x18046d230 -> 0x180473ae8 */
/* cut edge: 0x18046d253 -> 0x180473ae8 */
/* cut edge: 0x18046d454 -> 0x180473ae8 */
/* cut edge: 0x18046d454 -> 0x18046d5a0 */
/* target 0x18046d5a0 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180473fa6 original byte 0x48 replaced by RET */
/* target 0x180473ffd original byte 0x48 replaced by RET */
/* target 0x180474059 original byte 0x48 replaced by RET */
/* target 0x180474093 original byte 0x4c replaced by RET */
/* target 0x1804740c9 original byte 0x48 replaced by RET */
/* target 0x180474125 original byte 0x48 replaced by RET */
/* target 0x18047415e original byte 0x48 replaced by RET */
/* target 0x180474194 original byte 0x4c replaced by RET */


void region_00145_18046c637(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
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
  undefined8 uVar15;
  undefined8 uVar16;
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
  undefined8 in_stack_000000e8;
  undefined8 in_stack_00000148;
  undefined8 in_stack_000001b0;
  undefined8 in_stack_000001c0;
  undefined8 in_stack_00000738;
  undefined8 in_stack_00000790;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000090,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_000000e8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00000738);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,2,in_stack_00000058);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar8,3,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar8,4,uVar9);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  lVar5 = DAT_1804b52a8;
  if (cVar6 == '\0') {
    if (DAT_1804b52a8 == 0) {
      return;
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048);
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          if (DAT_1804b47f8 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar9 = FUN_1803335a0(in_stack_00000048);
            uVar8 = DAT_1804b59f0;
            uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000002cc,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar11 = FUN_1800024f0(in_stack_00000048);
            uVar12 = FUN_180002550(in_stack_00000048);
            uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003600,0x16);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar14 = FUN_1800025a0(in_stack_00000048);
            uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar14);
            (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
            uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar9,uVar8,uVar10);
            DAT_1804b47f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b47f8,in_stack_000000a0);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x50e0);
          (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                              in_stack_00000068 % 0xabe08 ^ in_stack_000000c8 ^ 0xa42e3961b5c43545);
          (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar6 == '\0') {
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar6 == '\0') {
                if (DAT_1804b4800 == 0) {
                  lVar2 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar2 + 0x390);
                  uVar10 = FUN_1803335a0(in_stack_00000048);
                  uVar9 = DAT_1804b59f0;
                  uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000002ca,1);
                  lVar2 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar2 + 0x390);
                  uVar12 = FUN_1800024f0(in_stack_00000048);
                  uVar13 = FUN_180002550(in_stack_00000048);
                  uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000035d0,0x16);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar15 = FUN_1800025a0(in_stack_00000048);
                  uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar15);
                  (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                  uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar10,uVar9,uVar11);
                  DAT_1804b4800 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b4800,in_stack_000000a0);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                   (in_stack_00000048,2,in_stack_00000090,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2a89);
                (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                    in_stack_00000068 % 0x16ec2 ^ in_stack_000000c8 ^
                                    0xed29df2e6001e415);
                (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar9,in_stack_000000a8,uVar10);
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar6 == '\0') {
                  if (DAT_1804b52b0 == 0) {
                    return;
                  }
                  if (DAT_1804b52b8 == 0) {
                    return;
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x488))
                                     (in_stack_00000048,DAT_1804b52b0);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
                    if (DAT_1804b52c0 == 0) {
                      return;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar9,DAT_1804b52c0,uVar10);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar6 == '\0') {
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar6 == '\0') {
                          if (DAT_1804b4808 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar11 = FUN_180333500(in_stack_00000048);
                            uVar10 = DAT_1804b59f0;
                            uVar12 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x000002c8,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar13 = FUN_1800024f0(in_stack_00000048);
                            uVar14 = FUN_180002550(in_stack_00000048);
                            uVar15 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00003140,0x19);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar16 = FUN_1800025a0(in_stack_00000048);
                            uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar16);
                            (*pcVar3)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
                            uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar11,uVar10,
                                               uVar12);
                            DAT_1804b4808 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                          }
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804b4808,in_stack_000000a0);
                          uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                             (in_stack_00000048,4,in_stack_00000090,0);
                          (**(code **)(*in_stack_00000048 + 0x570))
                                    (in_stack_00000048,uVar11,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             in_stack_00000790);
                          (*pcVar1)(in_stack_00000048,uVar11,1,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             in_stack_00000068 % 0x3bf2b ^ 0x5028ead18b8a3bb9);
                          (*pcVar1)(in_stack_00000048,uVar11,2,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             in_stack_000000c8);
                          (*pcVar1)(in_stack_00000048,uVar11,3,uVar9);
                          (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar10,in_stack_000000a8,uVar11);
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar6 == '\0') {
                            if (DAT_1804b52c8 == 0) {
                              return;
                            }
                            if (DAT_1804b52d0 == 0) {
                              return;
                            }
                            (**(code **)(*in_stack_00000048 + 0x488))
                                      (in_stack_00000048,DAT_1804b52c8);
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar6 == '\0') {
                              if (DAT_1804b52d8 == 0) {
                                return;
                              }
                              (**(code **)(*in_stack_00000048 + 0x420))
                                        (in_stack_00000048,in_stack_00000148);
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar6 == '\0') {
                                if (DAT_1804b52e0 == 0) {
                                  return;
                                }
                                (**(code **)(*in_stack_00000048 + 0x2d8))
                                          (in_stack_00000048,uVar7,lVar5,DAT_1804b52e0,uVar8);
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar6 == '\0') {
                                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar6 == '\0') {
                                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if (cVar6 == '\0') {
                                      if (DAT_1804b4810 == 0) {
                                        lVar5 = *in_stack_00000048;
                                        pcVar1 = *(code **)(lVar5 + 0x390);
                                        uVar9 = FUN_180333500(in_stack_00000048);
                                        uVar8 = DAT_1804b59f0;
                                        uVar10 = (**(code **)(lVar5 + 0x518))
                                                           (in_stack_00000048,&stack0x000002c6,1);
                                        lVar5 = *in_stack_00000048;
                                        pcVar3 = *(code **)(lVar5 + 0x390);
                                        uVar11 = FUN_1800024f0(in_stack_00000048);
                                        uVar12 = FUN_180002550(in_stack_00000048);
                                        uVar13 = (**(code **)(lVar5 + 0x518))
                                                           (in_stack_00000048,&stack0x00003c40,0x2a)
                                        ;
                                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar14 = FUN_1800025a0(in_stack_00000048);
                                        uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000088,
                                                           uVar14);
                                        (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                        uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar9,
                                                          uVar8,uVar10);
                                        DAT_1804b4810 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar8);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar8);
                                      }
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,DAT_1804b4810,
                                                         in_stack_000000a0);
                                      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,5,in_stack_00000090,0);
                                      (**(code **)(*in_stack_00000048 + 0x570))
                                                (in_stack_00000048,uVar9,0,in_stack_000001c0);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                         (in_stack_00000048,in_stack_00000080,
                                                          in_stack_00000098,in_stack_000001b0);
                                      (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                                      (**(code **)(*in_stack_00000048 + 0x570))
                                                (in_stack_00000048,uVar9,2,uVar7);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000080,
                                                         in_stack_00000098,
                                                         in_stack_00000068 % 0xcf64e ^
                                                         0x50236604602d1797);
                                      (*pcVar1)(in_stack_00000048,uVar9,3,uVar7);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000080,
                                                         in_stack_00000098,in_stack_000000c8);
                                      (*pcVar1)(in_stack_00000048,uVar9,4,uVar7);
                                      (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
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
  return;
}

