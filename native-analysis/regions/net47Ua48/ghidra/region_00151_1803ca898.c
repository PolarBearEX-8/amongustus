/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=151 entry=1803ca898 blocks=33 maxInstructions=50000 */

/* cut edge: 0x1803ca898 -> 0x1803351d6 */
/* cut edge: 0x1803ca8d5 -> 0x1803351d6 */
/* cut edge: 0x1803caa75 -> 0x1803351d6 */
/* cut edge: 0x1803cab53 -> 0x1803351d6 */
/* cut edge: 0x1803cabaa -> 0x1803351d6 */
/* cut edge: 0x1803cabe7 -> 0x1803351d6 */
/* cut edge: 0x1803cad86 -> 0x1803351d6 */
/* cut edge: 0x1803cae64 -> 0x1803351d6 */
/* cut edge: 0x1803caea1 -> 0x1803351d6 */
/* cut edge: 0x1803cb041 -> 0x1803351d6 */
/* cut edge: 0x1803cb11f -> 0x1803351d6 */
/* cut edge: 0x1803cb15a -> 0x1803351d6 */
/* cut edge: 0x1803cb192 -> 0x1803351d6 */
/* cut edge: 0x1803cb1d5 -> 0x1803351d6 */
/* cut edge: 0x1803cb20d -> 0x1803351d6 */
/* cut edge: 0x1803cb379 -> 0x1803351d6 */
/* cut edge: 0x1803cb45b -> 0x1803351d6 */
/* cut edge: 0x1803cb607 -> 0x1803351d6 */
/* cut edge: 0x1803cb708 -> 0x1803351d6 */
/* cut edge: 0x1803cb740 -> 0x1803351d6 */
/* cut edge: 0x1803cb77b -> 0x1803351d6 */
/* cut edge: 0x1803cb79e -> 0x1803cb92d */
/* cut edge: 0x1803cb7ae -> 0x1803cb92d */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803cb92d original byte 0x48 replaced by RET */


void region_00151_1803ca898(void)

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
  undefined8 uVar14;
  undefined8 uVar15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000e8;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,in_stack_000000e8,in_stack_00000060);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b3160 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar7 = FUN_1803335a0(in_stack_00000048);
        uVar13 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000388,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003480,0x16);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
        (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar13,uVar8);
        DAT_1804b3160 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
      }
      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b3160,in_stack_00000078);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000070,in_stack_000000a8,0x4ad3);
      (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                         in_stack_000000c8 ^ 0x7fc613bd16cd50b4);
      (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar13,in_stack_00000080,uVar7);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        uVar7 = FUN_18001ed70(in_stack_00000048);
        uVar8 = FUN_18001edd0(in_stack_00000048);
        uVar13 = (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar13);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar6,in_stack_00000060,uVar13);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b3168 == 0) {
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar9 = FUN_1803335a0(in_stack_00000048);
                uVar13 = DAT_1804b59f0;
                uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000386,1);
                lVar1 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar11 = FUN_1800024f0(in_stack_00000048);
                uVar12 = FUN_180002550(in_stack_00000048);
                uVar14 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003450,0x16);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar15 = FUN_1800025a0(in_stack_00000048);
                uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar14,uVar15);
                uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar9,uVar13,uVar10);
                DAT_1804b3168 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
              }
              uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b3168,in_stack_00000078);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000070,in_stack_000000a8,0x4b59);
              (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                  in_stack_000000c8 ^ 0x77f9caee8efb510b);
              (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
              uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar13,in_stack_00000080,uVar9);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar6,in_stack_00000060,uVar13);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b3170 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1803335a0(in_stack_00000048);
                      uVar13 = DAT_1804b59f0;
                      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000384,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000048);
                      uVar12 = FUN_180002550(in_stack_00000048);
                      uVar14 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003420,0x16)
                      ;
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar15 = FUN_1800025a0(in_stack_00000048);
                      uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar14,uVar15);
                      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar9,uVar13,uVar10);
                      DAT_1804b3170 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                    }
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b3170,in_stack_00000078);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_00000070,in_stack_000000a8,0x30dd
                                       );
                    (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                        in_stack_000000c8 ^ 0x4413de42ddbf2af2);
                    (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar13,in_stack_00000080,uVar9);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,uVar7,uVar8,uVar13);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar6,in_stack_00000060,uVar13);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          uVar13 = (**(code **)(*in_stack_00000048 + 0x518))
                                             (in_stack_00000048,&stack0x00000382,1);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar6,in_stack_00000060,uVar13);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                if (DAT_1804b3178 == 0) {
                                  lVar1 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar1 + 0x390);
                                  uVar7 = FUN_180333500(in_stack_00000048);
                                  uVar13 = DAT_1804b59f0;
                                  uVar8 = (**(code **)(lVar1 + 0x518))
                                                    (in_stack_00000048,&stack0x00000380,1);
                                  lVar1 = *in_stack_00000048;
                                  pcVar3 = *(code **)(lVar1 + 0x390);
                                  uVar9 = FUN_1800024f0(in_stack_00000048);
                                  uVar10 = FUN_180002550(in_stack_00000048);
                                  uVar11 = (**(code **)(lVar1 + 0x518))
                                                     (in_stack_00000048,&stack0x00002690,0xd);
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar12 = FUN_1800025a0(in_stack_00000048);
                                  uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                                  (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                  uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,
                                                     uVar13,uVar8);
                                  DAT_1804b3178 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar13);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13)
                                  ;
                                }
                                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,DAT_1804b3178,
                                                    in_stack_00000078);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_00000088,0);
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_00000090,0xdfc5815bc278527a);
                                (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_00000090,in_stack_000000c8);
                                (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,uVar13,in_stack_00000080,uVar7
                                                   );
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    if (DAT_1804b3180 == 0) {
                                      lVar1 = *in_stack_00000048;
                                      pcVar2 = *(code **)(lVar1 + 0x390);
                                      uVar8 = FUN_180333500(in_stack_00000048);
                                      uVar7 = DAT_1804b59f0;
                                      uVar9 = (**(code **)(lVar1 + 0x518))
                                                        (in_stack_00000048,&stack0x0000037e,1);
                                      lVar1 = *in_stack_00000048;
                                      pcVar3 = *(code **)(lVar1 + 0x390);
                                      uVar10 = FUN_1800024f0(in_stack_00000048);
                                      uVar11 = FUN_180002550(in_stack_00000048);
                                      uVar12 = (**(code **)(lVar1 + 0x518))
                                                         (in_stack_00000048,&stack0x00007410,0x28);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar14 = FUN_1800025a0(in_stack_00000048);
                                      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14)
                                      ;
                                      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar14);
                                      uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar8,
                                                        uVar7,uVar9);
                                      DAT_1804b3180 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar7);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar7);
                                    }
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,DAT_1804b3180,
                                                       in_stack_00000078);
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,3,in_stack_00000088,0);
                                    (**(code **)(*in_stack_00000048 + 0x570))
                                              (in_stack_00000048,uVar8,0,uVar13);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar13 = (**(code **)(*in_stack_00000048 + 0x390))
                                                       (in_stack_00000048,in_stack_00000098,
                                                        in_stack_00000090,0xdfe95e8d85cdd102);
                                    (*pcVar2)(in_stack_00000048,uVar8,1,uVar13);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar13 = (**(code **)(*in_stack_00000048 + 0x390))
                                                       (in_stack_00000048,in_stack_00000098,
                                                        in_stack_00000090,in_stack_000000c8);
                                    (*pcVar2)(in_stack_00000048,uVar8,2,uVar13);
                                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,uVar7,in_stack_00000080,
                                                        uVar8);
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar5 == '\0') {
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,uVar6,in_stack_00000060,
                                                         uVar13);
                                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                                      if (cVar5 == '\0') {
                                        pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar13 = FUN_180010980(in_stack_00000048);
                                        (*pcVar2)(in_stack_00000048,uVar6,uVar13);
                                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
                                        if (cVar5 == '\0') {
                                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                          if (cVar5 == '\0') {
                                            if (DAT_1804b3188 == 0) {
                                              lVar1 = *in_stack_00000048;
                                              pcVar2 = *(code **)(lVar1 + 0x390);
                                              uVar13 = FUN_180333500(in_stack_00000048);
                                              uVar6 = DAT_1804b59f0;
                                              uVar7 = (**(code **)(lVar1 + 0x518))
                                                                (in_stack_00000048,&stack0x0000037c,
                                                                 1);
                                              lVar1 = *in_stack_00000048;
                                              pcVar3 = *(code **)(lVar1 + 0x390);
                                              uVar8 = FUN_1800024f0(in_stack_00000048);
                                              uVar9 = FUN_180002550(in_stack_00000048);
                                              uVar10 = (**(code **)(lVar1 + 0x518))
                                                                 (in_stack_00000048,&stack0x00005a60
                                                                  ,0x2a);
                                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                              uVar11 = FUN_1800025a0(in_stack_00000048);
                                              uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058
                                                                 ,uVar11);
                                              (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11)
                                              ;
                                              uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,
                                                                uVar13,uVar6,uVar7);
                                              DAT_1804b3188 =
                                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                                             (in_stack_00000048,uVar6);
                                              (**(code **)(*in_stack_00000048 + 0xb8))
                                                        (in_stack_00000048,uVar6);
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
              }
            }
          }
        }
      }
    }
  }
  return;
}

