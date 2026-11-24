/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=128 entry=18045b54e blocks=28 maxInstructions=50000 */

/* cut edge: 0x18045b6b5 -> 0x180473ae8 */
/* cut edge: 0x18045b7aa -> 0x180473ae8 */
/* cut edge: 0x18045b7c0 -> 0x180473ae8 */
/* cut edge: 0x18045b9a6 -> 0x180473ae8 */
/* cut edge: 0x18045ba9a -> 0x180473ae8 */
/* cut edge: 0x18045babc -> 0x180473ae8 */
/* cut edge: 0x18045bc86 -> 0x180473ae8 */
/* cut edge: 0x18045bdc8 -> 0x180473ae8 */
/* cut edge: 0x18045bdde -> 0x180473ae8 */
/* cut edge: 0x18045bfb6 -> 0x180473ae8 */
/* cut edge: 0x18045c0aa -> 0x180473ae8 */
/* cut edge: 0x18045c0f4 -> 0x180473ae8 */
/* cut edge: 0x18045c10a -> 0x180473ae8 */
/* cut edge: 0x18045c321 -> 0x180473ae8 */
/* cut edge: 0x18045c461 -> 0x180473ae8 */
/* cut edge: 0x18045c496 -> 0x180473ae8 */
/* cut edge: 0x18045c4ea -> 0x180473ae8 */
/* cut edge: 0x18045c500 -> 0x180473ae8 */
/* cut edge: 0x18045c516 -> 0x18045c6e7 */
/* cut edge: 0x18045c516 -> 0x18045c56f */
/* target 0x18045c56f original byte 0x48 replaced by RET */
/* target 0x18045c6e7 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


longlong region_00128_18045b54e(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  longlong lVar11;
  undefined8 uVar12;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  code *pcStack00000000000000e0;
  undefined8 in_stack_000000f0;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00000168;
  undefined8 in_stack_000001b8;
  undefined8 in_stack_000007a8;
  undefined8 in_stack_000007b0;
  
  lVar11 = *in_stack_00000048;
  pcStack00000000000000e0 = *(code **)(lVar11 + 0x390);
  uVar4 = FUN_180333550(in_stack_00000048);
  uVar10 = DAT_1804b59f0;
  uVar5 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00000370,1);
  lVar11 = *in_stack_00000048;
  pcVar1 = *(code **)(lVar11 + 0x390);
  uVar6 = FUN_1800024f0(in_stack_00000048);
  uVar7 = FUN_180002550(in_stack_00000048);
  uVar8 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x000010a0,5);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
  uVar9 = FUN_1800025a0(in_stack_00000048);
  uVar9 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar9);
  (*pcVar1)(in_stack_00000048,uVar6,uVar7,uVar8,uVar9);
  uVar10 = (*pcStack00000000000000e0)(in_stack_00000048,in_stack_00000088,uVar4,uVar10,uVar5);
  DAT_1804b4578 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b4578,in_stack_000000a0);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5a30);
  (*pcVar1)(in_stack_00000048,uVar4,0,uVar5);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar4,1,uVar5);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar10,in_stack_000000a8,uVar4);
  (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000120);
  lVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar11 == '\0') {
    lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar11 == '\0') {
      lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)lVar11 == '\0') {
        if (DAT_1804b4580 == 0) {
          lVar11 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar11 + 0x390);
          pcStack00000000000000e0 = (code *)FUN_180333550(in_stack_00000048);
          uVar10 = DAT_1804b59f0;
          uVar4 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x0000036e,1);
          lVar11 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar11 + 0x390);
          uVar5 = FUN_1800024f0(in_stack_00000048);
          uVar6 = FUN_180002550(in_stack_00000048);
          uVar7 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00001090,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar8 = FUN_1800025a0(in_stack_00000048);
          uVar8 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar8);
          (*pcVar2)(in_stack_00000048,uVar5,uVar6,uVar7,uVar8);
          uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,pcStack00000000000000e0,uVar10,
                             uVar4);
          DAT_1804b4580 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
        }
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b4580,in_stack_000000a0);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1193);
        (*pcVar1)(in_stack_00000048,uVar4,0,uVar5);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0xdbbf0 ^ in_stack_000000c8 ^ 0xc715bf7f94ec29ca);
        (*pcVar1)(in_stack_00000048,uVar4,1,uVar5);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar10,in_stack_000000a8,uVar4);
        (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000120);
        lVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)lVar11 == '\0') {
          lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)lVar11 == '\0') {
            lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)lVar11 == '\0') {
              if (DAT_1804b4588 == 0) {
                lVar11 = *in_stack_00000048;
                pcStack00000000000000e0 = *(code **)(lVar11 + 0x390);
                uVar4 = FUN_180333500(in_stack_00000048);
                uVar10 = DAT_1804b59f0;
                uVar5 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x0000036c,1);
                lVar11 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar11 + 0x390);
                uVar6 = FUN_1800024f0(in_stack_00000048);
                uVar7 = FUN_180002550(in_stack_00000048);
                uVar8 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00003dc0,0x18);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                uVar9 = FUN_1800025a0(in_stack_00000048);
                uVar9 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar9);
                (*pcVar1)(in_stack_00000048,uVar6,uVar7,uVar8,uVar9);
                uVar10 = (*pcStack00000000000000e0)
                                   (in_stack_00000048,in_stack_00000088,uVar4,uVar10,uVar5);
                DAT_1804b4588 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
              }
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b4588,in_stack_000000a0);
              uVar4 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,4,in_stack_00000090,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar4,0,in_stack_00000058)
              ;
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_000001b8);
              (*pcVar1)(in_stack_00000048,uVar4,1,uVar5);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_00000068 % 0xc0285 ^ 0x5035e9d9162eed75);
              (*pcVar1)(in_stack_00000048,uVar4,2,uVar5);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar4,3,uVar5);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar10,in_stack_000000a8,uVar4);
              (**(code **)(*in_stack_00000048 + 0x140))(in_stack_00000048,uVar10,in_stack_000000b0);
              lVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)lVar11 == '\0') {
                lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)lVar11 == '\0') {
                  lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)lVar11 == '\0') {
                    if (DAT_1804b4590 == 0) {
                      lVar11 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar11 + 0x390);
                      pcStack00000000000000e0 = (code *)FUN_180333550(in_stack_00000048);
                      uVar10 = DAT_1804b59f0;
                      uVar4 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x0000036a,1);
                      lVar11 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar11 + 0x390);
                      uVar5 = FUN_1800024f0(in_stack_00000048);
                      uVar6 = FUN_180002550(in_stack_00000048);
                      uVar7 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00001080,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar8 = FUN_1800025a0(in_stack_00000048);
                      uVar8 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar8);
                      (*pcVar2)(in_stack_00000048,uVar5,uVar6,uVar7,uVar8);
                      uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,pcStack00000000000000e0
                                         ,uVar10,uVar4);
                      DAT_1804b4590 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                    }
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b4590,in_stack_000000a0);
                    uVar4 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5a30)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar4,0,uVar5);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_00000068 % 0xd0000 ^ in_stack_000000c8 ^
                                       0xfd1683012c9afb73);
                    (*pcVar1)(in_stack_00000048,uVar4,1,uVar5);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar10,in_stack_000000a8,uVar4);
                    (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar10,in_stack_00000120);
                    lVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)lVar11 == '\0') {
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048);
                      lVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)lVar11 == '\0') {
                        lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)lVar11 == '\0') {
                          lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)lVar11 == '\0') {
                            if (DAT_1804b4598 == 0) {
                              lVar11 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar11 + 0x390);
                              uVar5 = FUN_180333500(in_stack_00000048);
                              uVar4 = DAT_1804b59f0;
                              uVar6 = (**(code **)(lVar11 + 0x518))
                                                (in_stack_00000048,&stack0x00000368,1);
                              lVar11 = *in_stack_00000048;
                              pcStack00000000000000e0 = *(code **)(lVar11 + 0x390);
                              uVar7 = FUN_1800024f0(in_stack_00000048);
                              uVar8 = FUN_180002550(in_stack_00000048);
                              uVar9 = (**(code **)(lVar11 + 0x518))
                                                (in_stack_00000048,&stack0x00003fb0,0x33);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar12 = FUN_1800025a0(in_stack_00000048);
                              uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar12);
                              (*pcStack00000000000000e0)(in_stack_00000048,uVar7,uVar8,uVar9,uVar12)
                              ;
                              uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar5,uVar4,
                                                uVar6);
                              DAT_1804b4598 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
                            }
                            uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b4598,in_stack_000000a0);
                            uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,5,in_stack_00000090,0);
                            (**(code **)(*in_stack_00000048 + 0x570))
                                      (in_stack_00000048,uVar5,0,in_stack_00000058);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000080,in_stack_00000098
                                               ,in_stack_000007b0);
                            (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
                            (**(code **)(*in_stack_00000048 + 0x570))
                                      (in_stack_00000048,uVar5,2,uVar10);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000080,
                                                in_stack_00000098,
                                                in_stack_00000068 % 0x650e2 ^ 0x502c6d1e2ec119a4);
                            (*pcVar1)(in_stack_00000048,uVar5,3,uVar10);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000080,
                                                in_stack_00000098,in_stack_000000c8);
                            (*pcVar1)(in_stack_00000048,uVar5,4,uVar10);
                            (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
                            lVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)lVar11 == '\0') {
                              uVar10 = (**(code **)(*in_stack_00000048 + 0xd8))
                                                 (in_stack_00000048,in_stack_00000168);
                              lVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if ((char)lVar11 == '\0') {
                                (**(code **)(*in_stack_00000048 + 0x2d8))
                                          (in_stack_00000048,uVar10,in_stack_00000168,
                                           in_stack_000000f0,in_stack_000007a8);
                                lVar11 = (**(code **)(*in_stack_00000048 + 0x720))
                                                   (in_stack_00000048);
                                if ((char)lVar11 == '\0') {
                                  lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)lVar11 == '\0') {
                                    lVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if ((char)lVar11 == '\0') {
                                      lVar11 = (in_stack_00000068 / 0xc7ff1) * 0xc7ff1;
                                      if (DAT_1804b45a0 == 0) {
                                        return lVar11;
                                      }
                                      return lVar11;
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
  return lVar11;
}

