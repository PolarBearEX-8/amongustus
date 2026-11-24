/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=139 entry=1803be8e2 blocks=39 maxInstructions=50000 */

/* cut edge: 0x1803be8e2 -> 0x1803351d6 */
/* cut edge: 0x1803be922 -> 0x1803351d6 */
/* cut edge: 0x1803beacb -> 0x1803351d6 */
/* cut edge: 0x1803bebab -> 0x1803351d6 */
/* cut edge: 0x1803bed4b -> 0x1803351d6 */
/* cut edge: 0x1803bee26 -> 0x1803351d6 */
/* cut edge: 0x1803bee6c -> 0x1803351d6 */
/* cut edge: 0x1803beeaa -> 0x1803351d6 */
/* cut edge: 0x1803bf036 -> 0x1803351d6 */
/* cut edge: 0x1803bf163 -> 0x1803351d6 */
/* cut edge: 0x1803bf1a9 -> 0x1803351d6 */
/* cut edge: 0x1803bf1e9 -> 0x1803351d6 */
/* cut edge: 0x1803bf245 -> 0x1803351d6 */
/* cut edge: 0x1803bf3ea -> 0x1803351d6 */
/* cut edge: 0x1803bf55a -> 0x1803351d6 */
/* cut edge: 0x1803bf570 -> 0x1803c138c */
/* cut edge: 0x1803bf578 -> 0x1803c82ae */
/* cut edge: 0x1803bf5aa -> 0x1803ceb7d */
/* cut edge: 0x1803bf5d4 -> 0x1803351d6 */
/* cut edge: 0x1803bf5ea -> 0x1803351d6 */
/* cut edge: 0x1803bf610 -> 0x1803cebcb */
/* cut edge: 0x1803bf634 -> 0x1803cec01 */
/* cut edge: 0x1803bf696 -> 0x1803cec52 */
/* cut edge: 0x1803bf6a6 -> 0x1803cec8c */
/* cut edge: 0x1803bf795 -> 0x1803c82ff */
/* cut edge: 0x1803bf795 -> 0x1803bf89d */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803bf89d original byte 0x48 replaced by RET */
/* target 0x1803c138c original byte 0x48 replaced by RET */
/* target 0x1803c82ae original byte 0x48 replaced by RET */
/* target 0x1803c82ff original byte 0x48 replaced by RET */
/* target 0x1803ceb7d original byte 0x48 replaced by RET */
/* target 0x1803cebcb original byte 0x48 replaced by RET */
/* target 0x1803cec01 original byte 0x48 replaced by RET */
/* target 0x1803cec52 original byte 0x48 replaced by RET */
/* target 0x1803cec8c original byte 0x48 replaced by RET */


void region_00139_1803be8e2(void)

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
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  undefined8 in_stack_00000248;
  
  pcVar1 = *(code **)(*in_stack_00000048 + 0xd8);
  uVar6 = FUN_18001ee70(in_stack_00000048);
  uVar7 = (*pcVar1)(in_stack_00000048,uVar6);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b3200 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar8 = FUN_1803335a0(in_stack_00000048);
        uVar14 = DAT_1804b59f0;
        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000035c,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000032a0,0x16);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar14 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar14,uVar9);
        DAT_1804b3200 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
      }
      uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b3200,in_stack_00000078);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x50e0);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                         in_stack_000000c8 ^ 0x5bd1c33d2263cad2);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar14,in_stack_00000080,uVar8);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804b3208 == 0) {
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar9 = FUN_1803335a0(in_stack_00000048);
            uVar8 = DAT_1804b59f0;
            uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000035a,1);
            lVar2 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar11 = FUN_1800024f0(in_stack_00000048);
            uVar12 = FUN_180002550(in_stack_00000048);
            uVar13 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003270,0x16);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar15 = FUN_1800025a0(in_stack_00000048);
            uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
            (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar15);
            uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar9,uVar8,uVar10);
            DAT_1804b3208 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b3208,in_stack_00000078);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2a89);
          (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000c8 ^ 0x12d62572f7af30f4);
          (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar5 == '\0') {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
            uVar9 = FUN_18001eed0(in_stack_00000048);
            uVar10 = FUN_18001ef30(in_stack_00000048);
            uVar9 = (*pcVar1)(in_stack_00000048,uVar9,uVar10);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x110);
              uVar10 = FUN_18001ef80(in_stack_00000048);
              uVar8 = (*pcVar1)(in_stack_00000048,uVar8,uVar10,uVar9);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b3210 == 0) {
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar10 = FUN_180333500(in_stack_00000048);
                    uVar9 = DAT_1804b59f0;
                    uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000358,1);
                    lVar2 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar12 = FUN_1800024f0(in_stack_00000048);
                    uVar13 = FUN_180002550(in_stack_00000048);
                    uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00002aa0,0x19);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar16 = FUN_1800025a0(in_stack_00000048);
                    uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar16);
                    (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar15,uVar16);
                    uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar10,uVar9,uVar11);
                    DAT_1804b3210 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
                  }
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b3210,in_stack_00000078);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                     (in_stack_00000048,4,in_stack_00000088,0);
                  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar10,0,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_00000248);
                  (*pcVar1)(in_stack_00000048,uVar10,1,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     0xdfcc108d1c27cf45);
                  (*pcVar1)(in_stack_00000048,uVar10,2,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000c8);
                  (*pcVar1)(in_stack_00000048,uVar10,3,uVar8);
                  (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar9,in_stack_00000080,uVar10);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x488);
                    uVar8 = FUN_18001efd0(in_stack_00000048);
                    uVar9 = FUN_18001f030(in_stack_00000048);
                    (*pcVar1)(in_stack_00000048,uVar8,uVar9);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x420);
                      uVar8 = FUN_18001f080(in_stack_00000048);
                      (*pcVar1)(in_stack_00000048,in_stack_000000a0,uVar8);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
                        uVar8 = FUN_18001f0d0(in_stack_00000048);
                        (*pcVar1)(in_stack_00000048,uVar7,uVar6,uVar8,uVar14);
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar5 == '\0') {
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar5 == '\0') {
                            if (DAT_1804b3218 == 0) {
                              lVar2 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar2 + 0x390);
                              uVar14 = FUN_180333500(in_stack_00000048);
                              uVar6 = DAT_1804b59f0;
                              uVar8 = (**(code **)(lVar2 + 0x518))
                                                (in_stack_00000048,&stack0x00000356,1);
                              lVar2 = *in_stack_00000048;
                              pcVar3 = *(code **)(lVar2 + 0x390);
                              uVar9 = FUN_1800024f0(in_stack_00000048);
                              uVar10 = FUN_180002550(in_stack_00000048);
                              uVar11 = (**(code **)(lVar2 + 0x518))
                                                 (in_stack_00000048,&stack0x00005940,0x2a);
                              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar12 = FUN_1800025a0(in_stack_00000048);
                              uVar12 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar12);
                              (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                              uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar14,uVar6,
                                                uVar8);
                              DAT_1804b3218 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                            }
                            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804b3218,in_stack_00000078);
                            uVar14 = (**(code **)(*in_stack_00000048 + 0x560))
                                               (in_stack_00000048,5,in_stack_00000088,0);
                            (**(code **)(*in_stack_00000048 + 0x570))
                                      (in_stack_00000048,uVar14,0,in_stack_00000168);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,in_stack_00000170);
                            (*pcVar1)(in_stack_00000048,uVar14,1,uVar8);
                            (**(code **)(*in_stack_00000048 + 0x570))
                                      (in_stack_00000048,uVar14,2,uVar7);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,0xdfc79c58f7839374);
                            (*pcVar1)(in_stack_00000048,uVar14,3,uVar7);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_00000090
                                               ,in_stack_000000c8);
                            (*pcVar1)(in_stack_00000048,uVar14,4,uVar7);
                            (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_00000080,uVar14);
                            (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
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

