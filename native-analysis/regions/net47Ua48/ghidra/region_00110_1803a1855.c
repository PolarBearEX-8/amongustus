/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=110 entry=1803a1855 blocks=45 maxInstructions=50000 */

/* cut edge: 0x1803a1865 -> 0x1803351d6 */
/* cut edge: 0x1803a1960 -> 0x1803a4fb0 */
/* cut edge: 0x1803a1988 -> 0x1803351d6 */
/* cut edge: 0x1803a19b1 -> 0x1803351d6 */
/* cut edge: 0x1803a19ec -> 0x1803351d6 */
/* cut edge: 0x1803a1a27 -> 0x1803b8a32 */
/* cut edge: 0x1803a1a5e -> 0x1803351d6 */
/* cut edge: 0x1803a1a96 -> 0x1803d3a0f */
/* cut edge: 0x1803a1aad -> 0x1803351d6 */
/* cut edge: 0x1803a1ad3 -> 0x1803d3a60 */
/* cut edge: 0x1803a1afc -> 0x1803351d6 */
/* cut edge: 0x1803a1b22 -> 0x1803d3a96 */
/* cut edge: 0x1803a1b41 -> 0x1803351d6 */
/* cut edge: 0x1803a1b65 -> 0x1803d3bde */
/* cut edge: 0x1803a1b89 -> 0x1803351d6 */
/* cut edge: 0x1803a1bad -> 0x1803351d6 */
/* cut edge: 0x1803a1d29 -> 0x1803d3c14 */
/* cut edge: 0x1803a1d93 -> 0x1803d3c6e */
/* cut edge: 0x1803a1db0 -> 0x1803351d6 */
/* cut edge: 0x1803a1e46 -> 0x1803d3cab */
/* cut edge: 0x1803a1e65 -> 0x1803351d6 */
/* cut edge: 0x1803a1e94 -> 0x1803351d6 */
/* cut edge: 0x1803a2010 -> 0x1803351d6 */
/* cut edge: 0x1803a20fb -> 0x1803351d6 */
/* cut edge: 0x1803a22b9 -> 0x1803351d6 */
/* cut edge: 0x1803a23ee -> 0x1803351d6 */
/* cut edge: 0x1803a2429 -> 0x1803351d6 */
/* cut edge: 0x1803a25d1 -> 0x1803351d6 */
/* cut edge: 0x1803a26ac -> 0x1803351d6 */
/* cut edge: 0x1803a26e7 -> 0x1803351d6 */
/* cut edge: 0x1803a26fd -> 0x1803a288f */
/* cut edge: 0x1803a271f -> 0x1803a288f */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803a288f original byte 0x48 replaced by RET */
/* target 0x1803a4fb0 original byte 0x49 replaced by RET */
/* target 0x1803b8a32 original byte 0x48 replaced by RET */
/* target 0x1803d3a0f original byte 0x48 replaced by RET */
/* target 0x1803d3a60 original byte 0x48 replaced by RET */
/* target 0x1803d3a96 original byte 0x48 replaced by RET */
/* target 0x1803d3bde original byte 0x48 replaced by RET */
/* target 0x1803d3c14 original byte 0x48 replaced by RET */
/* target 0x1803d3c6e original byte 0x48 replaced by RET */
/* target 0x1803d3cab original byte 0x48 replaced by RET */


void region_00110_1803a1855(void)

{
  code *pcVar1;
  code *pcVar2;
  char cVar3;
  char cVar4;
  char cVar5;
  int iVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  int unaff_EBX;
  int unaff_EDI;
  ulonglong uVar13;
  longlong lVar14;
  int iVar15;
  longlong *in_stack_00000048;
  char cStack0000000000000050;
  undefined8 in_stack_00000058;
  longlong in_stack_00000060;
  code *in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  code *in_stack_000000a0;
  ulonglong in_stack_000000a8;
  ulonglong in_stack_000000b0;
  ulonglong in_stack_000000b8;
  ulonglong in_stack_000000c0;
  ulonglong in_stack_000000c8;
  ulonglong in_stack_000000d0;
  ulonglong in_stack_000000e0;
  code *in_stack_000000e8;
  undefined8 in_stack_00000108;
  ulonglong in_stack_00000140;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000208;
  
  lVar14 = DAT_1804b5270;
  if (unaff_EDI == unaff_EBX) {
    iVar6 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048);
    cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar3 == '\0') {
      in_stack_000000d0 = in_stack_000000c8 ^ 0x28797f055fc1;
      in_stack_000000b8 = in_stack_000000c8 ^ 0x3097c23ce302;
      in_stack_000000c0 = in_stack_000000c8 ^ 0x1f44034ff168;
      in_stack_000000e8 = (code *)(in_stack_000000d0 >> 8);
      in_stack_000000e0 = in_stack_000000c8 ^ 0x33a4bd9abd9b;
      in_stack_000000a0 = (code *)(in_stack_000000c8 ^ 0x2d525b935bda);
      in_stack_000000b0 = in_stack_000000b8 >> 8;
      in_stack_000000a8 = in_stack_000000c8 ^ 0x4c11cbeb6b90;
      in_stack_00000140 = in_stack_000000c8 ^ 0x9745fb3ddf6;
      iVar15 = 0;
      do {
        lVar14 = *in_stack_00000048;
        if (iVar6 <= iVar15) {
          return;
        }
        pcVar1 = *(code **)(lVar14 + 0x2f8);
        if (DAT_1804b5b50 == 0) {
          uVar7 = FUN_1803334a0(in_stack_00000048);
          DAT_1804b5b50 =
               (**(code **)(lVar14 + 0x2f0))(in_stack_00000048,uVar7,&DAT_1804a5920,&DAT_1804a4de0);
        }
        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000168,DAT_1804b5b50);
        cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar3 != '\0') {
          return;
        }
        (**(code **)(*in_stack_00000048 + 0x640))(in_stack_00000048,uVar7,iVar15,1,&stack0x00000050)
        ;
        cVar3 = cStack0000000000000050;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 != '\0') {
          return;
        }
        (**(code **)(*in_stack_00000048 + 0x640))(in_stack_00000048);
        cVar4 = cStack0000000000000050;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 != '\0') {
          return;
        }
        if (cVar3 != cVar4) {
          return;
        }
        iVar15 = iVar15 + 1;
        iVar6 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048);
        cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      } while (cVar3 == '\0');
    }
  }
  else {
    if (DAT_1804b5270 == 0) {
      return;
    }
    in_stack_00000070 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,DAT_1804b5270);
    cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar3 == '\0') {
      _cStack0000000000000050 = in_stack_00000070;
      if (DAT_1804b5278 == 0) {
        return;
      }
      (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,in_stack_00000070,lVar14);
      cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      lVar14 = DAT_1804b5020;
      if (cVar3 == '\0') {
        if (DAT_1804b5020 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048,DAT_1804b5020);
        cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar3 == '\0') {
          if (DAT_1804b5028 == 0) {
            return;
          }
          _cStack0000000000000050 = uVar7;
          (**(code **)(*in_stack_00000048 + 0x2d8))(in_stack_00000048,uVar7,lVar14);
          cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((cVar3 == '\0') &&
             (cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar3 == '\0')) {
            if (DAT_1804b2f30 == 0) {
              lVar14 = *in_stack_00000048;
              in_stack_00000068 = *(code **)(lVar14 + 0x390);
              in_stack_00000060 = FUN_1803335a0(in_stack_00000048);
              in_stack_000000a8 = DAT_1804b59f0;
              in_stack_000000a0 =
                   (code *)(**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000418,1);
              lVar14 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar14 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000039f0,0x16);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar12 = in_stack_00000058;
              uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar11);
              (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*in_stack_00000068)
                                 (in_stack_00000048,uVar12,in_stack_00000060,in_stack_000000a8,
                                  in_stack_000000a0);
              DAT_1804b2f30 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            _cStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2f30,in_stack_00000078);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            in_stack_00000068 = (code *)DAT_1804b4dd8;
            if (DAT_1804b4dd8 == 0) {
              return;
            }
            uVar13 = in_stack_000000c8 ^ 0x1f5535ce42eee283;
            in_stack_00000060 = DAT_1804b4de0;
            if (DAT_1804b4de0 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,DAT_1804b4dd8,DAT_1804b4de0,0x78d2);
            (*pcVar1)(in_stack_00000048,uVar12,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,uVar13);
            (*pcVar1)(in_stack_00000048,uVar12,1,uVar8);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar12);
            cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            lVar14 = DAT_1804b5030;
            if (cVar3 == '\0') {
              if (DAT_1804b5030 == 0) {
                return;
              }
              in_stack_000000a0 =
                   (code *)(**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar7,DAT_1804b5030,uVar12);
              cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((cVar3 == '\0') &&
                 (cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar3 == '\0')) {
                in_stack_000000a8 = lVar14;
                if (DAT_1804b2f38 == 0) {
                  lVar14 = *in_stack_00000048;
                  in_stack_000000e8 = *(code **)(lVar14 + 0x390);
                  in_stack_000000d0 = FUN_180333500(in_stack_00000048);
                  in_stack_000000e0 = DAT_1804b59f0;
                  uVar12 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000416,1);
                  lVar14 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar14 + 0x390);
                  uVar8 = FUN_1800024f0(in_stack_00000048);
                  uVar9 = FUN_180002550(in_stack_00000048);
                  uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00002850,0xd);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar11 = FUN_1800025a0(in_stack_00000048);
                  uVar7 = in_stack_00000058;
                  uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar11);
                  (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                  lVar14 = in_stack_000000a8;
                  uVar7 = (*in_stack_000000e8)
                                    (in_stack_00000048,uVar7,in_stack_000000d0,in_stack_000000e0,
                                     uVar12);
                  DAT_1804b2f38 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                }
                _cStack0000000000000050 =
                     (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b2f38,in_stack_00000078);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000088,0);
                uVar12 = in_stack_00000098;
                uVar7 = in_stack_00000090;
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   0xdfc5815bc278527a);
                (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,uVar12,uVar7,in_stack_000000c8);
                (*pcVar1)(in_stack_00000048,uVar8,1,uVar7);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,uVar8
                                  );
                cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((cVar3 == '\0') &&
                   (cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar3 == '\0')) {
                  if (DAT_1804b2f40 == 0) {
                    lVar14 = *in_stack_00000048;
                    in_stack_000000e8 = *(code **)(lVar14 + 0x390);
                    in_stack_000000d0 = FUN_180333500(in_stack_00000048);
                    in_stack_000000e0 = DAT_1804b59f0;
                    in_stack_00000108 =
                         (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000414,1);
                    lVar14 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar14 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004e30,0x29);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar12 = in_stack_00000058;
                    uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar11);
                    lVar14 = in_stack_000000a8;
                    (*pcVar1)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar12 = (*in_stack_000000e8)
                                       (in_stack_00000048,uVar12,in_stack_000000d0,in_stack_000000e0
                                        ,in_stack_00000108);
                    DAT_1804b2f40 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                  }
                  _cStack0000000000000050 =
                       (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b2f40,in_stack_00000078);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,4,in_stack_00000088,0);
                  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,uVar7);
                  uVar12 = in_stack_00000098;
                  uVar7 = in_stack_00000090;
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_00000208);
                  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,uVar12,uVar7,0xdfb22bab6c698c7f);
                  (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,uVar12,uVar7,in_stack_000000c8);
                  (*pcVar1)(in_stack_00000048,uVar8,3,uVar7);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,_cStack0000000000000050,in_stack_00000080,
                                     uVar8);
                  cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar3 == '\0') {
                    in_stack_000000a0 =
                         (code *)(**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,in_stack_000000a0,lVar14,uVar7);
                    cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((cVar3 == '\0') &&
                       (cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar3 == '\0')) {
                      uVar13 = in_stack_000000c8 ^ 0x4dd1571d1c070a6e;
                      if (DAT_1804b2f48 == 0) {
                        lVar14 = *in_stack_00000048;
                        in_stack_000000e8 = *(code **)(lVar14 + 0x390);
                        in_stack_000000d0 = FUN_1803335a0(in_stack_00000048);
                        in_stack_000000e0 = DAT_1804b59f0;
                        in_stack_00000108 =
                             (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000412,1);
                        lVar14 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar14 + 0x390);
                        uVar12 = FUN_1800024f0(in_stack_00000048);
                        uVar8 = FUN_180002550(in_stack_00000048);
                        uVar9 = (**(code **)(lVar14 + 0x518))
                                          (in_stack_00000048,&stack0x000039c0,0x16);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar10 = FUN_1800025a0(in_stack_00000048);
                        uVar7 = in_stack_00000058;
                        uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar10);
                        lVar14 = in_stack_000000a8;
                        (*pcVar1)(in_stack_00000048,uVar12,uVar8,uVar9,uVar10);
                        uVar7 = (*in_stack_000000e8)
                                          (in_stack_00000048,uVar7,in_stack_000000d0,
                                           in_stack_000000e0,in_stack_00000108);
                        DAT_1804b2f48 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                      }
                      _cStack0000000000000050 =
                           (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b2f48,in_stack_00000078);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                          0x1019);
                      (*pcVar1)(in_stack_00000048,uVar7,0,uVar12);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                          uVar13);
                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar12);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,_cStack0000000000000050,in_stack_00000080
                                         ,uVar7);
                      cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar3 == '\0') {
                        in_stack_000000e8 =
                             (code *)(**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,in_stack_000000a0,lVar14,uVar7);
                        cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((cVar3 == '\0') &&
                           (cVar3 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar3 == '\0')) {
                          if (DAT_1804b2f50 == 0) {
                            lVar14 = *in_stack_00000048;
                            in_stack_000000a0 = *(code **)(lVar14 + 0x390);
                            in_stack_000000d0 = FUN_1803335a0(in_stack_00000048);
                            in_stack_000000e0 = DAT_1804b59f0;
                            in_stack_00000108 =
                                 (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00000410,1)
                            ;
                            lVar14 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar14 + 0x390);
                            uVar12 = FUN_1800024f0(in_stack_00000048);
                            uVar8 = FUN_180002550(in_stack_00000048);
                            uVar9 = (**(code **)(lVar14 + 0x518))
                                              (in_stack_00000048,&stack0x00003990,0x16);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar10 = FUN_1800025a0(in_stack_00000048);
                            uVar7 = in_stack_00000058;
                            uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar10);
                            (*pcVar1)(in_stack_00000048,uVar12,uVar8,uVar9,uVar10);
                            uVar7 = (*in_stack_000000a0)
                                              (in_stack_00000048,uVar7,in_stack_000000d0,
                                               in_stack_000000e0,in_stack_00000108);
                            DAT_1804b2f50 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
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
  return;
}

