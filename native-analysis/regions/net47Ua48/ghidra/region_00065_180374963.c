/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=65 entry=180374963 blocks=47 maxInstructions=50000 */

/* cut edge: 0x180374963 -> 0x1803351d6 */
/* cut edge: 0x180374a50 -> 0x1803351d6 */
/* cut edge: 0x180374a7f -> 0x1803351d6 */
/* cut edge: 0x180374bf7 -> 0x1803351d6 */
/* cut edge: 0x180374cf5 -> 0x1803351d6 */
/* cut edge: 0x180374d37 -> 0x1803351d6 */
/* cut edge: 0x180374ebe -> 0x1803351d6 */
/* cut edge: 0x180374fab -> 0x1803351d6 */
/* cut edge: 0x18037500b -> 0x1803351d6 */
/* cut edge: 0x180375010 -> 0x1803351d6 */
/* cut edge: 0x180375186 -> 0x1803351d6 */
/* cut edge: 0x18037527f -> 0x1803351d6 */
/* cut edge: 0x1803752c8 -> 0x1803351d6 */
/* cut edge: 0x18037530c -> 0x1803351d6 */
/* cut edge: 0x18037536c -> 0x1803351d6 */
/* cut edge: 0x180375371 -> 0x18037d947 */
/* cut edge: 0x18037538a -> 0x1803351d6 */
/* cut edge: 0x180375503 -> 0x1803351d6 */
/* cut edge: 0x180375604 -> 0x1803351d6 */
/* cut edge: 0x18037563c -> 0x1803351d6 */
/* cut edge: 0x180375680 -> 0x1803351d6 */
/* cut edge: 0x1803756bf -> 0x1803351d6 */
/* cut edge: 0x18037571d -> 0x1803351d6 */
/* cut edge: 0x180375722 -> 0x1803351d6 */
/* cut edge: 0x18037576d -> 0x1803351d6 */
/* cut edge: 0x1803757b4 -> 0x1803351d6 */
/* cut edge: 0x1803757fb -> 0x1803351d6 */
/* cut edge: 0x180375811 -> 0x180375963 */
/* cut edge: 0x180375821 -> 0x180375963 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180375963 original byte 0x48 replaced by RET */
/* target 0x18037d947 original byte 0x48 replaced by RET */


void region_00065_180374963(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  ulonglong uVar4;
  char cVar5;
  undefined4 uVar6;
  int iVar7;
  uint uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  uint uVar15;
  ulonglong uVar16;
  int iVar17;
  longlong *in_stack_00000048;
  char cStack0000000000000050;
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
  code *in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  code *in_stack_00000100;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00000130;
  int in_stack_00000200;
  
  _cStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar9);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar9,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  in_stack_000000a0 = (**(code **)(*in_stack_00000048 + 0x598))(in_stack_00000048,uVar6);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 != '\0') {
    return;
  }
  iVar17 = 0;
  do {
    if (DAT_1804b2870 == 0) {
      lVar2 = *in_stack_00000048;
      in_stack_000000b0 = *(code **)(lVar2 + 0x390);
      in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
      in_stack_000000c0 = DAT_1804b59f0;
      in_stack_000000d8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005d2,1);
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001208,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar9 = in_stack_00000058;
      uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar9 = (*in_stack_000000b0)
                        (in_stack_00000048,uVar9,in_stack_000000b8,in_stack_000000c0,
                         in_stack_000000d8);
      DAT_1804b2870 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    _cStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2870,in_stack_00000078);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
    (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
    (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar9);
    iVar7 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar9,in_stack_00000070);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    if (iVar7 <= iVar17) {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 != '\0') {
        return;
      }
      uVar16 = 0;
      do {
        if (DAT_1804b2880 == 0) {
          lVar2 = *in_stack_00000048;
          in_stack_000000b0 = *(code **)(lVar2 + 0x390);
          in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_000000c0 = DAT_1804b59f0;
          in_stack_000000d8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005ce,1);
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000048);
          uVar11 = FUN_180002550(in_stack_00000048);
          uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000011e8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar9 = in_stack_00000058;
          uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
          (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
          uVar9 = (*in_stack_000000b0)
                            (in_stack_00000048,uVar9,in_stack_000000b8,in_stack_000000c0,
                             in_stack_000000d8);
          DAT_1804b2880 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
        }
        _cStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2880,in_stack_00000078);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
        (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0)
        ;
        (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar9);
        iVar17 = (**(code **)(*in_stack_00000048 + 0x188))
                           (in_stack_00000048,uVar9,in_stack_00000070);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 != '\0') {
          return;
        }
        if ((longlong)iVar17 <= (longlong)uVar16) {
          if (in_stack_00000200 < 2) {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 != '\0') {
            return;
          }
          uVar16 = 0;
          while( true ) {
            if (DAT_1804b2888 == 0) {
              lVar2 = *in_stack_00000048;
              in_stack_000000b0 = *(code **)(lVar2 + 0x390);
              in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
              in_stack_000000c0 = DAT_1804b59f0;
              in_stack_000000d8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005cc,1)
              ;
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar10 = FUN_1800024f0(in_stack_00000048);
              uVar11 = FUN_180002550(in_stack_00000048);
              uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000011d8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar13 = FUN_1800025a0(in_stack_00000048);
              uVar9 = in_stack_00000058;
              uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
              (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
              uVar9 = (*in_stack_000000b0)
                                (in_stack_00000048,uVar9,in_stack_000000b8,in_stack_000000c0,
                                 in_stack_000000d8);
              DAT_1804b2888 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
            }
            _cStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2888,in_stack_00000078);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
            (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                in_stack_000000e0);
            (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar9);
            iVar17 = (**(code **)(*in_stack_00000048 + 0x188))
                               (in_stack_00000048,uVar9,in_stack_00000070);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            if ((longlong)iVar17 <= (longlong)uVar16) {
              (**(code **)(*in_stack_00000048 + 0x658))
                        (in_stack_00000048,in_stack_000000a0,1,1,&stack0x00000050);
              in_stack_000000b0 = (code *)CONCAT44(in_stack_000000b0._4_4_,_cStack0000000000000050);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 != '\0') {
                return;
              }
              (**(code **)(*in_stack_00000048 + 0x658))
                        (in_stack_00000048,in_stack_000000a0,5,1,&stack0x00000050);
              uVar6 = _cStack0000000000000050;
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 != '\0') {
                return;
              }
              _cStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar6);
              (**(code **)(*in_stack_00000048 + 0x698))
                        (in_stack_00000048,in_stack_000000a0,1,1,&stack0x00000050);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 != '\0') {
                return;
              }
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 != '\0') {
                return;
              }
              if (DAT_1804b2890 == 0) {
                lVar2 = *in_stack_00000048;
                in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                in_stack_000000d8 = DAT_1804b59f0;
                uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005ca,1);
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar11 = FUN_1800024f0(in_stack_00000048);
                uVar12 = FUN_180002550(in_stack_00000048);
                uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000011c8,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar14 = FUN_1800025a0(in_stack_00000048);
                uVar9 = in_stack_00000058;
                uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                (*pcVar1)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                uVar9 = (*in_stack_000000b8)
                                  (in_stack_00000048,uVar9,in_stack_000000c0,in_stack_000000d8,
                                   uVar10);
                DAT_1804b2890 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
              }
              return;
            }
            pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
            uVar9 = FUN_1803dab60(in_stack_00000048);
            uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            (**(code **)(*in_stack_00000048 + 0x658))
                      (in_stack_00000048,in_stack_000000a0,uVar16 & 0xffffffff,1,&stack0x00000050);
            uVar4 = _cStack0000000000000050;
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            (**(code **)(*in_stack_00000048 + 0x658))
                      (in_stack_00000048,uVar9,uVar4 & 0xffffffff,1,&stack0x00000050);
            uVar6 = _cStack0000000000000050;
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            _cStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar6);
            (**(code **)(*in_stack_00000048 + 0x698))
                      (in_stack_00000048,in_stack_000000a0,uVar16 & 0xffffffff,1,&stack0x00000050);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') break;
            uVar16 = uVar16 + 1;
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 != '\0') {
              return;
            }
          }
          return;
        }
        _cStack0000000000000050 = (uint)uVar16;
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_000000a0,uVar16 & 0xffffffff,1,&stack0x00000050);
        uVar15 = _cStack0000000000000050;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 != '\0') {
          return;
        }
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_00000130,uVar16 & 0xffffffff,1,&stack0x00000050);
        uVar8 = _cStack0000000000000050;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 != '\0') {
          return;
        }
        _cStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar8 ^ uVar15);
        (**(code **)(*in_stack_00000048 + 0x698))
                  (in_stack_00000048,in_stack_000000a0,uVar16 & 0xffffffff,1,&stack0x00000050);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 != '\0') {
          return;
        }
        uVar16 = uVar16 + 1;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      } while (cVar5 == '\0');
      return;
    }
    (**(code **)(*in_stack_00000048 + 0x640))
              (in_stack_00000048,in_stack_00000120,iVar17,1,&stack0x00000050);
    uVar15 = (uint)cStack0000000000000050;
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b2878 == 0) {
      lVar2 = *in_stack_00000048;
      in_stack_000000b0 = *(code **)(lVar2 + 0x390);
      in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
      in_stack_000000c0 = DAT_1804b59f0;
      in_stack_000000d8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000005d0,1);
      lVar2 = *in_stack_00000048;
      in_stack_00000100 = *(code **)(lVar2 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000011f8,5);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar9 = in_stack_00000058;
      uVar13 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar13);
      (*in_stack_00000100)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar9 = (*in_stack_000000b0)
                        (in_stack_00000048,uVar9,in_stack_000000b8,in_stack_000000c0,
                         in_stack_000000d8);
      DAT_1804b2878 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    _cStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2878,in_stack_00000078);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
    (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
    (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar9);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar9,in_stack_00000070);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    _cStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar8 & uVar15);
    (**(code **)(*in_stack_00000048 + 0x698))
              (in_stack_00000048,in_stack_000000a0,iVar17,1,&stack0x00000050);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    iVar17 = iVar17 + 1;
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  } while (cVar5 == '\0');
  return;
}

