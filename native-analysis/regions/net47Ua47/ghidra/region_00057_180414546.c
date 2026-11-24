/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=57 entry=180414546 blocks=52 maxInstructions=50000 */

/* cut edge: 0x180414546 -> 0x1804378c6 */
/* cut edge: 0x18041455a -> 0x180473ae8 */
/* cut edge: 0x180414570 -> 0x180473ae8 */
/* cut edge: 0x1804145eb -> 0x18042bcce */
/* cut edge: 0x180414614 -> 0x18042bd04 */
/* cut edge: 0x180414673 -> 0x18042bd55 */
/* cut edge: 0x180414683 -> 0x18042bd8f */
/* cut edge: 0x180414761 -> 0x180473ae8 */
/* cut edge: 0x18041485b -> 0x180473ae8 */
/* cut edge: 0x180414871 -> 0x180473ae8 */
/* cut edge: 0x1804148e4 -> 0x18042d5bc */
/* cut edge: 0x180414908 -> 0x18042d5ef */
/* cut edge: 0x18041496c -> 0x18042d640 */
/* cut edge: 0x18041497c -> 0x18042d679 */
/* cut edge: 0x180414a57 -> 0x180473ae8 */
/* cut edge: 0x180414b4c -> 0x180473ae8 */
/* cut edge: 0x180414b62 -> 0x180473ae8 */
/* cut edge: 0x180414bd2 -> 0x18042ee98 */
/* cut edge: 0x180414bf6 -> 0x18042eecb */
/* cut edge: 0x180414c5a -> 0x18042ef1c */
/* cut edge: 0x180414c6a -> 0x18042ef55 */
/* cut edge: 0x180414d45 -> 0x180473ae8 */
/* cut edge: 0x180414e3a -> 0x180473ae8 */
/* cut edge: 0x180414e50 -> 0x180473ae8 */
/* cut edge: 0x180414ebb -> 0x18043077e */
/* cut edge: 0x180414ee4 -> 0x1804307b1 */
/* cut edge: 0x180414f48 -> 0x180430802 */
/* cut edge: 0x180414f58 -> 0x18043083b */
/* cut edge: 0x180415039 -> 0x180473ae8 */
/* cut edge: 0x18041512e -> 0x1803ddc53 */
/* cut edge: 0x18041514a -> 0x180473ae8 */
/* cut edge: 0x180415165 -> 0x180473ae8 */
/* cut edge: 0x1804151cd -> 0x180434ca5 */
/* cut edge: 0x1804151f1 -> 0x180434cd8 */
/* cut edge: 0x180415255 -> 0x180434d29 */
/* cut edge: 0x180415265 -> 0x180434d62 */
/* cut edge: 0x180415341 -> 0x180473ae8 */
/* cut edge: 0x180415436 -> 0x180473ae8 */
/* cut edge: 0x18041544c -> 0x180473ae8 */
/* cut edge: 0x180415462 -> 0x18041563c */
/* cut edge: 0x1804154bc -> 0x180434d9a */
/* cut edge: 0x1804154e0 -> 0x180434dcd */
/* cut edge: 0x1804154e0 -> 0x180415547 */
/* target 0x1803ddc53 original byte 0x8b replaced by RET */
/* target 0x180415547 original byte 0x48 replaced by RET */
/* target 0x18041563c original byte 0x48 replaced by RET */
/* target 0x18042bcce original byte 0x48 replaced by RET */
/* target 0x18042bd04 original byte 0x48 replaced by RET */
/* target 0x18042bd55 original byte 0x48 replaced by RET */
/* target 0x18042bd8f original byte 0x48 replaced by RET */
/* target 0x18042d5bc original byte 0x48 replaced by RET */
/* target 0x18042d5ef original byte 0x48 replaced by RET */
/* target 0x18042d640 original byte 0x48 replaced by RET */
/* target 0x18042d679 original byte 0x48 replaced by RET */
/* target 0x18042ee98 original byte 0x48 replaced by RET */
/* target 0x18042eecb original byte 0x48 replaced by RET */
/* target 0x18042ef1c original byte 0x48 replaced by RET */
/* target 0x18042ef55 original byte 0x48 replaced by RET */
/* target 0x18043077e original byte 0x48 replaced by RET */
/* target 0x1804307b1 original byte 0x48 replaced by RET */
/* target 0x180430802 original byte 0x48 replaced by RET */
/* target 0x18043083b original byte 0x48 replaced by RET */
/* target 0x180434ca5 original byte 0x48 replaced by RET */
/* target 0x180434cd8 original byte 0x48 replaced by RET */
/* target 0x180434d29 original byte 0x48 replaced by RET */
/* target 0x180434d62 original byte 0x48 replaced by RET */
/* target 0x180434d9a original byte 0x48 replaced by RET */
/* target 0x180434dcd original byte 0x48 replaced by RET */
/* target 0x1804378c6 original byte 0x4c replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


code * region_00057_180414546(void)

{
  ulonglong uVar1;
  longlong lVar2;
  longlong lVar3;
  longlong lVar4;
  code *in_RAX;
  code *pcVar5;
  undefined8 uVar6;
  code *pcVar7;
  code *pcVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  ulonglong uVar11;
  longlong lVar12;
  longlong unaff_RSI;
  longlong *in_stack_00000048;
  longlong in_stack_00000058;
  code *in_stack_00000070;
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
  longlong in_stack_00000118;
  
  uVar1 = in_stack_00000058 - unaff_RSI;
  if (in_stack_00000118 == 0) {
    return in_RAX;
  }
  pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)pcVar5 == '\0') {
    pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar12 = DAT_1804b5a08;
    uVar10 = DAT_1804b59f0;
    if ((char)pcVar5 == '\0') {
      if (DAT_1804b4140 == 0) {
        pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return pcVar5;
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000047e,1);
        lVar3 = DAT_1804b4d38;
        lVar2 = DAT_1804b4d30;
        pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return pcVar7;
        }
        if (DAT_1804b4d38 == 0) {
          return pcVar7;
        }
        pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                   (in_stack_00000048,&stack0x00001830,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar8;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar7)(in_stack_00000048,lVar2,lVar3,pcVar8,uVar9);
        uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000088,lVar12,uVar10,uVar6);
        DAT_1804b4140 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b4140,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x47e7);
      (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
      pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)uVar1 % 0x4e0c7 ^ in_stack_000000c8 ^ 0xf0e07a52e962f7a4);
      (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
      pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar5 == '\0') {
        pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar5 == '\0') {
          pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          lVar12 = DAT_1804b5a08;
          uVar10 = DAT_1804b59f0;
          if ((char)pcVar5 == '\0') {
            if (DAT_1804b4148 == 0) {
              pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return (code *)0x0;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x0000047c,1);
              lVar3 = DAT_1804b4d38;
              lVar2 = DAT_1804b4d30;
              pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar7;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar7;
              }
              pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x00001820,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar8;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar7)(in_stack_00000048,lVar2,lVar3,pcVar8,uVar9);
              uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000088,lVar12,uVar10,uVar6);
              DAT_1804b4148 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b4148,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2575);
            (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               (longlong)uVar1 % 0x91e27 ^ in_stack_000000c8 ^ 0xc46c8f8235931bc3);
            (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
            pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar5 == '\0') {
              pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)pcVar5 == '\0') {
                pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                lVar12 = DAT_1804b5a08;
                uVar10 = DAT_1804b59f0;
                if ((char)pcVar5 == '\0') {
                  if (DAT_1804b4150 == 0) {
                    pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b5a08 == 0) {
                      return (code *)0x0;
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x0000047a,1);
                    lVar3 = DAT_1804b4d38;
                    lVar2 = DAT_1804b4d30;
                    pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b4d30 == 0) {
                      return pcVar7;
                    }
                    if (DAT_1804b4d38 == 0) {
                      return pcVar7;
                    }
                    pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                               (in_stack_00000048,&stack0x00001810,5);
                    if (DAT_1804b4d40 == 0) {
                      return pcVar8;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000088);
                    (*pcVar7)(in_stack_00000048,lVar2,lVar3,pcVar8,uVar9);
                    uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000088,lVar12,uVar10,uVar6);
                    DAT_1804b4150 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b4150,in_stack_000000a0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x13b8);
                  (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
                  pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     (longlong)uVar1 % 0x90bed ^ in_stack_000000c8 ^
                                     0xa0a1c924d7cb203d);
                  (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                     (in_stack_00000048,uVar10,in_stack_000000d8);
                  pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)pcVar5 == '\0') {
                    pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)pcVar5 == '\0') {
                      pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      lVar12 = DAT_1804b5a08;
                      uVar10 = DAT_1804b59f0;
                      if ((char)pcVar5 == '\0') {
                        if (DAT_1804b4158 == 0) {
                          pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b5a08 == 0) {
                            return (code *)0x0;
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000478,1);
                          lVar3 = DAT_1804b4d38;
                          lVar2 = DAT_1804b4d30;
                          pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b4d30 == 0) {
                            return pcVar7;
                          }
                          if (DAT_1804b4d38 == 0) {
                            return pcVar7;
                          }
                          pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                     (in_stack_00000048,&stack0x00001800,5);
                          if (DAT_1804b4d40 == 0) {
                            return pcVar8;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000088);
                          (*pcVar7)(in_stack_00000048,lVar2,lVar3,pcVar8,uVar9);
                          uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000088,lVar12,uVar10,uVar6
                                            );
                          DAT_1804b4158 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804b4158,in_stack_000000a0);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x78a0);
                        (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
                        pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           (longlong)uVar1 % 0x3abc2 ^ in_stack_000000c8 ^
                                           0xc97c315062940767);
                        (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
                        lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                           (in_stack_00000048,uVar10,in_stack_000000d8);
                        pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                   (in_stack_00000048);
                        if ((char)pcVar5 == '\0') {
                          if (in_stack_00000118 == 0) {
                            return in_stack_00000070;
                          }
                          pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)pcVar5 == '\0') {
                            pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                            lVar2 = DAT_1804b5a08;
                            uVar10 = DAT_1804b59f0;
                            if ((char)pcVar5 == '\0') {
                              if (DAT_1804b4160 == 0) {
                                pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b5a08 == 0) {
                                  return (code *)0x0;
                                }
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00000476,1);
                                lVar4 = DAT_1804b4d38;
                                lVar3 = DAT_1804b4d30;
                                pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b4d30 == 0) {
                                  return pcVar7;
                                }
                                if (DAT_1804b4d38 == 0) {
                                  return pcVar7;
                                }
                                pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                           (in_stack_00000048,&stack0x000017f0,5);
                                if (DAT_1804b4d40 == 0) {
                                  return pcVar8;
                                }
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000088);
                                (*pcVar7)(in_stack_00000048,lVar3,lVar4,pcVar8,uVar9);
                                uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000088,lVar2,uVar10,
                                                   uVar6);
                                DAT_1804b4160 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar10);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                              }
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b4160,in_stack_000000a0)
                              ;
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000090,0);
                              pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000b8,
                                                 in_stack_000000c0,0x2457);
                              (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
                              pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 (longlong)((uVar11 ^ uVar1) - lVar12) % 0x5f1bb ^
                                                 in_stack_000000c8 ^ 0xbab09395feb87270);
                              (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar10,in_stack_000000d8);
                              pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                         (in_stack_00000048);
                              if ((char)pcVar5 == '\0') {
                                pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)pcVar5 == '\0') {
                                  pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)pcVar5 == '\0') {
                                    if (DAT_1804b4168 != 0) {
                                      return (code *)0xffdeeb315df574cd;
                                    }
                                    if (DAT_1804b5a08 != 0) {
                                      (**(code **)(*in_stack_00000048 + 0x518))
                                                (in_stack_00000048,&stack0x00000474,1);
                                      if (DAT_1804b4d30 != 0) {
                                        return *(code **)(*in_stack_00000048 + 0x390);
                                      }
                                      return *(code **)(*in_stack_00000048 + 0x390);
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
  return pcVar5;
}

