/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=87 entry=18038a844 blocks=38 maxInstructions=50000 */

/* cut edge: 0x18038a844 -> 0x1803351d6 */
/* cut edge: 0x18038a87a -> 0x1803351d6 */
/* cut edge: 0x18038aa25 -> 0x1803351d6 */
/* cut edge: 0x18038aaff -> 0x1803351d6 */
/* cut edge: 0x18038ab3d -> 0x1803351d6 */
/* cut edge: 0x18038ab50 -> 0x1803351d6 */
/* cut edge: 0x18038ab82 -> 0x1803351d6 */
/* cut edge: 0x18038abc7 -> 0x1803351d6 */
/* cut edge: 0x18038ac0f -> 0x1803351d6 */
/* cut edge: 0x18038ac4d -> 0x1803351d6 */
/* cut edge: 0x18038aca0 -> 0x1803351d6 */
/* cut edge: 0x18038ad10 -> 0x1803351d6 */
/* cut edge: 0x18038ad3d -> 0x1803351d6 */
/* cut edge: 0x18038aeed -> 0x1803351d6 */
/* cut edge: 0x18038b031 -> 0x1803351d6 */
/* cut edge: 0x18038b079 -> 0x1803351d6 */
/* cut edge: 0x18038b0f3 -> 0x1803351d6 */
/* cut edge: 0x18038b141 -> 0x1803351d6 */
/* cut edge: 0x18038b170 -> 0x1803351d6 */
/* cut edge: 0x18038b1d2 -> 0x1803351d6 */
/* cut edge: 0x18038b202 -> 0x1803351d6 */
/* cut edge: 0x18038b261 -> 0x1803351d6 */
/* cut edge: 0x18038b3e4 -> 0x1803351d6 */
/* cut edge: 0x18038b54b -> 0x1803351d6 */
/* cut edge: 0x18038b6de -> 0x1803351d6 */
/* cut edge: 0x18038b80b -> 0x1803351d6 */
/* cut edge: 0x18038b80b -> 0x18038b84b */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18038b84b original byte 0x84 replaced by RET */


void region_00087_18038a844(void)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  longlong in_RAX;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  ulonglong in_stack_000000c8;
  
  pcVar1 = *(code **)(in_RAX + 0xd8);
  uVar6 = FUN_1803dac50(in_stack_00000048);
  uVar7 = (*pcVar1)(in_stack_00000048,uVar6);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b2c78 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar8 = FUN_1803335a0(in_stack_00000048);
        uVar14 = DAT_1804b59f0;
        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x000004d0,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000048);
        uVar11 = FUN_180002550(in_stack_00000048);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003f30,0x16);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000048);
        uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar13);
        (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
        uVar14 = (*pcVar1)(in_stack_00000048,in_stack_00000058,uVar8,uVar14,uVar9);
        DAT_1804b2c78 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
      }
      uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2c78,in_stack_00000078);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4749);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                         in_stack_000000c8 ^ 0x1b839cca82575d79);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar14,in_stack_00000080,uVar8);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x2d8);
        uVar8 = FUN_1803dacb0(in_stack_00000048);
        (*pcVar1)(in_stack_00000048,uVar7,uVar6,uVar8,uVar14);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          (**(code **)(*in_stack_00000048 + 0x68))(in_stack_00000048,uVar7);
        }
      }
    }
  }
  return;
}

