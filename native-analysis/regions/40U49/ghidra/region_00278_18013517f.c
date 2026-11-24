/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=278 entry=18013517f blocks=27 maxInstructions=100000 */

/* cut edge: 0x18013517f -> 0x18014d3db */
/* cut edge: 0x180135276 -> 0x18014d3db */
/* cut edge: 0x18013528c -> 0x18014d3db */
/* cut edge: 0x180135488 -> 0x18014d3db */
/* cut edge: 0x180135626 -> 0x18014d3db */
/* cut edge: 0x18013566f -> 0x18014d3db */
/* cut edge: 0x1801356b2 -> 0x18014d3db */
/* cut edge: 0x1801356f2 -> 0x18014d3db */
/* cut edge: 0x180135708 -> 0x18014d3db */
/* cut edge: 0x1801358cb -> 0x18014d3db */
/* cut edge: 0x1801359c0 -> 0x18014d3db */
/* cut edge: 0x1801359d6 -> 0x18014d3db */
/* cut edge: 0x180135bba -> 0x18014d3db */
/* cut edge: 0x180135d5b -> 0x18014d3db */
/* cut edge: 0x180135d93 -> 0x18014d3db */
/* cut edge: 0x180135dcc -> 0x18014d3db */
/* cut edge: 0x180135e20 -> 0x18014d3db */
/* cut edge: 0x180135e43 -> 0x18014d3db */
/* cut edge: 0x180136039 -> 0x18014d3db */
/* cut edge: 0x180136039 -> 0x18013618f */
/* target 0x18013618f original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00278_18013517f(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  longlong unaff_R15;
  longlong *in_stack_00000048;
  longlong in_stack_00000058;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
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
  undefined8 in_stack_000001a0;
  undefined8 in_stack_00000bf8;
  undefined8 in_stack_00000cc0;
  undefined8 in_stack_00000cc8;
  undefined8 in_stack_00000d50;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x494e);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  uVar6 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804aca98 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar8 = FUN_18001e7d0(in_stack_00000048);
          uVar7 = DAT_1804b5208;
          uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000099a,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000048);
          uVar11 = FUN_180002550(in_stack_00000048);
          uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00006130,0x1a);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,uVar9);
          DAT_1804aca98 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804aca98,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,6,in_stack_000000a0,0);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_000001a0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           in_stack_00000058 +
                           ((unaff_R15 >> 0x12) - (unaff_R15 >> 0x3f)) * -0xcc0b9 ^
                           0xfffffffffff74e62);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,uVar6);
        (*pcVar1)(in_stack_00000048,uVar8,2,uVar6);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00000cc0);
        (*pcVar1)(in_stack_00000048,uVar8,3,uVar6);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           in_stack_00000058 % 0x330f8 ^ 0xe9d47a679f27abac);
        (*pcVar1)(in_stack_00000048,uVar8,4,uVar6);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
        (*pcVar1)(in_stack_00000048,uVar8,5,uVar6);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8)
        ;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
          uVar7 = FUN_18014da00(in_stack_00000048);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000080,uVar7,uVar6);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000998,1);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
              uVar9 = FUN_1800108e0(in_stack_00000048);
              uVar6 = (*pcVar1)(in_stack_00000048,uVar6,uVar9,uVar8);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804acaa0 == 0) {
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar9 = FUN_18014d730(in_stack_00000048);
                      uVar8 = DAT_1804b5208;
                      uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000996,1);
                      lVar2 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar11 = FUN_1800024f0(in_stack_00000048);
                      uVar12 = FUN_180002550(in_stack_00000048);
                      uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000040e8,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar14 = FUN_1800025a0(in_stack_00000048);
                      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
                      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                      uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar9,uVar8,uVar10);
                      DAT_1804acaa0 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804acaa0,in_stack_000000b0);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x575e
                                       );
                    (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                       (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                        in_stack_00000058 % 0x45218 ^ in_stack_000000c0 ^
                                        0xc5f418840d6a6e1f);
                    (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                      (in_stack_00000048,uVar8,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804acaa8 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar10 = FUN_18001e7d0(in_stack_00000048);
                            uVar9 = DAT_1804b5208;
                            uVar11 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00000994,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar12 = FUN_1800024f0(in_stack_00000048);
                            uVar13 = FUN_180002550(in_stack_00000048);
                            uVar14 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x000060f0,0x1a);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar15 = FUN_1800025a0(in_stack_00000048);
                            uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar15);
                            (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                            uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar10,uVar9,
                                              uVar11);
                            DAT_1804acaa8 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804acaa8,in_stack_000000b0);
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                             (in_stack_00000048,6,in_stack_000000a0,0);
                          (**(code **)(*in_stack_00000048 + 0x570))
                                    (in_stack_00000048,uVar10,0,in_stack_000001a0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                              0);
                          (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             uVar8);
                          (*pcVar1)(in_stack_00000048,uVar10,2,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00000cc0);
                          (*pcVar1)(in_stack_00000048,uVar10,3,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00000058 % 0xf0a76 ^ 0xe9d47a679f2eaeec);
                          (*pcVar1)(in_stack_00000048,uVar10,4,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_000000c0);
                          (*pcVar1)(in_stack_00000048,uVar10,5,uVar8);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar9,in_stack_000000a8,uVar10);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                            (in_stack_00000048,uVar8,in_stack_000000d8);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar6,uVar7,uVar8);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar7 = FUN_180010980(in_stack_00000048);
                              (*pcVar1)(in_stack_00000048,uVar6,uVar7);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                                uVar6 = FUN_18014da50(in_stack_00000048);
                                (*pcVar1)(in_stack_00000048,in_stack_00000078,in_stack_00000070,
                                          uVar6,in_stack_00000cc8);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if (cVar5 == '\0') {
                                      if (DAT_1804acab0 == 0) {
                                        lVar2 = *in_stack_00000048;
                                        pcVar1 = *(code **)(lVar2 + 0x390);
                                        uVar7 = FUN_18001e7d0(in_stack_00000048);
                                        uVar6 = DAT_1804b5208;
                                        uVar8 = (**(code **)(lVar2 + 0x518))
                                                          (in_stack_00000048,&stack0x00000992,1);
                                        lVar2 = *in_stack_00000048;
                                        pcVar3 = *(code **)(lVar2 + 0x390);
                                        uVar9 = FUN_1800024f0(in_stack_00000048);
                                        uVar10 = FUN_180002550(in_stack_00000048);
                                        uVar11 = (**(code **)(lVar2 + 0x518))
                                                           (in_stack_00000048,&stack0x00008140,0x33)
                                        ;
                                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar12 = FUN_1800025a0(in_stack_00000048);
                                        uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,
                                                           uVar12);
                                        (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,
                                                          uVar6,uVar8);
                                        DAT_1804acab0 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar6);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar6);
                                      }
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,DAT_1804acab0,
                                                         in_stack_000000b0);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,5,in_stack_000000a0,0);
                                      (**(code **)(*in_stack_00000048 + 0x570))
                                                (in_stack_00000048,uVar7,0,in_stack_00000bf8);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_000000b8,in_stack_00000d50);
                                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                      (**(code **)(*in_stack_00000048 + 0x570))
                                                (in_stack_00000048,uVar7,2,in_stack_00000078);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_000000b8,
                                                         in_stack_00000058 % 0xe47d ^
                                                         0xe9eb0d09640811d7);
                                      (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_000000b8,in_stack_000000c0);
                                      (*pcVar1)(in_stack_00000048,uVar7,4,uVar8);
                                      (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
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

