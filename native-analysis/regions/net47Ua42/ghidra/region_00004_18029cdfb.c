/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=4 entry=18029cdfb blocks=58 maxInstructions=50000 */

/* cut edge: 0x18029cdfb -> 0x180333186 */
/* cut edge: 0x18029ce11 -> 0x180333186 */
/* cut edge: 0x18029ce79 -> 0x1802b07c9 */
/* cut edge: 0x18029cea2 -> 0x1802b0801 */
/* cut edge: 0x18029cefb -> 0x1802b084f */
/* cut edge: 0x18029cf13 -> 0x1802b0888 */
/* cut edge: 0x18029cfed -> 0x180333186 */
/* cut edge: 0x18029d0e2 -> 0x1802a2b28 */
/* cut edge: 0x18029d101 -> 0x1802a2b28 */
/* cut edge: 0x18029d10f -> 0x1802a546c */
/* cut edge: 0x18029d139 -> 0x180333186 */
/* cut edge: 0x18029d154 -> 0x180333186 */
/* cut edge: 0x18029d1bc -> 0x1802acbdf */
/* cut edge: 0x18029d1e5 -> 0x1802acc17 */
/* cut edge: 0x18029d241 -> 0x1802acc68 */
/* cut edge: 0x18029d251 -> 0x1802acca1 */
/* cut edge: 0x18029d329 -> 0x180333186 */
/* cut edge: 0x18029d420 -> 0x180333186 */
/* cut edge: 0x18029d436 -> 0x180333186 */
/* cut edge: 0x18029d49e -> 0x1802accd8 */
/* cut edge: 0x18029d4c2 -> 0x1802acd0b */
/* cut edge: 0x18029d526 -> 0x1802acd5c */
/* cut edge: 0x18029d536 -> 0x1802acd95 */
/* cut edge: 0x18029d611 -> 0x180333186 */
/* cut edge: 0x18029d706 -> 0x180333186 */
/* cut edge: 0x18029d71c -> 0x180333186 */
/* cut edge: 0x18029d787 -> 0x1802acdcc */
/* cut edge: 0x18029d7b8 -> 0x1802ace04 */
/* cut edge: 0x18029d817 -> 0x1802ace55 */
/* cut edge: 0x18029d827 -> 0x1802ace8e */
/* cut edge: 0x18029d90a -> 0x180333186 */
/* cut edge: 0x18029d9ff -> 0x180333186 */
/* cut edge: 0x18029da15 -> 0x180333186 */
/* cut edge: 0x18029da7d -> 0x1802acec6 */
/* cut edge: 0x18029daa9 -> 0x1802acefe */
/* cut edge: 0x18029db08 -> 0x1802acf4d */
/* cut edge: 0x18029db23 -> 0x1802acf88 */
/* cut edge: 0x18029dc0a -> 0x180333186 */
/* cut edge: 0x18029dd06 -> 0x1802f250a */
/* cut edge: 0x18029dd63 -> 0x180333186 */
/* cut edge: 0x18029dd63 -> 0x18029dd79 */
/* target 0x18029dd79 original byte 0x48 replaced by RET */
/* target 0x1802a2b28 original byte 0x48 replaced by RET */
/* target 0x1802a546c original byte 0x48 replaced by RET */
/* target 0x1802acbdf original byte 0x48 replaced by RET */
/* target 0x1802acc17 original byte 0x48 replaced by RET */
/* target 0x1802acc68 original byte 0x48 replaced by RET */
/* target 0x1802acca1 original byte 0x48 replaced by RET */
/* target 0x1802accd8 original byte 0x48 replaced by RET */
/* target 0x1802acd0b original byte 0x48 replaced by RET */
/* target 0x1802acd5c original byte 0x48 replaced by RET */
/* target 0x1802acd95 original byte 0x48 replaced by RET */
/* target 0x1802acdcc original byte 0x48 replaced by RET */
/* target 0x1802ace04 original byte 0x48 replaced by RET */
/* target 0x1802ace55 original byte 0x48 replaced by RET */
/* target 0x1802ace8e original byte 0x48 replaced by RET */
/* target 0x1802acec6 original byte 0x48 replaced by RET */
/* target 0x1802acefe original byte 0x48 replaced by RET */
/* target 0x1802acf4d original byte 0x48 replaced by RET */
/* target 0x1802acf88 original byte 0x48 replaced by RET */
/* target 0x1802b07c9 original byte 0x48 replaced by RET */
/* target 0x1802b0801 original byte 0x48 replaced by RET */
/* target 0x1802b084f original byte 0x48 replaced by RET */
/* target 0x1802b0888 original byte 0x48 replaced by RET */
/* target 0x1802f250a original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00004_18029cdfb(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  longlong lVar10;
  longlong unaff_R12;
  longlong *in_stack_00000040;
  longlong in_stack_00000070;
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
  longlong in_stack_00000108;
  int in_stack_00000120;
  
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    lVar10 = DAT_1804b5a08;
    uVar9 = DAT_1804b59f0;
    if (cVar5 == '\0') {
      if (DAT_1804b1618 == 0) {
        pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar6 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000003c6,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000017f8,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
        (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar7,uVar8);
        uVar9 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar10,uVar9,uVar6);
        DAT_1804b1618 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar9);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar9);
      }
      uVar9 = (**(code **)(*in_stack_00000040 + 0x110))
                        (in_stack_00000040,DAT_1804b1618,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1641);
      (*pcVar1)(in_stack_00000040,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         in_stack_00000070 % 0xbffff ^ in_stack_000000b0 ^ 0x8741a998881196a3);
      (*pcVar1)(in_stack_00000040,uVar6,1,uVar7);
      uVar9 = (**(code **)(*in_stack_00000040 + 0x110))
                        (in_stack_00000040,uVar9,in_stack_000000a8,uVar6);
      lVar10 = (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar9,in_stack_000000d0);
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (cVar5 == '\0') {
        if ((lVar10 + unaff_R12 * in_stack_00000108 == -0x8000000000000000) &&
           (in_stack_00000120 == -1)) {
          return;
        }
        return;
      }
    }
  }
  return;
}

