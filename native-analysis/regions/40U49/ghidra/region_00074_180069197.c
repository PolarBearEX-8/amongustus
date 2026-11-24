/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=74 entry=180069197 blocks=56 maxInstructions=100000 */

/* cut edge: 0x180069197 -> 0x18014d3db */
/* cut edge: 0x1800691ad -> 0x18014d3db */
/* cut edge: 0x18006935e -> 0x18014d3db */
/* cut edge: 0x180069455 -> 0x180102a4d */
/* cut edge: 0x18006945f -> 0x18014d3db */
/* cut edge: 0x180069467 -> 0x18014d3db */
/* cut edge: 0x1800694cf -> 0x18007533a */
/* cut edge: 0x1800694fb -> 0x180075375 */
/* cut edge: 0x180069557 -> 0x1800753c6 */
/* cut edge: 0x180069567 -> 0x1800753ff */
/* cut edge: 0x180069642 -> 0x18014d3db */
/* cut edge: 0x180069737 -> 0x18014d3db */
/* cut edge: 0x18006974d -> 0x18014d3db */
/* cut edge: 0x1800697ba -> 0x180075437 */
/* cut edge: 0x1800697e6 -> 0x18007546f */
/* cut edge: 0x180069847 -> 0x1800754c9 */
/* cut edge: 0x180069857 -> 0x180075502 */
/* cut edge: 0x180069934 -> 0x18014d3db */
/* cut edge: 0x180069a2b -> 0x18014d3db */
/* cut edge: 0x180069a41 -> 0x18014d3db */
/* cut edge: 0x180069aae -> 0x18007553a */
/* cut edge: 0x180069ada -> 0x180075575 */
/* cut edge: 0x180069b36 -> 0x1800755c6 */
/* cut edge: 0x180069b46 -> 0x1800755ff */
/* cut edge: 0x180069c21 -> 0x18014d3db */
/* cut edge: 0x180069d18 -> 0x18014d3db */
/* cut edge: 0x180069d2e -> 0x18014d3db */
/* cut edge: 0x180069d9b -> 0x180075637 */
/* cut edge: 0x180069dc4 -> 0x18007566d */
/* cut edge: 0x180069e23 -> 0x1800756be */
/* cut edge: 0x180069e33 -> 0x1800756f7 */
/* cut edge: 0x180069f11 -> 0x18014d3db */
/* cut edge: 0x18006a00b -> 0x180109c10 */
/* cut edge: 0x18006a027 -> 0x1801253ba */
/* cut edge: 0x18006a04a -> 0x18006ac34 */
/* cut edge: 0x18006a061 -> 0x18014d3db */
/* cut edge: 0x18006a077 -> 0x18014d3db */
/* cut edge: 0x18006a08d -> 0x18006a250 */
/* cut edge: 0x18006a0e2 -> 0x180074b8b */
/* cut edge: 0x18006a106 -> 0x180074bbe */
/* cut edge: 0x18006a106 -> 0x18006a167 */
/* target 0x18006a167 original byte 0x48 replaced by RET */
/* target 0x18006a250 original byte 0x48 replaced by RET */
/* target 0x18006ac34 original byte 0x48 replaced by RET */
/* target 0x180074b8b original byte 0x48 replaced by RET */
/* target 0x180074bbe original byte 0x48 replaced by RET */
/* target 0x18007533a original byte 0x48 replaced by RET */
/* target 0x180075375 original byte 0x48 replaced by RET */
/* target 0x1800753c6 original byte 0x48 replaced by RET */
/* target 0x1800753ff original byte 0x48 replaced by RET */
/* target 0x180075437 original byte 0x48 replaced by RET */
/* target 0x18007546f original byte 0x48 replaced by RET */
/* target 0x1800754c9 original byte 0x48 replaced by RET */
/* target 0x180075502 original byte 0x48 replaced by RET */
/* target 0x18007553a original byte 0x48 replaced by RET */
/* target 0x180075575 original byte 0x48 replaced by RET */
/* target 0x1800755c6 original byte 0x48 replaced by RET */
/* target 0x1800755ff original byte 0x48 replaced by RET */
/* target 0x180075637 original byte 0x48 replaced by RET */
/* target 0x18007566d original byte 0x48 replaced by RET */
/* target 0x1800756be original byte 0x48 replaced by RET */
/* target 0x1800756f7 original byte 0x48 replaced by RET */
/* target 0x180102a4d original byte 0x48 replaced by RET */
/* target 0x180109c10 original byte 0x4c replaced by RET */
/* target 0x1801253ba original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00074_180069197(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong *in_stack_00000048;
  longlong in_stack_00000060;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804ae348 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_18014d730(in_stack_00000048);
        uVar12 = DAT_1804b5208;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000035a,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000017a8,5);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,uVar11);
        (*pcVar3)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*pcVar2)(in_stack_00000048,in_stack_00000090,uVar6,uVar12,uVar7);
        DAT_1804ae348 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804ae348,in_stack_000000b0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7db4);
      (*pcVar2)(in_stack_00000048,uVar6,0,uVar7);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         in_stack_00000060 % 0xbcc9b ^ in_stack_000000c0 ^ 0xa154aeb9b287282a);
      (*pcVar2)(in_stack_00000048,uVar6,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar12,in_stack_000000d8);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        return;
      }
    }
  }
  return;
}

