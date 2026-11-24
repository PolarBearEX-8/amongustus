/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=125 entry=180458541 blocks=27 maxInstructions=50000 */

/* cut edge: 0x180458541 -> 0x180473ae8 */
/* cut edge: 0x180458683 -> 0x180473ae8 */
/* cut edge: 0x180458699 -> 0x180473ae8 */
/* cut edge: 0x180458865 -> 0x180473ae8 */
/* cut edge: 0x18045895e -> 0x180473ae8 */
/* cut edge: 0x180458974 -> 0x180473ae8 */
/* cut edge: 0x180458b39 -> 0x180473ae8 */
/* cut edge: 0x180458c37 -> 0x180473ae8 */
/* cut edge: 0x180458c65 -> 0x180473ae8 */
/* cut edge: 0x180458e28 -> 0x180473ae8 */
/* cut edge: 0x180458f6b -> 0x180473ae8 */
/* cut edge: 0x180458f81 -> 0x180473ae8 */
/* cut edge: 0x18045914f -> 0x180473ae8 */
/* cut edge: 0x180459244 -> 0x180473ae8 */
/* cut edge: 0x18045925a -> 0x180473ae8 */
/* cut edge: 0x180459430 -> 0x180473ae8 */
/* cut edge: 0x180459524 -> 0x180473ae8 */
/* cut edge: 0x180459524 -> 0x180459547 */
/* target 0x180459547 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00125_180458541(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
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
  undefined8 in_stack_00000120;
  undefined8 in_stack_000001b8;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000090,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_00000058);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000001b8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  (**(code **)(*in_stack_00000048 + 0x140))(in_stack_00000048,uVar6,in_stack_000000b0);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b4500 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000048);
          uVar6 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000038e,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001130,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
          DAT_1804b4500 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b4500,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5a30);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0x58a1d ^ in_stack_000000c8 ^ 0xfd1683012c95cb06);
        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
        (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000120);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b4508 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar7 = FUN_180333550(in_stack_00000048);
                uVar6 = DAT_1804b59f0;
                uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000038c,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001120,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                DAT_1804b4508 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b4508,in_stack_000000a0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x583d);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_00000068 % 0x9dfe9 ^ in_stack_000000c8 ^
                                 0x8c5afd715088a3a4);
              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
              (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000120);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b4510 == 0) {
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar7 = FUN_180333500(in_stack_00000048);
                      uVar6 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000038a,1);
                      lVar2 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003eb0,0x18)
                      ;
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                      DAT_1804b4510 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b4510,in_stack_000000a0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,4,in_stack_00000090,0);
                    (**(code **)(*in_stack_00000048 + 0x570))
                              (in_stack_00000048,uVar7,0,in_stack_00000058);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_000001b8);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_00000068 % 0x19c8e ^ 0x5035e9d9162ec30e);
                    (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_000000c8);
                    (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    (**(code **)(*in_stack_00000048 + 0x140))
                              (in_stack_00000048,uVar6,in_stack_000000b0);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804b4518 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar7 = FUN_180333550(in_stack_00000048);
                            uVar6 = DAT_1804b59f0;
                            uVar8 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,&stack0x00000388,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar9 = FUN_1800024f0(in_stack_00000048);
                            uVar10 = FUN_180002550(in_stack_00000048);
                            uVar11 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00001110,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar12 = FUN_1800025a0(in_stack_00000048);
                            uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8)
                            ;
                            DAT_1804b4518 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b4518,in_stack_000000a0);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000090,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                             0x5a30);
                          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             in_stack_00000068 % 0xda4ba ^ in_stack_000000c8 ^
                                             0xfd1683012c941297);
                          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                          (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar6,in_stack_00000120);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                if (DAT_1804b4520 == 0) {
                                  lVar2 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar2 + 0x390);
                                  uVar7 = FUN_180333550(in_stack_00000048);
                                  uVar6 = DAT_1804b59f0;
                                  uVar8 = (**(code **)(lVar2 + 0x518))
                                                    (in_stack_00000048,&stack0x00000386,1);
                                  lVar2 = *in_stack_00000048;
                                  pcVar3 = *(code **)(lVar2 + 0x390);
                                  uVar9 = FUN_1800024f0(in_stack_00000048);
                                  uVar10 = FUN_180002550(in_stack_00000048);
                                  uVar11 = (**(code **)(lVar2 + 0x518))
                                                     (in_stack_00000048,&stack0x00001100,5);
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar12 = FUN_1800025a0(in_stack_00000048);
                                  uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                                  (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                  uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,
                                                    uVar8);
                                  DAT_1804b4520 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar6);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                                }
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804b4520,in_stack_000000a0
                                                  );
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_00000090,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000b8,
                                                   in_stack_000000c0,0x1193);
                                (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000080,
                                                   in_stack_00000098,
                                                   in_stack_00000068 % 0x56256 ^ in_stack_000000c8 ^
                                                   0xc715bf7f94eaca8a);
                                (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                                (**(code **)(*in_stack_00000048 + 0x188))
                                          (in_stack_00000048,uVar6,in_stack_00000120);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
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
  return;
}

