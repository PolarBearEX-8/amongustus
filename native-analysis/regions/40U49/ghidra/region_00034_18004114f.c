/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=34 entry=18004114f blocks=59 maxInstructions=100000 */

/* cut edge: 0x1800412bc -> 0x18014d3db */
/* cut edge: 0x1800413b1 -> 0x18014d3db */
/* cut edge: 0x1800413c7 -> 0x18014d3db */
/* cut edge: 0x1800415b5 -> 0x18014d3db */
/* cut edge: 0x1800416aa -> 0x18014d3db */
/* cut edge: 0x1800416c0 -> 0x18014d3db */
/* cut edge: 0x1800418a9 -> 0x18014d3db */
/* cut edge: 0x18004199e -> 0x18014d3db */
/* cut edge: 0x1800419b4 -> 0x18014d3db */
/* cut edge: 0x180041a4b -> 0x18004214a */
/* cut edge: 0x180041aac -> 0x18004219b */
/* cut edge: 0x180041ac1 -> 0x1800421d5 */
/* cut edge: 0x180041ba7 -> 0x18014d3db */
/* cut edge: 0x180041c9e -> 0x18004220d */
/* cut edge: 0x180041cb2 -> 0x18010c398 */
/* cut edge: 0x180041cb7 -> 0x18004109a */
/* cut edge: 0x180041ced -> 0x18004109a */
/* cut edge: 0x180041d09 -> 0x1800410aa */
/* cut edge: 0x180041d43 -> 0x180040e3d */
/* cut edge: 0x180041d76 -> 0x180040e9e */
/* cut edge: 0x180041dab -> 0x180040e9e */
/* cut edge: 0x180041dc7 -> 0x180040eae */
/* cut edge: 0x180041e00 -> 0x180040f06 */
/* target 0x180040e3d original byte 0x48 replaced by RET */
/* target 0x180040e9e original byte 0x48 replaced by RET */
/* target 0x180040eae original byte 0x48 replaced by RET */
/* target 0x180040f06 original byte 0x48 replaced by RET */
/* target 0x18004109a original byte 0x4c replaced by RET */
/* target 0x1800410aa original byte 0x48 replaced by RET */
/* target 0x18004214a original byte 0x48 replaced by RET */
/* target 0x18004219b original byte 0x48 replaced by RET */
/* target 0x1800421d5 original byte 0x48 replaced by RET */
/* target 0x18004220d original byte 0x48 replaced by RET */
/* target 0x18010c398 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00034_18004114f(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  ulonglong uVar10;
  longlong lVar11;
  longlong lVar12;
  longlong lVar13;
  code *pcVar14;
  bool bVar15;
  longlong *in_stack_00000048;
  longlong in_stack_00000070;
  code *pcStack0000000000000078;
  ulonglong in_stack_00000080;
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
  
  lVar11 = *in_stack_00000048;
  pcStack0000000000000078 = *(code **)(lVar11 + 0x390);
  if (DAT_1804b52e8 == 0) {
    uVar9 = FUN_180019ff0(in_stack_00000048);
    DAT_1804b52e8 =
         (**(code **)(lVar11 + 0x388))
                   (in_stack_00000048,uVar9,&DAT_180493f72,
                    "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                   );
  }
  lVar3 = DAT_1804b52e8;
  uVar9 = DAT_1804b5208;
  uVar6 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00000808,1);
  lVar11 = *in_stack_00000048;
  pcVar14 = *(code **)(lVar11 + 0x390);
  lVar13 = DAT_1804b4d30;
  if (DAT_1804b4d30 == 0) {
    uVar7 = (**(code **)(lVar11 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
    lVar13 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
    lVar12 = 0;
    LOCK();
    bVar15 = DAT_1804b4d30 != 0;
    lVar4 = lVar13;
    if (bVar15) {
      lVar12 = DAT_1804b4d30;
      lVar4 = DAT_1804b4d30;
    }
    DAT_1804b4d30 = lVar4;
    UNLOCK();
    if (bVar15) {
      (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar13);
      lVar13 = lVar12;
    }
  }
  if (DAT_1804b4d38 == 0) {
    uVar7 = FUN_1800024f0(in_stack_00000048);
    DAT_1804b4d38 =
         (**(code **)(*in_stack_00000048 + 0x388))
                   (in_stack_00000048,uVar7,"fromMethodDescriptorString",
                    "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;");
  }
  lVar12 = DAT_1804b4d38;
  uVar7 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00003748,5);
  lVar11 = *in_stack_00000048;
  pcVar1 = *(code **)(lVar11 + 0x110);
  if (DAT_1804b4d40 == 0) {
    uVar8 = FUN_18000fbd0(in_stack_00000048);
    DAT_1804b4d40 =
         (**(code **)(lVar11 + 0x108))
                   (in_stack_00000048,uVar8,"getClassLoader","()Ljava/lang/ClassLoader;");
  }
  uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000090,DAT_1804b4d40);
  (*pcVar14)(in_stack_00000048,lVar13,lVar12,uVar7,uVar8);
  uVar9 = (*pcStack0000000000000078)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar6);
  DAT_1804ad0b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804ad0b8,in_stack_000000b0);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar14 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x52de);
  (*pcVar14)(in_stack_00000048,uVar6,0,uVar7);
  pcVar14 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar14)(in_stack_00000048,uVar6,1,uVar7);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar9,in_stack_000000a8,uVar6)
  ;
  uVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) &&
     (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
    pcVar14 = (code *)(uVar10 ^ in_stack_00000080);
    if (DAT_1804ad0c0 == 0) {
      lVar11 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar11 + 0x390);
      if (DAT_1804b52e8 == 0) {
        uVar9 = FUN_180019ff0(in_stack_00000048);
        DAT_1804b52e8 =
             (**(code **)(lVar11 + 0x388))
                       (in_stack_00000048,uVar9,&DAT_180493f72,
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                       );
      }
      lVar3 = DAT_1804b52e8;
      uVar9 = DAT_1804b5208;
      uVar6 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00000806,1);
      lVar11 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar11 + 0x390);
      lVar13 = DAT_1804b4d30;
      if (DAT_1804b4d30 == 0) {
        uVar7 = (**(code **)(lVar11 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
        lVar13 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
        lVar12 = 0;
        LOCK();
        bVar15 = DAT_1804b4d30 != 0;
        lVar4 = lVar13;
        if (bVar15) {
          lVar12 = DAT_1804b4d30;
          lVar4 = DAT_1804b4d30;
        }
        DAT_1804b4d30 = lVar4;
        UNLOCK();
        if (bVar15) {
          (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar13);
          lVar13 = lVar12;
        }
      }
      pcStack0000000000000078 = pcVar14;
      if (DAT_1804b4d38 == 0) {
        uVar7 = FUN_1800024f0(in_stack_00000048);
        DAT_1804b4d38 =
             (**(code **)(*in_stack_00000048 + 0x388))
                       (in_stack_00000048,uVar7,"fromMethodDescriptorString",
                        "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;");
      }
      lVar12 = DAT_1804b4d38;
      uVar7 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00003738,5);
      lVar11 = *in_stack_00000048;
      pcVar14 = *(code **)(lVar11 + 0x110);
      if (DAT_1804b4d40 == 0) {
        uVar8 = FUN_18000fbd0(in_stack_00000048);
        DAT_1804b4d40 =
             (**(code **)(lVar11 + 0x108))
                       (in_stack_00000048,uVar8,"getClassLoader","()Ljava/lang/ClassLoader;");
      }
      uVar8 = (*pcVar14)(in_stack_00000048,in_stack_00000090,DAT_1804b4d40);
      (*pcVar2)(in_stack_00000048,lVar13,lVar12,uVar7,uVar8);
      uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar6);
      DAT_1804ad0c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      pcVar14 = pcStack0000000000000078;
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804ad0c0,in_stack_000000b0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x37bd);
    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       in_stack_00000070 % 0x3c46d ^ in_stack_000000c0 ^ 0xf6ba3f05ffba6c4);
    (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
    lVar11 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) &&
       (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
      pcVar14 = pcVar14 + -lVar11;
      if (DAT_1804ad0c8 == 0) {
        lVar11 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar11 + 0x390);
        if (DAT_1804b52e8 == 0) {
          uVar9 = FUN_180019ff0(in_stack_00000048);
          DAT_1804b52e8 =
               (**(code **)(lVar11 + 0x388))
                         (in_stack_00000048,uVar9,&DAT_180493f72,
                          "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                         );
        }
        lVar3 = DAT_1804b52e8;
        uVar9 = DAT_1804b5208;
        uVar6 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00000804,1);
        lVar11 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar11 + 0x390);
        lVar13 = DAT_1804b4d30;
        if (DAT_1804b4d30 == 0) {
          uVar7 = (**(code **)(lVar11 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
          lVar13 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7);
          lVar12 = 0;
          LOCK();
          bVar15 = DAT_1804b4d30 != 0;
          lVar4 = lVar13;
          if (bVar15) {
            lVar12 = DAT_1804b4d30;
            lVar4 = DAT_1804b4d30;
          }
          DAT_1804b4d30 = lVar4;
          UNLOCK();
          if (bVar15) {
            (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar13);
            lVar13 = lVar12;
          }
        }
        pcStack0000000000000078 = pcVar14;
        if (DAT_1804b4d38 == 0) {
          uVar7 = FUN_1800024f0(in_stack_00000048);
          DAT_1804b4d38 =
               (**(code **)(*in_stack_00000048 + 0x388))
                         (in_stack_00000048,uVar7,"fromMethodDescriptorString",
                          "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;"
                         );
        }
        lVar12 = DAT_1804b4d38;
        uVar7 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00003728,5);
        lVar11 = *in_stack_00000048;
        pcVar14 = *(code **)(lVar11 + 0x110);
        if (DAT_1804b4d40 == 0) {
          uVar8 = FUN_18000fbd0(in_stack_00000048);
          DAT_1804b4d40 =
               (**(code **)(lVar11 + 0x108))
                         (in_stack_00000048,uVar8,"getClassLoader","()Ljava/lang/ClassLoader;");
        }
        uVar8 = (*pcVar14)(in_stack_00000048,in_stack_00000090,DAT_1804b4d40);
        (*pcVar2)(in_stack_00000048,lVar13,lVar12,uVar7,uVar8);
        uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar3,uVar9,uVar6);
        DAT_1804ad0c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
        pcVar14 = pcStack0000000000000078;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ad0c8,in_stack_000000b0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x7b12);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         in_stack_00000070 % 0xddd62 ^ in_stack_000000c0 ^ 0x3b69f0e12317b9d4);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (((cVar5 == '\0') && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0'))
         && (cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar5 == '\0')) {
        if (DAT_1804ad0d0 == 0) {
          lVar11 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar11 + 0x390);
          if (DAT_1804b52e8 == 0) {
            uVar9 = FUN_180019ff0(in_stack_00000048);
            DAT_1804b52e8 =
                 (**(code **)(lVar11 + 0x388))
                           (in_stack_00000048,uVar9,&DAT_180493f72,
                            "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                           );
          }
          lVar13 = DAT_1804b52e8;
          uVar9 = DAT_1804b5208;
          uVar6 = (**(code **)(lVar11 + 0x518))(in_stack_00000048,&stack0x00000802,1);
          lVar3 = DAT_1804b4d38;
          lVar11 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          pcStack0000000000000078 = pcVar14;
          uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00003718,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
          (*pcVar2)(in_stack_00000048,lVar11,lVar3,uVar7,uVar8);
          uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar13,uVar9,uVar6);
          DAT_1804ad0d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ad0d0,in_stack_000000b0);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar14 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x1e7b);
        (*pcVar14)(in_stack_00000048,uVar6,0,uVar7);
        pcVar14 = *(code **)(*in_stack_00000048 + 0x570);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           in_stack_00000070 % 0x706a3 ^ in_stack_000000c0 ^ 0x7e53ca3a0c86ba8d);
        (*pcVar14)(in_stack_00000048,uVar6,1,uVar7);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          if (-1 < (longlong)in_stack_000000c0) {
            return;
          }
          return;
        }
      }
    }
  }
  return;
}

