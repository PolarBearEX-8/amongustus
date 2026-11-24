/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=267 entry=18012a193 blocks=47 maxInstructions=100000 */

/* cut edge: 0x18012a193 -> 0x18014d3db */
/* cut edge: 0x18012a1a9 -> 0x18014d3db */
/* cut edge: 0x18012a358 -> 0x18014d3db */
/* cut edge: 0x18012a44f -> 0x18014d3db */
/* cut edge: 0x18012a465 -> 0x18014d3db */
/* cut edge: 0x18012a61a -> 0x18014d3db */
/* cut edge: 0x18012a70f -> 0x18014d3db */
/* cut edge: 0x18012a725 -> 0x18014d3db */
/* cut edge: 0x18012a8e1 -> 0x18014d3db */
/* cut edge: 0x18012a9df -> 0x1801253d7 */
/* cut edge: 0x18012aa08 -> 0x180130058 */
/* cut edge: 0x18012aa36 -> 0x18012f316 */
/* cut edge: 0x18012aa3b -> 0x1801253ba */
/* cut edge: 0x18012aa45 -> 0x18014d3db */
/* cut edge: 0x18012aa4d -> 0x18014d3db */
/* cut edge: 0x18012aab5 -> 0x18013d739 */
/* cut edge: 0x18012aade -> 0x18013d771 */
/* cut edge: 0x18012ab3a -> 0x18013d7c2 */
/* cut edge: 0x18012ab4a -> 0x18013d7fb */
/* cut edge: 0x18012ac22 -> 0x18014d3db */
/* cut edge: 0x18012ad19 -> 0x18014d3db */
/* cut edge: 0x18012ad2f -> 0x18014d3db */
/* cut edge: 0x18012ad97 -> 0x18013d832 */
/* cut edge: 0x18012adc0 -> 0x18013d86a */
/* cut edge: 0x18012ae1f -> 0x18013d8bb */
/* cut edge: 0x18012ae2f -> 0x18013d8f4 */
/* cut edge: 0x18012af0a -> 0x18014d3db */
/* cut edge: 0x18012b001 -> 0x18014d3db */
/* cut edge: 0x18012b017 -> 0x18014d3db */
/* cut edge: 0x18012b02d -> 0x18012b1f8 */
/* cut edge: 0x18012b07f -> 0x18013d92b */
/* cut edge: 0x18012b0a8 -> 0x18013d963 */
/* cut edge: 0x18012b10a -> 0x18013d9b4 */
/* cut edge: 0x18012b11a -> 0x18013d9ed */
/* cut edge: 0x18012b11a -> 0x18012b172 */
/* target 0x1801253ba original byte 0x48 replaced by RET */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x18012b172 original byte 0x48 replaced by RET */
/* target 0x18012b1f8 original byte 0x48 replaced by RET */
/* target 0x18012f316 original byte 0x48 replaced by RET */
/* target 0x180130058 original byte 0x48 replaced by RET */
/* target 0x18013d739 original byte 0x48 replaced by RET */
/* target 0x18013d771 original byte 0x48 replaced by RET */
/* target 0x18013d7c2 original byte 0x48 replaced by RET */
/* target 0x18013d7fb original byte 0x48 replaced by RET */
/* target 0x18013d832 original byte 0x48 replaced by RET */
/* target 0x18013d86a original byte 0x48 replaced by RET */
/* target 0x18013d8bb original byte 0x48 replaced by RET */
/* target 0x18013d8f4 original byte 0x48 replaced by RET */
/* target 0x18013d92b original byte 0x48 replaced by RET */
/* target 0x18013d963 original byte 0x48 replaced by RET */
/* target 0x18013d9b4 original byte 0x48 replaced by RET */
/* target 0x18013d9ed original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00267_18012a193(void)

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
  longlong unaff_R14;
  longlong *in_stack_00000048;
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
  longlong in_stack_000000f8;
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804ac9a0 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_18014d730(in_stack_00000048);
        uVar12 = DAT_1804b5208;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000009d8,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004268,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
        (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
        DAT_1804ac9a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804ac9a0,in_stack_000000b0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1f1d);
      (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         unaff_R14 % 0x6a672 ^ in_stack_000000c0 ^ 0x208a7922e7127062);
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
            if (DAT_1804ac9a8 == 0) {
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar6 = FUN_18014d730(in_stack_00000048);
              uVar12 = DAT_1804b5208;
              uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000009d6,1);
              lVar1 = *in_stack_00000048;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar8 = FUN_1800024f0(in_stack_00000048);
              uVar9 = FUN_180002550(in_stack_00000048);
              uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004258,5);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
              uVar11 = FUN_1800025a0(in_stack_00000048);
              uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
              (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
              uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
              DAT_1804ac9a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804ac9a8,in_stack_000000b0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x444d);
            (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               unaff_R14 % 0x1c8bf ^ in_stack_000000c0 ^ 0x1716948a12ea8fd6);
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
                  if (DAT_1804ac9b0 == 0) {
                    lVar1 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar1 + 0x390);
                    uVar6 = FUN_18014d730(in_stack_00000048);
                    uVar12 = DAT_1804b5208;
                    uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000009d4,1);
                    lVar1 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar1 + 0x390);
                    uVar8 = FUN_1800024f0(in_stack_00000048);
                    uVar9 = FUN_180002550(in_stack_00000048);
                    uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00004248,5);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar11 = FUN_1800025a0(in_stack_00000048);
                    uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
                    (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                    uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
                    DAT_1804ac9b0 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                  }
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804ac9b0,in_stack_000000b0);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5c12);
                  (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     unaff_R14 % 0x38f63 ^ in_stack_000000c0 ^ 0x5611b317273618);
                  (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                  (**(code **)(*in_stack_00000048 + 0x1a0))
                            (in_stack_00000048,uVar12,in_stack_000000d8);
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar5 == '\0') {
                    if ((longlong)in_stack_000000c0 < 1) {
                      return;
                    }
                    if (in_stack_000000f8 == 0) {
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
  return;
}

