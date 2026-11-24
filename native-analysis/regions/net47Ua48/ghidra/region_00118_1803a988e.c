/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=118 entry=1803a988e blocks=47 maxInstructions=50000 */

/* cut edge: 0x1803a988e -> 0x1803d7bfe */
/* cut edge: 0x1803a98ad -> 0x1803d7c46 */
/* cut edge: 0x1803a98bd -> 0x1803351d6 */
/* cut edge: 0x1803a98f8 -> 0x1803351d6 */
/* cut edge: 0x1803a9933 -> 0x1803351d6 */
/* cut edge: 0x1803a9adb -> 0x1803351d6 */
/* cut edge: 0x1803a9bb6 -> 0x1803351d6 */
/* cut edge: 0x1803a9bf1 -> 0x1803351d6 */
/* cut edge: 0x1803a9d98 -> 0x1803351d6 */
/* cut edge: 0x1803a9e73 -> 0x1803351d6 */
/* cut edge: 0x1803a9eae -> 0x1803351d6 */
/* cut edge: 0x1803a9ee9 -> 0x1803351d6 */
/* cut edge: 0x1803aa091 -> 0x1803351d6 */
/* cut edge: 0x1803aa16c -> 0x1803351d6 */
/* cut edge: 0x1803aa1a2 -> 0x1803d7d8c */
/* cut edge: 0x1803aa1c1 -> 0x1803351d6 */
/* cut edge: 0x1803aa1e5 -> 0x1803351d6 */
/* cut edge: 0x1803aa385 -> 0x1803d7e5d */
/* cut edge: 0x1803aa4f2 -> 0x1803351d6 */
/* cut edge: 0x1803aa51e -> 0x1803cecc3 */
/* cut edge: 0x1803aa556 -> 0x1803351d6 */
/* cut edge: 0x1803aa56c -> 0x180397314 */
/* cut edge: 0x1803aa585 -> 0x1803aaa01 */
/* cut edge: 0x1803aa592 -> 0x1803c9c3a */
/* cut edge: 0x1803aa5cc -> 0x1803d3336 */
/* cut edge: 0x1803aa5f6 -> 0x1803c9c8b */
/* cut edge: 0x1803aa612 -> 0x1803c7d0c */
/* cut edge: 0x1803aa63f -> 0x1803d3388 */
/* cut edge: 0x1803aa663 -> 0x1803d33be */
/* cut edge: 0x1803aa6c2 -> 0x1803d340f */
/* cut edge: 0x1803aa6d2 -> 0x1803d3448 */
/* cut edge: 0x1803aa7fc -> 0x1803cc738 */
/* cut edge: 0x1803aa7fc -> 0x1803aa874 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180397314 original byte 0x84 replaced by RET */
/* target 0x1803aa874 original byte 0x48 replaced by RET */
/* target 0x1803aaa01 original byte 0x48 replaced by RET */
/* target 0x1803c7d0c original byte 0x48 replaced by RET */
/* target 0x1803c9c3a original byte 0x48 replaced by RET */
/* target 0x1803c9c8b original byte 0x48 replaced by RET */
/* target 0x1803cc738 original byte 0x48 replaced by RET */
/* target 0x1803cecc3 original byte 0x48 replaced by RET */
/* target 0x1803d3336 original byte 0x48 replaced by RET */
/* target 0x1803d3388 original byte 0x48 replaced by RET */
/* target 0x1803d33be original byte 0x48 replaced by RET */
/* target 0x1803d340f original byte 0x48 replaced by RET */
/* target 0x1803d3448 original byte 0x48 replaced by RET */
/* target 0x1803d7bfe original byte 0x48 replaced by RET */
/* target 0x1803d7c46 original byte 0x48 replaced by RET */
/* target 0x1803d7d8c original byte 0x48 replaced by RET */
/* target 0x1803d7e5d original byte 0x48 replaced by RET */


void region_00118_1803a988e(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  longlong lVar6;
  code *pcVar7;
  char cVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 unaff_R15;
  bool bVar16;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000c8;
  code *pcStack00000000000000d0;
  longlong lStack00000000000000e0;
  undefined8 in_stack_000000e8;
  longlong in_stack_00000108;
  undefined8 in_stack_00000168;
  
  if (DAT_1804b5290 == (code *)0x0) {
    return;
  }
  if (DAT_1804b5298 == 0) {
    return;
  }
  pcStack00000000000000d0 = DAT_1804b5290;
  lStack00000000000000e0 = DAT_1804b5298;
  (**(code **)(*in_stack_00000048 + 0x390))(in_stack_00000048,DAT_1804b5290,DAT_1804b5298);
  cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar8 == '\0') {
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_000000e8);
    cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar8 == '\0') {
      cVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar8 == '\0') {
        if (DAT_1804b3118 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar10 = FUN_1803335a0(in_stack_00000048);
          pcVar7 = DAT_1804b59f0;
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000039a,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar12 = FUN_1800024f0(in_stack_00000048);
          uVar13 = FUN_180002550(in_stack_00000048);
          uVar14 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003570,0x16);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar15 = FUN_1800025a0(in_stack_00000048);
          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
          (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
          uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar10,pcVar7,uVar11);
          DAT_1804b3118 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
          unaff_R15 = in_stack_000000a8;
        }
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b3118,in_stack_00000078);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000060,in_stack_00000070,0x4b59);
        (*pcVar2)(in_stack_00000048,uVar11,0,uVar12);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                            in_stack_000000c8 ^ 0x77f9caee8efb510b);
        (*pcVar2)(in_stack_00000048,uVar11,1,uVar12);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
        cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar8 == '\0') {
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar9,unaff_R15,uVar10);
          cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar8 == '\0') {
            cVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar8 == '\0') {
              if (DAT_1804b3120 == 0) {
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar10 = FUN_1803335a0(in_stack_00000048);
                pcVar7 = DAT_1804b59f0;
                uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000398,1);
                lVar1 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar12 = FUN_1800024f0(in_stack_00000048);
                uVar13 = FUN_180002550(in_stack_00000048);
                uVar14 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003540,0x16);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar15 = FUN_1800025a0(in_stack_00000048);
                uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
                (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
                uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar10,pcVar7,uVar11);
                DAT_1804b3120 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                unaff_R15 = in_stack_000000a8;
              }
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b3120,in_stack_00000078);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                 (in_stack_00000048,2,in_stack_00000088,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000060,in_stack_00000070,0x30dd);
              (*pcVar2)(in_stack_00000048,uVar11,0,uVar12);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                  in_stack_000000c8 ^ 0x4413de42ddbf2af2);
              (*pcVar2)(in_stack_00000048,uVar11,1,uVar12);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
              cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar8 == '\0') {
                uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                   (in_stack_00000048,pcStack00000000000000d0,lStack00000000000000e0
                                    ,uVar10);
                cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar8 == '\0') {
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                    (in_stack_00000048,uVar9,unaff_R15,uVar10);
                  cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar8 == '\0') {
                    cVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
                    if (cVar8 == '\0') {
                      if (DAT_1804b3128 == 0) {
                        lVar1 = *in_stack_00000048;
                        pcStack00000000000000d0 = *(code **)(lVar1 + 0x390);
                        lStack00000000000000e0 = FUN_1803335a0(in_stack_00000048);
                        pcVar4 = DAT_1804b59f0;
                        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000396,1);
                        lVar1 = *in_stack_00000048;
                        pcVar2 = *(code **)(lVar1 + 0x390);
                        uVar11 = FUN_1800024f0(in_stack_00000048);
                        uVar12 = FUN_180002550(in_stack_00000048);
                        uVar13 = (**(code **)(lVar1 + 0x518))
                                           (in_stack_00000048,&stack0x00003510,0x16);
                        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                        uVar14 = FUN_1800025a0(in_stack_00000048);
                        uVar14 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar14);
                        (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                        uVar10 = (*pcStack00000000000000d0)
                                           (in_stack_00000048,in_stack_00000058,
                                            lStack00000000000000e0,pcVar4,uVar10);
                        DAT_1804b3128 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                        unaff_R15 = in_stack_000000a8;
                      }
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,DAT_1804b3128,in_stack_00000078);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                         (in_stack_00000048,2,in_stack_00000088,0);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000060,in_stack_00000070,
                                          0x2457);
                      (*pcVar2)(in_stack_00000048,uVar11,0,uVar12);
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                          in_stack_000000c8 ^ 0x591ae490f73f3e21);
                      (*pcVar2)(in_stack_00000048,uVar11,1,uVar12);
                      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
                      cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar8 == '\0') {
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar9,unaff_R15,uVar10);
                        cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if (cVar8 == '\0') {
                          if (DAT_1804b5040 == 0) {
                            return;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar9)
                          ;
                          cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar8 == '\0') {
                            cVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar8 == '\0') {
                              if (DAT_1804b3130 == 0) {
                                lVar1 = *in_stack_00000048;
                                pcVar2 = *(code **)(lVar1 + 0x390);
                                uVar10 = FUN_180333500(in_stack_00000048);
                                pcStack00000000000000d0 = DAT_1804b59f0;
                                lStack00000000000000e0 =
                                     (**(code **)(lVar1 + 0x518))
                                               (in_stack_00000048,&stack0x00000394,1);
                                lVar1 = *in_stack_00000048;
                                pcVar3 = *(code **)(lVar1 + 0x390);
                                uVar11 = FUN_1800024f0(in_stack_00000048);
                                uVar12 = FUN_180002550(in_stack_00000048);
                                uVar13 = (**(code **)(lVar1 + 0x518))
                                                   (in_stack_00000048,&stack0x00002b20,0x19);
                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                uVar14 = FUN_1800025a0(in_stack_00000048);
                                uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar14);
                                (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar14);
                                uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000058,uVar10,
                                                   pcStack00000000000000d0,lStack00000000000000e0);
                                DAT_1804b3130 =
                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                               (in_stack_00000048,uVar10);
                                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                              }
                              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                                 (in_stack_00000048,DAT_1804b3130,in_stack_00000078)
                              ;
                              uVar11 = (**(code **)(*in_stack_00000048 + 0x560))
                                                 (in_stack_00000048,5,in_stack_00000088,0);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                                 (in_stack_00000048,in_stack_00000098,
                                                  in_stack_00000090,in_stack_000000a0);
                              (*pcVar2)(in_stack_00000048,uVar11,0,uVar12);
                              (**(code **)(*in_stack_00000048 + 0x570))
                                        (in_stack_00000048,uVar11,1,uVar9);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000060,
                                                 in_stack_00000070,1);
                              (*pcVar2)(in_stack_00000048,uVar11,2,uVar9);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,0xdfb5e78a2633cad6);
                              (*pcVar2)(in_stack_00000048,uVar11,3,uVar9);
                              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                                (in_stack_00000048,in_stack_00000098,
                                                 in_stack_00000090,in_stack_000000c8);
                              (*pcVar2)(in_stack_00000048,uVar11,4,uVar9);
                              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                (in_stack_00000048,uVar10,in_stack_00000080,uVar11);
                              if (DAT_1804b4e00 == 0) {
                                return;
                              }
                              (**(code **)(*in_stack_00000048 + 0x128))(in_stack_00000048,uVar9);
                              cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                              if (cVar8 == '\0') {
                                if (in_stack_00000108 == 0) {
                                  bVar16 = false;
                                }
                                else {
                                  if (DAT_1804b5bd8 == 0) {
                                    return;
                                  }
                                  cVar8 = (**(code **)(*in_stack_00000048 + 0x100))
                                                    (in_stack_00000048,in_stack_00000108);
                                  bVar16 = cVar8 != '\0';
                                }
                                cVar8 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar8 == '\0') {
                                  if ((longlong)in_stack_000000c8 < 0) {
                                    return;
                                  }
                                  if (!bVar16) {
                                    return;
                                  }
                                  if (DAT_1804b5bd8 == 0) {
                                    return;
                                  }
                                  cVar8 = (**(code **)(*in_stack_00000048 + 0x100))
                                                    (in_stack_00000048,in_stack_00000108);
                                  if (cVar8 == '\0') {
                                    if (DAT_1804b4db0 == 0) {
                                      return;
                                    }
                                    (**(code **)(*in_stack_00000048 + 0x70))
                                              (in_stack_00000048,DAT_1804b4db0,L"");
                                  }
                                  cVar8 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar8 != '\0') {
                                    return;
                                  }
                                  cVar8 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  pcVar2 = DAT_1804b59f0;
                                  lVar1 = DAT_1804b59e8;
                                  if (cVar8 != '\0') {
                                    return;
                                  }
                                  if (DAT_1804b3138 == 0) {
                                    pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
                                    if (DAT_1804b59e8 == 0) {
                                      return;
                                    }
                                    uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                                      (in_stack_00000048,&stack0x00000392,1);
                                    lVar6 = DAT_1804b4d38;
                                    lVar5 = DAT_1804b4d30;
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                                    if (DAT_1804b4d30 == 0) {
                                      return;
                                    }
                                    if (DAT_1804b4d38 == 0) {
                                      return;
                                    }
                                    uVar10 = (**(code **)(*in_stack_00000048 + 0x518))
                                                       (in_stack_00000048,&stack0x00006840,0x2c);
                                    if (DAT_1804b4d40 == 0) {
                                      return;
                                    }
                                    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                                       (in_stack_00000048,in_stack_00000058);
                                    (*pcVar4)(in_stack_00000048,lVar5,lVar6,uVar10,uVar11);
                                    uVar9 = (*pcVar3)(in_stack_00000048,in_stack_00000058,lVar1,
                                                      pcVar2,uVar9);
                                    DAT_1804b3138 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar9);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar9);
                                  }
                                  (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,DAT_1804b3138,in_stack_00000078);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,7,in_stack_00000088,0);
                                  (**(code **)(*in_stack_00000048 + 0x570))
                                            (in_stack_00000048,uVar9,0,in_stack_00000168);
                                  if (DAT_1804b5388 != 0) {
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
        }
      }
    }
  }
  return;
}

