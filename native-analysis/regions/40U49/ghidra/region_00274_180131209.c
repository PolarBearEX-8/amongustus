/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=274 entry=180131209 blocks=33 maxInstructions=100000 */

/* cut edge: 0x180131209 -> 0x18014d3db */
/* cut edge: 0x18013121f -> 0x18014d3db */
/* cut edge: 0x1801313c8 -> 0x18014d3db */
/* cut edge: 0x1801314c2 -> 0x18014d3db */
/* cut edge: 0x1801314d8 -> 0x18014d3db */
/* cut edge: 0x180131686 -> 0x18014d3db */
/* cut edge: 0x18013177d -> 0x18014d3db */
/* cut edge: 0x180131793 -> 0x18014d3db */
/* cut edge: 0x180131941 -> 0x18014d3db */
/* cut edge: 0x180131a36 -> 0x18014d3db */
/* cut edge: 0x180131a4c -> 0x18014d3db */
/* cut edge: 0x180131c09 -> 0x18014d3db */
/* cut edge: 0x180131d05 -> 0x18013cef0 */
/* cut edge: 0x180131d39 -> 0x18014d3db */
/* cut edge: 0x180131d54 -> 0x18014d3db */
/* cut edge: 0x180131d6a -> 0x18014d3db */
/* cut edge: 0x180131f29 -> 0x18014d3db */
/* cut edge: 0x18013200d -> 0x18013dbb1 */
/* cut edge: 0x18013202c -> 0x18014d3db */
/* cut edge: 0x180132055 -> 0x18014d3db */
/* cut edge: 0x18013206b -> 0x18014d3db */
/* cut edge: 0x180132081 -> 0x180132214 */
/* cut edge: 0x1801320d3 -> 0x180132214 */
/* target 0x180132214 original byte 0x48 replaced by RET */
/* target 0x18013cef0 original byte 0x48 replaced by RET */
/* target 0x18013dbb1 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


longlong * region_00274_180131209(void)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong *plVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  ulonglong uVar12;
  longlong lVar13;
  longlong *plVar14;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
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
  longlong in_stack_000000f8;
  
  plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
  if ((char)plVar4 == '\0') {
    plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
    if ((char)plVar4 == '\0') {
      if (DAT_1804ac9e8 == 0) {
        lVar13 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar13 + 0x390);
        uVar5 = FUN_18014d730(in_stack_00000048);
        uVar11 = DAT_1804b5208;
        uVar6 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000009c6,1);
        lVar13 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar13 + 0x390);
        uVar7 = FUN_1800024f0(in_stack_00000048);
        uVar8 = FUN_180002550(in_stack_00000048);
        uVar9 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000041f8,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar10 = FUN_1800025a0(in_stack_00000048);
        uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar10);
        (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
        uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6);
        DAT_1804ac9e8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
      }
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804ac9e8,in_stack_000000b0);
      uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3111);
      (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         (longlong)in_stack_00000058 % 0x61a27 ^ in_stack_000000c0 ^
                         0x241c98e655d94c32);
      (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
      plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)plVar4 == '\0') {
        plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)plVar4 == '\0') {
          plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
          if ((char)plVar4 == '\0') {
            if (DAT_1804ac9f0 == 0) {
              lVar13 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar13 + 0x390);
              uVar5 = FUN_18014d730(in_stack_00000048);
              uVar11 = DAT_1804b5208;
              uVar6 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000009c4,1);
              lVar13 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar13 + 0x390);
              uVar7 = FUN_1800024f0(in_stack_00000048);
              uVar8 = FUN_180002550(in_stack_00000048);
              uVar9 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000041e8,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar10 = FUN_1800025a0(in_stack_00000048);
              uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar10);
              (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
              uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6);
              DAT_1804ac9f0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
            }
            uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804ac9f0,in_stack_000000b0);
            uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_000000a0,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x979);
            (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                               (longlong)in_stack_00000058 % 0x3aca6 ^ in_stack_000000c0 ^
                               0x23ef5226e0b8cc96);
            (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
            uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
            plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)plVar4 == '\0') {
              plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)plVar4 == '\0') {
                plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)plVar4 == '\0') {
                  if (DAT_1804ac9f8 == 0) {
                    lVar13 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar13 + 0x390);
                    uVar5 = FUN_18014d730(in_stack_00000048);
                    uVar11 = DAT_1804b5208;
                    uVar6 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000009c2,1);
                    lVar13 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar13 + 0x390);
                    uVar7 = FUN_1800024f0(in_stack_00000048);
                    uVar8 = FUN_180002550(in_stack_00000048);
                    uVar9 = (**(code **)(lVar13 + 0x518))(in_stack_00000048,&stack0x000041d8,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar10 = FUN_1800025a0(in_stack_00000048);
                    uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar10);
                    (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                    uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6);
                    DAT_1804ac9f8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                  }
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804ac9f8,in_stack_000000b0);
                  uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_000000a0,0);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2adc);
                  (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                     (longlong)in_stack_00000058 % 0xc1e52 ^ in_stack_000000c0 ^
                                     0x1a99b540ac7495f8);
                  (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
                  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
                  uVar12 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                     (in_stack_00000048,uVar11,in_stack_000000d8);
                  plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)plVar4 == '\0') {
                    plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)plVar4 == '\0') {
                      plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)plVar4 == '\0') {
                        if (DAT_1804aca00 == 0) {
                          lVar13 = *in_stack_00000048;
                          pcVar1 = *(code **)(lVar13 + 0x390);
                          uVar5 = FUN_18014d730(in_stack_00000048);
                          uVar11 = DAT_1804b5208;
                          uVar6 = (**(code **)(lVar13 + 0x518))
                                            (in_stack_00000048,&stack0x000009c0,1);
                          lVar13 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar13 + 0x390);
                          uVar7 = FUN_1800024f0(in_stack_00000048);
                          uVar8 = FUN_180002550(in_stack_00000048);
                          uVar9 = (**(code **)(lVar13 + 0x518))
                                            (in_stack_00000048,&stack0x000041c8,5);
                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar10 = FUN_1800025a0(in_stack_00000048);
                          uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar10);
                          (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                          uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,uVar11,uVar6)
                          ;
                          DAT_1804aca00 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                        }
                        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,DAT_1804aca00,in_stack_000000b0);
                        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_000000a0,0);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,
                                           0x6763);
                        (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
                        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                           (longlong)in_stack_00000058 % 0x51a3d ^ in_stack_000000c0
                                           ^ 0x2e3046b22aa47934);
                        (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
                        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                           (in_stack_00000048,uVar11,in_stack_000000a8,uVar5);
                        lVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                           (in_stack_00000048,uVar11,in_stack_000000d8);
                        plVar4 = (longlong *)
                                 (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)plVar4 == '\0') {
                          plVar14 = (longlong *)((in_stack_00000058 ^ uVar12) - lVar13);
                          if (in_stack_000000f8 == 0) {
                            return plVar14;
                          }
                          plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                          if ((char)plVar4 == '\0') {
                            plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                            if ((char)plVar4 == '\0') {
                              plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)plVar4 == '\0') {
                                if (DAT_1804aca08 == 0) {
                                  lVar13 = *in_stack_00000048;
                                  pcVar1 = *(code **)(lVar13 + 0x390);
                                  uVar5 = FUN_18001ed20(in_stack_00000048);
                                  uVar11 = DAT_1804b5208;
                                  uVar6 = (**(code **)(lVar13 + 0x518))
                                                    (in_stack_00000048,&stack0x000009be,1);
                                  lVar13 = *in_stack_00000048;
                                  pcVar2 = *(code **)(lVar13 + 0x390);
                                  uVar7 = FUN_1800024f0(in_stack_00000048);
                                  uVar8 = FUN_180002550(in_stack_00000048);
                                  uVar9 = (**(code **)(lVar13 + 0x518))
                                                    (in_stack_00000048,&stack0x00006c40,0x16);
                                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                  uVar10 = FUN_1800025a0(in_stack_00000048);
                                  uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar10);
                                  (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                                  uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar5,
                                                     uVar11,uVar6);
                                  DAT_1804aca08 =
                                       (**(code **)(*in_stack_00000048 + 0xa8))
                                                 (in_stack_00000048,uVar11);
                                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11)
                                  ;
                                }
                                uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,DAT_1804aca08,
                                                    in_stack_000000b0);
                                uVar5 = (**(code **)(*in_stack_00000048 + 0x560))
                                                  (in_stack_00000048,2,in_stack_000000a0,0);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_000000c8,
                                                   in_stack_000000d0,0x65df);
                                (*pcVar1)(in_stack_00000048,uVar5,0,uVar6);
                                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                  (in_stack_00000048,in_stack_00000098,
                                                   in_stack_000000b8,
                                                   (longlong)plVar14 % 0x81670 ^ in_stack_000000c0 ^
                                                   0x85d2f81ed0efce64);
                                (*pcVar1)(in_stack_00000048,uVar5,1,uVar6);
                                uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                                   (in_stack_00000048,uVar11,in_stack_000000a8,uVar5
                                                   );
                                plVar4 = (longlong *)
                                         (**(code **)(*in_stack_00000048 + 0x720))
                                                   (in_stack_00000048);
                                if ((char)plVar4 == '\0') {
                                  if (DAT_1804b5340 == 0) {
                                    return in_stack_00000048;
                                  }
                                  (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,in_stack_00000090,DAT_1804b5340,
                                             uVar11);
                                  plVar4 = (longlong *)
                                           (**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                                  if ((char)plVar4 == '\0') {
                                    plVar4 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))()
                                    ;
                                    if ((char)plVar4 == '\0') {
                                      plVar4 = (longlong *)
                                               (**(code **)(*in_stack_00000048 + 0x720))();
                                      if ((char)plVar4 == '\0') {
                                        plVar4 = (longlong *)0xf437cb4f09355265;
                                        if (DAT_1804aca10 == 0) {
                                          lVar13 = *in_stack_00000048;
                                          pcVar1 = *(code **)(lVar13 + 0x390);
                                          uVar5 = FUN_18014d730(in_stack_00000048);
                                          uVar11 = DAT_1804b5208;
                                          uVar6 = (**(code **)(lVar13 + 0x518))
                                                            (in_stack_00000048,&stack0x000009bc,1);
                                          lVar13 = *in_stack_00000048;
                                          pcVar2 = *(code **)(lVar13 + 0x390);
                                          uVar7 = FUN_1800024f0(in_stack_00000048);
                                          uVar8 = FUN_180002550(in_stack_00000048);
                                          uVar9 = (**(code **)(lVar13 + 0x518))
                                                            (in_stack_00000048,&stack0x000041b8,5);
                                          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                          uVar10 = FUN_1800025a0(in_stack_00000048);
                                          uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000090,
                                                             uVar10);
                                          (*pcVar2)(in_stack_00000048,uVar7,uVar8,uVar9,uVar10);
                                          uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000090,
                                                             uVar5,uVar11,uVar6);
                                          DAT_1804aca10 =
                                               (**(code **)(*in_stack_00000048 + 0xa8))
                                                         (in_stack_00000048,uVar11);
                                          plVar4 = (longlong *)
                                                   (**(code **)(*in_stack_00000048 + 0xb8))
                                                             (in_stack_00000048,uVar11);
                                        }
                                        return plVar4;
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  return plVar4;
}

