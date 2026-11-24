/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=38 entry=1802bedbe blocks=44 maxInstructions=50000 */

/* cut edge: 0x1802bedbe -> 0x1802c940f */
/* cut edge: 0x1802bede7 -> 0x1802c9447 */
/* cut edge: 0x1802bee40 -> 0x1802c9498 */
/* cut edge: 0x1802bee50 -> 0x1802c94d1 */
/* cut edge: 0x1802bef25 -> 0x180333186 */
/* cut edge: 0x1802bf01c -> 0x180333186 */
/* cut edge: 0x1802bf032 -> 0x180333186 */
/* cut edge: 0x1802bf09a -> 0x1802c9508 */
/* cut edge: 0x1802bf0c3 -> 0x1802c9540 */
/* cut edge: 0x1802bf11f -> 0x1802c9591 */
/* cut edge: 0x1802bf12f -> 0x1802c95ca */
/* cut edge: 0x1802bf207 -> 0x180333186 */
/* cut edge: 0x1802bf2fe -> 0x180333186 */
/* cut edge: 0x1802bf314 -> 0x180333186 */
/* cut edge: 0x1802bf37c -> 0x1802c9601 */
/* cut edge: 0x1802bf3a5 -> 0x1802c9639 */
/* cut edge: 0x1802bf404 -> 0x1802c968a */
/* cut edge: 0x1802bf414 -> 0x1802c96c3 */
/* cut edge: 0x1802bf4f0 -> 0x180333186 */
/* cut edge: 0x1802bf5e5 -> 0x180333186 */
/* cut edge: 0x1802bf5fb -> 0x180333186 */
/* cut edge: 0x1802bf666 -> 0x1802c96fb */
/* cut edge: 0x1802bf68f -> 0x1802c9733 */
/* cut edge: 0x1802bf6f6 -> 0x1802c978e */
/* cut edge: 0x1802bf706 -> 0x1802c97c8 */
/* cut edge: 0x1802bf84a -> 0x180333186 */
/* cut edge: 0x1802bf8e3 -> 0x1802b9a89 */
/* cut edge: 0x1802bf90a -> 0x180333186 */
/* cut edge: 0x1802bfa55 -> 0x180333186 */
/* cut edge: 0x1802bfb4e -> 0x1803260e0 */
/* cut edge: 0x1802bfb5b -> 0x180333186 */
/* cut edge: 0x1802bfcb4 -> 0x180333186 */
/* cut edge: 0x1802bfcb4 -> 0x1802bfdad */
/* target 0x1802b9a89 original byte 0x48 replaced by RET */
/* target 0x1802bfdad original byte 0x3b replaced by RET */
/* target 0x1802c940f original byte 0x48 replaced by RET */
/* target 0x1802c9447 original byte 0x48 replaced by RET */
/* target 0x1802c9498 original byte 0x48 replaced by RET */
/* target 0x1802c94d1 original byte 0x48 replaced by RET */
/* target 0x1802c9508 original byte 0x48 replaced by RET */
/* target 0x1802c9540 original byte 0x48 replaced by RET */
/* target 0x1802c9591 original byte 0x48 replaced by RET */
/* target 0x1802c95ca original byte 0x48 replaced by RET */
/* target 0x1802c9601 original byte 0x48 replaced by RET */
/* target 0x1802c9639 original byte 0x48 replaced by RET */
/* target 0x1802c968a original byte 0x48 replaced by RET */
/* target 0x1802c96c3 original byte 0x48 replaced by RET */
/* target 0x1802c96fb original byte 0x48 replaced by RET */
/* target 0x1802c9733 original byte 0x48 replaced by RET */
/* target 0x1802c978e original byte 0x48 replaced by RET */
/* target 0x1802c97c8 original byte 0x48 replaced by RET */
/* target 0x1803260e0 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


code * region_00038_1802bedbe(void)

{
  code *pcVar1;
  longlong lVar2;
  undefined8 uVar3;
  code *pcVar4;
  code *pcVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  code *pcVar8;
  code *pcVar9;
  ulonglong uVar10;
  longlong *in_stack_00000040;
  code *pcStack0000000000000048;
  code *pcStack0000000000000060;
  code *pcStack0000000000000068;
  ulonglong in_stack_00000070;
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
  
  pcStack0000000000000068 = *(code **)(*in_stack_00000040 + 0x390);
  pcStack0000000000000060 = DAT_1804b5a08;
  if (DAT_1804b5a08 == (code *)0x0) {
    return (code *)0x0;
  }
  pcStack0000000000000048 = DAT_1804b59f0;
  uVar3 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000434,1);
  lVar2 = DAT_1804b4d38;
  pcVar1 = DAT_1804b4d30;
  pcVar4 = *(code **)(*in_stack_00000040 + 0x390);
  if (DAT_1804b4d30 == (code *)0x0) {
    return pcVar4;
  }
  if (DAT_1804b4d38 == 0) {
    return pcVar4;
  }
  pcVar5 = (code *)(**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001ab8,5);
  if (DAT_1804b4d40 == 0) {
    return pcVar5;
  }
  uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
  (*pcVar4)(in_stack_00000040,pcVar1,lVar2,pcVar5,uVar6);
  uVar3 = (*pcStack0000000000000068)
                    (in_stack_00000040,in_stack_00000080,pcStack0000000000000060,
                     pcStack0000000000000048,uVar3);
  DAT_1804b1468 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar3);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar3);
  uVar3 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b1468,in_stack_000000a0);
  uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar4 = *(code **)(*in_stack_00000040 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x700b);
  (*pcVar4)(in_stack_00000040,uVar6,0,uVar7);
  pcVar4 = *(code **)(*in_stack_00000040 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar4)(in_stack_00000040,uVar6,1,uVar7);
  uVar3 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar3,in_stack_000000a8,uVar6)
  ;
  pcStack0000000000000060 =
       (code *)(**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar3,in_stack_000000d0);
  pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if ((char)pcVar4 == '\0') {
    pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
    if ((char)pcVar4 == '\0') {
      pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
      pcVar1 = DAT_1804b59f0;
      if ((char)pcVar4 == '\0') {
        if (DAT_1804b1470 == 0) {
          pcStack0000000000000048 = *(code **)(*in_stack_00000040 + 0x390);
          pcStack0000000000000068 = DAT_1804b5a08;
          if (DAT_1804b5a08 == (code *)0x0) {
            return (code *)0x0;
          }
          uVar3 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000432,1);
          lVar2 = DAT_1804b4d38;
          pcVar5 = DAT_1804b4d30;
          pcVar4 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == (code *)0x0) {
            return pcVar4;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar4;
          }
          pcVar8 = (code *)(**(code **)(*in_stack_00000040 + 0x518))
                                     (in_stack_00000040,&stack0x00001aa8,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar8;
          }
          uVar6 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar4)(in_stack_00000040,pcVar5,lVar2,pcVar8,uVar6);
          uVar3 = (*pcStack0000000000000048)
                            (in_stack_00000040,in_stack_00000080,pcStack0000000000000068,pcVar1,
                             uVar3);
          DAT_1804b1470 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar3);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar3);
        }
        uVar3 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b1470,in_stack_000000a0);
        uVar6 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x5b00);
        (*pcVar4)(in_stack_00000040,uVar6,0,uVar7);
        pcVar4 = *(code **)(*in_stack_00000040 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           (longlong)in_stack_00000070 % 0x73dd8 ^ in_stack_000000b0 ^
                           0xaabb6a4d1ca31e4b);
        (*pcVar4)(in_stack_00000040,uVar6,1,uVar7);
        uVar3 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar3,in_stack_000000a8,uVar6);
        pcStack0000000000000068 =
             (code *)(**(code **)(*in_stack_00000040 + 0x1a0))
                               (in_stack_00000040,uVar3,in_stack_000000d0);
        pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if ((char)pcVar4 == '\0') {
          pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
          if ((char)pcVar4 == '\0') {
            pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
            pcVar1 = DAT_1804b59f0;
            if ((char)pcVar4 == '\0') {
              if (DAT_1804b1478 == 0) {
                pcVar4 = *(code **)(*in_stack_00000040 + 0x390);
                pcStack0000000000000048 = DAT_1804b5a08;
                if (DAT_1804b5a08 == (code *)0x0) {
                  return (code *)0x0;
                }
                uVar3 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x00000430,1);
                lVar2 = DAT_1804b4d38;
                pcVar8 = DAT_1804b4d30;
                pcVar5 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b4d30 == (code *)0x0) {
                  return pcVar5;
                }
                if (DAT_1804b4d38 == 0) {
                  return pcVar5;
                }
                pcVar9 = (code *)(**(code **)(*in_stack_00000040 + 0x518))
                                           (in_stack_00000040,&stack0x00001a98,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar9;
                }
                uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                  (in_stack_00000040,in_stack_00000080);
                (*pcVar5)(in_stack_00000040,pcVar8,lVar2,pcVar9,uVar6);
                uVar3 = (*pcVar4)(in_stack_00000040,in_stack_00000080,pcStack0000000000000048,pcVar1
                                  ,uVar3);
                DAT_1804b1478 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar3);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar3);
              }
              uVar3 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b1478,in_stack_000000a0);
              uVar6 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x4164);
              (*pcVar4)(in_stack_00000040,uVar6,0,uVar7);
              pcVar4 = *(code **)(*in_stack_00000040 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 (longlong)in_stack_00000070 % 0xeb48 ^ in_stack_000000b0 ^
                                 0xa05512612b814900);
              (*pcVar4)(in_stack_00000040,uVar6,1,uVar7);
              uVar3 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar3,in_stack_000000a8,uVar6);
              uVar10 = (**(code **)(*in_stack_00000040 + 0x1a0))
                                 (in_stack_00000040,uVar3,in_stack_000000d0);
              pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if ((char)pcVar4 == '\0') {
                pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
                if ((char)pcVar4 == '\0') {
                  pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))();
                  pcVar5 = DAT_1804b5a08;
                  pcVar1 = DAT_1804b59f0;
                  if ((char)pcVar4 == '\0') {
                    if (DAT_1804b1480 == 0) {
                      pcVar4 = *(code **)(*in_stack_00000040 + 0x390);
                      if (DAT_1804b5a08 == (code *)0x0) {
                        return (code *)0x0;
                      }
                      uVar3 = (**(code **)(*in_stack_00000040 + 0x518))
                                        (in_stack_00000040,&stack0x0000042e,1);
                      lVar2 = DAT_1804b4d38;
                      pcVar8 = *(code **)(*in_stack_00000040 + 0x390);
                      pcStack0000000000000048 = DAT_1804b4d30;
                      if (DAT_1804b4d30 == (code *)0x0) {
                        return (code *)0x0;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return DAT_1804b4d30;
                      }
                      pcVar9 = (code *)(**(code **)(*in_stack_00000040 + 0x518))
                                                 (in_stack_00000040,&stack0x00001a88,5);
                      if (DAT_1804b4d40 == 0) {
                        return pcVar9;
                      }
                      uVar6 = (**(code **)(*in_stack_00000040 + 0x110))
                                        (in_stack_00000040,in_stack_00000080);
                      (*pcVar8)(in_stack_00000040,pcStack0000000000000048,lVar2,pcVar9,uVar6);
                      uVar3 = (*pcVar4)(in_stack_00000040,in_stack_00000080,pcVar5,pcVar1,uVar3);
                      DAT_1804b1480 =
                           (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar3);
                      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar3);
                    }
                    uVar3 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,DAT_1804b1480,in_stack_000000a0);
                    uVar6 = (**(code **)(*in_stack_00000040 + 0x560))
                                      (in_stack_00000040,2,in_stack_00000090,0);
                    pcVar4 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x791b)
                    ;
                    (*pcVar4)(in_stack_00000040,uVar6,0,uVar7);
                    pcVar4 = *(code **)(*in_stack_00000040 + 0x570);
                    uVar7 = (**(code **)(*in_stack_00000040 + 0x390))
                                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                       (longlong)in_stack_00000070 % 0x52775 ^ in_stack_000000b0 ^
                                       0x9348a2b9cc7df80b);
                    (*pcVar4)(in_stack_00000040,uVar6,1,uVar7);
                    uVar3 = (**(code **)(*in_stack_00000040 + 0x110))
                                      (in_stack_00000040,uVar3,in_stack_000000a8,uVar6);
                    (**(code **)(*in_stack_00000040 + 0x1a0))
                              (in_stack_00000040,uVar3,in_stack_000000d0);
                    pcVar4 = (code *)(**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
                    if ((char)pcVar4 == '\0') {
                      return (code *)(in_stack_00000070 ^ uVar10);
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
  return pcVar4;
}

