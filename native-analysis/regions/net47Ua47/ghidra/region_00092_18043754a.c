/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=92 entry=18043754a blocks=57 maxInstructions=50000 */

/* cut edge: 0x18043759c -> 0x18043d1a4 */
/* cut edge: 0x1804375cb -> 0x18043d1df */
/* cut edge: 0x180437635 -> 0x18043d242 */
/* cut edge: 0x18043764d -> 0x18043d27b */
/* cut edge: 0x18043773e -> 0x180473ae8 */
/* cut edge: 0x180437852 -> 0x1803dd67b */
/* cut edge: 0x180437870 -> 0x1803dd67b */
/* cut edge: 0x180437889 -> 0x1803dd67b */
/* cut edge: 0x1804378b4 -> 0x1803dd67b */
/* cut edge: 0x1804378fa -> 0x1803dd67b */
/* cut edge: 0x180437919 -> 0x1803dd67b */
/* cut edge: 0x180437935 -> 0x18043abca */
/* cut edge: 0x18043794a -> 0x18043b723 */
/* cut edge: 0x180437954 -> 0x1803dd67b */
/* cut edge: 0x180437961 -> 0x1803dd67b */
/* cut edge: 0x180437992 -> 0x1803dd67b */
/* cut edge: 0x1804379a4 -> 0x1803dd67b */
/* cut edge: 0x1804379b6 -> 0x1803dd67b */
/* cut edge: 0x1804379c0 -> 0x1803dd67b */
/* cut edge: 0x1804379e5 -> 0x1803dd67b */
/* cut edge: 0x1804379f2 -> 0x180439fc6 */
/* cut edge: 0x180437a06 -> 0x180473ae8 */
/* cut edge: 0x180437a55 -> 0x1804385dc */
/* cut edge: 0x180437a66 -> 0x180473ae8 */
/* cut edge: 0x180437a7c -> 0x180473ae8 */
/* cut edge: 0x180437c36 -> 0x180473ae8 */
/* cut edge: 0x180437d2d -> 0x180473ae8 */
/* cut edge: 0x180437d43 -> 0x180473ae8 */
/* cut edge: 0x180437f05 -> 0x180473ae8 */
/* cut edge: 0x180437fff -> 0x180473ae8 */
/* cut edge: 0x180438015 -> 0x180473ae8 */
/* cut edge: 0x1804381ce -> 0x180473ae8 */
/* cut edge: 0x1804382c3 -> 0x180473ae8 */
/* cut edge: 0x1804382d9 -> 0x180473ae8 */
/* cut edge: 0x18043849e -> 0x180473ae8 */
/* cut edge: 0x18043849e -> 0x180438593 */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x180438593 original byte 0x48 replaced by RET */
/* target 0x1804385dc original byte 0x48 replaced by RET */
/* target 0x180439fc6 original byte 0x48 replaced by RET */
/* target 0x18043abca original byte 0x48 replaced by RET */
/* target 0x18043b723 original byte 0x48 replaced by RET */
/* target 0x18043d1a4 original byte 0x48 replaced by RET */
/* target 0x18043d1df original byte 0x48 replaced by RET */
/* target 0x18043d242 original byte 0x48 replaced by RET */
/* target 0x18043d27b original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00092_18043754a(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  longlong *in_stack_00000048;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  
  lVar5 = DAT_1804b5a08;
  uVar10 = DAT_1804b59f0;
  if (DAT_1804b4a00 == 0) {
    pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
    if (DAT_1804b5a08 == 0) {
      return;
    }
    uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000248,1);
    lVar4 = DAT_1804b4d38;
    lVar3 = DAT_1804b4d30;
    pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
    if (DAT_1804b4d30 == 0) {
      return;
    }
    if (DAT_1804b4d38 == 0) {
      return;
    }
    uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000af0,5);
    if (DAT_1804b4d40 == 0) {
      return;
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
    (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
    uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar5,uVar10,uVar7);
    DAT_1804b4a00 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
  }
  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b4a00,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x58bb);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                     in_stack_00000068 % 0x79c89 ^ in_stack_000000c8 ^ 0x33c3d3f1cbba18c4);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    return;
  }
  return;
}

