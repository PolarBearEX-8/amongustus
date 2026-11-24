/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=235 entry=18010a130 blocks=52 maxInstructions=100000 */

/* cut edge: 0x18010a1b3 -> 0x18014d3db */
/* cut edge: 0x18010a2a8 -> 0x18014d3db */
/* cut edge: 0x18010a2be -> 0x18014d3db */
/* cut edge: 0x18010a32b -> 0x180110839 */
/* cut edge: 0x18010a35a -> 0x180110874 */
/* cut edge: 0x18010a3b8 -> 0x1801108c5 */
/* cut edge: 0x18010a3c8 -> 0x1801108fe */
/* cut edge: 0x18010a4a9 -> 0x18014d3db */
/* cut edge: 0x18010a59e -> 0x18014d3db */
/* cut edge: 0x18010a5b4 -> 0x18014d3db */
/* cut edge: 0x18010a61f -> 0x180110a2f */
/* cut edge: 0x18010a64b -> 0x180110a6a */
/* cut edge: 0x18010a6a4 -> 0x180110abb */
/* cut edge: 0x18010a6b7 -> 0x180110af4 */
/* cut edge: 0x18010a793 -> 0x18014d3db */
/* cut edge: 0x18010a888 -> 0x18014d3db */
/* cut edge: 0x18010a89e -> 0x18014d3db */
/* cut edge: 0x18010a90c -> 0x180110c29 */
/* cut edge: 0x18010a93d -> 0x180110c64 */
/* cut edge: 0x18010a996 -> 0x180110cb5 */
/* cut edge: 0x18010a9a9 -> 0x180110cee */
/* cut edge: 0x18010aa8a -> 0x18014d3db */
/* cut edge: 0x18010ab81 -> 0x18010c3ac */
/* cut edge: 0x18010ab9a -> 0x18014d3db */
/* cut edge: 0x18010abb0 -> 0x18014d3db */
/* cut edge: 0x18010ac18 -> 0x18010f41d */
/* cut edge: 0x18010ac3c -> 0x18010f450 */
/* cut edge: 0x18010aca0 -> 0x18010f4a1 */
/* cut edge: 0x18010acb0 -> 0x18010f4da */
/* cut edge: 0x18010ad8b -> 0x18014d3db */
/* cut edge: 0x18010ae80 -> 0x18014d3db */
/* cut edge: 0x18010ae96 -> 0x18014d3db */
/* cut edge: 0x18010af03 -> 0x18010f511 */
/* cut edge: 0x18010af27 -> 0x18010f544 */
/* cut edge: 0x18010af88 -> 0x18010f592 */
/* cut edge: 0x18010afa0 -> 0x18010f5cb */
/* cut edge: 0x18010b07d -> 0x18014d3db */
/* cut edge: 0x18010b07d -> 0x18010b177 */
/* target 0x18010b177 original byte 0x48 replaced by RET */
/* target 0x18010c3ac original byte 0x48 replaced by RET */
/* target 0x18010f41d original byte 0x48 replaced by RET */
/* target 0x18010f450 original byte 0x48 replaced by RET */
/* target 0x18010f4a1 original byte 0x48 replaced by RET */
/* target 0x18010f4da original byte 0x48 replaced by RET */
/* target 0x18010f511 original byte 0x48 replaced by RET */
/* target 0x18010f544 original byte 0x48 replaced by RET */
/* target 0x18010f592 original byte 0x48 replaced by RET */
/* target 0x18010f5cb original byte 0x48 replaced by RET */
/* target 0x180110839 original byte 0x48 replaced by RET */
/* target 0x180110874 original byte 0x48 replaced by RET */
/* target 0x1801108c5 original byte 0x48 replaced by RET */
/* target 0x1801108fe original byte 0x48 replaced by RET */
/* target 0x180110a2f original byte 0x48 replaced by RET */
/* target 0x180110a6a original byte 0x48 replaced by RET */
/* target 0x180110abb original byte 0x48 replaced by RET */
/* target 0x180110af4 original byte 0x48 replaced by RET */
/* target 0x180110c29 original byte 0x48 replaced by RET */
/* target 0x180110c64 original byte 0x48 replaced by RET */
/* target 0x180110cb5 original byte 0x48 replaced by RET */
/* target 0x180110cee original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00235_18010a130(void)

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
  ulonglong uVar11;
  longlong lVar12;
  code *unaff_R14;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  ulonglong in_stack_00000060;
  undefined8 in_stack_00000070;
  code *in_stack_00000078;
  undefined8 in_stack_00000080;
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
  
  (*unaff_R14)(in_stack_00000048,in_stack_00000090);
  (*in_stack_00000088)(in_stack_00000048);
  uVar7 = (*in_stack_00000078)
                    (in_stack_00000048,in_stack_00000090,in_stack_00000080,in_stack_00000058,
                     in_stack_00000070);
  DAT_1804ae7e8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804ae7e8,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7d67);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar12 = DAT_1804b52e8;
      uVar7 = DAT_1804b5208;
      if (cVar6 == '\0') {
        if (DAT_1804ae7f0 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000022e,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000f48,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar7,uVar8);
          DAT_1804ae7f0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae7f0,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6a0a);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000060 % 0x377ae ^ in_stack_000000c0 ^
                           0xbb67b07e31b1b515);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            lVar12 = DAT_1804b52e8;
            uVar7 = DAT_1804b5208;
            if (cVar6 == '\0') {
              if (DAT_1804ae7f8 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x0000022c,1);
                lVar4 = DAT_1804b4d38;
                lVar3 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000f38,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000090);
                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar7,uVar8);
                DAT_1804ae7f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae7f8,in_stack_000000b0);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3e82);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000060 % 0xc3a08 ^ in_stack_000000c0 ^
                                 0xb19ea21a69251d15);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                 (in_stack_00000048,uVar7,in_stack_000000d8);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  lVar12 = DAT_1804b52e8;
                  uVar7 = DAT_1804b5208;
                  if (cVar6 == '\0') {
                    if (DAT_1804ae800 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b52e8 == 0) {
                        return;
                      }
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x0000022a,1);
                      lVar4 = DAT_1804b4d38;
                      lVar3 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00000f28,5);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,in_stack_00000090);
                      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar7,uVar8);
                      DAT_1804ae800 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ae800,in_stack_000000b0);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x385);
                    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)in_stack_00000060 % 0x5ee1f ^ in_stack_000000c0 ^
                                       0xc21a8883ca951c6f);
                    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                    lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                       (in_stack_00000048,uVar7,in_stack_000000d8);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar6 == '\0') {
                      lVar12 = lVar12 + (uVar11 ^ in_stack_00000060);
                      if (in_stack_000000f8 == 0) {
                        return;
                      }
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar6 == '\0') {
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                        lVar3 = DAT_1804b52e8;
                        uVar7 = DAT_1804b5208;
                        if (cVar6 == '\0') {
                          if (DAT_1804ae808 == 0) {
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b52e8 == 0) {
                              return;
                            }
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x00000228,1);
                            lVar5 = DAT_1804b4d38;
                            lVar4 = DAT_1804b4d30;
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b4d30 == 0) {
                              return;
                            }
                            if (DAT_1804b4d38 == 0) {
                              return;
                            }
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x00000f18,5);
                            if (DAT_1804b4d40 == 0) {
                              return;
                            }
                            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,in_stack_00000090);
                            (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
                            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar7,uVar8)
                            ;
                            DAT_1804ae808 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ae808,in_stack_000000b0);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x650a);
                          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             lVar12 % 0x14244 ^ in_stack_000000c0 ^
                                             0xb03cbee0e3f96ed8);
                          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar7,in_stack_000000d8);
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar6 == '\0') {
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                              lVar3 = DAT_1804b52e8;
                              uVar7 = DAT_1804b5208;
                              if (cVar6 == '\0') {
                                if (DAT_1804ae810 == 0) {
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b52e8 == 0) {
                                    return;
                                  }
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x00000226,1);
                                  lVar5 = DAT_1804b4d38;
                                  lVar4 = DAT_1804b4d30;
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b4d30 == 0) {
                                    return;
                                  }
                                  if (DAT_1804b4d38 == 0) {
                                    return;
                                  }
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x00000f08,5);
                                  if (DAT_1804b4d40 == 0) {
                                    return;
                                  }
                                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,in_stack_00000090);
                                  (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
                                  uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar7,
                                                    uVar8);
                                  DAT_1804ae810 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar7);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                                }
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804ae810,in_stack_000000b0
                                                  );
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x5771);
                                (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   lVar12 % 0x196de ^ in_stack_000000c0 ^
                                                   0x901c4f8099069589);
                                (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar7,in_stack_000000d8);
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
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
  return;
}

