/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=66 entry=18041d60c blocks=54 maxInstructions=50000 */

/* cut edge: 0x18041d60c -> 0x180473ae8 */
/* cut edge: 0x18041d622 -> 0x180473ae8 */
/* cut edge: 0x18041d68d -> 0x180435cdb */
/* cut edge: 0x18041d6b1 -> 0x180435d11 */
/* cut edge: 0x18041d712 -> 0x180435d62 */
/* cut edge: 0x18041d727 -> 0x180435d9b */
/* cut edge: 0x18041d803 -> 0x180473ae8 */
/* cut edge: 0x18041d8f8 -> 0x18042a797 */
/* cut edge: 0x18041d917 -> 0x180473ae8 */
/* cut edge: 0x18041d92d -> 0x180473ae8 */
/* cut edge: 0x18041d998 -> 0x180433a4e */
/* cut edge: 0x18041d9bc -> 0x180433a81 */
/* cut edge: 0x18041da1d -> 0x180433ad2 */
/* cut edge: 0x18041da2d -> 0x180433b0b */
/* cut edge: 0x18041db06 -> 0x180473ae8 */
/* cut edge: 0x18041dbfd -> 0x180473ae8 */
/* cut edge: 0x18041dc13 -> 0x180473ae8 */
/* cut edge: 0x18041dc7b -> 0x180433e24 */
/* cut edge: 0x18041dca4 -> 0x180433e5c */
/* cut edge: 0x18041dd03 -> 0x180433ead */
/* cut edge: 0x18041dd13 -> 0x180433ee7 */
/* cut edge: 0x18041ddee -> 0x180473ae8 */
/* cut edge: 0x18041dee5 -> 0x180473ae8 */
/* cut edge: 0x18041defb -> 0x180473ae8 */
/* cut edge: 0x18041df63 -> 0x180434200 */
/* cut edge: 0x18041df87 -> 0x180434238 */
/* cut edge: 0x18041dfd4 -> 0x18043428a */
/* cut edge: 0x18041e001 -> 0x1804342c4 */
/* cut edge: 0x18041e0df -> 0x180473ae8 */
/* cut edge: 0x18041e1d4 -> 0x180473ae8 */
/* cut edge: 0x18041e1ea -> 0x180473ae8 */
/* cut edge: 0x18041e252 -> 0x1804345db */
/* cut edge: 0x18041e27b -> 0x180434613 */
/* cut edge: 0x18041e2dd -> 0x180434662 */
/* cut edge: 0x18041e2f5 -> 0x18043469d */
/* cut edge: 0x18041e3dc -> 0x180473ae8 */
/* cut edge: 0x18041e4d3 -> 0x180437954 */
/* cut edge: 0x18041e501 -> 0x18041f112 */
/* cut edge: 0x18041e51b -> 0x180473ae8 */
/* cut edge: 0x18041e531 -> 0x180473ae8 */
/* cut edge: 0x18041e531 -> 0x18041e547 */
/* target 0x18041e547 original byte 0x48 replaced by RET */
/* target 0x18041f112 original byte 0x48 replaced by RET */
/* target 0x18042a797 original byte 0x4c replaced by RET */
/* target 0x180433a4e original byte 0x48 replaced by RET */
/* target 0x180433a81 original byte 0x48 replaced by RET */
/* target 0x180433ad2 original byte 0x48 replaced by RET */
/* target 0x180433b0b original byte 0x48 replaced by RET */
/* target 0x180433e24 original byte 0x48 replaced by RET */
/* target 0x180433e5c original byte 0x48 replaced by RET */
/* target 0x180433ead original byte 0x48 replaced by RET */
/* target 0x180433ee7 original byte 0x48 replaced by RET */
/* target 0x180434200 original byte 0x48 replaced by RET */
/* target 0x180434238 original byte 0x4c replaced by RET */
/* target 0x18043428a original byte 0x48 replaced by RET */
/* target 0x1804342c4 original byte 0x48 replaced by RET */
/* target 0x1804345db original byte 0x48 replaced by RET */
/* target 0x180434613 original byte 0x48 replaced by RET */
/* target 0x180434662 original byte 0x48 replaced by RET */
/* target 0x18043469d original byte 0x48 replaced by RET */
/* target 0x180435cdb original byte 0x48 replaced by RET */
/* target 0x180435d11 original byte 0x48 replaced by RET */
/* target 0x180435d62 original byte 0x48 replaced by RET */
/* target 0x180435d9b original byte 0x48 replaced by RET */
/* target 0x180437954 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00066_18041d60c(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  longlong lVar11;
  ulonglong unaff_R13;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  int in_stack_000000f8;
  longlong in_stack_00000118;
  int in_stack_00000170;
  undefined8 in_stack_00000210;
  undefined2 uStack0000000000000218;
  undefined2 uStack000000000000021a;
  
  uVar10 = _uStack0000000000000218;
  uStack000000000000021a = (undefined2)((ulonglong)_uStack0000000000000218 >> 0x10);
  uStack0000000000000218 = (undefined2)uVar10;
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b5a08;
    uVar10 = DAT_1804b59f0;
    if (cVar6 == '\0') {
      if (DAT_1804b4ab0 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                          (in_stack_00000048,(longlong)&stack0x00000218 + 4,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000990,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar10,uVar7);
        DAT_1804b4ab0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b4ab0,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x761a);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)in_stack_00000068 % 0xe2f65 ^ in_stack_000000c8 ^
                         0x42b1a3887cbc6b03);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
      lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8)
      ;
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        lVar11 = (unaff_R13 ^ in_stack_00000068) - lVar11;
        if (in_stack_00000118 == 0) {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          lVar3 = DAT_1804b5a08;
          uVar10 = DAT_1804b59f0;
          if (cVar6 == '\0') {
            if (DAT_1804b4ab8 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return;
              }
              uStack000000000000021a = 0x7a;
              uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,(longlong)&stack0x00000218 + 2,1);
              lVar5 = DAT_1804b4d38;
              lVar4 = DAT_1804b4d30;
              pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000980,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar3,uVar10,uVar7);
              DAT_1804b4ab8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b4ab8,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x20d7);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               lVar11 % 0x7bb3b ^ in_stack_000000c8 ^ 0x49137ec087bb1adb);
            (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                lVar3 = DAT_1804b5a08;
                uVar10 = DAT_1804b59f0;
                if (cVar6 == '\0') {
                  if (DAT_1804b4ac0 == 0) {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b5a08 == 0) {
                      return;
                    }
                    uStack0000000000000218 = 0x7a;
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000218,1);
                    lVar5 = DAT_1804b4d38;
                    lVar4 = DAT_1804b4d30;
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b4d30 == 0) {
                      return;
                    }
                    if (DAT_1804b4d38 == 0) {
                      return;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000970,5);
                    if (DAT_1804b4d40 == 0) {
                      return;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000088);
                    (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
                    uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar3,uVar10,uVar7);
                    DAT_1804b4ac0 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b4ac0,in_stack_000000a0);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x46f2);
                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     lVar11 % 0xda592 ^ in_stack_000000c8 ^ 0x16b4cf4c9e898139);
                  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                  (**(code **)(*in_stack_00000048 + 0x1a0))
                            (in_stack_00000048,uVar10,in_stack_000000d8);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      lVar3 = DAT_1804b5a08;
                      uVar10 = DAT_1804b59f0;
                      if (cVar6 == '\0') {
                        if (DAT_1804b4ac8 == 0) {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b5a08 == 0) {
                            return;
                          }
                          in_stack_00000210._6_2_ = 0x7a;
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,(longlong)&stack0x00000210 + 6,1);
                          lVar5 = DAT_1804b4d38;
                          lVar4 = DAT_1804b4d30;
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b4d30 == 0) {
                            return;
                          }
                          if (DAT_1804b4d38 == 0) {
                            return;
                          }
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000960,5);
                          if (DAT_1804b4d40 == 0) {
                            return;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000088);
                          (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
                          uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar3,uVar10,uVar7)
                          ;
                          DAT_1804b4ac8 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804b4ac8,in_stack_000000a0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x30dd);
                        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           lVar11 % 0xa5487 ^ in_stack_000000c8 ^ 0x789302b747515868
                                          );
                        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                        (**(code **)(*in_stack_00000048 + 0x1a0))
                                  (in_stack_00000048,uVar10,in_stack_000000d8);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 == '\0') {
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                            lVar3 = DAT_1804b5a08;
                            uVar10 = DAT_1804b59f0;
                            if (cVar6 == '\0') {
                              if (DAT_1804b4ad0 == 0) {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b5a08 == 0) {
                                  return;
                                }
                                in_stack_00000210._4_2_ = 0x7a;
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,(longlong)&stack0x00000210 + 4,
                                                   1);
                                lVar5 = DAT_1804b4d38;
                                lVar4 = DAT_1804b4d30;
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b4d30 == 0) {
                                  return;
                                }
                                if (DAT_1804b4d38 == 0) {
                                  return;
                                }
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00000950,5);
                                if (DAT_1804b4d40 == 0) {
                                  return;
                                }
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000088);
                                (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
                                uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar3,uVar10,
                                                   uVar7);
                                DAT_1804b4ad0 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar10);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                              }
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b4ad0,in_stack_000000a0)
                              ;
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000090,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000b8,
                                                 in_stack_000000c0,0x78e4);
                              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 lVar11 % 0xe9fdc ^ in_stack_000000c8 ^
                                                 0x7e470656ecbc0e63);
                              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar10,in_stack_000000d8);
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar6 == '\0') {
                                if (in_stack_00000118 == 0) {
                                  return;
                                }
                                if (in_stack_00000170 < in_stack_000000f8) {
                                  return;
                                }
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar6 == '\0') {
                                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar6 == '\0') {
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
  return;
}

