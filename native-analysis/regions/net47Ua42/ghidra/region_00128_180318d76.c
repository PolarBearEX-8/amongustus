/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=128 entry=180318d76 blocks=53 maxInstructions=50000 */

/* cut edge: 0x180318d76 -> 0x180333186 */
/* cut edge: 0x180318ddc -> 0x18031aed2 */
/* cut edge: 0x180318e05 -> 0x18031af0a */
/* cut edge: 0x180318e64 -> 0x18031af5b */
/* cut edge: 0x180318e74 -> 0x18031af94 */
/* cut edge: 0x180318f54 -> 0x180333186 */
/* cut edge: 0x180319049 -> 0x180333186 */
/* cut edge: 0x18031905f -> 0x180333186 */
/* cut edge: 0x1803190c5 -> 0x18031afcb */
/* cut edge: 0x1803190f6 -> 0x18031b006 */
/* cut edge: 0x180319152 -> 0x18031b057 */
/* cut edge: 0x180319162 -> 0x18031b090 */
/* cut edge: 0x180319247 -> 0x180333186 */
/* cut edge: 0x18031933c -> 0x180333186 */
/* cut edge: 0x180319352 -> 0x180333186 */
/* cut edge: 0x1803193b8 -> 0x18031b0c7 */
/* cut edge: 0x1803193e4 -> 0x18031b0ff */
/* cut edge: 0x180319448 -> 0x18031b159 */
/* cut edge: 0x18031945b -> 0x18031b193 */
/* cut edge: 0x180319542 -> 0x180333186 */
/* cut edge: 0x180319661 -> 0x18031a231 */
/* cut edge: 0x18031967a -> 0x18031ad4f */
/* cut edge: 0x180319699 -> 0x18031ada0 */
/* cut edge: 0x1803196a9 -> 0x180333186 */
/* cut edge: 0x1803196d8 -> 0x18031ad3d */
/* cut edge: 0x1803196df -> 0x18029ad3c */
/* cut edge: 0x1803196f3 -> 0x18031a23b */
/* cut edge: 0x18031970a -> 0x180333186 */
/* cut edge: 0x180319712 -> 0x180333186 */
/* cut edge: 0x1803198c1 -> 0x180333186 */
/* cut edge: 0x1803199b8 -> 0x180333186 */
/* cut edge: 0x1803199ce -> 0x180333186 */
/* cut edge: 0x180319b82 -> 0x180333186 */
/* cut edge: 0x180319c7c -> 0x180333186 */
/* cut edge: 0x180319c92 -> 0x180333186 */
/* cut edge: 0x180319ca8 -> 0x180319e40 */
/* cut edge: 0x180319cfa -> 0x180319e40 */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x180319e40 original byte 0x48 replaced by RET */
/* target 0x18031a231 original byte 0x48 replaced by RET */
/* target 0x18031a23b original byte 0x84 replaced by RET */
/* target 0x18031ad3d original byte 0x48 replaced by RET */
/* target 0x18031ad4f original byte 0x48 replaced by RET */
/* target 0x18031ada0 original byte 0x48 replaced by RET */
/* target 0x18031aed2 original byte 0x48 replaced by RET */
/* target 0x18031af0a original byte 0x48 replaced by RET */
/* target 0x18031af5b original byte 0x48 replaced by RET */
/* target 0x18031af94 original byte 0x48 replaced by RET */
/* target 0x18031afcb original byte 0x48 replaced by RET */
/* target 0x18031b006 original byte 0x48 replaced by RET */
/* target 0x18031b057 original byte 0x48 replaced by RET */
/* target 0x18031b090 original byte 0x48 replaced by RET */
/* target 0x18031b0c7 original byte 0x48 replaced by RET */
/* target 0x18031b0ff original byte 0x48 replaced by RET */
/* target 0x18031b159 original byte 0x48 replaced by RET */
/* target 0x18031b193 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


code * region_00128_180318d76(void)

{
  code *pcVar1;
  code *pcVar2;
  undefined8 uVar3;
  code *pcVar4;
  code *pcVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  ulonglong uVar8;
  longlong lVar9;
  longlong lVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  ulonglong unaff_R12;
  ulonglong uVar15;
  ulonglong uVar16;
  code *in_stack_00000040;
  ulonglong in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  longlong in_stack_00000110;
  undefined2 uStack000000000000018e;
  undefined2 uStack0000000000000190;
  undefined2 uStack0000000000000192;
  undefined2 uStack0000000000000194;
  undefined2 uStack0000000000000196;
  undefined2 in_stack_00000198;
  
  pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
  lVar9 = DAT_1804b5a08;
  uVar7 = DAT_1804b59f0;
  if ((char)pcVar2 == '\0') {
    uVar16 = (longlong)unaff_R12 % 0x12882;
    if (DAT_1804b1ec8 == 0) {
      pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return (code *)0x0;
      }
      in_stack_00000198 = 0x7a;
      uVar3 = (**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                        (in_stack_00000040,&stack0x00000198,1);
      lVar10 = DAT_1804b4d38;
      pcVar1 = DAT_1804b4d30;
      pcVar4 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
      if (DAT_1804b4d30 == (code *)0x0) {
        return pcVar4;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar4;
      }
      pcVar5 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                                 (in_stack_00000040,&stack0x000007d8,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar5;
      }
      uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                        (in_stack_00000040,in_stack_00000080);
      (*pcVar4)(in_stack_00000040,pcVar1,lVar10,pcVar5,uVar6);
      uVar7 = (*pcVar2)(in_stack_00000040,in_stack_00000080,lVar9,uVar7,uVar3);
      DAT_1804b1ec8 = (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
      (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
      unaff_R12 = in_stack_00000070;
    }
    uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                      (in_stack_00000040,DAT_1804b1ec8,in_stack_000000a0);
    uVar3 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                      (in_stack_00000040,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
    uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4352);
    (*pcVar2)(in_stack_00000040,uVar3,0,uVar6);
    pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
    uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                       uVar16 ^ in_stack_000000b0 ^ 0xd8d564a7d6ea841c);
    (*pcVar2)(in_stack_00000040,uVar3,1,uVar6);
    uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                      (in_stack_00000040,uVar7,in_stack_000000a8,uVar3);
    (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0)
    ;
    pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))(in_stack_00000040);
    if ((char)pcVar2 == '\0') {
      pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
      if ((char)pcVar2 == '\0') {
        pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
        lVar9 = DAT_1804b5a08;
        uVar7 = DAT_1804b59f0;
        if ((char)pcVar2 == '\0') {
          uVar16 = unaff_R12;
          if (DAT_1804b1ed0 == 0) {
            pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
            if (DAT_1804b5a08 == 0) {
              return (code *)0x0;
            }
            uStack0000000000000196 = 0x7a;
            uVar3 = (**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                              (in_stack_00000040,(longlong)&stack0x00000190 + 6,1);
            lVar10 = DAT_1804b4d38;
            pcVar1 = DAT_1804b4d30;
            pcVar4 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
            if (DAT_1804b4d30 == (code *)0x0) {
              return pcVar4;
            }
            if (DAT_1804b4d38 == 0) {
              return pcVar4;
            }
            pcVar5 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                                       (in_stack_00000040,&stack0x000007c8,5);
            if (DAT_1804b4d40 == 0) {
              return pcVar5;
            }
            uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                              (in_stack_00000040,in_stack_00000080);
            (*pcVar4)(in_stack_00000040,pcVar1,lVar10,pcVar5,uVar6);
            uVar7 = (*pcVar2)(in_stack_00000040,in_stack_00000080,lVar9,uVar7,uVar3);
            DAT_1804b1ed0 =
                 (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
            (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
            uVar16 = in_stack_00000070;
          }
          uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                            (in_stack_00000040,DAT_1804b1ed0,in_stack_000000a0);
          uVar3 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                            (in_stack_00000040,2,in_stack_00000090,0);
          pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
          uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7ae7);
          (*pcVar2)(in_stack_00000040,uVar3,0,uVar6);
          pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
          uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                             (longlong)unaff_R12 % 0xbb9ce ^ in_stack_000000b0 ^ 0xa1ff9bed99452058)
          ;
          (*pcVar2)(in_stack_00000040,uVar3,1,uVar6);
          uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                            (in_stack_00000040,uVar7,in_stack_000000a8,uVar3);
          uVar8 = (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))
                            (in_stack_00000040,uVar7,in_stack_000000d0);
          pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))(in_stack_00000040);
          if ((char)pcVar2 == '\0') {
            pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
            if ((char)pcVar2 == '\0') {
              pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
              lVar9 = DAT_1804b5a08;
              uVar7 = DAT_1804b59f0;
              if ((char)pcVar2 == '\0') {
                uVar15 = uVar16;
                if (DAT_1804b1ed8 == 0) {
                  pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
                  if (DAT_1804b5a08 == 0) {
                    return (code *)0x0;
                  }
                  uStack0000000000000194 = 0x7a;
                  uVar3 = (**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                                    (in_stack_00000040,(longlong)&stack0x00000190 + 4,1);
                  lVar10 = DAT_1804b4d38;
                  pcVar1 = DAT_1804b4d30;
                  pcVar4 = *(code **)(*(longlong *)in_stack_00000040 + 0x390);
                  if (DAT_1804b4d30 == (code *)0x0) {
                    return (code *)0x0;
                  }
                  if (DAT_1804b4d38 == 0) {
                    return DAT_1804b4d30;
                  }
                  pcVar5 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x518))
                                             (in_stack_00000040,&stack0x000007b8,5);
                  if (DAT_1804b4d40 == 0) {
                    return pcVar5;
                  }
                  uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                    (in_stack_00000040,in_stack_00000080);
                  (*pcVar4)(in_stack_00000040,pcVar1,lVar10,pcVar5,uVar6);
                  uVar7 = (*pcVar2)(in_stack_00000040,in_stack_00000080,lVar9,uVar7,uVar3);
                  DAT_1804b1ed8 =
                       (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7)
                  ;
                  (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
                  uVar15 = in_stack_00000070;
                }
                uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                  (in_stack_00000040,DAT_1804b1ed8,in_stack_000000a0);
                uVar3 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                                  (in_stack_00000040,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5161);
                (*pcVar2)(in_stack_00000040,uVar3,0,uVar6);
                pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   (longlong)uVar16 % 0xe4c31 ^ in_stack_000000b0 ^
                                   0xfc2385410883b36e);
                (*pcVar2)(in_stack_00000040,uVar3,1,uVar6);
                uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                  (in_stack_00000040,uVar7,in_stack_000000a8,uVar3);
                lVar9 = (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))
                                  (in_stack_00000040,uVar7,in_stack_000000d0);
                pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))
                                           (in_stack_00000040);
                if ((char)pcVar2 == '\0') {
                  lVar9 = lVar9 + (uVar8 ^ uVar15);
                  if ((longlong)in_stack_000000b0 < 0) {
                    return (code *)0x0;
                  }
                  if (in_stack_00000110 == 0) {
                    return (code *)0x0;
                  }
                  if (DAT_1804b5290 == 0) {
                    return in_stack_00000040;
                  }
                  if (DAT_1804b5400 == 0) {
                    return in_stack_00000040;
                  }
                  lVar10 = (**(code **)(*(longlong *)in_stack_00000040 + 0x420))
                                     (in_stack_00000040,DAT_1804b5290);
                  pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))
                                             (in_stack_00000040);
                  if ((char)pcVar2 == '\0') {
                    if (in_stack_00000110 != 0) {
                      return pcVar2;
                    }
                    pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
                    if (lVar10 < 1) {
                      return pcVar2;
                    }
                    if ((char)pcVar2 == '\0') {
                      pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
                      if ((char)pcVar2 == '\0') {
                        if (DAT_1804b1ee0 == 0) {
                          lVar10 = *(longlong *)in_stack_00000040;
                          pcVar2 = *(code **)(lVar10 + 0x390);
                          uVar3 = FUN_1803335f0(in_stack_00000040);
                          uVar7 = DAT_1804b59f0;
                          uStack0000000000000192 = 0x7a;
                          uVar6 = (**(code **)(lVar10 + 0x518))
                                            (in_stack_00000040,(longlong)&stack0x00000190 + 2,1);
                          lVar10 = *(longlong *)in_stack_00000040;
                          pcVar4 = *(code **)(lVar10 + 0x390);
                          uVar11 = FUN_1800024f0(in_stack_00000040);
                          uVar12 = FUN_180002550(in_stack_00000040);
                          uVar13 = (**(code **)(lVar10 + 0x518))
                                             (in_stack_00000040,&stack0x000007a8,5);
                          pcVar1 = *(code **)(*(longlong *)in_stack_00000040 + 0x110);
                          uVar14 = FUN_1800025a0(in_stack_00000040);
                          uVar14 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar14);
                          (*pcVar4)(in_stack_00000040,uVar11,uVar12,uVar13,uVar14);
                          uVar7 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar3,uVar7,uVar6);
                          DAT_1804b1ee0 =
                               (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))
                                         (in_stack_00000040,uVar7);
                          (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))
                                    (in_stack_00000040,uVar7);
                        }
                        uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                          (in_stack_00000040,DAT_1804b1ee0,in_stack_000000a0);
                        uVar3 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                                          (in_stack_00000040,2,in_stack_00000090,0);
                        pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                        uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,
                                           0x4fd5);
                        (*pcVar2)(in_stack_00000040,uVar3,0,uVar6);
                        pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                        uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                           lVar9 % 0xdb939 ^ in_stack_000000b0 ^ 0x56f59f38b9b55e82)
                        ;
                        (*pcVar2)(in_stack_00000040,uVar3,1,uVar6);
                        uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                          (in_stack_00000040,uVar7,in_stack_000000a8,uVar3);
                        (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))
                                  (in_stack_00000040,uVar7,in_stack_000000d0);
                        pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))
                                                   (in_stack_00000040);
                        if ((char)pcVar2 == '\0') {
                          pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))();
                          if ((char)pcVar2 == '\0') {
                            pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))()
                            ;
                            if ((char)pcVar2 == '\0') {
                              if (DAT_1804b1ee8 == 0) {
                                lVar10 = *(longlong *)in_stack_00000040;
                                pcVar2 = *(code **)(lVar10 + 0x390);
                                uVar3 = FUN_1803335f0(in_stack_00000040);
                                uVar7 = DAT_1804b59f0;
                                uStack0000000000000190 = 0x7a;
                                uVar6 = (**(code **)(lVar10 + 0x518))
                                                  (in_stack_00000040,&stack0x00000190,1);
                                lVar10 = *(longlong *)in_stack_00000040;
                                pcVar4 = *(code **)(lVar10 + 0x390);
                                uVar11 = FUN_1800024f0(in_stack_00000040);
                                uVar12 = FUN_180002550(in_stack_00000040);
                                uVar13 = (**(code **)(lVar10 + 0x518))
                                                   (in_stack_00000040,&stack0x00000798,5);
                                pcVar1 = *(code **)(*(longlong *)in_stack_00000040 + 0x110);
                                uVar14 = FUN_1800025a0(in_stack_00000040);
                                uVar14 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar14);
                                (*pcVar4)(in_stack_00000040,uVar11,uVar12,uVar13,uVar14);
                                uVar7 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar3,uVar7,
                                                  uVar6);
                                DAT_1804b1ee8 =
                                     (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))
                                               (in_stack_00000040,uVar7);
                                (**(code **)(*(longlong *)in_stack_00000040 + 0xb8))
                                          (in_stack_00000040,uVar7);
                              }
                              uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                                (in_stack_00000040,DAT_1804b1ee8,in_stack_000000a0);
                              uVar3 = (**(code **)(*(longlong *)in_stack_00000040 + 0x560))
                                                (in_stack_00000040,2,in_stack_00000090,0);
                              pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                              uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                                (in_stack_00000040,in_stack_000000b8,
                                                 in_stack_000000c0,0x848);
                              (*pcVar2)(in_stack_00000040,uVar3,0,uVar6);
                              pcVar2 = *(code **)(*(longlong *)in_stack_00000040 + 0x570);
                              uVar6 = (**(code **)(*(longlong *)in_stack_00000040 + 0x390))
                                                (in_stack_00000040,in_stack_00000088,
                                                 in_stack_00000098,
                                                 lVar9 % 0x969b3 ^ in_stack_000000b0 ^
                                                 0x11cbf57a8aef1c21);
                              (*pcVar2)(in_stack_00000040,uVar3,1,uVar6);
                              uVar7 = (**(code **)(*(longlong *)in_stack_00000040 + 0x110))
                                                (in_stack_00000040,uVar7,in_stack_000000a8,uVar3);
                              (**(code **)(*(longlong *)in_stack_00000040 + 0x1a0))
                                        (in_stack_00000040,uVar7,in_stack_000000d0);
                              pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720))
                                                         (in_stack_00000040);
                              if ((char)pcVar2 == '\0') {
                                pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 + 0x720
                                                             ))();
                                if ((char)pcVar2 == '\0') {
                                  pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 +
                                                               0x720))();
                                  if ((char)pcVar2 == '\0') {
                                    pcVar2 = (code *)0x1ac4065062f250a0;
                                    if (DAT_1804b1ef0 == 0) {
                                      lVar9 = *(longlong *)in_stack_00000040;
                                      pcVar2 = *(code **)(lVar9 + 0x390);
                                      uVar3 = FUN_1803335f0(in_stack_00000040);
                                      uVar7 = DAT_1804b59f0;
                                      uStack000000000000018e = 0x7a;
                                      uVar6 = (**(code **)(lVar9 + 0x518))
                                                        (in_stack_00000040,&stack0x0000018e,1);
                                      lVar9 = *(longlong *)in_stack_00000040;
                                      pcVar4 = *(code **)(lVar9 + 0x390);
                                      uVar11 = FUN_1800024f0(in_stack_00000040);
                                      uVar12 = FUN_180002550(in_stack_00000040);
                                      uVar13 = (**(code **)(lVar9 + 0x518))
                                                         (in_stack_00000040,&stack0x00000788,5);
                                      pcVar1 = *(code **)(*(longlong *)in_stack_00000040 + 0x110);
                                      uVar14 = FUN_1800025a0(in_stack_00000040);
                                      uVar14 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar14)
                                      ;
                                      (*pcVar4)(in_stack_00000040,uVar11,uVar12,uVar13,uVar14);
                                      uVar7 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar3,
                                                        uVar7,uVar6);
                                      DAT_1804b1ef0 =
                                           (**(code **)(*(longlong *)in_stack_00000040 + 0xa8))
                                                     (in_stack_00000040,uVar7);
                                      pcVar2 = (code *)(**(code **)(*(longlong *)in_stack_00000040 +
                                                                   0xb8))(in_stack_00000040,uVar7);
                                    }
                                    return pcVar2;
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
  return pcVar2;
}

