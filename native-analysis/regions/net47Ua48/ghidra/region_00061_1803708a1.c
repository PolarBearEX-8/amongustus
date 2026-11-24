/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=61 entry=1803708a1 blocks=32 maxInstructions=50000 */

/* cut edge: 0x1803708a1 -> 0x1803351d6 */
/* cut edge: 0x18037098e -> 0x1803351d6 */
/* cut edge: 0x1803709d2 -> 0x1803351d6 */
/* cut edge: 0x180370a1b -> 0x1803351d6 */
/* cut edge: 0x180370b83 -> 0x1803351d6 */
/* cut edge: 0x180370c70 -> 0x1803351d6 */
/* cut edge: 0x180370cbb -> 0x1803351d6 */
/* cut edge: 0x180370d09 -> 0x1803351d6 */
/* cut edge: 0x180370e72 -> 0x1803351d6 */
/* cut edge: 0x180370f5f -> 0x1803351d6 */
/* cut edge: 0x180370fa3 -> 0x1803351d6 */
/* cut edge: 0x180370fea -> 0x1803351d6 */
/* cut edge: 0x180371152 -> 0x1803351d6 */
/* cut edge: 0x18037123f -> 0x1803351d6 */
/* cut edge: 0x18037128a -> 0x1803351d6 */
/* cut edge: 0x1803713f2 -> 0x1803351d6 */
/* cut edge: 0x1803714df -> 0x1803351d6 */
/* cut edge: 0x18037152a -> 0x1803351d6 */
/* cut edge: 0x180371692 -> 0x1803351d6 */
/* cut edge: 0x180371784 -> 0x1803351d6 */
/* cut edge: 0x18037179a -> 0x1803718ed */
/* cut edge: 0x1803717aa -> 0x1803718ed */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803718ed original byte 0x48 replaced by RET */


void region_00061_1803708a1(undefined8 param_1,undefined8 param_2)

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
  undefined4 uStack00000000000000b0;
  undefined4 uStack00000000000000b4;
  code *in_stack_000000b8;
  code *in_stack_000000c0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000218;
  undefined8 in_stack_00000220;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4516);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000220);
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
                (in_stack_00000048,in_stack_000000a0,(ulonglong)in_stack_000000b8 & 0xffffffff,1,
                 &stack0x00000050);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar4 == '\0') {
          if (DAT_1804b27b8 == 0) {
            lVar2 = *in_stack_00000048;
            in_stack_000000b8 = *(code **)(lVar2 + 0x390);
            in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_000000d8 = DAT_1804b59f0;
            uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000600,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001378,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar6 = in_stack_00000058;
            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
            (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar6 = (*in_stack_000000b8)
                              (in_stack_00000048,uVar6,in_stack_000000c0,in_stack_000000d8,uVar7);
            DAT_1804b27b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b27b8,in_stack_00000078);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4516);
          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000220
                            );
          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar6);
          uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar6,in_stack_00000070);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uStack00000000000000b0);
            (**(code **)(*in_stack_00000048 + 0x698))
                      (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar4 == '\0') {
              (**(code **)(*in_stack_00000048 + 0x658))
                        (in_stack_00000048,in_stack_000000a0,2,1,&stack0x00000050);
              _uStack00000000000000b0 =
                   (code *)CONCAT44(uStack00000000000000b4,uStack0000000000000050);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar4 == '\0') {
                  if (DAT_1804b27c0 == 0) {
                    lVar2 = *in_stack_00000048;
                    in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                    in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
                    in_stack_000000d8 = DAT_1804b59f0;
                    uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005fe,1);
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001368,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar6 = in_stack_00000058;
                    uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                    (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar6 = (*in_stack_000000b8)
                                      (in_stack_00000048,uVar6,in_stack_000000c0,in_stack_000000d8,
                                       uVar7);
                    DAT_1804b27c0 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                  }
                  _uStack0000000000000050 =
                       (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b27c0,in_stack_00000078);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x3e8f);
                  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_00000218);
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
                    uVar5 = uStack0000000000000050;
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar4 == '\0') {
                      _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar5);
                      (**(code **)(*in_stack_00000048 + 0x698))
                                (in_stack_00000048,in_stack_000000a0,2,1,&stack0x00000050);
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar4 == '\0') {
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar4 == '\0') {
                          if (DAT_1804b27c8 == 0) {
                            lVar2 = *in_stack_00000048;
                            in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                            in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
                            in_stack_000000d8 = DAT_1804b59f0;
                            uVar7 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,&stack0x000005fc,1);
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar8 = FUN_1800024f0(in_stack_00000048);
                            uVar9 = FUN_180002550(in_stack_00000048);
                            uVar10 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00001358,5);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar11 = FUN_1800025a0(in_stack_00000048);
                            uVar6 = in_stack_00000058;
                            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                            (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                            uVar6 = (*in_stack_000000b8)
                                              (in_stack_00000048,uVar6,in_stack_000000c0,
                                               in_stack_000000d8,uVar7);
                            DAT_1804b27c8 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          _uStack0000000000000050 =
                               (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,DAT_1804b27c8,in_stack_00000078);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000088,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                             0x3e8f);
                          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_00000218);
                          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,_uStack0000000000000050,
                                             in_stack_00000080,uVar6);
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                            (in_stack_00000048,uVar6,in_stack_00000070);
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar4 == '\0') {
                            _uStack0000000000000050 =
                                 CONCAT44(uStack0000000000000054,uStack00000000000000b0);
                            (**(code **)(*in_stack_00000048 + 0x698))
                                      (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050)
                            ;
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar4 == '\0') {
                              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar4 == '\0') {
                                if (DAT_1804b27d0 == 0) {
                                  lVar2 = *in_stack_00000048;
                                  _uStack00000000000000b0 = *(code **)(lVar2 + 0x390);
                                  in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
                                  in_stack_000000c0 = (code *)DAT_1804b59f0;
                                  uVar7 = (**(code **)(lVar2 + 0x518))
                                                    (in_stack_00000048,&stack0x000005fa,1);
                                  lVar2 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar2 + 0x390);
                                  uVar8 = FUN_1800024f0(in_stack_00000048);
                                  uVar9 = FUN_180002550(in_stack_00000048);
                                  uVar10 = (**(code **)(lVar2 + 0x518))
                                                     (in_stack_00000048,&stack0x00001348,5);
                                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar11 = FUN_1800025a0(in_stack_00000048);
                                  uVar6 = in_stack_00000058;
                                  uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                                  (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                  uVar6 = (*_uStack00000000000000b0)
                                                    (in_stack_00000048,uVar6,in_stack_000000b8,
                                                     in_stack_000000c0,uVar7);
                                  DAT_1804b27d0 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar6);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                                }
                                _uStack0000000000000050 =
                                     (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,DAT_1804b27d0,in_stack_00000078);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_00000088,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000068,
                                                   in_stack_00000060,0x5a10);
                                (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_00000090,in_stack_000000e8);
                                (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,_uStack0000000000000050,
                                                   in_stack_00000080,uVar6);
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                                  (in_stack_00000048,uVar6,in_stack_00000070);
                                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar4 == '\0') {
                                  (**(code **)(*in_stack_00000048 + 0x658))
                                            (in_stack_00000048,in_stack_000000a0,uVar5,1,
                                             &stack0x00000050);
                                  in_stack_000000b8 =
                                       (code *)CONCAT44(in_stack_000000b8._4_4_,
                                                        uStack0000000000000050);
                                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar4 == '\0') {
                                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if (cVar4 == '\0') {
                                      if (DAT_1804b27d8 == 0) {
                                        lVar2 = *in_stack_00000048;
                                        _uStack00000000000000b0 = *(code **)(lVar2 + 0x390);
                                        in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048)
                                        ;
                                        in_stack_000000d8 = DAT_1804b59f0;
                                        uVar7 = (**(code **)(lVar2 + 0x518))
                                                          (in_stack_00000048,&stack0x000005f8,1);
                                        lVar2 = *in_stack_00000048;
                                        pcVar1 = *(code **)(lVar2 + 0x390);
                                        uVar8 = FUN_1800024f0(in_stack_00000048);
                                        uVar9 = FUN_180002550(in_stack_00000048);
                                        uVar10 = (**(code **)(lVar2 + 0x518))
                                                           (in_stack_00000048,&stack0x00001338,5);
                                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar11 = FUN_1800025a0(in_stack_00000048);
                                        uVar6 = in_stack_00000058;
                                        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,
                                                           uVar11);
                                        (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                        uVar6 = (*_uStack00000000000000b0)
                                                          (in_stack_00000048,uVar6,in_stack_000000c0
                                                           ,in_stack_000000d8,uVar7);
                                        DAT_1804b27d8 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar6);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar6);
                                      }
                                      _uStack0000000000000050 =
                                           (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,DAT_1804b27d8,
                                                      in_stack_00000078);
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,2,in_stack_00000088,0);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000068,
                                                         in_stack_00000060,0x5a10);
                                      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_00000090,in_stack_000000e8);
                                      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,_uStack0000000000000050,
                                                         in_stack_00000080,uVar6);
                                      uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                                        (in_stack_00000048,uVar6,in_stack_00000070);
                                      _uStack00000000000000b0 =
                                           (code *)CONCAT44(uStack00000000000000b4,uVar5);
                                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                                      if (cVar4 == '\0') {
                                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                                        if (cVar4 == '\0') {
                                          if (DAT_1804b27e0 == 0) {
                                            lVar2 = *in_stack_00000048;
                                            in_stack_000000c0 = *(code **)(lVar2 + 0x390);
                                            in_stack_000000d8 = FUN_180333550(in_stack_00000048);
                                            in_stack_00000100 = DAT_1804b59f0;
                                            uVar7 = (**(code **)(lVar2 + 0x518))
                                                              (in_stack_00000048,&stack0x000005f6,1)
                                            ;
                                            lVar2 = *in_stack_00000048;
                                            pcVar1 = *(code **)(lVar2 + 0x390);
                                            uVar8 = FUN_1800024f0(in_stack_00000048);
                                            uVar9 = FUN_180002550(in_stack_00000048);
                                            uVar10 = (**(code **)(lVar2 + 0x518))
                                                               (in_stack_00000048,&stack0x00001328,5
                                                               );
                                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                            uVar11 = FUN_1800025a0(in_stack_00000048);
                                            uVar6 = in_stack_00000058;
                                            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,
                                                               uVar11);
                                            (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                            uVar6 = (*in_stack_000000c0)
                                                              (in_stack_00000048,uVar6,
                                                               in_stack_000000d8,in_stack_00000100,
                                                               uVar7);
                                            DAT_1804b27e0 =
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
  return;
}

