/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=120 entry=1804535e4 blocks=55 maxInstructions=50000 */

/* cut edge: 0x1804535e4 -> 0x180473ae8 */
/* cut edge: 0x1804535fa -> 0x180473ae8 */
/* cut edge: 0x1804537ac -> 0x180473ae8 */
/* cut edge: 0x1804538a1 -> 0x1804502ca */
/* cut edge: 0x1804538c4 -> 0x180450e13 */
/* cut edge: 0x1804538de -> 0x180473ae8 */
/* cut edge: 0x180453912 -> 0x180473ae8 */
/* cut edge: 0x180453941 -> 0x180473ae8 */
/* cut edge: 0x1804539a0 -> 0x180473ae8 */
/* cut edge: 0x1804539cf -> 0x180454f20 */
/* cut edge: 0x1804539e8 -> 0x180473ae8 */
/* cut edge: 0x180453a18 -> 0x180473ae8 */
/* cut edge: 0x180453a4d -> 0x180473ae8 */
/* cut edge: 0x180453a93 -> 0x180473ae8 */
/* cut edge: 0x180453aa9 -> 0x180473ae8 */
/* cut edge: 0x180453b21 -> 0x180455c1d */
/* cut edge: 0x180453b48 -> 0x180455c58 */
/* cut edge: 0x180453b95 -> 0x180455caa */
/* cut edge: 0x180453bbf -> 0x180455ce3 */
/* cut edge: 0x180453cac -> 0x180473ae8 */
/* cut edge: 0x180453da0 -> 0x180473ae8 */
/* cut edge: 0x180453df2 -> 0x180473ae8 */
/* cut edge: 0x180453e08 -> 0x180473ae8 */
/* cut edge: 0x180453e77 -> 0x180455f11 */
/* cut edge: 0x180453e9b -> 0x180455f49 */
/* cut edge: 0x180453ee8 -> 0x180455f9c */
/* cut edge: 0x180453f15 -> 0x180455fd5 */
/* cut edge: 0x180453ffb -> 0x180473ae8 */
/* cut edge: 0x1804540f7 -> 0x180473ae8 */
/* cut edge: 0x180454140 -> 0x180473ae8 */
/* cut edge: 0x180454156 -> 0x180473ae8 */
/* cut edge: 0x1804541c5 -> 0x180456107 */
/* cut edge: 0x1804541e9 -> 0x18045613f */
/* cut edge: 0x18045423e -> 0x180456191 */
/* cut edge: 0x18045426b -> 0x1804561ca */
/* cut edge: 0x180454359 -> 0x180473ae8 */
/* cut edge: 0x18045444d -> 0x180473ae8 */
/* cut edge: 0x180454498 -> 0x180473ae8 */
/* cut edge: 0x1804544ae -> 0x180473ae8 */
/* cut edge: 0x1804544c4 -> 0x1804546a7 */
/* cut edge: 0x18045451b -> 0x180456201 */
/* cut edge: 0x18045453f -> 0x180454547 */
/* target 0x1804502ca original byte 0x48 replaced by RET */
/* target 0x180450e13 original byte 0x48 replaced by RET */
/* target 0x180454547 original byte 0x48 replaced by RET */
/* target 0x1804546a7 original byte 0x48 replaced by RET */
/* target 0x180454f20 original byte 0x48 replaced by RET */
/* target 0x180455c1d original byte 0x48 replaced by RET */
/* target 0x180455c58 original byte 0x48 replaced by RET */
/* target 0x180455caa original byte 0x48 replaced by RET */
/* target 0x180455ce3 original byte 0x48 replaced by RET */
/* target 0x180455f11 original byte 0x48 replaced by RET */
/* target 0x180455f49 original byte 0x48 replaced by RET */
/* target 0x180455f9c original byte 0x48 replaced by RET */
/* target 0x180455fd5 original byte 0x48 replaced by RET */
/* target 0x180456107 original byte 0x48 replaced by RET */
/* target 0x18045613f original byte 0x48 replaced by RET */
/* target 0x180456191 original byte 0x48 replaced by RET */
/* target 0x1804561ca original byte 0x48 replaced by RET */
/* target 0x180456201 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


code * region_00120_1804535e4(void)

{
  longlong lVar1;
  code *pcVar2;
  byte bVar3;
  undefined4 uVar4;
  int iVar5;
  code *pcVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong lVar12;
  code *pcVar13;
  ulonglong unaff_R14;
  ulonglong uVar14;
  longlong *in_stack_00000048;
  undefined4 uStack0000000000000050;
  undefined8 in_stack_00000058;
  code *in_stack_00000060;
  ulonglong in_stack_00000068;
  code *in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  code *in_stack_000000e8;
  int iStack00000000000000f0;
  undefined4 uStack00000000000000f4;
  int iStack00000000000000f8;
  undefined4 uStack00000000000000fc;
  undefined1 in_stack_00000100;
  longlong in_stack_00000118;
  undefined8 in_stack_00000120;
  int in_stack_00000130;
  code *in_stack_00000138;
  code *in_stack_00000140;
  code *in_stack_00000148;
  undefined8 in_stack_00000178;
  
  pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)pcVar6 == '\0') {
    pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar6 == '\0') {
      in_stack_00000068 = in_stack_00000068 ^ unaff_R14;
      uVar14 = (longlong)unaff_R14 % 0x658be ^ in_stack_000000c8;
      if (DAT_1804b4440 == 0) {
        lVar12 = *in_stack_00000048;
        _iStack00000000000000f8 = *(code **)(lVar12 + 0x390);
        _iStack00000000000000f0 = (code *)FUN_1803335f0(in_stack_00000048);
        in_stack_00000060 = DAT_1804b59f0;
        in_stack_00000070 =
             (code *)(**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x000003be,1);
        lVar12 = *in_stack_00000048;
        pcVar6 = *(code **)(lVar12 + 0x390);
        uVar7 = FUN_1800024f0(in_stack_00000048);
        uVar8 = FUN_180002550(in_stack_00000048);
        uVar9 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00001280,5);
        pcVar13 = *(code **)(*in_stack_00000048 + 0x110);
        uVar10 = FUN_1800025a0(in_stack_00000048);
        uVar11 = in_stack_00000088;
        uVar10 = (*pcVar13)(in_stack_00000048,in_stack_00000088,uVar10);
        (*pcVar6)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
        uVar11 = (*_iStack00000000000000f8)
                           (in_stack_00000048,uVar11,_iStack00000000000000f0,in_stack_00000060,
                            in_stack_00000070);
        DAT_1804b4440 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
      }
      _uStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b4440,in_stack_000000a0);
      uVar11 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar6 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4577);
      (*pcVar6)(in_stack_00000048,uVar11,0,uVar7);
      pcVar6 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         uVar14 ^ 0xbfe61fee8855e4a6);
      (*pcVar6)(in_stack_00000048,uVar11,1,uVar7);
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,_uStack0000000000000050,in_stack_000000a8,uVar11);
      lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8)
      ;
      pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar6 == '\0') {
        in_stack_00000068 = in_stack_00000068 - lVar12;
        if (in_stack_00000118 == 0) {
          return in_stack_000000e8;
        }
        if (in_stack_00000130 < 0) {
          return (code *)((ulonglong)in_stack_000000e8 & 0xffffffff);
        }
        iStack00000000000000f8 = (int)in_stack_000000e8;
        uVar4 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000178);
        pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar6 == '\0') {
          in_stack_000000b0 = (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,uVar4);
          pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)pcVar6 == '\0') {
            iVar5 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000178);
            pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar6 == '\0') {
              in_stack_00000100 = -1 < in_stack_00000130 && in_stack_00000118 == 0;
              in_stack_000000e8 = (code *)((ulonglong)in_stack_000000e8 & 0xffffffff00000000);
              in_stack_00000058 = 0;
              if (iVar5 + -1 < 1) {
                return (code *)*in_stack_00000048;
              }
              pcVar13 = (code *)(**(code **)((code *)*in_stack_00000048 + 0x568))
                                          (in_stack_00000048,in_stack_00000178,0);
              pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)pcVar6 == '\0') {
                uVar11 = (**(code **)(*in_stack_00000048 + 0x568))
                                   (in_stack_00000048,in_stack_00000178,1);
                pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)pcVar6 == '\0') {
                  (**(code **)(*in_stack_00000048 + 0x658))
                            (in_stack_00000048,pcVar13,0,1,&stack0x00000050);
                  _iStack00000000000000f8 =
                       (code *)CONCAT44(uStack00000000000000fc,uStack0000000000000050);
                  pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)pcVar6 == '\0') {
                    pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)pcVar6 == '\0') {
                      pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      pcVar2 = DAT_1804b59f0;
                      if ((char)pcVar6 == '\0') {
                        uVar14 = (longlong)in_stack_00000068 % 0x7d8b3 ^ in_stack_000000c8;
                        in_stack_000000e0 = uVar11;
                        in_stack_000000e8 = pcVar13;
                        if (DAT_1804b4468 == 0) {
                          pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b59f8 == (code *)0x0) {
                            return (code *)0x0;
                          }
                          in_stack_00000148 = DAT_1804b59f8;
                          in_stack_00000070 =
                               (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                 (in_stack_00000048,&stack0x000003b4,1);
                          lVar1 = DAT_1804b4d38;
                          lVar12 = DAT_1804b4d30;
                          in_stack_00000138 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b4d30 == 0) {
                            return in_stack_00000070;
                          }
                          in_stack_00000060 = pcVar2;
                          if (DAT_1804b4d38 == 0) {
                            return in_stack_00000070;
                          }
                          _iStack00000000000000f0 = pcVar6;
                          pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                     (in_stack_00000048,&stack0x00001230,5);
                          uVar11 = in_stack_000000e0;
                          uVar7 = in_stack_00000088;
                          if (DAT_1804b4d40 == 0) {
                            return pcVar6;
                          }
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000088);
                          (*in_stack_00000138)(in_stack_00000048,lVar12,lVar1,pcVar6,uVar8);
                          uVar7 = (*_iStack00000000000000f0)
                                            (in_stack_00000048,uVar7,in_stack_00000148,
                                             in_stack_00000060,in_stack_00000070);
                          DAT_1804b4468 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                        }
                        pcVar13 = in_stack_000000e8;
                        _uStack0000000000000050 =
                             (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b4468,in_stack_000000a0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar6 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x5a10);
                        (*pcVar6)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar6 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           uVar14 ^ 0xe7634bee918c0c23);
                        (*pcVar6)(in_stack_00000048,uVar7,1,uVar8);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,_uStack0000000000000050,
                                           in_stack_000000a8,uVar7);
                        bVar3 = (**(code **)(*in_stack_00000048 + 0x188))
                                          (in_stack_00000048,uVar7,in_stack_00000120);
                        pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                   (in_stack_00000048);
                        if ((char)pcVar6 == '\0') {
                          _iStack00000000000000f8 =
                               (code *)CONCAT44(uStack00000000000000fc,
                                                iStack00000000000000f8 >> (bVar3 & 0x1f));
                          (**(code **)(*in_stack_00000048 + 0x658))
                                    (in_stack_00000048,pcVar13,1,1,&stack0x00000050);
                          _iStack00000000000000f0 =
                               (code *)CONCAT44(uStack00000000000000f4,uStack0000000000000050);
                          pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                          if ((char)pcVar6 == '\0') {
                            pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)pcVar6 == '\0') {
                              pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                              pcVar13 = DAT_1804b59f0;
                              if ((char)pcVar6 == '\0') {
                                uVar14 = (longlong)in_stack_00000068 % 0x916ed ^ in_stack_000000c8;
                                if (DAT_1804b4470 == 0) {
                                  pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b59f8 == (code *)0x0) {
                                    return (code *)0x0;
                                  }
                                  in_stack_00000070 = DAT_1804b59f8;
                                  in_stack_00000148 =
                                       (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                         (in_stack_00000048,&stack0x000003b2,1);
                                  lVar1 = DAT_1804b4d38;
                                  lVar12 = DAT_1804b4d30;
                                  in_stack_00000140 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b4d30 == 0) {
                                    return in_stack_00000148;
                                  }
                                  in_stack_00000138 = pcVar13;
                                  if (DAT_1804b4d38 == 0) {
                                    return in_stack_00000148;
                                  }
                                  in_stack_00000060 = pcVar6;
                                  pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                             (in_stack_00000048,&stack0x00001220,5);
                                  uVar11 = in_stack_000000e0;
                                  uVar7 = in_stack_00000088;
                                  if (DAT_1804b4d40 == 0) {
                                    return pcVar6;
                                  }
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,in_stack_00000088);
                                  (*in_stack_00000140)(in_stack_00000048,lVar12,lVar1,pcVar6,uVar8);
                                  uVar7 = (*in_stack_00000060)
                                                    (in_stack_00000048,uVar7,in_stack_00000070,
                                                     in_stack_00000138,in_stack_00000148);
                                  DAT_1804b4470 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar7);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                                }
                                _uStack0000000000000050 =
                                     (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,DAT_1804b4470,in_stack_000000a0);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_00000090,0);
                                pcVar6 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000b8,
                                                   in_stack_000000c0,0x5a10);
                                (*pcVar6)(in_stack_00000048,uVar7,0,uVar8);
                                pcVar6 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000080,
                                                   in_stack_00000098,uVar14 ^ 0xe7634bee91815f10);
                                (*pcVar6)(in_stack_00000048,uVar7,1,uVar8);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,_uStack0000000000000050,
                                                   in_stack_000000a8,uVar7);
                                bVar3 = (**(code **)(*in_stack_00000048 + 0x188))
                                                  (in_stack_00000048,uVar7,in_stack_00000120);
                                pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                           (in_stack_00000048);
                                if ((char)pcVar6 == '\0') {
                                  _iStack00000000000000f0 =
                                       (code *)CONCAT44(uStack00000000000000f4,
                                                        iStack00000000000000f0 >> (bVar3 & 0x1f));
                                  (**(code **)(*in_stack_00000048 + 0x658))
                                            (in_stack_00000048,in_stack_000000e8,2,1,
                                             &stack0x00000050);
                                  uVar4 = uStack0000000000000050;
                                  pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                             (in_stack_00000048);
                                  if ((char)pcVar6 == '\0') {
                                    pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                    if ((char)pcVar6 == '\0') {
                                      pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                      pcVar13 = DAT_1804b59f0;
                                      if ((char)pcVar6 == '\0') {
                                        uVar14 = (longlong)in_stack_00000068 % 0x402ba ^
                                                 in_stack_000000c8;
                                        if (DAT_1804b4478 == 0) {
                                          pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                                          if (DAT_1804b59f8 == (code *)0x0) {
                                            return (code *)0x0;
                                          }
                                          in_stack_00000070 = DAT_1804b59f8;
                                          in_stack_00000148 =
                                               (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                                 (in_stack_00000048,&stack0x000003b0
                                                                  ,1);
                                          lVar1 = DAT_1804b4d38;
                                          lVar12 = DAT_1804b4d30;
                                          in_stack_00000140 = *(code **)(*in_stack_00000048 + 0x390)
                                          ;
                                          if (DAT_1804b4d30 == 0) {
                                            return in_stack_00000140;
                                          }
                                          in_stack_00000060 =
                                               (code *)CONCAT44(in_stack_00000060._4_4_,uVar4);
                                          in_stack_00000138 = pcVar13;
                                          if (DAT_1804b4d38 == 0) {
                                            return in_stack_00000140;
                                          }
                                          in_stack_000000e8 = pcVar6;
                                          pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                                     (in_stack_00000048,
                                                                      &stack0x00001210,5);
                                          uVar11 = in_stack_000000e0;
                                          uVar7 = in_stack_00000088;
                                          if (DAT_1804b4d40 == 0) {
                                            return pcVar6;
                                          }
                                          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                            (in_stack_00000048,in_stack_00000088);
                                          (*in_stack_00000140)
                                                    (in_stack_00000048,lVar12,lVar1,pcVar6,uVar8);
                                          uVar7 = (*in_stack_000000e8)
                                                            (in_stack_00000048,uVar7,
                                                             in_stack_00000070,in_stack_00000138,
                                                             in_stack_00000148);
                                          DAT_1804b4478 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar7);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar7);
                                        }
                                        _uStack0000000000000050 =
                                             (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,DAT_1804b4478,
                                                        in_stack_000000a0);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                          (in_stack_00000048,2,in_stack_00000090,0);
                                        pcVar6 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_000000b8,
                                                           in_stack_000000c0,0x5a10);
                                        (*pcVar6)(in_stack_00000048,uVar7,0,uVar8);
                                        pcVar6 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                          (in_stack_00000048,in_stack_00000080,
                                                           in_stack_00000098,
                                                           uVar14 ^ 0xe7634bee918a9ca9);
                                        (*pcVar6)(in_stack_00000048,uVar7,1,uVar8);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,_uStack0000000000000050
                                                           ,in_stack_000000a8,uVar7);
                                        (**(code **)(*in_stack_00000048 + 0x188))
                                                  (in_stack_00000048,uVar7,in_stack_00000120);
                                        pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                                   (in_stack_00000048);
                                        if ((char)pcVar6 == '\0') {
                                          (**(code **)(*in_stack_00000048 + 0x658))
                                                    (in_stack_00000048,uVar11,0,1,&stack0x00000050);
                                          in_stack_000000e8 =
                                               (code *)CONCAT44(in_stack_000000e8._4_4_,
                                                                uStack0000000000000050);
                                          pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                                     (in_stack_00000048);
                                          if ((char)pcVar6 == '\0') {
                                            pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x720
                                                                         ))();
                                            if ((char)pcVar6 == '\0') {
                                              pcVar6 = (code *)(**(code **)(*in_stack_00000048 +
                                                                           0x720))();
                                              if ((char)pcVar6 == '\0') {
                                                if (DAT_1804b4480 != 0) {
                                                  return (code *)0xe7634bee918c0c23;
                                                }
                                                if (DAT_1804b59f8 != (code *)0x0) {
                                                  return DAT_1804b59f8;
                                                }
                                                return (code *)0x0;
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
  }
  return pcVar6;
}

