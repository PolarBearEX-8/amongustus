/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=143 entry=180327db8 blocks=41 maxInstructions=50000 */

/* cut edge: 0x180327db8 -> 0x180331bc0 */
/* cut edge: 0x180327de1 -> 0x180331bf8 */
/* cut edge: 0x180327e40 -> 0x180331c49 */
/* cut edge: 0x180327e50 -> 0x180331c82 */
/* cut edge: 0x180327f2b -> 0x180333186 */
/* cut edge: 0x180328022 -> 0x180333186 */
/* cut edge: 0x180328038 -> 0x180333186 */
/* cut edge: 0x1803280a0 -> 0x180331cb9 */
/* cut edge: 0x1803280c4 -> 0x180331cef */
/* cut edge: 0x180328123 -> 0x180331d40 */
/* cut edge: 0x180328133 -> 0x180331d79 */
/* cut edge: 0x18032820b -> 0x180333186 */
/* cut edge: 0x180328300 -> 0x180333186 */
/* cut edge: 0x180328316 -> 0x180333186 */
/* cut edge: 0x18032837e -> 0x180331daf */
/* cut edge: 0x1803283aa -> 0x180331dea */
/* cut edge: 0x18032840e -> 0x180331e44 */
/* cut edge: 0x18032841e -> 0x180331e7d */
/* cut edge: 0x1803284fd -> 0x180333186 */
/* cut edge: 0x18032860c -> 0x18029ad3c */
/* cut edge: 0x180328620 -> 0x180333186 */
/* cut edge: 0x180328628 -> 0x180333186 */
/* cut edge: 0x1803287d1 -> 0x180333186 */
/* cut edge: 0x1803288c8 -> 0x180333186 */
/* cut edge: 0x1803288de -> 0x180333186 */
/* cut edge: 0x180328a8c -> 0x180333186 */
/* cut edge: 0x180328b83 -> 0x180333186 */
/* cut edge: 0x180328b99 -> 0x180333186 */
/* cut edge: 0x180328d4d -> 0x180333186 */
/* cut edge: 0x180328d4d -> 0x180328e42 */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x180328e42 original byte 0x48 replaced by RET */
/* target 0x180331bc0 original byte 0x48 replaced by RET */
/* target 0x180331bf8 original byte 0x48 replaced by RET */
/* target 0x180331c49 original byte 0x48 replaced by RET */
/* target 0x180331c82 original byte 0x48 replaced by RET */
/* target 0x180331cb9 original byte 0x48 replaced by RET */
/* target 0x180331cef original byte 0x48 replaced by RET */
/* target 0x180331d40 original byte 0x48 replaced by RET */
/* target 0x180331d79 original byte 0x48 replaced by RET */
/* target 0x180331daf original byte 0x48 replaced by RET */
/* target 0x180331dea original byte 0x48 replaced by RET */
/* target 0x180331e44 original byte 0x48 replaced by RET */
/* target 0x180331e7d original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00143_180327db8(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong *in_stack_00000040;
  code *pcStack0000000000000048;
  longlong lStack0000000000000068;
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
  longlong lStack00000000000000c8;
  undefined8 in_stack_000000d0;
  
  pcStack0000000000000048 = *(code **)(*in_stack_00000040 + 0x390);
  lStack0000000000000068 = DAT_1804b5a08;
  if (DAT_1804b5a08 == 0) {
    return;
  }
  lStack00000000000000c8 = DAT_1804b59f0;
  uVar6 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000004c6,1);
  lVar3 = DAT_1804b4d38;
  pcVar2 = DAT_1804b4d30;
  pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
  if (DAT_1804b4d30 == (code *)0x0) {
    return;
  }
  if (DAT_1804b4d38 == 0) {
    return;
  }
  uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001f28,5);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  uVar8 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
  (*pcVar1)(in_stack_00000040,pcVar2,lVar3,uVar7,uVar8);
  uVar6 = (*pcStack0000000000000048)
                    (in_stack_00000040,in_stack_00000080,lStack0000000000000068,
                     lStack00000000000000c8,uVar6);
  DAT_1804b1220 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b1220,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7184);
  (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar6,in_stack_000000a8,uVar7)
  ;
  lStack0000000000000068 =
       (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      lVar3 = DAT_1804b5a08;
      if (cVar5 == '\0') {
        if (DAT_1804b1228 == 0) {
          pcStack0000000000000048 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          lStack00000000000000c8 = DAT_1804b59f0;
          uVar6 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000004c4,1);
          lVar4 = DAT_1804b4d38;
          pcVar2 = DAT_1804b4d30;
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == (code *)0x0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001f18,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar1)(in_stack_00000040,pcVar2,lVar4,uVar7,uVar8);
          uVar6 = (*pcStack0000000000000048)
                            (in_stack_00000040,in_stack_00000080,lVar3,lStack00000000000000c8,uVar6)
          ;
          DAT_1804b1228 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b1228,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1839);
        (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0x88b8c ^ in_stack_000000b0 ^ 0xed42900ef48558a9);
        (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
            lVar3 = DAT_1804b59f0;
            if (cVar5 == '\0') {
              if (DAT_1804b1230 == 0) {
                pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                lStack00000000000000c8 = DAT_1804b5a08;
                if (DAT_1804b5a08 == 0) {
                  return;
                }
                uVar6 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x000004c2,1);
                lVar4 = DAT_1804b4d38;
                pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                pcStack0000000000000048 = DAT_1804b4d30;
                if (DAT_1804b4d30 == (code *)0x0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar7 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x00001f08,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000040 + 0x110))
                                  (in_stack_00000040,in_stack_00000080);
                (*pcVar2)(in_stack_00000040,pcStack0000000000000048,lVar4,uVar7,uVar8);
                uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lStack00000000000000c8,lVar3,
                                  uVar6);
                DAT_1804b1230 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b1230,in_stack_000000a0);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5e09);
              (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000070 % 0xb1c5b ^ in_stack_000000b0 ^
                                 0xe09e4fbef739c059);
              (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
              cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar5 == '\0') {
                return;
              }
            }
          }
        }
      }
    }
  }
  return;
}

