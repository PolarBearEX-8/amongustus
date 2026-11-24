/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=17 entry=1802a9d78 blocks=38 maxInstructions=50000 */

/* cut edge: 0x1802a9ebb -> 0x180333186 */
/* cut edge: 0x1802a9fb4 -> 0x1802fdd52 */
/* cut edge: 0x1802a9fc1 -> 0x180333186 */
/* cut edge: 0x1802aa11a -> 0x180333186 */
/* cut edge: 0x1802aa213 -> 0x180333186 */
/* cut edge: 0x1802aa220 -> 0x180333186 */
/* cut edge: 0x1802aa379 -> 0x180333186 */
/* cut edge: 0x1802aa47b -> 0x1802d9a34 */
/* cut edge: 0x1802aa485 -> 0x18029b02c */
/* cut edge: 0x1802aa498 -> 0x1803000ca */
/* cut edge: 0x1802aa49d -> 0x180333186 */
/* cut edge: 0x1802aa5f6 -> 0x180333186 */
/* cut edge: 0x1802aa707 -> 0x1802ec69e */
/* cut edge: 0x1802aa719 -> 0x180333186 */
/* cut edge: 0x1802aa872 -> 0x180333186 */
/* cut edge: 0x1802aa96b -> 0x18029bf02 */
/* cut edge: 0x1802aa978 -> 0x180333186 */
/* cut edge: 0x1802aaad1 -> 0x180333186 */
/* cut edge: 0x1802aabca -> 0x1802d8d61 */
/* cut edge: 0x1802aabd7 -> 0x180333186 */
/* cut edge: 0x1802aad30 -> 0x180333186 */
/* cut edge: 0x1802aad30 -> 0x1802aae29 */
/* target 0x18029b02c original byte 0x83 replaced by RET */
/* target 0x18029bf02 original byte 0x48 replaced by RET */
/* target 0x1802aae29 original byte 0x3b replaced by RET */
/* target 0x1802d8d61 original byte 0x48 replaced by RET */
/* target 0x1802d9a34 original byte 0x48 replaced by RET */
/* target 0x1802ec69e original byte 0x48 replaced by RET */
/* target 0x1802fdd52 original byte 0x48 replaced by RET */
/* target 0x1803000ca original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00017_1802a9d78(void)

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
  undefined8 in_stack_00003138;
  undefined8 in_stack_00003140;
  undefined8 in_stack_00003148;
  undefined8 in_stack_00003160;
  undefined8 in_stack_00003168;
  undefined8 in_stack_00003170;
  undefined8 in_stack_00003178;
  
  if (DAT_1804b0a28 == 0) {
    lVar1 = *in_stack_00000040;
    pcVar2 = *(code **)(lVar1 + 0x390);
    uVar7 = FUN_180333550(in_stack_00000040);
    uVar13 = DAT_1804b59f0;
    uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006c8,1);
    lVar1 = *in_stack_00000040;
    pcVar3 = *(code **)(lVar1 + 0x390);
    uVar9 = FUN_1800024f0(in_stack_00000040);
    uVar10 = FUN_180002550(in_stack_00000040);
    uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002e28,5);
    pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
    uVar12 = FUN_1800025a0(in_stack_00000040);
    uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
    (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
    uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
    DAT_1804b0a28 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
    (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
  }
  uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                     (in_stack_00000040,DAT_1804b0a28,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4475);
  (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00003178);
  (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
  uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                     (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
  iVar6 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar13,in_stack_000000f8);
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar5 == '\0') {
    if (iVar6 == in_stack_000000e0) {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b0a30 == 0) {
        lVar1 = *in_stack_00000040;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar7 = FUN_180333550(in_stack_00000040);
        uVar13 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006c6,1);
        lVar1 = *in_stack_00000040;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000040);
        uVar10 = FUN_180002550(in_stack_00000040);
        uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002e18,5);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000040);
        uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
        (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
        uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
        DAT_1804b0a30 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
      }
      uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b0a30,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4866);
      (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00003170);
      (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
      uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
      iVar6 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar13,in_stack_000000f8);
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (((cVar5 == '\0') && (iVar6 != in_stack_000000e0)) &&
         (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) {
        if (DAT_1804b0a38 == 0) {
          lVar1 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000040);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006c4,1);
          lVar1 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000040);
          uVar10 = FUN_180002550(in_stack_00000040);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002e08,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000040);
          uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
          (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
          DAT_1804b0a38 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b0a38,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7704);
        (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00003160);
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
            if (DAT_1804b0a40 == 0) {
              lVar1 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_180333550(in_stack_00000040);
              uVar13 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006c2,1);
              lVar1 = *in_stack_00000040;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000040);
              uVar10 = FUN_180002550(in_stack_00000040);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002df8,5);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000040);
              uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
              (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
              DAT_1804b0a40 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b0a40,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1bd3);
            (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00003168);
            (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
            iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                              (in_stack_00000040,uVar13,in_stack_000000f8);
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if (cVar5 == '\0') {
              if (0 < in_stack_000000b0) {
                if (iVar6 == in_stack_000000e0) {
                  return;
                }
                cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                if (cVar5 != '\0') {
                  return;
                }
                if (DAT_1804b0a48 == 0) {
                  lVar1 = *in_stack_00000040;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_180333550(in_stack_00000040);
                  uVar13 = DAT_1804b59f0;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006c0,1);
                  lVar1 = *in_stack_00000040;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000040);
                  uVar10 = FUN_180002550(in_stack_00000040);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002de8,5);
                  pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000040);
                  uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                  (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                  DAT_1804b0a48 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13)
                  ;
                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,DAT_1804b0a48,in_stack_000000a0);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                  (in_stack_00000040,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6ff3);
                (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   in_stack_00003140);
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
                if (DAT_1804b0a50 == 0) {
                  lVar1 = *in_stack_00000040;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_180333550(in_stack_00000040);
                  uVar13 = DAT_1804b59f0;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006be,1);
                  lVar1 = *in_stack_00000040;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000040);
                  uVar10 = FUN_180002550(in_stack_00000040);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002dd8,5);
                  pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000040);
                  uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                  (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                  DAT_1804b0a50 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13)
                  ;
                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,DAT_1804b0a50,in_stack_000000a0);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                  (in_stack_00000040,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x12f4);
                (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   in_stack_00003148);
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
                    if (DAT_1804b0a58 == 0) {
                      lVar1 = *in_stack_00000040;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_180333550(in_stack_00000040);
                      uVar13 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000006bc,1);
                      lVar1 = *in_stack_00000040;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000040);
                      uVar10 = FUN_180002550(in_stack_00000040);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002dc8,5);
                      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000040);
                      uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                      (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                      uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                      DAT_1804b0a58 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                    }
                    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                       (in_stack_00000040,DAT_1804b0a58,in_stack_000000a0);
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x52fb)
                    ;
                    (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                       in_stack_00003138);
                    (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                       (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
                    (**(code **)(*in_stack_00000040 + 0x188))
                              (in_stack_00000040,uVar13,in_stack_000000f8);
                    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if (cVar5 == '\0') {
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
  return;
}

