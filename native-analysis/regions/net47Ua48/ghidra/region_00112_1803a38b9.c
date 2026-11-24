/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=112 entry=1803a38b9 blocks=39 maxInstructions=50000 */

/* cut edge: 0x1803a38b9 -> 0x1803d3da7 */
/* cut edge: 0x1803a38d8 -> 0x1803351d6 */
/* cut edge: 0x1803a3901 -> 0x1803351d6 */
/* cut edge: 0x1803a3aaf -> 0x1803351d6 */
/* cut edge: 0x1803a3b8f -> 0x1803351d6 */
/* cut edge: 0x1803a3d34 -> 0x1803351d6 */
/* cut edge: 0x1803a3e0f -> 0x1803d3df8 */
/* cut edge: 0x1803a3e2e -> 0x1803d3e4a */
/* cut edge: 0x1803a3e3e -> 0x1803351d6 */
/* cut edge: 0x1803a3e62 -> 0x1803d3e84 */
/* cut edge: 0x1803a3e81 -> 0x1803351d6 */
/* cut edge: 0x1803a3ea9 -> 0x1803351d6 */
/* cut edge: 0x1803a403a -> 0x1803351d6 */
/* cut edge: 0x1803a4167 -> 0x1803d3eba */
/* cut edge: 0x1803a4186 -> 0x1803d3f0c */
/* cut edge: 0x1803a4196 -> 0x1803351d6 */
/* cut edge: 0x1803a41ba -> 0x1803d3f45 */
/* cut edge: 0x1803a41d9 -> 0x1803351d6 */
/* cut edge: 0x1803a4202 -> 0x1803d3f7b */
/* cut edge: 0x1803a4221 -> 0x1803351d6 */
/* cut edge: 0x1803a4266 -> 0x1803351d6 */
/* cut edge: 0x1803a4410 -> 0x1803351d6 */
/* cut edge: 0x1803a4557 -> 0x1803d3fb1 */
/* cut edge: 0x1803a4576 -> 0x1803351d6 */
/* cut edge: 0x1803a459f -> 0x1803d3fe7 */
/* cut edge: 0x1803a45be -> 0x1803351d6 */
/* cut edge: 0x1803a45df -> 0x1803351d6 */
/* cut edge: 0x1803a4739 -> 0x1803351d6 */
/* cut edge: 0x1803a481b -> 0x1803351d6 */
/* cut edge: 0x1803a481b -> 0x1803a4831 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803a4831 original byte 0x48 replaced by RET */
/* target 0x1803d3da7 original byte 0x48 replaced by RET */
/* target 0x1803d3df8 original byte 0x48 replaced by RET */
/* target 0x1803d3e4a original byte 0x48 replaced by RET */
/* target 0x1803d3e84 original byte 0x4c replaced by RET */
/* target 0x1803d3eba original byte 0x48 replaced by RET */
/* target 0x1803d3f0c original byte 0x48 replaced by RET */
/* target 0x1803d3f45 original byte 0x48 replaced by RET */
/* target 0x1803d3f7b original byte 0x4c replaced by RET */
/* target 0x1803d3fb1 original byte 0x48 replaced by RET */
/* target 0x1803d3fe7 original byte 0x48 replaced by RET */


void region_00112_1803a38b9(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  longlong lStack00000000000000a8;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  undefined8 in_stack_00000248;
  
  if (DAT_1804b52a8 == 0) {
    return;
  }
  lStack00000000000000a8 = DAT_1804b52a8;
  uVar7 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      if (DAT_1804b2f80 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1803335a0(in_stack_00000048);
        lVar5 = DAT_1804b59f0;
        uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000402,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003900,0x16);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar8,lVar5,uVar9);
        DAT_1804b2f80 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b2f80,in_stack_00000078);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x50e0);
      (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                          in_stack_000000c8 ^ 0x5bd1c33d2263cad2);
      (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          if (DAT_1804b2f88 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar9 = FUN_1803335a0(in_stack_00000048);
            lVar5 = DAT_1804b59f0;
            uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000400,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar11 = FUN_1800024f0(in_stack_00000048);
            uVar12 = FUN_180002550(in_stack_00000048);
            uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000038d0,0x16);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar14 = FUN_1800025a0(in_stack_00000048);
            uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
            (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
            uVar9 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar9,lVar5,uVar10);
            DAT_1804b2f88 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2f88,in_stack_00000078);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2a89);
          (*pcVar2)(in_stack_00000048,uVar10,0,uVar11);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000c8 ^ 0x12d62572f7af30f4);
          (*pcVar2)(in_stack_00000048,uVar10,1,uVar11);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            if (DAT_1804b52b0 == 0) {
              return;
            }
            if (DAT_1804b52b8 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x488))(in_stack_00000048,DAT_1804b52b0);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              if (DAT_1804b52c0 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar9,DAT_1804b52c0,uVar10);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar6 == '\0') {
                  if (DAT_1804b2f90 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar10 = FUN_180333500(in_stack_00000048);
                    lVar5 = DAT_1804b59f0;
                    uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003fe,1);
                    lVar1 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar12 = FUN_1800024f0(in_stack_00000048);
                    uVar13 = FUN_180002550(in_stack_00000048);
                    uVar14 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002be0,0x19);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar15 = FUN_1800025a0(in_stack_00000048);
                    uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                    (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                    uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar10,lVar5,uVar11);
                    DAT_1804b2f90 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b2f90,in_stack_00000078);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                     (in_stack_00000048,4,in_stack_00000088,0);
                  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar11,0,uVar9);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_00000248);
                  (*pcVar2)(in_stack_00000048,uVar11,1,uVar9);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     0xdfcc108d1c27cf45);
                  (*pcVar2)(in_stack_00000048,uVar11,2,uVar9);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000c8);
                  (*pcVar2)(in_stack_00000048,uVar11,3,uVar9);
                  (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
                    if (DAT_1804b52c8 == 0) {
                      return;
                    }
                    if (DAT_1804b52d0 == 0) {
                      return;
                    }
                    (**(code **)(*in_stack_00000048 + 0x488))(in_stack_00000048,DAT_1804b52c8);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar6 == '\0') {
                      if (DAT_1804b52d8 == 0) {
                        return;
                      }
                      (**(code **)(*in_stack_00000048 + 0x420))(in_stack_00000048,in_stack_000000a0)
                      ;
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar6 == '\0') {
                        if (DAT_1804b52e0 == 0) {
                          return;
                        }
                        (**(code **)(*in_stack_00000048 + 0x2d8))
                                  (in_stack_00000048,uVar7,lStack00000000000000a8,DAT_1804b52e0,
                                   uVar8);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 == '\0') {
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar6 == '\0') {
                            if (DAT_1804b2f98 == 0) {
                              lVar1 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar1 + 0x390);
                              uVar8 = FUN_180333500(in_stack_00000048);
                              lStack00000000000000a8 = DAT_1804b59f0;
                              uVar9 = (**(code **)(lVar1 + 0x518))
                                                (in_stack_00000048,&stack0x000003fc,1);
                              lVar1 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar1 + 0x390);
                              uVar10 = FUN_1800024f0(in_stack_00000048);
                              uVar11 = FUN_180002550(in_stack_00000048);
                              uVar12 = (**(code **)(lVar1 + 0x518))
                                                 (in_stack_00000048,&stack0x00005c10,0x2a);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar13 = FUN_1800025a0(in_stack_00000048);
                              uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                              (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                              uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar8,
                                                lStack00000000000000a8,uVar9);
                              DAT_1804b2f98 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                            }
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b2f98,in_stack_00000078);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,5,in_stack_00000088,0);
                            (**(code **)(*in_stack_00000048 + 0x570))
                                      (in_stack_00000048,uVar9,0,in_stack_00000168);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                               (in_stack_00000048,in_stack_00000098,
                                                in_stack_00000090,in_stack_00000170);
                            (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
                            (**(code **)(*in_stack_00000048 + 0x570))
                                      (in_stack_00000048,uVar9,2,uVar7);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,0xdfc79c58f7839374);
                            (*pcVar2)(in_stack_00000048,uVar9,3,uVar7);
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,in_stack_000000c8);
                            (*pcVar2)(in_stack_00000048,uVar9,4,uVar7);
                            (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar6 == '\0') {
                              if (DAT_1804b5a30 == 0) {
                                return;
                              }
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x2f8))
                                                (in_stack_00000048,in_stack_00000168);
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar6 == '\0') {
                                if (DAT_1804b5b40 == 0) {
                                  return;
                                }
                                (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7);
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar6 == '\0') {
                                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar6 == '\0') {
                                    if (DAT_1804b2fa0 == 0) {
                                      lVar1 = *in_stack_00000048;
                                      pcVar2 = *(code **)(lVar1 + 0x390);
                                      uVar7 = FUN_180333500(in_stack_00000048);
                                      lVar5 = DAT_1804b59f0;
                                      uVar8 = (**(code **)(lVar1 + 0x518))
                                                        (in_stack_00000048,&stack0x000003fa,1);
                                      lVar1 = *in_stack_00000048;
                                      pcVar3 = *(code **)(lVar1 + 0x390);
                                      uVar9 = FUN_1800024f0(in_stack_00000048);
                                      uVar10 = FUN_180002550(in_stack_00000048);
                                      uVar11 = (**(code **)(lVar1 + 0x518))
                                                         (in_stack_00000048,&stack0x00002810,0xd);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar12 = FUN_1800025a0(in_stack_00000048);
                                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12)
                                      ;
                                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                      uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,
                                                        lVar5,uVar8);
                                      DAT_1804b2fa0 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar7);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar7);
                                    }
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                      (in_stack_00000048,DAT_1804b2fa0,
                                                       in_stack_00000078);
                                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,2,in_stack_00000088,0);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,0xdfc5815bc278527a);
                                    (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,in_stack_000000c8);
                                    (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                                    (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar6 == '\0') {
                                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar6 == '\0') {
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

