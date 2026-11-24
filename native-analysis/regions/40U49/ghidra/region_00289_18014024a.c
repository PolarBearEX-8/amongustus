/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=289 entry=18014024a blocks=45 maxInstructions=100000 */

/* cut edge: 0x18014024a -> 0x18014d3db */
/* cut edge: 0x180140260 -> 0x18014d3db */
/* cut edge: 0x1801402c8 -> 0x18014aa3b */
/* cut edge: 0x1801402f1 -> 0x18014aa73 */
/* cut edge: 0x18014034d -> 0x18014aac4 */
/* cut edge: 0x18014035d -> 0x18014aafd */
/* cut edge: 0x180140435 -> 0x18014d3db */
/* cut edge: 0x18014052c -> 0x18014d3db */
/* cut edge: 0x180140542 -> 0x18014d3db */
/* cut edge: 0x1801405aa -> 0x18014ab34 */
/* cut edge: 0x1801405d3 -> 0x18014ab6c */
/* cut edge: 0x180140632 -> 0x18014abbd */
/* cut edge: 0x180140642 -> 0x18014abf6 */
/* cut edge: 0x18014071d -> 0x18014d3db */
/* cut edge: 0x180140814 -> 0x18014d3db */
/* cut edge: 0x18014082a -> 0x18014d3db */
/* cut edge: 0x180140892 -> 0x18014ac2d */
/* cut edge: 0x1801408bb -> 0x18014ac65 */
/* cut edge: 0x18014091d -> 0x18014acb6 */
/* cut edge: 0x18014092d -> 0x18014acef */
/* cut edge: 0x180140a0b -> 0x18014d3db */
/* cut edge: 0x180140b00 -> 0x18014d3db */
/* cut edge: 0x180140b16 -> 0x18014d3db */
/* cut edge: 0x180140b7e -> 0x18014ad26 */
/* cut edge: 0x180140baa -> 0x18014ad5e */
/* cut edge: 0x180140c11 -> 0x18014adb8 */
/* cut edge: 0x180140c21 -> 0x18014adf2 */
/* cut edge: 0x180140d64 -> 0x18014d3db */
/* cut edge: 0x180140df9 -> 0x18012b5f8 */
/* cut edge: 0x180140e15 -> 0x18014d3db */
/* cut edge: 0x180140e5b -> 0x18014d3db */
/* cut edge: 0x180140e71 -> 0x18014d3db */
/* cut edge: 0x180141017 -> 0x18014d3db */
/* cut edge: 0x18014110e -> 0x18014d3db */
/* cut edge: 0x18014110e -> 0x180141124 */
/* target 0x18012b5f8 original byte 0x4c replaced by RET */
/* target 0x180141124 original byte 0x48 replaced by RET */
/* target 0x18014aa3b original byte 0x48 replaced by RET */
/* target 0x18014aa73 original byte 0x48 replaced by RET */
/* target 0x18014aac4 original byte 0x48 replaced by RET */
/* target 0x18014aafd original byte 0x48 replaced by RET */
/* target 0x18014ab34 original byte 0x48 replaced by RET */
/* target 0x18014ab6c original byte 0x48 replaced by RET */
/* target 0x18014abbd original byte 0x48 replaced by RET */
/* target 0x18014abf6 original byte 0x48 replaced by RET */
/* target 0x18014ac2d original byte 0x48 replaced by RET */
/* target 0x18014ac65 original byte 0x48 replaced by RET */
/* target 0x18014acb6 original byte 0x48 replaced by RET */
/* target 0x18014acef original byte 0x48 replaced by RET */
/* target 0x18014ad26 original byte 0x48 replaced by RET */
/* target 0x18014ad5e original byte 0x48 replaced by RET */
/* target 0x18014adb8 original byte 0x48 replaced by RET */
/* target 0x18014adf2 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00289_18014024a(void)

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
  longlong *in_stack_00000048;
  longlong in_stack_00000078;
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
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar5 = DAT_1804b52e8;
    uVar10 = DAT_1804b5208;
    if (cVar6 == '\0') {
      if (DAT_1804acc78 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b52e8 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000091c,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00003e18,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7);
        DAT_1804acc78 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804acc78,in_stack_000000b0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x737f);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         in_stack_00000078 % 0x4a7ed ^ in_stack_000000c0 ^ 0x9cba33143c1c2d0);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          lVar5 = DAT_1804b52e8;
          uVar10 = DAT_1804b5208;
          if (cVar6 == '\0') {
            if (DAT_1804acc80 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b52e8 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x0000091a,1);
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
                                (in_stack_00000048,&stack0x00003e08,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090)
              ;
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7);
              DAT_1804acc80 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804acc80,in_stack_000000b0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6ed2);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_00000078 % 0x4b622 ^ in_stack_000000c0 ^ 0x92dc2d8d86cedca);
            (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                lVar5 = DAT_1804b52e8;
                uVar10 = DAT_1804b5208;
                if (cVar6 == '\0') {
                  if (DAT_1804acc88 == 0) {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b52e8 == 0) {
                      return;
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000918,1);
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
                                      (in_stack_00000048,&stack0x00003df8,5);
                    if (DAT_1804b4d40 == 0) {
                      return;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000090);
                    (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
                    uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7);
                    DAT_1804acc88 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804acc88,in_stack_000000b0);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0xfac);
                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_00000078 % 0xc3f57 ^ in_stack_000000c0 ^
                                     0x781d1e56d50787e);
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
                      lVar5 = DAT_1804b52e8;
                      uVar10 = DAT_1804b5208;
                      if (cVar6 == '\0') {
                        if (DAT_1804acc90 == 0) {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b52e8 == 0) {
                            return;
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000916,1);
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
                                            (in_stack_00000048,&stack0x00003de8,5);
                          if (DAT_1804b4d40 == 0) {
                            return;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000090);
                          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
                          uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7)
                          ;
                          DAT_1804acc90 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804acc90,in_stack_000000b0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x7200);
                        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_00000078 % 0x80298 ^ in_stack_000000c0 ^
                                           0x39006019492b9740);
                        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
                        (**(code **)(*in_stack_00000048 + 0x1a0))
                                  (in_stack_00000048,uVar10,in_stack_000000d8);
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
      }
    }
  }
  return;
}

