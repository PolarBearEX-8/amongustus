/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=6 entry=180025126 blocks=46 maxInstructions=100000 */

/* cut edge: 0x180025126 -> 0x18014d3db */
/* cut edge: 0x18002527f -> 0x18014d3db */
/* cut edge: 0x180025378 -> 0x18002510a */
/* cut edge: 0x180025387 -> 0x18014d3db */
/* cut edge: 0x1800254e0 -> 0x18014d3db */
/* cut edge: 0x1800255f1 -> 0x1800479f2 */
/* cut edge: 0x18002560d -> 0x18014d3db */
/* cut edge: 0x180025766 -> 0x18014d3db */
/* cut edge: 0x180025868 -> 0x18004c5c7 */
/* cut edge: 0x180025899 -> 0x18014d3db */
/* cut edge: 0x1800259f2 -> 0x18014d3db */
/* cut edge: 0x180025b0a -> 0x18004d5d1 */
/* cut edge: 0x180025b1a -> 0x18004d5cc */
/* cut edge: 0x180025b1f -> 0x18004d5eb */
/* cut edge: 0x180025b4d -> 0x180053f44 */
/* cut edge: 0x180025b64 -> 0x18014d3db */
/* cut edge: 0x180025cbd -> 0x18014d3db */
/* cut edge: 0x180025dc3 -> 0x180101c73 */
/* cut edge: 0x180025dd2 -> 0x18004d5cc */
/* cut edge: 0x180025dd9 -> 0x1800227d5 */
/* cut edge: 0x180025e11 -> 0x18002282a */
/* cut edge: 0x180025e46 -> 0x18002282a */
/* cut edge: 0x180025e62 -> 0x180022849 */
/* cut edge: 0x180025e9b -> 0x1800228a1 */
/* cut edge: 0x180025ed2 -> 0x18014d3db */
/* cut edge: 0x18002602b -> 0x18014d3db */
/* cut edge: 0x18002602b -> 0x180026124 */
/* target 0x1800227d5 original byte 0x4c replaced by RET */
/* target 0x18002282a original byte 0x48 replaced by RET */
/* target 0x180022849 original byte 0x48 replaced by RET */
/* target 0x1800228a1 original byte 0x48 replaced by RET */
/* target 0x18002510a original byte 0x48 replaced by RET */
/* target 0x180026124 original byte 0x3b replaced by RET */
/* target 0x1800479f2 original byte 0x48 replaced by RET */
/* target 0x18004c5c7 original byte 0x48 replaced by RET */
/* target 0x18004d5cc original byte 0x89 replaced by RET */
/* target 0x18004d5d1 original byte 0x4c replaced by RET */
/* target 0x18004d5eb original byte 0x48 replaced by RET */
/* target 0x180053f44 original byte 0x48 replaced by RET */
/* target 0x180101c73 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


ulonglong region_00006_180025126(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  uint uVar5;
  ulonglong uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000078;
  ulonglong in_stack_00000080;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  longlong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  ulonglong in_stack_000000e0;
  uint in_stack_000000e8;
  longlong in_stack_000000f8;
  undefined8 in_stack_00000108;
  undefined8 in_stack_000055b0;
  undefined8 in_stack_000055b8;
  undefined8 in_stack_000055d0;
  undefined8 in_stack_000055e0;
  undefined8 in_stack_000055e8;
  undefined8 in_stack_00005600;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)uVar6 == '\0') {
    if (DAT_1804ac230 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_18001eb20(in_stack_00000048);
      uVar13 = DAT_1804b5208;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bb8,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000048);
      uVar10 = FUN_180002550(in_stack_00000048);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004ff8,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000048);
      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
      DAT_1804ac230 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804ac230,in_stack_000000b0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x219);
    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000055e8);
    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar13,in_stack_00000108);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)uVar6 == '\0') {
      if (in_stack_000000e8 == uVar5) {
        return (ulonglong)uVar5;
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)uVar6 == '\0') {
        if (DAT_1804ac238 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_18001eb20(in_stack_00000048);
          uVar13 = DAT_1804b5208;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bb6,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004fe8,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
          (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
          DAT_1804ac238 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ac238,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3334);
        (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005600);
        (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar13,in_stack_00000108);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)uVar6 == '\0') {
          if (in_stack_000000f8 == 0) {
            if (uVar5 == in_stack_000000e8) {
              return in_stack_00000080;
            }
            uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            if ((char)uVar6 != '\0') {
              return uVar6;
            }
            if (DAT_1804ac240 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_18001eb20(in_stack_00000048);
              uVar13 = DAT_1804b5208;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bb4,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004fd8,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
              DAT_1804ac240 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804ac240,in_stack_000000b0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6576);
            (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_000055d0);
            (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar13,in_stack_00000108);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)uVar6 != '\0') {
              return uVar6;
            }
          }
          if (uVar5 == in_stack_000000e8) {
            return in_stack_000000e0;
          }
          if (in_stack_000000c0 < 1) {
            return in_stack_000000e0;
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)uVar6 == '\0') {
            if (DAT_1804ac248 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_18001eb20(in_stack_00000048);
              uVar13 = DAT_1804b5208;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bb2,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000048);
              uVar10 = FUN_180002550(in_stack_00000048);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004fc8,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000048);
              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
              DAT_1804ac248 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804ac248,in_stack_000000b0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6ed9);
            (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_000055e0);
            (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar13,in_stack_00000108);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)uVar6 == '\0') {
              if (in_stack_000000f8 != 0) {
                if ((in_stack_000000e8 == 0x80000000) && (uVar6 = 0x80000000, uVar5 == 0xffffffff))
                {
                  return uVar6;
                }
                return uVar6;
              }
              if (uVar5 == in_stack_000000e8) {
                return in_stack_00000080;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)uVar6 == '\0') {
                if (DAT_1804ac250 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_18001eb20(in_stack_00000048);
                  uVar13 = DAT_1804b5208;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bb0,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000048);
                  uVar10 = FUN_180002550(in_stack_00000048);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004fb8,5);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000048);
                  uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                  (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
                  DAT_1804ac250 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804ac250,in_stack_000000b0);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_000000a0,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6a16);
                (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                   in_stack_000055b8);
                (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                uVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                  (in_stack_00000048,uVar13,in_stack_00000108);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((char)uVar6 == '\0') {
                  if (uVar5 == in_stack_000000e8) {
                    return in_stack_00000078;
                  }
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if ((char)uVar6 == '\0') {
                    if (DAT_1804ac258 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_18001eb20(in_stack_00000048);
                      uVar13 = DAT_1804b5208;
                      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000bae,1);
                      lVar1 = *in_stack_00000048;
                      pcVar3 = *(code **)(lVar1 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004fa8,5);
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar13 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar7,uVar13,uVar8);
                      DAT_1804ac258 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar13);
                    }
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804ac258,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1bbb)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       in_stack_000055b0);
                    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
                    uVar13 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar13,in_stack_000000a8,uVar7);
                    (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar13,in_stack_00000108);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)uVar6 == '\0') {
                      return uVar6;
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
  return uVar6;
}

