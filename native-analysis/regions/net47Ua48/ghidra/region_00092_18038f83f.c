/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=92 entry=18038f83f blocks=29 maxInstructions=50000 */

/* cut edge: 0x18038f83f -> 0x1803351d6 */
/* cut edge: 0x18038f9ab -> 0x1803351d6 */
/* cut edge: 0x18038fa8d -> 0x1803351d6 */
/* cut edge: 0x18038fc43 -> 0x1803351d6 */
/* cut edge: 0x18038fd70 -> 0x1803351d6 */
/* cut edge: 0x18038fdb0 -> 0x1803351d6 */
/* cut edge: 0x18038ff53 -> 0x1803351d6 */
/* cut edge: 0x180390028 -> 0x1803351d6 */
/* cut edge: 0x180390068 -> 0x1803351d6 */
/* cut edge: 0x18039020b -> 0x1803351d6 */
/* cut edge: 0x1803902e6 -> 0x1803351d6 */
/* cut edge: 0x18039032f -> 0x1803351d6 */
/* cut edge: 0x18039036f -> 0x1803351d6 */
/* cut edge: 0x180390511 -> 0x1803351d6 */
/* cut edge: 0x1803905ec -> 0x1803351d6 */
/* cut edge: 0x18039062c -> 0x1803351d6 */
/* cut edge: 0x1803907ca -> 0x1803351d6 */
/* cut edge: 0x1803907ca -> 0x1803908a5 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803908a5 original byte 0x48 replaced by RET */


void region_00092_18038f83f(void)

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
  undefined8 in_stack_000000f8;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_00000208;
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804b2d38 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar6 = FUN_180333500(in_stack_00000048);
      uVar12 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000049e,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002990,0xd);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
      (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar6,uVar12,uVar7);
      DAT_1804b2d38 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2d38,in_stack_00000078);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc5815bc278527a);
    (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
    (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar12,in_stack_00000080,uVar6);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b2d40 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333500(in_stack_00000048);
          uVar6 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000049c,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00005010,0x29);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
          uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
          DAT_1804b2d40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b2d40,in_stack_00000078);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,uVar12);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000208)
        ;
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar12);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfb22bab6c698c7f
                           );
        (*pcVar2)(in_stack_00000048,uVar7,2,uVar12);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8)
        ;
        (*pcVar2)(in_stack_00000048,uVar7,3,uVar12);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,in_stack_000000f8,in_stack_000000a8,uVar12);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b2d48 == 0) {
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar7 = FUN_1803335a0(in_stack_00000048);
                uVar6 = DAT_1804b59f0;
                uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000049a,1);
                lVar1 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003d80,0x16);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
                uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
                DAT_1804b2d48 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2d48,in_stack_00000078);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1019);
              (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8 ^ 0x4dd1571d1c070a6e);
              (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b2d50 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_1803335a0(in_stack_00000048);
                      uVar6 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000498,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003d50,0x16)
                      ;
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar13 = FUN_1800025a0(in_stack_00000048);
                      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
                      uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
                      DAT_1804b2d50 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b2d50,in_stack_00000078);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4ad3)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       in_stack_000000c8 ^ 0x7fc613bd16cd50b4);
                    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                      uVar7 = FUN_18001edd0(in_stack_00000048);
                      uVar6 = (*pcVar2)(in_stack_00000048,in_stack_000001a0,uVar7,uVar6);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar5 == '\0') {
                            if (DAT_1804b2d58 == 0) {
                              lVar1 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar1 + 0x390);
                              uVar7 = FUN_1803335a0(in_stack_00000048);
                              uVar6 = DAT_1804b59f0;
                              uVar8 = (**(code **)(lVar1 + 0x518))
                                                (in_stack_00000048,&stack0x00000496,1);
                              lVar1 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar1 + 0x390);
                              uVar9 = FUN_1800024f0(in_stack_00000048);
                              uVar10 = FUN_180002550(in_stack_00000048);
                              uVar11 = (**(code **)(lVar1 + 0x518))
                                                 (in_stack_00000048,&stack0x00003d20,0x16);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar13 = FUN_1800025a0(in_stack_00000048);
                              uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
                              uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,
                                                uVar8);
                              DAT_1804b2d58 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                            }
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b2d58,in_stack_00000078);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,2,in_stack_00000088,0);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000068,in_stack_00000060
                                               ,0x4b59);
                            (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,in_stack_000000c8 ^ 0x77f9caee8efb510b);
                            (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  if (DAT_1804b2d60 == 0) {
                                    lVar1 = *in_stack_00000048;
                                    pcVar2 = *(code **)(lVar1 + 0x390);
                                    uVar6 = FUN_1803335a0(in_stack_00000048);
                                    uVar12 = DAT_1804b59f0;
                                    uVar7 = (**(code **)(lVar1 + 0x518))
                                                      (in_stack_00000048,&stack0x00000494,1);
                                    lVar1 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar1 + 0x390);
                                    uVar8 = FUN_1800024f0(in_stack_00000048);
                                    uVar9 = FUN_180002550(in_stack_00000048);
                                    uVar10 = (**(code **)(lVar1 + 0x518))
                                                       (in_stack_00000048,&stack0x00003cf0,0x16);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar11 = FUN_1800025a0(in_stack_00000048);
                                    uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
                                    (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                    uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar6,
                                                       uVar12,uVar7);
                                    DAT_1804b2d60 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar12);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar12);
                                  }
                                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,DAT_1804b2d60,
                                                      in_stack_00000078);
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_00000088,0);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000068,
                                                     in_stack_00000060,0x30dd);
                                  (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000098,
                                                     in_stack_00000090,
                                                     in_stack_000000c8 ^ 0x4413de42ddbf2af2);
                                  (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                                  (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar12,in_stack_00000080,uVar6);
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
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

