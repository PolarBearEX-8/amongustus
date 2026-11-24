/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=8 entry=1803e3665 blocks=36 maxInstructions=50000 */

/* cut edge: 0x1803e3665 -> 0x180473ae8 */
/* cut edge: 0x1803e3767 -> 0x1803ff139 */
/* cut edge: 0x1803e3791 -> 0x180473ae8 */
/* cut edge: 0x1803e38ea -> 0x180473ae8 */
/* cut edge: 0x1803e39ec -> 0x180415d47 */
/* cut edge: 0x1803e39fe -> 0x18041ca67 */
/* cut edge: 0x1803e3a0c -> 0x180473ae8 */
/* cut edge: 0x1803e3b65 -> 0x180473ae8 */
/* cut edge: 0x1803e3c67 -> 0x180417899 */
/* cut edge: 0x1803e3c98 -> 0x180473ae8 */
/* cut edge: 0x1803e3df1 -> 0x180473ae8 */
/* cut edge: 0x1803e3eea -> 0x18043f671 */
/* cut edge: 0x1803e3eff -> 0x180473ae8 */
/* cut edge: 0x1803e4055 -> 0x180473ae8 */
/* cut edge: 0x1803e4157 -> 0x180419704 */
/* cut edge: 0x1803e4169 -> 0x180473ae8 */
/* cut edge: 0x1803e42c2 -> 0x180473ae8 */
/* cut edge: 0x1803e43bb -> 0x18040d8cd */
/* cut edge: 0x1803e43c8 -> 0x180473ae8 */
/* cut edge: 0x1803e4521 -> 0x180473ae8 */
/* cut edge: 0x1803e4521 -> 0x1803e461a */
/* target 0x1803e461a original byte 0x3b replaced by RET */
/* target 0x1803ff139 original byte 0x48 replaced by RET */
/* target 0x18040d8cd original byte 0x48 replaced by RET */
/* target 0x180415d47 original byte 0x48 replaced by RET */
/* target 0x180417899 original byte 0x48 replaced by RET */
/* target 0x180419704 original byte 0x48 replaced by RET */
/* target 0x18041ca67 original byte 0x48 replaced by RET */
/* target 0x18043f671 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


longlong region_00008_1803e3665(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  int iVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  longlong lVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  int in_stack_000000e0;
  longlong in_stack_00000100;
  longlong in_stack_00000108;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00002ea8;
  undefined8 in_stack_00002eb0;
  undefined8 in_stack_00002eb8;
  undefined8 in_stack_00002ec0;
  undefined8 in_stack_00002ec8;
  undefined8 in_stack_00002ed0;
  undefined8 in_stack_00002ed8;
  
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x19d7);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002ed8);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_000000a8,uVar6)
  ;
  iVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar5,in_stack_00000120);
  lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar8 == '\0') {
    if (iVar4 == in_stack_000000e0) {
      return in_stack_00000100;
    }
    lVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar8 == '\0') {
      if (DAT_1804b3748 == 0) {
        lVar8 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar8 + 0x390);
        uVar6 = FUN_180333550(in_stack_00000048);
        uVar5 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x000006fc,1);
        lVar8 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar8 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x00002c10,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
        (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
        DAT_1804b3748 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b3748,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x229);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002ed0);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
      iVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar5,in_stack_00000120);
      lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)lVar8 == '\0') {
        if (iVar4 == in_stack_000000e0) {
          return in_stack_00000108;
        }
        if (in_stack_000000e0 == 2) {
          return lVar8;
        }
        lVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)lVar8 == '\0') {
          if (DAT_1804b3750 == 0) {
            lVar8 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar8 + 0x390);
            uVar6 = FUN_180333550(in_stack_00000048);
            uVar5 = DAT_1804b59f0;
            uVar7 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x000006fa,1);
            lVar8 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar8 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x00002c00,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar12 = FUN_1800025a0(in_stack_00000048);
            uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
            (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
            uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
            DAT_1804b3750 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
          }
          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b3750,in_stack_000000a0);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x7b10);
          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002ec8
                            );
          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
          iVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar5,in_stack_00000120);
          lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)lVar8 == '\0') {
            if (iVar4 == in_stack_000000e0) {
              return in_stack_00000100;
            }
            lVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)lVar8 == '\0') {
              if (DAT_1804b3758 == 0) {
                lVar8 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar8 + 0x390);
                uVar6 = FUN_180333550(in_stack_00000048);
                uVar5 = DAT_1804b59f0;
                uVar7 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x000006f8,1);
                lVar8 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar8 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x00002bf0,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
                (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
                DAT_1804b3758 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b3758,in_stack_000000a0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4142);
              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_00002ec0);
              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
              iVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar5,in_stack_00000120);
              lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)lVar8 == '\0') {
                if (iVar4 == in_stack_000000e0) {
                  return *in_stack_00000048;
                }
                lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)lVar8 == '\0') {
                  if (DAT_1804b3760 == 0) {
                    lVar8 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar8 + 0x390);
                    uVar6 = FUN_180333550(in_stack_00000048);
                    uVar5 = DAT_1804b59f0;
                    uVar7 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x000006f6,1);
                    lVar8 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar8 + 0x390);
                    uVar9 = FUN_1800024f0(in_stack_00000048);
                    uVar10 = FUN_180002550(in_stack_00000048);
                    uVar11 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x00002be0,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar12 = FUN_1800025a0(in_stack_00000048);
                    uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
                    (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                    uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
                    DAT_1804b3760 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                  }
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b3760,in_stack_000000a0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2b6a);
                  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     in_stack_00002eb8);
                  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                  iVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar5,in_stack_00000120);
                  lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)lVar8 == '\0') {
                    if (iVar4 == in_stack_000000e0) {
                      return in_stack_00000108;
                    }
                    lVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)lVar8 == '\0') {
                      if (DAT_1804b3768 == 0) {
                        lVar8 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar8 + 0x390);
                        uVar6 = FUN_180333550(in_stack_00000048);
                        uVar5 = DAT_1804b59f0;
                        uVar7 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x000006f4,1);
                        lVar8 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar8 + 0x390);
                        uVar9 = FUN_1800024f0(in_stack_00000048);
                        uVar10 = FUN_180002550(in_stack_00000048);
                        uVar11 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x00002bd0,5);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar12 = FUN_1800025a0(in_stack_00000048);
                        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
                        (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                        uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
                        DAT_1804b3768 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                      }
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b3768,in_stack_000000a0);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_00000090,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                         0x5f3d);
                      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                         in_stack_00002eb0);
                      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                      iVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                                        (in_stack_00000048,uVar5,in_stack_00000120);
                      lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)lVar8 == '\0') {
                        if (iVar4 == in_stack_000000e0) {
                          return lVar8;
                        }
                        lVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)lVar8 == '\0') {
                          if (DAT_1804b3770 == 0) {
                            lVar8 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar8 + 0x390);
                            uVar6 = FUN_180333550(in_stack_00000048);
                            uVar5 = DAT_1804b59f0;
                            uVar7 = (**(code **)(lVar8 + 0x518))
                                              (in_stack_00000048,&stack0x000006f2,1);
                            lVar8 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar8 + 0x390);
                            uVar9 = FUN_1800024f0(in_stack_00000048);
                            uVar10 = FUN_180002550(in_stack_00000048);
                            uVar11 = (**(code **)(lVar8 + 0x518))
                                               (in_stack_00000048,&stack0x00002bc0,5);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar12 = FUN_1800025a0(in_stack_00000048);
                            uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
                            (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                            uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7)
                            ;
                            DAT_1804b3770 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                          }
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b3770,in_stack_000000a0);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000090,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                             0x6433);
                          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             in_stack_00002ea8);
                          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                          (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar5,in_stack_00000120);
                          lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)lVar8 == '\0') {
                            return lVar8;
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
  return lVar8;
}

