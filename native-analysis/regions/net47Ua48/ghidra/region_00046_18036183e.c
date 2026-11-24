/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=46 entry=18036183e blocks=38 maxInstructions=50000 */

/* cut edge: 0x18036199e -> 0x1803351d6 */
/* cut edge: 0x180361a8f -> 0x1803351d6 */
/* cut edge: 0x180361ae3 -> 0x1803351d6 */
/* cut edge: 0x180361c5e -> 0x1803351d6 */
/* cut edge: 0x180361d4b -> 0x180361091 */
/* cut edge: 0x180361d70 -> 0x1803351d6 */
/* cut edge: 0x180361ed8 -> 0x1803351d6 */
/* cut edge: 0x180361fc5 -> 0x1803351d6 */
/* cut edge: 0x180361ff4 -> 0x1803351d6 */
/* cut edge: 0x180362065 -> 0x180366608 */
/* cut edge: 0x180362080 -> 0x1803351d6 */
/* cut edge: 0x1803621ef -> 0x1803351d6 */
/* cut edge: 0x1803622e5 -> 0x1803351d6 */
/* cut edge: 0x180362328 -> 0x1803351d6 */
/* cut edge: 0x180362372 -> 0x1803351d6 */
/* cut edge: 0x1803623b7 -> 0x1803351d6 */
/* cut edge: 0x180362418 -> 0x1803351d6 */
/* cut edge: 0x18036241d -> 0x1803351d6 */
/* cut edge: 0x180362585 -> 0x1803351d6 */
/* cut edge: 0x180362672 -> 0x1803351d6 */
/* cut edge: 0x1803626a1 -> 0x1803351d6 */
/* cut edge: 0x180362809 -> 0x1803351d6 */
/* cut edge: 0x180362809 -> 0x1803628f6 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180361091 original byte 0x48 replaced by RET */
/* target 0x1803628f6 original byte 0x48 replaced by RET */
/* target 0x180366608 original byte 0x84 replaced by RET */


ulonglong region_00046_18036183e(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  byte bVar4;
  byte bVar5;
  undefined4 uVar6;
  int iVar7;
  int iVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  ulonglong uVar14;
  undefined8 uVar15;
  int iVar16;
  int unaff_R13D;
  longlong unaff_R14;
  longlong *in_stack_00000048;
  byte bStack0000000000000050;
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
  code *in_stack_000000a0;
  undefined8 in_stack_000000b0;
  int iStack00000000000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  code *pcStack00000000000000d8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_000000f0;
  code *in_stack_000000f8;
  code *in_stack_00000100;
  code *in_stack_00000120;
  undefined8 in_stack_00000138;
  undefined8 in_stack_00000158;
  int iStack0000000000000178;
  undefined8 in_stack_00000180;
  ulonglong in_stack_00000198;
  undefined8 in_stack_000001a8;
  undefined8 in_stack_000001c0;
  
  lVar1 = *in_stack_00000048;
  pcStack00000000000000d8 = *(code **)(lVar1 + 0x390);
  in_stack_00000100 = (code *)FUN_1803335f0(in_stack_00000048);
  in_stack_00000120 = DAT_1804b59f0;
  in_stack_000000f0 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006a6,1);
  lVar1 = *in_stack_00000048;
  in_stack_000000f8 = *(code **)(lVar1 + 0x390);
  uVar9 = FUN_1800024f0(in_stack_00000048);
  uVar10 = FUN_180002550(in_stack_00000048);
  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000018a8,5);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
  uVar12 = FUN_1800025a0(in_stack_00000048);
  uVar13 = in_stack_00000058;
  uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar12);
  (*in_stack_000000f8)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
  uVar13 = (*pcStack00000000000000d8)
                     (in_stack_00000048,uVar13,in_stack_00000100,in_stack_00000120,in_stack_000000f0
                     );
  DAT_1804b2520 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
  _bStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2520,in_stack_00000078);
  uVar13 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5f01);
  (*pcVar2)(in_stack_00000048,uVar13,0,uVar9);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000b0);
  (*pcVar2)(in_stack_00000048,uVar13,1,uVar9);
  uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar13);
  bVar5 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar13,in_stack_000001c0);
  uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)uVar14 == '\0') {
    _bStack0000000000000050 = CONCAT71(uStack0000000000000051,bVar5 & (byte)in_stack_000000a0);
    (**(code **)(*in_stack_00000048 + 0x680))
              (in_stack_00000048,in_stack_00000138,iStack00000000000000b8 + unaff_R13D,1,
               &stack0x00000050);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((char)uVar14 == '\0') &&
       (uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(), (char)uVar14 == '\0')) {
      if (DAT_1804b2528 == 0) {
        lVar1 = *in_stack_00000048;
        _iStack00000000000000b8 = *(code **)(lVar1 + 0x390);
        pcStack00000000000000d8 = (code *)FUN_180333550(in_stack_00000048);
        in_stack_00000100 = DAT_1804b59f0;
        in_stack_00000120 =
             (code *)(**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006a4,1);
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001898,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar13 = in_stack_00000058;
        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
        (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        uVar13 = (*_iStack00000000000000b8)
                           (in_stack_00000048,uVar13,pcStack00000000000000d8,in_stack_00000100,
                            in_stack_00000120);
        DAT_1804b2528 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
      }
      _bStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2528,in_stack_00000078);
      uVar13 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1193);
      (*pcVar2)(in_stack_00000048,uVar13,0,uVar9);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000158);
      (*pcVar2)(in_stack_00000048,uVar13,1,uVar9);
      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar13);
      bVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar13,in_stack_00000070);
      uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)uVar14 == '\0') {
        in_stack_000000a0 = (code *)((ulonglong)in_stack_000000a0 >> (bVar5 & 0x3f));
        if ((unaff_R14 + 0xffffffffU & 0x80000000) == 0) {
          return unaff_R14 + 0xffffffffU & 0xffffffff;
        }
        uVar14 = (**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)uVar14 == '\0') {
          if (DAT_1804b2530 == 0) {
            lVar1 = *in_stack_00000048;
            in_stack_000000a0 = *(code **)(lVar1 + 0x390);
            in_stack_000000b0 = FUN_180333550(in_stack_00000048);
            _iStack00000000000000b8 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006a2,1);
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001888,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar15 = FUN_1800025a0(in_stack_00000048);
            uVar13 = in_stack_00000058;
            uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
            (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar15);
            uVar13 = (*in_stack_000000a0)
                               (in_stack_00000048,uVar13,in_stack_000000b0,_iStack00000000000000b8,
                                uVar9);
            DAT_1804b2530 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
          }
          _bStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2530,in_stack_00000078);
          uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
          (*pcVar2)(in_stack_00000048,uVar13,0,uVar9);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0
                            );
          (*pcVar2)(in_stack_00000048,uVar13,1,uVar9);
          uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar13);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar13,in_stack_00000070);
          uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((char)uVar14 == '\0') {
            in_stack_00000180 = (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,uVar6);
            uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)uVar14 == '\0') {
              iVar7 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000138)
              ;
              uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)uVar14 == '\0') {
                in_stack_00000198 = in_stack_000000c8 ^ 0x776d852de73826de;
                _iStack0000000000000178 = 0;
                in_stack_000001a8 = 0;
                in_stack_000000b0 = 0;
                in_stack_000000a0 = (code *)0x0;
                uVar14 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (iVar7 <= iStack0000000000000178) {
                  return uVar14;
                }
                if ((char)uVar14 == '\0') {
                  iVar7 = 0;
                  do {
                    if (DAT_1804b2538 == 0) {
                      lVar1 = *in_stack_00000048;
                      _iStack00000000000000b8 = *(code **)(lVar1 + 0x390);
                      in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                      pcStack00000000000000d8 = DAT_1804b59f0;
                      in_stack_00000100 =
                           (code *)(**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x000006a0,1);
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001878,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar13 = in_stack_00000058;
                      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar13 = (*_iStack00000000000000b8)
                                         (in_stack_00000048,uVar13,in_stack_000000c0,
                                          pcStack00000000000000d8,in_stack_00000100);
                      DAT_1804b2538 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                    }
                    _bStack0000000000000050 =
                         (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b2538,in_stack_00000078);
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                       (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar13,0,uVar9);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       in_stack_000000e0);
                    (*pcVar2)(in_stack_00000048,uVar13,1,uVar9);
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,
                                        uVar13);
                    iVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                                      (in_stack_00000048,uVar13,in_stack_00000070);
                    uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar14 != '\0') {
                      return uVar14;
                    }
                    if (iVar8 <= iVar7) {
LAB_18036241d:
                      uVar14 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)uVar14 != '\0') {
                        return uVar14;
                      }
                      if (DAT_1804b2540 == 0) {
                        lVar1 = *in_stack_00000048;
                        _iStack00000000000000b8 = *(code **)(lVar1 + 0x390);
                        in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                        pcStack00000000000000d8 = DAT_1804b59f0;
                        uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000069e,1);
                        lVar1 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar1 + 0x390);
                        uVar10 = FUN_1800024f0(in_stack_00000048);
                        uVar11 = FUN_180002550(in_stack_00000048);
                        uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001868,5);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar15 = FUN_1800025a0(in_stack_00000048);
                        uVar13 = in_stack_00000058;
                        uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
                        (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar15);
                        uVar13 = (*_iStack00000000000000b8)
                                           (in_stack_00000048,uVar13,in_stack_000000c0,
                                            pcStack00000000000000d8,uVar9);
                        DAT_1804b2540 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                      }
                      _bStack0000000000000050 =
                           (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b2540,in_stack_00000078);
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                         (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                         0x583d);
                      (*pcVar2)(in_stack_00000048,uVar13,0,uVar9);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_000000e0);
                      (*pcVar2)(in_stack_00000048,uVar13,1,uVar9);
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,_bStack0000000000000050,
                                          in_stack_00000080,uVar13);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                        (in_stack_00000048,uVar13,in_stack_00000070);
                      uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)uVar14 != '\0') {
                        return uVar14;
                      }
                      pcStack00000000000000d8 =
                           (code *)(**(code **)(*in_stack_00000048 + 0x580))
                                             (in_stack_00000048,uVar6);
                      uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)uVar14 != '\0') {
                        return uVar14;
                      }
                      uVar14 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)uVar14 != '\0') {
                        return uVar14;
                      }
                      if (DAT_1804b2548 == 0) {
                        lVar1 = *in_stack_00000048;
                        _iStack00000000000000b8 = *(code **)(lVar1 + 0x390);
                        in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                        in_stack_00000100 = DAT_1804b59f0;
                        uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000069c,1);
                        lVar1 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar1 + 0x390);
                        uVar10 = FUN_1800024f0(in_stack_00000048);
                        uVar11 = FUN_180002550(in_stack_00000048);
                        uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001858,5);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar15 = FUN_1800025a0(in_stack_00000048);
                        uVar13 = in_stack_00000058;
                        uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
                        (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar15);
                        uVar13 = (*_iStack00000000000000b8)
                                           (in_stack_00000048,uVar13,in_stack_000000c0,
                                            in_stack_00000100,uVar9);
                        DAT_1804b2548 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                      }
                      _bStack0000000000000050 =
                           (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b2548,in_stack_00000078);
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x560))
                                         (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                         0x583d);
                      (*pcVar2)(in_stack_00000048,uVar13,0,uVar9);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_000000e0);
                      (*pcVar2)(in_stack_00000048,uVar13,1,uVar9);
                      uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,_bStack0000000000000050,
                                          in_stack_00000080,uVar13);
                      (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar13,in_stack_00000070);
                      uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if ((char)uVar14 != '\0') {
                        return uVar14;
                      }
                      return uVar14;
                    }
                    iVar8 = (**(code **)(*in_stack_00000048 + 0x558))
                                      (in_stack_00000048,in_stack_00000138);
                    uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar14 != '\0') {
                      return uVar14;
                    }
                    iVar16 = iVar7 + (int)_iStack0000000000000178;
                    if (iVar8 <= iVar16) goto LAB_18036241d;
                    _bStack0000000000000050 = CONCAT44(uStack0000000000000051._3_4_,iVar7);
                    (**(code **)(*in_stack_00000048 + 0x640))
                              (in_stack_00000048,in_stack_00000180,iVar7,1,&stack0x00000050);
                    bVar5 = bStack0000000000000050;
                    uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar14 != '\0') {
                      return uVar14;
                    }
                    (**(code **)(*in_stack_00000048 + 0x640))
                              (in_stack_00000048,in_stack_00000138,iVar16,1,&stack0x00000050);
                    bVar4 = bStack0000000000000050;
                    uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar14 != '\0') {
                      return uVar14;
                    }
                    _bStack0000000000000050 = CONCAT71(uStack0000000000000051,bVar4 ^ bVar5);
                    (**(code **)(*in_stack_00000048 + 0x680))
                              (in_stack_00000048,in_stack_00000180,iVar7,1,&stack0x00000050);
                    uVar14 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar14 != '\0') {
                      return uVar14;
                    }
                    iVar7 = iVar7 + 1;
                    uVar14 = (**(code **)(*in_stack_00000048 + 0x720))();
                  } while ((char)uVar14 == '\0');
                }
              }
            }
          }
        }
      }
    }
  }
  return uVar14;
}

