/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=42 entry=18035d964 blocks=30 maxInstructions=50000 */

/* cut edge: 0x18035d964 -> 0x1803351d6 */
/* cut edge: 0x18035da56 -> 0x1803351d6 */
/* cut edge: 0x18035dbbf -> 0x1803351d6 */
/* cut edge: 0x18035dcac -> 0x1803351d6 */
/* cut edge: 0x18035dcf0 -> 0x1803351d6 */
/* cut edge: 0x18035dd39 -> 0x1803351d6 */
/* cut edge: 0x18035dea1 -> 0x1803351d6 */
/* cut edge: 0x18035df8e -> 0x1803351d6 */
/* cut edge: 0x18035dfd9 -> 0x1803351d6 */
/* cut edge: 0x18035e141 -> 0x1803351d6 */
/* cut edge: 0x18035e22e -> 0x1803351d6 */
/* cut edge: 0x18035e279 -> 0x1803351d6 */
/* cut edge: 0x18035e3e1 -> 0x1803351d6 */
/* cut edge: 0x18035e4d3 -> 0x1803351d6 */
/* cut edge: 0x18035e63c -> 0x1803351d6 */
/* cut edge: 0x18035e729 -> 0x1803351d6 */
/* cut edge: 0x18035e76d -> 0x1803351d6 */
/* cut edge: 0x18035e7b6 -> 0x1803351d6 */
/* cut edge: 0x18035e7cc -> 0x18035e91e */
/* cut edge: 0x18035e7dc -> 0x18035e91e */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18035e91e original byte 0x48 replaced by RET */


void region_00042_18035d964(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  char cVar4;
  undefined4 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
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
  code *in_stack_000000b0;
  undefined4 uStack00000000000000b8;
  undefined4 uStack00000000000000bc;
  code *in_stack_000000c0;
  code *in_stack_000000d8;
  undefined8 in_stack_000000e8;
  code *in_stack_00000100;
  undefined8 in_stack_000001b8;
  undefined8 in_stack_000001f8;
  undefined8 in_stack_00000210;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a10);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e8);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar6);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
  in_stack_000000b0 = (code *)CONCAT44(in_stack_000000b0._4_4_,uVar5);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      if (DAT_1804b2468 == 0) {
        lVar2 = *in_stack_00000048;
        in_stack_000000c0 = *(code **)(lVar2 + 0x390);
        in_stack_000000d8 = (code *)FUN_180333550(in_stack_00000048);
        in_stack_00000100 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000006d4,1);
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001a18,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar6 = in_stack_00000058;
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
        (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar6 = (*in_stack_000000c0)
                          (in_stack_00000048,uVar6,in_stack_000000d8,in_stack_00000100,uVar7);
        DAT_1804b2468 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
      }
      _uStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2468,in_stack_00000078);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4475);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000210);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar6);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
        uVar5 = uStack0000000000000050;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar5);
          (**(code **)(*in_stack_00000048 + 0x698))
                    (in_stack_00000048,in_stack_000000a0,(ulonglong)in_stack_000000b0 & 0xffffffff,1
                     ,&stack0x00000050);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar4 == '\0') {
              if (DAT_1804b2470 == 0) {
                lVar2 = *in_stack_00000048;
                in_stack_000000b0 = *(code **)(lVar2 + 0x390);
                in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
                in_stack_000000d8 = DAT_1804b59f0;
                uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000006d2,1);
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000048);
                uVar9 = FUN_180002550(in_stack_00000048);
                uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001a08,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000048);
                uVar6 = in_stack_00000058;
                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                uVar6 = (*in_stack_000000b0)
                                  (in_stack_00000048,uVar6,in_stack_000000c0,in_stack_000000d8,uVar7
                                  );
                DAT_1804b2470 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              _uStack0000000000000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2470,in_stack_00000078);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4475);
              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_00000210);
              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar6);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar6,in_stack_00000070);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uStack00000000000000b8);
                (**(code **)(*in_stack_00000048 + 0x698))
                          (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar4 == '\0') {
                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar4 == '\0') {
                    if (DAT_1804b2478 == 0) {
                      lVar2 = *in_stack_00000048;
                      in_stack_000000b0 = *(code **)(lVar2 + 0x390);
                      _uStack00000000000000b8 = FUN_180333550(in_stack_00000048);
                      in_stack_000000c0 = DAT_1804b59f0;
                      uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000006d0,1);
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar8 = FUN_1800024f0(in_stack_00000048);
                      uVar9 = FUN_180002550(in_stack_00000048);
                      uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000019f8,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar11 = FUN_1800025a0(in_stack_00000048);
                      uVar6 = in_stack_00000058;
                      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                      (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                      uVar6 = (*in_stack_000000b0)
                                        (in_stack_00000048,uVar6,_uStack00000000000000b8,
                                         in_stack_000000c0,uVar7);
                      DAT_1804b2478 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    _uStack0000000000000050 =
                         (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b2478,in_stack_00000078);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       in_stack_000001b8);
                    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,
                                       uVar6);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                      (in_stack_00000048,uVar6,in_stack_00000070);
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar4 == '\0') {
                      (**(code **)(*in_stack_00000048 + 0x658))
                                (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
                      _uStack00000000000000b8 =
                           CONCAT44(uStack00000000000000bc,uStack0000000000000050);
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar4 == '\0') {
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar4 == '\0') {
                          if (DAT_1804b2480 == 0) {
                            lVar2 = *in_stack_00000048;
                            in_stack_000000b0 = *(code **)(lVar2 + 0x390);
                            in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
                            in_stack_000000d8 = DAT_1804b59f0;
                            uVar7 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,&stack0x000006ce,1);
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar8 = FUN_1800024f0(in_stack_00000048);
                            uVar9 = FUN_180002550(in_stack_00000048);
                            uVar10 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x000019e8,5);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar11 = FUN_1800025a0(in_stack_00000048);
                            uVar6 = in_stack_00000058;
                            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                            (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                            uVar6 = (*in_stack_000000b0)
                                              (in_stack_00000048,uVar6,in_stack_000000c0,
                                               in_stack_000000d8,uVar7);
                            DAT_1804b2480 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          _uStack0000000000000050 =
                               (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,DAT_1804b2480,in_stack_00000078);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000088,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                             0x4c62);
                          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_000001b8);
                          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,_uStack0000000000000050,
                                             in_stack_00000080,uVar6);
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                            (in_stack_00000048,uVar6,in_stack_00000070);
                          in_stack_000000b0 = (code *)CONCAT44(in_stack_000000b0._4_4_,uVar5);
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar4 == '\0') {
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar4 == '\0') {
                              if (DAT_1804b2488 == 0) {
                                lVar2 = *in_stack_00000048;
                                in_stack_000000c0 = *(code **)(lVar2 + 0x390);
                                in_stack_000000d8 = (code *)FUN_180333550(in_stack_00000048);
                                in_stack_00000100 = DAT_1804b59f0;
                                uVar7 = (**(code **)(lVar2 + 0x518))
                                                  (in_stack_00000048,&stack0x000006cc,1);
                                lVar2 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar2 + 0x390);
                                uVar8 = FUN_1800024f0(in_stack_00000048);
                                uVar9 = FUN_180002550(in_stack_00000048);
                                uVar10 = (**(code **)(lVar2 + 0x518))
                                                   (in_stack_00000048,&stack0x000019d8,5);
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar11 = FUN_1800025a0(in_stack_00000048);
                                uVar6 = in_stack_00000058;
                                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                                (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                uVar6 = (*in_stack_000000c0)
                                                  (in_stack_00000048,uVar6,in_stack_000000d8,
                                                   in_stack_00000100,uVar7);
                                DAT_1804b2488 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar6);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                              }
                              _uStack0000000000000050 =
                                   (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804b2488,in_stack_00000078);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000068,
                                                 in_stack_00000060,0x67b9);
                              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_000001f8);
                              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,_uStack0000000000000050,
                                                 in_stack_00000080,uVar6);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                                (in_stack_00000048,uVar6,in_stack_00000070);
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
                                            (in_stack_00000048,in_stack_000000a0,
                                             (ulonglong)in_stack_000000b0 & 0xffffffff,1,
                                             &stack0x00000050);
                                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar4 == '\0') {
                                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if (cVar4 == '\0') {
                                      if (DAT_1804b2490 == 0) {
                                        lVar2 = *in_stack_00000048;
                                        in_stack_000000b0 = *(code **)(lVar2 + 0x390);
                                        in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048)
                                        ;
                                        in_stack_000000d8 = DAT_1804b59f0;
                                        uVar7 = (**(code **)(lVar2 + 0x518))
                                                          (in_stack_00000048,&stack0x000006ca,1);
                                        lVar2 = *in_stack_00000048;
                                        pcVar1 = *(code **)(lVar2 + 0x390);
                                        uVar8 = FUN_1800024f0(in_stack_00000048);
                                        uVar9 = FUN_180002550(in_stack_00000048);
                                        uVar10 = (**(code **)(lVar2 + 0x518))
                                                           (in_stack_00000048,&stack0x000019c8,5);
                                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar11 = FUN_1800025a0(in_stack_00000048);
                                        uVar6 = in_stack_00000058;
                                        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,
                                                           uVar11);
                                        (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                        uVar6 = (*in_stack_000000b0)
                                                          (in_stack_00000048,uVar6,in_stack_000000c0
                                                           ,in_stack_000000d8,uVar7);
                                        DAT_1804b2490 =
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
  return;
}

