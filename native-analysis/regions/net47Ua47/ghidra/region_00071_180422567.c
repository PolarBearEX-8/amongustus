/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=71 entry=180422567 blocks=52 maxInstructions=50000 */

/* cut edge: 0x180422567 -> 0x18042d170 */
/* cut edge: 0x18042257f -> 0x18042d1aa */
/* cut edge: 0x18042265c -> 0x180473ae8 */
/* cut edge: 0x18042276f -> 0x180473ae8 */
/* cut edge: 0x180422785 -> 0x180473ae8 */
/* cut edge: 0x1804227ed -> 0x18043543f */
/* cut edge: 0x180422811 -> 0x180435472 */
/* cut edge: 0x180422872 -> 0x1804354c3 */
/* cut edge: 0x180422882 -> 0x1804354fc */
/* cut edge: 0x18042295b -> 0x180473ae8 */
/* cut edge: 0x180422a50 -> 0x180473ae8 */
/* cut edge: 0x180422a66 -> 0x180473ae8 */
/* cut edge: 0x180422ad6 -> 0x18043580f */
/* cut edge: 0x180422afa -> 0x180435845 */
/* cut edge: 0x180422b53 -> 0x180435896 */
/* cut edge: 0x180422b6e -> 0x1804358cf */
/* cut edge: 0x180422c49 -> 0x180473ae8 */
/* cut edge: 0x180422d3e -> 0x180473ae8 */
/* cut edge: 0x180422d54 -> 0x180473ae8 */
/* cut edge: 0x180422dbf -> 0x180435be4 */
/* cut edge: 0x180422de3 -> 0x180435c1a */
/* cut edge: 0x180422e3c -> 0x180435c6b */
/* cut edge: 0x180422e57 -> 0x180435ca4 */
/* cut edge: 0x180422f32 -> 0x180473ae8 */
/* cut edge: 0x180423029 -> 0x180473ae8 */
/* cut edge: 0x18042303f -> 0x180473ae8 */
/* cut edge: 0x1804230ac -> 0x180435ec8 */
/* cut edge: 0x1804230d0 -> 0x180435efe */
/* cut edge: 0x180423129 -> 0x180435f4f */
/* cut edge: 0x180423144 -> 0x180435f88 */
/* cut edge: 0x18042321f -> 0x180473ae8 */
/* cut edge: 0x180423314 -> 0x18042a7a9 */
/* cut edge: 0x180423328 -> 0x180473ae8 */
/* cut edge: 0x18042333e -> 0x180473ae8 */
/* cut edge: 0x1804233a6 -> 0x180433d30 */
/* cut edge: 0x1804233ca -> 0x180433d63 */
/* cut edge: 0x18042342e -> 0x180433db4 */
/* cut edge: 0x180423443 -> 0x180433ded */
/* cut edge: 0x180423523 -> 0x180473ae8 */
/* cut edge: 0x180423523 -> 0x18042361d */
/* target 0x18042361d original byte 0x48 replaced by RET */
/* target 0x18042a7a9 original byte 0x48 replaced by RET */
/* target 0x18042d170 original byte 0x48 replaced by RET */
/* target 0x18042d1aa original byte 0x48 replaced by RET */
/* target 0x180433d30 original byte 0x48 replaced by RET */
/* target 0x180433d63 original byte 0x48 replaced by RET */
/* target 0x180433db4 original byte 0x48 replaced by RET */
/* target 0x180433ded original byte 0x48 replaced by RET */
/* target 0x18043543f original byte 0x48 replaced by RET */
/* target 0x180435472 original byte 0x48 replaced by RET */
/* target 0x1804354c3 original byte 0x48 replaced by RET */
/* target 0x1804354fc original byte 0x48 replaced by RET */
/* target 0x18043580f original byte 0x48 replaced by RET */
/* target 0x180435845 original byte 0x48 replaced by RET */
/* target 0x180435896 original byte 0x48 replaced by RET */
/* target 0x1804358cf original byte 0x48 replaced by RET */
/* target 0x180435be4 original byte 0x48 replaced by RET */
/* target 0x180435c1a original byte 0x48 replaced by RET */
/* target 0x180435c6b original byte 0x48 replaced by RET */
/* target 0x180435ca4 original byte 0x48 replaced by RET */
/* target 0x180435ec8 original byte 0x48 replaced by RET */
/* target 0x180435efe original byte 0x48 replaced by RET */
/* target 0x180435f4f original byte 0x48 replaced by RET */
/* target 0x180435f88 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00071_180422567(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  uint uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  ulonglong uVar10;
  longlong lVar11;
  undefined8 uVar12;
  longlong unaff_RSI;
  ushort unaff_R13W;
  longlong *in_stack_00000048;
  code *in_stack_00000058;
  undefined8 in_stack_00000060;
  ulonglong in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  code *in_stack_000000d0;
  undefined8 in_stack_000000d8;
  longlong in_stack_00000118;
  undefined8 in_stack_00000120;
  undefined8 uStack0000000000000128;
  
  lVar11 = DAT_1804b4d38;
  if (DAT_1804b4d38 == 0) {
    return;
  }
  uStack0000000000000128 = param_2;
  uVar7 = (**(code **)(unaff_RSI + 0x518))(in_stack_00000048,&stack0x00002060,5);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
  (*in_stack_000000d0)(in_stack_00000048,uStack0000000000000128,lVar11,uVar7,uVar8);
  uVar7 = (*in_stack_00000058)
                    (in_stack_00000048,in_stack_00000088,in_stack_00000060,in_stack_00000070,
                     in_stack_00000078);
  DAT_1804b3d20 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804b3d20,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x6b5);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8)
  ;
  uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000120);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  if (uVar6 == unaff_R13W) {
    in_stack_00000058 = (code *)in_stack_00000068;
  }
  else {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b5a08;
    uVar7 = DAT_1804b59f0;
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b3d28 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000584,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002050,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar12);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar7,uVar8);
      DAT_1804b3d28 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b3d28,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x592b);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0x51e57 ^ in_stack_000000c8 ^
                       0xd679126dcf422520);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b5a08;
    uVar7 = DAT_1804b59f0;
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b3d30 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000582,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002040,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar12);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar7,uVar8);
      DAT_1804b3d30 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b3d30,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x7c95);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0x9a0db ^ in_stack_000000c8 ^
                       0xde8dbbabbb6681a3);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b5a08;
    uVar7 = DAT_1804b59f0;
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b3d38 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000580,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002030,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar12);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar7,uVar8);
      DAT_1804b3d38 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b3d38,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5702);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0xa99ad ^ in_stack_000000c8 ^
                       0xeafc6450a3981c3f);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b5a08;
    uVar7 = DAT_1804b59f0;
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b3d40 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000057e,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002020,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar12);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar7,uVar8);
      DAT_1804b3d40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b3d40,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2d5f);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0x714d9 ^ in_stack_000000c8 ^
                       0xb5e74c6bf0fcb43e);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
    lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    in_stack_00000058 = (code *)((uVar10 ^ in_stack_00000068) - lVar11);
    if (in_stack_00000118 == 0) {
      return;
    }
  }
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((cVar5 == '\0') &&
     (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), lVar11 = DAT_1804b5a08,
     uVar7 = DAT_1804b59f0, cVar5 == '\0')) {
    if (DAT_1804b3d48 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000057c,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002010,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar12);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar7,uVar8);
      DAT_1804b3d48 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b3d48,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5b13);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000058 % 0x855f7 ^ in_stack_000000c8 ^
                       0xeccb976902ece04a);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      return;
    }
  }
  return;
}

