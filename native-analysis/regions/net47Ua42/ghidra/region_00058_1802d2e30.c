/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=58 entry=1802d2e30 blocks=55 maxInstructions=50000 */

/* cut edge: 0x1802d2e4a -> 0x1802f744f */
/* cut edge: 0x1802d2e61 -> 0x180333186 */
/* cut edge: 0x1802d2e77 -> 0x180333186 */
/* cut edge: 0x1802d2ee6 -> 0x1802e244d */
/* cut edge: 0x1802d2f12 -> 0x1802e2485 */
/* cut edge: 0x1802d2f6b -> 0x1802e24d3 */
/* cut edge: 0x1802d2f83 -> 0x1802e250c */
/* cut edge: 0x1802d3060 -> 0x180333186 */
/* cut edge: 0x1802d3168 -> 0x180333186 */
/* cut edge: 0x1802d317e -> 0x180333186 */
/* cut edge: 0x1802d31e6 -> 0x1802e6976 */
/* cut edge: 0x1802d320f -> 0x1802e69ae */
/* cut edge: 0x1802d326b -> 0x1802e69ff */
/* cut edge: 0x1802d327b -> 0x1802e6a38 */
/* cut edge: 0x1802d3353 -> 0x180333186 */
/* cut edge: 0x1802d344d -> 0x180333186 */
/* cut edge: 0x1802d3463 -> 0x180333186 */
/* cut edge: 0x1802d34cb -> 0x1802e6b68 */
/* cut edge: 0x1802d34ef -> 0x1802e6b9b */
/* cut edge: 0x1802d3550 -> 0x1802e6bec */
/* cut edge: 0x1802d3560 -> 0x1802e6c25 */
/* cut edge: 0x1802d3639 -> 0x180333186 */
/* cut edge: 0x1802d372e -> 0x180333186 */
/* cut edge: 0x1802d3744 -> 0x180333186 */
/* cut edge: 0x1802d37ac -> 0x1802e6d52 */
/* cut edge: 0x1802d37d5 -> 0x1802e6d85 */
/* cut edge: 0x1802d3839 -> 0x1802e6dd6 */
/* cut edge: 0x1802d3849 -> 0x1802e6e0f */
/* cut edge: 0x1802d392a -> 0x180333186 */
/* cut edge: 0x1802d3a1f -> 0x180333186 */
/* cut edge: 0x1802d3a35 -> 0x180333186 */
/* cut edge: 0x1802d3a9d -> 0x1802e6f3c */
/* cut edge: 0x1802d3ac6 -> 0x1802e6f74 */
/* cut edge: 0x1802d3b25 -> 0x1802e6fc3 */
/* cut edge: 0x1802d3b40 -> 0x1802e6ffe */
/* cut edge: 0x1802d3c24 -> 0x180333186 */
/* cut edge: 0x1802d3d22 -> 0x18031a231 */
/* cut edge: 0x1802d3d50 -> 0x180333186 */
/* cut edge: 0x1802d3d66 -> 0x180333186 */
/* cut edge: 0x1802d3d66 -> 0x1802d3d7c */
/* target 0x1802d3d7c original byte 0x4c replaced by RET */
/* target 0x1802e244d original byte 0x48 replaced by RET */
/* target 0x1802e2485 original byte 0x48 replaced by RET */
/* target 0x1802e24d3 original byte 0x48 replaced by RET */
/* target 0x1802e250c original byte 0x48 replaced by RET */
/* target 0x1802e6976 original byte 0x48 replaced by RET */
/* target 0x1802e69ae original byte 0x48 replaced by RET */
/* target 0x1802e69ff original byte 0x48 replaced by RET */
/* target 0x1802e6a38 original byte 0x48 replaced by RET */
/* target 0x1802e6b68 original byte 0x48 replaced by RET */
/* target 0x1802e6b9b original byte 0x48 replaced by RET */
/* target 0x1802e6bec original byte 0x48 replaced by RET */
/* target 0x1802e6c25 original byte 0x48 replaced by RET */
/* target 0x1802e6d52 original byte 0x48 replaced by RET */
/* target 0x1802e6d85 original byte 0x48 replaced by RET */
/* target 0x1802e6dd6 original byte 0x48 replaced by RET */
/* target 0x1802e6e0f original byte 0x48 replaced by RET */
/* target 0x1802e6f3c original byte 0x48 replaced by RET */
/* target 0x1802e6f74 original byte 0x48 replaced by RET */
/* target 0x1802e6fc3 original byte 0x48 replaced by RET */
/* target 0x1802e6ffe original byte 0x48 replaced by RET */
/* target 0x1802f744f original byte 0x48 replaced by RET */
/* target 0x18031a231 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00058_1802d2e30(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  int iVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong unaff_RSI;
  longlong lVar12;
  ulonglong unaff_R12;
  longlong *in_stack_00000040;
  ulonglong in_stack_00000070;
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
  undefined8 in_stack_000000f8;
  longlong in_stack_00000110;
  int in_stack_00000118;
  
  lVar12 = unaff_RSI + (unaff_R12 ^ in_stack_00000070);
  if (in_stack_00000110 == 0) {
    return;
  }
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
  if ((cVar6 == '\0') &&
     (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), lVar5 = DAT_1804b59f8,
     uVar11 = DAT_1804b59f0, cVar6 == '\0')) {
    if (DAT_1804b1880 == 0) {
      pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
      if (DAT_1804b59f8 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x0000032c,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001368,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
      (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
      uVar11 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar11,uVar8);
      DAT_1804b1880 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar11);
      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar11);
    }
    uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,DAT_1804b1880,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x3979);
    (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                       lVar12 % 0x29031 ^ in_stack_000000b0 ^ 0x70c2ba5829c629fe);
    (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
    uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                       (in_stack_00000040,uVar11,in_stack_000000a8,uVar8);
    iVar7 = (**(code **)(*in_stack_00000040 + 0x188))(in_stack_00000040,uVar11,in_stack_000000f8);
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if (cVar6 == '\0') {
      if (iVar7 <= in_stack_00000118) {
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar6 != '\0') {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        lVar5 = DAT_1804b5a08;
        uVar11 = DAT_1804b59f0;
        if (cVar6 != '\0') {
          return;
        }
        if (DAT_1804b1888 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x0000032a,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001358,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar11,uVar8);
          DAT_1804b1888 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar11);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b1888,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x57c0);
        (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           lVar12 % 0x9d934 ^ in_stack_000000b0 ^ 0x383d1bfca9e31c64);
        (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar11,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar11,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar6 != '\0') {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar6 != '\0') {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        lVar5 = DAT_1804b5a08;
        uVar11 = DAT_1804b59f0;
        if (cVar6 != '\0') {
          return;
        }
        if (DAT_1804b1890 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000328,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001348,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar11,uVar8);
          DAT_1804b1890 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar11);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b1890,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4604);
        (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           lVar12 % 0x2a9a6 ^ in_stack_000000b0 ^ 0xb6302c285ed7b8d);
        (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar11,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar11,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar6 != '\0') {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar6 != '\0') {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        lVar5 = DAT_1804b5a08;
        uVar11 = DAT_1804b59f0;
        if (cVar6 != '\0') {
          return;
        }
        if (DAT_1804b1898 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000326,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001338,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar11,uVar8);
          DAT_1804b1898 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar11);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b1898,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x376a);
        (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           lVar12 % 0xd1ce ^ in_stack_000000b0 ^ 0x1783af88208ffe70);
        (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar11,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar11,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar6 != '\0') {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        if (cVar6 != '\0') {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
        lVar5 = DAT_1804b5a08;
        uVar11 = DAT_1804b59f0;
        if (cVar6 != '\0') {
          return;
        }
        if (DAT_1804b18a0 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000324,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001328,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar11,uVar8);
          DAT_1804b18a0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar11);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,DAT_1804b18a0,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5b5);
        (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           lVar12 % 0xb5638 ^ in_stack_000000b0 ^ 0x204d6ef110cb9d94);
        (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                           (in_stack_00000040,uVar11,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar11,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar6 != '\0') {
          return;
        }
        if (in_stack_00000110 == 0) {
          return;
        }
      }
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
      if ((cVar6 == '\0') && (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar6 == '\0')) {
        return;
      }
    }
  }
  return;
}

