/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=61 entry=1802d5de1 blocks=42 maxInstructions=50000 */

/* cut edge: 0x1802d5de1 -> 0x180333186 */
/* cut edge: 0x1802d5df7 -> 0x180333186 */
/* cut edge: 0x1802d5e62 -> 0x1802e5edc */
/* cut edge: 0x1802d5e86 -> 0x1802e5f0f */
/* cut edge: 0x1802d5eea -> 0x1802e5f60 */
/* cut edge: 0x1802d5efa -> 0x1802e5f99 */
/* cut edge: 0x1802d5fd5 -> 0x180333186 */
/* cut edge: 0x1802d60ca -> 0x180333186 */
/* cut edge: 0x1802d60e0 -> 0x180333186 */
/* cut edge: 0x1802d6148 -> 0x1802e5fd0 */
/* cut edge: 0x1802d6171 -> 0x1802e6008 */
/* cut edge: 0x1802d61d0 -> 0x1802e6056 */
/* cut edge: 0x1802d61e8 -> 0x1802e608f */
/* cut edge: 0x1802d62c7 -> 0x180333186 */
/* cut edge: 0x1802d63bc -> 0x1802f2543 */
/* cut edge: 0x1802d63f7 -> 0x1802e23c3 */
/* cut edge: 0x1802d6416 -> 0x1802e2414 */
/* cut edge: 0x1802d6426 -> 0x180333186 */
/* cut edge: 0x1802d644f -> 0x180333186 */
/* cut edge: 0x1802d6465 -> 0x180333186 */
/* cut edge: 0x1802d660e -> 0x180333186 */
/* cut edge: 0x1802d6705 -> 0x180333186 */
/* cut edge: 0x1802d671b -> 0x180333186 */
/* cut edge: 0x1802d68c9 -> 0x180333186 */
/* cut edge: 0x1802d69c3 -> 0x180333186 */
/* cut edge: 0x1802d69d9 -> 0x180333186 */
/* cut edge: 0x1802d6b87 -> 0x180333186 */
/* cut edge: 0x1802d6c7c -> 0x180333186 */
/* cut edge: 0x1802d6c92 -> 0x180333186 */
/* cut edge: 0x1802d6ca8 -> 0x1802d6e4e */
/* cut edge: 0x1802d6cfd -> 0x1802d6e4e */
/* target 0x1802d6e4e original byte 0x48 replaced by RET */
/* target 0x1802e23c3 original byte 0x48 replaced by RET */
/* target 0x1802e2414 original byte 0x48 replaced by RET */
/* target 0x1802e5edc original byte 0x48 replaced by RET */
/* target 0x1802e5f0f original byte 0x48 replaced by RET */
/* target 0x1802e5f60 original byte 0x48 replaced by RET */
/* target 0x1802e5f99 original byte 0x48 replaced by RET */
/* target 0x1802e5fd0 original byte 0x48 replaced by RET */
/* target 0x1802e6008 original byte 0x48 replaced by RET */
/* target 0x1802e6056 original byte 0x48 replaced by RET */
/* target 0x1802e608f original byte 0x48 replaced by RET */
/* target 0x1802f2543 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


code * region_00061_1802d5de1(void)

{
  longlong lVar1;
  longlong lVar2;
  code *pcVar3;
  undefined8 uVar4;
  code *pcVar5;
  code *pcVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  ulonglong uVar9;
  longlong lVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  code *in_stack_00000040;
  ulonglong in_stack_00000070;
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
  longlong in_stack_00000110;
  
  pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
  if ((char)pcVar3 == '\0') {
    pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
    lVar10 = DAT_1804b5a08;
    uVar8 = DAT_1804b59f0;
    if ((char)pcVar3 == '\0') {
      if (DAT_1804b1900 == 0) {
        pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return (code *)0x0;
        }
        uVar4 = (**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                          (in_stack_00000040,&stack0x0000030c,1);
        lVar2 = DAT_1804b4d38;
        lVar1 = DAT_1804b4d30;
        pcVar5 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return pcVar5;
        }
        if (DAT_1804b4d38 == 0) {
          return pcVar5;
        }
        pcVar6 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                                   (in_stack_00000040,&stack0x00001268,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar6;
        }
        uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                          (in_stack_00000040,in_stack_00000080);
        (*pcVar5)(in_stack_00000040,lVar1,lVar2,pcVar6,uVar7);
        uVar8 = (*pcVar3)(in_stack_00000040,in_stack_00000080,lVar10,uVar8,uVar4);
        DAT_1804b1900 =
             (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))(in_stack_00000040,uVar8);
        (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))(in_stack_00000040,uVar8);
      }
      uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                        (in_stack_00000040,DAT_1804b1900,in_stack_000000a0);
      uVar4 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                        (in_stack_00000040,2,in_stack_00000090,0);
      pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5f98);
      (*pcVar3)(in_stack_00000040,uVar4,0,uVar7);
      pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         (longlong)in_stack_00000070 % 0x5e6b5 ^ in_stack_000000b0 ^
                         0xc70ddd72a9f1444a);
      (*pcVar3)(in_stack_00000040,uVar4,1,uVar7);
      uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                        (in_stack_00000040,uVar8,in_stack_000000a8,uVar4);
      uVar9 = (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))
                        (in_stack_00000040,uVar8,in_stack_000000d0);
      pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))(in_stack_00000040);
      if ((char)pcVar3 == '\0') {
        pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
        if ((char)pcVar3 == '\0') {
          pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
          lVar10 = DAT_1804b5a08;
          uVar8 = DAT_1804b59f0;
          if ((char)pcVar3 == '\0') {
            if (DAT_1804b1908 == 0) {
              pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return (code *)0x0;
              }
              uVar4 = (**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                                (in_stack_00000040,&stack0x0000030a,1);
              lVar2 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              pcVar5 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar5;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar5;
              }
              pcVar6 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                                         (in_stack_00000040,&stack0x00001258,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar6;
              }
              uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                (in_stack_00000040,in_stack_00000080);
              (*pcVar5)(in_stack_00000040,lVar1,lVar2,pcVar6,uVar7);
              uVar8 = (*pcVar3)(in_stack_00000040,in_stack_00000080,lVar10,uVar8,uVar4);
              DAT_1804b1908 =
                   (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))(in_stack_00000040,uVar8);
              (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))(in_stack_00000040,uVar8);
            }
            uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                              (in_stack_00000040,DAT_1804b1908,in_stack_000000a0);
            uVar4 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
            uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x57c4);
            (*pcVar3)(in_stack_00000040,uVar4,0,uVar7);
            pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
            uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               (longlong)in_stack_00000070 % 0x239fc ^ in_stack_000000b0 ^
                               0xc545bde72f42aa55);
            (*pcVar3)(in_stack_00000040,uVar4,1,uVar7);
            uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                              (in_stack_00000040,uVar8,in_stack_000000a8,uVar4);
            lVar10 = (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))
                               (in_stack_00000040,uVar8,in_stack_000000d0);
            pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))
                                       (in_stack_00000040);
            if ((char)pcVar3 == '\0') {
              lVar10 = (in_stack_00000070 ^ uVar9) - lVar10;
              if (in_stack_00000110 == 0) {
                return (code *)0x0;
              }
              if (DAT_1804b5290 == 0) {
                return in_stack_00000040;
              }
              if (DAT_1804b5400 == 0) {
                return in_stack_00000040;
              }
              (**(code **)(*(longlong *)in_stack_00000040 + 0x420))(in_stack_00000040,DAT_1804b5290)
              ;
              pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))
                                         (in_stack_00000040);
              if ((char)pcVar3 == '\0') {
                pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
                if ((char)pcVar3 == '\0') {
                  pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
                  if ((char)pcVar3 == '\0') {
                    if (DAT_1804b1910 == 0) {
                      lVar1 = *(longlong *)in_stack_00000040;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar4 = FUN_1803335f0(in_stack_00000040);
                      uVar8 = DAT_1804b59f0;
                      uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000308,1);
                      lVar1 = *(longlong *)in_stack_00000040;
                      pcVar5 = *(code **)(lVar1 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000040);
                      uVar12 = FUN_180002550(in_stack_00000040);
                      uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00001248,5);
                      pcVar6 = *(code **)(*(longlong *)in_stack_00000040 + 0x110);
                      uVar14 = FUN_1800025a0(in_stack_00000040);
                      uVar14 = (*pcVar6)(in_stack_00000040,in_stack_00000080,uVar14);
                      (*pcVar5)(in_stack_00000040,uVar11,uVar12,uVar13,uVar14);
                      uVar8 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar4,uVar8,uVar7);
                      DAT_1804b1910 =
                           (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))
                                     (in_stack_00000040,uVar8);
                      (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))(in_stack_00000040,uVar8);
                    }
                    uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                      (in_stack_00000040,DAT_1804b1910,in_stack_000000a0);
                    uVar4 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                    uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x27cd)
                    ;
                    (*pcVar3)(in_stack_00000040,uVar4,0,uVar7);
                    pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                    uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                       lVar10 % 0x76be7 ^ in_stack_000000b0 ^ 0x525b3007eb575873);
                    (*pcVar3)(in_stack_00000040,uVar4,1,uVar7);
                    uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                      (in_stack_00000040,uVar8,in_stack_000000a8,uVar4);
                    (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))
                              (in_stack_00000040,uVar8,in_stack_000000d0);
                    pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))
                                               (in_stack_00000040);
                    if ((char)pcVar3 == '\0') {
                      pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
                      if ((char)pcVar3 == '\0') {
                        pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
                        if ((char)pcVar3 == '\0') {
                          if (DAT_1804b1918 == 0) {
                            lVar1 = *(longlong *)in_stack_00000040;
                            pcVar3 = *(code **)(lVar1 + 0x390);
                            uVar4 = FUN_1803335f0(in_stack_00000040);
                            uVar8 = DAT_1804b59f0;
                            uVar7 = (**(code **)(lVar1 + 0x518))
                                              (in_stack_00000040,&stack0x00000306,1);
                            lVar1 = *(longlong *)in_stack_00000040;
                            pcVar5 = *(code **)(lVar1 + 0x390);
                            uVar11 = FUN_1800024f0(in_stack_00000040);
                            uVar12 = FUN_180002550(in_stack_00000040);
                            uVar13 = (**(code **)(lVar1 + 0x518))
                                               (in_stack_00000040,&stack0x00001238,5);
                            pcVar6 = *(code **)(*(longlong *)in_stack_00000040 + 0x110);
                            uVar14 = FUN_1800025a0(in_stack_00000040);
                            uVar14 = (*pcVar6)(in_stack_00000040,in_stack_00000080,uVar14);
                            (*pcVar5)(in_stack_00000040,uVar11,uVar12,uVar13,uVar14);
                            uVar8 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar4,uVar8,uVar7)
                            ;
                            DAT_1804b1918 =
                                 (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))
                                           (in_stack_00000040,uVar8);
                            (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))
                                      (in_stack_00000040,uVar8);
                          }
                          uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                            (in_stack_00000040,DAT_1804b1918,in_stack_000000a0);
                          uVar4 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                                            (in_stack_00000040,2,in_stack_00000090,0);
                          pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                          uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                            (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,
                                             0x1398);
                          (*pcVar3)(in_stack_00000040,uVar4,0,uVar7);
                          pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                          uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                             lVar10 % 0xcd809 ^ in_stack_000000b0 ^
                                             0x44a2044dd38b491d);
                          (*pcVar3)(in_stack_00000040,uVar4,1,uVar7);
                          uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                            (in_stack_00000040,uVar8,in_stack_000000a8,uVar4);
                          (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))
                                    (in_stack_00000040,uVar8,in_stack_000000d0);
                          pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))
                                                     (in_stack_00000040);
                          if ((char)pcVar3 == '\0') {
                            pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))()
                            ;
                            if ((char)pcVar3 == '\0') {
                              pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))
                                                         ();
                              if ((char)pcVar3 == '\0') {
                                if (DAT_1804b1920 == 0) {
                                  lVar1 = *(longlong *)in_stack_00000040;
                                  pcVar3 = *(code **)(lVar1 + 0x390);
                                  uVar4 = FUN_1803335f0(in_stack_00000040);
                                  uVar8 = DAT_1804b59f0;
                                  uVar7 = (**(code **)(lVar1 + 0x518))
                                                    (in_stack_00000040,&stack0x00000304,1);
                                  lVar1 = *(longlong *)in_stack_00000040;
                                  pcVar5 = *(code **)(lVar1 + 0x390);
                                  uVar11 = FUN_1800024f0(in_stack_00000040);
                                  uVar12 = FUN_180002550(in_stack_00000040);
                                  uVar13 = (**(code **)(lVar1 + 0x518))
                                                     (in_stack_00000040,&stack0x00001228,5);
                                  pcVar6 = *(code **)(*(longlong *)in_stack_00000040 + 0x110);
                                  uVar14 = FUN_1800025a0(in_stack_00000040);
                                  uVar14 = (*pcVar6)(in_stack_00000040,in_stack_00000080,uVar14);
                                  (*pcVar5)(in_stack_00000040,uVar11,uVar12,uVar13,uVar14);
                                  uVar8 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar4,uVar8,
                                                    uVar7);
                                  DAT_1804b1920 =
                                       (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))
                                                 (in_stack_00000040,uVar8);
                                  (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))
                                            (in_stack_00000040,uVar8);
                                }
                                uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                                  (in_stack_00000040,DAT_1804b1920,in_stack_000000a0
                                                  );
                                uVar4 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                                                  (in_stack_00000040,2,in_stack_00000090,0);
                                pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                                uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                                  (in_stack_00000040,in_stack_000000b8,
                                                   in_stack_000000c0,0x7564);
                                (*pcVar3)(in_stack_00000040,uVar4,0,uVar7);
                                pcVar3 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                                uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                                  (in_stack_00000040,in_stack_00000088,
                                                   in_stack_00000098,
                                                   lVar10 % 0x84926 ^ in_stack_000000b0 ^
                                                   0x1e7a7731de265ad8);
                                (*pcVar3)(in_stack_00000040,uVar4,1,uVar7);
                                uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                                  (in_stack_00000040,uVar8,in_stack_000000a8,uVar4);
                                (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))
                                          (in_stack_00000040,uVar8,in_stack_000000d0);
                                pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720
                                                             ))(in_stack_00000040);
                                if ((char)pcVar3 == '\0') {
                                  pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 +
                                                               0x720))();
                                  if ((char)pcVar3 == '\0') {
                                    pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040 +
                                                                 0x720))();
                                    if ((char)pcVar3 == '\0') {
                                      pcVar3 = (code *)0x4f5e3fc51439d9b8;
                                      if (DAT_1804b1928 == 0) {
                                        lVar10 = *(longlong *)in_stack_00000040;
                                        pcVar3 = *(code **)(lVar10 + 0x390);
                                        uVar4 = FUN_1803335f0(in_stack_00000040);
                                        uVar8 = DAT_1804b59f0;
                                        uVar7 = (**(code **)(lVar10 + 0x518))
                                                          (in_stack_00000040,&stack0x00000302,1);
                                        lVar10 = *(longlong *)in_stack_00000040;
                                        pcVar5 = *(code **)(lVar10 + 0x390);
                                        uVar11 = FUN_1800024f0(in_stack_00000040);
                                        uVar12 = FUN_180002550(in_stack_00000040);
                                        uVar13 = (**(code **)(lVar10 + 0x518))
                                                           (in_stack_00000040,&stack0x00001218,5);
                                        pcVar6 = *(code **)(*(longlong *)in_stack_00000040 + 0x110);
                                        uVar14 = FUN_1800025a0(in_stack_00000040);
                                        uVar14 = (*pcVar6)(in_stack_00000040,in_stack_00000080,
                                                           uVar14);
                                        (*pcVar5)(in_stack_00000040,uVar11,uVar12,uVar13,uVar14);
                                        uVar8 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar4,
                                                          uVar8,uVar7);
                                        DAT_1804b1928 =
                                             (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))
                                                       (in_stack_00000040,uVar8);
                                        pcVar3 = (code *)(**(code **)(*(longlong *)in_stack_00000040
                                                                     + 0xb8))
                                                                   (in_stack_00000040,uVar8);
                                      }
                                      return pcVar3;
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
  return pcVar3;
}

