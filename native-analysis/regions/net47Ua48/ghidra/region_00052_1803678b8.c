/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=52 entry=1803678b8 blocks=36 maxInstructions=50000 */

/* cut edge: 0x1803678b8 -> 0x1803351d6 */
/* cut edge: 0x1803679ba -> 0x1803351d6 */
/* cut edge: 0x1803679f2 -> 0x1803351d6 */
/* cut edge: 0x180367a3b -> 0x1803351d6 */
/* cut edge: 0x180367a7a -> 0x1803351d6 */
/* cut edge: 0x180367ac3 -> 0x180367766 */
/* cut edge: 0x180367afc -> 0x1803351d6 */
/* cut edge: 0x180367b01 -> 0x1803351d6 */
/* cut edge: 0x180367b4c -> 0x1803351d6 */
/* cut edge: 0x180367b93 -> 0x1803351d6 */
/* cut edge: 0x180367bda -> 0x1803351d6 */
/* cut edge: 0x180367d42 -> 0x1803351d6 */
/* cut edge: 0x180367e2f -> 0x1803351d6 */
/* cut edge: 0x180367e73 -> 0x1803351d6 */
/* cut edge: 0x180367eba -> 0x1803351d6 */
/* cut edge: 0x180368022 -> 0x1803351d6 */
/* cut edge: 0x180368110 -> 0x1803351d6 */
/* cut edge: 0x180368286 -> 0x1803351d6 */
/* cut edge: 0x180368373 -> 0x1803351d6 */
/* cut edge: 0x1803683b7 -> 0x1803351d6 */
/* cut edge: 0x1803683fb -> 0x1803351d6 */
/* cut edge: 0x180368563 -> 0x1803351d6 */
/* cut edge: 0x180368650 -> 0x1803351d6 */
/* cut edge: 0x18036869b -> 0x1803351d6 */
/* cut edge: 0x1803686e9 -> 0x1803351d6 */
/* cut edge: 0x1803686ff -> 0x180368852 */
/* cut edge: 0x18036870f -> 0x180368852 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180367766 original byte 0x48 replaced by RET */
/* target 0x180368852 original byte 0x48 replaced by RET */


void region_00052_1803678b8(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  ulonglong uVar4;
  char cVar5;
  int iVar6;
  undefined4 uVar7;
  undefined4 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
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
  undefined8 in_stack_000000e0;
  undefined8 in_stack_00000100;
  int iStack00000000000001a8;
  undefined8 in_stack_00000220;
  undefined8 in_stack_00000228;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar9);
  iVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar9,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    if (iStack00000000000001a8 < iVar6) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
      uVar9 = FUN_1803dab60(in_stack_00000048);
      uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_000000a0,_iStack00000000000001a8,1,&stack0x00000050);
        uVar4 = _uStack0000000000000050;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          (**(code **)(*in_stack_00000048 + 0x658))
                    (in_stack_00000048,uVar9,uVar4 & 0xffffffff,1,&stack0x00000050);
          uVar7 = uStack0000000000000050;
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar7);
            (**(code **)(*in_stack_00000048 + 0x698))
                      (in_stack_00000048,in_stack_000000a0,_iStack00000000000001a8,1,
                       &stack0x00000050);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              _iStack00000000000001a8 =
                   _iStack00000000000001a8 & 0xffffffff00000000 |
                   (ulonglong)((int)_iStack00000000000001a8 + 1);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                return;
              }
            }
          }
        }
      }
    }
    else {
      (**(code **)(*in_stack_00000048 + 0x658))
                (in_stack_00000048,in_stack_000000a0,1,1,&stack0x00000050);
      in_stack_000000b0 = uStack0000000000000050;
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_000000a0,5,1,&stack0x00000050);
        uVar7 = uStack0000000000000050;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar7);
          (**(code **)(*in_stack_00000048 + 0x698))
                    (in_stack_00000048,in_stack_000000a0,1,1,&stack0x00000050);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((cVar5 == '\0') &&
             (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
            if (DAT_1804b2608 == 0) {
              lVar2 = *in_stack_00000048;
              in_stack_000000b8 = *(code **)(lVar2 + 0x390);
              in_stack_000000c0 = FUN_180333550(in_stack_00000048);
              in_stack_000000d8 = DAT_1804b59f0;
              uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000066c,1);
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar11 = FUN_1800024f0(in_stack_00000048);
              uVar12 = FUN_180002550(in_stack_00000048);
              uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000016d8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar14 = FUN_1800025a0(in_stack_00000048);
              uVar9 = in_stack_00000058;
              uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
              (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
              uVar9 = (*in_stack_000000b8)
                                (in_stack_00000048,uVar9,in_stack_000000c0,in_stack_000000d8,uVar10)
              ;
              DAT_1804b2608 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2608,in_stack_00000078);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2d6c);
            (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                in_stack_00000228);
            (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar9);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar9,in_stack_00000070);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              (**(code **)(*in_stack_00000048 + 0x658))
                        (in_stack_00000048,in_stack_000000a0,uVar7,1,&stack0x00000050);
              uVar7 = uStack0000000000000050;
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar7);
                (**(code **)(*in_stack_00000048 + 0x698))
                          (in_stack_00000048,in_stack_000000a0,5,1,&stack0x00000050);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((cVar5 == '\0') &&
                   (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
                  if (DAT_1804b2610 == 0) {
                    lVar2 = *in_stack_00000048;
                    in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                    in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                    in_stack_000000d8 = DAT_1804b59f0;
                    uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000066a,1);
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar11 = FUN_1800024f0(in_stack_00000048);
                    uVar12 = FUN_180002550(in_stack_00000048);
                    uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000016c8,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar14 = FUN_1800025a0(in_stack_00000048);
                    uVar9 = in_stack_00000058;
                    uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                    (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                    uVar9 = (*in_stack_000000b8)
                                      (in_stack_00000048,uVar9,in_stack_000000c0,in_stack_000000d8,
                                       uVar10);
                    DAT_1804b2610 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                  }
                  _uStack0000000000000050 =
                       (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b2610,in_stack_00000078);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2d6c);
                  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                      in_stack_00000228);
                  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,
                                     uVar9);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar9,in_stack_00000070);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((cVar5 == '\0') &&
                     (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
                    if (DAT_1804b2618 == 0) {
                      lVar2 = *in_stack_00000048;
                      in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                      in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                      in_stack_000000d8 = DAT_1804b59f0;
                      in_stack_00000100 =
                           (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000668,1);
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar10 = FUN_1800024f0(in_stack_00000048);
                      uVar11 = FUN_180002550(in_stack_00000048);
                      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000016b8,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar13 = FUN_1800025a0(in_stack_00000048);
                      uVar9 = in_stack_00000058;
                      uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
                      (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                      uVar9 = (*in_stack_000000b8)
                                        (in_stack_00000048,uVar9,in_stack_000000c0,in_stack_000000d8
                                         ,in_stack_00000100);
                      DAT_1804b2618 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                    }
                    _uStack0000000000000050 =
                         (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b2618,in_stack_00000078);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4516
                                       );
                    (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                        in_stack_00000220);
                    (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,
                                       uVar9);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                                      (in_stack_00000048,uVar9,in_stack_00000070);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      (**(code **)(*in_stack_00000048 + 0x658))
                                (in_stack_00000048,in_stack_000000a0,uVar8,1,&stack0x00000050);
                      uVar8 = uStack0000000000000050;
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar8);
                        (**(code **)(*in_stack_00000048 + 0x698))
                                  (in_stack_00000048,in_stack_000000a0,uVar7,1,&stack0x00000050);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((cVar5 == '\0') &&
                           (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
                          if (DAT_1804b2620 == 0) {
                            lVar2 = *in_stack_00000048;
                            in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                            in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                            in_stack_000000d8 = DAT_1804b59f0;
                            uVar10 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00000666,1);
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar11 = FUN_1800024f0(in_stack_00000048);
                            uVar12 = FUN_180002550(in_stack_00000048);
                            uVar13 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x000016a8,5);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar14 = FUN_1800025a0(in_stack_00000048);
                            uVar9 = in_stack_00000058;
                            uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                            (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                            uVar9 = (*in_stack_000000b8)
                                              (in_stack_00000048,uVar9,in_stack_000000c0,
                                               in_stack_000000d8,uVar10);
                            DAT_1804b2620 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                          }
                          _uStack0000000000000050 =
                               (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,DAT_1804b2620,in_stack_00000078);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000088,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                              0x4516);
                          (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                              in_stack_00000220);
                          (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,_uStack0000000000000050,
                                             in_stack_00000080,uVar9);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                                            (in_stack_00000048,uVar9,in_stack_00000070);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            _uStack0000000000000050 =
                                 CONCAT44(uStack0000000000000054,in_stack_000000b0);
                            (**(code **)(*in_stack_00000048 + 0x698))
                                      (in_stack_00000048,in_stack_000000a0,uVar7,1,&stack0x00000050)
                            ;
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              (**(code **)(*in_stack_00000048 + 0x658))
                                        (in_stack_00000048,in_stack_000000a0,2,1,&stack0x00000050);
                              in_stack_000000b0 = uStack0000000000000050;
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if ((cVar5 == '\0') &&
                                 (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0'
                                 )) {
                                if (DAT_1804b2628 == 0) {
                                  lVar2 = *in_stack_00000048;
                                  in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                                  in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                                  in_stack_000000d8 = DAT_1804b59f0;
                                  uVar10 = (**(code **)(lVar2 + 0x518))
                                                     (in_stack_00000048,&stack0x00000664,1);
                                  lVar2 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar2 + 0x390);
                                  uVar11 = FUN_1800024f0(in_stack_00000048);
                                  uVar12 = FUN_180002550(in_stack_00000048);
                                  uVar13 = (**(code **)(lVar2 + 0x518))
                                                     (in_stack_00000048,&stack0x00001698,5);
                                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar14 = FUN_1800025a0(in_stack_00000048);
                                  uVar9 = in_stack_00000058;
                                  uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                                  (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                  uVar9 = (*in_stack_000000b8)
                                                    (in_stack_00000048,uVar9,in_stack_000000c0,
                                                     in_stack_000000d8,uVar10);
                                  DAT_1804b2628 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar9);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
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
  return;
}

