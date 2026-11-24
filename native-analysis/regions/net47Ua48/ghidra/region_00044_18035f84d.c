/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=44 entry=18035f84d blocks=34 maxInstructions=50000 */

/* cut edge: 0x18035f84d -> 0x1803351d6 */
/* cut edge: 0x18035f87c -> 0x180397197 */
/* cut edge: 0x18035f88b -> 0x1803351d6 */
/* cut edge: 0x18035fa08 -> 0x1803351d6 */
/* cut edge: 0x18035fb06 -> 0x1803351d6 */
/* cut edge: 0x18035fb47 -> 0x1803351d6 */
/* cut edge: 0x18035fba6 -> 0x1803351d6 */
/* cut edge: 0x18035fbab -> 0x1803351d6 */
/* cut edge: 0x18035fd10 -> 0x1803351d6 */
/* cut edge: 0x18035fe02 -> 0x1803351d6 */
/* cut edge: 0x18035ff8a -> 0x1803351d6 */
/* cut edge: 0x180360077 -> 0x1803351d6 */
/* cut edge: 0x1803600ab -> 0x1803351d6 */
/* cut edge: 0x180360213 -> 0x1803351d6 */
/* cut edge: 0x180360305 -> 0x1803351d6 */
/* cut edge: 0x18036048b -> 0x1803351d6 */
/* cut edge: 0x180360579 -> 0x1803351d6 */
/* cut edge: 0x1803606e2 -> 0x1803351d6 */
/* cut edge: 0x1803607cf -> 0x1803351d6 */
/* cut edge: 0x1803607cf -> 0x180360832 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180360832 original byte 0x48 replaced by RET */
/* target 0x180397197 original byte 0x48 replaced by RET */


void region_00044_18035f84d(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  int iVar5;
  undefined4 uVar6;
  uint uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined4 unaff_ESI;
  int iVar14;
  ulonglong uVar15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000050;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  uint uStack00000000000000a0;
  undefined4 uStack00000000000000a4;
  uint uStack00000000000000b0;
  undefined4 uStack00000000000000b4;
  int iStack00000000000000b8;
  undefined4 uStack00000000000000bc;
  code *in_stack_000000c0;
  ulonglong in_stack_000000c8;
  int iStack00000000000000d8;
  undefined4 uStack00000000000000dc;
  undefined8 in_stack_000000e0;
  code *in_stack_000000f0;
  code *in_stack_00000100;
  code *in_stack_00000120;
  undefined8 in_stack_00000138;
  undefined8 in_stack_00000148;
  undefined8 in_stack_000001b8;
  undefined8 in_stack_00000238;
  
  in_stack_00000148 = (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,unaff_ESI);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    if (in_stack_000000c8 == 0) {
      return;
    }
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      iVar14 = 0;
      do {
        if (DAT_1804b24c0 == 0) {
          lVar1 = *in_stack_00000048;
          _uStack00000000000000b0 = *(code **)(lVar1 + 0x390);
          _iStack00000000000000b8 = (code *)FUN_180333550(in_stack_00000048);
          in_stack_000000c0 = DAT_1804b59f0;
          _iStack00000000000000d8 =
               (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006be,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001968,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar12 = in_stack_00000058;
          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*_uStack00000000000000b0)
                             (in_stack_00000048,uVar12,_iStack00000000000000b8,in_stack_000000c0,
                              _iStack00000000000000d8);
          DAT_1804b24c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        in_stack_00000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b24c0,in_stack_00000078);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
        (*pcVar2)(in_stack_00000048,uVar12,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
        (*pcVar2)(in_stack_00000048,uVar12,1,uVar8);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
        iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar12,in_stack_00000070);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        if (iVar5 <= iVar14) {
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 != '\0') {
            return;
          }
          if (DAT_1804b24c8 == 0) {
            lVar1 = *in_stack_00000048;
            _uStack00000000000000a0 = *(code **)(lVar1 + 0x390);
            _uStack00000000000000b0 = (code *)FUN_180333550(in_stack_00000048);
            _iStack00000000000000b8 = DAT_1804b59f0;
            uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006bc,1);
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001958,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar12 = in_stack_00000058;
            uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
            uVar12 = (*_uStack00000000000000a0)
                               (in_stack_00000048,uVar12,_uStack00000000000000b0,
                                _iStack00000000000000b8,uVar8);
            DAT_1804b24c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          in_stack_00000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b24c8,in_stack_00000078);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
          (*pcVar2)(in_stack_00000048,uVar12,0,uVar8);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001b8
                            );
          (*pcVar2)(in_stack_00000048,uVar12,1,uVar8);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar12,in_stack_00000070);
          _uStack00000000000000a0 = (code *)CONCAT44(uStack00000000000000a4,uVar6);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 != '\0') {
            return;
          }
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 != '\0') {
            return;
          }
          uVar15 = in_stack_000000c8 ^ 0x2f43a36cc6780b97;
          if (DAT_1804b24d0 == 0) {
            lVar1 = *in_stack_00000048;
            _uStack00000000000000b0 = *(code **)(lVar1 + 0x390);
            _iStack00000000000000b8 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_000000c0 = DAT_1804b59f0;
            _iStack00000000000000d8 =
                 (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006ba,1);
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001948,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar12 = in_stack_00000058;
            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
            (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar12 = (*_uStack00000000000000b0)
                               (in_stack_00000048,uVar12,_iStack00000000000000b8,in_stack_000000c0,
                                _iStack00000000000000d8);
            DAT_1804b24d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          in_stack_00000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b24d0,in_stack_00000078);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x7bed);
          (*pcVar2)(in_stack_00000048,uVar12,0,uVar8);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,uVar15);
          (*pcVar2)(in_stack_00000048,uVar12,1,uVar8);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar12,in_stack_00000070);
          _uStack00000000000000b0 = (code *)CONCAT44(uStack00000000000000b4,uVar6);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 != '\0') {
            return;
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000238);
          _iStack00000000000000b8 = (code *)CONCAT44(uStack00000000000000bc,uVar6);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 != '\0') {
            return;
          }
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 != '\0') {
            return;
          }
          if (DAT_1804b24d8 == 0) {
            lVar1 = *in_stack_00000048;
            in_stack_000000c0 = *(code **)(lVar1 + 0x390);
            _iStack00000000000000d8 = FUN_180333550(in_stack_00000048);
            in_stack_00000100 = DAT_1804b59f0;
            uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006b8,1);
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001938,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar12 = in_stack_00000058;
            uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
            uVar12 = (*in_stack_000000c0)
                               (in_stack_00000048,uVar12,_iStack00000000000000d8,in_stack_00000100,
                                uVar8);
            DAT_1804b24d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          in_stack_00000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b24d8,in_stack_00000078);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
          (*pcVar2)(in_stack_00000048,uVar12,0,uVar8);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001b8
                            );
          (*pcVar2)(in_stack_00000048,uVar12,1,uVar8);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar12,in_stack_00000070);
          _iStack00000000000000d8 = CONCAT44(uStack00000000000000dc,uVar6);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 != '\0') {
            return;
          }
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 != '\0') {
            return;
          }
          uVar15 = in_stack_000000c8 ^ 0x1ca679c845188ab;
          if (DAT_1804b24e0 == 0) {
            lVar1 = *in_stack_00000048;
            in_stack_000000c0 = *(code **)(lVar1 + 0x390);
            in_stack_00000100 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_00000120 = DAT_1804b59f0;
            in_stack_000000f0 =
                 (code *)(**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006b6,1);
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001928,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar12 = in_stack_00000058;
            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
            (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar12 = (*in_stack_000000c0)
                               (in_stack_00000048,uVar12,in_stack_00000100,in_stack_00000120,
                                in_stack_000000f0);
            DAT_1804b24e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          in_stack_00000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b24e0,in_stack_00000078);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x78fd);
          (*pcVar2)(in_stack_00000048,uVar12,0,uVar8);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,uVar15);
          (*pcVar2)(in_stack_00000048,uVar12,1,uVar8);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
          in_stack_000000c0 =
               (code *)(**(code **)(*in_stack_00000048 + 0x188))
                                 (in_stack_00000048,uVar12,in_stack_00000070);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 != '\0') {
            return;
          }
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 != '\0') {
            return;
          }
          if (DAT_1804b24e8 == 0) {
            lVar1 = *in_stack_00000048;
            in_stack_00000100 = *(code **)(lVar1 + 0x390);
            in_stack_00000120 = (code *)FUN_180333550(in_stack_00000048);
            in_stack_000000f0 = DAT_1804b59f0;
            uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000006b4,1);
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001918,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar12 = in_stack_00000058;
            uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
            uVar12 = (*in_stack_00000100)
                               (in_stack_00000048,uVar12,in_stack_00000120,in_stack_000000f0,uVar8);
            DAT_1804b24e8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          in_stack_00000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b24e8,in_stack_00000078);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
          (*pcVar2)(in_stack_00000048,uVar12,0,uVar8);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0
                            );
          (*pcVar2)(in_stack_00000048,uVar12,1,uVar8);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar12);
          iVar14 = (**(code **)(*in_stack_00000048 + 0x188))
                             (in_stack_00000048,uVar12,in_stack_00000070);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 != '\0') {
            return;
          }
          uVar7 = ((uint)in_stack_000000c0 & iStack00000000000000d8 + iStack00000000000000b8) +
                  (uStack00000000000000b0 & uStack00000000000000a0);
          in_stack_000000c0 = (code *)(ulonglong)uVar7;
          in_stack_00000138 =
               (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,iVar14 + uVar7);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 != '\0') {
            return;
          }
          return;
        }
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,_uStack00000000000000a0,iVar14,1,&stack0x00000050);
        uVar12 = in_stack_00000050;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        in_stack_00000050 = CONCAT71(in_stack_00000050._1_7_,(char)uVar12);
        (**(code **)(*in_stack_00000048 + 0x680))
                  (in_stack_00000048,in_stack_00000148,iVar14,1,&stack0x00000050);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        iVar14 = iVar14 + 1;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
      } while (cVar4 == '\0');
    }
  }
  return;
}

