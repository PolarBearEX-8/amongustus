/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=102 entry=1802fed8a blocks=55 maxInstructions=50000 */

/* cut edge: 0x1802fee0c -> 0x180333186 */
/* cut edge: 0x1802fef03 -> 0x180333186 */
/* cut edge: 0x1802fef19 -> 0x180333186 */
/* cut edge: 0x1802fef81 -> 0x180307354 */
/* cut edge: 0x1802fefa5 -> 0x18030738a */
/* cut edge: 0x1802ff004 -> 0x1803073db */
/* cut edge: 0x1802ff014 -> 0x180307414 */
/* cut edge: 0x1802ff0ec -> 0x180333186 */
/* cut edge: 0x1802ff1e1 -> 0x180333186 */
/* cut edge: 0x1802ff1f7 -> 0x180333186 */
/* cut edge: 0x1802ff25f -> 0x18030754f */
/* cut edge: 0x1802ff28e -> 0x18030758a */
/* cut edge: 0x1802ff2ef -> 0x1803075e4 */
/* cut edge: 0x1802ff2ff -> 0x18030761d */
/* cut edge: 0x1802ff49f -> 0x180333186 */
/* cut edge: 0x1802ff4d3 -> 0x18029ad3c */
/* cut edge: 0x1802ff4fa -> 0x180333186 */
/* cut edge: 0x1802ff502 -> 0x180333186 */
/* cut edge: 0x1802ff56a -> 0x18030814c */
/* cut edge: 0x1802ff596 -> 0x180308187 */
/* cut edge: 0x1802ff5f2 -> 0x1803081d8 */
/* cut edge: 0x1802ff602 -> 0x180308211 */
/* cut edge: 0x1802ff6dd -> 0x180333186 */
/* cut edge: 0x1802ff7d4 -> 0x180333186 */
/* cut edge: 0x1802ff7ea -> 0x180333186 */
/* cut edge: 0x1802ff852 -> 0x180308248 */
/* cut edge: 0x1802ff87e -> 0x180308280 */
/* cut edge: 0x1802ff8dd -> 0x1803082d1 */
/* cut edge: 0x1802ff8ed -> 0x18030830a */
/* cut edge: 0x1802ff9cb -> 0x180333186 */
/* cut edge: 0x1802ffac5 -> 0x180333186 */
/* cut edge: 0x1802ffadb -> 0x180333186 */
/* cut edge: 0x1802ffb43 -> 0x180308341 */
/* cut edge: 0x1802ffb6f -> 0x18030837c */
/* cut edge: 0x1802ffbcb -> 0x1803083cd */
/* cut edge: 0x1802ffbdb -> 0x180308406 */
/* cut edge: 0x1802ffcb6 -> 0x180333186 */
/* cut edge: 0x1802ffcb6 -> 0x1802ffdab */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x1802ffdab original byte 0x48 replaced by RET */
/* target 0x180307354 original byte 0x48 replaced by RET */
/* target 0x18030738a original byte 0x48 replaced by RET */
/* target 0x1803073db original byte 0x48 replaced by RET */
/* target 0x180307414 original byte 0x48 replaced by RET */
/* target 0x18030754f original byte 0x48 replaced by RET */
/* target 0x18030758a original byte 0x48 replaced by RET */
/* target 0x1803075e4 original byte 0x48 replaced by RET */
/* target 0x18030761d original byte 0x48 replaced by RET */
/* target 0x18030814c original byte 0x48 replaced by RET */
/* target 0x180308187 original byte 0x48 replaced by RET */
/* target 0x1803081d8 original byte 0x48 replaced by RET */
/* target 0x180308211 original byte 0x48 replaced by RET */
/* target 0x180308248 original byte 0x48 replaced by RET */
/* target 0x180308280 original byte 0x48 replaced by RET */
/* target 0x1803082d1 original byte 0x48 replaced by RET */
/* target 0x18030830a original byte 0x48 replaced by RET */
/* target 0x180308341 original byte 0x48 replaced by RET */
/* target 0x18030837c original byte 0x48 replaced by RET */
/* target 0x1803083cd original byte 0x48 replaced by RET */
/* target 0x180308406 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


code * region_00102_1802fed8a(void)

{
  code *pcVar1;
  longlong lVar2;
  longlong lVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  code *pcVar7;
  code *pcVar8;
  code *pcVar9;
  code *unaff_RSI;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  undefined8 in_stack_00000058;
  ulonglong in_stack_00000060;
  code *in_stack_00000068;
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
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  ulonglong in_stack_000000d8;
  code *in_stack_000000e0;
  
  (*unaff_RSI)(in_stack_00000040,in_stack_00000080);
  (*in_stack_000000e0)(in_stack_00000040);
  uVar4 = (*in_stack_00000068)
                    (in_stack_00000040,in_stack_00000080,in_stack_00000048,in_stack_000000c8,
                     in_stack_00000058);
  DAT_1804b14c0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
  uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b14c0,in_stack_000000a0);
  uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar7 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x31bc);
  (*pcVar7)(in_stack_00000040,uVar5,0,uVar6);
  pcVar7 = *(code **)(*in_stack_00000040 + 0x570);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar7)(in_stack_00000040,uVar5,1,uVar6);
  uVar4 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar4,in_stack_000000a8,uVar5)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar4,in_stack_000000d0);
  pcVar7 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if ((char)pcVar7 == '\0') {
    pcVar7 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
    if ((char)pcVar7 == '\0') {
      pcVar7 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
      lVar3 = DAT_1804b5a08;
      uVar4 = DAT_1804b59f0;
      if ((char)pcVar7 == '\0') {
        if (DAT_1804b14c8 == 0) {
          pcVar7 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return pcVar7;
          }
          uVar5 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x0000041c,1);
          lVar2 = DAT_1804b4d38;
          pcVar1 = DAT_1804b4d30;
          pcVar8 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == (code *)0x0) {
            return pcVar8;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar8;
          }
          pcVar9 = (code *)(**(code **)(*in_stack_00000040 + 0x518))
                                     (in_stack_00000040,&stack0x000019f8,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar9;
          }
          uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar8)(in_stack_00000040,pcVar1,lVar2,pcVar9,uVar6);
          uVar4 = (*pcVar7)(in_stack_00000040,in_stack_00000080,lVar3,uVar4,uVar5);
          DAT_1804b14c8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
        }
        uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b14c8,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar7 = *(code **)(*in_stack_00000040 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x6849);
        (*pcVar7)(in_stack_00000040,uVar5,0,uVar6);
        pcVar7 = *(code **)(*in_stack_00000040 + 0x570);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0xdf7cd ^ in_stack_000000b0 ^ 0x7bba082d76f31344);
        (*pcVar7)(in_stack_00000040,uVar5,1,uVar6);
        uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar4,in_stack_000000d0);
        pcVar7 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if ((char)pcVar7 == '\0') {
          pcVar7 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
          if ((char)pcVar7 == '\0') {
            pcVar7 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
            lVar3 = DAT_1804b5a08;
            uVar4 = DAT_1804b59f0;
            if ((char)pcVar7 == '\0') {
              if (DAT_1804b14d0 == 0) {
                pcVar7 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return (code *)0x0;
                }
                uVar5 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x0000041a,1);
                lVar2 = DAT_1804b4d38;
                pcVar1 = DAT_1804b4d30;
                pcVar8 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b4d30 == (code *)0x0) {
                  return (code *)0x0;
                }
                if (DAT_1804b4d38 == 0) {
                  return DAT_1804b4d30;
                }
                pcVar9 = (code *)(**(code **)(*in_stack_00000040 + 0x518))
                                           (in_stack_00000040,&stack0x000019e8,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar9;
                }
                uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                  (in_stack_00000040,in_stack_00000080);
                (*pcVar8)(in_stack_00000040,pcVar1,lVar2,pcVar9,uVar6);
                uVar4 = (*pcVar7)(in_stack_00000040,in_stack_00000080,lVar3,uVar4,uVar5);
                DAT_1804b14d0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar4);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar4);
              }
              uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b14d0,in_stack_000000a0);
              uVar5 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar7 = *(code **)(*in_stack_00000040 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x142c);
              (*pcVar7)(in_stack_00000040,uVar5,0,uVar6);
              pcVar7 = *(code **)(*in_stack_00000040 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000070 % 0x77c2a ^ in_stack_000000b0 ^
                                 0x7ece81a27d0c0716);
              (*pcVar7)(in_stack_00000040,uVar5,1,uVar6);
              uVar4 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar4,in_stack_000000a8,uVar5);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar4,in_stack_000000d0);
              pcVar7 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if ((char)pcVar7 == '\0') {
                return (code *)(in_stack_00000060 ^ in_stack_000000d8);
              }
            }
          }
        }
      }
    }
  }
  return pcVar7;
}

