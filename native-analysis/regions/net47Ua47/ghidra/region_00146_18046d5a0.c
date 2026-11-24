/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=146 entry=18046d5a0 blocks=40 maxInstructions=50000 */

/* cut edge: 0x18046d5a0 -> 0x180473db5 */
/* cut edge: 0x18046d5c4 -> 0x180473ae8 */
/* cut edge: 0x18046d63e -> 0x18046ed56 */
/* cut edge: 0x18046d64e -> 0x180473ae8 */
/* cut edge: 0x18046d68c -> 0x18046f8ab */
/* cut edge: 0x18046d694 -> 0x180473ae8 */
/* cut edge: 0x18046d6aa -> 0x180473ae8 */
/* cut edge: 0x18046d86f -> 0x180473ae8 */
/* cut edge: 0x18046d961 -> 0x180473ae8 */
/* cut edge: 0x18046d977 -> 0x180473ae8 */
/* cut edge: 0x18046db3c -> 0x180473ae8 */
/* cut edge: 0x18046dc2e -> 0x180473ae8 */
/* cut edge: 0x18046dc44 -> 0x180473ae8 */
/* cut edge: 0x18046de0c -> 0x180473ae8 */
/* cut edge: 0x18046df01 -> 0x180473ae8 */
/* cut edge: 0x18046df17 -> 0x180473ae8 */
/* cut edge: 0x18046e0e7 -> 0x180473ae8 */
/* cut edge: 0x18046e1dc -> 0x18046f8ab */
/* cut edge: 0x18046e1f7 -> 0x1804727ab */
/* cut edge: 0x18046e1fc -> 0x180473ae8 */
/* cut edge: 0x18046e212 -> 0x180473ae8 */
/* cut edge: 0x18046e3d7 -> 0x180473ae8 */
/* cut edge: 0x18046e4c9 -> 0x180473ae8 */
/* cut edge: 0x18046e4df -> 0x180473ae8 */
/* cut edge: 0x18046e4f5 -> 0x18046e6a4 */
/* cut edge: 0x18046e4f5 -> 0x18046e547 */
/* target 0x18046e547 original byte 0x4c replaced by RET */
/* target 0x18046e6a4 original byte 0x48 replaced by RET */
/* target 0x18046ed56 original byte 0x48 replaced by RET */
/* target 0x18046f8ab original byte 0x48 replaced by RET */
/* target 0x1804727ab original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180473db5 original byte 0x48 replaced by RET */


ulonglong region_00146_18046d5a0(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  ulonglong uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000048;
  longlong lStack0000000000000058;
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
  char in_stack_000000d0;
  undefined8 in_stack_000000d8;
  char in_stack_00000118;
  int in_stack_00000130;
  undefined8 in_stack_00000148;
  longlong in_stack_00000158;
  
  lStack0000000000000058 = DAT_1804b52d8;
  if (DAT_1804b52d8 == 0) {
    return 0;
  }
  lVar4 = (**(code **)(*in_stack_00000048 + 0x420))
                    (in_stack_00000048,in_stack_00000148,DAT_1804b52d8);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)uVar5 == '\0') {
    uVar5 = (ulonglong)(lVar4 < in_stack_00000158);
    if (in_stack_00000158 < lVar4) {
      uVar5 = 0xffffffff;
    }
    if (-1 < in_stack_00000130 && in_stack_000000d0 == '\0') {
      if (lVar4 <= in_stack_00000158) {
        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)uVar5 != '\0') {
          return uVar5;
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)uVar5 != '\0') {
          return uVar5;
        }
        if (DAT_1804b4818 == 0) {
          lVar4 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar4 + 0x390);
          uVar6 = FUN_1803335f0(in_stack_00000048);
          uVar12 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x000002c4,1);
          lVar4 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar4 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000dd0,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
          DAT_1804b4818 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b4818,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x71d7);
        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0xab18f ^ in_stack_000000c8 ^ 0x9cb184d815c60fd3);
        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)uVar5 != '\0') {
          return uVar5;
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)uVar5 != '\0') {
          return uVar5;
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)uVar5 != '\0') {
          return uVar5;
        }
        uVar5 = (in_stack_00000068 / 0xdd927) * 0xdd927;
        if (DAT_1804b4820 != 0) {
          return uVar5;
        }
        return uVar5;
      }
      uVar5 = (ulonglong)(in_stack_00000158 != 0);
      if (in_stack_00000158 < 0) {
        uVar5 = 0xffffffff;
      }
    }
    if (in_stack_00000118 != '\0') {
      if ((int)uVar5 < 1) {
        return uVar5;
      }
      lVar4 = (**(code **)(*in_stack_00000048 + 0x420))
                        (in_stack_00000048,in_stack_00000148,lStack0000000000000058);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)uVar5 != '\0') {
        return uVar5;
      }
      uVar5 = (ulonglong)(lVar4 != 0);
      if (lVar4 < 0) {
        uVar5 = 0xffffffff;
      }
    }
    if ((int)uVar5 < 1) {
      return uVar5;
    }
    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (((char)uVar5 == '\0') &&
       (uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar5 == '\0')) {
      if (DAT_1804b4858 == 0) {
        lVar4 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar4 + 0x390);
        uVar6 = FUN_1803335f0(in_stack_00000048);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x000002b4,1);
        lVar4 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar4 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000d50,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
        DAT_1804b4858 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b4858,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4bfb);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         in_stack_00000068 % 0x60035 ^ in_stack_000000c8 ^ 0xf7972e12d3a829cf);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((((char)uVar5 == '\0') &&
          (uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar5 == '\0')) &&
         (uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar5 == '\0')) {
        if (DAT_1804b4860 == 0) {
          lVar4 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar4 + 0x390);
          uVar6 = FUN_1803335f0(in_stack_00000048);
          uVar12 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x000002b2,1);
          lVar4 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar4 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000d40,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
          DAT_1804b4860 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b4860,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0xc1a);
        (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0x84993 ^ in_stack_000000c8 ^ 0x9fb2abeec371e92b);
        (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((((char)uVar5 == '\0') &&
            (uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar5 == '\0')) &&
           (uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar5 == '\0')) {
          if (DAT_1804b4868 == 0) {
            lVar4 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar4 + 0x390);
            uVar6 = FUN_1803335f0(in_stack_00000048);
            uVar12 = DAT_1804b59f0;
            uVar7 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x000002b0,1);
            lVar4 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar4 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000d30,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
            (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
            DAT_1804b4868 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b4868,in_stack_000000a0);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x49d4);
          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                             in_stack_00000068 % 0x58004 ^ in_stack_000000c8 ^ 0xc3ca258aef2e2b23);
          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
          (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
          uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((((char)uVar5 == '\0') &&
              (uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar5 == '\0')) &&
             (uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar5 == '\0')) {
            if (DAT_1804b4870 == 0) {
              lVar4 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar4 + 0x390);
              uVar6 = FUN_1803335f0(in_stack_00000048);
              uVar12 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x000002ae,1);
              lVar4 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar4 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000d20,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar11);
              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
              DAT_1804b4870 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b4870,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x162d);
            (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_00000068 % 0x498bf ^ in_stack_000000c8 ^ 0xe0fcc34a3cccb07a)
            ;
            (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)uVar5 == '\0') {
              if (in_stack_00000118 == '\0') {
                return uVar5;
              }
              return uVar5;
            }
          }
        }
      }
    }
  }
  return uVar5;
}

