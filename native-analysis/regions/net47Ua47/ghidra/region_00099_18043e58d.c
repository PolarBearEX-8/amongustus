/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=99 entry=18043e58d blocks=67 maxInstructions=50000 */

/* cut edge: 0x18043e58d -> 0x180473ae8 */
/* cut edge: 0x18043e5a3 -> 0x180473ae8 */
/* cut edge: 0x18043e77e -> 0x180473ae8 */
/* cut edge: 0x18043e878 -> 0x180473ae8 */
/* cut edge: 0x18043e88e -> 0x180473ae8 */
/* cut edge: 0x18043ea6c -> 0x180473ae8 */
/* cut edge: 0x18043eb61 -> 0x180473ae8 */
/* cut edge: 0x18043eb77 -> 0x180473ae8 */
/* cut edge: 0x18043ebdf -> 0x18043f554 */
/* cut edge: 0x18043ec0b -> 0x18043f58f */
/* cut edge: 0x18043ec6f -> 0x18043f5e9 */
/* cut edge: 0x18043ec7f -> 0x18043f622 */
/* cut edge: 0x18043ed60 -> 0x180473ae8 */
/* cut edge: 0x18043ee57 -> 0x1803dd67b */
/* cut edge: 0x18043ee83 -> 0x18043d787 */
/* cut edge: 0x18043eebe -> 0x18043d7e6 */
/* cut edge: 0x18043eef3 -> 0x18043d7e6 */
/* cut edge: 0x18043ef0f -> 0x18043d7f6 */
/* cut edge: 0x18043ef48 -> 0x18043d84e */
/* cut edge: 0x18043ef7f -> 0x18043da70 */
/* cut edge: 0x18043efb5 -> 0x18043dacf */
/* cut edge: 0x18043efea -> 0x18043dacf */
/* cut edge: 0x18043f006 -> 0x18043dadf */
/* cut edge: 0x18043f03f -> 0x18043db37 */
/* cut edge: 0x18043f075 -> 0x18043dd5a */
/* cut edge: 0x18043f0ab -> 0x18043ddb6 */
/* cut edge: 0x18043f0e0 -> 0x18043ddb6 */
/* cut edge: 0x18043f0fc -> 0x18043ddc6 */
/* cut edge: 0x18043f135 -> 0x18043de1e */
/* cut edge: 0x18043f16b -> 0x18043e041 */
/* cut edge: 0x18043f1a3 -> 0x18043e0a0 */
/* cut edge: 0x18043f1d8 -> 0x18043e0a0 */
/* cut edge: 0x18043f1f4 -> 0x18043e0b0 */
/* cut edge: 0x18043f22d -> 0x18043e108 */
/* cut edge: 0x18043f263 -> 0x18043e346 */
/* cut edge: 0x18043f29e -> 0x18043e3a5 */
/* cut edge: 0x18043f2d3 -> 0x18043e3a5 */
/* cut edge: 0x18043f2ef -> 0x18043e3b5 */
/* cut edge: 0x18043f328 -> 0x18043e40d */
/* target 0x1803dd67b original byte 0x48 replaced by RET */
/* target 0x18043d787 original byte 0x48 replaced by RET */
/* target 0x18043d7e6 original byte 0x48 replaced by RET */
/* target 0x18043d7f6 original byte 0x48 replaced by RET */
/* target 0x18043d84e original byte 0x48 replaced by RET */
/* target 0x18043da70 original byte 0x48 replaced by RET */
/* target 0x18043dacf original byte 0x48 replaced by RET */
/* target 0x18043dadf original byte 0x48 replaced by RET */
/* target 0x18043db37 original byte 0x48 replaced by RET */
/* target 0x18043dd5a original byte 0x48 replaced by RET */
/* target 0x18043ddb6 original byte 0x48 replaced by RET */
/* target 0x18043ddc6 original byte 0x48 replaced by RET */
/* target 0x18043de1e original byte 0x48 replaced by RET */
/* target 0x18043e041 original byte 0x48 replaced by RET */
/* target 0x18043e0a0 original byte 0x48 replaced by RET */
/* target 0x18043e0b0 original byte 0x48 replaced by RET */
/* target 0x18043e108 original byte 0x48 replaced by RET */
/* target 0x18043e346 original byte 0x48 replaced by RET */
/* target 0x18043e3a5 original byte 0x48 replaced by RET */
/* target 0x18043e3b5 original byte 0x48 replaced by RET */
/* target 0x18043e40d original byte 0x48 replaced by RET */
/* target 0x18043f554 original byte 0x48 replaced by RET */
/* target 0x18043f58f original byte 0x48 replaced by RET */
/* target 0x18043f5e9 original byte 0x48 replaced by RET */
/* target 0x18043f622 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00099_18043e58d(void)

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
  undefined8 in_stack_000000d8;
  
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
    if (DAT_1804b4040 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      if (DAT_1804b5a08 == 0) {
        uVar11 = FUN_1803334a0(in_stack_00000048);
        DAT_1804b5a08 =
             (**(code **)(lVar1 + 0x388))
                       (in_stack_00000048,uVar11,&DAT_180493f72,
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                       );
      }
      lVar5 = DAT_1804b5a08;
      uVar11 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004be,1);
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
      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001a30,5);
      lVar1 = *in_stack_00000048;
      pcVar4 = *(code **)(lVar1 + 0x110);
      if (DAT_1804b4d40 == 0) {
        uVar10 = FUN_18000fbd0(in_stack_00000048);
        DAT_1804b4d40 =
             (**(code **)(lVar1 + 0x108))
                       (in_stack_00000048,uVar10,"getClassLoader","()Ljava/lang/ClassLoader;");
      }
      uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000088,DAT_1804b4d40);
      (*pcVar3)(in_stack_00000048,lVar13,lVar12,uVar9,uVar10);
      uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000088,lVar5,uVar11,uVar8);
      DAT_1804b4040 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
    }
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b4040,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x6d08);
    (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       in_stack_00000068 % 0xd6176 ^ in_stack_000000c8 ^ 0x4fc47a3e588b5016);
    (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
       (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
      if (DAT_1804b4048 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        if (DAT_1804b5a08 == 0) {
          uVar11 = FUN_1803334a0(in_stack_00000048);
          DAT_1804b5a08 =
               (**(code **)(lVar1 + 0x388))
                         (in_stack_00000048,uVar11,&DAT_180493f72,
                          "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                         );
        }
        lVar5 = DAT_1804b5a08;
        uVar11 = DAT_1804b59f0;
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000004bc,1);
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
                          "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;"
                         );
        }
        lVar12 = DAT_1804b4d38;
        uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001a20,5);
        lVar1 = *in_stack_00000048;
        pcVar4 = *(code **)(lVar1 + 0x110);
        if (DAT_1804b4d40 == 0) {
          uVar10 = FUN_18000fbd0(in_stack_00000048);
          DAT_1804b4d40 =
               (**(code **)(lVar1 + 0x108))
                         (in_stack_00000048,uVar10,"getClassLoader","()Ljava/lang/ClassLoader;");
        }
        uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000088,DAT_1804b4d40);
        (*pcVar3)(in_stack_00000048,lVar13,lVar12,uVar9,uVar10);
        uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000088,lVar5,uVar11,uVar8);
        DAT_1804b4048 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
      }
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b4048,in_stack_000000a0);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2af1);
      (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         in_stack_00000068 % 0xe5c06 ^ in_stack_000000c8 ^ 0x6f392cf24562f018);
      (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0'))
         && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), lVar1 = DAT_1804b5a08,
            uVar11 = DAT_1804b59f0, cVar7 == '\0')) {
        if (DAT_1804b4050 == 0) {
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000004ba,1);
          lVar13 = DAT_1804b4d38;
          lVar5 = DAT_1804b4d30;
          pcVar3 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001a10,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar3)(in_stack_00000048,lVar5,lVar13,uVar9,uVar10);
          uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000088,lVar1,uVar11,uVar8);
          DAT_1804b4050 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b4050,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x692c);
        (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0x88f90 ^ in_stack_000000c8 ^ 0x36a16c66e1a1d4b);
        (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 == '\0') {
          return;
        }
      }
    }
  }
  return;
}

