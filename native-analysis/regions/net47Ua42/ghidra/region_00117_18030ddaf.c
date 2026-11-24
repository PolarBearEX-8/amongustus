/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=117 entry=18030ddaf blocks=36 maxInstructions=50000 */

/* cut edge: 0x18030ddaf -> 0x180333186 */
/* cut edge: 0x18030dea4 -> 0x18029ad3c */
/* cut edge: 0x18030decb -> 0x180333186 */
/* cut edge: 0x18030ded3 -> 0x180333186 */
/* cut edge: 0x18030e07c -> 0x180333186 */
/* cut edge: 0x18030e173 -> 0x180333186 */
/* cut edge: 0x18030e189 -> 0x180333186 */
/* cut edge: 0x18030e335 -> 0x180333186 */
/* cut edge: 0x18030e42c -> 0x180333186 */
/* cut edge: 0x18030e442 -> 0x180333186 */
/* cut edge: 0x18030e5f5 -> 0x180333186 */
/* cut edge: 0x18030e6ea -> 0x180333186 */
/* cut edge: 0x18030e700 -> 0x180333186 */
/* cut edge: 0x18030e8b9 -> 0x180333186 */
/* cut edge: 0x18030e9ae -> 0x18031a231 */
/* cut edge: 0x18030e9e1 -> 0x180333186 */
/* cut edge: 0x18030ea1a -> 0x180333186 */
/* cut edge: 0x18030ea5a -> 0x180333186 */
/* cut edge: 0x18030eaa0 -> 0x180333186 */
/* cut edge: 0x18030eae5 -> 0x180333186 */
/* cut edge: 0x18030eb21 -> 0x180333186 */
/* cut edge: 0x18030eb57 -> 0x180333186 */
/* cut edge: 0x18030eb90 -> 0x180333186 */
/* cut edge: 0x18030ebd6 -> 0x180333186 */
/* cut edge: 0x18030ec19 -> 0x180333186 */
/* cut edge: 0x18030ec2f -> 0x180333186 */
/* cut edge: 0x18030ec45 -> 0x18030edde */
/* cut edge: 0x18030ec97 -> 0x18030edde */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x18030edde original byte 0x48 replaced by RET */
/* target 0x18031a231 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


ulonglong region_00117_18030ddaf(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  undefined8 uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  ulonglong uVar5;
  longlong *in_stack_00000040;
  ulonglong in_stack_00000070;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  ulonglong in_stack_000000d8;
  
  uVar2 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,param_2,in_stack_000000a0);
  uVar3 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar4 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x289);
  (*pcVar1)(in_stack_00000040,uVar3,0,uVar4);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar4 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar3,1,uVar4);
  uVar2 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar2,in_stack_000000a8,uVar3)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar2,in_stack_000000d0);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if ((char)uVar5 == '\0') {
    return in_stack_00000070 ^ in_stack_000000d8;
  }
  return uVar5;
}

