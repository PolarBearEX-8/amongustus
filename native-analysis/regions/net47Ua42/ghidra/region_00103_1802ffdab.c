/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=103 entry=1802ffdab blocks=64 maxInstructions=50000 */

/* cut edge: 0x1802ffdab -> 0x180333186 */
/* cut edge: 0x1802ffdc1 -> 0x180333186 */
/* cut edge: 0x1802ffe29 -> 0x18030843e */
/* cut edge: 0x1802ffe55 -> 0x180308476 */
/* cut edge: 0x1802ffeb9 -> 0x1803084d1 */
/* cut edge: 0x1802ffec9 -> 0x18030850b */
/* cut edge: 0x1802fffaa -> 0x180333186 */
/* cut edge: 0x1803000a6 -> 0x18029ad3c */
/* cut edge: 0x1803000ca -> 0x1802f24e4 */
/* cut edge: 0x1803000e5 -> 0x180333186 */
/* cut edge: 0x1803000fb -> 0x180333186 */
/* cut edge: 0x180300163 -> 0x1803054fd */
/* cut edge: 0x18030018c -> 0x180305535 */
/* cut edge: 0x1803001e8 -> 0x180305586 */
/* cut edge: 0x1803001f8 -> 0x1803055bf */
/* cut edge: 0x1803002d0 -> 0x180333186 */
/* cut edge: 0x1803003c7 -> 0x180333186 */
/* cut edge: 0x1803003dd -> 0x180333186 */
/* cut edge: 0x180300448 -> 0x1803055f6 */
/* cut edge: 0x180300471 -> 0x18030562e */
/* cut edge: 0x1803004d0 -> 0x18030567f */
/* cut edge: 0x1803004e0 -> 0x1803056b8 */
/* cut edge: 0x1803005bb -> 0x180333186 */
/* cut edge: 0x1803006b2 -> 0x180333186 */
/* cut edge: 0x1803006c8 -> 0x180333186 */
/* cut edge: 0x180300730 -> 0x1803056ef */
/* cut edge: 0x180300754 -> 0x180305725 */
/* cut edge: 0x1803007b3 -> 0x180305776 */
/* cut edge: 0x1803007c3 -> 0x1803057af */
/* cut edge: 0x18030089b -> 0x180333186 */
/* cut edge: 0x180300990 -> 0x180333186 */
/* cut edge: 0x1803009a6 -> 0x180333186 */
/* cut edge: 0x180300a11 -> 0x1803057e5 */
/* cut edge: 0x180300a3d -> 0x18030581d */
/* cut edge: 0x180300aa1 -> 0x180305877 */
/* cut edge: 0x180300ab1 -> 0x1803058b0 */
/* cut edge: 0x180300bf0 -> 0x180333186 */
/* cut edge: 0x180300c9c -> 0x18029ad3c */
/* cut edge: 0x180300ca9 -> 0x18029ad8e */
/* cut edge: 0x180300ce1 -> 0x18029addb */
/* cut edge: 0x180300d17 -> 0x18029addb */
/* cut edge: 0x180300d33 -> 0x18029adfd */
/* cut edge: 0x180300d6c -> 0x18029ae55 */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x18029ad8e original byte 0x4c replaced by RET */
/* target 0x18029addb original byte 0x48 replaced by RET */
/* target 0x18029adfd original byte 0x48 replaced by RET */
/* target 0x18029ae55 original byte 0x48 replaced by RET */
/* target 0x1802f24e4 original byte 0x48 replaced by RET */
/* target 0x1803054fd original byte 0x48 replaced by RET */
/* target 0x180305535 original byte 0x48 replaced by RET */
/* target 0x180305586 original byte 0x48 replaced by RET */
/* target 0x1803055bf original byte 0x48 replaced by RET */
/* target 0x1803055f6 original byte 0x48 replaced by RET */
/* target 0x18030562e original byte 0x48 replaced by RET */
/* target 0x18030567f original byte 0x48 replaced by RET */
/* target 0x1803056b8 original byte 0x48 replaced by RET */
/* target 0x1803056ef original byte 0x48 replaced by RET */
/* target 0x180305725 original byte 0x48 replaced by RET */
/* target 0x180305776 original byte 0x48 replaced by RET */
/* target 0x1803057af original byte 0x48 replaced by RET */
/* target 0x1803057e5 original byte 0x48 replaced by RET */
/* target 0x18030581d original byte 0x48 replaced by RET */
/* target 0x180305877 original byte 0x48 replaced by RET */
/* target 0x1803058b0 original byte 0x48 replaced by RET */
/* target 0x18030843e original byte 0x48 replaced by RET */
/* target 0x180308476 original byte 0x48 replaced by RET */
/* target 0x1803084d1 original byte 0x48 replaced by RET */
/* target 0x18030850b original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00103_1802ffdab(void)

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
  longlong *in_stack_00000040;
  longlong in_stack_00000060;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    lVar5 = DAT_1804b5a08;
    uVar10 = DAT_1804b59f0;
    if (cVar6 == '\0') {
      if (DAT_1804b19f8 == 0) {
        pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000002ce,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001088,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
        (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar10,uVar7);
        DAT_1804b19f8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar10);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b19f8,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x13d7);
      (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         in_stack_00000060 % 0xa4a51 ^ in_stack_000000b0 ^ 0xcfc781e5e89a7465);
      (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,uVar10,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar10,in_stack_000000d0);
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (cVar6 == '\0') {
        return;
      }
    }
  }
  return;
}

