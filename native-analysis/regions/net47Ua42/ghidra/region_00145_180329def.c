/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=145 entry=180329def blocks=41 maxInstructions=50000 */

/* cut edge: 0x180329def -> 0x180333186 */
/* cut edge: 0x180329ee6 -> 0x180333186 */
/* cut edge: 0x180329efc -> 0x180333186 */
/* cut edge: 0x18032a0ad -> 0x180333186 */
/* cut edge: 0x18032a1a4 -> 0x180333186 */
/* cut edge: 0x18032a1ba -> 0x180333186 */
/* cut edge: 0x18032a36e -> 0x180333186 */
/* cut edge: 0x18032a463 -> 0x180333186 */
/* cut edge: 0x18032a479 -> 0x180333186 */
/* cut edge: 0x18032a633 -> 0x180304a3d */
/* cut edge: 0x18032a698 -> 0x180333186 */
/* cut edge: 0x18032a6a0 -> 0x180333186 */
/* cut edge: 0x18032a708 -> 0x180332692 */
/* cut edge: 0x18032a731 -> 0x1803326ca */
/* cut edge: 0x18032a78d -> 0x18033271b */
/* cut edge: 0x18032a79d -> 0x180332754 */
/* cut edge: 0x18032a875 -> 0x180333186 */
/* cut edge: 0x18032a96c -> 0x180333186 */
/* cut edge: 0x18032a982 -> 0x180333186 */
/* cut edge: 0x18032a9ea -> 0x18033278b */
/* cut edge: 0x18032aa13 -> 0x1803327c3 */
/* cut edge: 0x18032aa72 -> 0x180332814 */
/* cut edge: 0x18032aa82 -> 0x18033284d */
/* cut edge: 0x18032ab5d -> 0x180333186 */
/* cut edge: 0x18032ac54 -> 0x180333186 */
/* cut edge: 0x18032ac6a -> 0x180333186 */
/* cut edge: 0x18032ac80 -> 0x18032ae3d */
/* cut edge: 0x18032acd2 -> 0x180332884 */
/* cut edge: 0x18032acf6 -> 0x1803328ba */
/* cut edge: 0x18032ad55 -> 0x18033290b */
/* cut edge: 0x18032ad65 -> 0x180332944 */
/* cut edge: 0x18032ad65 -> 0x18032adbd */
/* target 0x180304a3d original byte 0xff replaced by RET */
/* target 0x18032adbd original byte 0x48 replaced by RET */
/* target 0x18032ae3d original byte 0x48 replaced by RET */
/* target 0x180332692 original byte 0x48 replaced by RET */
/* target 0x1803326ca original byte 0x48 replaced by RET */
/* target 0x18033271b original byte 0x48 replaced by RET */
/* target 0x180332754 original byte 0x48 replaced by RET */
/* target 0x18033278b original byte 0x48 replaced by RET */
/* target 0x1803327c3 original byte 0x48 replaced by RET */
/* target 0x180332814 original byte 0x48 replaced by RET */
/* target 0x18033284d original byte 0x48 replaced by RET */
/* target 0x180332884 original byte 0x48 replaced by RET */
/* target 0x1803328ba original byte 0x48 replaced by RET */
/* target 0x18033290b original byte 0x48 replaced by RET */
/* target 0x180332944 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00145_180329def(undefined8 param_1,undefined8 param_2)

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
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4094);
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
        if (DAT_1804b0e48 == 0) {
          lVar2 = *in_stack_00000040;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar7 = FUN_1803335f0(in_stack_00000040);
          uVar6 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000005be,1);
          lVar2 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar2 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000040);
          uVar10 = FUN_180002550(in_stack_00000040);
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00002688,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000040);
          uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
          (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
          DAT_1804b0e48 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b0e48,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5387);
        (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0xf232d ^ in_stack_000000b0 ^ 0xf459f941bf9b21b);
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
              if (DAT_1804b0e50 == 0) {
                lVar2 = *in_stack_00000040;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar7 = FUN_1803335f0(in_stack_00000040);
                uVar6 = DAT_1804b59f0;
                uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000005bc,1);
                lVar2 = *in_stack_00000040;
                pcVar3 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000040);
                uVar10 = FUN_180002550(in_stack_00000040);
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00002678,5);
                pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000040);
                uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
                DAT_1804b0e50 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b0e50,in_stack_000000a0);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7b11);
              (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000070 % 0xe3434 ^ in_stack_000000b0 ^
                                 0x3e75f552aab2363e);
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
                    if (DAT_1804b0e58 == 0) {
                      lVar2 = *in_stack_00000040;
                      pcVar1 = *(code **)(lVar2 + 0x390);
                      uVar7 = FUN_1803335f0(in_stack_00000040);
                      uVar6 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x000005ba,1);
                      lVar2 = *in_stack_00000040;
                      pcVar3 = *(code **)(lVar2 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000040);
                      uVar10 = FUN_180002550(in_stack_00000040);
                      uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000040,&stack0x00002668,5);
                      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000040);
                      uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                      (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
                      DAT_1804b0e58 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
                    }
                    (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,DAT_1804b0e58,in_stack_000000a0);
                    (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
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
  return;
}

