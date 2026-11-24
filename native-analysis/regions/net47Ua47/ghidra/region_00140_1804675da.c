/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=140 entry=1804675da blocks=45 maxInstructions=50000 */

/* cut edge: 0x1804675da -> 0x180474731 */
/* cut edge: 0x1804675f9 -> 0x180473ae8 */
/* cut edge: 0x180467622 -> 0x180473ae8 */
/* cut edge: 0x18046763d -> 0x180473ae8 */
/* cut edge: 0x18046781e -> 0x180473ae8 */
/* cut edge: 0x180467907 -> 0x180473ae8 */
/* cut edge: 0x18046791d -> 0x180473ae8 */
/* cut edge: 0x180467af6 -> 0x180473ae8 */
/* cut edge: 0x180467bda -> 0x180474788 */
/* cut edge: 0x180467bf9 -> 0x1804747e4 */
/* cut edge: 0x180467c09 -> 0x180473ae8 */
/* cut edge: 0x180467c2d -> 0x18047481e */
/* cut edge: 0x180467c4c -> 0x180473ae8 */
/* cut edge: 0x180467c79 -> 0x180473ae8 */
/* cut edge: 0x180467c8f -> 0x180473ae8 */
/* cut edge: 0x180467e68 -> 0x180473ae8 */
/* cut edge: 0x180467f96 -> 0x180474854 */
/* cut edge: 0x180467fb5 -> 0x1804748b0 */
/* cut edge: 0x180467fc5 -> 0x180473ae8 */
/* cut edge: 0x180467fe9 -> 0x1804748e9 */
/* cut edge: 0x180468008 -> 0x180473ae8 */
/* cut edge: 0x180468031 -> 0x18047491f */
/* cut edge: 0x180468050 -> 0x180473ae8 */
/* cut edge: 0x180468095 -> 0x180473ae8 */
/* cut edge: 0x1804680b8 -> 0x180473ae8 */
/* cut edge: 0x1804682bc -> 0x180473ae8 */
/* cut edge: 0x180468408 -> 0x180473ae8 */
/* cut edge: 0x180468462 -> 0x180469019 */
/* cut edge: 0x18046846b -> 0x180469b5b */
/* cut edge: 0x18046847c -> 0x180473ae8 */
/* cut edge: 0x1804684b1 -> 0x18046a695 */
/* cut edge: 0x1804684c1 -> 0x18046a695 */
/* cut edge: 0x1804684c9 -> 0x180473ae8 */
/* cut edge: 0x1804684df -> 0x180473ae8 */
/* cut edge: 0x1804684f5 -> 0x18046869e */
/* cut edge: 0x1804684f5 -> 0x180468547 */
/* target 0x180468547 original byte 0x4c replaced by RET */
/* target 0x18046869e original byte 0x48 replaced by RET */
/* target 0x180469019 original byte 0x48 replaced by RET */
/* target 0x180469b5b original byte 0x48 replaced by RET */
/* target 0x18046a695 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180474731 original byte 0x48 replaced by RET */
/* target 0x180474788 original byte 0x48 replaced by RET */
/* target 0x1804747e4 original byte 0x48 replaced by RET */
/* target 0x18047481e original byte 0x4c replaced by RET */
/* target 0x180474854 original byte 0x48 replaced by RET */
/* target 0x1804748b0 original byte 0x48 replaced by RET */
/* target 0x1804748e9 original byte 0x48 replaced by RET */
/* target 0x18047491f original byte 0x4c replaced by RET */


longlong * region_00140_1804675da(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  undefined8 uVar4;
  longlong *plVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  longlong lVar15;
  longlong *in_stack_00000048;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  code *pcStack00000000000000e8;
  char in_stack_00000118;
  undefined8 in_stack_00000148;
  undefined8 in_stack_00000170;
  undefined8 in_stack_000001b0;
  undefined8 in_stack_000001c0;
  longlong in_stack_000001c8;
  undefined8 in_stack_00000790;
  
  if (DAT_1804b52a8 == (code *)0x0) {
    return in_stack_00000048;
  }
  pcStack00000000000000e8 = DAT_1804b52a8;
  uVar4 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048);
  plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)plVar5 == '\0') {
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    if (DAT_1804b4728 == 0) {
      lVar15 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar15 + 0x390);
      uVar6 = FUN_1803335a0(in_stack_00000048);
      uVar12 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000302,1);
      lVar15 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar15 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00003750,0x16);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
      (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
      DAT_1804b4728 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b4728,in_stack_000000a0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x50e0);
    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       in_stack_00000068 % 0xa5c70 ^ in_stack_000000c8 ^ 0xa42e3961b5c5de55);
    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    if (DAT_1804b4730 == 0) {
      lVar15 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar15 + 0x390);
      uVar7 = FUN_1803335a0(in_stack_00000048);
      uVar6 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000300,1);
      lVar15 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar15 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00003720,0x16);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar13);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
      DAT_1804b4730 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4730,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2a89);
    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       in_stack_00000068 % 0x5ad2d ^ in_stack_000000c8 ^ 0xed29df2e60025b99);
    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    if (DAT_1804b52b0 == 0) {
      return in_stack_00000048;
    }
    if (DAT_1804b52b8 == 0) {
      return in_stack_00000048;
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x488))(in_stack_00000048,DAT_1804b52b0);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    if (DAT_1804b52c0 == 0) {
      return in_stack_00000048;
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,DAT_1804b52c0,uVar7);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    if (DAT_1804b4738 == 0) {
      lVar15 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar15 + 0x390);
      uVar8 = FUN_180333500(in_stack_00000048);
      uVar7 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x000002fe,1);
      lVar15 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar15 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar13 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00003180,0x19);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar14 = FUN_1800025a0(in_stack_00000048);
      uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar14);
      (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar13,uVar14);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar8,uVar7,uVar9);
      DAT_1804b4738 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4738,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000090,0);
    (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,uVar6);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00000790);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar6);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       in_stack_00000068 % 0x4ebf6 ^ 0x5028ead18b8d9f20);
    (*pcVar1)(in_stack_00000048,uVar8,2,uVar6);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000000c8);
    (*pcVar1)(in_stack_00000048,uVar8,3,uVar6);
    (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    if (DAT_1804b52c8 == 0) {
      return in_stack_00000048;
    }
    if (DAT_1804b52d0 == 0) {
      return in_stack_00000048;
    }
    (**(code **)(*in_stack_00000048 + 0x488))(in_stack_00000048,DAT_1804b52c8);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    if (DAT_1804b52d8 == 0) {
      return in_stack_00000048;
    }
    (**(code **)(*in_stack_00000048 + 0x420))(in_stack_00000048,in_stack_00000148);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    if (DAT_1804b52e0 == 0) {
      return in_stack_00000048;
    }
    (**(code **)(*in_stack_00000048 + 0x2d8))
              (in_stack_00000048,uVar4,pcStack00000000000000e8,DAT_1804b52e0,uVar12);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    if (DAT_1804b4740 == 0) {
      lVar15 = *in_stack_00000048;
      pcStack00000000000000e8 = *(code **)(lVar15 + 0x390);
      uVar6 = FUN_180333500(in_stack_00000048);
      uVar12 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x000002fc,1);
      lVar15 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar15 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00003d00,0x2a);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar11);
      (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar12 = (*pcStack00000000000000e8)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
      DAT_1804b4740 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b4740,in_stack_000000a0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000090,0);
    (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,0,in_stack_000001c0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000001b0);
    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
    (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,2,uVar4);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       in_stack_00000068 % 0x3a296 ^ 0x50236604602ceb6d);
    (*pcVar1)(in_stack_00000048,uVar6,3,uVar4);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000000c8);
    (*pcVar1)(in_stack_00000048,uVar6,4,uVar4);
    (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    lVar15 = (**(code **)(*in_stack_00000048 + 0x420))
                       (in_stack_00000048,in_stack_00000148,in_stack_00000170);
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar5 != '\0') {
      return plVar5;
    }
    plVar5 = (longlong *)0xffffffff;
    if (lVar15 <= in_stack_000001c8) {
      plVar5 = (longlong *)(ulonglong)(lVar15 < in_stack_000001c8);
    }
    if (in_stack_00000118 != '\0') {
      if (lVar15 <= in_stack_000001c8) {
        return plVar5;
      }
      if (in_stack_000001c8 < 1) {
        return (longlong *)*in_stack_00000048;
      }
      lVar15 = (*(code *)((longlong *)*in_stack_00000048)[0x84])
                         (in_stack_00000048,in_stack_00000148,in_stack_00000170);
      plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)plVar5 != '\0') {
        return plVar5;
      }
      if (lVar15 < 0) {
        return (longlong *)0x0;
      }
      plVar5 = (longlong *)(ulonglong)(lVar15 != 0);
    }
    if ((int)plVar5 < 1) {
      return plVar5;
    }
    plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if (((char)plVar5 == '\0') &&
       (plVar5 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)plVar5 == '\0')) {
      plVar5 = (longlong *)((in_stack_00000068 / 0x71756) * 0x71756);
      if (DAT_1804b4788 != 0) {
        return plVar5;
      }
      return plVar5;
    }
  }
  return plVar5;
}

