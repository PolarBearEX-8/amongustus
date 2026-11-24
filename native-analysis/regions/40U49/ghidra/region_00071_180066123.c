/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=71 entry=180066123 blocks=51 maxInstructions=100000 */

/* cut edge: 0x18006617a -> 0x180073a44 */
/* cut edge: 0x18006619e -> 0x180073a77 */
/* cut edge: 0x180066202 -> 0x180073ac8 */
/* cut edge: 0x180066212 -> 0x180073b01 */
/* cut edge: 0x1800662ed -> 0x18014d3db */
/* cut edge: 0x1800663e4 -> 0x18014d3db */
/* cut edge: 0x1800663fa -> 0x18014d3db */
/* cut edge: 0x18006646a -> 0x180073c2d */
/* cut edge: 0x180066493 -> 0x180073c63 */
/* cut edge: 0x1800664f2 -> 0x180073cb4 */
/* cut edge: 0x180066502 -> 0x180073ced */
/* cut edge: 0x1800665e0 -> 0x18014d3db */
/* cut edge: 0x1800666d5 -> 0x1801253c7 */
/* cut edge: 0x1800666e9 -> 0x180070978 */
/* cut edge: 0x180066708 -> 0x18014d3db */
/* cut edge: 0x180066731 -> 0x18014d3db */
/* cut edge: 0x180066747 -> 0x18014d3db */
/* cut edge: 0x1800667b2 -> 0x180070ff8 */
/* cut edge: 0x1800667de -> 0x180071030 */
/* cut edge: 0x18006683d -> 0x180071081 */
/* cut edge: 0x180066852 -> 0x1800710bb */
/* cut edge: 0x180066949 -> 0x18014d3db */
/* cut edge: 0x180066a2d -> 0x18014d3db */
/* cut edge: 0x180066a43 -> 0x18014d3db */
/* cut edge: 0x180066aa3 -> 0x1800717b2 */
/* cut edge: 0x180066aca -> 0x1800717e5 */
/* cut edge: 0x180066b33 -> 0x180071836 */
/* cut edge: 0x180066b43 -> 0x18007186f */
/* cut edge: 0x180066c60 -> 0x18014d3db */
/* cut edge: 0x180066d9e -> 0x18014d3db */
/* cut edge: 0x180066db4 -> 0x18014d3db */
/* cut edge: 0x180066e1f -> 0x180072052 */
/* cut edge: 0x180066e43 -> 0x180072085 */
/* cut edge: 0x180066ea7 -> 0x1800720d6 */
/* cut edge: 0x180066eb7 -> 0x18007210f */
/* cut edge: 0x180066f93 -> 0x18014d3db */
/* cut edge: 0x18006708a -> 0x18014d3db */
/* cut edge: 0x1800670a0 -> 0x18014d3db */
/* cut edge: 0x1800670b6 -> 0x18006728e */
/* cut edge: 0x180067117 -> 0x1800728f0 */
/* cut edge: 0x180067117 -> 0x18006713b */
/* target 0x18006713b original byte 0x48 replaced by RET */
/* target 0x18006728e original byte 0x48 replaced by RET */
/* target 0x180070978 original byte 0x48 replaced by RET */
/* target 0x180070ff8 original byte 0x48 replaced by RET */
/* target 0x180071030 original byte 0x48 replaced by RET */
/* target 0x180071081 original byte 0x48 replaced by RET */
/* target 0x1800710bb original byte 0x48 replaced by RET */
/* target 0x1800717b2 original byte 0x48 replaced by RET */
/* target 0x1800717e5 original byte 0x48 replaced by RET */
/* target 0x180071836 original byte 0x48 replaced by RET */
/* target 0x18007186f original byte 0x48 replaced by RET */
/* target 0x180072052 original byte 0x48 replaced by RET */
/* target 0x180072085 original byte 0x48 replaced by RET */
/* target 0x1800720d6 original byte 0x48 replaced by RET */
/* target 0x18007210f original byte 0x48 replaced by RET */
/* target 0x1800728f0 original byte 0x48 replaced by RET */
/* target 0x180073a44 original byte 0x48 replaced by RET */
/* target 0x180073a77 original byte 0x48 replaced by RET */
/* target 0x180073ac8 original byte 0x48 replaced by RET */
/* target 0x180073b01 original byte 0x48 replaced by RET */
/* target 0x180073c2d original byte 0x48 replaced by RET */
/* target 0x180073c63 original byte 0x48 replaced by RET */
/* target 0x180073cb4 original byte 0x48 replaced by RET */
/* target 0x180073ced original byte 0x48 replaced by RET */
/* target 0x1801253c7 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00071_180066123(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  undefined8 uVar4;
  code *pcVar5;
  code *pcVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  ulonglong uVar9;
  code *pcVar10;
  longlong lVar11;
  code *pcVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  code *in_stack_00000048;
  ulonglong in_stack_00000060;
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
  undefined8 in_stack_00000138;
  undefined8 in_stack_000056a0;
  
  pcVar10 = DAT_1804b52e8;
  uVar8 = DAT_1804b5208;
  if (DAT_1804ae2c8 == 0) {
    pcVar12 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
    if (DAT_1804b52e8 == (code *)0x0) {
      return (code *)0x0;
    }
    uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                      (in_stack_00000048,&stack0x0000037a,1);
    lVar2 = DAT_1804b4d38;
    lVar11 = DAT_1804b4d30;
    pcVar5 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
    if (DAT_1804b4d30 == 0) {
      return pcVar5;
    }
    if (DAT_1804b4d38 == 0) {
      return pcVar5;
    }
    pcVar6 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                               (in_stack_00000048,&stack0x00001878,5);
    if (DAT_1804b4d40 == 0) {
      return pcVar6;
    }
    uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                      (in_stack_00000048,in_stack_00000090);
    (*pcVar5)(in_stack_00000048,lVar11,lVar2,pcVar6,uVar7);
    uVar8 = (*pcVar12)(in_stack_00000048,in_stack_00000090,pcVar10,uVar8,uVar4);
    DAT_1804ae2c8 = (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
    (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
  }
  uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804ae2c8,in_stack_000000b0);
  uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                    (in_stack_00000048,2,in_stack_000000a0,0);
  pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1627);
  (*pcVar10)(in_stack_00000048,uVar4,0,uVar7);
  pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                     (longlong)in_stack_00000060 % 0x31541 ^ in_stack_000000c0 ^ 0x73c8c160dba9dbcb)
  ;
  (*pcVar10)(in_stack_00000048,uVar4,1,uVar7);
  uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                    (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
  uVar9 = (**(code **)(*(longlong *)in_stack_00000048 + 0x1a0))
                    (in_stack_00000048,uVar8,in_stack_000000d8);
  pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)pcVar10 == '\0') {
    pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
    if ((char)pcVar10 == '\0') {
      pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
      pcVar12 = DAT_1804b52e8;
      uVar8 = DAT_1804b5208;
      if ((char)pcVar10 == '\0') {
        if (DAT_1804ae2d0 == 0) {
          pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == (code *)0x0) {
            return pcVar10;
          }
          uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                            (in_stack_00000048,&stack0x00000378,1);
          lVar2 = DAT_1804b4d38;
          lVar11 = DAT_1804b4d30;
          pcVar5 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar5;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar5;
          }
          pcVar6 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00001868,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar6;
          }
          uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                            (in_stack_00000048,in_stack_00000090);
          (*pcVar5)(in_stack_00000048,lVar11,lVar2,pcVar6,uVar7);
          uVar8 = (*pcVar10)(in_stack_00000048,in_stack_00000090,pcVar12,uVar8,uVar4);
          DAT_1804ae2d0 =
               (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        }
        uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae2d0,in_stack_000000b0);
        uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                          (in_stack_00000048,2,in_stack_000000a0,0);
        pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3d70);
        (*pcVar10)(in_stack_00000048,uVar4,0,uVar7);
        pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000060 % 0xd15fb ^ in_stack_000000c0 ^
                           0x36297fddfe2b1ca5);
        (*pcVar10)(in_stack_00000048,uVar4,1,uVar7);
        uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
        lVar11 = (**(code **)(*(longlong *)in_stack_00000048 + 0x1a0))
                           (in_stack_00000048,uVar8,in_stack_000000d8);
        pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar10 == '\0') {
          lVar11 = (uVar9 ^ in_stack_00000060) - lVar11;
          if (in_stack_000000f8 == 0) {
            return pcVar10;
          }
          if (DAT_1804b54a0 == 0) {
            return in_stack_00000048;
          }
          uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x2f8))
                            (in_stack_00000048,in_stack_00000138);
          pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))(in_stack_00000048)
          ;
          if ((char)pcVar10 == '\0') {
            pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
            if ((char)pcVar10 == '\0') {
              pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
              lVar2 = DAT_1804b5288;
              uVar4 = DAT_1804b5208;
              if ((char)pcVar10 == '\0') {
                if (DAT_1804ae2d8 == 0) {
                  pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                  if (DAT_1804b5288 == 0) {
                    return (code *)0x0;
                  }
                  uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                    (in_stack_00000048,&stack0x00000376,1);
                  lVar3 = DAT_1804b4d38;
                  lVar1 = DAT_1804b4d30;
                  pcVar12 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                  if (DAT_1804b4d30 == 0) {
                    return pcVar12;
                  }
                  if (DAT_1804b4d38 == 0) {
                    return pcVar12;
                  }
                  pcVar5 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                             (in_stack_00000048,&stack0x00006340,0x16);
                  if (DAT_1804b4d40 == 0) {
                    return pcVar5;
                  }
                  uVar13 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                     (in_stack_00000048,in_stack_00000090);
                  (*pcVar12)(in_stack_00000048,lVar1,lVar3,pcVar5,uVar13);
                  uVar4 = (*pcVar10)(in_stack_00000048,in_stack_00000090,lVar2,uVar4,uVar7);
                  DAT_1804ae2d8 =
                       (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))(in_stack_00000048,uVar4)
                  ;
                  (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))(in_stack_00000048,uVar4);
                }
                uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                  (in_stack_00000048,DAT_1804ae2d8,in_stack_000000b0);
                uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_000000a0,0);
                pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                uVar13 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6644);
                (*pcVar10)(in_stack_00000048,uVar7,0,uVar13);
                pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                uVar13 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                   (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                    lVar11 % 0xc59ed ^ in_stack_000000c0 ^ 0xbb286e52d69d2ce0);
                (*pcVar10)(in_stack_00000048,uVar7,1,uVar13);
                uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                  (in_stack_00000048,uVar4,in_stack_000000a8,uVar7);
                pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))
                                            (in_stack_00000048);
                if ((char)pcVar10 == '\0') {
                  pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
                  if ((char)pcVar10 == '\0') {
                    pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
                    uVar7 = DAT_1804b5208;
                    lVar2 = DAT_1804b5200;
                    if ((char)pcVar10 == '\0') {
                      if (DAT_1804ae2e0 == 0) {
                        pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                        if (DAT_1804b5200 == 0) {
                          return (code *)0x0;
                        }
                        uVar13 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                           (in_stack_00000048,&stack0x00000374,1);
                        lVar3 = DAT_1804b4d38;
                        lVar1 = DAT_1804b4d30;
                        pcVar12 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                        if (DAT_1804b4d30 == 0) {
                          return pcVar12;
                        }
                        if (DAT_1804b4d38 == 0) {
                          return pcVar12;
                        }
                        pcVar5 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                                   (in_stack_00000048,&stack0x000075c0,0x2a);
                        if (DAT_1804b4d40 == 0) {
                          return pcVar5;
                        }
                        uVar14 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                           (in_stack_00000048,in_stack_00000090);
                        (*pcVar12)(in_stack_00000048,lVar1,lVar3,pcVar5,uVar14);
                        uVar7 = (*pcVar10)(in_stack_00000048,in_stack_00000090,lVar2,uVar7,uVar13);
                        DAT_1804ae2e0 =
                             (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))
                                       (in_stack_00000048,uVar7);
                        (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))
                                  (in_stack_00000048,uVar7);
                      }
                      uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804ae2e0,in_stack_000000b0);
                      uVar13 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                                         (in_stack_00000048,5,in_stack_000000a0,0);
                      (**(code **)(*(longlong *)in_stack_00000048 + 0x570))
                                (in_stack_00000048,uVar13,0,uVar8);
                      (**(code **)(*(longlong *)in_stack_00000048 + 0x570))
                                (in_stack_00000048,uVar13,1,uVar4);
                      pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         in_stack_000056a0);
                      (*pcVar10)(in_stack_00000048,uVar13,2,uVar8);
                      pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         lVar11 % 0x10682 ^ 0xe9e45ddc2e4d73e9);
                      (*pcVar10)(in_stack_00000048,uVar13,3,uVar8);
                      pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                      uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         in_stack_000000c0);
                      (*pcVar10)(in_stack_00000048,uVar13,4,uVar8);
                      (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_000000a8,uVar13);
                      pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))
                                                  (in_stack_00000048);
                      if ((char)pcVar10 == '\0') {
                        pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
                        if ((char)pcVar10 == '\0') {
                          pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
                          pcVar12 = DAT_1804b52e8;
                          uVar8 = DAT_1804b5208;
                          if ((char)pcVar10 == '\0') {
                            if (DAT_1804ae2e8 == 0) {
                              pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                              if (DAT_1804b52e8 == (code *)0x0) {
                                return (code *)0x0;
                              }
                              uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                                (in_stack_00000048,&stack0x00000372,1);
                              lVar1 = DAT_1804b4d38;
                              lVar2 = DAT_1804b4d30;
                              pcVar5 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
                              if (DAT_1804b4d30 == 0) {
                                return pcVar5;
                              }
                              if (DAT_1804b4d38 == 0) {
                                return pcVar5;
                              }
                              pcVar6 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                                         (in_stack_00000048,&stack0x00001858,5);
                              if (DAT_1804b4d40 == 0) {
                                return pcVar6;
                              }
                              uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                                (in_stack_00000048,in_stack_00000090);
                              (*pcVar5)(in_stack_00000048,lVar2,lVar1,pcVar6,uVar7);
                              uVar8 = (*pcVar10)(in_stack_00000048,in_stack_00000090,pcVar12,uVar8,
                                                 uVar4);
                              DAT_1804ae2e8 =
                                   (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))
                                             (in_stack_00000048,uVar8);
                              (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))
                                        (in_stack_00000048,uVar8);
                            }
                            uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804ae2e8,in_stack_000000b0);
                            uVar4 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                                              (in_stack_00000048,2,in_stack_000000a0,0);
                            pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0
                                               ,0x23ce);
                            (*pcVar10)(in_stack_00000048,uVar4,0,uVar7);
                            pcVar10 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
                            uVar7 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8
                                               ,lVar11 % 0x364df ^ in_stack_000000c0 ^
                                                0xe59ced57b93165d1);
                            (*pcVar10)(in_stack_00000048,uVar4,1,uVar7);
                            uVar8 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
                            (**(code **)(*(longlong *)in_stack_00000048 + 0x1a0))
                                      (in_stack_00000048,uVar8,in_stack_000000d8);
                            pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))
                                                        (in_stack_00000048);
                            if ((char)pcVar10 == '\0') {
                              pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720)
                                                )();
                              if ((char)pcVar10 == '\0') {
                                pcVar10 = (code *)(**(code **)(*(longlong *)in_stack_00000048 +
                                                              0x720))();
                                if ((char)pcVar10 == '\0') {
                                  if (DAT_1804ae2f0 != 0) {
                                    return (code *)0xbbbf092aca13f221;
                                  }
                                  if (DAT_1804b52e8 != (code *)0x0) {
                                    return DAT_1804b52e8;
                                  }
                                  return (code *)0x0;
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
  return pcVar10;
}

