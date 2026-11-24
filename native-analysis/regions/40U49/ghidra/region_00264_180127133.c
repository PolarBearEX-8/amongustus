/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=264 entry=180127133 blocks=40 maxInstructions=100000 */

/* cut edge: 0x180127133 -> 0x18014d3db */
/* cut edge: 0x18012722a -> 0x18014d3db */
/* cut edge: 0x180127240 -> 0x18014d3db */
/* cut edge: 0x1801273f3 -> 0x18014d3db */
/* cut edge: 0x1801274ea -> 0x18014d3db */
/* cut edge: 0x180127500 -> 0x18014d3db */
/* cut edge: 0x1801276b9 -> 0x18014d3db */
/* cut edge: 0x1801277ae -> 0x18014d3db */
/* cut edge: 0x1801277c4 -> 0x18014d3db */
/* cut edge: 0x180127984 -> 0x18014d3db */
/* cut edge: 0x180127aa5 -> 0x18012aa08 */
/* cut edge: 0x180127ab4 -> 0x1801253d7 */
/* cut edge: 0x180127ac3 -> 0x18013d50a */
/* cut edge: 0x180127ae2 -> 0x18014d3db */
/* cut edge: 0x180127b08 -> 0x18013d55b */
/* cut edge: 0x180127b31 -> 0x18014d3db */
/* cut edge: 0x180127b57 -> 0x18013d591 */
/* cut edge: 0x180127b76 -> 0x18014d3db */
/* cut edge: 0x180127b9a -> 0x18013d5e2 */
/* cut edge: 0x180127bbe -> 0x18014d3db */
/* cut edge: 0x180127be2 -> 0x18014d3db */
/* cut edge: 0x180127bf8 -> 0x18014d3db */
/* cut edge: 0x180127dc4 -> 0x18014d3db */
/* cut edge: 0x180127ea2 -> 0x18013d618 */
/* cut edge: 0x180127ec1 -> 0x18014d3db */
/* cut edge: 0x180127eeb -> 0x18014d3db */
/* cut edge: 0x180127f01 -> 0x18014d3db */
/* cut edge: 0x180128104 -> 0x18014d3db */
/* cut edge: 0x180128104 -> 0x180128232 */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x180128232 original byte 0x48 replaced by RET */
/* target 0x18012aa08 original byte 0x4c replaced by RET */
/* target 0x18013d50a original byte 0x48 replaced by RET */
/* target 0x18013d55b original byte 0x48 replaced by RET */
/* target 0x18013d591 original byte 0x48 replaced by RET */
/* target 0x18013d5e2 original byte 0x48 replaced by RET */
/* target 0x18013d618 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00264_180127133(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  ulonglong uVar13;
  longlong lVar14;
  undefined8 uVar15;
  ulonglong uVar16;
  ulonglong unaff_R14;
  ulonglong uVar17;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000080;
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
  undefined8 in_stack_00000138;
  undefined8 in_stack_000001d0;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7fb9);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        uVar16 = (longlong)unaff_R14 % 0xda592;
        if (DAT_1804ac928 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar7 = FUN_18014d730(in_stack_00000048);
          uVar6 = DAT_1804b5208;
          uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000009f8,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000042e8,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
          DAT_1804ac928 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          unaff_R14 = in_stack_00000080;
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ac928,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1576);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           uVar16 ^ in_stack_000000c0 ^ 0xfaaf1627bfb0ce93);
        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              uVar16 = unaff_R14;
              if (DAT_1804ac930 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar7 = FUN_18014d730(in_stack_00000048);
                uVar6 = DAT_1804b5208;
                uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000009f6,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000042d8,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                DAT_1804ac930 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                uVar16 = in_stack_00000080;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ac930,in_stack_000000b0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x120);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)unaff_R14 % 0xba85f ^ in_stack_000000c0 ^
                                 0xcf12530e69db20f6);
              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
              uVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                 (in_stack_00000048,uVar6,in_stack_000000d8);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    uVar17 = uVar16;
                    if (DAT_1804ac938 == 0) {
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar7 = FUN_18014d730(in_stack_00000048);
                      uVar6 = DAT_1804b5208;
                      uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000009f4,1);
                      lVar2 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000042c8,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                      DAT_1804ac938 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                      uVar17 = in_stack_00000080;
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ac938,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x663a)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)uVar16 % 0xd7b60 ^ in_stack_000000c0 ^
                                       0xb7df47fc054667c1);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                       (in_stack_00000048,uVar6,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    lVar2 = DAT_1804b5270;
                    if (cVar5 == '\0') {
                      lVar14 = (uVar13 ^ uVar17) - lVar14;
                      if ((longlong)in_stack_000000c0 < 0) {
                        return;
                      }
                      if (in_stack_000000f8 == 0) {
                        return;
                      }
                      if (DAT_1804b5270 == 0) {
                        return;
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0xd8))
                                        (in_stack_00000048,DAT_1804b5270);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        if (DAT_1804b5278 == 0) {
                          return;
                        }
                        (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar6,lVar2);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        lVar2 = DAT_1804b5020;
                        if (cVar5 == '\0') {
                          if (DAT_1804b5020 == 0) {
                            return;
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0xd8))
                                            (in_stack_00000048,DAT_1804b5020);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            if (DAT_1804b5028 == 0) {
                              return;
                            }
                            (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar6,lVar2)
                            ;
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  if (DAT_1804ac940 == 0) {
                                    lVar2 = *in_stack_00000048;
                                    pcVar1 = *(code **)(lVar2 + 0x390);
                                    uVar8 = FUN_18001ed20(in_stack_00000048);
                                    uVar7 = DAT_1804b5208;
                                    uVar9 = (**(code **)(lVar2 + 0x518))
                                                      (in_stack_00000048,&stack0x000009f2,1);
                                    lVar2 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar2 + 0x390);
                                    uVar10 = FUN_1800024f0(in_stack_00000048);
                                    uVar11 = FUN_180002550(in_stack_00000048);
                                    uVar12 = (**(code **)(lVar2 + 0x518))
                                                       (in_stack_00000048,&stack0x00006d60,0x16);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar15 = FUN_1800025a0(in_stack_00000048);
                                    uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar15);
                                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar15);
                                    uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,
                                                      uVar7,uVar9);
                                    DAT_1804ac940 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar7);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar7);
                                  }
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,DAT_1804ac940,
                                                     in_stack_000000b0);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_000000a0,0);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_000000c8,
                                                     in_stack_000000d0,0x72d4);
                                  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000098,
                                                     in_stack_000000b8,
                                                     lVar14 % 0x2325d ^ in_stack_000000c0 ^
                                                     0x8f708baa809644c0);
                                  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar7,in_stack_000000a8,uVar8
                                                    );
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar5 == '\0') {
                                    if (DAT_1804b5030 == 0) {
                                      return;
                                    }
                                    (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar6,DAT_1804b5030,uVar7);
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar5 == '\0') {
                                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar5 == '\0') {
                                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                        if (cVar5 == '\0') {
                                          if (DAT_1804ac948 == 0) {
                                            lVar2 = *in_stack_00000048;
                                            pcVar1 = *(code **)(lVar2 + 0x390);
                                            uVar7 = FUN_18001e7d0(in_stack_00000048);
                                            uVar6 = DAT_1804b5208;
                                            uVar8 = (**(code **)(lVar2 + 0x518))
                                                              (in_stack_00000048,&stack0x000009f0,1)
                                            ;
                                            lVar2 = *in_stack_00000048;
                                            pcVar3 = *(code **)(lVar2 + 0x390);
                                            uVar9 = FUN_1800024f0(in_stack_00000048);
                                            uVar10 = FUN_180002550(in_stack_00000048);
                                            uVar11 = (**(code **)(lVar2 + 0x518))
                                                               (in_stack_00000048,&stack0x000073b0,
                                                                0x29);
                                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                            uVar12 = FUN_1800025a0(in_stack_00000048);
                                            uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,
                                                               uVar12);
                                            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,
                                                              uVar7,uVar6,uVar8);
                                            DAT_1804ac948 =
                                                 (**(code **)(*in_stack_00000048 + 0xa8))
                                                           (in_stack_00000048,uVar6);
                                            (**(code **)(*in_stack_00000048 + 0xb8))
                                                      (in_stack_00000048,uVar6);
                                          }
                                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                            (in_stack_00000048,DAT_1804ac948,
                                                             in_stack_000000b0);
                                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                            (in_stack_00000048,4,in_stack_000000a0,0
                                                            );
                                          (**(code **)(*in_stack_00000048 + 0x570))
                                                    (in_stack_00000048,uVar7,0,in_stack_00000138);
                                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                            (in_stack_00000048,in_stack_00000098,
                                                             in_stack_000000b8,in_stack_000001d0);
                                          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                            (in_stack_00000048,in_stack_00000098,
                                                             in_stack_000000b8,
                                                             lVar14 % 0xa82c5 ^ 0xe9d78abf5e479fb6);
                                          (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
                                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                            (in_stack_00000048,in_stack_00000098,
                                                             in_stack_000000b8,in_stack_000000c0);
                                          (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
                                          (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar6,in_stack_000000a8,uVar7
                                                    );
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
      }
    }
  }
  return;
}

