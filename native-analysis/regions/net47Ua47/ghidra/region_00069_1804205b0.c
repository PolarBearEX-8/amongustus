/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=69 entry=1804205b0 blocks=57 maxInstructions=50000 */

/* cut edge: 0x1804205b0 -> 0x180473ae8 */
/* cut edge: 0x1804205c6 -> 0x180473ae8 */
/* cut edge: 0x18042062e -> 0x180433768 */
/* cut edge: 0x180420657 -> 0x1804337a0 */
/* cut edge: 0x1804206be -> 0x1804337f1 */
/* cut edge: 0x1804206ce -> 0x18043382b */
/* cut edge: 0x1804207b1 -> 0x180473ae8 */
/* cut edge: 0x1804208a6 -> 0x180473ae8 */
/* cut edge: 0x1804208bc -> 0x180473ae8 */
/* cut edge: 0x180420924 -> 0x180433862 */
/* cut edge: 0x18042094d -> 0x18043389a */
/* cut edge: 0x1804209af -> 0x1804338e8 */
/* cut edge: 0x1804209cc -> 0x180433921 */
/* cut edge: 0x180420ab5 -> 0x180473ae8 */
/* cut edge: 0x180420bd5 -> 0x1803dd67b */
/* cut edge: 0x180420c0c -> 0x1803dd9b0 */
/* cut edge: 0x180420c26 -> 0x180473ae8 */
/* cut edge: 0x180420c3c -> 0x180473ae8 */
/* cut edge: 0x180420ca7 -> 0x180433959 */
/* cut edge: 0x180420ccb -> 0x18043398c */
/* cut edge: 0x180420d2c -> 0x1804339dd */
/* cut edge: 0x180420d3c -> 0x180433a16 */
/* cut edge: 0x180420e15 -> 0x180473ae8 */
/* cut edge: 0x180420f0c -> 0x180473ae8 */
/* cut edge: 0x180420f22 -> 0x180473ae8 */
/* cut edge: 0x180420f8a -> 0x180433b43 */
/* cut edge: 0x180420fb3 -> 0x180433b7b */
/* cut edge: 0x180421012 -> 0x180433bcc */
/* cut edge: 0x180421022 -> 0x180433c05 */
/* cut edge: 0x1804210fd -> 0x180473ae8 */
/* cut edge: 0x1804211f2 -> 0x180473ae8 */
/* cut edge: 0x180421208 -> 0x180473ae8 */
/* cut edge: 0x180421270 -> 0x180433f1e */
/* cut edge: 0x180421299 -> 0x180433f56 */
/* cut edge: 0x1804212fb -> 0x180433fa7 */
/* cut edge: 0x180421310 -> 0x180433fe1 */
/* cut edge: 0x1804213f3 -> 0x180473ae8 */
/* cut edge: 0x1804214e8 -> 0x180473ae8 */
/* cut edge: 0x1804214fe -> 0x180473ae8 */
/* cut edge: 0x180421514 -> 0x1804216fa */
/* cut edge: 0x180421514 -> 0x180421566 */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x1803dd9b0 original byte 0x48 replaced by RET */
/* target 0x180421566 original byte 0x48 replaced by RET */
/* target 0x1804216fa original byte 0x48 replaced by RET */
/* target 0x180433768 original byte 0x48 replaced by RET */
/* target 0x1804337a0 original byte 0x48 replaced by RET */
/* target 0x1804337f1 original byte 0x48 replaced by RET */
/* target 0x18043382b original byte 0x48 replaced by RET */
/* target 0x180433862 original byte 0x48 replaced by RET */
/* target 0x18043389a original byte 0x48 replaced by RET */
/* target 0x1804338e8 original byte 0x48 replaced by RET */
/* target 0x180433921 original byte 0x48 replaced by RET */
/* target 0x180433959 original byte 0x48 replaced by RET */
/* target 0x18043398c original byte 0x48 replaced by RET */
/* target 0x1804339dd original byte 0x48 replaced by RET */
/* target 0x180433a16 original byte 0x48 replaced by RET */
/* target 0x180433b43 original byte 0x48 replaced by RET */
/* target 0x180433b7b original byte 0x48 replaced by RET */
/* target 0x180433bcc original byte 0x48 replaced by RET */
/* target 0x180433c05 original byte 0x48 replaced by RET */
/* target 0x180433f1e original byte 0x48 replaced by RET */
/* target 0x180433f56 original byte 0x48 replaced by RET */
/* target 0x180433fa7 original byte 0x48 replaced by RET */
/* target 0x180433fe1 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


code * region_00069_1804205b0(void)

{
  longlong lVar1;
  longlong lVar2;
  code *pcVar3;
  undefined8 uVar4;
  code *pcVar5;
  code *pcVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  ulonglong uVar9;
  longlong lVar10;
  longlong unaff_R14;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
  ulonglong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  longlong in_stack_00000118;
  undefined8 in_stack_000001f0;
  undefined2 uStack00000000000001f8;
  undefined2 uStack00000000000001fa;
  undefined2 uStack00000000000001fc;
  short in_stack_00004280;
  
  pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if (((char)pcVar3 == '\0') &&
     (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar10 = DAT_1804b5a08,
     uVar8 = DAT_1804b59f0, (char)pcVar3 == '\0')) {
    if (DAT_1804b4b30 == 0) {
      pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return (code *)0x0;
      }
      uStack00000000000001fc = 0x7a;
      uVar4 = (**(code **)(*in_stack_00000048 + 0x518))
                        (in_stack_00000048,(longlong)&stack0x000001f8 + 4,1);
      lVar2 = DAT_1804b4d38;
      lVar1 = DAT_1804b4d30;
      pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar5;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar5;
      }
      pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00000890,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar6;
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
      (*pcVar5)(in_stack_00000048,lVar1,lVar2,pcVar6,uVar7);
      uVar8 = (*pcVar3)(in_stack_00000048,in_stack_00000088,lVar10,uVar8,uVar4);
      DAT_1804b4b30 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
    }
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b4b30,in_stack_000000a0);
    uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x42e);
    (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
    pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000068 % 0xd8b2 ^ in_stack_000000c8 ^ 0xc83f4d4163d9e9bc
                      );
    (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
    pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((((char)pcVar3 == '\0') &&
        (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar3 == '\0')) &&
       (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar10 = DAT_1804b5a08,
       uVar8 = DAT_1804b59f0, (char)pcVar3 == '\0')) {
      if (DAT_1804b4b38 == 0) {
        pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b5a08 == 0) {
          return (code *)0x0;
        }
        uStack00000000000001fa = 0x7a;
        uVar4 = (**(code **)(*in_stack_00000048 + 0x518))
                          (in_stack_00000048,(longlong)&stack0x000001f8 + 2,1);
        lVar2 = DAT_1804b4d38;
        lVar1 = DAT_1804b4d30;
        pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return pcVar5;
        }
        if (DAT_1804b4d38 == 0) {
          return pcVar5;
        }
        pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                   (in_stack_00000048,&stack0x00000880,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar6;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
        (*pcVar5)(in_stack_00000048,lVar1,lVar2,pcVar6,uVar7);
        uVar8 = (*pcVar3)(in_stack_00000048,in_stack_00000088,lVar10,uVar8,uVar4);
        DAT_1804b4b38 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b4b38,in_stack_000000a0);
      uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x47cf);
      (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         (longlong)in_stack_00000068 % 0xb4a97 ^ in_stack_000000c8 ^
                         0xa3c4045e5cadf94a);
      (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
      lVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
      pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar3 == '\0') {
        if ((in_stack_00004280 != 0) &&
           (in_stack_00000068 = (uVar9 ^ in_stack_00000068) - lVar10, in_stack_00000118 == 0)) {
          return (code *)((in_stack_000000b0 ^ in_stack_00000058) - unaff_R14);
        }
        pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if (((char)pcVar3 == '\0') &&
           (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar10 = DAT_1804b5a08,
           uVar8 = DAT_1804b59f0, (char)pcVar3 == '\0')) {
          if (DAT_1804b4b40 == 0) {
            pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b5a08 == 0) {
              return (code *)0x0;
            }
            uStack00000000000001f8 = 0x7a;
            uVar4 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000001f8,1);
            lVar2 = DAT_1804b4d38;
            lVar1 = DAT_1804b4d30;
            pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return pcVar5;
            }
            if (DAT_1804b4d38 == 0) {
              return pcVar5;
            }
            pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                       (in_stack_00000048,&stack0x00000870,5);
            if (DAT_1804b4d40 == 0) {
              return pcVar6;
            }
            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
            (*pcVar5)(in_stack_00000048,lVar1,lVar2,pcVar6,uVar7);
            uVar8 = (*pcVar3)(in_stack_00000048,in_stack_00000088,lVar10,uVar8,uVar4);
            DAT_1804b4b40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804b4b40,in_stack_000000a0);
          uVar4 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x88f);
          (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                             (longlong)in_stack_00000068 % 0xe9c96 ^ in_stack_000000c8 ^
                             0xadd1a4a506360deb);
          (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
          (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
          pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((((char)pcVar3 == '\0') &&
              (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar3 == '\0'))
             && (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(),
                lVar10 = DAT_1804b5a08, uVar8 = DAT_1804b59f0, (char)pcVar3 == '\0')) {
            if (DAT_1804b4b48 == 0) {
              pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return (code *)0x0;
              }
              in_stack_000001f0._6_2_ = 0x7a;
              uVar4 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,(longlong)&stack0x000001f0 + 6,1);
              lVar2 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar5;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar5;
              }
              pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x00000860,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar6;
              }
              uVar7 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088)
              ;
              (*pcVar5)(in_stack_00000048,lVar1,lVar2,pcVar6,uVar7);
              uVar8 = (*pcVar3)(in_stack_00000048,in_stack_00000088,lVar10,uVar8,uVar4);
              DAT_1804b4b48 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,DAT_1804b4b48,in_stack_000000a0);
            uVar4 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000090,0);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x653a);
            (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               (longlong)in_stack_00000068 % 0x32a84 ^ in_stack_000000c8 ^
                               0x9f1bdd2f8478f026);
            (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
            pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((((char)pcVar3 == '\0') &&
                (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar3 == '\0')
                ) && (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(),
                     lVar10 = DAT_1804b5a08, uVar8 = DAT_1804b59f0, (char)pcVar3 == '\0')) {
              if (DAT_1804b4b50 == 0) {
                pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return (code *)0x0;
                }
                in_stack_000001f0._4_2_ = 0x7a;
                uVar4 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,(longlong)&stack0x000001f0 + 4,1);
                lVar2 = DAT_1804b4d38;
                lVar1 = DAT_1804b4d30;
                pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return pcVar5;
                }
                if (DAT_1804b4d38 == 0) {
                  return pcVar5;
                }
                pcVar6 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                           (in_stack_00000048,&stack0x00000850,5);
                if (DAT_1804b4d40 == 0) {
                  return pcVar6;
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000088);
                (*pcVar5)(in_stack_00000048,lVar1,lVar2,pcVar6,uVar7);
                uVar8 = (*pcVar3)(in_stack_00000048,in_stack_00000088,lVar10,uVar8,uVar4);
                DAT_1804b4b50 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804b4b50,in_stack_000000a0);
              uVar4 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000090,0);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x40a1);
              (*pcVar3)(in_stack_00000048,uVar4,0,uVar7);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 (longlong)in_stack_00000068 % 0x3d96a ^ in_stack_000000c8 ^
                                 0x910768120c0aa9bd);
              (*pcVar3)(in_stack_00000048,uVar4,1,uVar7);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar8,in_stack_000000a8,uVar4);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
              pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((((char)pcVar3 == '\0') &&
                  (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(),
                  (char)pcVar3 == '\0')) &&
                 (pcVar3 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar3 == '\0'
                 )) {
                if (DAT_1804b4b58 != 0) {
                  return (code *)0xdd18ea58c16895e1;
                }
                return (code *)0xdd18ea58c16895e1;
              }
            }
          }
        }
      }
    }
  }
  return pcVar3;
}

