/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=41 entry=1802c1d81 blocks=32 maxInstructions=50000 */

/* cut edge: 0x1802c1eef -> 0x180333186 */
/* cut edge: 0x1802c1fd3 -> 0x180333186 */
/* cut edge: 0x1802c2008 -> 0x180333186 */
/* cut edge: 0x1802c203b -> 0x180333186 */
/* cut edge: 0x1802c207e -> 0x180333186 */
/* cut edge: 0x1802c20b3 -> 0x180333186 */
/* cut edge: 0x1802c20c9 -> 0x180333186 */
/* cut edge: 0x1802c227d -> 0x180333186 */
/* cut edge: 0x1802c2361 -> 0x180333186 */
/* cut edge: 0x1802c2377 -> 0x180333186 */
/* cut edge: 0x1802c2558 -> 0x180333186 */
/* cut edge: 0x1802c264e -> 0x180333186 */
/* cut edge: 0x1802c2683 -> 0x180333186 */
/* cut edge: 0x1802c26bc -> 0x180333186 */
/* cut edge: 0x1802c26df -> 0x180333186 */
/* cut edge: 0x1802c28c7 -> 0x180333186 */
/* cut edge: 0x1802c2a07 -> 0x180333186 */
/* cut edge: 0x1802c2a1d -> 0x180333186 */
/* cut edge: 0x1802c2bc3 -> 0x180333186 */
/* cut edge: 0x1802c2cba -> 0x180333186 */
/* cut edge: 0x1802c2cd0 -> 0x180333186 */
/* cut edge: 0x1802c2ce6 -> 0x1802c2e78 */
/* cut edge: 0x1802c2d38 -> 0x1802c2e78 */
/* target 0x1802c2e78 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


undefined8 region_00041_1802c1d81(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  longlong in_stack_00000070;
  code *pcStack0000000000000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000006f0;
  
  lVar1 = *in_stack_00000040;
  pcStack0000000000000078 = *(code **)(lVar1 + 0x390);
  uVar5 = FUN_1803335a0(in_stack_00000040);
  uVar11 = DAT_1804b59f0;
  uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000060c,1);
  lVar1 = *in_stack_00000040;
  pcVar2 = *(code **)(lVar1 + 0x390);
  uVar7 = FUN_1800024f0(in_stack_00000040);
  uVar8 = FUN_180002550(in_stack_00000040);
  uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000036f0,0x16);
  pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
  uVar10 = FUN_1800025a0(in_stack_00000040);
  uVar10 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar10);
  (*pcVar2)(in_stack_00000040,uVar7,uVar8,uVar9,uVar10);
  uVar11 = (*pcStack0000000000000078)(in_stack_00000040,in_stack_00000080,uVar5,uVar11,uVar6);
  DAT_1804b0d18 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar11);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar11);
  uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                     (in_stack_00000040,DAT_1804b0d18,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x30dd);
  (*pcVar2)(in_stack_00000040,uVar5,0,uVar6);
  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar2)(in_stack_00000040,uVar5,1,uVar6);
  uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                     (in_stack_00000040,uVar11,in_stack_000000a8,uVar5);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if ((char)uVar5 == '\0') {
    (**(code **)(*in_stack_00000040 + 0x390))
              (in_stack_00000040,in_stack_00000048,in_stack_00000058,uVar11);
    uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if ((char)uVar5 == '\0') {
      uVar11 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040);
      uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if ((char)uVar5 == '\0') {
        uVar6 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x0000060a,1);
        uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if ((char)uVar5 == '\0') {
          uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                             (in_stack_00000040,uVar11,in_stack_00000060,uVar6);
          uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
          if ((char)uVar5 == '\0') {
            uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
            if ((char)uVar5 == '\0') {
              uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
              if ((char)uVar5 == '\0') {
                if (DAT_1804b0d20 == 0) {
                  lVar1 = *in_stack_00000040;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  pcStack0000000000000078 = (code *)FUN_180333500(in_stack_00000040);
                  uVar5 = DAT_1804b59f0;
                  uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000608,1);
                  lVar1 = *in_stack_00000040;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_1800024f0(in_stack_00000040);
                  uVar8 = FUN_180002550(in_stack_00000040);
                  uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00003270,0xd);
                  pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                  uVar10 = FUN_1800025a0(in_stack_00000040);
                  uVar10 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar10);
                  (*pcVar3)(in_stack_00000040,uVar7,uVar8,uVar9,uVar10);
                  uVar5 = (*pcVar2)(in_stack_00000040,in_stack_00000080,pcStack0000000000000078,
                                    uVar5,uVar6);
                  DAT_1804b0d20 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5);
                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
                }
                uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                  (in_stack_00000040,DAT_1804b0d20,in_stack_000000a0);
                uVar6 = (**(code **)(*in_stack_00000040 + 0x560))
                                  (in_stack_00000040,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   in_stack_00000070 % 0x83b54 ^ 0xdf3a88030c14e71f);
                (*pcVar2)(in_stack_00000040,uVar6,0,uVar7);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   in_stack_000000b0);
                (*pcVar2)(in_stack_00000040,uVar6,1,uVar7);
                uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                  (in_stack_00000040,uVar5,in_stack_000000a8,uVar6);
                uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                if ((char)uVar5 == '\0') {
                  uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                  if ((char)uVar5 == '\0') {
                    uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                    if ((char)uVar5 == '\0') {
                      if (DAT_1804b0d28 == 0) {
                        lVar1 = *in_stack_00000040;
                        pcVar2 = *(code **)(lVar1 + 0x390);
                        pcStack0000000000000078 = (code *)FUN_180333500(in_stack_00000040);
                        uVar5 = DAT_1804b59f0;
                        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000606,1);
                        lVar1 = *in_stack_00000040;
                        pcVar3 = *(code **)(lVar1 + 0x390);
                        uVar8 = FUN_1800024f0(in_stack_00000040);
                        uVar9 = FUN_180002550(in_stack_00000040);
                        uVar10 = (**(code **)(lVar1 + 0x518))
                                           (in_stack_00000040,&stack0x00003df0,0x28);
                        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                        uVar12 = FUN_1800025a0(in_stack_00000040);
                        uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                        (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar12);
                        uVar5 = (*pcVar2)(in_stack_00000040,in_stack_00000080,
                                          pcStack0000000000000078,uVar5,uVar7);
                        DAT_1804b0d28 =
                             (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5);
                        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
                      }
                      uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                        (in_stack_00000040,DAT_1804b0d28,in_stack_000000a0);
                      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                        (in_stack_00000040,3,in_stack_00000090,0);
                      (**(code **)(*in_stack_00000040 + 0x570))(in_stack_00000040,uVar7,0,uVar6);
                      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                      uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                         in_stack_00000070 % 0x1058c ^ 0xdf1657d54ba5e0ff);
                      (*pcVar2)(in_stack_00000040,uVar7,1,uVar6);
                      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                      uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                         in_stack_000000b0);
                      (*pcVar2)(in_stack_00000040,uVar7,2,uVar6);
                      uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                        (in_stack_00000040,uVar5,in_stack_000000a8,uVar7);
                      uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                      if ((char)uVar5 == '\0') {
                        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                           (in_stack_00000040,uVar11,in_stack_00000060,uVar6);
                        uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                        if ((char)uVar5 == '\0') {
                          pcVar2 = *(code **)(*in_stack_00000040 + 0x110);
                          uVar5 = FUN_180010980(in_stack_00000040);
                          uVar11 = (*pcVar2)(in_stack_00000040,uVar11,uVar5);
                          uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                          if ((char)uVar5 == '\0') {
                            uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                            if ((char)uVar5 == '\0') {
                              uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                              if ((char)uVar5 == '\0') {
                                if (DAT_1804b0d30 == 0) {
                                  lVar1 = *in_stack_00000040;
                                  pcVar2 = *(code **)(lVar1 + 0x390);
                                  uVar6 = FUN_180333500(in_stack_00000040);
                                  uVar5 = DAT_1804b59f0;
                                  pcStack0000000000000078 =
                                       (code *)(**(code **)(lVar1 + 0x518))
                                                         (in_stack_00000040,&stack0x00000604,1);
                                  lVar1 = *in_stack_00000040;
                                  pcVar3 = *(code **)(lVar1 + 0x390);
                                  uVar7 = FUN_1800024f0(in_stack_00000040);
                                  uVar8 = FUN_180002550(in_stack_00000040);
                                  uVar9 = (**(code **)(lVar1 + 0x518))
                                                    (in_stack_00000040,&stack0x00003c90,0x2a);
                                  pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                                  uVar10 = FUN_1800025a0(in_stack_00000040);
                                  uVar10 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar10);
                                  (*pcVar3)(in_stack_00000040,uVar7,uVar8,uVar9,uVar10);
                                  uVar5 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar5,
                                                    pcStack0000000000000078);
                                  DAT_1804b0d30 =
                                       (**(code **)(*in_stack_00000040 + 0xa8))
                                                 (in_stack_00000040,uVar5);
                                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
                                }
                                uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                                  (in_stack_00000040,DAT_1804b0d30,in_stack_000000a0
                                                  );
                                uVar6 = (**(code **)(*in_stack_00000040 + 0x560))
                                                  (in_stack_00000040,5,in_stack_00000090,0);
                                (**(code **)(*in_stack_00000040 + 0x570))
                                          (in_stack_00000040,uVar6,0,in_stack_00000068);
                                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                                  (in_stack_00000040,in_stack_00000088,
                                                   in_stack_00000098,in_stack_000006f0);
                                (*pcVar2)(in_stack_00000040,uVar6,1,uVar7);
                                (**(code **)(*in_stack_00000040 + 0x570))
                                          (in_stack_00000040,uVar6,2,uVar11);
                                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                                uVar11 = (**(code **)(*in_stack_00000040 + 0x390))
                                                   (in_stack_00000040,in_stack_00000088,
                                                    in_stack_00000098,
                                                    in_stack_00000070 % 0xeafc6 ^ 0xdf3f0037bc1c229b
                                                   );
                                (*pcVar2)(in_stack_00000040,uVar6,3,uVar11);
                                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                                uVar11 = (**(code **)(*in_stack_00000040 + 0x390))
                                                   (in_stack_00000040,in_stack_00000088,
                                                    in_stack_00000098,in_stack_000000b0);
                                (*pcVar2)(in_stack_00000040,uVar6,4,uVar11);
                                (**(code **)(*in_stack_00000040 + 0x110))
                                          (in_stack_00000040,uVar5,in_stack_000000a8,uVar6);
                                uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040)
                                ;
                                if ((char)uVar5 == '\0') {
                                  uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                                  if ((char)uVar5 == '\0') {
                                    uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                                    if ((char)uVar5 == '\0') {
                                      if (DAT_1804b0d38 == 0) {
                                        lVar1 = *in_stack_00000040;
                                        pcVar2 = *(code **)(lVar1 + 0x390);
                                        uVar5 = FUN_1803335f0(in_stack_00000040);
                                        uVar11 = DAT_1804b59f0;
                                        uVar6 = (**(code **)(lVar1 + 0x518))
                                                          (in_stack_00000040,&stack0x00000602,1);
                                        lVar1 = *in_stack_00000040;
                                        pcVar3 = *(code **)(lVar1 + 0x390);
                                        uVar7 = FUN_1800024f0(in_stack_00000040);
                                        uVar8 = FUN_180002550(in_stack_00000040);
                                        uVar9 = (**(code **)(lVar1 + 0x518))
                                                          (in_stack_00000040,&stack0x000028a8,5);
                                        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                                        uVar10 = FUN_1800025a0(in_stack_00000040);
                                        uVar10 = (*pcVar4)(in_stack_00000040,in_stack_00000080,
                                                           uVar10);
                                        (*pcVar3)(in_stack_00000040,uVar7,uVar8,uVar9,uVar10);
                                        uVar11 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar5
                                                           ,uVar11,uVar6);
                                        DAT_1804b0d38 =
                                             (**(code **)(*in_stack_00000040 + 0xa8))
                                                       (in_stack_00000040,uVar11);
                                        (**(code **)(*in_stack_00000040 + 0xb8))
                                                  (in_stack_00000040,uVar11);
                                      }
                                      uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                                         (in_stack_00000040,DAT_1804b0d38,
                                                          in_stack_000000a0);
                                      uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                                        (in_stack_00000040,2,in_stack_00000090,0);
                                      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                                      uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                                        (in_stack_00000040,in_stack_000000b8,
                                                         in_stack_000000c0,0x5719);
                                      (*pcVar2)(in_stack_00000040,uVar5,0,uVar6);
                                      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                                      uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                                        (in_stack_00000040,in_stack_00000088,
                                                         in_stack_00000098,
                                                         in_stack_00000070 % 0x70b5e ^
                                                         in_stack_000000b0 ^ 0x7d73e4e228476e0a);
                                      (*pcVar2)(in_stack_00000040,uVar5,1,uVar6);
                                      uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                                         (in_stack_00000040,uVar11,in_stack_000000a8
                                                          ,uVar5);
                                      (**(code **)(*in_stack_00000040 + 0x1a0))
                                                (in_stack_00000040,uVar11,in_stack_000000d0);
                                      uVar5 = (**(code **)(*in_stack_00000040 + 0x720))
                                                        (in_stack_00000040);
                                      if ((char)uVar5 == '\0') {
                                        uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                                        if ((char)uVar5 == '\0') {
                                          uVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                                          if ((char)uVar5 == '\0') {
                                            uVar11 = 0xc056028243127a2;
                                            if (DAT_1804b0d40 == 0) {
                                              lVar1 = *in_stack_00000040;
                                              pcVar2 = *(code **)(lVar1 + 0x390);
                                              uVar5 = FUN_1803335f0(in_stack_00000040);
                                              uVar11 = DAT_1804b59f0;
                                              pcStack0000000000000078 =
                                                   (code *)(**(code **)(lVar1 + 0x518))
                                                                     (in_stack_00000040,
                                                                      &stack0x00000600,1);
                                              lVar1 = *in_stack_00000040;
                                              pcVar3 = *(code **)(lVar1 + 0x390);
                                              uVar6 = FUN_1800024f0(in_stack_00000040);
                                              uVar7 = FUN_180002550(in_stack_00000040);
                                              uVar8 = (**(code **)(lVar1 + 0x518))
                                                                (in_stack_00000040,&stack0x00002898,
                                                                 5);
                                              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                                              uVar9 = FUN_1800025a0(in_stack_00000040);
                                              uVar9 = (*pcVar4)(in_stack_00000040,in_stack_00000080,
                                                                uVar9);
                                              (*pcVar3)(in_stack_00000040,uVar6,uVar7,uVar8,uVar9);
                                              uVar11 = (*pcVar2)(in_stack_00000040,in_stack_00000080
                                                                 ,uVar5,uVar11,
                                                                 pcStack0000000000000078);
                                              DAT_1804b0d40 =
                                                   (**(code **)(*in_stack_00000040 + 0xa8))
                                                             (in_stack_00000040,uVar11);
                                              uVar11 = (**(code **)(*in_stack_00000040 + 0xb8))
                                                                 (in_stack_00000040,uVar11);
                                            }
                                            return uVar11;
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
  }
  return uVar5;
}

