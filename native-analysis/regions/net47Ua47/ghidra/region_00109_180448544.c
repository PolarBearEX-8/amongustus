/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=109 entry=180448544 blocks=35 maxInstructions=50000 */

/* cut edge: 0x1804486e0 -> 0x180473ae8 */
/* cut edge: 0x1804487d5 -> 0x180473ae8 */
/* cut edge: 0x1804487eb -> 0x180473ae8 */
/* cut edge: 0x1804489ae -> 0x180473ae8 */
/* cut edge: 0x180448abd -> 0x180473ae8 */
/* cut edge: 0x180448ad3 -> 0x180473ae8 */
/* cut edge: 0x180448caf -> 0x180473ae8 */
/* cut edge: 0x180448e57 -> 0x180473ae8 */
/* cut edge: 0x180448e9e -> 0x180473ae8 */
/* cut edge: 0x180448ee7 -> 0x180473ae8 */
/* cut edge: 0x180448f2d -> 0x18044ed41 */
/* cut edge: 0x180448f82 -> 0x18044f86b */
/* cut edge: 0x180448f98 -> 0x180473ae8 */
/* cut edge: 0x180448fa0 -> 0x180473ae8 */
/* cut edge: 0x18044916e -> 0x180473ae8 */
/* cut edge: 0x180449320 -> 0x180473ae8 */
/* cut edge: 0x180449366 -> 0x180473ae8 */
/* cut edge: 0x1804493b1 -> 0x180449f5e */
/* cut edge: 0x1804493d0 -> 0x180473ae8 */
/* cut edge: 0x1804493fc -> 0x180473ae8 */
/* cut edge: 0x180449431 -> 0x180473ae8 */
/* cut edge: 0x180449447 -> 0x180473ae8 */
/* cut edge: 0x18044945d -> 0x180449603 */
/* cut edge: 0x1804494ad -> 0x180449603 */
/* target 0x180449603 original byte 0x48 replaced by RET */
/* target 0x180449f5e original byte 0x48 replaced by RET */
/* target 0x18044ed41 original byte 0x48 replaced by RET */
/* target 0x18044f86b original byte 0x84 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


ulonglong region_00109_180448544(void)

{
  code *pcVar1;
  ulonglong uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  ulonglong uVar8;
  longlong lVar9;
  undefined8 uVar10;
  ulonglong uVar11;
  ulonglong *puVar12;
  code *pcVar13;
  ulonglong unaff_R15;
  ulonglong *in_stack_00000048;
  undefined4 uStack0000000000000050;
  code *in_stack_00000058;
  ulonglong in_stack_00000060;
  code *in_stack_00000068;
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
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  code *in_stack_000000e8;
  code *in_stack_000000f0;
  code *in_stack_000000f8;
  longlong in_stack_00000118;
  int iStack0000000000000140;
  undefined8 in_stack_00000178;
  undefined8 in_stack_00000180;
  undefined8 in_stack_00000748;
  undefined8 in_stack_00000750;
  short in_stack_00004280;
  
  uVar11 = (longlong)unaff_R15 % 0x68847 ^ in_stack_000000c8;
  if (DAT_1804b4270 == 0) {
    uVar8 = *in_stack_00000048;
    in_stack_00000068 = *(code **)(uVar8 + 0x390);
    in_stack_00000058 = (code *)FUN_1803335f0(in_stack_00000048);
    in_stack_000000b0 = DAT_1804b59f0;
    in_stack_000000e8 = (code *)(**(code **)(uVar8 + 0x518))(in_stack_00000048,&stack0x00000432,1);
    uVar8 = *in_stack_00000048;
    pcVar13 = *(code **)(uVar8 + 0x390);
    uVar3 = FUN_1800024f0(in_stack_00000048);
    uVar4 = FUN_180002550(in_stack_00000048);
    uVar5 = (**(code **)(uVar8 + 0x518))(in_stack_00000048,&stack0x000015d0,5);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
    uVar6 = FUN_1800025a0(in_stack_00000048);
    uVar7 = in_stack_00000088;
    uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6);
    (*pcVar13)(in_stack_00000048,uVar3,uVar4,uVar5,uVar6);
    uVar7 = (*in_stack_00000068)
                      (in_stack_00000048,uVar7,in_stack_00000058,in_stack_000000b0,in_stack_000000e8
                      );
    DAT_1804b4270 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
    unaff_R15 = in_stack_00000060;
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
  }
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b4270,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
  uVar3 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x44de);
  (*pcVar13)(in_stack_00000048,uVar7,0,uVar3);
  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
  uVar3 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                     uVar11 ^ 0xb88c5ead6eda6cc4);
  (*pcVar13)(in_stack_00000048,uVar7,1,uVar3);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_000000a8,uVar7);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((((char)uVar8 == '\0') &&
      (uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar8 == '\0')) &&
     (uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar8 == '\0')) {
    uVar8 = (longlong)unaff_R15 % 0x81ad0 ^ in_stack_000000c8;
    if (DAT_1804b4278 == 0) {
      uVar2 = *in_stack_00000048;
      in_stack_00000068 = *(code **)(uVar2 + 0x390);
      in_stack_00000058 = (code *)FUN_1803335f0(in_stack_00000048);
      in_stack_000000b0 = DAT_1804b59f0;
      in_stack_000000e8 = (code *)(**(code **)(uVar2 + 0x518))(in_stack_00000048,&stack0x00000430,1)
      ;
      uVar2 = *in_stack_00000048;
      in_stack_000000f8 = *(code **)(uVar2 + 0x390);
      uVar3 = FUN_1800024f0(in_stack_00000048);
      uVar4 = FUN_180002550(in_stack_00000048);
      uVar5 = (**(code **)(uVar2 + 0x518))(in_stack_00000048,&stack0x000015c0,5);
      pcVar13 = *(code **)(*in_stack_00000048 + 0x110);
      uVar6 = FUN_1800025a0(in_stack_00000048);
      uVar7 = in_stack_00000088;
      uVar6 = (*pcVar13)(in_stack_00000048,in_stack_00000088,uVar6);
      unaff_R15 = in_stack_00000060;
      (*in_stack_000000f8)(in_stack_00000048,uVar3,uVar4,uVar5,uVar6);
      uVar7 = (*in_stack_00000068)
                        (in_stack_00000048,uVar7,in_stack_00000058,in_stack_000000b0,
                         in_stack_000000e8);
      DAT_1804b4278 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    _uStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b4278,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
    uVar3 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x23ce);
    (*pcVar13)(in_stack_00000048,uVar7,0,uVar3);
    pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
    uVar3 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       uVar8 ^ 0x854750b5e109b93f);
    (*pcVar13)(in_stack_00000048,uVar7,1,uVar3);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,_uStack0000000000000050,in_stack_000000a8,uVar7);
    lVar9 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar8 == '\0') {
      lVar9 = lVar9 + (uVar11 ^ unaff_R15);
      in_stack_00000068 = (code *)0x1;
      in_stack_00000058 = (code *)0x1;
      uVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (((char)uVar8 == '\0') &&
         (uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar8 == '\0')) {
        in_stack_00000060 = lVar9;
        if (DAT_1804b4280 == 0) {
          uVar11 = *in_stack_00000048;
          in_stack_000000b0 = *(code **)(uVar11 + 0x390);
          in_stack_000000e8 = (code *)FUN_180333500(in_stack_00000048);
          in_stack_000000f8 = DAT_1804b59f0;
          in_stack_000000f0 =
               (code *)(**(code **)(uVar11 + 0x518))(in_stack_00000048,&stack0x0000042e,1);
          uVar11 = *in_stack_00000048;
          pcVar13 = *(code **)(uVar11 + 0x390);
          uVar3 = FUN_1800024f0(in_stack_00000048);
          uVar4 = FUN_180002550(in_stack_00000048);
          uVar5 = (**(code **)(uVar11 + 0x518))(in_stack_00000048,&stack0x00003440,0x1b);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
          uVar6 = FUN_1800025a0(in_stack_00000048);
          uVar7 = in_stack_00000088;
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6);
          (*pcVar13)(in_stack_00000048,uVar3,uVar4,uVar5,uVar6);
          uVar7 = (*in_stack_000000b0)
                            (in_stack_00000048,uVar7,in_stack_000000e8,in_stack_000000f8,
                             in_stack_000000f0);
          DAT_1804b4280 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        _uStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b4280,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,6,in_stack_00000090,0);
        uVar5 = in_stack_000000c0;
        uVar4 = in_stack_000000b8;
        pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,in_stack_00000750);
        (*pcVar13)(in_stack_00000048,uVar6,0,uVar7);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,1,in_stack_00000180);
        uVar3 = in_stack_00000098;
        uVar7 = in_stack_00000080;
        pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00000748)
        ;
        (*pcVar13)(in_stack_00000048,uVar6,2,uVar10);
        pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,uVar4,uVar5,
                           (ulonglong)_iStack0000000000000140 & 0xffffffff);
        (*pcVar13)(in_stack_00000048,uVar6,3,uVar4);
        pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,uVar7,uVar3,lVar9 % 0x9f98d ^ 0x50249742a6a90793);
        (*pcVar13)(in_stack_00000048,uVar6,4,uVar4);
        pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,uVar7,uVar3,in_stack_000000c8);
        (*pcVar13)(in_stack_00000048,uVar6,5,uVar7);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,_uStack0000000000000050,in_stack_000000a8,uVar6);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        lVar9 = in_stack_00000060;
        if ((char)uVar8 == '\0') {
          (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048,uVar7,0,1,&stack0x00000050);
          _iStack0000000000000140 = (code *)(_uStack0000000000000050 & 0xffffffff);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)uVar8 == '\0') {
            (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048,uVar7,1,1,&stack0x00000050);
            in_stack_000000b0 = (code *)CONCAT44(in_stack_000000b0._4_4_,uStack0000000000000050);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)uVar8 == '\0') {
              pcVar13 = *(code **)(*in_stack_00000048 + 0x560);
              uVar7 = FUN_1803daa50(in_stack_00000048);
              in_stack_00000178 = (*pcVar13)(in_stack_00000048,_iStack0000000000000140,uVar7,0);
              puVar12 = in_stack_00000048;
              uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)uVar8 == '\0') {
                uVar11 = CONCAT71((int7)((ulonglong)puVar12 >> 8),
                                  in_stack_00004280 != 0 && in_stack_00000118 == 0) & 0xffffffff ^ 1
                ;
                pcVar13 = in_stack_00000068;
                if (in_stack_00004280 != 0 && in_stack_00000118 == 0) {
                  pcVar13 = _iStack0000000000000140;
                }
                if ((char)in_stack_00000058 == '\0' && (char)uVar11 == '\0') {
                  return uVar11;
                }
                in_stack_000000f8 = (code *)((ulonglong)in_stack_000000f8 & 0xffffffff00000000);
                if (in_stack_00000118 == 0) {
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((int)pcVar13 < 1) {
                    return uVar11;
                  }
                  if ((char)uVar11 != '\0') {
                    return uVar11;
                  }
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)uVar11 != '\0') {
                    return uVar11;
                  }
                  if (DAT_1804b42c8 == 0) {
                    uVar11 = *in_stack_00000048;
                    in_stack_00000068 = *(code **)(uVar11 + 0x390);
                    in_stack_00000058 = (code *)FUN_180333500(in_stack_00000048);
                    in_stack_000000e8 = DAT_1804b59f0;
                    in_stack_000000f8 =
                         (code *)(**(code **)(uVar11 + 0x518))(in_stack_00000048,&stack0x0000041c,1)
                    ;
                    uVar11 = *in_stack_00000048;
                    pcVar13 = *(code **)(uVar11 + 0x390);
                    uVar3 = FUN_1800024f0(in_stack_00000048);
                    uVar4 = FUN_180002550(in_stack_00000048);
                    uVar5 = (**(code **)(uVar11 + 0x518))(in_stack_00000048,&stack0x00003400,0x1b);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar6 = FUN_1800025a0(in_stack_00000048);
                    uVar7 = in_stack_00000088;
                    uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6);
                    (*pcVar13)(in_stack_00000048,uVar3,uVar4,uVar5,uVar6);
                    uVar7 = (*in_stack_00000068)
                                      (in_stack_00000048,uVar7,in_stack_00000058,in_stack_000000e8,
                                       in_stack_000000f8);
                    DAT_1804b42c8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                  }
                  _uStack0000000000000050 =
                       (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b42c8,in_stack_000000a0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,6,in_stack_00000090,0);
                  uVar5 = in_stack_000000c0;
                  uVar4 = in_stack_000000b8;
                  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                     in_stack_00000750);
                  (*pcVar13)(in_stack_00000048,uVar6,0,uVar7);
                  (**(code **)(*in_stack_00000048 + 0x570))
                            (in_stack_00000048,uVar6,1,in_stack_00000180);
                  uVar3 = in_stack_00000098;
                  uVar7 = in_stack_00000080;
                  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                      in_stack_00000748);
                  (*pcVar13)(in_stack_00000048,uVar6,2,uVar10);
                  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar4 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,uVar4,uVar5,
                                     (ulonglong)in_stack_000000b0 & 0xffffffff);
                  (*pcVar13)(in_stack_00000048,uVar6,3,uVar4);
                  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar4 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,uVar7,uVar3,
                                     lVar9 % 0xcefc1 ^ 0x50249742a6a5474c);
                  (*pcVar13)(in_stack_00000048,uVar6,4,uVar4);
                  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,uVar7,uVar3,in_stack_000000c8);
                  (*pcVar13)(in_stack_00000048,uVar6,5,uVar7);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,_uStack0000000000000050,in_stack_000000a8,
                                     uVar6);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  lVar9 = in_stack_00000060;
                  if ((char)uVar11 != '\0') {
                    return uVar11;
                  }
                  (**(code **)(*in_stack_00000048 + 0x658))
                            (in_stack_00000048,uVar7,0,1,&stack0x00000050);
                  in_stack_000000f8 =
                       (code *)CONCAT44(in_stack_000000f8._4_4_,uStack0000000000000050);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)uVar11 != '\0') {
                    return uVar11;
                  }
                  (**(code **)(*in_stack_00000048 + 0x658))
                            (in_stack_00000048,uVar7,1,1,&stack0x00000050);
                  in_stack_000000b0 =
                       (code *)CONCAT44(in_stack_000000b0._4_4_,uStack0000000000000050);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)uVar11 != '\0') {
                    return uVar11;
                  }
                  pcVar13 = (code *)0x0;
                }
                if (iStack0000000000000140 <= (int)pcVar13) {
                  return *in_stack_00000048;
                }
                in_stack_00000060 = lVar9;
                in_stack_00000068 = pcVar13;
                uVar7 = (**(code **)(*in_stack_00000048 + 0x598))
                                  (in_stack_00000048,(ulonglong)in_stack_000000f8 & 0xffffffff);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)uVar8 == '\0') {
                  (**(code **)(*in_stack_00000048 + 0x570))
                            (in_stack_00000048,in_stack_00000178,in_stack_00000068,uVar7);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((((char)uVar8 == '\0') &&
                      (uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar8 == '\0')) &&
                     (uVar8 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar8 == '\0')) {
                    uVar11 = 0x852b54c137f62fbc;
                    if (DAT_1804b42f0 == 0) {
                      uVar11 = *in_stack_00000048;
                      in_stack_00000058 = *(code **)(uVar11 + 0x390);
                      in_stack_000000e8 = (code *)FUN_1803335f0(in_stack_00000048);
                      in_stack_000000f0 = DAT_1804b59f0;
                      in_stack_000000e0 =
                           (**(code **)(uVar11 + 0x518))(in_stack_00000048,&stack0x00000412,1);
                      uVar11 = *in_stack_00000048;
                      pcVar13 = *(code **)(uVar11 + 0x390);
                      uVar3 = FUN_1800024f0(in_stack_00000048);
                      uVar4 = FUN_180002550(in_stack_00000048);
                      uVar5 = (**(code **)(uVar11 + 0x518))(in_stack_00000048,&stack0x000014f0,5);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar6 = FUN_1800025a0(in_stack_00000048);
                      uVar7 = in_stack_00000088;
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6);
                      (*pcVar13)(in_stack_00000048,uVar3,uVar4,uVar5,uVar6);
                      uVar7 = (*in_stack_00000058)
                                        (in_stack_00000048,uVar7,in_stack_000000e8,in_stack_000000f0
                                         ,in_stack_000000e0);
                      DAT_1804b42f0 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    return uVar11;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  return uVar8;
}

