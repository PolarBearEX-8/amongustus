/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=71 entry=18037a8bc blocks=33 maxInstructions=50000 */

/* cut edge: 0x18037a8d1 -> 0x1803351d6 */
/* cut edge: 0x18037aa2b -> 0x1803351d6 */
/* cut edge: 0x18037ab2b -> 0x1803351d6 */
/* cut edge: 0x18037ac93 -> 0x1803351d6 */
/* cut edge: 0x18037ad9b -> 0x1803351d6 */
/* cut edge: 0x18037aef5 -> 0x1803351d6 */
/* cut edge: 0x18037afe3 -> 0x1803351d6 */
/* cut edge: 0x18037b159 -> 0x1803351d6 */
/* cut edge: 0x18037b260 -> 0x1803351d6 */
/* cut edge: 0x18037b3ba -> 0x1803351d6 */
/* cut edge: 0x18037b4ba -> 0x1803351d6 */
/* cut edge: 0x18037b622 -> 0x1803351d6 */
/* cut edge: 0x18037b70f -> 0x18037bc01 */
/* cut edge: 0x18037b737 -> 0x1803351d6 */
/* cut edge: 0x18037b73f -> 0x18037b891 */
/* cut edge: 0x18037b74f -> 0x18037b891 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18037b891 original byte 0x48 replaced by RET */
/* target 0x18037bc01 original byte 0x84 replaced by RET */


void region_00071_18037a8bc(void)

{
  longlong lVar1;
  code *pcVar2;
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
  uint in_stack_000000c0;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_00000110;
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804b2990 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_180333550(in_stack_00000048);
      uVar13 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000058a,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000fc8,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar13,uVar8);
      DAT_1804b2990 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2990,in_stack_00000078);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar13,in_stack_00000080,uVar7);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar13,in_stack_00000070);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if ((in_stack_000000b8 & uVar6) == 0) {
        if (cVar5 != '\0') {
          return;
        }
        if (DAT_1804b29a8 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000048);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000584,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000f98,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar13,uVar8);
          DAT_1804b29a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b29a8,in_stack_00000078);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar13,in_stack_00000080,uVar7);
        (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar13,in_stack_00000070);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      }
      else {
        if (cVar5 != '\0') {
          return;
        }
        if (DAT_1804b2998 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000048);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000588,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000fb8,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar13,uVar8);
          DAT_1804b2998 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2998,in_stack_00000078);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar13,in_stack_00000080,uVar7);
        (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar13,in_stack_00000070);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 != '\0') {
          return;
        }
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 != '\0') {
          return;
        }
        if (DAT_1804b29a0 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000048);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000586,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000fa8,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar13,uVar8);
          DAT_1804b29a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b29a0,in_stack_00000078);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar13,in_stack_00000080,uVar7);
        (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar13,in_stack_00000070);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      }
      if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
        if (DAT_1804b29b0 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000048);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000582,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000f88,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar13,uVar8);
          DAT_1804b29b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b29b0,in_stack_00000078);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar13,in_stack_00000080,uVar7);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar13,in_stack_00000070);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((uVar6 & in_stack_000000c0) == 0) {
            return;
          }
          if (cVar5 == '\0') {
            if (DAT_1804b29b8 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_180333550(in_stack_00000048);
              uVar13 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000580,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000f78,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar7,uVar13,uVar8);
              DAT_1804b29b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
            }
            return;
          }
        }
      }
    }
  }
  return;
}

