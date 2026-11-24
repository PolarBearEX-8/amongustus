/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=5 entry=1800241d3 blocks=52 maxInstructions=100000 */

/* cut edge: 0x1800241ec -> 0x1800c410b */
/* cut edge: 0x1800241fe -> 0x18014d3db */
/* cut edge: 0x180024357 -> 0x18014d3db */
/* cut edge: 0x18002445b -> 0x1800d0cb8 */
/* cut edge: 0x18002446a -> 0x18014d3db */
/* cut edge: 0x180024490 -> 0x180027a34 */
/* cut edge: 0x1800244b4 -> 0x180027a6a */
/* cut edge: 0x18002450d -> 0x180027abb */
/* cut edge: 0x18002451d -> 0x180027af4 */
/* cut edge: 0x1800245f0 -> 0x18014d3db */
/* cut edge: 0x1800246f9 -> 0x18007ce14 */
/* cut edge: 0x180024706 -> 0x18014d3db */
/* cut edge: 0x18002485f -> 0x18014d3db */
/* cut edge: 0x18002496c -> 0x1800c7a08 */
/* cut edge: 0x18002498f -> 0x180044575 */
/* cut edge: 0x1800249a4 -> 0x18014d3db */
/* cut edge: 0x180024afd -> 0x18014d3db */
/* cut edge: 0x180024bff -> 0x180077d77 */
/* cut edge: 0x180024c0e -> 0x18014d3db */
/* cut edge: 0x180024d67 -> 0x18014d3db */
/* cut edge: 0x180024e79 -> 0x18005aa75 */
/* cut edge: 0x180024e9e -> 0x18014d3db */
/* cut edge: 0x180024ff7 -> 0x18014d3db */
/* cut edge: 0x1800250fd -> 0x180025387 */
/* cut edge: 0x18002510a -> 0x180106a89 */
/* cut edge: 0x180025119 -> 0x1800bb676 */
/* cut edge: 0x180025119 -> 0x180025126 */
/* target 0x180025126 original byte 0x48 replaced by RET */
/* target 0x180025387 original byte 0x48 replaced by RET */
/* target 0x180027a34 original byte 0x48 replaced by RET */
/* target 0x180027a6a original byte 0x48 replaced by RET */
/* target 0x180027abb original byte 0x48 replaced by RET */
/* target 0x180027af4 original byte 0x48 replaced by RET */
/* target 0x180044575 original byte 0x48 replaced by RET */
/* target 0x18005aa75 original byte 0x48 replaced by RET */
/* target 0x180077d77 original byte 0x48 replaced by RET */
/* target 0x18007ce14 original byte 0x48 replaced by RET */
/* target 0x1800bb676 original byte 0x48 replaced by RET */
/* target 0x1800c410b original byte 0x48 replaced by RET */
/* target 0x1800c7a08 original byte 0x48 replaced by RET */
/* target 0x1800d0cb8 original byte 0x48 replaced by RET */
/* target 0x180106a89 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00005_1800241d3(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  longlong lVar6;
  char cVar7;
  int iVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  int unaff_R12D;
  bool bVar16;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  int in_stack_000000e8;
  longlong in_stack_000000f8;
  undefined8 in_stack_00000108;
  undefined8 in_stack_00000148;
  undefined8 in_stack_00000158;
  undefined8 in_stack_000055f0;
  undefined8 in_stack_000055f8;
  undefined8 in_stack_00005608;
  undefined8 in_stack_00005610;
  undefined8 in_stack_00005618;
  undefined8 in_stack_00005630;
  
  if (in_stack_000000f8 == 0) {
    if (unaff_R12D == in_stack_000000e8) {
      return;
    }
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar7 != '\0') {
      return;
    }
    if (DAT_1804ac200 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_18001eb20(in_stack_00000048);
      uVar15 = DAT_1804b5208;
      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bc4,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar11 = FUN_1800024f0(in_stack_00000048);
      uVar12 = FUN_180002550(in_stack_00000048);
      uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00005058,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar14 = FUN_1800025a0(in_stack_00000048);
      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
      (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
      uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar9,uVar15,uVar10);
      DAT_1804ac200 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
    }
    uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804ac200,in_stack_000000b0);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x862);
    (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005618);
    (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
    uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar15,in_stack_000000a8,uVar9);
    unaff_R12D = (**(code **)(*in_stack_00000048 + 0x188))
                           (in_stack_00000048,uVar15,in_stack_00000108);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 != '\0') {
      return;
    }
  }
  if (unaff_R12D == in_stack_000000e8) {
    return;
  }
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  lVar1 = DAT_1804b5258;
  uVar15 = DAT_1804b5208;
  if (cVar7 == '\0') {
    if (DAT_1804ac208 == 0) {
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5258 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000bc2,1);
      lVar6 = DAT_1804b4d38;
      lVar5 = DAT_1804b4d30;
      pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00005048,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar3)(in_stack_00000048,lVar5,lVar6,uVar10,uVar11);
      uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,lVar1,uVar15,uVar9);
      DAT_1804ac208 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
    }
    uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804ac208,in_stack_000000b0);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2106);
    (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005630);
    (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
    uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar15,in_stack_000000a8,uVar9);
    iVar8 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar15,in_stack_00000108);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 == '\0') {
      if (in_stack_00000148._3_1_ != '\0') {
        if (iVar8 == in_stack_000000e8) {
          return;
        }
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar7 != '\0') {
          return;
        }
        if (DAT_1804ac210 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_18001eb20(in_stack_00000048);
          uVar15 = DAT_1804b5208;
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bc0,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00005038,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar9,uVar15,uVar10);
          DAT_1804ac210 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
        }
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac210,in_stack_000000b0);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x56d1);
        (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005610)
        ;
        (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar15,in_stack_000000a8,uVar9);
        iVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar15,in_stack_00000108);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 != '\0') {
          return;
        }
      }
      if ((in_stack_00000158._7_1_ != '\0') &&
         (bVar16 = iVar8 == in_stack_000000e8, iVar8 = 2, bVar16)) {
        return;
      }
      if (in_stack_00000148._3_1_ != '\0') {
        if (in_stack_000000e8 == iVar8) {
          return;
        }
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar7 != '\0') {
          return;
        }
        if (DAT_1804ac218 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_18001eb20(in_stack_00000048);
          uVar15 = DAT_1804b5208;
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bbe,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00005028,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar9,uVar15,uVar10);
          DAT_1804ac218 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
        }
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac218,in_stack_000000b0);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3146);
        (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005608)
        ;
        (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar15,in_stack_000000a8,uVar9);
        iVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar15,in_stack_00000108);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 != '\0') {
          return;
        }
        if (iVar8 == in_stack_000000e8) {
          return;
        }
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar7 != '\0') {
          return;
        }
        if (DAT_1804ac220 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_18001eb20(in_stack_00000048);
          uVar15 = DAT_1804b5208;
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bbc,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00005018,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar9,uVar15,uVar10);
          DAT_1804ac220 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
        }
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac220,in_stack_000000b0);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x25e3);
        (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000055f0)
        ;
        (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar15,in_stack_000000a8,uVar9);
        iVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar15,in_stack_00000108);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 != '\0') {
          return;
        }
      }
      if (in_stack_00000158._7_1_ == '\0') {
LAB_1800250f2:
        if (in_stack_000000f8 == 0) {
          if (in_stack_000000e8 != iVar8) {
            return;
          }
          return;
        }
        if (in_stack_000000e8 == iVar8) {
          return;
        }
        return;
      }
      if (in_stack_000000e8 == iVar8) {
        return;
      }
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar7 == '\0') {
        if (DAT_1804ac228 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_18001eb20(in_stack_00000048);
          uVar15 = DAT_1804b5208;
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bba,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar11 = FUN_1800024f0(in_stack_00000048);
          uVar12 = FUN_180002550(in_stack_00000048);
          uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00005008,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar14 = FUN_1800025a0(in_stack_00000048);
          uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar14);
          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
          uVar15 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar9,uVar15,uVar10);
          DAT_1804ac228 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
        }
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac228,in_stack_000000b0);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3a09);
        (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000055f8)
        ;
        (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
        uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar15,in_stack_000000a8,uVar9);
        iVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar15,in_stack_00000108);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 == '\0') goto LAB_1800250f2;
      }
    }
  }
  return;
}

