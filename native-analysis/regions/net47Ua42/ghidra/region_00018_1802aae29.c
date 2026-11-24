/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=18 entry=1802aae29 blocks=54 maxInstructions=50000 */

/* cut edge: 0x1802aae36 -> 0x180333186 */
/* cut edge: 0x1802aae4c -> 0x180333186 */
/* cut edge: 0x1802aaeb7 -> 0x1802b5d4c */
/* cut edge: 0x1802aaee0 -> 0x1802b5d84 */
/* cut edge: 0x1802aaf39 -> 0x1802b5dd5 */
/* cut edge: 0x1802aaf49 -> 0x1802b5e0e */
/* cut edge: 0x1802ab01e -> 0x180333186 */
/* cut edge: 0x1802ab115 -> 0x180333186 */
/* cut edge: 0x1802ab12b -> 0x180333186 */
/* cut edge: 0x1802ab193 -> 0x1802b5e45 */
/* cut edge: 0x1802ab1bc -> 0x1802b5e7d */
/* cut edge: 0x1802ab218 -> 0x1802b5ece */
/* cut edge: 0x1802ab228 -> 0x1802b5f07 */
/* cut edge: 0x1802ab300 -> 0x180333186 */
/* cut edge: 0x1802ab3f7 -> 0x180333186 */
/* cut edge: 0x1802ab40d -> 0x180333186 */
/* cut edge: 0x1802ab478 -> 0x1802b5f3e */
/* cut edge: 0x1802ab4a1 -> 0x1802b5f76 */
/* cut edge: 0x1802ab500 -> 0x1802b5fc7 */
/* cut edge: 0x1802ab510 -> 0x1802b6000 */
/* cut edge: 0x1802ab5eb -> 0x180333186 */
/* cut edge: 0x1802ab6e0 -> 0x180333186 */
/* cut edge: 0x1802ab6f6 -> 0x180333186 */
/* cut edge: 0x1802ab761 -> 0x1802b6037 */
/* cut edge: 0x1802ab78d -> 0x1802b6072 */
/* cut edge: 0x1802ab7f1 -> 0x1802b60cc */
/* cut edge: 0x1802ab801 -> 0x1802b6105 */
/* cut edge: 0x1802ab8e0 -> 0x180333186 */
/* cut edge: 0x1802aba04 -> 0x18029b02c */
/* cut edge: 0x1802aba2d -> 0x18029ad3c */
/* cut edge: 0x1802aba32 -> 0x180333186 */
/* cut edge: 0x1802abb8b -> 0x180333186 */
/* cut edge: 0x1802abc84 -> 0x1802abef2 */
/* cut edge: 0x1802abc93 -> 0x1802dcca5 */
/* cut edge: 0x1802abca0 -> 0x180333186 */
/* cut edge: 0x1802abcb6 -> 0x1802abdf9 */
/* cut edge: 0x1802abcc6 -> 0x1802abdf9 */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x18029b02c original byte 0x83 replaced by RET */
/* target 0x1802abdf9 original byte 0x48 replaced by RET */
/* target 0x1802abef2 original byte 0x48 replaced by RET */
/* target 0x1802b5d4c original byte 0x48 replaced by RET */
/* target 0x1802b5d84 original byte 0x48 replaced by RET */
/* target 0x1802b5dd5 original byte 0x48 replaced by RET */
/* target 0x1802b5e0e original byte 0x48 replaced by RET */
/* target 0x1802b5e45 original byte 0x48 replaced by RET */
/* target 0x1802b5e7d original byte 0x48 replaced by RET */
/* target 0x1802b5ece original byte 0x48 replaced by RET */
/* target 0x1802b5f07 original byte 0x48 replaced by RET */
/* target 0x1802b5f3e original byte 0x48 replaced by RET */
/* target 0x1802b5f76 original byte 0x48 replaced by RET */
/* target 0x1802b5fc7 original byte 0x48 replaced by RET */
/* target 0x1802b6000 original byte 0x48 replaced by RET */
/* target 0x1802b6037 original byte 0x48 replaced by RET */
/* target 0x1802b6072 original byte 0x48 replaced by RET */
/* target 0x1802b60cc original byte 0x48 replaced by RET */
/* target 0x1802b6105 original byte 0x48 replaced by RET */
/* target 0x1802dcca5 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00018_1802aae29(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  code *pcVar4;
  longlong lVar5;
  longlong lVar6;
  char cVar7;
  int iVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  int unaff_ESI;
  longlong *in_stack_00000040;
  longlong in_stack_00000070;
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
  int in_stack_000000e0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00003130;
  
  if (unaff_ESI == in_stack_000000e0) {
    cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar7 == '\0') {
      cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
      lVar3 = DAT_1804b5a08;
      uVar12 = DAT_1804b59f0;
      if (cVar7 == '\0') {
        if (DAT_1804b11d8 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000004d8,1);
          lVar6 = DAT_1804b4d38;
          lVar5 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001fb8,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar11 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar5,lVar6,uVar10,uVar11);
          uVar12 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar3,uVar12,uVar9);
          DAT_1804b11d8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
        }
        uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b11d8,in_stack_000000a0);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                           (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x3ddd);
        (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                           (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                            in_stack_00000070 % 0x547cb ^ in_stack_000000b0 ^ 0x33f3d99dda82c8b4);
        (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
        uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar12,in_stack_000000a8,uVar9);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar12,in_stack_000000d0);
        cVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar7 == '\0') {
          cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar7 == '\0') {
            cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
            lVar3 = DAT_1804b5a08;
            uVar12 = DAT_1804b59f0;
            if (cVar7 == '\0') {
              if (DAT_1804b11e0 == 0) {
                pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x000004d6,1);
                lVar6 = DAT_1804b4d38;
                lVar5 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000040 + 0x518))
                                   (in_stack_00000040,&stack0x00001fa8,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,in_stack_00000080);
                (*pcVar2)(in_stack_00000040,lVar5,lVar6,uVar10,uVar11);
                uVar12 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar3,uVar12,uVar9);
                DAT_1804b11e0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
              }
              uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,DAT_1804b11e0,in_stack_000000a0);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                 (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6acd);
              (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                 (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                  in_stack_00000070 % 0x343d1 ^ in_stack_000000b0 ^
                                  0x3f6e30175bb1d715);
              (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
              uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,uVar12,in_stack_000000a8,uVar9);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar12,in_stack_000000d0);
              cVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar7 == '\0') {
                cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
                if (cVar7 == '\0') {
                  cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
                  lVar3 = DAT_1804b5a08;
                  uVar12 = DAT_1804b59f0;
                  if (cVar7 == '\0') {
                    if (DAT_1804b11e8 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b5a08 == 0) {
                        return;
                      }
                      uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                        (in_stack_00000040,&stack0x000004d4,1);
                      lVar6 = DAT_1804b4d38;
                      lVar5 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar10 = (**(code **)(*in_stack_00000040 + 0x518))
                                         (in_stack_00000040,&stack0x00001f98,5);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                         (in_stack_00000040,in_stack_00000080);
                      (*pcVar2)(in_stack_00000040,lVar5,lVar6,uVar10,uVar11);
                      uVar12 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar3,uVar12,uVar9);
                      DAT_1804b11e8 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
                    }
                    uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                                       (in_stack_00000040,DAT_1804b11e8,in_stack_000000a0);
                    uVar9 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                       (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5a41
                                       );
                    (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                       (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                        in_stack_00000070 % 0xb6bc7 ^ in_stack_000000b0 ^
                                        0x2d6292db4598f8ea);
                    (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
                    uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                                       (in_stack_00000040,uVar12,in_stack_000000a8,uVar9);
                    (**(code **)(*in_stack_00000040 + 0x1a0))
                              (in_stack_00000040,uVar12,in_stack_000000d0);
                    cVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if (cVar7 == '\0') {
                      cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
                      if (cVar7 == '\0') {
                        cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
                        lVar3 = DAT_1804b5a08;
                        uVar12 = DAT_1804b59f0;
                        if (cVar7 == '\0') {
                          if (DAT_1804b11f0 == 0) {
                            pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                            if (DAT_1804b5a08 == 0) {
                              return;
                            }
                            uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                              (in_stack_00000040,&stack0x000004d2,1);
                            lVar6 = DAT_1804b4d38;
                            lVar5 = DAT_1804b4d30;
                            pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                            if (DAT_1804b4d30 == 0) {
                              return;
                            }
                            if (DAT_1804b4d38 == 0) {
                              return;
                            }
                            uVar10 = (**(code **)(*in_stack_00000040 + 0x518))
                                               (in_stack_00000040,&stack0x00001f88,5);
                            if (DAT_1804b4d40 == 0) {
                              return;
                            }
                            uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                               (in_stack_00000040,in_stack_00000080);
                            (*pcVar2)(in_stack_00000040,lVar5,lVar6,uVar10,uVar11);
                            uVar12 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar3,uVar12,
                                               uVar9);
                            DAT_1804b11f0 =
                                 (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
                            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
                          }
                          uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                                             (in_stack_00000040,DAT_1804b11f0,in_stack_000000a0);
                          uVar9 = (**(code **)(*in_stack_00000040 + 0x560))
                                            (in_stack_00000040,2,in_stack_00000090,0);
                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                             (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,
                                              0x4c59);
                          (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                             (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                              in_stack_00000070 % 0x54276 ^ in_stack_000000b0 ^
                                              0x47574ab533d237cd);
                          (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
                          uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                                             (in_stack_00000040,uVar12,in_stack_000000a8,uVar9);
                          (**(code **)(*in_stack_00000040 + 0x1a0))
                                    (in_stack_00000040,uVar12,in_stack_000000d0);
                          cVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                          if (cVar7 == '\0') {
                            if (0 < (longlong)in_stack_000000b0) {
                              return;
                            }
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
  else {
    cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar7 == '\0') {
      if (DAT_1804b0a60 == 0) {
        lVar3 = *in_stack_00000040;
        pcVar1 = *(code **)(lVar3 + 0x390);
        uVar9 = FUN_180333550(in_stack_00000040);
        uVar12 = DAT_1804b59f0;
        uVar10 = (**(code **)(lVar3 + 0x518))(in_stack_00000040,&stack0x000006ba,1);
        lVar3 = *in_stack_00000040;
        pcVar2 = *(code **)(lVar3 + 0x390);
        uVar11 = FUN_1800024f0(in_stack_00000040);
        uVar13 = FUN_180002550(in_stack_00000040);
        uVar14 = (**(code **)(lVar3 + 0x518))(in_stack_00000040,&stack0x00002db8,5);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
        uVar15 = FUN_1800025a0(in_stack_00000040);
        uVar15 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar15);
        (*pcVar2)(in_stack_00000040,uVar11,uVar13,uVar14,uVar15);
        uVar12 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar9,uVar12,uVar10);
        DAT_1804b0a60 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b0a60,in_stack_000000a0);
      uVar9 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                         (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7089);
      (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                         (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00003130);
      (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
      uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,uVar12,in_stack_000000a8,uVar9);
      iVar8 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar12,in_stack_000000f8);
      cVar7 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (cVar7 == '\0') {
        if ((longlong)in_stack_000000b0 < 1) {
          return;
        }
        if (iVar8 == in_stack_000000e0) {
          return;
        }
        cVar7 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar7 == '\0') {
          if (DAT_1804b0a68 == 0) {
            lVar3 = *in_stack_00000040;
            pcVar1 = *(code **)(lVar3 + 0x390);
            uVar9 = FUN_180333550(in_stack_00000040);
            uVar12 = DAT_1804b59f0;
            uVar10 = (**(code **)(lVar3 + 0x518))(in_stack_00000040,&stack0x000006b8,1);
            lVar3 = *in_stack_00000040;
            pcVar2 = *(code **)(lVar3 + 0x390);
            uVar11 = FUN_1800024f0(in_stack_00000040);
            uVar13 = FUN_180002550(in_stack_00000040);
            uVar14 = (**(code **)(lVar3 + 0x518))(in_stack_00000040,&stack0x00002da8,5);
            pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
            uVar15 = FUN_1800025a0(in_stack_00000040);
            uVar15 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar15);
            (*pcVar2)(in_stack_00000040,uVar11,uVar13,uVar14,uVar15);
            uVar12 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar9,uVar12,uVar10);
            DAT_1804b0a68 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
          }
          return;
        }
      }
    }
  }
  return;
}

