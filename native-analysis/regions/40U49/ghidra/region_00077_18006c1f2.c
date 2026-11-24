/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=77 entry=18006c1f2 blocks=50 maxInstructions=100000 */

/* cut edge: 0x18006c1f2 -> 0x18014d3db */
/* cut edge: 0x18006c208 -> 0x18014d3db */
/* cut edge: 0x18006c275 -> 0x180072bd1 */
/* cut edge: 0x18006c299 -> 0x180072c04 */
/* cut edge: 0x18006c2fd -> 0x180072c55 */
/* cut edge: 0x18006c30d -> 0x180072c8e */
/* cut edge: 0x18006c3e9 -> 0x18014d3db */
/* cut edge: 0x18006c4de -> 0x18014d3db */
/* cut edge: 0x18006c4f4 -> 0x18014d3db */
/* cut edge: 0x18006c55f -> 0x18007328b */
/* cut edge: 0x18006c588 -> 0x1800732be */
/* cut edge: 0x18006c5ec -> 0x18007330f */
/* cut edge: 0x18006c5fc -> 0x180073348 */
/* cut edge: 0x18006c6dd -> 0x18014d3db */
/* cut edge: 0x18006c7d4 -> 0x1801253c7 */
/* cut edge: 0x18006c7e8 -> 0x18006d3c1 */
/* cut edge: 0x18006c7f7 -> 0x18014d3db */
/* cut edge: 0x18006c80d -> 0x18014d3db */
/* cut edge: 0x18006c875 -> 0x1800747ba */
/* cut edge: 0x18006c899 -> 0x1800747ed */
/* cut edge: 0x18006c8fd -> 0x18007483e */
/* cut edge: 0x18006c90d -> 0x180074877 */
/* cut edge: 0x18006c9e9 -> 0x18014d3db */
/* cut edge: 0x18006cade -> 0x18014d3db */
/* cut edge: 0x18006caf4 -> 0x18014d3db */
/* cut edge: 0x18006cb64 -> 0x1800748af */
/* cut edge: 0x18006cb88 -> 0x1800748e2 */
/* cut edge: 0x18006cbe9 -> 0x180074930 */
/* cut edge: 0x18006cc01 -> 0x180074969 */
/* cut edge: 0x18006ccde -> 0x18014d3db */
/* cut edge: 0x18006cdd5 -> 0x18014d3db */
/* cut edge: 0x18006cdeb -> 0x18014d3db */
/* cut edge: 0x18006ce5b -> 0x1800749a0 */
/* cut edge: 0x18006ce7f -> 0x1800749d3 */
/* cut edge: 0x18006cee3 -> 0x180074a24 */
/* cut edge: 0x18006cef3 -> 0x180074a5d */
/* cut edge: 0x18006cfcf -> 0x18014d3db */
/* cut edge: 0x18006d0c4 -> 0x18014d3db */
/* cut edge: 0x18006d0da -> 0x18014d3db */
/* cut edge: 0x18006d0f0 -> 0x18006d2b5 */
/* cut edge: 0x18006d0f0 -> 0x18006d145 */
/* target 0x18006d145 original byte 0x48 replaced by RET */
/* target 0x18006d2b5 original byte 0x48 replaced by RET */
/* target 0x18006d3c1 original byte 0x48 replaced by RET */
/* target 0x180072bd1 original byte 0x48 replaced by RET */
/* target 0x180072c04 original byte 0x48 replaced by RET */
/* target 0x180072c55 original byte 0x48 replaced by RET */
/* target 0x180072c8e original byte 0x48 replaced by RET */
/* target 0x18007328b original byte 0x48 replaced by RET */
/* target 0x1800732be original byte 0x48 replaced by RET */
/* target 0x18007330f original byte 0x48 replaced by RET */
/* target 0x180073348 original byte 0x48 replaced by RET */
/* target 0x1800747ba original byte 0x48 replaced by RET */
/* target 0x1800747ed original byte 0x48 replaced by RET */
/* target 0x18007483e original byte 0x48 replaced by RET */
/* target 0x180074877 original byte 0x48 replaced by RET */
/* target 0x1800748af original byte 0x48 replaced by RET */
/* target 0x1800748e2 original byte 0x48 replaced by RET */
/* target 0x180074930 original byte 0x48 replaced by RET */
/* target 0x180074969 original byte 0x48 replaced by RET */
/* target 0x1800749a0 original byte 0x48 replaced by RET */
/* target 0x1800749d3 original byte 0x48 replaced by RET */
/* target 0x180074a24 original byte 0x48 replaced by RET */
/* target 0x180074a5d original byte 0x48 replaced by RET */
/* target 0x1801253c7 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00077_18006c1f2(void)

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
  ulonglong uVar10;
  longlong lVar11;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
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
  longlong in_stack_00000c38;
  
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b52e8;
    uVar9 = DAT_1804b5208;
    if ((char)pcVar4 == '\0') {
      if (DAT_1804ae3c8 == 0) {
        pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b52e8 == 0) {
          return (code *)0x0;
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000033a,1);
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
                                   (in_stack_00000048,&stack0x000016b8,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar7;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
        uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar5);
        DAT_1804ae3c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ae3c8,in_stack_000000b0);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3d29);
      (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         (longlong)in_stack_00000058 % 0xcf0a8 ^ in_stack_000000c0 ^
                         0x46b8194276c641ce);
      (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar4 == '\0') {
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar4 == '\0') {
          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          lVar11 = DAT_1804b52e8;
          uVar9 = DAT_1804b5208;
          if ((char)pcVar4 == '\0') {
            if (DAT_1804ae3d0 == 0) {
              pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b52e8 == 0) {
                return (code *)0x0;
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000338,1);
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
                                         (in_stack_00000048,&stack0x000016a8,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar7;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090)
              ;
              (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
              uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar5);
              DAT_1804ae3d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804ae3d0,in_stack_000000b0);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x475a);
            (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               (longlong)in_stack_00000058 % 0x9e47b ^ in_stack_000000c0 ^
                               0x2325b0dad0e43e06);
            (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
            lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                               (in_stack_00000048,uVar9,in_stack_000000d8);
            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar4 == '\0') {
              lVar11 = lVar11 + (uVar10 ^ in_stack_00000058);
              if (in_stack_000000f8 == 0) {
                return pcVar4;
              }
              if (in_stack_00000c38 == 0) {
                return pcVar4;
              }
              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)pcVar4 == '\0') {
                pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                lVar1 = DAT_1804b52e8;
                uVar9 = DAT_1804b5208;
                if ((char)pcVar4 == '\0') {
                  if (DAT_1804ae3d8 == 0) {
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b52e8 == 0) {
                      return (code *)0x0;
                    }
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000336,1);
                    lVar3 = DAT_1804b4d38;
                    lVar2 = DAT_1804b4d30;
                    pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b4d30 == 0) {
                      return pcVar6;
                    }
                    if (DAT_1804b4d38 == 0) {
                      return pcVar6;
                    }
                    pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                               (in_stack_00000048,&stack0x00001698,5);
                    if (DAT_1804b4d40 == 0) {
                      return pcVar7;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000090);
                    (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                    uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar9,uVar5);
                    DAT_1804ae3d8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                  }
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804ae3d8,in_stack_000000b0);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x9b6);
                  (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     lVar11 % 0x50095 ^ in_stack_000000c0 ^ 0xf15a1c6704621fe3);
                  (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
                  (**(code **)(*in_stack_00000048 + 0x1a0))
                            (in_stack_00000048,uVar9,in_stack_000000d8);
                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)pcVar4 == '\0') {
                    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)pcVar4 == '\0') {
                      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                      lVar1 = DAT_1804b52e8;
                      uVar9 = DAT_1804b5208;
                      if ((char)pcVar4 == '\0') {
                        if (DAT_1804ae3e0 == 0) {
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b52e8 == 0) {
                            return (code *)0x0;
                          }
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000334,1);
                          lVar3 = DAT_1804b4d38;
                          lVar2 = DAT_1804b4d30;
                          pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b4d30 == 0) {
                            return pcVar6;
                          }
                          if (DAT_1804b4d38 == 0) {
                            return pcVar6;
                          }
                          pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                     (in_stack_00000048,&stack0x00001688,5);
                          if (DAT_1804b4d40 == 0) {
                            return pcVar7;
                          }
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000090);
                          (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                          uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar9,uVar5);
                          DAT_1804ae3e0 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                        }
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804ae3e0,in_stack_000000b0);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x2319);
                        (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           lVar11 % 0x5ee93 ^ in_stack_000000c0 ^ 0x998d6b12a66c619d
                                          );
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
                            lVar1 = DAT_1804b52e8;
                            uVar9 = DAT_1804b5208;
                            if ((char)pcVar4 == '\0') {
                              if (DAT_1804ae3e8 == 0) {
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b52e8 == 0) {
                                  return (code *)0x0;
                                }
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00000332,1);
                                lVar3 = DAT_1804b4d38;
                                lVar2 = DAT_1804b4d30;
                                pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b4d30 == 0) {
                                  return pcVar6;
                                }
                                if (DAT_1804b4d38 == 0) {
                                  return pcVar6;
                                }
                                pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                           (in_stack_00000048,&stack0x00001678,5);
                                if (DAT_1804b4d40 == 0) {
                                  return pcVar7;
                                }
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000090);
                                (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                                uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar9,
                                                  uVar5);
                                DAT_1804ae3e8 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar9);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                              }
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804ae3e8,in_stack_000000b0);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_000000a0,0);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000c8,
                                                 in_stack_000000d0,0x479b);
                              (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,
                                                 lVar11 % 0xbe4f9 ^ in_stack_000000c0 ^
                                                 0xe64a170a37ff8268);
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
                                  if ((char)pcVar4 == '\0') {
                                    if (DAT_1804ae3f0 == 0) {
                                      return (code *)0x8f4fc2099ddd9883;
                                    }
                                    return (code *)0x8f4fc2099ddd9883;
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

