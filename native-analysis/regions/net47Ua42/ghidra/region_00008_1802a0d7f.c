/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=8 entry=1802a0d7f blocks=55 maxInstructions=50000 */

/* cut edge: 0x1802a0d7f -> 0x180333186 */
/* cut edge: 0x1802a0de7 -> 0x1802ad99a */
/* cut edge: 0x1802a0e13 -> 0x1802ad9d2 */
/* cut edge: 0x1802a0e7a -> 0x1802ada21 */
/* cut edge: 0x1802a0e92 -> 0x1802ada5b */
/* cut edge: 0x1802a0f8f -> 0x180333186 */
/* cut edge: 0x1802a1073 -> 0x180333186 */
/* cut edge: 0x1802a10a6 -> 0x180333186 */
/* cut edge: 0x1802a10bc -> 0x180333186 */
/* cut edge: 0x1802a112b -> 0x1802ada92 */
/* cut edge: 0x1802a115c -> 0x1802adacd */
/* cut edge: 0x1802a11be -> 0x1802adb1e */
/* cut edge: 0x1802a11ce -> 0x1802adb57 */
/* cut edge: 0x1802a12c6 -> 0x180333186 */
/* cut edge: 0x1802a13aa -> 0x180333186 */
/* cut edge: 0x1802a13dd -> 0x180333186 */
/* cut edge: 0x1802a1410 -> 0x180333186 */
/* cut edge: 0x1802a1453 -> 0x180333186 */
/* cut edge: 0x1802a1486 -> 0x180333186 */
/* cut edge: 0x1802a149c -> 0x180333186 */
/* cut edge: 0x1802a14fc -> 0x1802adb8d */
/* cut edge: 0x1802a1520 -> 0x1802adbc0 */
/* cut edge: 0x1802a1587 -> 0x1802adc11 */
/* cut edge: 0x1802a159c -> 0x1802adc4a */
/* cut edge: 0x1802a1684 -> 0x180333186 */
/* cut edge: 0x1802a1768 -> 0x180333186 */
/* cut edge: 0x1802a177e -> 0x180333186 */
/* cut edge: 0x1802a17e8 -> 0x1802adc81 */
/* cut edge: 0x1802a180c -> 0x1802adcb7 */
/* cut edge: 0x1802a186e -> 0x1802add05 */
/* cut edge: 0x1802a188e -> 0x1802add3e */
/* cut edge: 0x1802a19a2 -> 0x180333186 */
/* cut edge: 0x1802a1a98 -> 0x180333186 */
/* cut edge: 0x1802a1acb -> 0x1802ac197 */
/* cut edge: 0x1802a1aea -> 0x180333186 */
/* cut edge: 0x1802a1b0e -> 0x180333186 */
/* cut edge: 0x1802a1b31 -> 0x180333186 */
/* cut edge: 0x1802a1b91 -> 0x1802add75 */
/* cut edge: 0x1802a1bba -> 0x1802addad */
/* cut edge: 0x1802a1c16 -> 0x1802addfb */
/* cut edge: 0x1802a1c2e -> 0x1802ade34 */
/* cut edge: 0x1802a1d45 -> 0x180333186 */
/* cut edge: 0x1802a1d45 -> 0x1802a1e85 */
/* target 0x1802a1e85 original byte 0x48 replaced by RET */
/* target 0x1802ac197 original byte 0x48 replaced by RET */
/* target 0x1802ad99a original byte 0x48 replaced by RET */
/* target 0x1802ad9d2 original byte 0x48 replaced by RET */
/* target 0x1802ada21 original byte 0x48 replaced by RET */
/* target 0x1802ada5b original byte 0x48 replaced by RET */
/* target 0x1802ada92 original byte 0x48 replaced by RET */
/* target 0x1802adacd original byte 0x48 replaced by RET */
/* target 0x1802adb1e original byte 0x48 replaced by RET */
/* target 0x1802adb57 original byte 0x4c replaced by RET */
/* target 0x1802adb8d original byte 0x48 replaced by RET */
/* target 0x1802adbc0 original byte 0x48 replaced by RET */
/* target 0x1802adc11 original byte 0x48 replaced by RET */
/* target 0x1802adc4a original byte 0x48 replaced by RET */
/* target 0x1802adc81 original byte 0x48 replaced by RET */
/* target 0x1802adcb7 original byte 0x48 replaced by RET */
/* target 0x1802add05 original byte 0x48 replaced by RET */
/* target 0x1802add3e original byte 0x48 replaced by RET */
/* target 0x1802add75 original byte 0x48 replaced by RET */
/* target 0x1802addad original byte 0x48 replaced by RET */
/* target 0x1802addfb original byte 0x48 replaced by RET */
/* target 0x1802ade34 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00008_1802a0d7f(void)

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
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 unaff_R14;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  longlong in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000006f0;
  
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
  lVar5 = DAT_1804b5a00;
  uVar10 = DAT_1804b59f0;
  if (cVar6 == '\0') {
    if (DAT_1804b1c20 == 0) {
      pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
      if (DAT_1804b5a00 == 0) {
        return;
      }
      uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000244,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00003420,0x16);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
      (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar8,uVar9);
      uVar10 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar10,uVar7);
      DAT_1804b1c20 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar10);
      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar10);
    }
    uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,DAT_1804b1c20,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4b59);
    (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                       in_stack_00000060 % 0x215f0 ^ in_stack_000000b0 ^ 0x77f9c3b640924087);
    (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
    (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar10,in_stack_000000a8,uVar7);
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if (cVar6 == '\0') {
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040);
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
          lVar5 = DAT_1804b5a00;
          uVar7 = DAT_1804b59f0;
          if (cVar6 == '\0') {
            if (DAT_1804b1c28 == 0) {
              pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
              if (DAT_1804b5a00 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                (in_stack_00000040,&stack0x00000242,1);
              lVar4 = DAT_1804b4d38;
              lVar3 = DAT_1804b4d30;
              pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                (in_stack_00000040,&stack0x000033f0,0x16);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,in_stack_00000080);
              (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar11);
              uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
              DAT_1804b1c28 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
            }
            uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,DAT_1804b1c28,in_stack_000000a0);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x30dd);
            (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00000060 % 0xce03a ^ in_stack_000000b0 ^ 0x4413d71a13dcbfc2)
            ;
            (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
            cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if (cVar6 == '\0') {
              uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,unaff_R14,in_stack_00000048,uVar7);
              cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar6 == '\0') {
                uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,uVar10,in_stack_00000070,uVar7);
                cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                if (cVar6 == '\0') {
                  uVar7 = (**(code **)(*in_stack_00000040 + 0x518))
                                    (in_stack_00000040,&stack0x00000240,1);
                  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                  if (cVar6 == '\0') {
                    uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                       (in_stack_00000040,uVar10,in_stack_00000070,uVar7);
                    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                      if (cVar6 == '\0') {
                        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                        uVar7 = DAT_1804b59f0;
                        lVar5 = DAT_1804b59e8;
                        if (cVar6 == '\0') {
                          if (DAT_1804b1c30 == 0) {
                            pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                            if (DAT_1804b59e8 == 0) {
                              return;
                            }
                            uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                              (in_stack_00000040,&stack0x0000023e,1);
                            lVar4 = DAT_1804b4d38;
                            lVar3 = DAT_1804b4d30;
                            pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                            if (DAT_1804b4d30 == 0) {
                              return;
                            }
                            if (DAT_1804b4d38 == 0) {
                              return;
                            }
                            uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                              (in_stack_00000040,&stack0x000031f0,0xd);
                            if (DAT_1804b4d40 == 0) {
                              return;
                            }
                            uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                               (in_stack_00000040,in_stack_00000080);
                            (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar11);
                            uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8)
                            ;
                            DAT_1804b1c30 =
                                 (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                            (in_stack_00000040,DAT_1804b1c30,in_stack_000000a0);
                          uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                            (in_stack_00000040,2,in_stack_00000090,0);
                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                             in_stack_00000060 % 0x2cf30 ^ 0xdf3a88030c103146);
                          (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                             in_stack_000000b0);
                          (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
                          uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                            (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
                          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                            if (cVar6 == '\0') {
                              cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                              uVar8 = DAT_1804b59f0;
                              lVar5 = DAT_1804b59e8;
                              if (cVar6 == '\0') {
                                if (DAT_1804b1c38 == 0) {
                                  pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                                  if (DAT_1804b59e8 == 0) {
                                    return;
                                  }
                                  uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                                    (in_stack_00000040,&stack0x0000023c,1);
                                  lVar4 = DAT_1804b4d38;
                                  lVar3 = DAT_1804b4d30;
                                  pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                                  if (DAT_1804b4d30 == 0) {
                                    return;
                                  }
                                  if (DAT_1804b4d38 == 0) {
                                    return;
                                  }
                                  uVar11 = (**(code **)(*in_stack_00000040 + 0x518))
                                                     (in_stack_00000040,&stack0x00003d50,0x28);
                                  if (DAT_1804b4d40 == 0) {
                                    return;
                                  }
                                  uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                                                     (in_stack_00000040,in_stack_00000080);
                                  (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar11,uVar12);
                                  uVar8 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar8,
                                                    uVar9);
                                  DAT_1804b1c38 =
                                       (**(code **)(*in_stack_00000040 + 0xa8))
                                                 (in_stack_00000040,uVar8);
                                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar8);
                                }
                                uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                                  (in_stack_00000040,DAT_1804b1c38,in_stack_000000a0
                                                  );
                                uVar9 = (**(code **)(*in_stack_00000040 + 0x560))
                                                  (in_stack_00000040,3,in_stack_00000090,0);
                                (**(code **)(*in_stack_00000040 + 0x570))
                                          (in_stack_00000040,uVar9,0,uVar7);
                                pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                                  (in_stack_00000040,in_stack_00000088,
                                                   in_stack_00000098,
                                                   in_stack_00000060 % 0xe0bf7 ^ 0xdf1657d54bacc4cb)
                                ;
                                (*pcVar1)(in_stack_00000040,uVar9,1,uVar7);
                                pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                                  (in_stack_00000040,in_stack_00000088,
                                                   in_stack_00000098,in_stack_000000b0);
                                (*pcVar1)(in_stack_00000040,uVar9,2,uVar7);
                                uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                                  (in_stack_00000040,uVar8,in_stack_000000a8,uVar9);
                                cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040)
                                ;
                                if (cVar6 == '\0') {
                                  uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                                     (in_stack_00000040,uVar10,in_stack_00000070,
                                                      uVar7);
                                  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))
                                                    (in_stack_00000040);
                                  if (cVar6 == '\0') {
                                    if (DAT_1804b5040 == 0) {
                                      return;
                                    }
                                    uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                                       (in_stack_00000040,uVar10);
                                    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))
                                                      (in_stack_00000040);
                                    if (cVar6 == '\0') {
                                      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                                      if (cVar6 == '\0') {
                                        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                                        uVar7 = DAT_1804b59f0;
                                        lVar5 = DAT_1804b59e8;
                                        if (cVar6 == '\0') {
                                          if (DAT_1804b1c40 == 0) {
                                            pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                                            if (DAT_1804b59e8 == 0) {
                                              return;
                                            }
                                            uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                                              (in_stack_00000040,&stack0x0000023a,1)
                                            ;
                                            lVar4 = DAT_1804b4d38;
                                            lVar3 = DAT_1804b4d30;
                                            pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                                            if (DAT_1804b4d30 == 0) {
                                              return;
                                            }
                                            if (DAT_1804b4d38 == 0) {
                                              return;
                                            }
                                            uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                                              (in_stack_00000040,&stack0x000039f0,
                                                               0x2a);
                                            if (DAT_1804b4d40 == 0) {
                                              return;
                                            }
                                            uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                                               (in_stack_00000040,in_stack_00000080)
                                            ;
                                            (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar11);
                                            uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,
                                                              lVar5,uVar7,uVar8);
                                            DAT_1804b1c40 =
                                                 (**(code **)(*in_stack_00000040 + 0xa8))
                                                           (in_stack_00000040,uVar7);
                                            (**(code **)(*in_stack_00000040 + 0xb8))
                                                      (in_stack_00000040,uVar7);
                                          }
                                          uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                                            (in_stack_00000040,DAT_1804b1c40,
                                                             in_stack_000000a0);
                                          uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                                            (in_stack_00000040,5,in_stack_00000090,0
                                                            );
                                          (**(code **)(*in_stack_00000040 + 0x570))
                                                    (in_stack_00000040,uVar8,0,in_stack_00000068);
                                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                                          uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                                            (in_stack_00000040,in_stack_00000088,
                                                             in_stack_00000098,in_stack_000006f0);
                                          (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
                                          (**(code **)(*in_stack_00000040 + 0x570))
                                                    (in_stack_00000040,uVar8,2,uVar10);
                                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                                          uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                                             (in_stack_00000040,in_stack_00000088,
                                                              in_stack_00000098,
                                                              in_stack_00000060 % 0xd0f0e ^
                                                              0xdf3f0037bc127512);
                                          (*pcVar1)(in_stack_00000040,uVar8,3,uVar10);
                                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                                          uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                                             (in_stack_00000040,in_stack_00000088,
                                                              in_stack_00000098,in_stack_000000b0);
                                          (*pcVar1)(in_stack_00000040,uVar8,4,uVar10);
                                          (**(code **)(*in_stack_00000040 + 0x110))
                                                    (in_stack_00000040,uVar7,in_stack_000000a8,uVar8
                                                    );
                                          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))
                                                            (in_stack_00000040);
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
        }
      }
    }
  }
  return;
}

