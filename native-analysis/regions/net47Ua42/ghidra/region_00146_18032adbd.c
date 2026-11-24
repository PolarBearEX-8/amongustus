/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=146 entry=18032adbd blocks=36 maxInstructions=50000 */

/* cut edge: 0x18032ae3d -> 0x180333186 */
/* cut edge: 0x18032af32 -> 0x180333186 */
/* cut edge: 0x18032af48 -> 0x180333186 */
/* cut edge: 0x18032afb3 -> 0x18033297a */
/* cut edge: 0x18032afdf -> 0x1803329b5 */
/* cut edge: 0x18032b043 -> 0x180332a0f */
/* cut edge: 0x18032b053 -> 0x180332a48 */
/* cut edge: 0x18032b132 -> 0x180333186 */
/* cut edge: 0x18032b227 -> 0x18029ad3c */
/* cut edge: 0x18032b25e -> 0x180333186 */
/* cut edge: 0x18032b274 -> 0x180333186 */
/* cut edge: 0x18032b41d -> 0x180333186 */
/* cut edge: 0x18032b514 -> 0x180333186 */
/* cut edge: 0x18032b52a -> 0x180333186 */
/* cut edge: 0x18032b6d8 -> 0x180333186 */
/* cut edge: 0x18032b7cf -> 0x180333186 */
/* cut edge: 0x18032b7e5 -> 0x180333186 */
/* cut edge: 0x18032b99c -> 0x180333186 */
/* cut edge: 0x18032ba91 -> 0x180333186 */
/* cut edge: 0x18032baa7 -> 0x180333186 */
/* cut edge: 0x18032bcc0 -> 0x180333186 */
/* cut edge: 0x18032bd57 -> 0x18029ad3c */
/* target 0x18029ad3c original byte 0x48 replaced by RET */
/* target 0x18033297a original byte 0x48 replaced by RET */
/* target 0x1803329b5 original byte 0x48 replaced by RET */
/* target 0x180332a0f original byte 0x48 replaced by RET */
/* target 0x180332a48 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00146_18032adbd(void)

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
  code *unaff_RBP;
  longlong *in_stack_00000040;
  code *in_stack_00000048;
  undefined8 in_stack_00000058;
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
  undefined8 in_stack_000000d0;
  code *in_stack_000000e0;
  
  (*unaff_RBP)(in_stack_00000040,in_stack_00000080);
  (*in_stack_000000e0)(in_stack_00000040);
  uVar7 = (*in_stack_00000048)(in_stack_00000040,in_stack_00000080);
  DAT_1804b1368 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b1368,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                    (in_stack_00000040,2,in_stack_00000090,0,in_stack_00000058);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x2d08);
  (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar7,in_stack_000000a8,uVar8)
  ;
  (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (cVar6 == '\0') {
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
      lVar5 = DAT_1804b5a08;
      uVar7 = DAT_1804b59f0;
      if (cVar6 == '\0') {
        if (DAT_1804b1370 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000472,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001c98,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
          DAT_1804b1370 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b1370,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4098);
        (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0x8f953 ^ in_stack_000000b0 ^ 0x2507bb555a3cff89);
        (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar6 == '\0') {
          return;
        }
      }
    }
  }
  return;
}

