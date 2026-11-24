/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=127 entry=18045a5cf blocks=28 maxInstructions=50000 */

/* cut edge: 0x18045a5cf -> 0x180473ae8 */
/* cut edge: 0x18045a5e5 -> 0x180473ae8 */
/* cut edge: 0x18045a7b8 -> 0x180473ae8 */
/* cut edge: 0x18045a8ac -> 0x180473ae8 */
/* cut edge: 0x18045a8da -> 0x180473ae8 */
/* cut edge: 0x18045aa9a -> 0x180473ae8 */
/* cut edge: 0x18045abdc -> 0x180473ae8 */
/* cut edge: 0x18045abf2 -> 0x180473ae8 */
/* cut edge: 0x18045adc3 -> 0x180473ae8 */
/* cut edge: 0x18045aebc -> 0x180473ae8 */
/* cut edge: 0x18045aed2 -> 0x180473ae8 */
/* cut edge: 0x18045b0a5 -> 0x180473ae8 */
/* cut edge: 0x18045b199 -> 0x180473ae8 */
/* cut edge: 0x18045b1c7 -> 0x180473ae8 */
/* cut edge: 0x18045b387 -> 0x180473ae8 */
/* cut edge: 0x18045b4c9 -> 0x180473ae8 */
/* cut edge: 0x18045b4df -> 0x180473ae8 */
/* cut edge: 0x18045b4f5 -> 0x18045b6b5 */
/* cut edge: 0x18045b4f5 -> 0x18045b54e */
/* target 0x18045b54e original byte 0x44 replaced by RET */
/* target 0x18045b6b5 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


undefined8 region_00127_18045a5cf(void)

{
  ulonglong uVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  code *pcVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong unaff_R12;
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
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)uVar6 == '\0') {
    uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar6 == '\0') {
      if (DAT_1804b4550 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar7 = FUN_180333550(in_stack_00000048);
        uVar6 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000037a,1);
        lVar2 = *in_stack_00000048;
        pcVar4 = *(code **)(lVar2 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000010d0,5);
        pcVar5 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar12 = (*pcVar5)(in_stack_00000048,in_stack_00000088,uVar12);
        (*pcVar4)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        unaff_R12 = 0x5035e9d91624950a;
        uVar6 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
        DAT_1804b4550 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b4550,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5dd1);
      (*pcVar3)(in_stack_00000048,uVar7,0,uVar8);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         in_stack_00000068 % 0x2d711 ^ in_stack_000000c8 ^ 0xc18ac7c5c65ff18f);
      (*pcVar3)(in_stack_00000048,uVar7,1,uVar8);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000120);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)uVar6 == '\0') {
        uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)uVar6 == '\0') {
          uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)uVar6 == '\0') {
            uVar1 = unaff_R12 + 0x9c626;
            if (DAT_1804b4558 == 0) {
              lVar2 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar2 + 0x390);
              uVar7 = FUN_180333500(in_stack_00000048);
              uVar6 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000378,1);
              lVar2 = *in_stack_00000048;
              pcVar4 = *(code **)(lVar2 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003e20,0x18);
              pcVar5 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar5)(in_stack_00000048,in_stack_00000088,uVar12);
              (*pcVar4)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              unaff_R12 = 0x5035e9d91624950a;
              uVar6 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
              DAT_1804b4558 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
            }
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b4558,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,4,in_stack_00000090,0);
            (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_00000058);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_000001b8);
            (*pcVar3)(in_stack_00000048,uVar7,1,uVar8);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               uVar1 ^ in_stack_00000068 % 0x2a038);
            (*pcVar3)(in_stack_00000048,uVar7,2,uVar8);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_000000c8);
            (*pcVar3)(in_stack_00000048,uVar7,3,uVar8);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
            (**(code **)(*in_stack_00000048 + 0x140))(in_stack_00000048,uVar6,in_stack_000000b0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)uVar6 == '\0') {
              uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)uVar6 == '\0') {
                uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)uVar6 == '\0') {
                  if (DAT_1804b4560 == 0) {
                    lVar2 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar7 = FUN_180333550(in_stack_00000048);
                    uVar6 = DAT_1804b59f0;
                    uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000376,1);
                    lVar2 = *in_stack_00000048;
                    pcVar4 = *(code **)(lVar2 + 0x390);
                    uVar9 = FUN_1800024f0(in_stack_00000048);
                    uVar10 = FUN_180002550(in_stack_00000048);
                    uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000010c0,5);
                    pcVar5 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar12 = FUN_1800025a0(in_stack_00000048);
                    uVar12 = (*pcVar5)(in_stack_00000048,in_stack_00000088,uVar12);
                    (*pcVar4)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                    uVar6 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                    DAT_1804b4560 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                    unaff_R12 = 0x5035e9d91624950a;
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b4560,in_stack_000000a0);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5a30);
                  (*pcVar3)(in_stack_00000048,uVar7,0,uVar8);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     in_stack_00000068 % 0x1222c ^ in_stack_000000c8 ^
                                     0xfd1683012c9179c3);
                  (*pcVar3)(in_stack_00000048,uVar7,1,uVar8);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                  (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar6,in_stack_00000120);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)uVar6 == '\0') {
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)uVar6 == '\0') {
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)uVar6 == '\0') {
                        if (DAT_1804b4568 == 0) {
                          lVar2 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar2 + 0x390);
                          uVar7 = FUN_180333550(in_stack_00000048);
                          uVar6 = DAT_1804b59f0;
                          uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000374,1)
                          ;
                          lVar2 = *in_stack_00000048;
                          pcVar4 = *(code **)(lVar2 + 0x390);
                          uVar9 = FUN_1800024f0(in_stack_00000048);
                          uVar10 = FUN_180002550(in_stack_00000048);
                          uVar11 = (**(code **)(lVar2 + 0x518))
                                             (in_stack_00000048,&stack0x000010b0,5);
                          pcVar5 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar12 = FUN_1800025a0(in_stack_00000048);
                          uVar12 = (*pcVar5)(in_stack_00000048,in_stack_00000088,uVar12);
                          (*pcVar4)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                          unaff_R12 = 0x5035e9d91624950a;
                          uVar6 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                          DAT_1804b4568 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                        }
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b4568,in_stack_000000a0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x583d);
                        (*pcVar3)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           in_stack_00000068 % 0x2056a ^ in_stack_000000c8 ^
                                           0x8c5afd715089bed6);
                        (*pcVar3)(in_stack_00000048,uVar7,1,uVar8);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                        (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar6,in_stack_00000120);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)uVar6 == '\0') {
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)uVar6 == '\0') {
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)uVar6 == '\0') {
                              if (DAT_1804b4570 == 0) {
                                lVar2 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar2 + 0x390);
                                uVar7 = FUN_180333500(in_stack_00000048);
                                uVar6 = DAT_1804b59f0;
                                uVar8 = (**(code **)(lVar2 + 0x518))
                                                  (in_stack_00000048,&stack0x00000372,1);
                                lVar2 = *in_stack_00000048;
                                pcVar4 = *(code **)(lVar2 + 0x390);
                                uVar9 = FUN_1800024f0(in_stack_00000048);
                                uVar10 = FUN_180002550(in_stack_00000048);
                                uVar11 = (**(code **)(lVar2 + 0x518))
                                                   (in_stack_00000048,&stack0x00003df0,0x18);
                                pcVar5 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar12 = FUN_1800025a0(in_stack_00000048);
                                uVar12 = (*pcVar5)(in_stack_00000048,in_stack_00000088,uVar12);
                                (*pcVar4)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                uVar6 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,
                                                  uVar8);
                                DAT_1804b4570 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar6);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                              }
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b4570,in_stack_000000a0);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,4,in_stack_00000090,0);
                              (**(code **)(*in_stack_00000048 + 0x570))
                                        (in_stack_00000048,uVar7,0,in_stack_00000058);
                              pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,in_stack_000001b8);
                              (*pcVar3)(in_stack_00000048,uVar7,1,uVar8);
                              pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 unaff_R12 + 0xaba96U ^ in_stack_00000068 % 0xaaae8)
                              ;
                              (*pcVar3)(in_stack_00000048,uVar7,2,uVar8);
                              pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,in_stack_000000c8);
                              (*pcVar3)(in_stack_00000048,uVar7,3,uVar8);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                              (**(code **)(*in_stack_00000048 + 0x140))
                                        (in_stack_00000048,uVar6,in_stack_000000b0);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if ((char)uVar6 == '\0') {
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)uVar6 == '\0') {
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)uVar6 == '\0') {
                                    if (DAT_1804b4578 == 0) {
                                      return 0xfd1683012c905674;
                                    }
                                    return 0xfd1683012c905674;
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

