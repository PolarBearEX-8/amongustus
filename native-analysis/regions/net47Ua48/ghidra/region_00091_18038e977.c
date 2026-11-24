/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=91 entry=18038e977 blocks=32 maxInstructions=50000 */

/* cut edge: 0x18038e977 -> 0x1803351d6 */
/* cut edge: 0x18038ea57 -> 0x1803351d6 */
/* cut edge: 0x18038ebfa -> 0x1803351d6 */
/* cut edge: 0x18038eccf -> 0x1803351d6 */
/* cut edge: 0x18038ed15 -> 0x1803351d6 */
/* cut edge: 0x18038ed53 -> 0x1803351d6 */
/* cut edge: 0x18038eeeb -> 0x1803351d6 */
/* cut edge: 0x18038f018 -> 0x1803351d6 */
/* cut edge: 0x18038f056 -> 0x1803351d6 */
/* cut edge: 0x18038f090 -> 0x1803351d6 */
/* cut edge: 0x18038f0ef -> 0x1803351d6 */
/* cut edge: 0x18038f2a0 -> 0x1803351d6 */
/* cut edge: 0x18038f3ec -> 0x1803351d6 */
/* cut edge: 0x18038f439 -> 0x1803351d6 */
/* cut edge: 0x18038f471 -> 0x180391e74 */
/* cut edge: 0x18038f47a -> 0x1803351d6 */
/* cut edge: 0x18038f4b8 -> 0x1803351d6 */
/* cut edge: 0x18038f4fe -> 0x1803351d6 */
/* cut edge: 0x18038f537 -> 0x1803351d6 */
/* cut edge: 0x18038f575 -> 0x1803351d6 */
/* cut edge: 0x18038f721 -> 0x1803351d6 */
/* cut edge: 0x18038f7f6 -> 0x1803351d6 */
/* cut edge: 0x18038f7f6 -> 0x18038f83f */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18038f83f original byte 0x48 replaced by RET */
/* target 0x180391e74 original byte 0x48 replaced by RET */


void region_00091_18038e977(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong lVar14;
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
  undefined8 in_stack_000000f0;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  undefined8 in_stack_00000178;
  longlong in_stack_00000180;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_00000248;
  undefined8 in_stack_00000250;
  undefined8 in_stack_00000838;
  
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x50e0);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_00000080,uVar6)
  ;
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((cVar4 == '\0') && (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
    if (DAT_1804b2d18 == 0) {
      lVar14 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar14 + 0x390);
      uVar7 = FUN_1803335a0(in_stack_00000048);
      uVar6 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000004a6,1);
      lVar14 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar14 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00003de0,0x16);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
      DAT_1804b2d18 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b2d18,in_stack_00000078);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2a89);
    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                       in_stack_000000c8 ^ 0x12d62572f7af30f4);
    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 == '\0') {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
      uVar7 = FUN_18001eed0(in_stack_00000048);
      uVar8 = FUN_18001ef30(in_stack_00000048);
      uVar7 = (*pcVar1)(in_stack_00000048,uVar7,uVar8);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
        uVar8 = FUN_18001ef80(in_stack_00000048);
        uVar6 = (*pcVar1)(in_stack_00000048,uVar6,uVar8,uVar7);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar4 == '\0') && (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0'))
        {
          if (DAT_1804b2d20 == 0) {
            lVar14 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar14 + 0x390);
            uVar8 = FUN_180333500(in_stack_00000048);
            uVar7 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000004a4,1);
            lVar14 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar14 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00002ca0,0x19);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
            DAT_1804b2d20 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2d20,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0)
          ;
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,uVar6);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000248
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar6);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                             0xdfcc108d1c27cf45);
          (*pcVar1)(in_stack_00000048,uVar8,2,uVar6);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8
                            );
          (*pcVar1)(in_stack_00000048,uVar8,3,uVar6);
          (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar8)
          ;
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
            uVar6 = FUN_18001f030(in_stack_00000048);
            (*pcVar1)(in_stack_00000048,in_stack_00000838,uVar6);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar4 == '\0') {
              (**(code **)(*in_stack_00000048 + 0x420))
                        (in_stack_00000048,in_stack_000001a0,in_stack_00000250);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                uVar6 = FUN_18001f0d0(in_stack_00000048);
                (*pcVar1)(in_stack_00000048,in_stack_000000a8,in_stack_000000f0,uVar6,uVar5);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((cVar4 == '\0') &&
                   (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
                  if (DAT_1804b2d28 == 0) {
                    lVar14 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar14 + 0x390);
                    uVar6 = FUN_180333500(in_stack_00000048);
                    uVar5 = DAT_1804b59f0;
                    uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000004a2,1);
                    lVar14 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar14 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000061b0,0x2a);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                    (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar6,uVar5,uVar7);
                    DAT_1804b2d28 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                  }
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b2d28,in_stack_00000078);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,5,in_stack_00000088,0);
                  (**(code **)(*in_stack_00000048 + 0x570))
                            (in_stack_00000048,uVar6,0,in_stack_00000168);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_00000170);
                  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                  (**(code **)(*in_stack_00000048 + 0x570))
                            (in_stack_00000048,uVar6,2,in_stack_000000a8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     0xdfc79c58f7839374);
                  (*pcVar1)(in_stack_00000048,uVar6,3,uVar7);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000c8);
                  (*pcVar1)(in_stack_00000048,uVar6,4,uVar7);
                  (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar5,in_stack_00000080,uVar6);
                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar4 == '\0') {
                    lVar14 = (**(code **)(*in_stack_00000048 + 0x420))
                                       (in_stack_00000048,in_stack_000001a0,in_stack_00000178);
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar4 == '\0') {
                      if ((0 < in_stack_00000180) && (in_stack_00000180 < lVar14)) {
                        lVar14 = (**(code **)(*in_stack_00000048 + 0x420))
                                           (in_stack_00000048,in_stack_000001a0,in_stack_00000178);
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar4 != '\0') {
                          return;
                        }
                        if (0 < lVar14) {
                          return;
                        }
                      }
                      pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
                      uVar5 = FUN_18001ec20(in_stack_00000048);
                      uVar6 = (*pcVar1)(in_stack_00000048,uVar5);
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar4 == '\0') {
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                        uVar7 = FUN_18001ec80(in_stack_00000048);
                        (*pcVar1)(in_stack_00000048,uVar6,uVar5,uVar7);
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar4 == '\0') {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
                          uVar5 = FUN_180010830(in_stack_00000048);
                          uVar6 = (*pcVar1)(in_stack_00000048,uVar5);
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar4 == '\0') {
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                            uVar7 = FUN_180010890(in_stack_00000048);
                            (*pcVar1)(in_stack_00000048,uVar6,uVar5,uVar7);
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((cVar4 == '\0') &&
                               (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0'))
                            {
                              if (DAT_1804b2d30 == 0) {
                                lVar14 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar14 + 0x390);
                                uVar7 = FUN_1803335a0(in_stack_00000048);
                                uVar5 = DAT_1804b59f0;
                                uVar8 = (**(code **)(lVar14 + 0x518))
                                                  (in_stack_00000048,&stack0x000004a0,1);
                                lVar14 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar14 + 0x390);
                                uVar9 = FUN_1800024f0(in_stack_00000048);
                                uVar10 = FUN_180002550(in_stack_00000048);
                                uVar11 = (**(code **)(lVar14 + 0x518))
                                                   (in_stack_00000048,&stack0x00003db0,0x16);
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar12 = FUN_1800025a0(in_stack_00000048);
                                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                                (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar5,
                                                  uVar8);
                                DAT_1804b2d30 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar5);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                              }
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b2d30,in_stack_00000078);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000068,
                                                 in_stack_00000060,0x5a63);
                              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,
                                                 in_stack_000000c8 ^ 0x46df400e6f50c028);
                              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar5,in_stack_00000080,uVar7);
                              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar4 == '\0') {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar7 = FUN_1800108e0(in_stack_00000048);
                                (*pcVar1)(in_stack_00000048,uVar6,uVar7,uVar5);
                                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar4 == '\0') {
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
  return;
}

