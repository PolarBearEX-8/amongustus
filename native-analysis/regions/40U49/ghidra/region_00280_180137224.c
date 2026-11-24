/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=280 entry=180137224 blocks=31 maxInstructions=100000 */

/* cut edge: 0x180137224 -> 0x18014d3db */
/* cut edge: 0x18013731b -> 0x18014d3db */
/* cut edge: 0x180137331 -> 0x18014d3db */
/* cut edge: 0x1801374de -> 0x18014d3db */
/* cut edge: 0x1801375d5 -> 0x18014d3db */
/* cut edge: 0x1801375eb -> 0x18014d3db */
/* cut edge: 0x18013779e -> 0x18014d3db */
/* cut edge: 0x180137893 -> 0x18014d3db */
/* cut edge: 0x1801378a9 -> 0x18014d3db */
/* cut edge: 0x180137a68 -> 0x18014d3db */
/* cut edge: 0x180137b66 -> 0x180138787 */
/* cut edge: 0x180137b91 -> 0x1801253d7 */
/* cut edge: 0x180137ba0 -> 0x18014d3db */
/* cut edge: 0x180137bd6 -> 0x18014d3db */
/* cut edge: 0x180137c19 -> 0x18014d3db */
/* cut edge: 0x180137c5c -> 0x18014d3db */
/* cut edge: 0x180137c72 -> 0x18014d3db */
/* cut edge: 0x180137e1e -> 0x18014d3db */
/* cut edge: 0x180137f15 -> 0x18014d3db */
/* cut edge: 0x180137f2b -> 0x18014d3db */
/* cut edge: 0x1801380e1 -> 0x18014d3db */
/* cut edge: 0x1801380e1 -> 0x1801381db */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x1801381db original byte 0x48 replaced by RET */
/* target 0x180138787 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00280_180137224(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
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
  undefined8 in_stack_00000c80;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x362);
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
        if (DAT_1804acae8 == 0) {
          lVar14 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar14 + 0x390);
          uVar7 = FUN_18014d730(in_stack_00000048);
          uVar6 = DAT_1804b5208;
          uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000984,1);
          lVar14 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar14 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004088,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
          (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
          DAT_1804acae8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804acae8,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x35ce);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000078 % 0x9608a ^ in_stack_000000c0 ^
                           0x226fdaee979d8c6c);
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
              if (DAT_1804acaf0 == 0) {
                lVar14 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar14 + 0x390);
                uVar7 = FUN_18014d730(in_stack_00000048);
                uVar6 = DAT_1804b5208;
                uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000982,1);
                lVar14 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar14 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004078,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                DAT_1804acaf0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804acaf0,in_stack_000000b0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x68f3);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000078 % 0x826b6 ^ in_stack_000000c0 ^
                                 0x410acbdc1687e8fd);
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
                    if (DAT_1804acaf8 == 0) {
                      lVar14 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar14 + 0x390);
                      uVar7 = FUN_18014d730(in_stack_00000048);
                      uVar6 = DAT_1804b5208;
                      uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000980,1);
                      lVar14 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar14 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004068,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                      DAT_1804acaf8 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804acaf8,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2b93)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)in_stack_00000078 % 0x58fdc ^ in_stack_000000c0 ^
                                       0x4f357bbb263ef786);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                       (in_stack_00000048,uVar6,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      lVar14 = lVar14 + (uVar13 ^ in_stack_00000078);
                      if ((longlong)in_stack_000000c0 < 1) {
                        return;
                      }
                      if (in_stack_000000f8 == 0) {
                        return;
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x568))
                                        (in_stack_00000048,in_stack_00000c80,1);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                          (in_stack_00000048,&stack0x0000097e,1);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar8 = FUN_18014db40(in_stack_00000048);
                          (*pcVar1)(in_stack_00000048,uVar6,uVar8,uVar7);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                if (DAT_1804acb00 == 0) {
                                  lVar4 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar4 + 0x390);
                                  uVar7 = FUN_18014d730(in_stack_00000048);
                                  uVar6 = DAT_1804b5208;
                                  uVar8 = (**(code **)(lVar4 + 0x518))
                                                    (in_stack_00000048,&stack0x0000097c,1);
                                  lVar4 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar4 + 0x390);
                                  uVar9 = FUN_1800024f0(in_stack_00000048);
                                  uVar10 = FUN_180002550(in_stack_00000048);
                                  uVar11 = (**(code **)(lVar4 + 0x518))
                                                     (in_stack_00000048,&stack0x00004058,5);
                                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar12 = FUN_1800025a0(in_stack_00000048);
                                  uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                                  (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                  uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,
                                                    uVar8);
                                  DAT_1804acb00 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar6);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                                }
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804acb00,in_stack_000000b0
                                                  );
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x6164);
                                (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   lVar14 % 0x66841 ^ in_stack_000000c0 ^
                                                   0x1895a9676dab6a93);
                                (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar6,in_stack_000000d8);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if (cVar5 == '\0') {
                                      if (DAT_1804acb08 == 0) {
                                        lVar4 = *in_stack_00000048;
                                        pcVar1 = *(code **)(lVar4 + 0x390);
                                        uVar7 = FUN_18014d730(in_stack_00000048);
                                        uVar6 = DAT_1804b5208;
                                        uVar8 = (**(code **)(lVar4 + 0x518))
                                                          (in_stack_00000048,&stack0x0000097a,1);
                                        lVar4 = *in_stack_00000048;
                                        pcVar2 = *(code **)(lVar4 + 0x390);
                                        uVar9 = FUN_1800024f0(in_stack_00000048);
                                        uVar10 = FUN_180002550(in_stack_00000048);
                                        uVar11 = (**(code **)(lVar4 + 0x518))
                                                           (in_stack_00000048,&stack0x00004048,5);
                                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar12 = FUN_1800025a0(in_stack_00000048);
                                        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,
                                                           uVar12);
                                        (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,
                                                          uVar6,uVar8);
                                        DAT_1804acb08 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar6);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar6);
                                      }
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,DAT_1804acb08,
                                                         in_stack_000000b0);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,2,in_stack_000000a0,0);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_000000c8,
                                                         in_stack_000000d0,0xc46);
                                      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_000000b8,
                                                         lVar14 % 0xe0792 ^ in_stack_000000c0 ^
                                                         0x306245fc2f711ad4);
                                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,uVar6,in_stack_000000a8,
                                                         uVar7);
                                      (**(code **)(*in_stack_00000048 + 0x1a0))
                                                (in_stack_00000048,uVar6,in_stack_000000d8);
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
  return;
}

