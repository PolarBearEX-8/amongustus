/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=67 entry=180376841 blocks=33 maxInstructions=50000 */

/* cut edge: 0x180376841 -> 0x1803351d6 */
/* cut edge: 0x18037688c -> 0x1803351d6 */
/* cut edge: 0x1803769f4 -> 0x1803351d6 */
/* cut edge: 0x180376ae1 -> 0x1803351d6 */
/* cut edge: 0x180376b2c -> 0x1803351d6 */
/* cut edge: 0x180376c94 -> 0x1803351d6 */
/* cut edge: 0x180376d82 -> 0x1803351d6 */
/* cut edge: 0x180376ef8 -> 0x1803351d6 */
/* cut edge: 0x180376fe5 -> 0x1803351d6 */
/* cut edge: 0x180377029 -> 0x1803351d6 */
/* cut edge: 0x18037706d -> 0x1803351d6 */
/* cut edge: 0x1803771d5 -> 0x1803351d6 */
/* cut edge: 0x1803772c2 -> 0x1803351d6 */
/* cut edge: 0x18037730d -> 0x1803351d6 */
/* cut edge: 0x180377475 -> 0x1803351d6 */
/* cut edge: 0x180377562 -> 0x1803351d6 */
/* cut edge: 0x1803775ad -> 0x1803351d6 */
/* cut edge: 0x180377715 -> 0x1803351d6 */
/* cut edge: 0x180377803 -> 0x1803351d6 */
/* cut edge: 0x180377819 -> 0x180377979 */
/* cut edge: 0x180377829 -> 0x180377979 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180377979 original byte 0x48 replaced by RET */


void region_00067_180376841(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  undefined4 uVar5;
  undefined4 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined4 unaff_ESI;
  longlong *in_stack_00000048;
  undefined4 uStack0000000000000050;
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
  undefined4 uStack00000000000000b0;
  undefined4 uStack00000000000000b4;
  code *in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_00000100;
  undefined8 in_stack_000001b8;
  undefined8 in_stack_00000210;
  
  _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uStack00000000000000b0);
  (**(code **)(*in_stack_00000048 + 0x698))
            (in_stack_00000048,in_stack_000000a0,unaff_ESI,1,&stack0x00000050);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      if (DAT_1804b28c0 == 0) {
        lVar1 = *in_stack_00000048;
        _uStack00000000000000b0 = *(code **)(lVar1 + 0x390);
        in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
        in_stack_000000c0 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000005be,1);
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001168,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar12 = in_stack_00000058;
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*_uStack00000000000000b0)
                           (in_stack_00000048,uVar12,in_stack_000000b8,in_stack_000000c0,uVar7);
        DAT_1804b28c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      _uStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b28c0,in_stack_00000078);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a10);
      (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e8);
      (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar12);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000070);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
        _uStack00000000000000b0 = (code *)CONCAT44(uStack00000000000000b4,uStack0000000000000050);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 == '\0') {
            if (DAT_1804b28c8 == 0) {
              lVar1 = *in_stack_00000048;
              in_stack_000000b8 = *(code **)(lVar1 + 0x390);
              in_stack_000000c0 = FUN_180333550(in_stack_00000048);
              in_stack_000000d8 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000005bc,1);
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001158,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar12 = in_stack_00000058;
              uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
              (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*in_stack_000000b8)
                                 (in_stack_00000048,uVar12,in_stack_000000c0,in_stack_000000d8,uVar7
                                 );
              DAT_1804b28c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b28c8,in_stack_00000078);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_00000088,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a10);
            (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000000e8);
            (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar12);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar12,in_stack_00000070);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar4 == '\0') {
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar4 == '\0') {
                if (DAT_1804b28d0 == 0) {
                  lVar1 = *in_stack_00000048;
                  in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                  in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                  in_stack_000000d8 = DAT_1804b59f0;
                  in_stack_00000100 =
                       (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000005ba,1);
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_1800024f0(in_stack_00000048);
                  uVar8 = FUN_180002550(in_stack_00000048);
                  uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001148,5);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar10 = FUN_1800025a0(in_stack_00000048);
                  uVar12 = in_stack_00000058;
                  uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar10);
                  (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                  uVar12 = (*in_stack_000000b8)
                                     (in_stack_00000048,uVar12,in_stack_000000c0,in_stack_000000d8,
                                      in_stack_00000100);
                  DAT_1804b28d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                }
                _uStack0000000000000050 =
                     (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b28d0,in_stack_00000078);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                   (in_stack_00000048,2,in_stack_00000088,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4475);
                (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_00000210);
                (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,
                                    uVar12);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar12,in_stack_00000070);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar4 == '\0') {
                  (**(code **)(*in_stack_00000048 + 0x658))
                            (in_stack_00000048,in_stack_000000a0,uVar6,1,&stack0x00000050);
                  uVar6 = uStack0000000000000050;
                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar4 == '\0') {
                    _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar6);
                    (**(code **)(*in_stack_00000048 + 0x698))
                              (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar4 == '\0') {
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar4 == '\0') {
                        if (DAT_1804b28d8 == 0) {
                          lVar1 = *in_stack_00000048;
                          in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                          in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                          in_stack_000000d8 = DAT_1804b59f0;
                          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000005b8,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar8 = FUN_1800024f0(in_stack_00000048);
                          uVar9 = FUN_180002550(in_stack_00000048);
                          uVar10 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00001138,5);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar11 = FUN_1800025a0(in_stack_00000048);
                          uVar12 = in_stack_00000058;
                          uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                          (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                          uVar12 = (*in_stack_000000b8)
                                             (in_stack_00000048,uVar12,in_stack_000000c0,
                                              in_stack_000000d8,uVar7);
                          DAT_1804b28d8 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                        }
                        _uStack0000000000000050 =
                             (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b28d8,in_stack_00000078);
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                           (in_stack_00000048,2,in_stack_00000088,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                           0x4475);
                        (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_00000210);
                        (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
                        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,_uStack0000000000000050,
                                            in_stack_00000080,uVar12);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                          (in_stack_00000048,uVar12,in_stack_00000070);
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar4 == '\0') {
                          _uStack0000000000000050 =
                               CONCAT44(uStack0000000000000054,uStack00000000000000b0);
                          (**(code **)(*in_stack_00000048 + 0x698))
                                    (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar4 == '\0') {
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar4 == '\0') {
                              if (DAT_1804b28e0 == 0) {
                                lVar1 = *in_stack_00000048;
                                _uStack00000000000000b0 = *(code **)(lVar1 + 0x390);
                                in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
                                in_stack_000000c0 = DAT_1804b59f0;
                                uVar7 = (**(code **)(lVar1 + 0x518))
                                                  (in_stack_00000048,&stack0x000005b6,1);
                                lVar1 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar1 + 0x390);
                                uVar8 = FUN_1800024f0(in_stack_00000048);
                                uVar9 = FUN_180002550(in_stack_00000048);
                                uVar10 = (**(code **)(lVar1 + 0x518))
                                                   (in_stack_00000048,&stack0x00001128,5);
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar11 = FUN_1800025a0(in_stack_00000048);
                                uVar12 = in_stack_00000058;
                                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                                (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                uVar12 = (*_uStack00000000000000b0)
                                                   (in_stack_00000048,uVar12,in_stack_000000b8,
                                                    in_stack_000000c0,uVar7);
                                DAT_1804b28e0 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar12);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                              }
                              _uStack0000000000000050 =
                                   (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804b28e0,in_stack_00000078);
                              uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                                 (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000068,
                                                 in_stack_00000060,0x4c62);
                              (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_000001b8);
                              (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
                              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,_uStack0000000000000050,
                                                  in_stack_00000080,uVar12);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                                (in_stack_00000048,uVar12,in_stack_00000070);
                              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar4 == '\0') {
                                (**(code **)(*in_stack_00000048 + 0x658))
                                          (in_stack_00000048,in_stack_000000a0,uVar5,1,
                                           &stack0x00000050);
                                _uStack00000000000000b0 =
                                     (code *)CONCAT44(uStack00000000000000b4,uStack0000000000000050)
                                ;
                                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar4 == '\0') {
                                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar4 == '\0') {
                                    if (DAT_1804b28e8 == 0) {
                                      lVar1 = *in_stack_00000048;
                                      in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                                      in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                                      in_stack_000000d8 = DAT_1804b59f0;
                                      uVar7 = (**(code **)(lVar1 + 0x518))
                                                        (in_stack_00000048,&stack0x000005b4,1);
                                      lVar1 = *in_stack_00000048;
                                      pcVar2 = *(code **)(lVar1 + 0x390);
                                      uVar8 = FUN_1800024f0(in_stack_00000048);
                                      uVar9 = FUN_180002550(in_stack_00000048);
                                      uVar10 = (**(code **)(lVar1 + 0x518))
                                                         (in_stack_00000048,&stack0x00001118,5);
                                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar11 = FUN_1800025a0(in_stack_00000048);
                                      uVar12 = in_stack_00000058;
                                      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11)
                                      ;
                                      (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                      uVar12 = (*in_stack_000000b8)
                                                         (in_stack_00000048,uVar12,in_stack_000000c0
                                                          ,in_stack_000000d8,uVar7);
                                      DAT_1804b28e8 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar12);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar12);
                                    }
                                    _uStack0000000000000050 =
                                         (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,DAT_1804b28e8,
                                                    in_stack_00000078);
                                    uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                                                       (in_stack_00000048,2,in_stack_00000088,0);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000068,
                                                       in_stack_00000060,0x4c62);
                                    (*pcVar2)(in_stack_00000048,uVar12,0,uVar7);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,in_stack_000001b8);
                                    (*pcVar2)(in_stack_00000048,uVar12,1,uVar7);
                                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,_uStack0000000000000050,
                                                        in_stack_00000080,uVar12);
                                    (**(code **)(*in_stack_00000048 + 0x188))
                                              (in_stack_00000048,uVar12,in_stack_00000070);
                                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar4 == '\0') {
                                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar4 == '\0') {
                                        if (DAT_1804b28f0 == 0) {
                                          lVar1 = *in_stack_00000048;
                                          in_stack_000000b8 = *(code **)(lVar1 + 0x390);
                                          in_stack_000000c0 = FUN_180333550(in_stack_00000048);
                                          in_stack_000000d8 = DAT_1804b59f0;
                                          in_stack_00000100 =
                                               (**(code **)(lVar1 + 0x518))
                                                         (in_stack_00000048,&stack0x000005b2,1);
                                          lVar1 = *in_stack_00000048;
                                          pcVar2 = *(code **)(lVar1 + 0x390);
                                          uVar7 = FUN_1800024f0(in_stack_00000048);
                                          uVar8 = FUN_180002550(in_stack_00000048);
                                          uVar9 = (**(code **)(lVar1 + 0x518))
                                                            (in_stack_00000048,&stack0x00001108,5);
                                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar10 = FUN_1800025a0(in_stack_00000048);
                                          uVar12 = in_stack_00000058;
                                          uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000058,
                                                             uVar10);
                                          (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                                          uVar12 = (*in_stack_000000b8)
                                                             (in_stack_00000048,uVar12,
                                                              in_stack_000000c0,in_stack_000000d8,
                                                              in_stack_00000100);
                                          DAT_1804b28f0 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar12);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar12);
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
          }
        }
      }
    }
  }
  return;
}

