/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=96 entry=1802f8da2 blocks=53 maxInstructions=50000 */

/* cut edge: 0x1802f8e25 -> 0x180333186 */
/* cut edge: 0x1802f8f1c -> 0x180333186 */
/* cut edge: 0x1802f8f32 -> 0x180333186 */
/* cut edge: 0x1802f8f9d -> 0x180305ebe */
/* cut edge: 0x1802f8fc9 -> 0x180305ef6 */
/* cut edge: 0x1802f9028 -> 0x180305f47 */
/* cut edge: 0x1802f9038 -> 0x180305f80 */
/* cut edge: 0x1802f9116 -> 0x180333186 */
/* cut edge: 0x1802f9210 -> 0x180333186 */
/* cut edge: 0x1802f9226 -> 0x180333186 */
/* cut edge: 0x1802f928e -> 0x180305fb8 */
/* cut edge: 0x1802f92b2 -> 0x180305fee */
/* cut edge: 0x1802f9311 -> 0x18030603f */
/* cut edge: 0x1802f9321 -> 0x180306078 */
/* cut edge: 0x1802f93f9 -> 0x180333186 */
/* cut edge: 0x1802f94ee -> 0x180333186 */
/* cut edge: 0x1802f9504 -> 0x180333186 */
/* cut edge: 0x1802f956c -> 0x1803060ae */
/* cut edge: 0x1802f9598 -> 0x1803060e6 */
/* cut edge: 0x1802f95fc -> 0x180306141 */
/* cut edge: 0x1802f960c -> 0x18030617b */
/* cut edge: 0x1802f96ed -> 0x180333186 */
/* cut edge: 0x1802f97e9 -> 0x18029ad3c */
/* cut edge: 0x1802f9810 -> 0x180333186 */
/* cut edge: 0x1802f9818 -> 0x180333186 */
/* cut edge: 0x1802f987e -> 0x180304c28 */
/* cut edge: 0x1802f98a7 -> 0x180304c60 */
/* cut edge: 0x1802f9903 -> 0x180304cb1 */
/* cut edge: 0x1802f9913 -> 0x180304cea */
/* cut edge: 0x1802f99f0 -> 0x180333186 */
/* cut edge: 0x1802f9ae7 -> 0x180333186 */
/* cut edge: 0x1802f9afd -> 0x180333186 */
/* cut edge: 0x1802f9b63 -> 0x180304d59 */
/* cut edge: 0x1802f9b8c -> 0x180304e53 */
/* cut edge: 0x1802f9beb -> 0x180304ea4 */
/* cut edge: 0x1802f9bfb -> 0x180304edd */
/* cut edge: 0x1802f9cdb -> 0x180333186 */
/* cut edge: 0x1802f9cdb -> 0x1802f9dd2 */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x1802f9dd2 original byte 0x48 replaced by RET */
/* target 0x180304c28 original byte 0x48 replaced by RET */
/* target 0x180304c60 original byte 0x48 replaced by RET */
/* target 0x180304cb1 original byte 0x48 replaced by RET */
/* target 0x180304cea original byte 0x48 replaced by RET */
/* target 0x180304d59 original byte 0x48 replaced by RET */
/* target 0x180304e53 original byte 0x48 replaced by RET */
/* target 0x180304ea4 original byte 0x48 replaced by RET */
/* target 0x180304edd original byte 0x48 replaced by RET */
/* target 0x180305ebe original byte 0x48 replaced by RET */
/* target 0x180305ef6 original byte 0x48 replaced by RET */
/* target 0x180305f47 original byte 0x48 replaced by RET */
/* target 0x180305f80 original byte 0x48 replaced by RET */
/* target 0x180305fb8 original byte 0x48 replaced by RET */
/* target 0x180305fee original byte 0x48 replaced by RET */
/* target 0x18030603f original byte 0x48 replaced by RET */
/* target 0x180306078 original byte 0x4c replaced by RET */
/* target 0x1803060ae original byte 0x48 replaced by RET */
/* target 0x1803060e6 original byte 0x48 replaced by RET */
/* target 0x180306141 original byte 0x48 replaced by RET */
/* target 0x18030617b original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


code * region_00096_1802f8da2(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  ulonglong uVar7;
  code *pcVar8;
  code *pcVar9;
  code *pcVar10;
  code *pcVar11;
  code *unaff_R14;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  code *in_stack_00000058;
  longlong in_stack_00000060;
  ulonglong in_stack_00000068;
  code *in_stack_00000070;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  
  (*unaff_R14)(in_stack_00000040,in_stack_00000080);
  (*in_stack_00000058)(in_stack_00000040);
  uVar4 = (*in_stack_00000070)
                    (in_stack_00000040,in_stack_00000080,in_stack_000000d8,in_stack_00000048,
                     in_stack_000000c8);
  DAT_1804b19c0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
  uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b19c0,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar8 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x38ac);
  (*pcVar8)(in_stack_00000040,uVar5,0,uVar6);
  pcVar8 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar8)(in_stack_00000040,uVar5,1,uVar6);
  uVar4 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar4,in_stack_000000a8,uVar5)
  ;
  uVar7 = (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar4,in_stack_000000d0);
  pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if ((char)pcVar8 == '\0') {
    pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
    if ((char)pcVar8 == '\0') {
      pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
      lVar3 = DAT_1804b5a08;
      uVar4 = DAT_1804b59f0;
      if ((char)pcVar8 == '\0') {
        if (DAT_1804b19c8 == 0) {
          pcVar8 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return (code *)0x0;
          }
          uVar5 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000002da,1);
          lVar2 = DAT_1804b4d38;
          lVar1 = DAT_1804b4d30;
          pcVar9 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar9;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar9;
          }
          pcVar10 = (code *)(**(code **)(*in_stack_00000040 + 0x518))
                                      (in_stack_00000040,&stack0x000010e8,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar10;
          }
          uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar9)(in_stack_00000040,lVar1,lVar2,pcVar10,uVar6);
          uVar4 = (*pcVar8)(in_stack_00000040,in_stack_00000080,lVar3,uVar4,uVar5);
          DAT_1804b19c8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
        }
        uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b19c8,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar8 = *(code **)(*in_stack_00000040 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x248c);
        (*pcVar8)(in_stack_00000040,uVar5,0,uVar6);
        pcVar8 = *(code **)(*in_stack_00000040 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000060 % 0x91cfc ^ in_stack_000000b0 ^ 0xf0ac57499642f6d6);
        (*pcVar8)(in_stack_00000040,uVar5,1,uVar6);
        uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar4,in_stack_000000d0);
        pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if ((char)pcVar8 == '\0') {
          pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
          if ((char)pcVar8 == '\0') {
            pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
            lVar3 = DAT_1804b5a08;
            uVar4 = DAT_1804b59f0;
            if ((char)pcVar8 == '\0') {
              if (DAT_1804b19d0 == 0) {
                pcVar8 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return pcVar8;
                }
                uVar5 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x000002d8,1);
                lVar2 = DAT_1804b4d38;
                lVar1 = DAT_1804b4d30;
                pcVar9 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return pcVar9;
                }
                if (DAT_1804b4d38 == 0) {
                  return pcVar9;
                }
                pcVar10 = (code *)(**(code **)(*in_stack_00000040 + 0x518))
                                            (in_stack_00000040,&stack0x000010d8,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar10;
                }
                uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                  (in_stack_00000040,in_stack_00000080);
                (*pcVar9)(in_stack_00000040,lVar1,lVar2,pcVar10,uVar6);
                uVar4 = (*pcVar8)(in_stack_00000040,in_stack_00000080,lVar3,uVar4,uVar5);
                DAT_1804b19d0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
              }
              uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b19d0,in_stack_000000a0);
              uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar8 = *(code **)(*in_stack_00000040 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x26be);
              (*pcVar8)(in_stack_00000040,uVar5,0,uVar6);
              pcVar8 = *(code **)(*in_stack_00000040 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000060 % 0xcd114 ^ in_stack_000000b0 ^
                                 0xbac7aed4026e1e02);
              (*pcVar8)(in_stack_00000040,uVar5,1,uVar6);
              uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar4,in_stack_000000d0);
              pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if ((char)pcVar8 == '\0') {
                pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
                if ((char)pcVar8 == '\0') {
                  pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
                  lVar3 = DAT_1804b5a08;
                  uVar4 = DAT_1804b59f0;
                  if ((char)pcVar8 == '\0') {
                    if (DAT_1804b19d8 == 0) {
                      pcVar8 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b5a08 == 0) {
                        return pcVar8;
                      }
                      pcVar10 = (code *)(**(code **)(*in_stack_00000040 + 0x518))
                                                  (in_stack_00000040,&stack0x000002d6,1);
                      lVar2 = DAT_1804b4d38;
                      lVar1 = DAT_1804b4d30;
                      pcVar9 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return pcVar10;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return pcVar10;
                      }
                      pcVar11 = (code *)(**(code **)(*in_stack_00000040 + 0x518))
                                                  (in_stack_00000040,&stack0x000010c8,5);
                      if (DAT_1804b4d40 == 0) {
                        return pcVar11;
                      }
                      uVar5 = (**(code **)(*in_stack_00000040 + 0x110))
                                        (in_stack_00000040,in_stack_00000080);
                      (*pcVar9)(in_stack_00000040,lVar1,lVar2,pcVar11,uVar5);
                      uVar4 = (*pcVar8)(in_stack_00000040,in_stack_00000080,lVar3,uVar4,pcVar10);
                      DAT_1804b19d8 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
                    }
                    uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,DAT_1804b19d8,in_stack_000000a0);
                    uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar8 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0xd9e);
                    (*pcVar8)(in_stack_00000040,uVar5,0,uVar6);
                    pcVar8 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                       in_stack_00000060 % 0x17030 ^ in_stack_000000b0 ^
                                       0xc76407f6f356ad0a);
                    (*pcVar8)(in_stack_00000040,uVar5,1,uVar6);
                    uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
                    (**(code **)(*in_stack_00000040 + 0x1a0))
                              (in_stack_00000040,uVar4,in_stack_000000d0);
                    pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if ((char)pcVar8 == '\0') {
                      return (code *)(uVar7 ^ in_stack_00000068);
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
  return pcVar8;
}

