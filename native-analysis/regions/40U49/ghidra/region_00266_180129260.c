/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=266 entry=180129260 blocks=27 maxInstructions=100000 */

/* cut edge: 0x180129260 -> 0x18014d3db */
/* cut edge: 0x1801293a0 -> 0x18014d3db */
/* cut edge: 0x1801293b6 -> 0x18014d3db */
/* cut edge: 0x18012955f -> 0x18014d3db */
/* cut edge: 0x180129659 -> 0x18014d3db */
/* cut edge: 0x18012966f -> 0x18014d3db */
/* cut edge: 0x180129820 -> 0x18014d3db */
/* cut edge: 0x180129917 -> 0x18014d3db */
/* cut edge: 0x18012992d -> 0x18014d3db */
/* cut edge: 0x180129adc -> 0x18014d3db */
/* cut edge: 0x180129bd1 -> 0x18014d3db */
/* cut edge: 0x180129be7 -> 0x18014d3db */
/* cut edge: 0x180129da1 -> 0x18014d3db */
/* cut edge: 0x180129e9d -> 0x18013c340 */
/* cut edge: 0x180129ec9 -> 0x18014d3db */
/* cut edge: 0x180129ee4 -> 0x18014d3db */
/* cut edge: 0x18012a099 -> 0x18014d3db */
/* cut edge: 0x18012a099 -> 0x18012a193 */
/* target 0x18012a193 original byte 0x48 replaced by RET */
/* target 0x18013c340 original byte 0x4c replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00266_180129260(undefined8 param_1,undefined8 param_2)

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
  ulonglong in_stack_00000058;
  undefined8 in_stack_00000078;
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
  undefined8 in_stack_00000198;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_000000a0,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_00000078);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00000198);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,2);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
  (*pcVar1)(in_stack_00000048,uVar7,4,uVar8);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804ac978 == 0) {
          lVar14 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar14 + 0x390);
          uVar7 = FUN_18014d730(in_stack_00000048);
          uVar6 = DAT_1804b5208;
          uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009e2,1);
          lVar14 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar14 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000042b8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
          (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
          DAT_1804ac978 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ac978,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2ef0);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000058 % 0x8c83a ^ in_stack_000000c0 ^
                           0xa728f888fda1ca8e);
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
              if (DAT_1804ac980 == 0) {
                lVar14 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar14 + 0x390);
                uVar7 = FUN_18014d730(in_stack_00000048);
                uVar6 = DAT_1804b5208;
                uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009e0,1);
                lVar14 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar14 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000042a8,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                DAT_1804ac980 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ac980,in_stack_000000b0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3af9);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000058 % 0xc55ad ^ in_stack_000000c0 ^
                                 0xc6ba1353c9007ac6);
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
                    if (DAT_1804ac988 == 0) {
                      lVar14 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar14 + 0x390);
                      uVar7 = FUN_18014d730(in_stack_00000048);
                      uVar6 = DAT_1804b5208;
                      uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009de,1);
                      lVar14 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar14 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004298,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                      DAT_1804ac988 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ac988,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x73ae)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)in_stack_00000058 % 0x209bb ^ in_stack_000000c0 ^
                                       0xc3900e56c4f423bd);
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
                          if (DAT_1804ac990 == 0) {
                            lVar14 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar14 + 0x390);
                            uVar7 = FUN_18014d730(in_stack_00000048);
                            uVar6 = DAT_1804b5208;
                            uVar8 = (**(code **)(lVar14 + 0x518))
                                              (in_stack_00000048,&stack0x000009dc,1);
                            lVar14 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar14 + 0x390);
                            uVar9 = FUN_1800024f0(in_stack_00000048);
                            uVar10 = FUN_180002550(in_stack_00000048);
                            uVar11 = (**(code **)(lVar14 + 0x518))
                                               (in_stack_00000048,&stack0x00004288,5);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar12 = FUN_1800025a0(in_stack_00000048);
                            uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                            (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8)
                            ;
                            DAT_1804ac990 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ac990,in_stack_000000b0);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x4fc);
                          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             (longlong)in_stack_00000058 % 0x52a6a ^
                                             in_stack_000000c0 ^ 0xf81e2ea61bbc7685);
                          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                          lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                             (in_stack_00000048,uVar6,in_stack_000000d8);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            if (in_stack_000000f8 == 0) {
                              return;
                            }
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                if (DAT_1804ac998 == 0) {
                                  lVar4 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar4 + 0x390);
                                  uVar7 = FUN_18014d730(in_stack_00000048);
                                  uVar6 = DAT_1804b5208;
                                  uVar8 = (**(code **)(lVar4 + 0x518))
                                                    (in_stack_00000048,&stack0x000009da,1);
                                  lVar4 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar4 + 0x390);
                                  uVar9 = FUN_1800024f0(in_stack_00000048);
                                  uVar10 = FUN_180002550(in_stack_00000048);
                                  uVar11 = (**(code **)(lVar4 + 0x518))
                                                     (in_stack_00000048,&stack0x00004278,5);
                                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar12 = FUN_1800025a0(in_stack_00000048);
                                  uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                                  (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                  uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,
                                                    uVar8);
                                  DAT_1804ac998 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar6);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                                }
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804ac998,in_stack_000000b0
                                                  );
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x34a6);
                                (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   (longlong)(lVar14 + (uVar13 ^ in_stack_00000058))
                                                   % 0xdf3f ^ in_stack_000000c0 ^ 0x182c5f81517106d5
                                                  );
                                (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar6,in_stack_000000d8);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
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
  return;
}

