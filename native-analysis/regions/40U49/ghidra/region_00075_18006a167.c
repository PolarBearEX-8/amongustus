/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=75 entry=18006a167 blocks=49 maxInstructions=100000 */

/* cut edge: 0x18006a167 -> 0x180074c0f */
/* cut edge: 0x18006a177 -> 0x180074c48 */
/* cut edge: 0x18006a250 -> 0x18014d3db */
/* cut edge: 0x18006a347 -> 0x18014d3db */
/* cut edge: 0x18006a35d -> 0x18014d3db */
/* cut edge: 0x18006a3d7 -> 0x180074c80 */
/* cut edge: 0x18006a3fb -> 0x180074cb3 */
/* cut edge: 0x18006a45f -> 0x180074d04 */
/* cut edge: 0x18006a46f -> 0x180074d3e */
/* cut edge: 0x18006a54e -> 0x18014d3db */
/* cut edge: 0x18006a643 -> 0x18014d3db */
/* cut edge: 0x18006a659 -> 0x18014d3db */
/* cut edge: 0x18006a6c6 -> 0x180074d76 */
/* cut edge: 0x18006a6ea -> 0x180074da9 */
/* cut edge: 0x18006a74e -> 0x180074dfa */
/* cut edge: 0x18006a75e -> 0x180074e33 */
/* cut edge: 0x18006a83a -> 0x18014d3db */
/* cut edge: 0x18006a92f -> 0x18014d3db */
/* cut edge: 0x18006a945 -> 0x18014d3db */
/* cut edge: 0x18006a9b0 -> 0x180074e6b */
/* cut edge: 0x18006a9d9 -> 0x180074ea1 */
/* cut edge: 0x18006aa35 -> 0x180074ef2 */
/* cut edge: 0x18006aa4d -> 0x180074f2b */
/* cut edge: 0x18006ab2d -> 0x18014d3db */
/* cut edge: 0x18006ac22 -> 0x180125436 */
/* cut edge: 0x18006ac34 -> 0x18014d3db */
/* cut edge: 0x18006ac4a -> 0x18014d3db */
/* cut edge: 0x18006acb3 -> 0x180073953 */
/* cut edge: 0x18006acd7 -> 0x180073986 */
/* cut edge: 0x18006ad38 -> 0x1800739d4 */
/* cut edge: 0x18006ad50 -> 0x180073a0d */
/* cut edge: 0x18006ae2c -> 0x18014d3db */
/* cut edge: 0x18006af21 -> 0x18014d3db */
/* cut edge: 0x18006af37 -> 0x18014d3db */
/* cut edge: 0x18006af4d -> 0x18006b129 */
/* cut edge: 0x18006afa5 -> 0x180073b39 */
/* cut edge: 0x18006afcc -> 0x180073b6f */
/* cut edge: 0x18006b025 -> 0x180073bbd */
/* cut edge: 0x18006b047 -> 0x180073bf6 */
/* cut edge: 0x18006b09f -> 0x18006b129 */
/* target 0x18006b129 original byte 0x48 replaced by RET */
/* target 0x180073953 original byte 0x48 replaced by RET */
/* target 0x180073986 original byte 0x48 replaced by RET */
/* target 0x1800739d4 original byte 0x48 replaced by RET */
/* target 0x180073a0d original byte 0x48 replaced by RET */
/* target 0x180073b39 original byte 0x48 replaced by RET */
/* target 0x180073b6f original byte 0x48 replaced by RET */
/* target 0x180073bbd original byte 0x48 replaced by RET */
/* target 0x180073bf6 original byte 0x48 replaced by RET */
/* target 0x180074c0f original byte 0x48 replaced by RET */
/* target 0x180074c48 original byte 0x48 replaced by RET */
/* target 0x180074c80 original byte 0x48 replaced by RET */
/* target 0x180074cb3 original byte 0x48 replaced by RET */
/* target 0x180074d04 original byte 0x48 replaced by RET */
/* target 0x180074d3e original byte 0x48 replaced by RET */
/* target 0x180074d76 original byte 0x48 replaced by RET */
/* target 0x180074da9 original byte 0x48 replaced by RET */
/* target 0x180074dfa original byte 0x48 replaced by RET */
/* target 0x180074e33 original byte 0x48 replaced by RET */
/* target 0x180074e6b original byte 0x48 replaced by RET */
/* target 0x180074ea1 original byte 0x48 replaced by RET */
/* target 0x180074ef2 original byte 0x48 replaced by RET */
/* target 0x180074f2b original byte 0x48 replaced by RET */
/* target 0x180125436 original byte 0x4c replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00075_18006a167(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  code *in_RAX;
  code *pcVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  code *pcVar8;
  code *pcVar9;
  ulonglong uVar10;
  longlong lVar11;
  longlong unaff_RDI;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  code *in_stack_00000078;
  code *in_stack_00000088;
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
  
  if (DAT_1804b4d38 == 0) {
    return in_RAX;
  }
  pcVar4 = (code *)(**(code **)(unaff_RDI + 0x518))(in_stack_00000048,&stack0x00001758,5);
  if (DAT_1804b4d40 == 0) {
    return pcVar4;
  }
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
  (*in_stack_00000088)(in_stack_00000048);
  uVar5 = (*in_stack_00000078)
                    (in_stack_00000048,in_stack_00000090,in_stack_00000068,in_stack_00000060,
                     in_stack_00000070);
  DAT_1804ae370 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804ae370,in_stack_000000b0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4a69);
  (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_000000a8,uVar6)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar4 == '\0') {
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
      lVar11 = DAT_1804b52e8;
      uVar5 = DAT_1804b5208;
      if ((char)pcVar4 == '\0') {
        if (DAT_1804ae378 == 0) {
          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return (code *)0x0;
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000034e,1);
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
                                     (in_stack_00000048,&stack0x00001748,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar9;
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar7);
          uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar5,uVar6);
          DAT_1804ae378 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae378,in_stack_000000b0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x108a);
        (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000058 % 0x7d3b3 ^ in_stack_000000c0 ^
                           0x51c15044d3bd437c);
        (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar4 == '\0') {
          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)pcVar4 == '\0') {
            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
            lVar11 = DAT_1804b52e8;
            uVar5 = DAT_1804b5208;
            if ((char)pcVar4 == '\0') {
              if (DAT_1804ae380 == 0) {
                pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return (code *)0x0;
                }
                uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x0000034c,1);
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
                                           (in_stack_00000048,&stack0x00001738,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar9;
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000090);
                (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar7);
                uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar5,uVar6);
                DAT_1804ae380 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae380,in_stack_000000b0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4829);
              (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000058 % 0x4a94c ^ in_stack_000000c0 ^
                                 0x7ef6af535b2d2e58);
              (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                 (in_stack_00000048,uVar5,in_stack_000000d8);
              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)pcVar4 == '\0') {
                pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)pcVar4 == '\0') {
                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                  lVar11 = DAT_1804b52e8;
                  uVar5 = DAT_1804b5208;
                  if ((char)pcVar4 == '\0') {
                    if (DAT_1804ae388 == 0) {
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b52e8 == 0) {
                        return pcVar4;
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x0000034a,1);
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
                                                 (in_stack_00000048,&stack0x00001728,5);
                      if (DAT_1804b4d40 == 0) {
                        return pcVar9;
                      }
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,in_stack_00000090);
                      (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar7);
                      uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar5,uVar6);
                      DAT_1804ae388 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                    }
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ae388,in_stack_000000b0);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5e98)
                    ;
                    (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)in_stack_00000058 % 0x1b9fc ^ in_stack_000000c0 ^
                                       0x707b88c408b1abc4);
                    (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                    lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                       (in_stack_00000048,uVar5,in_stack_000000d8);
                    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)pcVar4 == '\0') {
                      if (in_stack_000000f8 == 0) {
                        return pcVar4;
                      }
                      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)pcVar4 == '\0') {
                        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                        lVar1 = DAT_1804b52e8;
                        uVar5 = DAT_1804b5208;
                        if ((char)pcVar4 == '\0') {
                          if (DAT_1804ae390 == 0) {
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b52e8 == 0) {
                              return (code *)0x0;
                            }
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x00000348,1);
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
                                                       (in_stack_00000048,&stack0x00001718,5);
                            if (DAT_1804b4d40 == 0) {
                              return pcVar9;
                            }
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,in_stack_00000090);
                            (*pcVar8)(in_stack_00000048,lVar2,lVar3,pcVar9,uVar7);
                            uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar5,uVar6)
                            ;
                            DAT_1804ae390 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                          }
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ae390,in_stack_000000b0);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x1370);
                          (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             (longlong)((uVar10 ^ in_stack_00000058) - lVar11) %
                                             0x34463 ^ in_stack_000000c0 ^ 0x3368bb3ce4f5bf4a);
                          (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar5,in_stack_000000d8);
                          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                          if ((char)pcVar4 == '\0') {
                            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)pcVar4 == '\0') {
                              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                              lVar11 = DAT_1804b52e8;
                              uVar5 = DAT_1804b5208;
                              if ((char)pcVar4 == '\0') {
                                pcVar4 = (code *)0x15b87d819e42d41d;
                                if (DAT_1804ae398 == 0) {
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b52e8 == 0) {
                                    return pcVar4;
                                  }
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x00000346,1);
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
                                                             (in_stack_00000048,&stack0x00001708,5);
                                  if (DAT_1804b4d40 == 0) {
                                    return pcVar9;
                                  }
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,in_stack_00000090);
                                  (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar7);
                                  uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar5
                                                    ,uVar6);
                                  DAT_1804ae398 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar5);
                                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0xb8))
                                                             (in_stack_00000048,uVar5);
                                }
                                return pcVar4;
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

