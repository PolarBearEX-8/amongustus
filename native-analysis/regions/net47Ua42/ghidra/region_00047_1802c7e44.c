/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=47 entry=1802c7e44 blocks=32 maxInstructions=50000 */

/* cut edge: 0x1802c7e44 -> 0x1802c8e25 */
/* cut edge: 0x1802c7e53 -> 0x1803161af */
/* cut edge: 0x1802c7e6e -> 0x180333186 */
/* cut edge: 0x1802c7fb9 -> 0x180333186 */
/* cut edge: 0x1802c80bf -> 0x180333186 */
/* cut edge: 0x1802c80d5 -> 0x180333186 */
/* cut edge: 0x1802c8278 -> 0x180333186 */
/* cut edge: 0x1802c836f -> 0x180333186 */
/* cut edge: 0x1802c8385 -> 0x180333186 */
/* cut edge: 0x1802c852c -> 0x180333186 */
/* cut edge: 0x1802c8623 -> 0x180333186 */
/* cut edge: 0x1802c8639 -> 0x180333186 */
/* cut edge: 0x1802c87e1 -> 0x180333186 */
/* cut edge: 0x1802c88d6 -> 0x180333186 */
/* cut edge: 0x1802c88ec -> 0x180333186 */
/* cut edge: 0x1802c8aa0 -> 0x180333186 */
/* cut edge: 0x1802c8b9c -> 0x1802a6060 */
/* cut edge: 0x1802c8bd3 -> 0x180333186 */
/* cut edge: 0x1802c8d2c -> 0x180333186 */
/* cut edge: 0x1802c8d2c -> 0x1802c8e25 */
/* target 0x1802a6060 original byte 0x48 replaced by RET */
/* target 0x1802c8e25 original byte 0x3b replaced by RET */
/* target 0x1803161af original byte 0x84 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00047_1802c7e44(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  int iVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  int unaff_ESI;
  longlong *in_stack_00000040;
  longlong in_stack_00000070;
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
  int in_stack_000000e0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00002ef8;
  undefined8 in_stack_00002f00;
  
  if ((longlong)in_stack_000000b0 < 1) {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (unaff_ESI != in_stack_000000e0) {
    if (cVar5 == '\0') {
      if (DAT_1804b0c80 == 0) {
        lVar1 = *in_stack_00000040;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar7 = FUN_180333550(in_stack_00000040);
        uVar13 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000632,1);
        lVar1 = *in_stack_00000040;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000040);
        uVar10 = FUN_180002550(in_stack_00000040);
        uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002978,5);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000040);
        uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
        (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
        uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
        DAT_1804b0c80 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
      }
      uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b0c80,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4bf9);
      (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00002f00);
      (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
      uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
      iVar6 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar13,in_stack_000000f8);
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (cVar5 == '\0') {
        if (iVar6 == in_stack_000000e0) {
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
          if ((cVar5 == '\0') &&
             (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) {
            if (DAT_1804b1018 == 0) {
              lVar1 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_1803335f0(in_stack_00000040);
              uVar13 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000548,1);
              lVar1 = *in_stack_00000040;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000040);
              uVar10 = FUN_180002550(in_stack_00000040);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002328,5);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000040);
              uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
              (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
              DAT_1804b1018 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b1018,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x11e8);
            (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00000070 % 0x42c69 ^ in_stack_000000b0 ^ 0x8cf1eacde4f83882)
            ;
            (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
            (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar13,in_stack_000000d0);
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if (((cVar5 == '\0') &&
                (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) &&
               (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) {
              if (DAT_1804b1020 == 0) {
                lVar1 = *in_stack_00000040;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar7 = FUN_1803335f0(in_stack_00000040);
                uVar13 = DAT_1804b59f0;
                uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000546,1);
                lVar1 = *in_stack_00000040;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000040);
                uVar10 = FUN_180002550(in_stack_00000040);
                uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002318,5);
                pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000040);
                uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                DAT_1804b1020 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
              }
              uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,DAT_1804b1020,in_stack_000000a0);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x449e);
              (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
              pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000070 % 0x26307 ^ in_stack_000000b0 ^
                                 0xc66496652c3de6b0);
              (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
              uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar13,in_stack_000000d0);
              cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (((cVar5 == '\0') &&
                  (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) &&
                 (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) {
                if (DAT_1804b1028 == 0) {
                  lVar1 = *in_stack_00000040;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_1803335f0(in_stack_00000040);
                  uVar13 = DAT_1804b59f0;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000544,1);
                  lVar1 = *in_stack_00000040;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000040);
                  uVar10 = FUN_180002550(in_stack_00000040);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002308,5);
                  pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000040);
                  uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                  (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                  DAT_1804b1028 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13)
                  ;
                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,DAT_1804b1028,in_stack_000000a0);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                  (in_stack_00000040,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x53b4);
                (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   in_stack_00000070 % 0x93642 ^ in_stack_000000b0 ^
                                   0xe0ffe1ce9f8a59d8);
                (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
                (**(code **)(*in_stack_00000040 + 0x1a0))
                          (in_stack_00000040,uVar13,in_stack_000000d0);
                cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                if (((cVar5 == '\0') &&
                    (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) &&
                   (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) {
                  if (DAT_1804b1030 == 0) {
                    lVar1 = *in_stack_00000040;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar7 = FUN_1803335f0(in_stack_00000040);
                    uVar13 = DAT_1804b59f0;
                    uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000542,1);
                    lVar1 = *in_stack_00000040;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar9 = FUN_1800024f0(in_stack_00000040);
                    uVar10 = FUN_180002550(in_stack_00000040);
                    uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000022f8,5);
                    pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                    uVar12 = FUN_1800025a0(in_stack_00000040);
                    uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                    (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                    uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                    DAT_1804b1030 =
                         (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
                    (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                  }
                  uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                     (in_stack_00000040,DAT_1804b1030,in_stack_000000a0);
                  uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                    (in_stack_00000040,2,in_stack_00000090,0);
                  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x49a4);
                  (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                    (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                     in_stack_00000070 % 0xb8ad9 ^ in_stack_000000b0 ^
                                     0xda760e382e2e494c);
                  (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                  uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                     (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
                  (**(code **)(*in_stack_00000040 + 0x1a0))
                            (in_stack_00000040,uVar13,in_stack_000000d0);
                  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                  if (cVar5 == '\0') {
                    return;
                  }
                }
              }
            }
          }
        }
        else {
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b0c88 == 0) {
              lVar1 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_180333550(in_stack_00000040);
              uVar13 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000630,1);
              lVar1 = *in_stack_00000040;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000040);
              uVar10 = FUN_180002550(in_stack_00000040);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002968,5);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000040);
              uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
              (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
              DAT_1804b0c88 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b0c88,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1193);
            (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00002ef8);
            (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
            (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar13,in_stack_000000f8);
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if (cVar5 == '\0') {
              return;
            }
          }
        }
      }
    }
    return;
  }
  return;
}

