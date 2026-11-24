/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=36 entry=18035785b blocks=55 maxInstructions=50000 */

/* cut edge: 0x18035785b -> 0x1803351d6 */
/* cut edge: 0x180357881 -> 0x1803598f2 */
/* cut edge: 0x1803578a8 -> 0x18035992d */
/* cut edge: 0x180357902 -> 0x18035997e */
/* cut edge: 0x180357925 -> 0x1803599b8 */
/* cut edge: 0x180357a10 -> 0x1803351d6 */
/* cut edge: 0x180357b10 -> 0x1803351d6 */
/* cut edge: 0x180357b28 -> 0x18035a49f */
/* cut edge: 0x180357b57 -> 0x18035a4d5 */
/* cut edge: 0x180357bb9 -> 0x18035a526 */
/* cut edge: 0x180357bc9 -> 0x18035a560 */
/* cut edge: 0x180357cb8 -> 0x1803351d6 */
/* cut edge: 0x180357da9 -> 0x1803351d6 */
/* cut edge: 0x180357dcf -> 0x180359120 */
/* cut edge: 0x180357dfe -> 0x180359156 */
/* cut edge: 0x180357e60 -> 0x1803591a7 */
/* cut edge: 0x180357e70 -> 0x1803591e1 */
/* cut edge: 0x180357f60 -> 0x1803351d6 */
/* cut edge: 0x180358077 -> 0x1803351d6 */
/* cut edge: 0x18035808f -> 0x180359fcb */
/* cut edge: 0x1803580b6 -> 0x18035a001 */
/* cut edge: 0x180358118 -> 0x18035a052 */
/* cut edge: 0x180358128 -> 0x18035a08b */
/* cut edge: 0x180358214 -> 0x1803351d6 */
/* cut edge: 0x180358302 -> 0x1803351d6 */
/* cut edge: 0x180358328 -> 0x18035a0c1 */
/* cut edge: 0x180358357 -> 0x18035a0f7 */
/* cut edge: 0x1803583b9 -> 0x18035a148 */
/* cut edge: 0x1803583c9 -> 0x18035a181 */
/* cut edge: 0x1803584b5 -> 0x1803351d6 */
/* cut edge: 0x1803585a2 -> 0x180358860 */
/* cut edge: 0x1803585c0 -> 0x18035885e */
/* cut edge: 0x1803585cd -> 0x1803351d6 */
/* cut edge: 0x1803585e5 -> 0x18035a87f */
/* cut edge: 0x18035860c -> 0x18035a8b5 */
/* cut edge: 0x18035866e -> 0x18035a906 */
/* cut edge: 0x18035867e -> 0x18035a93f */
/* cut edge: 0x18035876a -> 0x1803351d6 */
/* cut edge: 0x18035876a -> 0x180358857 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180358857 original byte 0x8b replaced by RET */
/* target 0x18035885e original byte 0x21 replaced by RET */
/* target 0x180358860 original byte 0x4c replaced by RET */
/* target 0x180359120 original byte 0x48 replaced by RET */
/* target 0x180359156 original byte 0x48 replaced by RET */
/* target 0x1803591a7 original byte 0x48 replaced by RET */
/* target 0x1803591e1 original byte 0x48 replaced by RET */
/* target 0x1803598f2 original byte 0x48 replaced by RET */
/* target 0x18035992d original byte 0x48 replaced by RET */
/* target 0x18035997e original byte 0x48 replaced by RET */
/* target 0x1803599b8 original byte 0x48 replaced by RET */
/* target 0x180359fcb original byte 0x48 replaced by RET */
/* target 0x18035a001 original byte 0x48 replaced by RET */
/* target 0x18035a052 original byte 0x48 replaced by RET */
/* target 0x18035a08b original byte 0x4c replaced by RET */
/* target 0x18035a0c1 original byte 0x48 replaced by RET */
/* target 0x18035a0f7 original byte 0x48 replaced by RET */
/* target 0x18035a148 original byte 0x48 replaced by RET */
/* target 0x18035a181 original byte 0x48 replaced by RET */
/* target 0x18035a49f original byte 0x48 replaced by RET */
/* target 0x18035a4d5 original byte 0x48 replaced by RET */
/* target 0x18035a526 original byte 0x48 replaced by RET */
/* target 0x18035a560 original byte 0x48 replaced by RET */
/* target 0x18035a87f original byte 0x48 replaced by RET */
/* target 0x18035a8b5 original byte 0x48 replaced by RET */
/* target 0x18035a906 original byte 0x48 replaced by RET */
/* target 0x18035a93f original byte 0x4c replaced by RET */


code * region_00036_18035785b(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  uint uVar4;
  code *pcVar5;
  undefined8 uVar6;
  code *pcVar7;
  code *pcVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  code *pcVar11;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  uint uStack00000000000000c0;
  longlong in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_00000110;
  
  pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  lVar3 = DAT_1804b59f8;
  uVar10 = DAT_1804b59f0;
  if ((char)pcVar5 == '\0') {
    if (DAT_1804b23e0 == 0) {
      pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b59f8 == 0) {
        return (code *)0x0;
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000006f6,1);
      lVar2 = DAT_1804b4d38;
      lVar1 = DAT_1804b4d30;
      pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar7;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar7;
      }
      pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00001b28,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar8;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
      (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
      uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar6);
      DAT_1804b23e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
    }
    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b23e0,in_stack_00000078);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
    (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
    pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
    (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
    (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
    pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)pcVar5 == '\0') {
      pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
      lVar3 = DAT_1804b59f8;
      uVar10 = DAT_1804b59f0;
      if ((char)pcVar5 == '\0') {
        if (DAT_1804b23f0 == 0) {
          pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b59f8 == 0) {
            return pcVar5;
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000006f2,1);
          lVar2 = DAT_1804b4d38;
          lVar1 = DAT_1804b4d30;
          pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar7;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar7;
          }
          pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00001b08,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar8;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
          (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
          uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar6);
          DAT_1804b23f0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
        }
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b23f0,in_stack_00000078);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
        (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
        pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
        (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar10,in_stack_00000070);
        pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar5 == '\0') {
          if (in_stack_000000c8 == 0) {
            return _uStack00000000000000c0;
          }
          pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          lVar3 = DAT_1804b59f8;
          uVar10 = DAT_1804b59f0;
          if ((uStack00000000000000c0 & uVar4) == 0) {
            if ((char)pcVar5 == '\0') {
              if (DAT_1804b2408 == 0) {
                pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b59f8 == 0) {
                  return pcVar5;
                }
                pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                           (in_stack_00000048,&stack0x000006ec,1);
                lVar2 = DAT_1804b4d38;
                lVar1 = DAT_1804b4d30;
                pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return pcVar8;
                }
                if (DAT_1804b4d38 == 0) {
                  return pcVar8;
                }
                pcVar11 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00001ad8,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar11;
                }
                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000058);
                (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar11,uVar6);
                uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,pcVar8);
                DAT_1804b2408 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
              }
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b2408,in_stack_00000078);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
              (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
              pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000a8);
              (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
              (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
              pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)pcVar5 == '\0') {
                return pcVar5;
              }
            }
          }
          else if ((char)pcVar5 == '\0') {
            if (DAT_1804b23f8 == 0) {
              pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59f8 == 0) {
                return pcVar5;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x000006f0,1);
              lVar2 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar7;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar7;
              }
              pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x00001af8,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar8;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058)
              ;
              (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
              uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar6);
              DAT_1804b23f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b23f8,in_stack_00000078);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
            (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_00000110);
            (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
            (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
            pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar5 == '\0') {
              pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
              lVar3 = DAT_1804b59f8;
              uVar10 = DAT_1804b59f0;
              if ((char)pcVar5 == '\0') {
                if (DAT_1804b2400 == 0) {
                  pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
                  if (DAT_1804b59f8 == 0) {
                    return pcVar5;
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                    (in_stack_00000048,&stack0x000006ee,1);
                  lVar2 = DAT_1804b4d38;
                  lVar1 = DAT_1804b4d30;
                  pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
                  if (DAT_1804b4d30 == 0) {
                    return pcVar7;
                  }
                  if (DAT_1804b4d38 == 0) {
                    return pcVar7;
                  }
                  pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                             (in_stack_00000048,&stack0x00001ae8,5);
                  if (DAT_1804b4d40 == 0) {
                    return pcVar8;
                  }
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,in_stack_00000058);
                  (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
                  uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar6);
                  DAT_1804b2400 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b2400,in_stack_00000078);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000088,0);
                pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
                (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
                pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_000000a8);
                (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
                (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar10,in_stack_00000070);
                pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)pcVar5 == '\0') {
                  return pcVar5;
                }
              }
            }
          }
        }
      }
    }
  }
  return pcVar5;
}

