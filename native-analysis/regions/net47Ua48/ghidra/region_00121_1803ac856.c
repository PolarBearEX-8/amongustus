/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=121 entry=1803ac856 blocks=49 maxInstructions=50000 */

/* cut edge: 0x1803ac866 -> 0x1803cf63a */
/* cut edge: 0x1803ac88a -> 0x1803cf670 */
/* cut edge: 0x1803ac8ec -> 0x1803cf6c1 */
/* cut edge: 0x1803ac8fc -> 0x1803cf6fb */
/* cut edge: 0x1803ac9eb -> 0x1803c83ea */
/* cut edge: 0x1803acaf0 -> 0x1803351d6 */
/* cut edge: 0x1803acb20 -> 0x1803351d6 */
/* cut edge: 0x1803acb46 -> 0x1803cf732 */
/* cut edge: 0x1803acb6d -> 0x1803cf768 */
/* cut edge: 0x1803acbcf -> 0x1803cf7b9 */
/* cut edge: 0x1803acbdf -> 0x1803cf7f3 */
/* cut edge: 0x1803accef -> 0x1803351d6 */
/* cut edge: 0x1803acdf3 -> 0x1803351d6 */
/* cut edge: 0x1803ace19 -> 0x1803cf82a */
/* cut edge: 0x1803ace40 -> 0x1803cf860 */
/* cut edge: 0x1803acea2 -> 0x1803cf8b1 */
/* cut edge: 0x1803aceb2 -> 0x1803cf8eb */
/* cut edge: 0x1803acfc2 -> 0x1803351d6 */
/* cut edge: 0x1803ad0c6 -> 0x1803351d6 */
/* cut edge: 0x1803ad0ec -> 0x1803cf9a9 */
/* cut edge: 0x1803ad113 -> 0x1803cf9df */
/* cut edge: 0x1803ad175 -> 0x1803cfa30 */
/* cut edge: 0x1803ad185 -> 0x1803cfa6a */
/* cut edge: 0x1803ad295 -> 0x1803351d6 */
/* cut edge: 0x1803ad399 -> 0x1803351d6 */
/* cut edge: 0x1803ad3bf -> 0x1803cfb28 */
/* cut edge: 0x1803ad3e6 -> 0x1803cfb5e */
/* cut edge: 0x1803ad448 -> 0x1803cfbaf */
/* cut edge: 0x1803ad458 -> 0x1803cfbe9 */
/* cut edge: 0x1803ad54a -> 0x1803c841d */
/* cut edge: 0x1803ad64f -> 0x1803351d6 */
/* cut edge: 0x1803ad678 -> 0x1803351d6 */
/* cut edge: 0x1803ad69e -> 0x1803cfcb5 */
/* cut edge: 0x1803ad6c5 -> 0x1803cfceb */
/* cut edge: 0x1803ad727 -> 0x1803cfd3c */
/* cut edge: 0x1803ad737 -> 0x1803cfd76 */
/* cut edge: 0x1803ad829 -> 0x1803351d6 */
/* cut edge: 0x1803ad829 -> 0x1803ad93b */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803ad93b original byte 0x48 replaced by RET */
/* target 0x1803c83ea original byte 0x48 replaced by RET */
/* target 0x1803c841d original byte 0x48 replaced by RET */
/* target 0x1803cf63a original byte 0x48 replaced by RET */
/* target 0x1803cf670 original byte 0x48 replaced by RET */
/* target 0x1803cf6c1 original byte 0x48 replaced by RET */
/* target 0x1803cf6fb original byte 0x48 replaced by RET */
/* target 0x1803cf732 original byte 0x48 replaced by RET */
/* target 0x1803cf768 original byte 0x48 replaced by RET */
/* target 0x1803cf7b9 original byte 0x48 replaced by RET */
/* target 0x1803cf7f3 original byte 0x48 replaced by RET */
/* target 0x1803cf82a original byte 0x48 replaced by RET */
/* target 0x1803cf860 original byte 0x48 replaced by RET */
/* target 0x1803cf8b1 original byte 0x48 replaced by RET */
/* target 0x1803cf8eb original byte 0x48 replaced by RET */
/* target 0x1803cf9a9 original byte 0x48 replaced by RET */
/* target 0x1803cf9df original byte 0x48 replaced by RET */
/* target 0x1803cfa30 original byte 0x48 replaced by RET */
/* target 0x1803cfa6a original byte 0x48 replaced by RET */
/* target 0x1803cfb28 original byte 0x48 replaced by RET */
/* target 0x1803cfb5e original byte 0x48 replaced by RET */
/* target 0x1803cfbaf original byte 0x48 replaced by RET */
/* target 0x1803cfbe9 original byte 0x48 replaced by RET */
/* target 0x1803cfcb5 original byte 0x48 replaced by RET */
/* target 0x1803cfceb original byte 0x48 replaced by RET */
/* target 0x1803cfd3c original byte 0x48 replaced by RET */
/* target 0x1803cfd76 original byte 0x48 replaced by RET */


void region_00121_1803ac856(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  longlong lVar5;
  longlong lVar6;
  char cVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_00000108;
  
  uVar11 = DAT_1804b59f0;
  lVar6 = DAT_1804b59e8;
  if (DAT_1804b3338 == 0) {
    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
    if (DAT_1804b59e8 == 0) {
      return;
    }
    uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000030c,1);
    lVar4 = DAT_1804b4d38;
    lVar3 = DAT_1804b4d30;
    pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
    if (DAT_1804b4d30 == 0) {
      return;
    }
    if (DAT_1804b4d38 == 0) {
      return;
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000045c0,0x17);
    if (DAT_1804b4d40 == 0) {
      return;
    }
    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
    (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
    uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar6,uVar11,uVar8);
    DAT_1804b3338 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
  }
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b3338,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000108);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfe982549095380d);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
  lVar6 = DAT_1804b4fa8;
  if (DAT_1804b4fa8 == 0) {
    return;
  }
  (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,DAT_1804b4fa8);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 == '\0') {
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    uVar11 = DAT_1804b59f0;
    lVar3 = DAT_1804b59e8;
    if (cVar7 == '\0') {
      if (DAT_1804b3340 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b59e8 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000030a,1);
        lVar5 = DAT_1804b4d38;
        lVar4 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00007140,0x28);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
        (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
        uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar3,uVar11,uVar8);
        DAT_1804b3340 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
      }
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b3340,in_stack_00000078);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000108);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfb65ca6f9e88e18);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
      (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
      (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar11,in_stack_00000080,uVar8);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 == '\0') {
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
        uVar11 = DAT_1804b59f0;
        lVar3 = DAT_1804b59e8;
        if (cVar7 == '\0') {
          if (DAT_1804b3348 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59e8 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000308,1);
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
                              (in_stack_00000048,&stack0x000070f0,0x28);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
            uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar3,uVar11,uVar8);
            DAT_1804b3348 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b3348,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,3,in_stack_00000088,0)
          ;
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000108);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                             0xdfea450cef08e499);
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8
                            );
          (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
          (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 == '\0') {
            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
            uVar11 = DAT_1804b59f0;
            lVar3 = DAT_1804b59e8;
            if (cVar7 == '\0') {
              if (DAT_1804b3350 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b59e8 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000306,1);
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
                                  (in_stack_00000048,&stack0x000070a0,0x28);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000058);
                (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
                uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar3,uVar11,uVar8);
                DAT_1804b3350 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
              }
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b3350,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,3,in_stack_00000088,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000108)
              ;
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 0xdfda1b0c766df201);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
              (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar7 == '\0') {
                cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                uVar11 = DAT_1804b59f0;
                lVar3 = DAT_1804b59e8;
                if (cVar7 == '\0') {
                  if (DAT_1804b3358 == 0) {
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                    if (DAT_1804b59e8 == 0) {
                      return;
                    }
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                      (in_stack_00000048,&stack0x00000304,1);
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
                                      (in_stack_00000048,&stack0x00004590,0x17);
                    if (DAT_1804b4d40 == 0) {
                      return;
                    }
                    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,in_stack_00000058);
                    (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
                    uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar3,uVar11,uVar8);
                    DAT_1804b3358 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                  }
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b3358,in_stack_00000078);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,3,in_stack_00000088,0);
                  (**(code **)(*in_stack_00000048 + 0x570))
                            (in_stack_00000048,uVar8,0,in_stack_00000108);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     0xdfdb68205ec57c24);
                  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                     in_stack_000000c8);
                  (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
                  if (DAT_1804b4fb0 == 0) {
                    return;
                  }
                  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11);
                  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar7 == '\0') {
                    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
                    uVar11 = DAT_1804b59f0;
                    lVar3 = DAT_1804b59e8;
                    if (cVar7 == '\0') {
                      if (DAT_1804b3360 == 0) {
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                        if (DAT_1804b59e8 == 0) {
                          return;
                        }
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                          (in_stack_00000048,&stack0x00000302,1);
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
                                          (in_stack_00000048,&stack0x00004560,0x17);
                        if (DAT_1804b4d40 == 0) {
                          return;
                        }
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,in_stack_00000058);
                        (*pcVar2)(in_stack_00000048,lVar4,lVar5,uVar9,uVar10);
                        uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar3,uVar11,uVar8);
                        DAT_1804b3360 =
                             (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                      }
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,DAT_1804b3360,in_stack_00000078);
                      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                        (in_stack_00000048,3,in_stack_00000088,0);
                      (**(code **)(*in_stack_00000048 + 0x570))
                                (in_stack_00000048,uVar8,0,in_stack_00000108);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         0xdfd1a1b82a121f59);
                      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                         in_stack_000000c8);
                      (*pcVar1)(in_stack_00000048,uVar8,2,uVar9);
                      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
                      (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,lVar6);
                      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar7 == '\0') {
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
  return;
}

