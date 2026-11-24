/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=31 entry=1802b7d88 blocks=46 maxInstructions=50000 */

/* cut edge: 0x1802b7e05 -> 0x180333186 */
/* cut edge: 0x1802b7efc -> 0x180333186 */
/* cut edge: 0x1802b7f12 -> 0x180333186 */
/* cut edge: 0x1802b7f7d -> 0x1802c3703 */
/* cut edge: 0x1802b7fa6 -> 0x1802c373b */
/* cut edge: 0x1802b8002 -> 0x1802c378c */
/* cut edge: 0x1802b8012 -> 0x1802c37c5 */
/* cut edge: 0x1802b80ea -> 0x180333186 */
/* cut edge: 0x1802b81e1 -> 0x180333186 */
/* cut edge: 0x1802b81f7 -> 0x180333186 */
/* cut edge: 0x1802b8262 -> 0x1802c37fc */
/* cut edge: 0x1802b828b -> 0x1802c3834 */
/* cut edge: 0x1802b82ea -> 0x1802c3885 */
/* cut edge: 0x1802b82fa -> 0x1802c38be */
/* cut edge: 0x1802b83d6 -> 0x180333186 */
/* cut edge: 0x1802b84cb -> 0x180333186 */
/* cut edge: 0x1802b84e1 -> 0x180333186 */
/* cut edge: 0x1802b8549 -> 0x1802c38f6 */
/* cut edge: 0x1802b8572 -> 0x1802c392e */
/* cut edge: 0x1802b85d9 -> 0x1802c3989 */
/* cut edge: 0x1802b85e9 -> 0x1802c39c3 */
/* cut edge: 0x1802b86ca -> 0x180333186 */
/* cut edge: 0x1802b87c6 -> 0x18029b02c */
/* cut edge: 0x1802b8801 -> 0x18029ad34 */
/* cut edge: 0x1802b8806 -> 0x1802b9e7e */
/* cut edge: 0x1802b8814 -> 0x180333186 */
/* cut edge: 0x1802b895f -> 0x180333186 */
/* cut edge: 0x1802b8a58 -> 0x18032313b */
/* cut edge: 0x1802b8a73 -> 0x180333186 */
/* cut edge: 0x1802b8bbe -> 0x180333186 */
/* cut edge: 0x1802b8cb7 -> 0x1802bad42 */
/* cut edge: 0x1802b8cd2 -> 0x180333186 */
/* cut edge: 0x1802b8cda -> 0x180333186 */
/* cut edge: 0x1802b8cf0 -> 0x1802b8e87 */
/* cut edge: 0x1802b8d4c -> 0x1802b8e87 */
/* target 0x18029ad34 original byte 0x48 replaced by RET */
/* target 0x18029b02c original byte 0x83 replaced by RET */
/* target 0x1802b8e87 original byte 0x48 replaced by RET */
/* target 0x1802b9e7e original byte 0x84 replaced by RET */
/* target 0x1802bad42 original byte 0x84 replaced by RET */
/* target 0x1802c3703 original byte 0x48 replaced by RET */
/* target 0x1802c373b original byte 0x48 replaced by RET */
/* target 0x1802c378c original byte 0x48 replaced by RET */
/* target 0x1802c37c5 original byte 0x48 replaced by RET */
/* target 0x1802c37fc original byte 0x48 replaced by RET */
/* target 0x1802c3834 original byte 0x48 replaced by RET */
/* target 0x1802c3885 original byte 0x48 replaced by RET */
/* target 0x1802c38be original byte 0x48 replaced by RET */
/* target 0x1802c38f6 original byte 0x48 replaced by RET */
/* target 0x1802c392e original byte 0x48 replaced by RET */
/* target 0x1802c3989 original byte 0x48 replaced by RET */
/* target 0x1802c39c3 original byte 0x48 replaced by RET */
/* target 0x18032313b original byte 0x84 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00031_1802b7d88(void)

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
  code *unaff_R14;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  code *in_stack_00000068;
  longlong in_stack_00000070;
  code *in_stack_00000078;
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
  
  (*unaff_R14)(in_stack_00000040,in_stack_00000080);
  (*in_stack_00000078)(in_stack_00000040);
  uVar7 = (*in_stack_00000068)
                    (in_stack_00000040,in_stack_00000080,in_stack_00000060,in_stack_00000048,
                     in_stack_00000058);
  DAT_1804b1490 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b1490,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1f13);
  (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar7,in_stack_000000a8,uVar8)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
      lVar5 = DAT_1804b5a08;
      uVar7 = DAT_1804b59f0;
      if (cVar6 == '\0') {
        if (DAT_1804b1498 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000428,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001a58,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
          DAT_1804b1498 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b1498,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x72d5);
        (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0xf4052 ^ in_stack_000000b0 ^ 0xba7e1e12417fb061);
        (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
            lVar5 = DAT_1804b5a08;
            uVar7 = DAT_1804b59f0;
            if (cVar6 == '\0') {
              if (DAT_1804b14a0 == 0) {
                pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x00000426,1);
                lVar4 = DAT_1804b4d38;
                lVar3 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x00001a48,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,in_stack_00000080);
                (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
                uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
                DAT_1804b14a0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b14a0,in_stack_000000a0);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1598);
              (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000070 % 0x3fae5 ^ in_stack_000000b0 ^
                                 0xd3b8f67859f03a2a);
              (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
              cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                  lVar5 = DAT_1804b5a08;
                  uVar7 = DAT_1804b59f0;
                  if (cVar6 == '\0') {
                    if (DAT_1804b14a8 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b5a08 == 0) {
                        return;
                      }
                      uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                        (in_stack_00000040,&stack0x00000424,1);
                      lVar4 = DAT_1804b4d38;
                      lVar3 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                        (in_stack_00000040,&stack0x00001a38,5);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                         (in_stack_00000040,in_stack_00000080);
                      (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
                      uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
                      DAT_1804b14a8 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
                    }
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,DAT_1804b14a8,in_stack_000000a0);
                    uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x3877)
                    ;
                    (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
                    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                       in_stack_00000070 % 0x1f9d1 ^ in_stack_000000b0 ^
                                       0x93ef4b930ca8e719);
                    (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
                    (**(code **)(*in_stack_00000040 + 0x1a0))
                              (in_stack_00000040,uVar7,in_stack_000000d0);
                    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if (cVar6 == '\0') {
                      if (0 < (longlong)in_stack_000000b0) {
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

