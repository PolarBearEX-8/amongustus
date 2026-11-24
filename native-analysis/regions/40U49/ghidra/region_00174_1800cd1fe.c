/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=174 entry=1800cd1fe blocks=50 maxInstructions=100000 */

/* cut edge: 0x1800cd1fe -> 0x18014d3db */
/* cut edge: 0x1800cd214 -> 0x18014d3db */
/* cut edge: 0x1800cd287 -> 0x1800ddde6 */
/* cut edge: 0x1800cd2ab -> 0x1800dde19 */
/* cut edge: 0x1800cd30c -> 0x1800dde6a */
/* cut edge: 0x1800cd31c -> 0x1800ddea3 */
/* cut edge: 0x1800cd3f4 -> 0x18014d3db */
/* cut edge: 0x1800cd4e9 -> 0x18014d3db */
/* cut edge: 0x1800cd4ff -> 0x18014d3db */
/* cut edge: 0x1800cd56a -> 0x1800ddfcb */
/* cut edge: 0x1800cd58e -> 0x1800ddffe */
/* cut edge: 0x1800cd5ec -> 0x1800de04c */
/* cut edge: 0x1800cd604 -> 0x1800de085 */
/* cut edge: 0x1800cd6de -> 0x18014d3db */
/* cut edge: 0x1800cd7db -> 0x1801253b0 */
/* cut edge: 0x1800cd7ea -> 0x18014d3db */
/* cut edge: 0x1800cd800 -> 0x18014d3db */
/* cut edge: 0x1800cd870 -> 0x1800d5edd */
/* cut edge: 0x1800cd894 -> 0x1800d5f10 */
/* cut edge: 0x1800cd8f5 -> 0x1800d5f61 */
/* cut edge: 0x1800cd905 -> 0x1800d5f9a */
/* cut edge: 0x1800cd9dd -> 0x18014d3db */
/* cut edge: 0x1800cdad4 -> 0x18014d3db */
/* cut edge: 0x1800cdaea -> 0x18014d3db */
/* cut edge: 0x1800cdb64 -> 0x1800d78a6 */
/* cut edge: 0x1800cdb8b -> 0x1800d78d9 */
/* cut edge: 0x1800cdbec -> 0x1800d792a */
/* cut edge: 0x1800cdbfc -> 0x1800d7964 */
/* cut edge: 0x1800cdcda -> 0x18014d3db */
/* cut edge: 0x1800cddcf -> 0x18014d3db */
/* cut edge: 0x1800cdde5 -> 0x18014d3db */
/* cut edge: 0x1800cde55 -> 0x1800d9460 */
/* cut edge: 0x1800cde7c -> 0x1800d9493 */
/* cut edge: 0x1800cdedd -> 0x1800d94e4 */
/* cut edge: 0x1800cdeed -> 0x1800d951d */
/* cut edge: 0x1800cdfc8 -> 0x18014d3db */
/* cut edge: 0x1800ce0bf -> 0x18014d3db */
/* cut edge: 0x1800ce0d5 -> 0x18014d3db */
/* cut edge: 0x1800ce0eb -> 0x1800ce2ae */
/* cut edge: 0x1800ce0eb -> 0x1800ce145 */
/* target 0x1800ce145 original byte 0x48 replaced by RET */
/* target 0x1800ce2ae original byte 0x48 replaced by RET */
/* target 0x1800d5edd original byte 0x48 replaced by RET */
/* target 0x1800d5f10 original byte 0x48 replaced by RET */
/* target 0x1800d5f61 original byte 0x48 replaced by RET */
/* target 0x1800d5f9a original byte 0x48 replaced by RET */
/* target 0x1800d78a6 original byte 0x48 replaced by RET */
/* target 0x1800d78d9 original byte 0x48 replaced by RET */
/* target 0x1800d792a original byte 0x48 replaced by RET */
/* target 0x1800d7964 original byte 0x48 replaced by RET */
/* target 0x1800d9460 original byte 0x48 replaced by RET */
/* target 0x1800d9493 original byte 0x48 replaced by RET */
/* target 0x1800d94e4 original byte 0x48 replaced by RET */
/* target 0x1800d951d original byte 0x48 replaced by RET */
/* target 0x1800ddde6 original byte 0x48 replaced by RET */
/* target 0x1800dde19 original byte 0x48 replaced by RET */
/* target 0x1800dde6a original byte 0x48 replaced by RET */
/* target 0x1800ddea3 original byte 0x48 replaced by RET */
/* target 0x1800ddfcb original byte 0x48 replaced by RET */
/* target 0x1800ddffe original byte 0x48 replaced by RET */
/* target 0x1800de04c original byte 0x48 replaced by RET */
/* target 0x1800de085 original byte 0x48 replaced by RET */
/* target 0x1801253b0 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00174_1800cd1fe(void)

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
  ulonglong in_stack_00000078;
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
  
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b52e8;
    uVar9 = DAT_1804b5208;
    if ((char)pcVar4 == '\0') {
      if (DAT_1804ad758 == 0) {
        pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b52e8 == 0) {
          return (code *)0x0;
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000065c,1);
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
                                   (in_stack_00000048,&stack0x00002c08,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar7;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
        uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar5);
        DAT_1804ad758 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ad758,in_stack_000000b0);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x59c1);
      (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         (longlong)in_stack_00000078 % 0xbf09b ^ in_stack_000000c0 ^
                         0x54bfa90bae225e70);
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
            if (DAT_1804ad760 == 0) {
              pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b52e8 == 0) {
                return (code *)0x0;
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x0000065a,1);
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
                                         (in_stack_00000048,&stack0x00002bf8,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar7;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090)
              ;
              (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
              uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar5);
              DAT_1804ad760 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804ad760,in_stack_000000b0);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2f86);
            (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               (longlong)in_stack_00000078 % 0x9c8fc ^ in_stack_000000c0 ^
                               0x412b3b51cb94690a);
            (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
            lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                               (in_stack_00000048,uVar9,in_stack_000000d8);
            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar4 == '\0') {
              lVar11 = lVar11 + (uVar10 ^ in_stack_00000078);
              if (in_stack_000000f8 == 0) {
                return pcVar4;
              }
              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)pcVar4 == '\0') {
                pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                lVar1 = DAT_1804b52e8;
                uVar9 = DAT_1804b5208;
                if ((char)pcVar4 == '\0') {
                  if (DAT_1804ad768 == 0) {
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b52e8 == 0) {
                      return (code *)0x0;
                    }
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000658,1);
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
                                               (in_stack_00000048,&stack0x00002be8,5);
                    if (DAT_1804b4d40 == 0) {
                      return pcVar7;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000090);
                    (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                    uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar9,uVar5);
                    DAT_1804ad768 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                  }
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804ad768,in_stack_000000b0);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7139);
                  (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     lVar11 % 0x4004d ^ in_stack_000000c0 ^ 0x2f484c8973b90427);
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
                        if (DAT_1804ad770 == 0) {
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b52e8 == 0) {
                            return (code *)0x0;
                          }
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000656,1);
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
                                                     (in_stack_00000048,&stack0x00002bd8,5);
                          if (DAT_1804b4d40 == 0) {
                            return pcVar7;
                          }
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000090);
                          (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                          uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar9,uVar5);
                          DAT_1804ad770 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                        }
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804ad770,in_stack_000000b0);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x514e);
                        (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           lVar11 % 0xa4580 ^ in_stack_000000c0 ^ 0x6ebc1f6cd58d76fa
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
                              if (DAT_1804ad778 == 0) {
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b52e8 == 0) {
                                  return (code *)0x0;
                                }
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00000654,1);
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
                                                           (in_stack_00000048,&stack0x00002bc8,5);
                                if (DAT_1804b4d40 == 0) {
                                  return pcVar7;
                                }
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000090);
                                (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                                uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar1,uVar9,
                                                  uVar5);
                                DAT_1804ad778 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar9);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                              }
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804ad778,in_stack_000000b0);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_000000a0,0);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000c8,
                                                 in_stack_000000d0,0x7219);
                              (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,
                                                 lVar11 % 0xe2c03 ^ in_stack_000000c0 ^
                                                 0x1bc79e840bfce42b);
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
                                    if (DAT_1804ad780 == 0) {
                                      return (code *)0xbdd535ef63c6426;
                                    }
                                    return (code *)0xbdd535ef63c6426;
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

