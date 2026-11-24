/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=131 entry=18031bdc5 blocks=43 maxInstructions=50000 */

/* cut edge: 0x18031bdc5 -> 0x180333186 */
/* cut edge: 0x18031bddb -> 0x180333186 */
/* cut edge: 0x18031bf89 -> 0x180333186 */
/* cut edge: 0x18031c080 -> 0x180333186 */
/* cut edge: 0x18031c096 -> 0x180333186 */
/* cut edge: 0x18031c24d -> 0x180333186 */
/* cut edge: 0x18031c342 -> 0x180333186 */
/* cut edge: 0x18031c358 -> 0x180333186 */
/* cut edge: 0x18031c511 -> 0x180301a90 */
/* cut edge: 0x18031c576 -> 0x180329076 */
/* cut edge: 0x18031c593 -> 0x180333186 */
/* cut edge: 0x18031c59b -> 0x180333186 */
/* cut edge: 0x18031c603 -> 0x1803316d7 */
/* cut edge: 0x18031c62c -> 0x18033170f */
/* cut edge: 0x18031c688 -> 0x180331760 */
/* cut edge: 0x18031c698 -> 0x180331799 */
/* cut edge: 0x18031c770 -> 0x180333186 */
/* cut edge: 0x18031c867 -> 0x180333186 */
/* cut edge: 0x18031c87d -> 0x180333186 */
/* cut edge: 0x18031c8e8 -> 0x1803317d0 */
/* cut edge: 0x18031c911 -> 0x180331808 */
/* cut edge: 0x18031c970 -> 0x180331859 */
/* cut edge: 0x18031c980 -> 0x180331892 */
/* cut edge: 0x18031ca5b -> 0x180333186 */
/* cut edge: 0x18031cb52 -> 0x180333186 */
/* cut edge: 0x18031cb68 -> 0x180333186 */
/* cut edge: 0x18031cbd0 -> 0x1803318c9 */
/* cut edge: 0x18031cbfc -> 0x180331901 */
/* cut edge: 0x18031cc58 -> 0x180331952 */
/* cut edge: 0x18031cc68 -> 0x18033198b */
/* cut edge: 0x18031cd43 -> 0x180333186 */
/* cut edge: 0x18031cd43 -> 0x18031ce38 */
/* target 0x180301a90 original byte 0xff replaced by RET */
/* target 0x18031ce38 original byte 0x48 replaced by RET */
/* target 0x180329076 original byte 0x84 replaced by RET */
/* target 0x1803316d7 original byte 0x48 replaced by RET */
/* target 0x18033170f original byte 0x48 replaced by RET */
/* target 0x180331760 original byte 0x48 replaced by RET */
/* target 0x180331799 original byte 0x48 replaced by RET */
/* target 0x1803317d0 original byte 0x48 replaced by RET */
/* target 0x180331808 original byte 0x48 replaced by RET */
/* target 0x180331859 original byte 0x48 replaced by RET */
/* target 0x180331892 original byte 0x48 replaced by RET */
/* target 0x1803318c9 original byte 0x48 replaced by RET */
/* target 0x180331901 original byte 0x48 replaced by RET */
/* target 0x180331952 original byte 0x48 replaced by RET */
/* target 0x18033198b original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00131_18031bdc5(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000040;
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
  
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b1000 == 0) {
        lVar1 = *in_stack_00000040;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_1803335f0(in_stack_00000040);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000054e,1);
        lVar1 = *in_stack_00000040;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000040);
        uVar9 = FUN_180002550(in_stack_00000040);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002358,5);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000040);
        uVar11 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar11);
        (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar12,uVar7);
        DAT_1804b1000 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b1000,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7253);
      (*pcVar2)(in_stack_00000040,uVar6,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         in_stack_00000070 % 0xa8864 ^ in_stack_000000b0 ^ 0xfd1283b7ee80ea1c);
      (*pcVar2)(in_stack_00000040,uVar6,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,uVar12,in_stack_000000a8,uVar6);
      (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar12,in_stack_000000d0);
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (cVar5 == '\0') {
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b1008 == 0) {
              lVar1 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar6 = FUN_1803335f0(in_stack_00000040);
              uVar12 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000054c,1);
              lVar1 = *in_stack_00000040;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000040);
              uVar9 = FUN_180002550(in_stack_00000040);
              uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002348,5);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000040);
              uVar11 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar11);
              (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar12,uVar7);
              DAT_1804b1008 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
            }
            uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b1008,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x2eed);
            (*pcVar2)(in_stack_00000040,uVar6,0,uVar7);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00000070 % 0xef5cf ^ in_stack_000000b0 ^ 0xe3714b2a9ec70ec9)
            ;
            (*pcVar2)(in_stack_00000040,uVar6,1,uVar7);
            uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,uVar12,in_stack_000000a8,uVar6);
            (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar12,in_stack_000000d0);
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if (cVar5 == '\0') {
              cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b1010 == 0) {
                    lVar1 = *in_stack_00000040;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar6 = FUN_1803335f0(in_stack_00000040);
                    uVar12 = DAT_1804b59f0;
                    uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000054a,1);
                    lVar1 = *in_stack_00000040;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000040);
                    uVar9 = FUN_180002550(in_stack_00000040);
                    uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002338,5);
                    pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000040);
                    uVar11 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar11);
                    (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
                    uVar12 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar12,uVar7);
                    DAT_1804b1010 =
                         (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
                    (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
                  }
                  (**(code **)(*in_stack_00000040 + 0x110))
                            (in_stack_00000040,DAT_1804b1010,in_stack_000000a0);
                  (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0)
                  ;
                  return;
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

