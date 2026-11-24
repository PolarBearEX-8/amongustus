/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=55 entry=18036a871 blocks=32 maxInstructions=50000 */

/* cut edge: 0x18036a871 -> 0x1803351d6 */
/* cut edge: 0x18036a8d1 -> 0x1803351d6 */
/* cut edge: 0x18036aa39 -> 0x1803351d6 */
/* cut edge: 0x18036ab4e -> 0x1803351d6 */
/* cut edge: 0x18036aca8 -> 0x1803351d6 */
/* cut edge: 0x18036ad96 -> 0x1803351d6 */
/* cut edge: 0x18036af0c -> 0x1803351d6 */
/* cut edge: 0x18036b013 -> 0x1803351d6 */
/* cut edge: 0x18036b16d -> 0x1803351d6 */
/* cut edge: 0x18036b26d -> 0x1803351d6 */
/* cut edge: 0x18036b3d5 -> 0x1803351d6 */
/* cut edge: 0x18036b4c2 -> 0x18036b9af */
/* cut edge: 0x18036b4ea -> 0x1803351d6 */
/* cut edge: 0x18036b644 -> 0x1803351d6 */
/* cut edge: 0x18036b732 -> 0x1803351d6 */
/* cut edge: 0x18036b748 -> 0x18036b8a8 */
/* cut edge: 0x18036b758 -> 0x18036b8a8 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18036b8a8 original byte 0x48 replaced by RET */
/* target 0x18036b9af original byte 0x84 replaced by RET */


void region_00055_18036a871(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  uint uVar5;
  uint uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000050;
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
  ulonglong in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  code *in_stack_000000f0;
  uint uStack00000000000000f8;
  undefined4 uStack00000000000000fc;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000110;
  uint uStack0000000000000118;
  undefined4 uStack000000000000011c;
  code *in_stack_00000120;
  undefined8 in_stack_00000128;
  undefined8 in_stack_00000138;
  undefined8 in_stack_00000148;
  uint uStack00000000000001b0;
  
  uStack00000000000001b0 = (uint)in_stack_000000c0 | 3;
  (**(code **)(*in_stack_00000048 + 0x658))
            (in_stack_00000048,in_stack_000000a0,uStack00000000000001b0,1,&stack0x00000050);
  in_stack_000000b8 = in_stack_00000050 & 0xffffffff;
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((cVar4 == '\0') && (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
    if (DAT_1804b2688 == 0) {
      lVar1 = *in_stack_00000048;
      in_stack_00000120 = *(code **)(lVar1 + 0x390);
      in_stack_000000f0 = (code *)FUN_180333550(in_stack_00000048);
      _uStack00000000000000f8 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000064c,1);
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000015d8,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar12 = in_stack_00000058;
      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
      (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar12 = (*in_stack_00000120)
                         (in_stack_00000048,uVar12,in_stack_000000f0,_uStack00000000000000f8,uVar7);
      DAT_1804b2688 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    in_stack_00000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2688,in_stack_00000078);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
    (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
    (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000070);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 == '\0') {
      uVar6 = (uint)in_stack_00000100;
      _uStack00000000000000f8 = CONCAT44(uStack00000000000000fc,uVar6 * 2);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
      if ((uVar5 & uVar6) == 0) {
        if (cVar4 != '\0') {
          return;
        }
        if (DAT_1804b26a0 == 0) {
          lVar1 = *in_stack_00000048;
          in_stack_00000120 = *(code **)(lVar1 + 0x390);
          in_stack_000000f0 = (code *)FUN_180333550(in_stack_00000048);
          _uStack0000000000000118 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000646,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000015a8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar12 = in_stack_00000058;
          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*in_stack_00000120)
                             (in_stack_00000048,uVar12,in_stack_000000f0,_uStack0000000000000118,
                              uVar7);
          DAT_1804b26a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        in_stack_00000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b26a0,in_stack_00000078);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
        (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
        (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar12,in_stack_00000070);
        uStack0000000000000118 = uVar5;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        _uStack0000000000000118 =
             CONCAT44(uStack000000000000011c,uStack0000000000000118 & uStack00000000000000f8);
      }
      else {
        if (cVar4 != '\0') {
          return;
        }
        if (DAT_1804b2690 == 0) {
          lVar1 = *in_stack_00000048;
          in_stack_00000120 = *(code **)(lVar1 + 0x390);
          in_stack_000000f0 = (code *)FUN_180333550(in_stack_00000048);
          _uStack0000000000000118 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000064a,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000015c8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar12 = in_stack_00000058;
          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*in_stack_00000120)
                             (in_stack_00000048,uVar12,in_stack_000000f0,_uStack0000000000000118,
                              uVar7);
          DAT_1804b2690 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        in_stack_00000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2690,in_stack_00000078);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
        (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110);
        (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar12,in_stack_00000070);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar4 != '\0') {
          return;
        }
        if (DAT_1804b2698 == 0) {
          lVar1 = *in_stack_00000048;
          in_stack_00000120 = *(code **)(lVar1 + 0x390);
          in_stack_000000f0 = (code *)FUN_180333550(in_stack_00000048);
          _uStack0000000000000118 = DAT_1804b59f0;
          in_stack_00000128 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000648,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_1800024f0(in_stack_00000048);
          uVar8 = FUN_180002550(in_stack_00000048);
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000015b8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar10 = FUN_1800025a0(in_stack_00000048);
          uVar12 = in_stack_00000058;
          uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar10);
          (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
          uVar12 = (*in_stack_00000120)
                             (in_stack_00000048,uVar12,in_stack_000000f0,_uStack0000000000000118,
                              in_stack_00000128);
          DAT_1804b2698 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        in_stack_00000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2698,in_stack_00000078);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
        (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
        (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar12,in_stack_00000070);
        uStack0000000000000118 = uVar6;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        _uStack0000000000000118 =
             CONCAT44(uStack000000000000011c,
                      uStack0000000000000118 & (uVar5 ^ uStack00000000000000f8));
      }
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar4 == '\0') {
        if (DAT_1804b26a8 == 0) {
          lVar1 = *in_stack_00000048;
          in_stack_00000120 = *(code **)(lVar1 + 0x390);
          in_stack_000000f0 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_00000128 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000644,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001598,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar12 = in_stack_00000058;
          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*in_stack_00000120)
                             (in_stack_00000048,uVar12,in_stack_000000f0,in_stack_00000128,uVar7);
          DAT_1804b26a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        in_stack_00000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b26a8,in_stack_00000078);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
        (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
        (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar12,in_stack_00000070);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          uVar6 = (uint)in_stack_000000d8;
          in_stack_00000120 = (code *)CONCAT44(in_stack_00000120._4_4_,uVar6 * 2);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((uVar5 & uVar6) == 0) {
            return;
          }
          if (cVar4 == '\0') {
            if (DAT_1804b26b0 == 0) {
              lVar1 = *in_stack_00000048;
              in_stack_000000f0 = *(code **)(lVar1 + 0x390);
              in_stack_00000128 = FUN_180333550(in_stack_00000048);
              in_stack_00000138 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000642,1);
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001588,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar12 = in_stack_00000058;
              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*in_stack_000000f0)
                                 (in_stack_00000048,uVar12,in_stack_00000128,in_stack_00000138,uVar7
                                 );
              DAT_1804b26b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            in_stack_00000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b26b0,in_stack_00000078);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_00000088,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
            (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_00000110);
            (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
            (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000070);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((cVar4 == '\0') &&
               (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
              if (DAT_1804b26b8 == 0) {
                lVar1 = *in_stack_00000048;
                in_stack_000000f0 = *(code **)(lVar1 + 0x390);
                in_stack_00000128 = FUN_180333550(in_stack_00000048);
                in_stack_00000138 = DAT_1804b59f0;
                in_stack_00000148 =
                     (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000640,1);
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar7 = FUN_1800024f0(in_stack_00000048);
                uVar8 = FUN_180002550(in_stack_00000048);
                uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001578,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar10 = FUN_1800025a0(in_stack_00000048);
                uVar12 = in_stack_00000058;
                uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar10);
                (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                uVar12 = (*in_stack_000000f0)
                                   (in_stack_00000048,uVar12,in_stack_00000128,in_stack_00000138,
                                    in_stack_00000148);
                DAT_1804b26b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
              }
              return;
            }
          }
        }
      }
    }
  }
  return;
}

