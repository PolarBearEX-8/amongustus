/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=141 entry=180468547 blocks=29 maxInstructions=50000 */

/* cut edge: 0x18046869e -> 0x180473ae8 */
/* cut edge: 0x180468790 -> 0x180473ae8 */
/* cut edge: 0x1804687a6 -> 0x180473ae8 */
/* cut edge: 0x180468968 -> 0x180473ae8 */
/* cut edge: 0x180468a5a -> 0x180473ae8 */
/* cut edge: 0x180468a70 -> 0x180473ae8 */
/* cut edge: 0x180468c2f -> 0x180473ae8 */
/* cut edge: 0x180468d24 -> 0x180473ae8 */
/* cut edge: 0x180468d3a -> 0x180473ae8 */
/* cut edge: 0x180468f04 -> 0x180473ae8 */
/* cut edge: 0x180468ff9 -> 0x18046a695 */
/* cut edge: 0x180469014 -> 0x18046d5a0 */
/* cut edge: 0x180469019 -> 0x180473ae8 */
/* cut edge: 0x18046902f -> 0x180473ae8 */
/* cut edge: 0x1804691ee -> 0x180473ae8 */
/* cut edge: 0x1804692e0 -> 0x180473ae8 */
/* cut edge: 0x1804692f6 -> 0x180473ae8 */
/* cut edge: 0x1804694b5 -> 0x180473ae8 */
/* cut edge: 0x1804694b5 -> 0x1804695a7 */
/* target 0x1804695a7 original byte 0x48 replaced by RET */
/* target 0x18046a695 original byte 0x48 replaced by RET */
/* target 0x18046d5a0 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00141_180468547(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong *in_stack_00000048;
  code *pcStack0000000000000058;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  char in_stack_00000118;
  
  lVar1 = *in_stack_00000048;
  pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
  uVar5 = FUN_1803335f0(in_stack_00000048);
  uVar11 = DAT_1804b59f0;
  uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002ea,1);
  lVar1 = *in_stack_00000048;
  pcVar2 = *(code **)(lVar1 + 0x390);
  uVar7 = FUN_1800024f0(in_stack_00000048);
  uVar8 = FUN_180002550(in_stack_00000048);
  uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000e10,5);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
  uVar10 = FUN_1800025a0(in_stack_00000048);
  uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
  (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
  uVar11 = (*pcStack0000000000000058)(in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
  DAT_1804b4788 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b4788,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x3a0e);
  (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar4 == '\0') {
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar4 == '\0') {
        if (DAT_1804b4790 == 0) {
          lVar1 = *in_stack_00000048;
          pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
          uVar5 = FUN_1803335f0(in_stack_00000048);
          uVar11 = DAT_1804b59f0;
          uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002e8,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_1800024f0(in_stack_00000048);
          uVar8 = FUN_180002550(in_stack_00000048);
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000e00,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar10 = FUN_1800025a0(in_stack_00000048);
          uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
          (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
          uVar11 = (*pcStack0000000000000058)
                             (in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
          DAT_1804b4790 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b4790,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5f76);
        (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0xa1183 ^ in_stack_000000c8 ^ 0xdcbe8eb93b38c096);
        (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar4 == '\0') {
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar4 == '\0') {
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar4 == '\0') {
              if (DAT_1804b4798 == 0) {
                lVar1 = *in_stack_00000048;
                pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
                uVar5 = FUN_1803335f0(in_stack_00000048);
                uVar11 = DAT_1804b59f0;
                uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002e6,1);
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar7 = FUN_1800024f0(in_stack_00000048);
                uVar8 = FUN_180002550(in_stack_00000048);
                uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000df0,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar10 = FUN_1800025a0(in_stack_00000048);
                uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
                (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                uVar11 = (*pcStack0000000000000058)
                                   (in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
                DAT_1804b4798 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
              }
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b4798,in_stack_000000a0);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x285);
              (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_00000068 % 0x14cab ^ in_stack_000000c8 ^
                                 0xfa266383dd0ac6b8);
              (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar4 == '\0') {
                  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar4 == '\0') {
                    if (DAT_1804b47a0 == 0) {
                      lVar1 = *in_stack_00000048;
                      pcStack0000000000000058 = *(code **)(lVar1 + 0x390);
                      uVar5 = FUN_1803335f0(in_stack_00000048);
                      uVar11 = DAT_1804b59f0;
                      uVar6 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000002e4,1);
                      lVar1 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar1 + 0x390);
                      uVar7 = FUN_1800024f0(in_stack_00000048);
                      uVar8 = FUN_180002550(in_stack_00000048);
                      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000de0,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar10 = FUN_1800025a0(in_stack_00000048);
                      uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar10);
                      (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                      uVar11 = (*pcStack0000000000000058)
                                         (in_stack_00000048,in_stack_00000088,uVar5,uVar11,uVar6);
                      DAT_1804b47a0 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                    }
                    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,DAT_1804b47a0,in_stack_000000a0);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x42bc)
                    ;
                    (*pcVar2)(in_stack_00000048,uVar5,0,uVar6);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_00000068 % 0x66c85 ^ in_stack_000000c8 ^
                                       0xa57ed06dddba674f);
                    (*pcVar2)(in_stack_00000048,uVar5,1,uVar6);
                    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                       (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar11,in_stack_000000d8);
                    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar4 == '\0') {
                      if (in_stack_00000118 != '\0') {
                        return;
                      }
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
  return;
}

