/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=47 entry=1803628f6 blocks=40 maxInstructions=50000 */

/* cut edge: 0x1803628f6 -> 0x1803351d6 */
/* cut edge: 0x180362920 -> 0x1803351d6 */
/* cut edge: 0x180362a98 -> 0x1803351d6 */
/* cut edge: 0x180362b85 -> 0x1803351d6 */
/* cut edge: 0x180362bdb -> 0x1803351d6 */
/* cut edge: 0x180362d5d -> 0x1803351d6 */
/* cut edge: 0x180362e4a -> 0x1803652aa */
/* cut edge: 0x180362e57 -> 0x1803351d6 */
/* cut edge: 0x180362e7a -> 0x1803351d6 */
/* cut edge: 0x180362ff2 -> 0x1803351d6 */
/* cut edge: 0x1803630df -> 0x18036526c */
/* cut edge: 0x1803630ec -> 0x1803351d6 */
/* cut edge: 0x180363137 -> 0x1803351d6 */
/* cut edge: 0x1803632ba -> 0x1803351d6 */
/* cut edge: 0x1803633b8 -> 0x1803351d6 */
/* cut edge: 0x18036353a -> 0x1803351d6 */
/* cut edge: 0x180363638 -> 0x1803351d6 */
/* cut edge: 0x18036367f -> 0x1803351d6 */
/* cut edge: 0x1803636c4 -> 0x1803351d6 */
/* cut edge: 0x180363749 -> 0x1803351d6 */
/* cut edge: 0x18036374e -> 0x1803351d6 */
/* cut edge: 0x18036378f -> 0x1803351d6 */
/* cut edge: 0x1803637a5 -> 0x180363912 */
/* cut edge: 0x1803637b5 -> 0x180363912 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180363912 original byte 0x48 replaced by RET */
/* target 0x18036526c original byte 0x48 replaced by RET */
/* target 0x1803652aa original byte 0x48 replaced by RET */


void region_00047_1803628f6(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  byte bVar4;
  byte bVar5;
  char cVar6;
  int iVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined4 unaff_ESI;
  uint uVar14;
  ulonglong in_stack_00000020;
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
  ulonglong in_stack_000000b0;
  code *in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_000000f0;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00000148;
  int in_stack_00000150;
  undefined8 in_stack_00000158;
  undefined8 in_stack_00000180;
  undefined8 in_stack_000001a0;
  int iStack00000000000001a8;
  undefined8 in_stack_000001d0;
  undefined8 in_stack_000001f0;
  
  uVar8 = (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,unaff_ESI);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((cVar6 == '\0') && (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
    in_stack_000000c0 = uVar8;
    if (DAT_1804b2550 == 0) {
      lVar1 = *in_stack_00000048;
      in_stack_000000b8 = *(code **)(lVar1 + 0x390);
      in_stack_00000100 = FUN_180333550(in_stack_00000048);
      in_stack_00000120 = DAT_1804b59f0;
      in_stack_00000020 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000069a,1);
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001848,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar12 = in_stack_00000058;
      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
      (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar8 = in_stack_000000c0;
      uVar12 = (*in_stack_000000b8)
                         (in_stack_00000048,uVar12,in_stack_00000100,in_stack_00000120,
                          in_stack_00000020);
      DAT_1804b2550 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    _bStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2550,in_stack_00000078);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
    (*pcVar2)(in_stack_00000048,uVar12,0,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
    (*pcVar2)(in_stack_00000048,uVar12,1,uVar9);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar12);
    (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000070);
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar6 == '\0') {
      (**(code **)(*in_stack_00000048 + 0x468))
                (in_stack_00000048,in_stack_000001a0,in_stack_000001f0,in_stack_00000180,
                 in_stack_00000020 & 0xffffffff00000000);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((cVar6 == '\0') && (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
        _iStack00000000000001a8 = _iStack00000000000001a8 & 0xffffffff00000000;
        if (DAT_1804b2558 == 0) {
          lVar1 = *in_stack_00000048;
          in_stack_000000b8 = *(code **)(lVar1 + 0x390);
          in_stack_00000100 = FUN_180333550(in_stack_00000048);
          in_stack_00000120 = DAT_1804b59f0;
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000698,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001838,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar12 = in_stack_00000058;
          uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
          (*pcVar2)(in_stack_00000048,uVar8,uVar10,uVar11,uVar13);
          uVar8 = in_stack_000000c0;
          uVar12 = (*in_stack_000000b8)
                             (in_stack_00000048,uVar12,in_stack_00000100,in_stack_00000120,uVar9);
          DAT_1804b2558 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        _bStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2558,in_stack_00000078);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
        (*pcVar2)(in_stack_00000048,uVar12,0,uVar9);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
        (*pcVar2)(in_stack_00000048,uVar12,1,uVar9);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar12);
        iVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar12,in_stack_00000070);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          if (iVar7 <= iStack00000000000001a8) {
            return;
          }
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar6 == '\0') {
            in_stack_00000150 = 0;
            if (DAT_1804b2560 == 0) {
              lVar1 = *in_stack_00000048;
              in_stack_000000b8 = *(code **)(lVar1 + 0x390);
              in_stack_00000100 = FUN_180333550(in_stack_00000048);
              in_stack_00000120 = DAT_1804b59f0;
              uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000696,1);
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001828,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar13 = FUN_1800025a0(in_stack_00000048);
              uVar12 = in_stack_00000058;
              uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
              (*pcVar2)(in_stack_00000048,uVar8,uVar10,uVar11,uVar13);
              uVar8 = in_stack_000000c0;
              uVar12 = (*in_stack_000000b8)
                                 (in_stack_00000048,uVar12,in_stack_00000100,in_stack_00000120,uVar9
                                 );
              DAT_1804b2560 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            _bStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2560,in_stack_00000078);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_00000088,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1193);
            (*pcVar2)(in_stack_00000048,uVar12,0,uVar9);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_00000158);
            (*pcVar2)(in_stack_00000048,uVar12,1,uVar9);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar12);
            iVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar12,in_stack_00000070);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              if (iVar7 <= in_stack_00000150) {
                return;
              }
              (**(code **)(*in_stack_00000048 + 0x640))
                        (in_stack_00000048,in_stack_00000148,_iStack00000000000001a8,1,
                         &stack0x00000050);
              uVar14 = (uint)(char)bStack0000000000000050;
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((cVar6 == '\0') &&
                 (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
                if (DAT_1804b2568 == 0) {
                  lVar1 = *in_stack_00000048;
                  in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                  in_stack_00000100 = FUN_180333550(in_stack_00000048);
                  in_stack_00000120 = DAT_1804b59f0;
                  in_stack_000000f0 =
                       (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000694,1);
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar8 = FUN_1800024f0(in_stack_00000048);
                  uVar9 = FUN_180002550(in_stack_00000048);
                  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001818,5);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar11 = FUN_1800025a0(in_stack_00000048);
                  uVar12 = in_stack_00000058;
                  uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                  (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                  uVar8 = in_stack_000000c0;
                  uVar12 = (*in_stack_000000b8)
                                     (in_stack_00000048,uVar12,in_stack_00000100,in_stack_00000120,
                                      in_stack_000000f0);
                  DAT_1804b2568 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                }
                _bStack0000000000000050 =
                     (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b2568,in_stack_00000078);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                   (in_stack_00000048,2,in_stack_00000088,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x79b1);
                (*pcVar2)(in_stack_00000048,uVar12,0,uVar9);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_000001d0);
                (*pcVar2)(in_stack_00000048,uVar12,1,uVar9);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,
                                    uVar12);
                iVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar12,in_stack_00000070);
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar6 == '\0') {
                  if ((uVar14 >> (iVar7 - in_stack_00000150 & 0x1fU) & 1) == 0) {
LAB_18036374e:
                    (**(code **)(*in_stack_00000048 + 0x640))
                              (in_stack_00000048,uVar8,0,1,&stack0x00000050);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((cVar6 == '\0') &&
                       (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
                      if (DAT_1804b2578 == 0) {
                        lVar1 = *in_stack_00000048;
                        in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                        in_stack_00000100 = FUN_180333550(in_stack_00000048);
                        in_stack_00000120 = DAT_1804b59f0;
                        in_stack_000000f0 =
                             (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000690,1);
                        lVar1 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar1 + 0x390);
                        uVar12 = FUN_1800024f0(in_stack_00000048);
                        uVar9 = FUN_180002550(in_stack_00000048);
                        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000017f8,5);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar11 = FUN_1800025a0(in_stack_00000048);
                        uVar8 = in_stack_00000058;
                        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                        (*pcVar2)(in_stack_00000048,uVar12,uVar9,uVar10,uVar11);
                        uVar8 = (*in_stack_000000b8)
                                          (in_stack_00000048,uVar8,in_stack_00000100,
                                           in_stack_00000120,in_stack_000000f0);
                        DAT_1804b2578 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                      }
                      return;
                    }
                  }
                  else {
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar6 == '\0') {
                      in_stack_000000b0 = in_stack_000000b0 & 0xffffffff00000000;
                      do {
                        if (DAT_1804b2570 == 0) {
                          lVar1 = *in_stack_00000048;
                          in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                          in_stack_00000100 = FUN_180333550(in_stack_00000048);
                          in_stack_00000120 = DAT_1804b59f0;
                          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000692,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar8 = FUN_1800024f0(in_stack_00000048);
                          uVar10 = FUN_180002550(in_stack_00000048);
                          uVar11 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00001808,5);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar13 = FUN_1800025a0(in_stack_00000048);
                          uVar12 = in_stack_00000058;
                          uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
                          (*pcVar2)(in_stack_00000048,uVar8,uVar10,uVar11,uVar13);
                          uVar8 = in_stack_000000c0;
                          uVar12 = (*in_stack_000000b8)
                                             (in_stack_00000048,uVar12,in_stack_00000100,
                                              in_stack_00000120,uVar9);
                          DAT_1804b2570 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                        }
                        _bStack0000000000000050 =
                             (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b2570,in_stack_00000078);
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                           (in_stack_00000048,2,in_stack_00000088,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                           0x583d);
                        (*pcVar2)(in_stack_00000048,uVar12,0,uVar9);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_000000e0);
                        (*pcVar2)(in_stack_00000048,uVar12,1,uVar9);
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,_bStack0000000000000050,
                                            in_stack_00000080,uVar12);
                        iVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                                          (in_stack_00000048,uVar12,in_stack_00000070);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 != '\0') {
                          return;
                        }
                        if (iVar7 <= (int)in_stack_000000b0) goto LAB_18036374e;
                        _bStack0000000000000050 =
                             CONCAT44(uStack0000000000000051._3_4_,(int)in_stack_000000b0);
                        (**(code **)(*in_stack_00000048 + 0x640))
                                  (in_stack_00000048,in_stack_000000d8,in_stack_000000b0,1,
                                   &stack0x00000050);
                        bVar4 = bStack0000000000000050;
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 != '\0') {
                          return;
                        }
                        (**(code **)(*in_stack_00000048 + 0x640))
                                  (in_stack_00000048,uVar8,in_stack_000000b0,1,&stack0x00000050);
                        bVar5 = bStack0000000000000050;
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 != '\0') {
                          return;
                        }
                        _bStack0000000000000050 = CONCAT71(uStack0000000000000051,bVar5 ^ bVar4);
                        (**(code **)(*in_stack_00000048 + 0x680))
                                  (in_stack_00000048,in_stack_000000d8,in_stack_000000b0,1,
                                   &stack0x00000050);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 != '\0') {
                          return;
                        }
                        in_stack_000000b0 =
                             in_stack_000000b0 & 0xffffffff00000000 |
                             (ulonglong)((int)in_stack_000000b0 + 1);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      } while (cVar6 == '\0');
                    }
                  }
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

