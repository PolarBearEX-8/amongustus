/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=137 entry=180321d98 blocks=46 maxInstructions=50000 */

/* cut edge: 0x180321d98 -> 0x180333186 */
/* cut edge: 0x180321dae -> 0x180333186 */
/* cut edge: 0x180321f60 -> 0x180333186 */
/* cut edge: 0x180322055 -> 0x180333186 */
/* cut edge: 0x18032206b -> 0x180333186 */
/* cut edge: 0x180322226 -> 0x180333186 */
/* cut edge: 0x18032231b -> 0x1802f40ab */
/* cut edge: 0x180322343 -> 0x180333186 */
/* cut edge: 0x18032234b -> 0x180333186 */
/* cut edge: 0x1803223bd -> 0x18032f86a */
/* cut edge: 0x1803223e6 -> 0x18032f8a2 */
/* cut edge: 0x180322442 -> 0x18032f8f3 */
/* cut edge: 0x180322452 -> 0x18032f92c */
/* cut edge: 0x18032252a -> 0x180333186 */
/* cut edge: 0x180322624 -> 0x180333186 */
/* cut edge: 0x18032263a -> 0x180333186 */
/* cut edge: 0x1803226a2 -> 0x18032f963 */
/* cut edge: 0x1803226cb -> 0x18032f99b */
/* cut edge: 0x180322727 -> 0x18032f9ec */
/* cut edge: 0x180322737 -> 0x18032fa25 */
/* cut edge: 0x18032280f -> 0x180333186 */
/* cut edge: 0x180322906 -> 0x180333186 */
/* cut edge: 0x18032291c -> 0x180333186 */
/* cut edge: 0x180322984 -> 0x18032fb55 */
/* cut edge: 0x1803229ad -> 0x18032fb8d */
/* cut edge: 0x180322a0c -> 0x18032fbde */
/* cut edge: 0x180322a1c -> 0x18032fc17 */
/* cut edge: 0x180322af7 -> 0x180333186 */
/* cut edge: 0x180322bee -> 0x180333186 */
/* cut edge: 0x180322c04 -> 0x180333186 */
/* cut edge: 0x180322c1a -> 0x180322dda */
/* cut edge: 0x180322c6f -> 0x18032fd47 */
/* cut edge: 0x180322c93 -> 0x18032fd7d */
/* cut edge: 0x180322cf2 -> 0x18032fdce */
/* cut edge: 0x180322d02 -> 0x18032fe07 */
/* cut edge: 0x180322d5a -> 0x180322dda */
/* target 0x1802f40ab original byte 0x48 replaced by RET */
/* target 0x180322dda original byte 0x48 replaced by RET */
/* target 0x18032f86a original byte 0x48 replaced by RET */
/* target 0x18032f8a2 original byte 0x48 replaced by RET */
/* target 0x18032f8f3 original byte 0x48 replaced by RET */
/* target 0x18032f92c original byte 0x48 replaced by RET */
/* target 0x18032f963 original byte 0x48 replaced by RET */
/* target 0x18032f99b original byte 0x48 replaced by RET */
/* target 0x18032f9ec original byte 0x48 replaced by RET */
/* target 0x18032fa25 original byte 0x48 replaced by RET */
/* target 0x18032fb55 original byte 0x48 replaced by RET */
/* target 0x18032fb8d original byte 0x48 replaced by RET */
/* target 0x18032fbde original byte 0x48 replaced by RET */
/* target 0x18032fc17 original byte 0x48 replaced by RET */
/* target 0x18032fd47 original byte 0x48 replaced by RET */
/* target 0x18032fd7d original byte 0x48 replaced by RET */
/* target 0x18032fdce original byte 0x48 replaced by RET */
/* target 0x18032fe07 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00137_180321d98(void)

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
      if (DAT_1804b0e78 == 0) {
        lVar1 = *in_stack_00000040;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_1803335f0(in_stack_00000040);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000005b2,1);
        lVar1 = *in_stack_00000040;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000040);
        uVar9 = FUN_180002550(in_stack_00000040);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002628,5);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000040);
        uVar11 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar11);
        (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar12,uVar7);
        DAT_1804b0e78 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b0e78,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7b34);
      (*pcVar2)(in_stack_00000040,uVar6,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         in_stack_00000070 % 0x9d2b1 ^ in_stack_000000b0 ^ 0x2ad34c1bb1e3aff2);
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
            if (DAT_1804b0e80 == 0) {
              lVar1 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar6 = FUN_1803335f0(in_stack_00000040);
              uVar12 = DAT_1804b59f0;
              uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x000005b0,1);
              lVar1 = *in_stack_00000040;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000040);
              uVar9 = FUN_180002550(in_stack_00000040);
              uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002618,5);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000040);
              uVar11 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar11);
              (*pcVar3)(in_stack_00000040,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar6,uVar12,uVar7);
              DAT_1804b0e80 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar12);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar12);
            }
            uVar12 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b0e80,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1c0f);
            (*pcVar2)(in_stack_00000040,uVar6,0,uVar7);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00000070 % 0x1d094 ^ in_stack_000000b0 ^ 0x440a9800e838d158)
            ;
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
  return;
}

