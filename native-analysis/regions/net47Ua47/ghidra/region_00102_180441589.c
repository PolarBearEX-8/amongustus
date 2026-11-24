/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=102 entry=180441589 blocks=35 maxInstructions=50000 */

/* cut edge: 0x180441589 -> 0x180473ae8 */
/* cut edge: 0x180441685 -> 0x180473ae8 */
/* cut edge: 0x18044169b -> 0x180473ae8 */
/* cut edge: 0x180441703 -> 0x180446332 */
/* cut edge: 0x18044172f -> 0x18044636d */
/* cut edge: 0x18044178e -> 0x1804463bf */
/* cut edge: 0x18044179e -> 0x1804463f9 */
/* cut edge: 0x18044187c -> 0x180473ae8 */
/* cut edge: 0x180441978 -> 0x1804429db */
/* cut edge: 0x1804419af -> 0x180473ae8 */
/* cut edge: 0x1804419b7 -> 0x180473ae8 */
/* cut edge: 0x180441b6f -> 0x180473ae8 */
/* cut edge: 0x180441c63 -> 0x180473ae8 */
/* cut edge: 0x180441c83 -> 0x180473ae8 */
/* cut edge: 0x180441e31 -> 0x180473ae8 */
/* cut edge: 0x180441f28 -> 0x180473ae8 */
/* cut edge: 0x180441f3e -> 0x180473ae8 */
/* cut edge: 0x1804420f0 -> 0x180473ae8 */
/* cut edge: 0x1804421ea -> 0x180473ae8 */
/* cut edge: 0x180442200 -> 0x180473ae8 */
/* cut edge: 0x1804423ac -> 0x180473ae8 */
/* cut edge: 0x1804424a1 -> 0x180473ae8 */
/* cut edge: 0x1804424b7 -> 0x180473ae8 */
/* cut edge: 0x1804424cd -> 0x18044266b */
/* cut edge: 0x18044251f -> 0x18044266b */
/* target 0x18044266b original byte 0x48 replaced by RET */
/* target 0x1804429db original byte 0x48 replaced by RET */
/* target 0x180446332 original byte 0x48 replaced by RET */
/* target 0x18044636d original byte 0x48 replaced by RET */
/* target 0x1804463bf original byte 0x48 replaced by RET */
/* target 0x1804463f9 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00102_180441589(undefined8 param_1,undefined8 param_2)

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
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1e07);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar5 = DAT_1804b5a08;
      uVar7 = DAT_1804b59f0;
      if (cVar6 == '\0') {
        if (DAT_1804b38f8 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000690,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000028b0,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar5,uVar7,uVar8);
          DAT_1804b38f8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b38f8,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x6472);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0x6bb54 ^ in_stack_000000c8 ^ 0xf12b6917275ad70e);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          return;
        }
      }
    }
  }
  return;
}

