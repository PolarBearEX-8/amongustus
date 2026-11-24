/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=23 entry=18034a84b blocks=54 maxInstructions=50000 */

/* cut edge: 0x18034a84b -> 0x1803351d6 */
/* cut edge: 0x18034a885 -> 0x1803351d6 */
/* cut edge: 0x18034aa21 -> 0x1803351d6 */
/* cut edge: 0x18034ab0e -> 0x1803351d6 */
/* cut edge: 0x18034ab50 -> 0x18034d5e1 */
/* cut edge: 0x18034ab5b -> 0x1803351d6 */
/* cut edge: 0x18034ab88 -> 0x180365625 */
/* cut edge: 0x18034abb7 -> 0x180365660 */
/* cut edge: 0x18034ac04 -> 0x1803656af */
/* cut edge: 0x18034ac24 -> 0x1803656e9 */
/* cut edge: 0x18034ad08 -> 0x1803351d6 */
/* cut edge: 0x18034adf5 -> 0x1803351d6 */
/* cut edge: 0x18034ae35 -> 0x180365720 */
/* cut edge: 0x18034ae64 -> 0x18036575b */
/* cut edge: 0x18034aec6 -> 0x1803657a9 */
/* cut edge: 0x18034aede -> 0x1803657e2 */
/* cut edge: 0x18034afcc -> 0x1803351d6 */
/* cut edge: 0x18034b0b5 -> 0x1803351d6 */
/* cut edge: 0x18034b0de -> 0x180365819 */
/* cut edge: 0x18034b10d -> 0x180365854 */
/* cut edge: 0x18034b16f -> 0x1803658a5 */
/* cut edge: 0x18034b17f -> 0x1803658de */
/* cut edge: 0x18034b268 -> 0x1803351d6 */
/* cut edge: 0x18034b355 -> 0x1803351d6 */
/* cut edge: 0x18034b380 -> 0x180365915 */
/* cut edge: 0x18034b3af -> 0x180365950 */
/* cut edge: 0x18034b411 -> 0x1803659a1 */
/* cut edge: 0x18034b424 -> 0x1803659da */
/* cut edge: 0x18034b508 -> 0x1803351d6 */
/* cut edge: 0x18034b5f5 -> 0x1803655b4 */
/* cut edge: 0x18034b629 -> 0x1803351d6 */
/* cut edge: 0x18034b657 -> 0x1803351d6 */
/* cut edge: 0x18034b67d -> 0x180365a11 */
/* cut edge: 0x18034b6a4 -> 0x180365a4c */
/* cut edge: 0x18034b6f1 -> 0x180365a9e */
/* cut edge: 0x18034b71c -> 0x180365ad7 */
/* cut edge: 0x18034b7fa -> 0x1803351d6 */
/* cut edge: 0x18034b7fa -> 0x18034b8e7 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18034b8e7 original byte 0x44 replaced by RET */
/* target 0x18034d5e1 original byte 0x48 replaced by RET */
/* target 0x1803655b4 original byte 0x48 replaced by RET */
/* target 0x180365625 original byte 0x48 replaced by RET */
/* target 0x180365660 original byte 0x48 replaced by RET */
/* target 0x1803656af original byte 0x48 replaced by RET */
/* target 0x1803656e9 original byte 0x48 replaced by RET */
/* target 0x180365720 original byte 0x48 replaced by RET */
/* target 0x18036575b original byte 0x48 replaced by RET */
/* target 0x1803657a9 original byte 0x48 replaced by RET */
/* target 0x1803657e2 original byte 0x48 replaced by RET */
/* target 0x180365819 original byte 0x48 replaced by RET */
/* target 0x180365854 original byte 0x48 replaced by RET */
/* target 0x1803658a5 original byte 0x48 replaced by RET */
/* target 0x1803658de original byte 0x48 replaced by RET */
/* target 0x180365915 original byte 0x48 replaced by RET */
/* target 0x180365950 original byte 0x48 replaced by RET */
/* target 0x1803659a1 original byte 0x48 replaced by RET */
/* target 0x1803659da original byte 0x48 replaced by RET */
/* target 0x180365a11 original byte 0x48 replaced by RET */
/* target 0x180365a4c original byte 0x48 replaced by RET */
/* target 0x180365a9e original byte 0x48 replaced by RET */
/* target 0x180365ad7 original byte 0x48 replaced by RET */


void region_00023_18034a84b(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  uint uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  uint unaff_R15D;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000050;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  uint in_stack_000000b0;
  undefined8 in_stack_000000b8;
  code *in_stack_000000d8;
  undefined8 in_stack_000000f0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00000100;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x488))
                    (in_stack_00000048,in_stack_00000058,in_stack_000000b8);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      if (DAT_1804b2230 == 0) {
        lVar1 = *in_stack_00000048;
        in_stack_000000d8 = *(code **)(lVar1 + 0x390);
        in_stack_00000100 = FUN_180333550(in_stack_00000048);
        in_stack_000000f0 = DAT_1804b59f0;
        in_stack_000000f8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000762,1);
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        in_stack_000000b8 = uVar6;
        uVar6 = FUN_1800024f0(in_stack_00000048);
        uVar7 = FUN_180002550(in_stack_00000048);
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001e88,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar9 = FUN_1800025a0(in_stack_00000048);
        uVar10 = in_stack_00000058;
        uVar9 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar9);
        unaff_R15D = in_stack_000000b0;
        (*pcVar2)(in_stack_00000048,uVar6,uVar7,uVar8,uVar9);
        uVar6 = in_stack_000000b8;
        uVar10 = (*in_stack_000000d8)
                           (in_stack_00000048,uVar10,in_stack_00000100,in_stack_000000f0,
                            in_stack_000000f8);
        DAT_1804b2230 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      in_stack_00000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2230,in_stack_00000078);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
      (*pcVar2)(in_stack_00000048,uVar10,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
      (*pcVar2)(in_stack_00000048,uVar10,1,uVar7);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar10);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        (**(code **)(*in_stack_00000048 + 0x658))
                  (in_stack_00000048,uVar6,uVar5 & unaff_R15D,1,&stack0x00000050);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          return;
        }
      }
    }
  }
  return;
}

