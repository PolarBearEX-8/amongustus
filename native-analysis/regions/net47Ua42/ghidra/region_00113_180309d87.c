/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=113 entry=180309d87 blocks=34 maxInstructions=50000 */

/* cut edge: 0x180309d87 -> 0x180333186 */
/* cut edge: 0x180309dcb -> 0x180333186 */
/* cut edge: 0x180309e21 -> 0x180333186 */
/* cut edge: 0x180309e44 -> 0x180333186 */
/* cut edge: 0x18030a021 -> 0x180333186 */
/* cut edge: 0x18030a16f -> 0x180333186 */
/* cut edge: 0x18030a185 -> 0x180333186 */
/* cut edge: 0x18030a32e -> 0x180333186 */
/* cut edge: 0x18030a425 -> 0x180333186 */
/* cut edge: 0x18030a43b -> 0x180333186 */
/* cut edge: 0x18030a5e7 -> 0x180333186 */
/* cut edge: 0x18030a6de -> 0x180333186 */
/* cut edge: 0x18030a6f4 -> 0x180333186 */
/* cut edge: 0x18030a8ab -> 0x180333186 */
/* cut edge: 0x18030a9a0 -> 0x180333186 */
/* cut edge: 0x18030a9b6 -> 0x180333186 */
/* cut edge: 0x18030ab72 -> 0x180308101 */
/* cut edge: 0x18030ac38 -> 0x180333186 */
/* cut edge: 0x18030ac40 -> 0x180333186 */
/* cut edge: 0x18030ac56 -> 0x18030ae0e */
/* cut edge: 0x18030aca8 -> 0x18032caeb */
/* cut edge: 0x18030accc -> 0x18032cb21 */
/* cut edge: 0x18030ad28 -> 0x18032cb72 */
/* cut edge: 0x18030ad38 -> 0x18032cbab */
/* cut edge: 0x18030ad38 -> 0x18030ad90 */
/* target 0x180308101 original byte 0xff replaced by RET */
/* target 0x18030ad90 original byte 0x48 replaced by RET */
/* target 0x18030ae0e original byte 0x48 replaced by RET */
/* target 0x18032caeb original byte 0x48 replaced by RET */
/* target 0x18032cb21 original byte 0x48 replaced by RET */
/* target 0x18032cb72 original byte 0x48 replaced by RET */
/* target 0x18032cbab original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


longlong region_00113_180309d87(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  longlong lVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  longlong in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_00000150;
  undefined8 in_stack_000006e8;
  
  pcVar1 = *(code **)(*in_stack_00000040 + 0x420);
  uVar4 = FUN_18001ed70(in_stack_00000040);
  uVar5 = FUN_18001f080(in_stack_00000040);
  (*pcVar1)(in_stack_00000040,uVar4,uVar5);
  lVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if ((char)lVar6 == '\0') {
    pcVar1 = *(code **)(*in_stack_00000040 + 0x2d8);
    uVar4 = FUN_18001f0d0(in_stack_00000040);
    (*pcVar1)(in_stack_00000040,in_stack_00000060,in_stack_00000068,uVar4,in_stack_00000048);
    lVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if ((char)lVar6 == '\0') {
      lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
      if ((char)lVar6 == '\0') {
        lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        if ((char)lVar6 == '\0') {
          if (DAT_1804b0fd0 == 0) {
            lVar6 = *in_stack_00000040;
            pcVar1 = *(code **)(lVar6 + 0x390);
            uVar5 = FUN_180333500(in_stack_00000040);
            uVar4 = DAT_1804b59f0;
            uVar7 = (**(code **)(lVar6 + 0x518))(in_stack_00000040,&stack0x0000055a,1);
            lVar6 = *in_stack_00000040;
            pcVar2 = *(code **)(lVar6 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000040);
            uVar9 = FUN_180002550(in_stack_00000040);
            uVar10 = (**(code **)(lVar6 + 0x518))(in_stack_00000040,&stack0x00003c30,0x2a);
            pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000040);
            uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
            (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
            uVar4 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar5,uVar4,uVar7);
            DAT_1804b0fd0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
          }
          uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                            (in_stack_00000040,DAT_1804b0fd0,in_stack_000000a0);
          uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,5,in_stack_00000090,0)
          ;
          (**(code **)(*in_stack_00000040 + 0x570))(in_stack_00000040,uVar5,0,in_stack_00000150);
          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_000006e8
                            );
          (*pcVar1)(in_stack_00000040,uVar5,1,uVar7);
          (**(code **)(*in_stack_00000040 + 0x570))(in_stack_00000040,uVar5,2,in_stack_00000060);
          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                             in_stack_00000070 % 0xb14e8 ^ 0xdf38950039efef0a);
          (*pcVar1)(in_stack_00000040,uVar5,3,uVar7);
          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_000000b0
                            );
          (*pcVar1)(in_stack_00000040,uVar5,4,uVar7);
          (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar4,in_stack_000000a8,uVar5)
          ;
          lVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
          if ((char)lVar6 == '\0') {
            lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
            if ((char)lVar6 == '\0') {
              lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
              if ((char)lVar6 == '\0') {
                if (DAT_1804b0fd8 == 0) {
                  lVar6 = *in_stack_00000040;
                  pcVar1 = *(code **)(lVar6 + 0x390);
                  uVar5 = FUN_1803335f0(in_stack_00000040);
                  uVar4 = DAT_1804b59f0;
                  uVar7 = (**(code **)(lVar6 + 0x518))(in_stack_00000040,&stack0x00000558,1);
                  lVar6 = *in_stack_00000040;
                  pcVar2 = *(code **)(lVar6 + 0x390);
                  uVar8 = FUN_1800024f0(in_stack_00000040);
                  uVar9 = FUN_180002550(in_stack_00000040);
                  uVar10 = (**(code **)(lVar6 + 0x518))(in_stack_00000040,&stack0x000023a8,5);
                  pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                  uVar11 = FUN_1800025a0(in_stack_00000040);
                  uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
                  (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
                  uVar4 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar5,uVar4,uVar7);
                  DAT_1804b0fd8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
                }
                uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                  (in_stack_00000040,DAT_1804b0fd8,in_stack_000000a0);
                uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                  (in_stack_00000040,2,in_stack_00000090,0);
                pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1288);
                (*pcVar1)(in_stack_00000040,uVar5,0,uVar7);
                pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   in_stack_00000070 % 0x2984a ^ in_stack_000000b0 ^
                                   0x3e12d3972ca28755);
                (*pcVar1)(in_stack_00000040,uVar5,1,uVar7);
                uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                  (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
                (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar4,in_stack_000000d0)
                ;
                lVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                if ((char)lVar6 == '\0') {
                  lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                  if ((char)lVar6 == '\0') {
                    lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                    if ((char)lVar6 == '\0') {
                      if (DAT_1804b0fe0 == 0) {
                        lVar6 = *in_stack_00000040;
                        pcVar1 = *(code **)(lVar6 + 0x390);
                        uVar5 = FUN_1803335f0(in_stack_00000040);
                        uVar4 = DAT_1804b59f0;
                        uVar7 = (**(code **)(lVar6 + 0x518))(in_stack_00000040,&stack0x00000556,1);
                        lVar6 = *in_stack_00000040;
                        pcVar2 = *(code **)(lVar6 + 0x390);
                        uVar8 = FUN_1800024f0(in_stack_00000040);
                        uVar9 = FUN_180002550(in_stack_00000040);
                        uVar10 = (**(code **)(lVar6 + 0x518))(in_stack_00000040,&stack0x00002398,5);
                        pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                        uVar11 = FUN_1800025a0(in_stack_00000040);
                        uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
                        (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
                        uVar4 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar5,uVar4,uVar7);
                        DAT_1804b0fe0 =
                             (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
                        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
                      }
                      uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                        (in_stack_00000040,DAT_1804b0fe0,in_stack_000000a0);
                      uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                        (in_stack_00000040,2,in_stack_00000090,0);
                      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,
                                         0x5471);
                      (*pcVar1)(in_stack_00000040,uVar5,0,uVar7);
                      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                         in_stack_00000070 % 0xe42c2 ^ in_stack_000000b0 ^
                                         0xc0e6827edd66218);
                      (*pcVar1)(in_stack_00000040,uVar5,1,uVar7);
                      uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                        (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
                      (**(code **)(*in_stack_00000040 + 0x1a0))
                                (in_stack_00000040,uVar4,in_stack_000000d0);
                      lVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                      if ((char)lVar6 == '\0') {
                        lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                        if ((char)lVar6 == '\0') {
                          lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                          if ((char)lVar6 == '\0') {
                            if (DAT_1804b0fe8 == 0) {
                              lVar6 = *in_stack_00000040;
                              pcVar1 = *(code **)(lVar6 + 0x390);
                              uVar5 = FUN_1803335f0(in_stack_00000040);
                              uVar4 = DAT_1804b59f0;
                              uVar7 = (**(code **)(lVar6 + 0x518))
                                                (in_stack_00000040,&stack0x00000554,1);
                              lVar6 = *in_stack_00000040;
                              pcVar2 = *(code **)(lVar6 + 0x390);
                              uVar8 = FUN_1800024f0(in_stack_00000040);
                              uVar9 = FUN_180002550(in_stack_00000040);
                              uVar10 = (**(code **)(lVar6 + 0x518))
                                                 (in_stack_00000040,&stack0x00002388,5);
                              pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                              uVar11 = FUN_1800025a0(in_stack_00000040);
                              uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
                              (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
                              uVar4 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar5,uVar4,
                                                uVar7);
                              DAT_1804b0fe8 =
                                   (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4)
                              ;
                              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
                            }
                            uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                              (in_stack_00000040,DAT_1804b0fe8,in_stack_000000a0);
                            uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                              (in_stack_00000040,2,in_stack_00000090,0);
                            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0
                                               ,0x6bfd);
                            (*pcVar1)(in_stack_00000040,uVar5,0,uVar7);
                            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                            uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                              (in_stack_00000040,in_stack_00000088,in_stack_00000098
                                               ,in_stack_00000070 % 0xdf29e ^ in_stack_000000b0 ^
                                                0x3120509d82f809d0);
                            (*pcVar1)(in_stack_00000040,uVar5,1,uVar7);
                            uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                              (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
                            (**(code **)(*in_stack_00000040 + 0x1a0))
                                      (in_stack_00000040,uVar4,in_stack_000000d0);
                            lVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                            if ((char)lVar6 == '\0') {
                              lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                              if ((char)lVar6 == '\0') {
                                lVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                                if ((char)lVar6 == '\0') {
                                  if (DAT_1804b0ff0 == 0) {
                                    lVar6 = *in_stack_00000040;
                                    pcVar1 = *(code **)(lVar6 + 0x390);
                                    uVar5 = FUN_1803335f0(in_stack_00000040);
                                    uVar4 = DAT_1804b59f0;
                                    uVar7 = (**(code **)(lVar6 + 0x518))
                                                      (in_stack_00000040,&stack0x00000552,1);
                                    lVar6 = *in_stack_00000040;
                                    pcVar2 = *(code **)(lVar6 + 0x390);
                                    uVar8 = FUN_1800024f0(in_stack_00000040);
                                    uVar9 = FUN_180002550(in_stack_00000040);
                                    uVar10 = (**(code **)(lVar6 + 0x518))
                                                       (in_stack_00000040,&stack0x00002378,5);
                                    pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
                                    uVar11 = FUN_1800025a0(in_stack_00000040);
                                    uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
                                    (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
                                    uVar4 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar5,
                                                      uVar4,uVar7);
                                    DAT_1804b0ff0 =
                                         (**(code **)(*in_stack_00000040 + 0xa8))
                                                   (in_stack_00000040,uVar4);
                                    (**(code **)(*in_stack_00000040 + 0xb8))
                                              (in_stack_00000040,uVar4);
                                  }
                                  (**(code **)(*in_stack_00000040 + 0x110))
                                            (in_stack_00000040,DAT_1804b0ff0,in_stack_000000a0);
                                  uVar4 = (**(code **)(*in_stack_00000040 + 0x560))
                                                    (in_stack_00000040,2,in_stack_00000090,0);
                                  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                                  uVar5 = (**(code **)(*in_stack_00000040 + 0x390))
                                                    (in_stack_00000040,in_stack_000000b8,
                                                     in_stack_000000c0,0x7ce0);
                                  (*pcVar1)(in_stack_00000040,uVar4,0,uVar5);
                                  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                                  uVar5 = (**(code **)(*in_stack_00000040 + 0x390))
                                                    (in_stack_00000040,in_stack_00000088,
                                                     in_stack_00000098,
                                                     in_stack_00000070 % 0x6fb87 ^ in_stack_000000b0
                                                     ^ 0x119dbe9c5cb4ca6a);
                                  (*pcVar1)(in_stack_00000040,uVar4,1,uVar5);
                                  return *in_stack_00000040;
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
  return lVar6;
}

