/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=80 entry=18006f130 blocks=51 maxInstructions=100000 */

/* cut edge: 0x18006f1b3 -> 0x18014d3db */
/* cut edge: 0x18006f2aa -> 0x18014d3db */
/* cut edge: 0x18006f2c0 -> 0x18014d3db */
/* cut edge: 0x18006f32d -> 0x180071e66 */
/* cut edge: 0x18006f351 -> 0x180071e99 */
/* cut edge: 0x18006f3b5 -> 0x180071eea */
/* cut edge: 0x18006f3c5 -> 0x180071f24 */
/* cut edge: 0x18006f4a4 -> 0x18014d3db */
/* cut edge: 0x18006f599 -> 0x18014d3db */
/* cut edge: 0x18006f5af -> 0x18014d3db */
/* cut edge: 0x18006f61c -> 0x180072705 */
/* cut edge: 0x18006f640 -> 0x180072738 */
/* cut edge: 0x18006f6a4 -> 0x180072789 */
/* cut edge: 0x18006f6b4 -> 0x1800727c2 */
/* cut edge: 0x18006f790 -> 0x18014d3db */
/* cut edge: 0x18006f885 -> 0x18014d3db */
/* cut edge: 0x18006f89b -> 0x18014d3db */
/* cut edge: 0x18006f909 -> 0x180072dbd */
/* cut edge: 0x18006f932 -> 0x180072df3 */
/* cut edge: 0x18006f98e -> 0x180072e44 */
/* cut edge: 0x18006f9a1 -> 0x180072e7d */
/* cut edge: 0x18006fa7f -> 0x18014d3db */
/* cut edge: 0x18006fb74 -> 0x180125423 */
/* cut edge: 0x18006fb86 -> 0x18014d3db */
/* cut edge: 0x18006fbbc -> 0x18014d3db */
/* cut edge: 0x18006fbff -> 0x180070ad7 */
/* cut edge: 0x18006fc1e -> 0x18014d3db */
/* cut edge: 0x18006fc4b -> 0x18014d3db */
/* cut edge: 0x18006fc61 -> 0x18014d3db */
/* cut edge: 0x18006fccc -> 0x1800713d8 */
/* cut edge: 0x18006fcf3 -> 0x18007140b */
/* cut edge: 0x18006fd54 -> 0x18007145c */
/* cut edge: 0x18006fd64 -> 0x180071495 */
/* cut edge: 0x18006fe44 -> 0x18014d3db */
/* cut edge: 0x18006ff3e -> 0x18014d3db */
/* cut edge: 0x18006ff54 -> 0x18014d3db */
/* cut edge: 0x18006ff6a -> 0x18007013c */
/* cut edge: 0x18006ffc7 -> 0x180071c7c */
/* cut edge: 0x18006ffeb -> 0x180071caf */
/* cut edge: 0x18007004c -> 0x180071d00 */
/* cut edge: 0x18007005c -> 0x180071d3a */
/* cut edge: 0x1800700b5 -> 0x18007013c */
/* target 0x18007013c original byte 0x48 replaced by RET */
/* target 0x180070ad7 original byte 0x4c replaced by RET */
/* target 0x1800713d8 original byte 0x48 replaced by RET */
/* target 0x18007140b original byte 0x48 replaced by RET */
/* target 0x18007145c original byte 0x48 replaced by RET */
/* target 0x180071495 original byte 0x48 replaced by RET */
/* target 0x180071c7c original byte 0x48 replaced by RET */
/* target 0x180071caf original byte 0x48 replaced by RET */
/* target 0x180071d00 original byte 0x48 replaced by RET */
/* target 0x180071d3a original byte 0x48 replaced by RET */
/* target 0x180071e66 original byte 0x48 replaced by RET */
/* target 0x180071e99 original byte 0x48 replaced by RET */
/* target 0x180071eea original byte 0x48 replaced by RET */
/* target 0x180071f24 original byte 0x48 replaced by RET */
/* target 0x180072705 original byte 0x48 replaced by RET */
/* target 0x180072738 original byte 0x48 replaced by RET */
/* target 0x180072789 original byte 0x48 replaced by RET */
/* target 0x1800727c2 original byte 0x48 replaced by RET */
/* target 0x180072dbd original byte 0x48 replaced by RET */
/* target 0x180072df3 original byte 0x48 replaced by RET */
/* target 0x180072e44 original byte 0x48 replaced by RET */
/* target 0x180072e7d original byte 0x48 replaced by RET */
/* target 0x180125423 original byte 0x4c replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00080_18006f130(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  code *pcVar7;
  code *pcVar8;
  code *pcVar9;
  ulonglong uVar10;
  longlong lVar11;
  code *unaff_R12;
  code *in_stack_00000048;
  ulonglong in_stack_00000058;
  undefined8 in_stack_00000060;
  code *in_stack_00000070;
  code *in_stack_00000078;
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
  undefined8 in_stack_00000c80;
  
  (*unaff_R12)(in_stack_00000048,in_stack_00000090);
  (*in_stack_00000070)(in_stack_00000048);
  uVar4 = (*in_stack_00000078)(in_stack_00000048,in_stack_00000090);
  DAT_1804ae440 = (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
  (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
  uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804ae440,in_stack_000000b0);
  uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                    (in_stack_00000048,2,in_stack_000000a0,0,in_stack_00000060);
  pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x67fc);
  (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
  pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
  uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                    (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
  (**(code **)(*(longlong *)in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar4,in_stack_000000d8);
  pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)pcVar7 == '\0') {
    pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
    if ((char)pcVar7 == '\0') {
      pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
      lVar11 = DAT_1804b52e8;
      uVar4 = DAT_1804b5208;
      if ((char)pcVar7 == '\0') {
        if (DAT_1804ae448 == 0) {
          pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return (code *)0x0;
          }
          uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                            (in_stack_00000048,&stack0x0000031a,1);
          lVar2 = DAT_1804b4d38;
          lVar1 = DAT_1804b4d30;
          pcVar8 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar8;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar8;
          }
          pcVar9 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x000015c8,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar9;
          }
          uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                            (in_stack_00000048,in_stack_00000090);
          (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar6);
          uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000090,lVar11,uVar4,uVar5);
          DAT_1804ae448 =
               (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
          (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
        }
        uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae448,in_stack_000000b0);
        uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                          (in_stack_00000048,2,in_stack_000000a0,0);
        pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x216d);
        (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
        pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000058 % 0xb1cbf ^ in_stack_000000c0 ^
                           0xf95d8f510c2f5323);
        (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
        uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
        (**(code **)(*(longlong *)in_stack_00000048 + 0x1a0))
                  (in_stack_00000048,uVar4,in_stack_000000d8);
        pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar7 == '\0') {
          pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
          if ((char)pcVar7 == '\0') {
            pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
            lVar11 = DAT_1804b52e8;
            uVar4 = DAT_1804b5208;
            if ((char)pcVar7 == '\0') {
              if (DAT_1804ae450 == 0) {
                pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return (code *)0x0;
                }
                uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000318,1);
                lVar2 = DAT_1804b4d38;
                lVar1 = DAT_1804b4d30;
                pcVar8 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return pcVar8;
                }
                if (DAT_1804b4d38 == 0) {
                  return pcVar8;
                }
                pcVar9 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                           (in_stack_00000048,&stack0x000015b8,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar9;
                }
                uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000090);
                (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar6);
                uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000090,lVar11,uVar4,uVar5);
                DAT_1804ae450 =
                     (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
                (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
              }
              uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae450,in_stack_000000b0);
              uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4b0b);
              (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
              pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000058 % 0x81fb9 ^ in_stack_000000c0 ^
                                 0xdd0d3988eba0361f);
              (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
              uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
              uVar10 = (**(code **)(*(longlong *)in_stack_00000048 + 0x1a0))
                                 (in_stack_00000048,uVar4,in_stack_000000d8);
              pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))
                                         (in_stack_00000048);
              if ((char)pcVar7 == '\0') {
                pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
                if ((char)pcVar7 == '\0') {
                  pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
                  lVar11 = DAT_1804b52e8;
                  uVar4 = DAT_1804b5208;
                  if ((char)pcVar7 == '\0') {
                    if (DAT_1804ae458 == 0) {
                      pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                      if (DAT_1804b52e8 == 0) {
                        return pcVar7;
                      }
                      uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00000316,1);
                      lVar2 = DAT_1804b4d38;
                      lVar1 = DAT_1804b4d30;
                      pcVar8 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return pcVar8;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return pcVar8;
                      }
                      pcVar9 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                                 (in_stack_00000048,&stack0x000015a8,5);
                      if (DAT_1804b4d40 == 0) {
                        return pcVar9;
                      }
                      uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                        (in_stack_00000048,in_stack_00000090);
                      (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar6);
                      uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000090,lVar11,uVar4,uVar5);
                      DAT_1804ae458 =
                           (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))
                                     (in_stack_00000048,uVar4);
                      (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
                    }
                    uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ae458,in_stack_000000b0);
                    uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6fad)
                    ;
                    (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
                    pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)in_stack_00000058 % 0xe8c16 ^ in_stack_000000c0 ^
                                       0x8fbfdc8e57e2b048);
                    (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
                    uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
                    lVar11 = (**(code **)(*(longlong *)in_stack_00000048 + 0x1a0))
                                       (in_stack_00000048,uVar4,in_stack_000000d8);
                    pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))
                                               (in_stack_00000048);
                    if ((char)pcVar7 == '\0') {
                      if (in_stack_000000f8 == 0) {
                        return pcVar7;
                      }
                      uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x568))
                                        (in_stack_00000048,in_stack_00000c80,2);
                      pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))
                                                 (in_stack_00000048);
                      if ((char)pcVar7 == '\0') {
                        uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                          (in_stack_00000048,&stack0x00000314,1);
                        pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))
                                                   (in_stack_00000048);
                        if ((char)pcVar7 == '\0') {
                          if (DAT_1804b5370 == 0) {
                            return in_stack_00000048;
                          }
                          (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar4,DAT_1804b5370,uVar5);
                          pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                          if ((char)pcVar7 == '\0') {
                            pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))()
                            ;
                            if ((char)pcVar7 == '\0') {
                              pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))
                                                         ();
                              lVar1 = DAT_1804b52e8;
                              uVar4 = DAT_1804b5208;
                              if ((char)pcVar7 == '\0') {
                                if (DAT_1804ae460 == 0) {
                                  pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                                  if (DAT_1804b52e8 == 0) {
                                    return (code *)0x0;
                                  }
                                  uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x00000312,1);
                                  lVar3 = DAT_1804b4d38;
                                  lVar2 = DAT_1804b4d30;
                                  pcVar8 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                                  if (DAT_1804b4d30 == 0) {
                                    return pcVar8;
                                  }
                                  if (DAT_1804b4d38 == 0) {
                                    return pcVar8;
                                  }
                                  pcVar9 = (code *)(**(code **)(*(longlong *)in_stack_00000048 +
                                                               0x518))(in_stack_00000048,
                                                                       &stack0x00001598,5);
                                  if (DAT_1804b4d40 == 0) {
                                    return pcVar9;
                                  }
                                  uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,in_stack_00000090);
                                  (*pcVar8)(in_stack_00000048,lVar2,lVar3,pcVar9,uVar6);
                                  uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000090,lVar1,uVar4,
                                                    uVar5);
                                  DAT_1804ae460 =
                                       (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar4);
                                  (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))
                                            (in_stack_00000048,uVar4);
                                }
                                uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804ae460,in_stack_000000b0
                                                  );
                                uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                                uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x6db4);
                                (*pcVar7)(in_stack_00000048,uVar5,0,uVar6);
                                pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                                uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   (longlong)((uVar10 ^ in_stack_00000058) - lVar11)
                                                   % 0xeda8d ^ in_stack_000000c0 ^
                                                   0x4f408869dbb6b7cf);
                                (*pcVar7)(in_stack_00000048,uVar5,1,uVar6);
                                uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
                                (**(code **)(*(longlong *)in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar4,in_stack_000000d8);
                                pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720
                                                             ))(in_stack_00000048);
                                if ((char)pcVar7 == '\0') {
                                  pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 +
                                                               0x720))();
                                  if ((char)pcVar7 == '\0') {
                                    pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 +
                                                                 0x720))();
                                    lVar11 = DAT_1804b52e8;
                                    uVar4 = DAT_1804b5208;
                                    if ((char)pcVar7 == '\0') {
                                      pcVar7 = (code *)0x2295afb87f30d44d;
                                      if (DAT_1804ae468 == 0) {
                                        pcVar7 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                                        if (DAT_1804b52e8 == 0) {
                                          return (code *)0x0;
                                        }
                                        uVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518)
                                                )(in_stack_00000048,&stack0x00000310,1);
                                        lVar2 = DAT_1804b4d38;
                                        lVar1 = DAT_1804b4d30;
                                        pcVar8 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                                        if (DAT_1804b4d30 == 0) {
                                          return pcVar8;
                                        }
                                        if (DAT_1804b4d38 == 0) {
                                          return pcVar8;
                                        }
                                        pcVar9 = (code *)(**(code **)(*(longlong *)in_stack_00000048
                                                                     + 0x518))
                                                                   (in_stack_00000048,
                                                                    &stack0x00001588,5);
                                        if (DAT_1804b4d40 == 0) {
                                          return pcVar9;
                                        }
                                        uVar6 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110)
                                                )(in_stack_00000048,in_stack_00000090);
                                        (*pcVar8)(in_stack_00000048,lVar1,lVar2,pcVar9,uVar6);
                                        uVar4 = (*pcVar7)(in_stack_00000048,in_stack_00000090,lVar11
                                                          ,uVar4,uVar5);
                                        DAT_1804ae468 =
                                             (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar4);
                                        pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048
                                                                     + 0xb8))
                                                                   (in_stack_00000048,uVar4);
                                      }
                                      return pcVar7;
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
  return pcVar7;
}

