/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=121 entry=180311e1e blocks=35 maxInstructions=50000 */

/* cut edge: 0x180311e1e -> 0x180333186 */
/* cut edge: 0x180311f15 -> 0x180333186 */
/* cut edge: 0x180311f2b -> 0x180333186 */
/* cut edge: 0x1803120df -> 0x180333186 */
/* cut edge: 0x1803121d4 -> 0x180333186 */
/* cut edge: 0x1803121ea -> 0x180333186 */
/* cut edge: 0x1803123a3 -> 0x180301a90 */
/* cut edge: 0x180312408 -> 0x180333186 */
/* cut edge: 0x180312410 -> 0x180333186 */
/* cut edge: 0x1803125b9 -> 0x180333186 */
/* cut edge: 0x1803126ae -> 0x180333186 */
/* cut edge: 0x1803126c4 -> 0x180333186 */
/* cut edge: 0x18031287f -> 0x180333186 */
/* cut edge: 0x180312974 -> 0x180327a1c */
/* cut edge: 0x180312993 -> 0x180327a1c */
/* cut edge: 0x1803129a1 -> 0x18032b25e */
/* cut edge: 0x1803129b0 -> 0x18030d0df */
/* cut edge: 0x1803129bf -> 0x180328620 */
/* cut edge: 0x1803129dc -> 0x180333186 */
/* cut edge: 0x1803129e4 -> 0x180333186 */
/* cut edge: 0x180312b90 -> 0x180333186 */
/* cut edge: 0x180312c87 -> 0x180333186 */
/* cut edge: 0x180312c9d -> 0x180333186 */
/* cut edge: 0x180312cb3 -> 0x180312e4e */
/* cut edge: 0x180312d08 -> 0x180312e4e */
/* target 0x180301a90 original byte 0xff replaced by RET */
/* target 0x18030d0df original byte 0x48 replaced by RET */
/* target 0x180312e4e original byte 0x48 replaced by RET */
/* target 0x180327a1c original byte 0x48 replaced by RET */
/* target 0x180328620 original byte 0x84 replaced by RET */
/* target 0x18032b25e original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00121_180311e1e(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
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
  
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,param_2,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x2aa3);
  (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar6,in_stack_000000a8,uVar7)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804b0f20 == 0) {
          lVar2 = *in_stack_00000040;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar7 = FUN_1803335f0(in_stack_00000040);
          uVar6 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00000588,1);
          lVar2 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000040);
          uVar10 = FUN_180002550(in_stack_00000040);
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000024d8,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000040);
          uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
          (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
          DAT_1804b0f20 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b0f20,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x36d1);
        (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0x74f56 ^ in_stack_000000b0 ^ 0x60bd1c8ee261ba52);
        (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b0f28 == 0) {
                lVar2 = *in_stack_00000040;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar7 = FUN_1803335f0(in_stack_00000040);
                uVar6 = DAT_1804b59f0;
                uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00000586,1);
                lVar2 = *in_stack_00000040;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000040);
                uVar10 = FUN_180002550(in_stack_00000040);
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000024c8,5);
                pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000040);
                uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
                DAT_1804b0f28 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
              }
              (**(code **)(*in_stack_00000040 + 0x110))
                        (in_stack_00000040,DAT_1804b0f28,in_stack_000000a0);
              (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
              return;
            }
          }
        }
      }
    }
  }
  return;
}

