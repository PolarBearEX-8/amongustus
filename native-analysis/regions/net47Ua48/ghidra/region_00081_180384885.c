/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=81 entry=180384885 blocks=26 maxInstructions=50000 */

/* cut edge: 0x180384885 -> 0x1803351d6 */
/* cut edge: 0x180384972 -> 0x1803841f9 */
/* cut edge: 0x18038499a -> 0x1803351d6 */
/* cut edge: 0x18038499f -> 0x1803351d6 */
/* cut edge: 0x180384b2d -> 0x1803351d6 */
/* cut edge: 0x180384cdd -> 0x1803351d6 */
/* cut edge: 0x180384e58 -> 0x1803351d6 */
/* cut edge: 0x180384fa5 -> 0x1803351d6 */
/* cut edge: 0x18038510e -> 0x1803351d6 */
/* cut edge: 0x180385200 -> 0x1803351d6 */
/* cut edge: 0x180385368 -> 0x1803351d6 */
/* cut edge: 0x180385455 -> 0x1803351d6 */
/* cut edge: 0x1803855ec -> 0x1803351d6 */
/* cut edge: 0x180385739 -> 0x1803351d6 */
/* cut edge: 0x18038574f -> 0x1803858a2 */
/* cut edge: 0x18038575f -> 0x1803858a2 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803841f9 original byte 0x48 replaced by RET */
/* target 0x1803858a2 original byte 0x48 replaced by RET */


void region_00081_180384885(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  char cVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_00000158;
  
  uVar3 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1193);
  (*pcVar1)(in_stack_00000048,uVar4,0,uVar5);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000158);
  (*pcVar1)(in_stack_00000048,uVar4,1,uVar5);
  uVar3 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar3,in_stack_00000080,uVar4)
  ;
  (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar3,in_stack_00000070);
  cVar2 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((cVar2 == '\0') && (cVar2 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar2 == '\0')) {
    return;
  }
  return;
}

