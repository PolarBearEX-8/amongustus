/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=129 entry=18045c56f blocks=40 maxInstructions=50000 */

/* cut edge: 0x18045c6e7 -> 0x180473ae8 */
/* cut edge: 0x18045c85e -> 0x180473ae8 */
/* cut edge: 0x18045c874 -> 0x180473ae8 */
/* cut edge: 0x18045ca3e -> 0x180473ae8 */
/* cut edge: 0x18045cb33 -> 0x180473ae8 */
/* cut edge: 0x18045cb49 -> 0x180473ae8 */
/* cut edge: 0x18045cd22 -> 0x180473ae8 */
/* cut edge: 0x18045ce3f -> 0x180473ae8 */
/* cut edge: 0x18045ce55 -> 0x180473ae8 */
/* cut edge: 0x18045ce6b -> 0x180473c5a */
/* cut edge: 0x18045cea7 -> 0x180473cbd */
/* cut edge: 0x18045ceb7 -> 0x180473ae8 */
/* cut edge: 0x18045d079 -> 0x180473ae8 */
/* cut edge: 0x18045d16f -> 0x180473ae8 */
/* cut edge: 0x18045d1c0 -> 0x180473ae8 */
/* cut edge: 0x18045d1d6 -> 0x180473ae8 */
/* cut edge: 0x18045d23e -> 0x18045e0bd */
/* cut edge: 0x18045d26d -> 0x18045e0f8 */
/* cut edge: 0x18045d2c9 -> 0x18045e149 */
/* cut edge: 0x18045d2d9 -> 0x18045e182 */
/* cut edge: 0x18045d3b7 -> 0x180473ae8 */
/* cut edge: 0x18045d4ab -> 0x18045e0b0 */
/* cut edge: 0x18045d4b5 -> 0x180473ae8 */
/* cut edge: 0x18045d4cb -> 0x180473ae8 */
/* cut edge: 0x18045d4e1 -> 0x18045d6ac */
/* cut edge: 0x18045d533 -> 0x18045e1b9 */
/* cut edge: 0x18045d533 -> 0x18045d55a */
/* target 0x18045d55a original byte 0x48 replaced by RET */
/* target 0x18045d6ac original byte 0x48 replaced by RET */
/* target 0x18045e0b0 original byte 0x4c replaced by RET */
/* target 0x18045e0bd original byte 0x48 replaced by RET */
/* target 0x18045e0f8 original byte 0x48 replaced by RET */
/* target 0x18045e149 original byte 0x48 replaced by RET */
/* target 0x18045e182 original byte 0x48 replaced by RET */
/* target 0x18045e1b9 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x180473c5a original byte 0x48 replaced by RET */
/* target 0x180473cbd original byte 0x48 replaced by RET */


code * region_00129_18045c56f(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  undefined4 uVar4;
  int iVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  code *pcVar13;
  code *pcVar14;
  code *pcVar15;
  undefined4 unaff_EBX;
  longlong *in_stack_00000048;
  int iStack0000000000000058;
  undefined4 uStack000000000000005c;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_00000110;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00000740;
  undefined8 in_stack_00000768;
  undefined8 in_stack_00002fa8;
  
  lVar1 = *in_stack_00000048;
  _iStack0000000000000058 = *(code **)(lVar1 + 0x390);
  uVar6 = FUN_180333500(in_stack_00000048);
  uVar12 = DAT_1804b59f0;
  uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000366,1);
  lVar1 = *in_stack_00000048;
  pcVar13 = *(code **)(lVar1 + 0x390);
  uVar8 = FUN_1800024f0(in_stack_00000048);
  uVar9 = FUN_180002550(in_stack_00000048);
  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003240,0x19);
  pcVar14 = *(code **)(*in_stack_00000048 + 0x110);
  uVar11 = FUN_1800025a0(in_stack_00000048);
  uVar11 = (*pcVar14)(in_stack_00000048,in_stack_00000088,uVar11);
  (*pcVar13)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
  uVar12 = (*_iStack0000000000000058)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
  DAT_1804b45a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b45a0,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000090,0);
  (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,0,in_stack_00000110);
  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000768,in_stack_00000740,unaff_EBX);
  (*pcVar13)(in_stack_00000048,uVar6,1,uVar7);
  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002fa8);
  (*pcVar13)(in_stack_00000048,uVar6,2,uVar7);
  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar13)(in_stack_00000048,uVar6,3,uVar7);
  pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar13)(in_stack_00000048,uVar6,4,uVar7);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
  uVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000120);
  pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((((char)pcVar13 == '\0') &&
      (pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar13 == '\0')) &&
     (pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar13 == '\0')) {
    if (DAT_1804b45a8 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar13 = *(code **)(lVar1 + 0x390);
      uVar6 = FUN_180333550(in_stack_00000048);
      uVar12 = DAT_1804b59f0;
      uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000364,1);
      lVar1 = *in_stack_00000048;
      _iStack0000000000000058 = (code *)CONCAT44(uStack000000000000005c,uVar4);
      pcVar14 = *(code **)(lVar1 + 0x390);
      uVar8 = FUN_1800024f0(in_stack_00000048);
      uVar9 = FUN_180002550(in_stack_00000048);
      uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001070,5);
      pcVar15 = *(code **)(*in_stack_00000048 + 0x110);
      uVar11 = FUN_1800025a0(in_stack_00000048);
      uVar11 = (*pcVar15)(in_stack_00000048,in_stack_00000088,uVar11);
      (*pcVar14)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
      uVar12 = (*pcVar13)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
      DAT_1804b45a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
    }
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b45a8,in_stack_000000a0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x79b1);
    (*pcVar13)(in_stack_00000048,uVar6,0,uVar7);
    pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       in_stack_00000068 % 0xa2842 ^ in_stack_000000c8 ^ 0xa14de9b68c17a45a);
    (*pcVar13)(in_stack_00000048,uVar6,1,uVar7);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
    (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000120);
    pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((((char)pcVar13 == '\0') &&
        (pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar13 == '\0')) &&
       (pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar13 == '\0')) {
      if (DAT_1804b45b0 == 0) {
        lVar1 = *in_stack_00000048;
        _iStack0000000000000058 = *(code **)(lVar1 + 0x390);
        uVar6 = FUN_180333550(in_stack_00000048);
        uVar12 = DAT_1804b59f0;
        uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000362,1);
        lVar1 = *in_stack_00000048;
        pcVar13 = *(code **)(lVar1 + 0x390);
        uVar8 = FUN_1800024f0(in_stack_00000048);
        uVar9 = FUN_180002550(in_stack_00000048);
        uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001060,5);
        pcVar14 = *(code **)(*in_stack_00000048 + 0x110);
        uVar11 = FUN_1800025a0(in_stack_00000048);
        uVar11 = (*pcVar14)(in_stack_00000048,in_stack_00000088,uVar11);
        (*pcVar13)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
        uVar12 = (*_iStack0000000000000058)(in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
        DAT_1804b45b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
      }
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b45b0,in_stack_000000a0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1193);
      (*pcVar13)(in_stack_00000048,uVar6,0,uVar7);
      pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         in_stack_00000068 % 0x7d3c3 ^ in_stack_000000c8 ^ 0xc715bf7f94ec2bec);
      (*pcVar13)(in_stack_00000048,uVar6,1,uVar7);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
      (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar12,in_stack_00000120);
      pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((((char)pcVar13 == '\0') &&
          (pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar13 == '\0')) &&
         (pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar13 == '\0')) {
        if (DAT_1804b5290 == 0) {
          return (code *)0x0;
        }
        if (DAT_1804b5400 == 0) {
          return (code *)0x0;
        }
        (**(code **)(*in_stack_00000048 + 0x420))(in_stack_00000048,DAT_1804b5290,DAT_1804b5400);
        pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar13 == '\0') {
          if (DAT_1804b45b8 == 0) {
            lVar1 = *in_stack_00000048;
            _iStack0000000000000058 = *(code **)(lVar1 + 0x390);
            uVar6 = FUN_180333550(in_stack_00000048);
            uVar12 = DAT_1804b59f0;
            uVar7 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000360,1);
            lVar1 = *in_stack_00000048;
            pcVar13 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_1800024f0(in_stack_00000048);
            uVar9 = FUN_180002550(in_stack_00000048);
            uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001050,5);
            pcVar14 = *(code **)(*in_stack_00000048 + 0x110);
            uVar11 = FUN_1800025a0(in_stack_00000048);
            uVar11 = (*pcVar14)(in_stack_00000048,in_stack_00000088,uVar11);
            (*pcVar13)(in_stack_00000048,uVar8,uVar9,uVar10,uVar11);
            uVar12 = (*_iStack0000000000000058)
                               (in_stack_00000048,in_stack_00000088,uVar6,uVar12,uVar7);
            DAT_1804b45b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
          }
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b45b8,in_stack_000000a0);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2a98);
          (*pcVar13)(in_stack_00000048,uVar6,0,uVar7);
          pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                             in_stack_00000068 % 0x8a00f ^ in_stack_000000c8 ^ 0x840c35f4f8815c61);
          (*pcVar13)(in_stack_00000048,uVar6,1,uVar7);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
          uVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar12,in_stack_00000120);
          _iStack0000000000000058 = (code *)CONCAT44(uStack000000000000005c,uVar4);
          pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((((char)pcVar13 == '\0') &&
              (pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar13 == '\0')
              ) && (pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(),
                   lVar1 = DAT_1804b59f8, uVar12 = DAT_1804b59f0, (char)pcVar13 == '\0')) {
            if (DAT_1804b45c0 == 0) {
              pcVar13 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59f8 == 0) {
                return (code *)0x0;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x0000035e,1);
              lVar3 = DAT_1804b4d38;
              lVar2 = DAT_1804b4d30;
              pcVar14 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar14;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar14;
              }
              pcVar15 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                          (in_stack_00000048,&stack0x00001040,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar15;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar14)(in_stack_00000048,lVar2,lVar3,pcVar15,uVar7);
              uVar12 = (*pcVar13)(in_stack_00000048,in_stack_00000088,lVar1,uVar12,uVar6);
              DAT_1804b45c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar12);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar12);
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b45c0,in_stack_000000a0);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x9c2);
            (*pcVar13)(in_stack_00000048,uVar6,0,uVar7);
            pcVar13 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               in_stack_00000068 % 0xa8af2 ^ in_stack_000000c8 ^ 0x9832b1ea6720f286)
            ;
            (*pcVar13)(in_stack_00000048,uVar6,1,uVar7);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar12,in_stack_000000a8,uVar6);
            iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar12,in_stack_00000120);
            pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar13 == '\0') {
              if (iVar5 <= iStack0000000000000058) {
                return pcVar13;
              }
              pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
              if (((char)pcVar13 == '\0') &&
                 (pcVar13 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(),
                 (char)pcVar13 == '\0')) {
                if (DAT_1804b45c8 != 0) {
                  return (code *)0xac96b1bd66e493a9;
                }
                if (DAT_1804b5a08 == 0) {
                  return *(code **)(*in_stack_00000048 + 0x390);
                }
                return *(code **)(*in_stack_00000048 + 0x390);
              }
            }
          }
        }
      }
    }
  }
  return pcVar13;
}

