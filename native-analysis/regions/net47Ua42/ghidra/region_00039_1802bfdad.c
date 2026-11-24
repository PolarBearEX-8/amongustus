/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=39 entry=1802bfdad blocks=38 maxInstructions=50000 */

/* cut edge: 0x1802bfdad -> 0x1802ee2ed */
/* cut edge: 0x1802bfdba -> 0x180333186 */
/* cut edge: 0x1802bff13 -> 0x180333186 */
/* cut edge: 0x1802c0015 -> 0x18029dd63 */
/* cut edge: 0x1802c0020 -> 0x180333186 */
/* cut edge: 0x1802c0179 -> 0x180333186 */
/* cut edge: 0x1802c0272 -> 0x18031bab4 */
/* cut edge: 0x1802c027f -> 0x180333186 */
/* cut edge: 0x1802c03d8 -> 0x180333186 */
/* cut edge: 0x1802c04d1 -> 0x1802cba6d */
/* cut edge: 0x1802c04de -> 0x180333186 */
/* cut edge: 0x1802c0637 -> 0x180333186 */
/* cut edge: 0x1802c073f -> 0x18031d73f */
/* cut edge: 0x1802c075a -> 0x180333186 */
/* cut edge: 0x1802c08a5 -> 0x180333186 */
/* cut edge: 0x1802c099e -> 0x1802c39fa */
/* cut edge: 0x1802c09b3 -> 0x180333186 */
/* cut edge: 0x1802c09eb -> 0x180333186 */
/* cut edge: 0x1802c0a2e -> 0x180333186 */
/* cut edge: 0x1802c0a67 -> 0x180333186 */
/* cut edge: 0x1802c0aa5 -> 0x180333186 */
/* cut edge: 0x1802c0abb -> 0x180333186 */
/* cut edge: 0x1802c0c83 -> 0x180333186 */
/* cut edge: 0x1802c0d61 -> 0x180333186 */
/* cut edge: 0x1802c0d61 -> 0x1802c0da4 */
/* target 0x18029dd63 original byte 0x48 replaced by RET */
/* target 0x1802c0da4 original byte 0x48 replaced by RET */
/* target 0x1802c39fa original byte 0x48 replaced by RET */
/* target 0x1802cba6d original byte 0x48 replaced by RET */
/* target 0x1802ee2ed original byte 0x48 replaced by RET */
/* target 0x18031bab4 original byte 0x48 replaced by RET */
/* target 0x18031d73f original byte 0x84 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00039_1802bfdad(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  int iVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
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
  int in_stack_000000e0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00002f80;
  undefined8 in_stack_00002f88;
  undefined8 in_stack_00002f90;
  undefined8 in_stack_00002f98;
  undefined8 in_stack_00002fa0;
  
  if (unaff_ESI == in_stack_000000e0) {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804b0be0 == 0) {
      lVar1 = *in_stack_00000040;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_180333550(in_stack_00000040);
      uVar13 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000065a,1);
      lVar1 = *in_stack_00000040;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000040);
      uVar10 = FUN_180002550(in_stack_00000040);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002ab8,5);
      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000040);
      uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
      (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
      DAT_1804b0be0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,DAT_1804b0be0,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x3ca1);
    (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00002fa0);
    (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
    iVar6 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar13,in_stack_000000f8);
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if (cVar5 == '\0') {
      if (iVar6 == in_stack_000000e0) {
        return;
      }
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b0be8 == 0) {
          lVar1 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000040);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000658,1);
          lVar1 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000040);
          uVar10 = FUN_180002550(in_stack_00000040);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002aa8,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000040);
          uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
          (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
          DAT_1804b0be8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b0be8,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1396);
        (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00002f98);
        (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
        iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                          (in_stack_00000040,uVar13,in_stack_000000f8);
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar5 == '\0') {
          if (iVar6 == in_stack_000000e0) {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b0bf0 == 0) {
              lVar1 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_180333550(in_stack_00000040);
              uVar13 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000656,1);
              lVar1 = *in_stack_00000040;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000040);
              uVar10 = FUN_180002550(in_stack_00000040);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002a98,5);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000040);
              uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
              (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
              DAT_1804b0bf0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b0bf0,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x10fa);
            (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00002f90);
            (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
            iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                              (in_stack_00000040,uVar13,in_stack_000000f8);
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if (cVar5 == '\0') {
              if (iVar6 == in_stack_000000e0) {
                return;
              }
              cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
              if (cVar5 == '\0') {
                if (DAT_1804b0bf8 == 0) {
                  lVar1 = *in_stack_00000040;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_180333550(in_stack_00000040);
                  uVar13 = DAT_1804b59f0;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000654,1);
                  lVar1 = *in_stack_00000040;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000040);
                  uVar10 = FUN_180002550(in_stack_00000040);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002a88,5);
                  pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000040);
                  uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                  (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                  DAT_1804b0bf8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13)
                  ;
                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,DAT_1804b0bf8,in_stack_000000a0);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                  (in_stack_00000040,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4648);
                (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   in_stack_00002f88);
                (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
                iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                                  (in_stack_00000040,uVar13,in_stack_000000f8);
                cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                if (cVar5 == '\0') {
                  if (0 < (longlong)in_stack_000000b0) {
                    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                    if (iVar6 == in_stack_000000e0) {
                      return;
                    }
                    if (cVar5 != '\0') {
                      return;
                    }
                    if (DAT_1804b0c00 == 0) {
                      lVar1 = *in_stack_00000040;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_180333550(in_stack_00000040);
                      uVar13 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000652,1);
                      lVar1 = *in_stack_00000040;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000040);
                      uVar10 = FUN_180002550(in_stack_00000040);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002a78,5);
                      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000040);
                      uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                      (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                      uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                      DAT_1804b0c00 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                    }
                    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                       (in_stack_00000040,DAT_1804b0c00,in_stack_000000a0);
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x693c)
                    ;
                    (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                       in_stack_00002f80);
                    (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                       (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
                    iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                                      (in_stack_00000040,uVar13,in_stack_000000f8);
                    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if (cVar5 != '\0') {
                      return;
                    }
                  }
                  if (iVar6 != in_stack_000000e0) {
                    return;
                  }
                  pcVar2 = *(code **)(*in_stack_00000040 + 0xd8);
                  uVar13 = FUN_18001ec20(in_stack_00000040);
                  uVar7 = (*pcVar2)(in_stack_00000040,uVar13);
                  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                  if (cVar5 == '\0') {
                    pcVar2 = *(code **)(*in_stack_00000040 + 0x2d8);
                    uVar8 = FUN_18001ec80(in_stack_00000040);
                    (*pcVar2)(in_stack_00000040,uVar7,uVar13,uVar8);
                    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if (cVar5 == '\0') {
                      pcVar2 = *(code **)(*in_stack_00000040 + 0xd8);
                      uVar13 = FUN_180010830(in_stack_00000040);
                      uVar7 = (*pcVar2)(in_stack_00000040,uVar13);
                      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                      if (cVar5 == '\0') {
                        pcVar2 = *(code **)(*in_stack_00000040 + 0x2d8);
                        uVar8 = FUN_180010890(in_stack_00000040);
                        (*pcVar2)(in_stack_00000040,uVar7,uVar13,uVar8);
                        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                        if (((cVar5 == '\0') &&
                            (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) &&
                           (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) {
                          if (DAT_1804b0ce8 == 0) {
                            lVar1 = *in_stack_00000040;
                            pcVar2 = *(code **)(lVar1 + 0x390);
                            uVar8 = FUN_1803335a0(in_stack_00000040);
                            uVar13 = DAT_1804b59f0;
                            uVar9 = (**(code **)(lVar1 + 0x518))
                                              (in_stack_00000040,&stack0x00000618,1);
                            lVar1 = *in_stack_00000040;
                            pcVar3 = *(code **)(lVar1 + 0x390);
                            uVar10 = FUN_1800024f0(in_stack_00000040);
                            uVar11 = FUN_180002550(in_stack_00000040);
                            uVar12 = (**(code **)(lVar1 + 0x518))
                                               (in_stack_00000040,&stack0x000037b0,0x16);
                            pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                            uVar14 = FUN_1800025a0(in_stack_00000040);
                            uVar14 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar14);
                            (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar14);
                            uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar8,uVar13,
                                               uVar9);
                            DAT_1804b0ce8 =
                                 (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
                            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                          }
                          uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                             (in_stack_00000040,DAT_1804b0ce8,in_stack_000000a0);
                          uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                            (in_stack_00000040,2,in_stack_00000090,0);
                          pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                            (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,
                                             0x494f);
                          (*pcVar2)(in_stack_00000040,uVar8,0,uVar9);
                          pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                             in_stack_00000070 % 0x37a1b ^ in_stack_000000b0 ^
                                             0x28193b3d763ebe6);
                          (*pcVar2)(in_stack_00000040,uVar8,1,uVar9);
                          uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                             (in_stack_00000040,uVar13,in_stack_000000a8,uVar8);
                          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                          if (cVar5 == '\0') {
                            pcVar2 = *(code **)(*in_stack_00000040 + 0x110);
                            uVar8 = FUN_1800108e0(in_stack_00000040);
                            (*pcVar2)(in_stack_00000040,uVar7,uVar8,uVar13);
                            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                            if (cVar5 == '\0') {
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
  return;
}

