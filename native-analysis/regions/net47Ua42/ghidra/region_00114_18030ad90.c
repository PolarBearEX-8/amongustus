/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=114 entry=18030ad90 blocks=42 maxInstructions=50000 */

/* cut edge: 0x18030ae0e -> 0x180333186 */
/* cut edge: 0x18030af05 -> 0x180333186 */
/* cut edge: 0x18030af1b -> 0x180333186 */
/* cut edge: 0x18030af83 -> 0x18032ccd7 */
/* cut edge: 0x18030afa7 -> 0x18032cd0d */
/* cut edge: 0x18030b003 -> 0x18032cd5e */
/* cut edge: 0x18030b013 -> 0x18032cd97 */
/* cut edge: 0x18030b0e9 -> 0x180333186 */
/* cut edge: 0x18030b1e3 -> 0x180333186 */
/* cut edge: 0x18030b1f9 -> 0x180333186 */
/* cut edge: 0x18030b261 -> 0x18032cec3 */
/* cut edge: 0x18030b285 -> 0x18032cef9 */
/* cut edge: 0x18030b2e1 -> 0x18032cf4a */
/* cut edge: 0x18030b2f1 -> 0x18032cf83 */
/* cut edge: 0x18030b3c7 -> 0x180333186 */
/* cut edge: 0x18030b4bc -> 0x180333186 */
/* cut edge: 0x18030b4d2 -> 0x180333186 */
/* cut edge: 0x18030b53a -> 0x18032d0b3 */
/* cut edge: 0x18030b563 -> 0x18032d0eb */
/* cut edge: 0x18030b5bf -> 0x18032d13c */
/* cut edge: 0x18030b5cf -> 0x18032d175 */
/* cut edge: 0x18030b6a8 -> 0x180333186 */
/* cut edge: 0x18030b79d -> 0x180333186 */
/* cut edge: 0x18030b7b3 -> 0x180333186 */
/* cut edge: 0x18030b96c -> 0x180333186 */
/* cut edge: 0x18030ba60 -> 0x180333186 */
/* cut edge: 0x18030ba8f -> 0x180333186 */
/* cut edge: 0x18030baa5 -> 0x180333186 */
/* cut edge: 0x18030bc53 -> 0x180333186 */
/* cut edge: 0x18030bd4a -> 0x180333186 */
/* cut edge: 0x18030bd60 -> 0x180333186 */
/* cut edge: 0x18030bd60 -> 0x18030bd76 */
/* target 0x18030bd76 original byte 0x48 replaced by RET */
/* target 0x18032ccd7 original byte 0x48 replaced by RET */
/* target 0x18032cd0d original byte 0x48 replaced by RET */
/* target 0x18032cd5e original byte 0x48 replaced by RET */
/* target 0x18032cd97 original byte 0x48 replaced by RET */
/* target 0x18032cec3 original byte 0x48 replaced by RET */
/* target 0x18032cef9 original byte 0x48 replaced by RET */
/* target 0x18032cf4a original byte 0x48 replaced by RET */
/* target 0x18032cf83 original byte 0x48 replaced by RET */
/* target 0x18032d0b3 original byte 0x48 replaced by RET */
/* target 0x18032d0eb original byte 0x48 replaced by RET */
/* target 0x18032d13c original byte 0x48 replaced by RET */
/* target 0x18032d175 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00114_18030ad90(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  code *pcVar4;
  longlong lVar5;
  char cVar6;
  undefined4 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  ulonglong uVar12;
  longlong lVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  code *unaff_R12;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  ulonglong in_stack_00000060;
  code *in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  code *in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000f8;
  
  (*unaff_R12)(in_stack_00000040,in_stack_00000080);
  (*in_stack_000000c8)(in_stack_00000040);
  uVar8 = (*in_stack_00000070)(in_stack_00000040,in_stack_00000080);
  DAT_1804b10a8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar8);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar8);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b10a8,in_stack_000000a0);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x560))
                    (in_stack_00000040,2,in_stack_00000090,0,in_stack_00000048);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                     (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x145a);
  (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                     (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar8,in_stack_000000a8,uVar9)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar8,in_stack_000000d0);
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
      lVar13 = DAT_1804b5a08;
      uVar8 = DAT_1804b59f0;
      if (cVar6 == '\0') {
        if (DAT_1804b10b0 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000522,1);
          lVar5 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00002208,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar11 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar5,uVar10,uVar11);
          uVar8 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar13,uVar8,uVar9);
          DAT_1804b10b0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar8);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar8);
        }
        uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b10b0,in_stack_000000a0);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                           (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x18ac);
        (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                           (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                            (longlong)in_stack_00000060 % 0x9ee8d ^ in_stack_000000b0 ^
                            0x48ccf2a3a471759b);
        (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar8,in_stack_000000a8,uVar9);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar8,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
            lVar13 = DAT_1804b5a08;
            uVar8 = DAT_1804b59f0;
            if (cVar6 == '\0') {
              if (DAT_1804b10b8 == 0) {
                pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x00000520,1);
                lVar5 = DAT_1804b4d38;
                lVar3 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000040 + 0x518))
                                   (in_stack_00000040,&stack0x000021f8,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,in_stack_00000080);
                (*pcVar2)(in_stack_00000040,lVar3,lVar5,uVar10,uVar11);
                uVar8 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar13,uVar8,uVar9);
                DAT_1804b10b8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar8);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar8);
              }
              uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b10b8,in_stack_000000a0);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                 (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7dd7);
              (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                 (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                  (longlong)in_stack_00000060 % 0x89c9c ^ in_stack_000000b0 ^
                                  0x48332282c6912d80);
              (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar8,in_stack_000000a8,uVar9);
              uVar12 = (**(code **)(*in_stack_00000040 + 0x1a0))
                                 (in_stack_00000040,uVar8,in_stack_000000d0);
              cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                  lVar13 = DAT_1804b5a08;
                  uVar8 = DAT_1804b59f0;
                  if (cVar6 == '\0') {
                    if (DAT_1804b10c0 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b5a08 == 0) {
                        return;
                      }
                      uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                        (in_stack_00000040,&stack0x0000051e,1);
                      lVar5 = DAT_1804b4d38;
                      lVar3 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar10 = (**(code **)(*in_stack_00000040 + 0x518))
                                         (in_stack_00000040,&stack0x000021e8,5);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                         (in_stack_00000040,in_stack_00000080);
                      (*pcVar2)(in_stack_00000040,lVar3,lVar5,uVar10,uVar11);
                      uVar8 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar13,uVar8,uVar9);
                      DAT_1804b10c0 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar8);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar8);
                    }
                    uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,DAT_1804b10c0,in_stack_000000a0);
                    uVar9 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                       (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x37d5
                                       );
                    (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                       (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                        (longlong)in_stack_00000060 % 0xca07b ^ in_stack_000000b0 ^
                                        0x6ca7581f5f93db3f);
                    (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
                    uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,uVar8,in_stack_000000a8,uVar9);
                    lVar13 = (**(code **)(*in_stack_00000040 + 0x1a0))
                                       (in_stack_00000040,uVar8,in_stack_000000d0);
                    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if (cVar6 == '\0') {
                      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                      if (cVar6 == '\0') {
                        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                        if (cVar6 == '\0') {
                          lVar13 = (uVar12 ^ in_stack_00000060) - lVar13;
                          if (DAT_1804b0dc8 == 0) {
                            lVar3 = *in_stack_00000040;
                            pcVar1 = *(code **)(lVar3 + 0x390);
                            uVar9 = FUN_180333550(in_stack_00000040);
                            uVar8 = DAT_1804b59f0;
                            uVar10 = (**(code **)(lVar3 + 0x518))
                                               (in_stack_00000040,&stack0x000005de,1);
                            lVar3 = *in_stack_00000040;
                            pcVar2 = *(code **)(lVar3 + 0x390);
                            uVar11 = FUN_1800024f0(in_stack_00000040);
                            uVar14 = FUN_180002550(in_stack_00000040);
                            uVar15 = (**(code **)(lVar3 + 0x518))
                                               (in_stack_00000040,&stack0x00002788,5);
                            pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                            uVar16 = FUN_1800025a0(in_stack_00000040);
                            uVar16 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar16);
                            (*pcVar2)(in_stack_00000040,uVar11,uVar14,uVar15,uVar16);
                            uVar8 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar9,uVar8,uVar10
                                             );
                            DAT_1804b0dc8 =
                                 (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar8);
                            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar8);
                          }
                          uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                            (in_stack_00000040,DAT_1804b0dc8,in_stack_000000a0);
                          uVar9 = (**(code **)(*in_stack_00000040 + 0x560))
                                            (in_stack_00000040,2,in_stack_00000090,0);
                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                             (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,
                                              0x3979);
                          (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
                          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                          uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                             (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                              lVar13 % 0x80cf3 ^ in_stack_000000b0 ^
                                              0x70c2ba5829c089e3);
                          (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
                          uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                            (in_stack_00000040,uVar8,in_stack_000000a8,uVar9);
                          uVar7 = (**(code **)(*in_stack_00000040 + 0x188))
                                            (in_stack_00000040,uVar8,in_stack_000000f8);
                          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                          if (cVar6 == '\0') {
                            (**(code **)(*in_stack_00000040 + 0x580))(in_stack_00000040,uVar7);
                            cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                            if (cVar6 == '\0') {
                              cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                              if (cVar6 == '\0') {
                                cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                                if (cVar6 == '\0') {
                                  if (DAT_1804b0dd0 == 0) {
                                    lVar3 = *in_stack_00000040;
                                    pcVar1 = *(code **)(lVar3 + 0x390);
                                    uVar9 = FUN_1803335f0(in_stack_00000040);
                                    uVar8 = DAT_1804b59f0;
                                    uVar10 = (**(code **)(lVar3 + 0x518))
                                                       (in_stack_00000040,&stack0x000005dc,1);
                                    lVar3 = *in_stack_00000040;
                                    pcVar2 = *(code **)(lVar3 + 0x390);
                                    uVar11 = FUN_1800024f0(in_stack_00000040);
                                    uVar14 = FUN_180002550(in_stack_00000040);
                                    uVar15 = (**(code **)(lVar3 + 0x518))
                                                       (in_stack_00000040,&stack0x00002778,5);
                                    pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                                    uVar16 = FUN_1800025a0(in_stack_00000040);
                                    uVar16 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar16);
                                    (*pcVar2)(in_stack_00000040,uVar11,uVar14,uVar15,uVar16);
                                    uVar8 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar9,
                                                      uVar8,uVar10);
                                    DAT_1804b0dd0 =
                                         (**(code **)(*in_stack_00000040 + 0xa8))
                                                   (in_stack_00000040,uVar8);
                                    (**(code **)(*in_stack_00000040 + 0xb8))
                                              (in_stack_00000040,uVar8);
                                  }
                                  uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                                    (in_stack_00000040,DAT_1804b0dd0,
                                                     in_stack_000000a0);
                                  uVar9 = (**(code **)(*in_stack_00000040 + 0x560))
                                                    (in_stack_00000040,2,in_stack_00000090,0);
                                  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                                  uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                                     (in_stack_00000040,in_stack_000000b8,
                                                      in_stack_000000c0,0x47cc);
                                  (*pcVar1)(in_stack_00000040,uVar9,0,uVar10);
                                  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                                  uVar10 = (**(code **)(*in_stack_00000040 + 0x390))
                                                     (in_stack_00000040,in_stack_00000088,
                                                      in_stack_00000098,
                                                      lVar13 % 0xa9bee ^ in_stack_000000b0 ^
                                                      0x537ef6ff9c759449);
                                  (*pcVar1)(in_stack_00000040,uVar9,1,uVar10);
                                  uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                                    (in_stack_00000040,uVar8,in_stack_000000a8,uVar9
                                                    );
                                  (**(code **)(*in_stack_00000040 + 0x1a0))
                                            (in_stack_00000040,uVar8,in_stack_000000d0);
                                  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))
                                                    (in_stack_00000040);
                                  if (cVar6 == '\0') {
                                    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                                    if (cVar6 == '\0') {
                                      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
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
      }
    }
  }
  return;
}

