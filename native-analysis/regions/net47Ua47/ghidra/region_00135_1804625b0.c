/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=135 entry=1804625b0 blocks=37 maxInstructions=50000 */

/* cut edge: 0x1804625b0 -> 0x180473ae8 */
/* cut edge: 0x1804626a2 -> 0x180473ae8 */
/* cut edge: 0x1804626b8 -> 0x180473ae8 */
/* cut edge: 0x18046287d -> 0x180473ae8 */
/* cut edge: 0x180462972 -> 0x180473ae8 */
/* cut edge: 0x180462988 -> 0x180473ae8 */
/* cut edge: 0x180462bb3 -> 0x180473ae8 */
/* cut edge: 0x180462c58 -> 0x180473ae8 */
/* cut edge: 0x180462c9d -> 0x180473ae8 */
/* cut edge: 0x180462ccc -> 0x180473ae8 */
/* cut edge: 0x180462cf6 -> 0x180473ae8 */
/* cut edge: 0x180462d20 -> 0x18047436d */
/* cut edge: 0x180462d48 -> 0x1804638ea */
/* cut edge: 0x180462d8e -> 0x180473ae8 */
/* cut edge: 0x180462d93 -> 0x180473ae8 */
/* cut edge: 0x180462da9 -> 0x180473ae8 */
/* cut edge: 0x180462f6f -> 0x180473ae8 */
/* cut edge: 0x180463061 -> 0x180473ae8 */
/* cut edge: 0x180463077 -> 0x180473ae8 */
/* cut edge: 0x180463240 -> 0x180473ae8 */
/* cut edge: 0x180463332 -> 0x180473ae8 */
/* cut edge: 0x180463348 -> 0x180473ae8 */
/* cut edge: 0x18046350e -> 0x180473ae8 */
/* cut edge: 0x18046350e -> 0x180463603 */
/* target 0x180463603 original byte 0x48 replaced by RET */
/* target 0x1804638ea original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x18047436d original byte 0x4c replaced by RET */


void region_00135_1804625b0(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  int iVar5;
  char cVar6;
  int iVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  ulonglong uVar14;
  longlong lVar15;
  undefined8 uVar16;
  undefined8 unaff_R12;
  ulonglong in_stack_00000020;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  ulonglong in_stack_00000068;
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
  int in_stack_00000108;
  char in_stack_00000118;
  undefined8 in_stack_00000148;
  
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x75f4);
  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,in_stack_000000a8,uVar9)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (((cVar6 == '\0') && (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) &&
     (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
    if (DAT_1804b4660 == 0) {
      lVar15 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar15 + 0x390);
      uVar9 = FUN_1803335f0(in_stack_00000048);
      uVar8 = DAT_1804b59f0;
      in_stack_00000020 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000336,1);
      lVar15 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar15 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000048);
      uVar11 = FUN_180002550(in_stack_00000048);
      uVar12 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000f30,5);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000048);
      uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar13);
      (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
      uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar9,uVar8,in_stack_00000020);
      DAT_1804b4660 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      unaff_R12 = in_stack_00000058;
    }
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4660,in_stack_000000a0);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2dff);
    (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                        (longlong)in_stack_00000068 % 0x94c2a ^ in_stack_000000c8 ^
                        0xa7351968fa81fcb6);
    (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
    uVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((cVar6 == '\0') && (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) &&
       (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
      if (DAT_1804b4668 == 0) {
        lVar15 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar15 + 0x390);
        uVar9 = FUN_1803335f0(in_stack_00000048);
        uVar8 = DAT_1804b59f0;
        in_stack_00000020 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000334,1);
        lVar15 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar15 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar15 + 0x518))(in_stack_00000048,&stack0x00000f20,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar13);
        (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar9,uVar8,in_stack_00000020);
        DAT_1804b4668 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        unaff_R12 = in_stack_00000058;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b4668,in_stack_000000a0);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0xc23);
      (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                          (longlong)in_stack_00000068 % 0x9a208 ^ in_stack_000000c8 ^
                          0xeec800a45d0a5467);
      (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
      lVar15 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        lVar15 = (uVar14 ^ in_stack_00000068) - lVar15;
        iVar7 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,unaff_R12);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          iVar5 = iVar7;
          if ((in_stack_00000118 == '\0') || (iVar5 = in_stack_00000108, iVar7 < in_stack_00000108))
          {
            (**(code **)(*in_stack_00000048 + 0x580))(in_stack_00000048,iVar5);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,unaff_R12);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,unaff_R12);
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar6 == '\0') {
                  if (DAT_1804b5ab0 == 0) {
                    return;
                  }
                  (**(code **)(*in_stack_00000048 + 0x468))
                            (in_stack_00000048,in_stack_00000148,DAT_1804b5ab0,unaff_R12,
                             in_stack_00000020 & 0xffffffff00000000);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
                    return;
                  }
                }
              }
            }
          }
          else {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((cVar6 == '\0') &&
               (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
              if (DAT_1804b4670 == 0) {
                lVar4 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar4 + 0x390);
                uVar9 = FUN_1803335f0(in_stack_00000048);
                uVar8 = DAT_1804b59f0;
                uVar10 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000332,1);
                lVar4 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar4 + 0x390);
                uVar11 = FUN_1800024f0(in_stack_00000048);
                uVar12 = FUN_180002550(in_stack_00000048);
                uVar13 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000f10,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar16 = FUN_1800025a0(in_stack_00000048);
                uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar16);
                (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar16);
                uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar9,uVar8,uVar10);
                DAT_1804b4670 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b4670,in_stack_000000a0);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x18cc);
              (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                  lVar15 % 0x661b2 ^ in_stack_000000c8 ^ 0xab0c11709819b558);
              (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (((cVar6 == '\0') &&
                  (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) &&
                 (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
                if (DAT_1804b4678 == 0) {
                  lVar4 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar4 + 0x390);
                  uVar9 = FUN_1803335f0(in_stack_00000048);
                  uVar8 = DAT_1804b59f0;
                  uVar10 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000330,1);
                  lVar4 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar4 + 0x390);
                  uVar11 = FUN_1800024f0(in_stack_00000048);
                  uVar12 = FUN_180002550(in_stack_00000048);
                  uVar13 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000f00,5);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar16 = FUN_1800025a0(in_stack_00000048);
                  uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar16);
                  (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar16);
                  uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar9,uVar8,uVar10);
                  DAT_1804b4678 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b4678,in_stack_000000a0);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000090,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x7a71);
                (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                    lVar15 % 0x1ee8e ^ in_stack_000000c8 ^ 0xc809244dfa3ccc4f);
                (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
                (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8)
                ;
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (((cVar6 == '\0') &&
                    (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) &&
                   (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0')) {
                  if (DAT_1804b4680 == 0) {
                    lVar4 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar4 + 0x390);
                    uVar9 = FUN_1803335f0(in_stack_00000048);
                    uVar8 = DAT_1804b59f0;
                    uVar10 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x0000032e,1);
                    lVar4 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar4 + 0x390);
                    uVar11 = FUN_1800024f0(in_stack_00000048);
                    uVar12 = FUN_180002550(in_stack_00000048);
                    uVar13 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000ef0,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar16 = FUN_1800025a0(in_stack_00000048);
                    uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar16);
                    (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar16);
                    uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar9,uVar8,uVar10);
                    DAT_1804b4680 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                  }
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b4680,in_stack_000000a0);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x672d);
                  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                     (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                      lVar15 % 0x1056d ^ in_stack_000000c8 ^ 0xffb1b376d8ec14d9);
                  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
                  (**(code **)(*in_stack_00000048 + 0x1a0))
                            (in_stack_00000048,uVar8,in_stack_000000d8);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
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

