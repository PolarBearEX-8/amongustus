/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=290 entry=180141124 blocks=43 maxInstructions=100000 */

/* cut edge: 0x180141124 -> 0x18014d3db */
/* cut edge: 0x1801412d2 -> 0x18014d3db */
/* cut edge: 0x1801413c9 -> 0x18014d3db */
/* cut edge: 0x1801413df -> 0x18014d3db */
/* cut edge: 0x180141593 -> 0x18014d3db */
/* cut edge: 0x180141688 -> 0x18014d3db */
/* cut edge: 0x18014169e -> 0x18014d3db */
/* cut edge: 0x18014185d -> 0x18014d3db */
/* cut edge: 0x180141952 -> 0x18013e7f3 */
/* cut edge: 0x180141969 -> 0x180130bc0 */
/* cut edge: 0x180141995 -> 0x180126bea */
/* cut edge: 0x1801419b7 -> 0x18014d3db */
/* cut edge: 0x1801419bf -> 0x18014d3db */
/* cut edge: 0x180141a2a -> 0x18014ae29 */
/* cut edge: 0x180141a53 -> 0x18014ae61 */
/* cut edge: 0x180141aaf -> 0x18014aeb2 */
/* cut edge: 0x180141abf -> 0x18014aeeb */
/* cut edge: 0x180141b97 -> 0x18014d3db */
/* cut edge: 0x180141c8e -> 0x18014d3db */
/* cut edge: 0x180141ca4 -> 0x18014d3db */
/* cut edge: 0x180141d0f -> 0x18014af22 */
/* cut edge: 0x180141d38 -> 0x18014af5a */
/* cut edge: 0x180141d97 -> 0x18014afab */
/* cut edge: 0x180141da7 -> 0x18014afe4 */
/* cut edge: 0x180141e82 -> 0x18014d3db */
/* cut edge: 0x180141f79 -> 0x18014d3db */
/* cut edge: 0x180141f8f -> 0x18014d3db */
/* cut edge: 0x180141fa5 -> 0x180142173 */
/* cut edge: 0x180141ffa -> 0x18014b01b */
/* cut edge: 0x180142023 -> 0x18014b053 */
/* cut edge: 0x180142085 -> 0x18014b0a4 */
/* cut edge: 0x180142095 -> 0x18014b0dd */
/* cut edge: 0x1801420ed -> 0x180142173 */
/* target 0x180126bea original byte 0x48 replaced by RET */
/* target 0x180130bc0 original byte 0x48 replaced by RET */
/* target 0x18013e7f3 original byte 0x4c replaced by RET */
/* target 0x180142173 original byte 0x48 replaced by RET */
/* target 0x18014ae29 original byte 0x48 replaced by RET */
/* target 0x18014ae61 original byte 0x48 replaced by RET */
/* target 0x18014aeb2 original byte 0x48 replaced by RET */
/* target 0x18014aeeb original byte 0x48 replaced by RET */
/* target 0x18014af22 original byte 0x48 replaced by RET */
/* target 0x18014af5a original byte 0x48 replaced by RET */
/* target 0x18014afab original byte 0x48 replaced by RET */
/* target 0x18014afe4 original byte 0x48 replaced by RET */
/* target 0x18014b01b original byte 0x48 replaced by RET */
/* target 0x18014b053 original byte 0x48 replaced by RET */
/* target 0x18014b0a4 original byte 0x48 replaced by RET */
/* target 0x18014b0dd original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00290_180141124(void)

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
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804acc00 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar6 = FUN_18014d730(in_stack_00000048);
      uVar12 = DAT_1804b5208;
      uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000093a,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003ec8,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
      (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
      DAT_1804acc00 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804acc00,in_stack_000000b0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x56f0);
    (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       in_stack_00000078 % 0x84e97 ^ in_stack_000000c0 ^ 0x773d30246cd196b1);
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
          if (DAT_1804acc08 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar6 = FUN_18014d730(in_stack_00000048);
            uVar12 = DAT_1804b5208;
            uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000938,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003eb8,5);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
            (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
            DAT_1804acc08 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804acc08,in_stack_000000b0);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7819);
          (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                             in_stack_00000078 % 0xd0507 ^ in_stack_000000c0 ^ 0x26a0aefb557fba07);
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
                if (DAT_1804acc10 == 0) {
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar6 = FUN_18014d730(in_stack_00000048);
                  uVar12 = DAT_1804b5208;
                  uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000936,1);
                  lVar1 = *in_stack_00000048;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar8 = FUN_1800024f0(in_stack_00000048);
                  uVar9 = FUN_180002550(in_stack_00000048);
                  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003ea8,5);
                  pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar11 = FUN_1800025a0(in_stack_00000048);
                  uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
                  (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
                  uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
                  DAT_1804acc10 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
                }
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804acc10,in_stack_000000b0);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_000000a0,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x163e);
                (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                   in_stack_00000078 % 0xb8a36 ^ in_stack_000000c0 ^
                                   0x26981ff47146bec8);
                (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
                uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
                (**(code **)(*in_stack_00000048 + 0x1a0))
                          (in_stack_00000048,uVar12,in_stack_000000d8);
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
  }
  return;
}

