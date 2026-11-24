/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=72 entry=18042361d blocks=50 maxInstructions=50000 */

/* cut edge: 0x18042361d -> 0x180473ae8 */
/* cut edge: 0x180423633 -> 0x180473ae8 */
/* cut edge: 0x1804236a6 -> 0x18043410c */
/* cut edge: 0x1804236ca -> 0x18043413f */
/* cut edge: 0x18042372b -> 0x180434190 */
/* cut edge: 0x18042373b -> 0x1804341c9 */
/* cut edge: 0x180423813 -> 0x180473ae8 */
/* cut edge: 0x180423908 -> 0x180473ae8 */
/* cut edge: 0x18042391e -> 0x180473ae8 */
/* cut edge: 0x18042398e -> 0x1804344e7 */
/* cut edge: 0x1804239b2 -> 0x18043451a */
/* cut edge: 0x180423a13 -> 0x18043456b */
/* cut edge: 0x180423a23 -> 0x1804345a4 */
/* cut edge: 0x180423afb -> 0x180473ae8 */
/* cut edge: 0x180423bf2 -> 0x180473ae8 */
/* cut edge: 0x180423c08 -> 0x180473ae8 */
/* cut edge: 0x180423c78 -> 0x1804347cc */
/* cut edge: 0x180423ca1 -> 0x180434802 */
/* cut edge: 0x180423cfd -> 0x180434853 */
/* cut edge: 0x180423d0d -> 0x18043488c */
/* cut edge: 0x180423de8 -> 0x180473ae8 */
/* cut edge: 0x180423edd -> 0x1803dd9b0 */
/* cut edge: 0x180423ef0 -> 0x1803dd671 */
/* cut edge: 0x180423eff -> 0x180473ae8 */
/* cut edge: 0x180423f15 -> 0x180473ae8 */
/* cut edge: 0x180423f7d -> 0x18042b804 */
/* cut edge: 0x180423fa1 -> 0x18042b837 */
/* cut edge: 0x180424002 -> 0x18042b888 */
/* cut edge: 0x180424012 -> 0x18042b8c1 */
/* cut edge: 0x1804240eb -> 0x180473ae8 */
/* cut edge: 0x1804241e4 -> 0x180473ae8 */
/* cut edge: 0x1804241fa -> 0x180473ae8 */
/* cut edge: 0x180424262 -> 0x18042d2d8 */
/* cut edge: 0x180424286 -> 0x18042d30b */
/* cut edge: 0x1804242e7 -> 0x18042d35c */
/* cut edge: 0x1804242f7 -> 0x18042d395 */
/* cut edge: 0x1804243d0 -> 0x180473ae8 */
/* cut edge: 0x1804244c5 -> 0x180473ae8 */
/* cut edge: 0x1804244db -> 0x180473ae8 */
/* cut edge: 0x1804244f1 -> 0x1804246c5 */
/* cut edge: 0x1804244f1 -> 0x18042454b */
/* target 0x1803dd671 original byte 0x48 replaced by RET */
/* target 0x1803dd9b0 original byte 0x48 replaced by RET */
/* target 0x18042454b original byte 0x48 replaced by RET */
/* target 0x1804246c5 original byte 0x48 replaced by RET */
/* target 0x18042b804 original byte 0x48 replaced by RET */
/* target 0x18042b837 original byte 0x48 replaced by RET */
/* target 0x18042b888 original byte 0x48 replaced by RET */
/* target 0x18042b8c1 original byte 0x48 replaced by RET */
/* target 0x18042d2d8 original byte 0x48 replaced by RET */
/* target 0x18042d30b original byte 0x48 replaced by RET */
/* target 0x18042d35c original byte 0x48 replaced by RET */
/* target 0x18042d395 original byte 0x48 replaced by RET */
/* target 0x18043410c original byte 0x48 replaced by RET */
/* target 0x18043413f original byte 0x48 replaced by RET */
/* target 0x180434190 original byte 0x48 replaced by RET */
/* target 0x1804341c9 original byte 0x48 replaced by RET */
/* target 0x1804344e7 original byte 0x48 replaced by RET */
/* target 0x18043451a original byte 0x48 replaced by RET */
/* target 0x18043456b original byte 0x48 replaced by RET */
/* target 0x1804345a4 original byte 0x48 replaced by RET */
/* target 0x1804347cc original byte 0x48 replaced by RET */
/* target 0x180434802 original byte 0x48 replaced by RET */
/* target 0x180434853 original byte 0x48 replaced by RET */
/* target 0x18043488c original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


code * region_00072_18042361d(void)

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
  longlong lVar11;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
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
  undefined8 in_stack_00000120;
  int in_stack_00000130;
  
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b5a08;
    uVar9 = DAT_1804b59f0;
    if ((char)pcVar4 == '\0') {
      if (DAT_1804b3d50 == 0) {
        pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return (code *)0x0;
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000057a,1);
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
                                   (in_stack_00000048,&stack0x00002000,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar7;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
        uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar11,uVar9,uVar5);
        DAT_1804b3d50 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b3d50,in_stack_000000a0);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0xa76);
      (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)in_stack_00000058 % 0xc391 ^ in_stack_000000c8 ^
                         0x913762d28f29a5bf);
      (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar4 == '\0') {
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar4 == '\0') {
          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          lVar11 = DAT_1804b5a08;
          uVar9 = DAT_1804b59f0;
          if ((char)pcVar4 == '\0') {
            if (DAT_1804b3d58 == 0) {
              pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return (code *)0x0;
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000578,1);
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
                                         (in_stack_00000048,&stack0x00001ff0,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar7;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
              uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar11,uVar9,uVar5);
              DAT_1804b3d58 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b3d58,in_stack_000000a0);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0xd32);
            (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               (longlong)in_stack_00000058 % 0xdc990 ^ in_stack_000000c8 ^
                               0xde681df7f087763f);
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
                lVar11 = DAT_1804b5a08;
                uVar9 = DAT_1804b59f0;
                if ((char)pcVar4 == '\0') {
                  if (DAT_1804b3d60 == 0) {
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b5a08 == 0) {
                      return pcVar4;
                    }
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000576,1);
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
                                               (in_stack_00000048,&stack0x00001fe0,5);
                    if (DAT_1804b4d40 == 0) {
                      return pcVar7;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000088);
                    (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
                    uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar11,uVar9,uVar5);
                    DAT_1804b3d60 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                  }
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b3d60,in_stack_000000a0);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2bdb);
                  (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     (longlong)in_stack_00000058 % 0xe1184 ^ in_stack_000000c8 ^
                                     0xded7a5896a956ce6);
                  (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
                  lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                     (in_stack_00000048,uVar9,in_stack_000000d8);
                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)pcVar4 == '\0') {
                    lVar11 = (uVar10 ^ in_stack_00000058) - lVar11;
                    if (in_stack_00000130 < 0) {
                      return pcVar4;
                    }
                    if (in_stack_00000118 == 0) {
                      return pcVar4;
                    }
                    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)pcVar4 == '\0') {
                      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      lVar1 = DAT_1804b59f8;
                      uVar9 = DAT_1804b59f0;
                      if ((char)pcVar4 == '\0') {
                        if (DAT_1804b3d68 == 0) {
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b59f8 == 0) {
                            return (code *)0x0;
                          }
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000574,1);
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
                                                     (in_stack_00000048,&stack0x00001fd0,5);
                          if (DAT_1804b4d40 == 0) {
                            return pcVar7;
                          }
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000088);
                          (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                          uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar9,uVar5);
                          DAT_1804b3d68 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                        }
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b3d68,in_stack_000000a0);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x1af9);
                        (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           lVar11 % 0x72210 ^ in_stack_000000c8 ^ 0xed9465c061950047
                                          );
                        (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
                        (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar9,in_stack_00000120);
                        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                   (in_stack_00000048);
                        if ((char)pcVar4 == '\0') {
                          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)pcVar4 == '\0') {
                            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                            lVar1 = DAT_1804b5a08;
                            uVar9 = DAT_1804b59f0;
                            if ((char)pcVar4 == '\0') {
                              if (DAT_1804b3d70 == 0) {
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b5a08 == 0) {
                                  return (code *)0x0;
                                }
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00000572,1);
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
                                                           (in_stack_00000048,&stack0x00001fc0,5);
                                if (DAT_1804b4d40 == 0) {
                                  return pcVar7;
                                }
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000088);
                                (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                                uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar9,
                                                  uVar5);
                                DAT_1804b3d70 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar9);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                              }
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b3d70,in_stack_000000a0);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000090,0);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000b8,
                                                 in_stack_000000c0,0x19dd);
                              (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 lVar11 % 0xa1bee ^ in_stack_000000c8 ^
                                                 0x94c0f08d88e389f9);
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
                                    if (DAT_1804b3d78 == 0) {
                                      return (code *)0xc1d54cfa98339077;
                                    }
                                    return (code *)0xc1d54cfa98339077;
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

