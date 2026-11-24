/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=66 entry=180375963 blocks=29 maxInstructions=50000 */

/* cut edge: 0x180375963 -> 0x1803351d6 */
/* cut edge: 0x180375a50 -> 0x1803351d6 */
/* cut edge: 0x180375a94 -> 0x1803351d6 */
/* cut edge: 0x180375adb -> 0x1803351d6 */
/* cut edge: 0x180375c43 -> 0x1803351d6 */
/* cut edge: 0x180375d31 -> 0x1803351d6 */
/* cut edge: 0x180375ea7 -> 0x1803351d6 */
/* cut edge: 0x180375f94 -> 0x1803351d6 */
/* cut edge: 0x180375fd8 -> 0x1803351d6 */
/* cut edge: 0x18037601c -> 0x1803351d6 */
/* cut edge: 0x180376184 -> 0x1803351d6 */
/* cut edge: 0x180376271 -> 0x1803351d6 */
/* cut edge: 0x1803762bc -> 0x1803351d6 */
/* cut edge: 0x18037630a -> 0x1803351d6 */
/* cut edge: 0x180376473 -> 0x1803351d6 */
/* cut edge: 0x180376560 -> 0x1803351d6 */
/* cut edge: 0x1803765a4 -> 0x1803351d6 */
/* cut edge: 0x1803765eb -> 0x1803351d6 */
/* cut edge: 0x180376754 -> 0x1803351d6 */
/* cut edge: 0x180376754 -> 0x180376841 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180376841 original byte 0x8b replaced by RET */


void region_00066_180375963(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  char cVar4;
  undefined4 uVar5;
  undefined4 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000048;
  undefined4 uStack0000000000000050;
  undefined4 uStack0000000000000054;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined4 in_stack_000000b0;
  code *in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000218;
  undefined8 in_stack_00000220;
  undefined8 in_stack_00000228;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2d6c);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000228);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar7);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    (**(code **)(*in_stack_00000048 + 0x658))
              (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
    uVar5 = uStack0000000000000050;
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 == '\0') {
      _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar5);
      (**(code **)(*in_stack_00000048 + 0x698))
                (in_stack_00000048,in_stack_000000a0,5,1,&stack0x00000050);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar4 == '\0') {
          if (DAT_1804b2898 == 0) {
            lVar2 = *in_stack_00000048;
            in_stack_000000b8 = *(code **)(lVar2 + 0x390);
            in_stack_000000c0 = FUN_180333550(in_stack_00000048);
            in_stack_000000d8 = DAT_1804b59f0;
            uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005c8,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000011b8,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar12 = FUN_1800025a0(in_stack_00000048);
            uVar7 = in_stack_00000058;
            uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
            (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
            uVar7 = (*in_stack_000000b8)
                              (in_stack_00000048,uVar7,in_stack_000000c0,in_stack_000000d8,uVar8);
            DAT_1804b2898 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2898,in_stack_00000078);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2d6c);
          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000228
                            );
          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar7);
          uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar7,in_stack_00000070);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar4 == '\0') {
              if (DAT_1804b28a0 == 0) {
                lVar2 = *in_stack_00000048;
                in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                in_stack_000000d8 = DAT_1804b59f0;
                in_stack_00000100 =
                     (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005c6,1);
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000048);
                uVar9 = FUN_180002550(in_stack_00000048);
                uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000011a8,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000048);
                uVar7 = in_stack_00000058;
                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                uVar7 = (*in_stack_000000b8)
                                  (in_stack_00000048,uVar7,in_stack_000000c0,in_stack_000000d8,
                                   in_stack_00000100);
                DAT_1804b28a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              _uStack0000000000000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b28a0,in_stack_00000078);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4516);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_00000220);
              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar7);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar7,in_stack_00000070);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                (**(code **)(*in_stack_00000048 + 0x658))
                          (in_stack_00000048,in_stack_000000a0,uVar6,1,&stack0x00000050);
                uVar6 = uStack0000000000000050;
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar4 == '\0') {
                  _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar6);
                  (**(code **)(*in_stack_00000048 + 0x698))
                            (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar4 == '\0') {
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar4 == '\0') {
                      if (DAT_1804b28a8 == 0) {
                        lVar2 = *in_stack_00000048;
                        in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                        in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                        in_stack_000000d8 = DAT_1804b59f0;
                        uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005c4,1);
                        lVar2 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar9 = FUN_1800024f0(in_stack_00000048);
                        uVar10 = FUN_180002550(in_stack_00000048);
                        uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001198,5);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar12 = FUN_1800025a0(in_stack_00000048);
                        uVar7 = in_stack_00000058;
                        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                        (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                        uVar7 = (*in_stack_000000b8)
                                          (in_stack_00000048,uVar7,in_stack_000000c0,
                                           in_stack_000000d8,uVar8);
                        DAT_1804b28a8 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                      }
                      _uStack0000000000000050 =
                           (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b28a8,in_stack_00000078);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                         0x4516);
                      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_00000220);
                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,_uStack0000000000000050,in_stack_00000080
                                         ,uVar7);
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                        (in_stack_00000048,uVar7,in_stack_00000070);
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar4 == '\0') {
                        _uStack0000000000000050 = CONCAT44(uStack0000000000000054,in_stack_000000b0)
                        ;
                        (**(code **)(*in_stack_00000048 + 0x698))
                                  (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar4 == '\0') {
                          (**(code **)(*in_stack_00000048 + 0x658))
                                    (in_stack_00000048,in_stack_000000a0,2,1,&stack0x00000050);
                          in_stack_000000b0 = uStack0000000000000050;
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar4 == '\0') {
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar4 == '\0') {
                              if (DAT_1804b28b0 == 0) {
                                lVar2 = *in_stack_00000048;
                                in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                                in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                                in_stack_000000d8 = DAT_1804b59f0;
                                uVar8 = (**(code **)(lVar2 + 0x518))
                                                  (in_stack_00000048,&stack0x000005c2,1);
                                lVar2 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar2 + 0x390);
                                uVar9 = FUN_1800024f0(in_stack_00000048);
                                uVar10 = FUN_180002550(in_stack_00000048);
                                uVar11 = (**(code **)(lVar2 + 0x518))
                                                   (in_stack_00000048,&stack0x00001188,5);
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar12 = FUN_1800025a0(in_stack_00000048);
                                uVar7 = in_stack_00000058;
                                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                                (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                uVar7 = (*in_stack_000000b8)
                                                  (in_stack_00000048,uVar7,in_stack_000000c0,
                                                   in_stack_000000d8,uVar8);
                                DAT_1804b28b0 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar7);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                              }
                              _uStack0000000000000050 =
                                   (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804b28b0,in_stack_00000078);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000068,
                                                 in_stack_00000060,0x3e8f);
                              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_00000218);
                              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,_uStack0000000000000050,
                                                 in_stack_00000080,uVar7);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                                (in_stack_00000048,uVar7,in_stack_00000070);
                              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar4 == '\0') {
                                (**(code **)(*in_stack_00000048 + 0x658))
                                          (in_stack_00000048,in_stack_000000a0,uVar5,1,
                                           &stack0x00000050);
                                uVar5 = uStack0000000000000050;
                                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar4 == '\0') {
                                  _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar5);
                                  (**(code **)(*in_stack_00000048 + 0x698))
                                            (in_stack_00000048,in_stack_000000a0,2,1,
                                             &stack0x00000050);
                                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar4 == '\0') {
                                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if (cVar4 == '\0') {
                                      if (DAT_1804b28b8 == 0) {
                                        lVar2 = *in_stack_00000048;
                                        in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                                        in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                                        in_stack_000000d8 = DAT_1804b59f0;
                                        uVar8 = (**(code **)(lVar2 + 0x518))
                                                          (in_stack_00000048,&stack0x000005c0,1);
                                        lVar2 = *in_stack_00000048;
                                        pcVar1 = *(code **)(lVar2 + 0x390);
                                        uVar9 = FUN_1800024f0(in_stack_00000048);
                                        uVar10 = FUN_180002550(in_stack_00000048);
                                        uVar11 = (**(code **)(lVar2 + 0x518))
                                                           (in_stack_00000048,&stack0x00001178,5);
                                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar12 = FUN_1800025a0(in_stack_00000048);
                                        uVar7 = in_stack_00000058;
                                        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,
                                                           uVar12);
                                        (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                        uVar7 = (*in_stack_000000b8)
                                                          (in_stack_00000048,uVar7,in_stack_000000c0
                                                           ,in_stack_000000d8,uVar8);
                                        DAT_1804b28b8 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar7);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar7);
                                      }
                                      _uStack0000000000000050 =
                                           (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,DAT_1804b28b8,
                                                      in_stack_00000078);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,2,in_stack_00000088,0);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000068,
                                                         in_stack_00000060,0x3e8f);
                                      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_00000090,in_stack_00000218);
                                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,_uStack0000000000000050,
                                                         in_stack_00000080,uVar7);
                                      (**(code **)(*in_stack_00000048 + 0x188))
                                                (in_stack_00000048,uVar7,in_stack_00000070);
                                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
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
      }
    }
  }
  return;
}

