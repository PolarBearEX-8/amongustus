/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=296 entry=18014718a blocks=55 maxInstructions=100000 */

/* cut edge: 0x18014718a -> 0x18014d3db */
/* cut edge: 0x180147287 -> 0x18014d3db */
/* cut edge: 0x18014728f -> 0x18014d3db */
/* cut edge: 0x1801472f2 -> 0x180148b46 */
/* cut edge: 0x18014731b -> 0x180148b7e */
/* cut edge: 0x180147377 -> 0x180148bcf */
/* cut edge: 0x180147387 -> 0x180148c08 */
/* cut edge: 0x180147467 -> 0x18014d3db */
/* cut edge: 0x180147563 -> 0x18014d3db */
/* cut edge: 0x180147579 -> 0x18014d3db */
/* cut edge: 0x1801475e2 -> 0x180148c3f */
/* cut edge: 0x18014760b -> 0x180148c77 */
/* cut edge: 0x18014766a -> 0x180148cc8 */
/* cut edge: 0x18014767a -> 0x180148d02 */
/* cut edge: 0x180147763 -> 0x18014d3db */
/* cut edge: 0x18014785a -> 0x18014d3db */
/* cut edge: 0x180147870 -> 0x18014d3db */
/* cut edge: 0x1801478d6 -> 0x180148d3a */
/* cut edge: 0x1801478ff -> 0x180148d72 */
/* cut edge: 0x180147961 -> 0x180148dc3 */
/* cut edge: 0x180147971 -> 0x180148dfd */
/* cut edge: 0x180147a5d -> 0x18014d3db */
/* cut edge: 0x180147b52 -> 0x18014d3db */
/* cut edge: 0x180147b68 -> 0x18014d3db */
/* cut edge: 0x180147bce -> 0x180148e35 */
/* cut edge: 0x180147bf7 -> 0x180148e6d */
/* cut edge: 0x180147c59 -> 0x180148ebe */
/* cut edge: 0x180147c69 -> 0x180148ef8 */
/* cut edge: 0x180147d54 -> 0x18014d3db */
/* cut edge: 0x180147e66 -> 0x1801253d7 */
/* cut edge: 0x180147e78 -> 0x18014d3db */
/* cut edge: 0x180147e80 -> 0x18014d3db */
/* cut edge: 0x180147ee3 -> 0x180148f2e */
/* cut edge: 0x180147f0c -> 0x180148f66 */
/* cut edge: 0x180147f68 -> 0x180148fb7 */
/* cut edge: 0x180147f78 -> 0x180148ff0 */
/* cut edge: 0x180148058 -> 0x18014d3db */
/* cut edge: 0x180148058 -> 0x180148154 */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x180148154 original byte 0x48 replaced by RET */
/* target 0x180148b46 original byte 0x48 replaced by RET */
/* target 0x180148b7e original byte 0x48 replaced by RET */
/* target 0x180148bcf original byte 0x48 replaced by RET */
/* target 0x180148c08 original byte 0x48 replaced by RET */
/* target 0x180148c3f original byte 0x48 replaced by RET */
/* target 0x180148c77 original byte 0x48 replaced by RET */
/* target 0x180148cc8 original byte 0x48 replaced by RET */
/* target 0x180148d02 original byte 0x48 replaced by RET */
/* target 0x180148d3a original byte 0x48 replaced by RET */
/* target 0x180148d72 original byte 0x48 replaced by RET */
/* target 0x180148dc3 original byte 0x48 replaced by RET */
/* target 0x180148dfd original byte 0x48 replaced by RET */
/* target 0x180148e35 original byte 0x48 replaced by RET */
/* target 0x180148e6d original byte 0x48 replaced by RET */
/* target 0x180148ebe original byte 0x48 replaced by RET */
/* target 0x180148ef8 original byte 0x48 replaced by RET */
/* target 0x180148f2e original byte 0x48 replaced by RET */
/* target 0x180148f66 original byte 0x48 replaced by RET */
/* target 0x180148fb7 original byte 0x48 replaced by RET */
/* target 0x180148ff0 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00296_18014718a(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong lVar9;
  undefined8 uVar10;
  longlong unaff_R12;
  longlong *in_stack_00000048;
  longlong in_stack_00000078;
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
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  lVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (lVar9 == 0) {
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        lVar9 = DAT_1804b52e8;
        uVar6 = DAT_1804b5208;
        if (cVar5 == '\0') {
          if (DAT_1804acc20 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b52e8 == 0) {
              return;
            }
            uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000932,1);
            lVar4 = DAT_1804b4d38;
            lVar3 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00003e98,5);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
            (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7);
            DAT_1804acc20 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804acc20,in_stack_000000b0);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6f11);
          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                             unaff_R12 % 0xd4947 ^ in_stack_000000c0 ^ 0x9407287f133c52d0);
          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
          (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            return;
          }
        }
      }
    }
    else if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar9 = DAT_1804b52e8;
      uVar6 = DAT_1804b5208;
      if (cVar5 == '\0') {
        if (DAT_1804acc40 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return;
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000092a,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00003e58,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7);
          DAT_1804acc40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804acc40,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1959);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           unaff_R12 % 0x6cb1f ^ in_stack_000000c0 ^ 0x8007379d53b3e090);
        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            lVar9 = DAT_1804b52e8;
            uVar6 = DAT_1804b5208;
            if (cVar5 == '\0') {
              if (DAT_1804acc48 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return;
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000928,1);
                lVar4 = DAT_1804b4d38;
                lVar3 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00003e48,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000090);
                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7);
                DAT_1804acc48 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804acc48,in_stack_000000b0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x336e);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_00000078 % 0xdcf2 ^ in_stack_000000c0 ^ 0xe3cda51644c22be7
                                );
              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  lVar9 = DAT_1804b52e8;
                  uVar6 = DAT_1804b5208;
                  if (cVar5 == '\0') {
                    if (DAT_1804acc50 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b52e8 == 0) {
                        return;
                      }
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00000926,1);
                      lVar4 = DAT_1804b4d38;
                      lVar3 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00003e38,5);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,in_stack_00000090);
                      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7);
                      DAT_1804acc50 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804acc50,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2768)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       in_stack_00000078 % 0x24e22 ^ in_stack_000000c0 ^
                                       0xe2dc133e5dfad2db);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar6,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        lVar9 = DAT_1804b52e8;
                        uVar6 = DAT_1804b5208;
                        if (cVar5 == '\0') {
                          if (DAT_1804acc58 == 0) {
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b52e8 == 0) {
                              return;
                            }
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x00000924,1);
                            lVar4 = DAT_1804b4d38;
                            lVar3 = DAT_1804b4d30;
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b4d30 == 0) {
                              return;
                            }
                            if (DAT_1804b4d38 == 0) {
                              return;
                            }
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x00003e28,5);
                            if (DAT_1804b4d40 == 0) {
                              return;
                            }
                            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,in_stack_00000090);
                            (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7)
                            ;
                            DAT_1804acc58 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804acc58,in_stack_000000b0);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x1a35);
                          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00000078 % 0x54a88 ^ in_stack_000000c0 ^
                                             0xe9dbdb809db9cf90);
                          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar6,in_stack_000000d8);
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

