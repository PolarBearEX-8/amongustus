/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=140 entry=180324e34 blocks=42 maxInstructions=50000 */

/* cut edge: 0x180324e34 -> 0x180333186 */
/* cut edge: 0x180324e4a -> 0x180333186 */
/* cut edge: 0x180324eb5 -> 0x18033061e */
/* cut edge: 0x180324ede -> 0x180330656 */
/* cut edge: 0x180324f3d -> 0x1803306a7 */
/* cut edge: 0x180324f4d -> 0x1803306e0 */
/* cut edge: 0x180325028 -> 0x180333186 */
/* cut edge: 0x18032511f -> 0x180333186 */
/* cut edge: 0x180325135 -> 0x180333186 */
/* cut edge: 0x1803251a0 -> 0x180330906 */
/* cut edge: 0x1803251cc -> 0x18033093e */
/* cut edge: 0x180325228 -> 0x18033098f */
/* cut edge: 0x180325238 -> 0x1803309c8 */
/* cut edge: 0x180325313 -> 0x180333186 */
/* cut edge: 0x180325408 -> 0x180333186 */
/* cut edge: 0x18032541e -> 0x180333186 */
/* cut edge: 0x180325489 -> 0x180330bfa */
/* cut edge: 0x1803254b5 -> 0x180330c35 */
/* cut edge: 0x180325519 -> 0x180330c8f */
/* cut edge: 0x180325529 -> 0x180330cc8 */
/* cut edge: 0x180325608 -> 0x180329b78 */
/* cut edge: 0x18032566d -> 0x180329c3e */
/* cut edge: 0x18032568a -> 0x180333186 */
/* cut edge: 0x180325692 -> 0x180333186 */
/* cut edge: 0x18032583b -> 0x180333186 */
/* cut edge: 0x180325932 -> 0x180333186 */
/* cut edge: 0x180325948 -> 0x180333186 */
/* cut edge: 0x180325af6 -> 0x180333186 */
/* cut edge: 0x180325bed -> 0x180333186 */
/* cut edge: 0x180325c03 -> 0x180333186 */
/* cut edge: 0x180325c19 -> 0x180325db7 */
/* cut edge: 0x180325c6b -> 0x180325db7 */
/* target 0x180325db7 original byte 0x48 replaced by RET */
/* target 0x180329b78 original byte 0xff replaced by RET */
/* target 0x180329c3e original byte 0x84 replaced by RET */
/* target 0x18033061e original byte 0x48 replaced by RET */
/* target 0x180330656 original byte 0x48 replaced by RET */
/* target 0x1803306a7 original byte 0x48 replaced by RET */
/* target 0x1803306e0 original byte 0x48 replaced by RET */
/* target 0x180330906 original byte 0x48 replaced by RET */
/* target 0x18033093e original byte 0x48 replaced by RET */
/* target 0x18033098f original byte 0x48 replaced by RET */
/* target 0x1803309c8 original byte 0x48 replaced by RET */
/* target 0x180330bfa original byte 0x48 replaced by RET */
/* target 0x180330c35 original byte 0x48 replaced by RET */
/* target 0x180330c8f original byte 0x48 replaced by RET */
/* target 0x180330cc8 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00140_180324e34(void)

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
  
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    lVar5 = DAT_1804b5a08;
    uVar10 = DAT_1804b59f0;
    if (cVar6 == '\0') {
      if (DAT_1804b1118 == 0) {
        pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000508,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00002138,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
        (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar10,uVar7);
        DAT_1804b1118 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar10);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                         (in_stack_00000040,DAT_1804b1118,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x12c2);
      (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                         in_stack_00000070 % 0x2326d ^ in_stack_000000b0 ^ 0x55e719bef01e010b);
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
            if (DAT_1804b1120 == 0) {
              pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000040 + 0x518))
                                (in_stack_00000040,&stack0x00000506,1);
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
                                (in_stack_00000040,&stack0x00002128,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080)
              ;
              (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar10,uVar7);
              DAT_1804b1120 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar10);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b1120,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7e47);
            (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00000070 % 0x77531 ^ in_stack_000000b0 ^ 0x58569db33f060224)
            ;
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
                  if (DAT_1804b1128 == 0) {
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                    if (DAT_1804b5a08 == 0) {
                      return;
                    }
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x518))
                                      (in_stack_00000040,&stack0x00000504,1);
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
                                      (in_stack_00000040,&stack0x00002118,5);
                    if (DAT_1804b4d40 == 0) {
                      return;
                    }
                    uVar9 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,in_stack_00000080);
                    (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar8,uVar9);
                    uVar10 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar10,uVar7);
                    DAT_1804b1128 =
                         (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar10);
                    (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar10);
                  }
                  (**(code **)(*in_stack_00000040 + 0x110))
                            (in_stack_00000040,DAT_1804b1128,in_stack_000000a0);
                  (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0)
                  ;
                  return;
                }
              }
            }
          }
        }
      }
    }
  }
  return;
}

