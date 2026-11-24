/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=98 entry=1802fadb0 blocks=54 maxInstructions=50000 */

/* cut edge: 0x1802fadb0 -> 0x180306f02 */
/* cut edge: 0x1802fadc0 -> 0x180306f3b */
/* cut edge: 0x1802fae98 -> 0x180333186 */
/* cut edge: 0x1802faf8f -> 0x180333186 */
/* cut edge: 0x1802fafa5 -> 0x180333186 */
/* cut edge: 0x1802fb010 -> 0x18030706b */
/* cut edge: 0x1802fb039 -> 0x1803070a3 */
/* cut edge: 0x1802fb098 -> 0x1803070f4 */
/* cut edge: 0x1802fb0a8 -> 0x18030712d */
/* cut edge: 0x1802fb183 -> 0x180333186 */
/* cut edge: 0x1802fb27a -> 0x180333186 */
/* cut edge: 0x1802fb290 -> 0x180333186 */
/* cut edge: 0x1802fb2f8 -> 0x18030725e */
/* cut edge: 0x1802fb31c -> 0x180307294 */
/* cut edge: 0x1802fb37b -> 0x1803072e5 */
/* cut edge: 0x1802fb38b -> 0x18030731e */
/* cut edge: 0x1802fb463 -> 0x180333186 */
/* cut edge: 0x1802fb558 -> 0x180333186 */
/* cut edge: 0x1802fb56e -> 0x180333186 */
/* cut edge: 0x1802fb5d6 -> 0x18030744a */
/* cut edge: 0x1802fb605 -> 0x180307485 */
/* cut edge: 0x1802fb666 -> 0x1803074df */
/* cut edge: 0x1802fb676 -> 0x180307518 */
/* cut edge: 0x1802fb755 -> 0x180300bf0 */
/* cut edge: 0x1802fb7ba -> 0x180333186 */
/* cut edge: 0x1802fb7d0 -> 0x180333186 */
/* cut edge: 0x1802fb838 -> 0x1803059e1 */
/* cut edge: 0x1802fb861 -> 0x180305a19 */
/* cut edge: 0x1802fb8c0 -> 0x180305a6a */
/* cut edge: 0x1802fb8d0 -> 0x180305aa3 */
/* cut edge: 0x1802fb9ab -> 0x180333186 */
/* cut edge: 0x1802fbaa2 -> 0x180333186 */
/* cut edge: 0x1802fbab8 -> 0x180333186 */
/* cut edge: 0x1802fbb23 -> 0x180305ada */
/* cut edge: 0x1802fbb4c -> 0x180305b12 */
/* cut edge: 0x1802fbbab -> 0x180305b63 */
/* cut edge: 0x1802fbbbb -> 0x180305b9c */
/* cut edge: 0x1802fbc96 -> 0x180333186 */
/* cut edge: 0x1802fbc96 -> 0x1802fbd8d */
/* target 0x1802fbd8d original byte 0x48 replaced by RET */
/* target 0x180300bf0 original byte 0xff replaced by RET */
/* target 0x1803059e1 original byte 0x48 replaced by RET */
/* target 0x180305a19 original byte 0x48 replaced by RET */
/* target 0x180305a6a original byte 0x48 replaced by RET */
/* target 0x180305aa3 original byte 0x48 replaced by RET */
/* target 0x180305ada original byte 0x48 replaced by RET */
/* target 0x180305b12 original byte 0x48 replaced by RET */
/* target 0x180305b63 original byte 0x48 replaced by RET */
/* target 0x180305b9c original byte 0x48 replaced by RET */
/* target 0x180306f02 original byte 0x48 replaced by RET */
/* target 0x180306f3b original byte 0x48 replaced by RET */
/* target 0x18030706b original byte 0x48 replaced by RET */
/* target 0x1803070a3 original byte 0x48 replaced by RET */
/* target 0x1803070f4 original byte 0x48 replaced by RET */
/* target 0x18030712d original byte 0x48 replaced by RET */
/* target 0x18030725e original byte 0x48 replaced by RET */
/* target 0x180307294 original byte 0x48 replaced by RET */
/* target 0x1803072e5 original byte 0x48 replaced by RET */
/* target 0x18030731e original byte 0x48 replaced by RET */
/* target 0x18030744a original byte 0x48 replaced by RET */
/* target 0x180307485 original byte 0x48 replaced by RET */
/* target 0x1803074df original byte 0x48 replaced by RET */
/* target 0x180307518 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00098_1802fadb0(void)

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
  longlong unaff_RBX;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  undefined8 in_stack_00000058;
  code *in_stack_00000060;
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
  code *in_stack_000000c8;
  undefined8 in_stack_000000d0;
  
  if (DAT_1804b4d38 == 0) {
    return;
  }
  (**(code **)(unaff_RBX + 0x518))(in_stack_00000040,&stack0x000019d8,5);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
  (*in_stack_000000c8)(in_stack_00000040);
  uVar7 = (*in_stack_00000060)
                    (in_stack_00000040,in_stack_00000080,in_stack_00000058,in_stack_00000068,
                     in_stack_00000048);
  DAT_1804b14d8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b14d8,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5d5a);
  (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar7,in_stack_000000a8,uVar8)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
      lVar5 = DAT_1804b5a08;
      uVar7 = DAT_1804b59f0;
      if (cVar6 == '\0') {
        if (DAT_1804b14e0 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000416,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000019c8,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
          DAT_1804b14e0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b14e0,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4a03);
        (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0xf1ed2 ^ in_stack_000000b0 ^ 0x444db7deec4710f4);
        (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
            lVar5 = DAT_1804b5a08;
            uVar7 = DAT_1804b59f0;
            if (cVar6 == '\0') {
              if (DAT_1804b14e8 == 0) {
                pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x00000414,1);
                lVar4 = DAT_1804b4d38;
                lVar3 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x000019b8,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,in_stack_00000080);
                (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
                uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
                DAT_1804b14e8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b14e8,in_stack_000000a0);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4f5e);
              (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000070 % 0x55ab5 ^ in_stack_000000b0 ^
                                 0x5d04fcfa8f2ae5e3);
              (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
              cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                  lVar5 = DAT_1804b5a08;
                  uVar7 = DAT_1804b59f0;
                  if (cVar6 == '\0') {
                    if (DAT_1804b14f0 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b5a08 == 0) {
                        return;
                      }
                      uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                        (in_stack_00000040,&stack0x00000412,1);
                      lVar4 = DAT_1804b4d38;
                      lVar3 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                        (in_stack_00000040,&stack0x000019a8,5);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                         (in_stack_00000040,in_stack_00000080);
                      (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
                      uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
                      DAT_1804b14f0 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
                    }
                    (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,DAT_1804b14f0,in_stack_000000a0);
                    (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
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
  return;
}

