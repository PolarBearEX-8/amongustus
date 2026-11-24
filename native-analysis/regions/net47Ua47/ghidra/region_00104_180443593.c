/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=104 entry=180443593 blocks=51 maxInstructions=50000 */

/* cut edge: 0x180443593 -> 0x180473ae8 */
/* cut edge: 0x1804435a9 -> 0x180473ae8 */
/* cut edge: 0x180443611 -> 0x180446a05 */
/* cut edge: 0x18044363d -> 0x180446a3d */
/* cut edge: 0x1804436a4 -> 0x180446aa1 */
/* cut edge: 0x1804436b4 -> 0x180446adb */
/* cut edge: 0x180443798 -> 0x180473ae8 */
/* cut edge: 0x180443894 -> 0x1803dd67b */
/* cut edge: 0x1804438c3 -> 0x180473ae8 */
/* cut edge: 0x1804438cb -> 0x180473ae8 */
/* cut edge: 0x180443936 -> 0x180446528 */
/* cut edge: 0x18044395f -> 0x180446560 */
/* cut edge: 0x1804439be -> 0x1804465b1 */
/* cut edge: 0x1804439ce -> 0x1804465ea */
/* cut edge: 0x180443aa9 -> 0x180473ae8 */
/* cut edge: 0x180443ba0 -> 0x180473ae8 */
/* cut edge: 0x180443bb6 -> 0x180473ae8 */
/* cut edge: 0x180443c1e -> 0x18044671b */
/* cut edge: 0x180443c4a -> 0x180446753 */
/* cut edge: 0x180443ca6 -> 0x1804467a4 */
/* cut edge: 0x180443cb6 -> 0x1804467dd */
/* cut edge: 0x180443d91 -> 0x180473ae8 */
/* cut edge: 0x180443e8b -> 0x180473ae8 */
/* cut edge: 0x180443ea1 -> 0x180473ae8 */
/* cut edge: 0x180443f09 -> 0x18044690c */
/* cut edge: 0x180443f32 -> 0x180446944 */
/* cut edge: 0x180443f91 -> 0x180446995 */
/* cut edge: 0x180443fa1 -> 0x1804469ce */
/* cut edge: 0x18044407c -> 0x180473ae8 */
/* cut edge: 0x180444171 -> 0x180473ae8 */
/* cut edge: 0x180444187 -> 0x180473ae8 */
/* cut edge: 0x1804441ef -> 0x180446b12 */
/* cut edge: 0x18044421b -> 0x180446b4a */
/* cut edge: 0x18044427f -> 0x180446ba4 */
/* cut edge: 0x18044428f -> 0x180446bde */
/* cut edge: 0x18044436f -> 0x180473ae8 */
/* cut edge: 0x180444464 -> 0x180473ae8 */
/* cut edge: 0x18044447a -> 0x180473ae8 */
/* cut edge: 0x180444490 -> 0x180444660 */
/* cut edge: 0x1804444ed -> 0x180446c15 */
/* cut edge: 0x180444516 -> 0x180446c4d */
/* cut edge: 0x180444516 -> 0x180444575 */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x180444575 original byte 0x48 replaced by RET */
/* target 0x180444660 original byte 0x48 replaced by RET */
/* target 0x180446528 original byte 0x48 replaced by RET */
/* target 0x180446560 original byte 0x48 replaced by RET */
/* target 0x1804465b1 original byte 0x48 replaced by RET */
/* target 0x1804465ea original byte 0x48 replaced by RET */
/* target 0x18044671b original byte 0x48 replaced by RET */
/* target 0x180446753 original byte 0x48 replaced by RET */
/* target 0x1804467a4 original byte 0x48 replaced by RET */
/* target 0x1804467dd original byte 0x48 replaced by RET */
/* target 0x18044690c original byte 0x48 replaced by RET */
/* target 0x180446944 original byte 0x48 replaced by RET */
/* target 0x180446995 original byte 0x48 replaced by RET */
/* target 0x1804469ce original byte 0x48 replaced by RET */
/* target 0x180446a05 original byte 0x48 replaced by RET */
/* target 0x180446a3d original byte 0x48 replaced by RET */
/* target 0x180446aa1 original byte 0x48 replaced by RET */
/* target 0x180446adb original byte 0x48 replaced by RET */
/* target 0x180446b12 original byte 0x48 replaced by RET */
/* target 0x180446b4a original byte 0x48 replaced by RET */
/* target 0x180446ba4 original byte 0x48 replaced by RET */
/* target 0x180446bde original byte 0x48 replaced by RET */
/* target 0x180446c15 original byte 0x48 replaced by RET */
/* target 0x180446c4d original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00104_180443593(void)

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
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar5 = DAT_1804b5a08;
    uVar10 = DAT_1804b59f0;
    if (cVar6 == '\0') {
      if (DAT_1804b49c0 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000258,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000b70,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar5,uVar10,uVar7);
        DAT_1804b49c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b49c0,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4f08);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         in_stack_00000068 % 0x7a924 ^ in_stack_000000c8 ^ 0x7c5fd3b3aa15b194);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        return;
      }
    }
  }
  return;
}

