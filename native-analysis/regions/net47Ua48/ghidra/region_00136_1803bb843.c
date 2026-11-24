/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=136 entry=1803bb843 blocks=46 maxInstructions=50000 */

/* cut edge: 0x1803bb843 -> 0x1803da65f */
/* cut edge: 0x1803bb853 -> 0x1803da699 */
/* cut edge: 0x1803bb92d -> 0x1803351d6 */
/* cut edge: 0x1803bba0f -> 0x1803351d6 */
/* cut edge: 0x1803bba35 -> 0x1803da6d0 */
/* cut edge: 0x1803bba5e -> 0x1803da708 */
/* cut edge: 0x1803bbabd -> 0x1803da759 */
/* cut edge: 0x1803bbacd -> 0x1803da792 */
/* cut edge: 0x1803bbbe0 -> 0x1803351d6 */
/* cut edge: 0x1803bbcef -> 0x1803351d6 */
/* cut edge: 0x1803bbd15 -> 0x1803da7c9 */
/* cut edge: 0x1803bbd39 -> 0x1803da7ff */
/* cut edge: 0x1803bbd95 -> 0x1803da850 */
/* cut edge: 0x1803bbda5 -> 0x1803da88a */
/* cut edge: 0x1803bbe7f -> 0x1803351d6 */
/* cut edge: 0x1803bbf61 -> 0x1803351d6 */
/* cut edge: 0x1803bbf87 -> 0x1803da8c1 */
/* cut edge: 0x1803bbfb3 -> 0x1803da8fc */
/* cut edge: 0x1803bc00f -> 0x1803da94d */
/* cut edge: 0x1803bc01f -> 0x1803da986 */
/* cut edge: 0x1803bc132 -> 0x1803351d6 */
/* cut edge: 0x1803bc268 -> 0x1803351d6 */
/* cut edge: 0x1803bc27e -> 0x1803bf570 */
/* cut edge: 0x1803bc28d -> 0x1803bf578 */
/* cut edge: 0x1803bc295 -> 0x1803351d6 */
/* cut edge: 0x1803bc3fb -> 0x1803351d6 */
/* cut edge: 0x1803bc4dd -> 0x1803351d6 */
/* cut edge: 0x1803bc68d -> 0x1803351d6 */
/* cut edge: 0x1803bc7bc -> 0x1803351d6 */
/* cut edge: 0x1803bc7d2 -> 0x1803bc95c */
/* cut edge: 0x1803bc7f4 -> 0x1803bc95c */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803bc95c original byte 0x48 replaced by RET */
/* target 0x1803bf570 original byte 0x85 replaced by RET */
/* target 0x1803bf578 original byte 0x48 replaced by RET */
/* target 0x1803da65f original byte 0x48 replaced by RET */
/* target 0x1803da699 original byte 0x48 replaced by RET */
/* target 0x1803da6d0 original byte 0x48 replaced by RET */
/* target 0x1803da708 original byte 0x48 replaced by RET */
/* target 0x1803da759 original byte 0x48 replaced by RET */
/* target 0x1803da792 original byte 0x48 replaced by RET */
/* target 0x1803da7c9 original byte 0x48 replaced by RET */
/* target 0x1803da7ff original byte 0x48 replaced by RET */
/* target 0x1803da850 original byte 0x48 replaced by RET */
/* target 0x1803da88a original byte 0x48 replaced by RET */
/* target 0x1803da8c1 original byte 0x48 replaced by RET */
/* target 0x1803da8fc original byte 0x48 replaced by RET */
/* target 0x1803da94d original byte 0x48 replaced by RET */
/* target 0x1803da986 original byte 0x48 replaced by RET */


void region_00136_1803bb843(void)

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
  undefined8 uVar11;
  longlong unaff_RBX;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  code *in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  code *in_stack_000000a8;
  undefined8 in_stack_000000c8;
  
  if (DAT_1804b4d38 == 0) {
    return;
  }
  (**(code **)(unaff_RBX + 0x518))(in_stack_00000048,&stack0x00002790,0xd);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
  (*in_stack_000000a8)(in_stack_00000048);
  uVar7 = (*in_stack_00000068)(in_stack_00000048,in_stack_00000058);
  DAT_1804b3030 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804b3030,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                    (in_stack_00000048,2,in_stack_00000088,0,in_stack_00000070);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc5815bc278527a);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_00000080,uVar8)
  ;
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    uVar8 = DAT_1804b59f0;
    lVar5 = DAT_1804b59e8;
    if (cVar6 == '\0') {
      if (DAT_1804b3038 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b59e8 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000003d2,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar10 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00004cb0,0x29);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar10,uVar11);
        uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar8,uVar9);
        DAT_1804b3038 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b3038,in_stack_00000078);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000088,0);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,uVar7);
      (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,1,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,0xdfc1fc402600d63b);
      (*pcVar1)(in_stack_00000048,uVar9,2,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8);
      (*pcVar1)(in_stack_00000048,uVar9,3,uVar7);
      (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,in_stack_00000080,uVar9);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        uVar7 = DAT_1804b59f0;
        lVar5 = DAT_1804b59e8;
        if (cVar6 == '\0') {
          if (DAT_1804b3040 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59e8 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000003d0,1);
            lVar4 = DAT_1804b4d38;
            lVar3 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                              (in_stack_00000048,&stack0x00002770,0xd);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
            uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar7,uVar8);
            DAT_1804b3040 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b3040,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                             0xdfc5815bc278527a);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c8
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar7,in_stack_00000080,uVar8);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            uVar8 = DAT_1804b59f0;
            lVar5 = DAT_1804b59e8;
            if (cVar6 == '\0') {
              if (DAT_1804b3048 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b59e8 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x000003ce,1);
                lVar4 = DAT_1804b4d38;
                lVar3 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x518))
                                   (in_stack_00000048,&stack0x00004c50,0x29);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000058);
                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar10,uVar11);
                uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar8,uVar9);
                DAT_1804b3048 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b3048,in_stack_00000078);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,4,in_stack_00000088,0);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,0,uVar7);
              (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,1,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 0xdfd4e03d2f61cade);
              (*pcVar1)(in_stack_00000048,uVar9,2,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000c8);
              (*pcVar1)(in_stack_00000048,uVar9,3,uVar7);
              (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar8,in_stack_00000080,uVar9);
              (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            }
          }
        }
      }
    }
  }
  return;
}

