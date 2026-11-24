/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=129 entry=180319e40 blocks=33 maxInstructions=50000 */

/* cut edge: 0x180319e40 -> 0x180333186 */
/* cut edge: 0x180319f35 -> 0x180333186 */
/* cut edge: 0x180319f4b -> 0x180333186 */
/* cut edge: 0x18031a0ff -> 0x180333186 */
/* cut edge: 0x18031a231 -> 0x18029ad3c */
/* cut edge: 0x18031a23b -> 0x180333186 */
/* cut edge: 0x18031a243 -> 0x180333186 */
/* cut edge: 0x18031a3f2 -> 0x180333186 */
/* cut edge: 0x18031a4e7 -> 0x180333186 */
/* cut edge: 0x18031a4fd -> 0x180333186 */
/* cut edge: 0x18031a6b7 -> 0x180333186 */
/* cut edge: 0x18031a7b9 -> 0x180333186 */
/* cut edge: 0x18031a7cf -> 0x180333186 */
/* cut edge: 0x18031a97e -> 0x180333186 */
/* cut edge: 0x18031aa73 -> 0x180333186 */
/* cut edge: 0x18031aa89 -> 0x180333186 */
/* cut edge: 0x18031ac43 -> 0x180333186 */
/* cut edge: 0x18031ad40 -> 0x18029ad3c */
/* cut edge: 0x18031ad4f -> 0x180319699 */
/* cut edge: 0x18031ad4f -> 0x18031ad84 */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x180319699 original byte 0x4c replaced by RET */
/* target 0x18031ad84 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00129_180319e40(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  ulonglong uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  longlong lVar14;
  longlong *in_stack_00000040;
  ulonglong in_stack_00000060;
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
  undefined8 in_stack_00000180;
  undefined8 in_stack_00000188;
  
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,param_2,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5935);
  (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar6,in_stack_000000a8,uVar7)
  ;
  uVar9 = (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) &&
     (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) {
    if (DAT_1804b1ef8 == 0) {
      lVar14 = *in_stack_00000040;
      pcVar1 = *(code **)(lVar14 + 0x390);
      uVar7 = FUN_1803335f0(in_stack_00000040);
      uVar6 = DAT_1804b59f0;
      in_stack_00000188._4_2_ = 0x7a;
      uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000040,(longlong)&stack0x00000188 + 4,1);
      lVar14 = *in_stack_00000040;
      pcVar2 = *(code **)(lVar14 + 0x390);
      uVar10 = FUN_1800024f0(in_stack_00000040);
      uVar11 = FUN_180002550(in_stack_00000040);
      uVar12 = (**(code **)(lVar14 + 0x518))(in_stack_00000040,&stack0x00000778,5);
      pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
      uVar13 = FUN_1800025a0(in_stack_00000040);
      uVar13 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar13);
      (*pcVar2)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
      uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
      DAT_1804b1ef8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                      (in_stack_00000040,DAT_1804b1ef8,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6693);
    (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                       (longlong)in_stack_00000060 % 0x330b7 ^ in_stack_000000b0 ^
                       0x2d4e7e52b3e93dc6);
    (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                      (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
    lVar14 = (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if (cVar5 == '\0') {
      lVar14 = (in_stack_00000060 ^ uVar9) - lVar14;
      if (in_stack_000000b0 != 0) {
        return;
      }
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      if ((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) {
        if (DAT_1804b1f10 == 0) {
          lVar4 = *in_stack_00000040;
          pcVar1 = *(code **)(lVar4 + 0x390);
          uVar7 = FUN_1803335f0(in_stack_00000040);
          uVar6 = DAT_1804b59f0;
          in_stack_00000180._6_2_ = 0x7a;
          uVar8 = (**(code **)(lVar4 + 0x518))(in_stack_00000040,(longlong)&stack0x00000180 + 6,1);
          lVar4 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar4 + 0x390);
          uVar10 = FUN_1800024f0(in_stack_00000040);
          uVar11 = FUN_180002550(in_stack_00000040);
          uVar12 = (**(code **)(lVar4 + 0x518))(in_stack_00000040,&stack0x00000748,5);
          pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
          uVar13 = FUN_1800025a0(in_stack_00000040);
          uVar13 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar13);
          (*pcVar2)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
          uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
          DAT_1804b1f10 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b1f10,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6786);
        (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           lVar14 % 0x60220 ^ 0x3541032a674fe214);
        (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')
            ) && (cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar5 == '\0')) {
          if (DAT_1804b1f18 == 0) {
            lVar4 = *in_stack_00000040;
            pcVar1 = *(code **)(lVar4 + 0x390);
            uVar7 = FUN_1803335f0(in_stack_00000040);
            uVar6 = DAT_1804b59f0;
            in_stack_00000180._4_2_ = 0x7a;
            uVar8 = (**(code **)(lVar4 + 0x518))(in_stack_00000040,(longlong)&stack0x00000180 + 4,1)
            ;
            lVar4 = *in_stack_00000040;
            pcVar2 = *(code **)(lVar4 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000040);
            uVar11 = FUN_180002550(in_stack_00000040);
            uVar12 = (**(code **)(lVar4 + 0x518))(in_stack_00000040,&stack0x00000738,5);
            pcVar3 = *(code **)(*in_stack_00000040 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000040);
            uVar13 = (*pcVar3)(in_stack_00000040,in_stack_00000080,uVar13);
            (*pcVar2)(in_stack_00000040,uVar10,uVar11,uVar12,uVar13);
            uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,uVar7,uVar6,uVar8);
            DAT_1804b1f18 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
          }
          uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                            (in_stack_00000040,DAT_1804b1f18,in_stack_000000a0);
          uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x648a);
          (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                             lVar14 % 0xc2a95 ^ 0x357886e755f274cc);
          (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
          uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                            (in_stack_00000040,uVar6,in_stack_000000a8,uVar7);
          (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
          if (cVar5 == '\0') {
            return;
          }
        }
      }
    }
  }
  return;
}

