/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=54 entry=180369854 blocks=33 maxInstructions=50000 */

/* cut edge: 0x180369854 -> 0x1803351d6 */
/* cut edge: 0x180369941 -> 0x1803351d6 */
/* cut edge: 0x18036998c -> 0x1803351d6 */
/* cut edge: 0x180369af4 -> 0x1803351d6 */
/* cut edge: 0x180369be2 -> 0x1803351d6 */
/* cut edge: 0x180369d58 -> 0x1803351d6 */
/* cut edge: 0x180369e45 -> 0x1803351d6 */
/* cut edge: 0x180369e89 -> 0x1803351d6 */
/* cut edge: 0x180369ecd -> 0x1803351d6 */
/* cut edge: 0x18036a035 -> 0x1803351d6 */
/* cut edge: 0x18036a123 -> 0x1803351d6 */
/* cut edge: 0x18036a299 -> 0x1803351d6 */
/* cut edge: 0x18036a386 -> 0x1803351d6 */
/* cut edge: 0x18036a3ca -> 0x1803351d6 */
/* cut edge: 0x18036a40e -> 0x1803351d6 */
/* cut edge: 0x18036a576 -> 0x1803351d6 */
/* cut edge: 0x18036a663 -> 0x1803351d6 */
/* cut edge: 0x18036a6aa -> 0x1803351d6 */
/* cut edge: 0x18036a6ee -> 0x1803351d6 */
/* cut edge: 0x18036a75c -> 0x1803351d6 */
/* cut edge: 0x18036a7b1 -> 0x1803351d6 */
/* cut edge: 0x18036a811 -> 0x1803351d6 */
/* cut edge: 0x18036a811 -> 0x18036a871 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18036a871 original byte 0x48 replaced by RET */


void region_00054_180369854(undefined8 param_1,undefined8 param_2)

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
  undefined4 uStack00000000000000b0;
  undefined4 uStack00000000000000b4;
  code *in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000d8;
  ulonglong in_stack_00000100;
  uint in_stack_00000190;
  uint in_stack_00000198;
  ulonglong in_stack_000001a8;
  undefined8 in_stack_000001b8;
  undefined8 in_stack_000001d0;
  undefined8 in_stack_000001f8;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001b8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar7);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    (**(code **)(*in_stack_00000048 + 0x658))
              (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
    _uStack00000000000000b0 = CONCAT44(uStack00000000000000b4,uStack0000000000000050);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 == '\0') {
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar4 == '\0') {
        if (DAT_1804b2660 == 0) {
          lVar2 = *in_stack_00000048;
          in_stack_000000b8 = *(code **)(lVar2 + 0x390);
          in_stack_000000c0 = FUN_180333550(in_stack_00000048);
          in_stack_000000d8 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000656,1);
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001628,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar7 = in_stack_00000058;
          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
          (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar7 = (*in_stack_000000b8)
                            (in_stack_00000048,uVar7,in_stack_000000c0,in_stack_000000d8,uVar8);
          DAT_1804b2660 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        _uStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2660,in_stack_00000078);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001b8);
        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar7);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070)
        ;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 == '\0') {
            if (DAT_1804b2668 == 0) {
              lVar2 = *in_stack_00000048;
              in_stack_000000b8 = *(code **)(lVar2 + 0x390);
              in_stack_000000c0 = FUN_180333550(in_stack_00000048);
              in_stack_000000d8 = DAT_1804b59f0;
              in_stack_00000100 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000654,1)
              ;
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001618,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar7 = in_stack_00000058;
              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
              (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar7 = (*in_stack_000000b8)
                                (in_stack_00000048,uVar7,in_stack_000000c0,in_stack_000000d8,
                                 in_stack_00000100);
              DAT_1804b2668 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2668,in_stack_00000078);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x67b9);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000001f8);
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
                    if (DAT_1804b2670 == 0) {
                      lVar2 = *in_stack_00000048;
                      in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                      in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                      in_stack_000000d8 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000652,1);
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001608,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar7 = in_stack_00000058;
                      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                      (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar7 = (*in_stack_000000b8)
                                        (in_stack_00000048,uVar7,in_stack_000000c0,in_stack_000000d8
                                         ,uVar8);
                      DAT_1804b2670 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    _uStack0000000000000050 =
                         (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b2670,in_stack_00000078);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x67b9)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       in_stack_000001f8);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,
                                       uVar7);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                      (in_stack_00000048,uVar7,in_stack_00000070);
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar4 == '\0') {
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar4 == '\0') {
                        if (DAT_1804b2678 == 0) {
                          lVar2 = *in_stack_00000048;
                          in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                          in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                          in_stack_000000d8 = DAT_1804b59f0;
                          in_stack_00000100 =
                               (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000650,1);
                          lVar2 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar2 + 0x390);
                          uVar8 = FUN_1800024f0(in_stack_00000048);
                          uVar9 = FUN_180002550(in_stack_00000048);
                          uVar10 = (**(code **)(lVar2 + 0x518))
                                             (in_stack_00000048,&stack0x000015f8,5);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar11 = FUN_1800025a0(in_stack_00000048);
                          uVar7 = in_stack_00000058;
                          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                          (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                          uVar7 = (*in_stack_000000b8)
                                            (in_stack_00000048,uVar7,in_stack_000000c0,
                                             in_stack_000000d8,in_stack_00000100);
                          DAT_1804b2678 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                        }
                        _uStack0000000000000050 =
                             (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b2678,in_stack_00000078);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000088,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                           0x79b1);
                        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_000001d0);
                        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,_uStack0000000000000050,
                                           in_stack_00000080,uVar7);
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
                                      (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050)
                            ;
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar4 == '\0') {
                              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar4 == '\0') {
                                if (DAT_1804b2680 == 0) {
                                  lVar2 = *in_stack_00000048;
                                  in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                                  in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                                  in_stack_000000d8 = DAT_1804b59f0;
                                  uVar8 = (**(code **)(lVar2 + 0x518))
                                                    (in_stack_00000048,&stack0x0000064e,1);
                                  lVar2 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar2 + 0x390);
                                  uVar9 = FUN_1800024f0(in_stack_00000048);
                                  uVar10 = FUN_180002550(in_stack_00000048);
                                  uVar11 = (**(code **)(lVar2 + 0x518))
                                                     (in_stack_00000048,&stack0x000015e8,5);
                                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar12 = FUN_1800025a0(in_stack_00000048);
                                  uVar7 = in_stack_00000058;
                                  uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                                  (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                  uVar7 = (*in_stack_000000b8)
                                                    (in_stack_00000048,uVar7,in_stack_000000c0,
                                                     in_stack_000000d8,uVar8);
                                  DAT_1804b2680 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar7);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                                }
                                _uStack0000000000000050 =
                                     (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,DAT_1804b2680,in_stack_00000078);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_00000088,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000068,
                                                   in_stack_00000060,0x79b1);
                                (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_00000090,in_stack_000001d0);
                                (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,_uStack0000000000000050,
                                                   in_stack_00000080,uVar7);
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                                  (in_stack_00000048,uVar7,in_stack_00000070);
                                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar4 == '\0') {
                                  (**(code **)(*in_stack_00000048 + 0x658))
                                            (in_stack_00000048,in_stack_000000a0,3,1,
                                             &stack0x00000050);
                                  uVar6 = uStack0000000000000050;
                                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar4 == '\0') {
                                    uStack0000000000000050 = uVar6;
                                    (**(code **)(*in_stack_00000048 + 0x698))
                                              (in_stack_00000048,in_stack_000000a0,uVar5,1,
                                               &stack0x00000050);
                                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar4 == '\0') {
                                      _uStack0000000000000050 =
                                           CONCAT44(uStack0000000000000054,uStack00000000000000b0);
                                      (**(code **)(*in_stack_00000048 + 0x698))
                                                (in_stack_00000048,in_stack_000000a0,3,1,
                                                 &stack0x00000050);
                                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                                      if (cVar4 == '\0') {
                                        in_stack_000001a8 = in_stack_000001a8 & 0xffffffff00000000;
                                        in_stack_000000c0 = 0;
                                        (**(code **)(*in_stack_00000048 + 0x658))
                                                  (in_stack_00000048,in_stack_000000a0,0,1,
                                                   &stack0x00000050);
                                        in_stack_00000100 = _uStack0000000000000050 & 0xffffffff;
                                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
                                        if (cVar4 == '\0') {
                                          in_stack_00000190 = (uint)in_stack_000000c0 | 1;
                                          (**(code **)(*in_stack_00000048 + 0x658))
                                                    (in_stack_00000048,in_stack_000000a0,
                                                     in_stack_00000190,1,&stack0x00000050);
                                          in_stack_000000d8 = _uStack0000000000000050 & 0xffffffff;
                                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                            (in_stack_00000048);
                                          if (cVar4 == '\0') {
                                            in_stack_00000198 = (uint)in_stack_000000c0 | 2;
                                            (**(code **)(*in_stack_00000048 + 0x658))
                                                      (in_stack_00000048,in_stack_000000a0,
                                                       in_stack_00000198,1,&stack0x00000050);
                                            _uStack00000000000000b0 =
                                                 _uStack0000000000000050 & 0xffffffff;
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
      }
    }
  }
  return;
}

