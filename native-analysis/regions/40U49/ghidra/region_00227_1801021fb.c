/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=227 entry=1801021fb blocks=52 maxInstructions=100000 */

/* cut edge: 0x1801021fb -> 0x18014d3db */
/* cut edge: 0x180102211 -> 0x18014d3db */
/* cut edge: 0x180102274 -> 0x18010dd27 */
/* cut edge: 0x180102298 -> 0x18010dd5a */
/* cut edge: 0x1801022f9 -> 0x18010ddab */
/* cut edge: 0x180102309 -> 0x18010dde6 */
/* cut edge: 0x1801023e1 -> 0x18014d3db */
/* cut edge: 0x1801024c2 -> 0x18014d3db */
/* cut edge: 0x1801024d8 -> 0x18014d3db */
/* cut edge: 0x180102538 -> 0x18010e4cc */
/* cut edge: 0x18010255c -> 0x18010e4ff */
/* cut edge: 0x1801025bd -> 0x18010e550 */
/* cut edge: 0x1801025cd -> 0x18010e58b */
/* cut edge: 0x1801026a5 -> 0x18014d3db */
/* cut edge: 0x180102786 -> 0x18014d3db */
/* cut edge: 0x18010279c -> 0x18014d3db */
/* cut edge: 0x1801027ff -> 0x18010c726 */
/* cut edge: 0x180102823 -> 0x18010c759 */
/* cut edge: 0x180102884 -> 0x18010c7aa */
/* cut edge: 0x180102894 -> 0x18010c7e5 */
/* cut edge: 0x18010296c -> 0x18014d3db */
/* cut edge: 0x180102a4d -> 0x18014d3db */
/* cut edge: 0x180102a63 -> 0x18014d3db */
/* cut edge: 0x180102a79 -> 0x18014d3db */
/* cut edge: 0x180102a8f -> 0x18014d3db */
/* cut edge: 0x180102aa5 -> 0x18014d3db */
/* cut edge: 0x180102ad8 -> 0x18014d3db */
/* cut edge: 0x180102b4d -> 0x18010d767 */
/* cut edge: 0x180102b71 -> 0x18010d79d */
/* cut edge: 0x180102bcd -> 0x18010d7ee */
/* cut edge: 0x180102be2 -> 0x18010d827 */
/* cut edge: 0x180102cba -> 0x18014d3db */
/* cut edge: 0x180102db1 -> 0x18014d3db */
/* cut edge: 0x180102dc7 -> 0x18014d3db */
/* cut edge: 0x180102e3f -> 0x18010df11 */
/* cut edge: 0x180102e63 -> 0x18010df44 */
/* cut edge: 0x180102ec7 -> 0x18010df95 */
/* cut edge: 0x180102ed7 -> 0x18010dfcf */
/* cut edge: 0x180102fb6 -> 0x18014d3db */
/* cut edge: 0x1801030ab -> 0x18014d3db */
/* cut edge: 0x1801030c1 -> 0x18014d3db */
/* cut edge: 0x1801030d7 -> 0x1801032a2 */
/* cut edge: 0x1801030d7 -> 0x18010312e */
/* target 0x18010312e original byte 0x48 replaced by RET */
/* target 0x1801032a2 original byte 0x48 replaced by RET */
/* target 0x18010c726 original byte 0x48 replaced by RET */
/* target 0x18010c759 original byte 0x48 replaced by RET */
/* target 0x18010c7aa original byte 0x48 replaced by RET */
/* target 0x18010c7e5 original byte 0x48 replaced by RET */
/* target 0x18010d767 original byte 0x48 replaced by RET */
/* target 0x18010d79d original byte 0x48 replaced by RET */
/* target 0x18010d7ee original byte 0x48 replaced by RET */
/* target 0x18010d827 original byte 0x48 replaced by RET */
/* target 0x18010dd27 original byte 0x48 replaced by RET */
/* target 0x18010dd5a original byte 0x48 replaced by RET */
/* target 0x18010ddab original byte 0x48 replaced by RET */
/* target 0x18010dde6 original byte 0x48 replaced by RET */
/* target 0x18010df11 original byte 0x48 replaced by RET */
/* target 0x18010df44 original byte 0x48 replaced by RET */
/* target 0x18010df95 original byte 0x48 replaced by RET */
/* target 0x18010dfcf original byte 0x48 replaced by RET */
/* target 0x18010e4cc original byte 0x48 replaced by RET */
/* target 0x18010e4ff original byte 0x48 replaced by RET */
/* target 0x18010e550 original byte 0x48 replaced by RET */
/* target 0x18010e58b original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00227_1801021fb(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  code *pcVar4;
  undefined8 uVar5;
  code *pcVar6;
  code *pcVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  longlong *in_stack_00000048;
  longlong in_stack_00000058;
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
  
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    uVar9 = DAT_1804b5208;
    lVar3 = DAT_1804b5200;
    if ((char)pcVar4 == '\0') {
      if (DAT_1804ae6a0 == 0) {
        pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5200 == 0) {
          return (code *)0x0;
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000282,1);
        lVar2 = DAT_1804b4d38;
        lVar1 = DAT_1804b4d30;
        pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return pcVar6;
        }
        if (DAT_1804b4d38 == 0) {
          return pcVar6;
        }
        pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                   (in_stack_00000048,&stack0x000011a8,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar7;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
        uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar5);
        DAT_1804ae6a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ae6a0,in_stack_000000b0);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         in_stack_00000058 % 0xf32fc ^ 0xe9d0458e26c26c4d);
      (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
      (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
      (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar4 == '\0') {
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar4 == '\0') {
          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          uVar9 = DAT_1804b5208;
          lVar3 = DAT_1804b5200;
          if ((char)pcVar4 == '\0') {
            if (DAT_1804ae6a8 == 0) {
              pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b5200 == 0) {
                return (code *)0x0;
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000280,1);
              lVar2 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar6;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar6;
              }
              pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x00001198,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar7;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090)
              ;
              (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
              uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar5);
              DAT_1804ae6a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804ae6a8,in_stack_000000b0);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_00000058 % 0xa4f5b ^ 0xe9d2055714a697af);
            (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_000000c0);
            (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
            (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar4 == '\0') {
              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)pcVar4 == '\0') {
                pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                uVar9 = DAT_1804b5208;
                lVar3 = DAT_1804b5200;
                if ((char)pcVar4 == '\0') {
                  if (DAT_1804ae6b0 == 0) {
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b5200 == 0) {
                      return (code *)0x0;
                    }
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x0000027e,1);
                    lVar2 = DAT_1804b4d38;
                    lVar1 = DAT_1804b4d30;
                    pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b4d30 == 0) {
                      return pcVar6;
                    }
                    if (DAT_1804b4d38 == 0) {
                      return pcVar6;
                    }
                    pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                               (in_stack_00000048,&stack0x00001188,5);
                    if (DAT_1804b4d40 == 0) {
                      return pcVar7;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000090);
                    (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
                    uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar5);
                    DAT_1804ae6b0 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                  }
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804ae6b0,in_stack_000000b0);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_00000058 % 0x7e52f ^ 0xe9d40c08d08221c8);
                  (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_000000c0);
                  (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                  (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)pcVar4 == '\0') {
                    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)pcVar4 == '\0') {
                      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)pcVar4 == '\0') {
                        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)pcVar4 == '\0') {
                          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)pcVar4 == '\0') {
                            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)pcVar4 == '\0') {
                              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                              lVar3 = DAT_1804b52e8;
                              uVar9 = DAT_1804b5208;
                              if ((char)pcVar4 == '\0') {
                                if (DAT_1804ae6b8 == 0) {
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b52e8 == 0) {
                                    return pcVar4;
                                  }
                                  uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x0000027c,1);
                                  lVar2 = DAT_1804b4d38;
                                  lVar1 = DAT_1804b4d30;
                                  pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b4d30 == 0) {
                                    return pcVar6;
                                  }
                                  if (DAT_1804b4d38 == 0) {
                                    return pcVar6;
                                  }
                                  pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                             (in_stack_00000048,&stack0x00001178,5);
                                  if (DAT_1804b4d40 == 0) {
                                    return pcVar7;
                                  }
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,in_stack_00000090);
                                  (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
                                  uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,
                                                    uVar5);
                                  DAT_1804ae6b8 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar9);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                                }
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804ae6b8,in_stack_000000b0
                                                  );
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x6b42);
                                (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   in_stack_00000058 % 0x6ac77 ^ in_stack_000000c0 ^
                                                   0xd92b75c2d7bd8145);
                                (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar9,in_stack_000000d8);
                                pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                           (in_stack_00000048);
                                if ((char)pcVar4 == '\0') {
                                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)pcVar4 == '\0') {
                                    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                                    lVar3 = DAT_1804b52e8;
                                    uVar9 = DAT_1804b5208;
                                    if ((char)pcVar4 == '\0') {
                                      if (DAT_1804ae6c0 == 0) {
                                        pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                        if (DAT_1804b52e8 == 0) {
                                          return (code *)0x0;
                                        }
                                        uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                                          (in_stack_00000048,&stack0x0000027a,1);
                                        lVar2 = DAT_1804b4d38;
                                        lVar1 = DAT_1804b4d30;
                                        pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                                        if (DAT_1804b4d30 == 0) {
                                          return pcVar6;
                                        }
                                        if (DAT_1804b4d38 == 0) {
                                          return pcVar6;
                                        }
                                        pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                                   (in_stack_00000048,
                                                                    &stack0x00001168,5);
                                        if (DAT_1804b4d40 == 0) {
                                          return pcVar7;
                                        }
                                        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,in_stack_00000090);
                                        (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
                                        uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,
                                                          uVar9,uVar5);
                                        DAT_1804ae6c0 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar9);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar9);
                                      }
                                      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,DAT_1804ae6c0,
                                                         in_stack_000000b0);
                                      uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,2,in_stack_000000a0,0);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_000000c8,
                                                         in_stack_000000d0,0x1fd7);
                                      (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_000000b8,
                                                         in_stack_00000058 % 0xc1900 ^
                                                         in_stack_000000c0 ^ 0xdf105eff36074643);
                                      (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                                      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,uVar9,in_stack_000000a8,
                                                         uVar5);
                                      (**(code **)(*in_stack_00000048 + 0x1a0))
                                                (in_stack_00000048,uVar9,in_stack_000000d8);
                                      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                                 (in_stack_00000048);
                                      if ((char)pcVar4 == '\0') {
                                        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))()
                                        ;
                                        if ((char)pcVar4 == '\0') {
                                          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))
                                                                     ();
                                          if ((char)pcVar4 == '\0') {
                                            if (DAT_1804ae6c8 == 0) {
                                              return (code *)0x96423f6e0c3da8e5;
                                            }
                                            return (code *)0x96423f6e0c3da8e5;
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
    }
  }
  return pcVar4;
}

