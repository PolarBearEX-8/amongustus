/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=78 entry=18038191c blocks=36 maxInstructions=50000 */

/* cut edge: 0x18038191c -> 0x1803351d6 */
/* cut edge: 0x180381a24 -> 0x1803351d6 */
/* cut edge: 0x180381b7e -> 0x1803351d6 */
/* cut edge: 0x180381c70 -> 0x1803351d6 */
/* cut edge: 0x180381dd8 -> 0x1803351d6 */
/* cut edge: 0x180381ec6 -> 0x1803351d6 */
/* cut edge: 0x180382040 -> 0x1803351d6 */
/* cut edge: 0x180382131 -> 0x1803351d6 */
/* cut edge: 0x1803822b7 -> 0x1803351d6 */
/* cut edge: 0x18038244d -> 0x1803351d6 */
/* cut edge: 0x1803825b5 -> 0x1803351d6 */
/* cut edge: 0x1803826c6 -> 0x1803351d6 */
/* cut edge: 0x18038271d -> 0x1803817ca */
/* cut edge: 0x180382746 -> 0x1803351d6 */
/* cut edge: 0x18038274b -> 0x1803351d6 */
/* cut edge: 0x180382753 -> 0x1803828a5 */
/* cut edge: 0x180382763 -> 0x1803828a5 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803817ca original byte 0x48 replaced by RET */
/* target 0x1803828a5 original byte 0x48 replaced by RET */


void region_00078_18038191c(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  char cVar3;
  int iVar4;
  undefined4 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong lVar12;
  longlong lVar13;
  ulonglong uVar14;
  uint uVar15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000050;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  int iStack00000000000000b0;
  undefined4 uStack00000000000000b4;
  code *in_stack_000000b8;
  undefined8 in_stack_000000c0;
  int iStack00000000000000d0;
  undefined8 in_stack_000000d8;
  ulonglong in_stack_000000e8;
  code *in_stack_000000f0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000110;
  undefined8 in_stack_00000118;
  undefined8 in_stack_00000120;
  code *in_stack_00000128;
  undefined8 in_stack_00000130;
  undefined8 in_stack_00000138;
  undefined8 in_stack_000001c0;
  
  in_stack_00000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x3979);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c0);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar6);
  iVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar3 != '\0') {
    return;
  }
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (iVar4 <= iStack00000000000000d0) {
    if (cVar3 != '\0') {
      return;
    }
    if (DAT_1804b2af8 == 0) {
      lVar12 = *in_stack_00000048;
      _iStack00000000000000d0 = *(code **)(lVar12 + 0x390);
      _iStack00000000000000b0 = (code *)FUN_180333550(in_stack_00000048);
      in_stack_000000d8 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000530,1);
      lVar12 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar12 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000cf8,5);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar6 = in_stack_00000058;
      uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar11);
      (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar6 = (*_iStack00000000000000d0)
                        (in_stack_00000048,uVar6,_iStack00000000000000b0,in_stack_000000d8,uVar7);
      DAT_1804b2af8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    return;
  }
  if (cVar3 != '\0') {
    return;
  }
  if (DAT_1804b2ad0 == 0) {
    lVar12 = *in_stack_00000048;
    _iStack00000000000000b0 = *(code **)(lVar12 + 0x390);
    in_stack_000000f0 = (code *)FUN_180333550(in_stack_00000048);
    in_stack_000000f8 = DAT_1804b59f0;
    uVar7 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x0000053a,1);
    lVar12 = *in_stack_00000048;
    pcVar1 = *(code **)(lVar12 + 0x390);
    uVar8 = FUN_1800024f0(in_stack_00000048);
    uVar9 = FUN_180002550(in_stack_00000048);
    uVar10 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000d48,5);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
    uVar11 = FUN_1800025a0(in_stack_00000048);
    uVar6 = in_stack_00000058;
    uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar11);
    (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
    uVar6 = (*_iStack00000000000000b0)
                      (in_stack_00000048,uVar6,in_stack_000000f0,in_stack_000000f8,uVar7);
    DAT_1804b2ad0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
  }
  in_stack_00000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2ad0,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2876);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d8);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar6);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
  _iStack00000000000000b0 = (code *)CONCAT44(uStack00000000000000b4,uVar5);
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar3 != '\0') {
    return;
  }
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar3 != '\0') {
    return;
  }
  if (DAT_1804b2ad8 == 0) {
    lVar12 = *in_stack_00000048;
    in_stack_000000f0 = *(code **)(lVar12 + 0x390);
    in_stack_000000f8 = FUN_180333550(in_stack_00000048);
    in_stack_00000110 = DAT_1804b59f0;
    uVar7 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000538,1);
    lVar12 = *in_stack_00000048;
    pcVar1 = *(code **)(lVar12 + 0x390);
    uVar8 = FUN_1800024f0(in_stack_00000048);
    uVar9 = FUN_180002550(in_stack_00000048);
    uVar10 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000d38,5);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
    uVar11 = FUN_1800025a0(in_stack_00000048);
    uVar6 = in_stack_00000058;
    uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar11);
    (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
    uVar6 = (*in_stack_000000f0)(in_stack_00000048,uVar6,in_stack_000000f8,in_stack_00000110,uVar7);
    DAT_1804b2ad8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
  }
  in_stack_00000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2ad8,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x28ac);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000100);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar6);
  iVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar3 != '\0') {
    return;
  }
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar3 != '\0') {
    return;
  }
  if (DAT_1804b2ae0 == 0) {
    lVar12 = *in_stack_00000048;
    in_stack_000000f0 = *(code **)(lVar12 + 0x390);
    in_stack_000000f8 = FUN_1803335f0(in_stack_00000048);
    in_stack_00000110 = DAT_1804b59f0;
    in_stack_00000118 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000536,1);
    lVar12 = *in_stack_00000048;
    pcVar1 = *(code **)(lVar12 + 0x390);
    uVar7 = FUN_1800024f0(in_stack_00000048);
    uVar8 = FUN_180002550(in_stack_00000048);
    uVar9 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000d28,5);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
    uVar10 = FUN_1800025a0(in_stack_00000048);
    uVar6 = in_stack_00000058;
    uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar10);
    (*pcVar1)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
    uVar6 = (*in_stack_000000f0)
                      (in_stack_00000048,uVar6,in_stack_000000f8,in_stack_00000110,in_stack_00000118
                      );
    DAT_1804b2ae0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
  }
  in_stack_00000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2ae0,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1317);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000130);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar6);
  lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000001c0);
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar3 != '\0') {
    return;
  }
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar3 != '\0') {
    return;
  }
  if (DAT_1804b2ae8 == 0) {
    lVar13 = *in_stack_00000048;
    in_stack_000000f0 = *(code **)(lVar13 + 0x390);
    in_stack_000000f8 = FUN_1803335f0(in_stack_00000048);
    in_stack_00000110 = DAT_1804b59f0;
    in_stack_00000118 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x00000534,1);
    lVar13 = *in_stack_00000048;
    in_stack_00000128 = *(code **)(lVar13 + 0x390);
    uVar7 = FUN_1800024f0(in_stack_00000048);
    uVar8 = FUN_180002550(in_stack_00000048);
    uVar9 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x00000d18,5);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
    uVar10 = FUN_1800025a0(in_stack_00000048);
    uVar6 = in_stack_00000058;
    uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10);
    (*in_stack_00000128)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
    uVar6 = (*in_stack_000000f0)
                      (in_stack_00000048,uVar6,in_stack_000000f8,in_stack_00000110,in_stack_00000118
                      );
    DAT_1804b2ae8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
  }
  in_stack_00000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2ae8,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1641);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000120);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar6);
  lVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000001c0);
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar3 != '\0') {
    return;
  }
  uVar15 = (iVar4 - iStack00000000000000b0) + 1;
  uVar14 = lVar13 + lVar12 * (in_stack_000000e8 & 0xffffffff | (longlong)in_stack_000000b8 << 0x20);
  if (uVar14 == 0x8000000000000000) {
    in_stack_000000b8 = (code *)0x0;
    in_stack_000000e8 = 0;
    if (uVar15 == 0xffffffff) goto LAB_1803826c6;
  }
  if ((uVar14 | (longlong)(int)uVar15) >> 0x20 == 0) {
    in_stack_000000e8 = (uVar14 & 0xffffffff) % (ulonglong)uVar15;
  }
  else {
    in_stack_000000e8 = (longlong)uVar14 % (longlong)(int)uVar15;
  }
  if ((longlong)in_stack_000000e8 < 0) {
    cVar3 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar3 != '\0') {
      return;
    }
    if (DAT_1804b2af0 == 0) {
      lVar12 = *in_stack_00000048;
      in_stack_000000b8 = *(code **)(lVar12 + 0x390);
      in_stack_000000f0 = (code *)FUN_1803335f0(in_stack_00000048);
      in_stack_000000f8 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000532,1);
      lVar12 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar12 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar12 + 0x518))(in_stack_00000048,&stack0x00000d08,5);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar6 = in_stack_00000058;
      uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar11);
      (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar6 = (*in_stack_000000b8)
                        (in_stack_00000048,uVar6,in_stack_000000f0,in_stack_000000f8,uVar7);
      DAT_1804b2af0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    in_stack_00000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2af0,in_stack_00000078);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4a33);
    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000138);
    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar6);
    lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000001c0);
    cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar3 != '\0') {
      return;
    }
    in_stack_000000e8 = lVar12 * in_stack_000000e8;
    in_stack_000000b8 = (code *)(in_stack_000000e8 >> 0x20);
  }
  else {
    in_stack_000000b8 = (code *)(in_stack_000000e8 >> 0x20);
  }
LAB_1803826c6:
  in_stack_00000050 =
       CONCAT71(in_stack_00000050._1_7_,(char)_iStack00000000000000b0 + (char)in_stack_000000e8);
  (**(code **)(*in_stack_00000048 + 0x680))
            (in_stack_00000048,in_stack_000000a0,_iStack00000000000000d0,1,&stack0x00000050);
  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar3 == '\0') {
    _iStack00000000000000d0 = (code *)(ulonglong)((int)_iStack00000000000000d0 + 1);
    cVar3 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar3 == '\0') {
      return;
    }
  }
  return;
}

