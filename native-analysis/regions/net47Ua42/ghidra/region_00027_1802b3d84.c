/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=27 entry=1802b3d84 blocks=44 maxInstructions=50000 */

/* cut edge: 0x1802b3d84 -> 0x1802e144b */
/* cut edge: 0x1802b3d9b -> 0x1802e9fcb */
/* cut edge: 0x1802b3db7 -> 0x18029b02c */
/* cut edge: 0x1802b3dc6 -> 0x1802f24df */
/* cut edge: 0x1802b3dcb -> 0x180333186 */
/* cut edge: 0x1802b3f24 -> 0x180333186 */
/* cut edge: 0x1802b402c -> 0x1803093e7 */
/* cut edge: 0x1802b4041 -> 0x180333186 */
/* cut edge: 0x1802b4197 -> 0x180333186 */
/* cut edge: 0x1802b4299 -> 0x1802ce35d */
/* cut edge: 0x1802b42ab -> 0x180333186 */
/* cut edge: 0x1802b4404 -> 0x180333186 */
/* cut edge: 0x1802b44fd -> 0x180308542 */
/* cut edge: 0x1802b450a -> 0x180333186 */
/* cut edge: 0x1802b4663 -> 0x180333186 */
/* cut edge: 0x1802b4765 -> 0x1802d1360 */
/* cut edge: 0x1802b4774 -> 0x180333186 */
/* cut edge: 0x1802b48cd -> 0x180333186 */
/* cut edge: 0x1802b49c6 -> 0x18030c5b8 */
/* cut edge: 0x1802b49d3 -> 0x180333186 */
/* cut edge: 0x1802b4b2c -> 0x180333186 */
/* cut edge: 0x1802b4c25 -> 0x18030c5ae */
/* cut edge: 0x1802b4c32 -> 0x1802a2a89 */
/* cut edge: 0x1802b4c40 -> 0x180333186 */
/* cut edge: 0x1802b4c56 -> 0x1802b4d99 */
/* cut edge: 0x1802b4c66 -> 0x1802b4d99 */
/* target 0x18029b02c original byte 0x83 replaced by RET */
/* target 0x1802a2a89 original byte 0x48 replaced by RET */
/* target 0x1802b4d99 original byte 0x48 replaced by RET */
/* target 0x1802ce35d original byte 0x48 replaced by RET */
/* target 0x1802d1360 original byte 0x48 replaced by RET */
/* target 0x1802e144b original byte 0x48 replaced by RET */
/* target 0x1802e9fcb original byte 0x48 replaced by RET */
/* target 0x1802f24df original byte 0x48 replaced by RET */
/* target 0x180308542 original byte 0x48 replaced by RET */
/* target 0x1803093e7 original byte 0x48 replaced by RET */
/* target 0x18030c5ae original byte 0x4c replaced by RET */
/* target 0x18030c5b8 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00027_1802b3d84(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  int iVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  int unaff_ESI;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  longlong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  int in_stack_000000e0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_000030a8;
  undefined8 in_stack_000030b0;
  undefined8 in_stack_000030b8;
  undefined8 in_stack_000030c0;
  undefined8 in_stack_000030c8;
  undefined8 in_stack_000030d0;
  
  if (unaff_ESI == in_stack_000000e0) {
    return;
  }
  if (in_stack_000000e0 == 4) {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804b0ab8 == 0) {
      lVar1 = *in_stack_00000040;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_180333550(in_stack_00000040);
      uVar13 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006a4,1);
      lVar1 = *in_stack_00000040;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000040);
      uVar10 = FUN_180002550(in_stack_00000040);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002d08,5);
      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000040);
      uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
      (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
      DAT_1804b0ab8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,DAT_1804b0ab8,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5ce5);
    (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_000030d0);
    (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
    iVar6 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar13,in_stack_000000f8);
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if (cVar5 == '\0') {
      if (0 < in_stack_000000b0) {
        if (iVar6 == in_stack_000000e0) {
          return;
        }
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar5 != '\0') {
          return;
        }
        if (DAT_1804b0ac0 == 0) {
          lVar1 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000040);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006a2,1);
          lVar1 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000040);
          uVar10 = FUN_180002550(in_stack_00000040);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002cf8,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000040);
          uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
          (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
          DAT_1804b0ac0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b0ac0,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x3979);
        (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_000030c0);
        (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
        iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                          (in_stack_00000040,uVar13,in_stack_000000f8);
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar5 != '\0') {
          return;
        }
        if (iVar6 == in_stack_000000e0) {
          return;
        }
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar5 != '\0') {
          return;
        }
        if (DAT_1804b0ac8 == 0) {
          lVar1 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000040);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006a0,1);
          lVar1 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000040);
          uVar10 = FUN_180002550(in_stack_00000040);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002ce8,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000040);
          uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
          (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
          DAT_1804b0ac8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b0ac8,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5dd1);
        (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_000030a8);
        (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
        iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                          (in_stack_00000040,uVar13,in_stack_000000f8);
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar5 != '\0') {
          return;
        }
      }
      if (iVar6 == in_stack_000000e0) {
        return;
      }
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b0ad0 == 0) {
          lVar1 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000040);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000069e,1);
          lVar1 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000040);
          uVar10 = FUN_180002550(in_stack_00000040);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002cd8,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000040);
          uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
          (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
          DAT_1804b0ad0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b0ad0,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x604f);
        (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_000030c8);
        (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
        iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                          (in_stack_00000040,uVar13,in_stack_000000f8);
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar5 == '\0') {
          if (iVar6 == in_stack_000000e0) {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b0ad8 == 0) {
              lVar1 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_180333550(in_stack_00000040);
              uVar13 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000069c,1);
              lVar1 = *in_stack_00000040;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000040);
              uVar10 = FUN_180002550(in_stack_00000040);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002cc8,5);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000040);
              uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
              (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
              DAT_1804b0ad8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b0ad8,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7c9);
            (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_000030b8);
            (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
            iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                              (in_stack_00000040,uVar13,in_stack_000000f8);
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if (cVar5 == '\0') {
              if (iVar6 == in_stack_000000e0) {
                return;
              }
              cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
              if (cVar5 == '\0') {
                if (DAT_1804b0ae0 == 0) {
                  lVar1 = *in_stack_00000040;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_180333550(in_stack_00000040);
                  uVar13 = DAT_1804b59f0;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000069a,1);
                  lVar1 = *in_stack_00000040;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000040);
                  uVar10 = FUN_180002550(in_stack_00000040);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002cb8,5);
                  pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000040);
                  uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                  (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                  DAT_1804b0ae0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13)
                  ;
                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,DAT_1804b0ae0,in_stack_000000a0);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                  (in_stack_00000040,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x3961);
                (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   in_stack_000030b0);
                (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
                iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                                  (in_stack_00000040,uVar13,in_stack_000000f8);
                cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                if (cVar5 == '\0') {
                  if (iVar6 == in_stack_000000e0) {
                    return;
                  }
                  if (in_stack_000000e0 == 3) {
                    return;
                  }
                  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b0ae8 == 0) {
                      lVar1 = *in_stack_00000040;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_180333550(in_stack_00000040);
                      uVar13 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000698,1);
                      lVar1 = *in_stack_00000040;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000040);
                      uVar10 = FUN_180002550(in_stack_00000040);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002ca8,5);
                      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000040);
                      uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                      (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                      uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                      DAT_1804b0ae8 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                    }
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
  return;
}

