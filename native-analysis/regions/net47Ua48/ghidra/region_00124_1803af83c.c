/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=124 entry=1803af83c blocks=36 maxInstructions=50000 */

/* cut edge: 0x1803af84c -> 0x1803d4ee7 */
/* cut edge: 0x1803af875 -> 0x1803d4f1f */
/* cut edge: 0x1803af8d4 -> 0x1803d4f70 */
/* cut edge: 0x1803af8e4 -> 0x1803d4fa9 */
/* cut edge: 0x1803af9de -> 0x1803351d6 */
/* cut edge: 0x1803afadb -> 0x1803b0ac1 */
/* cut edge: 0x1803afae4 -> 0x1803351d6 */
/* cut edge: 0x1803afc3e -> 0x1803351d6 */
/* cut edge: 0x1803afd20 -> 0x1803351d6 */
/* cut edge: 0x1803afeab -> 0x1803351d6 */
/* cut edge: 0x1803affa5 -> 0x1803cf077 */
/* cut edge: 0x1803affc4 -> 0x1803351d6 */
/* cut edge: 0x1803affe9 -> 0x1803351d6 */
/* cut edge: 0x1803b018b -> 0x1803cf0ad */
/* cut edge: 0x1803b0237 -> 0x1803cf0fe */
/* cut edge: 0x1803b024f -> 0x1803351d6 */
/* cut edge: 0x1803b031d -> 0x1803351d6 */
/* cut edge: 0x1803b0477 -> 0x1803351d6 */
/* cut edge: 0x1803b0559 -> 0x1803351d6 */
/* cut edge: 0x1803b06e4 -> 0x1803351d6 */
/* cut edge: 0x1803b07de -> 0x1803cf139 */
/* cut edge: 0x1803b07fd -> 0x1803351d6 */
/* cut edge: 0x1803b0821 -> 0x1803351d6 */
/* cut edge: 0x1803b0821 -> 0x1803b0837 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803b0837 original byte 0x48 replaced by RET */
/* target 0x1803b0ac1 original byte 0x48 replaced by RET */
/* target 0x1803cf077 original byte 0x48 replaced by RET */
/* target 0x1803cf0ad original byte 0x48 replaced by RET */
/* target 0x1803cf0fe original byte 0x48 replaced by RET */
/* target 0x1803cf139 original byte 0x48 replaced by RET */
/* target 0x1803d4ee7 original byte 0x48 replaced by RET */
/* target 0x1803d4f1f original byte 0x48 replaced by RET */
/* target 0x1803d4f70 original byte 0x48 replaced by RET */
/* target 0x1803d4fa9 original byte 0x48 replaced by RET */


void region_00124_1803af83c(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  longlong lVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000c8;
  ulonglong in_stack_00000878;
  
  uVar10 = DAT_1804b59f0;
  lVar11 = DAT_1804b59e8;
  if (DAT_1804b33b0 == 0) {
    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
    if (DAT_1804b59e8 == 0) {
      return;
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000002ee,1);
    lVar5 = DAT_1804b4d38;
    lVar4 = DAT_1804b4d30;
    pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
    if (DAT_1804b4d30 == 0) {
      return;
    }
    if (DAT_1804b4d38 == 0) {
      return;
    }
    uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00006d40,0x20);
    if (DAT_1804b4d40 == 0) {
      return;
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
    (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
    uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar11,uVar10,uVar7);
    DAT_1804b33b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
  }
  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b33b0,in_stack_00000078);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfb0b3c3223f6dc4);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
  lVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar10,in_stack_00000080,uVar7);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    if (lVar11 == 0) {
      return;
    }
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      if (DAT_1804b33b8 == 0) {
        lVar11 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar11 + 0x390);
        uVar7 = FUN_180333500(in_stack_00000048);
        uVar10 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x000002ec,1);
        lVar11 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar11 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar12 = FUN_180002550(in_stack_00000048);
        uVar13 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x000025d0,0xd);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar14 = FUN_1800025a0(in_stack_00000048);
        uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
        (*pcVar2)(in_stack_00000048,uVar9,uVar12,uVar13,uVar14);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar10,uVar8);
        DAT_1804b33b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b33b8,in_stack_00000078);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc5815bc278527a);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_00000080,uVar7);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          if (DAT_1804b33c0 == 0) {
            lVar11 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar11 + 0x390);
            uVar8 = FUN_180333500(in_stack_00000048);
            uVar7 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x000002ea,1);
            lVar11 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar11 + 0x390);
            uVar12 = FUN_1800024f0(in_stack_00000048);
            uVar13 = FUN_180002550(in_stack_00000048);
            uVar14 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00006d00,0x20);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar15 = FUN_1800025a0(in_stack_00000048);
            uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
            (*pcVar2)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
            DAT_1804b33c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b33c0,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0)
          ;
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,uVar10);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              0xdfb0b3c3223f6dc4);
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000c8);
          (*pcVar1)(in_stack_00000048,uVar8,2,uVar10);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            if (DAT_1804b5c70 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x2f8))(in_stack_00000048,uVar10);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar6 == '\0') {
                if (DAT_1804b33c8 == 0) {
                  lVar11 = *in_stack_00000048;
                  pcVar1 = *(code **)(lVar11 + 0x390);
                  uVar8 = FUN_180333500(in_stack_00000048);
                  uVar7 = DAT_1804b59f0;
                  uVar9 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x000002e8,1);
                  lVar11 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar11 + 0x390);
                  uVar12 = FUN_1800024f0(in_stack_00000048);
                  uVar13 = FUN_180002550(in_stack_00000048);
                  uVar14 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00002a20,0x19);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar15 = FUN_1800025a0(in_stack_00000048);
                  uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
                  (*pcVar2)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                  uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
                  DAT_1804b33c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804b33c8,in_stack_00000078);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,5,in_stack_00000088,0);
                (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,uVar10);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                    in_stack_00000878 >> 0x20);
                (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                if (DAT_1804b4dd8 == 0) {
                  return;
                }
                if (DAT_1804b4de0 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,DAT_1804b4dd8,DAT_1804b4de0,
                                    (uint)in_stack_000000c8 ^ 0xec1725e7);
                (*pcVar1)(in_stack_00000048,uVar8,2,uVar10);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                    0xdfc38943b1e4998c);
                (*pcVar1)(in_stack_00000048,uVar8,3,uVar10);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                    in_stack_000000c8);
                (*pcVar1)(in_stack_00000048,uVar8,4,uVar10);
                (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar6 == '\0') {
                    if (DAT_1804b33d0 == 0) {
                      lVar11 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar11 + 0x390);
                      uVar7 = FUN_180333500(in_stack_00000048);
                      uVar10 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x000002e6,1);
                      lVar11 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar11 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar12 = FUN_180002550(in_stack_00000048);
                      uVar13 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x000025b0,0xd)
                      ;
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar14 = FUN_1800025a0(in_stack_00000048);
                      uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                      (*pcVar2)(in_stack_00000048,uVar9,uVar12,uVar13,uVar14);
                      uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar7,uVar10,uVar8);
                      DAT_1804b33d0 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                    }
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b33d0,in_stack_00000078);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000088,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       0xdfc5815bc278527a);
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                       in_stack_000000c8);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar10,in_stack_00000080,uVar7);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar6 == '\0') {
                        if (DAT_1804b33d8 == 0) {
                          lVar11 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar11 + 0x390);
                          uVar8 = FUN_180333500(in_stack_00000048);
                          uVar7 = DAT_1804b59f0;
                          uVar9 = (**(code **)(lVar11 + 0x518))
                                            (in_stack_00000048,&stack0x000002e4,1);
                          lVar11 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar11 + 0x390);
                          uVar12 = FUN_1800024f0(in_stack_00000048);
                          uVar13 = FUN_180002550(in_stack_00000048);
                          uVar14 = (**(code **)(lVar11 + 0x518))
                                             (in_stack_00000048,&stack0x00006cc0,0x20);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar15 = FUN_1800025a0(in_stack_00000048);
                          uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar15);
                          (*pcVar2)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar7,uVar9);
                          DAT_1804b33d8 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                        }
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b33d8,in_stack_00000078);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,3,in_stack_00000088,0);
                        (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,uVar10);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                            0xdfb0b3c3223f6dc4);
                        (*pcVar1)(in_stack_00000048,uVar8,1,uVar10);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                            in_stack_000000c8);
                        (*pcVar1)(in_stack_00000048,uVar8,2,uVar10);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 == '\0') {
                          if (DAT_1804b5c78 == 0) {
                            return;
                          }
                          (**(code **)(*in_stack_00000048 + 0x2f8))(in_stack_00000048,uVar10);
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
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
          }
        }
      }
    }
  }
  return;
}

