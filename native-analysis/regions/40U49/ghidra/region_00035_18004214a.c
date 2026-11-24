/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=35 entry=18004214a blocks=53 maxInstructions=100000 */

/* cut edge: 0x18004214a -> 0x180041aac */
/* cut edge: 0x18004217f -> 0x180041aac */
/* cut edge: 0x18004219b -> 0x180041ac1 */
/* cut edge: 0x1800421d5 -> 0x180041b1a */
/* cut edge: 0x18004220d -> 0x180125449 */
/* cut edge: 0x18004221c -> 0x18014d3db */
/* cut edge: 0x180042232 -> 0x18014d3db */
/* cut edge: 0x180042292 -> 0x180043dd2 */
/* cut edge: 0x1800422b9 -> 0x180043e05 */
/* cut edge: 0x18004231a -> 0x180043e56 */
/* cut edge: 0x18004232f -> 0x180043e90 */
/* cut edge: 0x180042421 -> 0x18014d3db */
/* cut edge: 0x180042520 -> 0x18014d3db */
/* cut edge: 0x180042536 -> 0x18014d3db */
/* cut edge: 0x18004259e -> 0x180043fb9 */
/* cut edge: 0x1800425c5 -> 0x180043fec */
/* cut edge: 0x180042626 -> 0x18004403d */
/* cut edge: 0x18004263b -> 0x180044077 */
/* cut edge: 0x18004271c -> 0x18014d3db */
/* cut edge: 0x180042811 -> 0x18014d3db */
/* cut edge: 0x180042827 -> 0x18014d3db */
/* cut edge: 0x180042895 -> 0x1800441a3 */
/* cut edge: 0x1800428b9 -> 0x1800441d6 */
/* cut edge: 0x18004291a -> 0x180044225 */
/* cut edge: 0x180042932 -> 0x18004425f */
/* cut edge: 0x180042a0f -> 0x18014d3db */
/* cut edge: 0x180042b10 -> 0x18014d3db */
/* cut edge: 0x180042b26 -> 0x18014d3db */
/* cut edge: 0x180042b96 -> 0x18004438a */
/* cut edge: 0x180042bba -> 0x1800443bd */
/* cut edge: 0x180042c1b -> 0x18004440e */
/* cut edge: 0x180042c2b -> 0x180044447 */
/* cut edge: 0x180042d03 -> 0x18014d3db */
/* cut edge: 0x180042dfa -> 0x18014d3db */
/* cut edge: 0x180042e10 -> 0x18014d3db */
/* cut edge: 0x180042e7d -> 0x18004447e */
/* cut edge: 0x180042ea6 -> 0x1800444b4 */
/* cut edge: 0x180042f02 -> 0x180044505 */
/* cut edge: 0x180042f12 -> 0x18004453e */
/* cut edge: 0x180042fed -> 0x18014d3db */
/* cut edge: 0x1800430e2 -> 0x1801253fe */
/* cut edge: 0x1800430f6 -> 0x18014d3db */
/* cut edge: 0x18004310c -> 0x18014d3db */
/* cut edge: 0x18004310c -> 0x180043122 */
/* target 0x180041aac original byte 0x4c replaced by RET */
/* target 0x180041ac1 original byte 0x48 replaced by RET */
/* target 0x180041b1a original byte 0x48 replaced by RET */
/* target 0x180043122 original byte 0x83 replaced by RET */
/* target 0x180043dd2 original byte 0x48 replaced by RET */
/* target 0x180043e05 original byte 0x48 replaced by RET */
/* target 0x180043e56 original byte 0x48 replaced by RET */
/* target 0x180043e90 original byte 0x48 replaced by RET */
/* target 0x180043fb9 original byte 0x48 replaced by RET */
/* target 0x180043fec original byte 0x48 replaced by RET */
/* target 0x18004403d original byte 0x48 replaced by RET */
/* target 0x180044077 original byte 0x48 replaced by RET */
/* target 0x1800441a3 original byte 0x48 replaced by RET */
/* target 0x1800441d6 original byte 0x48 replaced by RET */
/* target 0x180044225 original byte 0x48 replaced by RET */
/* target 0x18004425f original byte 0x48 replaced by RET */
/* target 0x18004438a original byte 0x48 replaced by RET */
/* target 0x1800443bd original byte 0x48 replaced by RET */
/* target 0x18004440e original byte 0x48 replaced by RET */
/* target 0x180044447 original byte 0x48 replaced by RET */
/* target 0x18004447e original byte 0x48 replaced by RET */
/* target 0x1800444b4 original byte 0x48 replaced by RET */
/* target 0x180044505 original byte 0x48 replaced by RET */
/* target 0x18004453e original byte 0x48 replaced by RET */
/* target 0x1801253fe original byte 0x48 replaced by RET */
/* target 0x180125449 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


longlong region_00035_18004214a(void)

{
  longlong lVar1;
  undefined8 uVar2;
  longlong lVar3;
  longlong lVar4;
  longlong unaff_RDI;
  bool bVar5;
  longlong *in_stack_00000048;
  
  uVar2 = (**(code **)(unaff_RDI + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
  lVar3 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar2);
  lVar4 = 0;
  LOCK();
  bVar5 = DAT_1804b4d30 != 0;
  lVar1 = lVar3;
  if (bVar5) {
    lVar4 = DAT_1804b4d30;
    lVar1 = DAT_1804b4d30;
  }
  DAT_1804b4d30 = lVar1;
  UNLOCK();
  if (bVar5) {
    lVar4 = (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar3);
  }
  return lVar4;
}

