/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=135 entry=18031fd9e blocks=44 maxInstructions=50000 */

/* cut edge: 0x18031fd9e -> 0x18032fae5 */
/* cut edge: 0x18031fdae -> 0x18032fb1e */
/* cut edge: 0x18031fe86 -> 0x180333186 */
/* cut edge: 0x18031ff7d -> 0x180333186 */
/* cut edge: 0x18031ff93 -> 0x180333186 */
/* cut edge: 0x18031fffe -> 0x18032fc4e */
/* cut edge: 0x180320027 -> 0x18032fc86 */
/* cut edge: 0x180320086 -> 0x18032fcd7 */
/* cut edge: 0x180320096 -> 0x18032fd10 */
/* cut edge: 0x180320171 -> 0x180333186 */
/* cut edge: 0x180320268 -> 0x180333186 */
/* cut edge: 0x18032027e -> 0x180333186 */
/* cut edge: 0x1803202e6 -> 0x18032fe3d */
/* cut edge: 0x180320312 -> 0x18032fe75 */
/* cut edge: 0x18032036e -> 0x18032fec6 */
/* cut edge: 0x18032037e -> 0x18032feff */
/* cut edge: 0x180320459 -> 0x180333186 */
/* cut edge: 0x18032054e -> 0x180333186 */
/* cut edge: 0x180320564 -> 0x180333186 */
/* cut edge: 0x1803205cc -> 0x18033003b */
/* cut edge: 0x1803205f8 -> 0x180330076 */
/* cut edge: 0x18032065c -> 0x1803300d0 */
/* cut edge: 0x18032066c -> 0x180330109 */
/* cut edge: 0x18032074b -> 0x180329b78 */
/* cut edge: 0x1803207b0 -> 0x180333186 */
/* cut edge: 0x1803207b8 -> 0x180333186 */
/* cut edge: 0x180320964 -> 0x180333186 */
/* cut edge: 0x180320a5e -> 0x180333186 */
/* cut edge: 0x180320a74 -> 0x180333186 */
/* cut edge: 0x180320c1d -> 0x180333186 */
/* cut edge: 0x180320d14 -> 0x180333186 */
/* cut edge: 0x180320d2a -> 0x180333186 */
/* cut edge: 0x180320d40 -> 0x180320ed3 */
/* cut edge: 0x180320d40 -> 0x180320d92 */
/* target 0x180320d92 original byte 0x4c replaced by RET */
/* target 0x180320ed3 original byte 0x48 replaced by RET */
/* target 0x180329b78 original byte 0xff replaced by RET */
/* target 0x18032fae5 original byte 0x48 replaced by RET */
/* target 0x18032fb1e original byte 0x48 replaced by RET */
/* target 0x18032fc4e original byte 0x48 replaced by RET */
/* target 0x18032fc86 original byte 0x48 replaced by RET */
/* target 0x18032fcd7 original byte 0x48 replaced by RET */
/* target 0x18032fd10 original byte 0x48 replaced by RET */
/* target 0x18032fe3d original byte 0x48 replaced by RET */
/* target 0x18032fe75 original byte 0x48 replaced by RET */
/* target 0x18032fec6 original byte 0x48 replaced by RET */
/* target 0x18032feff original byte 0x48 replaced by RET */
/* target 0x18033003b original byte 0x48 replaced by RET */
/* target 0x180330076 original byte 0x48 replaced by RET */
/* target 0x1803300d0 original byte 0x48 replaced by RET */
/* target 0x180330109 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00135_18031fd9e(void)

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
  longlong unaff_RBX;
  longlong *in_stack_00000040;
  undefined8 in_stack_00000048;
  code *in_stack_00000058;
  undefined8 in_stack_00000060;
  code *in_stack_00000068;
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
  undefined8 in_stack_000000c8;
  undefined8 in_stack_000000d0;
  
  if (DAT_1804b4d38 == 0) {
    return;
  }
  (**(code **)(unaff_RBX + 0x518))(in_stack_00000040,&stack0x00002188,5);
  if (DAT_1804b4d40 == 0) {
    return;
  }
  (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
  (*in_stack_00000058)(in_stack_00000040);
  uVar7 = (*in_stack_00000068)
                    (in_stack_00000040,in_stack_00000080,in_stack_00000060,in_stack_00000048,
                     in_stack_000000c8);
  DAT_1804b10f0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b10f0,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x67b3);
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
        if (DAT_1804b10f8 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000510,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00002178,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
          uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
          DAT_1804b10f8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b10f8,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1d6d);
        (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           in_stack_00000070 % 0x7b3f6 ^ in_stack_000000b0 ^ 0xd623360df4b2e16f);
        (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
          if (cVar6 == '\0') {
            cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
            lVar5 = DAT_1804b5a08;
            uVar7 = DAT_1804b59f0;
            if (cVar6 == '\0') {
              if (DAT_1804b1100 == 0) {
                pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x0000050e,1);
                lVar4 = DAT_1804b4d38;
                lVar3 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x00002168,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,in_stack_00000080);
                (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
                uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
                DAT_1804b1100 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b1100,in_stack_000000a0);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x71c0);
              (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 in_stack_00000070 % 0x4535a ^ in_stack_000000b0 ^
                                 0xa286085a0f2bae5f);
              (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
              cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar6 == '\0') {
                cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                if (cVar6 == '\0') {
                  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
                  lVar5 = DAT_1804b5a08;
                  uVar7 = DAT_1804b59f0;
                  if (cVar6 == '\0') {
                    if (DAT_1804b1108 == 0) {
                      pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b5a08 == 0) {
                        return;
                      }
                      uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                        (in_stack_00000040,&stack0x0000050c,1);
                      lVar4 = DAT_1804b4d38;
                      lVar3 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                        (in_stack_00000040,&stack0x00002158,5);
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar10 = (**(code **)(*in_stack_00000040 + 0x110))
                                         (in_stack_00000040,in_stack_00000080);
                      (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar10);
                      uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar5,uVar7,uVar8);
                      DAT_1804b1108 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
                    }
                    (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,DAT_1804b1108,in_stack_000000a0);
                    (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
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

