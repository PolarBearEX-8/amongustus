/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=83 entry=1803868c1 blocks=26 maxInstructions=50000 */

/* cut edge: 0x1803868c1 -> 0x1803351d6 */
/* cut edge: 0x18038690c -> 0x1803351d6 */
/* cut edge: 0x180386ad8 -> 0x1803351d6 */
/* cut edge: 0x180386c28 -> 0x1803351d6 */
/* cut edge: 0x180386c5d -> 0x1803351d6 */
/* cut edge: 0x180386cb1 -> 0x1803351d6 */
/* cut edge: 0x180386e2c -> 0x1803351d6 */
/* cut edge: 0x180386f79 -> 0x1803351d6 */
/* cut edge: 0x1803870e2 -> 0x1803351d6 */
/* cut edge: 0x1803871d4 -> 0x1803351d6 */
/* cut edge: 0x18038733c -> 0x1803351d6 */
/* cut edge: 0x180387429 -> 0x1803351d6 */
/* cut edge: 0x1803875c0 -> 0x1803351d6 */
/* cut edge: 0x18038770d -> 0x1803351d6 */
/* cut edge: 0x180387723 -> 0x180387876 */
/* cut edge: 0x180387733 -> 0x180387876 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180387876 original byte 0x48 replaced by RET */


void region_00083_1803868c1(void)

{
  longlong lVar1;
  code *pcVar2;
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
  undefined8 uVar13;
  uint unaff_EBX;
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
  uint in_stack_000000c0;
  undefined8 in_stack_000000c8;
  uint in_stack_000000f0;
  uint in_stack_000000f8;
  char in_stack_00000110;
  undefined8 in_stack_00000140;
  undefined8 in_stack_000001d8;
  undefined8 in_stack_000001e0;
  undefined8 in_stack_000001e8;
  undefined8 in_stack_00000848;
  undefined8 in_stack_00000850;
  undefined8 in_stack_00000870;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x580))
                    (in_stack_00000048,
                     unaff_EBX & (int)in_stack_00000110 |
                     in_stack_000000f0 | in_stack_000000c0 | in_stack_000000f8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b2bc0 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar7 = FUN_180333500(in_stack_00000048);
        uVar13 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004fe,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000069f0,0x33);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
        (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar13,uVar8);
        DAT_1804b2bc0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
      }
      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2bc0,in_stack_00000078);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000088,0);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_000000b0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000850);
      (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,2,uVar6);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc89742b96b10c1);
      (*pcVar2)(in_stack_00000048,uVar7,3,uVar6);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
      (*pcVar2)(in_stack_00000048,uVar7,4,uVar6);
      (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar13,in_stack_00000080,uVar7);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        uVar6 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,in_stack_000001d8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          (**(code **)(*in_stack_00000048 + 0x2d8))
                    (in_stack_00000048,uVar6,in_stack_000001d8,in_stack_00000870,in_stack_00000848);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b2bc8 == 0) {
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar13 = FUN_180333500(in_stack_00000048);
                uVar6 = DAT_1804b59f0;
                uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004fc,1);
                lVar1 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000048);
                uVar9 = FUN_180002550(in_stack_00000048);
                uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00006360,0x18);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000048);
                uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
                (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,uVar7);
                DAT_1804b2bc8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2bc8,in_stack_00000078);
              uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                 (in_stack_00000048,4,in_stack_00000088,0);
              (**(code **)(*in_stack_00000048 + 0x570))
                        (in_stack_00000048,uVar13,0,in_stack_000000b0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000001e8);
              (*pcVar2)(in_stack_00000048,uVar13,1,uVar7);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 0xdfd1138581807c67);
              (*pcVar2)(in_stack_00000048,uVar13,2,uVar7);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8);
              (*pcVar2)(in_stack_00000048,uVar13,3,uVar7);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_00000080,uVar13);
              (**(code **)(*in_stack_00000048 + 0x140))(in_stack_00000048,uVar6,in_stack_000001e0);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b2bd0 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar13 = FUN_180333550(in_stack_00000048);
                    uVar6 = DAT_1804b59f0;
                    uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004fa,1);
                    lVar1 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bb8,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
                    (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,uVar7);
                    DAT_1804b2bd0 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b2bd0,in_stack_00000078);
                  uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                     (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
                  (*pcVar2)(in_stack_00000048,uVar13,0,uVar7);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000a8);
                  (*pcVar2)(in_stack_00000048,uVar13,1,uVar7);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_00000080,uVar13);
                  (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar6,in_stack_00000070);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar5 == '\0') {
                      if (DAT_1804b2bd8 == 0) {
                        lVar1 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar1 + 0x390);
                        uVar13 = FUN_180333550(in_stack_00000048);
                        uVar6 = DAT_1804b59f0;
                        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004f8,1);
                        lVar1 = *in_stack_00000048;
                        pcVar3 = *(code **)(lVar1 + 0x390);
                        uVar8 = FUN_1800024f0(in_stack_00000048);
                        uVar9 = FUN_180002550(in_stack_00000048);
                        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000ba8,5);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar11 = FUN_1800025a0(in_stack_00000048);
                        uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
                        (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                        uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,uVar7);
                        DAT_1804b2bd8 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b2bd8,in_stack_00000078);
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                         (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                         0x5dd1);
                      (*pcVar2)(in_stack_00000048,uVar13,0,uVar7);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_00000140);
                      (*pcVar2)(in_stack_00000048,uVar13,1,uVar7);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar6,in_stack_00000080,uVar13);
                      (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar6,in_stack_00000070);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804b2be0 == 0) {
                            lVar1 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar1 + 0x390);
                            uVar13 = FUN_180333500(in_stack_00000048);
                            uVar6 = DAT_1804b59f0;
                            uVar7 = (**(code **)(lVar1 + 0x518))
                                              (in_stack_00000048,&stack0x000004f6,1);
                            lVar1 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar1 + 0x390);
                            uVar8 = FUN_1800024f0(in_stack_00000048);
                            uVar9 = FUN_180002550(in_stack_00000048);
                            uVar10 = (**(code **)(lVar1 + 0x518))
                                               (in_stack_00000048,&stack0x00006330,0x18);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar11 = FUN_1800025a0(in_stack_00000048);
                            uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
                            (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                            uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,uVar7
                                             );
                            DAT_1804b2be0 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b2be0,in_stack_00000078);
                          uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                             (in_stack_00000048,4,in_stack_00000088,0);
                          (**(code **)(*in_stack_00000048 + 0x570))
                                    (in_stack_00000048,uVar13,0,in_stack_000000b0);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_000001e8);
                          (*pcVar2)(in_stack_00000048,uVar13,1,uVar7);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             0xdfd1138581807c67);
                          (*pcVar2)(in_stack_00000048,uVar13,2,uVar7);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_000000c8);
                          (*pcVar2)(in_stack_00000048,uVar13,3,uVar7);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,in_stack_00000080,uVar13);
                          (**(code **)(*in_stack_00000048 + 0x140))
                                    (in_stack_00000048,uVar6,in_stack_000001e0);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              if (DAT_1804b2be8 == 0) {
                                lVar1 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar1 + 0x390);
                                uVar13 = FUN_180333550(in_stack_00000048);
                                uVar6 = DAT_1804b59f0;
                                uVar7 = (**(code **)(lVar1 + 0x518))
                                                  (in_stack_00000048,&stack0x000004f4,1);
                                lVar1 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar1 + 0x390);
                                uVar8 = FUN_1800024f0(in_stack_00000048);
                                uVar9 = FUN_180002550(in_stack_00000048);
                                uVar10 = (**(code **)(lVar1 + 0x518))
                                                   (in_stack_00000048,&stack0x00000b98,5);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar11 = FUN_1800025a0(in_stack_00000048);
                                uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
                                (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,
                                                  uVar7);
                                DAT_1804b2be8 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar6);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                              }
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
  return;
}

