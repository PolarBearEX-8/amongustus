/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=86 entry=1803898c4 blocks=41 maxInstructions=50000 */

/* cut edge: 0x1803898c4 -> 0x1803351d6 */
/* cut edge: 0x1803899c8 -> 0x1803351d6 */
/* cut edge: 0x180389b2f -> 0x1803351d6 */
/* cut edge: 0x180389c20 -> 0x1803351d6 */
/* cut edge: 0x180389c6c -> 0x1803351d6 */
/* cut edge: 0x180389de1 -> 0x1803351d6 */
/* cut edge: 0x180389ece -> 0x180389766 */
/* cut edge: 0x180389ef6 -> 0x1803351d6 */
/* cut edge: 0x180389efb -> 0x1803351d6 */
/* cut edge: 0x18038a06a -> 0x1803351d6 */
/* cut edge: 0x18038a168 -> 0x1803351d6 */
/* cut edge: 0x18038a1a3 -> 0x1803351d6 */
/* cut edge: 0x18038a1e3 -> 0x1803351d6 */
/* cut edge: 0x18038a241 -> 0x1803351d6 */
/* cut edge: 0x18038a246 -> 0x1803351d6 */
/* cut edge: 0x18038a3c0 -> 0x1803351d6 */
/* cut edge: 0x18038a4be -> 0x1803351d6 */
/* cut edge: 0x18038a4f9 -> 0x1803351d6 */
/* cut edge: 0x18038a539 -> 0x1803351d6 */
/* cut edge: 0x18038a597 -> 0x1803351d6 */
/* cut edge: 0x18038a59c -> 0x1803351d6 */
/* cut edge: 0x18038a5cd -> 0x1803351d6 */
/* cut edge: 0x18038a736 -> 0x1803351d6 */
/* cut edge: 0x18038a823 -> 0x18038ab50 */
/* cut edge: 0x18038a823 -> 0x18038a844 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180389766 original byte 0x48 replaced by RET */
/* target 0x18038a844 original byte 0x48 replaced by RET */
/* target 0x18038ab50 original byte 0x48 replaced by RET */


longlong region_00086_1803898c4(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  undefined1 uVar3;
  byte bVar4;
  int iVar5;
  int iVar6;
  undefined4 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  longlong lVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  int unaff_R13D;
  longlong *in_stack_00000048;
  undefined1 uStack0000000000000050;
  undefined7 uStack0000000000000051;
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
  int iStack00000000000000a8;
  undefined4 uStack00000000000000ac;
  undefined8 in_stack_000000b0;
  code *in_stack_000000c0;
  code *in_stack_000000d0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_000000f0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000110;
  undefined8 in_stack_00000140;
  undefined8 in_stack_00000158;
  undefined8 in_stack_00000168;
  undefined8 in_stack_000001c0;
  undefined8 in_stack_000001f8;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x9c2);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000b0);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
  iVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar10 != '\0') {
    return lVar10;
  }
  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (unaff_R13D < iVar5) {
    if ((char)lVar10 != '\0') {
      return lVar10;
    }
    iVar5 = 0;
    do {
      if (DAT_1804b2c60 == 0) {
        lVar10 = *in_stack_00000048;
        _iStack00000000000000a8 = *(code **)(lVar10 + 0x390);
        in_stack_000000d0 = (code *)FUN_180333550(in_stack_00000048);
        in_stack_000000b0 = DAT_1804b59f0;
        in_stack_000000c0 =
             (code *)(**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x000004d6,1);
        lVar10 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar10 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000ae8,5);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar8 = in_stack_00000058;
        uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13);
        (*pcVar1)(in_stack_00000048,uVar9,uVar11,uVar12,uVar13);
        uVar8 = (*_iStack00000000000000a8)
                          (in_stack_00000048,uVar8,in_stack_000000d0,in_stack_000000b0,
                           in_stack_000000c0);
        DAT_1804b2c60 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      _uStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2c60,in_stack_00000078);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
      iVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
      lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)lVar10 != '\0') {
        return lVar10;
      }
      if (iVar6 <= iVar5) {
        lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)lVar10 != '\0') {
          return lVar10;
        }
        iVar5 = 0;
        do {
          if (DAT_1804b2c68 == 0) {
            lVar10 = *in_stack_00000048;
            _iStack00000000000000a8 = *(code **)(lVar10 + 0x390);
            in_stack_000000d0 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_000000b0 = DAT_1804b59f0;
            in_stack_000000c0 =
                 (code *)(**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x000004d4,1);
            lVar10 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar10 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000ad8,5);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar8 = in_stack_00000058;
            uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar1)(in_stack_00000048,uVar9,uVar11,uVar12,uVar13);
            uVar8 = (*_iStack00000000000000a8)
                              (in_stack_00000048,uVar8,in_stack_000000d0,in_stack_000000b0,
                               in_stack_000000c0);
            DAT_1804b2c68 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2c68,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
          iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar8,in_stack_00000070);
          lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)lVar10 != '\0') {
            return lVar10;
          }
          if (iVar6 <= iVar5) {
            uVar7 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_000000a0);
            _iStack00000000000000a8 = (code *)CONCAT44(uStack00000000000000ac,uVar7);
            lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)lVar10 != '\0') {
              return lVar10;
            }
            lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)lVar10 != '\0') {
              return lVar10;
            }
            if (DAT_1804b2c70 == 0) {
              lVar10 = *in_stack_00000048;
              in_stack_000000d0 = *(code **)(lVar10 + 0x390);
              in_stack_000000e0 = FUN_180333550(in_stack_00000048);
              in_stack_000000b0 = DAT_1804b59f0;
              uVar9 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x000004d2,1);
              lVar10 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar10 + 0x390);
              uVar11 = FUN_1800024f0(in_stack_00000048);
              uVar12 = FUN_180002550(in_stack_00000048);
              uVar13 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000ac8,5);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
              uVar14 = FUN_1800025a0(in_stack_00000048);
              uVar8 = in_stack_00000058;
              uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar14);
              (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
              uVar8 = (*in_stack_000000d0)
                                (in_stack_00000048,uVar8,in_stack_000000e0,in_stack_000000b0,uVar9);
              DAT_1804b2c70 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2c70,in_stack_00000078);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x67b9);
            (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000001f8);
            (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
            iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar8,in_stack_00000070);
            lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)lVar10 != '\0') {
              return lVar10;
            }
            if ((int)in_stack_00000110 - iVar5 < iStack00000000000000a8) {
              return *in_stack_00000048;
            }
            return *in_stack_00000048;
          }
          pcVar1 = *(code **)(*in_stack_00000048 + 0x2f8);
          uVar8 = FUN_1803dac00(in_stack_00000048);
          uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000168,uVar8);
          lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)lVar10 != '\0') {
            return lVar10;
          }
          (**(code **)(*in_stack_00000048 + 0x640))
                    (in_stack_00000048,uVar8,iVar5,1,&stack0x00000050);
          uVar3 = uStack0000000000000050;
          lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)lVar10 != '\0') {
            return lVar10;
          }
          _uStack0000000000000050 = CONCAT71(uStack0000000000000051,uVar3);
          (**(code **)(*in_stack_00000048 + 0x680))
                    (in_stack_00000048,in_stack_00000100,iVar5,1,&stack0x00000050);
          lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)lVar10 != '\0') {
            return lVar10;
          }
          iVar5 = iVar5 + 1;
          lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
        } while ((char)lVar10 == '\0');
        return lVar10;
      }
      pcVar1 = *(code **)(*in_stack_00000048 + 0x2f8);
      uVar8 = FUN_1803dabb0(in_stack_00000048);
      uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000168,uVar8);
      lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)lVar10 != '\0') {
        return lVar10;
      }
      (**(code **)(*in_stack_00000048 + 0x640))(in_stack_00000048,uVar8,iVar5,1,&stack0x00000050);
      uVar3 = uStack0000000000000050;
      lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)lVar10 != '\0') {
        return lVar10;
      }
      _uStack0000000000000050 = CONCAT71(uStack0000000000000051,uVar3);
      (**(code **)(*in_stack_00000048 + 0x680))
                (in_stack_00000048,in_stack_000000d8,iVar5,1,&stack0x00000050);
      lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)lVar10 != '\0') {
        return lVar10;
      }
      iVar5 = iVar5 + 1;
      lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
    } while ((char)lVar10 == '\0');
    return lVar10;
  }
  if ((char)lVar10 != '\0') {
    return lVar10;
  }
  if (DAT_1804b2c50 == 0) {
    lVar10 = *in_stack_00000048;
    in_stack_000000c0 = *(code **)(lVar10 + 0x390);
    in_stack_00000140 = FUN_1803335f0(in_stack_00000048);
    in_stack_000000f0 = DAT_1804b59f0;
    in_stack_000000f8 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x000004da,1);
    lVar10 = *in_stack_00000048;
    pcVar1 = *(code **)(lVar10 + 0x390);
    uVar9 = FUN_1800024f0(in_stack_00000048);
    uVar11 = FUN_180002550(in_stack_00000048);
    uVar12 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000b08,5);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
    uVar13 = FUN_1800025a0(in_stack_00000048);
    uVar8 = in_stack_00000058;
    uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13);
    (*pcVar1)(in_stack_00000048,uVar9,uVar11,uVar12,uVar13);
    uVar8 = (*in_stack_000000c0)
                      (in_stack_00000048,uVar8,in_stack_00000140,in_stack_000000f0,in_stack_000000f8
                      );
    DAT_1804b2c50 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
  }
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2c50,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5f01);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
  bVar4 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000001c0);
  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar10 != '\0') {
    return lVar10;
  }
  _uStack0000000000000050 = CONCAT71(uStack0000000000000051,bVar4 & (byte)_iStack00000000000000a8);
  (**(code **)(*in_stack_00000048 + 0x680))
            (in_stack_00000048,in_stack_000000a0,unaff_R13D,1,&stack0x00000050);
  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar10 != '\0') {
    return lVar10;
  }
  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)lVar10 != '\0') {
    return lVar10;
  }
  if (DAT_1804b2c58 == 0) {
    lVar10 = *in_stack_00000048;
    in_stack_000000c0 = *(code **)(lVar10 + 0x390);
    in_stack_00000140 = FUN_180333550(in_stack_00000048);
    in_stack_000000f0 = DAT_1804b59f0;
    in_stack_000000f8 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x000004d8,1);
    lVar10 = *in_stack_00000048;
    pcVar1 = *(code **)(lVar10 + 0x390);
    uVar9 = FUN_1800024f0(in_stack_00000048);
    uVar11 = FUN_180002550(in_stack_00000048);
    uVar12 = (**(code **)(lVar10 + 0x518))(in_stack_00000048,&stack0x00000af8,5);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
    uVar13 = FUN_1800025a0(in_stack_00000048);
    uVar8 = in_stack_00000058;
    uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar13);
    (*pcVar1)(in_stack_00000048,uVar9,uVar11,uVar12,uVar13);
    uVar8 = (*in_stack_000000c0)
                      (in_stack_00000048,uVar8,in_stack_00000140,in_stack_000000f0,in_stack_000000f8
                      );
    DAT_1804b2c58 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
  }
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2c58,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1193);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000158);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
  bVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar10 != '\0') {
    return lVar10;
  }
  _iStack00000000000000a8 = (code *)((longlong)_iStack00000000000000a8 >> (bVar4 & 0x3f));
  lVar10 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)lVar10 != '\0') {
    return lVar10;
  }
  return lVar10;
}

