/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=0 entry=180298d70 blocks=35 maxInstructions=50000 */

/* cut edge: 0x180298d70 -> 0x18033319a */
/* cut edge: 0x180298dc4 -> 0x1803331dc */
/* cut edge: 0x180298dd4 -> 0x180333186 */
/* cut edge: 0x180298dfd -> 0x180333186 */
/* cut edge: 0x180298e23 -> 0x180333186 */
/* cut edge: 0x180298e46 -> 0x180333186 */
/* cut edge: 0x180298e69 -> 0x180333186 */
/* cut edge: 0x180298e8c -> 0x180333186 */
/* cut edge: 0x180298eaf -> 0x180333186 */
/* cut edge: 0x180298ed2 -> 0x180333186 */
/* cut edge: 0x180298ef5 -> 0x180333186 */
/* cut edge: 0x180298f18 -> 0x180333186 */
/* cut edge: 0x180298f3b -> 0x180333186 */
/* cut edge: 0x180298f61 -> 0x1803333a7 */
/* cut edge: 0x180298f88 -> 0x1803333dd */
/* cut edge: 0x180298fea -> 0x18033342e */
/* cut edge: 0x180298ffa -> 0x180333467 */
/* cut edge: 0x1802990ea -> 0x180333215 */
/* cut edge: 0x180299111 -> 0x180333250 */
/* cut edge: 0x18029914d -> 0x180333292 */
/* cut edge: 0x180299193 -> 0x1803332d4 */
/* cut edge: 0x1802991ab -> 0x180333312 */
/* cut edge: 0x18029923e -> 0x180333186 */
/* cut edge: 0x180299273 -> 0x180333186 */
/* cut edge: 0x180299bf2 -> 0x180332b93 */
/* cut edge: 0x180299c21 -> 0x180332bce */
/* cut edge: 0x180299c6e -> 0x180332c20 */
/* cut edge: 0x180299c8b -> 0x180332c59 */
/* cut edge: 0x180299d69 -> 0x180332a7f */
/* cut edge: 0x180299d69 -> 0x180299dcf */
/* target 0x180299dcf original byte 0x48 replaced by RET */
/* target 0x180332a7f original byte 0x4c replaced by RET */
/* target 0x180332b93 original byte 0x48 replaced by RET */
/* target 0x180332bce original byte 0x48 replaced by RET */
/* target 0x180332c20 original byte 0x48 replaced by RET */
/* target 0x180332c59 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */
/* target 0x18033319a original byte 0x48 replaced by RET */
/* target 0x1803331dc original byte 0x48 replaced by RET */
/* target 0x180333215 original byte 0x48 replaced by RET */
/* target 0x180333250 original byte 0x48 replaced by RET */
/* target 0x180333292 original byte 0x48 replaced by RET */
/* target 0x1803332d4 original byte 0x48 replaced by RET */
/* target 0x180333312 original byte 0x4c replaced by RET */
/* target 0x1803333a7 original byte 0x48 replaced by RET */
/* target 0x1803333dd original byte 0x48 replaced by RET */
/* target 0x18033342e original byte 0x48 replaced by RET */
/* target 0x180333467 original byte 0x48 replaced by RET */


void region_00000_180298d70(longlong *param_1,undefined8 param_2,ulonglong param_3)

{
  code *pcVar1;
  longlong *plVar2;
  longlong lVar3;
  longlong lVar4;
  code *pcVar5;
  longlong lVar6;
  longlong lVar7;
  char cVar8;
  longlong lVar9;
  ulonglong uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  ulonglong auStackX_10 [3];
  undefined8 uStack_48;
  
  uStack_48 = 0x180298d86;
  lVar9 = FUN_180492640();
  lVar9 = -lVar9;
  *(undefined8 *)(&stack0x00000110 + lVar9) = param_2;
  *(undefined8 *)((longlong)auStackX_10 + lVar9) = 0;
  *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) = param_1;
  pcVar1 = *(code **)(*param_1 + 0x4b8);
  *(longlong *)(&stack0x00000040 + lVar9) = DAT_1804b59d8;
  if (DAT_1804b59d8 == 0) {
    return;
  }
  if (DAT_1804b59e0 == 0) {
    return;
  }
  plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
  *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298de6;
  uVar10 = (*pcVar1)(plVar2,*(undefined8 *)(&stack0x00000040 + lVar9));
  pcVar1 = *(code **)(*plVar2 + 0x720);
  *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298df5;
  cVar8 = (*pcVar1)(plVar2);
  if (cVar8 == '\0') {
    *(ulonglong *)(&stack0x00000070 + lVar9) = uVar10 ^ param_3;
    *(ulonglong *)((longlong)auStackX_10 + lVar9) = uVar10 ^ param_3;
    pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) + 0x720);
    *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298e1b;
    cVar8 = (*pcVar1)();
    if (cVar8 == '\0') {
      *(undefined8 *)((longlong)auStackX_10 + lVar9) = *(undefined8 *)(&stack0x00000070 + lVar9);
      pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) + 0x720);
      *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298e3e;
      cVar8 = (*pcVar1)();
      if (cVar8 == '\0') {
        *(undefined8 *)((longlong)auStackX_10 + lVar9) = *(undefined8 *)(&stack0x00000070 + lVar9);
        pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) + 0x720);
        *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298e61;
        cVar8 = (*pcVar1)();
        if (cVar8 == '\0') {
          *(undefined8 *)((longlong)auStackX_10 + lVar9) = *(undefined8 *)(&stack0x00000070 + lVar9)
          ;
          pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) + 0x720);
          *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298e84;
          cVar8 = (*pcVar1)();
          if (cVar8 == '\0') {
            *(undefined8 *)((longlong)auStackX_10 + lVar9) =
                 *(undefined8 *)(&stack0x00000070 + lVar9);
            pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) + 0x720);
            *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298ea7;
            cVar8 = (*pcVar1)();
            if (cVar8 == '\0') {
              *(undefined8 *)((longlong)auStackX_10 + lVar9) =
                   *(undefined8 *)(&stack0x00000070 + lVar9);
              pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) + 0x720);
              *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298eca;
              cVar8 = (*pcVar1)();
              if (cVar8 == '\0') {
                *(undefined8 *)((longlong)auStackX_10 + lVar9) =
                     *(undefined8 *)(&stack0x00000070 + lVar9);
                pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) + 0x720);
                *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298eed;
                cVar8 = (*pcVar1)();
                if (cVar8 == '\0') {
                  *(undefined8 *)((longlong)auStackX_10 + lVar9) =
                       *(undefined8 *)(&stack0x00000070 + lVar9);
                  pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) + 0x720
                                     );
                  *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298f10;
                  cVar8 = (*pcVar1)();
                  if (cVar8 == '\0') {
                    *(undefined8 *)((longlong)auStackX_10 + lVar9) =
                         *(undefined8 *)(&stack0x00000070 + lVar9);
                    pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) +
                                       0x720);
                    *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298f33;
                    cVar8 = (*pcVar1)();
                    if (cVar8 == '\0') {
                      pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) +
                                         0x720);
                      *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298f49;
                      cVar8 = (*pcVar1)();
                      lVar4 = DAT_1804b59e8;
                      if (cVar8 == '\0') {
                        if (DAT_1804b09b0 == 0) {
                          lVar3 = **(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                          *(undefined8 *)(&stack0x00000060 + lVar9) = *(undefined8 *)(lVar3 + 0x390)
                          ;
                          if (DAT_1804b59e8 == 0) {
                            return;
                          }
                          *(undefined8 *)(&stack0x00000050 + lVar9) = DAT_1804b59f0;
                          pcVar1 = *(code **)(lVar3 + 0x518);
                          *(undefined2 *)(&stack0x000006a6 + lVar9) = 0xde;
                          plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                          *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180298fc0;
                          uVar11 = (*pcVar1)(plVar2,&stack0x000006a6 + lVar9,1);
                          lVar7 = DAT_1804b4d38;
                          lVar6 = DAT_1804b4d30;
                          *(undefined8 *)(&stack0x00000048 + lVar9) = uVar11;
                          lVar3 = *plVar2;
                          *(undefined8 *)(&stack0x00000058 + lVar9) = *(undefined8 *)(lVar3 + 0x390)
                          ;
                          if (DAT_1804b4d30 == 0) {
                            return;
                          }
                          if (DAT_1804b4d38 == 0) {
                            return;
                          }
                          pcVar1 = *(code **)(lVar3 + 0x518);
                          *(undefined8 *)(&stack0x0000381c + lVar9) = 0x7200740053002f;
                          *(undefined8 *)(&stack0x00003824 + lVar9) = 0x3b0067006e0069;
                          *(undefined8 *)(&stack0x00003810 + lVar9) = 0x61006c002f0061;
                          *(undefined8 *)(&stack0x00003818 + lVar9) = 0x53002f0067006e;
                          *(undefined4 *)(&stack0x00003800 + lVar9) = 0x4a0028;
                          *(undefined4 *)(&stack0x00003804 + lVar9) = 0x29004a;
                          *(undefined4 *)(&stack0x00003808 + lVar9) = 0x6a004c;
                          *(undefined4 *)(&stack0x0000380c + lVar9) = 0x760061;
                          plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                          *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299046;
                          uVar11 = (*pcVar1)(plVar2,&stack0x00003800 + lVar9,0x16);
                          pcVar1 = *(code **)(*plVar2 + 0x110);
                          if (DAT_1804b4d40 == 0) {
                            return;
                          }
                          plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                          uVar14 = *(undefined8 *)(&stack0x00000040 + lVar9);
                          *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299079;
                          uVar12 = (*pcVar1)(plVar2,uVar14);
                          *(undefined8 *)(&stack0xffffffffffffffe0 + lVar9) = uVar12;
                          *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299091;
                          uVar11 = (**(code **)(&stack0x00000058 + lVar9))
                                             (plVar2,lVar6,lVar7,uVar11);
                          *(undefined8 *)(&stack0xffffffffffffffe8 + lVar9) = uVar11;
                          *(undefined8 *)(&stack0xffffffffffffffe0 + lVar9) =
                               *(undefined8 *)(&stack0x00000048 + lVar9);
                          *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x1802990bb;
                          uVar11 = (**(code **)(&stack0x00000060 + lVar9))
                                             (plVar2,uVar14,lVar4,
                                              *(undefined8 *)(&stack0x00000050 + lVar9));
                          pcVar1 = *(code **)(*plVar2 + 0xa8);
                          *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x1802990cd;
                          DAT_1804b09b0 = (*pcVar1)(plVar2,uVar11);
                          pcVar1 = *(code **)(*plVar2 + 0xb8);
                          *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x1802990e3;
                          (*pcVar1)(plVar2,uVar11);
                        }
                        pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10) +
                                           0x110);
                        *(longlong *)(&stack0x00000060 + lVar9) = DAT_1804b4d48;
                        if (DAT_1804b4d48 == 0) {
                          return;
                        }
                        plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                        *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299126;
                        uVar11 = (*pcVar1)(plVar2,DAT_1804b09b0,
                                           *(undefined8 *)(&stack0x00000060 + lVar9));
                        *(undefined8 *)((longlong)auStackX_10 + lVar9) = uVar11;
                        pcVar1 = *(code **)(*plVar2 + 0x560);
                        *(longlong *)(&stack0x00000050 + lVar9) = DAT_1804b4d58;
                        if (DAT_1804b4d58 == 0) {
                          return;
                        }
                        plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                        *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299167;
                        uVar11 = (*pcVar1)(plVar2,2,*(undefined8 *)(&stack0x00000050 + lVar9),0);
                        lVar4 = *plVar2;
                        pcVar1 = *(code **)(lVar4 + 0x390);
                        pcVar5 = *(code **)(lVar4 + 0x570);
                        *(longlong *)(&stack0x00000048 + lVar9) = DAT_1804b4d60;
                        if (DAT_1804b4d60 == 0) {
                          return;
                        }
                        *(longlong *)(&stack0x00000058 + lVar9) = DAT_1804b4d68;
                        if (DAT_1804b4d68 == 0) {
                          return;
                        }
                        plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                        uVar14 = *(undefined8 *)(&stack0x00000048 + lVar9);
                        uVar12 = *(undefined8 *)(&stack0x00000058 + lVar9);
                        *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x1802991d6;
                        uVar13 = (*pcVar1)(plVar2,uVar14,uVar12,0xdf4901d5e6c6fb04);
                        *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x1802991e5;
                        (*pcVar5)(plVar2,uVar11,0,uVar13);
                        pcVar1 = *(code **)(*plVar2 + 0x570);
                        pcVar5 = *(code **)(*plVar2 + 0x390);
                        *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299206;
                        uVar14 = (*pcVar5)(plVar2,uVar14,uVar12,
                                           *(undefined8 *)(&stack0x00000070 + lVar9));
                        *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299217;
                        (*pcVar1)(plVar2,uVar11,1,uVar14);
                        pcVar1 = *(code **)(*plVar2 + 0x110);
                        *(longlong *)(&stack0x00000068 + lVar9) = DAT_1804b4d70;
                        if (DAT_1804b4d70 == 0) {
                          return;
                        }
                        plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                        *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299256;
                        uVar11 = (*pcVar1)(plVar2,*(undefined8 *)((longlong)auStackX_10 + lVar9),
                                           *(undefined8 *)(&stack0x00000068 + lVar9),uVar11);
                        *(undefined8 *)(&stack0x000000d0 + lVar9) = uVar11;
                        pcVar1 = *(code **)(*plVar2 + 0x720);
                        *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x18029926b;
                        cVar8 = (*pcVar1)(plVar2);
                        if (cVar8 == '\0') {
                          pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_10 + lVar9 + -0x10)
                                             + 0x720);
                          *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299281;
                          cVar8 = (*pcVar1)();
                          uVar11 = DAT_1804b59f0;
                          if (cVar8 == '\0') {
                            uVar10 = *(ulonglong *)(&stack0x00000070 + lVar9);
                            *(ulonglong *)(&stack0x000006f0 + lVar9) = uVar10 ^ 0x5ff8aa7c60f8;
                            *(ulonglong *)(&stack0x000031a8 + lVar9) = uVar10 ^ 0x731004dea40c;
                            *(ulonglong *)(&stack0x00003118 + lVar9) = uVar10 ^ 0x427d152106cc;
                            *(ulonglong *)(&stack0x000006b0 + lVar9) = uVar10 ^ 0x69633e68faec;
                            *(ulonglong *)(&stack0x00003020 + lVar9) = uVar10 ^ 0x7f3197aee1a7;
                            *(ulonglong *)(&stack0x000006e0 + lVar9) = uVar10 ^ 0x2429ae09891d;
                            *(ulonglong *)(&stack0x000006e8 + lVar9) = uVar10 ^ 0x500ffda24997;
                            *(ulonglong *)(&stack0x000030e8 + lVar9) = uVar10 ^ 0x24728601441;
                            *(ulonglong *)(&stack0x000006a8 + lVar9) = uVar10 ^ 0x2fd4a8b5f4b7;
                            *(ulonglong *)(&stack0x00003188 + lVar9) = uVar10 ^ 0x376f53e7ecad1634;
                            *(ulonglong *)(&stack0x00003180 + lVar9) = uVar10 ^ 0x69a55a9589e2a844;
                            *(ulonglong *)(&stack0x00003178 + lVar9) = uVar10 ^ 0x7396b9ec38867234;
                            *(ulonglong *)(&stack0x00003170 + lVar9) = uVar10 ^ 0x3f31869837e211cd;
                            *(ulonglong *)(&stack0x000031a0 + lVar9) = uVar10 ^ 0x3bb867f1e1a12494;
                            *(ulonglong *)(&stack0x00003190 + lVar9) = uVar10 ^ 0x1020ee298f7e0d96;
                            *(ulonglong *)(&stack0x00003168 + lVar9) = uVar10 ^ 0x298d003a7e15f5fb;
                            *(ulonglong *)(&stack0x00003160 + lVar9) = uVar10 ^ 0x70a29239e51042ad;
                            *(ulonglong *)(&stack0x00003158 + lVar9) = uVar10 ^ 0x53b26617550a9a;
                            *(ulonglong *)(&stack0x00003150 + lVar9) = uVar10 ^ 0x5af3757d392631fb;
                            *(ulonglong *)(&stack0x00003148 + lVar9) = uVar10 ^ 0x4d5553c1db0f274f;
                            *(ulonglong *)(&stack0x00003140 + lVar9) = uVar10 ^ 0x4c157a0a9b9976b1;
                            *(ulonglong *)(&stack0x00003138 + lVar9) = uVar10 ^ 0x190a06e4b39c3480;
                            *(ulonglong *)(&stack0x00003130 + lVar9) = uVar10 ^ 0x48d7359d0bdfb9cc;
                            *(ulonglong *)(&stack0x00003120 + lVar9) = uVar10 ^ 0x27f53639e7290755;
                            *(ulonglong *)(&stack0x00003128 + lVar9) = uVar10 ^ 0x53cee5ec8b90eb83;
                            *(ulonglong *)(&stack0x00003100 + lVar9) = uVar10 ^ 0x36ceb727c2479f7c;
                            *(ulonglong *)(&stack0x00003108 + lVar9) = uVar10 ^ 0xc59d66f53ace21f;
                            *(ulonglong *)(&stack0x000030f8 + lVar9) = uVar10 ^ 0x4df38fcf41d4239d;
                            *(ulonglong *)(&stack0x000030f0 + lVar9) = uVar10 ^ 0x24c779a4a053016d;
                            *(ulonglong *)(&stack0x000030d8 + lVar9) = uVar10 ^ 0x342dd050a9edc524;
                            *(ulonglong *)(&stack0x000030e0 + lVar9) = uVar10 ^ 0x5edd8144b8e9a691;
                            *(ulonglong *)(&stack0x000030d0 + lVar9) = uVar10 ^ 0x64504d1312300a0b;
                            *(ulonglong *)(&stack0x000030c8 + lVar9) = uVar10 ^ 0x6ef3913cd4c99f8c;
                            *(ulonglong *)(&stack0x000030c0 + lVar9) = uVar10 ^ 0x5530b2a6a07817e6;
                            *(ulonglong *)(&stack0x000030b8 + lVar9) = uVar10 ^ 0x7b437b9873165ab8;
                            *(ulonglong *)(&stack0x000030b0 + lVar9) = uVar10 ^ 0x40e3cb1227e8995a;
                            *(ulonglong *)(&stack0x000030a8 + lVar9) = uVar10 ^ 0x52db492b699e1937;
                            *(ulonglong *)(&stack0x000030a0 + lVar9) = uVar10 ^ 0x9b07d8fb3e5e151;
                            *(ulonglong *)(&stack0x00003098 + lVar9) = uVar10 ^ 0x4827e5ea916ad0b1;
                            *(ulonglong *)(&stack0x00003090 + lVar9) = uVar10 ^ 0x3caef7852777ad78;
                            *(ulonglong *)(&stack0x00003080 + lVar9) = uVar10 ^ 0x70c2ba5829c6c940;
                            *(ulonglong *)(&stack0x00003068 + lVar9) = uVar10 ^ 0x3e7534c19f992c1d;
                            *(ulonglong *)(&stack0x00003088 + lVar9) = uVar10 ^ 0x243637c2221d902e;
                            *(ulonglong *)(&stack0x00003078 + lVar9) = uVar10 ^ 0x6da8ac96b8007783;
                            *(ulonglong *)(&stack0x00003070 + lVar9) = uVar10 ^ 0x34012f8ad59ac84a;
                            *(ulonglong *)(&stack0x00003060 + lVar9) = uVar10 ^ 0x271eb9d0de0bfd29;
                            *(ulonglong *)(&stack0x00003058 + lVar9) = uVar10 ^ 0x27610227289784b8;
                            *(ulonglong *)(&stack0x00003050 + lVar9) = uVar10 ^ 0x7bf3c6f0a1445aee;
                            *(ulonglong *)(&stack0x00003048 + lVar9) = uVar10 ^ 0x71682da491d49e93;
                            *(ulonglong *)(&stack0x00003040 + lVar9) = uVar10 ^ 0x215cdbbc3d070ff6;
                            *(ulonglong *)(&stack0x00003038 + lVar9) = uVar10 ^ 0x4607950688823197;
                            *(ulonglong *)(&stack0x00003030 + lVar9) = uVar10 ^ 0x67a68ff687e4d1;
                            *(ulonglong *)(&stack0x00003028 + lVar9) = uVar10 ^ 0x37c65571ba14f71f;
                            *(ulonglong *)(&stack0x00003018 + lVar9) = uVar10 ^ 0x75aedecb1524f0b9;
                            *(ulonglong *)(&stack0x00003010 + lVar9) = uVar10 ^ 0x73a50e75094e287a;
                            *(ulonglong *)(&stack0x00003008 + lVar9) = uVar10 ^ 0x43577d97d95b0693;
                            *(ulonglong *)(&stack0x00003000 + lVar9) = uVar10 ^ 0x2659845ec7abc0f5;
                            *(ulonglong *)(&stack0x00002ff8 + lVar9) = uVar10 ^ 0x126b96c438526afb;
                            *(ulonglong *)(&stack0x00002ff0 + lVar9) = uVar10 ^ 0x21948f891cc14526;
                            *(ulonglong *)(&stack0x00002fe8 + lVar9) = uVar10 ^ 0x112b0c237b282acc;
                            *(ulonglong *)(&stack0x00002fe0 + lVar9) = uVar10 ^ 0x72d95392914a9943;
                            *(ulonglong *)(&stack0x00002fd8 + lVar9) = uVar10 ^ 0x2ff85a5e2d297b9;
                            *(ulonglong *)(&stack0x00002fd0 + lVar9) = uVar10 ^ 0x5dbef9baabae7f45;
                            *(ulonglong *)(&stack0x00002fc8 + lVar9) = uVar10 ^ 0x1d52d7ad520337ed;
                            *(ulonglong *)(&stack0x00002fc0 + lVar9) = uVar10 ^ 0xa8510df075ba469;
                            *(ulonglong *)(&stack0x00002fb8 + lVar9) = uVar10 ^ 0x7b2f4867cd4d7179;
                            *(ulonglong *)(&stack0x00002fb0 + lVar9) = uVar10 ^ 0x6af9d8cc7fe0b182;
                            *(ulonglong *)(&stack0x00002fa8 + lVar9) = uVar10 ^ 0x4e9f65279d8960c8;
                            *(ulonglong *)(&stack0x00002fa0 + lVar9) = uVar10 ^ 0x1d2906cf5ec0da81;
                            *(ulonglong *)(&stack0x00002f98 + lVar9) = uVar10 ^ 0x208b48ee329630ab;
                            *(ulonglong *)(&stack0x00002f90 + lVar9) = uVar10 ^ 0x740034e79d338e38;
                            *(ulonglong *)(&stack0x00002f88 + lVar9) = uVar10 ^ 0x66196051356abc91;
                            *(ulonglong *)(&stack0x00002f80 + lVar9) = uVar10 ^ 0x5478084626e0da33;
                            *(ulonglong *)(&stack0x00002f78 + lVar9) = uVar10 ^ 0x7df935e6e82c34a3;
                            *(ulonglong *)(&stack0x00002f70 + lVar9) = uVar10 ^ 0x15290d95bb0f7016;
                            *(ulonglong *)(&stack0x00002f68 + lVar9) = uVar10 ^ 0x1f74a6862060a99d;
                            *(ulonglong *)(&stack0x00002f60 + lVar9) = uVar10 ^ 0x366bb0e15c6d4f36;
                            *(ulonglong *)(&stack0x00002f58 + lVar9) = uVar10 ^ 0x101596c382aae3ab;
                            *(ulonglong *)(&stack0x00002f50 + lVar9) = uVar10 ^ 0x34076f06ed11e146;
                            *(ulonglong *)(&stack0x00002f48 + lVar9) = uVar10 ^ 0x5f7cb6b4818e3642;
                            *(ulonglong *)(&stack0x00002f40 + lVar9) = uVar10 ^ 0x62b153a7052b98ef;
                            *(ulonglong *)(&stack0x00002f38 + lVar9) = uVar10 ^ 0x13ac985c426e6382;
                            *(ulonglong *)(&stack0x00002f30 + lVar9) = uVar10 ^ 0xaa3bf5cc96517ab;
                            *(ulonglong *)(&stack0x00002f28 + lVar9) = uVar10 ^ 0x40f3b5aa24d2e047;
                            *(ulonglong *)(&stack0x00002f20 + lVar9) = uVar10 ^ 0x67cd42ee3ee47938;
                            *(ulonglong *)(&stack0x00002f18 + lVar9) = uVar10 ^ 0xf73f811130ae505;
                            *(ulonglong *)(&stack0x00002f10 + lVar9) = uVar10 ^ 0x5eb21ab2d5d2893c;
                            *(ulonglong *)(&stack0x00002f08 + lVar9) = uVar10 ^ 0x61460be7dc1b7a05;
                            *(ulonglong *)(&stack0x00002f00 + lVar9) = uVar10 ^ 0x213f140a94f8dcb7;
                            *(ulonglong *)(&stack0x00002ef8 + lVar9) = uVar10 ^ 0x4e5f79173edbd0ab;
                            *(ulonglong *)(&stack0x00002ef0 + lVar9) = uVar10 ^ 0x79ad44b58b561d20;
                            *(ulonglong *)(&stack0x00002ee8 + lVar9) = uVar10 ^ 0x378ce26e012b3efc;
                            *(ulonglong *)(&stack0x00002ee0 + lVar9) = uVar10 ^ 0x189cb8eac84fab0b;
                            *(ulonglong *)(&stack0x00002ed8 + lVar9) = uVar10 ^ 0x544cf12c98751634;
                            *(ulonglong *)(&stack0x00002ed0 + lVar9) = uVar10 ^ 0x18bb69b389fc769f;
                            *(ulonglong *)(&stack0x00002ec8 + lVar9) = uVar10 ^ 0x6628e5ceece34fa5;
                            *(ulonglong *)(&stack0x00002ec0 + lVar9) = uVar10 ^ 0x7d6eb997431a3a09;
                            *(ulonglong *)(&stack0x00002eb8 + lVar9) = uVar10 ^ 0x38ea4c7bcd2c6152;
                            *(ulonglong *)(&stack0x00002eb0 + lVar9) = uVar10 ^ 0x4871adba47c364af;
                            *(ulonglong *)(&stack0x00002ea8 + lVar9) = uVar10 ^ 0x4785bcef6aebb53;
                            *(undefined8 *)(&stack0x00000108 + lVar9) = 0;
                            *(undefined8 *)(&stack0x00002ea0 + lVar9) = 0x20de1b2e903ffd51;
                            *(undefined8 *)(&stack0x00002e98 + lVar9) = 0xc88a4d8fbe4f1b48;
                            *(undefined8 *)(&stack0x00003110 + lVar9) = 0x7938dae9dd3e73b0;
                            *(undefined8 *)(&stack0x00000130 + lVar9) = 0;
                            *(undefined8 *)(&stack0x000000c8 + lVar9) = 0;
                            *(undefined8 *)(&stack0x000006d0 + lVar9) = 0;
                            *(undefined4 *)(&stack0x0000012c + lVar9) = 0;
                            *(undefined8 *)(&stack0x00000118 + lVar9) = 0;
                            *(undefined8 *)(&stack0x00000100 + lVar9) = 0;
                            *(undefined8 *)(&stack0x00000120 + lVar9) = 0;
                            *(undefined4 *)(&stack0x000000d8 + lVar9) = 0;
                            *(undefined8 *)(&stack0x000000e0 + lVar9) = 0;
                            *(undefined8 *)(&stack0x000000f8 + lVar9) = 0;
                            *(undefined4 *)(&stack0x000000e8 + lVar9) = 0;
                            *(undefined8 *)(&stack0x000006c8 + lVar9) = 0;
                            *(undefined8 *)(&stack0x000006c0 + lVar9) = 0;
                            *(undefined8 *)(&stack0x000006d8 + lVar9) = 0;
                            *(undefined8 *)(&stack0x00000138 + lVar9) = 0;
                            *(undefined8 *)(&stack0x000006b8 + lVar9) = 0;
                            if (DAT_1804b09b8 == 0) {
                              lVar4 = **(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                              *(undefined8 *)(&stack0x00000078 + lVar9) =
                                   *(undefined8 *)(lVar4 + 0x390);
                              if (DAT_1804b59f8 == 0) {
                                return;
                              }
                              *(longlong *)(&stack0x00000080 + lVar9) = DAT_1804b59f8;
                              pcVar1 = *(code **)(lVar4 + 0x518);
                              *(undefined2 *)(&stack0x000006a4 + lVar9) = 0x70;
                              plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                              *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299c51;
                              uVar12 = (*pcVar1)(plVar2,&stack0x000006a4 + lVar9,1);
                              lVar6 = DAT_1804b4d38;
                              lVar3 = DAT_1804b4d30;
                              lVar4 = *plVar2;
                              uVar14 = *(undefined8 *)(lVar4 + 0x390);
                              if (DAT_1804b4d30 == 0) {
                                return;
                              }
                              *(undefined8 *)(&stack0x00000030 + lVar9) = uVar12;
                              *(undefined8 *)(&stack0x00000090 + lVar9) = uVar14;
                              if (DAT_1804b4d38 == 0) {
                                return;
                              }
                              pcVar1 = *(code **)(lVar4 + 0x518);
                              *(undefined8 *)(&stack0x00002e88 + lVar9) = 0x29004a00490028;
                              *(undefined2 *)(&stack0x00002e90 + lVar9) = 0x49;
                              plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                              *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299cc6;
                              uVar14 = (*pcVar1)(plVar2,&stack0x00002e88 + lVar9,5);
                              pcVar1 = *(code **)(*plVar2 + 0x110);
                              if (DAT_1804b4d40 == 0) {
                                return;
                              }
                              plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                              uVar12 = *(undefined8 *)(&stack0x00000040 + lVar9);
                              *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299cf9;
                              uVar13 = (*pcVar1)(plVar2,uVar12);
                              *(undefined8 *)(&stack0xffffffffffffffe0 + lVar9) = uVar13;
                              *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299d11;
                              uVar14 = (**(code **)(&stack0x00000090 + lVar9))
                                                 (plVar2,lVar3,lVar6,uVar14);
                              *(undefined8 *)(&stack0xffffffffffffffe8 + lVar9) = uVar14;
                              *(undefined8 *)(&stack0xffffffffffffffe0 + lVar9) =
                                   *(undefined8 *)(&stack0x00000030 + lVar9);
                              *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299d38;
                              uVar11 = (**(code **)(&stack0x00000078 + lVar9))
                                                 (plVar2,uVar12,
                                                  *(undefined8 *)(&stack0x00000080 + lVar9),uVar11);
                              pcVar1 = *(code **)(*plVar2 + 0xa8);
                              *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299d4b;
                              DAT_1804b09b8 = (*pcVar1)(plVar2,uVar11);
                              pcVar1 = *(code **)(*plVar2 + 0xb8);
                              *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299d62;
                              (*pcVar1)(plVar2,uVar11);
                            }
                            plVar2 = *(longlong **)((longlong)auStackX_10 + lVar9 + -0x10);
                            pcVar1 = *(code **)(*plVar2 + 0x110);
                            *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299d82;
                            uVar11 = (*pcVar1)(plVar2,DAT_1804b09b8,
                                               *(undefined8 *)(&stack0x00000060 + lVar9));
                            *(undefined8 *)((longlong)auStackX_10 + lVar9) = uVar11;
                            pcVar1 = *(code **)(*plVar2 + 0x560);
                            *(undefined8 *)((longlong)&uStack_48 + lVar9) = 0x180299da3;
                            (*pcVar1)(plVar2,2,*(undefined8 *)(&stack0x00000050 + lVar9),0);
                            *(longlong *)(&stack0x00000078 + lVar9) = DAT_1804b4dd8;
                            if (DAT_1804b4dd8 != 0) {
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
  return;
}

