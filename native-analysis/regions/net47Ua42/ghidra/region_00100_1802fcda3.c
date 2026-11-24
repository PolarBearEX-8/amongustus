/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=100 entry=1802fcda3 blocks=57 maxInstructions=50000 */

/* cut edge: 0x1802fce2a -> 0x180300bf0 */
/* cut edge: 0x1802fce8f -> 0x180304af9 */
/* cut edge: 0x1802fcea6 -> 0x180333186 */
/* cut edge: 0x1802fcecf -> 0x180333186 */
/* cut edge: 0x1802fcee5 -> 0x180333186 */
/* cut edge: 0x1802fcf45 -> 0x18030659d */
/* cut edge: 0x1802fcf6e -> 0x1803065d5 */
/* cut edge: 0x1802fcfd2 -> 0x180306630 */
/* cut edge: 0x1802fcfe2 -> 0x18030666a */
/* cut edge: 0x1802fd0fc -> 0x180333186 */
/* cut edge: 0x1802fd248 -> 0x180333186 */
/* cut edge: 0x1802fd25e -> 0x180333186 */
/* cut edge: 0x1802fd2c6 -> 0x1803066a1 */
/* cut edge: 0x1802fd2ef -> 0x1803066d9 */
/* cut edge: 0x1802fd34b -> 0x18030672a */
/* cut edge: 0x1802fd35b -> 0x180306763 */
/* cut edge: 0x1802fd433 -> 0x180333186 */
/* cut edge: 0x1802fd52a -> 0x180333186 */
/* cut edge: 0x1802fd540 -> 0x180333186 */
/* cut edge: 0x1802fd5a8 -> 0x18030679a */
/* cut edge: 0x1802fd5d1 -> 0x1803067d2 */
/* cut edge: 0x1802fd630 -> 0x180306823 */
/* cut edge: 0x1802fd640 -> 0x18030685c */
/* cut edge: 0x1802fd71b -> 0x180333186 */
/* cut edge: 0x1802fd812 -> 0x180333186 */
/* cut edge: 0x1802fd828 -> 0x180333186 */
/* cut edge: 0x1802fd890 -> 0x180306894 */
/* cut edge: 0x1802fd8b4 -> 0x1803068ca */
/* cut edge: 0x1802fd913 -> 0x18030691b */
/* cut edge: 0x1802fd923 -> 0x180306954 */
/* cut edge: 0x1802fd9fb -> 0x180333186 */
/* cut edge: 0x1802fdaf0 -> 0x180333186 */
/* cut edge: 0x1802fdb06 -> 0x180333186 */
/* cut edge: 0x1802fdb6e -> 0x18030698a */
/* cut edge: 0x1802fdb9a -> 0x1803069c2 */
/* cut edge: 0x1802fdbfe -> 0x180306a1c */
/* cut edge: 0x1802fdc0e -> 0x180306a55 */
/* cut edge: 0x1802fdced -> 0x1802ff43e */
/* cut edge: 0x1802fdd52 -> 0x180333186 */
/* cut edge: 0x1802fdd68 -> 0x180333186 */
/* cut edge: 0x1802fdd68 -> 0x1802fdd7e */
/* target 0x1802fdd7e original byte 0x4c replaced by RET */
/* target 0x1802ff43e original byte 0xff replaced by RET */
/* target 0x180300bf0 original byte 0xff replaced by RET */
/* target 0x180304af9 original byte 0x48 replaced by RET */
/* target 0x18030659d original byte 0x48 replaced by RET */
/* target 0x1803065d5 original byte 0x48 replaced by RET */
/* target 0x180306630 original byte 0x48 replaced by RET */
/* target 0x18030666a original byte 0x48 replaced by RET */
/* target 0x1803066a1 original byte 0x48 replaced by RET */
/* target 0x1803066d9 original byte 0x48 replaced by RET */
/* target 0x18030672a original byte 0x48 replaced by RET */
/* target 0x180306763 original byte 0x48 replaced by RET */
/* target 0x18030679a original byte 0x48 replaced by RET */
/* target 0x1803067d2 original byte 0x48 replaced by RET */
/* target 0x180306823 original byte 0x48 replaced by RET */
/* target 0x18030685c original byte 0x48 replaced by RET */
/* target 0x180306894 original byte 0x48 replaced by RET */
/* target 0x1803068ca original byte 0x48 replaced by RET */
/* target 0x18030691b original byte 0x48 replaced by RET */
/* target 0x180306954 original byte 0x48 replaced by RET */
/* target 0x18030698a original byte 0x48 replaced by RET */
/* target 0x1803069c2 original byte 0x48 replaced by RET */
/* target 0x180306a1c original byte 0x48 replaced by RET */
/* target 0x180306a55 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00100_1802fcda3(void)

{
  undefined8 uVar1;
  code *unaff_RSI;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000090;
  undefined8 in_stack_000000a0;
  code *in_stack_000000c8;
  undefined8 in_stack_000000e0;
  code *in_stack_000000e8;
  
  (*unaff_RSI)(in_stack_00000040,in_stack_00000080);
  (*in_stack_000000e8)(in_stack_00000040,in_stack_00000048);
  uVar1 = (*in_stack_000000c8)
                    (in_stack_00000040,in_stack_00000080,in_stack_00000058,in_stack_000000e0,
                     in_stack_00000078);
  DAT_1804b1c80 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar1);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar1);
  (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,DAT_1804b1c80,in_stack_000000a0);
  (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  return;
}

