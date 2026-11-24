/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=12 entry=18002b1af blocks=35 maxInstructions=100000 */

/* cut edge: 0x18002b1af -> 0x18014d3db */
/* cut edge: 0x18002b2a8 -> 0x180087090 */
/* cut edge: 0x18002b2b5 -> 0x18014d3db */
/* cut edge: 0x18002b40e -> 0x18014d3db */
/* cut edge: 0x18002b507 -> 0x18014623b */
/* cut edge: 0x18002b514 -> 0x18014d3db */
/* cut edge: 0x18002b66d -> 0x18014d3db */
/* cut edge: 0x18002b76f -> 0x180109c56 */
/* cut edge: 0x18002b78b -> 0x18014d3db */
/* cut edge: 0x18002b8e4 -> 0x18014d3db */
/* cut edge: 0x18002b9e6 -> 0x180056570 */
/* cut edge: 0x18002ba0b -> 0x18014d3db */
/* cut edge: 0x18002bb64 -> 0x18014d3db */
/* cut edge: 0x18002bc66 -> 0x1800e309c */
/* cut edge: 0x18002bc7b -> 0x18014d3db */
/* cut edge: 0x18002bdd4 -> 0x18014d3db */
/* cut edge: 0x18002bed6 -> 0x18007af76 */
/* cut edge: 0x18002bee5 -> 0x18014d3db */
/* cut edge: 0x18002c03e -> 0x18014d3db */
/* cut edge: 0x18002c03e -> 0x18002c137 */
/* target 0x18002c137 original byte 0x48 replaced by RET */
/* target 0x180056570 original byte 0x48 replaced by RET */
/* target 0x18007af76 original byte 0x48 replaced by RET */
/* target 0x180087090 original byte 0x48 replaced by RET */
/* target 0x1800e309c original byte 0x48 replaced by RET */
/* target 0x180109c56 original byte 0x48 replaced by RET */
/* target 0x18014623b original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00012_18002b1af(undefined8 param_1,undefined8 param_2)

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
  undefined8 in_stack_00005490;
  undefined8 in_stack_00005498;
  undefined8 in_stack_000054a0;
  undefined8 in_stack_000054a8;
  undefined8 in_stack_000054b0;
  undefined8 in_stack_000054b8;
  undefined8 in_stack_000054c0;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7a2f);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000054c0);
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
      if (DAT_1804ac328 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar8 = FUN_18001eb20(in_stack_00000048);
        uVar7 = DAT_1804b5208;
        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b7a,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004e08,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
        DAT_1804ac328 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ac328,in_stack_000000b0);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,9000);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000054b8);
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
          if (DAT_1804ac330 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar8 = FUN_18001eb20(in_stack_00000048);
            uVar7 = DAT_1804b5208;
            uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b78,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004df8,5);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
            DAT_1804ac330 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804ac330,in_stack_000000b0);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x165f);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000054b0
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
              if (DAT_1804ac338 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_18001eb20(in_stack_00000048);
                uVar7 = DAT_1804b5208;
                uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b76,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004de8,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
                DAT_1804ac338 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ac338,in_stack_000000b0);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x782b);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_000054a8);
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
                  if (DAT_1804ac340 == 0) {
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar8 = FUN_18001eb20(in_stack_00000048);
                    uVar7 = DAT_1804b5208;
                    uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b74,1);
                    lVar2 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar10 = FUN_1800024f0(in_stack_00000048);
                    uVar11 = FUN_180002550(in_stack_00000048);
                    uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004dd8,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar13 = FUN_1800025a0(in_stack_00000048);
                    uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                    uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
                    DAT_1804ac340 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                  }
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804ac340,in_stack_000000b0);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x176e);
                  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_000054a0);
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
                      if (DAT_1804ac348 == 0) {
                        lVar2 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar8 = FUN_18001eb20(in_stack_00000048);
                        uVar7 = DAT_1804b5208;
                        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b72,1);
                        lVar2 = *in_stack_00000048;
                        pcVar3 = *(code **)(lVar2 + 0x390);
                        uVar10 = FUN_1800024f0(in_stack_00000048);
                        uVar11 = FUN_180002550(in_stack_00000048);
                        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004dc8,5);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar13 = FUN_1800025a0(in_stack_00000048);
                        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
                        DAT_1804ac348 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                      }
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804ac348,in_stack_000000b0);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_000000a0,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                         0x69cc);
                      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         in_stack_00005498);
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
                          if (DAT_1804ac350 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar8 = FUN_18001eb20(in_stack_00000048);
                            uVar7 = DAT_1804b5208;
                            uVar9 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,&stack0x00000b70,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar10 = FUN_1800024f0(in_stack_00000048);
                            uVar11 = FUN_180002550(in_stack_00000048);
                            uVar12 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00004db8,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar13 = FUN_1800025a0(in_stack_00000048);
                            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9)
                            ;
                            DAT_1804ac350 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ac350,in_stack_000000b0);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0xfb2);
                          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00005490);
                          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                          (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar7,in_stack_00000108);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
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
  return;
}

