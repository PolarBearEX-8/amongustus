/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=14 entry=1803418b2 blocks=45 maxInstructions=50000 */

/* cut edge: 0x1803418b2 -> 0x1803351d6 */
/* cut edge: 0x1803418f8 -> 0x1803351d6 */
/* cut edge: 0x18034193d -> 0x180346019 */
/* cut edge: 0x18034195c -> 0x1803351d6 */
/* cut edge: 0x18034198c -> 0x1803441a5 */
/* cut edge: 0x1803419a6 -> 0x1803441a5 */
/* cut edge: 0x1803419b4 -> 0x1803351d6 */
/* cut edge: 0x180341b4f -> 0x1803351d6 */
/* cut edge: 0x180341d09 -> 0x1803351d6 */
/* cut edge: 0x180341d44 -> 0x1803351d6 */
/* cut edge: 0x180341d90 -> 0x1803351d6 */
/* cut edge: 0x180341dbe -> 0x1803351d6 */
/* cut edge: 0x180341e0c -> 0x180344dc6 */
/* cut edge: 0x180341e25 -> 0x1803486bf */
/* cut edge: 0x180341e30 -> 0x1803351d6 */
/* cut edge: 0x180341e62 -> 0x1803351d6 */
/* cut edge: 0x180341fca -> 0x1803351d6 */
/* cut edge: 0x1803420c9 -> 0x1803351d6 */
/* cut edge: 0x180342231 -> 0x1803351d6 */
/* cut edge: 0x180342337 -> 0x1803351d6 */
/* cut edge: 0x1803424d0 -> 0x1803351d6 */
/* cut edge: 0x1803425bb -> 0x1803351d6 */
/* cut edge: 0x1803425f0 -> 0x1803351d6 */
/* cut edge: 0x18034264b -> 0x180344d96 */
/* cut edge: 0x180342670 -> 0x1803351d6 */
/* cut edge: 0x1803426d3 -> 0x1803351d6 */
/* cut edge: 0x1803426e9 -> 0x180342881 */
/* cut edge: 0x1803426f9 -> 0x180343aca */
/* cut edge: 0x180342728 -> 0x180343b00 */
/* cut edge: 0x18034278a -> 0x180343b51 */
/* cut edge: 0x18034279a -> 0x180343b8b */
/* cut edge: 0x1803427f3 -> 0x180342881 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180342881 original byte 0x48 replaced by RET */
/* target 0x180343aca original byte 0x48 replaced by RET */
/* target 0x180343b00 original byte 0x48 replaced by RET */
/* target 0x180343b51 original byte 0x48 replaced by RET */
/* target 0x180343b8b original byte 0x48 replaced by RET */
/* target 0x1803441a5 original byte 0x48 replaced by RET */
/* target 0x180344d96 original byte 0x8b replaced by RET */
/* target 0x180344dc6 original byte 0x48 replaced by RET */
/* target 0x180346019 original byte 0x48 replaced by RET */
/* target 0x1803486bf original byte 0x48 replaced by RET */


void region_00014_1803418b2(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  ulonglong uVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  int iVar13;
  ulonglong uVar14;
  ulonglong unaff_R14;
  undefined8 *puVar15;
  longlong *in_stack_00000048;
  int iStack0000000000000050;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  ulonglong in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  longlong in_stack_000000c8;
  ulonglong in_stack_000000d0;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_000000e8;
  int in_stack_00000120;
  undefined8 in_stack_00000140;
  longlong in_stack_00000150;
  
  puVar15 = (undefined8 *)&stack0x00000050;
  (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048);
  in_stack_00000120 = iStack0000000000000050;
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048);
    in_stack_000000d0 = _iStack0000000000000050 & 0xffffffff;
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar6 == '\0') {
      if (DAT_1804b5a88 == 0) {
        return;
      }
      in_stack_000000a0 =
           (**(code **)(*in_stack_00000048 + 0x560))
                     (in_stack_00000048,in_stack_00000120,DAT_1804b5a88,0,puVar15);
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        in_stack_000000a8 = in_stack_000000a8 & unaff_R14;
        if ((in_stack_00000150 == 0) || (in_stack_00000120 < 1)) {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        if (cVar6 == '\0') {
          if (DAT_1804b1f90 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            in_stack_000000b8 = FUN_180333500(in_stack_00000048);
            in_stack_000000c0 = DAT_1804b59f0;
            in_stack_00000140 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000080a,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar7 = FUN_1800024f0(in_stack_00000048);
            uVar8 = FUN_180002550(in_stack_00000048);
            uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00002f20,0x1b);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar10 = FUN_1800025a0(in_stack_00000048);
            uVar11 = in_stack_00000058;
            uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar10);
            (*pcVar3)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
            uVar11 = (*pcVar2)(in_stack_00000048,uVar11,in_stack_000000b8,in_stack_000000c0,
                               in_stack_00000140);
            DAT_1804b1f90 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          _iStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b1f90,in_stack_00000078);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,6,in_stack_00000088,0);
          uVar7 = in_stack_00000068;
          uVar11 = in_stack_00000060;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,in_stack_000000e0
                            );
          (*pcVar2)(in_stack_00000048,uVar10,0,uVar8);
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar10,1,in_stack_000000e8);
          uVar9 = in_stack_00000098;
          uVar8 = in_stack_00000090;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000b0);
          (*pcVar2)(in_stack_00000048,uVar10,2,uVar12);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,uVar7,uVar11,in_stack_000000d0);
          (*pcVar2)(in_stack_00000048,uVar10,3,uVar11);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,uVar9,uVar8,0xdfc06d1e310f3299);
          (*pcVar2)(in_stack_00000048,uVar10,4,uVar11);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,uVar9,uVar8,in_stack_000000c8);
          (*pcVar2)(in_stack_00000048,uVar10,5,uVar11);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,_iStack0000000000000050,in_stack_00000080,uVar10);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 == '\0') {
            (**(code **)(*in_stack_00000048 + 0x658))(in_stack_00000048,uVar11,0,1,&stack0x00000050)
            ;
            uVar5 = _iStack0000000000000050;
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              (**(code **)(*in_stack_00000048 + 0x658))
                        (in_stack_00000048,uVar11,1,1,&stack0x00000050);
              uVar14 = _iStack0000000000000050 & 0xffffffff;
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                in_stack_000000a8 = in_stack_000000a8 | uVar14;
                iVar13 = 0;
                while( true ) {
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x598))
                                     (in_stack_00000048,uVar5 & 0xffffffff);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 != '\0') break;
                  (**(code **)(*in_stack_00000048 + 0x570))
                            (in_stack_00000048,in_stack_000000a0,iVar13,uVar11);
                  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if (cVar6 != '\0') {
                    return;
                  }
                  iVar13 = iVar13 + 1;
                  if (in_stack_00000120 <= iVar13) {
                    if (in_stack_000000c8 < 1) {
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
    }
  }
  return;
}

