/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=11 entry=1803e65ba blocks=42 maxInstructions=50000 */

/* cut edge: 0x1803e65c3 -> 0x180424db3 */
/* cut edge: 0x1803e65cd -> 0x180473ae8 */
/* cut edge: 0x1803e6726 -> 0x180473ae8 */
/* cut edge: 0x1803e6828 -> 0x18042240b */
/* cut edge: 0x1803e683d -> 0x180473ae8 */
/* cut edge: 0x1803e6996 -> 0x180473ae8 */
/* cut edge: 0x1803e6a98 -> 0x1803f3d50 */
/* cut edge: 0x1803e6ab7 -> 0x180473ae8 */
/* cut edge: 0x1803e6c10 -> 0x180473ae8 */
/* cut edge: 0x1803e6d12 -> 0x180413680 */
/* cut edge: 0x1803e6d24 -> 0x180473ae8 */
/* cut edge: 0x1803e6e7d -> 0x180473ae8 */
/* cut edge: 0x1803e6f76 -> 0x180447f97 */
/* cut edge: 0x1803e6f83 -> 0x180473ae8 */
/* cut edge: 0x1803e70dc -> 0x180473ae8 */
/* cut edge: 0x1803e71de -> 0x180429b0d */
/* cut edge: 0x1803e7202 -> 0x180473ae8 */
/* cut edge: 0x1803e735b -> 0x180473ae8 */
/* cut edge: 0x1803e7454 -> 0x1803e8048 */
/* cut edge: 0x1803e7461 -> 0x18044536e */
/* cut edge: 0x1803e747e -> 0x180473ae8 */
/* cut edge: 0x1803e7486 -> 0x180473ae8 */
/* cut edge: 0x1803e749c -> 0x1803e765c */
/* cut edge: 0x1803e74ee -> 0x1803eaaaa */
/* cut edge: 0x1803e7517 -> 0x1803eaae2 */
/* cut edge: 0x1803e7517 -> 0x1803e7573 */
/* target 0x1803e7573 original byte 0x48 replaced by RET */
/* target 0x1803e765c original byte 0x48 replaced by RET */
/* target 0x1803e8048 original byte 0x48 replaced by RET */
/* target 0x1803eaaaa original byte 0x48 replaced by RET */
/* target 0x1803eaae2 original byte 0x48 replaced by RET */
/* target 0x1803f3d50 original byte 0x48 replaced by RET */
/* target 0x180413680 original byte 0x48 replaced by RET */
/* target 0x18042240b original byte 0x8d replaced by RET */
/* target 0x180424db3 original byte 0x48 replaced by RET */
/* target 0x180429b0d original byte 0x48 replaced by RET */
/* target 0x18044536e original byte 0x84 replaced by RET */
/* target 0x180447f97 original byte 0x4c replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


longlong region_00011_1803e65ba(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  int iVar4;
  longlong in_RAX;
  longlong lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  int unaff_ESI;
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
  longlong in_stack_000000e8;
  undefined8 in_stack_00000120;
  longlong in_stack_00000158;
  undefined8 in_stack_00002e10;
  undefined8 in_stack_00002e18;
  undefined8 in_stack_00002e20;
  undefined8 in_stack_00002e28;
  undefined8 in_stack_00002e30;
  undefined8 in_stack_00002e38;
  
  if (unaff_ESI == in_stack_000000e0) {
    return in_RAX;
  }
  lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)lVar5 == '\0') {
    if (DAT_1804b37e0 == 0) {
      lVar5 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar5 + 0x390);
      uVar6 = FUN_180333550(in_stack_00000048);
      uVar12 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000006d6,1);
      lVar5 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar5 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00002ae0,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
      (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
      DAT_1804b37e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b37e0,in_stack_000000a0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x600d);
    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002e38);
    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
    iVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000120);
    lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)lVar5 == '\0') {
      if (iVar4 == in_stack_000000e0) {
        return lVar5;
      }
      lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)lVar5 == '\0') {
        if (DAT_1804b37e8 == 0) {
          lVar5 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar5 + 0x390);
          uVar6 = FUN_180333550(in_stack_00000048);
          uVar12 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000006d4,1);
          lVar5 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar5 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00002ad0,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
          DAT_1804b37e8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b37e8,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5341);
        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002e30);
        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
        iVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar12,in_stack_00000120);
        lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)lVar5 == '\0') {
          if (iVar4 == in_stack_000000e0) {
            return in_stack_000000e8;
          }
          lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)lVar5 == '\0') {
            if (DAT_1804b37f0 == 0) {
              lVar5 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar5 + 0x390);
              uVar6 = FUN_180333550(in_stack_00000048);
              uVar12 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000006d2,1);
              lVar5 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar5 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00002ac0,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
              DAT_1804b37f0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b37f0,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4bf);
            (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_00002e28);
            (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
            iVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar12,in_stack_00000120);
            lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)lVar5 == '\0') {
              if (iVar4 == in_stack_000000e0) {
                return in_stack_00000158;
              }
              lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)lVar5 == '\0') {
                if (DAT_1804b37f8 == 0) {
                  lVar5 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar5 + 0x390);
                  uVar6 = FUN_180333550(in_stack_00000048);
                  uVar12 = DAT_1804b59f0;
                  uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000006d0,1);
                  lVar5 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar5 + 0x390);
                  uVar8 = FUN_1800024f0(in_stack_00000048);
                  uVar9 = FUN_180002550(in_stack_00000048);
                  uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00002ab0,5);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar11 = FUN_1800025a0(in_stack_00000048);
                  uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
                  (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                  uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
                  DAT_1804b37f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                }
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b37f8,in_stack_000000a0);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000090,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x11f2);
                (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                   in_stack_00002e20);
                (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                iVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar12,in_stack_00000120);
                lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)lVar5 == '\0') {
                  if (iVar4 == in_stack_000000e0) {
                    return lVar5;
                  }
                  lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)lVar5 == '\0') {
                    if (DAT_1804b3800 == 0) {
                      lVar5 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar5 + 0x390);
                      uVar6 = FUN_180333550(in_stack_00000048);
                      uVar12 = DAT_1804b59f0;
                      uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000006ce,1);
                      lVar5 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar5 + 0x390);
                      uVar8 = FUN_1800024f0(in_stack_00000048);
                      uVar9 = FUN_180002550(in_stack_00000048);
                      uVar10 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00002aa0,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar11 = FUN_1800025a0(in_stack_00000048);
                      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
                      (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                      uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
                      DAT_1804b3800 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                    }
                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b3800,in_stack_000000a0);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x3d99)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_00002e18);
                    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                    iVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                                      (in_stack_00000048,uVar12,in_stack_00000120);
                    lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)lVar5 == '\0') {
                      if (iVar4 == in_stack_000000e0) {
                        return in_stack_000000e8;
                      }
                      lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)lVar5 == '\0') {
                        if (DAT_1804b3808 == 0) {
                          lVar5 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar5 + 0x390);
                          uVar6 = FUN_180333550(in_stack_00000048);
                          uVar12 = DAT_1804b59f0;
                          uVar7 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000006cc,1)
                          ;
                          lVar5 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar5 + 0x390);
                          uVar8 = FUN_1800024f0(in_stack_00000048);
                          uVar9 = FUN_180002550(in_stack_00000048);
                          uVar10 = (**(code **)(lVar5 + 0x518))
                                             (in_stack_00000048,&stack0x00002a90,5);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar11 = FUN_1800025a0(in_stack_00000048);
                          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
                          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                          uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7)
                          ;
                          DAT_1804b3808 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                        }
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804b3808,in_stack_000000a0);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x79dc);
                        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           in_stack_00002e10);
                        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                        iVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                                          (in_stack_00000048,uVar12,in_stack_00000120);
                        lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)lVar5 == '\0') {
                          if (iVar4 != in_stack_000000e0) {
                            return lVar5;
                          }
                          lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (-1 < in_stack_000000e8) {
                            return lVar5;
                          }
                          if ((char)lVar5 == '\0') {
                            lVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)lVar5 == '\0') {
                              if (DAT_1804b3ff8 != 0) {
                                return 0x772a1d7e8da112e0;
                              }
                              if (DAT_1804b5a08 != 0) {
                                (**(code **)(*in_stack_00000048 + 0x518))
                                          (in_stack_00000048,&stack0x000004d0,1);
                                if (DAT_1804b4d30 != 0) {
                                  return *(longlong *)(*in_stack_00000048 + 0x390);
                                }
                                return *(longlong *)(*in_stack_00000048 + 0x390);
                              }
                              return 0;
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
  return lVar5;
}

