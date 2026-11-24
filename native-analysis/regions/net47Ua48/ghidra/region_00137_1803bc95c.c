/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=137 entry=1803bc95c blocks=37 maxInstructions=50000 */

/* cut edge: 0x1803bc95c -> 0x1803351d6 */
/* cut edge: 0x1803bca37 -> 0x1803bf530 */
/* cut edge: 0x1803bca45 -> 0x1803bf558 */
/* cut edge: 0x1803bca4e -> 0x1803bf55a */
/* cut edge: 0x1803bca6f -> 0x1803bf558 */
/* cut edge: 0x1803bca9a -> 0x1803bf558 */
/* cut edge: 0x1803bcac9 -> 0x1803c7c8c */
/* cut edge: 0x1803bcb08 -> 0x1803c7c8a */
/* cut edge: 0x1803bcb20 -> 0x1803c7c8c */
/* cut edge: 0x1803bcb25 -> 0x1803351d6 */
/* cut edge: 0x1803bcb63 -> 0x1803351d6 */
/* cut edge: 0x1803bcba9 -> 0x1803351d6 */
/* cut edge: 0x1803bcbe2 -> 0x1803351d6 */
/* cut edge: 0x1803bcc20 -> 0x1803351d6 */
/* cut edge: 0x1803bcdb5 -> 0x1803351d6 */
/* cut edge: 0x1803bcea7 -> 0x1803351d6 */
/* cut edge: 0x1803bceeb -> 0x1803351d6 */
/* cut edge: 0x1803bd061 -> 0x1803351d6 */
/* cut edge: 0x1803bd14c -> 0x1803351d6 */
/* cut edge: 0x1803bd307 -> 0x1803351d6 */
/* cut edge: 0x1803bd43c -> 0x1803351d6 */
/* cut edge: 0x1803bd477 -> 0x1803351d6 */
/* cut edge: 0x1803bd61c -> 0x1803351d6 */
/* cut edge: 0x1803bd6f7 -> 0x1803351d6 */
/* cut edge: 0x1803bd732 -> 0x1803351d6 */
/* cut edge: 0x1803bd748 -> 0x1803bd8d7 */
/* cut edge: 0x1803bd76a -> 0x1803bd8d7 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803bd8d7 original byte 0x48 replaced by RET */
/* target 0x1803bf530 original byte 0x48 replaced by RET */
/* target 0x1803bf558 original byte 0x31 replaced by RET */
/* target 0x1803bf55a original byte 0x48 replaced by RET */
/* target 0x1803c7c8a original byte 0x31 replaced by RET */
/* target 0x1803c7c8c original byte 0x48 replaced by RET */


void region_00137_1803bc95c(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  char cVar2;
  uint uVar3;
  uint uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  longlong lVar8;
  longlong lVar9;
  ulonglong uVar10;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000060;
  longlong in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000060,in_stack_00000070,0x15f4);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  lVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_00000080,uVar6)
  ;
  cVar2 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar2 != '\0') {
    return;
  }
  if (in_stack_00000068 == 0) {
    return;
  }
  if (lVar8 != 0) {
    cVar2 = (**(code **)(*in_stack_00000048 + 0xc0))(in_stack_00000048,in_stack_00000068,lVar8);
    if (cVar2 != '\0') {
      return;
    }
    pcVar1 = *(code **)(*in_stack_00000048 + 0x100);
    uVar5 = FUN_18000d210(in_stack_00000048);
    cVar2 = (*pcVar1)(in_stack_00000048,lVar8,uVar5);
    if (cVar2 != '\0') {
      uVar3 = (**(code **)(*in_stack_00000048 + 0x520))(in_stack_00000048,in_stack_00000068);
      uVar4 = (**(code **)(*in_stack_00000048 + 0x520))(in_stack_00000048,lVar8);
      if (uVar3 == uVar4) {
        lVar9 = (**(code **)(*in_stack_00000048 + 0x700))(in_stack_00000048,in_stack_00000068,0);
        lVar8 = (**(code **)(*in_stack_00000048 + 0x700))(in_stack_00000048,lVar8,0);
        if (0 < (int)uVar3) {
          uVar10 = 0;
          do {
            if (*(short *)(lVar9 + uVar10 * 2) != *(short *)(lVar8 + uVar10 * 2)) {
              return;
            }
            uVar10 = uVar10 + 1;
          } while (uVar3 != uVar10);
        }
        return;
      }
    }
  }
  return;
}

