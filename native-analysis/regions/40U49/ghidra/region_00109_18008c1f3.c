/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=109 entry=18008c1f3 blocks=45 maxInstructions=100000 */

/* cut edge: 0x18008c1f3 -> 0x18014d3db */
/* cut edge: 0x18008c209 -> 0x18014d3db */
/* cut edge: 0x18008c26d -> 0x1800a8f13 */
/* cut edge: 0x18008c291 -> 0x1800a8f46 */
/* cut edge: 0x18008c2f5 -> 0x1800a8f97 */
/* cut edge: 0x18008c308 -> 0x1800a8fd1 */
/* cut edge: 0x18008c402 -> 0x18014d3db */
/* cut edge: 0x18008c5b8 -> 0x18014d3db */
/* cut edge: 0x18008c5ce -> 0x18014d3db */
/* cut edge: 0x18008c639 -> 0x1800a9008 */
/* cut edge: 0x18008c65d -> 0x1800a903b */
/* cut edge: 0x18008c6be -> 0x1800a908c */
/* cut edge: 0x18008c6ce -> 0x1800a90c5 */
/* cut edge: 0x18008c7a6 -> 0x18014d3db */
/* cut edge: 0x18008c89d -> 0x18014d3db */
/* cut edge: 0x18008c8b3 -> 0x18014d3db */
/* cut edge: 0x18008c930 -> 0x1800a90fc */
/* cut edge: 0x18008c957 -> 0x1800a912f */
/* cut edge: 0x18008c9b8 -> 0x1800a9180 */
/* cut edge: 0x18008c9c8 -> 0x1800a91ba */
/* cut edge: 0x18008caa6 -> 0x18014d3db */
/* cut edge: 0x18008cb9b -> 0x18014d3db */
/* cut edge: 0x18008cbb1 -> 0x18014d3db */
/* cut edge: 0x18008cc1e -> 0x1800a91f1 */
/* cut edge: 0x18008cc45 -> 0x1800a9224 */
/* cut edge: 0x18008cca6 -> 0x1800a9275 */
/* cut edge: 0x18008ccb6 -> 0x1800a92ae */
/* cut edge: 0x18008cd91 -> 0x18014d3db */
/* cut edge: 0x18008ce86 -> 0x18014d3db */
/* cut edge: 0x18008ce9c -> 0x18014d3db */
/* cut edge: 0x18008cf07 -> 0x1800a9306 */
/* cut edge: 0x18008cf2b -> 0x1800a9339 */
/* cut edge: 0x18008cf8c -> 0x1800a9387 */
/* cut edge: 0x18008cfa4 -> 0x1800a93c0 */
/* cut edge: 0x18008d081 -> 0x18014d3db */
/* cut edge: 0x18008d081 -> 0x18008d176 */
/* target 0x18008d176 original byte 0x4c replaced by RET */
/* target 0x1800a8f13 original byte 0x48 replaced by RET */
/* target 0x1800a8f46 original byte 0x48 replaced by RET */
/* target 0x1800a8f97 original byte 0x48 replaced by RET */
/* target 0x1800a8fd1 original byte 0x48 replaced by RET */
/* target 0x1800a9008 original byte 0x48 replaced by RET */
/* target 0x1800a903b original byte 0x48 replaced by RET */
/* target 0x1800a908c original byte 0x48 replaced by RET */
/* target 0x1800a90c5 original byte 0x48 replaced by RET */
/* target 0x1800a90fc original byte 0x48 replaced by RET */
/* target 0x1800a912f original byte 0x48 replaced by RET */
/* target 0x1800a9180 original byte 0x48 replaced by RET */
/* target 0x1800a91ba original byte 0x48 replaced by RET */
/* target 0x1800a91f1 original byte 0x48 replaced by RET */
/* target 0x1800a9224 original byte 0x48 replaced by RET */
/* target 0x1800a9275 original byte 0x48 replaced by RET */
/* target 0x1800a92ae original byte 0x48 replaced by RET */
/* target 0x1800a9306 original byte 0x48 replaced by RET */
/* target 0x1800a9339 original byte 0x48 replaced by RET */
/* target 0x1800a9387 original byte 0x48 replaced by RET */
/* target 0x1800a93c0 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00109_18008c1f3(void)

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
  undefined4 unaff_ESI;
  undefined4 unaff_R14D;
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
  undefined8 in_stack_00000108;
  undefined8 in_stack_000001a0;
  undefined8 in_stack_00000c90;
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    uVar10 = DAT_1804b5208;
    lVar5 = DAT_1804b5200;
    if (cVar6 == '\0') {
      if (DAT_1804ada60 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5200 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000596,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00005f30,0x1a);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7);
        DAT_1804ada60 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804ada60,in_stack_000000b0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,6,in_stack_000000a0,0);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_000001a0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,unaff_R14D);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00000c90);
      (*pcVar1)(in_stack_00000048,uVar7,2,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,unaff_ESI);
      (*pcVar1)(in_stack_00000048,uVar7,3,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         in_stack_00000078 % 0x53a11 ^ 0xe9e630ee3347814b);
      (*pcVar1)(in_stack_00000048,uVar7,4,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
      (*pcVar1)(in_stack_00000048,uVar7,5,uVar8);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000108);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          lVar5 = DAT_1804b52e8;
          uVar10 = DAT_1804b5208;
          if (cVar6 == '\0') {
            if (DAT_1804ada68 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b52e8 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000594,1);
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
                                (in_stack_00000048,&stack0x00002718,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090)
              ;
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7);
              DAT_1804ada68 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804ada68,in_stack_000000b0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x372a);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               in_stack_00000078 % 0x4490c ^ in_stack_000000c0 ^ 0xbe708b17ce6c5efe)
            ;
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
                  if (DAT_1804ada70 == 0) {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b52e8 == 0) {
                      return;
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000592,1);
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
                                      (in_stack_00000048,&stack0x00002708,5);
                    if (DAT_1804b4d40 == 0) {
                      return;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,in_stack_00000090);
                    (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
                    uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7);
                    DAT_1804ada70 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804ada70,in_stack_000000b0);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7539);
                  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_00000078 % 0xb7583 ^ in_stack_000000c0 ^
                                     0xd83d9717bc2c60eb);
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
                        if (DAT_1804ada78 == 0) {
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                          if (DAT_1804b52e8 == 0) {
                            return;
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                            (in_stack_00000048,&stack0x00000590,1);
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
                                            (in_stack_00000048,&stack0x000026f8,5);
                          if (DAT_1804b4d40 == 0) {
                            return;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000090);
                          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
                          uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,uVar7)
                          ;
                          DAT_1804ada78 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804ada78,in_stack_000000b0);
                        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x4fc4);
                        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           in_stack_00000078 % 0x905f4 ^ in_stack_000000c0 ^
                                           0x813542960ae4788b);
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
                              if (DAT_1804ada80 == 0) {
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                if (DAT_1804b52e8 == 0) {
                                  return;
                                }
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                                  (in_stack_00000048,&stack0x0000058e,1);
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
                                                  (in_stack_00000048,&stack0x000026e8,5);
                                if (DAT_1804b4d40 == 0) {
                                  return;
                                }
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,in_stack_00000090);
                                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
                                uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar10,
                                                   uVar7);
                                DAT_1804ada80 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar10);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                              }
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804ada80,in_stack_000000b0)
                              ;
                              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                                (in_stack_00000048,2,in_stack_000000a0,0);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_000000c8,
                                                 in_stack_000000d0,0x3051);
                              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_000000b8,
                                                 in_stack_00000078 % 0xb77b8 ^ in_stack_000000c0 ^
                                                 0xfdfdffce375d3dee);
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
      }
    }
  }
  return;
}

