/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=119 entry=18030fd97 blocks=52 maxInstructions=50000 */

/* cut edge: 0x18030fd97 -> 0x18032d39d */
/* cut edge: 0x18030fdbb -> 0x18032d3d3 */
/* cut edge: 0x18030fe1a -> 0x18032d424 */
/* cut edge: 0x18030fe2a -> 0x18032d45d */
/* cut edge: 0x18030ff02 -> 0x180333186 */
/* cut edge: 0x18030fff7 -> 0x180333186 */
/* cut edge: 0x18031000d -> 0x180333186 */
/* cut edge: 0x180310075 -> 0x18032d493 */
/* cut edge: 0x1803100a1 -> 0x18032d4ce */
/* cut edge: 0x180310105 -> 0x18032d528 */
/* cut edge: 0x180310115 -> 0x18032d561 */
/* cut edge: 0x1803101f4 -> 0x180300bf0 */
/* cut edge: 0x180310273 -> 0x180333186 */
/* cut edge: 0x180310289 -> 0x180333186 */
/* cut edge: 0x1803102f1 -> 0x18032d7ae */
/* cut edge: 0x18031031a -> 0x18032d7e6 */
/* cut edge: 0x180310376 -> 0x18032d837 */
/* cut edge: 0x180310386 -> 0x18032d870 */
/* cut edge: 0x18031045e -> 0x180333186 */
/* cut edge: 0x180310555 -> 0x180333186 */
/* cut edge: 0x18031056b -> 0x180333186 */
/* cut edge: 0x1803105d3 -> 0x18032d8a7 */
/* cut edge: 0x1803105fc -> 0x18032d8df */
/* cut edge: 0x18031065b -> 0x18032d930 */
/* cut edge: 0x18031066b -> 0x18032d969 */
/* cut edge: 0x180310746 -> 0x180333186 */
/* cut edge: 0x18031083d -> 0x180333186 */
/* cut edge: 0x180310853 -> 0x180333186 */
/* cut edge: 0x1803108bb -> 0x18032d9a0 */
/* cut edge: 0x1803108e7 -> 0x18032d9d8 */
/* cut edge: 0x180310943 -> 0x18032da29 */
/* cut edge: 0x180310953 -> 0x18032da62 */
/* cut edge: 0x180310a2e -> 0x180333186 */
/* cut edge: 0x180310b23 -> 0x180333186 */
/* cut edge: 0x180310b39 -> 0x180333186 */
/* cut edge: 0x180310ba1 -> 0x18032da99 */
/* cut edge: 0x180310bcd -> 0x18032dad4 */
/* cut edge: 0x180310c31 -> 0x18032db2e */
/* cut edge: 0x180310c41 -> 0x18032db67 */
/* cut edge: 0x180310d21 -> 0x180333186 */
/* cut edge: 0x180310d21 -> 0x180310e16 */
/* target 0x180300bf0 original byte 0xff replaced by RET */
/* target 0x180310e16 original byte 0x48 replaced by RET */
/* target 0x18032d39d original byte 0x48 replaced by RET */
/* target 0x18032d3d3 original byte 0x48 replaced by RET */
/* target 0x18032d424 original byte 0x48 replaced by RET */
/* target 0x18032d45d original byte 0x48 replaced by RET */
/* target 0x18032d493 original byte 0x48 replaced by RET */
/* target 0x18032d4ce original byte 0x48 replaced by RET */
/* target 0x18032d528 original byte 0x48 replaced by RET */
/* target 0x18032d561 original byte 0x48 replaced by RET */
/* target 0x18032d7ae original byte 0x48 replaced by RET */
/* target 0x18032d7e6 original byte 0x48 replaced by RET */
/* target 0x18032d837 original byte 0x48 replaced by RET */
/* target 0x18032d870 original byte 0x48 replaced by RET */
/* target 0x18032d8a7 original byte 0x48 replaced by RET */
/* target 0x18032d8df original byte 0x48 replaced by RET */
/* target 0x18032d930 original byte 0x48 replaced by RET */
/* target 0x18032d969 original byte 0x48 replaced by RET */
/* target 0x18032d9a0 original byte 0x48 replaced by RET */
/* target 0x18032d9d8 original byte 0x48 replaced by RET */
/* target 0x18032da29 original byte 0x48 replaced by RET */
/* target 0x18032da62 original byte 0x48 replaced by RET */
/* target 0x18032da99 original byte 0x48 replaced by RET */
/* target 0x18032dad4 original byte 0x48 replaced by RET */
/* target 0x18032db2e original byte 0x48 replaced by RET */
/* target 0x18032db67 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00119_18030fd97(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong *in_stack_00000040;
  code *pcStack0000000000000048;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  longlong lStack00000000000000c8;
  undefined8 in_stack_000000d0;
  
  lVar4 = DAT_1804b5a08;
  pcStack0000000000000048 = *(code **)(*in_stack_00000040 + 0x390);
  if (DAT_1804b5a08 == 0) {
    return;
  }
  lStack00000000000000c8 = DAT_1804b59f0;
  uVar6 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000494,1);
  lVar3 = DAT_1804b4d38;
  pcVar2 = DAT_1804b4d30;
  pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
  if (DAT_1804b4d30 == (code *)0x0) {
    return;
  }
  if (DAT_1804b4d38 == 0) {
    return;
  }
  uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001da8,5);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  uVar8 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
  (*pcVar1)(in_stack_00000040,pcVar2,lVar3,uVar7,uVar8);
  uVar6 = (*pcStack0000000000000048)
                    (in_stack_00000040,in_stack_00000080,lVar4,lStack00000000000000c8,uVar6);
  DAT_1804b12e8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b12e8,in_stack_000000a0);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x3a1e);
  (*pcVar1)(in_stack_00000040,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar6,in_stack_000000a8,uVar7)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar6,in_stack_000000d0);
  cVar5 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000040 + 0x720))();
      lVar4 = DAT_1804b59f0;
      if (cVar5 == '\0') {
        if (DAT_1804b12f0 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          lStack00000000000000c8 = DAT_1804b5a08;
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar6 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000492,1);
          lVar3 = DAT_1804b4d38;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          pcStack0000000000000048 = DAT_1804b4d30;
          if (DAT_1804b4d30 == (code *)0x0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar7 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001d98,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,pcStack0000000000000048,lVar3,uVar7,uVar8);
          uVar6 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lStack00000000000000c8,lVar4,uVar6);
          DAT_1804b12f0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar6);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar6);
        }
        (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,DAT_1804b12f0,in_stack_000000a0)
        ;
        (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        return;
      }
    }
  }
  return;
}

