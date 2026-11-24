/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=48 entry=180363912 blocks=31 maxInstructions=50000 */

/* cut edge: 0x180363912 -> 0x1803351d6 */
/* cut edge: 0x180363a04 -> 0x1803351d6 */
/* cut edge: 0x180363bac -> 0x1803351d6 */
/* cut edge: 0x180363caa -> 0x1803351d6 */
/* cut edge: 0x180363cee -> 0x1803351d6 */
/* cut edge: 0x180363e5e -> 0x1803351d6 */
/* cut edge: 0x180363f4c -> 0x1803351d6 */
/* cut edge: 0x180363f96 -> 0x1803351d6 */
/* cut edge: 0x18036412a -> 0x1803351d6 */
/* cut edge: 0x180364217 -> 0x1803351d6 */
/* cut edge: 0x1803643b0 -> 0x1803351d6 */
/* cut edge: 0x18036449d -> 0x1803351d6 */
/* cut edge: 0x180364537 -> 0x1803351d6 */
/* cut edge: 0x18036453c -> 0x1803351d6 */
/* cut edge: 0x1803646ac -> 0x1803351d6 */
/* cut edge: 0x18036479e -> 0x1803351d6 */
/* cut edge: 0x1803647b4 -> 0x18036490f */
/* cut edge: 0x1803647c4 -> 0x18036490f */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18036490f original byte 0x48 replaced by RET */


void region_00048_180363912(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  char cVar4;
  byte bVar5;
  int iVar6;
  uint uVar7;
  uint uVar8;
  undefined4 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 unaff_R12;
  uint uVar16;
  uint unaff_R15D;
  bool bVar17;
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
  ulonglong in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  uint uStack00000000000000b8;
  undefined4 uStack00000000000000bc;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  code *in_stack_000000f0;
  code *in_stack_000000f8;
  uint uStack0000000000000100;
  undefined4 uStack0000000000000104;
  code *in_stack_00000118;
  code *in_stack_00000120;
  code *in_stack_00000128;
  uint in_stack_00000190;
  undefined8 in_stack_000001b8;
  undefined8 in_stack_000001d0;
  
  _bStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
  (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
  (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar10);
  in_stack_00000190 =
       (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    in_stack_00000190 = in_stack_00000190 & unaff_R15D;
    bVar17 = in_stack_00000190 != 0;
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      in_stack_000000b0 = in_stack_000000b0 & 0xffffffff00000000 | (ulonglong)bVar17;
      in_stack_000000a0 = in_stack_000000a0 & 0xffffffff00000000;
      while( true ) {
        if (DAT_1804b2580 == 0) {
          lVar2 = *in_stack_00000048;
          _uStack00000000000000b8 = *(code **)(lVar2 + 0x390);
          _uStack0000000000000100 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_00000120 = DAT_1804b59f0;
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000068e,1);
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar12 = FUN_1800024f0(in_stack_00000048);
          uVar13 = FUN_180002550(in_stack_00000048);
          uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000017e8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar15 = FUN_1800025a0(in_stack_00000048);
          uVar10 = in_stack_00000058;
          uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
          (*pcVar1)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
          unaff_R12 = in_stack_000000c0;
          uVar10 = (*_uStack00000000000000b8)
                             (in_stack_00000048,uVar10,_uStack0000000000000100,in_stack_00000120,
                              uVar11);
          DAT_1804b2580 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
        }
        _bStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2580,in_stack_00000078);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
        (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001b8)
        ;
        (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar10);
        iVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar10,in_stack_00000070);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        if (iVar6 <= (int)in_stack_000000a0) break;
        (**(code **)(*in_stack_00000048 + 0x640))
                  (in_stack_00000048,unaff_R12,in_stack_000000a0,1,&stack0x00000050);
        _uStack00000000000000b8 =
             (code *)CONCAT44(uStack00000000000000bc,(uint)bStack0000000000000050);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar4 != '\0') {
          return;
        }
        if (DAT_1804b2588 == 0) {
          lVar2 = *in_stack_00000048;
          _uStack0000000000000100 = *(code **)(lVar2 + 0x390);
          in_stack_00000120 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_000000f0 = DAT_1804b59f0;
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000068c,1);
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar12 = FUN_1800024f0(in_stack_00000048);
          uVar13 = FUN_180002550(in_stack_00000048);
          uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000017d8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar15 = FUN_1800025a0(in_stack_00000048);
          uVar10 = in_stack_00000058;
          uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
          (*pcVar1)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
          unaff_R12 = in_stack_000000c0;
          uVar10 = (*_uStack0000000000000100)
                             (in_stack_00000048,uVar10,in_stack_00000120,in_stack_000000f0,uVar11);
          DAT_1804b2588 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
        }
        _bStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2588,in_stack_00000078);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
        (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8)
        ;
        (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar10);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar10,in_stack_00000070);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        (**(code **)(*in_stack_00000048 + 0x640))
                  (in_stack_00000048,unaff_R12,(int)in_stack_000000a0 + 1,1,&stack0x00000050);
        uVar16 = (uint)(char)bStack0000000000000050;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar4 != '\0') {
          return;
        }
        if (DAT_1804b2590 == 0) {
          lVar2 = *in_stack_00000048;
          _uStack0000000000000100 = *(code **)(lVar2 + 0x390);
          in_stack_00000120 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_000000f0 = DAT_1804b59f0;
          in_stack_000000f8 =
               (code *)(**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000068a,1);
          lVar2 = *in_stack_00000048;
          in_stack_00000118 = *(code **)(lVar2 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000017c8,5);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar10 = in_stack_00000058;
          uVar14 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar14);
          unaff_R12 = in_stack_000000c0;
          (*in_stack_00000118)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          uVar10 = (*_uStack0000000000000100)
                             (in_stack_00000048,uVar10,in_stack_00000120,in_stack_000000f0,
                              in_stack_000000f8);
          DAT_1804b2590 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
        }
        _bStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2590,in_stack_00000078);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
        (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8)
        ;
        (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar10);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar10,in_stack_00000070);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar4 != '\0') {
          return;
        }
        if (DAT_1804b2598 == 0) {
          _uStack0000000000000100 = (code *)CONCAT44(uStack0000000000000104,uVar16);
          lVar2 = *in_stack_00000048;
          in_stack_00000120 = *(code **)(lVar2 + 0x390);
          in_stack_000000f0 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_000000f8 = DAT_1804b59f0;
          in_stack_00000118 =
               (code *)(**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000688,1);
          lVar2 = *in_stack_00000048;
          in_stack_00000128 = *(code **)(lVar2 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000017b8,5);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar10 = in_stack_00000058;
          uVar14 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar14);
          (*in_stack_00000128)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          unaff_R12 = in_stack_000000c0;
          uVar10 = (*in_stack_00000120)
                             (in_stack_00000048,uVar10,in_stack_000000f0,in_stack_000000f8,
                              in_stack_00000118);
          DAT_1804b2598 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
          uVar16 = uStack0000000000000100;
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
        }
        _bStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2598,in_stack_00000078);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x79b1);
        (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001d0)
        ;
        (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar10);
        bVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar10,in_stack_00000070);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        _bStack0000000000000050 =
             CONCAT71(uStack0000000000000051,
                      (byte)((uVar8 & uVar16) >> (bVar5 & 0x1f)) |
                      (byte)(((uVar7 & uStack00000000000000b8) << 0x19) >> 0x18));
        (**(code **)(*in_stack_00000048 + 0x680))
                  (in_stack_00000048,unaff_R12,in_stack_000000a0,1,&stack0x00000050);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        in_stack_000000a0 =
             in_stack_000000a0 & 0xffffffff00000000 | (ulonglong)((int)in_stack_000000a0 + 1);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar4 != '\0') {
          return;
        }
      }
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar4 == '\0') {
        if (DAT_1804b25a0 == 0) {
          lVar2 = *in_stack_00000048;
          _uStack00000000000000b8 = *(code **)(lVar2 + 0x390);
          _uStack0000000000000100 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_00000120 = DAT_1804b59f0;
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000686,1);
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar12 = FUN_1800024f0(in_stack_00000048);
          uVar13 = FUN_180002550(in_stack_00000048);
          uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000017a8,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar15 = FUN_1800025a0(in_stack_00000048);
          uVar10 = in_stack_00000058;
          uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
          (*pcVar1)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
          uVar10 = (*_uStack00000000000000b8)
                             (in_stack_00000048,uVar10,_uStack0000000000000100,in_stack_00000120,
                              uVar11);
          DAT_1804b25a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
        }
        _bStack0000000000000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b25a0,in_stack_00000078);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
        (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001b8)
        ;
        (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar10);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar10,in_stack_00000070);
        _uStack00000000000000b8 = (code *)CONCAT44(uStack00000000000000bc,uVar9);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar4 == '\0') && (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0'))
        {
          if (DAT_1804b25a8 == 0) {
            lVar2 = *in_stack_00000048;
            _uStack0000000000000100 = *(code **)(lVar2 + 0x390);
            in_stack_00000120 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_000000f0 = DAT_1804b59f0;
            uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000684,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar12 = FUN_1800024f0(in_stack_00000048);
            uVar13 = FUN_180002550(in_stack_00000048);
            uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001798,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar15 = FUN_1800025a0(in_stack_00000048);
            uVar10 = in_stack_00000058;
            uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
            (*pcVar1)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
            uVar10 = (*_uStack0000000000000100)
                               (in_stack_00000048,uVar10,in_stack_00000120,in_stack_000000f0,uVar11)
            ;
            DAT_1804b25a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
          }
          return;
        }
      }
    }
  }
  return;
}

