/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=29 entry=1802b5d84 blocks=48 maxInstructions=50000 */

/* cut edge: 0x1802b5d84 -> 0x1802aaf39 */
/* cut edge: 0x1802b5db9 -> 0x1802aaf39 */
/* cut edge: 0x1802b5dd5 -> 0x1802aaf49 */
/* cut edge: 0x1802b5e0e -> 0x1802aafa1 */
/* cut edge: 0x1802b5e45 -> 0x1802ab1bc */
/* cut edge: 0x1802b5e7d -> 0x1802ab218 */
/* cut edge: 0x1802b5eb2 -> 0x1802ab218 */
/* cut edge: 0x1802b5ece -> 0x1802ab228 */
/* cut edge: 0x1802b5f07 -> 0x1802ab280 */
/* cut edge: 0x1802b5f3e -> 0x1802ab4a1 */
/* cut edge: 0x1802b5f76 -> 0x1802ab500 */
/* cut edge: 0x1802b5fab -> 0x1802ab500 */
/* cut edge: 0x1802b5fc7 -> 0x1802ab510 */
/* cut edge: 0x1802b6000 -> 0x1802ab568 */
/* cut edge: 0x1802b6037 -> 0x1802ab78d */
/* cut edge: 0x1802b6072 -> 0x1802ab7f1 */
/* cut edge: 0x1802b60ac -> 0x1802ab7f1 */
/* cut edge: 0x1802b60cc -> 0x1802ab801 */
/* cut edge: 0x1802b6105 -> 0x1802ab859 */
/* cut edge: 0x1802b613c -> 0x180333186 */
/* cut edge: 0x1802b6287 -> 0x180333186 */
/* cut edge: 0x1802b6380 -> 0x1802c9fdc */
/* cut edge: 0x1802b638d -> 0x180333186 */
/* cut edge: 0x1802b64e6 -> 0x180333186 */
/* cut edge: 0x1802b65e8 -> 0x1802d63f7 */
/* cut edge: 0x1802b65fa -> 0x180333186 */
/* cut edge: 0x1802b6753 -> 0x180333186 */
/* cut edge: 0x1802b684c -> 0x1803207b0 */
/* cut edge: 0x1802b6867 -> 0x180333186 */
/* cut edge: 0x1802b69b2 -> 0x180333186 */
/* cut edge: 0x1802b6aab -> 0x180312408 */
/* cut edge: 0x1802b6ac6 -> 0x180333186 */
/* cut edge: 0x1802b6c11 -> 0x180333186 */
/* cut edge: 0x1802b6d0a -> 0x18029cb1a */
/* cut edge: 0x1802b6d17 -> 0x180333186 */
/* cut edge: 0x1802b6d2d -> 0x1802b6e70 */
/* cut edge: 0x1802b6d3d -> 0x1802b6e70 */
/* target 0x18029cb1a original byte 0x48 replaced by RET */
/* target 0x1802aaf39 original byte 0x48 replaced by RET */
/* target 0x1802aaf49 original byte 0x48 replaced by RET */
/* target 0x1802aafa1 original byte 0x48 replaced by RET */
/* target 0x1802ab1bc original byte 0x48 replaced by RET */
/* target 0x1802ab218 original byte 0x48 replaced by RET */
/* target 0x1802ab228 original byte 0x48 replaced by RET */
/* target 0x1802ab280 original byte 0x48 replaced by RET */
/* target 0x1802ab4a1 original byte 0x48 replaced by RET */
/* target 0x1802ab500 original byte 0x48 replaced by RET */
/* target 0x1802ab510 original byte 0x48 replaced by RET */
/* target 0x1802ab568 original byte 0x48 replaced by RET */
/* target 0x1802ab78d original byte 0x48 replaced by RET */
/* target 0x1802ab7f1 original byte 0x4c replaced by RET */
/* target 0x1802ab801 original byte 0x48 replaced by RET */
/* target 0x1802ab859 original byte 0x48 replaced by RET */
/* target 0x1802b6e70 original byte 0x48 replaced by RET */
/* target 0x1802c9fdc original byte 0x48 replaced by RET */
/* target 0x1802d63f7 original byte 0x48 replaced by RET */
/* target 0x180312408 original byte 0x84 replaced by RET */
/* target 0x1803207b0 original byte 0x84 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


longlong region_00029_1802b5d84(void)

{
  longlong lVar1;
  undefined8 uVar2;
  longlong lVar3;
  longlong lVar4;
  longlong unaff_RBX;
  bool bVar5;
  longlong *in_stack_00000040;
  
  uVar2 = (**(code **)(unaff_RBX + 0x30))(in_stack_00000040,"java/lang/invoke/MethodType");
  lVar3 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar2);
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
    lVar4 = (**(code **)(*in_stack_00000040 + 0xb0))(in_stack_00000040,lVar3);
  }
  return lVar4;
}

