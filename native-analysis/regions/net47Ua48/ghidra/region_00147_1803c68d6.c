/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=147 entry=1803c68d6 blocks=33 maxInstructions=50000 */

/* cut edge: 0x1803c68e1 -> 0x1803c7de8 */
/* cut edge: 0x1803c691a -> 0x180334621 */
/* cut edge: 0x1803c6930 -> 0x1803351d6 */
/* cut edge: 0x1803c6937 -> 0x1803c13f1 */
/* cut edge: 0x1803c6946 -> 0x1803c138c */
/* cut edge: 0x1803c694f -> 0x1803351d6 */
/* cut edge: 0x1803c69de -> 0x1803351d6 */
/* cut edge: 0x1803c69f4 -> 0x1803351d6 */
/* cut edge: 0x1803c6b74 -> 0x1803351d6 */
/* cut edge: 0x1803c6c98 -> 0x1803351d6 */
/* cut edge: 0x1803c6e18 -> 0x1803351d6 */
/* cut edge: 0x1803c6f2a -> 0x1803351d6 */
/* cut edge: 0x1803c6f73 -> 0x1803351d6 */
/* cut edge: 0x1803c7151 -> 0x1803351d6 */
/* cut edge: 0x1803c7343 -> 0x1803351d6 */
/* cut edge: 0x1803c74e9 -> 0x1803c137e */
/* cut edge: 0x1803c7632 -> 0x1803351d6 */
/* cut edge: 0x1803c766f -> 0x1803c7ac2 */
/* cut edge: 0x1803c7689 -> 0x1803c7ac2 */
/* target 0x180334621 original byte 0x45 replaced by RET */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803c137e original byte 0xff replaced by RET */
/* target 0x1803c138c original byte 0x48 replaced by RET */
/* target 0x1803c13f1 original byte 0x48 replaced by RET */
/* target 0x1803c7ac2 original byte 0x48 replaced by RET */
/* target 0x1803c7de8 original byte 0x48 replaced by RET */


void region_00147_1803c68d6(void)

{
  char cVar1;
  longlong *in_stack_00000048;
  longlong in_stack_00000108;
  
  if (in_stack_00000108 != 0) {
    if (DAT_1804b5d68 == 0) {
      return;
    }
    (**(code **)(*in_stack_00000048 + 0x100))(in_stack_00000048,in_stack_00000108);
  }
  cVar1 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar1 == '\0') {
    return;
  }
  return;
}

