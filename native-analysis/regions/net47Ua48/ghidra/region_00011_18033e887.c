/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=11 entry=18033e887 blocks=59 maxInstructions=50000 */

/* cut edge: 0x18033e887 -> 0x1803351d6 */
/* cut edge: 0x18033e974 -> 0x1803351d6 */
/* cut edge: 0x18033e9d2 -> 0x1803351d6 */
/* cut edge: 0x18033ea02 -> 0x1803351d6 */
/* cut edge: 0x18033ea28 -> 0x1803485c7 */
/* cut edge: 0x18033ea57 -> 0x1803485fd */
/* cut edge: 0x18033eab9 -> 0x18034864e */
/* cut edge: 0x18033eac9 -> 0x180348687 */
/* cut edge: 0x18033ebae -> 0x1803351d6 */
/* cut edge: 0x18033ec9b -> 0x180341e30 */
/* cut edge: 0x18033ecac -> 0x1803351d6 */
/* cut edge: 0x18033ee31 -> 0x1803351d6 */
/* cut edge: 0x18033ef1f -> 0x1803425bb */
/* cut edge: 0x18033ef2e -> 0x180341e62 */
/* cut edge: 0x18033ef33 -> 0x180346075 */
/* cut edge: 0x18033ef5f -> 0x1803351d6 */
/* cut edge: 0x18033ef7d -> 0x180345ae5 */
/* cut edge: 0x18033efa4 -> 0x1803351d6 */
/* cut edge: 0x18033effd -> 0x1803351d6 */
/* cut edge: 0x18033f02e -> 0x1803460c3 */
/* cut edge: 0x18033f05d -> 0x1803460fe */
/* cut edge: 0x18033f0c7 -> 0x180346161 */
/* cut edge: 0x18033f0d7 -> 0x18034619a */
/* cut edge: 0x18033f1c8 -> 0x1803351d6 */
/* cut edge: 0x18033f2e7 -> 0x1803351d6 */
/* cut edge: 0x18033f32b -> 0x1803351d6 */
/* cut edge: 0x18033f4d0 -> 0x1803351d6 */
/* cut edge: 0x18033f5e0 -> 0x1803351d6 */
/* cut edge: 0x18033f62f -> 0x1803414b4 */
/* cut edge: 0x18033f702 -> 0x1803414be */
/* cut edge: 0x18033f717 -> 0x180341549 */
/* cut edge: 0x18033f770 -> 0x180340c90 */
/* cut edge: 0x18033f779 -> 0x1803400c0 */
/* cut edge: 0x18033f782 -> 0x1803351d6 */
/* cut edge: 0x18033f7c4 -> 0x18033fe33 */
/* cut edge: 0x18033f7e3 -> 0x18033fe84 */
/* cut edge: 0x18033f7f3 -> 0x1803351d6 */
/* cut edge: 0x18033f817 -> 0x1803351d6 */
/* cut edge: 0x18033f817 -> 0x18033f85e */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18033f85e original byte 0x48 replaced by RET */
/* target 0x18033fe33 original byte 0x48 replaced by RET */
/* target 0x18033fe84 original byte 0x48 replaced by RET */
/* target 0x1803400c0 original byte 0x4c replaced by RET */
/* target 0x180340c90 original byte 0x8b replaced by RET */
/* target 0x1803414b4 original byte 0x31 replaced by RET */
/* target 0x1803414be original byte 0x48 replaced by RET */
/* target 0x180341549 original byte 0x48 replaced by RET */
/* target 0x180341e30 original byte 0x48 replaced by RET */
/* target 0x180341e62 original byte 0x48 replaced by RET */
/* target 0x1803425bb original byte 0x48 replaced by RET */
/* target 0x180345ae5 original byte 0x48 replaced by RET */
/* target 0x180346075 original byte 0x48 replaced by RET */
/* target 0x1803460c3 original byte 0x48 replaced by RET */
/* target 0x1803460fe original byte 0x48 replaced by RET */
/* target 0x180346161 original byte 0x48 replaced by RET */
/* target 0x18034619a original byte 0x48 replaced by RET */
/* target 0x1803485c7 original byte 0x48 replaced by RET */
/* target 0x1803485fd original byte 0x48 replaced by RET */
/* target 0x18034864e original byte 0x48 replaced by RET */
/* target 0x180348687 original byte 0x48 replaced by RET */


void region_00011_18033e887(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  code *pcVar4;
  longlong lVar5;
  longlong lVar6;
  char cVar7;
  int iVar8;
  int iVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
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
  undefined8 in_stack_000000b0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_000001f0;
  
  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
  (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
  (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
  (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 == '\0') {
    (**(code **)(*in_stack_00000048 + 0x468))(in_stack_00000048,in_stack_000001a0,in_stack_000001f0)
    ;
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 == '\0') {
      iVar8 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_000000b0);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 == '\0') {
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
        lVar3 = DAT_1804b59f8;
        uVar10 = DAT_1804b59f0;
        if (cVar7 == '\0') {
          if (DAT_1804b2108 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59f8 == 0) {
              return;
            }
            uVar11 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000007ac,1)
            ;
            lVar6 = DAT_1804b4d38;
            lVar5 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000020d8,5)
            ;
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar2)(in_stack_00000048,lVar5,lVar6,uVar12,uVar13);
            uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar11);
            DAT_1804b2108 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2108,in_stack_00000078);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
          (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000e0);
          (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
          iVar9 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar10,in_stack_00000070);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 == '\0') {
            if (iVar8 != iVar9) {
              return;
            }
            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar7 == '\0') {
              if (DAT_1804b2110 == 0) {
                lVar3 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar3 + 0x390);
                uVar11 = FUN_180333550(in_stack_00000048);
                uVar10 = DAT_1804b59f0;
                uVar12 = (**(code **)(lVar3 + 0x518))(in_stack_00000048,&stack0x000007aa,1);
                lVar3 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar3 + 0x390);
                uVar13 = FUN_1800024f0(in_stack_00000048);
                uVar14 = FUN_180002550(in_stack_00000048);
                uVar15 = (**(code **)(lVar3 + 0x518))(in_stack_00000048,&stack0x000020c8,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar16 = FUN_1800025a0(in_stack_00000048);
                uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar16);
                (*pcVar2)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
                uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar11,uVar10,uVar12);
                DAT_1804b2110 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
              }
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b2110,in_stack_00000078);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                 (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x3e8f);
              (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                  in_stack_000000c8 ^ 0x6628ec96228b4f5a);
              (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
              (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar7 == '\0') {
                if ((longlong)in_stack_000000c8 < 1) {
                  return;
                }
                return;
              }
            }
          }
        }
      }
    }
  }
  return;
}

