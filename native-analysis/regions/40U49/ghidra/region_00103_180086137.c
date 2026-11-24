/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=103 entry=180086137 blocks=50 maxInstructions=100000 */

/* cut edge: 0x180086137 -> 0x18014d3db */
/* cut edge: 0x18008615a -> 0x18014d3db */
/* cut edge: 0x1800861c1 -> 0x1800a5f50 */
/* cut edge: 0x1800861e5 -> 0x1800a5f83 */
/* cut edge: 0x18008624c -> 0x1800a5fd4 */
/* cut edge: 0x18008625c -> 0x1800a600e */
/* cut edge: 0x18008637c -> 0x18014d3db */
/* cut edge: 0x1800864c5 -> 0x18014d3db */
/* cut edge: 0x1800864db -> 0x18014d3db */
/* cut edge: 0x180086543 -> 0x1800a6325 */
/* cut edge: 0x180086567 -> 0x1800a6358 */
/* cut edge: 0x1800865c8 -> 0x1800a63a9 */
/* cut edge: 0x1800865d8 -> 0x1800a63e2 */
/* cut edge: 0x1800866b0 -> 0x18014d3db */
/* cut edge: 0x1800867a5 -> 0x18014d3db */
/* cut edge: 0x1800867bb -> 0x18014d3db */
/* cut edge: 0x18008682e -> 0x1800a67ec */
/* cut edge: 0x180086852 -> 0x1800a681f */
/* cut edge: 0x1800868b3 -> 0x1800a686d */
/* cut edge: 0x1800868cb -> 0x1800a68a7 */
/* cut edge: 0x1800869a8 -> 0x18014d3db */
/* cut edge: 0x180086aa2 -> 0x18014d3db */
/* cut edge: 0x180086ab8 -> 0x18014d3db */
/* cut edge: 0x180086b2b -> 0x1800a6cba */
/* cut edge: 0x180086b4f -> 0x1800a6ced */
/* cut edge: 0x180086bb0 -> 0x1800a6d3e */
/* cut edge: 0x180086bc0 -> 0x1800a6d77 */
/* cut edge: 0x180086c98 -> 0x18014d3db */
/* cut edge: 0x180086d8d -> 0x18014d3db */
/* cut edge: 0x180086da3 -> 0x18014d3db */
/* cut edge: 0x180086e0e -> 0x1800a6ea2 */
/* cut edge: 0x180086e37 -> 0x1800a6ed5 */
/* cut edge: 0x180086e98 -> 0x1800a6f26 */
/* cut edge: 0x180086ea8 -> 0x1800a6f5f */
/* cut edge: 0x180086f85 -> 0x18014d3db */
/* cut edge: 0x18008707c -> 0x1801253b0 */
/* cut edge: 0x180087090 -> 0x18009cf17 */
/* cut edge: 0x1800870af -> 0x18014d3db */
/* cut edge: 0x1800870d5 -> 0x18009d16d */
/* cut edge: 0x1800870fe -> 0x18014d3db */
/* cut edge: 0x1800870fe -> 0x180087124 */
/* target 0x180087124 original byte 0x48 replaced by RET */
/* target 0x18009cf17 original byte 0x48 replaced by RET */
/* target 0x18009d16d original byte 0x48 replaced by RET */
/* target 0x1800a5f50 original byte 0x48 replaced by RET */
/* target 0x1800a5f83 original byte 0x48 replaced by RET */
/* target 0x1800a5fd4 original byte 0x48 replaced by RET */
/* target 0x1800a600e original byte 0x48 replaced by RET */
/* target 0x1800a6325 original byte 0x48 replaced by RET */
/* target 0x1800a6358 original byte 0x48 replaced by RET */
/* target 0x1800a63a9 original byte 0x48 replaced by RET */
/* target 0x1800a63e2 original byte 0x48 replaced by RET */
/* target 0x1800a67ec original byte 0x48 replaced by RET */
/* target 0x1800a681f original byte 0x48 replaced by RET */
/* target 0x1800a686d original byte 0x48 replaced by RET */
/* target 0x1800a68a7 original byte 0x48 replaced by RET */
/* target 0x1800a6cba original byte 0x48 replaced by RET */
/* target 0x1800a6ced original byte 0x48 replaced by RET */
/* target 0x1800a6d3e original byte 0x48 replaced by RET */
/* target 0x1800a6d77 original byte 0x48 replaced by RET */
/* target 0x1800a6ea2 original byte 0x48 replaced by RET */
/* target 0x1800a6ed5 original byte 0x48 replaced by RET */
/* target 0x1800a6f26 original byte 0x48 replaced by RET */
/* target 0x1800a6f5f original byte 0x48 replaced by RET */
/* target 0x1801253b0 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00103_180086137(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 unaff_R13;
  longlong *in_stack_00000048;
  undefined8 uStack0000000000000050;
  longlong in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
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
  undefined8 in_stack_00000168;
  
  uStack0000000000000050 = in_stack_00000168;
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    uVar10 = DAT_1804b5208;
    lVar5 = DAT_1804b5200;
    if (cVar6 == '\0') {
      if (DAT_1804ac750 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5200 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000a70,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00007da0,0x2a);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7);
        DAT_1804ac750 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
        unaff_R13 = in_stack_00000078;
      }
      uStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804ac750,in_stack_000000b0);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_000000a0,0);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar10,0,in_stack_00000068);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00000168);
      (*pcVar1)(in_stack_00000048,uVar10,1,uVar7);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar10,2,unaff_R13);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         in_stack_00000060 % 0xe8e8f ^ 0xe9e4f46701fbf2fd);
      (*pcVar1)(in_stack_00000048,uVar10,3,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
      (*pcVar1)(in_stack_00000048,uVar10,4,uVar7);
      (**(code **)(*in_stack_00000048 + 0x110))
                (in_stack_00000048,uStack0000000000000050,in_stack_000000a8,uVar10);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          lVar5 = DAT_1804b52e8;
          uVar10 = DAT_1804b5208;
          if (cVar6 == '\0') {
            if (DAT_1804ac758 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b52e8 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000a6e,1);
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
                                (in_stack_00000048,&stack0x000045f8,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090)
              ;
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7);
              DAT_1804ac758 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac758,in_stack_000000b0);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2b33);
            (*pcVar1)(in_stack_00000048,uVar10,0,uVar7);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_00000060 % 0x6521c ^ in_stack_000000c0 ^ 0xe2b0ee9993434d02)
            ;
            (*pcVar1)(in_stack_00000048,uVar10,1,uVar7);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uStack0000000000000050,in_stack_000000a8,uVar10);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                lVar5 = DAT_1804b52e8;
                uVar10 = DAT_1804b5208;
                if (cVar6 == '\0') {
                  if (DAT_1804ac760 == 0) {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b52e8 == 0) {
                      return;
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000a6c,1);
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
                                      (in_stack_00000048,&stack0x000045e8,5);
                    if (DAT_1804b4d40 == 0) {
                      return;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000090);
                    (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
                    uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7);
                    DAT_1804ac760 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  uStack0000000000000050 =
                       (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804ac760,in_stack_000000b0);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                     (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6030);
                  (*pcVar1)(in_stack_00000048,uVar10,0,uVar7);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_00000060 % 0xbe8d5 ^ in_stack_000000c0 ^
                                     0xff7217bae0a20df7);
                  (*pcVar1)(in_stack_00000048,uVar10,1,uVar7);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uStack0000000000000050,in_stack_000000a8,
                                      uVar10);
                  (**(code **)(*in_stack_00000048 + 0x1a0))
                            (in_stack_00000048,uVar10,in_stack_000000d8);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      lVar5 = DAT_1804b52e8;
                      uVar10 = DAT_1804b5208;
                      if (cVar6 == '\0') {
                        if (DAT_1804ac768 == 0) {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b52e8 == 0) {
                            return;
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000a6a,1);
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
                                            (in_stack_00000048,&stack0x000045d8,5);
                          if (DAT_1804b4d40 == 0) {
                            return;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000090);
                          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
                          uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7)
                          ;
                          DAT_1804ac768 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                        }
                        uStack0000000000000050 =
                             (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804ac768,in_stack_000000b0);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                           (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x2854);
                        (*pcVar1)(in_stack_00000048,uVar10,0,uVar7);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_00000060 % 0xcd3f8 ^ in_stack_000000c0 ^
                                           0xfdc3eaed9ebcea22);
                        (*pcVar1)(in_stack_00000048,uVar10,1,uVar7);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uStack0000000000000050,
                                            in_stack_000000a8,uVar10);
                        (**(code **)(*in_stack_00000048 + 0x1a0))
                                  (in_stack_00000048,uVar10,in_stack_000000d8);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 == '\0') {
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                            lVar5 = DAT_1804b52e8;
                            uVar10 = DAT_1804b5208;
                            if (cVar6 == '\0') {
                              if (DAT_1804ac770 == 0) {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b52e8 == 0) {
                                  return;
                                }
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00000a68,1);
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
                                                  (in_stack_00000048,&stack0x000045c8,5);
                                if (DAT_1804b4d40 == 0) {
                                  return;
                                }
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000090);
                                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
                                uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,
                                                   uVar7);
                                DAT_1804ac770 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar10);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                              }
                              uStack0000000000000050 =
                                   (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804ac770,in_stack_000000b0);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                                 (in_stack_00000048,2,in_stack_000000a0,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000c8,
                                                 in_stack_000000d0,0x6cb6);
                              (*pcVar1)(in_stack_00000048,uVar10,0,uVar7);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,
                                                 in_stack_00000060 % 0x754e3 ^ in_stack_000000c0 ^
                                                 0xbed37c306605a03f);
                              (*pcVar1)(in_stack_00000048,uVar10,1,uVar7);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,uStack0000000000000050,
                                                  in_stack_000000a8,uVar10);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar10,in_stack_000000d8);
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              lVar5 = DAT_1804b5270;
                              if (cVar6 == '\0') {
                                if (in_stack_000000f8 == 0) {
                                  return;
                                }
                                if (DAT_1804b5270 == 0) {
                                  return;
                                }
                                uVar10 = (**(code **)(*in_stack_00000048 + 0xd8))
                                                   (in_stack_00000048,DAT_1804b5270);
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar6 == '\0') {
                                  if (DAT_1804b5278 == 0) {
                                    return;
                                  }
                                  uStack0000000000000050 = uVar10;
                                  (**(code **)(*in_stack_00000048 + 0x2d8))
                                            (in_stack_00000048,uVar10,lVar5);
                                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar6 == '\0') {
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
  return;
}

