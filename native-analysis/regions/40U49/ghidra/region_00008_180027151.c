/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=8 entry=180027151 blocks=43 maxInstructions=100000 */

/* cut edge: 0x180027292 -> 0x18014d3db */
/* cut edge: 0x180027389 -> 0x18014d3db */
/* cut edge: 0x18002739f -> 0x18014d3db */
/* cut edge: 0x180027556 -> 0x18014d3db */
/* cut edge: 0x18002764b -> 0x18014d3db */
/* cut edge: 0x180027661 -> 0x18014d3db */
/* cut edge: 0x180027820 -> 0x18014d3db */
/* cut edge: 0x180027917 -> 0x180082f76 */
/* cut edge: 0x18002793d -> 0x180023a45 */
/* cut edge: 0x180027973 -> 0x180023a9e */
/* cut edge: 0x1800279a8 -> 0x180023a9e */
/* cut edge: 0x1800279c4 -> 0x180023aae */
/* cut edge: 0x1800279fd -> 0x180023b06 */
/* cut edge: 0x180027a34 -> 0x1800244b4 */
/* cut edge: 0x180027a6a -> 0x18002450d */
/* cut edge: 0x180027a9f -> 0x18002450d */
/* cut edge: 0x180027abb -> 0x18002451d */
/* cut edge: 0x180027af4 -> 0x180024575 */
/* cut edge: 0x180027b2b -> 0x180023cf6 */
/* cut edge: 0x180027b61 -> 0x180023d4f */
/* cut edge: 0x180027b96 -> 0x180023d4f */
/* cut edge: 0x180027bb2 -> 0x180023d5f */
/* cut edge: 0x180027beb -> 0x180023db7 */
/* cut edge: 0x180027c22 -> 0x18014d3db */
/* cut edge: 0x180027d78 -> 0x18014d3db */
/* cut edge: 0x180027e7e -> 0x180053f8f */
/* cut edge: 0x180027ead -> 0x180023f9d */
/* cut edge: 0x180027ee3 -> 0x180023ff6 */
/* cut edge: 0x180027f18 -> 0x180023ff6 */
/* cut edge: 0x180027f34 -> 0x180024006 */
/* cut edge: 0x180027f6d -> 0x18002405e */
/* cut edge: 0x180027fa4 -> 0x18014d3db */
/* cut edge: 0x1800280fd -> 0x18014d3db */
/* cut edge: 0x1800280fd -> 0x1800281f6 */
/* target 0x180023a45 original byte 0x48 replaced by RET */
/* target 0x180023a9e original byte 0x48 replaced by RET */
/* target 0x180023aae original byte 0x48 replaced by RET */
/* target 0x180023b06 original byte 0x48 replaced by RET */
/* target 0x180023cf6 original byte 0x48 replaced by RET */
/* target 0x180023d4f original byte 0x48 replaced by RET */
/* target 0x180023d5f original byte 0x48 replaced by RET */
/* target 0x180023db7 original byte 0x48 replaced by RET */
/* target 0x180023f9d original byte 0x48 replaced by RET */
/* target 0x180023ff6 original byte 0x48 replaced by RET */
/* target 0x180024006 original byte 0x48 replaced by RET */
/* target 0x18002405e original byte 0x48 replaced by RET */
/* target 0x1800244b4 original byte 0x48 replaced by RET */
/* target 0x18002450d original byte 0x48 replaced by RET */
/* target 0x18002451d original byte 0x48 replaced by RET */
/* target 0x180024575 original byte 0x48 replaced by RET */
/* target 0x1800281f6 original byte 0x3b replaced by RET */
/* target 0x180053f8f original byte 0x48 replaced by RET */
/* target 0x180082f76 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00008_180027151(void)

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
  longlong *in_stack_00000048;
  code *pcStack0000000000000058;
  longlong in_stack_00000078;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  
  lVar1 = *in_stack_00000048;
  pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
  uVar6 = FUN_18014d730(in_stack_00000048);
  uVar12 = DAT_1804b5208;
  uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000094a,1);
  lVar1 = *in_stack_00000048;
  pcVar2 = *(code **)(lVar1 + 0x390);
  uVar8 = FUN_1800024f0(in_stack_00000048);
  uVar9 = FUN_180002550(in_stack_00000048);
  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003f48,5);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
  uVar11 = FUN_1800025a0(in_stack_00000048);
  uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar11);
  (*pcVar2)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
  uVar12 = (*pcStack0000000000000058)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
  DAT_1804acbc0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804acbc0,in_stack_000000b0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5ff);
  (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
  pcStack0000000000000058 =
       (code *)(**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8)
  ;
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804acbc8 == 0) {
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar6 = FUN_18014d730(in_stack_00000048);
          uVar12 = DAT_1804b5208;
          uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000948,1);
          lVar1 = *in_stack_00000048;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar8 = FUN_1800024f0(in_stack_00000048);
          uVar9 = FUN_180002550(in_stack_00000048);
          uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003f38,5);
          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
          uVar11 = FUN_1800025a0(in_stack_00000048);
          uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
          (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
          uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
          DAT_1804acbc8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
        }
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804acbc8,in_stack_000000b0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3515);
        (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           in_stack_00000078 % 0xd2244 ^ in_stack_000000c0 ^ 0x435851da88ea1988);
        (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
        uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804acbd0 == 0) {
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar6 = FUN_18014d730(in_stack_00000048);
                uVar12 = DAT_1804b5208;
                uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000946,1);
                lVar1 = *in_stack_00000048;
                pcVar3 = *(code **)(lVar1 + 0x390);
                uVar8 = FUN_1800024f0(in_stack_00000048);
                uVar9 = FUN_180002550(in_stack_00000048);
                uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003f28,5);
                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                uVar11 = FUN_1800025a0(in_stack_00000048);
                uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
                (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
                DAT_1804acbd0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
              }
              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804acbd0,in_stack_000000b0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5431);
              (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_00000078 % 0xc9556 ^ in_stack_000000c0 ^
                                 0x65829944cb049703);
              (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
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

