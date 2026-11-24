/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=139 entry=180323d93 blocks=53 maxInstructions=50000 */

/* cut edge: 0x180323d93 -> 0x180330d3a */
/* cut edge: 0x180323df7 -> 0x180330d94 */
/* cut edge: 0x180323e07 -> 0x180330dcd */
/* cut edge: 0x180323f4b -> 0x180333186 */
/* cut edge: 0x180323f53 -> 0x180333186 */
/* cut edge: 0x180323fbe -> 0x18033023a */
/* cut edge: 0x180323fe7 -> 0x180330272 */
/* cut edge: 0x180324043 -> 0x1803302c3 */
/* cut edge: 0x180324053 -> 0x1803302fc */
/* cut edge: 0x18032412b -> 0x180333186 */
/* cut edge: 0x180324222 -> 0x180333186 */
/* cut edge: 0x180324238 -> 0x180333186 */
/* cut edge: 0x1803242a3 -> 0x18033042c */
/* cut edge: 0x1803242cc -> 0x180330464 */
/* cut edge: 0x18032432b -> 0x1803304b5 */
/* cut edge: 0x18032433b -> 0x1803304ee */
/* cut edge: 0x180324416 -> 0x180333186 */
/* cut edge: 0x18032450d -> 0x180333186 */
/* cut edge: 0x180324523 -> 0x180333186 */
/* cut edge: 0x18032458b -> 0x180330717 */
/* cut edge: 0x1803245af -> 0x18033074d */
/* cut edge: 0x18032460e -> 0x18033079e */
/* cut edge: 0x18032461e -> 0x1803307d7 */
/* cut edge: 0x1803246f6 -> 0x180333186 */
/* cut edge: 0x1803247eb -> 0x180333186 */
/* cut edge: 0x180324801 -> 0x180333186 */
/* cut edge: 0x180324869 -> 0x1803309ff */
/* cut edge: 0x180324895 -> 0x180330a3a */
/* cut edge: 0x1803248f9 -> 0x180330a94 */
/* cut edge: 0x180324909 -> 0x180330acd */
/* cut edge: 0x180324a48 -> 0x180333186 */
/* cut edge: 0x180324add -> 0x1802f40b3 */
/* cut edge: 0x180324af4 -> 0x180333186 */
/* cut edge: 0x180324b4f -> 0x180333186 */
/* cut edge: 0x180324b65 -> 0x180333186 */
/* cut edge: 0x180324bd0 -> 0x180330333 */
/* cut edge: 0x180324bf9 -> 0x18033036b */
/* cut edge: 0x180324c55 -> 0x1803303bc */
/* cut edge: 0x180324c65 -> 0x1803303f5 */
/* cut edge: 0x180324d3d -> 0x180333186 */
/* cut edge: 0x180324d3d -> 0x180324e34 */
/* target 0x1802f40b3 original byte 0x4c replaced by RET */
/* target 0x180324e34 original byte 0x48 replaced by RET */
/* target 0x18033023a original byte 0x48 replaced by RET */
/* target 0x180330272 original byte 0x48 replaced by RET */
/* target 0x1803302c3 original byte 0x48 replaced by RET */
/* target 0x1803302fc original byte 0x48 replaced by RET */
/* target 0x180330333 original byte 0x48 replaced by RET */
/* target 0x18033036b original byte 0x48 replaced by RET */
/* target 0x1803303bc original byte 0x48 replaced by RET */
/* target 0x1803303f5 original byte 0x48 replaced by RET */
/* target 0x18033042c original byte 0x48 replaced by RET */
/* target 0x180330464 original byte 0x48 replaced by RET */
/* target 0x1803304b5 original byte 0x48 replaced by RET */
/* target 0x1803304ee original byte 0x48 replaced by RET */
/* target 0x180330717 original byte 0x48 replaced by RET */
/* target 0x18033074d original byte 0x48 replaced by RET */
/* target 0x18033079e original byte 0x48 replaced by RET */
/* target 0x1803307d7 original byte 0x48 replaced by RET */
/* target 0x1803309ff original byte 0x48 replaced by RET */
/* target 0x180330a3a original byte 0x48 replaced by RET */
/* target 0x180330a94 original byte 0x48 replaced by RET */
/* target 0x180330acd original byte 0x48 replaced by RET */
/* target 0x180330d3a original byte 0x48 replaced by RET */
/* target 0x180330d94 original byte 0x48 replaced by RET */
/* target 0x180330dcd original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00139_180323d93(void)

{
  code *pcVar1;
  longlong lVar2;
  longlong lVar3;
  char cVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  longlong unaff_RSI;
  longlong *in_stack_00000040;
  code *in_stack_00000058;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 uStack00000000000000e0;
  
  uStack00000000000000e0 = DAT_1804b59f0;
  uVar5 = (**(code **)(unaff_RSI + 0x518))(in_stack_00000040,&stack0x000004da,1);
  lVar3 = DAT_1804b4d38;
  lVar2 = DAT_1804b4d30;
  pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
  if (DAT_1804b4d30 == 0) {
    return;
  }
  if (DAT_1804b4d38 == 0) {
    return;
  }
  uVar6 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001fc8,5);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
  (*pcVar1)(in_stack_00000040,lVar2,lVar3,uVar6,uVar7);
  uVar5 = (*in_stack_00000058)
                    (in_stack_00000040,in_stack_00000080,in_stack_000000c8,uStack00000000000000e0,
                     uVar5);
  DAT_1804b11d0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar5);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar5);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b11d0,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6809);
  (*pcVar1)(in_stack_00000040,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar6,1,uVar7);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar5,in_stack_000000a8,uVar6)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar5,in_stack_000000d0);
  cVar4 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar4 == '\0') {
    return;
  }
  return;
}

