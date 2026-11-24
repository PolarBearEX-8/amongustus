/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=20 entry=180033142 blocks=37 maxInstructions=100000 */

/* cut edge: 0x180033142 -> 0x18014d3db */
/* cut edge: 0x18003329b -> 0x18014d3db */
/* cut edge: 0x180033394 -> 0x18009196c */
/* cut edge: 0x1800333a1 -> 0x18014d3db */
/* cut edge: 0x1800334fa -> 0x18014d3db */
/* cut edge: 0x1800335fc -> 0x180057dff */
/* cut edge: 0x18003362b -> 0x18014d3db */
/* cut edge: 0x180033784 -> 0x18014d3db */
/* cut edge: 0x180033886 -> 0x180050c71 */
/* cut edge: 0x1800338a8 -> 0x18014d3db */
/* cut edge: 0x180033a01 -> 0x18014d3db */
/* cut edge: 0x180033b03 -> 0x180045556 */
/* cut edge: 0x180033b18 -> 0x18014d3db */
/* cut edge: 0x180033c71 -> 0x18014d3db */
/* cut edge: 0x180033d73 -> 0x18006a036 */
/* cut edge: 0x180033d82 -> 0x18014d3db */
/* cut edge: 0x180033edb -> 0x18014d3db */
/* cut edge: 0x180033fd4 -> 0x1800956ec */
/* cut edge: 0x180033fe1 -> 0x18014d3db */
/* cut edge: 0x180033ff7 -> 0x18003413a */
/* cut edge: 0x180034007 -> 0x18003413a */
/* target 0x18003413a original byte 0x48 replaced by RET */
/* target 0x180045556 original byte 0x48 replaced by RET */
/* target 0x180050c71 original byte 0x48 replaced by RET */
/* target 0x180057dff original byte 0x48 replaced by RET */
/* target 0x18006a036 original byte 0x48 replaced by RET */
/* target 0x18009196c original byte 0x48 replaced by RET */
/* target 0x1800956ec original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00020_180033142(void)

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
  undefined8 in_stack_000053a0;
  undefined8 in_stack_000053a8;
  undefined8 in_stack_000053b0;
  undefined8 in_stack_000053b8;
  undefined8 in_stack_000053c0;
  undefined8 in_stack_000053c8;
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804ac418 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_18001eb20(in_stack_00000048);
      uVar13 = DAT_1804b5208;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b3e,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004c28,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
      DAT_1804ac418 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804ac418,in_stack_000000b0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x60f2);
    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000053c8);
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
        if (DAT_1804ac420 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_18001eb20(in_stack_00000048);
          uVar13 = DAT_1804b5208;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b3c,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004c18,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
          DAT_1804ac420 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac420,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x239d);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000053c0);
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
            if (DAT_1804ac428 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_18001eb20(in_stack_00000048);
              uVar13 = DAT_1804b5208;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b3a,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004c08,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
              DAT_1804ac428 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804ac428,in_stack_000000b0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x79e7);
            (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_000053b8);
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
                if (DAT_1804ac430 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_18001eb20(in_stack_00000048);
                  uVar13 = DAT_1804b5208;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b38,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000048);
                  uVar10 = FUN_180002550(in_stack_00000048);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004bf8,5);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000048);
                  uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                  (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
                  DAT_1804ac430 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804ac430,in_stack_000000b0);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_000000a0,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x64f9);
                (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                   in_stack_000053b0);
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
                    if (DAT_1804ac438 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_18001eb20(in_stack_00000048);
                      uVar13 = DAT_1804b5208;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b36,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004be8,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
                      DAT_1804ac438 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                    }
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804ac438,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5c45)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       in_stack_000053a8);
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
                        if (DAT_1804ac440 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar7 = FUN_18001eb20(in_stack_00000048);
                          uVar13 = DAT_1804b5208;
                          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000b34,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar9 = FUN_1800024f0(in_stack_00000048);
                          uVar10 = FUN_180002550(in_stack_00000048);
                          uVar11 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00004bd8,5);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar12 = FUN_1800025a0(in_stack_00000048);
                          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8)
                          ;
                          DAT_1804ac440 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                        }
                        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804ac440,in_stack_000000b0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x556);
                        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_000053a0);
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
                            if (DAT_1804ac448 == 0) {
                              lVar1 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar1 + 0x390);
                              uVar7 = FUN_18001eb20(in_stack_00000048);
                              uVar13 = DAT_1804b5208;
                              uVar8 = (**(code **)(lVar1 + 0x518))
                                                (in_stack_00000048,&stack0x00000b32,1);
                              lVar1 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar1 + 0x390);
                              uVar9 = FUN_1800024f0(in_stack_00000048);
                              uVar10 = FUN_180002550(in_stack_00000048);
                              uVar11 = (**(code **)(lVar1 + 0x518))
                                                 (in_stack_00000048,&stack0x00004bc8,5);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar12 = FUN_1800025a0(in_stack_00000048);
                              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                              uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,
                                                 uVar8);
                              DAT_1804ac448 =
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

