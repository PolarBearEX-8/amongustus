/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=282 entry=18013922f blocks=37 maxInstructions=100000 */

/* cut edge: 0x18013922f -> 0x18014d3db */
/* cut edge: 0x180139326 -> 0x18013c32c */
/* cut edge: 0x180139351 -> 0x1801253d7 */
/* cut edge: 0x180139360 -> 0x18014d3db */
/* cut edge: 0x1801393a9 -> 0x18014d3db */
/* cut edge: 0x1801393bf -> 0x18014d3db */
/* cut edge: 0x18013956b -> 0x18014d3db */
/* cut edge: 0x180139662 -> 0x18014d3db */
/* cut edge: 0x180139678 -> 0x18014d3db */
/* cut edge: 0x18013982c -> 0x18014d3db */
/* cut edge: 0x180139923 -> 0x18014d3db */
/* cut edge: 0x180139939 -> 0x18014d3db */
/* cut edge: 0x180139af6 -> 0x18014d3db */
/* cut edge: 0x180139beb -> 0x18014d3db */
/* cut edge: 0x180139c01 -> 0x18014d3db */
/* cut edge: 0x180139dc6 -> 0x18014d3db */
/* cut edge: 0x180139ebb -> 0x1801253d7 */
/* cut edge: 0x180139ef8 -> 0x18013e83b */
/* cut edge: 0x180139f17 -> 0x18014d3db */
/* cut edge: 0x180139f3d -> 0x18013e88c */
/* cut edge: 0x180139f66 -> 0x18014d3db */
/* cut edge: 0x180139f8c -> 0x18013e8c2 */
/* cut edge: 0x180139fab -> 0x18014d3db */
/* cut edge: 0x180139fcf -> 0x18013e913 */
/* cut edge: 0x180139ff3 -> 0x18014d3db */
/* cut edge: 0x18013a017 -> 0x18014d3db */
/* cut edge: 0x18013a02d -> 0x18014d3db */
/* cut edge: 0x18013a043 -> 0x18013a200 */
/* cut edge: 0x18013a098 -> 0x18013a200 */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x18013a200 original byte 0x48 replaced by RET */
/* target 0x18013c32c original byte 0x48 replaced by RET */
/* target 0x18013e83b original byte 0x48 replaced by RET */
/* target 0x18013e88c original byte 0x48 replaced by RET */
/* target 0x18013e8c2 original byte 0x48 replaced by RET */
/* target 0x18013e913 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


longlong * region_00282_18013922f(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  longlong lVar8;
  longlong *plVar9;
  longlong *plVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  ulonglong uVar15;
  longlong lVar16;
  ulonglong unaff_R12;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000060;
  longlong in_stack_00000068;
  ulonglong in_stack_00000078;
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
  undefined8 in_stack_00000c38;
  
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6628);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_000000a8,uVar6)
  ;
  lVar8 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
  plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)plVar9 == '\0') {
    plVar10 = (longlong *)(in_stack_00000060 ^ in_stack_00000080);
    lVar8 = lVar8 + (unaff_R12 ^ in_stack_00000078);
    if ((longlong)in_stack_000000c0 < 1) {
      return plVar10;
    }
    if (in_stack_000000f8 == 0) {
      return plVar10;
    }
    pcVar1 = *(code **)(*in_stack_00000048 + 0x468);
    uVar5 = FUN_18014db90(in_stack_00000048);
    uVar6 = FUN_18014dbf0(in_stack_00000048);
    (*pcVar1)(in_stack_00000048,uVar5,uVar6,in_stack_00000c38);
    plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((((char)plVar9 == '\0') &&
        (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)plVar9 == '\0')) &&
       (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)plVar9 == '\0')) {
      if (DAT_1804acb40 == 0) {
        lVar16 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar16 + 0x390);
        uVar6 = FUN_18014d730(in_stack_00000048);
        uVar5 = DAT_1804b5208;
        uVar7 = (**(code **)(lVar16 + 0x518))(in_stack_00000048,&stack0x0000096c,1);
        lVar16 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar16 + 0x390);
        uVar11 = FUN_1800024f0(in_stack_00000048);
        uVar12 = FUN_180002550(in_stack_00000048);
        uVar13 = (**(code **)(lVar16 + 0x518))(in_stack_00000048,&stack0x00003fd8,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar14 = FUN_1800025a0(in_stack_00000048);
        uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar14);
        (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
        uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
        DAT_1804acb40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804acb40,in_stack_000000b0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2115);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         lVar8 % 0x1984f ^ in_stack_000000c0 ^ 0xd4bdbccd9b285469);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
      uVar15 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
      plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((((char)plVar9 == '\0') &&
          (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)plVar9 == '\0'))
         && (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)plVar9 == '\0')
         ) {
        if (DAT_1804acb48 == 0) {
          lVar16 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar16 + 0x390);
          uVar6 = FUN_18014d730(in_stack_00000048);
          uVar5 = DAT_1804b5208;
          uVar7 = (**(code **)(lVar16 + 0x518))(in_stack_00000048,&stack0x0000096a,1);
          lVar16 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar16 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar16 + 0x518))(in_stack_00000048,&stack0x00003fc8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar14);
          (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
          DAT_1804acb48 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804acb48,in_stack_000000b0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x34a4);
        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           lVar8 % 0xd06fd ^ in_stack_000000c0 ^ 0xcf633d09636ac564);
        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
        lVar16 = (**(code **)(*in_stack_00000048 + 0x1a0))
                           (in_stack_00000048,uVar5,in_stack_000000d8);
        plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((((char)plVar9 == '\0') &&
            (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)plVar9 == '\0')
            ) && (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(),
                 (char)plVar9 == '\0')) {
          if (DAT_1804acb50 == 0) {
            lVar4 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar4 + 0x390);
            uVar6 = FUN_18014d730(in_stack_00000048);
            uVar5 = DAT_1804b5208;
            uVar7 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000968,1);
            lVar4 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar4 + 0x390);
            uVar11 = FUN_1800024f0(in_stack_00000048);
            uVar12 = FUN_180002550(in_stack_00000048);
            uVar13 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00003fb8,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar14 = FUN_1800025a0(in_stack_00000048);
            uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar14);
            (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
            uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
            DAT_1804acb50 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
          }
          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804acb50,in_stack_000000b0);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5dd4);
          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                             lVar8 % 0x32378 ^ in_stack_000000c0 ^ 0xc55e385b0e34095c);
          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
          uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
          (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
          plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((((char)plVar9 == '\0') &&
              (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(),
              (char)plVar9 == '\0')) &&
             (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)plVar9 == '\0'
             )) {
            if (DAT_1804acb58 == 0) {
              lVar4 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar4 + 0x390);
              uVar6 = FUN_18014d730(in_stack_00000048);
              uVar5 = DAT_1804b5208;
              uVar7 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000966,1);
              lVar4 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar4 + 0x390);
              uVar11 = FUN_1800024f0(in_stack_00000048);
              uVar12 = FUN_180002550(in_stack_00000048);
              uVar13 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00003fa8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar14 = FUN_1800025a0(in_stack_00000048);
              uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar14);
              (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
              uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
              DAT_1804acb58 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
            }
            uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804acb58,in_stack_000000b0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4b3a);
            (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               lVar8 % 0xdb27d ^ in_stack_000000c0 ^ 0xd97c23afb100e513);
            (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
            plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            lVar8 = DAT_1804b5270;
            if ((char)plVar9 == '\0') {
              if (in_stack_000000f8 == 0) {
                return (longlong *)(lVar16 + (uVar15 ^ in_stack_00000068 + (longlong)plVar10));
              }
              if (DAT_1804b5270 == 0) {
                return in_stack_00000048;
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,DAT_1804b5270);
              plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)plVar9 == '\0') {
                if (DAT_1804b5278 == 0) {
                  return in_stack_00000048;
                }
                (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar5,lVar8);
                plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                lVar8 = DAT_1804b5020;
                if ((char)plVar9 == '\0') {
                  if (DAT_1804b5020 == 0) {
                    return in_stack_00000048;
                  }
                  uVar5 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,DAT_1804b5020);
                  plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)plVar9 == '\0') {
                    if (DAT_1804b5028 == 0) {
                      return in_stack_00000048;
                    }
                    (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar5,lVar8);
                    plVar9 = (longlong *)
                             (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((((char)plVar9 == '\0') &&
                        (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(),
                        (char)plVar9 == '\0')) &&
                       (plVar9 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(),
                       (char)plVar9 == '\0')) {
                      plVar9 = (longlong *)0xc8f1a2c10255ad17;
                      if (DAT_1804acb60 == 0) {
                        lVar8 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar8 + 0x390);
                        uVar6 = FUN_18001ed20(in_stack_00000048);
                        uVar5 = DAT_1804b5208;
                        uVar7 = (**(code **)(lVar8 + 0x518))(in_stack_00000048,&stack0x00000964,1);
                        lVar8 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar8 + 0x390);
                        uVar11 = FUN_1800024f0(in_stack_00000048);
                        uVar12 = FUN_180002550(in_stack_00000048);
                        uVar13 = (**(code **)(lVar8 + 0x518))
                                           (in_stack_00000048,&stack0x00006bb0,0x16);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar14 = FUN_1800025a0(in_stack_00000048);
                        uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar14);
                        (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                        uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar7);
                        DAT_1804acb60 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                        plVar9 = (longlong *)
                                 (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                      }
                      return plVar9;
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
  return plVar9;
}

