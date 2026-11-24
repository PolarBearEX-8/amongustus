/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=76 entry=18037f87c blocks=30 maxInstructions=50000 */

/* cut edge: 0x18037f87c -> 0x1803351d6 */
/* cut edge: 0x18037f8c7 -> 0x1803351d6 */
/* cut edge: 0x18037fa2f -> 0x1803351d6 */
/* cut edge: 0x18037fb1c -> 0x1803351d6 */
/* cut edge: 0x18037fb67 -> 0x1803351d6 */
/* cut edge: 0x18037fccf -> 0x1803351d6 */
/* cut edge: 0x18037fdbd -> 0x1803351d6 */
/* cut edge: 0x18037ff33 -> 0x1803351d6 */
/* cut edge: 0x180380020 -> 0x1803351d6 */
/* cut edge: 0x180380064 -> 0x1803351d6 */
/* cut edge: 0x1803800a8 -> 0x1803351d6 */
/* cut edge: 0x180380210 -> 0x1803351d6 */
/* cut edge: 0x1803802fe -> 0x1803351d6 */
/* cut edge: 0x180380474 -> 0x1803351d6 */
/* cut edge: 0x180380561 -> 0x1803351d6 */
/* cut edge: 0x1803805a5 -> 0x1803351d6 */
/* cut edge: 0x1803805e9 -> 0x1803351d6 */
/* cut edge: 0x180380751 -> 0x1803351d6 */
/* cut edge: 0x180380751 -> 0x18038083e */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18038083e original byte 0x48 replaced by RET */


void region_00076_18037f87c(void)

{
  longlong lVar1;
  code *pcVar2;
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
  undefined4 unaff_ESI;
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
  undefined8 in_stack_000000d8;
  undefined8 in_stack_00000100;
  undefined8 in_stack_000001b8;
  undefined8 in_stack_000001d0;
  undefined8 in_stack_000001f8;
  
  _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uStack00000000000000b0);
  (**(code **)(*in_stack_00000048 + 0x698))
            (in_stack_00000048,in_stack_000000a0,unaff_ESI,1,&stack0x00000050);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      if (DAT_1804b2a78 == 0) {
        lVar1 = *in_stack_00000048;
        _uStack00000000000000b0 = *(code **)(lVar1 + 0x390);
        in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
        in_stack_000000c0 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000550,1);
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000df8,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar12 = in_stack_00000058;
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*_uStack00000000000000b0)
                           (in_stack_00000048,uVar12,in_stack_000000b8,in_stack_000000c0,uVar7);
        DAT_1804b2a78 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      _uStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2a78,in_stack_00000078);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
      (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001b8);
      (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar12);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000070);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
        _uStack00000000000000b0 = (code *)CONCAT44(uStack00000000000000b4,uStack0000000000000050);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 == '\0') {
            if (DAT_1804b2a80 == 0) {
              lVar1 = *in_stack_00000048;
              in_stack_000000b8 = *(code **)(lVar1 + 0x390);
              in_stack_000000c0 = FUN_180333550(in_stack_00000048);
              in_stack_000000d8 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000054e,1);
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000de8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar12 = in_stack_00000058;
              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*in_stack_000000b8)
                                 (in_stack_00000048,uVar12,in_stack_000000c0,in_stack_000000d8,uVar7
                                 );
              DAT_1804b2a80 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2a80,in_stack_00000078);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_00000088,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
            (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000001b8);
            (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar12);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar12,in_stack_00000070);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar4 == '\0') {
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar4 == '\0') {
                if (DAT_1804b2a88 == 0) {
                  lVar1 = *in_stack_00000048;
                  in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                  in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                  in_stack_000000d8 = DAT_1804b59f0;
                  in_stack_00000100 =
                       (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000054c,1);
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_1800024f0(in_stack_00000048);
                  uVar8 = FUN_180002550(in_stack_00000048);
                  uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000dd8,5);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar10 = FUN_1800025a0(in_stack_00000048);
                  uVar12 = in_stack_00000058;
                  uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar10);
                  (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                  uVar12 = (*in_stack_000000b8)
                                     (in_stack_00000048,uVar12,in_stack_000000c0,in_stack_000000d8,
                                      in_stack_00000100);
                  DAT_1804b2a88 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                }
                _uStack0000000000000050 =
                     (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b2a88,in_stack_00000078);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                   (in_stack_00000048,2,in_stack_00000088,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x67b9);
                (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_000001f8);
                (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,
                                    uVar12);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar12,in_stack_00000070);
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
                        if (DAT_1804b2a90 == 0) {
                          lVar1 = *in_stack_00000048;
                          in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                          in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                          in_stack_000000d8 = DAT_1804b59f0;
                          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000054a,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar8 = FUN_1800024f0(in_stack_00000048);
                          uVar9 = FUN_180002550(in_stack_00000048);
                          uVar10 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00000dc8,5);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar11 = FUN_1800025a0(in_stack_00000048);
                          uVar12 = in_stack_00000058;
                          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                          uVar12 = (*in_stack_000000b8)
                                             (in_stack_00000048,uVar12,in_stack_000000c0,
                                              in_stack_000000d8,uVar7);
                          DAT_1804b2a90 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                        }
                        _uStack0000000000000050 =
                             (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b2a90,in_stack_00000078);
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                           (in_stack_00000048,2,in_stack_00000088,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                           0x67b9);
                        (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_000001f8);
                        (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,_uStack0000000000000050,
                                            in_stack_00000080,uVar12);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                          (in_stack_00000048,uVar12,in_stack_00000070);
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar4 == '\0') {
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar4 == '\0') {
                            if (DAT_1804b2a98 == 0) {
                              lVar1 = *in_stack_00000048;
                              in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                              in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                              in_stack_000000d8 = DAT_1804b59f0;
                              in_stack_00000100 =
                                   (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00000548,1);
                              lVar1 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar1 + 0x390);
                              uVar7 = FUN_1800024f0(in_stack_00000048);
                              uVar8 = FUN_180002550(in_stack_00000048);
                              uVar9 = (**(code **)(lVar1 + 0x518))
                                                (in_stack_00000048,&stack0x00000db8,5);
                              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar10 = FUN_1800025a0(in_stack_00000048);
                              uVar12 = in_stack_00000058;
                              uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar10);
                              (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                              uVar12 = (*in_stack_000000b8)
                                                 (in_stack_00000048,uVar12,in_stack_000000c0,
                                                  in_stack_000000d8,in_stack_00000100);
                              DAT_1804b2a98 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                             (in_stack_00000048,uVar12);
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                            }
                            _uStack0000000000000050 =
                                 (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804b2a98,in_stack_00000078);
                            uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                               (in_stack_00000048,2,in_stack_00000088,0);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000068,in_stack_00000060
                                               ,0x79b1);
                            (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,in_stack_000001d0);
                            (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
                            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,_uStack0000000000000050,
                                                in_stack_00000080,uVar12);
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                              (in_stack_00000048,uVar12,in_stack_00000070);
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar4 == '\0') {
                              (**(code **)(*in_stack_00000048 + 0x658))
                                        (in_stack_00000048,in_stack_000000a0,uVar6,1,
                                         &stack0x00000050);
                              uVar6 = uStack0000000000000050;
                              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar4 == '\0') {
                                _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar6);
                                (**(code **)(*in_stack_00000048 + 0x698))
                                          (in_stack_00000048,in_stack_000000a0,uVar5,1,
                                           &stack0x00000050);
                                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar4 == '\0') {
                                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar4 == '\0') {
                                    if (DAT_1804b2aa0 == 0) {
                                      lVar1 = *in_stack_00000048;
                                      in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                                      in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                                      in_stack_000000d8 = DAT_1804b59f0;
                                      uVar7 = (**(code **)(lVar1 + 0x518))
                                                        (in_stack_00000048,&stack0x00000546,1);
                                      lVar1 = *in_stack_00000048;
                                      pcVar2 = *(code **)(lVar1 + 0x390);
                                      uVar8 = FUN_1800024f0(in_stack_00000048);
                                      uVar9 = FUN_180002550(in_stack_00000048);
                                      uVar10 = (**(code **)(lVar1 + 0x518))
                                                         (in_stack_00000048,&stack0x00000da8,5);
                                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar11 = FUN_1800025a0(in_stack_00000048);
                                      uVar12 = in_stack_00000058;
                                      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11)
                                      ;
                                      (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                      uVar12 = (*in_stack_000000b8)
                                                         (in_stack_00000048,uVar12,in_stack_000000c0
                                                          ,in_stack_000000d8,uVar7);
                                      DAT_1804b2aa0 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar12);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar12);
                                    }
                                    _uStack0000000000000050 =
                                         (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,DAT_1804b2aa0,
                                                    in_stack_00000078);
                                    uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                                       (in_stack_00000048,2,in_stack_00000088,0);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000068,
                                                       in_stack_00000060,0x79b1);
                                    (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,in_stack_000001d0);
                                    (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
                                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,_uStack0000000000000050,
                                                        in_stack_00000080,uVar12);
                                    (**(code **)(*in_stack_00000048 + 0x188))
                                              (in_stack_00000048,uVar12,in_stack_00000070);
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
  return;
}

