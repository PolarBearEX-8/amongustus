/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=230 entry=180105161 blocks=52 maxInstructions=100000 */

/* cut edge: 0x180105161 -> 0x18014d3db */
/* cut edge: 0x180105268 -> 0x180125436 */
/* cut edge: 0x180105277 -> 0x18014d3db */
/* cut edge: 0x18010528d -> 0x18014d3db */
/* cut edge: 0x1801052f6 -> 0x18010f7f0 */
/* cut edge: 0x18010531d -> 0x18010f823 */
/* cut edge: 0x18010537e -> 0x18010f874 */
/* cut edge: 0x180105393 -> 0x18010f8ad */
/* cut edge: 0x180105473 -> 0x18014d3db */
/* cut edge: 0x180105568 -> 0x18014d3db */
/* cut edge: 0x18010557e -> 0x18014d3db */
/* cut edge: 0x1801055ec -> 0x18010f8e4 */
/* cut edge: 0x180105613 -> 0x18010f917 */
/* cut edge: 0x180105671 -> 0x18010f965 */
/* cut edge: 0x18010568c -> 0x18010f99f */
/* cut edge: 0x18010576d -> 0x18014d3db */
/* cut edge: 0x180105867 -> 0x18014d3db */
/* cut edge: 0x18010587d -> 0x18014d3db */
/* cut edge: 0x1801058eb -> 0x18010f9d7 */
/* cut edge: 0x18010590f -> 0x18010fa0a */
/* cut edge: 0x180105970 -> 0x18010fa5b */
/* cut edge: 0x180105985 -> 0x18010fa94 */
/* cut edge: 0x180105a62 -> 0x18014d3db */
/* cut edge: 0x180105b57 -> 0x18014d3db */
/* cut edge: 0x180105b6d -> 0x18014d3db */
/* cut edge: 0x180105bd3 -> 0x18010facb */
/* cut edge: 0x180105bfc -> 0x18010fb01 */
/* cut edge: 0x180105c58 -> 0x18010fb52 */
/* cut edge: 0x180105c6d -> 0x18010fb8b */
/* cut edge: 0x180105d4a -> 0x18014d3db */
/* cut edge: 0x180105e3f -> 0x180109c15 */
/* cut edge: 0x180105e61 -> 0x180125436 */
/* cut edge: 0x180105e70 -> 0x18014d3db */
/* cut edge: 0x180105ea9 -> 0x18014d3db */
/* cut edge: 0x180105ebf -> 0x18014d3db */
/* cut edge: 0x180105f25 -> 0x18010d0ba */
/* cut edge: 0x180105f4c -> 0x18010d0ed */
/* cut edge: 0x180105fad -> 0x18010d13e */
/* cut edge: 0x180105fbd -> 0x18010d177 */
/* cut edge: 0x18010609d -> 0x18014d3db */
/* cut edge: 0x18010609d -> 0x180106192 */
/* target 0x180106192 original byte 0x48 replaced by RET */
/* target 0x180109c15 original byte 0x48 replaced by RET */
/* target 0x18010d0ba original byte 0x48 replaced by RET */
/* target 0x18010d0ed original byte 0x48 replaced by RET */
/* target 0x18010d13e original byte 0x48 replaced by RET */
/* target 0x18010d177 original byte 0x48 replaced by RET */
/* target 0x18010f7f0 original byte 0x48 replaced by RET */
/* target 0x18010f823 original byte 0x48 replaced by RET */
/* target 0x18010f874 original byte 0x48 replaced by RET */
/* target 0x18010f8ad original byte 0x48 replaced by RET */
/* target 0x18010f8e4 original byte 0x48 replaced by RET */
/* target 0x18010f917 original byte 0x48 replaced by RET */
/* target 0x18010f965 original byte 0x48 replaced by RET */
/* target 0x18010f99f original byte 0x48 replaced by RET */
/* target 0x18010f9d7 original byte 0x48 replaced by RET */
/* target 0x18010fa0a original byte 0x48 replaced by RET */
/* target 0x18010fa5b original byte 0x48 replaced by RET */
/* target 0x18010fa94 original byte 0x48 replaced by RET */
/* target 0x18010facb original byte 0x48 replaced by RET */
/* target 0x18010fb01 original byte 0x48 replaced by RET */
/* target 0x18010fb52 original byte 0x48 replaced by RET */
/* target 0x18010fb8b original byte 0x48 replaced by RET */
/* target 0x180125436 original byte 0x4c replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00230_180105161(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong lVar9;
  undefined8 uVar10;
  ulonglong uVar11;
  ulonglong uVar12;
  longlong unaff_R13;
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
  undefined8 in_stack_000000e0;
  longlong in_stack_000000f8;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1a7b);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  lVar9 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 != '\0') {
    return;
  }
  uVar12 = lVar9 + unaff_R13;
  if (0 < (longlong)in_stack_000000c0) {
    if (in_stack_000000f8 == 0) {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar9 = DAT_1804b52e8;
    uVar6 = DAT_1804b5208;
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804ae720 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return;
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000262,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000010b8,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7);
      DAT_1804ae720 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804ae720,in_stack_000000b0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x471a);
    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       (longlong)uVar12 % 0xcbbbc ^ in_stack_000000c0 ^ 0xc77eba64cf2b7f4b);
    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar9 = DAT_1804b52e8;
    uVar6 = DAT_1804b5208;
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804ae728 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return;
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000260,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000010a8,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7);
      DAT_1804ae728 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804ae728,in_stack_000000b0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x521c);
    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       (longlong)uVar12 % 0xead43 ^ in_stack_000000c0 ^ 0x836639f1dfb92ef2);
    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar9 = DAT_1804b52e8;
    uVar6 = DAT_1804b5208;
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804ae730 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return;
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000025e,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001098,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7);
      DAT_1804ae730 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804ae730,in_stack_000000b0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4ad1);
    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       (longlong)uVar12 % 0xb939a ^ in_stack_000000c0 ^ 0xc19e2a4c21679ee5);
    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 != '\0') {
      return;
    }
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar9 = DAT_1804b52e8;
    uVar6 = DAT_1804b5208;
    if (cVar5 != '\0') {
      return;
    }
    if (DAT_1804ae738 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return;
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000025c,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001088,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7);
      DAT_1804ae738 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804ae738,in_stack_000000b0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3c49);
    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       (longlong)uVar12 % 0xa3772 ^ in_stack_000000c0 ^ 0xa1e23c2320ebc17e);
    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
    lVar9 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 != '\0') {
      return;
    }
    uVar12 = (uVar11 ^ uVar12) - lVar9;
    if ((longlong)in_stack_000000c0 < 0) {
      return;
    }
  }
  if (in_stack_000000f8 == 0) {
    return;
  }
  (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_000000e0);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) &&
     (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), lVar9 = DAT_1804b52e8,
     uVar6 = DAT_1804b5208, cVar5 == '\0')) {
    if (DAT_1804ae740 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b52e8 == 0) {
        return;
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000025a,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001078,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar10);
      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar9,uVar6,uVar7);
      DAT_1804ae740 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804ae740,in_stack_000000b0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2b4f);
    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       (longlong)uVar12 % 0x8216f ^ in_stack_000000c0 ^ 0x6841955b9ef463b8);
    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar5 == '\0') {
      return;
    }
  }
  return;
}

