/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=121 entry=1800981f2 blocks=51 maxInstructions=100000 */

/* cut edge: 0x1800981f2 -> 0x18014d3db */
/* cut edge: 0x180098208 -> 0x18014d3db */
/* cut edge: 0x180098275 -> 0x18009deb6 */
/* cut edge: 0x1800982a1 -> 0x18009deee */
/* cut edge: 0x180098302 -> 0x18009df3f */
/* cut edge: 0x180098312 -> 0x18009df79 */
/* cut edge: 0x1800983f2 -> 0x18014d3db */
/* cut edge: 0x1800984e7 -> 0x18014d3db */
/* cut edge: 0x1800984fd -> 0x18014d3db */
/* cut edge: 0x18009856d -> 0x18009dfb0 */
/* cut edge: 0x180098594 -> 0x18009dfe6 */
/* cut edge: 0x1800985ed -> 0x18009e037 */
/* cut edge: 0x1800985fd -> 0x18009e071 */
/* cut edge: 0x1800986d8 -> 0x18014d3db */
/* cut edge: 0x1800987cd -> 0x18014d3db */
/* cut edge: 0x1800987e3 -> 0x18014d3db */
/* cut edge: 0x18009884e -> 0x18009e0a7 */
/* cut edge: 0x18009887a -> 0x18009e0dd */
/* cut edge: 0x1800988d3 -> 0x18009e12e */
/* cut edge: 0x1800988e3 -> 0x18009e167 */
/* cut edge: 0x1800989c3 -> 0x18014d3db */
/* cut edge: 0x180098aba -> 0x18009968c */
/* cut edge: 0x180098ace -> 0x18012551c */
/* cut edge: 0x180098add -> 0x18014d3db */
/* cut edge: 0x180098af3 -> 0x18014d3db */
/* cut edge: 0x180098b71 -> 0x18009ef1b */
/* cut edge: 0x180098b98 -> 0x18009ef51 */
/* cut edge: 0x180098bf1 -> 0x18009efa2 */
/* cut edge: 0x180098c01 -> 0x18009efdc */
/* cut edge: 0x180098cdc -> 0x18014d3db */
/* cut edge: 0x180098dd1 -> 0x18014d3db */
/* cut edge: 0x180098de7 -> 0x18014d3db */
/* cut edge: 0x180098e52 -> 0x18009f012 */
/* cut edge: 0x180098e76 -> 0x18009f045 */
/* cut edge: 0x180098ed7 -> 0x18009f096 */
/* cut edge: 0x180098ee7 -> 0x18009f0cf */
/* cut edge: 0x180098fc2 -> 0x18014d3db */
/* cut edge: 0x1800990b7 -> 0x18014d3db */
/* cut edge: 0x1800990cd -> 0x18014d3db */
/* cut edge: 0x1800990e3 -> 0x1800992a5 */
/* cut edge: 0x1800990e3 -> 0x180099138 */
/* target 0x180099138 original byte 0x48 replaced by RET */
/* target 0x1800992a5 original byte 0x48 replaced by RET */
/* target 0x18009968c original byte 0x4d replaced by RET */
/* target 0x18009deb6 original byte 0x48 replaced by RET */
/* target 0x18009deee original byte 0x48 replaced by RET */
/* target 0x18009df3f original byte 0x48 replaced by RET */
/* target 0x18009df79 original byte 0x48 replaced by RET */
/* target 0x18009dfb0 original byte 0x48 replaced by RET */
/* target 0x18009dfe6 original byte 0x48 replaced by RET */
/* target 0x18009e037 original byte 0x48 replaced by RET */
/* target 0x18009e071 original byte 0x48 replaced by RET */
/* target 0x18009e0a7 original byte 0x48 replaced by RET */
/* target 0x18009e0dd original byte 0x48 replaced by RET */
/* target 0x18009e12e original byte 0x48 replaced by RET */
/* target 0x18009e167 original byte 0x48 replaced by RET */
/* target 0x18009ef1b original byte 0x48 replaced by RET */
/* target 0x18009ef51 original byte 0x48 replaced by RET */
/* target 0x18009efa2 original byte 0x48 replaced by RET */
/* target 0x18009efdc original byte 0x48 replaced by RET */
/* target 0x18009f012 original byte 0x48 replaced by RET */
/* target 0x18009f045 original byte 0x48 replaced by RET */
/* target 0x18009f096 original byte 0x48 replaced by RET */
/* target 0x18009f0cf original byte 0x4c replaced by RET */
/* target 0x18012551c original byte 0x4c replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00121_1800981f2(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  code *pcVar4;
  undefined8 uVar5;
  code *pcVar6;
  code *pcVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  ulonglong uVar10;
  longlong lVar11;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000060;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  longlong in_stack_000000f8;
  
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b52e8;
    uVar9 = DAT_1804b5208;
    if ((char)pcVar4 == '\0') {
      if (DAT_1804ac8c0 == 0) {
        pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b52e8 == 0) {
          return (code *)0x0;
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000a12,1);
        lVar2 = DAT_1804b4d38;
        lVar1 = DAT_1804b4d30;
        pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return pcVar6;
        }
        if (DAT_1804b4d38 == 0) {
          return pcVar6;
        }
        pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                   (in_stack_00000048,&stack0x000043a8,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar7;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
        uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar5);
        DAT_1804ac8c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ac8c0,in_stack_000000b0);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4343);
      (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         (longlong)in_stack_00000060 % 0x22585 ^ in_stack_000000c0 ^
                         0x2ef9e0c7dec8bcd5);
      (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar4 == '\0') {
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar4 == '\0') {
          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          lVar11 = DAT_1804b52e8;
          uVar9 = DAT_1804b5208;
          if ((char)pcVar4 == '\0') {
            if (DAT_1804ac8c8 == 0) {
              pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b52e8 == 0) {
                return pcVar4;
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000a10,1);
              lVar2 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar6;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar6;
              }
              pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x00004398,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar7;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090)
              ;
              (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
              uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar5);
              DAT_1804ac8c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804ac8c8,in_stack_000000b0);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6845);
            (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               (longlong)in_stack_00000060 % 0xef31e ^ in_stack_000000c0 ^
                               0x6c6689a3656324c3);
            (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))
                               (in_stack_00000048,uVar9,in_stack_000000d8);
            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar4 == '\0') {
              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)pcVar4 == '\0') {
                pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                lVar11 = DAT_1804b52e8;
                uVar9 = DAT_1804b5208;
                if ((char)pcVar4 == '\0') {
                  if (DAT_1804ac8d0 == 0) {
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b52e8 == 0) {
                      return pcVar4;
                    }
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000a0e,1);
                    lVar2 = DAT_1804b4d38;
                    lVar1 = DAT_1804b4d30;
                    pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b4d30 == 0) {
                      return pcVar6;
                    }
                    if (DAT_1804b4d38 == 0) {
                      return pcVar6;
                    }
                    pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                               (in_stack_00000048,&stack0x00004388,5);
                    if (DAT_1804b4d40 == 0) {
                      return pcVar7;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000090);
                    (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
                    uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar5);
                    DAT_1804ac8d0 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                  }
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804ac8d0,in_stack_000000b0);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2783);
                  (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     (longlong)in_stack_00000060 % 0x8ddca ^ in_stack_000000c0 ^
                                     0x1fd2a095ba5df9e9);
                  (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
                  lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                     (in_stack_00000048,uVar9,in_stack_000000d8);
                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)pcVar4 == '\0') {
                    lVar11 = lVar11 + (uVar10 ^ in_stack_00000060);
                    if ((longlong)in_stack_000000c0 < 1) {
                      return pcVar4;
                    }
                    if (in_stack_000000f8 != 0) {
                      return pcVar4;
                    }
                    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)pcVar4 == '\0') {
                      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      lVar1 = DAT_1804b52e8;
                      uVar9 = DAT_1804b5208;
                      if ((char)pcVar4 == '\0') {
                        if (DAT_1804acdd8 == 0) {
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b52e8 == 0) {
                            return pcVar4;
                          }
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x000008c2,1);
                          lVar3 = DAT_1804b4d38;
                          lVar2 = DAT_1804b4d30;
                          pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b4d30 == 0) {
                            return pcVar6;
                          }
                          if (DAT_1804b4d38 == 0) {
                            return pcVar6;
                          }
                          pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                     (in_stack_00000048,&stack0x00003bd8,5);
                          if (DAT_1804b4d40 == 0) {
                            return pcVar7;
                          }
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000090);
                          (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                          uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar9,uVar5);
                          DAT_1804acdd8 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                        }
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804acdd8,in_stack_000000b0);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x729a);
                        (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           lVar11 % 0x72d9d ^ in_stack_000000c0 ^ 0x8b1f477444beb3e5
                                          );
                        (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
                        (**(code **)(*in_stack_00000048 + 0x1a0))
                                  (in_stack_00000048,uVar9,in_stack_000000d8);
                        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                   (in_stack_00000048);
                        if ((char)pcVar4 == '\0') {
                          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)pcVar4 == '\0') {
                            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                            lVar1 = DAT_1804b52e8;
                            uVar9 = DAT_1804b5208;
                            if ((char)pcVar4 == '\0') {
                              if (DAT_1804acde0 == 0) {
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b52e8 == 0) {
                                  return (code *)0x0;
                                }
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x000008c0,1);
                                lVar3 = DAT_1804b4d38;
                                lVar2 = DAT_1804b4d30;
                                pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b4d30 == 0) {
                                  return pcVar6;
                                }
                                if (DAT_1804b4d38 == 0) {
                                  return pcVar6;
                                }
                                pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                           (in_stack_00000048,&stack0x00003bc8,5);
                                if (DAT_1804b4d40 == 0) {
                                  return pcVar7;
                                }
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000090);
                                (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                                uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar9,
                                                  uVar5);
                                DAT_1804acde0 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar9);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                              }
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804acde0,in_stack_000000b0);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_000000a0,0);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000c8,
                                                 in_stack_000000d0,0x67f7);
                              (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,
                                                 lVar11 % 0xd226a ^ in_stack_000000c0 ^
                                                 0xa34a6b93e7b3e76a);
                              (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar9,in_stack_000000d8);
                              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                         (in_stack_00000048);
                              if ((char)pcVar4 == '\0') {
                                pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)pcVar4 == '\0') {
                                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)pcVar4 == '\0') {
                                    if (DAT_1804acde8 == 0) {
                                      return (code *)0xea586858305c0288;
                                    }
                                    return (code *)0xea586858305c0288;
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
  return pcVar4;
}

