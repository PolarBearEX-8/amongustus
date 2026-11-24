/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=138 entry=180322dda blocks=52 maxInstructions=50000 */

/* cut edge: 0x180322dda -> 0x180333186 */
/* cut edge: 0x180322ecf -> 0x180333186 */
/* cut edge: 0x180322ee5 -> 0x180333186 */
/* cut edge: 0x180322f4d -> 0x18032ff36 */
/* cut edge: 0x180322f79 -> 0x18032ff71 */
/* cut edge: 0x180322fdd -> 0x18032ffcb */
/* cut edge: 0x180322fed -> 0x180330004 */
/* cut edge: 0x1803230cc -> 0x180300bf0 */
/* cut edge: 0x180323131 -> 0x180318a76 */
/* cut edge: 0x18032313b -> 0x180333186 */
/* cut edge: 0x180323143 -> 0x180333186 */
/* cut edge: 0x1803231ae -> 0x180330140 */
/* cut edge: 0x1803231d7 -> 0x180330178 */
/* cut edge: 0x180323233 -> 0x1803301c9 */
/* cut edge: 0x180323243 -> 0x180330203 */
/* cut edge: 0x18032331e -> 0x180333186 */
/* cut edge: 0x180323412 -> 0x180333186 */
/* cut edge: 0x180323441 -> 0x180333186 */
/* cut edge: 0x180323457 -> 0x180333186 */
/* cut edge: 0x1803234bf -> 0x180330525 */
/* cut edge: 0x1803234e8 -> 0x18033055d */
/* cut edge: 0x180323544 -> 0x1803305ae */
/* cut edge: 0x180323554 -> 0x1803305e7 */
/* cut edge: 0x18032362c -> 0x180333186 */
/* cut edge: 0x180323723 -> 0x180333186 */
/* cut edge: 0x180323739 -> 0x180333186 */
/* cut edge: 0x1803237a1 -> 0x18033080d */
/* cut edge: 0x1803237ca -> 0x180330845 */
/* cut edge: 0x180323829 -> 0x180330896 */
/* cut edge: 0x180323839 -> 0x1803308cf */
/* cut edge: 0x180323914 -> 0x180333186 */
/* cut edge: 0x180323a0b -> 0x180333186 */
/* cut edge: 0x180323a21 -> 0x180333186 */
/* cut edge: 0x180323a89 -> 0x180330b04 */
/* cut edge: 0x180323aad -> 0x180330b3a */
/* cut edge: 0x180323b0c -> 0x180330b8b */
/* cut edge: 0x180323b1c -> 0x180330bc4 */
/* cut edge: 0x180323bf4 -> 0x180333186 */
/* cut edge: 0x180323ce9 -> 0x180333186 */
/* cut edge: 0x180323cff -> 0x180333186 */
/* cut edge: 0x180323d15 -> 0x180323ee6 */
/* cut edge: 0x180323d67 -> 0x180330cff */
/* cut edge: 0x180323d67 -> 0x180323d93 */
/* target 0x180300bf0 original byte 0xff replaced by RET */
/* target 0x180318a76 original byte 0x48 replaced by RET */
/* target 0x180323d93 original byte 0x48 replaced by RET */
/* target 0x180323ee6 original byte 0x48 replaced by RET */
/* target 0x18032ff36 original byte 0x48 replaced by RET */
/* target 0x18032ff71 original byte 0x48 replaced by RET */
/* target 0x18032ffcb original byte 0x48 replaced by RET */
/* target 0x180330004 original byte 0x48 replaced by RET */
/* target 0x180330140 original byte 0x48 replaced by RET */
/* target 0x180330178 original byte 0x48 replaced by RET */
/* target 0x1803301c9 original byte 0x48 replaced by RET */
/* target 0x180330203 original byte 0x48 replaced by RET */
/* target 0x180330525 original byte 0x48 replaced by RET */
/* target 0x18033055d original byte 0x48 replaced by RET */
/* target 0x1803305ae original byte 0x48 replaced by RET */
/* target 0x1803305e7 original byte 0x48 replaced by RET */
/* target 0x18033080d original byte 0x48 replaced by RET */
/* target 0x180330845 original byte 0x48 replaced by RET */
/* target 0x180330896 original byte 0x48 replaced by RET */
/* target 0x1803308cf original byte 0x48 replaced by RET */
/* target 0x180330b04 original byte 0x48 replaced by RET */
/* target 0x180330b3a original byte 0x48 replaced by RET */
/* target 0x180330b8b original byte 0x48 replaced by RET */
/* target 0x180330bc4 original byte 0x48 replaced by RET */
/* target 0x180330cff original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00138_180322dda(undefined8 param_1,undefined8 param_2)

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
  longlong *in_stack_00000040;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,param_2,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x113);
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
        if (DAT_1804b1458 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000438,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001ac8,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
          DAT_1804b1458 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
        }
        (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,DAT_1804b1458,in_stack_000000a0)
        ;
        (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        return;
      }
    }
  }
  return;
}

