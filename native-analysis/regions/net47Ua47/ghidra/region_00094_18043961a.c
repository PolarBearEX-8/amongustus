/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=94 entry=18043961a blocks=40 maxInstructions=50000 */

/* cut edge: 0x18043961a -> 0x180473ae8 */
/* cut edge: 0x180439711 -> 0x180473ae8 */
/* cut edge: 0x180439727 -> 0x180473ae8 */
/* cut edge: 0x1804398e3 -> 0x180473ae8 */
/* cut edge: 0x1804399d8 -> 0x180473ae8 */
/* cut edge: 0x1804399ee -> 0x180473ae8 */
/* cut edge: 0x180439bb2 -> 0x180473ae8 */
/* cut edge: 0x180439ca7 -> 0x180473ae8 */
/* cut edge: 0x180439cbd -> 0x180473ae8 */
/* cut edge: 0x180439e8a -> 0x180473ae8 */
/* cut edge: 0x180439fa0 -> 0x180447f9c */
/* cut edge: 0x180439fb4 -> 0x1803dd67b */
/* cut edge: 0x180439fc6 -> 0x180436b65 */
/* cut edge: 0x180439fd3 -> 0x180473ae8 */
/* cut edge: 0x180439fe9 -> 0x180473ae8 */
/* cut edge: 0x18043a051 -> 0x18043d2b2 */
/* cut edge: 0x18043a07d -> 0x18043d2ea */
/* cut edge: 0x18043a0dc -> 0x18043d33b */
/* cut edge: 0x18043a0ec -> 0x18043d374 */
/* cut edge: 0x18043a1ca -> 0x180473ae8 */
/* cut edge: 0x18043a2c4 -> 0x180473ae8 */
/* cut edge: 0x18043a2da -> 0x180473ae8 */
/* cut edge: 0x18043a342 -> 0x18043d3ab */
/* cut edge: 0x18043a36e -> 0x18043d3e6 */
/* cut edge: 0x18043a3cd -> 0x18043d437 */
/* cut edge: 0x18043a3dd -> 0x18043d470 */
/* cut edge: 0x18043a4bb -> 0x180473ae8 */
/* cut edge: 0x18043a4bb -> 0x18043a5b2 */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x180436b65 original byte 0x48 replaced by RET */
/* target 0x18043a5b2 original byte 0x48 replaced by RET */
/* target 0x18043d2b2 original byte 0x48 replaced by RET */
/* target 0x18043d2ea original byte 0x48 replaced by RET */
/* target 0x18043d33b original byte 0x48 replaced by RET */
/* target 0x18043d374 original byte 0x48 replaced by RET */
/* target 0x18043d3ab original byte 0x48 replaced by RET */
/* target 0x18043d3e6 original byte 0x48 replaced by RET */
/* target 0x18043d437 original byte 0x48 replaced by RET */
/* target 0x18043d470 original byte 0x48 replaced by RET */
/* target 0x180447f9c original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00094_18043961a(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  ulonglong uVar13;
  longlong unaff_R15;
  longlong lVar14;
  longlong *in_stack_00000048;
  longlong in_stack_00000060;
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
  longlong in_stack_00000118;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5696);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        uVar13 = unaff_R15 % 0x4c72a;
        if (DAT_1804b4248 == 0) {
          lVar14 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar14 + 0x390);
          uVar7 = FUN_1803335f0(in_stack_00000048);
          uVar6 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x0000043c,1);
          lVar14 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar14 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00001620,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
          (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
          DAT_1804b4248 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
          unaff_R15 = in_stack_00000060;
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b4248,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1be6);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           uVar13 ^ in_stack_000000c8 ^ 0xbb4b1bc25c8af66b);
        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              lVar14 = unaff_R15;
              if (DAT_1804b4250 == 0) {
                lVar14 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar14 + 0x390);
                uVar7 = FUN_1803335f0(in_stack_00000048);
                uVar6 = DAT_1804b59f0;
                uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x0000043a,1);
                lVar14 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar14 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00001610,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
                (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                DAT_1804b4250 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                lVar14 = in_stack_00000060;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b4250,in_stack_000000a0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1080);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 unaff_R15 % 0x19f77 ^ in_stack_000000c8 ^ 0x98f315b39e3f9bbd);
              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804b4258 == 0) {
                      lVar4 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar4 + 0x390);
                      uVar7 = FUN_1803335f0(in_stack_00000048);
                      uVar6 = DAT_1804b59f0;
                      uVar8 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00000438,1);
                      lVar4 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar4 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar4 + 0x518))(in_stack_00000048,&stack0x00001600,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar12);
                      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000088,uVar7,uVar6,uVar8);
                      DAT_1804b4258 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b4258,in_stack_000000a0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5507)
                    ;
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       lVar14 % 0xa067f ^ in_stack_000000c8 ^ 0xfdc107b101343c8f);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar6,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      if (in_stack_00000118 == 0) {
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

