/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=93 entry=180438593 blocks=36 maxInstructions=50000 */

/* cut edge: 0x1804385bb -> 0x180447c7b */
/* cut edge: 0x1804385dc -> 0x180473ae8 */
/* cut edge: 0x1804385f2 -> 0x180473ae8 */
/* cut edge: 0x1804387af -> 0x180473ae8 */
/* cut edge: 0x1804388a6 -> 0x180473ae8 */
/* cut edge: 0x1804388bc -> 0x180473ae8 */
/* cut edge: 0x180438a7b -> 0x180473ae8 */
/* cut edge: 0x180438b75 -> 0x180473ae8 */
/* cut edge: 0x180438b8b -> 0x180473ae8 */
/* cut edge: 0x180438d44 -> 0x180473ae8 */
/* cut edge: 0x180438e39 -> 0x180473ae8 */
/* cut edge: 0x180438e4f -> 0x180473ae8 */
/* cut edge: 0x180439017 -> 0x180473ae8 */
/* cut edge: 0x180439126 -> 0x180439fa0 */
/* cut edge: 0x180439135 -> 0x18043abc0 */
/* cut edge: 0x180439144 -> 0x180473ae8 */
/* cut edge: 0x18043915a -> 0x180473ae8 */
/* cut edge: 0x18043930f -> 0x180473ae8 */
/* cut edge: 0x180439404 -> 0x180473ae8 */
/* cut edge: 0x180439452 -> 0x180473ae8 */
/* cut edge: 0x180439468 -> 0x180473ae8 */
/* cut edge: 0x18043947e -> 0x18043961a */
/* cut edge: 0x1804394ce -> 0x18043961a */
/* target 0x18043961a original byte 0x48 replaced by RET */
/* target 0x180439fa0 original byte 0x48 replaced by RET */
/* target 0x18043abc0 original byte 0x4c replaced by RET */
/* target 0x180447c7b original byte 0x31 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


ulonglong region_00093_180438593(void)

{
  code *pcVar1;
  code *pcVar2;
  byte bVar3;
  ulonglong uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  longlong lVar10;
  ulonglong unaff_R14;
  longlong unaff_R15;
  ulonglong uVar11;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000050;
  ulonglong in_stack_00000058;
  code *in_stack_00000060;
  code *in_stack_00000068;
  undefined4 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  code *in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  code *in_stack_000000d0;
  undefined8 in_stack_000000d8;
  code *in_stack_000000e0;
  code *in_stack_00000108;
  code *in_stack_00000110;
  longlong in_stack_00000118;
  int in_stack_00000130;
  undefined4 in_stack_000001a8;
  undefined8 in_stack_000001d0;
  ulonglong in_stack_00002f98;
  short in_stack_00004280;
  
  uVar4 = (ulonglong)in_stack_00000068 ^ (ulonglong)in_stack_00000060;
  in_stack_000000b0 = (code *)((longlong)in_stack_000000b0 + uVar4);
  uVar11 = unaff_R15 + (unaff_R14 ^ in_stack_00000058);
  if (0 < in_stack_00000130) {
    if (in_stack_00000118 == 0) {
      return uVar4;
    }
    in_stack_00000058 = uVar11;
    in_stack_00000060 = in_stack_000000b0;
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (longlong)in_stack_00000058 % 0x6b9b1 ^ in_stack_000000c8;
    if (DAT_1804b4218 == 0) {
      lVar10 = *in_stack_00000048;
      in_stack_00000068 = *(code **)(lVar10 + 0x390);
      in_stack_000000b0 = (code *)FUN_1803335f0(in_stack_00000048);
      in_stack_000000e0 = DAT_1804b59f0;
      in_stack_00000110 =
           (code *)(**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000448,1);
      lVar10 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar10 + 0x390);
      uVar5 = FUN_1800024f0(in_stack_00000048);
      uVar6 = FUN_180002550(in_stack_00000048);
      uVar7 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00001680,5);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
      uVar8 = FUN_1800025a0(in_stack_00000048);
      uVar9 = in_stack_00000088;
      uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8);
      (*pcVar1)(in_stack_00000048,uVar5,uVar6,uVar7,uVar8);
      uVar9 = (*in_stack_00000068)
                        (in_stack_00000048,uVar9,in_stack_000000b0,in_stack_000000e0,
                         in_stack_00000110);
      DAT_1804b4218 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    in_stack_00000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b4218,in_stack_000000a0);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x7566);
    (*pcVar1)(in_stack_00000048,uVar9,0,uVar5);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       uVar4 ^ 0x44ca64f432da54a5);
    (*pcVar1)(in_stack_00000048,uVar9,1,uVar5);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,in_stack_00000050,in_stack_000000a8,uVar9);
    in_stack_00000068 =
         (code *)(**(code **)(*in_stack_00000048 + 0x1a0))
                           (in_stack_00000048,uVar9,in_stack_000000d8);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (longlong)in_stack_00000058 % 0xf5a8 ^ in_stack_000000c8;
    if (DAT_1804b4220 == 0) {
      lVar10 = *in_stack_00000048;
      in_stack_000000b0 = *(code **)(lVar10 + 0x390);
      in_stack_000000e0 = (code *)FUN_1803335f0(in_stack_00000048);
      in_stack_00000110 = DAT_1804b59f0;
      in_stack_00000108 =
           (code *)(**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000446,1);
      lVar10 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar10 + 0x390);
      uVar5 = FUN_1800024f0(in_stack_00000048);
      uVar6 = FUN_180002550(in_stack_00000048);
      uVar7 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00001670,5);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
      uVar8 = FUN_1800025a0(in_stack_00000048);
      uVar9 = in_stack_00000088;
      uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8);
      (*pcVar1)(in_stack_00000048,uVar5,uVar6,uVar7,uVar8);
      uVar9 = (*in_stack_000000b0)
                        (in_stack_00000048,uVar9,in_stack_000000e0,in_stack_00000110,
                         in_stack_00000108);
      DAT_1804b4220 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    in_stack_00000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b4220,in_stack_000000a0);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1e25);
    (*pcVar1)(in_stack_00000048,uVar9,0,uVar5);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       uVar4 ^ 0x2d75e2bee3052967);
    (*pcVar1)(in_stack_00000048,uVar9,1,uVar5);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,in_stack_00000050,in_stack_000000a8,uVar9);
    in_stack_000000b0 =
         (code *)(**(code **)(*in_stack_00000048 + 0x1a0))
                           (in_stack_00000048,uVar9,in_stack_000000d8);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (longlong)in_stack_00000058 % 0xf0276 ^ in_stack_000000c8;
    if (DAT_1804b4228 == 0) {
      lVar10 = *in_stack_00000048;
      in_stack_000000e0 = *(code **)(lVar10 + 0x390);
      in_stack_00000110 = (code *)FUN_1803335f0(in_stack_00000048);
      in_stack_00000108 = DAT_1804b59f0;
      in_stack_00000078 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000444,1);
      lVar10 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar10 + 0x390);
      uVar5 = FUN_1800024f0(in_stack_00000048);
      uVar6 = FUN_180002550(in_stack_00000048);
      uVar7 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00001660,5);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
      uVar8 = FUN_1800025a0(in_stack_00000048);
      uVar9 = in_stack_00000088;
      uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8);
      (*pcVar1)(in_stack_00000048,uVar5,uVar6,uVar7,uVar8);
      uVar9 = (*in_stack_000000e0)
                        (in_stack_00000048,uVar9,in_stack_00000110,in_stack_00000108,
                         in_stack_00000078);
      DAT_1804b4228 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    in_stack_00000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b4228,in_stack_000000a0);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x67ec);
    (*pcVar1)(in_stack_00000048,uVar9,0,uVar5);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       uVar4 ^ 0xd7f45710801b530);
    (*pcVar1)(in_stack_00000048,uVar9,1,uVar5);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,in_stack_00000050,in_stack_000000a8,uVar9);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (longlong)in_stack_00000058 % 0x3ff2b ^ in_stack_000000c8;
    if (DAT_1804b4230 == 0) {
      lVar10 = *in_stack_00000048;
      in_stack_000000e0 = *(code **)(lVar10 + 0x390);
      in_stack_00000110 = (code *)FUN_1803335f0(in_stack_00000048);
      in_stack_00000108 = DAT_1804b59f0;
      in_stack_00000078 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000442,1);
      lVar10 = *in_stack_00000048;
      in_stack_000000d0 = *(code **)(lVar10 + 0x390);
      uVar5 = FUN_1800024f0(in_stack_00000048);
      uVar6 = FUN_180002550(in_stack_00000048);
      uVar7 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00001650,5);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
      uVar8 = FUN_1800025a0(in_stack_00000048);
      uVar9 = in_stack_00000088;
      uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar8);
      (*in_stack_000000d0)(in_stack_00000048,uVar5,uVar6,uVar7,uVar8);
      uVar9 = (*in_stack_000000e0)
                        (in_stack_00000048,uVar9,in_stack_00000110,in_stack_00000108,
                         in_stack_00000078);
      DAT_1804b4230 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    in_stack_00000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b4230,in_stack_000000a0);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x6d31);
    (*pcVar1)(in_stack_00000048,uVar9,0,uVar5);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       uVar4 ^ 0x5b7e9f3a9319509d);
    (*pcVar1)(in_stack_00000048,uVar9,1,uVar5);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,in_stack_00000050,in_stack_000000a8,uVar9);
    lVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar4 != '\0') {
      return uVar4;
    }
    uVar4 = (ulonglong)in_stack_00000068 ^ (ulonglong)in_stack_00000060;
    in_stack_000000b0 = (code *)((longlong)in_stack_000000b0 + uVar4);
    uVar11 = lVar10 + (uVar11 ^ in_stack_00000058);
  }
  if (in_stack_00004280 == 0) {
    return uVar4;
  }
  if (in_stack_00000118 != 0) {
    uVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (((char)uVar4 == '\0') &&
       (uVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar4 == '\0')) {
      uVar4 = (longlong)uVar11 % 0xd1bfa ^ in_stack_000000c8;
      in_stack_00000060 = (code *)uVar11;
      if (DAT_1804b4238 == 0) {
        lVar10 = *in_stack_00000048;
        in_stack_00000068 = *(code **)(lVar10 + 0x390);
        in_stack_00000058 = FUN_1803335f0(in_stack_00000048);
        in_stack_000000e0 = DAT_1804b59f0;
        in_stack_00000110 =
             (code *)(**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000440,1);
        lVar10 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar10 + 0x390);
        uVar5 = FUN_1800024f0(in_stack_00000048);
        uVar6 = FUN_180002550(in_stack_00000048);
        uVar7 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00001640,5);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
        uVar8 = FUN_1800025a0(in_stack_00000048);
        uVar9 = in_stack_00000088;
        uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8);
        (*pcVar1)(in_stack_00000048,uVar5,uVar6,uVar7,uVar8);
        uVar9 = (*in_stack_00000068)
                          (in_stack_00000048,uVar9,in_stack_00000058,in_stack_000000e0,
                           in_stack_00000110);
        DAT_1804b4238 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      }
      in_stack_00000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b4238,in_stack_000000a0);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5f01);
      (*pcVar1)(in_stack_00000048,uVar9,0,uVar5);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         uVar4 ^ in_stack_00002f98);
      (*pcVar1)(in_stack_00000048,uVar9,1,uVar5);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,in_stack_00000050,in_stack_000000a8,uVar9);
      bVar3 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
      uVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)uVar4 == '\0') {
        in_stack_00000050 = CONCAT71(in_stack_00000050._1_7_,bVar3 & (byte)in_stack_00000070);
        (**(code **)(*in_stack_00000048 + 0x680))
                  (in_stack_00000048,in_stack_000001d0,in_stack_000001a8,1,&stack0x00000050);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((((char)uVar4 == '\0') &&
            (uVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar4 == '\0')) &&
           (uVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar4 == '\0')) {
          uVar4 = 0xd470baaacc7b6903;
          if (DAT_1804b4240 == 0) {
            lVar10 = *in_stack_00000048;
            in_stack_00000068 = *(code **)(lVar10 + 0x390);
            in_stack_00000058 = FUN_1803335f0(in_stack_00000048);
            in_stack_000000e0 = DAT_1804b59f0;
            in_stack_00000110 =
                 (code *)(**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x0000043e,1);
            lVar10 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar10 + 0x390);
            uVar5 = FUN_1800024f0(in_stack_00000048);
            uVar6 = FUN_180002550(in_stack_00000048);
            uVar7 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00001630,5);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
            uVar8 = FUN_1800025a0(in_stack_00000048);
            uVar9 = in_stack_00000088;
            uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8);
            (*pcVar1)(in_stack_00000048,uVar5,uVar6,uVar7,uVar8);
            uVar9 = (*in_stack_00000068)
                              (in_stack_00000048,uVar9,in_stack_00000058,in_stack_000000e0,
                               in_stack_00000110);
            DAT_1804b4240 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
            uVar4 = (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
          }
          return uVar4;
        }
      }
    }
    return uVar4;
  }
  return uVar4;
}

