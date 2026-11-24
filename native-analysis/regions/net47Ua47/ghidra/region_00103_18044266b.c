/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=103 entry=18044266b blocks=45 maxInstructions=50000 */

/* cut edge: 0x18044266b -> 0x180473ae8 */
/* cut edge: 0x180442762 -> 0x180445357 */
/* cut edge: 0x18044277c -> 0x180473ae8 */
/* cut edge: 0x1804428d5 -> 0x180473ae8 */
/* cut edge: 0x1804429ce -> 0x180447c60 */
/* cut edge: 0x1804429db -> 0x180473ae8 */
/* cut edge: 0x1804429f1 -> 0x180473ae8 */
/* cut edge: 0x180442a60 -> 0x180445f45 */
/* cut edge: 0x180442a89 -> 0x180445f7d */
/* cut edge: 0x180442ae8 -> 0x180445fce */
/* cut edge: 0x180442af8 -> 0x180446007 */
/* cut edge: 0x180442bd3 -> 0x180473ae8 */
/* cut edge: 0x180442cc7 -> 0x1804438c3 */
/* cut edge: 0x180442ce2 -> 0x180473ae8 */
/* cut edge: 0x180442cea -> 0x180473ae8 */
/* cut edge: 0x180442d55 -> 0x18044642f */
/* cut edge: 0x180442d7e -> 0x180446467 */
/* cut edge: 0x180442ddd -> 0x1804464b8 */
/* cut edge: 0x180442ded -> 0x1804464f1 */
/* cut edge: 0x180442ec8 -> 0x180473ae8 */
/* cut edge: 0x180442fbf -> 0x180473ae8 */
/* cut edge: 0x180442fd5 -> 0x180473ae8 */
/* cut edge: 0x180443040 -> 0x180446621 */
/* cut edge: 0x18044306c -> 0x180446659 */
/* cut edge: 0x1804430c8 -> 0x1804466aa */
/* cut edge: 0x1804430d8 -> 0x1804466e4 */
/* cut edge: 0x1804431b3 -> 0x180473ae8 */
/* cut edge: 0x1804432aa -> 0x180473ae8 */
/* cut edge: 0x1804432c0 -> 0x180473ae8 */
/* cut edge: 0x180443328 -> 0x180446814 */
/* cut edge: 0x180443352 -> 0x18044684a */
/* cut edge: 0x1804433ae -> 0x18044689c */
/* cut edge: 0x1804433be -> 0x1804468d6 */
/* cut edge: 0x180443497 -> 0x180473ae8 */
/* cut edge: 0x180443497 -> 0x180443593 */
/* target 0x180443593 original byte 0x48 replaced by RET */
/* target 0x1804438c3 original byte 0x84 replaced by RET */
/* target 0x180445357 original byte 0x4c replaced by RET */
/* target 0x180445f45 original byte 0x48 replaced by RET */
/* target 0x180445f7d original byte 0x48 replaced by RET */
/* target 0x180445fce original byte 0x48 replaced by RET */
/* target 0x180446007 original byte 0x48 replaced by RET */
/* target 0x18044642f original byte 0x48 replaced by RET */
/* target 0x180446467 original byte 0x48 replaced by RET */
/* target 0x1804464b8 original byte 0x48 replaced by RET */
/* target 0x1804464f1 original byte 0x48 replaced by RET */
/* target 0x180446621 original byte 0x48 replaced by RET */
/* target 0x180446659 original byte 0x48 replaced by RET */
/* target 0x1804466aa original byte 0x48 replaced by RET */
/* target 0x1804466e4 original byte 0x48 replaced by RET */
/* target 0x180446814 original byte 0x48 replaced by RET */
/* target 0x18044684a original byte 0x48 replaced by RET */
/* target 0x18044689c original byte 0x48 replaced by RET */
/* target 0x1804468d6 original byte 0x48 replaced by RET */
/* target 0x180447c60 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


longlong region_00103_18044266b(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  undefined8 uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  longlong lVar5;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000060;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d8;
  longlong in_stack_000000e0;
  
  uVar2 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar3 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x6234);
  (*pcVar1)(in_stack_00000048,uVar3,0,uVar4);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar3,1,uVar4);
  uVar2 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar2,in_stack_000000a8,uVar3)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar2,in_stack_000000d8);
  lVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)lVar5 == '\0') {
    return (in_stack_00000060 ^ in_stack_000000b0) - in_stack_000000e0;
  }
  return lVar5;
}

