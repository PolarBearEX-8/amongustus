/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=124 entry=18009b237 blocks=51 maxInstructions=100000 */

/* cut edge: 0x18009b237 -> 0x18014d3db */
/* cut edge: 0x18009b258 -> 0x18014d3db */
/* cut edge: 0x18009b26e -> 0x18014d3db */
/* cut edge: 0x18009b2d9 -> 0x18009eb43 */
/* cut edge: 0x18009b2fd -> 0x18009eb79 */
/* cut edge: 0x18009b359 -> 0x18009ebca */
/* cut edge: 0x18009b369 -> 0x18009ec03 */
/* cut edge: 0x18009b43f -> 0x18014d3db */
/* cut edge: 0x18009b539 -> 0x18014d3db */
/* cut edge: 0x18009b54f -> 0x18014d3db */
/* cut edge: 0x18009b5c7 -> 0x18009ec39 */
/* cut edge: 0x18009b5eb -> 0x18009ec6f */
/* cut edge: 0x18009b644 -> 0x18009ecc0 */
/* cut edge: 0x18009b654 -> 0x18009ecf9 */
/* cut edge: 0x18009b72a -> 0x18014d3db */
/* cut edge: 0x18009b81f -> 0x18014d3db */
/* cut edge: 0x18009b835 -> 0x18014d3db */
/* cut edge: 0x18009b8a5 -> 0x18009ed2f */
/* cut edge: 0x18009b8c9 -> 0x18009ed65 */
/* cut edge: 0x18009b922 -> 0x18009edb6 */
/* cut edge: 0x18009b932 -> 0x18009edef */
/* cut edge: 0x18009ba05 -> 0x18014d3db */
/* cut edge: 0x18009bafc -> 0x18014d3db */
/* cut edge: 0x18009bb12 -> 0x18014d3db */
/* cut edge: 0x18009bb7f -> 0x18009ee25 */
/* cut edge: 0x18009bba8 -> 0x18009ee5b */
/* cut edge: 0x18009bbfc -> 0x18009eeac */
/* cut edge: 0x18009bc0c -> 0x18009eee5 */
/* cut edge: 0x18009bce5 -> 0x18014d3db */
/* cut edge: 0x18009bdda -> 0x1800eccee */
/* cut edge: 0x18009bdee -> 0x1801253b0 */
/* cut edge: 0x18009bdfd -> 0x18014d3db */
/* cut edge: 0x18009be13 -> 0x18014d3db */
/* cut edge: 0x18009be8e -> 0x18009fc52 */
/* cut edge: 0x18009beb2 -> 0x18009fc85 */
/* cut edge: 0x18009bf13 -> 0x18009fcd6 */
/* cut edge: 0x18009bf23 -> 0x18009fd0f */
/* cut edge: 0x18009bffb -> 0x18014d3db */
/* cut edge: 0x18009c0f0 -> 0x18014d3db */
/* cut edge: 0x18009c106 -> 0x18014d3db */
/* cut edge: 0x18009c11c -> 0x18009c2f0 */
/* cut edge: 0x18009c11c -> 0x18009c176 */
/* target 0x18009c176 original byte 0x48 replaced by RET */
/* target 0x18009c2f0 original byte 0x48 replaced by RET */
/* target 0x18009eb43 original byte 0x48 replaced by RET */
/* target 0x18009eb79 original byte 0x48 replaced by RET */
/* target 0x18009ebca original byte 0x48 replaced by RET */
/* target 0x18009ec03 original byte 0x48 replaced by RET */
/* target 0x18009ec39 original byte 0x48 replaced by RET */
/* target 0x18009ec6f original byte 0x48 replaced by RET */
/* target 0x18009ecc0 original byte 0x48 replaced by RET */
/* target 0x18009ecf9 original byte 0x4c replaced by RET */
/* target 0x18009ed2f original byte 0x48 replaced by RET */
/* target 0x18009ed65 original byte 0x48 replaced by RET */
/* target 0x18009edb6 original byte 0x48 replaced by RET */
/* target 0x18009edef original byte 0x48 replaced by RET */
/* target 0x18009ee25 original byte 0x48 replaced by RET */
/* target 0x18009ee5b original byte 0x48 replaced by RET */
/* target 0x18009eeac original byte 0x48 replaced by RET */
/* target 0x18009eee5 original byte 0x4c replaced by RET */
/* target 0x18009fc52 original byte 0x48 replaced by RET */
/* target 0x18009fc85 original byte 0x48 replaced by RET */
/* target 0x18009fcd6 original byte 0x48 replaced by RET */
/* target 0x18009fd0f original byte 0x48 replaced by RET */
/* target 0x1800eccee original byte 0x48 replaced by RET */
/* target 0x1801253b0 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00124_18009b237(void)

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
  code *pcVar11;
  longlong lVar12;
  code *unaff_RDI;
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
  
  (*unaff_RDI)(in_stack_00000048);
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar4 == '\0') {
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
      lVar12 = DAT_1804b52e8;
      uVar9 = DAT_1804b5208;
      if ((char)pcVar4 == '\0') {
        if (DAT_1804ace38 == 0) {
          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return pcVar4;
          }
          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000008aa,1);
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
                                     (in_stack_00000048,&stack0x00003b98,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar7;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
          uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar12,uVar9,uVar5);
          DAT_1804ace38 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ace38,in_stack_000000b0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x29e5);
        (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000060 % 0xd86c0 ^ in_stack_000000c0 ^
                           0xb646e3fea5729666);
        (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar4 == '\0') {
          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)pcVar4 == '\0') {
            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
            lVar12 = DAT_1804b52e8;
            uVar9 = DAT_1804b5208;
            if ((char)pcVar4 == '\0') {
              if (DAT_1804ace40 == 0) {
                pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return pcVar4;
                }
                uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x000008a8,1);
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
                                           (in_stack_00000048,&stack0x00003b88,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar7;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000090);
                (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
                uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar12,uVar9,uVar5);
                DAT_1804ace40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ace40,in_stack_000000b0);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x84d);
              (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000060 % 0xedf1a ^ in_stack_000000c0 ^
                                 0xf476f356d5dacb30);
              (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)pcVar4 == '\0') {
                pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)pcVar4 == '\0') {
                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                  lVar12 = DAT_1804b52e8;
                  uVar9 = DAT_1804b5208;
                  if ((char)pcVar4 == '\0') {
                    if (DAT_1804ace48 == 0) {
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b52e8 == 0) {
                        return pcVar4;
                      }
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x000008a6,1);
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
                                                 (in_stack_00000048,&stack0x00003b78,5);
                      if (DAT_1804b4d40 == 0) {
                        return pcVar7;
                      }
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,in_stack_00000090);
                      (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
                      uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar12,uVar9,uVar5);
                      DAT_1804ace48 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ace48,in_stack_000000b0);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4b6c)
                    ;
                    (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)in_stack_00000060 % 0xeaf09 ^ in_stack_000000c0 ^
                                       0xf58f2fde844752b4);
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
                        lVar12 = DAT_1804b52e8;
                        uVar9 = DAT_1804b5208;
                        if ((char)pcVar4 == '\0') {
                          if (DAT_1804ace50 == 0) {
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b52e8 == 0) {
                              return pcVar4;
                            }
                            pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                       (in_stack_00000048,&stack0x000008a4,1);
                            lVar2 = DAT_1804b4d38;
                            lVar1 = DAT_1804b4d30;
                            pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b4d30 == 0) {
                              return pcVar7;
                            }
                            if (DAT_1804b4d38 == 0) {
                              return pcVar7;
                            }
                            pcVar11 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                        (in_stack_00000048,&stack0x00003b68,5);
                            if (DAT_1804b4d40 == 0) {
                              return pcVar11;
                            }
                            uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,in_stack_00000090);
                            (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar11,uVar5);
                            uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar12,uVar9,
                                              pcVar7);
                            DAT_1804ace50 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ace50,in_stack_000000b0);
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x3db);
                          (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             (longlong)in_stack_00000060 % 0xbb31b ^
                                             in_stack_000000c0 ^ 0xd50419af025b4233);
                          (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
                          lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                             (in_stack_00000048,uVar9,in_stack_000000d8);
                          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                          if ((char)pcVar4 == '\0') {
                            if ((longlong)in_stack_000000c0 < 1) {
                              return pcVar4;
                            }
                            if (in_stack_000000f8 == 0) {
                              return pcVar4;
                            }
                            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)pcVar4 == '\0') {
                              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                              lVar1 = DAT_1804b52e8;
                              uVar9 = DAT_1804b5208;
                              if ((char)pcVar4 == '\0') {
                                if (DAT_1804ace58 == 0) {
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b52e8 == 0) {
                                    return (code *)0x0;
                                  }
                                  uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x000008a2,1);
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
                                                             (in_stack_00000048,&stack0x00003b58,5);
                                  if (DAT_1804b4d40 == 0) {
                                    return pcVar7;
                                  }
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,in_stack_00000090);
                                  (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                                  uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar9,
                                                    uVar5);
                                  DAT_1804ace58 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar9);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                                }
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804ace58,in_stack_000000b0
                                                  );
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x12b8);
                                (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   (longlong)((uVar10 ^ in_stack_00000060) - lVar12)
                                                   % 0x8fbd8 ^ in_stack_000000c0 ^
                                                   0xfd51f9e08e37af7e);
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
                                      if (DAT_1804ace60 == 0) {
                                        return (code *)0xf6c2778a2bdbd741;
                                      }
                                      return (code *)0xf6c2778a2bdbd741;
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
  return pcVar4;
}

