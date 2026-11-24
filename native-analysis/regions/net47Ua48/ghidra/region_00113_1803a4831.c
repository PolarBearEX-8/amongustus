/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=113 entry=1803a4831 blocks=43 maxInstructions=50000 */

/* cut edge: 0x1803a49ba -> 0x1803351d6 */
/* cut edge: 0x1803a4ac9 -> 0x1803351d6 */
/* cut edge: 0x1803a4c23 -> 0x1803351d6 */
/* cut edge: 0x1803a4d05 -> 0x1803351d6 */
/* cut edge: 0x1803a4ea4 -> 0x1803351d6 */
/* cut edge: 0x1803a4fb0 -> 0x1803d6ce7 */
/* cut edge: 0x1803a4fc7 -> 0x1803351d6 */
/* cut edge: 0x1803a4ff0 -> 0x1803d6d1e */
/* cut edge: 0x1803a500f -> 0x1803351d6 */
/* cut edge: 0x1803a5038 -> 0x1803351d6 */
/* cut edge: 0x1803a505e -> 0x1803d8b5e */
/* cut edge: 0x1803a507d -> 0x1803d8b94 */
/* cut edge: 0x1803a50d9 -> 0x1803d8be5 */
/* cut edge: 0x1803a50e9 -> 0x1803d8c1e */
/* cut edge: 0x1803a51ce -> 0x1803d6e51 */
/* cut edge: 0x1803a5238 -> 0x1803d6eac */
/* cut edge: 0x1803a5255 -> 0x1803351d6 */
/* cut edge: 0x1803a52f1 -> 0x1803351d6 */
/* cut edge: 0x1803a5317 -> 0x1803d8c54 */
/* cut edge: 0x1803a533e -> 0x1803d8c8a */
/* cut edge: 0x1803a53a0 -> 0x1803d8cdb */
/* cut edge: 0x1803a53b0 -> 0x1803d8d14 */
/* cut edge: 0x1803a54a8 -> 0x1803d6ee8 */
/* cut edge: 0x1803a5566 -> 0x1803d6f40 */
/* cut edge: 0x1803a5581 -> 0x1803351d6 */
/* cut edge: 0x1803a5658 -> 0x1803351d6 */
/* cut edge: 0x1803a567e -> 0x1803d8d4b */
/* cut edge: 0x1803a56a5 -> 0x1803d8d81 */
/* cut edge: 0x1803a5707 -> 0x1803d8dd2 */
/* cut edge: 0x1803a5717 -> 0x1803d8e0c */
/* cut edge: 0x1803a5809 -> 0x1803351d6 */
/* cut edge: 0x1803a5809 -> 0x1803a5908 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803a5908 original byte 0x48 replaced by RET */
/* target 0x1803d6ce7 original byte 0x48 replaced by RET */
/* target 0x1803d6d1e original byte 0x48 replaced by RET */
/* target 0x1803d6e51 original byte 0x48 replaced by RET */
/* target 0x1803d6eac original byte 0x48 replaced by RET */
/* target 0x1803d6ee8 original byte 0x48 replaced by RET */
/* target 0x1803d6f40 original byte 0x48 replaced by RET */
/* target 0x1803d8b5e original byte 0x48 replaced by RET */
/* target 0x1803d8b94 original byte 0x48 replaced by RET */
/* target 0x1803d8be5 original byte 0x48 replaced by RET */
/* target 0x1803d8c1e original byte 0x48 replaced by RET */
/* target 0x1803d8c54 original byte 0x48 replaced by RET */
/* target 0x1803d8c8a original byte 0x48 replaced by RET */
/* target 0x1803d8cdb original byte 0x48 replaced by RET */
/* target 0x1803d8d14 original byte 0x48 replaced by RET */
/* target 0x1803d8d4b original byte 0x48 replaced by RET */
/* target 0x1803d8d81 original byte 0x48 replaced by RET */
/* target 0x1803d8dd2 original byte 0x48 replaced by RET */
/* target 0x1803d8e0c original byte 0x48 replaced by RET */


void region_00113_1803a4831(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000c8;
  
  if (DAT_1804b2fa8 == 0) {
    lVar1 = *in_stack_00000048;
    pcVar2 = *(code **)(lVar1 + 0x390);
    uVar6 = FUN_180333500(in_stack_00000048);
    uVar12 = DAT_1804b59f0;
    uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003f8,1);
    lVar1 = *in_stack_00000048;
    pcVar3 = *(code **)(lVar1 + 0x390);
    uVar8 = FUN_1800024f0(in_stack_00000048);
    uVar9 = FUN_180002550(in_stack_00000048);
    uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004dd0,0x29);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
    uVar11 = FUN_1800025a0(in_stack_00000048);
    uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
    (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
    uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar6,uVar12,uVar7);
    DAT_1804b2fa8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
  }
  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2fa8,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,1,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc1fc402600d63b);
  (*pcVar2)(in_stack_00000048,uVar6,2,uVar7);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar2)(in_stack_00000048,uVar6,3,uVar7);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar12,in_stack_00000080,uVar6);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b2fb0 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_180333500(in_stack_00000048);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003f6,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000027f0,0xd);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
        (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar6,uVar12,uVar7);
        DAT_1804b2fb0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2fb0,in_stack_00000078);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc5815bc278527a);
      (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
      (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar12,in_stack_00000080,uVar6);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804b2fb8 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar7 = FUN_180333500(in_stack_00000048);
            uVar6 = DAT_1804b59f0;
            uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003f4,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004d70,0x29);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
            uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
            DAT_1804b2fb8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2fb8,in_stack_00000078);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0)
          ;
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,uVar12);
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,1,0);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              0xdfd4e03d2f61cade);
          (*pcVar2)(in_stack_00000048,uVar7,2,uVar12);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000c8);
          (*pcVar2)(in_stack_00000048,uVar7,3,uVar12);
          (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_00000080,uVar7)
          ;
          (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        }
      }
    }
  }
  return;
}

