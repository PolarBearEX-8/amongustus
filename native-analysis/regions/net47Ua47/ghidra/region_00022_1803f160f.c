/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=22 entry=1803f160f blocks=53 maxInstructions=50000 */

/* cut edge: 0x1803f160f -> 0x180473ae8 */
/* cut edge: 0x1803f1625 -> 0x180473ae8 */
/* cut edge: 0x1803f1690 -> 0x18040214f */
/* cut edge: 0x1803f16b4 -> 0x180402182 */
/* cut edge: 0x1803f1715 -> 0x1804021d3 */
/* cut edge: 0x1803f1725 -> 0x18040220d */
/* cut edge: 0x1803f17fd -> 0x180473ae8 */
/* cut edge: 0x1803f1916 -> 0x1803f3d50 */
/* cut edge: 0x1803f1944 -> 0x180473ae8 */
/* cut edge: 0x1803f195a -> 0x180473ae8 */
/* cut edge: 0x1803f19c0 -> 0x1803ff727 */
/* cut edge: 0x1803f19ec -> 0x1803ff762 */
/* cut edge: 0x1803f1a4d -> 0x1803ff7b3 */
/* cut edge: 0x1803f1a5d -> 0x1803ff7ed */
/* cut edge: 0x1803f1b3e -> 0x180473ae8 */
/* cut edge: 0x1803f1c33 -> 0x180473ae8 */
/* cut edge: 0x1803f1c49 -> 0x180473ae8 */
/* cut edge: 0x1803f1cb4 -> 0x180400590 */
/* cut edge: 0x1803f1cdd -> 0x1804005c8 */
/* cut edge: 0x1803f1d3e -> 0x180400616 */
/* cut edge: 0x1803f1d56 -> 0x180400651 */
/* cut edge: 0x1803f1e3b -> 0x180473ae8 */
/* cut edge: 0x1803f1f30 -> 0x180473ae8 */
/* cut edge: 0x1803f1f46 -> 0x180473ae8 */
/* cut edge: 0x1803f1fb2 -> 0x1804011fb */
/* cut edge: 0x1803f1fde -> 0x180401236 */
/* cut edge: 0x1803f203f -> 0x180401287 */
/* cut edge: 0x1803f2052 -> 0x1804012c1 */
/* cut edge: 0x1803f2135 -> 0x180473ae8 */
/* cut edge: 0x1803f222a -> 0x180473ae8 */
/* cut edge: 0x1803f2240 -> 0x180473ae8 */
/* cut edge: 0x1803f22a9 -> 0x180401e71 */
/* cut edge: 0x1803f22d2 -> 0x180401ea7 */
/* cut edge: 0x1803f2333 -> 0x180401ef8 */
/* cut edge: 0x1803f2343 -> 0x180401f31 */
/* cut edge: 0x1803f2423 -> 0x180473ae8 */
/* cut edge: 0x1803f251a -> 0x180436bf4 */
/* cut edge: 0x1803f2539 -> 0x180473ae8 */
/* cut edge: 0x1803f2539 -> 0x1803f254f */
/* target 0x1803f254f original byte 0x48 replaced by RET */
/* target 0x1803f3d50 original byte 0x48 replaced by RET */
/* target 0x1803ff727 original byte 0x48 replaced by RET */
/* target 0x1803ff762 original byte 0x48 replaced by RET */
/* target 0x1803ff7b3 original byte 0x48 replaced by RET */
/* target 0x1803ff7ed original byte 0x48 replaced by RET */
/* target 0x180400590 original byte 0x48 replaced by RET */
/* target 0x1804005c8 original byte 0x48 replaced by RET */
/* target 0x180400616 original byte 0x48 replaced by RET */
/* target 0x180400651 original byte 0x48 replaced by RET */
/* target 0x1804011fb original byte 0x48 replaced by RET */
/* target 0x180401236 original byte 0x48 replaced by RET */
/* target 0x180401287 original byte 0x48 replaced by RET */
/* target 0x1804012c1 original byte 0x48 replaced by RET */
/* target 0x180401e71 original byte 0x48 replaced by RET */
/* target 0x180401ea7 original byte 0x48 replaced by RET */
/* target 0x180401ef8 original byte 0x48 replaced by RET */
/* target 0x180401f31 original byte 0x48 replaced by RET */
/* target 0x18040214f original byte 0x48 replaced by RET */
/* target 0x180402182 original byte 0x48 replaced by RET */
/* target 0x1804021d3 original byte 0x48 replaced by RET */
/* target 0x18040220d original byte 0x48 replaced by RET */
/* target 0x180436bf4 original byte 0x4c replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00022_1803f160f(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  longlong lVar11;
  ulonglong unaff_R14;
  longlong *in_stack_00000048;
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
  longlong in_stack_00000118;
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar11 = DAT_1804b5a08;
    uVar10 = DAT_1804b59f0;
    if (cVar6 == '\0') {
      if (DAT_1804b3f08 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000050c,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001ca0,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar11,uVar10,uVar7);
        DAT_1804b3f08 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b3f08,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x7bc6);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)in_stack_00000068 % 0xe0912 ^ in_stack_000000c8 ^
                         0x613ff8c345316a3e);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
      lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8)
      ;
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        lVar11 = lVar11 + (unaff_R14 ^ in_stack_00000068);
        if (in_stack_00000118 == 0) {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          lVar3 = DAT_1804b5a08;
          uVar10 = DAT_1804b59f0;
          if (cVar6 == '\0') {
            if (DAT_1804b3f10 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x0000050a,1);
              lVar5 = DAT_1804b4d38;
              lVar4 = DAT_1804b4d30;
              pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00001c90,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar3,uVar10,uVar7);
              DAT_1804b3f10 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b3f10,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x717d);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               lVar11 % 0x92cd2 ^ in_stack_000000c8 ^ 0x6eb615666040bbd1);
            (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                lVar3 = DAT_1804b5a08;
                uVar10 = DAT_1804b59f0;
                if (cVar6 == '\0') {
                  if (DAT_1804b3f18 == 0) {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b5a08 == 0) {
                      return;
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000508,1);
                    lVar5 = DAT_1804b4d38;
                    lVar4 = DAT_1804b4d30;
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b4d30 == 0) {
                      return;
                    }
                    if (DAT_1804b4d38 == 0) {
                      return;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00001c80,5);
                    if (DAT_1804b4d40 == 0) {
                      return;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000088);
                    (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
                    uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar3,uVar10,uVar7);
                    DAT_1804b3f18 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b3f18,in_stack_000000a0);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1595);
                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     lVar11 % 0x4125f ^ in_stack_000000c8 ^ 0x2628e40bd980a440);
                  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                  (**(code **)(*in_stack_00000048 + 0x1a0))
                            (in_stack_00000048,uVar10,in_stack_000000d8);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      lVar3 = DAT_1804b5a08;
                      uVar10 = DAT_1804b59f0;
                      if (cVar6 == '\0') {
                        if (DAT_1804b3f20 == 0) {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b5a08 == 0) {
                            return;
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000506,1);
                          lVar5 = DAT_1804b4d38;
                          lVar4 = DAT_1804b4d30;
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b4d30 == 0) {
                            return;
                          }
                          if (DAT_1804b4d38 == 0) {
                            return;
                          }
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00001c70,5);
                          if (DAT_1804b4d40 == 0) {
                            return;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000088);
                          (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
                          uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar3,uVar10,uVar7)
                          ;
                          DAT_1804b3f20 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804b3f20,in_stack_000000a0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                           0x3ba6);
                        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                           lVar11 % 0x3d6be ^ in_stack_000000c8 ^ 0x418d81947d513b73
                                          );
                        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                        (**(code **)(*in_stack_00000048 + 0x1a0))
                                  (in_stack_00000048,uVar10,in_stack_000000d8);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 == '\0') {
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                            lVar3 = DAT_1804b5a08;
                            uVar10 = DAT_1804b59f0;
                            if (cVar6 == '\0') {
                              if (DAT_1804b3f28 == 0) {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b5a08 == 0) {
                                  return;
                                }
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00000504,1);
                                lVar5 = DAT_1804b4d38;
                                lVar4 = DAT_1804b4d30;
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b4d30 == 0) {
                                  return;
                                }
                                if (DAT_1804b4d38 == 0) {
                                  return;
                                }
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x00001c60,5);
                                if (DAT_1804b4d40 == 0) {
                                  return;
                                }
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000088);
                                (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
                                uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar3,uVar10,
                                                   uVar7);
                                DAT_1804b3f28 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar10);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                              }
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b3f28,in_stack_000000a0)
                              ;
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_00000090,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000b8,
                                                 in_stack_000000c0,0x2cfb);
                              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000080,
                                                 in_stack_00000098,
                                                 lVar11 % 0x1da87 ^ in_stack_000000c8 ^
                                                 0x4eb550585ae01a04);
                              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar10,in_stack_000000d8);
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar6 == '\0') {
                                if (in_stack_00000118 == 0) {
                                  return;
                                }
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
    }
  }
  return;
}

