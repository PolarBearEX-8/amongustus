/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=116 entry=18030cde4 blocks=31 maxInstructions=50000 */

/* cut edge: 0x18030cde4 -> 0x180333186 */
/* cut edge: 0x18030cdfa -> 0x180333186 */
/* cut edge: 0x18030cfae -> 0x180333186 */
/* cut edge: 0x18030d0df -> 0x180333186 */
/* cut edge: 0x18030d0f5 -> 0x180333186 */
/* cut edge: 0x18030d2a8 -> 0x180333186 */
/* cut edge: 0x18030d39c -> 0x18030decb */
/* cut edge: 0x18030d3b7 -> 0x180333186 */
/* cut edge: 0x18030d3bf -> 0x180333186 */
/* cut edge: 0x18030d56b -> 0x180333186 */
/* cut edge: 0x18030d662 -> 0x180333186 */
/* cut edge: 0x18030d678 -> 0x180333186 */
/* cut edge: 0x18030d828 -> 0x180333186 */
/* cut edge: 0x18030d91f -> 0x180333186 */
/* cut edge: 0x18030d935 -> 0x180333186 */
/* cut edge: 0x18030dae8 -> 0x180333186 */
/* cut edge: 0x18030dbdd -> 0x180333186 */
/* cut edge: 0x18030dbf3 -> 0x180333186 */
/* cut edge: 0x18030dc09 -> 0x18030ddaf */
/* cut edge: 0x18030dc5e -> 0x18030ddaf */
/* target 0x18030ddaf original byte 0x48 replaced by RET */
/* target 0x18030decb original byte 0x84 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


undefined8 region_00116_18030cde4(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  int iVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong lVar13;
  ulonglong unaff_R13;
  longlong *in_stack_00000040;
  ulonglong in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000f8;
  int in_stack_00000140;
  
  uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
  if ((char)uVar6 == '\0') {
    uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    if ((char)uVar6 == '\0') {
      if (DAT_1804b0f90 == 0) {
        lVar13 = *in_stack_00000040;
        pcVar1 = *(code **)(lVar13 + 0x390);
        uVar7 = FUN_1803335f0(in_stack_00000040);
        uVar6 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar13 + 0x518))(in_stack_00000040,&stack0x0000056c,1);
        lVar13 = *in_stack_00000040;
        pcVar2 = *(code **)(lVar13 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000040);
        uVar10 = FUN_180002550(in_stack_00000040);
        uVar11 = (**(code **)(lVar13 + 0x518))(in_stack_00000040,&stack0x000023f8,5);
        pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000040);
        uVar12 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar12);
        (*pcVar2)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
        uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
        DAT_1804b0f90 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
      }
      uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                        (in_stack_00000040,DAT_1804b0f90,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6f51);
      (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         (longlong)in_stack_00000070 % 0xf0bb7 ^ in_stack_000000b0 ^
                         0x456e3fb74235714a);
      (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
      uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                        (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
      lVar13 = (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
      uVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if ((char)uVar6 == '\0') {
        lVar13 = lVar13 + (unaff_R13 ^ in_stack_00000070);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        if ((char)uVar6 == '\0') {
          uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
          if ((char)uVar6 == '\0') {
            if (DAT_1804b0ec8 == 0) {
              lVar4 = *in_stack_00000040;
              pcVar1 = *(code **)(lVar4 + 0x390);
              uVar7 = FUN_180333550(in_stack_00000040);
              uVar6 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar4 + 0x518))(in_stack_00000040,&stack0x0000059e,1);
              lVar4 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar4 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000040);
              uVar10 = FUN_180002550(in_stack_00000040);
              uVar11 = (**(code **)(lVar4 + 0x518))(in_stack_00000040,&stack0x00002588,5);
              pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000040);
              uVar12 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar12);
              (*pcVar2)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
              uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
              DAT_1804b0ec8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
            }
            uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,DAT_1804b0ec8,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x3979);
            (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               lVar13 % 0xf24c8 ^ in_stack_000000b0 ^ 0x70c2ba5829cb07fe);
            (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
            uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
            iVar5 = (**(code **)(*in_stack_00000040 + 0x188))
                              (in_stack_00000040,uVar6,in_stack_000000f8);
            uVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if ((char)uVar6 == '\0') {
              uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
              if (iVar5 <= in_stack_00000140 + 1) {
                return uVar6;
              }
              if ((char)uVar6 == '\0') {
                uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                if ((char)uVar6 == '\0') {
                  if (DAT_1804b0ef0 == 0) {
                    lVar4 = *in_stack_00000040;
                    pcVar1 = *(code **)(lVar4 + 0x390);
                    uVar7 = FUN_1803335f0(in_stack_00000040);
                    uVar6 = DAT_1804b59f0;
                    uVar8 = (**(code **)(lVar4 + 0x518))(in_stack_00000040,&stack0x00000594,1);
                    lVar4 = *in_stack_00000040;
                    pcVar2 = *(code **)(lVar4 + 0x390);
                    uVar9 = FUN_1800024f0(in_stack_00000040);
                    uVar10 = FUN_180002550(in_stack_00000040);
                    uVar11 = (**(code **)(lVar4 + 0x518))(in_stack_00000040,&stack0x00002538,5);
                    pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                    uVar12 = FUN_1800025a0(in_stack_00000040);
                    uVar12 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar12);
                    (*pcVar2)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                    uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
                    DAT_1804b0ef0 =
                         (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
                    (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
                  }
                  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                    (in_stack_00000040,DAT_1804b0ef0,in_stack_000000a0);
                  uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                    (in_stack_00000040,2,in_stack_00000090,0);
                  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0xb46);
                  (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                    (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                     lVar13 % 0x1fb58 ^ in_stack_000000b0 ^ 0x25046065f5b6e8be);
                  (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
                  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                    (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
                  (**(code **)(*in_stack_00000040 + 0x1a0))
                            (in_stack_00000040,uVar6,in_stack_000000d0);
                  uVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                  if ((char)uVar6 == '\0') {
                    uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                    if ((char)uVar6 == '\0') {
                      uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                      if ((char)uVar6 == '\0') {
                        if (DAT_1804b0ef8 == 0) {
                          lVar4 = *in_stack_00000040;
                          pcVar1 = *(code **)(lVar4 + 0x390);
                          uVar7 = FUN_1803335f0(in_stack_00000040);
                          uVar6 = DAT_1804b59f0;
                          uVar8 = (**(code **)(lVar4 + 0x518))(in_stack_00000040,&stack0x00000592,1)
                          ;
                          lVar4 = *in_stack_00000040;
                          pcVar2 = *(code **)(lVar4 + 0x390);
                          uVar9 = FUN_1800024f0(in_stack_00000040);
                          uVar10 = FUN_180002550(in_stack_00000040);
                          uVar11 = (**(code **)(lVar4 + 0x518))
                                             (in_stack_00000040,&stack0x00002528,5);
                          pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                          uVar12 = FUN_1800025a0(in_stack_00000040);
                          uVar12 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar12);
                          (*pcVar2)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                          uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
                          DAT_1804b0ef8 =
                               (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
                          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
                        }
                        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                          (in_stack_00000040,DAT_1804b0ef8,in_stack_000000a0);
                        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                          (in_stack_00000040,2,in_stack_00000090,0);
                        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,
                                           0x1daa);
                        (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                           lVar13 % 0xdc963 ^ in_stack_000000b0 ^ 0x3323b3571c6e0f80
                                          );
                        (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
                        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                          (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
                        (**(code **)(*in_stack_00000040 + 0x1a0))
                                  (in_stack_00000040,uVar6,in_stack_000000d0);
                        uVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                        if ((char)uVar6 == '\0') {
                          uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                          if ((char)uVar6 == '\0') {
                            uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                            if ((char)uVar6 == '\0') {
                              if (DAT_1804b0f00 == 0) {
                                lVar4 = *in_stack_00000040;
                                pcVar1 = *(code **)(lVar4 + 0x390);
                                uVar7 = FUN_1803335f0(in_stack_00000040);
                                uVar6 = DAT_1804b59f0;
                                uVar8 = (**(code **)(lVar4 + 0x518))
                                                  (in_stack_00000040,&stack0x00000590,1);
                                lVar4 = *in_stack_00000040;
                                pcVar2 = *(code **)(lVar4 + 0x390);
                                uVar9 = FUN_1800024f0(in_stack_00000040);
                                uVar10 = FUN_180002550(in_stack_00000040);
                                uVar11 = (**(code **)(lVar4 + 0x518))
                                                   (in_stack_00000040,&stack0x00002518,5);
                                pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                                uVar12 = FUN_1800025a0(in_stack_00000040);
                                uVar12 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar12);
                                (*pcVar2)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                                uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,
                                                  uVar8);
                                DAT_1804b0f00 =
                                     (**(code **)(*in_stack_00000040 + 0xa8))
                                               (in_stack_00000040,uVar6);
                                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
                              }
                              uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                                (in_stack_00000040,DAT_1804b0f00,in_stack_000000a0);
                              uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                                (in_stack_00000040,2,in_stack_00000090,0);
                              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                                (in_stack_00000040,in_stack_000000b8,
                                                 in_stack_000000c0,0x3439);
                              (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
                              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                                (in_stack_00000040,in_stack_00000088,
                                                 in_stack_00000098,
                                                 lVar13 % 0xb7dae ^ in_stack_000000b0 ^
                                                 0x5cbad695d2bddb7a);
                              (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
                              uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                                (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
                              (**(code **)(*in_stack_00000040 + 0x1a0))
                                        (in_stack_00000040,uVar6,in_stack_000000d0);
                              uVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                              if ((char)uVar6 == '\0') {
                                uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                                if ((char)uVar6 == '\0') {
                                  uVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                                  if ((char)uVar6 == '\0') {
                                    uVar6 = 0x6f22bd412a616d7b;
                                    if (DAT_1804b0f08 == 0) {
                                      lVar13 = *in_stack_00000040;
                                      pcVar1 = *(code **)(lVar13 + 0x390);
                                      uVar7 = FUN_1803335f0(in_stack_00000040);
                                      uVar6 = DAT_1804b59f0;
                                      uVar8 = (**(code **)(lVar13 + 0x518))
                                                        (in_stack_00000040,&stack0x0000058e,1);
                                      lVar13 = *in_stack_00000040;
                                      pcVar2 = *(code **)(lVar13 + 0x390);
                                      uVar9 = FUN_1800024f0(in_stack_00000040);
                                      uVar10 = FUN_180002550(in_stack_00000040);
                                      uVar11 = (**(code **)(lVar13 + 0x518))
                                                         (in_stack_00000040,&stack0x00002508,5);
                                      pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                                      uVar12 = FUN_1800025a0(in_stack_00000040);
                                      uVar12 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar12)
                                      ;
                                      (*pcVar2)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                                      uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,
                                                        uVar6,uVar8);
                                      DAT_1804b0f08 =
                                           (**(code **)(*in_stack_00000040 + 0xa8))
                                                     (in_stack_00000040,uVar6);
                                      uVar6 = (**(code **)(*in_stack_00000040 + 0xb8))
                                                        (in_stack_00000040,uVar6);
                                    }
                                    return uVar6;
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
  return uVar6;
}

