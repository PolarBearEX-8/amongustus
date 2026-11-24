/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=82 entry=1803858a2 blocks=25 maxInstructions=50000 */

/* cut edge: 0x1803858a2 -> 0x1803351d6 */
/* cut edge: 0x180385994 -> 0x1803351d6 */
/* cut edge: 0x180385afc -> 0x1803351d6 */
/* cut edge: 0x180385be9 -> 0x1803351d6 */
/* cut edge: 0x180385d80 -> 0x1803351d6 */
/* cut edge: 0x180385ecd -> 0x1803351d6 */
/* cut edge: 0x180386035 -> 0x1803351d6 */
/* cut edge: 0x180386127 -> 0x1803351d6 */
/* cut edge: 0x180386294 -> 0x1803351d6 */
/* cut edge: 0x180386381 -> 0x1803351d6 */
/* cut edge: 0x18038651e -> 0x1803351d6 */
/* cut edge: 0x18038666b -> 0x1803351d6 */
/* cut edge: 0x1803867d4 -> 0x1803351d6 */
/* cut edge: 0x1803867d4 -> 0x1803868c1 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803868c1 original byte 0x8b replaced by RET */


void region_00082_1803858a2(undefined8 param_1,undefined8 param_2)

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
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_00000158;
  undefined8 in_stack_000001e0;
  undefined8 in_stack_000001e8;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_00000080,uVar7)
  ;
  (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b2b90 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar7 = FUN_180333550(in_stack_00000048);
        uVar6 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000050a,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000bf8,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
        (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
        DAT_1804b2b90 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b2b90,in_stack_00000078);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
      (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804b2b98 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar7 = FUN_180333500(in_stack_00000048);
            uVar6 = DAT_1804b59f0;
            uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000508,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000063c0,0x18);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar12 = FUN_1800025a0(in_stack_00000048);
            uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
            DAT_1804b2b98 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2b98,in_stack_00000078);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0)
          ;
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_000000b0);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001e8
                            );
          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                             0xdfd1138581807c67);
          (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8
                            );
          (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
          (**(code **)(*in_stack_00000048 + 0x140))(in_stack_00000048,uVar6,in_stack_000001e0);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b2ba0 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar7 = FUN_180333550(in_stack_00000048);
                uVar6 = DAT_1804b59f0;
                uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000506,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000be8,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
                DAT_1804b2ba0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2ba0,in_stack_00000078);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000a8);
              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
              (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b2ba8 == 0) {
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar7 = FUN_180333550(in_stack_00000048);
                    uVar6 = DAT_1804b59f0;
                    uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000504,1);
                    lVar2 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar9 = FUN_1800024f0(in_stack_00000048);
                    uVar10 = FUN_180002550(in_stack_00000048);
                    uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000bd8,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar12 = FUN_1800025a0(in_stack_00000048);
                    uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                    (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                    uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
                    DAT_1804b2ba8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b2ba8,in_stack_00000078);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1193);
                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_00000158);
                  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
                  (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar6,in_stack_00000070);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar5 == '\0') {
                      if (DAT_1804b2bb0 == 0) {
                        lVar2 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar7 = FUN_180333500(in_stack_00000048);
                        uVar6 = DAT_1804b59f0;
                        uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000502,1);
                        lVar2 = *in_stack_00000048;
                        pcVar3 = *(code **)(lVar2 + 0x390);
                        uVar9 = FUN_1800024f0(in_stack_00000048);
                        uVar10 = FUN_180002550(in_stack_00000048);
                        uVar11 = (**(code **)(lVar2 + 0x518))
                                           (in_stack_00000048,&stack0x00006390,0x18);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar12 = FUN_1800025a0(in_stack_00000048);
                        uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                        (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
                        DAT_1804b2bb0 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b2bb0,in_stack_00000078);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,4,in_stack_00000088,0);
                      (**(code **)(*in_stack_00000048 + 0x570))
                                (in_stack_00000048,uVar7,0,in_stack_000000b0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_000001e8);
                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         0xdfd1138581807c67);
                      (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_000000c8);
                      (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
                      (**(code **)(*in_stack_00000048 + 0x140))
                                (in_stack_00000048,uVar6,in_stack_000001e0);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804b2bb8 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar7 = FUN_180333550(in_stack_00000048);
                            uVar6 = DAT_1804b59f0;
                            uVar8 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,&stack0x00000500,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar9 = FUN_1800024f0(in_stack_00000048);
                            uVar10 = FUN_180002550(in_stack_00000048);
                            uVar11 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00000bc8,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar12 = FUN_1800025a0(in_stack_00000048);
                            uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8)
                            ;
                            DAT_1804b2bb8 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b2bb8,in_stack_00000078);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000088,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                             0x5a30);
                          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_000000a8);
                          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
                          (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar6,in_stack_00000070);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
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
  return;
}

