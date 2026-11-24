/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=124 entry=180457548 blocks=33 maxInstructions=50000 */

/* cut edge: 0x180457548 -> 0x180473ae8 */
/* cut edge: 0x180457577 -> 0x180473ae8 */
/* cut edge: 0x1804575a6 -> 0x180473ae8 */
/* cut edge: 0x1804575dc -> 0x180473ae8 */
/* cut edge: 0x18045761b -> 0x180473ae8 */
/* cut edge: 0x180457663 -> 0x180473ae8 */
/* cut edge: 0x180457679 -> 0x180473ae8 */
/* cut edge: 0x180457851 -> 0x180473afd */
/* cut edge: 0x180457901 -> 0x180473b45 */
/* cut edge: 0x180457914 -> 0x180473ae8 */
/* cut edge: 0x180457a3f -> 0x180473ae8 */
/* cut edge: 0x180457a55 -> 0x180473ae8 */
/* cut edge: 0x180457c2a -> 0x180473b80 */
/* cut edge: 0x180457d6b -> 0x180473ae8 */
/* cut edge: 0x180457d97 -> 0x180473ae8 */
/* cut edge: 0x180457dad -> 0x180473ae8 */
/* cut edge: 0x180457f82 -> 0x180473ae8 */
/* cut edge: 0x18045807b -> 0x180473ae8 */
/* cut edge: 0x180458091 -> 0x180473ae8 */
/* cut edge: 0x180458260 -> 0x180473ae8 */
/* cut edge: 0x180458354 -> 0x180473ae8 */
/* cut edge: 0x180458382 -> 0x180473ae8 */
/* cut edge: 0x180458398 -> 0x180458541 */
/* cut edge: 0x1804583e3 -> 0x180458541 */
/* target 0x180458541 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180473afd original byte 0x48 replaced by RET */
/* target 0x180473b45 original byte 0x48 replaced by RET */
/* target 0x180473b80 original byte 0x48 replaced by RET */


longlong region_00124_180457548(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  byte bVar6;
  int iVar7;
  int iVar8;
  undefined4 uVar9;
  longlong lVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  longlong lVar16;
  ulonglong uVar17;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000050;
  code *in_stack_00000058;
  code *in_stack_00000060;
  longlong in_stack_00000068;
  undefined8 in_stack_00000070;
  longlong in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  code *in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  code *in_stack_000000e0;
  uint uStack00000000000000e8;
  undefined4 uStack00000000000000ec;
  code *in_stack_000000f0;
  char cStack00000000000000f8;
  undefined7 uStack00000000000000f9;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00000178;
  longlong in_stack_00000180;
  undefined8 in_stack_000001b8;
  ulonglong in_stack_00000780;
  undefined8 in_stack_00002fb0;
  
  iVar7 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_000000b0);
  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar10 == '\0') {
    iVar8 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000178);
    lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)lVar10 == '\0') {
      uVar11 = (**(code **)(*in_stack_00000048 + 0x568))
                         (in_stack_00000048,in_stack_00000178,iVar8 + -1);
      lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)lVar10 == '\0') {
        (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048,uVar11,0,1,&stack0x00000050);
        uVar11 = in_stack_00000050;
        lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)lVar10 == '\0') {
          in_stack_00000050 = CONCAT71(in_stack_00000050._1_7_,(char)uVar11);
          (**(code **)(*in_stack_00000048 + 0x680))
                    (in_stack_00000048,in_stack_000000b0,iVar7 + -1,1,&stack0x00000050);
          lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)lVar10 == '\0') {
            lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)lVar10 == '\0') {
              lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
              lVar4 = in_stack_00000068;
              if ((char)lVar10 == '\0') {
                if (DAT_1804b44d8 == 0) {
                  lVar10 = *in_stack_00000048;
                  in_stack_00000058 = *(code **)(lVar10 + 0x390);
                  _uStack00000000000000e8 = (code *)FUN_180333500(in_stack_00000048);
                  _cStack00000000000000f8 = DAT_1804b59f0;
                  in_stack_000000f0 =
                       (code *)(**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000398,1);
                  lVar10 = *in_stack_00000048;
                  in_stack_00000060 = *(code **)(lVar10 + 0x390);
                  uVar12 = FUN_1800024f0(in_stack_00000048);
                  uVar13 = FUN_180002550(in_stack_00000048);
                  uVar14 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x000039b0,0x23);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar15 = FUN_1800025a0(in_stack_00000048);
                  uVar11 = in_stack_00000088;
                  uVar15 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar15);
                  (*in_stack_00000060)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                  uVar11 = (*in_stack_00000058)
                                     (in_stack_00000048,uVar11,_uStack00000000000000e8,
                                      _cStack00000000000000f8,in_stack_000000f0);
                  DAT_1804b44d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                }
                in_stack_00000050 =
                     (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b44d8,in_stack_000000a0);
                uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                   (in_stack_00000048,6,in_stack_00000090,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                    in_stack_00002fb0);
                lVar16 = (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
                lVar3 = DAT_1804b5390;
                lVar10 = DAT_1804b5388;
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                if (DAT_1804b5388 == 0) {
                  return lVar16;
                }
                if (DAT_1804b5390 == 0) {
                  return lVar16;
                }
                uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,DAT_1804b5388,DAT_1804b5390,
                                    in_stack_00000780 >> 0x10 & 0xffff);
                (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
                (**(code **)(*in_stack_00000048 + 0x570))
                          (in_stack_00000048,uVar11,2,in_stack_000000b0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                in_stack_00000180 = lVar10;
                in_stack_00000078 = lVar3;
                uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,lVar10,lVar3,in_stack_00000780 & 0xffff);
                (*pcVar1)(in_stack_00000048,uVar11,3,uVar12);
                uVar13 = in_stack_00000098;
                uVar12 = in_stack_00000080;
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                    lVar4 % 0x2ba80 ^ 0x502cb2c131ab5fe5);
                (*pcVar1)(in_stack_00000048,uVar11,4,uVar14);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,uVar12,uVar13,in_stack_000000c8);
                (*pcVar1)(in_stack_00000048,uVar11,5,uVar12);
                in_stack_00000058 =
                     (code *)(**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,in_stack_00000050,in_stack_000000a8,uVar11
                                       );
                lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)lVar10 == '\0') {
                  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)lVar10 == '\0') {
                    lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)lVar10 == '\0') {
                      uVar17 = in_stack_00000068 % 0xaa4e3;
                      if (DAT_1804b44e0 == 0) {
                        lVar10 = *in_stack_00000048;
                        in_stack_000000b0 = *(code **)(lVar10 + 0x390);
                        _uStack00000000000000e8 = (code *)FUN_180333500(in_stack_00000048);
                        _cStack00000000000000f8 = DAT_1804b59f0;
                        in_stack_000000f0 =
                             (code *)(**(code **)(lVar10 + 0x518))
                                               (in_stack_00000048,&stack0x00000396,1);
                        lVar10 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar10 + 0x390);
                        uVar12 = FUN_1800024f0(in_stack_00000048);
                        uVar13 = FUN_180002550(in_stack_00000048);
                        uVar14 = (**(code **)(lVar10 + 0x518))
                                           (in_stack_00000048,&stack0x00003f10,0x18);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar15 = FUN_1800025a0(in_stack_00000048);
                        uVar11 = in_stack_00000088;
                        uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar15);
                        (*pcVar1)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                        uVar11 = (*in_stack_000000b0)
                                           (in_stack_00000048,uVar11,_uStack00000000000000e8,
                                            _cStack00000000000000f8,in_stack_000000f0);
                        DAT_1804b44e0 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                      }
                      in_stack_00000050 =
                           (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b44e0,in_stack_000000a0);
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                         (in_stack_00000048,4,in_stack_00000090,0);
                      (**(code **)(*in_stack_00000048 + 0x570))
                                (in_stack_00000048,uVar13,0,in_stack_00000058);
                      uVar12 = in_stack_00000098;
                      uVar11 = in_stack_00000080;
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                          in_stack_000001b8);
                      (*pcVar1)(in_stack_00000048,uVar13,1,uVar14);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,uVar11,uVar12,
                                          uVar17 ^ 0x5035e9d916276ef1);
                      (*pcVar1)(in_stack_00000048,uVar13,2,uVar14);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,uVar11,uVar12,in_stack_000000c8);
                      (*pcVar1)(in_stack_00000048,uVar13,3,uVar11);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,in_stack_00000050,in_stack_000000a8,
                                          uVar13);
                      in_stack_000000b0 = (code *)DAT_1804b5aa0;
                      if (DAT_1804b5aa0 == 0) {
                        return 0;
                      }
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x140))
                                        (in_stack_00000048,uVar11,DAT_1804b5aa0);
                      lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)lVar10 == '\0') {
                        lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)lVar10 == '\0') {
                          lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)lVar10 == '\0') {
                            uVar17 = in_stack_00000068 % 0x676b3 ^ in_stack_000000c8;
                            if (DAT_1804b44e8 == 0) {
                              lVar10 = *in_stack_00000048;
                              _uStack00000000000000e8 = *(code **)(lVar10 + 0x390);
                              in_stack_000000f0 = (code *)FUN_180333550(in_stack_00000048);
                              in_stack_00000060 = DAT_1804b59f0;
                              in_stack_000000e0 =
                                   (code *)(**(code **)(lVar10 + 0x518))
                                                     (in_stack_00000048,&stack0x00000394,1);
                              lVar10 = *in_stack_00000048;
                              _cStack00000000000000f8 =
                                   (code *)CONCAT71(uStack00000000000000f9,cVar5);
                              pcVar1 = *(code **)(lVar10 + 0x390);
                              uVar12 = FUN_1800024f0(in_stack_00000048);
                              uVar13 = FUN_180002550(in_stack_00000048);
                              uVar14 = (**(code **)(lVar10 + 0x518))
                                                 (in_stack_00000048,&stack0x00001150,5);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar15 = FUN_1800025a0(in_stack_00000048);
                              uVar11 = in_stack_00000088;
                              uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar15);
                              (*pcVar1)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                              cVar5 = cStack00000000000000f8;
                              uVar11 = (*_uStack00000000000000e8)
                                                 (in_stack_00000048,uVar11,in_stack_000000f0,
                                                  in_stack_00000060,in_stack_000000e0);
                              DAT_1804b44e8 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                             (in_stack_00000048,uVar11);
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                            }
                            in_stack_00000050 =
                                 (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804b44e8,in_stack_000000a0);
                            uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                               (in_stack_00000048,2,in_stack_00000090,0);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_000000b8,
                                                in_stack_000000c0,0x5a30);
                            (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000080,
                                                in_stack_00000098,uVar17 ^ 0xfd1683012c905674);
                            (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
                            uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,in_stack_00000050,
                                                in_stack_000000a8,uVar11);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x188))
                                              (in_stack_00000048,uVar11,in_stack_00000120);
                            _uStack00000000000000e8 = (code *)CONCAT44(uStack00000000000000ec,uVar9)
                            ;
                            lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)lVar10 == '\0') {
                              lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)lVar10 == '\0') {
                                lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)lVar10 == '\0') {
                                  uVar17 = in_stack_00000068 % 0x861ec ^ in_stack_000000c8;
                                  if (DAT_1804b44f0 == 0) {
                                    lVar10 = *in_stack_00000048;
                                    in_stack_000000f0 = *(code **)(lVar10 + 0x390);
                                    in_stack_00000060 = (code *)FUN_180333550(in_stack_00000048);
                                    in_stack_000000e0 = DAT_1804b59f0;
                                    in_stack_00000070 =
                                         (**(code **)(lVar10 + 0x518))
                                                   (in_stack_00000048,&stack0x00000392,1);
                                    lVar10 = *in_stack_00000048;
                                    _cStack00000000000000f8 =
                                         (code *)CONCAT71(uStack00000000000000f9,cVar5);
                                    pcVar1 = *(code **)(lVar10 + 0x390);
                                    uVar12 = FUN_1800024f0(in_stack_00000048);
                                    uVar13 = FUN_180002550(in_stack_00000048);
                                    uVar14 = (**(code **)(lVar10 + 0x518))
                                                       (in_stack_00000048,&stack0x00001140,5);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar15 = FUN_1800025a0(in_stack_00000048);
                                    uVar11 = in_stack_00000088;
                                    uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar15);
                                    (*pcVar1)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                                    cVar5 = cStack00000000000000f8;
                                    uVar11 = (*in_stack_000000f0)
                                                       (in_stack_00000048,uVar11,in_stack_00000060,
                                                        in_stack_000000e0,in_stack_00000070);
                                    DAT_1804b44f0 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar11);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar11);
                                  }
                                  in_stack_00000050 =
                                       (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b44f0,in_stack_000000a0)
                                  ;
                                  uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                                     (in_stack_00000048,2,in_stack_00000090,0);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                                     (in_stack_00000048,in_stack_000000b8,
                                                      in_stack_000000c0,0x5dd1);
                                  (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                                     (in_stack_00000048,in_stack_00000080,
                                                      in_stack_00000098,uVar17 ^ 0xc18ac7c5c65871f1)
                                  ;
                                  (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
                                  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,in_stack_00000050,
                                                      in_stack_000000a8,uVar11);
                                  bVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                                    (in_stack_00000048,uVar11,in_stack_00000120);
                                  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                                  if ((char)lVar10 == '\0') {
                                    _uStack00000000000000e8 =
                                         (code *)CONCAT44(uStack00000000000000ec,
                                                          (uStack00000000000000e8 & (int)cVar5) <<
                                                          (bVar6 & 0x1f));
                                    lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if ((char)lVar10 == '\0') {
                                      lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if ((char)lVar10 == '\0') {
                                        lVar10 = (in_stack_00000068 / 0x5c97c) * 0x5c97c;
                                        if (DAT_1804b44f8 == 0) {
                                          lVar10 = *in_stack_00000048;
                                          _cStack00000000000000f8 = *(code **)(lVar10 + 0x390);
                                          in_stack_000000f0 =
                                               (code *)FUN_180333500(in_stack_00000048);
                                          in_stack_00000060 = DAT_1804b59f0;
                                          in_stack_000000e0 =
                                               (code *)(**(code **)(lVar10 + 0x518))
                                                                 (in_stack_00000048,&stack0x00000390
                                                                  ,1);
                                          lVar10 = *in_stack_00000048;
                                          pcVar1 = *(code **)(lVar10 + 0x390);
                                          uVar12 = FUN_1800024f0(in_stack_00000048);
                                          uVar13 = FUN_180002550(in_stack_00000048);
                                          uVar14 = (**(code **)(lVar10 + 0x518))
                                                             (in_stack_00000048,&stack0x00003ee0,
                                                              0x18);
                                          pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar15 = FUN_1800025a0(in_stack_00000048);
                                          uVar11 = in_stack_00000088;
                                          uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000088,
                                                             uVar15);
                                          (*pcVar1)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                                          uVar11 = (*_cStack00000000000000f8)
                                                             (in_stack_00000048,uVar11,
                                                              in_stack_000000f0,in_stack_00000060,
                                                              in_stack_000000e0);
                                          DAT_1804b44f8 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar11);
                                          lVar10 = (**(code **)(*in_stack_00000048 + 0xb8))
                                                             (in_stack_00000048,uVar11);
                                        }
                                        return lVar10;
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
  return lVar10;
}

