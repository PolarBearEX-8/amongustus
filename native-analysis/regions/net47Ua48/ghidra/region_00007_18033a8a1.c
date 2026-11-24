/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=7 entry=18033a8a1 blocks=46 maxInstructions=50000 */

/* cut edge: 0x18033a8a1 -> 0x1803351d6 */
/* cut edge: 0x18033a9ea -> 0x1803351d6 */
/* cut edge: 0x18033aa10 -> 0x180347244 */
/* cut edge: 0x18033aa3f -> 0x18034727a */
/* cut edge: 0x18033aaa1 -> 0x1803472cb */
/* cut edge: 0x18033aab1 -> 0x180347304 */
/* cut edge: 0x18033ab96 -> 0x1803351d6 */
/* cut edge: 0x18033ac88 -> 0x1803351d6 */
/* cut edge: 0x18033acae -> 0x18034733b */
/* cut edge: 0x18033acdd -> 0x180347371 */
/* cut edge: 0x18033ad3f -> 0x1803473c2 */
/* cut edge: 0x18033ad4f -> 0x1803473fc */
/* cut edge: 0x18033ae37 -> 0x1803351d6 */
/* cut edge: 0x18033af24 -> 0x1803351d6 */
/* cut edge: 0x18033af62 -> 0x180347433 */
/* cut edge: 0x18033af89 -> 0x180347469 */
/* cut edge: 0x18033afeb -> 0x1803474ba */
/* cut edge: 0x18033affb -> 0x1803474f3 */
/* cut edge: 0x18033b0ea -> 0x1803351d6 */
/* cut edge: 0x18033b233 -> 0x1803351d6 */
/* cut edge: 0x18033b259 -> 0x18034752a */
/* cut edge: 0x18033b288 -> 0x180347560 */
/* cut edge: 0x18033b2ea -> 0x1803475b1 */
/* cut edge: 0x18033b2fa -> 0x1803475ea */
/* cut edge: 0x18033b3df -> 0x1803351d6 */
/* cut edge: 0x18033b4cd -> 0x1803351d6 */
/* cut edge: 0x18033b4f3 -> 0x180347621 */
/* cut edge: 0x18033b522 -> 0x180347657 */
/* cut edge: 0x18033b587 -> 0x1803476a8 */
/* cut edge: 0x18033b597 -> 0x1803476e1 */
/* cut edge: 0x18033b67e -> 0x1803351d6 */
/* cut edge: 0x18033b76b -> 0x1803351d6 */
/* cut edge: 0x18033b78d -> 0x18033b94b */
/* cut edge: 0x18033b7a5 -> 0x180347718 */
/* cut edge: 0x18033b7dc -> 0x180347753 */
/* cut edge: 0x18033b7dc -> 0x18033b83e */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18033b83e original byte 0x48 replaced by RET */
/* target 0x18033b94b original byte 0x48 replaced by RET */
/* target 0x180347244 original byte 0x48 replaced by RET */
/* target 0x18034727a original byte 0x48 replaced by RET */
/* target 0x1803472cb original byte 0x48 replaced by RET */
/* target 0x180347304 original byte 0x48 replaced by RET */
/* target 0x18034733b original byte 0x48 replaced by RET */
/* target 0x180347371 original byte 0x48 replaced by RET */
/* target 0x1803473c2 original byte 0x48 replaced by RET */
/* target 0x1803473fc original byte 0x48 replaced by RET */
/* target 0x180347433 original byte 0x48 replaced by RET */
/* target 0x180347469 original byte 0x48 replaced by RET */
/* target 0x1803474ba original byte 0x48 replaced by RET */
/* target 0x1803474f3 original byte 0x48 replaced by RET */
/* target 0x18034752a original byte 0x48 replaced by RET */
/* target 0x180347560 original byte 0x48 replaced by RET */
/* target 0x1803475b1 original byte 0x48 replaced by RET */
/* target 0x1803475ea original byte 0x48 replaced by RET */
/* target 0x180347621 original byte 0x48 replaced by RET */
/* target 0x180347657 original byte 0x48 replaced by RET */
/* target 0x1803476a8 original byte 0x48 replaced by RET */
/* target 0x1803476e1 original byte 0x48 replaced by RET */
/* target 0x180347718 original byte 0x48 replaced by RET */
/* target 0x180347753 original byte 0x48 replaced by RET */


void region_00007_18033a8a1(undefined8 param_1,undefined8 param_2)

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
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_00000158;
  undefined8 in_stack_000001e0;
  undefined8 in_stack_000001e8;
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_000000a0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001e8);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfd1138581807c67);
  (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar8,3,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar8)
  ;
  (**(code **)(*in_stack_00000048 + 0x140))(in_stack_00000048,uVar7,in_stack_000001e0);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar5 = DAT_1804b59f8;
    uVar7 = DAT_1804b59f0;
    if (cVar6 == '\0') {
      if (DAT_1804b2068 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b59f8 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000007d4,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002188,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar7,uVar8);
        DAT_1804b2068 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b2068,in_stack_00000078);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
      (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        lVar5 = DAT_1804b59f8;
        uVar7 = DAT_1804b59f0;
        if (cVar6 == '\0') {
          if (DAT_1804b2070 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59f8 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000007d2,1);
            lVar4 = DAT_1804b4d38;
            lVar3 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002178,5);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar7,uVar8);
            DAT_1804b2070 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b2070,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
          (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar7,in_stack_00000070);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            uVar7 = DAT_1804b59f0;
            lVar5 = DAT_1804b59e8;
            if (cVar6 == '\0') {
              if (DAT_1804b2078 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b59e8 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x000007d0,1);
                lVar4 = DAT_1804b4d38;
                lVar3 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x000064e0,0x18);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000058);
                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar7,uVar8);
                DAT_1804b2078 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b2078,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,4,in_stack_00000088,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_000000a0)
              ;
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000001e8);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 0xdfd1138581807c67);
              (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar8,3,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
              (**(code **)(*in_stack_00000048 + 0x140))(in_stack_00000048,uVar7,in_stack_000001e0);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                lVar5 = DAT_1804b59f8;
                uVar7 = DAT_1804b59f0;
                if (cVar6 == '\0') {
                  if (DAT_1804b2080 == 0) {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b59f8 == 0) {
                      return;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x000007ce,1);
                    lVar4 = DAT_1804b4d38;
                    lVar3 = DAT_1804b4d30;
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b4d30 == 0) {
                      return;
                    }
                    if (DAT_1804b4d38 == 0) {
                      return;
                    }
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00002168,5);
                    if (DAT_1804b4d40 == 0) {
                      return;
                    }
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,in_stack_00000058);
                    (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                    uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar7,uVar8);
                    DAT_1804b2080 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                  }
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,DAT_1804b2080,in_stack_00000078);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000088,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
                  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000a8);
                  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                  (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar7,in_stack_00000070);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 == '\0') {
                    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                    lVar5 = DAT_1804b59f8;
                    uVar7 = DAT_1804b59f0;
                    if (cVar6 == '\0') {
                      if (DAT_1804b2088 == 0) {
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                        if (DAT_1804b59f8 == 0) {
                          return;
                        }
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                          (in_stack_00000048,&stack0x000007cc,1);
                        lVar4 = DAT_1804b4d38;
                        lVar3 = DAT_1804b4d30;
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                        if (DAT_1804b4d30 == 0) {
                          return;
                        }
                        if (DAT_1804b4d38 == 0) {
                          return;
                        }
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                          (in_stack_00000048,&stack0x00002158,5);
                        if (DAT_1804b4d40 == 0) {
                          return;
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,in_stack_00000058);
                        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                        uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar7,uVar8);
                        DAT_1804b2088 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                      }
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,DAT_1804b2088,in_stack_00000078);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,
                                         0x1193);
                      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_00000158);
                      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
                      (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar7,in_stack_00000070);
                      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar6 == '\0') {
                        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar6 == '\0') {
                          if (DAT_1804b2090 != 0) {
                            return;
                          }
                          if (DAT_1804b59e8 != 0) {
                            (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x000007ca,1);
                            if (DAT_1804b4d30 != 0) {
                              return;
                            }
                            return;
                          }
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

