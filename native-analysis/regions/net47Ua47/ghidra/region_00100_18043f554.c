/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=100 entry=18043f554 blocks=36 maxInstructions=50000 */

/* cut edge: 0x18043f554 -> 0x18043ec0b */
/* cut edge: 0x18043f58f -> 0x18043ec6f */
/* cut edge: 0x18043f5c9 -> 0x18043ec6f */
/* cut edge: 0x18043f5e9 -> 0x18043ec7f */
/* cut edge: 0x18043f622 -> 0x18043ecd8 */
/* cut edge: 0x18043f65a -> 0x180439144 */
/* cut edge: 0x18043f671 -> 0x180473ae8 */
/* cut edge: 0x18043f6b8 -> 0x180473ae8 */
/* cut edge: 0x18043f70e -> 0x180473ae8 */
/* cut edge: 0x18043f724 -> 0x180473ae8 */
/* cut edge: 0x18043f8d2 -> 0x180473ae8 */
/* cut edge: 0x18043f9c9 -> 0x180473ae8 */
/* cut edge: 0x18043f9df -> 0x180473ae8 */
/* cut edge: 0x18043fb91 -> 0x180473ae8 */
/* cut edge: 0x18043fc88 -> 0x180473ae8 */
/* cut edge: 0x18043fc9e -> 0x180473ae8 */
/* cut edge: 0x18043fe51 -> 0x180473ae8 */
/* cut edge: 0x18043ff46 -> 0x180473ae8 */
/* cut edge: 0x18043ff5c -> 0x180473ae8 */
/* cut edge: 0x180440119 -> 0x180473ae8 */
/* cut edge: 0x18044020e -> 0x1803dd67b */
/* cut edge: 0x18044023a -> 0x180473ae8 */
/* cut edge: 0x180440280 -> 0x180473ae8 */
/* cut edge: 0x180440296 -> 0x180473ae8 */
/* cut edge: 0x180440444 -> 0x180473ae8 */
/* cut edge: 0x18044053b -> 0x180473ae8 */
/* cut edge: 0x18044053b -> 0x180440551 */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x180439144 original byte 0x48 replaced by RET */
/* target 0x18043ec0b original byte 0x48 replaced by RET */
/* target 0x18043ec6f original byte 0x48 replaced by RET */
/* target 0x18043ec7f original byte 0x48 replaced by RET */
/* target 0x18043ecd8 original byte 0x48 replaced by RET */
/* target 0x180440551 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00100_18043f554(void)

{
  undefined8 uVar1;
  longlong unaff_RSI;
  undefined8 in_stack_00000048;
  
  uVar1 = FUN_1803334a0(in_stack_00000048);
  DAT_1804b5a08 =
       (**(code **)(unaff_RSI + 0x388))
                 (in_stack_00000048,uVar1,&DAT_180493f72,
                  "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                 );
  return;
}

