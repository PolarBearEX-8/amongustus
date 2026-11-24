/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=49 entry=18040c5eb blocks=52 maxInstructions=50000 */

/* cut edge: 0x18040c5eb -> 0x180473ae8 */
/* cut edge: 0x18040c601 -> 0x180473ae8 */
/* cut edge: 0x18040c66f -> 0x180430d3e */
/* cut edge: 0x18040c693 -> 0x180430d74 */
/* cut edge: 0x18040c6ef -> 0x180430dc2 */
/* cut edge: 0x18040c711 -> 0x180430dfb */
/* cut edge: 0x18040c7f3 -> 0x180473ae8 */
/* cut edge: 0x18040c902 -> 0x1803eae9a */
/* cut edge: 0x18040c923 -> 0x180473ae8 */
/* cut edge: 0x18040c939 -> 0x180473ae8 */
/* cut edge: 0x18040c9a1 -> 0x18042b8f9 */
/* cut edge: 0x18040c9c5 -> 0x18042b92c */
/* cut edge: 0x18040ca29 -> 0x18042b97e */
/* cut edge: 0x18040ca39 -> 0x18042b9b8 */
/* cut edge: 0x18040cb1c -> 0x180473ae8 */
/* cut edge: 0x18040cc1d -> 0x180473ae8 */
/* cut edge: 0x18040cc33 -> 0x180473ae8 */
/* cut edge: 0x18040cc49 -> 0x18042a933 */
/* cut edge: 0x18040cc9d -> 0x180473ae8 */
/* cut edge: 0x18040ccc6 -> 0x180473ae8 */
/* cut edge: 0x18040ccdc -> 0x180473ae8 */
/* cut edge: 0x18040cd45 -> 0x18042ebb9 */
/* cut edge: 0x18040cd69 -> 0x18042ebec */
/* cut edge: 0x18040cdcd -> 0x18042ec3b */
/* cut edge: 0x18040cde5 -> 0x18042ec75 */
/* cut edge: 0x18040cecd -> 0x180473ae8 */
/* cut edge: 0x18040cfce -> 0x180473ae8 */
/* cut edge: 0x18040cfe4 -> 0x180473ae8 */
/* cut edge: 0x18040d055 -> 0x180430498 */
/* cut edge: 0x18040d079 -> 0x1804304cb */
/* cut edge: 0x18040d0da -> 0x18043051d */
/* cut edge: 0x18040d0ea -> 0x180430557 */
/* cut edge: 0x18040d1ca -> 0x180473ae8 */
/* cut edge: 0x18040d2c6 -> 0x180473ae8 */
/* cut edge: 0x18040d2dc -> 0x180473ae8 */
/* cut edge: 0x18040d34d -> 0x180431d8d */
/* cut edge: 0x18040d371 -> 0x180431dc0 */
/* cut edge: 0x18040d3d2 -> 0x180431e12 */
/* cut edge: 0x18040d3e2 -> 0x180431e4c */
/* cut edge: 0x18040d4c2 -> 0x180473ae8 */
/* cut edge: 0x18040d4c2 -> 0x18040d5c0 */
/* target 0x1803eae9a original byte 0x48 replaced by RET */
/* target 0x18040d5c0 original byte 0x48 replaced by RET */
/* target 0x18042a933 original byte 0x48 replaced by RET */
/* target 0x18042b8f9 original byte 0x48 replaced by RET */
/* target 0x18042b92c original byte 0x48 replaced by RET */
/* target 0x18042b97e original byte 0x48 replaced by RET */
/* target 0x18042b9b8 original byte 0x48 replaced by RET */
/* target 0x18042ebb9 original byte 0x48 replaced by RET */
/* target 0x18042ebec original byte 0x48 replaced by RET */
/* target 0x18042ec3b original byte 0x48 replaced by RET */
/* target 0x18042ec75 original byte 0x48 replaced by RET */
/* target 0x180430498 original byte 0x48 replaced by RET */
/* target 0x1804304cb original byte 0x48 replaced by RET */
/* target 0x18043051d original byte 0x48 replaced by RET */
/* target 0x180430557 original byte 0x48 replaced by RET */
/* target 0x180430d3e original byte 0x48 replaced by RET */
/* target 0x180430d74 original byte 0x48 replaced by RET */
/* target 0x180430dc2 original byte 0x48 replaced by RET */
/* target 0x180430dfb original byte 0x48 replaced by RET */
/* target 0x180431d8d original byte 0x48 replaced by RET */
/* target 0x180431dc0 original byte 0x48 replaced by RET */
/* target 0x180431e12 original byte 0x48 replaced by RET */
/* target 0x180431e4c original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00049_18040c5eb(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  longlong lVar10;
  longlong lVar11;
  ulonglong unaff_R12;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000068;
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
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar10 = DAT_1804b5a08;
    uVar9 = DAT_1804b59f0;
    if (cVar5 == '\0') {
      if (DAT_1804b3c38 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000005c0,1);
        lVar3 = DAT_1804b4d38;
        lVar11 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002230,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar2)(in_stack_00000048,lVar11,lVar3,uVar7,uVar8);
        uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar10,uVar9,uVar6);
        DAT_1804b3c38 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b3c38,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x243f);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)in_stack_00000068 % 0x38dc7 ^ in_stack_000000c8 ^
                         0xa3c230c9ea3d5cb4);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
      lVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        lVar10 = (unaff_R12 ^ in_stack_00000068) - lVar10;
        if (in_stack_00000118 == 0) {
          return;
        }
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          uVar9 = DAT_1804b59f0;
          lVar11 = DAT_1804b59e8;
          if (cVar5 == '\0') {
            if (DAT_1804b3c40 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59e8 == 0) {
                return;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x000005be,1);
              lVar4 = DAT_1804b4d38;
              lVar3 = DAT_1804b4d30;
              pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00002220,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar7,uVar8);
              uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar9,uVar6);
              DAT_1804b3c40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b3c40,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               lVar10 % 0x44804 ^ 0x5021a3ca31ed5a62);
            (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_000000c8);
            (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
            lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                               (in_stack_00000048,uVar9,in_stack_000000d8);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b5a28 == 0) {
                    return;
                  }
                  (**(code **)(*in_stack_00000048 + 0x500))
                            (in_stack_00000048,in_stack_00000088,DAT_1804b5a28,
                             lVar11 + (lVar10 % 0x44c18 ^ 0xffffffffffffdd7bU));
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      lVar11 = DAT_1804b5a08;
                      uVar9 = DAT_1804b59f0;
                      if (cVar5 == '\0') {
                        if (DAT_1804b3c48 == 0) {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b5a08 == 0) {
                            return;
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x000005bc,1);
                          lVar4 = DAT_1804b4d38;
                          lVar3 = DAT_1804b4d30;
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b4d30 == 0) {
                            return;
                          }
                          if (DAT_1804b4d38 == 0) {
                            return;
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00002210,5);
                          if (DAT_1804b4d40 == 0) {
                            return;
                          }
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000088);
                          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar7,uVar8);
                          uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar9,uVar6);
                          DAT_1804b3c48 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                        }
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b3c48,in_stack_000000a0);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x437e);
                        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           lVar10 % 0xc1607 ^ in_stack_000000c8 ^ 0x928a4d9ffade9588
                                          );
                        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
                        (**(code **)(*in_stack_00000048 + 0x1a0))
                                  (in_stack_00000048,uVar9,in_stack_000000d8);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            lVar11 = DAT_1804b5a08;
                            uVar9 = DAT_1804b59f0;
                            if (cVar5 == '\0') {
                              if (DAT_1804b3c50 == 0) {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b5a08 == 0) {
                                  return;
                                }
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x000005ba,1);
                                lVar4 = DAT_1804b4d38;
                                lVar3 = DAT_1804b4d30;
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b4d30 == 0) {
                                  return;
                                }
                                if (DAT_1804b4d38 == 0) {
                                  return;
                                }
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00002200,5);
                                if (DAT_1804b4d40 == 0) {
                                  return;
                                }
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000088);
                                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar7,uVar8);
                                uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar9,
                                                  uVar6);
                                DAT_1804b3c50 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar9);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                              }
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b3c50,in_stack_000000a0);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000090,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000b8,
                                                 in_stack_000000c0,0x243a);
                              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 lVar10 % 0x381f5 ^ in_stack_000000c8 ^
                                                 0xcf0e7ba506c74dc3);
                              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar9,in_stack_000000d8);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  lVar11 = DAT_1804b5a08;
                                  uVar9 = DAT_1804b59f0;
                                  if (cVar5 == '\0') {
                                    if (DAT_1804b3c58 == 0) {
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                      if (DAT_1804b5a08 == 0) {
                                        return;
                                      }
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                                        (in_stack_00000048,&stack0x000005b8,1);
                                      lVar4 = DAT_1804b4d38;
                                      lVar3 = DAT_1804b4d30;
                                      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                                      if (DAT_1804b4d30 == 0) {
                                        return;
                                      }
                                      if (DAT_1804b4d38 == 0) {
                                        return;
                                      }
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                                        (in_stack_00000048,&stack0x000021f0,5);
                                      if (DAT_1804b4d40 == 0) {
                                        return;
                                      }
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,in_stack_00000088);
                                      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar7,uVar8);
                                      uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,
                                                        uVar9,uVar6);
                                      DAT_1804b3c58 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar9);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar9);
                                    }
                                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,DAT_1804b3c58,
                                                       in_stack_000000a0);
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,2,in_stack_00000090,0);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_000000b8,
                                                       in_stack_000000c0,0x6232);
                                    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000080,
                                                       in_stack_00000098,
                                                       lVar10 % 0xf20f7 ^ in_stack_000000c8 ^
                                                       0xd6c25c9f5c41ec18);
                                    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,uVar9,in_stack_000000a8,
                                                       uVar6);
                                    (**(code **)(*in_stack_00000048 + 0x1a0))
                                              (in_stack_00000048,uVar9,in_stack_000000d8);
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
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
        }
      }
    }
  }
  return;
}

