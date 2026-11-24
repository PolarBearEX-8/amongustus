/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=151 entry=18047265f blocks=33 maxInstructions=50000 */

/* cut edge: 0x18047265f -> 0x180473ae8 */
/* cut edge: 0x1804727ba -> 0x180473ae8 */
/* cut edge: 0x1804727d0 -> 0x180473ae8 */
/* cut edge: 0x18047298b -> 0x180473ae8 */
/* cut edge: 0x180472a7d -> 0x180473ae8 */
/* cut edge: 0x180472a93 -> 0x180473ae8 */
/* cut edge: 0x180472c4e -> 0x180473ae8 */
/* cut edge: 0x180472d40 -> 0x180473ae8 */
/* cut edge: 0x180472d56 -> 0x180473ae8 */
/* cut edge: 0x180472f11 -> 0x180473ae8 */
/* cut edge: 0x18047300b -> 0x180473ae8 */
/* cut edge: 0x180473021 -> 0x180473ae8 */
/* cut edge: 0x1804731db -> 0x180473ae8 */
/* cut edge: 0x1804732f1 -> 0x180473ae8 */
/* cut edge: 0x180473326 -> 0x18047442a */
/* cut edge: 0x180473345 -> 0x18047447b */
/* cut edge: 0x180473355 -> 0x180473ae8 */
/* cut edge: 0x180473379 -> 0x1804744b5 */
/* cut edge: 0x180473398 -> 0x180473ae8 */
/* cut edge: 0x1804733c1 -> 0x180473ae8 */
/* cut edge: 0x1804733dc -> 0x180473ae8 */
/* cut edge: 0x1804733f2 -> 0x1804735aa */
/* cut edge: 0x180473447 -> 0x1804735aa */
/* target 0x1804735aa original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x18047442a original byte 0x48 replaced by RET */
/* target 0x18047447b original byte 0x48 replaced by RET */
/* target 0x1804744b5 original byte 0x48 replaced by RET */


longlong * region_00151_18047265f(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  longlong *plVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  ulonglong uVar13;
  longlong lVar14;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  ulonglong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  longlong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  char in_stack_00000118;
  undefined8 in_stack_00000148;
  undefined8 in_stack_000001b0;
  undefined8 in_stack_000001c0;
  
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000090,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,0,in_stack_000001c0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000001b0);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,2);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar6,3,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar6,4,uVar7);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
  plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)plVar8 != '\0') {
    return plVar8;
  }
  if (in_stack_000000b0 == 0) {
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    if (DAT_1804b48e8 == 0) {
      lVar14 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar14 + 0x390);
      uVar6 = FUN_1803335f0(in_stack_00000048);
      uVar5 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x0000028e,1);
      lVar14 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar14 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000d10,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
      DAT_1804b48e8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
    }
    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b48e8,in_stack_000000a0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x222);
    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0x34f42 ^ in_stack_000000c8 ^
                       0xf8ba8b90558eb3c5);
    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    if (DAT_1804b48f0 == 0) {
      lVar14 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar14 + 0x390);
      uVar6 = FUN_1803335f0(in_stack_00000048);
      uVar5 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x0000028c,1);
      lVar14 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar14 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000d00,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
      DAT_1804b48f0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
    }
    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b48f0,in_stack_000000a0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2c71);
    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0x8a46e ^ in_stack_000000c8 ^
                       0x8fcf68580dad85b9);
    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    if (DAT_1804b48f8 == 0) {
      lVar14 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar14 + 0x390);
      uVar6 = FUN_1803335f0(in_stack_00000048);
      uVar5 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x0000028a,1);
      lVar14 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar14 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000cf0,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
      DAT_1804b48f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
    }
    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b48f8,in_stack_000000a0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x6312);
    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0x86df9 ^ in_stack_000000c8 ^
                       0xe87d77e59e39bf04);
    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
    uVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar8 != '\0') {
      return plVar8;
    }
    if (DAT_1804b4900 == 0) {
      lVar14 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar14 + 0x390);
      uVar6 = FUN_1803335f0(in_stack_00000048);
      uVar5 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000288,1);
      lVar14 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar14 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000ce0,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
      DAT_1804b4900 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
    }
    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4900,in_stack_000000a0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4d19);
    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0x83e7e ^ in_stack_000000c8 ^
                       0xbae93be58c3f1452);
    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
    lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (in_stack_00000118 != '\0' || cVar4 != '\0') {
      return (longlong *)(ulonglong)(cVar4 != '\0');
    }
    in_stack_00000068 = lVar14 + (uVar13 ^ in_stack_00000068);
  }
  uVar5 = (**(code **)(*in_stack_00000048 + 0x420))
                    (in_stack_00000048,in_stack_00000148,in_stack_00000058);
  plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)plVar8 == '\0') {
    if (DAT_1804b52c8 == 0) {
      return in_stack_00000048;
    }
    if (DAT_1804b5a70 == 0) {
      return in_stack_00000048;
    }
    (**(code **)(*in_stack_00000048 + 0x500))(in_stack_00000048,DAT_1804b52c8,DAT_1804b5a70,uVar5);
    plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar8 == '\0') {
      if (DAT_1804b5d78 == 0) {
        return in_stack_00000048;
      }
      (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048);
      plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((((char)plVar8 == '\0') &&
          (plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)plVar8 == '\0'))
         && (plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)plVar8 == '\0')
         ) {
        plVar8 = (longlong *)(((longlong)in_stack_00000068 / 0xecd58) * 0xecd58);
        if (DAT_1804b4908 == 0) {
          lVar14 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar14 + 0x390);
          uVar6 = FUN_180333550(in_stack_00000048);
          uVar5 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000286,1);
          lVar14 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar14 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000cd0,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
          (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar5 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar5,uVar7);
          DAT_1804b4908 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
          plVar8 = (longlong *)(**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
        }
        return plVar8;
      }
    }
  }
  return plVar8;
}

