/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=18 entry=180031145 blocks=46 maxInstructions=100000 */

/* cut edge: 0x180031145 -> 0x18002c7cb */
/* cut edge: 0x18003117d -> 0x18002c827 */
/* cut edge: 0x1800311b2 -> 0x18002c827 */
/* cut edge: 0x1800311ce -> 0x18002c837 */
/* cut edge: 0x180031207 -> 0x18002c88f */
/* cut edge: 0x18003123e -> 0x18002caad */
/* cut edge: 0x180031276 -> 0x18002cb0c */
/* cut edge: 0x1800312ab -> 0x18002cb0c */
/* cut edge: 0x1800312c7 -> 0x18002cb1c */
/* cut edge: 0x180031300 -> 0x18002cb74 */
/* cut edge: 0x180031337 -> 0x18002cd98 */
/* cut edge: 0x18003136f -> 0x18002cdfa */
/* cut edge: 0x1800313a4 -> 0x18002cdfa */
/* cut edge: 0x1800313c0 -> 0x18002ce0a */
/* cut edge: 0x1800313f9 -> 0x18002ce62 */
/* cut edge: 0x180031430 -> 0x18002d08a */
/* cut edge: 0x180031468 -> 0x18002d0f1 */
/* cut edge: 0x1800314a2 -> 0x18002d0f1 */
/* cut edge: 0x1800314c2 -> 0x18002d101 */
/* cut edge: 0x1800314fc -> 0x18002d159 */
/* cut edge: 0x180031533 -> 0x18014d3db */
/* cut edge: 0x18003168c -> 0x18014d3db */
/* cut edge: 0x180031785 -> 0x1800e9314 */
/* cut edge: 0x180031792 -> 0x18014d3db */
/* cut edge: 0x1800318eb -> 0x18014d3db */
/* cut edge: 0x1800319ed -> 0x18004221c */
/* cut edge: 0x180031a14 -> 0x18014d3db */
/* cut edge: 0x180031b6d -> 0x18014d3db */
/* cut edge: 0x180031c6f -> 0x1800adf10 */
/* cut edge: 0x180031c81 -> 0x18014d3db */
/* cut edge: 0x180031dda -> 0x18014d3db */
/* cut edge: 0x180031ed3 -> 0x18013f6f2 */
/* cut edge: 0x180031ee0 -> 0x18014d3db */
/* cut edge: 0x180032039 -> 0x18014d3db */
/* cut edge: 0x180032039 -> 0x180032132 */
/* target 0x18002c7cb original byte 0x48 replaced by RET */
/* target 0x18002c827 original byte 0x48 replaced by RET */
/* target 0x18002c837 original byte 0x48 replaced by RET */
/* target 0x18002c88f original byte 0x48 replaced by RET */
/* target 0x18002caad original byte 0x48 replaced by RET */
/* target 0x18002cb0c original byte 0x48 replaced by RET */
/* target 0x18002cb1c original byte 0x48 replaced by RET */
/* target 0x18002cb74 original byte 0x48 replaced by RET */
/* target 0x18002cd98 original byte 0x48 replaced by RET */
/* target 0x18002cdfa original byte 0x48 replaced by RET */
/* target 0x18002ce0a original byte 0x48 replaced by RET */
/* target 0x18002ce62 original byte 0x48 replaced by RET */
/* target 0x18002d08a original byte 0x48 replaced by RET */
/* target 0x18002d0f1 original byte 0x4c replaced by RET */
/* target 0x18002d101 original byte 0x48 replaced by RET */
/* target 0x18002d159 original byte 0x48 replaced by RET */
/* target 0x180032132 original byte 0x3b replaced by RET */
/* target 0x18004221c original byte 0x48 replaced by RET */
/* target 0x1800adf10 original byte 0x48 replaced by RET */
/* target 0x1800e9314 original byte 0x48 replaced by RET */
/* target 0x18013f6f2 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00018_180031145(void)

{
  undefined8 uVar1;
  longlong unaff_RDI;
  undefined8 in_stack_00000048;
  
  uVar1 = FUN_180019ff0(in_stack_00000048);
  DAT_1804b52e8 =
       (**(code **)(unaff_RDI + 0x388))
                 (in_stack_00000048,uVar1,&DAT_180493f72,
                  "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                 );
  return;
}

