/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=43 entry=18035e91e blocks=33 maxInstructions=50000 */

/* cut edge: 0x18035e91e -> 0x1803351d6 */
/* cut edge: 0x18035ea10 -> 0x1803351d6 */
/* cut edge: 0x18035eb79 -> 0x1803351d6 */
/* cut edge: 0x18035ec66 -> 0x1803351d6 */
/* cut edge: 0x18035ecaa -> 0x1803351d6 */
/* cut edge: 0x18035ecf3 -> 0x1803351d6 */
/* cut edge: 0x18035ee5b -> 0x1803351d6 */
/* cut edge: 0x18035ef48 -> 0x1803351d6 */
/* cut edge: 0x18035ef8f -> 0x1803351d6 */
/* cut edge: 0x18035efd3 -> 0x1803351d6 */
/* cut edge: 0x18035f021 -> 0x1803351d6 */
/* cut edge: 0x18035f189 -> 0x1803351d6 */
/* cut edge: 0x18035f277 -> 0x1803351d6 */
/* cut edge: 0x18035f40f -> 0x1803351d6 */
/* cut edge: 0x18035f505 -> 0x1803351d6 */
/* cut edge: 0x18035f54e -> 0x1803351d6 */
/* cut edge: 0x18035f593 -> 0x1803351d6 */
/* cut edge: 0x18035f5f3 -> 0x1803351d6 */
/* cut edge: 0x18035f5f8 -> 0x1803351d6 */
/* cut edge: 0x18035f760 -> 0x1803351d6 */
/* cut edge: 0x18035f760 -> 0x18035f84d */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18035f84d original byte 0x48 replaced by RET */


void region_00043_18035e91e(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  undefined4 uVar4;
  uint uVar5;
  uint uVar6;
  char cVar7;
  undefined4 uVar8;
  int iVar9;
  int iVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  uint uVar17;
  longlong *in_stack_00000048;
  uint uStack0000000000000050;
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
  code *in_stack_000000c0;
  code *in_stack_000000d8;
  undefined8 in_stack_000000e0;
  code *in_stack_00000100;
  undefined8 in_stack_00000130;
  undefined8 in_stack_000001d0;
  undefined8 in_stack_000001f8;
  int in_stack_00000200;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x67b9);
  (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001f8);
  (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar11);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070);
  in_stack_000000b0 = (code *)CONCAT44(in_stack_000000b0._4_4_,uVar8);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
    if (DAT_1804b2498 == 0) {
      lVar2 = *in_stack_00000048;
      in_stack_000000c0 = *(code **)(lVar2 + 0x390);
      in_stack_000000d8 = (code *)FUN_180333550(in_stack_00000048);
      in_stack_00000100 = DAT_1804b59f0;
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000006c8,1);
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar13 = FUN_1800024f0(in_stack_00000048);
      uVar14 = FUN_180002550(in_stack_00000048);
      uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000019b8,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar16 = FUN_1800025a0(in_stack_00000048);
      uVar11 = in_stack_00000058;
      uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar16);
      (*pcVar1)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
      uVar11 = (*in_stack_000000c0)
                         (in_stack_00000048,uVar11,in_stack_000000d8,in_stack_00000100,uVar12);
      DAT_1804b2498 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
    }
    _uStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2498,in_stack_00000078);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x79b1);
    (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001d0);
    (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar11);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 == '\0') {
      (**(code **)(*in_stack_00000048 + 0x658))
                (in_stack_00000048,in_stack_000000a0,uVar8,1,&stack0x00000050);
      uVar8 = uStack0000000000000050;
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 == '\0') {
        _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar8);
        (**(code **)(*in_stack_00000048 + 0x698))
                  (in_stack_00000048,in_stack_000000a0,(ulonglong)in_stack_000000b0 & 0xffffffff,1,
                   &stack0x00000050);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0'))
        {
          if (DAT_1804b24a0 == 0) {
            lVar2 = *in_stack_00000048;
            in_stack_000000b0 = *(code **)(lVar2 + 0x390);
            in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_000000d8 = DAT_1804b59f0;
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000006c6,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar13 = FUN_1800024f0(in_stack_00000048);
            uVar14 = FUN_180002550(in_stack_00000048);
            uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000019a8,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar16 = FUN_1800025a0(in_stack_00000048);
            uVar11 = in_stack_00000058;
            uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar16);
            (*pcVar1)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
            uVar11 = (*in_stack_000000b0)
                               (in_stack_00000048,uVar11,in_stack_000000c0,in_stack_000000d8,uVar12)
            ;
            DAT_1804b24a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b24a0,in_stack_00000078);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x79b1);
          (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000001d0);
          (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar11);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar11,in_stack_00000070);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 == '\0') {
            (**(code **)(*in_stack_00000048 + 0x658))
                      (in_stack_00000048,in_stack_000000a0,3,1,&stack0x00000050);
            uVar4 = uStack0000000000000050;
            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar7 == '\0') {
              uStack0000000000000050 = uVar4;
              (**(code **)(*in_stack_00000048 + 0x698))
                        (in_stack_00000048,in_stack_000000a0,uVar8,1,&stack0x00000050);
              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar7 == '\0') {
                _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uStack00000000000000b8);
                (**(code **)(*in_stack_00000048 + 0x698))
                          (in_stack_00000048,in_stack_000000a0,3,1,&stack0x00000050);
                cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((cVar7 == '\0') &&
                   (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
                  if (DAT_1804b24a8 == 0) {
                    lVar2 = *in_stack_00000048;
                    in_stack_000000b0 = *(code **)(lVar2 + 0x390);
                    _uStack00000000000000b8 = FUN_180333550(in_stack_00000048);
                    in_stack_000000c0 = DAT_1804b59f0;
                    uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000006c4,1);
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar13 = FUN_1800024f0(in_stack_00000048);
                    uVar14 = FUN_180002550(in_stack_00000048);
                    uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001998,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar16 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = in_stack_00000058;
                    uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar16);
                    (*pcVar1)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
                    uVar11 = (*in_stack_000000b0)
                                       (in_stack_00000048,uVar11,_uStack00000000000000b8,
                                        in_stack_000000c0,uVar12);
                    DAT_1804b24a8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                  }
                  _uStack0000000000000050 =
                       (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b24a8,in_stack_00000078);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                     (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
                  (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                      in_stack_000000e0);
                  (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,
                                      uVar11);
                  iVar9 = (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar11,in_stack_00000070);
                  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((cVar7 == '\0') &&
                     (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
                    iVar9 = iVar9 * in_stack_00000200;
                    uVar17 = 0;
                    do {
                      if (DAT_1804b24b0 == 0) {
                        lVar2 = *in_stack_00000048;
                        in_stack_000000b0 = *(code **)(lVar2 + 0x390);
                        _uStack00000000000000b8 = FUN_180333550(in_stack_00000048);
                        in_stack_000000c0 = DAT_1804b59f0;
                        in_stack_000000d8 =
                             (code *)(**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x000006c2,1);
                        lVar2 = *in_stack_00000048;
                        in_stack_00000100 = *(code **)(lVar2 + 0x390);
                        uVar12 = FUN_1800024f0(in_stack_00000048);
                        uVar13 = FUN_180002550(in_stack_00000048);
                        uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001988,5);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar15 = FUN_1800025a0(in_stack_00000048);
                        uVar11 = in_stack_00000058;
                        uVar15 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar15);
                        (*in_stack_00000100)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                        uVar11 = (*in_stack_000000b0)
                                           (in_stack_00000048,uVar11,_uStack00000000000000b8,
                                            in_stack_000000c0,in_stack_000000d8);
                        DAT_1804b24b0 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                      }
                      _uStack0000000000000050 =
                           (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b24b0,in_stack_00000078);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                         (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                          0x583d);
                      (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                          in_stack_000000e0);
                      (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,_uStack0000000000000050,
                                          in_stack_00000080,uVar11);
                      iVar10 = (**(code **)(*in_stack_00000048 + 0x188))
                                         (in_stack_00000048,uVar11,in_stack_00000070);
                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar7 != '\0') {
                        return;
                      }
                      if (iVar10 <= (int)uVar17) {
                        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar7 != '\0') {
                          return;
                        }
                        if (DAT_1804b24b8 == 0) {
                          lVar2 = *in_stack_00000048;
                          in_stack_000000b0 = *(code **)(lVar2 + 0x390);
                          _uStack00000000000000b8 = FUN_180333550(in_stack_00000048);
                          in_stack_000000c0 = DAT_1804b59f0;
                          uVar12 = (**(code **)(lVar2 + 0x518))
                                             (in_stack_00000048,&stack0x000006c0,1);
                          lVar2 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar2 + 0x390);
                          uVar13 = FUN_1800024f0(in_stack_00000048);
                          uVar14 = FUN_180002550(in_stack_00000048);
                          uVar15 = (**(code **)(lVar2 + 0x518))
                                             (in_stack_00000048,&stack0x00001978,5);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar16 = FUN_1800025a0(in_stack_00000048);
                          uVar11 = in_stack_00000058;
                          uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar16);
                          (*pcVar1)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
                          uVar11 = (*in_stack_000000b0)
                                             (in_stack_00000048,uVar11,_uStack00000000000000b8,
                                              in_stack_000000c0,uVar12);
                          DAT_1804b24b8 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                        }
                        _uStack0000000000000050 =
                             (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b24b8,in_stack_00000078);
                        uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                           (in_stack_00000048,2,in_stack_00000088,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                            0x583d);
                        (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                            in_stack_000000e0);
                        (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
                        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,_uStack0000000000000050,
                                            in_stack_00000080,uVar11);
                        (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar11,in_stack_00000070);
                        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar7 != '\0') {
                          return;
                        }
                        return;
                      }
                      uStack0000000000000050 = uVar17;
                      (**(code **)(*in_stack_00000048 + 0x658))
                                (in_stack_00000048,in_stack_000000a0,uVar17,1,&stack0x00000050);
                      uVar5 = uStack0000000000000050;
                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar7 != '\0') {
                        return;
                      }
                      (**(code **)(*in_stack_00000048 + 0x658))
                                (in_stack_00000048,in_stack_00000130,iVar9 + uVar17,1,
                                 &stack0x00000050);
                      uVar6 = uStack0000000000000050;
                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar7 != '\0') {
                        return;
                      }
                      _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar6 ^ uVar5);
                      (**(code **)(*in_stack_00000048 + 0x698))
                                (in_stack_00000048,in_stack_000000a0,uVar17,1,&stack0x00000050);
                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar7 != '\0') {
                        return;
                      }
                      uVar17 = uVar17 + 1;
                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                    } while (cVar7 == '\0');
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

