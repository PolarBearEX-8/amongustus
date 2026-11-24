/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=15 entry=18002e13a blocks=45 maxInstructions=100000 */

/* cut edge: 0x18002e13a -> 0x180094acf */
/* cut edge: 0x18002e147 -> 0x18014d3db */
/* cut edge: 0x18002e2a0 -> 0x18014d3db */
/* cut edge: 0x18002e3a2 -> 0x1800ba9d1 */
/* cut edge: 0x18002e3b4 -> 0x18014d3db */
/* cut edge: 0x18002e50d -> 0x18014d3db */
/* cut edge: 0x18002e60f -> 0x180117be9 */
/* cut edge: 0x18002e61e -> 0x18014d3db */
/* cut edge: 0x18002e777 -> 0x18014d3db */
/* cut edge: 0x18002e870 -> 0x180093ecd */
/* cut edge: 0x18002e87d -> 0x18014d3db */
/* cut edge: 0x18002e9d6 -> 0x18014d3db */
/* cut edge: 0x18002ead8 -> 0x1800e1284 */
/* cut edge: 0x18002eaff -> 0x18014d3db */
/* cut edge: 0x18002ec58 -> 0x18014d3db */
/* cut edge: 0x18002ed5a -> 0x1800b1480 */
/* cut edge: 0x18002ed76 -> 0x18014d3db */
/* cut edge: 0x18002eecf -> 0x18014d3db */
/* cut edge: 0x18002efc8 -> 0x180030885 */
/* cut edge: 0x18002efdd -> 0x18003286e */
/* cut edge: 0x18002eff4 -> 0x18014d3db */
/* cut edge: 0x18002f01d -> 0x1800328bf */
/* cut edge: 0x18002f049 -> 0x180032910 */
/* cut edge: 0x18002f059 -> 0x18014d3db */
/* cut edge: 0x18002f07f -> 0x180032949 */
/* cut edge: 0x18002f0a6 -> 0x18014d3db */
/* cut edge: 0x18002f0e1 -> 0x18014d3db */
/* cut edge: 0x18002f0f7 -> 0x18014d3db */
/* cut edge: 0x18002f10d -> 0x18002f2cf */
/* cut edge: 0x18002f10d -> 0x18002f162 */
/* target 0x18002f162 original byte 0x48 replaced by RET */
/* target 0x18002f2cf original byte 0x48 replaced by RET */
/* target 0x180030885 original byte 0x48 replaced by RET */
/* target 0x18003286e original byte 0x48 replaced by RET */
/* target 0x1800328bf original byte 0x48 replaced by RET */
/* target 0x180032910 original byte 0x48 replaced by RET */
/* target 0x180032949 original byte 0x4c replaced by RET */
/* target 0x180093ecd original byte 0x48 replaced by RET */
/* target 0x180094acf original byte 0x48 replaced by RET */
/* target 0x1800b1480 original byte 0x48 replaced by RET */
/* target 0x1800ba9d1 original byte 0x48 replaced by RET */
/* target 0x1800e1284 original byte 0x48 replaced by RET */
/* target 0x180117be9 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


longlong * region_00015_18002e13a(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  int iVar5;
  longlong *in_RAX;
  longlong *plVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  ulonglong uVar14;
  int unaff_ESI;
  longlong *in_stack_00000048;
  longlong *in_stack_00000078;
  longlong *in_stack_00000080;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  longlong *in_stack_000000e0;
  int in_stack_000000e8;
  undefined8 in_stack_00000108;
  ulonglong in_stack_00000c08;
  undefined8 in_stack_00005428;
  undefined8 in_stack_00005430;
  undefined8 in_stack_00005438;
  undefined8 in_stack_00005440;
  undefined8 in_stack_00005448;
  undefined8 in_stack_00005450;
  
  if (unaff_ESI == in_stack_000000e8) {
    return in_RAX;
  }
  plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)plVar6 == '\0') {
    if (DAT_1804ac390 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_18001eb20(in_stack_00000048);
      uVar13 = DAT_1804b5208;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b60,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004d38,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
      DAT_1804ac390 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804ac390,in_stack_000000b0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x332a);
    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005450);
    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
    iVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar13,in_stack_00000108);
    plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar6 == '\0') {
      if (iVar5 == in_stack_000000e8) {
        return in_stack_00000080;
      }
      plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)plVar6 == '\0') {
        if (DAT_1804ac398 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_18001eb20(in_stack_00000048);
          uVar13 = DAT_1804b5208;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b5e,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004d28,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
          DAT_1804ac398 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac398,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4f2d);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005448);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
        iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar13,in_stack_00000108);
        plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)plVar6 == '\0') {
          if (iVar5 == in_stack_000000e8) {
            return in_stack_00000078;
          }
          plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)plVar6 == '\0') {
            if (DAT_1804ac3a0 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_18001eb20(in_stack_00000048);
              uVar13 = DAT_1804b5208;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b5c,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004d18,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
              DAT_1804ac3a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804ac3a0,in_stack_000000b0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6866);
            (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_00005440);
            (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
            iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar13,in_stack_00000108);
            plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)plVar6 == '\0') {
              if (iVar5 == in_stack_000000e8) {
                return plVar6;
              }
              plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)plVar6 == '\0') {
                if (DAT_1804ac3a8 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_18001eb20(in_stack_00000048);
                  uVar13 = DAT_1804b5208;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b5a,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000048);
                  uVar10 = FUN_180002550(in_stack_00000048);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004d08,5);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000048);
                  uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                  (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
                  DAT_1804ac3a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804ac3a8,in_stack_000000b0);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_000000a0,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0xc80);
                (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                   in_stack_00005438);
                (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar13,in_stack_00000108);
                plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)plVar6 == '\0') {
                  if (iVar5 == in_stack_000000e8) {
                    return in_stack_000000e0;
                  }
                  plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)plVar6 == '\0') {
                    if (DAT_1804ac3b0 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_18001eb20(in_stack_00000048);
                      uVar13 = DAT_1804b5208;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b58,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004cf8,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
                      DAT_1804ac3b0 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                    }
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804ac3b0,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6bf4)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       in_stack_00005430);
                    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                    iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                      (in_stack_00000048,uVar13,in_stack_00000108);
                    plVar6 = (longlong *)
                             (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)plVar6 == '\0') {
                      if (iVar5 == in_stack_000000e8) {
                        return in_stack_00000080;
                      }
                      plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)plVar6 == '\0') {
                        if (DAT_1804ac3b8 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar7 = FUN_18001eb20(in_stack_00000048);
                          uVar13 = DAT_1804b5208;
                          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b56,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar9 = FUN_1800024f0(in_stack_00000048);
                          uVar10 = FUN_180002550(in_stack_00000048);
                          uVar11 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00004ce8,5);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar12 = FUN_1800025a0(in_stack_00000048);
                          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8)
                          ;
                          DAT_1804ac3b8 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                        }
                        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804ac3b8,in_stack_000000b0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x2a9d);
                        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_00005428);
                        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                        iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                          (in_stack_00000048,uVar13,in_stack_00000108);
                        plVar6 = (longlong *)
                                 (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        lVar1 = DAT_1804b55a0;
                        if ((char)plVar6 == '\0') {
                          if (iVar5 != in_stack_000000e8) {
                            return in_stack_00000048;
                          }
                          if (DAT_1804b55a0 == 0) {
                            return in_stack_00000048;
                          }
                          uVar13 = (**(code **)(*in_stack_00000048 + 0xd8))
                                             (in_stack_00000048,DAT_1804b55a0);
                          plVar6 = (longlong *)
                                   (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)plVar6 == '\0') {
                            if (DAT_1804b5290 == 0) {
                              return in_stack_00000048;
                            }
                            if (DAT_1804b52d8 == 0) {
                              return in_stack_00000048;
                            }
                            uVar14 = (**(code **)(*in_stack_00000048 + 0x420))
                                               (in_stack_00000048,DAT_1804b5290);
                            plVar6 = (longlong *)
                                     (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)plVar6 == '\0') {
                              if (DAT_1804b55a8 == 0) {
                                return in_stack_00000048;
                              }
                              (**(code **)(*in_stack_00000048 + 0x2d8))
                                        (in_stack_00000048,uVar13,lVar1,DAT_1804b55a8,
                                         uVar14 ^ in_stack_00000c08);
                              plVar6 = (longlong *)
                                       (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if ((char)plVar6 == '\0') {
                                plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)plVar6 == '\0') {
                                  plVar6 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)plVar6 == '\0') {
                                    if (DAT_1804ae228 == 0) {
                                      return (longlong *)0x35f62d6631378b9d;
                                    }
                                    return (longlong *)0x35f62d6631378b9d;
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
  return plVar6;
}

