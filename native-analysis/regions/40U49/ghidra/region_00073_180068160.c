/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=73 entry=180068160 blocks=43 maxInstructions=100000 */

/* cut edge: 0x180068160 -> 0x18014d3db */
/* cut edge: 0x180068255 -> 0x18014d3db */
/* cut edge: 0x18006826b -> 0x18014d3db */
/* cut edge: 0x1800682d9 -> 0x180072610 */
/* cut edge: 0x180068302 -> 0x180072643 */
/* cut edge: 0x180068366 -> 0x180072694 */
/* cut edge: 0x180068376 -> 0x1800726cd */
/* cut edge: 0x180068456 -> 0x18014d3db */
/* cut edge: 0x180068555 -> 0x180110f4a */
/* cut edge: 0x180068588 -> 0x18006a00b */
/* cut edge: 0x180068597 -> 0x18014d3db */
/* cut edge: 0x1800685ad -> 0x18014d3db */
/* cut edge: 0x180068614 -> 0x180073f13 */
/* cut edge: 0x180068640 -> 0x180073f4e */
/* cut edge: 0x18006869f -> 0x180073f9f */
/* cut edge: 0x1800686af -> 0x180073fd8 */
/* cut edge: 0x1800687a7 -> 0x180070d81 */
/* cut edge: 0x18006881f -> 0x180070dd3 */
/* cut edge: 0x18006882f -> 0x18014d3db */
/* cut edge: 0x180068941 -> 0x18006a006 */
/* cut edge: 0x180068950 -> 0x18006945f */
/* cut edge: 0x180068969 -> 0x18014d3db */
/* cut edge: 0x180068971 -> 0x18014d3db */
/* cut edge: 0x180068b1f -> 0x18014d3db */
/* cut edge: 0x180068c14 -> 0x18014d3db */
/* cut edge: 0x180068c2a -> 0x18014d3db */
/* cut edge: 0x180068de2 -> 0x18014d3db */
/* cut edge: 0x180068edc -> 0x18014d3db */
/* cut edge: 0x180068ef2 -> 0x18014d3db */
/* cut edge: 0x1800690a2 -> 0x18014d3db */
/* cut edge: 0x1800690a2 -> 0x180069197 */
/* target 0x180069197 original byte 0x48 replaced by RET */
/* target 0x18006945f original byte 0x84 replaced by RET */
/* target 0x18006a006 original byte 0x48 replaced by RET */
/* target 0x18006a00b original byte 0x48 replaced by RET */
/* target 0x180070d81 original byte 0x48 replaced by RET */
/* target 0x180070dd3 original byte 0x48 replaced by RET */
/* target 0x180072610 original byte 0x48 replaced by RET */
/* target 0x180072643 original byte 0x48 replaced by RET */
/* target 0x180072694 original byte 0x48 replaced by RET */
/* target 0x1800726cd original byte 0x48 replaced by RET */
/* target 0x180073f13 original byte 0x48 replaced by RET */
/* target 0x180073f4e original byte 0x48 replaced by RET */
/* target 0x180073f9f original byte 0x48 replaced by RET */
/* target 0x180073fd8 original byte 0x48 replaced by RET */
/* target 0x180110f4a original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00073_180068160(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  ulonglong uVar10;
  undefined8 uVar11;
  longlong lVar12;
  longlong lVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000060;
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
  undefined8 in_stack_000001d8;
  undefined8 in_stack_00000c70;
  undefined8 in_stack_00000c78;
  longlong in_stack_00000ce8;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x35ab);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8)
  ;
  uVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar12 = DAT_1804b52e8;
      uVar7 = DAT_1804b5208;
      if (cVar6 == '\0') {
        if (DAT_1804ae320 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000364,1);
          lVar4 = DAT_1804b4d38;
          lVar13 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000017e8,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar2)(in_stack_00000048,lVar13,lVar4,uVar9,uVar11);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar7,uVar8);
          DAT_1804ae320 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae320,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x63a2);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000060 % 0xc4604 ^ in_stack_000000c0 ^
                           0xe7933449de31d96c);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
        lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                           (in_stack_00000048,uVar7,in_stack_000000d8);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          lVar12 = lVar12 + (uVar10 ^ in_stack_00000060);
          if (in_stack_000000f8 == 0) {
            return;
          }
          if ((longlong)in_stack_000000c0 < 1) {
            return;
          }
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            uVar7 = DAT_1804b5208;
            lVar13 = DAT_1804b5200;
            if (cVar6 == '\0') {
              if (DAT_1804ae328 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b5200 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000362,1);
                lVar5 = DAT_1804b4d38;
                lVar4 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00005af0,0x19);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000090);
                (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar11);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar13,uVar7,uVar8);
                DAT_1804ae328 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae328,in_stack_000000b0);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,5,in_stack_000000a0,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_000001d8)
              ;
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              if (DAT_1804b5328 == 0) {
                return;
              }
              if (DAT_1804b5330 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,DAT_1804b5328,DAT_1804b5330,in_stack_00000c70);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_00000c78);
              (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 lVar12 % 0x8f3c2 ^ 0xe9ebfdf6241a4ffb);
              (*pcVar1)(in_stack_00000048,uVar8,3,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_000000c0);
              (*pcVar1)(in_stack_00000048,uVar8,4,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
              lVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                 (in_stack_00000048,uVar7,in_stack_000000d8);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                if (in_stack_000000f8 != 0) {
                  return;
                }
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (in_stack_00000ce8 != lVar13) {
                  return;
                }
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar6 == '\0') {
                    if (DAT_1804ae330 == 0) {
                      lVar13 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar13 + 0x390);
                      uVar8 = FUN_18014d730(in_stack_00000048);
                      uVar7 = DAT_1804b5208;
                      uVar9 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x00000360,1);
                      lVar13 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar13 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000048);
                      uVar14 = FUN_180002550(in_stack_00000048);
                      uVar15 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000017d8,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar16 = FUN_1800025a0(in_stack_00000048);
                      uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar16);
                      (*pcVar2)(in_stack_00000048,uVar11,uVar14,uVar15,uVar16);
                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
                      DAT_1804ae330 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ae330,in_stack_000000b0);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3992)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       lVar12 % 0x5bb8e ^ in_stack_000000c0 ^ 0xb64d018a890ba37b);
                    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar7,in_stack_000000d8);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar6 == '\0') {
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar6 == '\0') {
                          if (DAT_1804ae338 == 0) {
                            lVar13 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar13 + 0x390);
                            uVar8 = FUN_18014d730(in_stack_00000048);
                            uVar7 = DAT_1804b5208;
                            uVar9 = (**(code **)(lVar13 + 0x518))
                                              (in_stack_00000048,&stack0x0000035e,1);
                            lVar13 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar13 + 0x390);
                            uVar11 = FUN_1800024f0(in_stack_00000048);
                            uVar14 = FUN_180002550(in_stack_00000048);
                            uVar15 = (**(code **)(lVar13 + 0x518))
                                               (in_stack_00000048,&stack0x000017c8,5);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar16 = FUN_1800025a0(in_stack_00000048);
                            uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar16);
                            (*pcVar2)(in_stack_00000048,uVar11,uVar14,uVar15,uVar16);
                            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9)
                            ;
                            DAT_1804ae338 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ae338,in_stack_000000b0);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x7d02);
                          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             lVar12 % 0x3662c ^ in_stack_000000c0 ^
                                             0xde39a29eaa847799);
                          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar7,in_stack_000000d8);
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar6 == '\0') {
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar6 == '\0') {
                                if (DAT_1804ae340 == 0) {
                                  lVar13 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar13 + 0x390);
                                  uVar8 = FUN_18014d730(in_stack_00000048);
                                  uVar7 = DAT_1804b5208;
                                  uVar9 = (**(code **)(lVar13 + 0x518))
                                                    (in_stack_00000048,&stack0x0000035c,1);
                                  lVar13 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar13 + 0x390);
                                  uVar11 = FUN_1800024f0(in_stack_00000048);
                                  uVar14 = FUN_180002550(in_stack_00000048);
                                  uVar15 = (**(code **)(lVar13 + 0x518))
                                                     (in_stack_00000048,&stack0x000017b8,5);
                                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar16 = FUN_1800025a0(in_stack_00000048);
                                  uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar16);
                                  (*pcVar2)(in_stack_00000048,uVar11,uVar14,uVar15,uVar16);
                                  uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,
                                                    uVar9);
                                  DAT_1804ae340 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar7);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                                }
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804ae340,in_stack_000000b0
                                                  );
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x58da);
                                (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   lVar12 % 0x6c883 ^ in_stack_000000c0 ^
                                                   0x8bdb6c8a515e3f68);
                                (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar7,in_stack_000000d8);
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar6 == '\0') {
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
  return;
}

