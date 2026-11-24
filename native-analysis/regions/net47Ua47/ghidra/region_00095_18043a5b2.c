/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=95 entry=18043a5b2 blocks=40 maxInstructions=50000 */

/* cut edge: 0x18043a5b2 -> 0x180473ae8 */
/* cut edge: 0x18043a5c8 -> 0x180473ae8 */
/* cut edge: 0x18043a630 -> 0x18043d4a7 */
/* cut edge: 0x18043a657 -> 0x18043d4dd */
/* cut edge: 0x18043a6b6 -> 0x18043d52e */
/* cut edge: 0x18043a6c6 -> 0x18043d568 */
/* cut edge: 0x18043a7a2 -> 0x180473ae8 */
/* cut edge: 0x18043a897 -> 0x180473ae8 */
/* cut edge: 0x18043a8ad -> 0x180473ae8 */
/* cut edge: 0x18043a918 -> 0x18043d59e */
/* cut edge: 0x18043a944 -> 0x18043d5d6 */
/* cut edge: 0x18043a9a3 -> 0x18043d627 */
/* cut edge: 0x18043a9b3 -> 0x18043d662 */
/* cut edge: 0x18043aa94 -> 0x180473ae8 */
/* cut edge: 0x18043ab89 -> 0x1803dd67b */
/* cut edge: 0x18043abc0 -> 0x180436b65 */
/* cut edge: 0x18043abca -> 0x180473ae8 */
/* cut edge: 0x18043abe0 -> 0x180473ae8 */
/* cut edge: 0x18043ad92 -> 0x180473ae8 */
/* cut edge: 0x18043ae90 -> 0x180473ae8 */
/* cut edge: 0x18043aea6 -> 0x180473ae8 */
/* cut edge: 0x18043b05c -> 0x180473ae8 */
/* cut edge: 0x18043b15a -> 0x180473ae8 */
/* cut edge: 0x18043b170 -> 0x180473ae8 */
/* cut edge: 0x18043b325 -> 0x180473ae8 */
/* cut edge: 0x18043b421 -> 0x180473ae8 */
/* cut edge: 0x18043b437 -> 0x180473ae8 */
/* cut edge: 0x18043b44d -> 0x18043b5fb */
/* cut edge: 0x18043b4a0 -> 0x18043b5fb */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x180436b65 original byte 0x48 replaced by RET */
/* target 0x18043b5fb original byte 0x48 replaced by RET */
/* target 0x18043d4a7 original byte 0x48 replaced by RET */
/* target 0x18043d4dd original byte 0x48 replaced by RET */
/* target 0x18043d52e original byte 0x48 replaced by RET */
/* target 0x18043d568 original byte 0x48 replaced by RET */
/* target 0x18043d59e original byte 0x48 replaced by RET */
/* target 0x18043d5d6 original byte 0x48 replaced by RET */
/* target 0x18043d627 original byte 0x48 replaced by RET */
/* target 0x18043d662 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00095_18043a5b2(void)

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
  longlong in_stack_00000058;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
    lVar5 = DAT_1804b5a08;
    uVar10 = DAT_1804b59f0;
    if (cVar6 == '\0') {
      if (DAT_1804b38d0 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000069a,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00002900,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar5,uVar10,uVar7);
        DAT_1804b38d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b38d0,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1c4c);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         in_stack_00000058 % 0x121c7 ^ in_stack_000000c8 ^ 0xe83ce1dc27d9ee1);
      (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          lVar5 = DAT_1804b5a08;
          uVar10 = DAT_1804b59f0;
          if (cVar6 == '\0') {
            if (DAT_1804b38d8 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000698,1);
              lVar4 = DAT_1804b4d38;
              lVar3 = DAT_1804b4d30;
              pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x000028f0,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar5,uVar10,uVar7);
              DAT_1804b38d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b38d8,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5a63);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_00000058 % 0x3a6c4 ^ in_stack_000000c8 ^ 0x18733695308d3ce2)
            ;
            (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_000000a8,uVar7);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              return;
            }
          }
        }
      }
    }
  }
  return;
}

