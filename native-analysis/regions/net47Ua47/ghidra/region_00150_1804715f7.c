/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=150 entry=1804715f7 blocks=40 maxInstructions=50000 */

/* cut edge: 0x1804715f7 -> 0x180473ae8 */
/* cut edge: 0x18047162d -> 0x1804743a3 */
/* cut edge: 0x18047164c -> 0x180473ae8 */
/* cut edge: 0x180471675 -> 0x180473ae8 */
/* cut edge: 0x180471698 -> 0x180473ae8 */
/* cut edge: 0x18047187e -> 0x180473ae8 */
/* cut edge: 0x1804719c6 -> 0x1804743d9 */
/* cut edge: 0x1804719e5 -> 0x180473ae8 */
/* cut edge: 0x180471a0e -> 0x180473ae8 */
/* cut edge: 0x180471a29 -> 0x180473ae8 */
/* cut edge: 0x180471c08 -> 0x180473ae8 */
/* cut edge: 0x180471ceb -> 0x180473ae8 */
/* cut edge: 0x180471d01 -> 0x180473ae8 */
/* cut edge: 0x180471ed2 -> 0x180473ae8 */
/* cut edge: 0x180471fb0 -> 0x180474506 */
/* cut edge: 0x180471fcf -> 0x18047455b */
/* cut edge: 0x180471fdf -> 0x180473ae8 */
/* cut edge: 0x180472003 -> 0x1804745cb */
/* cut edge: 0x180472022 -> 0x180473ae8 */
/* cut edge: 0x18047204f -> 0x180473ae8 */
/* cut edge: 0x180472065 -> 0x180473ae8 */
/* cut edge: 0x18047222d -> 0x180473ae8 */
/* cut edge: 0x18047235b -> 0x180474637 */
/* cut edge: 0x18047237a -> 0x18047468c */
/* cut edge: 0x18047238a -> 0x180473ae8 */
/* cut edge: 0x1804723ae -> 0x180473ae8 */
/* cut edge: 0x1804723e3 -> 0x1804746fb */
/* cut edge: 0x180472402 -> 0x180473ae8 */
/* cut edge: 0x180472447 -> 0x180473ae8 */
/* cut edge: 0x18047246a -> 0x180473ae8 */
/* cut edge: 0x180472480 -> 0x18047265f */
/* cut edge: 0x1804724d1 -> 0x18047265f */
/* target 0x18047265f original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */
/* target 0x1804743a3 original byte 0x48 replaced by RET */
/* target 0x1804743d9 original byte 0x48 replaced by RET */
/* target 0x180474506 original byte 0x48 replaced by RET */
/* target 0x18047455b original byte 0x48 replaced by RET */
/* target 0x1804745cb original byte 0x4c replaced by RET */
/* target 0x180474637 original byte 0x48 replaced by RET */
/* target 0x18047468c original byte 0x48 replaced by RET */
/* target 0x1804746fb original byte 0x4c replaced by RET */


longlong * region_00150_1804715f7(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  undefined8 uVar6;
  longlong *plVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
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
  undefined8 in_stack_000000f8;
  undefined8 in_stack_00000130;
  undefined8 in_stack_00000148;
  undefined8 in_stack_00000738;
  undefined8 in_stack_00000790;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_000000f8);
  plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if ((char)plVar7 == '\0') {
    if (DAT_1804b5040 == 0) {
      return in_stack_00000048;
    }
    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6);
    plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)plVar7 == '\0') {
      plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
      if ((char)plVar7 == '\0') {
        plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)plVar7 == '\0') {
          if (DAT_1804b48c0 == 0) {
            lVar1 = *in_stack_00000048;
            pcVar2 = *(code **)(lVar1 + 0x390);
            uVar8 = FUN_180333500(in_stack_00000048);
            uVar14 = DAT_1804b59f0;
            uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000298,1);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x390);
            uVar10 = FUN_1800024f0(in_stack_00000048);
            uVar11 = FUN_180002550(in_stack_00000048);
            uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003be0,0x2a);
            pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
            uVar13 = FUN_1800025a0(in_stack_00000048);
            uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
            (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
            uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar14,uVar9);
            DAT_1804b48c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
          }
          uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b48c0,in_stack_000000a0);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_00000090,0)
          ;
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,0,in_stack_00000130);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00000738
                            );
          (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
          (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar8,2,uVar6);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                             in_stack_00000068 % 0xc6d19 ^ 0x5024f333e5d073a0);
          (*pcVar2)(in_stack_00000048,uVar8,3,uVar6);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_000000c8
                            );
          (*pcVar2)(in_stack_00000048,uVar8,4,uVar6);
          (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,uVar14,in_stack_000000a8,uVar8);
          plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          lVar1 = DAT_1804b52a8;
          if ((char)plVar7 == '\0') {
            if (DAT_1804b52a8 == 0) {
              return in_stack_00000048;
            }
            uVar6 = (**(code **)(*in_stack_00000048 + 0xd8))(in_stack_00000048);
            plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)plVar7 == '\0') {
              plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
              if ((char)plVar7 == '\0') {
                plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                if ((char)plVar7 == '\0') {
                  if (DAT_1804b48c8 == 0) {
                    lVar5 = *in_stack_00000048;
                    pcVar2 = *(code **)(lVar5 + 0x390);
                    uVar8 = FUN_1803335a0(in_stack_00000048);
                    uVar14 = DAT_1804b59f0;
                    uVar9 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x00000296,1);
                    lVar5 = *in_stack_00000048;
                    pcVar3 = *(code **)(lVar5 + 0x390);
                    uVar10 = FUN_1800024f0(in_stack_00000048);
                    uVar11 = FUN_180002550(in_stack_00000048);
                    uVar12 = (**(code **)(lVar5 + 0x518))(in_stack_00000048,&stack0x000034b0,0x16);
                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar13 = FUN_1800025a0(in_stack_00000048);
                    uVar13 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar13);
                    (*pcVar3)(in_stack_00000048,uVar10,uVar11,uVar12,uVar13);
                    uVar14 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar8,uVar14,uVar9);
                    DAT_1804b48c8 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar14);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar14);
                  }
                  uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,DAT_1804b48c8,in_stack_000000a0);
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                    (in_stack_00000048,2,in_stack_00000090,0);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x50e0);
                  (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                    (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                     in_stack_00000068 % 0x46ac6 ^ in_stack_000000c8 ^
                                     0xa42e3961b5ce2b57);
                  (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
                  uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,uVar14,in_stack_000000a8,uVar8);
                  plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                  if ((char)plVar7 == '\0') {
                    plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                    if ((char)plVar7 == '\0') {
                      plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                      if ((char)plVar7 == '\0') {
                        if (DAT_1804b48d0 == 0) {
                          lVar5 = *in_stack_00000048;
                          pcVar2 = *(code **)(lVar5 + 0x390);
                          uVar9 = FUN_1803335a0(in_stack_00000048);
                          uVar8 = DAT_1804b59f0;
                          uVar10 = (**(code **)(lVar5 + 0x518))
                                             (in_stack_00000048,&stack0x00000294,1);
                          lVar5 = *in_stack_00000048;
                          pcVar3 = *(code **)(lVar5 + 0x390);
                          uVar11 = FUN_1800024f0(in_stack_00000048);
                          uVar12 = FUN_180002550(in_stack_00000048);
                          uVar13 = (**(code **)(lVar5 + 0x518))
                                             (in_stack_00000048,&stack0x00003480,0x16);
                          pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                          uVar15 = FUN_1800025a0(in_stack_00000048);
                          uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar15);
                          (*pcVar3)(in_stack_00000048,uVar11,uVar12,uVar13,uVar15);
                          uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar9,uVar8,uVar10);
                          DAT_1804b48d0 =
                               (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                        }
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,DAT_1804b48d0,in_stack_000000a0);
                        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                          (in_stack_00000048,2,in_stack_00000090,0);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                           (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,
                                            0x2a89);
                        (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
                        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                                           (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                            in_stack_00000068 % 0xcc8fe ^ in_stack_000000c8 ^
                                            0xed29df2e6007b68f);
                        (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
                        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                          (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
                        plVar7 = (longlong *)
                                 (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                        if ((char)plVar7 == '\0') {
                          if (DAT_1804b52b0 == 0) {
                            return in_stack_00000048;
                          }
                          if (DAT_1804b52b8 == 0) {
                            return in_stack_00000048;
                          }
                          uVar9 = (**(code **)(*in_stack_00000048 + 0x488))
                                            (in_stack_00000048,DAT_1804b52b0);
                          plVar7 = (longlong *)
                                   (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                          if ((char)plVar7 == '\0') {
                            if (DAT_1804b52c0 == 0) {
                              return in_stack_00000048;
                            }
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar8,DAT_1804b52c0,uVar9);
                            plVar7 = (longlong *)
                                     (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if ((char)plVar7 == '\0') {
                              plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                              if ((char)plVar7 == '\0') {
                                plVar7 = (longlong *)(**(code **)(*in_stack_00000048 + 0x720))();
                                if ((char)plVar7 == '\0') {
                                  if (DAT_1804b48d8 == 0) {
                                    lVar5 = *in_stack_00000048;
                                    pcVar2 = *(code **)(lVar5 + 0x390);
                                    uVar10 = FUN_180333500(in_stack_00000048);
                                    uVar9 = DAT_1804b59f0;
                                    uVar11 = (**(code **)(lVar5 + 0x518))
                                                       (in_stack_00000048,&stack0x00000292,1);
                                    lVar5 = *in_stack_00000048;
                                    pcVar3 = *(code **)(lVar5 + 0x390);
                                    uVar12 = FUN_1800024f0(in_stack_00000048);
                                    uVar13 = FUN_180002550(in_stack_00000048);
                                    uVar15 = (**(code **)(lVar5 + 0x518))
                                                       (in_stack_00000048,&stack0x00003100,0x19);
                                    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar16 = FUN_1800025a0(in_stack_00000048);
                                    uVar16 = (*pcVar4)(in_stack_00000048,in_stack_00000088,uVar16);
                                    (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar15,uVar16);
                                    uVar9 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar10,
                                                      uVar9,uVar11);
                                    DAT_1804b48d8 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar9);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar9);
                                  }
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,DAT_1804b48d8,
                                                     in_stack_000000a0);
                                  uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                                     (in_stack_00000048,4,in_stack_00000090,0);
                                  (**(code **)(*in_stack_00000048 + 0x570))
                                            (in_stack_00000048,uVar10,0,uVar8);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000080,
                                                     in_stack_00000098,in_stack_00000790);
                                  (*pcVar2)(in_stack_00000048,uVar10,1,uVar8);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000080,
                                                     in_stack_00000098,
                                                     in_stack_00000068 % 0x17a58 ^
                                                     0x5028ead18b88113a);
                                  (*pcVar2)(in_stack_00000048,uVar10,2,uVar8);
                                  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000080,
                                                     in_stack_00000098,in_stack_000000c8);
                                  (*pcVar2)(in_stack_00000048,uVar10,3,uVar8);
                                  (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar9,in_stack_000000a8,uVar10);
                                  plVar7 = (longlong *)
                                           (**(code **)(*in_stack_00000048 + 0x720))
                                                     (in_stack_00000048);
                                  if ((char)plVar7 == '\0') {
                                    if (DAT_1804b52c8 == 0) {
                                      return in_stack_00000048;
                                    }
                                    if (DAT_1804b52d0 == 0) {
                                      return in_stack_00000048;
                                    }
                                    (**(code **)(*in_stack_00000048 + 0x488))
                                              (in_stack_00000048,DAT_1804b52c8);
                                    plVar7 = (longlong *)
                                             (**(code **)(*in_stack_00000048 + 0x720))
                                                       (in_stack_00000048);
                                    if ((char)plVar7 == '\0') {
                                      (**(code **)(*in_stack_00000048 + 0x420))
                                                (in_stack_00000048,in_stack_00000148,
                                                 in_stack_00000058);
                                      plVar7 = (longlong *)
                                               (**(code **)(*in_stack_00000048 + 0x720))
                                                         (in_stack_00000048);
                                      if ((char)plVar7 == '\0') {
                                        if (DAT_1804b52e0 == 0) {
                                          return in_stack_00000048;
                                        }
                                        (**(code **)(*in_stack_00000048 + 0x2d8))
                                                  (in_stack_00000048,uVar6,lVar1,DAT_1804b52e0,
                                                   uVar14);
                                        plVar7 = (longlong *)
                                                 (**(code **)(*in_stack_00000048 + 0x720))
                                                           (in_stack_00000048);
                                        if ((char)plVar7 == '\0') {
                                          plVar7 = (longlong *)
                                                   (**(code **)(*in_stack_00000048 + 0x720))();
                                          if ((char)plVar7 == '\0') {
                                            plVar7 = (longlong *)
                                                     (**(code **)(*in_stack_00000048 + 0x720))();
                                            if ((char)plVar7 == '\0') {
                                              plVar7 = (longlong *)0x50236604602cdd63;
                                              if (DAT_1804b48e0 == 0) {
                                                lVar1 = *in_stack_00000048;
                                                pcVar2 = *(code **)(lVar1 + 0x390);
                                                uVar14 = FUN_180333500(in_stack_00000048);
                                                uVar6 = DAT_1804b59f0;
                                                uVar8 = (**(code **)(lVar1 + 0x518))
                                                                  (in_stack_00000048,
                                                                   &stack0x00000290,1);
                                                lVar1 = *in_stack_00000048;
                                                pcVar3 = *(code **)(lVar1 + 0x390);
                                                uVar9 = FUN_1800024f0(in_stack_00000048);
                                                uVar10 = FUN_180002550(in_stack_00000048);
                                                uVar11 = (**(code **)(lVar1 + 0x518))
                                                                   (in_stack_00000048,
                                                                    &stack0x00003b80,0x2a);
                                                pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
                                                uVar12 = FUN_1800025a0(in_stack_00000048);
                                                uVar12 = (*pcVar4)(in_stack_00000048,
                                                                   in_stack_00000088,uVar12);
                                                (*pcVar3)(in_stack_00000048,uVar9,uVar10,uVar11,
                                                          uVar12);
                                                uVar6 = (*pcVar2)(in_stack_00000048,
                                                                  in_stack_00000088,uVar14,uVar6,
                                                                  uVar8);
                                                DAT_1804b48e0 =
                                                     (**(code **)(*in_stack_00000048 + 0xa8))
                                                               (in_stack_00000048,uVar6);
                                                plVar7 = (longlong *)
                                                         (**(code **)(*in_stack_00000048 + 0xb8))
                                                                   (in_stack_00000048,uVar6);
                                              }
                                              return plVar7;
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
      }
    }
  }
  return plVar7;
}

