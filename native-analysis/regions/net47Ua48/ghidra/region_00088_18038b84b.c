/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=88 entry=18038b84b blocks=35 maxInstructions=50000 */

/* cut edge: 0x18038b853 -> 0x1803351d6 */
/* cut edge: 0x18038b88a -> 0x1803351d6 */
/* cut edge: 0x18038b8b4 -> 0x1803351d6 */
/* cut edge: 0x18038b8e6 -> 0x1803351d6 */
/* cut edge: 0x18038b96a -> 0x1803351d6 */
/* cut edge: 0x18038b977 -> 0x1803351d6 */
/* cut edge: 0x18038b9b3 -> 0x1803351d6 */
/* cut edge: 0x18038b9e2 -> 0x1803351d6 */
/* cut edge: 0x18038ba11 -> 0x1803351d6 */
/* cut edge: 0x18038ba42 -> 0x1803351d6 */
/* cut edge: 0x18038baa5 -> 0x1803351d6 */
/* cut edge: 0x18038bad5 -> 0x1803351d6 */
/* cut edge: 0x18038bc70 -> 0x1803351d6 */
/* cut edge: 0x18038be38 -> 0x1803351d6 */
/* cut edge: 0x18038bfcd -> 0x1803351d6 */
/* cut edge: 0x18038c149 -> 0x1803351d6 */
/* cut edge: 0x18038c304 -> 0x1803351d6 */
/* cut edge: 0x18038c450 -> 0x1803351d6 */
/* cut edge: 0x18038c5d5 -> 0x1803351d6 */
/* cut edge: 0x18038c70c -> 0x1803351d6 */
/* cut edge: 0x18038c722 -> 0x18038c887 */
/* cut edge: 0x18038c732 -> 0x18038c887 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18038c887 original byte 0x48 replaced by RET */


void region_00088_18038b84b(void)

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
  undefined8 uVar14;
  undefined8 uVar15;
  char unaff_BL;
  undefined8 unaff_RSI;
  ulonglong in_stack_00000020;
  undefined4 uVar16;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000c8;
  int in_stack_00000150;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_000001f0;
  undefined8 in_stack_00000858;
  undefined8 in_stack_000009f8;
  undefined8 in_stack_00000a00;
  undefined8 in_stack_00000a08;
  undefined8 in_stack_00000a10;
  short in_stack_00002340;
  undefined8 in_stack_00002348;
  undefined8 in_stack_00002350;
  undefined8 in_stack_00002358;
  undefined8 in_stack_00002360;
  
  if (unaff_BL == '\0') {
    iVar6 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    uVar16 = (undefined4)(in_stack_00000020 >> 0x20);
    if (cVar5 != '\0') {
      return;
    }
    if (in_stack_00000150 < iVar6) {
      iVar6 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 != '\0') {
        return;
      }
      unaff_RSI = (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,iVar6 + -1);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 != '\0') {
        return;
      }
      (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,unaff_RSI);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 != '\0') {
        return;
      }
      in_stack_00000020 = CONCAT44(uVar16,1);
      (**(code **)(*in_stack_00000048 + 0x468))
                (in_stack_00000048,in_stack_000001a0,in_stack_000001f0);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 != '\0') {
        return;
      }
    }
  }
  iVar6 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,unaff_RSI);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  uVar7 = unaff_RSI;
  if (iVar6 < in_stack_00000150) {
    uVar7 = (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,in_stack_00000150);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,unaff_RSI);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,unaff_RSI);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    (**(code **)(*in_stack_00000048 + 0x468))
              (in_stack_00000048,in_stack_000001a0,in_stack_000001f0,unaff_RSI,
               in_stack_00000020 & 0xffffffff00000000);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
  }
  iVar6 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,uVar7);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
    if (DAT_1804b2c98 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_180333500(in_stack_00000048);
      uVar14 = DAT_1804b59f0;
      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004c8,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000029f0,0x12);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
      (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar8,uVar14,uVar9);
      DAT_1804b2c98 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
    }
    uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2c98,in_stack_00000078);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,6,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,iVar6 + 1);
    (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000a10,in_stack_00000a08,in_stack_00000858);
    (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,in_stack_00002348);
    (*pcVar2)(in_stack_00000048,uVar8,2,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000a00,in_stack_000009f8,(int)in_stack_00002340)
    ;
    (*pcVar2)(in_stack_00000048,uVar8,3,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfcd4df2f8127b48);
    (*pcVar2)(in_stack_00000048,uVar8,4,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
    (*pcVar2)(in_stack_00000048,uVar8,5,uVar9);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar14,in_stack_00000080,uVar8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
      if (DAT_1804b2ca0 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar9 = FUN_180333500(in_stack_00000048);
        uVar8 = DAT_1804b59f0;
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004c6,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar11 = FUN_1800024f0(in_stack_00000048);
        uVar12 = FUN_180002550(in_stack_00000048);
        uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003fd0,0x22);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar15 = FUN_1800025a0(in_stack_00000048);
        uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
        (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar15);
        uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
        DAT_1804b2ca0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b2ca0,in_stack_00000078);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000088,0);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,uVar14);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00002350);
      (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = FUN_18014d8f0(in_stack_00000048);
      uVar11 = FUN_18014d950(in_stack_00000048);
      uVar10 = (*pcVar2)(in_stack_00000048,uVar10,uVar11,1);
      (*pcVar3)(in_stack_00000048,uVar9,2,uVar10);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfd9631efd3a671d);
      (*pcVar2)(in_stack_00000048,uVar9,3,uVar10);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
      (*pcVar2)(in_stack_00000048,uVar9,4,uVar10);
      (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,in_stack_00000080,uVar9);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
        if (DAT_1804b2ca8 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_180333500(in_stack_00000048);
          uVar8 = DAT_1804b59f0;
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004c4,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00006910,0x33);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar15 = FUN_1800025a0(in_stack_00000048);
          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar15);
          uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
          DAT_1804b2ca8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b2ca8,in_stack_00000078);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000088,0);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,uVar14);
        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,1,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00002358);
        (*pcVar2)(in_stack_00000048,uVar9,2,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc256b6fe495c3b)
        ;
        (*pcVar2)(in_stack_00000048,uVar9,3,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
        (*pcVar2)(in_stack_00000048,uVar9,4,uVar7);
        (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,in_stack_00000080,uVar9);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0'))
        {
          if (DAT_1804b2cb0 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_180333500(in_stack_00000048);
            uVar7 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004c2,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002ce0,0x19);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
            DAT_1804b2cb0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2cb0,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0)
          ;
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,uVar14);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_00002360);
          (*pcVar2)(in_stack_00000048,uVar8,1,uVar14);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              0xdfb7d5aac9d579bb);
          (*pcVar2)(in_stack_00000048,uVar8,2,uVar14);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar14 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000c8);
          (*pcVar2)(in_stack_00000048,uVar8,3,uVar14);
          (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar8)
          ;
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((cVar5 == '\0') &&
             (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
            if (DAT_1804b2cb8 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar14 = FUN_180333500(in_stack_00000048);
              uVar7 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004c0,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004680,0x17);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar7 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar14,uVar7,uVar8);
              DAT_1804b2cb8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
            }
            return;
          }
        }
      }
    }
  }
  return;
}

