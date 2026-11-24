/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=118 entry=180451682 blocks=31 maxInstructions=50000 */

/* cut edge: 0x180451682 -> 0x180473ae8 */
/* cut edge: 0x1804516ba -> 0x180473ae8 */
/* cut edge: 0x1804516fb -> 0x180473ae8 */
/* cut edge: 0x18045173c -> 0x180473ae8 */
/* cut edge: 0x180451785 -> 0x180473ae8 */
/* cut edge: 0x18045179b -> 0x180473ae8 */
/* cut edge: 0x18045194a -> 0x180473ae8 */
/* cut edge: 0x180451a3c -> 0x180473ae8 */
/* cut edge: 0x180451a52 -> 0x180473ae8 */
/* cut edge: 0x180451c04 -> 0x180473ae8 */
/* cut edge: 0x180451cf6 -> 0x180473ae8 */
/* cut edge: 0x180451d0c -> 0x180473ae8 */
/* cut edge: 0x180451ebb -> 0x180473ae8 */
/* cut edge: 0x180451fb0 -> 0x180473ae8 */
/* cut edge: 0x180451fc6 -> 0x180473ae8 */
/* cut edge: 0x180452185 -> 0x180473ae8 */
/* cut edge: 0x18045227a -> 0x1804512ef */
/* cut edge: 0x18045229e -> 0x180452d84 */
/* cut edge: 0x1804522a3 -> 0x180473ae8 */
/* cut edge: 0x1804522ab -> 0x180473ae8 */
/* cut edge: 0x180452457 -> 0x180473ae8 */
/* cut edge: 0x180452457 -> 0x180452549 */
/* target 0x1804512ef original byte 0x48 replaced by RET */
/* target 0x180452549 original byte 0x48 replaced by RET */
/* target 0x180452d84 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00118_180451682(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  undefined4 uVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  ulonglong uVar11;
  longlong *in_stack_00000048;
  undefined4 uStack0000000000000050;
  undefined4 uStack0000000000000054;
  undefined8 in_stack_00000058;
  code *in_stack_00000060;
  longlong in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined4 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_00000100;
  longlong in_stack_00000118;
  undefined8 in_stack_00000138;
  code *in_stack_00000148;
  undefined8 in_stack_00000178;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x568))
                    (in_stack_00000048,in_stack_00000178,in_stack_000000e8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048);
    uVar4 = uStack0000000000000050;
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar4);
      (**(code **)(*in_stack_00000048 + 0x698))
                (in_stack_00000048,uVar6,in_stack_00000058,1,&stack0x00000050);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048);
        in_stack_000000b0 = uStack0000000000000050;
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              uVar11 = in_stack_00000068 % 0x80c29 ^ in_stack_000000c8;
              if (DAT_1804b4408 == 0) {
                lVar1 = *in_stack_00000048;
                in_stack_00000060 = *(code **)(lVar1 + 0x390);
                in_stack_00000070 = FUN_1803335f0(in_stack_00000048);
                in_stack_00000100 = DAT_1804b59f0;
                in_stack_00000138 =
                     (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003cc,1);
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar7 = FUN_1800024f0(in_stack_00000048);
                uVar8 = FUN_180002550(in_stack_00000048);
                uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000012f0,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar10 = FUN_1800025a0(in_stack_00000048);
                uVar6 = in_stack_00000088;
                uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
                (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                uVar6 = (*in_stack_00000060)
                                  (in_stack_00000048,uVar6,in_stack_00000070,in_stack_00000100,
                                   in_stack_00000138);
                DAT_1804b4408 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              _uStack0000000000000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b4408,in_stack_000000a0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5500);
              (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 uVar11 ^ 0xe56d498584f588bf);
              (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,_uStack0000000000000050,in_stack_000000a8,uVar6);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    uVar11 = in_stack_00000068 % 0x18fe9 ^ in_stack_000000c8;
                    if (DAT_1804b4410 == 0) {
                      lVar1 = *in_stack_00000048;
                      in_stack_00000060 = *(code **)(lVar1 + 0x390);
                      in_stack_00000070 = FUN_1803335f0(in_stack_00000048);
                      in_stack_00000100 = DAT_1804b59f0;
                      in_stack_00000138 =
                           (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003ca,1);
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_1800024f0(in_stack_00000048);
                      uVar8 = FUN_180002550(in_stack_00000048);
                      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000012e0,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar10 = FUN_1800025a0(in_stack_00000048);
                      uVar6 = in_stack_00000088;
                      uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
                      (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                      uVar6 = (*in_stack_00000060)
                                        (in_stack_00000048,uVar6,in_stack_00000070,in_stack_00000100
                                         ,in_stack_00000138);
                      DAT_1804b4410 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    _uStack0000000000000050 =
                         (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b4410,in_stack_000000a0);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5289)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       uVar11 ^ 0x8ffdac2780234a85);
                    (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,_uStack0000000000000050,in_stack_000000a8,
                                       uVar6);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar6,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          uVar11 = in_stack_00000068 % 0x7a8bb ^ in_stack_000000c8;
                          if (DAT_1804b4418 == 0) {
                            lVar1 = *in_stack_00000048;
                            in_stack_00000060 = *(code **)(lVar1 + 0x390);
                            in_stack_00000070 = FUN_1803335f0(in_stack_00000048);
                            in_stack_00000100 = DAT_1804b59f0;
                            in_stack_00000138 =
                                 (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003c8,1);
                            lVar1 = *in_stack_00000048;
                            pcVar2 = *(code **)(lVar1 + 0x390);
                            uVar7 = FUN_1800024f0(in_stack_00000048);
                            uVar8 = FUN_180002550(in_stack_00000048);
                            uVar9 = (**(code **)(lVar1 + 0x518))
                                              (in_stack_00000048,&stack0x000012d0,5);
                            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                            uVar10 = FUN_1800025a0(in_stack_00000048);
                            uVar6 = in_stack_00000088;
                            uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
                            (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                            uVar6 = (*in_stack_00000060)
                                              (in_stack_00000048,uVar6,in_stack_00000070,
                                               in_stack_00000100,in_stack_00000138);
                            DAT_1804b4418 =
                                 (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                          }
                          _uStack0000000000000050 =
                               (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,DAT_1804b4418,in_stack_000000a0);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                            (in_stack_00000048,2,in_stack_00000090,0);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                             0x79af);
                          (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                             uVar11 ^ 0xc1ee3333fe4d3dc6);
                          (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                          uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,_uStack0000000000000050,
                                             in_stack_000000a8,uVar6);
                          (**(code **)(*in_stack_00000048 + 0x1a0))
                                    (in_stack_00000048,uVar6,in_stack_000000d8);
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if (cVar5 == '\0') {
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                uVar11 = in_stack_00000068 % 0xb92f3 ^ in_stack_000000c8;
                                if (DAT_1804b4420 == 0) {
                                  lVar1 = *in_stack_00000048;
                                  in_stack_00000060 = *(code **)(lVar1 + 0x390);
                                  in_stack_00000070 = FUN_1803335f0(in_stack_00000048);
                                  in_stack_00000100 = DAT_1804b59f0;
                                  in_stack_00000138 =
                                       (**(code **)(lVar1 + 0x518))
                                                 (in_stack_00000048,&stack0x000003c6,1);
                                  lVar1 = *in_stack_00000048;
                                  in_stack_00000148 = *(code **)(lVar1 + 0x390);
                                  uVar7 = FUN_1800024f0(in_stack_00000048);
                                  uVar8 = FUN_180002550(in_stack_00000048);
                                  uVar9 = (**(code **)(lVar1 + 0x518))
                                                    (in_stack_00000048,&stack0x000012c0,5);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar10 = FUN_1800025a0(in_stack_00000048);
                                  uVar6 = in_stack_00000088;
                                  uVar10 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar10);
                                  (*in_stack_00000148)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                                  uVar6 = (*in_stack_00000060)
                                                    (in_stack_00000048,uVar6,in_stack_00000070,
                                                     in_stack_00000100,in_stack_00000138);
                                  DAT_1804b4420 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar6);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                                }
                                _uStack0000000000000050 =
                                     (**(code **)(*in_stack_00000048 + 0x110))
                                               (in_stack_00000048,DAT_1804b4420,in_stack_000000a0);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_00000090,0);
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000b8,
                                                   in_stack_000000c0,0x449a);
                                (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000080,
                                                   in_stack_00000098,uVar11 ^ 0xae43dda1c159d761);
                                (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                                  (in_stack_00000048,_uStack0000000000000050,
                                                   in_stack_000000a8,uVar6);
                                (**(code **)(*in_stack_00000048 + 0x1a0))
                                          (in_stack_00000048,uVar6,in_stack_000000d8);
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048)
                                ;
                                if (cVar5 == '\0') {
                                  if (in_stack_00000118 != 0) {
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

