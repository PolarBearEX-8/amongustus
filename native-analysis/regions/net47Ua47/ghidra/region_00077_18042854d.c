/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=77 entry=18042854d blocks=51 maxInstructions=50000 */

/* cut edge: 0x18042854d -> 0x180473ae8 */
/* cut edge: 0x180428642 -> 0x180473ae8 */
/* cut edge: 0x180428658 -> 0x180473ae8 */
/* cut edge: 0x1804286c8 -> 0x180432af0 */
/* cut edge: 0x1804286ec -> 0x180432b23 */
/* cut edge: 0x18042874d -> 0x180432b74 */
/* cut edge: 0x18042875d -> 0x180432bad */
/* cut edge: 0x180428835 -> 0x180473ae8 */
/* cut edge: 0x18042892a -> 0x180473ae8 */
/* cut edge: 0x180428940 -> 0x180473ae8 */
/* cut edge: 0x1804289ae -> 0x180432be4 */
/* cut edge: 0x1804289d7 -> 0x180432c17 */
/* cut edge: 0x180428a38 -> 0x180432c68 */
/* cut edge: 0x180428a48 -> 0x180432ca1 */
/* cut edge: 0x180428b26 -> 0x180473ae8 */
/* cut edge: 0x180428c2b -> 0x1803dd671 */
/* cut edge: 0x180428c34 -> 0x180473ae8 */
/* cut edge: 0x180428c4a -> 0x180473ae8 */
/* cut edge: 0x180428cb2 -> 0x18042c94b */
/* cut edge: 0x180428cd6 -> 0x18042c97e */
/* cut edge: 0x180428d37 -> 0x18042c9cf */
/* cut edge: 0x180428d47 -> 0x18042ca08 */
/* cut edge: 0x180428e20 -> 0x180473ae8 */
/* cut edge: 0x180428f17 -> 0x180473ae8 */
/* cut edge: 0x180428f2d -> 0x180473ae8 */
/* cut edge: 0x180428fa8 -> 0x18042e138 */
/* cut edge: 0x180428fcc -> 0x18042e16e */
/* cut edge: 0x180429028 -> 0x18042e1bf */
/* cut edge: 0x180429040 -> 0x18042e1f8 */
/* cut edge: 0x18042911b -> 0x180473ae8 */
/* cut edge: 0x180429210 -> 0x180473ae8 */
/* cut edge: 0x180429226 -> 0x180473ae8 */
/* cut edge: 0x180429296 -> 0x18042fa12 */
/* cut edge: 0x1804292c2 -> 0x18042fa48 */
/* cut edge: 0x18042931e -> 0x18042fa9a */
/* cut edge: 0x18042932e -> 0x18042fad4 */
/* cut edge: 0x18042940c -> 0x180473ae8 */
/* cut edge: 0x180429501 -> 0x180473ae8 */
/* cut edge: 0x180429517 -> 0x180473ae8 */
/* cut edge: 0x18042952d -> 0x180429705 */
/* cut edge: 0x18042952d -> 0x180429582 */
/* target 0x1803dd671 original byte 0x48 replaced by RET */
/* target 0x180429582 original byte 0x48 replaced by RET */
/* target 0x180429705 original byte 0x48 replaced by RET */
/* target 0x18042c94b original byte 0x48 replaced by RET */
/* target 0x18042c97e original byte 0x48 replaced by RET */
/* target 0x18042c9cf original byte 0x48 replaced by RET */
/* target 0x18042ca08 original byte 0x48 replaced by RET */
/* target 0x18042e138 original byte 0x48 replaced by RET */
/* target 0x18042e16e original byte 0x48 replaced by RET */
/* target 0x18042e1bf original byte 0x48 replaced by RET */
/* target 0x18042e1f8 original byte 0x48 replaced by RET */
/* target 0x18042fa12 original byte 0x48 replaced by RET */
/* target 0x18042fa48 original byte 0x48 replaced by RET */
/* target 0x18042fa9a original byte 0x48 replaced by RET */
/* target 0x18042fad4 original byte 0x48 replaced by RET */
/* target 0x180432af0 original byte 0x48 replaced by RET */
/* target 0x180432b23 original byte 0x48 replaced by RET */
/* target 0x180432b74 original byte 0x48 replaced by RET */
/* target 0x180432bad original byte 0x48 replaced by RET */
/* target 0x180432be4 original byte 0x48 replaced by RET */
/* target 0x180432c17 original byte 0x48 replaced by RET */
/* target 0x180432c68 original byte 0x48 replaced by RET */
/* target 0x180432ca1 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


code * region_00077_18042854d(undefined8 param_1,undefined8 param_2)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  code *pcVar7;
  code *pcVar8;
  code *pcVar9;
  ulonglong uVar10;
  longlong lVar11;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
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
  
  uVar4 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2319);
  (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
  pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar4,in_stack_000000a8,uVar5)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar4,in_stack_000000d8);
  pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)pcVar7 == '\0') {
    pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar7 == '\0') {
      pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
      lVar11 = DAT_1804b5a08;
      uVar4 = DAT_1804b59f0;
      if ((char)pcVar7 == '\0') {
        if (DAT_1804b3e28 == 0) {
          pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return (code *)0x0;
          }
          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000544,1);
          lVar2 = DAT_1804b4d38;
          lVar1 = DAT_1804b4d30;
          pcVar8 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar8;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar8;
          }
          pcVar9 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00001e60,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar9;
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar6);
          uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000088,lVar11,uVar4,uVar5);
          DAT_1804b3e28 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
        }
        uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b3e28,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x884);
        (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
        pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           (longlong)in_stack_00000058 % 0x34ebc ^ in_stack_000000c8 ^
                           0xe1fe289ff5037859);
        (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))
                           (in_stack_00000048,uVar4,in_stack_000000d8);
        pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar7 == '\0') {
          pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)pcVar7 == '\0') {
            pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
            lVar11 = DAT_1804b5a08;
            uVar4 = DAT_1804b59f0;
            if ((char)pcVar7 == '\0') {
              if (DAT_1804b3e30 == 0) {
                pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return (code *)0x0;
                }
                uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000542,1);
                lVar2 = DAT_1804b4d38;
                lVar1 = DAT_1804b4d30;
                pcVar8 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return pcVar8;
                }
                if (DAT_1804b4d38 == 0) {
                  return pcVar8;
                }
                pcVar9 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                           (in_stack_00000048,&stack0x00001e50,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar9;
                }
                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000088);
                (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar6);
                uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000088,lVar11,uVar4,uVar5);
                DAT_1804b3e30 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
              }
              uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b3e30,in_stack_000000a0);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x30bf);
              (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
              pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 (longlong)in_stack_00000058 % 0xd0e01 ^ in_stack_000000c8 ^
                                 0xd763cc8aa16ac71b);
              (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
              uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
              lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                 (in_stack_00000048,uVar4,in_stack_000000d8);
              pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)pcVar7 == '\0') {
                lVar11 = (uVar10 ^ in_stack_00000058) - lVar11;
                if (in_stack_00000118 == 0) {
                  return (code *)0x0;
                }
                pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)pcVar7 == '\0') {
                  pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                  lVar1 = DAT_1804b5a08;
                  uVar4 = DAT_1804b59f0;
                  if ((char)pcVar7 == '\0') {
                    if (DAT_1804b3e38 == 0) {
                      pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b5a08 == 0) {
                        return (code *)0x0;
                      }
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00000540,1);
                      lVar3 = DAT_1804b4d38;
                      lVar2 = DAT_1804b4d30;
                      pcVar8 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return pcVar8;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return pcVar8;
                      }
                      pcVar9 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                 (in_stack_00000048,&stack0x00001e40,5);
                      if (DAT_1804b4d40 == 0) {
                        return pcVar9;
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,in_stack_00000088);
                      (*pcVar8)(in_stack_00000048,lVar2,lVar3,pcVar9,uVar6);
                      uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000088,lVar1,uVar4,uVar5);
                      DAT_1804b3e38 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
                    }
                    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b3e38,in_stack_000000a0);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4a33)
                    ;
                    (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
                    pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       lVar11 % 0x90368 ^ in_stack_000000c8 ^ 0x7938d6127b0fce01);
                    (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
                    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar4,in_stack_000000d8);
                    pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)pcVar7 == '\0') {
                      pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)pcVar7 == '\0') {
                        pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                        lVar1 = DAT_1804b5a08;
                        uVar4 = DAT_1804b59f0;
                        if ((char)pcVar7 == '\0') {
                          if (DAT_1804b3e40 == 0) {
                            pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b5a08 == 0) {
                              return pcVar7;
                            }
                            uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x0000053e,1);
                            lVar3 = DAT_1804b4d38;
                            lVar2 = DAT_1804b4d30;
                            pcVar8 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b4d30 == 0) {
                              return pcVar8;
                            }
                            if (DAT_1804b4d38 == 0) {
                              return pcVar8;
                            }
                            pcVar9 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                       (in_stack_00000048,&stack0x00001e30,5);
                            if (DAT_1804b4d40 == 0) {
                              return pcVar9;
                            }
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,in_stack_00000088);
                            (*pcVar8)(in_stack_00000048,lVar2,lVar3,pcVar9,uVar6);
                            uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000088,lVar1,uVar4,uVar5)
                            ;
                            DAT_1804b3e40 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
                          }
                          uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b3e40,in_stack_000000a0);
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000090,0);
                          pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                             0x4108);
                          (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
                          pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             lVar11 % 0xf39aa ^ in_stack_000000c8 ^
                                             0x261352370536d5b1);
                          (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
                          uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar4,in_stack_000000d8);
                          pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                          if ((char)pcVar7 == '\0') {
                            pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)pcVar7 == '\0') {
                              pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                              lVar1 = DAT_1804b5a08;
                              uVar4 = DAT_1804b59f0;
                              if ((char)pcVar7 == '\0') {
                                if (DAT_1804b3e48 == 0) {
                                  pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b5a08 == 0) {
                                    return pcVar7;
                                  }
                                  uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x0000053c,1);
                                  lVar3 = DAT_1804b4d38;
                                  lVar2 = DAT_1804b4d30;
                                  pcVar8 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b4d30 == 0) {
                                    return pcVar8;
                                  }
                                  if (DAT_1804b4d38 == 0) {
                                    return pcVar8;
                                  }
                                  pcVar9 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                             (in_stack_00000048,&stack0x00001e20,5);
                                  if (DAT_1804b4d40 == 0) {
                                    return pcVar9;
                                  }
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,in_stack_00000088);
                                  (*pcVar8)(in_stack_00000048,lVar2,lVar3,pcVar9,uVar6);
                                  uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000088,lVar1,uVar4,
                                                    uVar5);
                                  DAT_1804b3e48 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar4);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
                                }
                                uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804b3e48,in_stack_000000a0
                                                  );
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_00000090,0);
                                pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000b8,
                                                   in_stack_000000c0,0x2c66);
                                (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
                                pcVar7 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000080,
                                                   in_stack_00000098,
                                                   lVar11 % 0x57752 ^ in_stack_000000c8 ^
                                                   0x24715793349e0d88);
                                (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
                                uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar4,in_stack_000000d8);
                                pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                           (in_stack_00000048);
                                if ((char)pcVar7 == '\0') {
                                  pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)pcVar7 == '\0') {
                                    pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                    if ((char)pcVar7 == '\0') {
                                      if (DAT_1804b3e50 == 0) {
                                        return (code *)0x789165b8e26dad71;
                                      }
                                      return (code *)0x789165b8e26dad71;
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
  return pcVar7;
}

