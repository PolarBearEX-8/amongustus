/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=70 entry=180379968 blocks=29 maxInstructions=50000 */

/* cut edge: 0x180379968 -> 0x1803351d6 */
/* cut edge: 0x180379a70 -> 0x1803351d6 */
/* cut edge: 0x180379bca -> 0x1803351d6 */
/* cut edge: 0x180379cb8 -> 0x1803351d6 */
/* cut edge: 0x180379e2e -> 0x1803351d6 */
/* cut edge: 0x180379f35 -> 0x1803351d6 */
/* cut edge: 0x18037a08f -> 0x1803351d6 */
/* cut edge: 0x18037a18f -> 0x1803351d6 */
/* cut edge: 0x18037a2f7 -> 0x1803351d6 */
/* cut edge: 0x18037a3e4 -> 0x18037a8d1 */
/* cut edge: 0x18037a40c -> 0x1803351d6 */
/* cut edge: 0x18037a566 -> 0x1803351d6 */
/* cut edge: 0x18037a654 -> 0x1803351d6 */
/* cut edge: 0x18037a7ca -> 0x1803351d6 */
/* cut edge: 0x18037a7ca -> 0x18037a8bc */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18037a8bc original byte 0x44 replaced by RET */
/* target 0x18037a8d1 original byte 0x84 replaced by RET */


void region_00070_180379968(undefined8 param_1,undefined8 param_2)

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
  uint in_stack_000000b8;
  undefined8 in_stack_000000d0;
  uint in_stack_000000d8;
  undefined8 in_stack_00000110;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar8)
  ;
  uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((in_stack_000000d8 & uVar6) == 0) {
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b2968 == 0) {
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar8 = FUN_180333550(in_stack_00000048);
      uVar7 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000594,1);
      lVar2 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar2 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001018,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
      DAT_1804b2968 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b2968,in_stack_00000078);
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
  }
  else {
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b2958 == 0) {
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar8 = FUN_180333550(in_stack_00000048);
      uVar7 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000598,1);
      lVar2 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar2 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001038,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
      DAT_1804b2958 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b2958,in_stack_00000078);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
    (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b2960 == 0) {
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar8 = FUN_180333550(in_stack_00000048);
      uVar7 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000596,1);
      lVar2 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar2 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001028,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
      DAT_1804b2960 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b2960,in_stack_00000078);
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
  }
  if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
    if (DAT_1804b2970 == 0) {
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      uVar8 = FUN_180333550(in_stack_00000048);
      uVar7 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000592,1);
      lVar2 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar2 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00001008,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
      DAT_1804b2970 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b2970,in_stack_00000078);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if ((uVar6 & in_stack_000000b8) == 0) {
        return;
      }
      if (cVar5 == '\0') {
        if (DAT_1804b2978 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar8 = FUN_180333550(in_stack_00000048);
          uVar7 = DAT_1804b59f0;
          uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000590,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000048);
          uVar11 = FUN_180002550(in_stack_00000048);
          uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000ff8,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000048);
          uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
          (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
          DAT_1804b2978 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b2978,in_stack_00000078);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
        (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0'))
        {
          if (DAT_1804b2980 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar8 = FUN_180333550(in_stack_00000048);
            uVar7 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000058e,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000fe8,5);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
            DAT_1804b2980 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2980,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
          (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            return;
          }
        }
      }
    }
  }
  return;
}

