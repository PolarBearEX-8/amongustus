/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=64 entry=18037386e blocks=48 maxInstructions=50000 */

/* cut edge: 0x18037386e -> 0x1803351d6 */
/* cut edge: 0x1803738b3 -> 0x1803735d1 */
/* cut edge: 0x1803738cd -> 0x1803351d6 */
/* cut edge: 0x1803738d2 -> 0x1803351d6 */
/* cut edge: 0x180373a37 -> 0x1803351d6 */
/* cut edge: 0x180373b24 -> 0x1803351d6 */
/* cut edge: 0x180373b53 -> 0x1803351d6 */
/* cut edge: 0x180373ccf -> 0x1803351d6 */
/* cut edge: 0x180373dd0 -> 0x1803351d6 */
/* cut edge: 0x180373e12 -> 0x1803351d6 */
/* cut edge: 0x180373e57 -> 0x1803351d6 */
/* cut edge: 0x180373eb8 -> 0x1803351d6 */
/* cut edge: 0x180373ebd -> 0x1803351d6 */
/* cut edge: 0x180374036 -> 0x1803351d6 */
/* cut edge: 0x180374134 -> 0x1803351d6 */
/* cut edge: 0x180374171 -> 0x1803351d6 */
/* cut edge: 0x1803741cb -> 0x1803351d6 */
/* cut edge: 0x1803741d0 -> 0x1803351d6 */
/* cut edge: 0x180374335 -> 0x1803351d6 */
/* cut edge: 0x180374422 -> 0x1803351d6 */
/* cut edge: 0x180374451 -> 0x1803351d6 */
/* cut edge: 0x180374480 -> 0x1803351d6 */
/* cut edge: 0x1803744de -> 0x1803351d6 */
/* cut edge: 0x180374646 -> 0x1803351d6 */
/* cut edge: 0x180374733 -> 0x1803351d6 */
/* cut edge: 0x180374778 -> 0x1803351d6 */
/* cut edge: 0x1803747ac -> 0x1803351d6 */
/* cut edge: 0x1803747e5 -> 0x1803813d9 */
/* cut edge: 0x1803747fe -> 0x1803351d6 */
/* cut edge: 0x180374811 -> 0x180374963 */
/* cut edge: 0x180374821 -> 0x180374963 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803735d1 original byte 0x48 replaced by RET */
/* target 0x180374963 original byte 0x48 replaced by RET */
/* target 0x1803813d9 original byte 0x48 replaced by RET */


void region_00064_18037386e(void)

{
  char cVar1;
  undefined4 unaff_R12D;
  longlong *in_stack_00000048;
  undefined8 in_stack_000000b0;
  
  (**(code **)(*in_stack_00000048 + 0x680))
            (in_stack_00000048,in_stack_000000b0,unaff_R12D,1,&stack0x00000050);
  cVar1 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((cVar1 == '\0') && (cVar1 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar1 == '\0')) {
    return;
  }
  return;
}

