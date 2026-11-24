/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=225 entry=180100153 blocks=56 maxInstructions=100000 */

/* cut edge: 0x180100153 -> 0x18014d3db */
/* cut edge: 0x180100169 -> 0x18014d3db */
/* cut edge: 0x1801001cf -> 0x180110647 */
/* cut edge: 0x1801001f6 -> 0x18011067d */
/* cut edge: 0x180100252 -> 0x1801106ce */
/* cut edge: 0x180100267 -> 0x180110707 */
/* cut edge: 0x180100345 -> 0x18014d3db */
/* cut edge: 0x18010043c -> 0x18012ffc9 */
/* cut edge: 0x1801004b0 -> 0x18014d3db */
/* cut edge: 0x1801004c6 -> 0x18014d3db */
/* cut edge: 0x180100531 -> 0x18010fbc2 */
/* cut edge: 0x180100558 -> 0x18010fbf5 */
/* cut edge: 0x1801005b9 -> 0x18010fc46 */
/* cut edge: 0x1801005c9 -> 0x18010fc7f */
/* cut edge: 0x1801006a9 -> 0x18014d3db */
/* cut edge: 0x18010079e -> 0x18014d3db */
/* cut edge: 0x1801007b4 -> 0x18014d3db */
/* cut edge: 0x180100822 -> 0x18010fcb6 */
/* cut edge: 0x180100849 -> 0x18010fce9 */
/* cut edge: 0x1801008a7 -> 0x18010fd37 */
/* cut edge: 0x1801008bf -> 0x18010fd71 */
/* cut edge: 0x1801009a2 -> 0x18014d3db */
/* cut edge: 0x180100a99 -> 0x18014d3db */
/* cut edge: 0x180100aaf -> 0x18014d3db */
/* cut edge: 0x180100b1a -> 0x18010fda9 */
/* cut edge: 0x180100b41 -> 0x18010fddc */
/* cut edge: 0x180100ba2 -> 0x18010fe2d */
/* cut edge: 0x180100bb2 -> 0x18010fe66 */
/* cut edge: 0x180100c92 -> 0x18014d3db */
/* cut edge: 0x180100d87 -> 0x18014d3db */
/* cut edge: 0x180100d9d -> 0x18014d3db */
/* cut edge: 0x180100e03 -> 0x18010fe9d */
/* cut edge: 0x180100e2a -> 0x18010fed0 */
/* cut edge: 0x180100e8b -> 0x18010ff21 */
/* cut edge: 0x180100ea0 -> 0x18010ff5a */
/* cut edge: 0x180100f80 -> 0x18014d3db */
/* cut edge: 0x180101075 -> 0x18010b77b */
/* cut edge: 0x18010108a -> 0x18014d3db */
/* cut edge: 0x1801010a0 -> 0x18014d3db */
/* cut edge: 0x1801010b6 -> 0x180101283 */
/* cut edge: 0x18010110b -> 0x18010c910 */
/* cut edge: 0x18010110b -> 0x180101132 */
/* target 0x180101132 original byte 0x48 replaced by RET */
/* target 0x180101283 original byte 0x48 replaced by RET */
/* target 0x18010b77b original byte 0x4c replaced by RET */
/* target 0x18010c910 original byte 0x48 replaced by RET */
/* target 0x18010fbc2 original byte 0x48 replaced by RET */
/* target 0x18010fbf5 original byte 0x48 replaced by RET */
/* target 0x18010fc46 original byte 0x48 replaced by RET */
/* target 0x18010fc7f original byte 0x48 replaced by RET */
/* target 0x18010fcb6 original byte 0x48 replaced by RET */
/* target 0x18010fce9 original byte 0x48 replaced by RET */
/* target 0x18010fd37 original byte 0x48 replaced by RET */
/* target 0x18010fd71 original byte 0x48 replaced by RET */
/* target 0x18010fda9 original byte 0x48 replaced by RET */
/* target 0x18010fddc original byte 0x48 replaced by RET */
/* target 0x18010fe2d original byte 0x48 replaced by RET */
/* target 0x18010fe66 original byte 0x48 replaced by RET */
/* target 0x18010fe9d original byte 0x48 replaced by RET */
/* target 0x18010fed0 original byte 0x48 replaced by RET */
/* target 0x18010ff21 original byte 0x48 replaced by RET */
/* target 0x18010ff5a original byte 0x48 replaced by RET */
/* target 0x180110647 original byte 0x48 replaced by RET */
/* target 0x18011067d original byte 0x48 replaced by RET */
/* target 0x1801106ce original byte 0x48 replaced by RET */
/* target 0x180110707 original byte 0x48 replaced by RET */
/* target 0x18012ffc9 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00225_180100153(void)

{
  longlong lVar1;
  longlong lVar2;
  code *pcVar3;
  undefined8 uVar4;
  code *pcVar5;
  code *pcVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong lVar9;
  code *pcVar10;
  ulonglong unaff_R13;
  ulonglong unaff_R15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  ulonglong in_stack_000000c0;
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_000000d8;
  longlong in_stack_000000f8;
  int in_stack_00000188;
  int in_stack_000001b8;
  
  pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if (((char)pcVar3 == '\0') &&
     (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), pcVar10 = DAT_1804b52e8,
     uVar8 = DAT_1804b5208, (char)pcVar3 == '\0')) {
    if (DAT_1804ae648 == 0) {
      pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == (code *)0x0) {
        return pcVar3;
      }
      uVar4 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000298,1);
      lVar1 = DAT_1804b4d38;
      lVar9 = DAT_1804b4d30;
      pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar5;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar5;
      }
      pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00001258,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar6;
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar5)(in_stack_00000048,lVar9,lVar1,pcVar6,uVar7);
      uVar8 = (*pcVar3)(in_stack_00000048,in_stack_00000090,pcVar10,uVar8,uVar4);
      DAT_1804ae648 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
    }
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804ae648,in_stack_000000b0);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7367);
    (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
    pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       (longlong)unaff_R15 % 0x1a2f6 ^ in_stack_000000c0 ^ 0xf31817a91c514f0);
    (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
    lVar9 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
    pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)pcVar3 == '\0') {
      lVar9 = lVar9 + (unaff_R13 ^ unaff_R15);
      if (in_stack_000000f8 == 0) {
        return pcVar3;
      }
      if (in_stack_00000188 <= in_stack_000001b8) {
        pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar3 != '\0') {
          return pcVar3;
        }
        pcVar10 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        pcVar3 = DAT_1804b52e8;
        uVar8 = DAT_1804b5208;
        if ((char)pcVar10 != '\0') {
          return pcVar10;
        }
        if (DAT_1804ae650 == 0) {
          pcVar10 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == (code *)0x0) {
            return (code *)0x0;
          }
          uVar4 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000296,1);
          lVar2 = DAT_1804b4d38;
          lVar1 = DAT_1804b4d30;
          pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar5;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar5;
          }
          pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00001248,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar6;
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar5)(in_stack_00000048,lVar1,lVar2,pcVar6,uVar7);
          uVar8 = (*pcVar10)(in_stack_00000048,in_stack_00000090,pcVar3,uVar8,uVar4);
          DAT_1804ae650 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae650,in_stack_000000b0);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x701a);
        (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           lVar9 % 0x8045c ^ in_stack_000000c0 ^ 0x33a02502f81c0f8c);
        (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
        pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar3 != '\0') {
          return pcVar3;
        }
        pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar3 != '\0') {
          return pcVar3;
        }
        pcVar10 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        pcVar3 = DAT_1804b52e8;
        uVar8 = DAT_1804b5208;
        if ((char)pcVar10 != '\0') {
          return pcVar10;
        }
        if (DAT_1804ae658 == 0) {
          pcVar10 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == (code *)0x0) {
            return (code *)0x0;
          }
          uVar4 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000294,1);
          lVar2 = DAT_1804b4d38;
          lVar1 = DAT_1804b4d30;
          pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar5;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar5;
          }
          pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00001238,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar6;
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar5)(in_stack_00000048,lVar1,lVar2,pcVar6,uVar7);
          uVar8 = (*pcVar10)(in_stack_00000048,in_stack_00000090,pcVar3,uVar8,uVar4);
          DAT_1804ae658 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae658,in_stack_000000b0);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1643);
        (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           lVar9 % 0xf180f ^ in_stack_000000c0 ^ 0x482f18d0004da48b);
        (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
        pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar3 != '\0') {
          return pcVar3;
        }
        pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar3 != '\0') {
          return pcVar3;
        }
        pcVar10 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        pcVar3 = DAT_1804b52e8;
        uVar8 = DAT_1804b5208;
        if ((char)pcVar10 != '\0') {
          return pcVar10;
        }
        if (DAT_1804ae660 == 0) {
          pcVar10 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == (code *)0x0) {
            return (code *)0x0;
          }
          uVar4 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000292,1);
          lVar2 = DAT_1804b4d38;
          lVar1 = DAT_1804b4d30;
          pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar5;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar5;
          }
          pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00001228,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar6;
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar5)(in_stack_00000048,lVar1,lVar2,pcVar6,uVar7);
          uVar8 = (*pcVar10)(in_stack_00000048,in_stack_00000090,pcVar3,uVar8,uVar4);
          DAT_1804ae660 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae660,in_stack_000000b0);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0xb6e);
        (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           lVar9 % 0xc87f3 ^ in_stack_000000c0 ^ 0x397d7eaad711e32e);
        (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
        pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar3 != '\0') {
          return pcVar3;
        }
        pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar3 != '\0') {
          return pcVar3;
        }
        pcVar10 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        pcVar3 = DAT_1804b52e8;
        uVar8 = DAT_1804b5208;
        if ((char)pcVar10 != '\0') {
          return pcVar10;
        }
        if (DAT_1804ae668 == 0) {
          pcVar10 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == (code *)0x0) {
            return (code *)0x0;
          }
          uVar4 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000290,1);
          lVar2 = DAT_1804b4d38;
          lVar1 = DAT_1804b4d30;
          pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar5;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar5;
          }
          pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00001218,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar6;
          }
          uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar5)(in_stack_00000048,lVar1,lVar2,pcVar6,uVar7);
          uVar8 = (*pcVar10)(in_stack_00000048,in_stack_00000090,pcVar3,uVar8,uVar4);
          DAT_1804ae668 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae668,in_stack_000000b0);
        uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5226);
        (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           lVar9 % 0x26905 ^ in_stack_000000c0 ^ 0x6cd657aeba847e2f);
        (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
        pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar3 != '\0') {
          return pcVar3;
        }
        if (in_stack_000000f8 == 0) {
          return pcVar3;
        }
      }
      pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
      if (((char)pcVar3 == '\0') &&
         (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar3 == '\0')) {
        if (DAT_1804ae670 != 0) {
          return (code *)0x2964680d26b51567;
        }
        if (DAT_1804b52e8 != (code *)0x0) {
          return DAT_1804b52e8;
        }
        return (code *)0x0;
      }
    }
  }
  return pcVar3;
}

