/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=0 entry=1803db540 blocks=43 maxInstructions=50000 */

/* cut edge: 0x1803db540 -> 0x180447c8e */
/* cut edge: 0x1803db59c -> 0x180447cd0 */
/* cut edge: 0x1803db5ac -> 0x180473ae8 */
/* cut edge: 0x1803db5d5 -> 0x180473ae8 */
/* cut edge: 0x1803db61b -> 0x180473ae8 */
/* cut edge: 0x1803db63e -> 0x180473ae8 */
/* cut edge: 0x1803db661 -> 0x180473ae8 */
/* cut edge: 0x1803db684 -> 0x180473ae8 */
/* cut edge: 0x1803db6a7 -> 0x180473ae8 */
/* cut edge: 0x1803db6ca -> 0x180473ae8 */
/* cut edge: 0x1803db6ed -> 0x180473ae8 */
/* cut edge: 0x1803db710 -> 0x180473ae8 */
/* cut edge: 0x1803db733 -> 0x180473ae8 */
/* cut edge: 0x1803db756 -> 0x180473ae8 */
/* cut edge: 0x1803db779 -> 0x180473ae8 */
/* cut edge: 0x1803db79c -> 0x180473ae8 */
/* cut edge: 0x1803db7bf -> 0x180473ae8 */
/* cut edge: 0x1803db7e2 -> 0x180473ae8 */
/* cut edge: 0x1803db805 -> 0x180473ae8 */
/* cut edge: 0x1803db828 -> 0x180473ae8 */
/* cut edge: 0x1803db84b -> 0x180473ae8 */
/* cut edge: 0x1803db86e -> 0x180473ae8 */
/* cut edge: 0x1803db891 -> 0x180473ae8 */
/* cut edge: 0x1803db8b7 -> 0x180447e9b */
/* cut edge: 0x1803db8de -> 0x180447ed6 */
/* cut edge: 0x1803db933 -> 0x180447f27 */
/* cut edge: 0x1803db953 -> 0x180447f60 */
/* cut edge: 0x1803dba40 -> 0x180447d09 */
/* cut edge: 0x1803dba67 -> 0x180447d44 */
/* cut edge: 0x1803dbaa3 -> 0x180447d86 */
/* cut edge: 0x1803dbae9 -> 0x180447dc8 */
/* cut edge: 0x1803dbb01 -> 0x180447e06 */
/* cut edge: 0x1803dbb94 -> 0x180473ae8 */
/* cut edge: 0x1803dbbc9 -> 0x180473ae8 */
/* cut edge: 0x1803dc478 -> 0x1803dc5fa */
/* cut edge: 0x1803dc488 -> 0x180447686 */
/* cut edge: 0x1803dc4af -> 0x1804476bc */
/* cut edge: 0x1803dc50e -> 0x18044770d */
/* cut edge: 0x1803dc51e -> 0x180447746 */
/* cut edge: 0x1803dc51e -> 0x1803dc576 */
/* target 0x1803dc576 original byte 0x48 replaced by RET */
/* target 0x1803dc5fa original byte 0x48 replaced by RET */
/* target 0x180447686 original byte 0x48 replaced by RET */
/* target 0x1804476bc original byte 0x48 replaced by RET */
/* target 0x18044770d original byte 0x48 replaced by RET */
/* target 0x180447746 original byte 0x48 replaced by RET */
/* target 0x180447c8e original byte 0x48 replaced by RET */
/* target 0x180447cd0 original byte 0x48 replaced by RET */
/* target 0x180447d09 original byte 0x48 replaced by RET */
/* target 0x180447d44 original byte 0x48 replaced by RET */
/* target 0x180447d86 original byte 0x48 replaced by RET */
/* target 0x180447dc8 original byte 0x48 replaced by RET */
/* target 0x180447e06 original byte 0x4c replaced by RET */
/* target 0x180447e9b original byte 0x48 replaced by RET */
/* target 0x180447ed6 original byte 0x48 replaced by RET */
/* target 0x180447f27 original byte 0x48 replaced by RET */
/* target 0x180447f60 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00000_1803db540
               (longlong *param_1,undefined8 param_2,longlong param_3,undefined4 param_4)

{
  code *pcVar1;
  longlong *plVar2;
  longlong lVar3;
  code *pcVar4;
  longlong lVar5;
  longlong lVar6;
  char cVar7;
  longlong lVar8;
  ulonglong uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  ulonglong auStackX_8 [4];
  undefined8 uStack_48;
  
  uStack_48 = 0x1803db556;
  lVar8 = FUN_180492640();
  lVar8 = -lVar8;
  *(undefined4 *)(&stack0x000000f0 + lVar8) = param_4;
  *(undefined8 *)(&stack0x00000180 + lVar8) = param_2;
  *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) = 0;
  *(longlong **)((longlong)auStackX_8 + lVar8) = param_1;
  pcVar1 = *(code **)(*param_1 + 0x4b8);
  *(longlong *)(&stack0x00000048 + lVar8) = DAT_1804b59d8;
  if (DAT_1804b59d8 == 0) {
    return;
  }
  if (DAT_1804b59e0 == 0) {
    return;
  }
  plVar2 = *(longlong **)((longlong)auStackX_8 + lVar8);
  *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db5be;
  uVar9 = (*pcVar1)(plVar2,*(undefined8 *)(&stack0x00000048 + lVar8));
  pcVar1 = *(code **)(*plVar2 + 0x720);
  *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db5cd;
  cVar7 = (*pcVar1)(plVar2);
  if (cVar7 == '\0') {
    uVar9 = uVar9 ^ ((ulonglong)*(uint *)(&stack0x000000f0 + lVar8) << 0x10 | param_3 << 0x30 |
                    (ulonglong)*(ushort *)(&stack0x00004240 + lVar8));
    *(ulonglong *)(&stack0x00000088 + lVar8) = uVar9;
    *(ulonglong *)((longlong)auStackX_8 + lVar8 + 8) = uVar9;
    pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
    *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db613;
    cVar7 = (*pcVar1)();
    if (cVar7 == '\0') {
      *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) = *(undefined8 *)(&stack0x00000088 + lVar8);
      pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
      *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db636;
      cVar7 = (*pcVar1)();
      if (cVar7 == '\0') {
        *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
             *(undefined8 *)(&stack0x00000088 + lVar8);
        pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
        *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db659;
        cVar7 = (*pcVar1)();
        if (cVar7 == '\0') {
          *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
               *(undefined8 *)(&stack0x00000088 + lVar8);
          pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
          *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db67c;
          cVar7 = (*pcVar1)();
          if (cVar7 == '\0') {
            *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                 *(undefined8 *)(&stack0x00000088 + lVar8);
            pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
            *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db69f;
            cVar7 = (*pcVar1)();
            if (cVar7 == '\0') {
              *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                   *(undefined8 *)(&stack0x00000088 + lVar8);
              pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
              *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db6c2;
              cVar7 = (*pcVar1)();
              if (cVar7 == '\0') {
                *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                     *(undefined8 *)(&stack0x00000088 + lVar8);
                pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
                *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db6e5;
                cVar7 = (*pcVar1)();
                if (cVar7 == '\0') {
                  *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                       *(undefined8 *)(&stack0x00000088 + lVar8);
                  pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
                  *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db708;
                  cVar7 = (*pcVar1)();
                  if (cVar7 == '\0') {
                    *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                         *(undefined8 *)(&stack0x00000088 + lVar8);
                    pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
                    *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db72b;
                    cVar7 = (*pcVar1)();
                    if (cVar7 == '\0') {
                      *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                           *(undefined8 *)(&stack0x00000088 + lVar8);
                      pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
                      *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db74e;
                      cVar7 = (*pcVar1)();
                      if (cVar7 == '\0') {
                        *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                             *(undefined8 *)(&stack0x00000088 + lVar8);
                        pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720);
                        *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db771;
                        cVar7 = (*pcVar1)();
                        if (cVar7 == '\0') {
                          *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                               *(undefined8 *)(&stack0x00000088 + lVar8);
                          pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) + 0x720)
                          ;
                          *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db794;
                          cVar7 = (*pcVar1)();
                          if (cVar7 == '\0') {
                            *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                                 *(undefined8 *)(&stack0x00000088 + lVar8);
                            pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) +
                                               0x720);
                            *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db7b7;
                            cVar7 = (*pcVar1)();
                            if (cVar7 == '\0') {
                              *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                                   *(undefined8 *)(&stack0x00000088 + lVar8);
                              pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) +
                                                 0x720);
                              *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db7da;
                              cVar7 = (*pcVar1)();
                              if (cVar7 == '\0') {
                                *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                                     *(undefined8 *)(&stack0x00000088 + lVar8);
                                pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8) +
                                                   0x720);
                                *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db7fd;
                                cVar7 = (*pcVar1)();
                                if (cVar7 == '\0') {
                                  *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                                       *(undefined8 *)(&stack0x00000088 + lVar8);
                                  pcVar1 = *(code **)(**(longlong **)((longlong)auStackX_8 + lVar8)
                                                     + 0x720);
                                  *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db820;
                                  cVar7 = (*pcVar1)();
                                  if (cVar7 == '\0') {
                                    *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                                         *(undefined8 *)(&stack0x00000088 + lVar8);
                                    pcVar1 = *(code **)(**(longlong **)
                                                          ((longlong)auStackX_8 + lVar8) + 0x720);
                                    *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db843;
                                    cVar7 = (*pcVar1)();
                                    if (cVar7 == '\0') {
                                      *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                                           *(undefined8 *)(&stack0x00000088 + lVar8);
                                      pcVar1 = *(code **)(**(longlong **)
                                                            ((longlong)auStackX_8 + lVar8) + 0x720);
                                      *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db866;
                                      cVar7 = (*pcVar1)();
                                      if (cVar7 == '\0') {
                                        *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                                             *(undefined8 *)(&stack0x00000088 + lVar8);
                                        pcVar1 = *(code **)(**(longlong **)
                                                              ((longlong)auStackX_8 + lVar8) + 0x720
                                                           );
                                        *(undefined8 *)((longlong)&uStack_48 + lVar8) = 0x1803db889;
                                        cVar7 = (*pcVar1)();
                                        if (cVar7 == '\0') {
                                          pcVar1 = *(code **)(**(longlong **)
                                                                ((longlong)auStackX_8 + lVar8) +
                                                             0x720);
                                          *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                               0x1803db89f;
                                          cVar7 = (*pcVar1)();
                                          uVar11 = DAT_1804b59f0;
                                          if (cVar7 == '\0') {
                                            if (DAT_1804b3660 == 0) {
                                              lVar3 = **(longlong **)((longlong)auStackX_8 + lVar8);
                                              uVar13 = *(undefined8 *)(lVar3 + 0x390);
                                              if (DAT_1804b59e8 == 0) {
                                                return;
                                              }
                                              *(longlong *)(&stack0x00000040 + lVar8) =
                                                   DAT_1804b59e8;
                                              pcVar1 = *(code **)(lVar3 + 0x518);
                                              *(undefined2 *)(&stack0x000006f6 + lVar8) = 0xde;
                                              plVar2 = *(longlong **)((longlong)auStackX_8 + lVar8);
                                              *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                   0x1803db90e;
                                              uVar10 = (*pcVar1)(plVar2,&stack0x000006f6 + lVar8,1);
                                              lVar6 = DAT_1804b4d38;
                                              lVar5 = DAT_1804b4d30;
                                              lVar3 = *plVar2;
                                              *(undefined8 *)(&stack0x00000050 + lVar8) =
                                                   *(undefined8 *)(lVar3 + 0x390);
                                              if (DAT_1804b4d30 == 0) {
                                                return;
                                              }
                                              *(undefined8 *)(&stack0x00000058 + lVar8) = uVar11;
                                              *(undefined8 *)(&stack0x00000060 + lVar8) = uVar13;
                                              if (DAT_1804b4d38 == 0) {
                                                return;
                                              }
                                              pcVar1 = *(code **)(lVar3 + 0x518);
                                              *(undefined8 *)(&stack0x000038ac + lVar8) =
                                                   0x7200740053002f;
                                              *(undefined8 *)(&stack0x000038b4 + lVar8) =
                                                   0x3b0067006e0069;
                                              *(undefined8 *)(&stack0x000038a0 + lVar8) =
                                                   0x61006c002f0061;
                                              *(undefined8 *)(&stack0x000038a8 + lVar8) =
                                                   0x53002f0067006e;
                                              *(undefined4 *)(&stack0x00003890 + lVar8) = 0x4a0028;
                                              *(undefined4 *)(&stack0x00003894 + lVar8) = 0x29004a;
                                              *(undefined4 *)(&stack0x00003898 + lVar8) = 0x6a004c;
                                              *(undefined4 *)(&stack0x0000389c + lVar8) = 0x760061;
                                              plVar2 = *(longlong **)((longlong)auStackX_8 + lVar8);
                                              *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                   0x1803db99f;
                                              uVar11 = (*pcVar1)(plVar2,&stack0x00003890 + lVar8,
                                                                 0x16);
                                              pcVar1 = *(code **)(*plVar2 + 0x110);
                                              if (DAT_1804b4d40 == 0) {
                                                return;
                                              }
                                              plVar2 = *(longlong **)((longlong)auStackX_8 + lVar8);
                                              uVar13 = *(undefined8 *)(&stack0x00000048 + lVar8);
                                              *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                   0x1803db9d2;
                                              uVar12 = (*pcVar1)(plVar2,uVar13);
                                              *(undefined8 *)(&stack0xffffffffffffffe0 + lVar8) =
                                                   uVar12;
                                              *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                   0x1803db9ea;
                                              uVar11 = (**(code **)(&stack0x00000050 + lVar8))
                                                                 (plVar2,lVar5,lVar6,uVar11);
                                              *(undefined8 *)(&stack0xffffffffffffffe8 + lVar8) =
                                                   uVar11;
                                              *(undefined8 *)(&stack0xffffffffffffffe0 + lVar8) =
                                                   uVar10;
                                              *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                   0x1803dba11;
                                              uVar11 = (**(code **)(&stack0x00000060 + lVar8))
                                                                 (plVar2,uVar13,
                                                                  *(undefined8 *)
                                                                   (&stack0x00000040 + lVar8),
                                                                  *(undefined8 *)
                                                                   (&stack0x00000058 + lVar8));
                                              pcVar1 = *(code **)(*plVar2 + 0xa8);
                                              *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                   0x1803dba23;
                                              DAT_1804b3660 = (*pcVar1)(plVar2,uVar11);
                                              pcVar1 = *(code **)(*plVar2 + 0xb8);
                                              *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                   0x1803dba39;
                                              (*pcVar1)(plVar2,uVar11);
                                            }
                                            pcVar1 = *(code **)(**(longlong **)
                                                                  ((longlong)auStackX_8 + lVar8) +
                                                               0x110);
                                            *(longlong *)(&stack0x00000060 + lVar8) = DAT_1804b4d48;
                                            if (DAT_1804b4d48 == 0) {
                                              return;
                                            }
                                            plVar2 = *(longlong **)((longlong)auStackX_8 + lVar8);
                                            *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                 0x1803dba7c;
                                            uVar11 = (*pcVar1)(plVar2,DAT_1804b3660,
                                                               *(undefined8 *)
                                                                (&stack0x00000060 + lVar8));
                                            *(undefined8 *)((longlong)auStackX_8 + lVar8 + 8) =
                                                 uVar11;
                                            pcVar1 = *(code **)(*plVar2 + 0x560);
                                            *(longlong *)(&stack0x00000050 + lVar8) = DAT_1804b4d58;
                                            if (DAT_1804b4d58 == 0) {
                                              return;
                                            }
                                            plVar2 = *(longlong **)((longlong)auStackX_8 + lVar8);
                                            *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                 0x1803dbabd;
                                            uVar11 = (*pcVar1)(plVar2,2,*(undefined8 *)
                                                                         (&stack0x00000050 + lVar8),
                                                               0);
                                            lVar3 = *plVar2;
                                            pcVar1 = *(code **)(lVar3 + 0x390);
                                            pcVar4 = *(code **)(lVar3 + 0x570);
                                            *(longlong *)(&stack0x00000040 + lVar8) = DAT_1804b4d60;
                                            if (DAT_1804b4d60 == 0) {
                                              return;
                                            }
                                            *(longlong *)(&stack0x00000058 + lVar8) = DAT_1804b4d68;
                                            if (DAT_1804b4d68 == 0) {
                                              return;
                                            }
                                            plVar2 = *(longlong **)((longlong)auStackX_8 + lVar8);
                                            uVar13 = *(undefined8 *)(&stack0x00000040 + lVar8);
                                            uVar10 = *(undefined8 *)(&stack0x00000058 + lVar8);
                                            *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                 0x1803dbb2c;
                                            uVar12 = (*pcVar1)(plVar2,uVar13,uVar10,
                                                               0xafad0d2e40ff8be0);
                                            *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                 0x1803dbb3b;
                                            (*pcVar4)(plVar2,uVar11,0,uVar12);
                                            pcVar1 = *(code **)(*plVar2 + 0x570);
                                            pcVar4 = *(code **)(*plVar2 + 0x390);
                                            *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                 0x1803dbb5c;
                                            uVar13 = (*pcVar4)(plVar2,uVar13,uVar10,
                                                               *(undefined8 *)
                                                                (&stack0x00000088 + lVar8));
                                            *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                 0x1803dbb6d;
                                            (*pcVar1)(plVar2,uVar11,1,uVar13);
                                            pcVar1 = *(code **)(*plVar2 + 0x110);
                                            uVar13 = *(undefined8 *)
                                                      ((longlong)auStackX_8 + lVar8 + 8);
                                            *(longlong *)(&stack0x00000068 + lVar8) = DAT_1804b4d70;
                                            if (DAT_1804b4d70 == 0) {
                                              return;
                                            }
                                            plVar2 = *(longlong **)((longlong)auStackX_8 + lVar8);
                                            *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                 0x1803dbbac;
                                            uVar11 = (*pcVar1)(plVar2,uVar13,
                                                               *(undefined8 *)
                                                                (&stack0x00000068 + lVar8),uVar11);
                                            *(undefined8 *)(&stack0x000000d8 + lVar8) = uVar11;
                                            pcVar1 = *(code **)(*plVar2 + 0x720);
                                            *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                 0x1803dbbc1;
                                            cVar7 = (*pcVar1)(plVar2);
                                            if (cVar7 == '\0') {
                                              pcVar1 = *(code **)(**(longlong **)
                                                                    ((longlong)auStackX_8 + lVar8) +
                                                                 0x720);
                                              *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                   0x1803dbbd7;
                                              cVar7 = (*pcVar1)();
                                              if (cVar7 == '\0') {
                                                uVar9 = *(ulonglong *)(&stack0x00000088 + lVar8);
                                                *(ulonglong *)(&stack0x00000708 + lVar8) =
                                                     (uVar9 ^ 0x3374126c19a6) & 0xffffffff;
                                                *(ulonglong *)(&stack0x00000740 + lVar8) =
                                                     uVar9 ^ 0x424bcea1e19f;
                                                *(ulonglong *)(&stack0x00002f70 + lVar8) =
                                                     (uVar9 ^ 0x424bcea1e19f) >> 0x20;
                                                *(ulonglong *)(&stack0x00002f68 + lVar8) =
                                                     uVar9 & 0xffffffffffff ^ 0x3aa7488f8ec8;
                                                *(ulonglong *)(&stack0x00002f10 + lVar8) =
                                                     uVar9 ^ 0x6d787ebc151d;
                                                *(ulonglong *)(&stack0x00002f08 + lVar8) =
                                                     uVar9 ^ 0x6a5d9adbc9d1;
                                                *(ulonglong *)(&stack0x00000770 + lVar8) =
                                                     uVar9 ^ 0x36b37eef4b36;
                                                *(ulonglong *)(&stack0x00000178 + lVar8) =
                                                     uVar9 ^ 0xad264368c23;
                                                *(ulonglong *)(&stack0x00000778 + lVar8) =
                                                     uVar9 & 0xffffffffffff ^ 0x1c8a2f0ea188;
                                                *(ulonglong *)(&stack0x00002f60 + lVar8) =
                                                     uVar9 ^ 0x7a4a684676bc;
                                                *(ulonglong *)(&stack0x00002f98 + lVar8) =
                                                     (uVar9 ^ 0x7a4a684676bc) >> 0x10;
                                                *(ulonglong *)(&stack0x00002f40 + lVar8) =
                                                     uVar9 ^ 0x175433bc5557;
                                                *(ulonglong *)(&stack0x00000748 + lVar8) =
                                                     uVar9 ^ 0x5cf45b9b3973;
                                                *(ulonglong *)(&stack0x00000710 + lVar8) =
                                                     (uVar9 ^ 0x3374126c19a6) >> 0x20;
                                                *(ulonglong *)(&stack0x000006f8 + lVar8) =
                                                     uVar9 ^ 0x659898518a08;
                                                *(ulonglong *)(&stack0x00002f18 + lVar8) =
                                                     uVar9 ^ 0x1703bb6eda0e;
                                                *(ulonglong *)(&stack0x00000768 + lVar8) =
                                                     uVar9 ^ 0x8cf10d53a16;
                                                *(ulonglong *)(&stack0x00002f48 + lVar8) =
                                                     uVar9 ^ 0x5274394d4796;
                                                *(ulonglong *)(&stack0x00000750 + lVar8) =
                                                     uVar9 ^ 0x28d20830f9f9;
                                                *(ulonglong *)(&stack0x00002f38 + lVar8) =
                                                     uVar9 ^ 0x6bb6e86f3335;
                                                *(ulonglong *)(&stack0x00002f20 + lVar8) =
                                                     uVar9 ^ 0x2bbf7cdb17d;
                                                *(ulonglong *)(&stack0x00002f30 + lVar8) =
                                                     (uVar9 ^ 0x2bbf7cdb17d) >> 0x10;
                                                *(ulonglong *)(&stack0x00000170 + lVar8) =
                                                     uVar9 ^ 0x232f0e8c8453;
                                                *(ulonglong *)(&stack0x00002fb8 + lVar8) =
                                                     uVar9 ^ 0xe866ea2449d5c5;
                                                *(ulonglong *)(&stack0x00002fb0 + lVar8) =
                                                     uVar9 ^ 0x2c11154bb23545d1;
                                                *(ulonglong *)(&stack0x00002fa8 + lVar8) =
                                                     uVar9 ^ 0x124bed1ac547498a;
                                                *(ulonglong *)(&stack0x00002fa0 + lVar8) =
                                                     uVar9 ^ 0x41583666af5930b8;
                                                *(ulonglong *)(&stack0x00002fc8 + lVar8) =
                                                     uVar9 ^ 0x3b07f52012b9ca11;
                                                *(ulonglong *)(&stack0x00000128 + lVar8) =
                                                     uVar9 ^ 0x2e4cde28dae1f498;
                                                *(ulonglong *)(&stack0x00002fc0 + lVar8) =
                                                     uVar9 ^ 0xef2a72dd4653c46;
                                                *(ulonglong *)(&stack0x00002f90 + lVar8) =
                                                     uVar9 ^ 0x227e674636d28f61;
                                                *(ulonglong *)(&stack0x00002f88 + lVar8) =
                                                     uVar9 ^ 0x5eb2164973ebf9d8;
                                                *(ulonglong *)(&stack0x00002f80 + lVar8) =
                                                     uVar9 ^ 0x7b8b17a0419cef78;
                                                *(ulonglong *)(&stack0x00002f78 + lVar8) =
                                                     uVar9 ^ 0x7b577bb46158cb74;
                                                *(ulonglong *)(&stack0x00002f50 + lVar8) =
                                                     uVar9 ^ 0x6628e9354ada3f41;
                                                *(ulonglong *)(&stack0x00002f00 + lVar8) =
                                                     uVar9 ^ 0x7df9391d4e154447;
                                                *(ulonglong *)(&stack0x00002ef8 + lVar8) =
                                                     uVar9 ^ 0x73138313c43e6d0b;
                                                *(ulonglong *)(&stack0x00002ef0 + lVar8) =
                                                     uVar9 ^ 0x641a0796bb05bc1a;
                                                *(ulonglong *)(&stack0x00002ee8 + lVar8) =
                                                     uVar9 ^ 0x4c997cb530b11ca2;
                                                *(ulonglong *)(&stack0x00002ee0 + lVar8) =
                                                     uVar9 ^ 0x38759c8ec5025841;
                                                *(ulonglong *)(&stack0x00002ed8 + lVar8) =
                                                     uVar9 ^ 0x30659138781feab9;
                                                *(ulonglong *)(&stack0x00002ed0 + lVar8) =
                                                     uVar9 ^ 0x28e980e0a18c19e9;
                                                *(ulonglong *)(&stack0x00002ec8 + lVar8) =
                                                     uVar9 ^ 0x4953d3493ef1b2bb;
                                                *(ulonglong *)(&stack0x00002ec0 + lVar8) =
                                                     uVar9 ^ 0x7fee6e17c59b6020;
                                                *(ulonglong *)(&stack0x00002eb8 + lVar8) =
                                                     uVar9 ^ 0x6499109f6b238119;
                                                *(ulonglong *)(&stack0x00002eb0 + lVar8) =
                                                     uVar9 ^ 0x708aaa23f3bdb3db;
                                                *(ulonglong *)(&stack0x00002ea8 + lVar8) =
                                                     uVar9 ^ 0x40a0e3dc7c19b6ed;
                                                *(ulonglong *)(&stack0x00002ea0 + lVar8) =
                                                     uVar9 ^ 0x755d9ccd1449b9cb;
                                                *(ulonglong *)(&stack0x00002e98 + lVar8) =
                                                     uVar9 ^ 0x31a55c7c16c91862;
                                                *(ulonglong *)(&stack0x00002e90 + lVar8) =
                                                     uVar9 ^ 0x1a881af277fe8302;
                                                *(ulonglong *)(&stack0x00002e88 + lVar8) =
                                                     uVar9 ^ 0x2319187d3dd7a7d;
                                                *(ulonglong *)(&stack0x00002e80 + lVar8) =
                                                     uVar9 ^ 0xd13b4c17b3941c3;
                                                *(ulonglong *)(&stack0x00002e78 + lVar8) =
                                                     uVar9 ^ 0x18d42cd41b93aa0c;
                                                *(ulonglong *)(&stack0x00002e70 + lVar8) =
                                                     uVar9 ^ 0x5507c5e84e81de55;
                                                *(ulonglong *)(&stack0x00002e68 + lVar8) =
                                                     uVar9 ^ 0x12c7e6dab03e5ca;
                                                *(ulonglong *)(&stack0x00002e60 + lVar8) =
                                                     uVar9 ^ 0x4a0687ccc3b93869;
                                                *(ulonglong *)(&stack0x00002e58 + lVar8) =
                                                     uVar9 ^ 0x45cbadd54a49ea2;
                                                *(ulonglong *)(&stack0x00002e50 + lVar8) =
                                                     uVar9 ^ 0x4da9943c9cd918a2;
                                                *(ulonglong *)(&stack0x00002e48 + lVar8) =
                                                     uVar9 ^ 0x45d21ea88ce64cf0;
                                                *(ulonglong *)(&stack0x00002e40 + lVar8) =
                                                     uVar9 ^ 0x38ea40806b1511b6;
                                                *(ulonglong *)(&stack0x00002e38 + lVar8) =
                                                     uVar9 ^ 0x51b9858b4bf16b8a;
                                                *(ulonglong *)(&stack0x00002e30 + lVar8) =
                                                     uVar9 ^ 0x6857c98990fd4ef3;
                                                *(ulonglong *)(&stack0x00002e28 + lVar8) =
                                                     uVar9 ^ 0x460d97f8c4e9a528;
                                                *(ulonglong *)(&stack0x00002e20 + lVar8) =
                                                     uVar9 ^ 0xf97df0970333252;
                                                *(ulonglong *)(&stack0x00002e18 + lVar8) =
                                                     uVar9 ^ 0x117b16cee5e45dd8;
                                                *(ulonglong *)(&stack0x00002e10 + lVar8) =
                                                     uVar9 ^ 0x1f7f93214b4ae51b;
                                                *(ulonglong *)(&stack0x00002e08 + lVar8) =
                                                     uVar9 ^ 0x744afc8019a4cd5e;
                                                *(ulonglong *)(&stack0x00002e00 + lVar8) =
                                                     uVar9 ^ 0x7c34c81007a34e13;
                                                *(ulonglong *)(&stack0x00002df8 + lVar8) =
                                                     uVar9 ^ 0x38715325025ae0bd;
                                                *(ulonglong *)(&stack0x00002df0 + lVar8) =
                                                     uVar9 ^ 0x38c9583f69b7532f;
                                                *(ulonglong *)(&stack0x00002de8 + lVar8) =
                                                     uVar9 ^ 0x6893e225b3ca0448;
                                                *(ulonglong *)(&stack0x00002de0 + lVar8) =
                                                     uVar9 ^ 0x1620e439431a1053;
                                                *(ulonglong *)(&stack0x00002dd8 + lVar8) =
                                                     uVar9 ^ 0x14e35b8ae1bdbc0d;
                                                *(ulonglong *)(&stack0x00002dd0 + lVar8) =
                                                     uVar9 ^ 0xa1cb639d31d7878;
                                                *(ulonglong *)(&stack0x00002dc8 + lVar8) =
                                                     uVar9 ^ 0x47d79bffd029d240;
                                                *(ulonglong *)(&stack0x00002dc0 + lVar8) =
                                                     uVar9 ^ 0x6e9ea6279cd0c1f0;
                                                *(ulonglong *)(&stack0x00002db8 + lVar8) =
                                                     uVar9 ^ 0x1e51c78e007145f2;
                                                *(ulonglong *)(&stack0x00002db0 + lVar8) =
                                                     uVar9 ^ 0x544cfdd73e4c66d0;
                                                *(ulonglong *)(&stack0x00002da8 + lVar8) =
                                                     uVar9 ^ 0x515b1ecdfdc08bed;
                                                *(ulonglong *)(&stack0x00002da0 + lVar8) =
                                                     uVar9 ^ 0x189cb4116e76dbef;
                                                *(ulonglong *)(&stack0x00002d98 + lVar8) =
                                                     uVar9 ^ 0x213f18f132c1ac53;
                                                *(ulonglong *)(&stack0x00002d90 + lVar8) =
                                                     uVar9 ^ 0x190a0a1f15a54464;
                                                *(ulonglong *)(&stack0x00002d88 + lVar8) =
                                                     uVar9 ^ 0xf133b9504ad7611;
                                                *(ulonglong *)(&stack0x00002d80 + lVar8) =
                                                     uVar9 ^ 0x345e8c4071044468;
                                                *(ulonglong *)(&stack0x00002d78 + lVar8) =
                                                     uVar9 ^ 0x4b357a81f7048f01;
                                                *(ulonglong *)(&stack0x00002d70 + lVar8) =
                                                     uVar9 ^ 0xd8ff4294f530de4;
                                                *(ulonglong *)(&stack0x00002d68 + lVar8) =
                                                     uVar9 ^ 0x1f74aa7d8659d979;
                                                *(ulonglong *)(&stack0x00002d60 + lVar8) =
                                                     uVar9 ^ 0xaa72b75198d5141;
                                                *(ulonglong *)(&stack0x00002f28 + lVar8) =
                                                     uVar9 >> 0x30;
                                                *(int *)(&stack0x0000071c + lVar8) =
                                                     (int)(short)(uVar9 >> 0x30);
                                                *(undefined8 *)(&stack0x00000188 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x00002f58 + lVar8) =
                                                     0xae3d67a304fee773;
                                                *(undefined8 *)(&stack0x00000118 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x000000a8 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x00000190 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000164 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x00000760 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x000000f8 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000720 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000168 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000108 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000724 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000148 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x00000730 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x000000b0 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x00000138 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x000000b8 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000154 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x00000140 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x000000c0 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000130 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000100 + lVar8) = 0;
                                                *(undefined4 *)(&stack0x0000016c + lVar8) = 0;
                                                *(undefined4 *)(&stack0x00000158 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x00000738 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x00000758 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x000000c8 + lVar8) = 0;
                                                *(undefined8 *)(&stack0x00000110 + lVar8) = 0;
                                                if (DAT_1804b3668 != 0) {
                                                  return;
                                                }
                                                lVar3 = **(longlong **)
                                                          ((longlong)auStackX_8 + lVar8);
                                                *(undefined8 *)(&stack0x00000078 + lVar8) =
                                                     *(undefined8 *)(lVar3 + 0x390);
                                                if (DAT_1804b59f8 == 0) {
                                                  return;
                                                }
                                                *(undefined8 *)(&stack0x00000080 + lVar8) =
                                                     DAT_1804b59f0;
                                                pcVar1 = *(code **)(lVar3 + 0x518);
                                                *(undefined2 *)(&stack0x000006f4 + lVar8) = 0x70;
                                                plVar2 = *(longlong **)
                                                          ((longlong)auStackX_8 + lVar8);
                                                *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                     0x1803dc4e7;
                                                uVar11 = (*pcVar1)(plVar2,&stack0x000006f4 + lVar8,1
                                                                  );
                                                *(undefined8 *)(&stack0x00000098 + lVar8) = uVar11;
                                                lVar3 = *plVar2;
                                                *(undefined8 *)(&stack0x00000028 + lVar8) =
                                                     *(undefined8 *)(lVar3 + 0x390);
                                                if (DAT_1804b4d30 == 0) {
                                                  return;
                                                }
                                                if (DAT_1804b4d38 != 0) {
                                                  pcVar1 = *(code **)(lVar3 + 0x518);
                                                  *(undefined8 *)(&stack0x00002d50 + lVar8) =
                                                       0x29004a00490028;
                                                  *(undefined2 *)(&stack0x00002d58 + lVar8) = 0x49;
                                                  *(undefined8 *)((longlong)&uStack_48 + lVar8) =
                                                       0x1803dc559;
                                                  (*pcVar1)(*(undefined8 *)
                                                             ((longlong)auStackX_8 + lVar8),
                                                            &stack0x00002d50 + lVar8,5);
                                                  if (DAT_1804b4d40 != 0) {
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

