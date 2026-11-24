/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=105 entry=18039c867 blocks=55 maxInstructions=50000 */

/* cut edge: 0x18039c867 -> 0x1803351d6 */
/* cut edge: 0x18039c89a -> 0x1803d84f7 */
/* cut edge: 0x18039c8be -> 0x1803d852d */
/* cut edge: 0x18039c91d -> 0x1803d857e */
/* cut edge: 0x18039c92d -> 0x1803d85b8 */
/* cut edge: 0x18039cb74 -> 0x1803351d6 */
/* cut edge: 0x18039cb99 -> 0x1803c930f */
/* cut edge: 0x18039cbd1 -> 0x1803351d6 */
/* cut edge: 0x18039cbe7 -> 0x1803abac3 */
/* cut edge: 0x18039cbf0 -> 0x1803d34cd */
/* cut edge: 0x18039cc12 -> 0x1803351d6 */
/* cut edge: 0x18039cc30 -> 0x1803d351b */
/* cut edge: 0x18039cc4f -> 0x1803351d6 */
/* cut edge: 0x18039cc75 -> 0x1803d356c */
/* cut edge: 0x18039cc9e -> 0x1803351d6 */
/* cut edge: 0x18039ccc4 -> 0x1803d35a2 */
/* cut edge: 0x18039cce3 -> 0x1803351d6 */
/* cut edge: 0x18039cd07 -> 0x1803d35f3 */
/* cut edge: 0x18039cd2b -> 0x1803351d6 */
/* cut edge: 0x18039cd50 -> 0x1803351d6 */
/* cut edge: 0x18039cd7e -> 0x1803d3629 */
/* cut edge: 0x18039cd9d -> 0x1803351d6 */
/* cut edge: 0x18039cdca -> 0x1803351d6 */
/* cut edge: 0x18039cf5c -> 0x1803d3757 */
/* cut edge: 0x18039cfc1 -> 0x1803d37a8 */
/* cut edge: 0x18039cfd9 -> 0x1803351d6 */
/* cut edge: 0x18039d066 -> 0x1803351d6 */
/* cut edge: 0x18039d09b -> 0x1803351d6 */
/* cut edge: 0x18039d24a -> 0x1803351d6 */
/* cut edge: 0x18039d349 -> 0x1803351d6 */
/* cut edge: 0x18039d37c -> 0x1803d38dd */
/* cut edge: 0x18039d39b -> 0x1803351d6 */
/* cut edge: 0x18039d3c1 -> 0x1803351d6 */
/* cut edge: 0x18039d58e -> 0x1803351d6 */
/* cut edge: 0x18039d6d8 -> 0x1803351d6 */
/* cut edge: 0x18039d6ee -> 0x18039d86e */
/* cut edge: 0x18039d6fe -> 0x1803d365f */
/* cut edge: 0x18039d722 -> 0x1803d3695 */
/* cut edge: 0x18039d781 -> 0x1803d36e6 */
/* cut edge: 0x18039d791 -> 0x1803d3720 */
/* cut edge: 0x18039d7ee -> 0x18039d86e */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18039d86e original byte 0x49 replaced by RET */
/* target 0x1803abac3 original byte 0x48 replaced by RET */
/* target 0x1803c930f original byte 0x48 replaced by RET */
/* target 0x1803d34cd original byte 0x48 replaced by RET */
/* target 0x1803d351b original byte 0x48 replaced by RET */
/* target 0x1803d356c original byte 0x48 replaced by RET */
/* target 0x1803d35a2 original byte 0x48 replaced by RET */
/* target 0x1803d35f3 original byte 0x4c replaced by RET */
/* target 0x1803d3629 original byte 0x4c replaced by RET */
/* target 0x1803d365f original byte 0x48 replaced by RET */
/* target 0x1803d3695 original byte 0x48 replaced by RET */
/* target 0x1803d36e6 original byte 0x48 replaced by RET */
/* target 0x1803d3720 original byte 0x48 replaced by RET */
/* target 0x1803d3757 original byte 0x48 replaced by RET */
/* target 0x1803d37a8 original byte 0x48 replaced by RET */
/* target 0x1803d38dd original byte 0x48 replaced by RET */
/* target 0x1803d84f7 original byte 0x48 replaced by RET */
/* target 0x1803d852d original byte 0x48 replaced by RET */
/* target 0x1803d857e original byte 0x48 replaced by RET */
/* target 0x1803d85b8 original byte 0x48 replaced by RET */


code * region_00105_18039c867(void)

{
  code *pcVar1;
  longlong lVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  code *pcVar6;
  code *pcVar7;
  code *pcVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 unaff_R13;
  bool bVar11;
  code *in_stack_00000048;
  undefined8 uStack0000000000000050;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000c8;
  longlong in_stack_00000108;
  undefined8 in_stack_00000168;
  undefined8 in_stack_00000170;
  
  uStack0000000000000050 = in_stack_00000170;
  pcVar6 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
  uVar10 = DAT_1804b59f0;
  lVar4 = DAT_1804b59e8;
  if ((char)pcVar6 == '\0') {
    if (DAT_1804b3310 == 0) {
      pcVar6 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
      if (DAT_1804b59e8 == 0) {
        return pcVar6;
      }
      pcVar7 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00000316,1);
      lVar3 = DAT_1804b4d38;
      lVar2 = DAT_1804b4d30;
      pcVar1 = *(code **)(*(longlong *)in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar7;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar7;
      }
      pcVar8 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00005790,0x2a);
      if (DAT_1804b4d40 == 0) {
        return pcVar8;
      }
      uVar9 = (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                        (in_stack_00000048,in_stack_00000058);
      (*pcVar1)(in_stack_00000048,lVar2,lVar3,pcVar8,uVar9);
      uVar10 = (*pcVar6)(in_stack_00000048,in_stack_00000058,lVar4,uVar10,pcVar7);
      DAT_1804b3310 = (**(code **)(*(longlong *)in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10)
      ;
      (**(code **)(*(longlong *)in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      unaff_R13 = in_stack_00000070;
    }
    uStack0000000000000050 =
         (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b3310,in_stack_00000078);
    uVar10 = (**(code **)(*(longlong *)in_stack_00000048 + 0x560))
                       (in_stack_00000048,5,in_stack_00000088,0);
    (**(code **)(*(longlong *)in_stack_00000048 + 0x570))
              (in_stack_00000048,uVar10,0,in_stack_00000168);
    pcVar6 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000170);
    (*pcVar6)(in_stack_00000048,uVar10,1,uVar9);
    (**(code **)(*(longlong *)in_stack_00000048 + 0x570))(in_stack_00000048,uVar10,2,unaff_R13);
    pcVar6 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc79c58f7839374);
    (*pcVar6)(in_stack_00000048,uVar10,3,uVar9);
    pcVar6 = *(code **)(*(longlong *)in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*(longlong *)in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
    (*pcVar6)(in_stack_00000048,uVar10,4,uVar9);
    (**(code **)(*(longlong *)in_stack_00000048 + 0x110))
              (in_stack_00000048,uStack0000000000000050,in_stack_00000080,uVar10);
    pcVar6 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)pcVar6 == '\0') {
      if (in_stack_00000108 == 0) {
        bVar11 = false;
      }
      else {
        if (DAT_1804b5c20 == 0) {
          return in_stack_00000048;
        }
        cVar5 = (**(code **)(*(longlong *)in_stack_00000048 + 0x100))
                          (in_stack_00000048,in_stack_00000108);
        bVar11 = cVar5 != '\0';
      }
      pcVar6 = (code *)(**(code **)(*(longlong *)in_stack_00000048 + 0x720))();
      if ((char)pcVar6 == '\0') {
        return (code *)(ulonglong)bVar11;
      }
    }
  }
  return pcVar6;
}

