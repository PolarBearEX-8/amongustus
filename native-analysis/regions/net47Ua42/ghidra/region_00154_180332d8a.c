/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=154 entry=180332d8a blocks=40 maxInstructions=50000 */

/* cut edge: 0x180332d8a -> 0x18029a1a4 */
/* cut edge: 0x180332dc5 -> 0x18029a200 */
/* cut edge: 0x180332dfa -> 0x18029a200 */
/* cut edge: 0x180332e16 -> 0x18029a215 */
/* cut edge: 0x180332e4f -> 0x18029a289 */
/* cut edge: 0x180332e86 -> 0x18029a509 */
/* cut edge: 0x180332ebc -> 0x18029a556 */
/* cut edge: 0x180332ef2 -> 0x18029a556 */
/* cut edge: 0x180332f0e -> 0x18029a578 */
/* cut edge: 0x180332f47 -> 0x18029a5d0 */
/* cut edge: 0x180332f7e -> 0x18029a793 */
/* cut edge: 0x180332fb4 -> 0x18029a7ef */
/* cut edge: 0x180332fe9 -> 0x18029a7ef */
/* cut edge: 0x180333005 -> 0x18029a7ff */
/* cut edge: 0x18033303f -> 0x18029a868 */
/* cut edge: 0x180333075 -> 0x18029aa1b */
/* cut edge: 0x1803330b0 -> 0x18029aa68 */
/* cut edge: 0x1803330e6 -> 0x18029aa68 */
/* cut edge: 0x1803330ff -> 0x18029aa8f */
/* cut edge: 0x180333139 -> 0x18029ab02 */
/* cut edge: 0x180333170 -> 0x180299be2 */
/* cut edge: 0x1803331cf -> 0x180298dc4 */
/* cut edge: 0x1803331dc -> 0x180298dd4 */
/* cut edge: 0x180333215 -> 0x180299111 */
/* cut edge: 0x180333285 -> 0x18029914d */
/* cut edge: 0x1803332c7 -> 0x180299193 */
/* cut edge: 0x1803332d4 -> 0x1802991ab */
/* cut edge: 0x180333312 -> 0x18029923e */
/* cut edge: 0x18033334d -> 0x180298dc4 */
/* cut edge: 0x18033336b -> 0x18029914d */
/* cut edge: 0x180333389 -> 0x180299193 */
/* cut edge: 0x1803333a7 -> 0x180298f88 */
/* cut edge: 0x1803333dd -> 0x180298fea */
/* cut edge: 0x180333412 -> 0x180298fea */
/* cut edge: 0x18033342e -> 0x180298ffa */
/* cut edge: 0x180333467 -> 0x180299063 */
/* target 0x180298dc4 original byte 0x4c replaced by RET */
/* target 0x180298dd4 original byte 0x48 replaced by RET */
/* target 0x180298f88 original byte 0x48 replaced by RET */
/* target 0x180298fea original byte 0x48 replaced by RET */
/* target 0x180298ffa original byte 0x48 replaced by RET */
/* target 0x180299063 original byte 0x48 replaced by RET */
/* target 0x180299111 original byte 0x48 replaced by RET */
/* target 0x18029914d original byte 0x48 replaced by RET */
/* target 0x180299193 original byte 0x48 replaced by RET */
/* target 0x1802991ab original byte 0x49 replaced by RET */
/* target 0x18029923e original byte 0x48 replaced by RET */
/* target 0x180299be2 original byte 0x48 replaced by RET */
/* target 0x18029a1a4 original byte 0x48 replaced by RET */
/* target 0x18029a200 original byte 0x48 replaced by RET */
/* target 0x18029a215 original byte 0x48 replaced by RET */
/* target 0x18029a289 original byte 0x48 replaced by RET */
/* target 0x18029a509 original byte 0x4c replaced by RET */
/* target 0x18029a556 original byte 0x48 replaced by RET */
/* target 0x18029a578 original byte 0x48 replaced by RET */
/* target 0x18029a5d0 original byte 0x48 replaced by RET */
/* target 0x18029a793 original byte 0x48 replaced by RET */
/* target 0x18029a7ef original byte 0x4c replaced by RET */
/* target 0x18029a7ff original byte 0x48 replaced by RET */
/* target 0x18029a868 original byte 0x48 replaced by RET */
/* target 0x18029aa1b original byte 0x4c replaced by RET */
/* target 0x18029aa68 original byte 0x48 replaced by RET */
/* target 0x18029aa8f original byte 0x48 replaced by RET */
/* target 0x18029ab02 original byte 0x48 replaced by RET */


void region_00154_180332d8a(void)

{
  undefined8 uVar1;
  longlong unaff_RSI;
  undefined8 in_stack_00000040;
  
  uVar1 = FUN_1803334a0(in_stack_00000040);
  DAT_1804b59e8 =
       (**(code **)(unaff_RSI + 0x388))
                 (in_stack_00000040,uVar1,&DAT_180493ff6,
                  "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                 );
  return;
}

