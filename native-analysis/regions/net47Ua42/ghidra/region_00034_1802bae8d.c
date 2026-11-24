/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=34 entry=1802bae8d blocks=34 maxInstructions=50000 */

/* cut edge: 0x1802bae8d -> 0x180333186 */
/* cut edge: 0x1802baf86 -> 0x180316ec3 */
/* cut edge: 0x1802baf93 -> 0x180310273 */
/* cut edge: 0x1802bafa2 -> 0x180333186 */
/* cut edge: 0x1802bb0fb -> 0x180333186 */
/* cut edge: 0x1802bb1f4 -> 0x180310259 */
/* cut edge: 0x1802bb201 -> 0x180333186 */
/* cut edge: 0x1802bb35a -> 0x180333186 */
/* cut edge: 0x1802bb46b -> 0x1802eb770 */
/* cut edge: 0x1802bb487 -> 0x180333186 */
/* cut edge: 0x1802bb5e0 -> 0x180333186 */
/* cut edge: 0x1802bb6d9 -> 0x1802efd68 */
/* cut edge: 0x1802bb6e6 -> 0x180333186 */
/* cut edge: 0x1802bb83f -> 0x180333186 */
/* cut edge: 0x1802bb938 -> 0x180310e45 */
/* cut edge: 0x1802bb945 -> 0x180333186 */
/* cut edge: 0x1802bba9e -> 0x180333186 */
/* cut edge: 0x1802bbb97 -> 0x1803279fd */
/* cut edge: 0x1802bbba4 -> 0x180333186 */
/* cut edge: 0x1802bbcfd -> 0x180333186 */
/* cut edge: 0x1802bbcfd -> 0x1802bbdf6 */
/* target 0x1802bbdf6 original byte 0x48 replaced by RET */
/* target 0x1802eb770 original byte 0x48 replaced by RET */
/* target 0x1802efd68 original byte 0x48 replaced by RET */
/* target 0x180310259 original byte 0x48 replaced by RET */
/* target 0x180310273 original byte 0x48 replaced by RET */
/* target 0x180310e45 original byte 0x48 replaced by RET */
/* target 0x180316ec3 original byte 0x4c replaced by RET */
/* target 0x1803279fd original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00034_1802bae8d(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  char cVar5;
  int iVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  longlong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  int in_stack_000000e0;
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00003010;
  undefined8 in_stack_00003018;
  undefined8 in_stack_00003020;
  undefined8 in_stack_00003028;
  undefined8 in_stack_00003030;
  undefined8 in_stack_00003038;
  undefined8 in_stack_00003040;
  
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,param_2,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x303a);
  (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00003040);
  (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar7,in_stack_000000a8,uVar8)
  ;
  iVar6 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar7,in_stack_000000f8);
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar5 == '\0') {
    if (iVar6 == in_stack_000000e0) {
      return;
    }
    if (in_stack_000000b0 == 0) {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b0b48 == 0) {
        lVar2 = *in_stack_00000040;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar8 = FUN_180333550(in_stack_00000040);
        uVar7 = DAT_1804b59f0;
        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00000680,1);
        lVar2 = *in_stack_00000040;
        pcVar3 = *(code **)(lVar2 + 0x390);
        uVar10 = FUN_1800024f0(in_stack_00000040);
        uVar11 = FUN_180002550(in_stack_00000040);
        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00002be8,5);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
        uVar13 = FUN_1800025a0(in_stack_00000040);
        uVar13 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar13);
        (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
        uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar8,uVar7,uVar9);
        DAT_1804b0b48 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
      }
      uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                        (in_stack_00000040,DAT_1804b0b48,in_stack_000000a0);
      uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1af9);
      (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00003038);
      (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
      uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                        (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
      iVar6 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar7,in_stack_000000f8);
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
      if (cVar5 == '\0') {
        if (iVar6 == in_stack_000000e0) {
          return;
        }
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar5 == '\0') {
          if (DAT_1804b0b50 == 0) {
            lVar2 = *in_stack_00000040;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar8 = FUN_180333550(in_stack_00000040);
            uVar7 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x0000067e,1);
            lVar2 = *in_stack_00000040;
            pcVar3 = *(code **)(lVar2 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000040);
            uVar11 = FUN_180002550(in_stack_00000040);
            uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00002bd8,5);
            pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000040);
            uVar13 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar13);
            (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
            uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar8,uVar7,uVar9);
            DAT_1804b0b50 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                            (in_stack_00000040,DAT_1804b0b50,in_stack_000000a0);
          uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x353c);
          (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00003030
                            );
          (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
          uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                            (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
          iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                            (in_stack_00000040,uVar7,in_stack_000000f8);
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
          if (cVar5 == '\0') {
            if (0 < in_stack_000000b0) {
              if (iVar6 == in_stack_000000e0) {
                return;
              }
              cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
              if (cVar5 != '\0') {
                return;
              }
              if (DAT_1804b0b58 == 0) {
                lVar2 = *in_stack_00000040;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_180333550(in_stack_00000040);
                uVar7 = DAT_1804b59f0;
                uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x0000067c,1);
                lVar2 = *in_stack_00000040;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000040);
                uVar11 = FUN_180002550(in_stack_00000040);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00002bc8,5);
                pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000040);
                uVar13 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar13);
                (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
                uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar8,uVar7,uVar9);
                DAT_1804b0b58 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b0b58,in_stack_000000a0);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5bb5);
              (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00003028);
              (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
              iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                                (in_stack_00000040,uVar7,in_stack_000000f8);
              cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar5 != '\0') {
                return;
              }
            }
            if (iVar6 == in_stack_000000e0) {
              return;
            }
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804b0b60 == 0) {
                lVar2 = *in_stack_00000040;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar8 = FUN_180333550(in_stack_00000040);
                uVar7 = DAT_1804b59f0;
                uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x0000067a,1);
                lVar2 = *in_stack_00000040;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar10 = FUN_1800024f0(in_stack_00000040);
                uVar11 = FUN_180002550(in_stack_00000040);
                uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00002bb8,5);
                pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                uVar13 = FUN_1800025a0(in_stack_00000040);
                uVar13 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar13);
                (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
                uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar8,uVar7,uVar9);
                DAT_1804b0b60 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b0b60,in_stack_000000a0);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x690e);
              (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00003020);
              (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
              iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                                (in_stack_00000040,uVar7,in_stack_000000f8);
              cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar5 == '\0') {
                if (iVar6 == in_stack_000000e0) {
                  return;
                }
                cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                if (cVar5 == '\0') {
                  if (DAT_1804b0b68 == 0) {
                    lVar2 = *in_stack_00000040;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar8 = FUN_180333550(in_stack_00000040);
                    uVar7 = DAT_1804b59f0;
                    uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00000678,1);
                    lVar2 = *in_stack_00000040;
                    pcVar3 = *(code **)(lVar2 + 0x390);
                    uVar10 = FUN_1800024f0(in_stack_00000040);
                    uVar11 = FUN_180002550(in_stack_00000040);
                    uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00002ba8,5);
                    pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                    uVar13 = FUN_1800025a0(in_stack_00000040);
                    uVar13 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar13);
                    (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
                    uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar8,uVar7,uVar9);
                    DAT_1804b0b68 =
                         (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                    (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
                  }
                  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                    (in_stack_00000040,DAT_1804b0b68,in_stack_000000a0);
                  uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                    (in_stack_00000040,2,in_stack_00000090,0);
                  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6696);
                  (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
                  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                    (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                     in_stack_00003018);
                  (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
                  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                    (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
                  iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                                    (in_stack_00000040,uVar7,in_stack_000000f8);
                  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                  if (cVar5 == '\0') {
                    if (iVar6 == in_stack_000000e0) {
                      return;
                    }
                    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
                    if (cVar5 == '\0') {
                      if (DAT_1804b0b70 == 0) {
                        lVar2 = *in_stack_00000040;
                        pcVar1 = *(code **)(lVar2 + 0x390);
                        uVar8 = FUN_180333550(in_stack_00000040);
                        uVar7 = DAT_1804b59f0;
                        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00000676,1);
                        lVar2 = *in_stack_00000040;
                        pcVar3 = *(code **)(lVar2 + 0x390);
                        uVar10 = FUN_1800024f0(in_stack_00000040);
                        uVar11 = FUN_180002550(in_stack_00000040);
                        uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00002b98,5);
                        pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                        uVar13 = FUN_1800025a0(in_stack_00000040);
                        uVar13 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar13);
                        (*pcVar3)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
                        uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar8,uVar7,uVar9);
                        DAT_1804b0b70 =
                             (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                        (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
                      }
                      uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                        (in_stack_00000040,DAT_1804b0b70,in_stack_000000a0);
                      uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                        (in_stack_00000040,2,in_stack_00000090,0);
                      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                        (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0xeb6
                                        );
                      (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
                      pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                      uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                        (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                         in_stack_00003010);
                      (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
                      uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                        (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
                      (**(code **)(*in_stack_00000040 + 0x188))
                                (in_stack_00000040,uVar7,in_stack_000000f8);
                      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                      if (cVar5 == '\0') {
                        return;
                      }
                    }
                  }
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

