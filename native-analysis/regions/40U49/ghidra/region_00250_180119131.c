/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=250 entry=180119131 blocks=49 maxInstructions=100000 */

/* cut edge: 0x180119131 -> 0x18014d3db */
/* cut edge: 0x180119147 -> 0x18014d3db */
/* cut edge: 0x1801191ba -> 0x180121841 */
/* cut edge: 0x1801191de -> 0x180121874 */
/* cut edge: 0x18011923f -> 0x1801218c5 */
/* cut edge: 0x18011924f -> 0x1801218fe */
/* cut edge: 0x180119327 -> 0x18014d3db */
/* cut edge: 0x18011941c -> 0x18014d3db */
/* cut edge: 0x180119432 -> 0x18014d3db */
/* cut edge: 0x18011949d -> 0x1801220dd */
/* cut edge: 0x1801194c1 -> 0x180122110 */
/* cut edge: 0x18011951f -> 0x18012215e */
/* cut edge: 0x180119537 -> 0x180122197 */
/* cut edge: 0x180119611 -> 0x18014d3db */
/* cut edge: 0x180119706 -> 0x1800e7b59 */
/* cut edge: 0x18011971d -> 0x18014d3db */
/* cut edge: 0x180119733 -> 0x18014d3db */
/* cut edge: 0x18011979b -> 0x180123200 */
/* cut edge: 0x1801197bf -> 0x180123233 */
/* cut edge: 0x180119820 -> 0x180123284 */
/* cut edge: 0x180119830 -> 0x1801232bd */
/* cut edge: 0x180119908 -> 0x18014d3db */
/* cut edge: 0x1801199fc -> 0x18014d3db */
/* cut edge: 0x180119a12 -> 0x18014d3db */
/* cut edge: 0x180119a71 -> 0x1801232f4 */
/* cut edge: 0x180119a95 -> 0x18012332a */
/* cut edge: 0x180119af1 -> 0x180123378 */
/* cut edge: 0x180119b09 -> 0x1801233b1 */
/* cut edge: 0x180119c00 -> 0x18014d3db */
/* cut edge: 0x180119dba -> 0x18014d3db */
/* cut edge: 0x180119dd0 -> 0x18014d3db */
/* cut edge: 0x180119e38 -> 0x1801233e8 */
/* cut edge: 0x180119e5c -> 0x18012341b */
/* cut edge: 0x180119ebd -> 0x18012346c */
/* cut edge: 0x180119ecd -> 0x1801234a5 */
/* cut edge: 0x180119fa5 -> 0x18014d3db */
/* cut edge: 0x18011a09a -> 0x18014d3db */
/* cut edge: 0x18011a0b0 -> 0x18014d3db */
/* cut edge: 0x18011a0c6 -> 0x18011a29a */
/* cut edge: 0x18011a0c6 -> 0x18011a120 */
/* target 0x1800e7b59 original byte 0x8b replaced by RET */
/* target 0x18011a120 original byte 0x48 replaced by RET */
/* target 0x18011a29a original byte 0x48 replaced by RET */
/* target 0x180121841 original byte 0x48 replaced by RET */
/* target 0x180121874 original byte 0x48 replaced by RET */
/* target 0x1801218c5 original byte 0x48 replaced by RET */
/* target 0x1801218fe original byte 0x48 replaced by RET */
/* target 0x1801220dd original byte 0x48 replaced by RET */
/* target 0x180122110 original byte 0x48 replaced by RET */
/* target 0x18012215e original byte 0x48 replaced by RET */
/* target 0x180122197 original byte 0x48 replaced by RET */
/* target 0x180123200 original byte 0x48 replaced by RET */
/* target 0x180123233 original byte 0x48 replaced by RET */
/* target 0x180123284 original byte 0x48 replaced by RET */
/* target 0x1801232bd original byte 0x48 replaced by RET */
/* target 0x1801232f4 original byte 0x48 replaced by RET */
/* target 0x18012332a original byte 0x48 replaced by RET */
/* target 0x180123378 original byte 0x48 replaced by RET */
/* target 0x1801233b1 original byte 0x48 replaced by RET */
/* target 0x1801233e8 original byte 0x48 replaced by RET */
/* target 0x18012341b original byte 0x48 replaced by RET */
/* target 0x18012346c original byte 0x48 replaced by RET */
/* target 0x1801234a5 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00250_180119131(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  undefined4 uVar4;
  code *pcVar5;
  undefined8 uVar6;
  code *pcVar7;
  code *pcVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  ulonglong uVar11;
  longlong lVar12;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000078;
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
  undefined8 in_stack_00000108;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_00000c90;
  ulonglong in_stack_00005648;
  
  pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)pcVar5 == '\0') {
    pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar12 = DAT_1804b52e8;
    uVar10 = DAT_1804b5208;
    if ((char)pcVar5 == '\0') {
      if (DAT_1804ad360 == 0) {
        pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b52e8 == 0) {
          return (code *)0x0;
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000075c,1);
        lVar2 = DAT_1804b4d38;
        lVar1 = DAT_1804b4d30;
        pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return pcVar7;
        }
        if (DAT_1804b4d38 == 0) {
          return pcVar7;
        }
        pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                   (in_stack_00000048,&stack0x00003258,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar8;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
        uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000090,lVar12,uVar10,uVar6);
        DAT_1804ad360 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804ad360,in_stack_000000b0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x310a);
      (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
      pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         (longlong)in_stack_00000078 % 0x77de0 ^ in_stack_000000c0 ^
                         0xf110f29fc4ed2d60);
      (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
      uVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8)
      ;
      pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar5 == '\0') {
        pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar5 == '\0') {
          pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          lVar12 = DAT_1804b52e8;
          uVar10 = DAT_1804b5208;
          if ((char)pcVar5 == '\0') {
            if (DAT_1804ad368 == 0) {
              pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b52e8 == 0) {
                return (code *)0x0;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x0000075a,1);
              lVar2 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar7;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar7;
              }
              pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x00003248,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar8;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090)
              ;
              (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
              uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000090,lVar12,uVar10,uVar6);
              DAT_1804ad368 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804ad368,in_stack_000000b0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x125e);
            (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               (longlong)in_stack_00000078 % 0x984f6 ^ in_stack_000000c0 ^
                               0xb58cdfc076294762);
            (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
            lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                               (in_stack_00000048,uVar10,in_stack_000000d8);
            pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar5 == '\0') {
              lVar12 = (uVar11 ^ in_stack_00000078) - lVar12;
              if (in_stack_000000f8 == 0) {
                return pcVar5;
              }
              pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)pcVar5 == '\0') {
                pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                lVar1 = DAT_1804b5258;
                uVar10 = DAT_1804b5208;
                if ((char)pcVar5 == '\0') {
                  if (DAT_1804ad370 == 0) {
                    pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b5258 == 0) {
                      return (code *)0x0;
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000758,1);
                    lVar3 = DAT_1804b4d38;
                    lVar2 = DAT_1804b4d30;
                    pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b4d30 == 0) {
                      return pcVar7;
                    }
                    if (DAT_1804b4d38 == 0) {
                      return pcVar7;
                    }
                    pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                               (in_stack_00000048,&stack0x00003238,5);
                    if (DAT_1804b4d40 == 0) {
                      return pcVar8;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000090);
                    (*pcVar7)(in_stack_00000048,lVar2,lVar3,pcVar8,uVar9);
                    uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000090,lVar1,uVar10,uVar6);
                    DAT_1804ad370 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804ad370,in_stack_000000b0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6b6c);
                  (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
                  pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     lVar12 % 0x77fe9 ^ in_stack_000000c0 ^ 0xdc003c5f0cd2713a);
                  (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
                  uVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar10,in_stack_00000108);
                  pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)pcVar5 == '\0') {
                    pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)pcVar5 == '\0') {
                      pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      uVar10 = DAT_1804b5208;
                      lVar1 = DAT_1804b5200;
                      if ((char)pcVar5 == '\0') {
                        if (DAT_1804ad378 == 0) {
                          pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b5200 == 0) {
                            return pcVar5;
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000756,1);
                          lVar3 = DAT_1804b4d38;
                          lVar2 = DAT_1804b4d30;
                          pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b4d30 == 0) {
                            return pcVar7;
                          }
                          if (DAT_1804b4d38 == 0) {
                            return pcVar7;
                          }
                          pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                     (in_stack_00000048,&stack0x00006070,0x1a);
                          if (DAT_1804b4d40 == 0) {
                            return pcVar8;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000090);
                          (*pcVar7)(in_stack_00000048,lVar2,lVar3,pcVar8,uVar9);
                          uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000090,lVar1,uVar10,uVar6)
                          ;
                          DAT_1804ad378 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804ad378,in_stack_000000b0);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,6,in_stack_000000a0,0);
                        (**(code **)(*in_stack_00000048 + 0x570))
                                  (in_stack_00000048,uVar6,0,in_stack_000001a0);
                        pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,2);
                        (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
                        pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_00000c90);
                        (*pcVar5)(in_stack_00000048,uVar6,2,uVar9);
                        pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           uVar4);
                        (*pcVar5)(in_stack_00000048,uVar6,3,uVar9);
                        pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           lVar12 % 0xd12e9 ^ in_stack_00005648);
                        (*pcVar5)(in_stack_00000048,uVar6,4,uVar9);
                        pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_000000c0);
                        (*pcVar5)(in_stack_00000048,uVar6,5,uVar9);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
                        (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar10,in_stack_00000108);
                        pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                   (in_stack_00000048);
                        if ((char)pcVar5 == '\0') {
                          pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)pcVar5 == '\0') {
                            pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                            lVar1 = DAT_1804b52e8;
                            uVar10 = DAT_1804b5208;
                            if ((char)pcVar5 == '\0') {
                              if (DAT_1804ad380 == 0) {
                                pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b52e8 == 0) {
                                  return (code *)0x0;
                                }
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00000754,1);
                                lVar3 = DAT_1804b4d38;
                                lVar2 = DAT_1804b4d30;
                                pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b4d30 == 0) {
                                  return pcVar7;
                                }
                                if (DAT_1804b4d38 == 0) {
                                  return pcVar7;
                                }
                                pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                           (in_stack_00000048,&stack0x00003228,5);
                                if (DAT_1804b4d40 == 0) {
                                  return pcVar8;
                                }
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000090);
                                (*pcVar7)(in_stack_00000048,lVar2,lVar3,pcVar8,uVar9);
                                uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000090,lVar1,uVar10,
                                                   uVar6);
                                DAT_1804ad380 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar10);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                              }
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804ad380,in_stack_000000b0)
                              ;
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_000000a0,0);
                              pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000c8,
                                                 in_stack_000000d0,0x669d);
                              (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
                              pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,
                                                 lVar12 % 0x8b4ae ^ in_stack_000000c0 ^
                                                 0xadaaec3da52d70a1);
                              (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,uVar10,in_stack_000000a8,uVar6);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar10,in_stack_000000d8);
                              pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                         (in_stack_00000048);
                              if ((char)pcVar5 == '\0') {
                                pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)pcVar5 == '\0') {
                                  pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)pcVar5 == '\0') {
                                    if (DAT_1804ad388 == 0) {
                                      return (code *)0xc873faf5fd3ea47d;
                                    }
                                    return (code *)0xc873faf5fd3ea47d;
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
  return pcVar5;
}

