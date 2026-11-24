/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=252 entry=18011b14e blocks=49 maxInstructions=100000 */

/* cut edge: 0x18011b14e -> 0x18014d3db */
/* cut edge: 0x18011b245 -> 0x18014d3db */
/* cut edge: 0x18011b25b -> 0x18014d3db */
/* cut edge: 0x18011b2c8 -> 0x180121ef0 */
/* cut edge: 0x18011b2f1 -> 0x180121f26 */
/* cut edge: 0x18011b34d -> 0x180121f77 */
/* cut edge: 0x18011b35d -> 0x180121fb0 */
/* cut edge: 0x18011b438 -> 0x18014d3db */
/* cut edge: 0x18011b52d -> 0x18011c13b */
/* cut edge: 0x18011b541 -> 0x1801253b0 */
/* cut edge: 0x18011b55b -> 0x18014d3db */
/* cut edge: 0x18011b571 -> 0x18014d3db */
/* cut edge: 0x18011b5e4 -> 0x180120bdb */
/* cut edge: 0x18011b608 -> 0x180120c0e */
/* cut edge: 0x18011b669 -> 0x180120c5f */
/* cut edge: 0x18011b679 -> 0x180120c98 */
/* cut edge: 0x18011b751 -> 0x18014d3db */
/* cut edge: 0x18011b846 -> 0x18014d3db */
/* cut edge: 0x18011b85c -> 0x18014d3db */
/* cut edge: 0x18011b8cc -> 0x180121285 */
/* cut edge: 0x18011b8f0 -> 0x1801212bb */
/* cut edge: 0x18011b94c -> 0x18012130c */
/* cut edge: 0x18011b969 -> 0x180121345 */
/* cut edge: 0x18011ba49 -> 0x18014d3db */
/* cut edge: 0x18011bb3e -> 0x18014d3db */
/* cut edge: 0x18011bb54 -> 0x18014d3db */
/* cut edge: 0x18011bbc2 -> 0x180121a26 */
/* cut edge: 0x18011bbeb -> 0x180121a5e */
/* cut edge: 0x18011bc47 -> 0x180121aaf */
/* cut edge: 0x18011bc5f -> 0x180121ae8 */
/* cut edge: 0x18011bd3f -> 0x18014d3db */
/* cut edge: 0x18011be34 -> 0x18014d3db */
/* cut edge: 0x18011be4a -> 0x18014d3db */
/* cut edge: 0x18011beb5 -> 0x1801222c2 */
/* cut edge: 0x18011bed9 -> 0x1801222f5 */
/* cut edge: 0x18011bf3f -> 0x180122343 */
/* cut edge: 0x18011bf57 -> 0x18012237d */
/* cut edge: 0x18011c039 -> 0x18014d3db */
/* cut edge: 0x18011c039 -> 0x18011c12e */
/* target 0x18011c12e original byte 0x49 replaced by RET */
/* target 0x18011c13b original byte 0xc7 replaced by RET */
/* target 0x180120bdb original byte 0x48 replaced by RET */
/* target 0x180120c0e original byte 0x48 replaced by RET */
/* target 0x180120c5f original byte 0x48 replaced by RET */
/* target 0x180120c98 original byte 0x48 replaced by RET */
/* target 0x180121285 original byte 0x48 replaced by RET */
/* target 0x1801212bb original byte 0x48 replaced by RET */
/* target 0x18012130c original byte 0x48 replaced by RET */
/* target 0x180121345 original byte 0x48 replaced by RET */
/* target 0x180121a26 original byte 0x48 replaced by RET */
/* target 0x180121a5e original byte 0x48 replaced by RET */
/* target 0x180121aaf original byte 0x48 replaced by RET */
/* target 0x180121ae8 original byte 0x48 replaced by RET */
/* target 0x180121ef0 original byte 0x48 replaced by RET */
/* target 0x180121f26 original byte 0x48 replaced by RET */
/* target 0x180121f77 original byte 0x48 replaced by RET */
/* target 0x180121fb0 original byte 0x48 replaced by RET */
/* target 0x1801222c2 original byte 0x48 replaced by RET */
/* target 0x1801222f5 original byte 0x48 replaced by RET */
/* target 0x180122343 original byte 0x48 replaced by RET */
/* target 0x18012237d original byte 0x48 replaced by RET */
/* target 0x1801253b0 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00252_18011b14e(undefined8 param_1,undefined8 param_2)

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
  ulonglong uVar10;
  undefined8 uVar11;
  longlong lVar12;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
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
  int iStack00000000000001bc;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2bab);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8)
  ;
  uVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar12 = DAT_1804b52e8;
      uVar7 = DAT_1804b5208;
      if (cVar6 == '\0') {
        if (DAT_1804ad3b8 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000746,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000031b8,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar11);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar7,uVar8);
          DAT_1804ad3b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ad3b8,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4e8);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000058 % 0x91465 ^ in_stack_000000c0 ^
                           0x64440d1198751ee1);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
        lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                           (in_stack_00000048,uVar7,in_stack_000000d8);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          lVar12 = (uVar10 ^ in_stack_00000058) - lVar12;
          if ((longlong)in_stack_000000c0 < 1) {
            return;
          }
          iStack00000000000001bc = 0;
          if (in_stack_000000f8 == 0) {
            return;
          }
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            lVar3 = DAT_1804b52e8;
            uVar7 = DAT_1804b5208;
            if (cVar6 == '\0') {
              iStack00000000000001bc = iStack00000000000001bc + 1;
              if (DAT_1804ad3c0 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000744,1);
                lVar5 = DAT_1804b4d38;
                lVar4 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x000031a8,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000090);
                (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar11);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar7,uVar8);
                DAT_1804ad3c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ad3c0,in_stack_000000b0);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6be2);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 lVar12 % 0x38ea8 ^ in_stack_000000c0 ^ 0xd27bebe9fbc44944);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  lVar3 = DAT_1804b52e8;
                  uVar7 = DAT_1804b5208;
                  if (cVar6 == '\0') {
                    if (DAT_1804ad3c8 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b52e8 == 0) {
                        return;
                      }
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00000742,1);
                      lVar5 = DAT_1804b4d38;
                      lVar4 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x00003198,5);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,in_stack_00000090);
                      (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar11);
                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar7,uVar8);
                      DAT_1804ad3c8 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ad3c8,in_stack_000000b0);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7930)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       lVar12 % 0x9ad23 ^ in_stack_000000c0 ^ 0xe59f3479a2bedd91);
                    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar7,in_stack_000000d8);
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar6 == '\0') {
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                        lVar3 = DAT_1804b52e8;
                        uVar7 = DAT_1804b5208;
                        if (cVar6 == '\0') {
                          if (DAT_1804ad3d0 == 0) {
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b52e8 == 0) {
                              return;
                            }
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x00000740,1);
                            lVar5 = DAT_1804b4d38;
                            lVar4 = DAT_1804b4d30;
                            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b4d30 == 0) {
                              return;
                            }
                            if (DAT_1804b4d38 == 0) {
                              return;
                            }
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x00003188,5);
                            if (DAT_1804b4d40 == 0) {
                              return;
                            }
                            uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,in_stack_00000090);
                            (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar11);
                            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar7,uVar8)
                            ;
                            DAT_1804ad3d0 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ad3d0,in_stack_000000b0);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x26c3);
                          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             lVar12 % 0x171b6 ^ in_stack_000000c0 ^
                                             0xfd122deb19bfbd4a);
                          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar7,in_stack_000000d8);
                          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar6 == '\0') {
                            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar6 == '\0') {
                              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                              lVar3 = DAT_1804b52e8;
                              uVar7 = DAT_1804b5208;
                              if (cVar6 == '\0') {
                                if (DAT_1804ad3d8 == 0) {
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b52e8 == 0) {
                                    return;
                                  }
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x0000073e,1);
                                  lVar5 = DAT_1804b4d38;
                                  lVar4 = DAT_1804b4d30;
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b4d30 == 0) {
                                    return;
                                  }
                                  if (DAT_1804b4d38 == 0) {
                                    return;
                                  }
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x00003178,5);
                                  if (DAT_1804b4d40 == 0) {
                                    return;
                                  }
                                  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,in_stack_00000090);
                                  (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar11);
                                  uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar7,
                                                    uVar8);
                                  DAT_1804ad3d8 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar7);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                                }
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804ad3d8,in_stack_000000b0
                                                  );
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x8b1);
                                (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   lVar12 % 0x8fefe ^ in_stack_000000c0 ^
                                                   0xdd677cf91fbb11d3);
                                (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar7,in_stack_000000d8);
                                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
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

