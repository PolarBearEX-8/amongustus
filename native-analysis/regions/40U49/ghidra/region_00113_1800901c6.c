/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=113 entry=1800901c6 blocks=55 maxInstructions=100000 */

/* cut edge: 0x1800901e8 -> 0x180125417 */
/* cut edge: 0x180090213 -> 0x18014d3db */
/* cut edge: 0x180090230 -> 0x18014d3db */
/* cut edge: 0x180090298 -> 0x1800a93f7 */
/* cut edge: 0x1800902bc -> 0x1800a942d */
/* cut edge: 0x180090318 -> 0x1800a947e */
/* cut edge: 0x180090328 -> 0x1800a94b7 */
/* cut edge: 0x1800903fe -> 0x18014d3db */
/* cut edge: 0x1800904f3 -> 0x18014d3db */
/* cut edge: 0x180090509 -> 0x18014d3db */
/* cut edge: 0x180090579 -> 0x1800a95e1 */
/* cut edge: 0x18009059d -> 0x1800a9614 */
/* cut edge: 0x1800905fe -> 0x1800a9665 */
/* cut edge: 0x18009060e -> 0x1800a969f */
/* cut edge: 0x1800906e6 -> 0x18014d3db */
/* cut edge: 0x1800907db -> 0x18014d3db */
/* cut edge: 0x1800907f1 -> 0x18014d3db */
/* cut edge: 0x18009085c -> 0x1800a97c6 */
/* cut edge: 0x180090880 -> 0x1800a97f9 */
/* cut edge: 0x1800908e1 -> 0x1800a984a */
/* cut edge: 0x1800908f1 -> 0x1800a9884 */
/* cut edge: 0x1800909c9 -> 0x18014d3db */
/* cut edge: 0x180090abe -> 0x18014d3db */
/* cut edge: 0x180090ad4 -> 0x18014d3db */
/* cut edge: 0x180090b3f -> 0x1800a99ae */
/* cut edge: 0x180090b68 -> 0x1800a99e1 */
/* cut edge: 0x180090bc9 -> 0x1800a9a32 */
/* cut edge: 0x180090bd9 -> 0x1800a9a6c */
/* cut edge: 0x180090cb6 -> 0x18014d3db */
/* cut edge: 0x180090dab -> 0x18009c9ee */
/* cut edge: 0x180090dc7 -> 0x18014d3db */
/* cut edge: 0x180090ddd -> 0x18014d3db */
/* cut edge: 0x180090e45 -> 0x1800a857c */
/* cut edge: 0x180090e69 -> 0x1800a85b2 */
/* cut edge: 0x180090ec5 -> 0x1800a8603 */
/* cut edge: 0x180090ed5 -> 0x1800a863c */
/* cut edge: 0x180090fab -> 0x18014d3db */
/* cut edge: 0x1800910a5 -> 0x18014d3db */
/* cut edge: 0x1800910bb -> 0x18014d3db */
/* cut edge: 0x1800910d1 -> 0x180091296 */
/* cut edge: 0x1800910d1 -> 0x180091133 */
/* target 0x180091133 original byte 0x48 replaced by RET */
/* target 0x180091296 original byte 0x48 replaced by RET */
/* target 0x18009c9ee original byte 0x4c replaced by RET */
/* target 0x1800a857c original byte 0x48 replaced by RET */
/* target 0x1800a85b2 original byte 0x48 replaced by RET */
/* target 0x1800a8603 original byte 0x48 replaced by RET */
/* target 0x1800a863c original byte 0x48 replaced by RET */
/* target 0x1800a93f7 original byte 0x48 replaced by RET */
/* target 0x1800a942d original byte 0x48 replaced by RET */
/* target 0x1800a947e original byte 0x48 replaced by RET */
/* target 0x1800a94b7 original byte 0x48 replaced by RET */
/* target 0x1800a95e1 original byte 0x48 replaced by RET */
/* target 0x1800a9614 original byte 0x48 replaced by RET */
/* target 0x1800a9665 original byte 0x48 replaced by RET */
/* target 0x1800a969f original byte 0x48 replaced by RET */
/* target 0x1800a97c6 original byte 0x48 replaced by RET */
/* target 0x1800a97f9 original byte 0x48 replaced by RET */
/* target 0x1800a984a original byte 0x48 replaced by RET */
/* target 0x1800a9884 original byte 0x48 replaced by RET */
/* target 0x1800a99ae original byte 0x48 replaced by RET */
/* target 0x1800a99e1 original byte 0x48 replaced by RET */
/* target 0x1800a9a32 original byte 0x48 replaced by RET */
/* target 0x1800a9a6c original byte 0x48 replaced by RET */
/* target 0x180125417 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


code * region_00113_1800901c6(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  code *pcVar4;
  undefined8 uVar5;
  code *pcVar6;
  code *pcVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  ulonglong uVar10;
  longlong unaff_RDI;
  longlong unaff_R15;
  ulonglong uVar11;
  longlong *in_stack_00000048;
  ulonglong uStack0000000000000060;
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
  undefined8 in_stack_00000148;
  int in_stack_00000c30;
  
  uStack0000000000000060 = unaff_R15 - unaff_RDI;
  if (in_stack_000000f8 == 0) {
    return (code *)(ulonglong)in_stack_00000148._4_4_;
  }
  if (in_stack_00000c30 <= (int)in_stack_00000148._4_4_) {
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar3 = DAT_1804b52e8;
    uVar9 = DAT_1804b5208;
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    uVar11 = (longlong)uStack0000000000000060 % 0x3a246;
    if (DAT_1804adb48 == 0) {
      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return pcVar4;
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000055c,1);
      lVar2 = DAT_1804b4d38;
      lVar1 = DAT_1804b4d30;
      pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar6;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar6;
      }
      pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00002598,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar7;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
      uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar5);
      DAT_1804adb48 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804adb48,in_stack_000000b0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x674e);
    (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       uVar11 ^ in_stack_000000c0 ^ 0xec9a05e42cf2353);
    (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar3 = DAT_1804b52e8;
    uVar9 = DAT_1804b5208;
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    uVar11 = (longlong)uStack0000000000000060 % 0x4b867;
    if (DAT_1804adb50 == 0) {
      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return (code *)0x0;
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000055a,1);
      lVar2 = DAT_1804b4d38;
      lVar1 = DAT_1804b4d30;
      pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar6;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar6;
      }
      pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00002588,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar7;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
      uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar5);
      DAT_1804adb50 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804adb50,in_stack_000000b0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2001);
    (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       uVar11 ^ in_stack_000000c0 ^ 0x676cee9038a73742);
    (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar3 = DAT_1804b52e8;
    uVar9 = DAT_1804b5208;
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    uVar11 = (longlong)uStack0000000000000060 % 0x96191;
    if (DAT_1804adb58 == 0) {
      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return (code *)0x0;
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000558,1);
      lVar2 = DAT_1804b4d38;
      lVar1 = DAT_1804b4d30;
      pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar6;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar6;
      }
      pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00002578,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar7;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
      uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar5);
      DAT_1804adb58 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804adb58,in_stack_000000b0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1729);
    (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       uVar11 ^ in_stack_000000c0 ^ 0xc686149a2af79fc);
    (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
    lVar3 = DAT_1804b52e8;
    uVar9 = DAT_1804b5208;
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    uVar11 = uVar11 ^ uStack0000000000000060;
    uVar10 = (longlong)uStack0000000000000060 % 0x4cfea ^ in_stack_000000c0 ^ 0x7a90b046b8cad7d7;
    if (DAT_1804adb60 == 0) {
      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return (code *)0x0;
      }
      uStack0000000000000060 = uVar10;
      uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000556,1);
      lVar2 = DAT_1804b4d38;
      lVar1 = DAT_1804b4d30;
      pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar6;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar6;
      }
      pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00002568,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar7;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
      uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar5);
      DAT_1804adb60 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      uVar10 = uStack0000000000000060;
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804adb60,in_stack_000000b0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x481);
    (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,uVar10);
    (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
    lVar3 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)pcVar4 != '\0') {
      return pcVar4;
    }
    uStack0000000000000060 = uVar11 - lVar3;
    if (in_stack_000000f8 == 0) {
      return pcVar4;
    }
  }
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if (((char)pcVar4 == '\0') &&
     (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar3 = DAT_1804b52e8,
     uVar9 = DAT_1804b5208, (char)pcVar4 == '\0')) {
    uVar11 = (longlong)uStack0000000000000060 % 0x9c657;
    if (DAT_1804adb68 == 0) {
      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return pcVar4;
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000554,1);
      lVar2 = DAT_1804b4d38;
      lVar1 = DAT_1804b4d30;
      pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar6;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar6;
      }
      pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00002558,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar7;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
      uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar5);
      DAT_1804adb68 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804adb68,in_stack_000000b0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x59ab);
    (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       uVar11 ^ in_stack_000000c0 ^ 0x6d0621746ae88354);
    (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((((char)pcVar4 == '\0') &&
        (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar4 == '\0')) &&
       (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar4 == '\0')) {
      if (DAT_1804adb70 == 0) {
        return (code *)0x7633150892d57d53;
      }
      return (code *)0x7633150892d57d53;
    }
  }
  return pcVar4;
}

