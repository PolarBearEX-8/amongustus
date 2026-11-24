/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=285 entry=18013c213 blocks=43 maxInstructions=100000 */

/* cut edge: 0x18013c213 -> 0x18014d3db */
/* cut edge: 0x18013c32c -> 0x1801253d7 */
/* cut edge: 0x18013c33b -> 0x18014d3db */
/* cut edge: 0x18013c340 -> 0x1801253d7 */
/* cut edge: 0x18013c34f -> 0x1801253d7 */
/* cut edge: 0x18013c37b -> 0x18013db7b */
/* cut edge: 0x18013c39a -> 0x18014d3db */
/* cut edge: 0x18013c3c3 -> 0x18014d3db */
/* cut edge: 0x18013c3d9 -> 0x18014d3db */
/* cut edge: 0x18013c586 -> 0x18014d3db */
/* cut edge: 0x18013c67d -> 0x18014d3db */
/* cut edge: 0x18013c693 -> 0x18014d3db */
/* cut edge: 0x18013c849 -> 0x18014d3db */
/* cut edge: 0x18013c943 -> 0x18014d3db */
/* cut edge: 0x18013c959 -> 0x18014d3db */
/* cut edge: 0x18013cb0a -> 0x18014d3db */
/* cut edge: 0x18013cbff -> 0x18014d3db */
/* cut edge: 0x18013cc15 -> 0x18014d3db */
/* cut edge: 0x18013cdcf -> 0x18014d3db */
/* cut edge: 0x18013cec4 -> 0x1801253c7 */
/* cut edge: 0x18013cef0 -> 0x1801253d7 */
/* cut edge: 0x18013cefa -> 0x1801253d7 */
/* cut edge: 0x18013cf0e -> 0x1800224c4 */
/* cut edge: 0x18013cf44 -> 0x180022520 */
/* cut edge: 0x18013cf79 -> 0x180022520 */
/* cut edge: 0x18013cf95 -> 0x180022530 */
/* cut edge: 0x18013cfce -> 0x180022588 */
/* cut edge: 0x18013d005 -> 0x18012f39e */
/* cut edge: 0x18013d03a -> 0x18012f39e */
/* cut edge: 0x18013d056 -> 0x18012f3b5 */
/* cut edge: 0x18013d090 -> 0x18012f47b */
/* cut edge: 0x18013d0c6 -> 0x18012f4d7 */
/* cut edge: 0x18013d0fb -> 0x18012f4d7 */
/* cut edge: 0x18013d117 -> 0x18012f4e7 */
/* target 0x1800224c4 original byte 0x48 replaced by RET */
/* target 0x180022520 original byte 0x48 replaced by RET */
/* target 0x180022530 original byte 0x48 replaced by RET */
/* target 0x180022588 original byte 0x48 replaced by RET */
/* target 0x1801253c7 original byte 0x48 replaced by RET */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x18012f39e original byte 0x48 replaced by RET */
/* target 0x18012f3b5 original byte 0x48 replaced by RET */
/* target 0x18012f47b original byte 0x48 replaced by RET */
/* target 0x18012f4d7 original byte 0x48 replaced by RET */
/* target 0x18012f4e7 original byte 0x48 replaced by RET */
/* target 0x18013db7b original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00285_18013c213(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  char cVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  longlong in_stack_000000f8;
  
  uVar3 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7a22);
  (*pcVar1)(in_stack_00000048,uVar4,0,uVar5);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar4,1,uVar5);
  uVar3 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar3,in_stack_000000a8,uVar4)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar3,in_stack_000000d8);
  cVar2 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((cVar2 == '\0') && (in_stack_000000f8 == 0)) {
    return;
  }
  return;
}

