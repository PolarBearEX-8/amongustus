/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=85 entry=180388890 blocks=31 maxInstructions=50000 */

/* cut edge: 0x180388890 -> 0x1803351d6 */
/* cut edge: 0x1803888e2 -> 0x1803351d6 */
/* cut edge: 0x180388aae -> 0x1803351d6 */
/* cut edge: 0x180388bfe -> 0x1803351d6 */
/* cut edge: 0x180388c33 -> 0x1803351d6 */
/* cut edge: 0x180388c87 -> 0x1803351d6 */
/* cut edge: 0x180388e0c -> 0x1803351d6 */
/* cut edge: 0x180388f92 -> 0x1803351d6 */
/* cut edge: 0x1803890fa -> 0x1803351d6 */
/* cut edge: 0x1803891e8 -> 0x1803351d6 */
/* cut edge: 0x180389363 -> 0x1803351d6 */
/* cut edge: 0x180389481 -> 0x1803351d6 */
/* cut edge: 0x1803894be -> 0x1803351d6 */
/* cut edge: 0x18038964b -> 0x1803351d6 */
/* cut edge: 0x180389733 -> 0x1803351d6 */
/* cut edge: 0x180389766 -> 0x1803898c4 */
/* cut edge: 0x180389776 -> 0x1803898c4 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803898c4 original byte 0x48 replaced by RET */


void region_00085_180388890(void)

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
  uint unaff_EDI;
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
  undefined8 in_stack_000000b0;
  uint in_stack_000000c0;
  ulonglong in_stack_000000c8;
  uint in_stack_000000f0;
  char in_stack_000000f8;
  undefined8 in_stack_00000118;
  uint in_stack_00000140;
  uint uStack0000000000000150;
  undefined8 in_stack_00000158;
  undefined8 in_stack_00000178;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_000001d0;
  undefined8 in_stack_000001d8;
  undefined8 in_stack_00000848;
  undefined8 in_stack_00000850;
  undefined8 in_stack_00000870;
  undefined4 in_stack_00000884;
  undefined8 in_stack_000009f8;
  undefined8 in_stack_00000a00;
  undefined8 in_stack_00000a28;
  
  uStack0000000000000150 =
       unaff_EDI & (int)in_stack_000000f8 |
       in_stack_00000140 | in_stack_000000c0 | in_stack_000000f0;
  uVar6 = (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
    if (DAT_1804b2c20 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_180333500(in_stack_00000048);
      uVar13 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004e6,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00006980,0x33);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar13,uVar8);
      DAT_1804b2c20 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2c20,in_stack_00000078);
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
        if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0'))
        {
          if (DAT_1804b2c28 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar13 = FUN_180333500(in_stack_00000048);
            uVar6 = DAT_1804b59f0;
            uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004e4,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002d60,0x19);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
            (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,uVar7);
            DAT_1804b2c28 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2c28,in_stack_00000078);
          uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,5,in_stack_00000088,0);
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar13,0,in_stack_00000118);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000a00,in_stack_000009f8,in_stack_00000884
                            );
          (*pcVar2)(in_stack_00000048,uVar13,1,uVar7);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000a28
                            );
          (*pcVar2)(in_stack_00000048,uVar13,2,uVar7);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                             0xdfc3f853a1ea6f63);
          (*pcVar2)(in_stack_00000048,uVar13,3,uVar7);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8
                            );
          (*pcVar2)(in_stack_00000048,uVar13,4,uVar7);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar6,in_stack_00000080,uVar13);
          (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((cVar5 == '\0') &&
             (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
            if (DAT_1804b2c30 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar13 = FUN_180333550(in_stack_00000048);
              uVar6 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004e2,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b48,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
              (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,uVar7);
              DAT_1804b2c30 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
            }
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b2c30,in_stack_00000078);
            uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_00000088,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x79b1);
            (*pcVar2)(in_stack_00000048,uVar13,0,uVar7);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000001d0);
            (*pcVar2)(in_stack_00000048,uVar13,1,uVar7);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar6,in_stack_00000080,uVar13);
            (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((cVar5 == '\0') &&
               (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
              if (DAT_1804b2c38 == 0) {
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar13 = FUN_180333550(in_stack_00000048);
                uVar6 = DAT_1804b59f0;
                uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004e0,1);
                lVar1 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000048);
                uVar9 = FUN_180002550(in_stack_00000048);
                uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b38,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000048);
                uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
                (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,uVar7);
                DAT_1804b2c38 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2c38,in_stack_00000078);
              uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                 (in_stack_00000048,2,in_stack_00000088,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1193);
              (*pcVar2)(in_stack_00000048,uVar13,0,uVar7);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_00000158);
              (*pcVar2)(in_stack_00000048,uVar13,1,uVar7);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_00000080,uVar13);
              (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                (**(code **)(*in_stack_00000048 + 0x420))
                          (in_stack_00000048,in_stack_000001a0,in_stack_00000178);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((cVar5 == '\0') &&
                   (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
                  if (DAT_1804b2c40 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar13 = FUN_180333550(in_stack_00000048);
                    uVar6 = DAT_1804b59f0;
                    uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004de,1);
                    lVar1 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b28,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
                    (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,uVar7);
                    DAT_1804b2c40 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b2c40,in_stack_00000078);
                  uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                     (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2a98);
                  (*pcVar2)(in_stack_00000048,uVar13,0,uVar7);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000c8 ^ 0x7bf3cfa86f2c5a11);
                  (*pcVar2)(in_stack_00000048,uVar13,1,uVar7);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_00000080,uVar13);
                  (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar6,in_stack_00000070);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((cVar5 == '\0') &&
                     (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
                    if (DAT_1804b2c48 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar13 = FUN_180333550(in_stack_00000048);
                      uVar6 = DAT_1804b59f0;
                      uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004dc,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar8 = FUN_1800024f0(in_stack_00000048);
                      uVar9 = FUN_180002550(in_stack_00000048);
                      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b18,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar11 = FUN_1800025a0(in_stack_00000048);
                      uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
                      (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                      uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13,uVar6,uVar7);
                      DAT_1804b2c48 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
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
  return;
}

