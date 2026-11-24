/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=74 entry=18037d877 blocks=41 maxInstructions=50000 */

/* cut edge: 0x18037d877 -> 0x1803351d6 */
/* cut edge: 0x18037d8c4 -> 0x1803351d6 */
/* cut edge: 0x18037d90a -> 0x18037d5d0 */
/* cut edge: 0x18037d924 -> 0x1803351d6 */
/* cut edge: 0x18037d929 -> 0x18037538a */
/* cut edge: 0x18037d947 -> 0x1803351d6 */
/* cut edge: 0x18037dac0 -> 0x1803351d6 */
/* cut edge: 0x18037dbbe -> 0x1803351d6 */
/* cut edge: 0x18037dbf6 -> 0x1803351d6 */
/* cut edge: 0x18037dc3a -> 0x1803351d6 */
/* cut edge: 0x18037dc79 -> 0x1803351d6 */
/* cut edge: 0x18037dcd7 -> 0x1803351d6 */
/* cut edge: 0x18037dcdc -> 0x1803351d6 */
/* cut edge: 0x18037dd27 -> 0x1803351d6 */
/* cut edge: 0x18037dd6e -> 0x1803351d6 */
/* cut edge: 0x18037ddb5 -> 0x1803351d6 */
/* cut edge: 0x18037df1d -> 0x1803351d6 */
/* cut edge: 0x18037e00a -> 0x1803351d6 */
/* cut edge: 0x18037e04e -> 0x1803351d6 */
/* cut edge: 0x18037e095 -> 0x1803351d6 */
/* cut edge: 0x18037e1fd -> 0x1803351d6 */
/* cut edge: 0x18037e2eb -> 0x1803351d6 */
/* cut edge: 0x18037e461 -> 0x1803351d6 */
/* cut edge: 0x18037e54e -> 0x1803351d6 */
/* cut edge: 0x18037e592 -> 0x1803351d6 */
/* cut edge: 0x18037e5d6 -> 0x1803351d6 */
/* cut edge: 0x18037e73e -> 0x1803351d6 */
/* cut edge: 0x18037e82b -> 0x1803351d6 */
/* cut edge: 0x18037e82b -> 0x18037e876 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18037538a original byte 0x48 replaced by RET */
/* target 0x18037d5d0 original byte 0x48 replaced by RET */
/* target 0x18037e876 original byte 0x48 replaced by RET */


void region_00074_18037d877(void)

{
  uint uVar1;
  char cVar2;
  uint unaff_ESI;
  int unaff_R13D;
  longlong *in_stack_00000048;
  uint in_stack_00000050;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_00000130;
  
  (**(code **)(*in_stack_00000048 + 0x658))
            (in_stack_00000048,in_stack_00000130,(int)in_stack_000000b0 + unaff_R13D,1,
             &stack0x00000050);
  uVar1 = in_stack_00000050;
  cVar2 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar2 == '\0') {
    in_stack_00000050 = uVar1 ^ unaff_ESI;
    (**(code **)(*in_stack_00000048 + 0x698))
              (in_stack_00000048,in_stack_000000a0,unaff_R13D,1,&stack0x00000050);
    cVar2 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((cVar2 == '\0') && (cVar2 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar2 == '\0')) {
      return;
    }
  }
  return;
}

