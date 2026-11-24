/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=32 entry=1802b8e87 blocks=38 maxInstructions=50000 */

/* cut edge: 0x1802b8e87 -> 0x180333186 */
/* cut edge: 0x1802b8f81 -> 0x180333186 */
/* cut edge: 0x1802b8f97 -> 0x180333186 */
/* cut edge: 0x1802b913a -> 0x180333186 */
/* cut edge: 0x1802b9231 -> 0x180333186 */
/* cut edge: 0x1802b9247 -> 0x180333186 */
/* cut edge: 0x1802b93f2 -> 0x180333186 */
/* cut edge: 0x1802b94e9 -> 0x180333186 */
/* cut edge: 0x1802b94ff -> 0x180333186 */
/* cut edge: 0x1802b96ac -> 0x180333186 */
/* cut edge: 0x1802b97a1 -> 0x180333186 */
/* cut edge: 0x1802b97b7 -> 0x180333186 */
/* cut edge: 0x1802b996b -> 0x180333186 */
/* cut edge: 0x1802b9a89 -> 0x1802b3db2 */
/* cut edge: 0x1802b9a91 -> 0x1802b27c4 */
/* cut edge: 0x1802b9ac9 -> 0x1802b281d */
/* cut edge: 0x1802b9afe -> 0x1802b281d */
/* cut edge: 0x1802b9b1a -> 0x1802b282d */
/* cut edge: 0x1802b9b53 -> 0x1802b2885 */
/* cut edge: 0x1802b9b8a -> 0x1802b2aa3 */
/* cut edge: 0x1802b9bc2 -> 0x1802b2aff */
/* cut edge: 0x1802b9bf7 -> 0x1802b2aff */
/* cut edge: 0x1802b9c13 -> 0x1802b2b0f */
/* cut edge: 0x1802b9c4c -> 0x1802b2b68 */
/* cut edge: 0x1802b9c84 -> 0x1802b2d83 */
/* cut edge: 0x1802b9cba -> 0x1802b2ddf */
/* cut edge: 0x1802b9cef -> 0x1802b2ddf */
/* cut edge: 0x1802b9d0b -> 0x1802b2def */
/* cut edge: 0x1802b9d44 -> 0x1802b2e47 */
/* target 0x1802b27c4 original byte 0x48 replaced by RET */
/* target 0x1802b281d original byte 0x48 replaced by RET */
/* target 0x1802b282d original byte 0x48 replaced by RET */
/* target 0x1802b2885 original byte 0x48 replaced by RET */
/* target 0x1802b2aa3 original byte 0x48 replaced by RET */
/* target 0x1802b2aff original byte 0x48 replaced by RET */
/* target 0x1802b2b0f original byte 0x48 replaced by RET */
/* target 0x1802b2b68 original byte 0x48 replaced by RET */
/* target 0x1802b2d83 original byte 0x48 replaced by RET */
/* target 0x1802b2ddf original byte 0x48 replaced by RET */
/* target 0x1802b2def original byte 0x48 replaced by RET */
/* target 0x1802b2e47 original byte 0x48 replaced by RET */
/* target 0x1802b3db2 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


longlong region_00032_1802b8e87(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  longlong lVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  ulonglong uVar12;
  longlong lVar13;
  longlong *in_stack_00000040;
  ulonglong in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000f8;
  
  uVar4 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,param_2,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x2876);
  (*pcVar1)(in_stack_00000040,uVar5,0,uVar6);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar5,1,uVar6);
  uVar4 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar4,in_stack_000000a8,uVar5)
  ;
  (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar4,in_stack_000000f8);
  lVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if ((char)lVar7 == '\0') {
    lVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
    if ((char)lVar7 == '\0') {
      lVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
      if ((char)lVar7 == '\0') {
        if (DAT_1804b0d60 == 0) {
          lVar7 = *in_stack_00000040;
          pcVar1 = *(code **)(lVar7 + 0x390);
          uVar5 = FUN_1803335f0(in_stack_00000040);
          uVar4 = DAT_1804b59f0;
          uVar6 = (**(code **)(lVar7 + 0x518))(in_stack_00000040,&stack0x000005f8,1);
          lVar7 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar7 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000040);
          uVar9 = FUN_180002550(in_stack_00000040);
          uVar10 = (**(code **)(lVar7 + 0x518))(in_stack_00000040,&stack0x00002858,5);
          pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000040);
          uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
          (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
          uVar4 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar5,uVar4,uVar6);
          DAT_1804b0d60 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
        }
        uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b0d60,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x3905);
        (*pcVar1)(in_stack_00000040,uVar5,0,uVar6);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           (longlong)in_stack_00000070 % 0x1c0af ^ in_stack_000000b0 ^
                           0x6dbbefebd780b1aa);
        (*pcVar1)(in_stack_00000040,uVar5,1,uVar6);
        uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar4,in_stack_000000d0);
        lVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if ((char)lVar7 == '\0') {
          lVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
          if ((char)lVar7 == '\0') {
            lVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
            if ((char)lVar7 == '\0') {
              if (DAT_1804b0d68 == 0) {
                lVar7 = *in_stack_00000040;
                pcVar1 = *(code **)(lVar7 + 0x390);
                uVar5 = FUN_1803335f0(in_stack_00000040);
                uVar4 = DAT_1804b59f0;
                uVar6 = (**(code **)(lVar7 + 0x518))(in_stack_00000040,&stack0x000005f6,1);
                lVar7 = *in_stack_00000040;
                pcVar2 = *(code **)(lVar7 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000040);
                uVar9 = FUN_180002550(in_stack_00000040);
                uVar10 = (**(code **)(lVar7 + 0x518))(in_stack_00000040,&stack0x00002848,5);
                pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000040);
                uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
                (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
                uVar4 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar5,uVar4,uVar6);
                DAT_1804b0d68 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
              }
              uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b0d68,in_stack_000000a0);
              uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1272);
              (*pcVar1)(in_stack_00000040,uVar5,0,uVar6);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 (longlong)in_stack_00000070 % 0xdb8c9 ^ in_stack_000000b0 ^
                                 0x52a8b940217edf0);
              (*pcVar1)(in_stack_00000040,uVar5,1,uVar6);
              uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar4,in_stack_000000d0);
              lVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if ((char)lVar7 == '\0') {
                lVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
                if ((char)lVar7 == '\0') {
                  lVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
                  if ((char)lVar7 == '\0') {
                    if (DAT_1804b0d70 == 0) {
                      lVar7 = *in_stack_00000040;
                      pcVar1 = *(code **)(lVar7 + 0x390);
                      uVar5 = FUN_1803335f0(in_stack_00000040);
                      uVar4 = DAT_1804b59f0;
                      uVar6 = (**(code **)(lVar7 + 0x518))(in_stack_00000040,&stack0x000005f4,1);
                      lVar7 = *in_stack_00000040;
                      pcVar2 = *(code **)(lVar7 + 0x390);
                      uVar8 = FUN_1800024f0(in_stack_00000040);
                      uVar9 = FUN_180002550(in_stack_00000040);
                      uVar10 = (**(code **)(lVar7 + 0x518))(in_stack_00000040,&stack0x00002838,5);
                      pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                      uVar11 = FUN_1800025a0(in_stack_00000040);
                      uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
                      (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
                      uVar4 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar5,uVar4,uVar6);
                      DAT_1804b0d70 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
                    }
                    uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,DAT_1804b0d70,in_stack_000000a0);
                    uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0xbbf);
                    (*pcVar1)(in_stack_00000040,uVar5,0,uVar6);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                       (longlong)in_stack_00000070 % 0xa852d ^ in_stack_000000b0 ^
                                       0x44f8a0ed6550a3ef);
                    (*pcVar1)(in_stack_00000040,uVar5,1,uVar6);
                    uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
                    uVar12 = (**(code **)(*in_stack_00000040 + 0x1a0))
                                       (in_stack_00000040,uVar4,in_stack_000000d0);
                    lVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if ((char)lVar7 == '\0') {
                      lVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
                      if ((char)lVar7 == '\0') {
                        lVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
                        if ((char)lVar7 == '\0') {
                          if (DAT_1804b0d78 == 0) {
                            lVar7 = *in_stack_00000040;
                            pcVar1 = *(code **)(lVar7 + 0x390);
                            uVar5 = FUN_1803335f0(in_stack_00000040);
                            uVar4 = DAT_1804b59f0;
                            uVar6 = (**(code **)(lVar7 + 0x518))
                                              (in_stack_00000040,&stack0x000005f2,1);
                            lVar7 = *in_stack_00000040;
                            pcVar2 = *(code **)(lVar7 + 0x390);
                            uVar8 = FUN_1800024f0(in_stack_00000040);
                            uVar9 = FUN_180002550(in_stack_00000040);
                            uVar10 = (**(code **)(lVar7 + 0x518))
                                               (in_stack_00000040,&stack0x00002828,5);
                            pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                            uVar11 = FUN_1800025a0(in_stack_00000040);
                            uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
                            (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
                            uVar4 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar5,uVar4,uVar6)
                            ;
                            DAT_1804b0d78 =
                                 (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
                            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
                          }
                          uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                            (in_stack_00000040,DAT_1804b0d78,in_stack_000000a0);
                          uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                            (in_stack_00000040,2,in_stack_00000090,0);
                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                            (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,
                                             0x2c38);
                          (*pcVar1)(in_stack_00000040,uVar5,0,uVar6);
                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                             (longlong)in_stack_00000070 % 0x298bd ^
                                             in_stack_000000b0 ^ 0x5b049eb5c494dfb6);
                          (*pcVar1)(in_stack_00000040,uVar5,1,uVar6);
                          uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                            (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
                          lVar13 = (**(code **)(*in_stack_00000040 + 0x1a0))
                                             (in_stack_00000040,uVar4,in_stack_000000d0);
                          lVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                          if ((char)lVar7 == '\0') {
                            return (in_stack_00000070 ^ uVar12) - lVar13;
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
  return lVar7;
}

