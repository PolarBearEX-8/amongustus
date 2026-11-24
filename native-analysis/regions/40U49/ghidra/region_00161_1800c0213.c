/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=161 entry=1800c0213 blocks=47 maxInstructions=100000 */

/* cut edge: 0x1800c0213 -> 0x18014d3db */
/* cut edge: 0x1800c0229 -> 0x18014d3db */
/* cut edge: 0x1800c0299 -> 0x1800da597 */
/* cut edge: 0x1800c02c0 -> 0x1800da5ca */
/* cut edge: 0x1800c031e -> 0x1800da618 */
/* cut edge: 0x1800c0336 -> 0x1800da651 */
/* cut edge: 0x1800c0413 -> 0x18014d3db */
/* cut edge: 0x1800c050d -> 0x18014d3db */
/* cut edge: 0x1800c0523 -> 0x18014d3db */
/* cut edge: 0x1800c0593 -> 0x1800dbe6c */
/* cut edge: 0x1800c05b7 -> 0x1800dbe9f */
/* cut edge: 0x1800c0618 -> 0x1800dbef0 */
/* cut edge: 0x1800c0628 -> 0x1800dbf29 */
/* cut edge: 0x1800c0700 -> 0x18014d3db */
/* cut edge: 0x1800c07f5 -> 0x18014d3db */
/* cut edge: 0x1800c080b -> 0x18014d3db */
/* cut edge: 0x1800c0876 -> 0x1800dcfa4 */
/* cut edge: 0x1800c089a -> 0x1800dcfd7 */
/* cut edge: 0x1800c08f8 -> 0x1800dd025 */
/* cut edge: 0x1800c0910 -> 0x1800dd05e */
/* cut edge: 0x1800c09ea -> 0x18014d3db */
/* cut edge: 0x1800c0ae1 -> 0x1801253b0 */
/* cut edge: 0x1800c0af5 -> 0x18014d3db */
/* cut edge: 0x1800c0b0b -> 0x18014d3db */
/* cut edge: 0x1800c0b75 -> 0x1800d5cf3 */
/* cut edge: 0x1800c0b99 -> 0x1800d5d26 */
/* cut edge: 0x1800c0bfa -> 0x1800d5d77 */
/* cut edge: 0x1800c0c0a -> 0x1800d5db1 */
/* cut edge: 0x1800c0d00 -> 0x18014d3db */
/* cut edge: 0x1800c0e3b -> 0x18014d3db */
/* cut edge: 0x1800c0e51 -> 0x18014d3db */
/* cut edge: 0x1800c0eb9 -> 0x1800d76bd */
/* cut edge: 0x1800c0edd -> 0x1800d76f0 */
/* cut edge: 0x1800c0f3e -> 0x1800d7741 */
/* cut edge: 0x1800c0f4e -> 0x1800d777a */
/* cut edge: 0x1800c1026 -> 0x18014d3db */
/* cut edge: 0x1800c111b -> 0x18014d3db */
/* cut edge: 0x1800c111b -> 0x1800c1131 */
/* target 0x1800c1131 original byte 0x48 replaced by RET */
/* target 0x1800d5cf3 original byte 0x48 replaced by RET */
/* target 0x1800d5d26 original byte 0x48 replaced by RET */
/* target 0x1800d5d77 original byte 0x48 replaced by RET */
/* target 0x1800d5db1 original byte 0x48 replaced by RET */
/* target 0x1800d76bd original byte 0x48 replaced by RET */
/* target 0x1800d76f0 original byte 0x48 replaced by RET */
/* target 0x1800d7741 original byte 0x48 replaced by RET */
/* target 0x1800d777a original byte 0x48 replaced by RET */
/* target 0x1800da597 original byte 0x48 replaced by RET */
/* target 0x1800da5ca original byte 0x48 replaced by RET */
/* target 0x1800da618 original byte 0x48 replaced by RET */
/* target 0x1800da651 original byte 0x48 replaced by RET */
/* target 0x1800dbe6c original byte 0x48 replaced by RET */
/* target 0x1800dbe9f original byte 0x48 replaced by RET */
/* target 0x1800dbef0 original byte 0x48 replaced by RET */
/* target 0x1800dbf29 original byte 0x48 replaced by RET */
/* target 0x1800dcfa4 original byte 0x48 replaced by RET */
/* target 0x1800dcfd7 original byte 0x48 replaced by RET */
/* target 0x1800dd025 original byte 0x48 replaced by RET */
/* target 0x1800dd05e original byte 0x48 replaced by RET */
/* target 0x1801253b0 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00161_1800c0213(void)

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
  ulonglong uVar11;
  longlong lVar12;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000078;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  longlong in_stack_000000f8;
  undefined8 in_stack_000001c8;
  undefined8 in_stack_00000bf0;
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar12 = DAT_1804b52e8;
    uVar10 = DAT_1804b5208;
    if (cVar6 == '\0') {
      if (DAT_1804add10 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b52e8 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000004ea,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002258,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar10,uVar7);
        DAT_1804add10 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804add10,in_stack_000000b0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x54a7);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         (longlong)in_stack_00000078 % 0xdc8f1 ^ in_stack_000000c0 ^
                         0xbc67d6603bcc4651);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          lVar12 = DAT_1804b52e8;
          uVar10 = DAT_1804b5208;
          if (cVar6 == '\0') {
            if (DAT_1804add18 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b52e8 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x000004e8,1);
              lVar4 = DAT_1804b4d38;
              lVar3 = DAT_1804b4d30;
              pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00002248,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090)
              ;
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar10,uVar7);
              DAT_1804add18 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804add18,in_stack_000000b0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4ceb);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               (longlong)in_stack_00000078 % 0xddfd5 ^ in_stack_000000c0 ^
                               0xa5662455ac42e7de);
            (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
            uVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                               (in_stack_00000048,uVar10,in_stack_000000d8);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                lVar12 = DAT_1804b52e8;
                uVar10 = DAT_1804b5208;
                if (cVar6 == '\0') {
                  if (DAT_1804add20 == 0) {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b52e8 == 0) {
                      return;
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x000004e6,1);
                    lVar4 = DAT_1804b4d38;
                    lVar3 = DAT_1804b4d30;
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b4d30 == 0) {
                      return;
                    }
                    if (DAT_1804b4d38 == 0) {
                      return;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00002238,5);
                    if (DAT_1804b4d40 == 0) {
                      return;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000090);
                    (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
                    uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar10,uVar7);
                    DAT_1804add20 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804add20,in_stack_000000b0);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x283d);
                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     (longlong)in_stack_00000078 % 0xee8f8 ^ in_stack_000000c0 ^
                                     0x82d9677acfe87609);
                  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                  lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                     (in_stack_00000048,uVar10,in_stack_000000d8);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
                    lVar12 = lVar12 + (uVar11 ^ in_stack_00000078);
                    if (in_stack_000000f8 == 0) {
                      return;
                    }
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      uVar10 = DAT_1804b5208;
                      lVar3 = DAT_1804b5200;
                      if (cVar6 == '\0') {
                        if (DAT_1804add28 == 0) {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b5200 == 0) {
                            return;
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x000004e4,1);
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
                                            (in_stack_00000048,&stack0x00005b70,0x19);
                          if (DAT_1804b4d40 == 0) {
                            return;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000090);
                          (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
                          uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar10,uVar7)
                          ;
                          DAT_1804add28 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804add28,in_stack_000000b0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,4,in_stack_000000a0,0);
                        (**(code **)(*in_stack_00000048 + 0x570))
                                  (in_stack_00000048,uVar7,0,in_stack_00000bf0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_000001c8);
                        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           lVar12 % 0xcc653 ^ 0xe9e87d23014b5487);
                        (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_000000c0);
                        (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
                        (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar6 == '\0') {
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                            lVar3 = DAT_1804b52e8;
                            uVar10 = DAT_1804b5208;
                            if (cVar6 == '\0') {
                              if (DAT_1804add30 == 0) {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b52e8 == 0) {
                                  return;
                                }
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x000004e2,1);
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
                                                  (in_stack_00000048,&stack0x00002228,5);
                                if (DAT_1804b4d40 == 0) {
                                  return;
                                }
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000090);
                                (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar8,uVar9);
                                uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar10,
                                                   uVar7);
                                DAT_1804add30 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar10);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                              }
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804add30,in_stack_000000b0)
                              ;
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_000000a0,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000c8,
                                                 in_stack_000000d0,0x4d66);
                              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,
                                                 lVar12 % 0xa5ea4 ^ in_stack_000000c0 ^
                                                 0x8d0597b7c3d1a6fd);
                              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                              (**(code **)(*in_stack_00000048 + 0x1a0))
                                        (in_stack_00000048,uVar10,in_stack_000000d8);
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
    }
  }
  return;
}

