/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=269 entry=18012c185 blocks=29 maxInstructions=100000 */

/* cut edge: 0x18012c185 -> 0x18014d3db */
/* cut edge: 0x18012c2b3 -> 0x18014d3db */
/* cut edge: 0x18012c2f7 -> 0x18014d3db */
/* cut edge: 0x18012c33d -> 0x18014d3db */
/* cut edge: 0x18012c395 -> 0x18014d3db */
/* cut edge: 0x18012c3b8 -> 0x18014d3db */
/* cut edge: 0x18012c59f -> 0x18014d3db */
/* cut edge: 0x18012c6ef -> 0x18014d3db */
/* cut edge: 0x18012c705 -> 0x18014d3db */
/* cut edge: 0x18012c8b0 -> 0x18014d3db */
/* cut edge: 0x18012c9a7 -> 0x18014d3db */
/* cut edge: 0x18012c9bd -> 0x18014d3db */
/* cut edge: 0x18012cb6d -> 0x18014d3db */
/* cut edge: 0x18012cc64 -> 0x18014d3db */
/* cut edge: 0x18012cc7a -> 0x18014d3db */
/* cut edge: 0x18012ce30 -> 0x18014d3db */
/* cut edge: 0x18012cf25 -> 0x18014d3db */
/* cut edge: 0x18012cf3b -> 0x18014d3db */
/* cut edge: 0x18012d0fd -> 0x18014d3db */
/* cut edge: 0x18012d0fd -> 0x18012d1fb */
/* target 0x18012d1fb original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00269_18012c185(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
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
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  longlong in_stack_00000078;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_00000168;
  undefined8 in_stack_000001c8;
  undefined2 uStack0000000000000210;
  undefined2 uStack0000000000000212;
  undefined2 uStack0000000000000214;
  undefined2 uStack0000000000000216;
  undefined2 in_stack_00000218;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_000000a0,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000001c8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
  (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
    uVar6 = FUN_18001efd0(in_stack_00000048);
    uVar7 = FUN_18001f030(in_stack_00000048);
    (*pcVar1)(in_stack_00000048,uVar6,uVar7);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x420);
      uVar6 = FUN_18001ed70(in_stack_00000048);
      uVar7 = FUN_18001f080(in_stack_00000048);
      (*pcVar1)(in_stack_00000048,uVar6,uVar7);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
        uVar6 = FUN_18001f0d0(in_stack_00000048);
        (*pcVar1)(in_stack_00000048,in_stack_00000058,in_stack_00000068,uVar6,in_stack_00000070);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804ae848 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar7 = FUN_18001e7d0(in_stack_00000048);
                uVar6 = DAT_1804b5208;
                in_stack_00000218 = 0x4d;
                uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000218,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000074d0,0x2a);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                DAT_1804ae848 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae848,in_stack_000000b0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,5,in_stack_000000a0,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_00000060)
              ;
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_00000168);
              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,2,in_stack_00000058)
              ;
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_00000078 % 0x8a1d9 ^ 0xe9e4f46701f2f052);
              (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_000000c0);
              (*pcVar1)(in_stack_00000048,uVar7,4,uVar8);
              (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804ae850 == 0) {
                      lVar2 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar7 = FUN_18014d730(in_stack_00000048);
                      uVar6 = DAT_1804b5208;
                      uStack0000000000000216 = 0x6a;
                      uVar8 = (**(code **)(lVar2 + 0x518))
                                        (in_stack_00000048,(longlong)&stack0x00000210 + 6,1);
                      lVar2 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ed8,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                      DAT_1804ae850 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ae850,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3a3a)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       in_stack_00000078 % 0x3b2b5 ^ in_stack_000000c0 ^
                                       0xd5301acec50460cc);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar6,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          if (DAT_1804ae858 == 0) {
                            lVar2 = *in_stack_00000048;
                            pcVar1 = *(code **)(lVar2 + 0x390);
                            uVar7 = FUN_18014d730(in_stack_00000048);
                            uVar6 = DAT_1804b5208;
                            uStack0000000000000214 = 0x6a;
                            uVar8 = (**(code **)(lVar2 + 0x518))
                                              (in_stack_00000048,(longlong)&stack0x00000210 + 4,1);
                            lVar2 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar2 + 0x390);
                            uVar9 = FUN_1800024f0(in_stack_00000048);
                            uVar10 = FUN_180002550(in_stack_00000048);
                            uVar11 = (**(code **)(lVar2 + 0x518))
                                               (in_stack_00000048,&stack0x00000ec8,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar12 = FUN_1800025a0(in_stack_00000048);
                            uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                            (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                            uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8)
                            ;
                            DAT_1804ae858 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ae858,in_stack_000000b0);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x358a);
                          (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00000078 % 0x772e0 ^ in_stack_000000c0 ^
                                             0x8f52ba42a20b70d9);
                          (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar6,in_stack_000000d8);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                if (DAT_1804ae860 == 0) {
                                  lVar2 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar2 + 0x390);
                                  uVar7 = FUN_18014d730(in_stack_00000048);
                                  uVar6 = DAT_1804b5208;
                                  uStack0000000000000212 = 0x6a;
                                  uVar8 = (**(code **)(lVar2 + 0x518))
                                                    (in_stack_00000048,
                                                     (longlong)&stack0x00000210 + 2,1);
                                  lVar2 = *in_stack_00000048;
                                  pcVar3 = *(code **)(lVar2 + 0x390);
                                  uVar9 = FUN_1800024f0(in_stack_00000048);
                                  uVar10 = FUN_180002550(in_stack_00000048);
                                  uVar11 = (**(code **)(lVar2 + 0x518))
                                                     (in_stack_00000048,&stack0x00000eb8,5);
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar12 = FUN_1800025a0(in_stack_00000048);
                                  uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                                  (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                  uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,
                                                    uVar8);
                                  DAT_1804ae860 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar6);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                                }
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804ae860,in_stack_000000b0
                                                  );
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0xdf8);
                                (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   in_stack_00000078 % 0xee796 ^ in_stack_000000c0 ^
                                                   0xf3e1fb062074468e);
                                (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar6,in_stack_000000d8);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if (cVar5 == '\0') {
                                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if (cVar5 == '\0') {
                                      if (DAT_1804ae868 == 0) {
                                        lVar2 = *in_stack_00000048;
                                        pcVar1 = *(code **)(lVar2 + 0x390);
                                        uVar7 = FUN_18014d730(in_stack_00000048);
                                        uVar6 = DAT_1804b5208;
                                        uStack0000000000000210 = 0x6a;
                                        uVar8 = (**(code **)(lVar2 + 0x518))
                                                          (in_stack_00000048,&stack0x00000210,1);
                                        lVar2 = *in_stack_00000048;
                                        pcVar3 = *(code **)(lVar2 + 0x390);
                                        uVar9 = FUN_1800024f0(in_stack_00000048);
                                        uVar10 = FUN_180002550(in_stack_00000048);
                                        uVar11 = (**(code **)(lVar2 + 0x518))
                                                           (in_stack_00000048,&stack0x00000ea8,5);
                                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar12 = FUN_1800025a0(in_stack_00000048);
                                        uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,
                                                           uVar12);
                                        (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                                        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,
                                                          uVar6,uVar8);
                                        DAT_1804ae868 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar6);
                                        (**(code **)(*in_stack_00000048 + 0xb8))
                                                  (in_stack_00000048,uVar6);
                                      }
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,DAT_1804ae868,
                                                         in_stack_000000b0);
                                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                        (in_stack_00000048,2,in_stack_000000a0,0);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_000000c8,
                                                         in_stack_000000d0,0x53f6);
                                      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                        (in_stack_00000048,in_stack_00000098,
                                                         in_stack_000000b8,
                                                         in_stack_00000078 % 0x31324 ^
                                                         in_stack_000000c0 ^ 0x8bdaf6a311cb0482);
                                      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                        (in_stack_00000048,uVar6,in_stack_000000a8,
                                                         uVar7);
                                      (**(code **)(*in_stack_00000048 + 0x1a0))
                                                (in_stack_00000048,uVar6,in_stack_000000d8);
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
  return;
}

