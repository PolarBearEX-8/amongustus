/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=12 entry=1803e7573 blocks=45 maxInstructions=50000 */

/* cut edge: 0x1803e7573 -> 0x1803eab33 */
/* cut edge: 0x1803e7583 -> 0x1803eab6c */
/* cut edge: 0x1803e765c -> 0x180473ae8 */
/* cut edge: 0x1803e7753 -> 0x180473ae8 */
/* cut edge: 0x1803e7769 -> 0x180473ae8 */
/* cut edge: 0x1803e77d1 -> 0x1803eaba4 */
/* cut edge: 0x1803e77fa -> 0x1803eabdc */
/* cut edge: 0x1803e7859 -> 0x1803eac2d */
/* cut edge: 0x1803e7869 -> 0x1803eac66 */
/* cut edge: 0x1803e7945 -> 0x180473ae8 */
/* cut edge: 0x1803e7a3c -> 0x180473ae8 */
/* cut edge: 0x1803e7a52 -> 0x180473ae8 */
/* cut edge: 0x1803e7aba -> 0x1803eac9e */
/* cut edge: 0x1803e7ae3 -> 0x1803eacd6 */
/* cut edge: 0x1803e7b45 -> 0x1803ead27 */
/* cut edge: 0x1803e7b55 -> 0x1803ead61 */
/* cut edge: 0x1803e7c36 -> 0x180473ae8 */
/* cut edge: 0x1803e7d2b -> 0x180473ae8 */
/* cut edge: 0x1803e7d41 -> 0x180473ae8 */
/* cut edge: 0x1803e7da9 -> 0x1803ead98 */
/* cut edge: 0x1803e7dd5 -> 0x1803eadd0 */
/* cut edge: 0x1803e7e3c -> 0x1803eae2a */
/* cut edge: 0x1803e7e4c -> 0x1803eae63 */
/* cut edge: 0x1803e7f2f -> 0x180473ae8 */
/* cut edge: 0x1803e8024 -> 0x1803f9792 */
/* cut edge: 0x1803e8048 -> 0x180473ae8 */
/* cut edge: 0x1803e81a1 -> 0x180473ae8 */
/* cut edge: 0x1803e829a -> 0x180445f3b */
/* cut edge: 0x1803e82a7 -> 0x180473ae8 */
/* cut edge: 0x1803e8400 -> 0x180473ae8 */
/* cut edge: 0x1803e84f9 -> 0x180428c34 */
/* cut edge: 0x1803e8506 -> 0x180473ae8 */
/* cut edge: 0x1803e851c -> 0x1803e865f */
/* cut edge: 0x1803e852c -> 0x1803e865f */
/* target 0x1803e865f original byte 0x48 replaced by RET */
/* target 0x1803eab33 original byte 0x48 replaced by RET */
/* target 0x1803eab6c original byte 0x48 replaced by RET */
/* target 0x1803eaba4 original byte 0x48 replaced by RET */
/* target 0x1803eabdc original byte 0x48 replaced by RET */
/* target 0x1803eac2d original byte 0x48 replaced by RET */
/* target 0x1803eac66 original byte 0x48 replaced by RET */
/* target 0x1803eac9e original byte 0x48 replaced by RET */
/* target 0x1803eacd6 original byte 0x48 replaced by RET */
/* target 0x1803ead27 original byte 0x48 replaced by RET */
/* target 0x1803ead61 original byte 0x48 replaced by RET */
/* target 0x1803ead98 original byte 0x48 replaced by RET */
/* target 0x1803eadd0 original byte 0x48 replaced by RET */
/* target 0x1803eae2a original byte 0x48 replaced by RET */
/* target 0x1803eae63 original byte 0x48 replaced by RET */
/* target 0x1803f9792 original byte 0x48 replaced by RET */
/* target 0x180428c34 original byte 0x48 replaced by RET */
/* target 0x180445f3b original byte 0x4c replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


code * region_00012_1803e7573(void)

{
  code *pcVar1;
  longlong lVar2;
  longlong lVar3;
  code *in_RAX;
  code *pcVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  code *pcVar8;
  code *pcVar9;
  longlong unaff_RSI;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  code *in_stack_00000060;
  longlong in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  code *in_stack_000000d0;
  undefined8 in_stack_000000d8;
  code *in_stack_000000e8;
  
  if (DAT_1804b4d38 == 0) {
    return in_RAX;
  }
  pcVar4 = (code *)(**(code **)(unaff_RSI + 0x518))(in_stack_00000048,&stack0x00001ac0,5);
  if (DAT_1804b4d40 == 0) {
    return pcVar4;
  }
  (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
  (*in_stack_000000d0)(in_stack_00000048);
  uVar5 = (*in_stack_00000060)
                    (in_stack_00000048,in_stack_00000088,in_stack_00000058,in_stack_00000070,
                     in_stack_00000078);
  DAT_1804b3ff8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804b3ff8,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x3465);
  (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
  pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
  uVar5 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar5,in_stack_000000a8,uVar6)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar4 == '\0') {
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
      lVar3 = DAT_1804b5a08;
      uVar5 = DAT_1804b59f0;
      if ((char)pcVar4 == '\0') {
        if (DAT_1804b4000 == 0) {
          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return (code *)0x0;
          }
          uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000004ce,1);
          lVar2 = DAT_1804b4d38;
          pcVar1 = DAT_1804b4d30;
          pcVar8 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == (code *)0x0) {
            return pcVar8;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar8;
          }
          pcVar9 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00001ab0,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar9;
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar8)(in_stack_00000048,pcVar1,lVar2,pcVar9,uVar7);
          uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar3,uVar5,uVar6);
          DAT_1804b4000 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
        }
        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b4000,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x727d);
        (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0x1608a ^ in_stack_000000c8 ^ 0x4d2bbf9f100ed0bf);
        (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar4 == '\0') {
          pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)pcVar4 == '\0') {
            pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
            lVar3 = DAT_1804b5a08;
            uVar5 = DAT_1804b59f0;
            if ((char)pcVar4 == '\0') {
              if (DAT_1804b4008 == 0) {
                pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return (code *)0x0;
                }
                uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x000004cc,1);
                lVar2 = DAT_1804b4d38;
                pcVar1 = DAT_1804b4d30;
                pcVar8 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == (code *)0x0) {
                  return pcVar8;
                }
                if (DAT_1804b4d38 == 0) {
                  return pcVar8;
                }
                pcVar9 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                           (in_stack_00000048,&stack0x00001aa0,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar9;
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000088);
                (*pcVar8)(in_stack_00000048,pcVar1,lVar2,pcVar9,uVar7);
                uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar3,uVar5,uVar6);
                DAT_1804b4008 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
              }
              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b4008,in_stack_000000a0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x8bc);
              (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
              pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 in_stack_00000068 % 0x2f246 ^ in_stack_000000c8 ^
                                 0x744ad8400d779727);
              (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
              uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar5,in_stack_000000d8);
              pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((char)pcVar4 == '\0') {
                pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)pcVar4 == '\0') {
                  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
                  lVar3 = DAT_1804b5a08;
                  uVar5 = DAT_1804b59f0;
                  if ((char)pcVar4 == '\0') {
                    if (DAT_1804b4010 == 0) {
                      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b5a08 == 0) {
                        return (code *)0x0;
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                        (in_stack_00000048,&stack0x000004ca,1);
                      lVar2 = DAT_1804b4d38;
                      pcVar1 = DAT_1804b4d30;
                      pcVar8 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b4d30 == (code *)0x0) {
                        return (code *)0x0;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return DAT_1804b4d30;
                      }
                      pcVar9 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                                 (in_stack_00000048,&stack0x00001a90,5);
                      if (DAT_1804b4d40 == 0) {
                        return pcVar9;
                      }
                      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                        (in_stack_00000048,in_stack_00000088);
                      (*pcVar8)(in_stack_00000048,pcVar1,lVar2,pcVar9,uVar7);
                      uVar5 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar3,uVar5,uVar6);
                      DAT_1804b4010 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar5);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar5);
                    }
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804b4010,in_stack_000000a0);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_00000090,0);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5f56)
                    ;
                    (*pcVar4)(in_stack_00000048,uVar6,0,uVar7);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                       in_stack_00000068 % 0xc6e81 ^ in_stack_000000c8 ^
                                       0x2426c2f1525939e9);
                    (*pcVar4)(in_stack_00000048,uVar6,1,uVar7);
                    uVar5 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar5,in_stack_000000a8,uVar6);
                    (**(code **)(*in_stack_00000048 + 0x1a0))
                              (in_stack_00000048,uVar5,in_stack_000000d8);
                    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if ((char)pcVar4 == '\0') {
                      return in_stack_000000e8;
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
  return pcVar4;
}

