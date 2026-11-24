/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=33 entry=1802b9d7a blocks=56 maxInstructions=50000 */

/* cut edge: 0x1802b9d7a -> 0x1802b3063 */
/* cut edge: 0x1802b9db2 -> 0x1802b30ca */
/* cut edge: 0x1802b9ded -> 0x1802b30ca */
/* cut edge: 0x1802b9e0d -> 0x1802b30da */
/* cut edge: 0x1802b9e47 -> 0x1802b3132 */
/* cut edge: 0x1802b9e7e -> 0x180333186 */
/* cut edge: 0x1802b9e86 -> 0x180333186 */
/* cut edge: 0x1802b9ef1 -> 0x1802c439e */
/* cut edge: 0x1802b9f1a -> 0x1802c43d6 */
/* cut edge: 0x1802b9f73 -> 0x1802c4427 */
/* cut edge: 0x1802b9f83 -> 0x1802c4461 */
/* cut edge: 0x1802ba05b -> 0x180333186 */
/* cut edge: 0x1802ba14f -> 0x18031ee2d */
/* cut edge: 0x1802ba16a -> 0x180333186 */
/* cut edge: 0x1802ba172 -> 0x180333186 */
/* cut edge: 0x1802ba1da -> 0x1802c4498 */
/* cut edge: 0x1802ba203 -> 0x1802c44d0 */
/* cut edge: 0x1802ba25c -> 0x1802c4521 */
/* cut edge: 0x1802ba26c -> 0x1802c455a */
/* cut edge: 0x1802ba341 -> 0x180333186 */
/* cut edge: 0x1802ba438 -> 0x180333186 */
/* cut edge: 0x1802ba44e -> 0x180333186 */
/* cut edge: 0x1802ba4b9 -> 0x1802c4591 */
/* cut edge: 0x1802ba4e2 -> 0x1802c45c9 */
/* cut edge: 0x1802ba53e -> 0x1802c461a */
/* cut edge: 0x1802ba54e -> 0x1802c4653 */
/* cut edge: 0x1802ba626 -> 0x180333186 */
/* cut edge: 0x1802ba71d -> 0x180333186 */
/* cut edge: 0x1802ba733 -> 0x180333186 */
/* cut edge: 0x1802ba79b -> 0x1802c468a */
/* cut edge: 0x1802ba7c4 -> 0x1802c46c2 */
/* cut edge: 0x1802ba823 -> 0x1802c4713 */
/* cut edge: 0x1802ba833 -> 0x1802c474c */
/* cut edge: 0x1802ba90f -> 0x180333186 */
/* cut edge: 0x1802baa04 -> 0x180333186 */
/* cut edge: 0x1802baa1a -> 0x180333186 */
/* cut edge: 0x1802baa85 -> 0x1802c4784 */
/* cut edge: 0x1802baaae -> 0x1802c47bc */
/* cut edge: 0x1802bab15 -> 0x1802c4817 */
/* cut edge: 0x1802bab25 -> 0x1802c4851 */
/* cut edge: 0x1802bac06 -> 0x180333186 */
/* cut edge: 0x1802bad02 -> 0x18029b02c */
/* cut edge: 0x1802bad3d -> 0x18029ad34 */
/* cut edge: 0x1802bad42 -> 0x180333186 */
/* cut edge: 0x1802bad4a -> 0x1802bae8d */
/* cut edge: 0x1802bad5a -> 0x1802bae8d */
/* target 0x18029ad34 original byte 0x48 replaced by RET */
/* target 0x18029b02c original byte 0x83 replaced by RET */
/* target 0x1802b3063 original byte 0x48 replaced by RET */
/* target 0x1802b30ca original byte 0x4c replaced by RET */
/* target 0x1802b30da original byte 0x48 replaced by RET */
/* target 0x1802b3132 original byte 0x48 replaced by RET */
/* target 0x1802bae8d original byte 0x48 replaced by RET */
/* target 0x1802c439e original byte 0x48 replaced by RET */
/* target 0x1802c43d6 original byte 0x48 replaced by RET */
/* target 0x1802c4427 original byte 0x48 replaced by RET */
/* target 0x1802c4461 original byte 0x48 replaced by RET */
/* target 0x1802c4498 original byte 0x48 replaced by RET */
/* target 0x1802c44d0 original byte 0x48 replaced by RET */
/* target 0x1802c4521 original byte 0x48 replaced by RET */
/* target 0x1802c455a original byte 0x48 replaced by RET */
/* target 0x1802c4591 original byte 0x48 replaced by RET */
/* target 0x1802c45c9 original byte 0x48 replaced by RET */
/* target 0x1802c461a original byte 0x48 replaced by RET */
/* target 0x1802c4653 original byte 0x48 replaced by RET */
/* target 0x1802c468a original byte 0x48 replaced by RET */
/* target 0x1802c46c2 original byte 0x48 replaced by RET */
/* target 0x1802c4713 original byte 0x48 replaced by RET */
/* target 0x1802c474c original byte 0x48 replaced by RET */
/* target 0x1802c4784 original byte 0x48 replaced by RET */
/* target 0x1802c47bc original byte 0x48 replaced by RET */
/* target 0x1802c4817 original byte 0x48 replaced by RET */
/* target 0x1802c4851 original byte 0x48 replaced by RET */
/* target 0x18031ee2d original byte 0x84 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00033_1802b9d7a(void)

{
  undefined8 uVar1;
  longlong unaff_RDI;
  undefined8 in_stack_00000040;
  
  uVar1 = FUN_1803334a0(in_stack_00000040);
  DAT_1804b5a08 =
       (**(code **)(unaff_RDI + 0x388))
                 (in_stack_00000040,uVar1,&DAT_180493f72,
                  "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                 );
  return;
}

