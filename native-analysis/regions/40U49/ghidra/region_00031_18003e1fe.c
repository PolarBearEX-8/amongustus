/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=31 entry=18003e1fe blocks=36 maxInstructions=100000 */

/* cut edge: 0x18003e207 -> 0x1800fac3f */
/* cut edge: 0x18003e21c -> 0x18014d3db */
/* cut edge: 0x18003e375 -> 0x18014d3db */
/* cut edge: 0x18003e46e -> 0x1800c2902 */
/* cut edge: 0x18003e47b -> 0x18014d3db */
/* cut edge: 0x18003e5d4 -> 0x18014d3db */
/* cut edge: 0x18003e6cd -> 0x180078999 */
/* cut edge: 0x18003e6da -> 0x18014d3db */
/* cut edge: 0x18003e833 -> 0x18014d3db */
/* cut edge: 0x18003e935 -> 0x180108ccd */
/* cut edge: 0x18003e951 -> 0x18014d3db */
/* cut edge: 0x18003eaaa -> 0x18014d3db */
/* cut edge: 0x18003eba3 -> 0x1800ad2c0 */
/* cut edge: 0x18003ebb0 -> 0x18014d3db */
/* cut edge: 0x18003ed09 -> 0x18014d3db */
/* cut edge: 0x18003ee02 -> 0x180082393 */
/* cut edge: 0x18003ee0f -> 0x18014d3db */
/* cut edge: 0x18003ef68 -> 0x18014d3db */
/* cut edge: 0x18003f061 -> 0x180146267 */
/* cut edge: 0x18003f06e -> 0x18014d3db */
/* cut edge: 0x18003f084 -> 0x18003f1c7 */
/* cut edge: 0x18003f094 -> 0x18003f1c7 */
/* target 0x18003f1c7 original byte 0x48 replaced by RET */
/* target 0x180078999 original byte 0x48 replaced by RET */
/* target 0x180082393 original byte 0x48 replaced by RET */
/* target 0x1800ad2c0 original byte 0x48 replaced by RET */
/* target 0x1800c2902 original byte 0x4c replaced by RET */
/* target 0x1800fac3f original byte 0x48 replaced by RET */
/* target 0x180108ccd original byte 0x48 replaced by RET */
/* target 0x180146267 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00031_18003e1fe(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  int iVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  int unaff_ESI;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  int in_stack_000000e8;
  undefined8 in_stack_00000108;
  undefined8 in_stack_00005158;
  undefined8 in_stack_00005160;
  undefined8 in_stack_00005168;
  undefined8 in_stack_00005170;
  undefined8 in_stack_00005178;
  undefined8 in_stack_00005180;
  
  if (unaff_ESI == in_stack_000000e8) {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804ac660 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_18001eb20(in_stack_00000048);
      uVar13 = DAT_1804b5208;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000aac,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004798,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
      DAT_1804ac660 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804ac660,in_stack_000000b0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5e2f);
    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005180);
    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
    iVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar13,in_stack_00000108);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      if (iVar6 == in_stack_000000e8) {
        return;
      }
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804ac668 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_18001eb20(in_stack_00000048);
          uVar13 = DAT_1804b5208;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000aaa,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004788,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
          DAT_1804ac668 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac668,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x554b);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005178);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
        iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar13,in_stack_00000108);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          if (iVar6 == in_stack_000000e8) {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804ac670 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_18001eb20(in_stack_00000048);
              uVar13 = DAT_1804b5208;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000aa8,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004778,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
              DAT_1804ac670 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804ac670,in_stack_000000b0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1ef);
            (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_00005170);
            (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
            iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar13,in_stack_00000108);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              if (iVar6 == in_stack_000000e8) {
                return;
              }
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                if (DAT_1804ac678 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_18001eb20(in_stack_00000048);
                  uVar13 = DAT_1804b5208;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000aa6,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000048);
                  uVar10 = FUN_180002550(in_stack_00000048);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004768,5);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000048);
                  uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                  (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
                  DAT_1804ac678 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804ac678,in_stack_000000b0);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_000000a0,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2e34);
                (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                   in_stack_00005168);
                (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar13,in_stack_00000108);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  if (iVar6 == in_stack_000000e8) {
                    return;
                  }
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804ac680 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_18001eb20(in_stack_00000048);
                      uVar13 = DAT_1804b5208;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000aa4,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004758,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
                      DAT_1804ac680 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                    }
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804ac680,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6e39)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       in_stack_00005160);
                    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                    iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                      (in_stack_00000048,uVar13,in_stack_00000108);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      if (iVar6 == in_stack_000000e8) {
                        return;
                      }
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        if (DAT_1804ac688 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar7 = FUN_18001eb20(in_stack_00000048);
                          uVar13 = DAT_1804b5208;
                          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000aa2,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar9 = FUN_1800024f0(in_stack_00000048);
                          uVar10 = FUN_180002550(in_stack_00000048);
                          uVar11 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00004748,5);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar12 = FUN_1800025a0(in_stack_00000048);
                          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8)
                          ;
                          DAT_1804ac688 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                        }
                        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804ac688,in_stack_000000b0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x34fb);
                        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_00005158);
                        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                        iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                          (in_stack_00000048,uVar13,in_stack_00000108);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          if (iVar6 == in_stack_000000e8) {
                            return;
                          }
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar5 == '\0') {
                            if (DAT_1804ac690 == 0) {
                              lVar1 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar1 + 0x390);
                              uVar7 = FUN_18001eb20(in_stack_00000048);
                              uVar13 = DAT_1804b5208;
                              uVar8 = (**(code **)(lVar1 + 0x518))
                                                (in_stack_00000048,&stack0x00000aa0,1);
                              lVar1 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar1 + 0x390);
                              uVar9 = FUN_1800024f0(in_stack_00000048);
                              uVar10 = FUN_180002550(in_stack_00000048);
                              uVar11 = (**(code **)(lVar1 + 0x518))
                                                 (in_stack_00000048,&stack0x00004738,5);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar12 = FUN_1800025a0(in_stack_00000048);
                              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                              uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,
                                                 uVar8);
                              DAT_1804ac690 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))
                                             (in_stack_00000048,uVar13);
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                            }
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
  return;
}

