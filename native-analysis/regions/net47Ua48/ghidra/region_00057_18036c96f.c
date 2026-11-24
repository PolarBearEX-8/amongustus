/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=57 entry=18036c96f blocks=29 maxInstructions=50000 */

/* cut edge: 0x18036c96f -> 0x1803351d6 */
/* cut edge: 0x18036ca5d -> 0x1803351d6 */
/* cut edge: 0x18036cbd3 -> 0x1803351d6 */
/* cut edge: 0x18036ccda -> 0x1803351d6 */
/* cut edge: 0x18036ce34 -> 0x1803351d6 */
/* cut edge: 0x18036cf34 -> 0x1803351d6 */
/* cut edge: 0x18036d09c -> 0x1803351d6 */
/* cut edge: 0x18036d1a4 -> 0x1803351d6 */
/* cut edge: 0x18036d2fe -> 0x1803351d6 */
/* cut edge: 0x18036d3ec -> 0x1803351d6 */
/* cut edge: 0x18036d562 -> 0x1803351d6 */
/* cut edge: 0x18036d654 -> 0x18036d8c3 */
/* cut edge: 0x18036d669 -> 0x1803351d6 */
/* cut edge: 0x18036d7c3 -> 0x1803351d6 */
/* cut edge: 0x18036d7c3 -> 0x18036d8b5 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18036d8b5 original byte 0x8b replaced by RET */
/* target 0x18036d8c3 original byte 0x48 replaced by RET */


void region_00057_18036c96f(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  uint uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong *in_stack_00000048;
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
  uint in_stack_000000b0;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_00000110;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar8)
  ;
  (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b26f8 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar8 = FUN_180333550(in_stack_00000048);
        uVar7 = DAT_1804b59f0;
        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000630,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000014f8,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
        DAT_1804b26f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b26f8,in_stack_00000078);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
      (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804b2708 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar8 = FUN_180333550(in_stack_00000048);
            uVar7 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000062c,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000014d8,5);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
            DAT_1804b2708 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2708,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar7,in_stack_00000070);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((in_stack_000000b0 & uVar6) == 0) {
              if (cVar5 == '\0') {
                if (DAT_1804b2720 == 0) {
                  lVar2 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar2 + 0x390);
                  uVar8 = FUN_180333550(in_stack_00000048);
                  uVar7 = DAT_1804b59f0;
                  uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000626,1);
                  lVar2 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar2 + 0x390);
                  uVar10 = FUN_1800024f0(in_stack_00000048);
                  uVar11 = FUN_180002550(in_stack_00000048);
                  uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000014a8,5);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar13 = FUN_1800025a0(in_stack_00000048);
                  uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                  (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                  uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
                  DAT_1804b2720 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b2720,in_stack_00000078);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000088,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
                (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_000000a8);
                (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070)
                ;
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  return;
                }
              }
            }
            else if (cVar5 == '\0') {
              if (DAT_1804b2710 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_180333550(in_stack_00000048);
                uVar7 = DAT_1804b59f0;
                uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000062a,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000048);
                uVar11 = FUN_180002550(in_stack_00000048);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000014c8,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000048);
                uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
                DAT_1804b2710 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2710,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_00000110);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
              (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b2718 == 0) {
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar8 = FUN_180333550(in_stack_00000048);
                    uVar7 = DAT_1804b59f0;
                    uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000628,1);
                    lVar2 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar10 = FUN_1800024f0(in_stack_00000048);
                    uVar11 = FUN_180002550(in_stack_00000048);
                    uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000014b8,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar13 = FUN_1800025a0(in_stack_00000048);
                    uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                    uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
                    DAT_1804b2718 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                  }
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b2718,in_stack_00000078);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
                  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000a8);
                  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                  (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar7,in_stack_00000070);
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

