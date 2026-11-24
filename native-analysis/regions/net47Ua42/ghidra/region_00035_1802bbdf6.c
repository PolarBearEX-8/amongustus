/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=35 entry=1802bbdf6 blocks=48 maxInstructions=50000 */

/* cut edge: 0x1802bbe05 -> 0x18031f92e */
/* cut edge: 0x1802bbe20 -> 0x180333186 */
/* cut edge: 0x1802bbf6b -> 0x180333186 */
/* cut edge: 0x1802bc06d -> 0x1802cf238 */
/* cut edge: 0x1802bc07a -> 0x180333186 */
/* cut edge: 0x1802bc1d3 -> 0x180333186 */
/* cut edge: 0x1802bc2cc -> 0x18031d144 */
/* cut edge: 0x1802bc2e7 -> 0x180333186 */
/* cut edge: 0x1802bc432 -> 0x180333186 */
/* cut edge: 0x1802bc52b -> 0x18031c576 */
/* cut edge: 0x1802bc538 -> 0x180333186 */
/* cut edge: 0x1802bc691 -> 0x180333186 */
/* cut edge: 0x1802bc78a -> 0x1802ddb91 */
/* cut edge: 0x1802bc797 -> 0x180333186 */
/* cut edge: 0x1802bc8f0 -> 0x180333186 */
/* cut edge: 0x1802bc9e9 -> 0x1802bd14d */
/* cut edge: 0x1802bc9f8 -> 0x1802bcefb */
/* cut edge: 0x1802bca05 -> 0x18029fbe9 */
/* cut edge: 0x1802bca14 -> 0x1802b4f54 */
/* cut edge: 0x1802bca4c -> 0x1802b4fad */
/* cut edge: 0x1802bca81 -> 0x1802b4fad */
/* cut edge: 0x1802bca9d -> 0x1802b4fbd */
/* cut edge: 0x1802bcad7 -> 0x1802b5016 */
/* cut edge: 0x1802bcb0e -> 0x1802b5240 */
/* cut edge: 0x1802bcb46 -> 0x1802b5299 */
/* cut edge: 0x1802bcb7b -> 0x1802b5299 */
/* cut edge: 0x1802bcb97 -> 0x1802b52a9 */
/* cut edge: 0x1802bcbd0 -> 0x1802b5301 */
/* cut edge: 0x1802bcc07 -> 0x1802b551c */
/* cut edge: 0x1802bcc3f -> 0x1802b5578 */
/* cut edge: 0x1802bcc74 -> 0x1802b5578 */
/* cut edge: 0x1802bcc90 -> 0x1802b5588 */
/* cut edge: 0x1802bccc9 -> 0x1802b55e0 */
/* cut edge: 0x1802bcd00 -> 0x1802b5801 */
/* cut edge: 0x1802bcd38 -> 0x1802b5860 */
/* cut edge: 0x1802bcd6d -> 0x1802b5860 */
/* target 0x18029fbe9 original byte 0x48 replaced by RET */
/* target 0x1802b4f54 original byte 0x48 replaced by RET */
/* target 0x1802b4fad original byte 0x48 replaced by RET */
/* target 0x1802b4fbd original byte 0x48 replaced by RET */
/* target 0x1802b5016 original byte 0x48 replaced by RET */
/* target 0x1802b5240 original byte 0x48 replaced by RET */
/* target 0x1802b5299 original byte 0x48 replaced by RET */
/* target 0x1802b52a9 original byte 0x48 replaced by RET */
/* target 0x1802b5301 original byte 0x48 replaced by RET */
/* target 0x1802b551c original byte 0x48 replaced by RET */
/* target 0x1802b5578 original byte 0x48 replaced by RET */
/* target 0x1802b5588 original byte 0x48 replaced by RET */
/* target 0x1802b55e0 original byte 0x48 replaced by RET */
/* target 0x1802b5801 original byte 0x48 replaced by RET */
/* target 0x1802b5860 original byte 0x48 replaced by RET */
/* target 0x1802bcefb original byte 0x48 replaced by RET */
/* target 0x1802bd14d original byte 0x48 replaced by RET */
/* target 0x1802cf238 original byte 0x48 replaced by RET */
/* target 0x1802ddb91 original byte 0x48 replaced by RET */
/* target 0x18031c576 original byte 0x48 replaced by RET */
/* target 0x18031d144 original byte 0x84 replaced by RET */
/* target 0x18031f92e original byte 0x84 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00035_1802bbdf6(void)

{
  longlong lVar1;
  code *pcVar2;
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
  int unaff_ESI;
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
  undefined8 in_stack_00002fe8;
  undefined8 in_stack_00002ff0;
  undefined8 in_stack_00002ff8;
  undefined8 in_stack_00003000;
  undefined8 in_stack_00003008;
  
  if (0 < in_stack_000000b0) {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (unaff_ESI == in_stack_000000e0) {
      return;
    }
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804b0b78 == 0) {
      lVar1 = *in_stack_00000040;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_180333550(in_stack_00000040);
      uVar13 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000674,1);
      lVar1 = *in_stack_00000040;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000040);
      uVar10 = FUN_180002550(in_stack_00000040);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002b88,5);
      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000040);
      uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
      (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
      DAT_1804b0b78 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,DAT_1804b0b78,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x447b);
    (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00003008);
    (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
    unaff_ESI = (**(code **)(*in_stack_00000040 + 0x188))
                          (in_stack_00000040,uVar13,in_stack_000000f8);
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if (cVar5 != '\0') {
      return;
    }
  }
  if (unaff_ESI == in_stack_000000e0) {
    return;
  }
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
  if (cVar5 == '\0') {
    if (DAT_1804b0b80 == 0) {
      lVar1 = *in_stack_00000040;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar7 = FUN_180333550(in_stack_00000040);
      uVar13 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000672,1);
      lVar1 = *in_stack_00000040;
      pcVar3 = *(code **)(lVar1 + 0x390);
      uVar9 = FUN_1800024f0(in_stack_00000040);
      uVar10 = FUN_180002550(in_stack_00000040);
      uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002b78,5);
      pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
      uVar12 = FUN_1800025a0(in_stack_00000040);
      uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
      (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
      uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
      DAT_1804b0b80 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
    }
    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,DAT_1804b0b80,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x558a);
    (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
    pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00003000);
    (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
    uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
    iVar6 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar13,in_stack_000000f8);
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      if (iVar6 == in_stack_000000e0) {
        return;
      }
      if (cVar5 == '\0') {
        if (DAT_1804b0b88 == 0) {
          lVar1 = *in_stack_00000040;
          pcVar2 = *(code **)(lVar1 + 0x390);
          uVar7 = FUN_180333550(in_stack_00000040);
          uVar13 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00000670,1);
          lVar1 = *in_stack_00000040;
          pcVar3 = *(code **)(lVar1 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000040);
          uVar10 = FUN_180002550(in_stack_00000040);
          uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002b68,5);
          pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000040);
          uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
          (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
          uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
          DAT_1804b0b88 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
        }
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b0b88,in_stack_000000a0);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x93);
        (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
        pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,in_stack_00002ff8);
        (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
        uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
        iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                          (in_stack_00000040,uVar13,in_stack_000000f8);
        cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar5 == '\0') {
          if (iVar6 == in_stack_000000e0) {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar5 == '\0') {
            if (DAT_1804b0b90 == 0) {
              lVar1 = *in_stack_00000040;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar7 = FUN_180333550(in_stack_00000040);
              uVar13 = DAT_1804b59f0;
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000066e,1);
              lVar1 = *in_stack_00000040;
              pcVar3 = *(code **)(lVar1 + 0x390);
              uVar9 = FUN_1800024f0(in_stack_00000040);
              uVar10 = FUN_180002550(in_stack_00000040);
              uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002b58,5);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
              uVar12 = FUN_1800025a0(in_stack_00000040);
              uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
              (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
              uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
              DAT_1804b0b90 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
            }
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,DAT_1804b0b90,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x40dc);
            (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
            pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               in_stack_00002ff0);
            (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
            uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                               (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
            iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                              (in_stack_00000040,uVar13,in_stack_000000f8);
            cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if (cVar5 == '\0') {
              if (iVar6 == in_stack_000000e0) {
                return;
              }
              cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
              if (cVar5 == '\0') {
                if (DAT_1804b0b98 == 0) {
                  lVar1 = *in_stack_00000040;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar7 = FUN_180333550(in_stack_00000040);
                  uVar13 = DAT_1804b59f0;
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x0000066c,1);
                  lVar1 = *in_stack_00000040;
                  pcVar3 = *(code **)(lVar1 + 0x390);
                  uVar9 = FUN_1800024f0(in_stack_00000040);
                  uVar10 = FUN_180002550(in_stack_00000040);
                  uVar11 = (**(code **)(lVar1 + 0x518))(in_stack_00000040,&stack0x00002b48,5);
                  pcVar4 = *(code **)(*in_stack_00000040 + 0x110);
                  uVar12 = FUN_1800025a0(in_stack_00000040);
                  uVar12 = (*pcVar4)(in_stack_00000040,in_stack_00000080,uVar12);
                  (*pcVar3)(in_stack_00000040,uVar9,uVar10,uVar11,uVar12);
                  uVar13 = (*pcVar2)(in_stack_00000040,in_stack_00000080,uVar7,uVar13,uVar8);
                  DAT_1804b0b98 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar13)
                  ;
                  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar13);
                }
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,DAT_1804b0b98,in_stack_000000a0);
                uVar7 = (**(code **)(*in_stack_00000040 + 0x560))
                                  (in_stack_00000040,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x10c4);
                (*pcVar2)(in_stack_00000040,uVar7,0,uVar8);
                pcVar2 = *(code **)(*in_stack_00000040 + 0x570);
                uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                                  (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                   in_stack_00002fe8);
                (*pcVar2)(in_stack_00000040,uVar7,1,uVar8);
                uVar13 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,uVar13,in_stack_000000a8,uVar7);
                iVar6 = (**(code **)(*in_stack_00000040 + 0x188))
                                  (in_stack_00000040,uVar13,in_stack_000000f8);
                cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                if (cVar5 == '\0') {
                  if (in_stack_000000b0 < 1) {
                    return;
                  }
                  if (iVar6 != in_stack_000000e0) {
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
  return;
}

