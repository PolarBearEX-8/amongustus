/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=268 entry=18012b172 blocks=46 maxInstructions=100000 */

/* cut edge: 0x18012b1f8 -> 0x18014d3db */
/* cut edge: 0x18012b2ed -> 0x18014d3db */
/* cut edge: 0x18012b303 -> 0x18014d3db */
/* cut edge: 0x18012b36b -> 0x18013da24 */
/* cut edge: 0x18012b397 -> 0x18013da5c */
/* cut edge: 0x18012b3fe -> 0x18013dab6 */
/* cut edge: 0x18012b40e -> 0x18013daf0 */
/* cut edge: 0x18012b551 -> 0x18014d3db */
/* cut edge: 0x18012b5f8 -> 0x1801253c7 */
/* cut edge: 0x18012b60a -> 0x180125403 */
/* cut edge: 0x18012b614 -> 0x1801253d7 */
/* cut edge: 0x18012b63e -> 0x1801253ba */
/* cut edge: 0x18012b648 -> 0x1801253c7 */
/* cut edge: 0x18012b652 -> 0x180130003 */
/* cut edge: 0x18012b65c -> 0x1801253c7 */
/* cut edge: 0x18012b666 -> 0x1801253c7 */
/* cut edge: 0x18012b670 -> 0x180130027 */
/* cut edge: 0x18012b692 -> 0x18014d3db */
/* cut edge: 0x18012b6a8 -> 0x18014d3db */
/* cut edge: 0x18012b850 -> 0x18014d3db */
/* cut edge: 0x18012b93b -> 0x18014d3db */
/* cut edge: 0x18012b978 -> 0x18014d3db */
/* cut edge: 0x18012b998 -> 0x18014d3db */
/* cut edge: 0x18012bb66 -> 0x18014d3db */
/* cut edge: 0x18012bc4c -> 0x18014d3db */
/* cut edge: 0x18012bc62 -> 0x18014d3db */
/* cut edge: 0x18012be36 -> 0x18014d3db */
/* cut edge: 0x18012bf14 -> 0x18014d3db */
/* cut edge: 0x18012bf5a -> 0x18014d3db */
/* cut edge: 0x18012bf98 -> 0x18014d3db */
/* cut edge: 0x18012bfae -> 0x18014d3db */
/* cut edge: 0x18012bfc4 -> 0x18012c185 */
/* cut edge: 0x18012c00c -> 0x18012c185 */
/* target 0x1801253ba original byte 0x48 replaced by RET */
/* target 0x1801253c7 original byte 0x48 replaced by RET */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x180125403 original byte 0x48 replaced by RET */
/* target 0x18012c185 original byte 0x48 replaced by RET */
/* target 0x180130003 original byte 0x48 replaced by RET */
/* target 0x180130027 original byte 0x4c replaced by RET */
/* target 0x18013da24 original byte 0x48 replaced by RET */
/* target 0x18013da5c original byte 0x48 replaced by RET */
/* target 0x18013dab6 original byte 0x48 replaced by RET */
/* target 0x18013daf0 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00268_18012b172(void)

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
  code *unaff_R12;
  longlong *in_stack_00000048;
  code *in_stack_00000060;
  undefined8 in_stack_00000070;
  longlong in_stack_00000078;
  undefined8 in_stack_00000088;
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
  undefined8 in_stack_000000e8;
  code *in_stack_00000110;
  
  (*unaff_R12)(in_stack_00000048,in_stack_00000090);
  (*in_stack_00000110)(in_stack_00000048);
  uVar7 = (*in_stack_00000060)
                    (in_stack_00000048,in_stack_00000090,in_stack_00000070,in_stack_00000088,
                     in_stack_000000e8);
  DAT_1804accc8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804accc8,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x54f);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar5 = DAT_1804b52e8;
      uVar7 = DAT_1804b5208;
      if (cVar6 == '\0') {
        if (DAT_1804accd0 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000906,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00003d68,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar7,uVar8);
          DAT_1804accd0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804accd0,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4ff2);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           in_stack_00000078 % 0x2b005 ^ in_stack_000000c0 ^ 0x7f70c0e7c7e2f0c7);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          return;
        }
      }
    }
  }
  return;
}

