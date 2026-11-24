/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=51 entry=18036684f blocks=44 maxInstructions=50000 */

/* cut edge: 0x18036684f -> 0x1803351d6 */
/* cut edge: 0x18036687e -> 0x1803351d6 */
/* cut edge: 0x1803668ad -> 0x1803351d6 */
/* cut edge: 0x18036690b -> 0x1803351d6 */
/* cut edge: 0x180366a73 -> 0x1803351d6 */
/* cut edge: 0x180366b60 -> 0x1803351d6 */
/* cut edge: 0x180366ba5 -> 0x1803351d6 */
/* cut edge: 0x180366d0d -> 0x1803351d6 */
/* cut edge: 0x180366dfa -> 0x1803351d6 */
/* cut edge: 0x180366e29 -> 0x1803351d6 */
/* cut edge: 0x180366fa6 -> 0x1803351d6 */
/* cut edge: 0x1803670a4 -> 0x1803351d6 */
/* cut edge: 0x1803670e7 -> 0x1803351d6 */
/* cut edge: 0x18036726e -> 0x1803351d6 */
/* cut edge: 0x18036735b -> 0x1803351d6 */
/* cut edge: 0x1803673bc -> 0x1803351d6 */
/* cut edge: 0x1803673c1 -> 0x1803351d6 */
/* cut edge: 0x18036753b -> 0x1803351d6 */
/* cut edge: 0x180367631 -> 0x1803351d6 */
/* cut edge: 0x18036767a -> 0x1803351d6 */
/* cut edge: 0x1803676be -> 0x1803351d6 */
/* cut edge: 0x18036771e -> 0x1803351d6 */
/* cut edge: 0x180367723 -> 0x18036fd8c */
/* cut edge: 0x18036773e -> 0x1803351d6 */
/* cut edge: 0x180367766 -> 0x1803678b8 */
/* cut edge: 0x180367776 -> 0x1803678b8 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803678b8 original byte 0x48 replaced by RET */
/* target 0x18036fd8c original byte 0x48 replaced by RET */


void region_00051_18036684f(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  uint uVar4;
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
  undefined4 unaff_ESI;
  int iVar15;
  uint uVar16;
  undefined8 in_stack_00000020;
  longlong *in_stack_00000048;
  char cStack0000000000000050;
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
  undefined8 in_stack_000000a8;
  code *in_stack_000000b0;
  code *in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_00000100;
  code *in_stack_00000120;
  undefined8 in_stack_00000130;
  undefined8 in_stack_000001a0;
  ulonglong in_stack_000001a8;
  undefined8 in_stack_000001b8;
  undefined8 in_stack_000001f0;
  int in_stack_00000200;
  undefined8 in_stack_00000230;
  
  uVar16 = (uint)((ulonglong)in_stack_00000020 >> 0x20);
  in_stack_000000b0 = (code *)(**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,unaff_ESI)
  ;
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000230);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  (**(code **)(*in_stack_00000048 + 0x468))
            (in_stack_00000048,in_stack_000001a0,in_stack_000001f0,in_stack_00000230,
             (ulonglong)uVar16 << 0x20);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 != '\0') {
    return;
  }
  if (DAT_1804b25d8 == 0) {
    lVar1 = *in_stack_00000048;
    in_stack_000000a0 = *(code **)(lVar1 + 0x390);
    in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
    in_stack_000000c0 = DAT_1804b59f0;
    uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000678,1);
    lVar1 = *in_stack_00000048;
    pcVar2 = *(code **)(lVar1 + 0x390);
    uVar10 = FUN_1800024f0(in_stack_00000048);
    uVar11 = FUN_180002550(in_stack_00000048);
    uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001738,5);
    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
    uVar13 = FUN_1800025a0(in_stack_00000048);
    uVar14 = in_stack_00000058;
    uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
    (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
    uVar14 = (*in_stack_000000a0)
                       (in_stack_00000048,uVar14,in_stack_000000b8,in_stack_000000c0,uVar9);
    DAT_1804b25d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
  }
  _cStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b25d8,in_stack_00000078);
  uVar14 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
  (*pcVar2)(in_stack_00000048,uVar14,0,uVar9);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001b8);
  (*pcVar2)(in_stack_00000048,uVar14,1,uVar9);
  uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar14);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar14,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  _cStack0000000000000050 = CONCAT71(uStack0000000000000051,1);
  (**(code **)(*in_stack_00000048 + 0x680))
            (in_stack_00000048,in_stack_000000b0,uVar6,1,&stack0x00000050);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 != '\0') {
    return;
  }
  if (DAT_1804b25e0 == 0) {
    lVar1 = *in_stack_00000048;
    in_stack_000000a0 = *(code **)(lVar1 + 0x390);
    in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
    in_stack_000000c0 = DAT_1804b59f0;
    uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000676,1);
    lVar1 = *in_stack_00000048;
    pcVar2 = *(code **)(lVar1 + 0x390);
    uVar10 = FUN_1800024f0(in_stack_00000048);
    uVar11 = FUN_180002550(in_stack_00000048);
    uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001728,5);
    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
    uVar13 = FUN_1800025a0(in_stack_00000048);
    uVar14 = in_stack_00000058;
    uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
    (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
    uVar14 = (*in_stack_000000a0)
                       (in_stack_00000048,uVar14,in_stack_000000b8,in_stack_000000c0,uVar9);
    DAT_1804b25e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
  }
  _cStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b25e0,in_stack_00000078);
  uVar14 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
  (*pcVar2)(in_stack_00000048,uVar14,0,uVar9);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
  (*pcVar2)(in_stack_00000048,uVar14,1,uVar9);
  uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar14);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar14,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  in_stack_000000a0 = (code *)(**(code **)(*in_stack_00000048 + 0x598))(in_stack_00000048,uVar6);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 != '\0') {
    return;
  }
  iVar15 = 0;
  do {
    if (DAT_1804b25e8 == 0) {
      lVar1 = *in_stack_00000048;
      in_stack_000000b8 = *(code **)(lVar1 + 0x390);
      in_stack_000000c0 = FUN_180333550(in_stack_00000048);
      in_stack_000000d8 = DAT_1804b59f0;
      in_stack_00000100 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000674,1);
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001718,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar14 = in_stack_00000058;
      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar14 = (*in_stack_000000b8)
                         (in_stack_00000048,uVar14,in_stack_000000c0,in_stack_000000d8,
                          in_stack_00000100);
      DAT_1804b25e8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
    }
    _cStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b25e8,in_stack_00000078);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
    (*pcVar2)(in_stack_00000048,uVar14,0,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
    (*pcVar2)(in_stack_00000048,uVar14,1,uVar9);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar14);
    iVar7 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar14,in_stack_00000070);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    if (iVar7 <= iVar15) {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 != '\0') {
        return;
      }
      uVar16 = 0;
      do {
        if (DAT_1804b25f8 == 0) {
          lVar1 = *in_stack_00000048;
          in_stack_000000b0 = *(code **)(lVar1 + 0x390);
          in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_000000c0 = DAT_1804b59f0;
          in_stack_000000d8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000670,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000016f8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar14 = in_stack_00000058;
          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
          (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar14 = (*in_stack_000000b0)
                             (in_stack_00000048,uVar14,in_stack_000000b8,in_stack_000000c0,
                              in_stack_000000d8);
          DAT_1804b25f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
        }
        _cStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b25f8,in_stack_00000078);
        uVar14 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
        (*pcVar2)(in_stack_00000048,uVar14,0,uVar9);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
        (*pcVar2)(in_stack_00000048,uVar14,1,uVar9);
        uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar14);
        iVar15 = (**(code **)(*in_stack_00000048 + 0x188))
                           (in_stack_00000048,uVar14,in_stack_00000070);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 != '\0') {
          return;
        }
        if (iVar15 <= (int)uVar16) {
          if (in_stack_00000200 < 2) {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 != '\0') {
            return;
          }
          in_stack_000001a8 = in_stack_000001a8 & 0xffffffff00000000;
          if (DAT_1804b2600 == 0) {
            lVar1 = *in_stack_00000048;
            in_stack_000000b0 = *(code **)(lVar1 + 0x390);
            in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_000000c0 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000066e,1);
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000016e8,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar14 = in_stack_00000058;
            uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar14 = (*in_stack_000000b0)
                               (in_stack_00000048,uVar14,in_stack_000000b8,in_stack_000000c0,uVar9);
            DAT_1804b2600 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
          }
          return;
        }
        _cStack0000000000000050 = uVar16;
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_000000a0,uVar16,1,&stack0x00000050);
        uVar8 = _cStack0000000000000050;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 != '\0') {
          return;
        }
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_00000130,uVar16,1,&stack0x00000050);
        uVar4 = _cStack0000000000000050;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 != '\0') {
          return;
        }
        _cStack0000000000000050 = CONCAT44(uStack0000000000000051._3_4_,uVar4 ^ uVar8);
        (**(code **)(*in_stack_00000048 + 0x698))
                  (in_stack_00000048,in_stack_000000a0,uVar16,1,&stack0x00000050);
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
              (in_stack_00000048,in_stack_000000b0,iVar15,1,&stack0x00000050);
    uVar16 = (uint)cStack0000000000000050;
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b25f0 == 0) {
      lVar1 = *in_stack_00000048;
      in_stack_000000b8 = *(code **)(lVar1 + 0x390);
      in_stack_000000c0 = FUN_180333550(in_stack_00000048);
      in_stack_000000d8 = DAT_1804b59f0;
      in_stack_00000100 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000672,1);
      lVar1 = *in_stack_00000048;
      in_stack_00000120 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001708,5);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar14 = in_stack_00000058;
      uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar12);
      (*in_stack_00000120)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar14 = (*in_stack_000000b8)
                         (in_stack_00000048,uVar14,in_stack_000000c0,in_stack_000000d8,
                          in_stack_00000100);
      DAT_1804b25f0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
    }
    _cStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b25f0,in_stack_00000078);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
    (*pcVar2)(in_stack_00000048,uVar14,0,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
    (*pcVar2)(in_stack_00000048,uVar14,1,uVar9);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar14);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar14,in_stack_00000070);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    _cStack0000000000000050 = CONCAT44(uStack0000000000000051._3_4_,uVar8 & uVar16);
    (**(code **)(*in_stack_00000048 + 0x698))
              (in_stack_00000048,in_stack_000000a0,iVar15,1,&stack0x00000050);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    iVar15 = iVar15 + 1;
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  } while (cVar5 == '\0');
  return;
}

