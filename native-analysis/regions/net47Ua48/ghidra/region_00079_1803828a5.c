/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=79 entry=1803828a5 blocks=35 maxInstructions=50000 */

/* cut edge: 0x1803828a5 -> 0x1803351d6 */
/* cut edge: 0x180382992 -> 0x1803351d6 */
/* cut edge: 0x1803829c1 -> 0x1803351d6 */
/* cut edge: 0x180382b29 -> 0x1803351d6 */
/* cut edge: 0x180382c16 -> 0x1803351d6 */
/* cut edge: 0x180382c45 -> 0x1803351d6 */
/* cut edge: 0x180382de5 -> 0x1803351d6 */
/* cut edge: 0x180382edb -> 0x1803351d6 */
/* cut edge: 0x180383056 -> 0x1803351d6 */
/* cut edge: 0x180383143 -> 0x1803351d6 */
/* cut edge: 0x180383188 -> 0x1803351d6 */
/* cut edge: 0x1803831d3 -> 0x1803351d6 */
/* cut edge: 0x18038334e -> 0x1803351d6 */
/* cut edge: 0x18038343b -> 0x1803351d6 */
/* cut edge: 0x180383484 -> 0x1803351d6 */
/* cut edge: 0x1803834fc -> 0x1803351d6 */
/* cut edge: 0x180383501 -> 0x1803351d6 */
/* cut edge: 0x180383689 -> 0x1803351d6 */
/* cut edge: 0x180383772 -> 0x1803351d6 */
/* cut edge: 0x1803837b2 -> 0x180383910 */
/* cut edge: 0x1803837c2 -> 0x180383910 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180383910 original byte 0x48 replaced by RET */


void region_00079_1803828a5(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  undefined1 uVar4;
  char cVar5;
  undefined4 uVar6;
  int iVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  ulonglong uVar14;
  int iVar15;
  longlong *in_stack_00000048;
  undefined1 uStack0000000000000050;
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
  undefined8 in_stack_000000a0;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  int iStack00000000000000d0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  code *in_stack_000000f0;
  undefined8 in_stack_000000f8;
  code *in_stack_00000100;
  code *in_stack_00000110;
  undefined8 in_stack_00000118;
  undefined8 in_stack_00000148;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x3979);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c0);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    in_stack_000000d8 = (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,uVar6);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
      if (DAT_1804b2b00 == 0) {
        lVar2 = *in_stack_00000048;
        _iStack00000000000000d0 = *(code **)(lVar2 + 0x390);
        in_stack_000000b0 = FUN_180333550(in_stack_00000048);
        in_stack_00000100 = DAT_1804b59f0;
        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000052e,1);
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ce8,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar8 = in_stack_00000058;
        uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
        (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar8 = (*_iStack00000000000000d0)
                          (in_stack_00000048,uVar8,in_stack_000000b0,in_stack_00000100,uVar9);
        DAT_1804b2b00 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      _uStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2b00,in_stack_00000078);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x3979);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c0);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        in_stack_00000100 =
             (code *)(**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,uVar6);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0'))
        {
          in_stack_000000b0 = in_stack_000000c8 ^ 0x13ac91048c06637d;
          _iStack00000000000000d0 = (code *)0x0;
          iVar15 = 0;
          while( true ) {
            if (DAT_1804b2b08 == 0) {
              lVar2 = *in_stack_00000048;
              in_stack_000000f0 = *(code **)(lVar2 + 0x390);
              in_stack_000000f8 = FUN_180333550(in_stack_00000048);
              in_stack_00000110 = DAT_1804b59f0;
              in_stack_00000118 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000052c,1)
              ;
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000cd8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar8 = in_stack_00000058;
              uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
              (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar8 = (*in_stack_000000f0)
                                (in_stack_00000048,uVar8,in_stack_000000f8,in_stack_00000110,
                                 in_stack_00000118);
              DAT_1804b2b08 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2b08,in_stack_00000078);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x13fc);
            (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000000b0);
            (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
            iVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar8,in_stack_00000070);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            if (iVar7 <= iVar15) break;
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 != '\0') {
              return;
            }
            if (DAT_1804b2b10 == 0) {
              lVar2 = *in_stack_00000048;
              in_stack_000000f0 = *(code **)(lVar2 + 0x390);
              in_stack_000000f8 = FUN_180333550(in_stack_00000048);
              in_stack_00000110 = DAT_1804b59f0;
              in_stack_00000118 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000052a,1)
              ;
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000cc8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar8 = in_stack_00000058;
              uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
              (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar8 = (*in_stack_000000f0)
                                (in_stack_00000048,uVar8,in_stack_000000f8,in_stack_00000110,
                                 in_stack_00000118);
              DAT_1804b2b10 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2b10,in_stack_00000078);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
            (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000000e0);
            (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
            iVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar8,in_stack_00000070);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            (**(code **)(*in_stack_00000048 + 0x640))
                      (in_stack_00000048,in_stack_000000a0,iVar15,1,&stack0x00000050);
            uVar4 = uStack0000000000000050;
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            _uStack0000000000000050 = CONCAT71(uStack0000000000000051,uVar4);
            (**(code **)(*in_stack_00000048 + 0x680))
                      (in_stack_00000048,in_stack_000000d8,iVar7 + iStack00000000000000d0,1,
                       &stack0x00000050);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 != '\0') {
              return;
            }
            if (DAT_1804b2b18 == 0) {
              lVar2 = *in_stack_00000048;
              in_stack_000000f0 = *(code **)(lVar2 + 0x390);
              in_stack_000000f8 = FUN_180333550(in_stack_00000048);
              in_stack_00000110 = DAT_1804b59f0;
              in_stack_00000118 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000528,1)
              ;
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000cb8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar8 = in_stack_00000058;
              uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
              (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar8 = (*in_stack_000000f0)
                                (in_stack_00000048,uVar8,in_stack_000000f8,in_stack_00000110,
                                 in_stack_00000118);
              DAT_1804b2b18 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2b18,in_stack_00000078);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
            (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000000e0);
            (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
            iVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar8,in_stack_00000070);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            (**(code **)(*in_stack_00000048 + 0x640))
                      (in_stack_00000048,in_stack_000000a0,iVar15 + 1,1,&stack0x00000050);
            uVar4 = uStack0000000000000050;
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            _uStack0000000000000050 = CONCAT71(uStack0000000000000051,uVar4);
            (**(code **)(*in_stack_00000048 + 0x680))
                      (in_stack_00000048,in_stack_00000100,iVar7 + iStack00000000000000d0,1,
                       &stack0x00000050);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 != '\0') {
              return;
            }
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            _iStack00000000000000d0 = (code *)(ulonglong)((int)_iStack00000000000000d0 + 1);
            iVar15 = iVar15 + 2;
            if (cVar5 != '\0') {
              return;
            }
          }
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            uVar14 = in_stack_000000c8 ^ 0x366bb9b992054fc9;
            if (DAT_1804b2b20 == 0) {
              lVar2 = *in_stack_00000048;
              _iStack00000000000000d0 = *(code **)(lVar2 + 0x390);
              in_stack_000000b0 = FUN_180333550(in_stack_00000048);
              in_stack_000000f0 = DAT_1804b59f0;
              in_stack_000000f8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000526,1)
              ;
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ca8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar8 = in_stack_00000058;
              uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
              (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar8 = (*_iStack00000000000000d0)
                                (in_stack_00000048,uVar8,in_stack_000000b0,in_stack_000000f0,
                                 in_stack_000000f8);
              DAT_1804b2b20 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2b20,in_stack_00000078);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x3ca1);
            (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,uVar14);
            (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar8);
            (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((cVar5 == '\0') &&
               (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
              _iStack00000000000000d0 = (code *)(in_stack_000000c8 ^ 0x51c29dff93540ced);
              in_stack_000000b0 = in_stack_00000148;
              if (DAT_1804b2b28 == 0) {
                lVar2 = *in_stack_00000048;
                in_stack_000000f0 = *(code **)(lVar2 + 0x390);
                in_stack_000000f8 = FUN_180333550(in_stack_00000048);
                in_stack_00000110 = DAT_1804b59f0;
                in_stack_00000118 =
                     (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000524,1);
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000c98,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar8 = in_stack_00000058;
                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar8 = (*in_stack_000000f0)
                                  (in_stack_00000048,uVar8,in_stack_000000f8,in_stack_00000110,
                                   in_stack_00000118);
                DAT_1804b2b28 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
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

