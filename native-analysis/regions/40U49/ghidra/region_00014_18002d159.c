/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=14 entry=18002d159 blocks=34 maxInstructions=100000 */

/* cut edge: 0x18002d1e4 -> 0x18014d3db */
/* cut edge: 0x18002d2db -> 0x18006387a */
/* cut edge: 0x18002d2fc -> 0x18014d3db */
/* cut edge: 0x18002d452 -> 0x18014d3db */
/* cut edge: 0x18002d54b -> 0x18013f70f */
/* cut edge: 0x18002d558 -> 0x18014d3db */
/* cut edge: 0x18002d6b1 -> 0x18014d3db */
/* cut edge: 0x18002d7b3 -> 0x1800a9b93 */
/* cut edge: 0x18002d7bd -> 0x18014d3db */
/* cut edge: 0x18002d916 -> 0x18014d3db */
/* cut edge: 0x18002da0f -> 0x18013eab8 */
/* cut edge: 0x18002da1c -> 0x18014d3db */
/* cut edge: 0x18002db75 -> 0x18014d3db */
/* cut edge: 0x18002dc6e -> 0x18007de63 */
/* cut edge: 0x18002dc7b -> 0x18014d3db */
/* cut edge: 0x18002ddd4 -> 0x18014d3db */
/* cut edge: 0x18002ded6 -> 0x180114358 */
/* cut edge: 0x18002dee8 -> 0x18014d3db */
/* cut edge: 0x18002e041 -> 0x18014d3db */
/* cut edge: 0x18002e041 -> 0x18002e13a */
/* target 0x18002e13a original byte 0x3b replaced by RET */
/* target 0x18006387a original byte 0x48 replaced by RET */
/* target 0x18007de63 original byte 0x48 replaced by RET */
/* target 0x1800a9b93 original byte 0x48 replaced by RET */
/* target 0x180114358 original byte 0x48 replaced by RET */
/* target 0x18013eab8 original byte 0x48 replaced by RET */
/* target 0x18013f70f original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00014_18002d159(void)

{
  code *pcVar1;
  char cVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  code *unaff_R12;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000070;
  code *in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000f0;
  undefined8 in_stack_00000100;
  code *in_stack_00000120;
  
  (*unaff_R12)(in_stack_00000048,in_stack_00000090);
  (*in_stack_00000120)(in_stack_00000048,in_stack_00000060);
  uVar3 = (*in_stack_00000088)
                    (in_stack_00000048,in_stack_00000090,in_stack_00000070,in_stack_000000f0,
                     in_stack_00000100);
  DAT_1804acd50 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar3);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar3);
  uVar3 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804acd50,in_stack_000000b0);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2c01);
  (*pcVar1)(in_stack_00000048,uVar4,0,uVar5);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar4,1,uVar5);
  uVar3 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar3,in_stack_000000a8,uVar4)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar3,in_stack_000000d8);
  cVar2 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar2 == '\0') {
    return;
  }
  return;
}

