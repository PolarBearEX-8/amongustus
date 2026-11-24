/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=152 entry=1803cb92d blocks=32 maxInstructions=50000 */

/* cut edge: 0x1803cb92d -> 0x1803351d6 */
/* cut edge: 0x1803cba7f -> 0x1803351d6 */
/* cut edge: 0x1803cbabf -> 0x1803351d6 */
/* cut edge: 0x1803cbc68 -> 0x1803351d6 */
/* cut edge: 0x1803cbd4b -> 0x1803351d6 */
/* cut edge: 0x1803cbeeb -> 0x1803351d6 */
/* cut edge: 0x1803cbfc9 -> 0x1803351d6 */
/* cut edge: 0x1803cc00f -> 0x1803351d6 */
/* cut edge: 0x1803cc04d -> 0x1803351d6 */
/* cut edge: 0x1803cc1df -> 0x1803351d6 */
/* cut edge: 0x1803cc30c -> 0x1803351d6 */
/* cut edge: 0x1803cc352 -> 0x1803351d6 */
/* cut edge: 0x1803cc392 -> 0x1803351d6 */
/* cut edge: 0x1803cc3ee -> 0x1803351d6 */
/* cut edge: 0x1803cc599 -> 0x1803aaa01 */
/* cut edge: 0x1803cc6e2 -> 0x1803351d6 */
/* cut edge: 0x1803cc6e7 -> 0x1803aaa2b */
/* cut edge: 0x1803cc71c -> 0x1803aaa2b */
/* cut edge: 0x1803cc738 -> 0x1803aa874 */
/* cut edge: 0x1803cc76d -> 0x1803aa874 */
/* cut edge: 0x1803cc789 -> 0x1803aa890 */
/* cut edge: 0x1803cc7c2 -> 0x1803aa90a */
/* cut edge: 0x1803cc7f7 -> 0x1803aa90a */
/* cut edge: 0x1803cc813 -> 0x1803aa91a */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803aa874 original byte 0x48 replaced by RET */
/* target 0x1803aa890 original byte 0x4c replaced by RET */
/* target 0x1803aa90a original byte 0x4c replaced by RET */
/* target 0x1803aa91a original byte 0x48 replaced by RET */
/* target 0x1803aaa01 original byte 0x48 replaced by RET */
/* target 0x1803aaa2b original byte 0x48 replaced by RET */


void region_00152_1803cb92d(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  undefined8 in_stack_000001c8;
  undefined8 in_stack_00000248;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000088,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_000000a0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001c8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,2,in_stack_00000060);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc0096f727d872c);
  (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar7,4,uVar8);
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_00000080,uVar7);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
    uVar6 = FUN_18001ee70(in_stack_00000048);
    uVar7 = (*pcVar1)(in_stack_00000048,uVar6);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
      if (DAT_1804b3190 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar9 = FUN_1803335a0(in_stack_00000048);
        uVar8 = DAT_1804b59f0;
        uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000037a,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar11 = FUN_1800024f0(in_stack_00000048);
        uVar12 = FUN_180002550(in_stack_00000048);
        uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000033f0,0x16);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar14 = FUN_1800025a0(in_stack_00000048);
        uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
        (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
        uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
        DAT_1804b3190 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b3190,in_stack_00000078);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000070,in_stack_000000a8,0x50e0);
      (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                          in_stack_000000c8 ^ 0x5bd1c33d2263cad2);
      (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
        if (DAT_1804b3198 == 0) {
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1803335a0(in_stack_00000048);
          uVar9 = DAT_1804b59f0;
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000378,1);
          lVar2 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar12 = FUN_1800024f0(in_stack_00000048);
          uVar13 = FUN_180002550(in_stack_00000048);
          uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000033c0,0x16);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar15 = FUN_1800025a0(in_stack_00000048);
          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
          (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
          uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11);
          DAT_1804b3198 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b3198,in_stack_00000078);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000070,in_stack_000000a8,0x2a89);
        (*pcVar1)(in_stack_00000048,uVar10,0,uVar11);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                            in_stack_000000c8 ^ 0x12d62572f7af30f4);
        (*pcVar1)(in_stack_00000048,uVar10,1,uVar11);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
          uVar10 = FUN_18001eed0(in_stack_00000048);
          uVar11 = FUN_18001ef30(in_stack_00000048);
          uVar10 = (*pcVar1)(in_stack_00000048,uVar10,uVar11);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_18001ef80(in_stack_00000048);
            uVar9 = (*pcVar1)(in_stack_00000048,uVar9,uVar11,uVar10);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((cVar5 == '\0') &&
               (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
              if (DAT_1804b31a0 == 0) {
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar11 = FUN_180333500(in_stack_00000048);
                uVar10 = DAT_1804b59f0;
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000376,1);
                lVar2 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar13 = FUN_1800024f0(in_stack_00000048);
                uVar14 = FUN_180002550(in_stack_00000048);
                uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00002ae0,0x19);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar16 = FUN_1800025a0(in_stack_00000048);
                uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar16);
                (*pcVar3)(in_stack_00000048,uVar13,uVar14,uVar15,uVar16);
                uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar11,uVar10,uVar12);
                DAT_1804b31a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
              }
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b31a0,in_stack_00000078);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                 (in_stack_00000048,4,in_stack_00000088,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar11,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_00000248);
              (*pcVar1)(in_stack_00000048,uVar11,1,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 0xdfcc108d1c27cf45);
              (*pcVar1)(in_stack_00000048,uVar11,2,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar11,3,uVar9);
              (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
                uVar9 = FUN_18001efd0(in_stack_00000048);
                uVar10 = FUN_18001f030(in_stack_00000048);
                (*pcVar1)(in_stack_00000048,uVar9,uVar10);
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar5 == '\0') {
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x420);
                  uVar9 = FUN_18001f080(in_stack_00000048);
                  (*pcVar1)(in_stack_00000048,in_stack_000000e8,uVar9);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                    uVar9 = FUN_18001f0d0(in_stack_00000048);
                    (*pcVar1)(in_stack_00000048,uVar7,uVar6,uVar9,uVar8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((cVar5 == '\0') &&
                       (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
                      if (DAT_1804b31a8 == 0) {
                        lVar2 = *in_stack_00000048;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar8 = FUN_180333500(in_stack_00000048);
                        uVar6 = DAT_1804b59f0;
                        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000374,1);
                        lVar2 = *in_stack_00000048;
                        pcVar3 = *(code **)(lVar2 + 0x390);
                        uVar10 = FUN_1800024f0(in_stack_00000048);
                        uVar11 = FUN_180002550(in_stack_00000048);
                        uVar12 = (**(code **)(lVar2 + 0x518))
                                           (in_stack_00000048,&stack0x00005a00,0x2a);
                        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar13 = FUN_1800025a0(in_stack_00000048);
                        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
                        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                        uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar6,uVar9);
                        DAT_1804b31a8 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b31a8,in_stack_00000078);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,5,in_stack_00000088,0);
                      (**(code **)(*in_stack_00000048 + 0x570))
                                (in_stack_00000048,uVar8,0,in_stack_00000168);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_00000170);
                      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,2,uVar7);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         0xdfc79c58f7839374);
                      (*pcVar1)(in_stack_00000048,uVar8,3,uVar7);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_000000c8);
                      (*pcVar1)(in_stack_00000048,uVar8,4,uVar7);
                      (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_00000080,uVar8);
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
    }
  }
  return;
}

