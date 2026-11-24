/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=112 entry=180308dfe blocks=38 maxInstructions=50000 */

/* cut edge: 0x180308dfe -> 0x180333186 */
/* cut edge: 0x180308e14 -> 0x180333186 */
/* cut edge: 0x180308e7f -> 0x18032cdcd */
/* cut edge: 0x180308ea3 -> 0x18032ce03 */
/* cut edge: 0x180308f02 -> 0x18032ce54 */
/* cut edge: 0x180308f12 -> 0x18032ce8d */
/* cut edge: 0x180308fea -> 0x180333186 */
/* cut edge: 0x1803090df -> 0x180333186 */
/* cut edge: 0x1803090f5 -> 0x180333186 */
/* cut edge: 0x18030915d -> 0x18032cfb9 */
/* cut edge: 0x180309189 -> 0x18032cff1 */
/* cut edge: 0x1803091e5 -> 0x18032d043 */
/* cut edge: 0x1803091f5 -> 0x18032d07d */
/* cut edge: 0x1803092cf -> 0x180333186 */
/* cut edge: 0x1803093cb -> 0x180300c9c */
/* cut edge: 0x1803093e7 -> 0x180333186 */
/* cut edge: 0x180309421 -> 0x180333186 */
/* cut edge: 0x180309441 -> 0x180333186 */
/* cut edge: 0x180309604 -> 0x180333186 */
/* cut edge: 0x1803096ea -> 0x180333186 */
/* cut edge: 0x180309700 -> 0x180333186 */
/* cut edge: 0x1803098c0 -> 0x180333186 */
/* cut edge: 0x1803099a4 -> 0x180333186 */
/* cut edge: 0x1803099ea -> 0x180333186 */
/* cut edge: 0x180309a28 -> 0x180333186 */
/* cut edge: 0x180309a3e -> 0x180333186 */
/* cut edge: 0x180309c10 -> 0x180333186 */
/* cut edge: 0x180309d41 -> 0x180333186 */
/* cut edge: 0x180309d41 -> 0x180309d87 */
/* target 0x180300c9c original byte 0x4c replaced by RET */
/* target 0x180309d87 original byte 0x48 replaced by RET */
/* target 0x18032cdcd original byte 0x48 replaced by RET */
/* target 0x18032ce03 original byte 0x48 replaced by RET */
/* target 0x18032ce54 original byte 0x48 replaced by RET */
/* target 0x18032ce8d original byte 0x48 replaced by RET */
/* target 0x18032cfb9 original byte 0x48 replaced by RET */
/* target 0x18032cff1 original byte 0x48 replaced by RET */
/* target 0x18032d043 original byte 0x48 replaced by RET */
/* target 0x18032d07d original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00112_180308dfe(void)

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
      if (DAT_1804b10d8 == 0) {
        pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000518,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000021b8,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
        (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar10,uVar7);
        DAT_1804b10d8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar10);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b10d8,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x46fd);
      (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         in_stack_00000060 % 0xb4eb1 ^ in_stack_000000b0 ^ 0x38340f5f45ff5297);
      (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,uVar10,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar10,in_stack_000000d0);
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
          lVar5 = DAT_1804b5a08;
          uVar10 = DAT_1804b59f0;
          if (cVar6 == '\0') {
            if (DAT_1804b10e0 == 0) {
              pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000040 + 0x518))
                                (in_stack_00000040,&stack0x00000516,1);
              lVar4 = DAT_1804b4d38;
              lVar3 = DAT_1804b4d30;
              pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                (in_stack_00000040,&stack0x000021a8,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080)
              ;
              (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar10,uVar7);
              DAT_1804b10e0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar10);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b10e0,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4104);
            (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00000060 % 0x21b25 ^ in_stack_000000b0 ^ 0x5b87a040634ee70e)
            ;
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
      }
    }
  }
  return;
}

