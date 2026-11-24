/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=275 entry=180132214 blocks=35 maxInstructions=100000 */

/* cut edge: 0x180132214 -> 0x18014d3db */
/* cut edge: 0x18013230e -> 0x18014d3db */
/* cut edge: 0x180132324 -> 0x18014d3db */
/* cut edge: 0x1801324d5 -> 0x18014d3db */
/* cut edge: 0x1801325cc -> 0x18014d3db */
/* cut edge: 0x1801325e2 -> 0x18014d3db */
/* cut edge: 0x180132796 -> 0x18014d3db */
/* cut edge: 0x18013288b -> 0x18014d3db */
/* cut edge: 0x1801328a1 -> 0x18014d3db */
/* cut edge: 0x180132a5e -> 0x18014d3db */
/* cut edge: 0x180132b85 -> 0x180138787 */
/* cut edge: 0x180132b94 -> 0x1801253d7 */
/* cut edge: 0x180132ba3 -> 0x18014d3db */
/* cut edge: 0x180132bb9 -> 0x18014d3db */
/* cut edge: 0x180132d73 -> 0x18014d3db */
/* cut edge: 0x180132e57 -> 0x18013dbe7 */
/* cut edge: 0x180132e76 -> 0x18013dc38 */
/* cut edge: 0x180132e86 -> 0x18014d3db */
/* cut edge: 0x180132eac -> 0x18013dc71 */
/* cut edge: 0x180132ecb -> 0x18014d3db */
/* cut edge: 0x180132ef3 -> 0x18014d3db */
/* cut edge: 0x180132f09 -> 0x18014d3db */
/* cut edge: 0x1801330dd -> 0x18014d3db */
/* cut edge: 0x1801330dd -> 0x180133206 */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x180133206 original byte 0x48 replaced by RET */
/* target 0x180138787 original byte 0x48 replaced by RET */
/* target 0x18013dbe7 original byte 0x48 replaced by RET */
/* target 0x18013dc38 original byte 0x48 replaced by RET */
/* target 0x18013dc71 original byte 0x4c replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00275_180132214(undefined8 param_1,undefined8 param_2)

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
  undefined8 uVar15;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
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
  undefined8 in_stack_000001c8;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x410e);
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
        if (DAT_1804aca18 == 0) {
          lVar14 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar14 + 0x390);
          uVar7 = FUN_18014d730(in_stack_00000048);
          uVar6 = DAT_1804b5208;
          uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009ba,1);
          lVar14 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar14 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000041a8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
          (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
          DAT_1804aca18 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804aca18,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x74f4);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000058 % 0xa333c ^ in_stack_000000c0 ^
                           0xd2464a15fe7e9415);
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
              if (DAT_1804aca20 == 0) {
                lVar14 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar14 + 0x390);
                uVar7 = FUN_18014d730(in_stack_00000048);
                uVar6 = DAT_1804b5208;
                uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009b8,1);
                lVar14 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar14 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004198,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                DAT_1804aca20 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804aca20,in_stack_000000b0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x70fa);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000058 % 0x95c0a ^ in_stack_000000c0 ^
                                 0xe85622fc58d1845e);
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
                    if (DAT_1804aca28 == 0) {
                      lVar14 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar14 + 0x390);
                      uVar7 = FUN_18014d730(in_stack_00000048);
                      uVar6 = DAT_1804b5208;
                      uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009b6,1);
                      lVar14 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar14 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004188,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                      DAT_1804aca28 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804aca28,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7813)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)in_stack_00000058 % 0xf369e ^ in_stack_000000c0 ^
                                       0xc014075e8154fb33);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                       (in_stack_00000048,uVar6,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      lVar14 = (uVar13 ^ in_stack_00000058) - lVar14;
                      if ((longlong)in_stack_000000c0 < 0) {
                        return;
                      }
                      if (in_stack_000000f8 == 0) {
                        return;
                      }
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804aca30 == 0) {
                            lVar4 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar4 + 0x390);
                            uVar7 = FUN_18001ed20(in_stack_00000048);
                            uVar6 = DAT_1804b5208;
                            uVar8 = (**(code **)(lVar4 + 0x518))
                                              (in_stack_00000048,&stack0x000009b4,1);
                            lVar4 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar4 + 0x390);
                            uVar9 = FUN_1800024f0(in_stack_00000048);
                            uVar10 = FUN_180002550(in_stack_00000048);
                            uVar11 = (**(code **)(lVar4 + 0x518))
                                               (in_stack_00000048,&stack0x00006c10,0x16);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar12 = FUN_1800025a0(in_stack_00000048);
                            uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                            (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8)
                            ;
                            DAT_1804aca30 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804aca30,in_stack_000000b0);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x22b8);
                          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             lVar14 % 0x1ff21 ^ in_stack_000000c0 ^
                                             0x9c30b3c43ce7be8b);
                          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            if (DAT_1804b52b0 == 0) {
                              return;
                            }
                            if (DAT_1804b52b8 == 0) {
                              return;
                            }
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x488))
                                              (in_stack_00000048,DAT_1804b52b0);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              if (DAT_1804b52c0 == 0) {
                                return;
                              }
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar6,DAT_1804b52c0,uVar7);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    if (DAT_1804aca38 == 0) {
                                      lVar4 = *in_stack_00000048;
                                      pcVar1 = *(code **)(lVar4 + 0x390);
                                      uVar8 = FUN_18001e7d0(in_stack_00000048);
                                      uVar7 = DAT_1804b5208;
                                      uVar9 = (**(code **)(lVar4 + 0x518))
                                                        (in_stack_00000048,&stack0x000009b2,1);
                                      lVar4 = *in_stack_00000048;
                                      pcVar2 = *(code **)(lVar4 + 0x390);
                                      uVar10 = FUN_1800024f0(in_stack_00000048);
                                      uVar11 = FUN_180002550(in_stack_00000048);
                                      uVar12 = (**(code **)(lVar4 + 0x518))
                                                         (in_stack_00000048,&stack0x00005cf0,0x19);
                                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar15 = FUN_1800025a0(in_stack_00000048);
                                      uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar15)
                                      ;
                                      (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar15);
                                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,
                                                        uVar7,uVar9);
                                      DAT_1804aca38 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar7);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar7);
                                    }
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,DAT_1804aca38,
                                                       in_stack_000000b0);
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,4,in_stack_000000a0,0);
                                    (**(code **)(*in_stack_00000048 + 0x570))
                                              (in_stack_00000048,uVar8,0,uVar6);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_000000b8,in_stack_000001c8);
                                    (*pcVar1)(in_stack_00000048,uVar8,1,uVar6);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_000000b8,
                                                       lVar14 % 0x93507 ^ 0xe9e87d230144501a);
                                    (*pcVar1)(in_stack_00000048,uVar8,2,uVar6);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_000000b8,in_stack_000000c0);
                                    (*pcVar1)(in_stack_00000048,uVar8,3,uVar6);
                                    (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
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

