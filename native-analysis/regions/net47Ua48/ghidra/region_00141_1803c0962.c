/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=141 entry=1803c0962 blocks=40 maxInstructions=50000 */

/* cut edge: 0x1803c0962 -> 0x1803351d6 */
/* cut edge: 0x1803c0a63 -> 0x1803351d6 */
/* cut edge: 0x1803c0c40 -> 0x1803351d6 */
/* cut edge: 0x1803c0d6c -> 0x1803351d6 */
/* cut edge: 0x1803c0ef2 -> 0x1803351d6 */
/* cut edge: 0x1803c1002 -> 0x1803351d6 */
/* cut edge: 0x1803c1048 -> 0x1803351d6 */
/* cut edge: 0x1803c109b -> 0x1803351d6 */
/* cut edge: 0x1803c137e -> 0x1803351d6 */
/* cut edge: 0x1803c1397 -> 0x1803c7e39 */
/* cut edge: 0x1803c13cf -> 0x1803351d6 */
/* cut edge: 0x1803c13fd -> 0x1803c2c48 */
/* cut edge: 0x1803c140a -> 0x1803351d6 */
/* cut edge: 0x1803c1430 -> 0x1803d2466 */
/* cut edge: 0x1803c1454 -> 0x1803d249c */
/* cut edge: 0x1803c14b3 -> 0x1803d24ed */
/* cut edge: 0x1803c14c3 -> 0x1803d2527 */
/* cut edge: 0x1803c15a0 -> 0x1803351d6 */
/* cut edge: 0x1803c1682 -> 0x1803c1fe5 */
/* cut edge: 0x1803c1693 -> 0x1803c2007 */
/* cut edge: 0x1803c16a6 -> 0x1803c3913 */
/* cut edge: 0x1803c16b3 -> 0x1803c520f */
/* cut edge: 0x1803c16b8 -> 0x1803351d6 */
/* cut edge: 0x1803c16cb -> 0x1803c183f */
/* cut edge: 0x1803c16db -> 0x1803c183f */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803c183f original byte 0x48 replaced by RET */
/* target 0x1803c1fe5 original byte 0x48 replaced by RET */
/* target 0x1803c2007 original byte 0x48 replaced by RET */
/* target 0x1803c2c48 original byte 0x48 replaced by RET */
/* target 0x1803c3913 original byte 0x48 replaced by RET */
/* target 0x1803c520f original byte 0x48 replaced by RET */
/* target 0x1803c7e39 original byte 0x48 replaced by RET */
/* target 0x1803d2466 original byte 0x48 replaced by RET */
/* target 0x1803d249c original byte 0x48 replaced by RET */
/* target 0x1803d24ed original byte 0x48 replaced by RET */
/* target 0x1803d2527 original byte 0x48 replaced by RET */


/* WARNING: Removing unreachable block (ram,0x0001803c3913) */

void region_00141_1803c0962(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
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
  longlong lVar15;
  undefined8 unaff_R13;
  undefined8 unaff_R15;
  bool bVar16;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000c8;
  longlong in_stack_00000108;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000108);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfd506c68014bc2c);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar8)
  ;
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      if (DAT_1804b3530 == 0) {
        lVar15 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar15 + 0x390);
        uVar9 = FUN_180333500(in_stack_00000048);
        uVar8 = DAT_1804b59f0;
        uVar10 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000296,1);
        lVar15 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar15 + 0x390);
        uVar11 = FUN_1800024f0(in_stack_00000048);
        uVar12 = FUN_180002550(in_stack_00000048);
        uVar13 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x000067e0,0x2c);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar14 = FUN_1800025a0(in_stack_00000048);
        uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
        (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
        uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
        DAT_1804b3530 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        unaff_R13 = in_stack_00000068;
        unaff_R15 = in_stack_00000060;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b3530,in_stack_00000078);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                          in_stack_000000c8 ^ 0x6fba2995fc11);
      (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,1,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc7761109487c6d);
      (*pcVar1)(in_stack_00000048,uVar9,2,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
      (*pcVar1)(in_stack_00000048,uVar9,3,uVar7);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((cVar6 == '\0') && (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
        if (DAT_1804b3538 == 0) {
          lVar15 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar15 + 0x390);
          uVar9 = FUN_180333500(in_stack_00000048);
          uVar8 = DAT_1804b59f0;
          uVar10 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000294,1);
          lVar15 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar15 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00004230,0x17);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
          (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
          DAT_1804b3538 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          unaff_R13 = in_stack_00000068;
          unaff_R15 = in_stack_00000060;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b3538,in_stack_00000078);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,in_stack_00000108);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc4f495e7ef60e4
                           );
        (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8)
        ;
        (*pcVar1)(in_stack_00000048,uVar9,2,uVar10);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
        (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,unaff_R13);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
          uVar8 = FUN_18001efd0(in_stack_00000048);
          uVar9 = FUN_18001f030(in_stack_00000048);
          (*pcVar1)(in_stack_00000048,uVar8,uVar9);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
            uVar8 = FUN_1803db3b0(in_stack_00000048);
            (*pcVar1)(in_stack_00000048,unaff_R15,in_stack_000000a8,uVar8,uVar7);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((cVar6 == '\0') &&
               (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
              if (DAT_1804b3540 == 0) {
                lVar15 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar15 + 0x390);
                uVar8 = FUN_180333500(in_stack_00000048);
                uVar7 = DAT_1804b59f0;
                uVar9 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000292,1);
                lVar15 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar15 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x000053d0,0x2a);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
                (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
                DAT_1804b3540 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                unaff_R15 = in_stack_00000060;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b3540,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,5,in_stack_00000088,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000168)
              ;
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_00000170);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,2,unaff_R15);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 0xdfc79c58f7839374);
              (*pcVar1)(in_stack_00000048,uVar8,3,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar8,4,uVar9);
              (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                if (in_stack_00000108 == 0) {
                  bVar16 = false;
                }
                else {
                  if (DAT_1804b5d30 == 0) {
                    return;
                  }
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x100))
                                    (in_stack_00000048,in_stack_00000108);
                  bVar16 = cVar6 != '\0';
                }
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar6 == '\0') {
                  if ((longlong)in_stack_000000c8 < 0) {
                    return;
                  }
                  if (!bVar16) {
                    return;
                  }
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  uVar7 = DAT_1804b59f0;
                  lVar15 = DAT_1804b59e8;
                  if (cVar6 == '\0') {
                    if (DAT_1804b35b0 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b59e8 == 0) {
                        return;
                      }
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00000278,1);
                      lVar5 = DAT_1804b4d38;
                      lVar4 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00002490,0xd);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,in_stack_00000058);
                      (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar15,uVar7,uVar8);
                      DAT_1804b35b0 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b35b0,in_stack_00000078);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       0xdfc5815bc278527a);
                    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       in_stack_000000c8);
                    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                    lVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar6 == '\0') {
                      if (lVar15 == 0) {
                        return;
                      }
                      (**(code **)(*in_stack_00000048 + 0xf8))(in_stack_00000048,lVar15);
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
  return;
}

