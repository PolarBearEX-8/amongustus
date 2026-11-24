/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=145 entry=1800b0125 blocks=50 maxInstructions=100000 */

/* cut edge: 0x1800b01ad -> 0x18014d3db */
/* cut edge: 0x1800b02a2 -> 0x18014d3db */
/* cut edge: 0x1800b02b8 -> 0x18014d3db */
/* cut edge: 0x1800b0326 -> 0x1800e06ee */
/* cut edge: 0x1800b034f -> 0x1800e0726 */
/* cut edge: 0x1800b03ab -> 0x1800e0777 */
/* cut edge: 0x1800b03c3 -> 0x1800e07b0 */
/* cut edge: 0x1800b04a3 -> 0x18014d3db */
/* cut edge: 0x1800b0598 -> 0x18014d3db */
/* cut edge: 0x1800b05ae -> 0x18014d3db */
/* cut edge: 0x1800b061c -> 0x1800e08dc */
/* cut edge: 0x1800b0640 -> 0x1800e090f */
/* cut edge: 0x1800b06a6 -> 0x1800e095d */
/* cut edge: 0x1800b06be -> 0x1800e0997 */
/* cut edge: 0x1800b07a0 -> 0x18014d3db */
/* cut edge: 0x1800b0897 -> 0x1800b73be */
/* cut edge: 0x1800b08b3 -> 0x18014d3db */
/* cut edge: 0x1800b08c9 -> 0x18014d3db */
/* cut edge: 0x1800b0931 -> 0x1800dea42 */
/* cut edge: 0x1800b0955 -> 0x1800dea75 */
/* cut edge: 0x1800b09b6 -> 0x1800deac6 */
/* cut edge: 0x1800b09c6 -> 0x1800deaff */
/* cut edge: 0x1800b0a9e -> 0x18014d3db */
/* cut edge: 0x1800b0b95 -> 0x18014d3db */
/* cut edge: 0x1800b0bab -> 0x18014d3db */
/* cut edge: 0x1800b0c25 -> 0x1800dec2b */
/* cut edge: 0x1800b0c4c -> 0x1800dec5e */
/* cut edge: 0x1800b0cad -> 0x1800decaf */
/* cut edge: 0x1800b0cbd -> 0x1800dece9 */
/* cut edge: 0x1800b0d9b -> 0x18014d3db */
/* cut edge: 0x1800b0e90 -> 0x18014d3db */
/* cut edge: 0x1800b0ea6 -> 0x18014d3db */
/* cut edge: 0x1800b0f13 -> 0x1800dee11 */
/* cut edge: 0x1800b0f3a -> 0x1800dee44 */
/* cut edge: 0x1800b0f9b -> 0x1800dee95 */
/* cut edge: 0x1800b0fab -> 0x1800deece */
/* cut edge: 0x1800b1086 -> 0x18014d3db */
/* cut edge: 0x1800b1086 -> 0x1800b117d */
/* target 0x1800b117d original byte 0x48 replaced by RET */
/* target 0x1800b73be original byte 0x4c replaced by RET */
/* target 0x1800dea42 original byte 0x48 replaced by RET */
/* target 0x1800dea75 original byte 0x48 replaced by RET */
/* target 0x1800deac6 original byte 0x48 replaced by RET */
/* target 0x1800deaff original byte 0x48 replaced by RET */
/* target 0x1800dec2b original byte 0x48 replaced by RET */
/* target 0x1800dec5e original byte 0x48 replaced by RET */
/* target 0x1800decaf original byte 0x48 replaced by RET */
/* target 0x1800dece9 original byte 0x48 replaced by RET */
/* target 0x1800dee11 original byte 0x48 replaced by RET */
/* target 0x1800dee44 original byte 0x48 replaced by RET */
/* target 0x1800dee95 original byte 0x48 replaced by RET */
/* target 0x1800deece original byte 0x48 replaced by RET */
/* target 0x1800e06ee original byte 0x48 replaced by RET */
/* target 0x1800e0726 original byte 0x48 replaced by RET */
/* target 0x1800e0777 original byte 0x48 replaced by RET */
/* target 0x1800e07b0 original byte 0x48 replaced by RET */
/* target 0x1800e08dc original byte 0x48 replaced by RET */
/* target 0x1800e090f original byte 0x48 replaced by RET */
/* target 0x1800e095d original byte 0x48 replaced by RET */
/* target 0x1800e0997 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00145_1800b0125(void)

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
  code *unaff_R15;
  longlong *in_stack_00000048;
  code *in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  ulonglong in_stack_00000078;
  code *in_stack_00000088;
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
  
  (*unaff_R15)(in_stack_00000048,in_stack_00000090);
  (*in_stack_00000088)(in_stack_00000048);
  uVar7 = (*in_stack_00000058)
                    (in_stack_00000048,in_stack_00000090,in_stack_00000068,in_stack_00000060,
                     in_stack_00000070);
  DAT_1804ae0a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804ae0a0,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1920);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar12 = DAT_1804b52e8;
      uVar7 = DAT_1804b5208;
      if (cVar6 == '\0') {
        if (DAT_1804ae0a8 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000402,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001c38,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar7,uVar8);
          DAT_1804ae0a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae0a8,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5e55);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000078 % 0x36134 ^ in_stack_000000c0 ^
                           0x457bcf25919ec0f7);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))
                           (in_stack_00000048,uVar7,in_stack_000000d8);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            lVar12 = DAT_1804b52e8;
            uVar7 = DAT_1804b5208;
            if (cVar6 == '\0') {
              if (DAT_1804ae0b0 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000400,1);
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
                                  (in_stack_00000048,&stack0x00001c28,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000090);
                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar12,uVar7,uVar8);
                DAT_1804ae0b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae0b0,in_stack_000000b0);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1316);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000078 % 0xd2ea ^ in_stack_000000c0 ^
                                 0x1f5185051bbc3435);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
              lVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                 (in_stack_00000048,uVar7,in_stack_000000d8);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                lVar12 = lVar12 + (uVar11 ^ in_stack_00000078);
                if (in_stack_000000f8 == 0) {
                  return;
                }
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
                  lVar3 = DAT_1804b52e8;
                  uVar7 = DAT_1804b5208;
                  if (cVar6 == '\0') {
                    if (DAT_1804ae0b8 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b52e8 == 0) {
                        return;
                      }
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x000003fe,1);
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
                                        (in_stack_00000048,&stack0x00001c18,5);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,in_stack_00000090);
                      (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
                      uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar7,uVar8);
                      DAT_1804ae0b8 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                    }
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ae0b8,in_stack_000000b0);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0xcbe);
                    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       lVar12 % 0xcceb8 ^ in_stack_000000c0 ^ 0x5fea17ad34c2a1b3);
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
                          if (DAT_1804ae0c0 == 0) {
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                            if (DAT_1804b52e8 == 0) {
                              return;
                            }
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                              (in_stack_00000048,&stack0x000003fc,1);
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
                                              (in_stack_00000048,&stack0x00001c08,5);
                            if (DAT_1804b4d40 == 0) {
                              return;
                            }
                            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,in_stack_00000090);
                            (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
                            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar7,uVar8)
                            ;
                            DAT_1804ae0c0 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                          }
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804ae0c0,in_stack_000000b0);
                          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_000000a0,0);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                             0x377c);
                          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                             lVar12 % 0xb44f3 ^ in_stack_000000c0 ^
                                             0x3abe246bfad7db6e);
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
                                if (DAT_1804ae0c8 == 0) {
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                                  if (DAT_1804b52e8 == 0) {
                                    return;
                                  }
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                                    (in_stack_00000048,&stack0x000003fa,1);
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
                                                    (in_stack_00000048,&stack0x00001bf8,5);
                                  if (DAT_1804b4d40 == 0) {
                                    return;
                                  }
                                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                     (in_stack_00000048,in_stack_00000090);
                                  (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
                                  uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar7,
                                                    uVar8);
                                  DAT_1804ae0c8 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar7);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                                }
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,DAT_1804ae0c8,in_stack_000000b0
                                                  );
                                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x775d);
                                (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   lVar12 % 0x8a364 ^ in_stack_000000c0 ^
                                                   0x22efc59bf6e45769);
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

