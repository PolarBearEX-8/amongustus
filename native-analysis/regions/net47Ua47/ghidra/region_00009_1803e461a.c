/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=9 entry=1803e461a blocks=43 maxInstructions=50000 */

/* cut edge: 0x1803e4623 -> 0x1803eaf2d */
/* cut edge: 0x1803e463d -> 0x180473ae8 */
/* cut edge: 0x1803e4796 -> 0x180473ae8 */
/* cut edge: 0x1803e4898 -> 0x1803ec73f */
/* cut edge: 0x1803e48aa -> 0x180473ae8 */
/* cut edge: 0x1803e4a03 -> 0x180473ae8 */
/* cut edge: 0x1803e4afc -> 0x18040bd2d */
/* cut edge: 0x1803e4b09 -> 0x180473ae8 */
/* cut edge: 0x1803e4c62 -> 0x180473ae8 */
/* cut edge: 0x1803e4d64 -> 0x1803f6482 */
/* cut edge: 0x1803e4d8d -> 0x180473ae8 */
/* cut edge: 0x1803e4ee6 -> 0x180473ae8 */
/* cut edge: 0x1803e4fe8 -> 0x1803f5863 */
/* cut edge: 0x1803e5007 -> 0x180473ae8 */
/* cut edge: 0x1803e5160 -> 0x180473ae8 */
/* cut edge: 0x1803e5262 -> 0x1803f885b */
/* cut edge: 0x1803e526f -> 0x180473ae8 */
/* cut edge: 0x1803e53c8 -> 0x180473ae8 */
/* cut edge: 0x1803e54c1 -> 0x18043d6bc */
/* cut edge: 0x1803e54e2 -> 0x18040f3fd */
/* cut edge: 0x1803e54f9 -> 0x180406cd6 */
/* cut edge: 0x1803e5506 -> 0x180473ae8 */
/* cut edge: 0x1803e551c -> 0x1803e565f */
/* cut edge: 0x1803e552c -> 0x1803e565f */
/* target 0x1803e565f original byte 0x48 replaced by RET */
/* target 0x1803eaf2d original byte 0x39 replaced by RET */
/* target 0x1803ec73f original byte 0x48 replaced by RET */
/* target 0x1803f5863 original byte 0x48 replaced by RET */
/* target 0x1803f6482 original byte 0x48 replaced by RET */
/* target 0x1803f885b original byte 0x48 replaced by RET */
/* target 0x180406cd6 original byte 0x48 replaced by RET */
/* target 0x18040bd2d original byte 0x48 replaced by RET */
/* target 0x18040f3fd original byte 0x48 replaced by RET */
/* target 0x18043d6bc original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


undefined8 region_00009_1803e461a(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  int iVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  int unaff_ESI;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  int in_stack_000000e0;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00000108;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00002e78;
  undefined8 in_stack_00002e80;
  undefined8 in_stack_00002e88;
  undefined8 in_stack_00002e90;
  undefined8 in_stack_00002e98;
  undefined8 in_stack_00002ea0;
  
  if (unaff_ESI == in_stack_000000e0) {
    return in_stack_000000f8;
  }
  uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)uVar6 == '\0') {
    if (DAT_1804b3778 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_180333550(in_stack_00000048);
      uVar6 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006f0,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002bb0,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
      DAT_1804b3778 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b3778,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x39ec);
    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002ea0);
    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
    iVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000120);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar6 == '\0') {
      if (iVar5 == in_stack_000000e0) {
        return in_stack_00000108;
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)uVar6 == '\0') {
        if (DAT_1804b3780 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000048);
          uVar6 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006ee,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002ba0,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
          DAT_1804b3780 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b3780,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1ee3);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002e98);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
        iVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000120)
        ;
        uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)uVar6 == '\0') {
          if (iVar5 == in_stack_000000e0) {
            return uVar6;
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)uVar6 == '\0') {
            if (DAT_1804b3788 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_180333550(in_stack_00000048);
              uVar6 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006ec,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002b90,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
              DAT_1804b3788 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
            }
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b3788,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1877);
            (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_00002e90);
            (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
            iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar6,in_stack_00000120);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)uVar6 == '\0') {
              if (iVar5 == in_stack_000000e0) {
                return in_stack_000000e8;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)uVar6 == '\0') {
                if (DAT_1804b3790 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_180333550(in_stack_00000048);
                  uVar6 = DAT_1804b59f0;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006ea,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000048);
                  uVar10 = FUN_180002550(in_stack_00000048);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002b80,5);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000048);
                  uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                  (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                  uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                  DAT_1804b3790 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                }
                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b3790,in_stack_000000a0);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4c9c);
                (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                   in_stack_00002e88);
                (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar6,in_stack_00000120);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)uVar6 == '\0') {
                  if (iVar5 == in_stack_000000e0) {
                    return in_stack_000000e8;
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)uVar6 == '\0') {
                    if (DAT_1804b3798 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_180333550(in_stack_00000048);
                      uVar6 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006e8,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002b70,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                      DAT_1804b3798 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b3798,in_stack_000000a0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1193)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_00002e80);
                    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                      (in_stack_00000048,uVar6,in_stack_00000120);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar6 == '\0') {
                      if (iVar5 == in_stack_000000e0) {
                        return uVar6;
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)uVar6 == '\0') {
                        if (DAT_1804b37a0 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar7 = FUN_180333550(in_stack_00000048);
                          uVar6 = DAT_1804b59f0;
                          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006e6,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar9 = FUN_1800024f0(in_stack_00000048);
                          uVar10 = FUN_180002550(in_stack_00000048);
                          uVar11 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00002b60,5);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar12 = FUN_1800025a0(in_stack_00000048);
                          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                          uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                          DAT_1804b37a0 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                        }
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b37a0,in_stack_000000a0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x6a53);
                        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           in_stack_00002e78);
                        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                        iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                          (in_stack_00000048,uVar6,in_stack_00000120);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)uVar6 == '\0') {
                          if (iVar5 == in_stack_000000e0) {
                            return uVar6;
                          }
                          if (in_stack_000000e0 == 0) {
                            return uVar6;
                          }
                          if (in_stack_000000e0 == 3) {
                            return in_stack_00000068;
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)uVar6 == '\0') {
                            if (DAT_1804b37a8 == 0) {
                              lVar1 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar1 + 0x390);
                              uVar7 = FUN_180333550(in_stack_00000048);
                              uVar6 = DAT_1804b59f0;
                              uVar8 = (**(code **)(lVar1 + 0x518))
                                                (in_stack_00000048,&stack0x000006e4,1);
                              lVar1 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar1 + 0x390);
                              uVar9 = FUN_1800024f0(in_stack_00000048);
                              uVar10 = FUN_180002550(in_stack_00000048);
                              uVar11 = (**(code **)(lVar1 + 0x518))
                                                 (in_stack_00000048,&stack0x00002b50,5);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar12 = FUN_1800025a0(in_stack_00000048);
                              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar12);
                              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                              uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,
                                                uVar8);
                              DAT_1804b37a8 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6)
                              ;
                              uVar6 = (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar6);
                            }
                            return uVar6;
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
  return uVar6;
}

