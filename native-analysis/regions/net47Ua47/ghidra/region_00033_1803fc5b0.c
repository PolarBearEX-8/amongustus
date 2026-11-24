/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=33 entry=1803fc5b0 blocks=50 maxInstructions=50000 */

/* cut edge: 0x1803fc5b0 -> 0x180473ae8 */
/* cut edge: 0x1803fc5c6 -> 0x180473ae8 */
/* cut edge: 0x1803fc636 -> 0x1804045a0 */
/* cut edge: 0x1803fc65a -> 0x1804045d3 */
/* cut edge: 0x1803fc6be -> 0x180404624 */
/* cut edge: 0x1803fc6ce -> 0x18040465d */
/* cut edge: 0x1803fc7a9 -> 0x180473ae8 */
/* cut edge: 0x1803fc89e -> 0x180473ae8 */
/* cut edge: 0x1803fc8b4 -> 0x180473ae8 */
/* cut edge: 0x1803fc922 -> 0x18040478f */
/* cut edge: 0x1803fc950 -> 0x1804047c5 */
/* cut edge: 0x1803fc9af -> 0x180404816 */
/* cut edge: 0x1803fc9bf -> 0x18040484f */
/* cut edge: 0x1803fcaa2 -> 0x180473ae8 */
/* cut edge: 0x1803fcb97 -> 0x180411203 */
/* cut edge: 0x1803fcba9 -> 0x18041a2ed */
/* cut edge: 0x1803fcbc3 -> 0x18043d6d1 */
/* cut edge: 0x1803fcbe0 -> 0x180473ae8 */
/* cut edge: 0x1803fcbf6 -> 0x180473ae8 */
/* cut edge: 0x1803fcc72 -> 0x1803ffa0f */
/* cut edge: 0x1803fcc96 -> 0x1803ffa42 */
/* cut edge: 0x1803fccfa -> 0x1803ffa91 */
/* cut edge: 0x1803fcd12 -> 0x1803ffacb */
/* cut edge: 0x1803fcdf5 -> 0x180473ae8 */
/* cut edge: 0x1803fcef0 -> 0x180473ae8 */
/* cut edge: 0x1803fcf13 -> 0x180473ae8 */
/* cut edge: 0x1803fcf7c -> 0x180400688 */
/* cut edge: 0x1803fcfa0 -> 0x1804006bb */
/* cut edge: 0x1803fd004 -> 0x18040070a */
/* cut edge: 0x1803fd01c -> 0x180400744 */
/* cut edge: 0x1803fd10a -> 0x180473ae8 */
/* cut edge: 0x1803fd205 -> 0x180473ae8 */
/* cut edge: 0x1803fd252 -> 0x180473ae8 */
/* cut edge: 0x1803fd268 -> 0x180473ae8 */
/* cut edge: 0x1803fd2ce -> 0x1804018b8 */
/* cut edge: 0x1803fd2f2 -> 0x1804018eb */
/* cut edge: 0x1803fd356 -> 0x18040193a */
/* cut edge: 0x1803fd36e -> 0x180401974 */
/* cut edge: 0x1803fd451 -> 0x180473ae8 */
/* cut edge: 0x1803fd451 -> 0x1803fd552 */
/* target 0x1803fd552 original byte 0x48 replaced by RET */
/* target 0x1803ffa0f original byte 0x48 replaced by RET */
/* target 0x1803ffa42 original byte 0x48 replaced by RET */
/* target 0x1803ffa91 original byte 0x48 replaced by RET */
/* target 0x1803ffacb original byte 0x48 replaced by RET */
/* target 0x180400688 original byte 0x48 replaced by RET */
/* target 0x1804006bb original byte 0x48 replaced by RET */
/* target 0x18040070a original byte 0x48 replaced by RET */
/* target 0x180400744 original byte 0x48 replaced by RET */
/* target 0x1804018b8 original byte 0x48 replaced by RET */
/* target 0x1804018eb original byte 0x48 replaced by RET */
/* target 0x18040193a original byte 0x48 replaced by RET */
/* target 0x180401974 original byte 0x48 replaced by RET */
/* target 0x1804045a0 original byte 0x48 replaced by RET */
/* target 0x1804045d3 original byte 0x48 replaced by RET */
/* target 0x180404624 original byte 0x48 replaced by RET */
/* target 0x18040465d original byte 0x48 replaced by RET */
/* target 0x18040478f original byte 0x48 replaced by RET */
/* target 0x1804047c5 original byte 0x48 replaced by RET */
/* target 0x180404816 original byte 0x48 replaced by RET */
/* target 0x18040484f original byte 0x48 replaced by RET */
/* target 0x180411203 original byte 0x48 replaced by RET */
/* target 0x18041a2ed original byte 0x48 replaced by RET */
/* target 0x18043d6d1 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00033_1803fc5b0(void)

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
      if (DAT_1804b3ad8 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000618,1);
        lVar4 = DAT_1804b4d38;
        lVar3 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000024f0,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
        uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar5,uVar10,uVar7);
        DAT_1804b3ad8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b3ad8,in_stack_000000a0);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5bb4);
      (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         in_stack_00000058 % 0xea10e ^ in_stack_000000c8 ^ 0x7a832e9f939b2e4);
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
            if (DAT_1804b3ae0 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000616,1);
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
                                (in_stack_00000048,&stack0x000024e0,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar8,uVar9);
              uVar10 = (*pcVar1)(in_stack_00000048,in_stack_00000088,lVar5,uVar10,uVar7);
              DAT_1804b3ae0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b3ae0,in_stack_000000a0);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5eb6);
            (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_00000058 % 0xd5543 ^ in_stack_000000c8 ^ 0x5e0bb17c73b53783)
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

