/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=68 entry=180377979 blocks=33 maxInstructions=50000 */

/* cut edge: 0x180377979 -> 0x1803351d6 */
/* cut edge: 0x180377a66 -> 0x1803351d6 */
/* cut edge: 0x180377aaa -> 0x1803351d6 */
/* cut edge: 0x180377aee -> 0x1803351d6 */
/* cut edge: 0x180377c56 -> 0x1803351d6 */
/* cut edge: 0x180377d44 -> 0x1803351d6 */
/* cut edge: 0x180377eba -> 0x1803351d6 */
/* cut edge: 0x180377fa7 -> 0x1803351d6 */
/* cut edge: 0x180377feb -> 0x1803351d6 */
/* cut edge: 0x18037802f -> 0x1803351d6 */
/* cut edge: 0x180378197 -> 0x1803351d6 */
/* cut edge: 0x180378284 -> 0x1803351d6 */
/* cut edge: 0x1803782cb -> 0x1803351d6 */
/* cut edge: 0x18037830f -> 0x1803351d6 */
/* cut edge: 0x180378360 -> 0x1803351d6 */
/* cut edge: 0x1803783b1 -> 0x1803351d6 */
/* cut edge: 0x18037840e -> 0x1803351d6 */
/* cut edge: 0x18037846b -> 0x1803351d6 */
/* cut edge: 0x1803784c8 -> 0x1803351d6 */
/* cut edge: 0x180378630 -> 0x1803351d6 */
/* cut edge: 0x18037871d -> 0x180378c0a */
/* cut edge: 0x180378745 -> 0x1803351d6 */
/* cut edge: 0x18037874d -> 0x18037889f */
/* cut edge: 0x18037875d -> 0x18037889f */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18037889f original byte 0x48 replaced by RET */
/* target 0x180378c0a original byte 0x84 replaced by RET */


void region_00068_180377979(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  uint uVar4;
  char cVar5;
  undefined4 uVar6;
  undefined4 uVar7;
  uint uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined4 unaff_R12D;
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
  code *in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000d0;
  ulonglong in_stack_000000d8;
  code *in_stack_000000f0;
  undefined8 in_stack_000000f8;
  ulonglong in_stack_00000100;
  undefined8 in_stack_00000118;
  undefined8 in_stack_00000128;
  int in_stack_00000190;
  int in_stack_00000198;
  int in_stack_000001a8;
  undefined8 in_stack_000001d0;
  undefined8 in_stack_000001f8;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x67b9);
  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001f8);
  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar9);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar9,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    (**(code **)(*in_stack_00000048 + 0x658))
              (in_stack_00000048,in_stack_000000a0,uVar6,1,&stack0x00000050);
    uVar6 = uStack0000000000000050;
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar6);
      (**(code **)(*in_stack_00000048 + 0x698))
                (in_stack_00000048,in_stack_000000a0,unaff_R12D,1,&stack0x00000050);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804b28f8 == 0) {
            lVar2 = *in_stack_00000048;
            in_stack_000000b8 = *(code **)(lVar2 + 0x390);
            in_stack_000000c0 = FUN_180333550(in_stack_00000048);
            in_stack_000000d8 = DAT_1804b59f0;
            uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005b0,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar11 = FUN_1800024f0(in_stack_00000048);
            uVar12 = FUN_180002550(in_stack_00000048);
            uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000010f8,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar14 = FUN_1800025a0(in_stack_00000048);
            uVar9 = in_stack_00000058;
            uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
            (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
            uVar9 = (*in_stack_000000b8)
                              (in_stack_00000048,uVar9,in_stack_000000c0,in_stack_000000d8,uVar10);
            DAT_1804b28f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b28f8,in_stack_00000078);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x67b9);
          (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000001f8);
          (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar9);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar9,in_stack_00000070);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b2900 == 0) {
                lVar2 = *in_stack_00000048;
                in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                in_stack_000000d8 = DAT_1804b59f0;
                in_stack_00000100 =
                     (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005ae,1);
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000010e8,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar9 = in_stack_00000058;
                uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
                (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                uVar9 = (*in_stack_000000b8)
                                  (in_stack_00000048,uVar9,in_stack_000000c0,in_stack_000000d8,
                                   in_stack_00000100);
                DAT_1804b2900 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
              }
              _uStack0000000000000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2900,in_stack_00000078);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x79b1);
              (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                  in_stack_000001d0);
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
                            (in_stack_00000048,in_stack_000000a0,uVar6,1,&stack0x00000050);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar5 == '\0') {
                      if (DAT_1804b2908 == 0) {
                        lVar2 = *in_stack_00000048;
                        in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                        in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                        in_stack_000000d8 = DAT_1804b59f0;
                        uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005ac,1);
                        lVar2 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar11 = FUN_1800024f0(in_stack_00000048);
                        uVar12 = FUN_180002550(in_stack_00000048);
                        uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000010d8,5);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar14 = FUN_1800025a0(in_stack_00000048);
                        uVar9 = in_stack_00000058;
                        uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                        (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                        uVar9 = (*in_stack_000000b8)
                                          (in_stack_00000048,uVar9,in_stack_000000c0,
                                           in_stack_000000d8,uVar10);
                        DAT_1804b2908 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                      }
                      _uStack0000000000000050 =
                           (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b2908,in_stack_00000078);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                          0x79b1);
                      (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                          in_stack_000001d0);
                      (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,_uStack0000000000000050,in_stack_00000080
                                         ,uVar9);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                        (in_stack_00000048,uVar9,in_stack_00000070);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        (**(code **)(*in_stack_00000048 + 0x658))
                                  (in_stack_00000048,in_stack_000000a0,3,1,&stack0x00000050);
                        uVar7 = uStack0000000000000050;
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          uStack0000000000000050 = uVar7;
                          (**(code **)(*in_stack_00000048 + 0x698))
                                    (in_stack_00000048,in_stack_000000a0,uVar6,1,&stack0x00000050);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            _uStack0000000000000050 =
                                 CONCAT44(uStack0000000000000054,uStack00000000000000b0);
                            (**(code **)(*in_stack_00000048 + 0x698))
                                      (in_stack_00000048,in_stack_000000a0,3,1,&stack0x00000050);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              _uStack00000000000000b0 = 0;
                              (**(code **)(*in_stack_00000048 + 0x658))
                                        (in_stack_00000048,in_stack_000000a0,0,1,&stack0x00000050);
                              in_stack_00000100 = _uStack0000000000000050 & 0xffffffff;
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                in_stack_000001a8 = (int)_uStack00000000000000b0 + 1;
                                (**(code **)(*in_stack_00000048 + 0x658))
                                          (in_stack_00000048,in_stack_000000a0,in_stack_000001a8,1,
                                           &stack0x00000050);
                                in_stack_000000d8 = _uStack0000000000000050 & 0xffffffff;
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  in_stack_00000190 = (int)_uStack00000000000000b0 + 2;
                                  (**(code **)(*in_stack_00000048 + 0x658))
                                            (in_stack_00000048,in_stack_000000a0,in_stack_00000190,1
                                             ,&stack0x00000050);
                                  in_stack_000000b8 = (code *)(_uStack0000000000000050 & 0xffffffff)
                                  ;
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar5 == '\0') {
                                    in_stack_00000198 = (int)_uStack00000000000000b0 + 3;
                                    (**(code **)(*in_stack_00000048 + 0x658))
                                              (in_stack_00000048,in_stack_000000a0,in_stack_00000198
                                               ,1,&stack0x00000050);
                                    in_stack_000000c0 = _uStack0000000000000050 & 0xffffffff;
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar5 == '\0') {
                                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar5 == '\0') {
                                        if (DAT_1804b2910 == 0) {
                                          lVar2 = *in_stack_00000048;
                                          in_stack_000000f0 = *(code **)(lVar2 + 0x390);
                                          in_stack_000000f8 = FUN_180333550(in_stack_00000048);
                                          in_stack_00000118 = DAT_1804b59f0;
                                          uVar10 = (**(code **)(lVar2 + 0x518))
                                                             (in_stack_00000048,&stack0x000005aa,1);
                                          lVar2 = *in_stack_00000048;
                                          pcVar1 = *(code **)(lVar2 + 0x390);
                                          uVar11 = FUN_1800024f0(in_stack_00000048);
                                          uVar12 = FUN_180002550(in_stack_00000048);
                                          uVar13 = (**(code **)(lVar2 + 0x518))
                                                             (in_stack_00000048,&stack0x000010c8,5);
                                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar14 = FUN_1800025a0(in_stack_00000048);
                                          uVar9 = in_stack_00000058;
                                          uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,
                                                             uVar14);
                                          (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                          uVar9 = (*in_stack_000000f0)
                                                            (in_stack_00000048,uVar9,
                                                             in_stack_000000f8,in_stack_00000118,
                                                             uVar10);
                                          DAT_1804b2910 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar9);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar9);
                                        }
                                        _uStack0000000000000050 =
                                             (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,DAT_1804b2910,
                                                        in_stack_00000078);
                                        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                                          (in_stack_00000048,2,in_stack_00000088,0);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                           (in_stack_00000048,in_stack_00000068,
                                                            in_stack_00000060,0x4c73);
                                        (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                                           (in_stack_00000048,in_stack_00000098,
                                                            in_stack_00000090,in_stack_000000d0);
                                        (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,_uStack0000000000000050
                                                           ,in_stack_00000080,uVar9);
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                                                          (in_stack_00000048,uVar9,in_stack_00000070
                                                          );
                                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
                                        if (cVar5 == '\0') {
                                          uVar4 = (uint)in_stack_00000100;
                                          in_stack_00000118 =
                                               CONCAT44(in_stack_00000118._4_4_,uVar4 * 2);
                                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                          if ((uVar8 & uVar4) == 0) {
                                            return;
                                          }
                                          if (cVar5 == '\0') {
                                            if (DAT_1804b2918 == 0) {
                                              lVar2 = *in_stack_00000048;
                                              in_stack_000000f0 = *(code **)(lVar2 + 0x390);
                                              in_stack_000000f8 = FUN_180333550(in_stack_00000048);
                                              in_stack_00000128 = DAT_1804b59f0;
                                              uVar10 = (**(code **)(lVar2 + 0x518))
                                                                 (in_stack_00000048,&stack0x000005a8
                                                                  ,1);
                                              lVar2 = *in_stack_00000048;
                                              pcVar1 = *(code **)(lVar2 + 0x390);
                                              uVar11 = FUN_1800024f0(in_stack_00000048);
                                              uVar12 = FUN_180002550(in_stack_00000048);
                                              uVar13 = (**(code **)(lVar2 + 0x518))
                                                                 (in_stack_00000048,&stack0x000010b8
                                                                  ,5);
                                              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                              uVar14 = FUN_1800025a0(in_stack_00000048);
                                              uVar9 = in_stack_00000058;
                                              uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058
                                                                 ,uVar14);
                                              (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,
                                                        uVar14);
                                              uVar9 = (*in_stack_000000f0)
                                                                (in_stack_00000048,uVar9,
                                                                 in_stack_000000f8,in_stack_00000128
                                                                 ,uVar10);
                                              DAT_1804b2918 =
                                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                                             (in_stack_00000048,uVar9);
                                              (**(code **)(*in_stack_00000048 + 0xb8))
                                                        (in_stack_00000048,uVar9);
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

