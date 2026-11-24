/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=213 entry=1800f4124 blocks=58 maxInstructions=100000 */

/* cut edge: 0x1800f41a9 -> 0x18014d3db */
/* cut edge: 0x1800f429e -> 0x18014d3db */
/* cut edge: 0x1800f42b4 -> 0x18014d3db */
/* cut edge: 0x1800f4498 -> 0x18014d3db */
/* cut edge: 0x1800f458d -> 0x18014d3db */
/* cut edge: 0x1800f45a3 -> 0x18014d3db */
/* cut edge: 0x1800f478f -> 0x18014d3db */
/* cut edge: 0x1800f4884 -> 0x1800600f1 */
/* cut edge: 0x1800f489b -> 0x1801253d7 */
/* cut edge: 0x1800f48a0 -> 0x1800f3d61 */
/* cut edge: 0x1800f48d3 -> 0x1800f3dc2 */
/* cut edge: 0x1800f4908 -> 0x1800f3dc2 */
/* cut edge: 0x1800f4924 -> 0x1800f3dd2 */
/* cut edge: 0x1800f495d -> 0x1800f3e2b */
/* cut edge: 0x1800f4995 -> 0x1800f4056 */
/* cut edge: 0x1800f49c8 -> 0x1800f40ba */
/* cut edge: 0x1800f49fd -> 0x1800f40ba */
/* cut edge: 0x1800f4a19 -> 0x1800f40ca */
/* cut edge: 0x1800f4c75 -> 0x1800f5c13 */
/* cut edge: 0x1800f4c94 -> 0x18014d3db */
/* cut edge: 0x1800f4cbd -> 0x18014d3db */
/* cut edge: 0x1800f4ce0 -> 0x18014d3db */
/* cut edge: 0x1800f4d40 -> 0x1800f5c49 */
/* cut edge: 0x1800f4d67 -> 0x1800f5c7c */
/* cut edge: 0x1800f4dc8 -> 0x1800f5cca */
/* cut edge: 0x1800f4de0 -> 0x1800f5d03 */
/* cut edge: 0x1800f4edc -> 0x18014d3db */
/* cut edge: 0x1800f5047 -> 0x18014d3db */
/* cut edge: 0x1800f505d -> 0x18014d3db */
/* cut edge: 0x1800f5073 -> 0x1800f5238 */
/* cut edge: 0x1800f50c5 -> 0x1800f5d3a */
/* cut edge: 0x1800f50ec -> 0x1800f5d6d */
/* cut edge: 0x1800f50ec -> 0x1800f514d */
/* target 0x1800600f1 original byte 0x48 replaced by RET */
/* target 0x1800f3d61 original byte 0x48 replaced by RET */
/* target 0x1800f3dc2 original byte 0x48 replaced by RET */
/* target 0x1800f3dd2 original byte 0x48 replaced by RET */
/* target 0x1800f3e2b original byte 0x48 replaced by RET */
/* target 0x1800f4056 original byte 0x48 replaced by RET */
/* target 0x1800f40ba original byte 0x48 replaced by RET */
/* target 0x1800f40ca original byte 0x48 replaced by RET */
/* target 0x1800f514d original byte 0x48 replaced by RET */
/* target 0x1800f5238 original byte 0x48 replaced by RET */
/* target 0x1800f5c13 original byte 0x48 replaced by RET */
/* target 0x1800f5c49 original byte 0x48 replaced by RET */
/* target 0x1800f5c7c original byte 0x48 replaced by RET */
/* target 0x1800f5cca original byte 0x48 replaced by RET */
/* target 0x1800f5d03 original byte 0x48 replaced by RET */
/* target 0x1800f5d3a original byte 0x48 replaced by RET */
/* target 0x1800f5d6d original byte 0x48 replaced by RET */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00213_1800f4124(void)

{
  code *pcVar1;
  longlong lVar2;
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
  code *unaff_R14;
  bool bVar14;
  longlong *in_stack_00000048;
  longlong in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000070;
  code *in_stack_00000078;
  undefined8 in_stack_00000080;
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
  code *in_stack_000000e0;
  longlong in_stack_000000f8;
  
  (*unaff_R14)(in_stack_00000048,in_stack_00000090);
  (*in_stack_000000e0)(in_stack_00000048);
  uVar8 = (*in_stack_00000078)
                    (in_stack_00000048,in_stack_00000090,in_stack_00000080,in_stack_00000060,
                     in_stack_00000070);
  DAT_1804ad010 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804ad010,in_stack_000000b0);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2257);
  (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,in_stack_000000a8,uVar9)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
     (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
    if (DAT_1804ad018 == 0) {
      lVar2 = *in_stack_00000048;
      pcVar1 = *(code **)(lVar2 + 0x390);
      if (DAT_1804b52e8 == 0) {
        uVar8 = FUN_180019ff0(in_stack_00000048);
        DAT_1804b52e8 =
             (**(code **)(lVar2 + 0x388))
                       (in_stack_00000048,uVar8,&DAT_180493f72,
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                       );
      }
      lVar5 = DAT_1804b52e8;
      uVar8 = DAT_1804b5208;
      uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000830,1);
      lVar2 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar2 + 0x390);
      lVar13 = DAT_1804b4d30;
      if (DAT_1804b4d30 == 0) {
        uVar10 = (**(code **)(lVar2 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
        lVar13 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
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
        uVar10 = FUN_1800024f0(in_stack_00000048);
        DAT_1804b4d38 =
             (**(code **)(*in_stack_00000048 + 0x388))
                       (in_stack_00000048,uVar10,"fromMethodDescriptorString",
                        "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;");
      }
      lVar12 = DAT_1804b4d38;
      uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003878,5);
      lVar2 = *in_stack_00000048;
      pcVar4 = *(code **)(lVar2 + 0x110);
      if (DAT_1804b4d40 == 0) {
        uVar11 = FUN_18000fbd0(in_stack_00000048);
        DAT_1804b4d40 =
             (**(code **)(lVar2 + 0x108))
                       (in_stack_00000048,uVar11,"getClassLoader","()Ljava/lang/ClassLoader;");
      }
      uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,DAT_1804b4d40);
      (*pcVar3)(in_stack_00000048,lVar13,lVar12,uVar10,uVar11);
      uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar8,uVar9);
      DAT_1804ad018 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
    }
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804ad018,in_stack_000000b0);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x598f);
    (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                        in_stack_00000058 % 0x66203 ^ in_stack_000000c0 ^ 0xe327c8c6be57e3af);
    (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
       (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
      if (DAT_1804ad020 == 0) {
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        if (DAT_1804b52e8 == 0) {
          uVar8 = FUN_180019ff0(in_stack_00000048);
          DAT_1804b52e8 =
               (**(code **)(lVar2 + 0x388))
                         (in_stack_00000048,uVar8,&DAT_180493f72,
                          "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                         );
        }
        lVar5 = DAT_1804b52e8;
        uVar8 = DAT_1804b5208;
        uVar9 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000082e,1);
        lVar2 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar2 + 0x390);
        lVar13 = DAT_1804b4d30;
        if (DAT_1804b4d30 == 0) {
          uVar10 = (**(code **)(lVar2 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
          lVar13 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
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
          uVar10 = FUN_1800024f0(in_stack_00000048);
          DAT_1804b4d38 =
               (**(code **)(*in_stack_00000048 + 0x388))
                         (in_stack_00000048,uVar10,"fromMethodDescriptorString",
                          "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;"
                         );
        }
        lVar12 = DAT_1804b4d38;
        uVar10 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00003868,5);
        lVar2 = *in_stack_00000048;
        pcVar4 = *(code **)(lVar2 + 0x110);
        if (DAT_1804b4d40 == 0) {
          uVar11 = FUN_18000fbd0(in_stack_00000048);
          DAT_1804b4d40 =
               (**(code **)(lVar2 + 0x108))
                         (in_stack_00000048,uVar11,"getClassLoader","()Ljava/lang/ClassLoader;");
        }
        uVar11 = (*pcVar4)(in_stack_00000048,in_stack_00000090,DAT_1804b4d40);
        (*pcVar3)(in_stack_00000048,lVar13,lVar12,uVar10,uVar11);
        uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar5,uVar8,uVar9);
        DAT_1804ad020 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ad020,in_stack_000000b0);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0xbb0);
      (*pcVar1)(in_stack_00000048,uVar9,0,uVar10);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                          in_stack_00000058 % 0xc69fc ^ in_stack_000000c0 ^ 0xfaaa3a342a2308d5);
      (*pcVar1)(in_stack_00000048,uVar9,1,uVar10);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 == '\0') {
        if (in_stack_000000f8 != 0) {
          return;
        }
        return;
      }
    }
  }
  return;
}

