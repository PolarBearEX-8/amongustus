/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=95 entry=180392832 blocks=32 maxInstructions=50000 */

/* cut edge: 0x180392832 -> 0x1803351d6 */
/* cut edge: 0x1803929c6 -> 0x1803351d6 */
/* cut edge: 0x180392aa0 -> 0x1803351d6 */
/* cut edge: 0x180392ae0 -> 0x1803351d6 */
/* cut edge: 0x180392c74 -> 0x1803351d6 */
/* cut edge: 0x180392d4e -> 0x1803351d6 */
/* cut edge: 0x180392d97 -> 0x1803351d6 */
/* cut edge: 0x180392dd7 -> 0x1803351d6 */
/* cut edge: 0x180392f6f -> 0x1803351d6 */
/* cut edge: 0x180393049 -> 0x1803351d6 */
/* cut edge: 0x180393089 -> 0x1803351d6 */
/* cut edge: 0x180393222 -> 0x1803351d6 */
/* cut edge: 0x1803932fc -> 0x1803351d6 */
/* cut edge: 0x180393337 -> 0x1803351d6 */
/* cut edge: 0x180393372 -> 0x1803351d6 */
/* cut edge: 0x1803933b5 -> 0x1803351d6 */
/* cut edge: 0x1803933f0 -> 0x1803351d6 */
/* cut edge: 0x18039355c -> 0x1803351d6 */
/* cut edge: 0x18039363e -> 0x1803351d6 */
/* cut edge: 0x1803937ea -> 0x1803351d6 */
/* cut edge: 0x1803937ea -> 0x1803938e4 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803938e4 original byte 0x48 replaced by RET */


void region_00095_180392832(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  ulonglong in_stack_000000c0;
  ulonglong in_stack_000000c8;
  ulonglong in_stack_000000d0;
  ulonglong in_stack_000000e0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_000001a0;
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804b2db8 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar6 = FUN_1803335a0(in_stack_00000048);
      uVar12 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000047c,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003c30,0x16);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11);
      (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar6,uVar12,uVar7);
      DAT_1804b2db8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2db8,in_stack_00000078);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1019);
    (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                       in_stack_000000d0 ^ in_stack_000000c8);
    (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar12,in_stack_00000080,uVar6);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,in_stack_000000f8,in_stack_000000a8,uVar12);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804b2dc0 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar7 = FUN_1803335a0(in_stack_00000048);
            uVar6 = DAT_1804b59f0;
            uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000047a,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003c00,0x16);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
            uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,uVar8);
            DAT_1804b2dc0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2dc0,in_stack_00000078);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4ad3);
          (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                             in_stack_000000c0 ^ in_stack_000000c8);
          (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            uVar7 = FUN_18001edd0(in_stack_00000048);
            uVar6 = (*pcVar2)(in_stack_00000048,in_stack_000001a0,uVar7,uVar6);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b2dc8 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar8 = FUN_1803335a0(in_stack_00000048);
                    uVar6 = DAT_1804b59f0;
                    uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000478,1);
                    lVar1 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar10 = FUN_1800024f0(in_stack_00000048);
                    uVar11 = FUN_180002550(in_stack_00000048);
                    uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003bd0,0x16);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar14 = FUN_1800025a0(in_stack_00000048);
                    uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar13,uVar14);
                    uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar8,uVar6,uVar9);
                    DAT_1804b2dc8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b2dc8,in_stack_00000078);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4b59);
                  (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000b0 ^ in_stack_000000c8);
                  (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_00000080,uVar8);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        if (DAT_1804b2dd0 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar8 = FUN_1803335a0(in_stack_00000048);
                          uVar6 = DAT_1804b59f0;
                          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000476,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar10 = FUN_1800024f0(in_stack_00000048);
                          uVar11 = FUN_180002550(in_stack_00000048);
                          uVar13 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00003ba0,0x16);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar14 = FUN_1800025a0(in_stack_00000048);
                          uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar13,uVar14);
                          uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar8,uVar6,uVar9);
                          DAT_1804b2dd0 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                        }
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b2dd0,in_stack_00000078);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000088,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                           0x30dd);
                        (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_000000e0 ^ in_stack_000000c8);
                        (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar6,in_stack_00000080,uVar8);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000001a0,uVar7,uVar6);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                                (in_stack_00000048,&stack0x00000474,1);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    if (DAT_1804b2dd8 == 0) {
                                      lVar1 = *in_stack_00000048;
                                      pcVar2 = *(code **)(lVar1 + 0x390);
                                      uVar6 = FUN_180333500(in_stack_00000048);
                                      uVar12 = DAT_1804b59f0;
                                      uVar7 = (**(code **)(lVar1 + 0x518))
                                                        (in_stack_00000048,&stack0x00000472,1);
                                      lVar1 = *in_stack_00000048;
                                      pcVar3 = *(code **)(lVar1 + 0x390);
                                      uVar8 = FUN_1800024f0(in_stack_00000048);
                                      uVar9 = FUN_180002550(in_stack_00000048);
                                      uVar10 = (**(code **)(lVar1 + 0x518))
                                                         (in_stack_00000048,&stack0x00002930,0xd);
                                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar11 = FUN_1800025a0(in_stack_00000048);
                                      uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar11)
                                      ;
                                      (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                                      uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar6,
                                                         uVar12,uVar7);
                                      DAT_1804b2dd8 =
                                           (**(code **)(*in_stack_00000048 + 0xa8))
                                                     (in_stack_00000048,uVar12);
                                      (**(code **)(*in_stack_00000048 + 0xb8))
                                                (in_stack_00000048,uVar12);
                                    }
                                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,DAT_1804b2dd8,
                                                        in_stack_00000078);
                                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                      (in_stack_00000048,2,in_stack_00000088,0);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,0xdfc5815bc278527a);
                                    (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                      (in_stack_00000048,in_stack_00000098,
                                                       in_stack_00000090,in_stack_000000c8);
                                    (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                                    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,uVar12,in_stack_00000080,
                                                        uVar6);
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar5 == '\0') {
                                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                      if (cVar5 == '\0') {
                                        if (DAT_1804b2de0 == 0) {
                                          lVar1 = *in_stack_00000048;
                                          pcVar2 = *(code **)(lVar1 + 0x390);
                                          uVar7 = FUN_180333500(in_stack_00000048);
                                          uVar6 = DAT_1804b59f0;
                                          uVar8 = (**(code **)(lVar1 + 0x518))
                                                            (in_stack_00000048,&stack0x00000470,1);
                                          lVar1 = *in_stack_00000048;
                                          pcVar3 = *(code **)(lVar1 + 0x390);
                                          uVar9 = FUN_1800024f0(in_stack_00000048);
                                          uVar10 = FUN_180002550(in_stack_00000048);
                                          uVar11 = (**(code **)(lVar1 + 0x518))
                                                             (in_stack_00000048,&stack0x000075f0,
                                                              0x28);
                                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar13 = FUN_1800025a0(in_stack_00000048);
                                          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,
                                                             uVar13);
                                          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar13);
                                          uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,
                                                            uVar7,uVar6,uVar8);
                                          DAT_1804b2de0 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar6);
                                          (**(code **)(*in_stack_00000048 + 0xb8))
                                                    (in_stack_00000048,uVar6);
                                        }
                                        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                          (in_stack_00000048,DAT_1804b2de0,
                                                           in_stack_00000078);
                                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                          (in_stack_00000048,3,in_stack_00000088,0);
                                        (**(code **)(*in_stack_00000048 + 0x570))
                                                  (in_stack_00000048,uVar7,0,uVar12);
                                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                                           (in_stack_00000048,in_stack_00000098,
                                                            in_stack_00000090,0xdfe95e8d85cdd102);
                                        (*pcVar2)(in_stack_00000048,uVar7,1,uVar12);
                                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                                           (in_stack_00000048,in_stack_00000098,
                                                            in_stack_00000090,in_stack_000000c8);
                                        (*pcVar2)(in_stack_00000048,uVar7,2,uVar12);
                                        (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
                                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                          (in_stack_00000048);
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

