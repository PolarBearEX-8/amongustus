/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=29 entry=18003c19b blocks=35 maxInstructions=100000 */

/* cut edge: 0x18003c19b -> 0x18014d3db */
/* cut edge: 0x18003c294 -> 0x18013eaa3 */
/* cut edge: 0x18003c2a1 -> 0x18014d3db */
/* cut edge: 0x18003c3fa -> 0x18014d3db */
/* cut edge: 0x18003c4f3 -> 0x18009639f */
/* cut edge: 0x18003c500 -> 0x18014d3db */
/* cut edge: 0x18003c659 -> 0x18014d3db */
/* cut edge: 0x18003c75b -> 0x1800f76fc */
/* cut edge: 0x18003c770 -> 0x18014d3db */
/* cut edge: 0x18003c8c9 -> 0x18014d3db */
/* cut edge: 0x18003c9cb -> 0x1801036ae */
/* cut edge: 0x18003c9e2 -> 0x18014d3db */
/* cut edge: 0x18003cb3b -> 0x18014d3db */
/* cut edge: 0x18003cc34 -> 0x180149310 */
/* cut edge: 0x18003cc41 -> 0x18014d3db */
/* cut edge: 0x18003cd9a -> 0x18014d3db */
/* cut edge: 0x18003ce9c -> 0x1800b2e26 */
/* cut edge: 0x18003cec8 -> 0x18014d3db */
/* cut edge: 0x18003d021 -> 0x18014d3db */
/* cut edge: 0x18003d11a -> 0x18003d14f */
/* cut edge: 0x18003d11a -> 0x18003d123 */
/* target 0x18003d123 original byte 0x48 replaced by RET */
/* target 0x18003d14f original byte 0x48 replaced by RET */
/* target 0x18009639f original byte 0x48 replaced by RET */
/* target 0x1800b2e26 original byte 0x48 replaced by RET */
/* target 0x1800f76fc original byte 0x48 replaced by RET */
/* target 0x1801036ae original byte 0x48 replaced by RET */
/* target 0x18013eaa3 original byte 0x48 replaced by RET */
/* target 0x180149310 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00029_18003c19b(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
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
  undefined8 in_stack_000051c0;
  undefined8 in_stack_000051c8;
  undefined8 in_stack_000051d0;
  undefined8 in_stack_000051d8;
  undefined8 in_stack_000051e0;
  undefined8 in_stack_000051e8;
  undefined8 in_stack_000051f0;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2451);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000051f0);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8)
  ;
  iVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000108);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    if (iVar6 == in_stack_000000e8) {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804ac5f8 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar8 = FUN_18001eb20(in_stack_00000048);
        uVar7 = DAT_1804b5208;
        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ac6,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004868,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
        DAT_1804ac5f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ac5f8,in_stack_000000b0);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x45a3);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000051e8);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
      iVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000108);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        if (iVar6 == in_stack_000000e8) {
          return;
        }
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804ac600 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar8 = FUN_18001eb20(in_stack_00000048);
            uVar7 = DAT_1804b5208;
            uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ac4,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004858,5);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
            DAT_1804ac600 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804ac600,in_stack_000000b0);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3792);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000051e0
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
          iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar7,in_stack_00000108);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            if (iVar6 == in_stack_000000e8) {
              return;
            }
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804ac608 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_18001eb20(in_stack_00000048);
                uVar7 = DAT_1804b5208;
                uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ac2,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004848,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
                DAT_1804ac608 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ac608,in_stack_000000b0);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6362);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_000051d8);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
              iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar7,in_stack_00000108);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                if (iVar6 == in_stack_000000e8) {
                  return;
                }
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804ac610 == 0) {
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar8 = FUN_18001eb20(in_stack_00000048);
                    uVar7 = DAT_1804b5208;
                    uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ac0,1);
                    lVar2 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar10 = FUN_1800024f0(in_stack_00000048);
                    uVar11 = FUN_180002550(in_stack_00000048);
                    uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004838,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar13 = FUN_1800025a0(in_stack_00000048);
                    uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                    uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
                    DAT_1804ac610 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                  }
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804ac610,in_stack_000000b0);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x393b);
                  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_000051d0);
                  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                  iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar7,in_stack_00000108);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    if (iVar6 == in_stack_000000e8) {
                      return;
                    }
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar5 == '\0') {
                      if (DAT_1804ac618 == 0) {
                        lVar2 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar8 = FUN_18001eb20(in_stack_00000048);
                        uVar7 = DAT_1804b5208;
                        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000abe,1);
                        lVar2 = *in_stack_00000048;
                        pcVar3 = *(code **)(lVar2 + 0x390);
                        uVar10 = FUN_1800024f0(in_stack_00000048);
                        uVar11 = FUN_180002550(in_stack_00000048);
                        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004828,5);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar13 = FUN_1800025a0(in_stack_00000048);
                        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
                        DAT_1804ac618 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                      }
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804ac618,in_stack_000000b0);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_000000a0,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                         0x5ddb);
                      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         in_stack_000051c8);
                      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                      iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                        (in_stack_00000048,uVar7,in_stack_00000108);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        if (iVar6 == in_stack_000000e8) {
                          return;
                        }
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804ac620 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar8 = FUN_18001eb20(in_stack_00000048);
                            uVar7 = DAT_1804b5208;
                            uVar9 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,&stack0x00000abc,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar10 = FUN_1800024f0(in_stack_00000048);
                            uVar11 = FUN_180002550(in_stack_00000048);
                            uVar12 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00004818,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar13 = FUN_1800025a0(in_stack_00000048);
                            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9)
                            ;
                            DAT_1804ac620 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ac620,in_stack_000000b0);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x3e37);
                          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_000051c0);
                          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                          iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                            (in_stack_00000048,uVar7,in_stack_00000108);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            if (iVar6 == in_stack_000000e8) {
                              return;
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

