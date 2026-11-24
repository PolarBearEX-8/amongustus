/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=41 entry=18035c918 blocks=32 maxInstructions=50000 */

/* cut edge: 0x18035c918 -> 0x1803351d6 */
/* cut edge: 0x18035ca0a -> 0x1803351d6 */
/* cut edge: 0x18035cb73 -> 0x1803351d6 */
/* cut edge: 0x18035cc60 -> 0x1803351d6 */
/* cut edge: 0x18035cca4 -> 0x1803351d6 */
/* cut edge: 0x18035cced -> 0x1803351d6 */
/* cut edge: 0x18035ce55 -> 0x1803351d6 */
/* cut edge: 0x18035cf42 -> 0x1803351d6 */
/* cut edge: 0x18035cf8d -> 0x1803351d6 */
/* cut edge: 0x18035cfdb -> 0x1803351d6 */
/* cut edge: 0x18035d144 -> 0x1803351d6 */
/* cut edge: 0x18035d231 -> 0x1803351d6 */
/* cut edge: 0x18035d275 -> 0x1803351d6 */
/* cut edge: 0x18035d2bc -> 0x1803351d6 */
/* cut edge: 0x18035d424 -> 0x1803351d6 */
/* cut edge: 0x18035d511 -> 0x1803351d6 */
/* cut edge: 0x18035d55c -> 0x1803351d6 */
/* cut edge: 0x18035d6c4 -> 0x1803351d6 */
/* cut edge: 0x18035d7b1 -> 0x1803351d6 */
/* cut edge: 0x18035d7fc -> 0x1803351d6 */
/* cut edge: 0x18035d812 -> 0x18035d964 */
/* cut edge: 0x18035d822 -> 0x18035d964 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18035d964 original byte 0x48 replaced by RET */


void region_00041_18035c918(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  char cVar4;
  undefined4 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
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
  code *in_stack_000000c0;
  code *in_stack_000000d8;
  undefined8 in_stack_000000e8;
  code *in_stack_00000100;
  undefined8 in_stack_00000218;
  undefined8 in_stack_00000220;
  undefined8 in_stack_00000228;
  
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2d6c);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000228);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar6);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
  in_stack_000000b8 = (code *)CONCAT44(in_stack_000000b8._4_4_,uVar5);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      if (DAT_1804b2438 == 0) {
        lVar2 = *in_stack_00000048;
        in_stack_000000c0 = *(code **)(lVar2 + 0x390);
        in_stack_000000d8 = (code *)FUN_180333550(in_stack_00000048);
        in_stack_00000100 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000006e0,1);
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001a78,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar6 = in_stack_00000058;
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
        (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar6 = (*in_stack_000000c0)
                          (in_stack_00000048,uVar6,in_stack_000000d8,in_stack_00000100,uVar7);
        DAT_1804b2438 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
      }
      _uStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2438,in_stack_00000078);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4516);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000220);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar6);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
        uVar5 = uStack0000000000000050;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar5);
          (**(code **)(*in_stack_00000048 + 0x698))
                    (in_stack_00000048,in_stack_000000a0,(ulonglong)in_stack_000000b8 & 0xffffffff,1
                     ,&stack0x00000050);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar4 == '\0') {
              if (DAT_1804b2440 == 0) {
                lVar2 = *in_stack_00000048;
                in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
                in_stack_000000d8 = DAT_1804b59f0;
                uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000006de,1);
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000048);
                uVar9 = FUN_180002550(in_stack_00000048);
                uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001a68,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000048);
                uVar6 = in_stack_00000058;
                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                uVar6 = (*in_stack_000000b8)
                                  (in_stack_00000048,uVar6,in_stack_000000c0,in_stack_000000d8,uVar7
                                  );
                DAT_1804b2440 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              _uStack0000000000000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2440,in_stack_00000078);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4516);
              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_00000220);
              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar6);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar6,in_stack_00000070);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uStack00000000000000b0);
                (**(code **)(*in_stack_00000048 + 0x698))
                          (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar4 == '\0') {
                  (**(code **)(*in_stack_00000048 + 0x658))
                            (in_stack_00000048,in_stack_000000a0,2,1,&stack0x00000050);
                  _uStack00000000000000b0 =
                       (code *)CONCAT44(uStack00000000000000b4,uStack0000000000000050);
                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar4 == '\0') {
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar4 == '\0') {
                      if (DAT_1804b2448 == 0) {
                        lVar2 = *in_stack_00000048;
                        in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                        in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
                        in_stack_000000d8 = DAT_1804b59f0;
                        uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000006dc,1);
                        lVar2 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar8 = FUN_1800024f0(in_stack_00000048);
                        uVar9 = FUN_180002550(in_stack_00000048);
                        uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001a58,5);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar11 = FUN_1800025a0(in_stack_00000048);
                        uVar6 = in_stack_00000058;
                        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                        (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                        uVar6 = (*in_stack_000000b8)
                                          (in_stack_00000048,uVar6,in_stack_000000c0,
                                           in_stack_000000d8,uVar7);
                        DAT_1804b2448 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                      }
                      _uStack0000000000000050 =
                           (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b2448,in_stack_00000078);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                         0x3e8f);
                      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_00000218);
                      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,_uStack0000000000000050,in_stack_00000080
                                         ,uVar6);
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                        (in_stack_00000048,uVar6,in_stack_00000070);
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar4 == '\0') {
                        (**(code **)(*in_stack_00000048 + 0x658))
                                  (in_stack_00000048,in_stack_000000a0,uVar5,1,&stack0x00000050);
                        uVar5 = uStack0000000000000050;
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar4 == '\0') {
                          _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar5);
                          (**(code **)(*in_stack_00000048 + 0x698))
                                    (in_stack_00000048,in_stack_000000a0,2,1,&stack0x00000050);
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar4 == '\0') {
                            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar4 == '\0') {
                              if (DAT_1804b2450 == 0) {
                                lVar2 = *in_stack_00000048;
                                in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                                in_stack_000000c0 = (code *)FUN_180333550(in_stack_00000048);
                                in_stack_000000d8 = DAT_1804b59f0;
                                uVar7 = (**(code **)(lVar2 + 0x518))
                                                  (in_stack_00000048,&stack0x000006da,1);
                                lVar2 = *in_stack_00000048;
                                pcVar1 = *(code **)(lVar2 + 0x390);
                                uVar8 = FUN_1800024f0(in_stack_00000048);
                                uVar9 = FUN_180002550(in_stack_00000048);
                                uVar10 = (**(code **)(lVar2 + 0x518))
                                                   (in_stack_00000048,&stack0x00001a48,5);
                                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar11 = FUN_1800025a0(in_stack_00000048);
                                uVar6 = in_stack_00000058;
                                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11);
                                (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                uVar6 = (*in_stack_000000b8)
                                                  (in_stack_00000048,uVar6,in_stack_000000c0,
                                                   in_stack_000000d8,uVar7);
                                DAT_1804b2450 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar6);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                              }
                              _uStack0000000000000050 =
                                   (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804b2450,in_stack_00000078);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000068,
                                                 in_stack_00000060,0x3e8f);
                              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_00000218);
                              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,_uStack0000000000000050,
                                                 in_stack_00000080,uVar6);
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                                (in_stack_00000048,uVar6,in_stack_00000070);
                              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar4 == '\0') {
                                _uStack0000000000000050 =
                                     CONCAT44(uStack0000000000000054,uStack00000000000000b0);
                                (**(code **)(*in_stack_00000048 + 0x698))
                                          (in_stack_00000048,in_stack_000000a0,uVar5,1,
                                           &stack0x00000050);
                                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar4 == '\0') {
                                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar4 == '\0') {
                                    if (DAT_1804b2458 == 0) {
                                      lVar2 = *in_stack_00000048;
                                      _uStack00000000000000b0 = *(code **)(lVar2 + 0x390);
                                      in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
                                      in_stack_000000c0 = DAT_1804b59f0;
                                      uVar7 = (**(code **)(lVar2 + 0x518))
                                                        (in_stack_00000048,&stack0x000006d8,1);
                                      lVar2 = *in_stack_00000048;
                                      pcVar1 = *(code **)(lVar2 + 0x390);
                                      uVar8 = FUN_1800024f0(in_stack_00000048);
                                      uVar9 = FUN_180002550(in_stack_00000048);
                                      uVar10 = (**(code **)(lVar2 + 0x518))
                                                         (in_stack_00000048,&stack0x00001a38,5);
                                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar11 = FUN_1800025a0(in_stack_00000048);
                                      uVar6 = in_stack_00000058;
                                      uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar11)
                                      ;
                                      (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                      uVar6 = (*_uStack00000000000000b0)
                                                        (in_stack_00000048,uVar6,in_stack_000000b8,
                                                         in_stack_000000c0,uVar7);
                                      DAT_1804b2458 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar6);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar6);
                                    }
                                    _uStack0000000000000050 =
                                         (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,DAT_1804b2458,
                                                    in_stack_00000078);
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,2,in_stack_00000088,0);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000068,
                                                       in_stack_00000060,0x5a10);
                                    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
                                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,in_stack_000000e8);
                                    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,_uStack0000000000000050,
                                                       in_stack_00000080,uVar6);
                                    uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                                      (in_stack_00000048,uVar6,in_stack_00000070);
                                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar4 == '\0') {
                                      (**(code **)(*in_stack_00000048 + 0x658))
                                                (in_stack_00000048,in_stack_000000a0,uVar5,1,
                                                 &stack0x00000050);
                                      in_stack_000000b8 =
                                           (code *)CONCAT44(in_stack_000000b8._4_4_,
                                                            uStack0000000000000050);
                                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                                      if (cVar4 == '\0') {
                                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                                        if (cVar4 == '\0') {
                                          if (DAT_1804b2460 == 0) {
                                            lVar2 = *in_stack_00000048;
                                            _uStack00000000000000b0 = *(code **)(lVar2 + 0x390);
                                            in_stack_000000c0 =
                                                 (code *)FUN_180333550(in_stack_00000048);
                                            in_stack_000000d8 = DAT_1804b59f0;
                                            uVar7 = (**(code **)(lVar2 + 0x518))
                                                              (in_stack_00000048,&stack0x000006d6,1)
                                            ;
                                            lVar2 = *in_stack_00000048;
                                            pcVar1 = *(code **)(lVar2 + 0x390);
                                            uVar8 = FUN_1800024f0(in_stack_00000048);
                                            uVar9 = FUN_180002550(in_stack_00000048);
                                            uVar10 = (**(code **)(lVar2 + 0x518))
                                                               (in_stack_00000048,&stack0x00001a28,5
                                                               );
                                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                            uVar11 = FUN_1800025a0(in_stack_00000048);
                                            uVar6 = in_stack_00000058;
                                            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,
                                                               uVar11);
                                            (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                            uVar6 = (*_uStack00000000000000b0)
                                                              (in_stack_00000048,uVar6,
                                                               in_stack_000000c0,in_stack_000000d8,
                                                               uVar7);
                                            DAT_1804b2460 =
                                                 (**(code **)(*in_stack_00000048 + 0xa8))
                                                           (in_stack_00000048,uVar6);
                                            (**(code **)(*in_stack_00000048 + 0xb8))
                                                      (in_stack_00000048,uVar6);
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
  }
  return;
}

