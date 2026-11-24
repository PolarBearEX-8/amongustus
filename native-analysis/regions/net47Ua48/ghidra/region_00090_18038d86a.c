/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=90 entry=18038d86a blocks=33 maxInstructions=50000 */

/* cut edge: 0x18038d86a -> 0x1803351d6 */
/* cut edge: 0x18038d8b3 -> 0x1803351d6 */
/* cut edge: 0x18038d8f3 -> 0x1803351d6 */
/* cut edge: 0x18038da8f -> 0x1803351d6 */
/* cut edge: 0x18038db64 -> 0x1803351d6 */
/* cut edge: 0x18038dba4 -> 0x1803351d6 */
/* cut edge: 0x18038dd42 -> 0x1803351d6 */
/* cut edge: 0x18038de1d -> 0x1803351d6 */
/* cut edge: 0x18038de58 -> 0x1803351d6 */
/* cut edge: 0x18038de93 -> 0x1803351d6 */
/* cut edge: 0x18038ded6 -> 0x1803351d6 */
/* cut edge: 0x18038df11 -> 0x1803351d6 */
/* cut edge: 0x18038e07d -> 0x1803351d6 */
/* cut edge: 0x18038e15f -> 0x1803351d6 */
/* cut edge: 0x18038e30b -> 0x1803351d6 */
/* cut edge: 0x18038e405 -> 0x1803351d6 */
/* cut edge: 0x18038e440 -> 0x1803351d6 */
/* cut edge: 0x18038e47e -> 0x1803351d6 */
/* cut edge: 0x18038e630 -> 0x1803351d6 */
/* cut edge: 0x18038e785 -> 0x1803351d6 */
/* cut edge: 0x18038e7c8 -> 0x1803351d6 */
/* cut edge: 0x18038e7eb -> 0x18038e977 */
/* cut edge: 0x18038e810 -> 0x18038e977 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18038e977 original byte 0x48 replaced by RET */


undefined8 region_00090_18038d86a(void)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  undefined8 uVar5;
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
  undefined8 in_stack_000000f0;
  undefined8 in_stack_00000110;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_000001c8;
  
  pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
  uVar5 = FUN_18001edd0(in_stack_00000048);
  uVar6 = (*pcVar1)(in_stack_00000048,in_stack_000001a0,uVar5);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)uVar7 == '\0') {
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,in_stack_00000110,in_stack_000000a8,uVar6);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar7 == '\0') {
      uVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)uVar7 == '\0') {
        if (DAT_1804b2ce8 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar8 = FUN_1803335a0(in_stack_00000048);
          uVar7 = DAT_1804b59f0;
          uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000004b4,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000048);
          uVar11 = FUN_180002550(in_stack_00000048);
          uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003e70,0x16);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
          DAT_1804b2ce8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b2ce8,in_stack_00000078);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4b59);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                           in_stack_000000c8 ^ 0x77f9caee8efb510b);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)uVar7 == '\0') {
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar8);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)uVar7 == '\0') {
            uVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)uVar7 == '\0') {
              if (DAT_1804b2cf0 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_1803335a0(in_stack_00000048);
                uVar7 = DAT_1804b59f0;
                uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000004b2,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003e40,0x16);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
                DAT_1804b2cf0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2cf0,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x30dd);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8 ^ 0x4413de42ddbf2af2);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)uVar7 == '\0') {
                uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000001a0,uVar5,uVar8);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)uVar7 == '\0') {
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_000000a8,uVar5);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)uVar7 == '\0') {
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x000004b0,1);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar7 == '\0') {
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)uVar7 == '\0') {
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)uVar7 == '\0') {
                          if (DAT_1804b2cf8 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar6 = FUN_180333500(in_stack_00000048);
                            uVar7 = DAT_1804b59f0;
                            uVar8 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,&stack0x000004ae,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar9 = FUN_1800024f0(in_stack_00000048);
                            uVar10 = FUN_180002550(in_stack_00000048);
                            uVar11 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x000029b0,0xd);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar12 = FUN_1800025a0(in_stack_00000048);
                            uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar6,uVar7,uVar8)
                            ;
                            DAT_1804b2cf8 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b2cf8,in_stack_00000078);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000088,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             0xdfc5815bc278527a);
                          (*pcVar1)(in_stack_00000048,uVar6,0,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_000000c8);
                          (*pcVar1)(in_stack_00000048,uVar6,1,uVar8);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,in_stack_00000080,uVar6);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)uVar7 == '\0') {
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)uVar7 == '\0') {
                              if (DAT_1804b2d00 == 0) {
                                lVar2 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar2 + 0x390);
                                uVar8 = FUN_180333500(in_stack_00000048);
                                uVar7 = DAT_1804b59f0;
                                uVar9 = (**(code **)(lVar2 + 0x518))
                                                  (in_stack_00000048,&stack0x000004ac,1);
                                lVar2 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar2 + 0x390);
                                uVar10 = FUN_1800024f0(in_stack_00000048);
                                uVar11 = FUN_180002550(in_stack_00000048);
                                uVar12 = (**(code **)(lVar2 + 0x518))
                                                   (in_stack_00000048,&stack0x00007690,0x28);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar13 = FUN_1800025a0(in_stack_00000048);
                                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,
                                                  uVar9);
                                DAT_1804b2d00 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar7);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                              }
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b2d00,in_stack_00000078);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,3,in_stack_00000088,0);
                              (**(code **)(*in_stack_00000048 + 0x570))
                                        (in_stack_00000048,uVar8,0,uVar6);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,0xdfe95e8d85cdd102);
                              (*pcVar1)(in_stack_00000048,uVar8,1,uVar6);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_000000c8);
                              (*pcVar1)(in_stack_00000048,uVar8,2,uVar6);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if ((char)uVar7 == '\0') {
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if ((char)uVar7 == '\0') {
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar7 = FUN_180010980(in_stack_00000048);
                                  uVar5 = (*pcVar1)(in_stack_00000048,uVar5,uVar7);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if ((char)uVar7 == '\0') {
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if ((char)uVar7 == '\0') {
                                      if (DAT_1804b2d08 == 0) {
                                        lVar2 = *in_stack_00000048;
                                        pcVar1 = *(code **)(lVar2 + 0x390);
                                        uVar6 = FUN_180333500(in_stack_00000048);
                                        uVar7 = DAT_1804b59f0;
                                        uVar8 = (**(code **)(lVar2 + 0x518))
                                                          (in_stack_00000048,&stack0x000004aa,1);
                                        lVar2 = *in_stack_00000048;
                                        pcVar3 = *(code **)(lVar2 + 0x390);
                                        uVar9 = FUN_1800024f0(in_stack_00000048);
                                        uVar10 = FUN_180002550(in_stack_00000048);
                                        uVar11 = (**(code **)(lVar2 + 0x518))
                                                           (in_stack_00000048,&stack0x00006210,0x2a)
                                        ;
                                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar12 = FUN_1800025a0(in_stack_00000048);
                                        uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,
                                                           uVar12);
                                        (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar6,
                                                          uVar7,uVar8);
                                        DAT_1804b2d08 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar7);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar7);
                                      }
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,DAT_1804b2d08,
                                                         in_stack_00000078);
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,5,in_stack_00000088,0);
                                      (**(code **)(*in_stack_00000048 + 0x570))
                                                (in_stack_00000048,uVar6,0,in_stack_000000f0);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_00000090,in_stack_000001c8);
                                      (*pcVar1)(in_stack_00000048,uVar6,1,uVar8);
                                      (**(code **)(*in_stack_00000048 + 0x570))
                                                (in_stack_00000048,uVar6,2,uVar5);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_00000090,0xdfc0096f727d872c);
                                      (*pcVar1)(in_stack_00000048,uVar6,3,uVar5);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_00000090,in_stack_000000c8);
                                      (*pcVar1)(in_stack_00000048,uVar6,4,uVar5);
                                      (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar7,in_stack_00000080,uVar6);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                                      if ((char)uVar7 == '\0') {
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
                                        uVar7 = FUN_18001ee70(in_stack_00000048);
                                        (*pcVar1)(in_stack_00000048,uVar7);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
                                        if ((char)uVar7 == '\0') {
                                          uVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                                          if ((char)uVar7 == '\0') {
                                            uVar7 = 0x5bd1c33d2263cad2;
                                            if (DAT_1804b2d10 == 0) {
                                              lVar2 = *in_stack_00000048;
                                              pcVar1 = *(code **)(lVar2 + 0x390);
                                              uVar5 = FUN_1803335a0(in_stack_00000048);
                                              uVar7 = DAT_1804b59f0;
                                              uVar6 = (**(code **)(lVar2 + 0x518))
                                                                (in_stack_00000048,&stack0x000004a8,
                                                                 1);
                                              lVar2 = *in_stack_00000048;
                                              pcVar3 = *(code **)(lVar2 + 0x390);
                                              uVar8 = FUN_1800024f0(in_stack_00000048);
                                              uVar9 = FUN_180002550(in_stack_00000048);
                                              uVar10 = (**(code **)(lVar2 + 0x518))
                                                                 (in_stack_00000048,&stack0x00003e10
                                                                  ,0x16);
                                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                              uVar11 = FUN_1800025a0(in_stack_00000048);
                                              uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058
                                                                 ,uVar11);
                                              (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11)
                                              ;
                                              uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,
                                                                uVar5,uVar7,uVar6);
                                              DAT_1804b2d10 =
                                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                                             (in_stack_00000048,uVar7);
                                              uVar7 = (**(code **)(*in_stack_00000048 + 0xb8))
                                                                (in_stack_00000048,uVar7);
                                            }
                                            return uVar7;
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
  return uVar7;
}

