/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=27 entry=18003a124 blocks=34 maxInstructions=100000 */

/* cut edge: 0x18003a257 -> 0x18014d3db */
/* cut edge: 0x18003a359 -> 0x18002fccb */
/* cut edge: 0x18003a375 -> 0x18014d3db */
/* cut edge: 0x18003a4ce -> 0x18014d3db */
/* cut edge: 0x18003a5c7 -> 0x180146293 */
/* cut edge: 0x18003a5dc -> 0x18014d3db */
/* cut edge: 0x18003a732 -> 0x18014d3db */
/* cut edge: 0x18003a82b -> 0x18008d1a9 */
/* cut edge: 0x18003a838 -> 0x18014d3db */
/* cut edge: 0x18003a991 -> 0x18014d3db */
/* cut edge: 0x18003aa8a -> 0x1800eb502 */
/* cut edge: 0x18003aa97 -> 0x18014d3db */
/* cut edge: 0x18003abf0 -> 0x18014d3db */
/* cut edge: 0x18003ace9 -> 0x180142536 */
/* cut edge: 0x18003ad04 -> 0x18014d3db */
/* cut edge: 0x18003ae4f -> 0x18014d3db */
/* cut edge: 0x18003af51 -> 0x1800e21d1 */
/* cut edge: 0x18003af66 -> 0x18014d3db */
/* cut edge: 0x18003b0bf -> 0x18014d3db */
/* cut edge: 0x18003b0bf -> 0x18003b1b8 */
/* target 0x18002fccb original byte 0x48 replaced by RET */
/* target 0x18003b1b8 original byte 0x3b replaced by RET */
/* target 0x18008d1a9 original byte 0x48 replaced by RET */
/* target 0x1800e21d1 original byte 0x48 replaced by RET */
/* target 0x1800eb502 original byte 0x48 replaced by RET */
/* target 0x180142536 original byte 0x84 replaced by RET */
/* target 0x180146293 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00027_18003a124(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  int iVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000048;
  code *pcStack0000000000000058;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  int in_stack_000000e8;
  undefined8 in_stack_00000108;
  undefined8 in_stack_00005228;
  undefined8 in_stack_00005230;
  undefined8 in_stack_00005238;
  undefined8 in_stack_00005240;
  undefined8 in_stack_00005248;
  undefined8 in_stack_00005250;
  undefined8 in_stack_00005258;
  
  lVar1 = *in_stack_00000048;
  pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
  uVar6 = FUN_18001eb20(in_stack_00000048);
  uVar12 = DAT_1804b5208;
  uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000ae2,1);
  lVar1 = *in_stack_00000048;
  pcVar2 = *(code **)(lVar1 + 0x390);
  uVar8 = FUN_1800024f0(in_stack_00000048);
  uVar9 = FUN_180002550(in_stack_00000048);
  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004948,5);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
  uVar11 = FUN_1800025a0(in_stack_00000048);
  uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
  (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
  uVar12 = (*pcStack0000000000000058)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
  DAT_1804ac588 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804ac588,in_stack_000000b0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x296);
  (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005258);
  (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
  iVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000108);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    if (iVar5 == in_stack_000000e8) {
      return;
    }
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      if (DAT_1804ac590 == 0) {
        lVar1 = *in_stack_00000048;
        pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_18001eb20(in_stack_00000048);
        uVar12 = DAT_1804b5208;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000ae0,1);
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004938,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcStack0000000000000058)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
        DAT_1804ac590 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804ac590,in_stack_000000b0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x879);
      (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005250);
      (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
      iVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000108);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        if (iVar5 == in_stack_000000e8) {
          return;
        }
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          if (DAT_1804ac598 == 0) {
            lVar1 = *in_stack_00000048;
            pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
            uVar6 = FUN_18001eb20(in_stack_00000048);
            uVar12 = DAT_1804b5208;
            uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000ade,1);
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004928,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
            (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar12 = (*pcStack0000000000000058)
                               (in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
            DAT_1804ac598 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804ac598,in_stack_000000b0);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5f6c);
          (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005248
                            );
          (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
          iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar12,in_stack_00000108);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            if (iVar5 == in_stack_000000e8) {
              return;
            }
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar4 == '\0') {
              if (DAT_1804ac5a0 == 0) {
                lVar1 = *in_stack_00000048;
                pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
                uVar6 = FUN_18001eb20(in_stack_00000048);
                uVar12 = DAT_1804b5208;
                uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000adc,1);
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000048);
                uVar9 = FUN_180002550(in_stack_00000048);
                uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004918,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000048);
                uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                uVar12 = (*pcStack0000000000000058)
                                   (in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
                DAT_1804ac5a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
              }
              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804ac5a0,in_stack_000000b0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7ba);
              (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_00005240);
              (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
              iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar12,in_stack_00000108);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                if (iVar5 == in_stack_000000e8) {
                  return;
                }
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar4 == '\0') {
                  if (DAT_1804ac5a8 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
                    uVar6 = FUN_18001eb20(in_stack_00000048);
                    uVar12 = DAT_1804b5208;
                    uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000ada,1);
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004908,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                    (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar12 = (*pcStack0000000000000058)
                                       (in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
                    DAT_1804ac5a8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                  }
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804ac5a8,in_stack_000000b0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4735);
                  (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     in_stack_00005238);
                  (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                  iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar12,in_stack_00000108);
                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar4 == '\0') {
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (iVar5 == in_stack_000000e8) {
                      return;
                    }
                    if (cVar4 == '\0') {
                      if (DAT_1804ac5b0 == 0) {
                        lVar1 = *in_stack_00000048;
                        pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
                        uVar6 = FUN_18001eb20(in_stack_00000048);
                        uVar12 = DAT_1804b5208;
                        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000ad8,1);
                        lVar1 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar1 + 0x390);
                        uVar8 = FUN_1800024f0(in_stack_00000048);
                        uVar9 = FUN_180002550(in_stack_00000048);
                        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000048f8,5);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar11 = FUN_1800025a0(in_stack_00000048);
                        uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                        (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                        uVar12 = (*pcStack0000000000000058)
                                           (in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
                        DAT_1804ac5b0 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                      }
                      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,DAT_1804ac5b0,in_stack_000000b0);
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_000000a0,0);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                         0x14d4);
                      (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                         in_stack_00005230);
                      (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                      iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                        (in_stack_00000048,uVar12,in_stack_00000108);
                      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar4 == '\0') {
                        if (iVar5 == in_stack_000000e8) {
                          return;
                        }
                        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar4 == '\0') {
                          if (DAT_1804ac5b8 == 0) {
                            lVar1 = *in_stack_00000048;
                            pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
                            uVar6 = FUN_18001eb20(in_stack_00000048);
                            uVar12 = DAT_1804b5208;
                            uVar7 = (**(code **)(lVar1 + 0x518))
                                              (in_stack_00000048,&stack0x00000ad6,1);
                            lVar1 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar1 + 0x390);
                            uVar8 = FUN_1800024f0(in_stack_00000048);
                            uVar9 = FUN_180002550(in_stack_00000048);
                            uVar10 = (**(code **)(lVar1 + 0x518))
                                               (in_stack_00000048,&stack0x000048e8,5);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar11 = FUN_1800025a0(in_stack_00000048);
                            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
                            (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                            uVar12 = (*pcStack0000000000000058)
                                               (in_stack_00000048,in_stack_00000090,uVar6,uVar12,
                                                uVar7);
                            DAT_1804ac5b8 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                          }
                          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,DAT_1804ac5b8,in_stack_000000b0);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x6c79);
                          (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             in_stack_00005228);
                          (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                             (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                          (**(code **)(*in_stack_00000048 + 0x188))
                                    (in_stack_00000048,uVar12,in_stack_00000108);
                          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar4 == '\0') {
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
  return;
}

