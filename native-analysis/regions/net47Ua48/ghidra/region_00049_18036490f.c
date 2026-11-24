/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=49 entry=18036490f blocks=41 maxInstructions=50000 */

/* cut edge: 0x18036490f -> 0x1803351d6 */
/* cut edge: 0x1803649fc -> 0x1803351d6 */
/* cut edge: 0x180364a3d -> 0x1803351d6 */
/* cut edge: 0x180364bc0 -> 0x1803351d6 */
/* cut edge: 0x180364cad -> 0x1803351d6 */
/* cut edge: 0x180364cf8 -> 0x180362e7a */
/* cut edge: 0x180364d06 -> 0x1803351d6 */
/* cut edge: 0x180364e76 -> 0x1803351d6 */
/* cut edge: 0x180364f64 -> 0x1803351d6 */
/* cut edge: 0x180364fa9 -> 0x1803351d6 */
/* cut edge: 0x180365138 -> 0x1803351d6 */
/* cut edge: 0x180365225 -> 0x180362e7a */
/* cut edge: 0x180365267 -> 0x1803351d6 */
/* cut edge: 0x18036526c -> 0x180362c03 */
/* cut edge: 0x1803652a5 -> 0x1803351d6 */
/* cut edge: 0x1803652aa -> 0x1803351d6 */
/* cut edge: 0x180365412 -> 0x1803351d6 */
/* cut edge: 0x1803654ff -> 0x1803351d6 */
/* cut edge: 0x18036555d -> 0x180362065 */
/* cut edge: 0x1803655af -> 0x1803351d6 */
/* cut edge: 0x1803655b4 -> 0x18034b629 */
/* cut edge: 0x1803655ef -> 0x18034d2a7 */
/* cut edge: 0x180365625 -> 0x18034abb7 */
/* cut edge: 0x180365660 -> 0x18034ac04 */
/* cut edge: 0x180365696 -> 0x18034ac04 */
/* cut edge: 0x1803656af -> 0x18034ac24 */
/* cut edge: 0x1803656e9 -> 0x18034ac7d */
/* cut edge: 0x180365720 -> 0x18034ae64 */
/* cut edge: 0x18036575b -> 0x18034aec6 */
/* cut edge: 0x180365790 -> 0x18034aec6 */
/* cut edge: 0x1803657a9 -> 0x18034aede */
/* cut edge: 0x1803657e2 -> 0x18034af36 */
/* cut edge: 0x180365819 -> 0x18034b10d */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18034abb7 original byte 0x4c replaced by RET */
/* target 0x18034ac04 original byte 0x48 replaced by RET */
/* target 0x18034ac24 original byte 0x48 replaced by RET */
/* target 0x18034ac7d original byte 0x48 replaced by RET */
/* target 0x18034ae64 original byte 0x48 replaced by RET */
/* target 0x18034aec6 original byte 0x48 replaced by RET */
/* target 0x18034aede original byte 0x48 replaced by RET */
/* target 0x18034af36 original byte 0x48 replaced by RET */
/* target 0x18034b10d original byte 0x48 replaced by RET */
/* target 0x18034b629 original byte 0x48 replaced by RET */
/* target 0x18034d2a7 original byte 0x48 replaced by RET */
/* target 0x180362065 original byte 0x48 replaced by RET */
/* target 0x180362c03 original byte 0x48 replaced by RET */
/* target 0x180362e7a original byte 0x83 replaced by RET */


void region_00049_18036490f(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  byte bVar4;
  char cVar5;
  byte bVar6;
  undefined4 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 unaff_R12;
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
  undefined8 in_stack_000000a8;
  code *in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000f0;
  code *in_stack_000000f8;
  code *in_stack_00000100;
  undefined8 in_stack_00000120;
  int in_stack_00000190;
  undefined8 in_stack_00000198;
  undefined8 in_stack_000001b8;
  
  _bStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001b8);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar8);
  (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    (**(code **)(*in_stack_00000048 + 0x640))(in_stack_00000048);
    bVar4 = bStack0000000000000050;
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
      if (DAT_1804b25b0 == 0) {
        lVar2 = *in_stack_00000048;
        in_stack_00000100 = *(code **)(lVar2 + 0x390);
        in_stack_00000120 = FUN_180333550(in_stack_00000048);
        in_stack_000000f0 = DAT_1804b59f0;
        in_stack_000000f8 =
             (code *)(**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000682,1);
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001788,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar8 = in_stack_00000058;
        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
        (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        uVar8 = (*in_stack_00000100)
                          (in_stack_00000048,uVar8,in_stack_00000120,in_stack_000000f0,
                           in_stack_000000f8);
        DAT_1804b25b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        unaff_R12 = in_stack_000000c0;
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      _bStack0000000000000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b25b0,in_stack_00000078);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar8);
      bVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        _bStack0000000000000050 = CONCAT71(uStack0000000000000051,(bVar4 & bVar6) * '\x02');
        (**(code **)(*in_stack_00000048 + 0x680))
                  (in_stack_00000048,unaff_R12,(ulonglong)in_stack_000000b8 & 0xffffffff,1,
                   &stack0x00000050);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          if (in_stack_00000190 == 0) {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b25b8 == 0) {
              lVar2 = *in_stack_00000048;
              in_stack_000000b8 = *(code **)(lVar2 + 0x390);
              in_stack_00000100 = (code *)FUN_180333550(in_stack_00000048);
              in_stack_00000120 = DAT_1804b59f0;
              uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000680,1);
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x390);
              uVar10 = FUN_1800024f0(in_stack_00000048);
              uVar11 = FUN_180002550(in_stack_00000048);
              uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001778,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar13 = FUN_1800025a0(in_stack_00000048);
              uVar8 = in_stack_00000058;
              uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar13);
              unaff_R12 = in_stack_000000c0;
              (*pcVar1)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
              uVar8 = (*in_stack_000000b8)
                                (in_stack_00000048,uVar8,in_stack_00000100,in_stack_00000120,uVar9);
              DAT_1804b25b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
            }
            _bStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b25b8,in_stack_00000078);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c62);
            (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000001b8);
            (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar8);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar8,in_stack_00000070);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              _bStack0000000000000050 = CONCAT44(uStack0000000000000051._3_4_,uVar7);
              (**(code **)(*in_stack_00000048 + 0x640))
                        (in_stack_00000048,unaff_R12,uVar7,1,&stack0x00000050);
              bVar4 = bStack0000000000000050;
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((cVar5 == '\0') &&
                 (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
                if (DAT_1804b25c0 == 0) {
                  lVar2 = *in_stack_00000048;
                  in_stack_000000b8 = *(code **)(lVar2 + 0x390);
                  in_stack_00000100 = (code *)FUN_180333550(in_stack_00000048);
                  in_stack_00000120 = DAT_1804b59f0;
                  in_stack_000000f0 =
                       (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000067e,1);
                  lVar2 = *in_stack_00000048;
                  in_stack_000000f8 = *(code **)(lVar2 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000048);
                  uVar10 = FUN_180002550(in_stack_00000048);
                  uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001768,5);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000048);
                  uVar8 = in_stack_00000058;
                  uVar12 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar12);
                  (*in_stack_000000f8)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                  uVar8 = (*in_stack_000000b8)
                                    (in_stack_00000048,uVar8,in_stack_00000100,in_stack_00000120,
                                     in_stack_000000f0);
                  DAT_1804b25c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                  unaff_R12 = in_stack_000000c0;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                }
                _bStack0000000000000050 =
                     (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b25c0,in_stack_00000078);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000088,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x577a);
                (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_00000198);
                (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar8
                                  );
                bVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar8,in_stack_00000070);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  _bStack0000000000000050 = CONCAT71(uStack0000000000000051,bVar4 ^ bVar6);
                  (**(code **)(*in_stack_00000048 + 0x680))
                            (in_stack_00000048,unaff_R12,uVar7,1,&stack0x00000050);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
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
  return;
}

