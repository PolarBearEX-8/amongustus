/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=59 entry=18036e898 blocks=34 maxInstructions=50000 */

/* cut edge: 0x18036e898 -> 0x1803351d6 */
/* cut edge: 0x18036e99f -> 0x1803351d6 */
/* cut edge: 0x18036eaf9 -> 0x1803351d6 */
/* cut edge: 0x18036ec00 -> 0x1803351d6 */
/* cut edge: 0x18036ed6e -> 0x1803351d6 */
/* cut edge: 0x18036ee76 -> 0x1803351d6 */
/* cut edge: 0x18036efd5 -> 0x1803351d6 */
/* cut edge: 0x18036f0c3 -> 0x1803351d6 */
/* cut edge: 0x18036f23d -> 0x1803351d6 */
/* cut edge: 0x18036f33b -> 0x1803351d6 */
/* cut edge: 0x18036f495 -> 0x1803351d6 */
/* cut edge: 0x18036f58b -> 0x1803351d6 */
/* cut edge: 0x18036f5f8 -> 0x1803351d6 */
/* cut edge: 0x18036f665 -> 0x1803351d6 */
/* cut edge: 0x18036f6d8 -> 0x1803351d6 */
/* cut edge: 0x18036f732 -> 0x18036a75c */
/* cut edge: 0x18036f763 -> 0x1803351d6 */
/* cut edge: 0x18036f781 -> 0x18036f8d3 */
/* cut edge: 0x18036f791 -> 0x18036f8d3 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18036a75c original byte 0x48 replaced by RET */
/* target 0x18036f8d3 original byte 0x48 replaced by RET */


void region_00059_18036e898(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  int iVar4;
  char cVar5;
  uint uVar6;
  uint uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  uint unaff_R15D;
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
  uint uStack00000000000000b0;
  uint uStack00000000000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  uint in_stack_000000d8;
  uint in_stack_000000f0;
  uint uStack00000000000000f8;
  uint in_stack_00000100;
  undefined8 in_stack_00000110;
  uint in_stack_00000118;
  uint in_stack_00000120;
  uint in_stack_00000128;
  uint in_stack_00000138;
  uint in_stack_00000148;
  uint in_stack_00000150;
  undefined8 in_stack_00000160;
  uint in_stack_00000178;
  undefined8 in_stack_00000188;
  undefined4 in_stack_00000190;
  undefined4 in_stack_00000198;
  ulonglong in_stack_000001a8;
  undefined4 in_stack_000001b0;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
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
  in_stack_000000f0 =
       (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    in_stack_000000f0 = in_stack_000000f0 & (unaff_R15D ^ uStack00000000000000f8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b2768 == 0) {
        lVar2 = *in_stack_00000048;
        _uStack00000000000000f8 = *(code **)(lVar2 + 0x390);
        in_stack_00000160 = FUN_180333550(in_stack_00000048);
        in_stack_00000188 = DAT_1804b59f0;
        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000614,1);
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001418,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar8 = in_stack_00000058;
        uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
        (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar8 = (*_uStack00000000000000f8)
                          (in_stack_00000048,uVar8,in_stack_00000160,in_stack_00000188,uVar9);
        DAT_1804b2768 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      _uStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2768,in_stack_00000078);
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
      uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((uStack00000000000000b8 & uVar6) == 0) {
          if (cVar5 != '\0') {
            return;
          }
          if (DAT_1804b2780 == 0) {
            lVar2 = *in_stack_00000048;
            _uStack00000000000000f8 = *(code **)(lVar2 + 0x390);
            in_stack_00000160 = FUN_180333550(in_stack_00000048);
            in_stack_00000188 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000060e,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000013e8,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar8 = in_stack_00000058;
            uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar8 = (*_uStack00000000000000f8)
                              (in_stack_00000048,uVar8,in_stack_00000160,in_stack_00000188,uVar9);
            DAT_1804b2780 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2780,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar8,in_stack_00000070);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 != '\0') {
            return;
          }
          uVar6 = uVar6 & in_stack_00000150;
        }
        else {
          if (cVar5 != '\0') {
            return;
          }
          if (DAT_1804b2770 == 0) {
            lVar2 = *in_stack_00000048;
            _uStack00000000000000f8 = *(code **)(lVar2 + 0x390);
            in_stack_00000160 = FUN_180333550(in_stack_00000048);
            in_stack_00000188 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000612,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001408,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar8 = in_stack_00000058;
            uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar8 = (*_uStack00000000000000f8)
                              (in_stack_00000048,uVar8,in_stack_00000160,in_stack_00000188,uVar9);
            DAT_1804b2770 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2770,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar8,in_stack_00000070);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 != '\0') {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 != '\0') {
            return;
          }
          if (DAT_1804b2778 == 0) {
            lVar2 = *in_stack_00000048;
            _uStack00000000000000f8 = *(code **)(lVar2 + 0x390);
            in_stack_00000160 = FUN_180333550(in_stack_00000048);
            in_stack_00000188 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000610,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000013f8,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar8 = in_stack_00000058;
            uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar8 = (*_uStack00000000000000f8)
                              (in_stack_00000048,uVar8,in_stack_00000160,in_stack_00000188,uVar9);
            DAT_1804b2778 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2778,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar8,in_stack_00000070);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 != '\0') {
            return;
          }
          uVar6 = uVar6 & (uVar7 ^ in_stack_00000150);
        }
        uStack0000000000000050 =
             (uint)_uStack00000000000000b0 ^ in_stack_000000d8 ^ uStack00000000000000b8 ^
             in_stack_00000118 ^ in_stack_00000128;
        (**(code **)(*in_stack_00000048 + 0x698))
                  (in_stack_00000048,in_stack_000000a0,in_stack_000000c0,1,&stack0x00000050);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          uStack0000000000000050 =
               (uint)_uStack00000000000000b0 ^ in_stack_00000100 ^ uStack00000000000000b8 ^
               in_stack_00000138 ^ in_stack_00000148;
          (**(code **)(*in_stack_00000048 + 0x698))
                    (in_stack_00000048,in_stack_000000a0,in_stack_00000190,1,&stack0x00000050);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            uVar7 = in_stack_000000d8 ^ in_stack_00000100;
            uStack0000000000000050 =
                 (uint)_uStack00000000000000b8 ^ uVar7 ^ in_stack_00000178 ^ in_stack_00000120;
            (**(code **)(*in_stack_00000048 + 0x698))
                      (in_stack_00000048,in_stack_000000a0,in_stack_00000198,1,&stack0x00000050);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              _uStack0000000000000050 =
                   CONCAT44(uStack0000000000000054,
                            uVar7 ^ uStack00000000000000b0 ^ in_stack_000000f0 ^ uVar6);
              (**(code **)(*in_stack_00000048 + 0x698))
                        (in_stack_00000048,in_stack_000000a0,in_stack_000001b0,1,&stack0x00000050);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                iVar4 = (int)in_stack_000001a8;
                in_stack_000001a8 = in_stack_000001a8 & 0xffffffff00000000;
                if (iVar4 + 1 < 4) {
                  return;
                }
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b2788 == 0) {
                    lVar2 = *in_stack_00000048;
                    _uStack00000000000000b0 = *(code **)(lVar2 + 0x390);
                    _uStack00000000000000b8 = FUN_180333550(in_stack_00000048);
                    in_stack_000000c0 = DAT_1804b59f0;
                    uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000060c,1);
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar10 = FUN_1800024f0(in_stack_00000048);
                    uVar11 = FUN_180002550(in_stack_00000048);
                    uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000013d8,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar13 = FUN_1800025a0(in_stack_00000048);
                    uVar8 = in_stack_00000058;
                    uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
                    (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                    uVar8 = (*_uStack00000000000000b0)
                                      (in_stack_00000048,uVar8,_uStack00000000000000b8,
                                       in_stack_000000c0,uVar9);
                    DAT_1804b2788 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                  }
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

