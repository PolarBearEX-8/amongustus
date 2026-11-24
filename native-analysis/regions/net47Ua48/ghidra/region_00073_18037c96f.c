/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=73 entry=18037c96f blocks=32 maxInstructions=50000 */

/* cut edge: 0x18037c96f -> 0x1803351d6 */
/* cut edge: 0x18037ca77 -> 0x1803351d6 */
/* cut edge: 0x18037cbd1 -> 0x1803351d6 */
/* cut edge: 0x18037ccbf -> 0x1803351d6 */
/* cut edge: 0x18037ce39 -> 0x1803351d6 */
/* cut edge: 0x18037cf37 -> 0x1803351d6 */
/* cut edge: 0x18037d091 -> 0x1803351d6 */
/* cut edge: 0x18037d187 -> 0x1803351d6 */
/* cut edge: 0x18037d1f4 -> 0x1803351d6 */
/* cut edge: 0x18037d261 -> 0x1803351d6 */
/* cut edge: 0x18037d2d4 -> 0x1803351d6 */
/* cut edge: 0x18037d32e -> 0x180378360 */
/* cut edge: 0x18037d344 -> 0x1803351d6 */
/* cut edge: 0x18037d4ac -> 0x1803351d6 */
/* cut edge: 0x18037d59f -> 0x1803351d6 */
/* cut edge: 0x18037d735 -> 0x1803351d6 */
/* cut edge: 0x18037d822 -> 0x18037d929 */
/* cut edge: 0x18037d82e -> 0x1803351d6 */
/* cut edge: 0x18037d82e -> 0x18037d877 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180378360 original byte 0x48 replaced by RET */
/* target 0x18037d877 original byte 0x48 replaced by RET */
/* target 0x18037d929 original byte 0x8b replaced by RET */


void region_00073_18037c96f(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  char cVar4;
  uint uVar5;
  uint uVar6;
  int iVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong *in_stack_00000048;
  uint uStack0000000000000050;
  undefined4 uStack0000000000000054;
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
  undefined8 in_stack_000000a8;
  code *in_stack_000000b0;
  uint uStack00000000000000b8;
  uint uStack00000000000000c0;
  undefined8 in_stack_000000d0;
  uint uStack00000000000000d8;
  undefined8 in_stack_000000e0;
  uint in_stack_000000f0;
  uint in_stack_000000f8;
  uint uStack0000000000000100;
  undefined8 in_stack_00000110;
  code *in_stack_00000118;
  uint in_stack_00000128;
  uint in_stack_00000138;
  uint in_stack_00000148;
  uint in_stack_00000150;
  undefined8 in_stack_00000160;
  uint in_stack_00000178;
  uint in_stack_00000180;
  undefined8 in_stack_00000188;
  undefined4 in_stack_00000190;
  undefined4 in_stack_00000198;
  undefined4 in_stack_000001a8;
  int in_stack_00000830;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 != '\0') {
    return;
  }
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((uStack00000000000000c0 & uVar5) == 0) {
    if (cVar4 != '\0') {
      return;
    }
    if (DAT_1804b2a08 == 0) {
      lVar2 = *in_stack_00000048;
      in_stack_00000118 = *(code **)(lVar2 + 0x390);
      in_stack_00000160 = FUN_180333550(in_stack_00000048);
      in_stack_00000188 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000056c,1);
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ed8,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar8 = in_stack_00000058;
      uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar8 = (*in_stack_00000118)
                        (in_stack_00000048,uVar8,in_stack_00000160,in_stack_00000188,uVar9);
      DAT_1804b2a08 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
    }
    _uStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2a08,in_stack_00000078);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 != '\0') {
      return;
    }
    uVar5 = uVar5 & in_stack_00000150;
  }
  else {
    if (cVar4 != '\0') {
      return;
    }
    if (DAT_1804b29f8 == 0) {
      lVar2 = *in_stack_00000048;
      in_stack_00000118 = *(code **)(lVar2 + 0x390);
      in_stack_00000160 = FUN_180333550(in_stack_00000048);
      in_stack_00000188 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000570,1);
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ef8,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar8 = in_stack_00000058;
      uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar8 = (*in_stack_00000118)
                        (in_stack_00000048,uVar8,in_stack_00000160,in_stack_00000188,uVar9);
      DAT_1804b29f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
    }
    _uStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b29f8,in_stack_00000078);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 != '\0') {
      return;
    }
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 != '\0') {
      return;
    }
    if (DAT_1804b2a00 == 0) {
      lVar2 = *in_stack_00000048;
      in_stack_00000118 = *(code **)(lVar2 + 0x390);
      in_stack_00000160 = FUN_180333550(in_stack_00000048);
      in_stack_00000188 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000056e,1);
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ee8,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar8 = in_stack_00000058;
      uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar8 = (*in_stack_00000118)
                        (in_stack_00000048,uVar8,in_stack_00000160,in_stack_00000188,uVar9);
      DAT_1804b2a00 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
    }
    _uStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2a00,in_stack_00000078);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 != '\0') {
      return;
    }
    uVar5 = uVar5 & (uVar6 ^ in_stack_00000150);
  }
  uStack0000000000000050 =
       (uint)_uStack00000000000000b8 ^ uStack00000000000000d8 ^ uStack00000000000000c0 ^
       in_stack_00000128 ^ in_stack_00000138;
  (**(code **)(*in_stack_00000048 + 0x698))
            (in_stack_00000048,in_stack_000000a0,in_stack_000000b0,1,&stack0x00000050);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    uStack0000000000000050 =
         (uint)_uStack00000000000000b8 ^ uStack0000000000000100 ^ uStack00000000000000c0 ^
         in_stack_00000148 ^ in_stack_00000180;
    (**(code **)(*in_stack_00000048 + 0x698))
              (in_stack_00000048,in_stack_000000a0,in_stack_000001a8,1,&stack0x00000050);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 == '\0') {
      uVar6 = (uint)_uStack00000000000000d8 ^ uStack0000000000000100;
      uStack0000000000000050 =
           (uint)_uStack00000000000000c0 ^ uVar6 ^ in_stack_00000178 ^ in_stack_000000f0;
      (**(code **)(*in_stack_00000048 + 0x698))
                (in_stack_00000048,in_stack_000000a0,in_stack_00000190,1,&stack0x00000050);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        _uStack0000000000000050 =
             CONCAT44(uStack0000000000000054,
                      uVar6 ^ uStack00000000000000b8 ^ in_stack_000000f8 ^ uVar5);
        (**(code **)(*in_stack_00000048 + 0x698))
                  (in_stack_00000048,in_stack_000000a0,in_stack_00000198,1,&stack0x00000050);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          if ((int)in_stack_000000b0 != 0xc) {
            return;
          }
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 == '\0') {
            if (DAT_1804b2a10 == 0) {
              lVar2 = *in_stack_00000048;
              in_stack_000000b0 = *(code **)(lVar2 + 0x390);
              _uStack00000000000000b8 = (code *)FUN_180333550(in_stack_00000048);
              _uStack00000000000000c0 = DAT_1804b59f0;
              uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000056a,1);
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar10 = FUN_1800024f0(in_stack_00000048);
              uVar11 = FUN_180002550(in_stack_00000048);
              uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ec8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar13 = FUN_1800025a0(in_stack_00000048);
              uVar8 = in_stack_00000058;
              uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
              (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
              uVar8 = (*in_stack_000000b0)
                                (in_stack_00000048,uVar8,_uStack00000000000000b8,
                                 _uStack00000000000000c0,uVar9);
              DAT_1804b2a10 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2a10,in_stack_00000078);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
            (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000000e0);
            (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
            in_stack_000000b0 =
                 (code *)(**(code **)(*in_stack_00000048 + 0x188))
                                   (in_stack_00000048,uVar8,in_stack_00000070);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((cVar4 == '\0') &&
               (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
              in_stack_000000b0 =
                   (code *)(ulonglong)(uint)((int)in_stack_000000b0 * in_stack_00000830);
              if (DAT_1804b2a18 == 0) {
                lVar2 = *in_stack_00000048;
                _uStack00000000000000b8 = *(code **)(lVar2 + 0x390);
                _uStack00000000000000c0 = FUN_180333550(in_stack_00000048);
                _uStack00000000000000d8 = DAT_1804b59f0;
                _uStack0000000000000100 =
                     (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000568,1);
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000eb8,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar8 = in_stack_00000058;
                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar8 = (*_uStack00000000000000b8)
                                  (in_stack_00000048,uVar8,_uStack00000000000000c0,
                                   _uStack00000000000000d8,_uStack0000000000000100);
                DAT_1804b2a18 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
              }
              _uStack0000000000000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2a18,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000e0);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
              iVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar8,in_stack_00000070);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                if (iVar7 < 1) {
                  return;
                }
                _uStack0000000000000050 = _uStack0000000000000050 & 0xffffffff00000000;
                (**(code **)(*in_stack_00000048 + 0x658))
                          (in_stack_00000048,in_stack_000000a0,0,1,&stack0x00000050);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar4 == '\0') {
                  return;
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

