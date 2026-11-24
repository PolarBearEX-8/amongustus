/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=50 entry=18005113f blocks=54 maxInstructions=100000 */

/* cut edge: 0x1800511c7 -> 0x18014d3db */
/* cut edge: 0x1800512bc -> 0x18014d3db */
/* cut edge: 0x1800512d2 -> 0x18014d3db */
/* cut edge: 0x18005133b -> 0x1800533b6 */
/* cut edge: 0x180051362 -> 0x1800533e9 */
/* cut edge: 0x1800513c0 -> 0x180053437 */
/* cut edge: 0x1800513db -> 0x180053470 */
/* cut edge: 0x1800514c0 -> 0x18014d3db */
/* cut edge: 0x1800515b5 -> 0x18014d3db */
/* cut edge: 0x1800515cb -> 0x18014d3db */
/* cut edge: 0x180051631 -> 0x18005368e */
/* cut edge: 0x18005165d -> 0x1800536c1 */
/* cut edge: 0x1800516bb -> 0x180053712 */
/* cut edge: 0x1800516ce -> 0x18005374b */
/* cut edge: 0x1800517ae -> 0x18014d3db */
/* cut edge: 0x1800518a6 -> 0x18004c5c7 */
/* cut edge: 0x1800518b5 -> 0x180125467 */
/* cut edge: 0x1800518ba -> 0x18014d3db */
/* cut edge: 0x1800518d0 -> 0x18014d3db */
/* cut edge: 0x18005193b -> 0x180053b54 */
/* cut edge: 0x180051964 -> 0x180053b8c */
/* cut edge: 0x1800519c0 -> 0x180053bdd */
/* cut edge: 0x1800519d0 -> 0x180053c16 */
/* cut edge: 0x180051ab1 -> 0x18014d3db */
/* cut edge: 0x180051ba6 -> 0x18014d3db */
/* cut edge: 0x180051bbc -> 0x18014d3db */
/* cut edge: 0x180051c2a -> 0x180053c4e */
/* cut edge: 0x180051c56 -> 0x180053c89 */
/* cut edge: 0x180051cb7 -> 0x180053ce3 */
/* cut edge: 0x180051cc7 -> 0x180053d1c */
/* cut edge: 0x180051da4 -> 0x18014d3db */
/* cut edge: 0x180051e99 -> 0x18014d3db */
/* cut edge: 0x180051eaf -> 0x18014d3db */
/* cut edge: 0x180051f1a -> 0x180053d53 */
/* cut edge: 0x180051f43 -> 0x180053d8b */
/* cut edge: 0x180051f9f -> 0x180053ddc */
/* cut edge: 0x180051fb7 -> 0x180053e15 */
/* cut edge: 0x180052098 -> 0x18014d3db */
/* cut edge: 0x180052098 -> 0x18005218d */
/* target 0x18004c5c7 original byte 0x48 replaced by RET */
/* target 0x18005218d original byte 0x48 replaced by RET */
/* target 0x1800533b6 original byte 0x48 replaced by RET */
/* target 0x1800533e9 original byte 0x48 replaced by RET */
/* target 0x180053437 original byte 0x48 replaced by RET */
/* target 0x180053470 original byte 0x48 replaced by RET */
/* target 0x18005368e original byte 0x48 replaced by RET */
/* target 0x1800536c1 original byte 0x48 replaced by RET */
/* target 0x180053712 original byte 0x48 replaced by RET */
/* target 0x18005374b original byte 0x48 replaced by RET */
/* target 0x180053b54 original byte 0x48 replaced by RET */
/* target 0x180053b8c original byte 0x48 replaced by RET */
/* target 0x180053bdd original byte 0x48 replaced by RET */
/* target 0x180053c16 original byte 0x48 replaced by RET */
/* target 0x180053c4e original byte 0x48 replaced by RET */
/* target 0x180053c89 original byte 0x48 replaced by RET */
/* target 0x180053ce3 original byte 0x48 replaced by RET */
/* target 0x180053d1c original byte 0x48 replaced by RET */
/* target 0x180053d53 original byte 0x48 replaced by RET */
/* target 0x180053d8b original byte 0x48 replaced by RET */
/* target 0x180053ddc original byte 0x48 replaced by RET */
/* target 0x180053e15 original byte 0x48 replaced by RET */
/* target 0x180125467 original byte 0x4c replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00050_18005113f(void)

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
  longlong unaff_RBP;
  ulonglong uVar11;
  code *unaff_R15;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  code *in_stack_00000070;
  longlong in_stack_00000078;
  code *in_stack_00000080;
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
  
  (*unaff_R15)(in_stack_00000048,in_stack_00000090);
  (*in_stack_00000070)(in_stack_00000048,in_stack_000000e0);
  uVar7 = (*in_stack_00000080)
                    (in_stack_00000048,in_stack_00000090,in_stack_00000058,in_stack_00000068,
                     in_stack_00000060);
  DAT_1804ae1d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804ae1d0,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1303);
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
        uVar11 = unaff_RBP % 0xf1d19;
        if (DAT_1804ae1d8 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000003b6,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000019f8,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar7,uVar8);
          DAT_1804ae1d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
          unaff_RBP = in_stack_00000078;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ae1d8,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2b2b);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           uVar11 ^ in_stack_000000c0 ^ 0xc4d77c5d727b1241);
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
              if (DAT_1804ae1e0 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x000003b4,1);
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
                                  (in_stack_00000048,&stack0x000019e8,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000090);
                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar7,uVar8);
                DAT_1804ae1e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae1e0,in_stack_000000b0);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4e7a);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 unaff_RBP % 0x29bf0 ^ in_stack_000000c0 ^ 0xbeecc01a3f2e3af1);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar7,in_stack_000000d8);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                if (in_stack_000000f8 == 0) {
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
  return;
}

