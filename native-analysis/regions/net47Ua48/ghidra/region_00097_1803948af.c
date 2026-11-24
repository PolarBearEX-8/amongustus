/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=97 entry=1803948af blocks=30 maxInstructions=50000 */

/* cut edge: 0x1803948af -> 0x180396a38 */
/* cut edge: 0x1803948be -> 0x1803351d6 */
/* cut edge: 0x1803948f7 -> 0x1803351d6 */
/* cut edge: 0x180394944 -> 0x1803351d6 */
/* cut edge: 0x180394b02 -> 0x1803351d6 */
/* cut edge: 0x180394c52 -> 0x1803351d6 */
/* cut edge: 0x180394c90 -> 0x1803351d6 */
/* cut edge: 0x180394e42 -> 0x1803351d6 */
/* cut edge: 0x180394f89 -> 0x1803351d6 */
/* cut edge: 0x180394fc7 -> 0x1803351d6 */
/* cut edge: 0x180395179 -> 0x1803351d6 */
/* cut edge: 0x1803952c0 -> 0x1803351d6 */
/* cut edge: 0x1803952fd -> 0x1803964f8 */
/* cut edge: 0x180395305 -> 0x1803351d6 */
/* cut edge: 0x18039548d -> 0x1803351d6 */
/* cut edge: 0x180395577 -> 0x1803351d6 */
/* cut edge: 0x1803956df -> 0x1803351d6 */
/* cut edge: 0x1803957d0 -> 0x1803351d6 */
/* cut edge: 0x1803957e6 -> 0x18039594b */
/* cut edge: 0x1803957f6 -> 0x18039594b */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18039594b original byte 0x48 replaced by RET */
/* target 0x1803964f8 original byte 0x48 replaced by RET */
/* target 0x180396a38 original byte 0x48 replaced by RET */


void region_00097_1803948af(void)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
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
  longlong in_stack_000000a0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000130;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  undefined8 in_stack_000001c0;
  undefined8 in_stack_00002330;
  undefined8 in_stack_00002338;
  
  if (in_stack_000000a0 == 0) {
    return;
  }
  pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
  uVar7 = FUN_1803dadb0(in_stack_00000048);
  uVar8 = (*pcVar1)(in_stack_00000048,uVar7);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
    uVar9 = FUN_1803dae10(in_stack_00000048);
    (*pcVar1)(in_stack_00000048,uVar8,uVar7,uVar9,in_stack_000000a0);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b2e10 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar9 = FUN_180333500(in_stack_00000048);
          uVar7 = DAT_1804b59f0;
          uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000464,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00005fd0,0x2a);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar7,uVar10);
          DAT_1804b2e10 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b2e10,in_stack_00000078);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000088,0);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,in_stack_00000168);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000170)
        ;
        (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,2,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc79c58f7839374)
        ;
        (*pcVar1)(in_stack_00000048,uVar9,3,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
        (*pcVar1)(in_stack_00000048,uVar9,4,uVar8);
        (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar9);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x2f8);
          uVar7 = FUN_180333690(in_stack_00000048);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000168,uVar7);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b2e18 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_180333500(in_stack_00000048);
                uVar8 = DAT_1804b59f0;
                uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000462,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar11 = FUN_1800024f0(in_stack_00000048);
                uVar12 = FUN_180002550(in_stack_00000048);
                uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00005f70,0x2a);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar14 = FUN_1800025a0(in_stack_00000048);
                uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
                DAT_1804b2e18 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2e18,in_stack_00000078);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,5,in_stack_00000088,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_00002330);
              (*pcVar1)(in_stack_00000048,uVar9,1,uVar7);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,2,in_stack_00000100)
              ;
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 0xdfc5ce7233bfa37c);
              (*pcVar1)(in_stack_00000048,uVar9,3,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar9,4,uVar7);
              (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x2f8);
                uVar7 = FUN_180333640(in_stack_00000048);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000168,uVar7);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b2e20 == 0) {
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar9 = FUN_180333500(in_stack_00000048);
                      uVar8 = DAT_1804b59f0;
                      uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000460,1);
                      lVar2 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000048);
                      uVar12 = FUN_180002550(in_stack_00000048);
                      uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00005f10,0x2a)
                      ;
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar14 = FUN_1800025a0(in_stack_00000048);
                      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                      uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
                      DAT_1804b2e20 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b2e20,in_stack_00000078);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,5,in_stack_00000088,0);
                    (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,uVar7);
                    (**(code **)(*in_stack_00000048 + 0x570))
                              (in_stack_00000048,uVar9,1,in_stack_000000d8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       in_stack_00002338);
                    (*pcVar1)(in_stack_00000048,uVar9,2,uVar7);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       0xdfd7569f08615b67);
                    (*pcVar1)(in_stack_00000048,uVar9,3,uVar7);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       in_stack_000000c8);
                    (*pcVar1)(in_stack_00000048,uVar9,4,uVar7);
                    (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x300);
                      uVar7 = FUN_1803336e0(in_stack_00000048);
                      cVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000168,uVar7);
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar6 == '\0') {
                        if (cVar5 == '\0') {
                          return;
                        }
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804b2e28 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar8 = FUN_180333550(in_stack_00000048);
                            uVar7 = DAT_1804b59f0;
                            uVar9 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,&stack0x0000045e,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar10 = FUN_1800024f0(in_stack_00000048);
                            uVar11 = FUN_180002550(in_stack_00000048);
                            uVar12 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00000ab8,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar13 = FUN_1800025a0(in_stack_00000048);
                            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9)
                            ;
                            DAT_1804b2e28 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b2e28,in_stack_00000078);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000088,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                             0x35fb);
                          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_000000c8 ^ 0x73b62ef16e36c552);
                          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                          (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar7,in_stack_00000070);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              if (DAT_1804b2e30 == 0) {
                                lVar2 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar2 + 0x390);
                                uVar8 = FUN_1803335f0(in_stack_00000048);
                                uVar7 = DAT_1804b59f0;
                                uVar9 = (**(code **)(lVar2 + 0x518))
                                                  (in_stack_00000048,&stack0x0000045c,1);
                                lVar2 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar2 + 0x390);
                                uVar10 = FUN_1800024f0(in_stack_00000048);
                                uVar11 = FUN_180002550(in_stack_00000048);
                                uVar12 = (**(code **)(lVar2 + 0x518))
                                                   (in_stack_00000048,&stack0x00000aa8,5);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar13 = FUN_1800025a0(in_stack_00000048);
                                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,
                                                  uVar9);
                                DAT_1804b2e30 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar7);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                              }
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b2e30,in_stack_00000078);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000068,
                                                 in_stack_00000060,0x1317);
                              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_00000130);
                              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar7,in_stack_000001c0);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  if (DAT_1804b2e38 == 0) {
                                    lVar2 = *in_stack_00000048;
                                    pcVar1 = *(code **)(lVar2 + 0x390);
                                    uVar8 = FUN_1803335f0(in_stack_00000048);
                                    uVar7 = DAT_1804b59f0;
                                    uVar9 = (**(code **)(lVar2 + 0x518))
                                                      (in_stack_00000048,&stack0x0000045a,1);
                                    lVar2 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar2 + 0x390);
                                    uVar10 = FUN_1800024f0(in_stack_00000048);
                                    uVar11 = FUN_180002550(in_stack_00000048);
                                    uVar12 = (**(code **)(lVar2 + 0x518))
                                                       (in_stack_00000048,&stack0x00000a98,5);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar13 = FUN_1800025a0(in_stack_00000048);
                                    uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                    uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,
                                                      uVar7,uVar9);
                                    DAT_1804b2e38 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar7);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar7);
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
  return;
}

