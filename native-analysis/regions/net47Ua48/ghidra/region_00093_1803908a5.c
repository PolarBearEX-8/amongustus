/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=93 entry=1803908a5 blocks=32 maxInstructions=50000 */

/* cut edge: 0x1803908a5 -> 0x1803351d6 */
/* cut edge: 0x1803908e0 -> 0x1803351d6 */
/* cut edge: 0x18039091b -> 0x1803351d6 */
/* cut edge: 0x18039095e -> 0x1803351d6 */
/* cut edge: 0x180390999 -> 0x1803351d6 */
/* cut edge: 0x180390b05 -> 0x1803351d6 */
/* cut edge: 0x180390be7 -> 0x1803351d6 */
/* cut edge: 0x180390d93 -> 0x1803351d6 */
/* cut edge: 0x180390e8d -> 0x1803351d6 */
/* cut edge: 0x180390ec8 -> 0x1803351d6 */
/* cut edge: 0x180390f06 -> 0x1803351d6 */
/* cut edge: 0x1803910b8 -> 0x1803351d6 */
/* cut edge: 0x18039120d -> 0x1803351d6 */
/* cut edge: 0x180391250 -> 0x1803351d6 */
/* cut edge: 0x1803913ff -> 0x1803351d6 */
/* cut edge: 0x1803914df -> 0x1803351d6 */
/* cut edge: 0x180391682 -> 0x1803351d6 */
/* cut edge: 0x180391757 -> 0x1803351d6 */
/* cut edge: 0x18039179d -> 0x1803351d6 */
/* cut edge: 0x1803917db -> 0x1803351d6 */
/* cut edge: 0x1803917f1 -> 0x180391973 */
/* cut edge: 0x180391801 -> 0x180391973 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180391973 original byte 0x48 replaced by RET */


void region_00093_1803908a5(void)

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
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000f0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00000110;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_000001c8;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000001a0,in_stack_00000110);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,in_stack_000000f8,in_stack_000000a8,uVar6);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000492,1);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b2d68 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_180333500(in_stack_00000048);
              uVar7 = DAT_1804b59f0;
              uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000490,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar10 = FUN_1800024f0(in_stack_00000048);
              uVar11 = FUN_180002550(in_stack_00000048);
              uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002970,0xd);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar13 = FUN_1800025a0(in_stack_00000048);
              uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
              (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
              uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
              DAT_1804b2d68 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
            }
            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b2d68,in_stack_00000078);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               0xdfc5815bc278527a);
            (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000000c8);
            (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                if (DAT_1804b2d70 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_180333500(in_stack_00000048);
                  uVar8 = DAT_1804b59f0;
                  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000048e,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar11 = FUN_1800024f0(in_stack_00000048);
                  uVar12 = FUN_180002550(in_stack_00000048);
                  uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00007640,0x28);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar14 = FUN_1800025a0(in_stack_00000048);
                  uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                  (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                  uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
                  DAT_1804b2d70 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b2d70,in_stack_00000078);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,3,in_stack_00000088,0);
                (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,uVar7);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   0xdfe95e8d85cdd102);
                (*pcVar2)(in_stack_00000048,uVar9,1,uVar7);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_000000c8);
                (*pcVar2)(in_stack_00000048,uVar9,2,uVar7);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar7 = FUN_180010980(in_stack_00000048);
                    uVar6 = (*pcVar2)(in_stack_00000048,uVar6,uVar7);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        if (DAT_1804b2d78 == 0) {
                          lVar1 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar1 + 0x390);
                          uVar8 = FUN_180333500(in_stack_00000048);
                          uVar7 = DAT_1804b59f0;
                          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000048c,1)
                          ;
                          lVar1 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar1 + 0x390);
                          uVar10 = FUN_1800024f0(in_stack_00000048);
                          uVar11 = FUN_180002550(in_stack_00000048);
                          uVar12 = (**(code **)(lVar1 + 0x518))
                                             (in_stack_00000048,&stack0x00006150,0x2a);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar13 = FUN_1800025a0(in_stack_00000048);
                          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                          uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
                          DAT_1804b2d78 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                        }
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b2d78,in_stack_00000078);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,5,in_stack_00000088,0);
                        (**(code **)(*in_stack_00000048 + 0x570))
                                  (in_stack_00000048,uVar8,0,in_stack_000000f0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_000001c8);
                        (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,2,uVar6);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           0xdfc0096f727d872c);
                        (*pcVar2)(in_stack_00000048,uVar8,3,uVar6);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                           in_stack_000000c8);
                        (*pcVar2)(in_stack_00000048,uVar8,4,uVar6);
                        (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          pcVar2 = *(code **)(*in_stack_00000048 + 0xd8);
                          uVar6 = FUN_18001ee70(in_stack_00000048);
                          (*pcVar2)(in_stack_00000048,uVar6);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              if (DAT_1804b2d80 == 0) {
                                lVar1 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar1 + 0x390);
                                uVar7 = FUN_1803335a0(in_stack_00000048);
                                uVar6 = DAT_1804b59f0;
                                uVar8 = (**(code **)(lVar1 + 0x518))
                                                  (in_stack_00000048,&stack0x0000048a,1);
                                lVar1 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar1 + 0x390);
                                uVar9 = FUN_1800024f0(in_stack_00000048);
                                uVar10 = FUN_180002550(in_stack_00000048);
                                uVar11 = (**(code **)(lVar1 + 0x518))
                                                   (in_stack_00000048,&stack0x00003cc0,0x16);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar12 = FUN_1800025a0(in_stack_00000048);
                                uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar6,
                                                  uVar8);
                                DAT_1804b2d80 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar6);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                              }
                              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,DAT_1804b2d80,in_stack_00000078);
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000088,0);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000068,
                                                 in_stack_00000060,0x50e0);
                              (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,
                                                 in_stack_000000c8 ^ 0x5bd1c33d2263cad2);
                              (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                              (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar6,in_stack_00000080,uVar7);
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  if (DAT_1804b2d88 == 0) {
                                    lVar1 = *in_stack_00000048;
                                    pcVar2 = *(code **)(lVar1 + 0x390);
                                    uVar7 = FUN_1803335a0(in_stack_00000048);
                                    uVar6 = DAT_1804b59f0;
                                    uVar8 = (**(code **)(lVar1 + 0x518))
                                                      (in_stack_00000048,&stack0x00000488,1);
                                    lVar1 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar1 + 0x390);
                                    uVar9 = FUN_1800024f0(in_stack_00000048);
                                    uVar10 = FUN_180002550(in_stack_00000048);
                                    uVar11 = (**(code **)(lVar1 + 0x518))
                                                       (in_stack_00000048,&stack0x00003c90,0x16);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar12 = FUN_1800025a0(in_stack_00000048);
                                    uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                                    (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                    uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,
                                                      uVar6,uVar8);
                                    DAT_1804b2d88 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar6);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar6);
                                  }
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,DAT_1804b2d88,
                                                     in_stack_00000078);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_00000088,0);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000068,
                                                     in_stack_00000060,0x2a89);
                                  (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000098,
                                                     in_stack_00000090,
                                                     in_stack_000000c8 ^ 0x12d62572f7af30f4);
                                  (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar6,in_stack_00000080,uVar7
                                                    );
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar5 == '\0') {
                                    pcVar2 = *(code **)(*in_stack_00000048 + 0x488);
                                    uVar7 = FUN_18001eed0(in_stack_00000048);
                                    uVar8 = FUN_18001ef30(in_stack_00000048);
                                    uVar7 = (*pcVar2)(in_stack_00000048,uVar7,uVar8);
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                      (in_stack_00000048);
                                    if (cVar5 == '\0') {
                                      pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                                      uVar8 = FUN_18001ef80(in_stack_00000048);
                                      (*pcVar2)(in_stack_00000048,uVar6,uVar8,uVar7);
                                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                                      if (cVar5 == '\0') {
                                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                        if (cVar5 == '\0') {
                                          if (DAT_1804b2d90 == 0) {
                                            lVar1 = *in_stack_00000048;
                                            pcVar2 = *(code **)(lVar1 + 0x390);
                                            uVar7 = FUN_180333500(in_stack_00000048);
                                            uVar6 = DAT_1804b59f0;
                                            uVar8 = (**(code **)(lVar1 + 0x518))
                                                              (in_stack_00000048,&stack0x00000486,1)
                                            ;
                                            lVar1 = *in_stack_00000048;
                                            pcVar3 = *(code **)(lVar1 + 0x390);
                                            uVar9 = FUN_1800024f0(in_stack_00000048);
                                            uVar10 = FUN_180002550(in_stack_00000048);
                                            uVar11 = (**(code **)(lVar1 + 0x518))
                                                               (in_stack_00000048,&stack0x00002c60,
                                                                0x19);
                                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                            uVar12 = FUN_1800025a0(in_stack_00000048);
                                            uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,
                                                               uVar12);
                                            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                            uVar6 = (*pcVar2)(in_stack_00000048,in_stack_00000058,
                                                              uVar7,uVar6,uVar8);
                                            DAT_1804b2d90 =
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

