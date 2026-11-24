/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=20 entry=1803ef565 blocks=57 maxInstructions=50000 */

/* cut edge: 0x1803ef565 -> 0x180473ae8 */
/* cut edge: 0x1803ef57b -> 0x180473ae8 */
/* cut edge: 0x1803ef5eb -> 0x1804013ec */
/* cut edge: 0x1803ef60f -> 0x18040141f */
/* cut edge: 0x1803ef670 -> 0x180401470 */
/* cut edge: 0x1803ef680 -> 0x1804014a9 */
/* cut edge: 0x1803ef759 -> 0x180473ae8 */
/* cut edge: 0x1803ef84e -> 0x180473ae8 */
/* cut edge: 0x1803ef864 -> 0x180473ae8 */
/* cut edge: 0x1803ef8d2 -> 0x18040205e */
/* cut edge: 0x1803ef8f6 -> 0x180402091 */
/* cut edge: 0x1803ef954 -> 0x1804020df */
/* cut edge: 0x1803ef96c -> 0x180402118 */
/* cut edge: 0x1803efa46 -> 0x180473ae8 */
/* cut edge: 0x1803efb3d -> 0x1803dd671 */
/* cut edge: 0x1803efb51 -> 0x180473ae8 */
/* cut edge: 0x1803efb67 -> 0x180473ae8 */
/* cut edge: 0x1803efbd2 -> 0x1803ffde4 */
/* cut edge: 0x1803efbf6 -> 0x1803ffe17 */
/* cut edge: 0x1803efc57 -> 0x1803ffe68 */
/* cut edge: 0x1803efc67 -> 0x1803ffea1 */
/* cut edge: 0x1803efd40 -> 0x180473ae8 */
/* cut edge: 0x1803efe35 -> 0x180473ae8 */
/* cut edge: 0x1803efe4b -> 0x180473ae8 */
/* cut edge: 0x1803efebf -> 0x180400a58 */
/* cut edge: 0x1803efee3 -> 0x180400a8b */
/* cut edge: 0x1803eff44 -> 0x180400ad9 */
/* cut edge: 0x1803eff5c -> 0x180400b13 */
/* cut edge: 0x1803f0039 -> 0x180473ae8 */
/* cut edge: 0x1803f0180 -> 0x180473ae8 */
/* cut edge: 0x1803f0196 -> 0x180473ae8 */
/* cut edge: 0x1803f01fe -> 0x1804015d7 */
/* cut edge: 0x1803f0222 -> 0x18040160a */
/* cut edge: 0x1803f0283 -> 0x18040165b */
/* cut edge: 0x1803f0293 -> 0x180401694 */
/* cut edge: 0x1803f036c -> 0x180473ae8 */
/* cut edge: 0x1803f0461 -> 0x180473ae8 */
/* cut edge: 0x1803f0477 -> 0x180473ae8 */
/* cut edge: 0x1803f048d -> 0x1803f0664 */
/* cut edge: 0x1803f04ea -> 0x180402243 */
/* cut edge: 0x1803f050e -> 0x180402276 */
/* cut edge: 0x1803f050e -> 0x1803f056f */
/* target 0x1803dd671 original byte 0x48 replaced by RET */
/* target 0x1803f056f original byte 0x48 replaced by RET */
/* target 0x1803f0664 original byte 0x48 replaced by RET */
/* target 0x1803ffde4 original byte 0x48 replaced by RET */
/* target 0x1803ffe17 original byte 0x48 replaced by RET */
/* target 0x1803ffe68 original byte 0x48 replaced by RET */
/* target 0x1803ffea1 original byte 0x48 replaced by RET */
/* target 0x180400a58 original byte 0x48 replaced by RET */
/* target 0x180400a8b original byte 0x48 replaced by RET */
/* target 0x180400ad9 original byte 0x48 replaced by RET */
/* target 0x180400b13 original byte 0x48 replaced by RET */
/* target 0x1804013ec original byte 0x48 replaced by RET */
/* target 0x18040141f original byte 0x48 replaced by RET */
/* target 0x180401470 original byte 0x48 replaced by RET */
/* target 0x1804014a9 original byte 0x48 replaced by RET */
/* target 0x1804015d7 original byte 0x48 replaced by RET */
/* target 0x18040160a original byte 0x48 replaced by RET */
/* target 0x18040165b original byte 0x48 replaced by RET */
/* target 0x180401694 original byte 0x48 replaced by RET */
/* target 0x18040205e original byte 0x48 replaced by RET */
/* target 0x180402091 original byte 0x48 replaced by RET */
/* target 0x1804020df original byte 0x48 replaced by RET */
/* target 0x180402118 original byte 0x48 replaced by RET */
/* target 0x180402243 original byte 0x48 replaced by RET */
/* target 0x180402276 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


code * region_00020_1803ef565(void)

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
  ulonglong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  longlong in_stack_00000118;
  
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if (((char)pcVar4 == '\0') &&
     (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar11 = DAT_1804b5a08,
     uVar9 = DAT_1804b59f0, (char)pcVar4 == '\0')) {
    if (DAT_1804b3eb0 == 0) {
      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return (code *)0x0;
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000522,1);
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
                                 (in_stack_00000048,&stack0x00001d50,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar7;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
      (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
      uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar11,uVar9,uVar5);
      DAT_1804b3eb0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b3eb0,in_stack_000000a0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x3694);
    (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0x583d9 ^ in_stack_000000c8 ^
                       0xd2838ce2698ec6a0);
    (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((((char)pcVar4 == '\0') &&
        (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar4 == '\0')) &&
       (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar11 = DAT_1804b5a08,
       uVar9 = DAT_1804b59f0, (char)pcVar4 == '\0')) {
      if (DAT_1804b3eb8 == 0) {
        pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return (code *)0x0;
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000520,1);
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
                                   (in_stack_00000048,&stack0x00001d40,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar7;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
        uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar11,uVar9,uVar5);
        DAT_1804b3eb8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b3eb8,in_stack_000000a0);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x927);
      (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)in_stack_00000068 % 0x68a5c ^ in_stack_000000c8 ^
                         0xe86e068911cdecfe);
      (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
      lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar4 == '\0') {
        lVar11 = lVar11 + (uVar10 ^ in_stack_00000068);
        if (in_stack_00000118 == 0) {
          return pcVar4;
        }
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if (((char)pcVar4 == '\0') &&
           (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar1 = DAT_1804b5a08,
           uVar9 = DAT_1804b59f0, (char)pcVar4 == '\0')) {
          if (DAT_1804b3ec0 == 0) {
            pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b5a08 == 0) {
              return (code *)0x0;
            }
            uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000051e,1);
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
                                       (in_stack_00000048,&stack0x00001d30,5);
            if (DAT_1804b4d40 == 0) {
              return pcVar7;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
            (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
            uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar9,uVar5);
            DAT_1804b3ec0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b3ec0,in_stack_000000a0);
          uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1317);
          (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                             lVar11 % 0x67b5f ^ in_stack_000000c8 ^ 0xb090060686ab57d6);
          (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
          (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((((char)pcVar4 == '\0') &&
              (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar4 == '\0'))
             && (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar1 = DAT_1804b5a08
                , uVar9 = DAT_1804b59f0, (char)pcVar4 == '\0')) {
            if (DAT_1804b3ec8 == 0) {
              pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return (code *)0x0;
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x0000051c,1);
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
                                         (in_stack_00000048,&stack0x00001d20,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar7;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
              uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar9,uVar5);
              DAT_1804b3ec8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b3ec8,in_stack_000000a0);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1641);
            (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               lVar11 % 0x315c4 ^ in_stack_000000c8 ^ 0x8741a5632e2a9c3a);
            (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((((char)pcVar4 == '\0') &&
                (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar4 == '\0')
                ) && (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(),
                     lVar1 = DAT_1804b5a08, uVar9 = DAT_1804b59f0, (char)pcVar4 == '\0')) {
              if (DAT_1804b3ed0 == 0) {
                pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return (code *)0x0;
                }
                uVar5 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x0000051a,1);
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
                                           (in_stack_00000048,&stack0x00001d10,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar7;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000088);
                (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
                uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar9,uVar5);
                DAT_1804b3ed0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b3ed0,in_stack_000000a0);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5f04);
              (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 lVar11 % 0x597d7 ^ in_stack_000000c8 ^ 0x99df35cfb9ca0248);
              (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((((char)pcVar4 == '\0') &&
                  (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(),
                  (char)pcVar4 == '\0')) &&
                 (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar4 == '\0'
                 )) {
                if (DAT_1804b3ed8 != 0) {
                  return (code *)0xaad1093253186106;
                }
                if (DAT_1804b5a08 != 0) {
                  (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000518,1);
                  if (DAT_1804b4d30 != 0) {
                    return *(code **)(*in_stack_00000048 + 0x390);
                  }
                  return *(code **)(*in_stack_00000048 + 0x390);
                }
                return (code *)0x0;
              }
            }
          }
        }
      }
    }
  }
  return pcVar4;
}

