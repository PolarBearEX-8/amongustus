/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=98 entry=18039594b blocks=39 maxInstructions=50000 */

/* cut edge: 0x18039594b -> 0x1803351d6 */
/* cut edge: 0x180395aaa -> 0x1803351d6 */
/* cut edge: 0x180395c12 -> 0x1803351d6 */
/* cut edge: 0x180395d11 -> 0x1803351d6 */
/* cut edge: 0x180395e9e -> 0x1803351d6 */
/* cut edge: 0x180395f8a -> 0x1803351d6 */
/* cut edge: 0x180395fba -> 0x1803351d6 */
/* cut edge: 0x180396002 -> 0x1803351d6 */
/* cut edge: 0x180396052 -> 0x1803351d6 */
/* cut edge: 0x180396090 -> 0x1803351d6 */
/* cut edge: 0x1803960c8 -> 0x1803351d6 */
/* cut edge: 0x180396100 -> 0x1803351d6 */
/* cut edge: 0x180396139 -> 0x1803351d6 */
/* cut edge: 0x18039617c -> 0x1803351d6 */
/* cut edge: 0x1803961ba -> 0x1803351d6 */
/* cut edge: 0x180396204 -> 0x1803351d6 */
/* cut edge: 0x1803963b1 -> 0x1803351d6 */
/* cut edge: 0x1803964f8 -> 0x1803351d6 */
/* cut edge: 0x180396661 -> 0x1803351d6 */
/* cut edge: 0x1803967a9 -> 0x1803351d6 */
/* cut edge: 0x1803967bf -> 0x180396903 */
/* cut edge: 0x1803967cf -> 0x180396903 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180396903 original byte 0x48 replaced by RET */


void region_00098_18039594b(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  int iVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong lVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  ulonglong uVar15;
  longlong unaff_R13;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  uint in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  longlong in_stack_000000b8;
  ulonglong in_stack_000000c8;
  ulonglong in_stack_000000e8;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00000138;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_000001c0;
  undefined8 in_stack_000001d8;
  undefined8 in_stack_00000250;
  undefined8 in_stack_00000838;
  undefined8 in_stack_00002378;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1641);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000120);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_00000080,uVar7)
  ;
  lVar9 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000001c0);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 != '\0') {
    return;
  }
  uVar15 = lVar9 + unaff_R13 * (in_stack_000000e8 & 0xffffffff | in_stack_000000b8 << 0x20);
  if ((uVar15 == 0x8000000000000000) && (in_stack_00000070 == 0xffffffff)) {
    _in_stack_00000070 = 0;
  }
  else {
    if ((uVar15 | (longlong)(int)in_stack_00000070) >> 0x20 == 0) {
      _in_stack_00000070 = (uVar15 & 0xffffffff) % (ulonglong)in_stack_00000070;
    }
    else {
      _in_stack_00000070 = (longlong)uVar15 % (longlong)(int)in_stack_00000070;
    }
    if ((longlong)_in_stack_00000070 < 0) {
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar4 != '\0') {
        return;
      }
      if (DAT_1804b2e40 == 0) {
        lVar9 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar9 + 0x390);
        uVar7 = FUN_1803335f0(in_stack_00000048);
        uVar6 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar9 + 0x518))(in_stack_00000048,&stack0x00000458,1);
        lVar9 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar9 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar9 + 0x518))(in_stack_00000048,&stack0x00000a88,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
        (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
        DAT_1804b2e40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b2e40,in_stack_00000078);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4a33);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000138);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
      lVar9 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000001c0);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 != '\0') {
        return;
      }
      _in_stack_00000070 = _in_stack_00000070 * lVar9;
    }
  }
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar4 == '\0') {
    if (DAT_1804b2e48 == 0) {
      lVar9 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar9 + 0x390);
      uVar7 = FUN_1803335f0(in_stack_00000048);
      uVar6 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar9 + 0x518))(in_stack_00000048,&stack0x00000456,1);
      lVar9 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar9 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar9 + 0x518))(in_stack_00000048,&stack0x00000a78,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
      DAT_1804b2e48 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b2e48,in_stack_00000078);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x22ee);
    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                       in_stack_000000c8 ^ 0x6caccf5c2ec6775c);
    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
    iVar5 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000001c0);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 == '\0') {
      uVar6 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,in_stack_000001d8);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        uVar7 = FUN_18014daa0(in_stack_00000048);
        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000068,uVar7,iVar5 + in_stack_00000070);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
          uVar8 = FUN_18014d8a0(in_stack_00000048);
          (*pcVar1)(in_stack_00000048,uVar6,in_stack_000001d8,uVar8,uVar7);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x340);
            uVar7 = FUN_18014daf0(in_stack_00000048);
            (*pcVar1)(in_stack_00000048,in_stack_00000168,uVar7,uVar6);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar4 == '\0') {
              uVar6 = (**(code **)(*in_stack_00000048 + 0x420))
                                (in_stack_00000048,in_stack_000001a0,in_stack_00000250);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                (**(code **)(*in_stack_00000048 + 0x500))
                          (in_stack_00000048,in_stack_00000838,in_stack_00002378,uVar6);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar4 == '\0') {
                  pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
                  uVar6 = FUN_180333730(in_stack_00000048);
                  uVar7 = (*pcVar1)(in_stack_00000048,uVar6);
                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar4 == '\0') {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
                    uVar8 = FUN_18001f030(in_stack_00000048);
                    uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000838,uVar8);
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar4 == '\0') {
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
                      uVar10 = FUN_180333790(in_stack_00000048);
                      (*pcVar1)(in_stack_00000048,in_stack_00000838,uVar10);
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar4 == '\0') {
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                        uVar10 = FUN_1803337e0(in_stack_00000048);
                        (*pcVar1)(in_stack_00000048,uVar7,uVar6,uVar10,uVar8);
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((cVar4 == '\0') &&
                           (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
                          if (DAT_1804b2e50 == 0) {
                            lVar9 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar9 + 0x390);
                            uVar8 = FUN_180333500(in_stack_00000048);
                            uVar6 = DAT_1804b59f0;
                            uVar10 = (**(code **)(lVar9 + 0x518))
                                               (in_stack_00000048,&stack0x00000454,1);
                            lVar9 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar9 + 0x390);
                            uVar11 = FUN_1800024f0(in_stack_00000048);
                            uVar12 = FUN_180002550(in_stack_00000048);
                            uVar13 = (**(code **)(lVar9 + 0x518))
                                               (in_stack_00000048,&stack0x00005eb0,0x2a);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar14 = FUN_1800025a0(in_stack_00000048);
                            uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                            (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar6,uVar10
                                             );
                            DAT_1804b2e50 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b2e50,in_stack_00000078);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,5,in_stack_00000088,0);
                          (**(code **)(*in_stack_00000048 + 0x570))
                                    (in_stack_00000048,uVar8,0,in_stack_00000168);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                              in_stack_00000170);
                          (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
                          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,2,uVar7)
                          ;
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             0xdfc79c58f7839374);
                          (*pcVar1)(in_stack_00000048,uVar8,3,uVar7);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                             in_stack_000000c8);
                          (*pcVar1)(in_stack_00000048,uVar8,4,uVar7);
                          (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_00000080,uVar8);
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((cVar4 == '\0') &&
                             (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
                            if (DAT_1804b2e58 == 0) {
                              lVar9 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar9 + 0x390);
                              uVar7 = FUN_180333500(in_stack_00000048);
                              uVar6 = DAT_1804b59f0;
                              uVar8 = (**(code **)(lVar9 + 0x518))
                                                (in_stack_00000048,&stack0x00000452,1);
                              lVar9 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar9 + 0x390);
                              uVar10 = FUN_1800024f0(in_stack_00000048);
                              uVar11 = FUN_180002550(in_stack_00000048);
                              uVar12 = (**(code **)(lVar9 + 0x518))
                                                 (in_stack_00000048,&stack0x00005e80,0x18);
                              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar13 = FUN_1800025a0(in_stack_00000048);
                              uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
                              (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                              uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,
                                                uVar8);
                              DAT_1804b2e58 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                            }
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b2e58,in_stack_00000078);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,4,in_stack_00000088,0);
                            (**(code **)(*in_stack_00000048 + 0x570))
                                      (in_stack_00000048,uVar7,0,in_stack_00000168);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = FUN_180012f20(in_stack_00000048);
                            uVar10 = FUN_1803dae60(in_stack_00000048);
                            uVar8 = (*pcVar1)(in_stack_00000048,uVar8,uVar10,0);
                            (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,0xdfd71969f05ea083);
                            (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,in_stack_000000c8);
                            (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
                            (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((cVar4 == '\0') &&
                               (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0'))
                            {
                              if (DAT_1804b2e60 == 0) {
                                lVar9 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar9 + 0x390);
                                uVar7 = FUN_180333500(in_stack_00000048);
                                uVar6 = DAT_1804b59f0;
                                uVar8 = (**(code **)(lVar9 + 0x518))
                                                  (in_stack_00000048,&stack0x00000450,1);
                                lVar9 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar9 + 0x390);
                                uVar10 = FUN_1800024f0(in_stack_00000048);
                                uVar11 = FUN_180002550(in_stack_00000048);
                                uVar12 = (**(code **)(lVar9 + 0x518))
                                                   (in_stack_00000048,&stack0x00002910,0xd);
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar13 = FUN_1800025a0(in_stack_00000048);
                                uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
                                (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,
                                                  uVar8);
                                DAT_1804b2e60 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar6);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
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
            }
          }
        }
      }
    }
  }
  return;
}

