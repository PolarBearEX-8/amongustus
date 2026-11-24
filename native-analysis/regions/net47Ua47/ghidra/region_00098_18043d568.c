/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=98 entry=18043d568 blocks=58 maxInstructions=50000 */

/* cut edge: 0x18043d568 -> 0x18043a71e */
/* cut edge: 0x18043d59e -> 0x18043a944 */
/* cut edge: 0x18043d5d6 -> 0x18043a9a3 */
/* cut edge: 0x18043d60b -> 0x18043a9a3 */
/* cut edge: 0x18043d627 -> 0x18043a9b3 */
/* cut edge: 0x18043d662 -> 0x18043aa0c */
/* cut edge: 0x18043d698 -> 0x180437a06 */
/* cut edge: 0x18043d6e8 -> 0x180473ae8 */
/* cut edge: 0x18043d6f0 -> 0x180473ae8 */
/* cut edge: 0x18043d75b -> 0x18043ee83 */
/* cut edge: 0x18043d787 -> 0x18043eebe */
/* cut edge: 0x18043d7e6 -> 0x18043ef0f */
/* cut edge: 0x18043d7f6 -> 0x18043ef48 */
/* cut edge: 0x18043d8d4 -> 0x180473ae8 */
/* cut edge: 0x18043d9cb -> 0x180473ae8 */
/* cut edge: 0x18043d9e1 -> 0x180473ae8 */
/* cut edge: 0x18043da49 -> 0x18043ef7f */
/* cut edge: 0x18043da70 -> 0x18043efb5 */
/* cut edge: 0x18043dacf -> 0x18043f006 */
/* cut edge: 0x18043dadf -> 0x18043f03f */
/* cut edge: 0x18043dbbb -> 0x180473ae8 */
/* cut edge: 0x18043dcb5 -> 0x180473ae8 */
/* cut edge: 0x18043dccb -> 0x180473ae8 */
/* cut edge: 0x18043dd33 -> 0x18043f075 */
/* cut edge: 0x18043dd5a -> 0x18043f0ab */
/* cut edge: 0x18043ddb6 -> 0x18043f0fc */
/* cut edge: 0x18043ddc6 -> 0x18043f135 */
/* cut edge: 0x18043de9f -> 0x180473ae8 */
/* cut edge: 0x18043df94 -> 0x180473ae8 */
/* cut edge: 0x18043dfaa -> 0x180473ae8 */
/* cut edge: 0x18043e015 -> 0x18043f16b */
/* cut edge: 0x18043e041 -> 0x18043f1a3 */
/* cut edge: 0x18043e0a0 -> 0x18043f1f4 */
/* cut edge: 0x18043e0b0 -> 0x18043f22d */
/* cut edge: 0x18043e18e -> 0x180473ae8 */
/* cut edge: 0x18043e283 -> 0x1803dd67b */
/* cut edge: 0x18043e2a7 -> 0x180473ae8 */
/* cut edge: 0x18043e2af -> 0x180473ae8 */
/* cut edge: 0x18043e31a -> 0x18043f263 */
/* cut edge: 0x18043e346 -> 0x18043f29e */
/* cut edge: 0x18043e3a5 -> 0x18043f2ef */
/* cut edge: 0x18043e3b5 -> 0x18043f328 */
/* cut edge: 0x18043e493 -> 0x180473ae8 */
/* cut edge: 0x18043e493 -> 0x18043e58d */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x180437a06 original byte 0x48 replaced by RET */
/* target 0x18043a71e original byte 0x48 replaced by RET */
/* target 0x18043a944 original byte 0x48 replaced by RET */
/* target 0x18043a9a3 original byte 0x4c replaced by RET */
/* target 0x18043a9b3 original byte 0x48 replaced by RET */
/* target 0x18043aa0c original byte 0x48 replaced by RET */
/* target 0x18043e58d original byte 0x48 replaced by RET */
/* target 0x18043ee83 original byte 0x48 replaced by RET */
/* target 0x18043eebe original byte 0x48 replaced by RET */
/* target 0x18043ef0f original byte 0x48 replaced by RET */
/* target 0x18043ef48 original byte 0x48 replaced by RET */
/* target 0x18043ef7f original byte 0x48 replaced by RET */
/* target 0x18043efb5 original byte 0x48 replaced by RET */
/* target 0x18043f006 original byte 0x48 replaced by RET */
/* target 0x18043f03f original byte 0x48 replaced by RET */
/* target 0x18043f075 original byte 0x48 replaced by RET */
/* target 0x18043f0ab original byte 0x48 replaced by RET */
/* target 0x18043f0fc original byte 0x48 replaced by RET */
/* target 0x18043f135 original byte 0x48 replaced by RET */
/* target 0x18043f16b original byte 0x48 replaced by RET */
/* target 0x18043f1a3 original byte 0x48 replaced by RET */
/* target 0x18043f1f4 original byte 0x48 replaced by RET */
/* target 0x18043f22d original byte 0x48 replaced by RET */
/* target 0x18043f263 original byte 0x48 replaced by RET */
/* target 0x18043f29e original byte 0x48 replaced by RET */
/* target 0x18043f2ef original byte 0x48 replaced by RET */
/* target 0x18043f328 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00098_18043d568(void)

{
  undefined8 uVar1;
  longlong unaff_RBX;
  undefined8 in_stack_00000048;
  
  uVar1 = FUN_18000fbd0(in_stack_00000048);
  DAT_1804b4d40 =
       (**(code **)(unaff_RBX + 0x108))
                 (in_stack_00000048,uVar1,"getClassLoader","()Ljava/lang/ClassLoader;");
  return;
}

