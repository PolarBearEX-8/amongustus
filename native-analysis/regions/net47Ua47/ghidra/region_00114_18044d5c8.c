/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=114 entry=18044d5c8 blocks=32 maxInstructions=50000 */

/* cut edge: 0x18044d5c8 -> 0x180473ae8 */
/* cut edge: 0x18044d6d6 -> 0x180473ae8 */
/* cut edge: 0x18044d6f1 -> 0x180473ae8 */
/* cut edge: 0x18044d8ab -> 0x180473ae8 */
/* cut edge: 0x18044d99d -> 0x180473ae8 */
/* cut edge: 0x18044d9b3 -> 0x180473ae8 */
/* cut edge: 0x18044db68 -> 0x180473ae8 */
/* cut edge: 0x18044dc5a -> 0x180473ae8 */
/* cut edge: 0x18044dc70 -> 0x180473ae8 */
/* cut edge: 0x18044de22 -> 0x180473ae8 */
/* cut edge: 0x18044df17 -> 0x180473ae8 */
/* cut edge: 0x18044df2d -> 0x180473ae8 */
/* cut edge: 0x18044e0f0 -> 0x180473ae8 */
/* cut edge: 0x18044e1fb -> 0x18044aac5 */
/* cut edge: 0x18044e20a -> 0x180473ae8 */
/* cut edge: 0x18044e225 -> 0x180473ae8 */
/* cut edge: 0x18044e3df -> 0x180473ae8 */
/* cut edge: 0x18044e4d1 -> 0x180473ae8 */
/* cut edge: 0x18044e4e7 -> 0x180473ae8 */
/* cut edge: 0x18044e4fd -> 0x18044e699 */
/* cut edge: 0x18044e4fd -> 0x18044e54d */
/* target 0x18044aac5 original byte 0x8b replaced by RET */
/* target 0x18044e54d original byte 0x4c replaced by RET */
/* target 0x18044e699 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


longlong region_00114_18044d5c8(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  longlong lVar7;
  longlong lVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  ulonglong uVar13;
  ulonglong unaff_R12;
  ulonglong unaff_R14;
  ulonglong uVar14;
  longlong *in_stack_00000048;
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
  char in_stack_00000148;
  
  uVar4 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2d09);
  (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar4,in_stack_000000a8,uVar5)
  ;
  lVar7 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar4,in_stack_000000d8);
  lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar8 != '\0') {
    return lVar8;
  }
  uVar14 = lVar7 + (unaff_R12 ^ unaff_R14);
  if (in_stack_00000148 == '\0') {
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    if (DAT_1804b4378 == 0) {
      lVar7 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar7 + 0x390);
      uVar5 = FUN_1803335f0(in_stack_00000048);
      uVar4 = DAT_1804b59f0;
      uVar6 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000003f0,1);
      lVar7 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar7 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000013f0,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar5,uVar4,uVar6);
      DAT_1804b4378 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4378,in_stack_000000a0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x7059);
    (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)uVar14 % 0x6330b ^ in_stack_000000c8 ^ 0x8b21623db0077b92);
    (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar4,in_stack_000000d8);
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    if (DAT_1804b4380 == 0) {
      lVar7 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar7 + 0x390);
      uVar5 = FUN_1803335f0(in_stack_00000048);
      uVar4 = DAT_1804b59f0;
      uVar6 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000003ee,1);
      lVar7 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar7 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000013e0,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar5,uVar4,uVar6);
      DAT_1804b4380 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4380,in_stack_000000a0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x24c8);
    (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)uVar14 % 0x7be7e ^ in_stack_000000c8 ^ 0xd6808a4ef630ecd0);
    (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar4,in_stack_000000d8);
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    if (DAT_1804b4388 == 0) {
      lVar7 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar7 + 0x390);
      uVar5 = FUN_1803335f0(in_stack_00000048);
      uVar4 = DAT_1804b59f0;
      uVar6 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000003ec,1);
      lVar7 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar7 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000013d0,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar5,uVar4,uVar6);
      DAT_1804b4388 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4388,in_stack_000000a0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x6f77);
    (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)uVar14 % 0x8b653 ^ in_stack_000000c8 ^ 0x8b1aed9842cba272);
    (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
    uVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar4,in_stack_000000d8);
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)lVar7 != '\0') {
      return lVar7;
    }
    if (DAT_1804b4390 == 0) {
      lVar7 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar7 + 0x390);
      uVar5 = FUN_1803335f0(in_stack_00000048);
      uVar4 = DAT_1804b59f0;
      uVar6 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000003ea,1);
      lVar7 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar7 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000013c0,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar5,uVar4,uVar6);
      DAT_1804b4390 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4390,in_stack_000000a0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4a4e);
    (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)uVar14 % 0xb5268 ^ in_stack_000000c8 ^ 0xe2dc87cf4bc7cb62);
    (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
    lVar7 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar4,in_stack_000000d8);
    lVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)lVar8 != '\0') {
      return lVar8;
    }
    uVar14 = (uVar13 ^ uVar14) - lVar7;
  }
  if (in_stack_00000118 == 0) {
    return lVar8;
  }
  lVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (((char)lVar7 == '\0') &&
     (lVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)lVar7 == '\0')) {
    if (DAT_1804b4398 == 0) {
      lVar7 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar7 + 0x390);
      uVar5 = FUN_1803335f0(in_stack_00000048);
      uVar4 = DAT_1804b59f0;
      uVar6 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000003e8,1);
      lVar7 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar7 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar7 + 0x518))(in_stack_00000048,&stack0x000013b0,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar4 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar5,uVar4,uVar6);
      DAT_1804b4398 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4398,in_stack_000000a0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1492);
    (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)uVar14 % 0x689fe ^ in_stack_000000c8 ^ 0xec8c28a063535413);
    (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar4,in_stack_000000a8,uVar5);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar4,in_stack_000000d8);
    lVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((((char)lVar7 == '\0') &&
        (lVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)lVar7 == '\0')) &&
       (lVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)lVar7 == '\0')) {
      lVar7 = ((longlong)uVar14 / 0x1137d) * 0x1137d;
      if (DAT_1804b43a0 != 0) {
        return lVar7;
      }
      return lVar7;
    }
  }
  return lVar7;
}

