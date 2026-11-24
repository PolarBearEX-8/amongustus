/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=136 entry=180320d92 blocks=28 maxInstructions=50000 */

/* cut edge: 0x180320ed3 -> 0x180333186 */
/* cut edge: 0x180320fca -> 0x180333186 */
/* cut edge: 0x180320fe0 -> 0x180333186 */
/* cut edge: 0x180321194 -> 0x180333186 */
/* cut edge: 0x180321289 -> 0x180333186 */
/* cut edge: 0x18032129f -> 0x180333186 */
/* cut edge: 0x18032145c -> 0x180333186 */
/* cut edge: 0x180321551 -> 0x18029ad3c */
/* cut edge: 0x180321575 -> 0x180333186 */
/* cut edge: 0x18032157d -> 0x180333186 */
/* cut edge: 0x180321726 -> 0x180333186 */
/* cut edge: 0x18032181d -> 0x180333186 */
/* cut edge: 0x180321833 -> 0x180333186 */
/* cut edge: 0x1803219e1 -> 0x180333186 */
/* cut edge: 0x180321ad8 -> 0x180333186 */
/* cut edge: 0x180321aee -> 0x180333186 */
/* cut edge: 0x180321ca1 -> 0x180333186 */
/* cut edge: 0x180321ca1 -> 0x180321d98 */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x180321d98 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00136_180320d92(void)

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
  code *pcStack0000000000000060;
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
  
  lVar1 = *in_stack_00000040;
  pcStack0000000000000060 = *(code **)(lVar1 + 0x390);
  uVar6 = FUN_1803335f0(in_stack_00000040);
  uVar12 = DAT_1804b59f0;
  uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000005ec,1);
  lVar1 = *in_stack_00000040;
  pcVar2 = *(code **)(lVar1 + 0x390);
  uVar8 = FUN_1800024f0(in_stack_00000040);
  uVar9 = FUN_180002550(in_stack_00000040);
  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000027f8,5);
  pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
  uVar11 = FUN_1800025a0(in_stack_00000040);
  uVar11 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar11);
  (*pcVar2)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
  uVar12 = (*pcStack0000000000000060)(in_stack_00000040,in_stack_00000080,uVar6,uVar12,uVar7);
  DAT_1804b0d90 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
  uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                     (in_stack_00000040,DAT_1804b0d90,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7f4e);
  (*pcVar2)(in_stack_00000040,uVar6,0,uVar7);
  pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar2)(in_stack_00000040,uVar6,1,uVar7);
  uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                     (in_stack_00000040,uVar12,in_stack_000000a8,uVar6);
  pcStack0000000000000060 =
       (code *)(**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar12,in_stack_000000d0)
  ;
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b0d98 == 0) {
          lVar1 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar6 = FUN_1803335f0(in_stack_00000040);
          uVar12 = DAT_1804b59f0;
          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000005ea,1);
          lVar1 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000040);
          uVar9 = FUN_180002550(in_stack_00000040);
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000027e8,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000040);
          uVar11 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar11);
          (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar12,uVar7);
          DAT_1804b0d98 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
        }
        uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b0d98,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1f05);
        (*pcVar2)(in_stack_00000040,uVar6,0,uVar7);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0x314cf ^ in_stack_000000b0 ^ 0x1f8f7c47ede2e5e4);
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
              if (DAT_1804b0da0 == 0) {
                lVar1 = *in_stack_00000040;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar6 = FUN_1803335f0(in_stack_00000040);
                uVar12 = DAT_1804b59f0;
                uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000005e8,1);
                lVar1 = *in_stack_00000040;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000040);
                uVar9 = FUN_180002550(in_stack_00000040);
                uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000027d8,5);
                pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000040);
                uVar11 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar11);
                (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
                uVar12 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar12,uVar7);
                DAT_1804b0da0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
              }
              uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,DAT_1804b0da0,in_stack_000000a0);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4eae);
              (*pcVar2)(in_stack_00000040,uVar6,0,uVar7);
              pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000070 % 0xed648 ^ in_stack_000000b0 ^
                                 0x696ca3ec4c51efac);
              (*pcVar2)(in_stack_00000040,uVar6,1,uVar7);
              uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,uVar12,in_stack_000000a8,uVar6);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar12,in_stack_000000d0);
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

