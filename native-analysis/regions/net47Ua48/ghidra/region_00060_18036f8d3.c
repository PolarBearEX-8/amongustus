/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=60 entry=18036f8d3 blocks=41 maxInstructions=50000 */

/* cut edge: 0x18036f8d3 -> 0x1803351d6 */
/* cut edge: 0x18036f9c6 -> 0x1803351d6 */
/* cut edge: 0x18036fb47 -> 0x1803351d6 */
/* cut edge: 0x18036fc45 -> 0x1803351d6 */
/* cut edge: 0x18036fc8b -> 0x1803351d6 */
/* cut edge: 0x18036fce0 -> 0x1803351d6 */
/* cut edge: 0x18036fd64 -> 0x1803351d6 */
/* cut edge: 0x18036fd69 -> 0x18036773e */
/* cut edge: 0x18036fd8c -> 0x1803351d6 */
/* cut edge: 0x18036ff09 -> 0x1803351d6 */
/* cut edge: 0x180370007 -> 0x1803351d6 */
/* cut edge: 0x18037003f -> 0x1803351d6 */
/* cut edge: 0x180370083 -> 0x1803351d6 */
/* cut edge: 0x1803700c2 -> 0x1803351d6 */
/* cut edge: 0x180370120 -> 0x1803351d6 */
/* cut edge: 0x180370125 -> 0x1803351d6 */
/* cut edge: 0x180370170 -> 0x1803351d6 */
/* cut edge: 0x1803701b7 -> 0x1803351d6 */
/* cut edge: 0x1803701fe -> 0x1803351d6 */
/* cut edge: 0x180370366 -> 0x1803351d6 */
/* cut edge: 0x180370453 -> 0x1803351d6 */
/* cut edge: 0x180370497 -> 0x1803351d6 */
/* cut edge: 0x1803704de -> 0x1803351d6 */
/* cut edge: 0x180370646 -> 0x1803351d6 */
/* cut edge: 0x180370738 -> 0x1803351d6 */
/* cut edge: 0x18037074e -> 0x1803708a1 */
/* cut edge: 0x18037075e -> 0x1803708a1 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18036773e original byte 0x48 replaced by RET */
/* target 0x1803708a1 original byte 0x48 replaced by RET */


void region_00060_18036f8d3(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  uint uVar4;
  uint uVar5;
  ulonglong uVar6;
  char cVar7;
  int iVar8;
  int iVar9;
  undefined4 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
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
  code *in_stack_000000b0;
  code *in_stack_000000b8;
  code *in_stack_000000c0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000130;
  ulonglong in_stack_000001a8;
  int in_stack_00000200;
  undefined8 in_stack_00000228;
  int in_stack_00000868;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
  (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
  (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar11);
  in_stack_000000b0 =
       (code *)(**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070)
  ;
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 != '\0') {
    return;
  }
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar7 != '\0') {
    return;
  }
  in_stack_000000b0 = (code *)(ulonglong)(uint)((int)in_stack_000000b0 * in_stack_00000868);
  do {
    if (DAT_1804b2790 == 0) {
      lVar2 = *in_stack_00000048;
      in_stack_000000b8 = *(code **)(lVar2 + 0x390);
      in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
      in_stack_000000d8 = DAT_1804b59f0;
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000060a,1);
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar13 = FUN_1800024f0(in_stack_00000048);
      uVar14 = FUN_180002550(in_stack_00000048);
      uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000013c8,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar16 = FUN_1800025a0(in_stack_00000048);
      uVar11 = in_stack_00000058;
      uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar16);
      (*pcVar1)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
      uVar11 = (*in_stack_000000b8)
                         (in_stack_00000048,uVar11,in_stack_000000c0,in_stack_000000d8,uVar12);
      DAT_1804b2790 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
    }
    _uStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2790,in_stack_00000078);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
    (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
    (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar11);
    iVar8 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 != '\0') {
      return;
    }
    if (iVar8 <= (int)(uint)in_stack_000001a8) {
      if (in_stack_00000868 + 1 < in_stack_00000200) {
        return;
      }
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar7 != '\0') {
        return;
      }
      iVar8 = 0;
      while( true ) {
        if (DAT_1804b2798 == 0) {
          lVar2 = *in_stack_00000048;
          in_stack_000000b0 = *(code **)(lVar2 + 0x390);
          in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_000000c0 = (code *)DAT_1804b59f0;
          in_stack_000000d8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000608,1);
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar12 = FUN_1800024f0(in_stack_00000048);
          uVar13 = FUN_180002550(in_stack_00000048);
          uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000013b8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar15 = FUN_1800025a0(in_stack_00000048);
          uVar11 = in_stack_00000058;
          uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
          (*pcVar1)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
          uVar11 = (*in_stack_000000b0)
                             (in_stack_00000048,uVar11,in_stack_000000b8,in_stack_000000c0,
                              in_stack_000000d8);
          DAT_1804b2798 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        _uStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2798,in_stack_00000078);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
        (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0)
        ;
        (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar11);
        iVar9 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar11,in_stack_00000070);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 != '\0') {
          return;
        }
        if (iVar9 <= iVar8) {
          (**(code **)(*in_stack_00000048 + 0x658))
                    (in_stack_00000048,in_stack_000000a0,1,1,&stack0x00000050);
          in_stack_000000b0 = (code *)CONCAT44(in_stack_000000b0._4_4_,uStack0000000000000050);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 != '\0') {
            return;
          }
          (**(code **)(*in_stack_00000048 + 0x658))
                    (in_stack_00000048,in_stack_000000a0,5,1,&stack0x00000050);
          uVar10 = uStack0000000000000050;
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 != '\0') {
            return;
          }
          _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar10);
          (**(code **)(*in_stack_00000048 + 0x698))
                    (in_stack_00000048,in_stack_000000a0,1,1,&stack0x00000050);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 != '\0') {
            return;
          }
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar7 != '\0') {
            return;
          }
          if (DAT_1804b27a0 == 0) {
            lVar2 = *in_stack_00000048;
            in_stack_000000b8 = *(code **)(lVar2 + 0x390);
            in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_000000d8 = DAT_1804b59f0;
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000606,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar13 = FUN_1800024f0(in_stack_00000048);
            uVar14 = FUN_180002550(in_stack_00000048);
            uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000013a8,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar16 = FUN_1800025a0(in_stack_00000048);
            uVar11 = in_stack_00000058;
            uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar16);
            (*pcVar1)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
            uVar11 = (*in_stack_000000b8)
                               (in_stack_00000048,uVar11,in_stack_000000c0,in_stack_000000d8,uVar12)
            ;
            DAT_1804b27a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b27a0,in_stack_00000078);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2d6c);
          (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_00000228);
          (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar11);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x188))
                             (in_stack_00000048,uVar11,in_stack_00000070);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 != '\0') {
            return;
          }
          (**(code **)(*in_stack_00000048 + 0x658))
                    (in_stack_00000048,in_stack_000000a0,uVar10,1,&stack0x00000050);
          uVar10 = uStack0000000000000050;
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 != '\0') {
            return;
          }
          _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar10);
          (**(code **)(*in_stack_00000048 + 0x698))
                    (in_stack_00000048,in_stack_000000a0,5,1,&stack0x00000050);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 != '\0') {
            return;
          }
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar7 != '\0') {
            return;
          }
          if (DAT_1804b27a8 == 0) {
            lVar2 = *in_stack_00000048;
            in_stack_000000b8 = *(code **)(lVar2 + 0x390);
            in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_000000d8 = DAT_1804b59f0;
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000604,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar13 = FUN_1800024f0(in_stack_00000048);
            uVar14 = FUN_180002550(in_stack_00000048);
            uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001398,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar16 = FUN_1800025a0(in_stack_00000048);
            uVar11 = in_stack_00000058;
            uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar16);
            (*pcVar1)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
            uVar11 = (*in_stack_000000b8)
                               (in_stack_00000048,uVar11,in_stack_000000c0,in_stack_000000d8,uVar12)
            ;
            DAT_1804b27a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b27a8,in_stack_00000078);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2d6c);
          (*pcVar1)(in_stack_00000048,uVar11,0,uVar12);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_00000228);
          (*pcVar1)(in_stack_00000048,uVar11,1,uVar12);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar11);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x188))
                             (in_stack_00000048,uVar11,in_stack_00000070);
          in_stack_000000b8 = (code *)CONCAT44(in_stack_000000b8._4_4_,uVar10);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 != '\0') {
            return;
          }
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar7 != '\0') {
            return;
          }
          if (DAT_1804b27b0 == 0) {
            lVar2 = *in_stack_00000048;
            in_stack_000000c0 = *(code **)(lVar2 + 0x390);
            in_stack_000000d8 = FUN_180333550(in_stack_00000048);
            in_stack_00000100 = DAT_1804b59f0;
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000602,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar13 = FUN_1800024f0(in_stack_00000048);
            uVar14 = FUN_180002550(in_stack_00000048);
            uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001388,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar16 = FUN_1800025a0(in_stack_00000048);
            uVar11 = in_stack_00000058;
            uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar16);
            (*pcVar1)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
            uVar11 = (*in_stack_000000c0)
                               (in_stack_00000048,uVar11,in_stack_000000d8,in_stack_00000100,uVar12)
            ;
            DAT_1804b27b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          return;
        }
        pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
        uVar11 = FUN_1803dab60(in_stack_00000048);
        uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar11);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 != '\0') {
          return;
        }
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_000000a0,iVar8,1,&stack0x00000050);
        uVar6 = _uStack0000000000000050;
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 != '\0') {
          return;
        }
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,uVar11,uVar6 & 0xffffffff,1,&stack0x00000050);
        uVar10 = uStack0000000000000050;
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 != '\0') {
          return;
        }
        _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar10);
        (**(code **)(*in_stack_00000048 + 0x698))
                  (in_stack_00000048,in_stack_000000a0,iVar8,1,&stack0x00000050);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 != '\0') break;
        iVar8 = iVar8 + 1;
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar7 != '\0') {
          return;
        }
      }
      return;
    }
    uStack0000000000000050 = (uint)in_stack_000001a8;
    (**(code **)(*in_stack_00000048 + 0x658))
              (in_stack_00000048,in_stack_000000a0,in_stack_000001a8,1,&stack0x00000050);
    uVar4 = uStack0000000000000050;
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 != '\0') {
      return;
    }
    (**(code **)(*in_stack_00000048 + 0x658))
              (in_stack_00000048,in_stack_00000130,(int)in_stack_000000b0 + (int)in_stack_000001a8,1
               ,&stack0x00000050);
    uVar5 = uStack0000000000000050;
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 != '\0') {
      return;
    }
    _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar5 ^ uVar4);
    (**(code **)(*in_stack_00000048 + 0x698))
              (in_stack_00000048,in_stack_000000a0,in_stack_000001a8,1,&stack0x00000050);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 != '\0') {
      return;
    }
    in_stack_000001a8 =
         in_stack_000001a8 & 0xffffffff00000000 | (ulonglong)((int)in_stack_000001a8 + 1);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  } while (cVar7 == '\0');
  return;
}

