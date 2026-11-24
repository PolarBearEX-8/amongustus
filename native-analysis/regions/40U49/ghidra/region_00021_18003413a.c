/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=21 entry=18003413a blocks=38 maxInstructions=100000 */

/* cut edge: 0x18003413a -> 0x18014d3db */
/* cut edge: 0x18003423c -> 0x18004e215 */
/* cut edge: 0x180034268 -> 0x18014d3db */
/* cut edge: 0x1800343c1 -> 0x18014d3db */
/* cut edge: 0x1800344ba -> 0x1800e9f8d */
/* cut edge: 0x1800344c7 -> 0x18014d3db */
/* cut edge: 0x180034620 -> 0x18014d3db */
/* cut edge: 0x180034719 -> 0x180089644 */
/* cut edge: 0x180034726 -> 0x18014d3db */
/* cut edge: 0x18003487f -> 0x18014d3db */
/* cut edge: 0x180034981 -> 0x1800901fe */
/* cut edge: 0x1800349a4 -> 0x18014d3db */
/* cut edge: 0x180034afd -> 0x18014d3db */
/* cut edge: 0x180034bf6 -> 0x1800ef0db */
/* cut edge: 0x180034c03 -> 0x18014d3db */
/* cut edge: 0x180034d5c -> 0x18014d3db */
/* cut edge: 0x180034e5e -> 0x1800aaada */
/* cut edge: 0x180034e6d -> 0x18014d3db */
/* cut edge: 0x180034fc6 -> 0x18014d3db */
/* cut edge: 0x1800350bf -> 0x1801129f3 */
/* cut edge: 0x1800350cc -> 0x18014d3db */
/* cut edge: 0x1800350e2 -> 0x180035225 */
/* cut edge: 0x1800350f2 -> 0x180035225 */
/* target 0x180035225 original byte 0x48 replaced by RET */
/* target 0x18004e215 original byte 0x48 replaced by RET */
/* target 0x180089644 original byte 0x48 replaced by RET */
/* target 0x1800901fe original byte 0x3b replaced by RET */
/* target 0x1800aaada original byte 0x48 replaced by RET */
/* target 0x1800e9f8d original byte 0x48 replaced by RET */
/* target 0x1800ef0db original byte 0x48 replaced by RET */
/* target 0x1801129f3 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


ulonglong region_00021_18003413a(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  int iVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  ulonglong uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000078;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  ulonglong in_stack_000000e0;
  int in_stack_000000e8;
  undefined8 in_stack_00000108;
  ulonglong in_stack_00000148;
  undefined8 in_stack_00005368;
  undefined8 in_stack_00005370;
  undefined8 in_stack_00005378;
  undefined8 in_stack_00005380;
  undefined8 in_stack_00005388;
  undefined8 in_stack_00005390;
  undefined8 in_stack_00005398;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x704a);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005398);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  iVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000108);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)uVar9 == '\0') {
    if (iVar5 == in_stack_000000e8) {
      return in_stack_000000e0;
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar9 == '\0') {
      if (DAT_1804ac450 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar7 = FUN_18001eb20(in_stack_00000048);
        uVar6 = DAT_1804b5208;
        uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b30,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004bb8,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
        DAT_1804ac450 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ac450,in_stack_000000b0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2650);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005390);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
      iVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000108);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)uVar9 == '\0') {
        if (iVar5 == in_stack_000000e8) {
          return uVar9;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)uVar9 == '\0') {
          if (DAT_1804ac458 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar7 = FUN_18001eb20(in_stack_00000048);
            uVar6 = DAT_1804b5208;
            uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b2e,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004ba8,5);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
            DAT_1804ac458 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804ac458,in_stack_000000b0);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x777);
          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005388
                            );
          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
          iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar6,in_stack_00000108);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)uVar9 == '\0') {
            if (iVar5 == in_stack_000000e8) {
              return uVar9;
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)uVar9 == '\0') {
              if (DAT_1804ac460 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar7 = FUN_18001eb20(in_stack_00000048);
                uVar6 = DAT_1804b5208;
                uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b2c,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004b98,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                DAT_1804ac460 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ac460,in_stack_000000b0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6a97);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_00005380);
              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
              iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar6,in_stack_00000108);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)uVar9 == '\0') {
                if (iVar5 == in_stack_000000e8) {
                  return in_stack_00000148 >> 0x20;
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)uVar9 == '\0') {
                  if (DAT_1804ac468 == 0) {
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar7 = FUN_18001eb20(in_stack_00000048);
                    uVar6 = DAT_1804b5208;
                    uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b2a,1);
                    lVar2 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar10 = FUN_1800024f0(in_stack_00000048);
                    uVar11 = FUN_180002550(in_stack_00000048);
                    uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004b88,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar13 = FUN_1800025a0(in_stack_00000048);
                    uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                    uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                    DAT_1804ac468 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804ac468,in_stack_000000b0);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1a90);
                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_00005378);
                  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                  iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar6,in_stack_00000108);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)uVar9 == '\0') {
                    if (iVar5 == in_stack_000000e8) {
                      return uVar9;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)uVar9 == '\0') {
                      if (DAT_1804ac470 == 0) {
                        lVar2 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar7 = FUN_18001eb20(in_stack_00000048);
                        uVar6 = DAT_1804b5208;
                        uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000b28,1);
                        lVar2 = *in_stack_00000048;
                        pcVar3 = *(code **)(lVar2 + 0x390);
                        uVar10 = FUN_1800024f0(in_stack_00000048);
                        uVar11 = FUN_180002550(in_stack_00000048);
                        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00004b78,5);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar13 = FUN_1800025a0(in_stack_00000048);
                        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                        DAT_1804ac470 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804ac470,in_stack_000000b0);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_000000a0,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                         0x6f96);
                      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         in_stack_00005370);
                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                      iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                        (in_stack_00000048,uVar6,in_stack_00000108);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)uVar9 == '\0') {
                        if (iVar5 == in_stack_000000e8) {
                          return in_stack_00000078;
                        }
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)uVar9 == '\0') {
                          if (DAT_1804ac478 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar7 = FUN_18001eb20(in_stack_00000048);
                            uVar6 = DAT_1804b5208;
                            uVar8 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,&stack0x00000b26,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar10 = FUN_1800024f0(in_stack_00000048);
                            uVar11 = FUN_180002550(in_stack_00000048);
                            uVar12 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00004b68,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar13 = FUN_1800025a0(in_stack_00000048);
                            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8)
                            ;
                            DAT_1804ac478 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ac478,in_stack_000000b0);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x39ea);
                          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00005368);
                          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                          iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                            (in_stack_00000048,uVar6,in_stack_00000108);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)uVar9 == '\0') {
                            if (iVar5 == in_stack_000000e8) {
                              return uVar9;
                            }
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)uVar9 == '\0') {
                              if (DAT_1804ac480 == 0) {
                                lVar2 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar2 + 0x390);
                                uVar7 = FUN_18001eb20(in_stack_00000048);
                                uVar6 = DAT_1804b5208;
                                uVar8 = (**(code **)(lVar2 + 0x518))
                                                  (in_stack_00000048,&stack0x00000b24,1);
                                lVar2 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar2 + 0x390);
                                uVar10 = FUN_1800024f0(in_stack_00000048);
                                uVar11 = FUN_180002550(in_stack_00000048);
                                uVar12 = (**(code **)(lVar2 + 0x518))
                                                   (in_stack_00000048,&stack0x00004b58,5);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar13 = FUN_1800025a0(in_stack_00000048);
                                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,
                                                  uVar8);
                                DAT_1804ac480 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar6);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar6);
                              }
                              return uVar9;
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
  return uVar9;
}

