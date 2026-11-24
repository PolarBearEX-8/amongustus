/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=17 entry=180030190 blocks=45 maxInstructions=100000 */

/* cut edge: 0x180030190 -> 0x18014d3db */
/* cut edge: 0x18003028a -> 0x18014d3db */
/* cut edge: 0x1800302a0 -> 0x18014d3db */
/* cut edge: 0x180030308 -> 0x180032f50 */
/* cut edge: 0x18003032c -> 0x180032f86 */
/* cut edge: 0x18003038b -> 0x180032fd8 */
/* cut edge: 0x18003039b -> 0x180033012 */
/* cut edge: 0x180030474 -> 0x18014d3db */
/* cut edge: 0x180030569 -> 0x18014d3db */
/* cut edge: 0x18003057f -> 0x18014d3db */
/* cut edge: 0x1800305e7 -> 0x180033048 */
/* cut edge: 0x180030610 -> 0x180033080 */
/* cut edge: 0x180030672 -> 0x1800330d2 */
/* cut edge: 0x180030682 -> 0x18003310c */
/* cut edge: 0x180030760 -> 0x18014d3db */
/* cut edge: 0x18003085c -> 0x18006386b */
/* cut edge: 0x180030885 -> 0x18014d3db */
/* cut edge: 0x1800309db -> 0x18014d3db */
/* cut edge: 0x180030add -> 0x18008ea32 */
/* cut edge: 0x180030aef -> 0x18002c3c7 */
/* cut edge: 0x180030b25 -> 0x18002c6f6 */
/* cut edge: 0x180030b5b -> 0x18014d3db */
/* cut edge: 0x180030cb4 -> 0x18014d3db */
/* cut edge: 0x180030db6 -> 0x180064477 */
/* cut edge: 0x180030dd2 -> 0x18014d3db */
/* cut edge: 0x180030f2b -> 0x18014d3db */
/* cut edge: 0x180031024 -> 0x180031533 */
/* cut edge: 0x180031031 -> 0x1800c1a06 */
/* cut edge: 0x180031043 -> 0x18002c497 */
/* cut edge: 0x18003107b -> 0x18002c4fb */
/* cut edge: 0x1800310b5 -> 0x18002c4fb */
/* cut edge: 0x1800310d5 -> 0x18002c50b */
/* cut edge: 0x18003110e -> 0x18002c574 */
/* target 0x18002c3c7 original byte 0x48 replaced by RET */
/* target 0x18002c497 original byte 0x48 replaced by RET */
/* target 0x18002c4fb original byte 0x48 replaced by RET */
/* target 0x18002c50b original byte 0x48 replaced by RET */
/* target 0x18002c574 original byte 0x48 replaced by RET */
/* target 0x18002c6f6 original byte 0x48 replaced by RET */
/* target 0x180031533 original byte 0x48 replaced by RET */
/* target 0x180032f50 original byte 0x48 replaced by RET */
/* target 0x180032f86 original byte 0x48 replaced by RET */
/* target 0x180032fd8 original byte 0x48 replaced by RET */
/* target 0x180033012 original byte 0x48 replaced by RET */
/* target 0x180033048 original byte 0x48 replaced by RET */
/* target 0x180033080 original byte 0x48 replaced by RET */
/* target 0x1800330d2 original byte 0x48 replaced by RET */
/* target 0x18003310c original byte 0x48 replaced by RET */
/* target 0x18006386b original byte 0x48 replaced by RET */
/* target 0x180064477 original byte 0x48 replaced by RET */
/* target 0x18008ea32 original byte 0x48 replaced by RET */
/* target 0x1800c1a06 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00017_180030190(undefined8 param_1,undefined8 param_2)

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
  longlong *in_stack_00000048;
  longlong in_stack_00000060;
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
  
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x281f);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar7,in_stack_000000a8,uVar8)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar5 = DAT_1804b52e8;
      uVar7 = DAT_1804b5208;
      if (cVar6 == '\0') {
        if (DAT_1804ae258 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000396,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000018f8,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar7,uVar8);
          DAT_1804ae258 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae258,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0xb8);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           in_stack_00000060 % 0x9b741 ^ in_stack_000000c0 ^ 0xf704443d2d78fb49);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            lVar5 = DAT_1804b52e8;
            uVar7 = DAT_1804b5208;
            if (cVar6 == '\0') {
              if (DAT_1804ae260 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000394,1);
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
                                  (in_stack_00000048,&stack0x000018e8,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000090);
                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar7,uVar8);
                DAT_1804ae260 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae260,in_stack_000000b0);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x31c2);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_00000060 % 0x13998 ^ in_stack_000000c0 ^
                                 0xcc16c63d473a0424);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                return;
              }
            }
          }
        }
      }
    }
  }
  return;
}

