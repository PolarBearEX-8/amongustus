/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=16 entry=1802a8dc1 blocks=51 maxInstructions=50000 */

/* cut edge: 0x1802a8dc1 -> 0x1802af60c */
/* cut edge: 0x1802a8de5 -> 0x1802af642 */
/* cut edge: 0x1802a8e41 -> 0x1802af693 */
/* cut edge: 0x1802a8e51 -> 0x1802af6cc */
/* cut edge: 0x1802a8f26 -> 0x180333186 */
/* cut edge: 0x1802a901b -> 0x180333186 */
/* cut edge: 0x1802a9031 -> 0x180333186 */
/* cut edge: 0x1802a909c -> 0x1802af702 */
/* cut edge: 0x1802a90c5 -> 0x1802af73a */
/* cut edge: 0x1802a9124 -> 0x1802af789 */
/* cut edge: 0x1802a913c -> 0x1802af7c3 */
/* cut edge: 0x1802a921d -> 0x180333186 */
/* cut edge: 0x1802a931b -> 0x18029b02c */
/* cut edge: 0x1802a9346 -> 0x1802c9fdc */
/* cut edge: 0x1802a9355 -> 0x1802f24df */
/* cut edge: 0x1802a935a -> 0x1802eb770 */
/* cut edge: 0x1802a9373 -> 0x1802f2500 */
/* cut edge: 0x1802a9378 -> 0x180333186 */
/* cut edge: 0x1802a939e -> 0x1802b32e6 */
/* cut edge: 0x1802a93c2 -> 0x1802b331c */
/* cut edge: 0x1802a941b -> 0x1802b336d */
/* cut edge: 0x1802a942b -> 0x1802b33a6 */
/* cut edge: 0x1802a94fe -> 0x180333186 */
/* cut edge: 0x1802a95f7 -> 0x1802fa3b6 */
/* cut edge: 0x1802a9612 -> 0x180333186 */
/* cut edge: 0x1802a962a -> 0x1802b33dd */
/* cut edge: 0x1802a964e -> 0x1802b3413 */
/* cut edge: 0x1802a96a7 -> 0x1802b3464 */
/* cut edge: 0x1802a96b7 -> 0x1802b349d */
/* cut edge: 0x1802a978a -> 0x180333186 */
/* cut edge: 0x1802a9883 -> 0x1802fa9c2 */
/* cut edge: 0x1802a989e -> 0x180333186 */
/* cut edge: 0x1802a99e9 -> 0x180333186 */
/* cut edge: 0x1802a9ae2 -> 0x1802fb7ba */
/* cut edge: 0x1802a9aef -> 0x180333186 */
/* cut edge: 0x1802a9c48 -> 0x180333186 */
/* cut edge: 0x1802a9d41 -> 0x1802aa472 */
/* cut edge: 0x1802a9d50 -> 0x1802fce8f */
/* cut edge: 0x1802a9d65 -> 0x180333186 */
/* cut edge: 0x1802a9d65 -> 0x1802a9d78 */
/* target 0x18029b02c original byte 0x83 replaced by RET */
/* target 0x1802a9d78 original byte 0x48 replaced by RET */
/* target 0x1802aa472 original byte 0x3b replaced by RET */
/* target 0x1802af60c original byte 0x48 replaced by RET */
/* target 0x1802af642 original byte 0x48 replaced by RET */
/* target 0x1802af693 original byte 0x48 replaced by RET */
/* target 0x1802af6cc original byte 0x48 replaced by RET */
/* target 0x1802af702 original byte 0x48 replaced by RET */
/* target 0x1802af73a original byte 0x48 replaced by RET */
/* target 0x1802af789 original byte 0x48 replaced by RET */
/* target 0x1802af7c3 original byte 0x48 replaced by RET */
/* target 0x1802b32e6 original byte 0x48 replaced by RET */
/* target 0x1802b331c original byte 0x48 replaced by RET */
/* target 0x1802b336d original byte 0x48 replaced by RET */
/* target 0x1802b33a6 original byte 0x48 replaced by RET */
/* target 0x1802b33dd original byte 0x48 replaced by RET */
/* target 0x1802b3413 original byte 0x48 replaced by RET */
/* target 0x1802b3464 original byte 0x48 replaced by RET */
/* target 0x1802b349d original byte 0x48 replaced by RET */
/* target 0x1802c9fdc original byte 0x48 replaced by RET */
/* target 0x1802eb770 original byte 0x48 replaced by RET */
/* target 0x1802f24df original byte 0x48 replaced by RET */
/* target 0x1802f2500 original byte 0x48 replaced by RET */
/* target 0x1802fa3b6 original byte 0x84 replaced by RET */
/* target 0x1802fa9c2 original byte 0x84 replaced by RET */
/* target 0x1802fb7ba original byte 0x48 replaced by RET */
/* target 0x1802fce8f original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00016_1802a8dc1(void)

{
  code *pcVar1;
  longlong lVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong *in_stack_00000040;
  longlong in_stack_00000048;
  longlong lStack0000000000000058;
  code *pcStack0000000000000070;
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
  longlong in_stack_00000110;
  
  lVar4 = DAT_1804b5a08;
  pcStack0000000000000070 = *(code **)(*in_stack_00000040 + 0x390);
  if (DAT_1804b5a08 == 0) {
    return;
  }
  lStack0000000000000058 = DAT_1804b59f0;
  uVar6 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000390,1);
  lVar3 = DAT_1804b4d38;
  lVar2 = DAT_1804b4d30;
  pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
  if (DAT_1804b4d30 == 0) {
    return;
  }
  if (DAT_1804b4d38 == 0) {
    return;
  }
  uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001688,5);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  uVar8 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
  (*pcVar1)(in_stack_00000040,lVar2,lVar3,uVar7,uVar8);
  uVar6 = (*pcStack0000000000000070)
                    (in_stack_00000040,in_stack_00000080,lVar4,lStack0000000000000058,uVar6);
  DAT_1804b16f0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b16f0,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x314b);
  (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar6,in_stack_000000a8,uVar7)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      lVar4 = DAT_1804b59f0;
      if (cVar5 == '\0') {
        if (DAT_1804b16f8 == 0) {
          pcStack0000000000000070 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          lStack0000000000000058 = DAT_1804b5a08;
          uVar6 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x0000038e,1);
          lVar3 = DAT_1804b4d38;
          lVar2 = DAT_1804b4d30;
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001678,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar1)(in_stack_00000040,lVar2,lVar3,uVar7,uVar8);
          uVar6 = (*pcStack0000000000000070)
                            (in_stack_00000040,in_stack_00000080,lStack0000000000000058,lVar4,uVar6)
          ;
          DAT_1804b16f8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b16f8,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6005);
        (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000048 % 0x1fca7 ^ in_stack_000000b0 ^ 0x9c25615189f79c35);
        (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
        pcStack0000000000000070 =
             (code *)(**(code **)(*in_stack_00000040 + 0x1a0))
                               (in_stack_00000040,uVar6,in_stack_000000d0);
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar5 == '\0') {
          if ((longlong)in_stack_000000b0 < 1) {
            return;
          }
          if (in_stack_00000110 == 0) {
            return;
          }
          return;
        }
      }
    }
  }
  return;
}

