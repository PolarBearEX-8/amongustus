/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=40 entry=1802c0da4 blocks=33 maxInstructions=50000 */

/* cut edge: 0x1802c0da4 -> 0x180333186 */
/* cut edge: 0x1802c0dba -> 0x180333186 */
/* cut edge: 0x1802c0f6e -> 0x180333186 */
/* cut edge: 0x1802c1052 -> 0x180333186 */
/* cut edge: 0x1802c1068 -> 0x180333186 */
/* cut edge: 0x1802c125a -> 0x180333186 */
/* cut edge: 0x1802c1383 -> 0x180333186 */
/* cut edge: 0x1802c13b8 -> 0x180333186 */
/* cut edge: 0x1802c13ce -> 0x180333186 */
/* cut edge: 0x1802c1597 -> 0x180333186 */
/* cut edge: 0x1802c1675 -> 0x180333186 */
/* cut edge: 0x1802c16a8 -> 0x180333186 */
/* cut edge: 0x1802c16be -> 0x180333186 */
/* cut edge: 0x1802c188e -> 0x180333186 */
/* cut edge: 0x1802c1972 -> 0x180333186 */
/* cut edge: 0x1802c19c3 -> 0x180333186 */
/* cut edge: 0x1802c19f6 -> 0x180333186 */
/* cut edge: 0x1802c1a0c -> 0x180333186 */
/* cut edge: 0x1802c1be9 -> 0x180333186 */
/* cut edge: 0x1802c1ccd -> 0x180333186 */
/* cut edge: 0x1802c1d00 -> 0x180333186 */
/* cut edge: 0x1802c1d16 -> 0x180333186 */
/* cut edge: 0x1802c1d2c -> 0x1802c1eef */
/* cut edge: 0x1802c1d2c -> 0x1802c1d81 */
/* target 0x1802c1d81 original byte 0x48 replaced by RET */
/* target 0x1802c1eef original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


undefined8 region_00040_1802c0da4(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  undefined8 in_stack_00000060;
  longlong in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_00000728;
  
  uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
  if ((char)uVar5 == '\0') {
    uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if ((char)uVar5 == '\0') {
      if (DAT_1804b0cf0 == 0) {
        lVar1 = *in_stack_00000040;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_180333500(in_stack_00000040);
        uVar5 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000616,1);
        lVar1 = *in_stack_00000040;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000040);
        uVar9 = FUN_180002550(in_stack_00000040);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00003290,0xd);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000040);
        uVar11 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar11);
        (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
        uVar5 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar5,uVar7);
        DAT_1804b0cf0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
      }
      uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                        (in_stack_00000040,DAT_1804b0cf0,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         in_stack_00000070 % 0x6a2d9 ^ 0xdf3a88030c154317);
      (*pcVar2)(in_stack_00000040,uVar6,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_000000b0);
      (*pcVar2)(in_stack_00000040,uVar6,1,uVar7);
      uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                        (in_stack_00000040,uVar5,in_stack_000000a8,uVar6);
      uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if ((char)uVar5 == '\0') {
        uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
        if ((char)uVar5 == '\0') {
          uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
          if ((char)uVar5 == '\0') {
            if (DAT_1804b0cf8 == 0) {
              lVar1 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_180333500(in_stack_00000040);
              uVar5 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000614,1);
              lVar1 = *in_stack_00000040;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000040);
              uVar10 = FUN_180002550(in_stack_00000040);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00003990,0x29);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000040);
              uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
              (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
              uVar5 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar5,uVar8);
              DAT_1804b0cf8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
            }
            uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,DAT_1804b0cf8,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,4,in_stack_00000090,0);
            (**(code **)(*in_stack_00000040 + 0x570))(in_stack_00000040,uVar7,0,uVar6);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00000728);
            (*pcVar2)(in_stack_00000040,uVar7,1,uVar6);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00000070 % 0xca3d1 ^ 0xdf4d22f3a207d3e7);
            (*pcVar2)(in_stack_00000040,uVar7,2,uVar6);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_000000b0);
            (*pcVar2)(in_stack_00000040,uVar7,3,uVar6);
            uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,uVar5,in_stack_000000a8,uVar7);
            uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if ((char)uVar5 == '\0') {
              uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,in_stack_00000048,in_stack_00000060,uVar6);
              uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if ((char)uVar5 == '\0') {
                uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                if ((char)uVar5 == '\0') {
                  uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                  if ((char)uVar5 == '\0') {
                    if (DAT_1804b0d00 == 0) {
                      lVar1 = *in_stack_00000040;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_1803335a0(in_stack_00000040);
                      uVar5 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000612,1);
                      lVar1 = *in_stack_00000040;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000040);
                      uVar10 = FUN_180002550(in_stack_00000040);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00003780,0x16)
                      ;
                      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000040);
                      uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                      (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                      uVar5 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar5,uVar8);
                      DAT_1804b0d00 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
                    }
                    uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,DAT_1804b0d00,in_stack_000000a0);
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1019)
                    ;
                    (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                       in_stack_00000070 % 0xcb5d8 ^ in_stack_000000b0 ^
                                       0x4dd15e45d26579d3);
                    (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,uVar5,in_stack_000000a8,uVar7);
                    uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if ((char)uVar5 == '\0') {
                      uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                        (in_stack_00000040,uVar6,in_stack_00000060,uVar7);
                      uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                      if ((char)uVar5 == '\0') {
                        uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                        if ((char)uVar5 == '\0') {
                          uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                          if ((char)uVar5 == '\0') {
                            if (DAT_1804b0d08 == 0) {
                              lVar1 = *in_stack_00000040;
                              pcVar2 = *(code **)(lVar1 + 0x390);
                              uVar7 = FUN_1803335a0(in_stack_00000040);
                              uVar5 = DAT_1804b59f0;
                              uVar8 = (**(code **)(lVar1 + 0x518))
                                                (in_stack_00000040,&stack0x00000610,1);
                              lVar1 = *in_stack_00000040;
                              pcVar3 = *(code **)(lVar1 + 0x390);
                              uVar9 = FUN_1800024f0(in_stack_00000040);
                              uVar10 = FUN_180002550(in_stack_00000040);
                              uVar11 = (**(code **)(lVar1 + 0x518))
                                                 (in_stack_00000040,&stack0x00003750,0x16);
                              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                              uVar12 = FUN_1800025a0(in_stack_00000040);
                              uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                              (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                              uVar5 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar5,
                                                uVar8);
                              DAT_1804b0d08 =
                                   (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5)
                              ;
                              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
                            }
                            uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                              (in_stack_00000040,DAT_1804b0d08,in_stack_000000a0);
                            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                              (in_stack_00000040,2,in_stack_00000090,0);
                            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0
                                               ,0x4ad3);
                            (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                              (in_stack_00000040,in_stack_00000088,in_stack_00000098
                                               ,in_stack_00000070 % 0x333d1 ^ in_stack_000000b0 ^
                                                0x7fc61ae5d8a7000b);
                            (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                            uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                              (in_stack_00000040,uVar5,in_stack_000000a8,uVar7);
                            uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                            if ((char)uVar5 == '\0') {
                              pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                              uVar5 = FUN_18001ed70(in_stack_00000040);
                              uVar8 = FUN_18001edd0(in_stack_00000040);
                              uVar7 = (*pcVar2)(in_stack_00000040,uVar5,uVar8,uVar7);
                              uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                              if ((char)uVar5 == '\0') {
                                uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                                  (in_stack_00000040,uVar6,in_stack_00000060,uVar7);
                                uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040)
                                ;
                                if ((char)uVar5 == '\0') {
                                  uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                                  if ((char)uVar5 == '\0') {
                                    uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                                    if ((char)uVar5 == '\0') {
                                      if (DAT_1804b0d10 == 0) {
                                        lVar1 = *in_stack_00000040;
                                        pcVar2 = *(code **)(lVar1 + 0x390);
                                        uVar7 = FUN_1803335a0(in_stack_00000040);
                                        uVar5 = DAT_1804b59f0;
                                        uVar8 = (**(code **)(lVar1 + 0x518))
                                                          (in_stack_00000040,&stack0x0000060e,1);
                                        lVar1 = *in_stack_00000040;
                                        pcVar3 = *(code **)(lVar1 + 0x390);
                                        uVar9 = FUN_1800024f0(in_stack_00000040);
                                        uVar10 = FUN_180002550(in_stack_00000040);
                                        uVar11 = (**(code **)(lVar1 + 0x518))
                                                           (in_stack_00000040,&stack0x00003720,0x16)
                                        ;
                                        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                                        uVar12 = FUN_1800025a0(in_stack_00000040);
                                        uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,
                                                           uVar12);
                                        (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                                        uVar5 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,
                                                          uVar5,uVar8);
                                        DAT_1804b0d10 =
                                             (**(code **)(*in_stack_00000040 + 0xa8))
                                                       (in_stack_00000040,uVar5);
                                        (**(code **)(*in_stack_00000040 + 0xb8))
                                                  (in_stack_00000040,uVar5);
                                      }
                                      uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                                        (in_stack_00000040,DAT_1804b0d10,
                                                         in_stack_000000a0);
                                      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                                        (in_stack_00000040,2,in_stack_00000090,0);
                                      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                                        (in_stack_00000040,in_stack_000000b8,
                                                         in_stack_000000c0,0x4b59);
                                      (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                                      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                                        (in_stack_00000040,in_stack_00000088,
                                                         in_stack_00000098,
                                                         in_stack_00000070 % 0x7861a ^
                                                         in_stack_000000b0 ^ 0x77f9c3b640950864);
                                      (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                                      uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                                        (in_stack_00000040,uVar5,in_stack_000000a8,
                                                         uVar7);
                                      uVar5 = (**(code **)(*in_stack_00000040 + 0x720))
                                                        (in_stack_00000040);
                                      if ((char)uVar5 == '\0') {
                                        (**(code **)(*in_stack_00000040 + 0x110))
                                                  (in_stack_00000040,uVar6,in_stack_00000060,uVar7);
                                        uVar5 = (**(code **)(*in_stack_00000040 + 0x720))
                                                          (in_stack_00000040);
                                        if ((char)uVar5 == '\0') {
                                          uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                                          if ((char)uVar5 == '\0') {
                                            uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                                            if ((char)uVar5 == '\0') {
                                              if (DAT_1804b0d18 == 0) {
                                                return 0x4413d71a13d6deae;
                                              }
                                              return 0x4413d71a13d6deae;
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
  return uVar5;
}

