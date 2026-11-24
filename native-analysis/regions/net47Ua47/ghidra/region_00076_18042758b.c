/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=76 entry=18042758b blocks=51 maxInstructions=50000 */

/* cut edge: 0x18042758b -> 0x1804367fe */
/* cut edge: 0x18042759b -> 0x180436837 */
/* cut edge: 0x180427674 -> 0x180473ae8 */
/* cut edge: 0x180427769 -> 0x180473ae8 */
/* cut edge: 0x18042777f -> 0x180473ae8 */
/* cut edge: 0x1804277f2 -> 0x18043686f */
/* cut edge: 0x18042781e -> 0x1804368a7 */
/* cut edge: 0x18042787f -> 0x1804368f8 */
/* cut edge: 0x18042788f -> 0x180436932 */
/* cut edge: 0x18042796f -> 0x180473ae8 */
/* cut edge: 0x180427a64 -> 0x180473ae8 */
/* cut edge: 0x180427a7a -> 0x180473ae8 */
/* cut edge: 0x180427ae5 -> 0x180436969 */
/* cut edge: 0x180427b11 -> 0x1804369a4 */
/* cut edge: 0x180427b72 -> 0x1804369f5 */
/* cut edge: 0x180427b82 -> 0x180436a2f */
/* cut edge: 0x180427c62 -> 0x180473ae8 */
/* cut edge: 0x180427d59 -> 0x180473ae8 */
/* cut edge: 0x180427d6f -> 0x180473ae8 */
/* cut edge: 0x180427ddc -> 0x180436a66 */
/* cut edge: 0x180427e05 -> 0x180436a99 */
/* cut edge: 0x180427e69 -> 0x180436aea */
/* cut edge: 0x180427e79 -> 0x180436b23 */
/* cut edge: 0x180427f59 -> 0x180473ae8 */
/* cut edge: 0x18042804e -> 0x180436bf4 */
/* cut edge: 0x180428062 -> 0x180473ae8 */
/* cut edge: 0x180428078 -> 0x180473ae8 */
/* cut edge: 0x1804280e3 -> 0x180432907 */
/* cut edge: 0x18042810c -> 0x18043293a */
/* cut edge: 0x180428170 -> 0x18043298b */
/* cut edge: 0x180428180 -> 0x1804329c5 */
/* cut edge: 0x180428260 -> 0x180473ae8 */
/* cut edge: 0x18042835a -> 0x180473ae8 */
/* cut edge: 0x180428370 -> 0x180473ae8 */
/* cut edge: 0x180428386 -> 0x18042854d */
/* cut edge: 0x1804283e0 -> 0x1804329fc */
/* cut edge: 0x180428404 -> 0x180432a2f */
/* cut edge: 0x180428465 -> 0x180432a80 */
/* cut edge: 0x180428475 -> 0x180432ab9 */
/* cut edge: 0x1804284cd -> 0x18042854d */
/* target 0x18042854d original byte 0x48 replaced by RET */
/* target 0x180432907 original byte 0x48 replaced by RET */
/* target 0x18043293a original byte 0x48 replaced by RET */
/* target 0x18043298b original byte 0x48 replaced by RET */
/* target 0x1804329c5 original byte 0x48 replaced by RET */
/* target 0x1804329fc original byte 0x48 replaced by RET */
/* target 0x180432a2f original byte 0x48 replaced by RET */
/* target 0x180432a80 original byte 0x48 replaced by RET */
/* target 0x180432ab9 original byte 0x48 replaced by RET */
/* target 0x1804367fe original byte 0x48 replaced by RET */
/* target 0x180436837 original byte 0x48 replaced by RET */
/* target 0x18043686f original byte 0x48 replaced by RET */
/* target 0x1804368a7 original byte 0x48 replaced by RET */
/* target 0x1804368f8 original byte 0x48 replaced by RET */
/* target 0x180436932 original byte 0x48 replaced by RET */
/* target 0x180436969 original byte 0x48 replaced by RET */
/* target 0x1804369a4 original byte 0x48 replaced by RET */
/* target 0x1804369f5 original byte 0x48 replaced by RET */
/* target 0x180436a2f original byte 0x48 replaced by RET */
/* target 0x180436a66 original byte 0x48 replaced by RET */
/* target 0x180436a99 original byte 0x48 replaced by RET */
/* target 0x180436aea original byte 0x48 replaced by RET */
/* target 0x180436b23 original byte 0x48 replaced by RET */
/* target 0x180436bf4 original byte 0x4c replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


code * region_00076_18042758b(void)

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
  longlong unaff_RSI;
  longlong *in_stack_00000048;
  code *in_stack_00000058;
  undefined8 in_stack_00000060;
  ulonglong in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  code *in_stack_000000d0;
  undefined8 in_stack_000000d8;
  longlong in_stack_00000118;
  
  if (DAT_1804b4d38 == 0) {
    return in_RAX;
  }
  pcVar4 = (code *)(**(code **)(unaff_RSI + 0x518))(in_stack_00000048,&stack0x00001ec0,5);
  if (DAT_1804b4d40 == 0) {
    return pcVar4;
  }
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
  (*in_stack_000000d0)(in_stack_00000048);
  uVar5 = (*in_stack_00000058)
                    (in_stack_00000048,in_stack_00000088,in_stack_00000060,in_stack_00000070,
                     in_stack_00000078);
  DAT_1804b3df8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804b3df8,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x564d);
  (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_000000a8,uVar6)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar4 == '\0') {
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
      lVar11 = DAT_1804b5a08;
      uVar5 = DAT_1804b59f0;
      if ((char)pcVar4 == '\0') {
        if (DAT_1804b3e00 == 0) {
          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return (code *)0x0;
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000054e,1);
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
                                     (in_stack_00000048,&stack0x00001eb0,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar9;
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar7);
          uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar11,uVar5,uVar6);
          DAT_1804b3e00 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b3e00,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x6c94);
        (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           (longlong)in_stack_00000068 % 0xf2efc ^ in_stack_000000c8 ^
                           0xb4ae9e0141749caa);
        (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar4 == '\0') {
          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)pcVar4 == '\0') {
            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
            lVar11 = DAT_1804b5a08;
            uVar5 = DAT_1804b59f0;
            if ((char)pcVar4 == '\0') {
              if (DAT_1804b3e08 == 0) {
                pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return (code *)0x0;
                }
                uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x0000054c,1);
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
                                           (in_stack_00000048,&stack0x00001ea0,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar9;
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000088);
                (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar7);
                uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar11,uVar5,uVar6);
                DAT_1804b3e08 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b3e08,in_stack_000000a0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x275a);
              (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 (longlong)in_stack_00000068 % 0x7bc18 ^ in_stack_000000c8 ^
                                 0xc01f9bac677e881d);
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
                  lVar11 = DAT_1804b5a08;
                  uVar5 = DAT_1804b59f0;
                  if ((char)pcVar4 == '\0') {
                    if (DAT_1804b3e10 == 0) {
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b5a08 == 0) {
                        return (code *)0x0;
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x0000054a,1);
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
                                                 (in_stack_00000048,&stack0x00001e90,5);
                      if (DAT_1804b4d40 == 0) {
                        return pcVar9;
                      }
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,in_stack_00000088);
                      (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar7);
                      uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar11,uVar5,uVar6);
                      DAT_1804b3e10 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                    }
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b3e10,in_stack_000000a0);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x8a1);
                    (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       (longlong)in_stack_00000068 % 0xaa245 ^ in_stack_000000c8 ^
                                       0xbd4b1eb14a50d37e);
                    (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                    lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                       (in_stack_00000048,uVar5,in_stack_000000d8);
                    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)pcVar4 == '\0') {
                      if (in_stack_00000118 == 0) {
                        return pcVar4;
                      }
                      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)pcVar4 == '\0') {
                        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                        lVar1 = DAT_1804b5a08;
                        uVar5 = DAT_1804b59f0;
                        if ((char)pcVar4 == '\0') {
                          if (DAT_1804b3e18 == 0) {
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b5a08 == 0) {
                              return (code *)0x0;
                            }
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x00000548,1);
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
                                                       (in_stack_00000048,&stack0x00001e80,5);
                            if (DAT_1804b4d40 == 0) {
                              return pcVar9;
                            }
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,in_stack_00000088);
                            (*pcVar8)(in_stack_00000048,lVar2,lVar3,pcVar9,uVar7);
                            uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar5,uVar6)
                            ;
                            DAT_1804b3e18 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                          }
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b3e18,in_stack_000000a0);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000090,0);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                             0x7c7);
                          (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             (longlong)((uVar10 ^ in_stack_00000068) - lVar11) %
                                             0x500c1 ^ in_stack_000000c8 ^ 0xbd296db8b1d68959);
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
                              lVar11 = DAT_1804b5a08;
                              uVar5 = DAT_1804b59f0;
                              if ((char)pcVar4 == '\0') {
                                pcVar4 = (code *)0xfb3ae9b6900dd602;
                                if (DAT_1804b3e20 == 0) {
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b5a08 == 0) {
                                    return (code *)0x0;
                                  }
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x00000546,1);
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
                                                             (in_stack_00000048,&stack0x00001e70,5);
                                  if (DAT_1804b4d40 == 0) {
                                    return pcVar9;
                                  }
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,in_stack_00000088);
                                  (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar7);
                                  uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar11,uVar5
                                                    ,uVar6);
                                  DAT_1804b3e20 =
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

