/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=118 entry=18030edde blocks=39 maxInstructions=50000 */

/* cut edge: 0x18030edde -> 0x180333186 */
/* cut edge: 0x18030eed5 -> 0x180333186 */
/* cut edge: 0x18030eeeb -> 0x180333186 */
/* cut edge: 0x18030f09e -> 0x180333186 */
/* cut edge: 0x18030f198 -> 0x180333186 */
/* cut edge: 0x18030f1ae -> 0x180333186 */
/* cut edge: 0x18030f35c -> 0x180333186 */
/* cut edge: 0x18030f451 -> 0x180333186 */
/* cut edge: 0x18030f467 -> 0x180333186 */
/* cut edge: 0x18030f61b -> 0x180333186 */
/* cut edge: 0x18030f717 -> 0x18029ad3c */
/* cut edge: 0x18030f73e -> 0x18031967a */
/* cut edge: 0x18030f75a -> 0x180333186 */
/* cut edge: 0x18030f762 -> 0x180333186 */
/* cut edge: 0x18030f7ca -> 0x18032d1ab */
/* cut edge: 0x18030f7f3 -> 0x18032d1e3 */
/* cut edge: 0x18030f84f -> 0x18032d234 */
/* cut edge: 0x18030f85f -> 0x18032d26d */
/* cut edge: 0x18030f937 -> 0x180333186 */
/* cut edge: 0x18030fa2e -> 0x180333186 */
/* cut edge: 0x18030fa44 -> 0x180333186 */
/* cut edge: 0x18030faaf -> 0x18032d2a4 */
/* cut edge: 0x18030fad8 -> 0x18032d2dc */
/* cut edge: 0x18030fb37 -> 0x18032d32d */
/* cut edge: 0x18030fb47 -> 0x18032d366 */
/* cut edge: 0x18030fc22 -> 0x180333186 */
/* cut edge: 0x18030fd19 -> 0x180333186 */
/* cut edge: 0x18030fd2f -> 0x180333186 */
/* cut edge: 0x18030fd45 -> 0x18030ff02 */
/* cut edge: 0x18030fd45 -> 0x18030fd97 */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x18030fd97 original byte 0x48 replaced by RET */
/* target 0x18030ff02 original byte 0x48 replaced by RET */
/* target 0x18031967a original byte 0x48 replaced by RET */
/* target 0x18032d1ab original byte 0x48 replaced by RET */
/* target 0x18032d1e3 original byte 0x48 replaced by RET */
/* target 0x18032d234 original byte 0x48 replaced by RET */
/* target 0x18032d26d original byte 0x48 replaced by RET */
/* target 0x18032d2a4 original byte 0x48 replaced by RET */
/* target 0x18032d2dc original byte 0x48 replaced by RET */
/* target 0x18032d32d original byte 0x48 replaced by RET */
/* target 0x18032d366 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


ulonglong region_00118_18030edde(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  ulonglong uVar8;
  ulonglong uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong *in_stack_00000040;
  longlong in_stack_00000060;
  ulonglong in_stack_00000068;
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
  
  uVar5 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,param_2,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1166);
  (*pcVar1)(in_stack_00000040,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar6,1,uVar7);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar5,in_stack_000000a8,uVar6)
  ;
  uVar8 = (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar5,in_stack_000000d0);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if ((char)uVar9 == '\0') {
    uVar9 = (**(code **)(*in_stack_00000040 + 0x720))();
    if ((char)uVar9 == '\0') {
      uVar9 = (**(code **)(*in_stack_00000040 + 0x720))();
      if ((char)uVar9 == '\0') {
        if (DAT_1804b0fa0 == 0) {
          lVar2 = *in_stack_00000040;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar6 = FUN_1803335f0(in_stack_00000040);
          uVar5 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00000566,1);
          lVar2 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000040);
          uVar11 = FUN_180002550(in_stack_00000040);
          uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000023d8,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000040);
          uVar13 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar13);
          (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
          uVar5 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar6,uVar5,uVar7);
          DAT_1804b0fa0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
        }
        uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b0fa0,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x2aac);
        (*pcVar1)(in_stack_00000040,uVar6,0,uVar7);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000060 % 0x92d0f ^ in_stack_000000b0 ^ 0x59c787a7eef7971);
        (*pcVar1)(in_stack_00000040,uVar6,1,uVar7);
        uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar5,in_stack_000000a8,uVar6);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar5,in_stack_000000d0);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if ((char)uVar9 == '\0') {
          uVar9 = (**(code **)(*in_stack_00000040 + 0x720))();
          if ((char)uVar9 == '\0') {
            uVar9 = (**(code **)(*in_stack_00000040 + 0x720))();
            if ((char)uVar9 == '\0') {
              if (DAT_1804b0fa8 == 0) {
                lVar2 = *in_stack_00000040;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar6 = FUN_1803335f0(in_stack_00000040);
                uVar5 = DAT_1804b59f0;
                uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00000564,1);
                lVar2 = *in_stack_00000040;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000040);
                uVar11 = FUN_180002550(in_stack_00000040);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000023c8,5);
                pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000040);
                uVar13 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar13);
                (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
                uVar5 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar6,uVar5,uVar7);
                DAT_1804b0fa8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
              }
              uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b0fa8,in_stack_000000a0);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1163);
              (*pcVar1)(in_stack_00000040,uVar6,0,uVar7);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000060 % 0x7c684 ^ in_stack_000000b0 ^
                                 0x1e2af200b3bce94c);
              (*pcVar1)(in_stack_00000040,uVar6,1,uVar7);
              uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar5,in_stack_000000a8,uVar6);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar5,in_stack_000000d0);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if ((char)uVar9 == '\0') {
                uVar9 = (**(code **)(*in_stack_00000040 + 0x720))();
                if ((char)uVar9 == '\0') {
                  uVar9 = (**(code **)(*in_stack_00000040 + 0x720))();
                  if ((char)uVar9 == '\0') {
                    if (DAT_1804b0fb0 == 0) {
                      lVar2 = *in_stack_00000040;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar6 = FUN_1803335f0(in_stack_00000040);
                      uVar5 = DAT_1804b59f0;
                      uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00000562,1);
                      lVar2 = *in_stack_00000040;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar10 = FUN_1800024f0(in_stack_00000040);
                      uVar11 = FUN_180002550(in_stack_00000040);
                      uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000023b8,5);
                      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                      uVar13 = FUN_1800025a0(in_stack_00000040);
                      uVar13 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar13);
                      (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
                      uVar5 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar6,uVar5,uVar7);
                      DAT_1804b0fb0 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
                    }
                    uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,DAT_1804b0fb0,in_stack_000000a0);
                    uVar6 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x66b9)
                    ;
                    (*pcVar1)(in_stack_00000040,uVar6,0,uVar7);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                       in_stack_00000060 % 0x132e4 ^ in_stack_000000b0 ^
                                       0x7909aab143ac6004);
                    (*pcVar1)(in_stack_00000040,uVar6,1,uVar7);
                    uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,uVar5,in_stack_000000a8,uVar6);
                    (**(code **)(*in_stack_00000040 + 0x1a0))
                              (in_stack_00000040,uVar5,in_stack_000000d0);
                    uVar9 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if ((char)uVar9 == '\0') {
                      return uVar8 ^ in_stack_00000068;
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
  return uVar9;
}

