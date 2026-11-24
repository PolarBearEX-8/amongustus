/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=140 entry=1803bf89d blocks=30 maxInstructions=50000 */

/* cut edge: 0x1803bf89d -> 0x1803351d6 */
/* cut edge: 0x1803bf8d8 -> 0x1803351d6 */
/* cut edge: 0x1803bfa60 -> 0x1803351d6 */
/* cut edge: 0x1803bfb69 -> 0x1803c16b8 */
/* cut edge: 0x1803bfb7a -> 0x1803351d6 */
/* cut edge: 0x1803bfbba -> 0x1803351d6 */
/* cut edge: 0x1803bfd60 -> 0x1803351d6 */
/* cut edge: 0x1803bfe64 -> 0x1803351d6 */
/* cut edge: 0x1803c004e -> 0x1803351d6 */
/* cut edge: 0x1803c0205 -> 0x1803351d6 */
/* cut edge: 0x1803c0380 -> 0x1803351d6 */
/* cut edge: 0x1803c0490 -> 0x1803351d6 */
/* cut edge: 0x1803c04d6 -> 0x1803351d6 */
/* cut edge: 0x1803c052e -> 0x1803351d6 */
/* cut edge: 0x1803c06cd -> 0x1803c12ee */
/* cut edge: 0x1803c0780 -> 0x1803351d6 */
/* cut edge: 0x1803c07bb -> 0x1803351d6 */
/* cut edge: 0x1803c07d6 -> 0x1803c0962 */
/* cut edge: 0x1803c07eb -> 0x1803c0962 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803c0962 original byte 0x48 replaced by RET */
/* target 0x1803c12ee original byte 0xff replaced by RET */
/* target 0x1803c16b8 original byte 0x48 replaced by RET */


longlong region_00140_1803bf89d(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  int iVar4;
  longlong lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  code *unaff_RDI;
  undefined8 unaff_R13;
  uint uVar16;
  undefined8 unaff_R15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_00000108;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  
  iVar4 = (*unaff_RDI)(in_stack_00000048);
  lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar5 == '\0') {
    if (iVar4 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
      uVar12 = FUN_1803db350(in_stack_00000048);
      (*pcVar1)(in_stack_00000048,uVar12);
      lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)lVar5 == '\0') {
        lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)lVar5 == '\0') {
          if (DAT_1804b3528 == 0) {
            lVar5 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar5 + 0x390);
            uVar6 = FUN_180333500(in_stack_00000048);
            uVar12 = DAT_1804b59f0;
            uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00000298,1);
            lVar5 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar5 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00006f60,0x28);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
            (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar6,uVar12,uVar7);
            DAT_1804b3528 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            lVar5 = (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          return lVar5;
        }
      }
    }
    else {
      lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)lVar5 == '\0') {
        if (DAT_1804b3548 == 0) {
          lVar5 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar5 + 0x390);
          uVar6 = FUN_180333500(in_stack_00000048);
          uVar12 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00000290,1);
          lVar5 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar5 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00004200,0x17);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar6,uVar12,uVar7);
          DAT_1804b3548 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          unaff_R15 = 0xdfcc4ef1f3929c29;
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b3548,in_stack_00000078);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,0,in_stack_00000108);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,unaff_R15);
        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
        (*pcVar1)(in_stack_00000048,uVar6,2,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar12,in_stack_00000080,uVar6);
        iVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,unaff_R13);
        lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)lVar5 == '\0') {
          if (iVar4 != 1) {
            return *in_stack_00000048;
          }
          pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
          uVar12 = FUN_1803db350(in_stack_00000048);
          uVar6 = (*pcVar1)(in_stack_00000048,uVar12);
          lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)lVar5 == '\0') {
            lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)lVar5 == '\0') {
              if (DAT_1804b3550 == 0) {
                lVar5 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar5 + 0x390);
                uVar8 = FUN_180333500(in_stack_00000048);
                uVar7 = DAT_1804b59f0;
                uVar9 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x0000028e,1);
                lVar5 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar5 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar13 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00006f10,0x28);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar14 = FUN_1800025a0(in_stack_00000048);
                uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar13,uVar14);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
                DAT_1804b3550 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b3550,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,3,in_stack_00000088,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000108)
              ;
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 0xdfd506c68014bc2c);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
              lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)lVar5 == '\0') {
                uVar16 = (uint)(in_stack_000000c8 ^ 0x3f4bbb4888cc);
                lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)lVar5 == '\0') {
                  if (DAT_1804b3558 == 0) {
                    lVar5 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar5 + 0x390);
                    uVar9 = FUN_180333500(in_stack_00000048);
                    uVar8 = DAT_1804b59f0;
                    uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x0000028c,1);
                    lVar5 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar5 + 0x390);
                    uVar11 = FUN_1800024f0(in_stack_00000048);
                    uVar13 = FUN_180002550(in_stack_00000048);
                    uVar14 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00006d80,0x2e);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar15 = FUN_1800025a0(in_stack_00000048);
                    uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
                    (*pcVar2)(in_stack_00000048,uVar11,uVar13,uVar14,uVar15);
                    uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
                    DAT_1804b3558 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                  }
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b3558,in_stack_00000078);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,6,in_stack_00000088,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000060,in_stack_00000070,
                                      (in_stack_000000c8 ^ 0x3f4bbb4888cc) >> 0x20);
                  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = FUN_18014d8f0(in_stack_00000048);
                  uVar11 = FUN_18014d950(in_stack_00000048);
                  uVar10 = (*pcVar1)(in_stack_00000048,uVar10,uVar11,(int)uVar16 >> 0x18);
                  (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
                  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,2,uVar7);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000060,in_stack_00000070,
                                     uVar16 & 0xffffff);
                  (*pcVar1)(in_stack_00000048,uVar9,3,uVar7);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     0xdfcd231e5534039d);
                  (*pcVar1)(in_stack_00000048,uVar9,4,uVar7);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000c8);
                  (*pcVar1)(in_stack_00000048,uVar9,5,uVar7);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
                  lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)lVar5 == '\0') {
                    lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)lVar5 == '\0') {
                      if (DAT_1804b3560 == 0) {
                        lVar5 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar5 + 0x390);
                        uVar9 = FUN_180333500(in_stack_00000048);
                        uVar8 = DAT_1804b59f0;
                        uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x0000028a,1);
                        lVar5 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar5 + 0x390);
                        uVar11 = FUN_1800024f0(in_stack_00000048);
                        uVar13 = FUN_180002550(in_stack_00000048);
                        uVar14 = (**(code **)(lVar5 + 0x518))
                                           (in_stack_00000048,&stack0x000041d0,0x17);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar15 = FUN_1800025a0(in_stack_00000048);
                        uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
                        (*pcVar2)(in_stack_00000048,uVar11,uVar13,uVar14,uVar15);
                        uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
                        DAT_1804b3560 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                      }
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b3560,in_stack_00000078);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,3,in_stack_00000088,0);
                      (**(code **)(*in_stack_00000048 + 0x570))
                                (in_stack_00000048,uVar9,0,in_stack_00000108);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                          0xdfc4f495e7ef60e4);
                      (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                          in_stack_000000c8);
                      (*pcVar1)(in_stack_00000048,uVar9,2,uVar10);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
                      (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,unaff_R13);
                      lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)lVar5 == '\0') {
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
                        uVar8 = FUN_18001efd0(in_stack_00000048);
                        uVar9 = FUN_18001f030(in_stack_00000048);
                        (*pcVar1)(in_stack_00000048,uVar8,uVar9);
                        lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)lVar5 == '\0') {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                          uVar8 = FUN_1803db3b0(in_stack_00000048);
                          (*pcVar1)(in_stack_00000048,uVar6,uVar12,uVar8,uVar7);
                          lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)lVar5 == '\0') {
                            lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)lVar5 == '\0') {
                              if (DAT_1804b3568 == 0) {
                                lVar5 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar5 + 0x390);
                                uVar6 = FUN_180333500(in_stack_00000048);
                                uVar12 = DAT_1804b59f0;
                                uVar7 = (**(code **)(lVar5 + 0x518))
                                                  (in_stack_00000048,&stack0x00000288,1);
                                lVar5 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar5 + 0x390);
                                uVar8 = FUN_1800024f0(in_stack_00000048);
                                uVar9 = FUN_180002550(in_stack_00000048);
                                uVar10 = (**(code **)(lVar5 + 0x518))
                                                   (in_stack_00000048,&stack0x00005370,0x2a);
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar11 = FUN_1800025a0(in_stack_00000048);
                                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                                (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar6,uVar12,
                                                   uVar7);
                                DAT_1804b3568 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar12);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                              }
                              (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b3568,in_stack_00000078);
                              uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                                 (in_stack_00000048,5,in_stack_00000088,0);
                              (**(code **)(*in_stack_00000048 + 0x570))
                                        (in_stack_00000048,uVar12,0,in_stack_00000168);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_00000170);
                              (*pcVar1)(in_stack_00000048,uVar12,1,uVar6);
                              return *in_stack_00000048;
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
  return lVar5;
}

