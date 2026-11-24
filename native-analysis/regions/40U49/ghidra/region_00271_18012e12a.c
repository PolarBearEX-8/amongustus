/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=271 entry=18012e12a blocks=30 maxInstructions=100000 */

/* cut edge: 0x18012e2c0 -> 0x18014d3db */
/* cut edge: 0x18012e3a4 -> 0x18014d3db */
/* cut edge: 0x18012e3dd -> 0x18014d3db */
/* cut edge: 0x18012e426 -> 0x18014d3db */
/* cut edge: 0x18012e470 -> 0x18014d3db */
/* cut edge: 0x18012e493 -> 0x18014d3db */
/* cut edge: 0x18012e68f -> 0x18014d3db */
/* cut edge: 0x18012e7d6 -> 0x18014d3db */
/* cut edge: 0x18012e7ec -> 0x18014d3db */
/* cut edge: 0x18012e995 -> 0x18014d3db */
/* cut edge: 0x18012ea8c -> 0x18014d3db */
/* cut edge: 0x18012eaa2 -> 0x18014d3db */
/* cut edge: 0x18012ec56 -> 0x18014d3db */
/* cut edge: 0x18012ed50 -> 0x18014d3db */
/* cut edge: 0x18012ed66 -> 0x18014d3db */
/* cut edge: 0x18012ef19 -> 0x18014d3db */
/* cut edge: 0x18012f00e -> 0x18014d3db */
/* cut edge: 0x18012f024 -> 0x18014d3db */
/* cut edge: 0x18012f03a -> 0x18012f1de */
/* cut edge: 0x18012f08c -> 0x18012f1de */
/* target 0x18012f1de original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


undefined8 region_00271_18012e12a(void)

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
  undefined8 uVar13;
  undefined8 in_stack_00000020;
  undefined4 uVar14;
  longlong *in_stack_00000048;
  longlong in_stack_00000058;
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
  undefined8 in_stack_000001f8;
  undefined2 uStack0000000000000200;
  undefined2 uStack0000000000000202;
  undefined2 uStack0000000000000204;
  undefined4 in_stack_00000c68;
  
  uVar14 = (undefined4)((ulonglong)in_stack_00000020 >> 0x20);
  if (DAT_1804ae898 == 0) {
    lVar1 = *in_stack_00000048;
    pcVar2 = *(code **)(lVar1 + 0x390);
    uVar5 = FUN_18001e7d0(in_stack_00000048);
    uVar11 = DAT_1804b5208;
    uStack0000000000000204 = 0x4c;
    uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,(longlong)&stack0x00000200 + 4,1);
    lVar1 = *in_stack_00000048;
    pcVar3 = *(code **)(lVar1 + 0x390);
    uVar7 = FUN_1800024f0(in_stack_00000048);
    uVar8 = FUN_180002550(in_stack_00000048);
    uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00005790,0xe);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
    uVar10 = FUN_1800025a0(in_stack_00000048);
    uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar10);
    (*pcVar3)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
    uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6);
    uVar14 = (undefined4)((ulonglong)uVar6 >> 0x20);
    DAT_1804ae898 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
  }
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804ae898,in_stack_000000b0);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                     in_stack_00000058 % 0xb293a ^ 0x161a617f915b4ead);
  (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
  (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)uVar5 == '\0') {
    pcVar2 = *(code **)(*in_stack_00000048 + 0xd8);
    uVar6 = FUN_18014e440(in_stack_00000048);
    uVar7 = (*pcVar2)(in_stack_00000048,uVar6);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar5 == '\0') {
      pcVar2 = *(code **)(*in_stack_00000048 + 0x488);
      uVar5 = FUN_18001efd0(in_stack_00000048);
      uVar8 = FUN_18001f030(in_stack_00000048);
      (*pcVar2)(in_stack_00000048,uVar5,uVar8);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)uVar5 == '\0') {
        pcVar2 = *(code **)(*in_stack_00000048 + 0x2d8);
        uVar5 = FUN_18014e4a0(in_stack_00000048);
        (*pcVar2)(in_stack_00000048,uVar7,uVar6,uVar5,CONCAT44(uVar14,in_stack_00000c68));
        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)uVar5 == '\0') {
          uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)uVar5 == '\0') {
            uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)uVar5 == '\0') {
              if (DAT_1804ae8a0 == 0) {
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar6 = FUN_18001e7d0(in_stack_00000048);
                uVar5 = DAT_1804b5208;
                uStack0000000000000202 = 0x4d;
                uVar8 = (**(code **)(lVar1 + 0x518))
                                  (in_stack_00000048,(longlong)&stack0x00000200 + 2,1);
                lVar1 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00007470,0x2a);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar13);
                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar12,uVar13);
                uVar5 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar5,uVar8);
                DAT_1804ae8a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae8a0,in_stack_000000b0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,5,in_stack_000000a0,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,0,uVar11);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                  in_stack_00000168);
              (*pcVar2)(in_stack_00000048,uVar6,1,uVar11);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,2,uVar7);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                  in_stack_00000058 % 0x3ef18 ^ 0x161b0b98fe0dc3af);
              (*pcVar2)(in_stack_00000048,uVar6,3,uVar11);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                  in_stack_000000c0);
              (*pcVar2)(in_stack_00000048,uVar6,4,uVar11);
              (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)uVar5 == '\0') {
                uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)uVar5 == '\0') {
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)uVar5 == '\0') {
                    if (DAT_1804ae8a8 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar5 = FUN_18014d730(in_stack_00000048);
                      uVar11 = DAT_1804b5208;
                      uStack0000000000000200 = 0x6a;
                      uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000200,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_1800024f0(in_stack_00000048);
                      uVar8 = FUN_180002550(in_stack_00000048);
                      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000e58,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar10 = FUN_1800025a0(in_stack_00000048);
                      uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar10);
                      (*pcVar3)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                      uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6);
                      DAT_1804ae8a8 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                    }
                    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804ae8a8,in_stack_000000b0);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x53a4)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       in_stack_00000058 % 0xb2224 ^ in_stack_000000c0 ^
                                       0x4b835c25f40309fb);
                    (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
                    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar11,in_stack_000000d8);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar5 == '\0') {
                      uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)uVar5 == '\0') {
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if ((char)uVar5 == '\0') {
                          if (DAT_1804ae8b0 == 0) {
                            lVar1 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar1 + 0x390);
                            uVar5 = FUN_18014d730(in_stack_00000048);
                            uVar11 = DAT_1804b5208;
                            in_stack_000001f8._6_2_ = 0x6a;
                            uVar6 = (**(code **)(lVar1 + 0x518))
                                              (in_stack_00000048,(longlong)&stack0x000001f8 + 6,1);
                            lVar1 = *in_stack_00000048;
                            pcVar3 = *(code **)(lVar1 + 0x390);
                            uVar7 = FUN_1800024f0(in_stack_00000048);
                            uVar8 = FUN_180002550(in_stack_00000048);
                            uVar9 = (**(code **)(lVar1 + 0x518))
                                              (in_stack_00000048,&stack0x00000e48,5);
                            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar10 = FUN_1800025a0(in_stack_00000048);
                            uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar10);
                            (*pcVar3)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                            uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,
                                               uVar6);
                            DAT_1804ae8b0 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                          }
                          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804ae8b0,in_stack_000000b0);
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x2e2);
                          (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00000058 % 0x463f8 ^ in_stack_000000c0 ^
                                             0x1bcb106d478d2fcd);
                          (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
                          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar11,in_stack_000000d8);
                          uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)uVar5 == '\0') {
                            uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)uVar5 == '\0') {
                              uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)uVar5 == '\0') {
                                if (DAT_1804ae8b8 == 0) {
                                  lVar1 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar1 + 0x390);
                                  uVar5 = FUN_18014d730(in_stack_00000048);
                                  uVar11 = DAT_1804b5208;
                                  in_stack_000001f8._4_2_ = 0x6a;
                                  uVar6 = (**(code **)(lVar1 + 0x518))
                                                    (in_stack_00000048,
                                                     (longlong)&stack0x000001f8 + 4,1);
                                  lVar1 = *in_stack_00000048;
                                  pcVar3 = *(code **)(lVar1 + 0x390);
                                  uVar7 = FUN_1800024f0(in_stack_00000048);
                                  uVar8 = FUN_180002550(in_stack_00000048);
                                  uVar9 = (**(code **)(lVar1 + 0x518))
                                                    (in_stack_00000048,&stack0x00000e38,5);
                                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar10 = FUN_1800025a0(in_stack_00000048);
                                  uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar10);
                                  (*pcVar3)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                                  uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar5,
                                                     uVar11,uVar6);
                                  DAT_1804ae8b8 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar11);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11)
                                  ;
                                }
                                uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,DAT_1804ae8b8,
                                                    in_stack_000000b0);
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x500a);
                                (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   in_stack_00000058 % 0x58d01 ^ in_stack_000000c0 ^
                                                   0x5117b986e40b57ae);
                                (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
                                uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,uVar11,in_stack_000000a8,uVar5
                                                   );
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar11,in_stack_000000d8);
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if ((char)uVar5 == '\0') {
                                  uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                  if ((char)uVar5 == '\0') {
                                    uVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                    if ((char)uVar5 == '\0') {
                                      uVar11 = 0x734d05c649cb65cf;
                                      if (DAT_1804ae8c0 == 0) {
                                        lVar1 = *in_stack_00000048;
                                        pcVar2 = *(code **)(lVar1 + 0x390);
                                        uVar5 = FUN_18014d730(in_stack_00000048);
                                        uVar11 = DAT_1804b5208;
                                        in_stack_000001f8._2_2_ = 0x6a;
                                        uVar6 = (**(code **)(lVar1 + 0x518))
                                                          (in_stack_00000048,
                                                           (longlong)&stack0x000001f8 + 2,1);
                                        lVar1 = *in_stack_00000048;
                                        pcVar3 = *(code **)(lVar1 + 0x390);
                                        uVar7 = FUN_1800024f0(in_stack_00000048);
                                        uVar8 = FUN_180002550(in_stack_00000048);
                                        uVar9 = (**(code **)(lVar1 + 0x518))
                                                          (in_stack_00000048,&stack0x00000e28,5);
                                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                        uVar10 = FUN_1800025a0(in_stack_00000048);
                                        uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000090,
                                                           uVar10);
                                        (*pcVar3)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                                        uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar5
                                                           ,uVar11,uVar6);
                                        DAT_1804ae8c0 =
                                             (**(code **)(*in_stack_00000048 + 0xa8))
                                                       (in_stack_00000048,uVar11);
                                        uVar11 = (**(code **)(*in_stack_00000048 + 0xb8))
                                                           (in_stack_00000048,uVar11);
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
  return uVar5;
}

