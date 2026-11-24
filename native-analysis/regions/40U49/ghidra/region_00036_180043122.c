/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=36 entry=180043122 blocks=61 maxInstructions=100000 */

/* cut edge: 0x1800432e9 -> 0x18014d3db */
/* cut edge: 0x1800433de -> 0x18014d3db */
/* cut edge: 0x1800433f4 -> 0x18014d3db */
/* cut edge: 0x1800435de -> 0x18014d3db */
/* cut edge: 0x1800436d5 -> 0x18014d3db */
/* cut edge: 0x1800436eb -> 0x18014d3db */
/* cut edge: 0x1800437e0 -> 0x180044133 */
/* cut edge: 0x1800437f0 -> 0x18004416c */
/* cut edge: 0x1800438cb -> 0x18014d3db */
/* cut edge: 0x1800439c2 -> 0x18014d3db */
/* cut edge: 0x1800439d8 -> 0x18014d3db */
/* cut edge: 0x180043a48 -> 0x180044296 */
/* cut edge: 0x180043a6c -> 0x1800442c9 */
/* cut edge: 0x180043ad0 -> 0x18004431a */
/* cut edge: 0x180043ae0 -> 0x180044353 */
/* cut edge: 0x180043bbb -> 0x18014d3db */
/* cut edge: 0x180043cc0 -> 0x18010b790 */
/* cut edge: 0x180043ccf -> 0x18010046c */
/* cut edge: 0x180043cd4 -> 0x18010c398 */
/* cut edge: 0x180043dd2 -> 0x1800422b9 */
/* cut edge: 0x180043e05 -> 0x18004231a */
/* cut edge: 0x180043e3a -> 0x18004231a */
/* cut edge: 0x180043e56 -> 0x18004232f */
/* cut edge: 0x180043e90 -> 0x180042399 */
/* cut edge: 0x180043fb9 -> 0x1800425c5 */
/* cut edge: 0x180043fec -> 0x180042626 */
/* cut edge: 0x180044021 -> 0x180042626 */
/* cut edge: 0x18004403d -> 0x18004263b */
/* cut edge: 0x180044077 -> 0x180042694 */
/* target 0x1800422b9 original byte 0x48 replaced by RET */
/* target 0x18004231a original byte 0x4c replaced by RET */
/* target 0x18004232f original byte 0x48 replaced by RET */
/* target 0x180042399 original byte 0x48 replaced by RET */
/* target 0x1800425c5 original byte 0x48 replaced by RET */
/* target 0x180042626 original byte 0x4c replaced by RET */
/* target 0x18004263b original byte 0x48 replaced by RET */
/* target 0x180042694 original byte 0x48 replaced by RET */
/* target 0x180044133 original byte 0x48 replaced by RET */
/* target 0x18004416c original byte 0x48 replaced by RET */
/* target 0x180044296 original byte 0x48 replaced by RET */
/* target 0x1800442c9 original byte 0x48 replaced by RET */
/* target 0x18004431a original byte 0x48 replaced by RET */
/* target 0x180044353 original byte 0x48 replaced by RET */
/* target 0x18010046c original byte 0x8b replaced by RET */
/* target 0x18010b790 original byte 0x48 replaced by RET */
/* target 0x18010c398 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00036_180043122(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  longlong lVar6;
  char cVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong lVar12;
  longlong lVar13;
  bool bVar14;
  longlong *in_stack_00000048;
  longlong in_stack_00000078;
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
  
  if (DAT_1804ad100 == 0) {
    lVar1 = *in_stack_00000048;
    pcVar2 = *(code **)(lVar1 + 0x390);
    if (DAT_1804b52e8 == 0) {
      uVar11 = FUN_180019ff0(in_stack_00000048);
      DAT_1804b52e8 =
           (**(code **)(lVar1 + 0x388))
                     (in_stack_00000048,uVar11,&DAT_180493f72,
                      "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                     );
    }
    lVar5 = DAT_1804b52e8;
    uVar11 = DAT_1804b5208;
    uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000007f6,1);
    lVar1 = *in_stack_00000048;
    pcVar3 = *(code **)(lVar1 + 0x390);
    lVar13 = DAT_1804b4d30;
    if (DAT_1804b4d30 == 0) {
      uVar9 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
      lVar13 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      lVar12 = 0;
      LOCK();
      bVar14 = DAT_1804b4d30 != 0;
      lVar6 = lVar13;
      if (bVar14) {
        lVar12 = DAT_1804b4d30;
        lVar6 = DAT_1804b4d30;
      }
      DAT_1804b4d30 = lVar6;
      UNLOCK();
      if (bVar14) {
        (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar13);
        lVar13 = lVar12;
      }
    }
    if (DAT_1804b4d38 == 0) {
      uVar9 = FUN_1800024f0(in_stack_00000048);
      DAT_1804b4d38 =
           (**(code **)(*in_stack_00000048 + 0x388))
                     (in_stack_00000048,uVar9,"fromMethodDescriptorString",
                      "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;");
    }
    lVar12 = DAT_1804b4d38;
    uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000036c8,5);
    lVar1 = *in_stack_00000048;
    pcVar4 = *(code **)(lVar1 + 0x110);
    if (DAT_1804b4d40 == 0) {
      uVar10 = FUN_18000fbd0(in_stack_00000048);
      DAT_1804b4d40 =
           (**(code **)(lVar1 + 0x108))
                     (in_stack_00000048,uVar10,"getClassLoader","()Ljava/lang/ClassLoader;");
    }
    uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000090,DAT_1804b4d40);
    (*pcVar3)(in_stack_00000048,lVar13,lVar12,uVar9,uVar10);
    uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,lVar5,uVar11,uVar8);
    DAT_1804ad100 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
  }
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804ad100,in_stack_000000b0);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x16a3);
  (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                     in_stack_00000078 % 0x2604a ^ in_stack_000000c0 ^ 0x910654266bfc40c7);
  (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
  uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
     (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
    if (DAT_1804ad108 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      if (DAT_1804b52e8 == 0) {
        uVar11 = FUN_180019ff0(in_stack_00000048);
        DAT_1804b52e8 =
             (**(code **)(lVar1 + 0x388))
                       (in_stack_00000048,uVar11,&DAT_180493f72,
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                       );
      }
      lVar5 = DAT_1804b52e8;
      uVar11 = DAT_1804b5208;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000007f4,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      lVar13 = DAT_1804b4d30;
      if (DAT_1804b4d30 == 0) {
        uVar9 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
        lVar13 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        lVar12 = 0;
        LOCK();
        bVar14 = DAT_1804b4d30 != 0;
        lVar6 = lVar13;
        if (bVar14) {
          lVar12 = DAT_1804b4d30;
          lVar6 = DAT_1804b4d30;
        }
        DAT_1804b4d30 = lVar6;
        UNLOCK();
        if (bVar14) {
          (**(code **)(*in_stack_00000048 + 0xb0))();
          lVar13 = lVar12;
        }
      }
      if (DAT_1804b4d38 == 0) {
        uVar9 = FUN_1800024f0(in_stack_00000048);
        DAT_1804b4d38 =
             (**(code **)(*in_stack_00000048 + 0x388))
                       (in_stack_00000048,uVar9,"fromMethodDescriptorString",
                        "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;");
      }
      lVar12 = DAT_1804b4d38;
      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000036b8,5);
      lVar1 = *in_stack_00000048;
      pcVar4 = *(code **)(lVar1 + 0x110);
      if (DAT_1804b4d40 == 0) {
        uVar10 = FUN_18000fbd0(in_stack_00000048);
        DAT_1804b4d40 =
             (**(code **)(lVar1 + 0x108))
                       (in_stack_00000048,uVar10,"getClassLoader","()Ljava/lang/ClassLoader;");
      }
      uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000090,DAT_1804b4d40);
      (*pcVar3)(in_stack_00000048,lVar13,lVar12,uVar9,uVar10);
      uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,lVar5,uVar11,uVar8);
      DAT_1804ad108 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
    }
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804ad108,in_stack_000000b0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6c86);
    (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       in_stack_00000078 % 0x459a5 ^ in_stack_000000c0 ^ 0xf73cf628e3a17ce8);
    (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
       (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
      if (DAT_1804ad110 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        if (DAT_1804b52e8 == 0) {
          uVar11 = FUN_180019ff0(in_stack_00000048);
          DAT_1804b52e8 =
               (**(code **)(lVar1 + 0x388))
                         (in_stack_00000048,uVar11,&DAT_180493f72,
                          "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                         );
        }
        lVar5 = DAT_1804b52e8;
        uVar11 = DAT_1804b5208;
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000007f2,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        lVar13 = DAT_1804b4d30;
        if (DAT_1804b4d30 == 0) {
          uVar9 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
          lVar13 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          lVar12 = 0;
          LOCK();
          bVar14 = DAT_1804b4d30 != 0;
          lVar6 = lVar13;
          if (bVar14) {
            lVar12 = DAT_1804b4d30;
            lVar6 = DAT_1804b4d30;
          }
          DAT_1804b4d30 = lVar6;
          UNLOCK();
          if (bVar14) {
            (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar13);
            lVar13 = lVar12;
          }
        }
        lVar12 = DAT_1804b4d38;
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000036a8,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar3)(in_stack_00000048,lVar13,lVar12,uVar9,uVar10);
        uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,lVar5,uVar11,uVar8);
        DAT_1804ad110 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
      }
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804ad110,in_stack_000000b0);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x3106);
      (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         in_stack_00000078 % 0xe78ec ^ in_stack_000000c0 ^ 0x8fc75040c4302802);
      (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0'))
         && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), lVar1 = DAT_1804b52e8,
            uVar11 = DAT_1804b5208, cVar7 == '\0')) {
        if (DAT_1804ad118 == 0) {
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b52e8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000007f0,1);
          lVar13 = DAT_1804b4d38;
          lVar5 = DAT_1804b4d30;
          pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00003698,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar3)(in_stack_00000048,lVar5,lVar13,uVar9,uVar10);
          uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,lVar1,uVar11,uVar8);
          DAT_1804ad118 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ad118,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x597c);
        (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           in_stack_00000078 % 0x506c6 ^ in_stack_000000c0 ^ 0x98813d6aafd56e65);
        (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 == '\0') {
          if ((longlong)in_stack_000000c0 < 1) {
            return;
          }
          if (in_stack_000000f8 != 0) {
            return;
          }
          return;
        }
      }
    }
  }
  return;
}

