/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=137 entry=1804645aa blocks=30 maxInstructions=50000 */

/* cut edge: 0x1804645aa -> 0x180473ae8 */
/* cut edge: 0x1804646e2 -> 0x180473ae8 */
/* cut edge: 0x1804646f8 -> 0x180473ae8 */
/* cut edge: 0x1804648b8 -> 0x180473ae8 */
/* cut edge: 0x1804649b7 -> 0x1804675da */
/* cut edge: 0x1804649e6 -> 0x180473ae8 */
/* cut edge: 0x1804649f9 -> 0x180473ae8 */
/* cut edge: 0x180464bb8 -> 0x180473ae8 */
/* cut edge: 0x180464caa -> 0x180473ae8 */
/* cut edge: 0x180464cc0 -> 0x180473ae8 */
/* cut edge: 0x180464e7f -> 0x180473ae8 */
/* cut edge: 0x180464f71 -> 0x180473ae8 */
/* cut edge: 0x180464f87 -> 0x180473ae8 */
/* cut edge: 0x180465146 -> 0x180473ae8 */
/* cut edge: 0x18046523b -> 0x180473ae8 */
/* cut edge: 0x180465251 -> 0x180473ae8 */
/* cut edge: 0x18046541e -> 0x180473ae8 */
/* cut edge: 0x180465513 -> 0x180468408 */
/* cut edge: 0x180465525 -> 0x180473ae8 */
/* cut edge: 0x180465525 -> 0x180465560 */
/* target 0x180465560 original byte 0x48 replaced by RET */
/* target 0x1804675da original byte 0x48 replaced by RET */
/* target 0x180468408 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00137_1804645aa(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong lVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  char in_stack_00000118;
  undefined8 in_stack_000001c0;
  undefined8 in_stack_00002f48;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000090,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_00000058);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002f48);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
  lVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b46b0 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar7 = FUN_180333500(in_stack_00000048);
          uVar6 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000322,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000048);
          uVar11 = FUN_180002550(in_stack_00000048);
          uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003950,0x17);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
          DAT_1804b46b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b46b0,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000090,0);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_000001c0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0x29018 ^ 0x503a17d536069048);
        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000000c8);
        (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
        (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          if (in_stack_00000118 == '\0') {
            return;
          }
          if (lVar9 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
            uVar6 = FUN_18001ec20(in_stack_00000048);
            (*pcVar1)(in_stack_00000048,uVar6);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              return;
            }
          }
          else {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar5 == '\0') {
                if (DAT_1804b46b8 == 0) {
                  lVar9 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar9 + 0x390);
                  uVar7 = FUN_1803335f0(in_stack_00000048);
                  uVar6 = DAT_1804b59f0;
                  uVar8 = (**(code **)(lVar9 + 0x518))(in_stack_00000048,&stack0x00000320,1);
                  lVar9 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar9 + 0x390);
                  uVar10 = FUN_1800024f0(in_stack_00000048);
                  uVar11 = FUN_180002550(in_stack_00000048);
                  uVar12 = (**(code **)(lVar9 + 0x518))(in_stack_00000048,&stack0x00000ed0,5);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar13 = FUN_1800025a0(in_stack_00000048);
                  uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
                  (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                  uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                  DAT_1804b46b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                }
                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b46b8,in_stack_000000a0);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000090,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x638c);
                (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                   in_stack_00000068 % 0xbe695 ^ in_stack_000000c8 ^
                                   0x9f166c689d22241e);
                (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8)
                ;
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar5 == '\0') {
                      if (DAT_1804b46c0 == 0) {
                        lVar9 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar9 + 0x390);
                        uVar7 = FUN_1803335f0(in_stack_00000048);
                        uVar6 = DAT_1804b59f0;
                        uVar8 = (**(code **)(lVar9 + 0x518))(in_stack_00000048,&stack0x0000031e,1);
                        lVar9 = *in_stack_00000048;
                        pcVar3 = *(code **)(lVar9 + 0x390);
                        uVar10 = FUN_1800024f0(in_stack_00000048);
                        uVar11 = FUN_180002550(in_stack_00000048);
                        uVar12 = (**(code **)(lVar9 + 0x518))(in_stack_00000048,&stack0x00000ec0,5);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar13 = FUN_1800025a0(in_stack_00000048);
                        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
                        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                        DAT_1804b46c0 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b46c0,in_stack_000000a0);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_00000090,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                         0x56f8);
                      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                         in_stack_00000068 % 0x23a8c ^ in_stack_000000c8 ^
                                         0xd6fb03eb6b3bb288);
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
                            if (DAT_1804b46c8 == 0) {
                              lVar9 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar9 + 0x390);
                              uVar7 = FUN_1803335f0(in_stack_00000048);
                              uVar6 = DAT_1804b59f0;
                              uVar8 = (**(code **)(lVar9 + 0x518))
                                                (in_stack_00000048,&stack0x0000031c,1);
                              lVar9 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar9 + 0x390);
                              uVar10 = FUN_1800024f0(in_stack_00000048);
                              uVar11 = FUN_180002550(in_stack_00000048);
                              uVar12 = (**(code **)(lVar9 + 0x518))
                                                 (in_stack_00000048,&stack0x00000eb0,5);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar13 = FUN_1800025a0(in_stack_00000048);
                              uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
                              (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                              uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,
                                                uVar8);
                              DAT_1804b46c8 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                            }
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b46c8,in_stack_000000a0);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,2,in_stack_00000090,0);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0
                                               ,0x6d26);
                            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000080,in_stack_00000098
                                               ,in_stack_00000068 % 0x3beeb ^ in_stack_000000c8 ^
                                                0xa76941b6e4df111d);
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
                                  if (DAT_1804b46d0 == 0) {
                                    lVar9 = *in_stack_00000048;
                                    pcVar1 = *(code **)(lVar9 + 0x390);
                                    uVar7 = FUN_1803335f0(in_stack_00000048);
                                    uVar6 = DAT_1804b59f0;
                                    uVar8 = (**(code **)(lVar9 + 0x518))
                                                      (in_stack_00000048,&stack0x0000031a,1);
                                    lVar9 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar9 + 0x390);
                                    uVar10 = FUN_1800024f0(in_stack_00000048);
                                    uVar11 = FUN_180002550(in_stack_00000048);
                                    uVar12 = (**(code **)(lVar9 + 0x518))
                                                       (in_stack_00000048,&stack0x00000ea0,5);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar13 = FUN_1800025a0(in_stack_00000048);
                                    uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
                                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                                    uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,
                                                      uVar6,uVar8);
                                    DAT_1804b46d0 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar6);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar6);
                                  }
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,DAT_1804b46d0,
                                                     in_stack_000000a0);
                                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,2,in_stack_00000090,0);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_000000b8,
                                                     in_stack_000000c0,0x139c);
                                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000080,
                                                     in_stack_00000098,
                                                     in_stack_00000068 % 0xac221 ^ in_stack_000000c8
                                                     ^ 0xf8321fb561790037);
                                  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,uVar6,in_stack_000000a8,uVar7
                                                    );
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
  return;
}

