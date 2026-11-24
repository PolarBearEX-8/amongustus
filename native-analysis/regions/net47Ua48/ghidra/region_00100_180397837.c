/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=100 entry=180397837 blocks=48 maxInstructions=50000 */

/* cut edge: 0x180397837 -> 0x1803d14c0 */
/* cut edge: 0x18039793b -> 0x1803351d6 */
/* cut edge: 0x180397a16 -> 0x1803351d6 */
/* cut edge: 0x180397a58 -> 0x1803351d6 */
/* cut edge: 0x180397a83 -> 0x1803351d6 */
/* cut edge: 0x180397aae -> 0x1803d14f8 */
/* cut edge: 0x180397add -> 0x1803d1533 */
/* cut edge: 0x180397b3f -> 0x1803d1584 */
/* cut edge: 0x180397b4f -> 0x1803d15bd */
/* cut edge: 0x180397c71 -> 0x1803351d6 */
/* cut edge: 0x180397d70 -> 0x1803351d6 */
/* cut edge: 0x180397db0 -> 0x1803351d6 */
/* cut edge: 0x180397dde -> 0x1803d1642 */
/* cut edge: 0x180397e05 -> 0x1803d1678 */
/* cut edge: 0x180397e67 -> 0x1803d16c9 */
/* cut edge: 0x180397e77 -> 0x1803d1702 */
/* cut edge: 0x180397f61 -> 0x1803351d6 */
/* cut edge: 0x1803980d2 -> 0x1803351d6 */
/* cut edge: 0x1803980fd -> 0x1803351d6 */
/* cut edge: 0x180398128 -> 0x1803d1833 */
/* cut edge: 0x180398157 -> 0x1803d1869 */
/* cut edge: 0x1803981b9 -> 0x1803d18ba */
/* cut edge: 0x1803981c9 -> 0x1803d18f4 */
/* cut edge: 0x1803982f2 -> 0x1803351d6 */
/* cut edge: 0x1803983f1 -> 0x1803351d6 */
/* cut edge: 0x180398431 -> 0x1803c91ad */
/* cut edge: 0x180398450 -> 0x1803351d6 */
/* cut edge: 0x180398479 -> 0x1803351d6 */
/* cut edge: 0x1803984a7 -> 0x1803d1a29 */
/* cut edge: 0x1803984e6 -> 0x1803d1a64 */
/* cut edge: 0x180398548 -> 0x1803d1ab5 */
/* cut edge: 0x180398558 -> 0x1803d1aee */
/* cut edge: 0x180398654 -> 0x1803351d6 */
/* cut edge: 0x1803987bb -> 0x1803351d6 */
/* cut edge: 0x1803987e6 -> 0x1803351d6 */
/* cut edge: 0x180398801 -> 0x1803989dc */
/* cut edge: 0x180398811 -> 0x1803d1c1c */
/* cut edge: 0x180398811 -> 0x180398838 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180398838 original byte 0x48 replaced by RET */
/* target 0x1803989dc original byte 0x48 replaced by RET */
/* target 0x1803c91ad original byte 0x48 replaced by RET */
/* target 0x1803d14c0 original byte 0x48 replaced by RET */
/* target 0x1803d14f8 original byte 0x48 replaced by RET */
/* target 0x1803d1533 original byte 0x48 replaced by RET */
/* target 0x1803d1584 original byte 0x48 replaced by RET */
/* target 0x1803d15bd original byte 0x48 replaced by RET */
/* target 0x1803d1642 original byte 0x48 replaced by RET */
/* target 0x1803d1678 original byte 0x48 replaced by RET */
/* target 0x1803d16c9 original byte 0x48 replaced by RET */
/* target 0x1803d1702 original byte 0x48 replaced by RET */
/* target 0x1803d1833 original byte 0x48 replaced by RET */
/* target 0x1803d1869 original byte 0x48 replaced by RET */
/* target 0x1803d18ba original byte 0x48 replaced by RET */
/* target 0x1803d18f4 original byte 0x48 replaced by RET */
/* target 0x1803d1a29 original byte 0x48 replaced by RET */
/* target 0x1803d1a64 original byte 0x48 replaced by RET */
/* target 0x1803d1ab5 original byte 0x48 replaced by RET */
/* target 0x1803d1aee original byte 0x48 replaced by RET */
/* target 0x1803d1c1c original byte 0x48 replaced by RET */


void region_00100_180397837(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  longlong lVar5;
  longlong lVar6;
  char cVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong unaff_RDI;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  code *in_stack_000000c0;
  undefined8 in_stack_000000c8;
  code *in_stack_000000d0;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_00000108;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000860;
  
  (**(code **)(unaff_RDI + 0x518))(in_stack_00000048,&stack0x00003210,0x16);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
  (*in_stack_000000c0)(in_stack_00000048);
  uVar8 = (*in_stack_000000d0)
                    (in_stack_00000048,in_stack_00000058,in_stack_000000e0,in_stack_000000b0,
                     in_stack_000000b8);
  DAT_1804b3248 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804b3248,in_stack_00000078);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5884);
  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090);
  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,in_stack_00000080,uVar9)
  ;
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 == '\0') {
    (**(code **)(*in_stack_00000048 + 0x2d8))
              (in_stack_00000048,in_stack_00000070,in_stack_000000e8,in_stack_000000a0,uVar8);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 == '\0') {
      uVar8 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,in_stack_000000e8);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 == '\0') {
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
        uVar9 = DAT_1804b59f0;
        lVar6 = DAT_1804b59e8;
        if (cVar7 == '\0') {
          if (DAT_1804b3250 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59e8 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000348,1)
            ;
            lVar5 = DAT_1804b4d38;
            lVar4 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar11 = (**(code **)(*in_stack_00000048 + 0x518))
                               (in_stack_00000048,&stack0x00007370,0x28);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar11,uVar12);
            uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar6,uVar9,uVar10);
            DAT_1804b3250 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b3250,in_stack_00000078);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,3,in_stack_00000088,0);
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar10,0,in_stack_00000108);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              0xdfd54fb22c452c88);
          (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000c8);
          (*pcVar1)(in_stack_00000048,uVar10,2,uVar11);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 == '\0') {
            (**(code **)(*in_stack_00000048 + 0x2d8))
                      (in_stack_00000048,uVar8,in_stack_000000e8,in_stack_000000a0,uVar9);
            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar7 == '\0') {
              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
              uVar9 = DAT_1804b59f0;
              lVar6 = DAT_1804b59e8;
              if (cVar7 == '\0') {
                if (DAT_1804b3258 == 0) {
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                  if (DAT_1804b59e8 == 0) {
                    return;
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00000346,1);
                  lVar5 = DAT_1804b4d38;
                  lVar4 = DAT_1804b4d30;
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                  if (DAT_1804b4d30 == 0) {
                    return;
                  }
                  if (DAT_1804b4d38 == 0) {
                    return;
                  }
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x518);
                  FUN_180492670(&stack0x000077f0,&DAT_1804a2a84,0x8a);
                  uVar11 = (*pcVar3)(in_stack_00000048,&stack0x000077f0,0x45);
                  if (DAT_1804b4d40 == 0) {
                    return;
                  }
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,in_stack_00000058);
                  (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar11,uVar12);
                  uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar6,uVar9,uVar10);
                  DAT_1804b3258 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b3258,in_stack_00000078);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                   (in_stack_00000048,6,in_stack_00000088,0);
                (**(code **)(*in_stack_00000048 + 0x570))
                          (in_stack_00000048,uVar10,0,in_stack_000000a8);
                (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar10,1,uVar8);
                (**(code **)(*in_stack_00000048 + 0x570))
                          (in_stack_00000048,uVar10,2,in_stack_00000070);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_00000860);
                (*pcVar1)(in_stack_00000048,uVar10,3,uVar8);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   0xdfcf903ad2fb3102);
                (*pcVar1)(in_stack_00000048,uVar10,4,uVar8);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_000000c8);
                (*pcVar1)(in_stack_00000048,uVar10,5,uVar8);
                (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
                cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar7 == '\0') {
                  uVar8 = (**(code **)(*in_stack_00000048 + 0xd8))
                                    (in_stack_00000048,in_stack_000000e8);
                  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar7 == '\0') {
                    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                    uVar9 = DAT_1804b59f0;
                    lVar6 = DAT_1804b59e8;
                    if (cVar7 == '\0') {
                      if (DAT_1804b3260 == 0) {
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                        if (DAT_1804b59e8 == 0) {
                          return;
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x518))
                                           (in_stack_00000048,&stack0x00000344,1);
                        lVar5 = DAT_1804b4d38;
                        lVar4 = DAT_1804b4d30;
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                        if (DAT_1804b4d30 == 0) {
                          return;
                        }
                        if (DAT_1804b4d38 == 0) {
                          return;
                        }
                        uVar11 = (**(code **)(*in_stack_00000048 + 0x518))
                                           (in_stack_00000048,&stack0x00007320,0x28);
                        if (DAT_1804b4d40 == 0) {
                          return;
                        }
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,in_stack_00000058);
                        (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar11,uVar12);
                        uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar6,uVar9,uVar10);
                        DAT_1804b3260 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                      }
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b3260,in_stack_00000078);
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                         (in_stack_00000048,3,in_stack_00000088,0);
                      (**(code **)(*in_stack_00000048 + 0x570))
                                (in_stack_00000048,uVar10,0,in_stack_00000108);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                          0xdfb1eef31b4c896a);
                      (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                          in_stack_000000c8);
                      (*pcVar1)(in_stack_00000048,uVar10,2,uVar11);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar7 == '\0') {
                        (**(code **)(*in_stack_00000048 + 0x2d8))
                                  (in_stack_00000048,uVar8,in_stack_000000e8,in_stack_000000a0,uVar9
                                  );
                        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar7 == '\0') {
                          if (DAT_1804b5368 == 0) {
                            return;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x2f8))
                                            (in_stack_00000048,in_stack_00000168);
                          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar7 == '\0') {
                            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                            uVar10 = DAT_1804b59f0;
                            lVar6 = DAT_1804b59e8;
                            if (cVar7 == '\0') {
                              if (DAT_1804b3268 == 0) {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b59e8 == 0) {
                                  return;
                                }
                                uVar11 = (**(code **)(*in_stack_00000048 + 0x518))
                                                   (in_stack_00000048,&stack0x00000342,1);
                                lVar5 = DAT_1804b4d38;
                                lVar4 = DAT_1804b4d30;
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b4d30 == 0) {
                                  return;
                                }
                                if (DAT_1804b4d38 == 0) {
                                  return;
                                }
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x518);
                                FUN_180492670(&stack0x00007766,&DAT_1804a2a84,0x8a);
                                uVar12 = (*pcVar3)(in_stack_00000048,&stack0x00007766,0x45);
                                if (DAT_1804b4d40 == 0) {
                                  return;
                                }
                                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,in_stack_00000058);
                                (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar12,uVar13);
                                uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar6,uVar10,
                                                   uVar11);
                                DAT_1804b3268 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar10);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                              }
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b3268,in_stack_00000078)
                              ;
                              uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                                 (in_stack_00000048,6,in_stack_00000088,0);
                              (**(code **)(*in_stack_00000048 + 0x570))
                                        (in_stack_00000048,uVar11,0,uVar8);
                              (**(code **)(*in_stack_00000048 + 0x570))
                                        (in_stack_00000048,uVar11,1,uVar9);
                              (**(code **)(*in_stack_00000048 + 0x570))
                                        (in_stack_00000048,uVar11,2,in_stack_00000070);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_00000860);
                              (*pcVar1)(in_stack_00000048,uVar11,3,uVar8);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,0xdfcf903ad2fb3102);
                              (*pcVar1)(in_stack_00000048,uVar11,4,uVar8);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_000000c8);
                              (*pcVar1)(in_stack_00000048,uVar11,5,uVar8);
                              (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
                              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar7 == '\0') {
                                (**(code **)(*in_stack_00000048 + 0xd8))
                                          (in_stack_00000048,in_stack_000000e8);
                                cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar7 == '\0') {
                                  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar7 == '\0') {
                                    if (DAT_1804b3270 != 0) {
                                      return;
                                    }
                                    if (DAT_1804b59e8 != 0) {
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
        }
      }
    }
  }
  return;
}

