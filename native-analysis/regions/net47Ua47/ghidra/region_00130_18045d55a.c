/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=130 entry=18045d55a blocks=64 maxInstructions=50000 */

/* cut edge: 0x18045d6ac -> 0x180473ae8 */
/* cut edge: 0x18045d79e -> 0x180473ae8 */
/* cut edge: 0x18045d7b4 -> 0x180473ae8 */
/* cut edge: 0x18045d998 -> 0x180473ae8 */
/* cut edge: 0x18045da8a -> 0x180473ae8 */
/* cut edge: 0x18045daa0 -> 0x180473ae8 */
/* cut edge: 0x18045dc81 -> 0x180473ae8 */
/* cut edge: 0x18045dd76 -> 0x180473ae8 */
/* cut edge: 0x18045dd8c -> 0x180473ae8 */
/* cut edge: 0x18045de87 -> 0x18045e546 */
/* cut edge: 0x18045de97 -> 0x18045e580 */
/* cut edge: 0x18045df7b -> 0x180473ae8 */
/* cut edge: 0x18045e070 -> 0x18045f7e5 */
/* cut edge: 0x18045e08b -> 0x18045d1c0 */
/* cut edge: 0x18045e09e -> 0x18045f7e5 */
/* cut edge: 0x18045e0b0 -> 0x18045e5c0 */
/* cut edge: 0x18045e0bd -> 0x18045d26d */
/* cut edge: 0x18045e0f8 -> 0x18045d2c9 */
/* cut edge: 0x18045e12d -> 0x18045d2c9 */
/* cut edge: 0x18045e149 -> 0x18045d2d9 */
/* cut edge: 0x18045e182 -> 0x18045d331 */
/* target 0x18045d1c0 original byte 0x48 replaced by RET */
/* target 0x18045d26d original byte 0x48 replaced by RET */
/* target 0x18045d2c9 original byte 0x48 replaced by RET */
/* target 0x18045d2d9 original byte 0x48 replaced by RET */
/* target 0x18045d331 original byte 0x48 replaced by RET */
/* target 0x18045e546 original byte 0x48 replaced by RET */
/* target 0x18045e580 original byte 0x48 replaced by RET */
/* target 0x18045e5c0 original byte 0x48 replaced by RET */
/* target 0x18045f7e5 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00130_18045d55a(code *param_1)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  code *pcVar5;
  longlong lVar6;
  char cVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  longlong lVar11;
  longlong lVar12;
  longlong unaff_RSI;
  bool bVar13;
  longlong *in_stack_00000048;
  code *pcStack0000000000000060;
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
  code *in_stack_000000f0;
  char in_stack_00000118;
  char in_stack_00000140;
  
  pcVar4 = DAT_1804b59f0;
  pcStack0000000000000060 = param_1;
  uVar8 = (**(code **)(unaff_RSI + 0x518))(in_stack_00000048,&stack0x0000035c,1);
  lVar1 = *in_stack_00000048;
  pcVar2 = *(code **)(lVar1 + 0x390);
  lVar12 = DAT_1804b4d30;
  if (DAT_1804b4d30 == 0) {
    uVar9 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
    lVar12 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
    lVar11 = 0;
    LOCK();
    bVar13 = DAT_1804b4d30 != 0;
    lVar6 = lVar12;
    if (bVar13) {
      lVar11 = DAT_1804b4d30;
      lVar6 = DAT_1804b4d30;
    }
    DAT_1804b4d30 = lVar6;
    UNLOCK();
    if (bVar13) {
      (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar12);
      lVar12 = lVar11;
    }
  }
  if (DAT_1804b4d38 == 0) {
    uVar9 = FUN_1800024f0(in_stack_00000048);
    DAT_1804b4d38 =
         (**(code **)(*in_stack_00000048 + 0x388))
                   (in_stack_00000048,uVar9,"fromMethodDescriptorString",
                    "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;");
  }
  lVar11 = DAT_1804b4d38;
  uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001030,5);
  lVar1 = *in_stack_00000048;
  pcVar3 = *(code **)(lVar1 + 0x110);
  if (DAT_1804b4d40 == 0) {
    uVar10 = FUN_18000fbd0(in_stack_00000048);
    DAT_1804b4d40 =
         (**(code **)(lVar1 + 0x108))
                   (in_stack_00000048,uVar10,"getClassLoader","()Ljava/lang/ClassLoader;");
  }
  uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000088,DAT_1804b4d40);
  (*pcVar2)(in_stack_00000048,lVar12,lVar11,uVar9,uVar10);
  uVar8 = (*in_stack_000000f0)
                    (in_stack_00000048,in_stack_00000088,pcStack0000000000000060,pcVar4,uVar8);
  DAT_1804b45c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,DAT_1804b45c8,in_stack_000000a0);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x3280);
  (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
  pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000080,in_stack_00000098);
  (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar8,in_stack_000000a8,uVar9)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
     (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
    if (DAT_1804b45d0 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      if (DAT_1804b5a08 == (code *)0x0) {
        uVar8 = FUN_1803334a0(in_stack_00000048);
        DAT_1804b5a08 =
             (code *)(**(code **)(lVar1 + 0x388))
                               (in_stack_00000048,uVar8,&DAT_180493f72,
                                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                               );
      }
      pcVar3 = DAT_1804b5a08;
      pcStack0000000000000060 = DAT_1804b59f0;
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000035a,1);
      lVar1 = *in_stack_00000048;
      pcVar4 = *(code **)(lVar1 + 0x390);
      lVar12 = DAT_1804b4d30;
      if (DAT_1804b4d30 == 0) {
        uVar9 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
        lVar12 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        lVar11 = 0;
        LOCK();
        bVar13 = DAT_1804b4d30 != 0;
        lVar6 = lVar12;
        if (bVar13) {
          lVar11 = DAT_1804b4d30;
          lVar6 = DAT_1804b4d30;
        }
        DAT_1804b4d30 = lVar6;
        UNLOCK();
        if (bVar13) {
          (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar12);
          lVar12 = lVar11;
        }
      }
      if (DAT_1804b4d38 == 0) {
        uVar9 = FUN_1800024f0(in_stack_00000048);
        DAT_1804b4d38 =
             (**(code **)(*in_stack_00000048 + 0x388))
                       (in_stack_00000048,uVar9,"fromMethodDescriptorString",
                        "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;");
      }
      lVar11 = DAT_1804b4d38;
      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001020,5);
      lVar1 = *in_stack_00000048;
      pcVar5 = *(code **)(lVar1 + 0x110);
      if (DAT_1804b4d40 == 0) {
        uVar10 = FUN_18000fbd0(in_stack_00000048);
        DAT_1804b4d40 =
             (**(code **)(lVar1 + 0x108))
                       (in_stack_00000048,uVar10,"getClassLoader","()Ljava/lang/ClassLoader;");
      }
      uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000088,DAT_1804b4d40);
      (*pcVar4)(in_stack_00000048,lVar12,lVar11,uVar9,uVar10);
      uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,pcVar3,pcStack0000000000000060,uVar8);
      DAT_1804b45d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
    }
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b45d0,in_stack_000000a0);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1733);
    (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                        in_stack_00000068 % 0xe806d ^ in_stack_000000c8 ^ 0x93fa79740e89e8bd);
    (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
       (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
      if (DAT_1804b45d8 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        if (DAT_1804b5a08 == (code *)0x0) {
          uVar8 = FUN_1803334a0(in_stack_00000048);
          DAT_1804b5a08 =
               (code *)(**(code **)(lVar1 + 0x388))
                                 (in_stack_00000048,uVar8,&DAT_180493f72,
                                  "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                                 );
        }
        pcVar3 = DAT_1804b59f0;
        pcStack0000000000000060 = DAT_1804b5a08;
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000358,1);
        lVar1 = *in_stack_00000048;
        pcVar4 = *(code **)(lVar1 + 0x390);
        lVar12 = DAT_1804b4d30;
        if (DAT_1804b4d30 == 0) {
          uVar9 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
          lVar12 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          lVar11 = 0;
          LOCK();
          bVar13 = DAT_1804b4d30 != 0;
          lVar6 = lVar12;
          if (bVar13) {
            lVar11 = DAT_1804b4d30;
            lVar6 = DAT_1804b4d30;
          }
          DAT_1804b4d30 = lVar6;
          UNLOCK();
          if (bVar13) {
            (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar12);
            lVar12 = lVar11;
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
        lVar11 = DAT_1804b4d38;
        uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001010,5);
        lVar1 = *in_stack_00000048;
        pcVar5 = *(code **)(lVar1 + 0x110);
        if (DAT_1804b4d40 == 0) {
          uVar10 = FUN_18000fbd0(in_stack_00000048);
          DAT_1804b4d40 =
               (**(code **)(lVar1 + 0x108))
                         (in_stack_00000048,uVar10,"getClassLoader","()Ljava/lang/ClassLoader;");
        }
        uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000088,DAT_1804b4d40);
        (*pcVar4)(in_stack_00000048,lVar12,lVar11,uVar9,uVar10);
        uVar8 = (*pcVar2)(in_stack_00000048,in_stack_00000088,pcStack0000000000000060,pcVar3,uVar8);
        DAT_1804b45d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804b45d8,in_stack_000000a0);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x546b);
      (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                          in_stack_00000068 % 0x9a3df ^ in_stack_000000c8 ^ 0xb2762d75e39bd51a);
      (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0'))
         && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
        if (DAT_1804b45e0 == 0) {
          lVar1 = *in_stack_00000048;
          pcStack0000000000000060 = *(code **)(lVar1 + 0x390);
          if (DAT_1804b5a08 == (code *)0x0) {
            uVar8 = FUN_1803334a0(in_stack_00000048);
            DAT_1804b5a08 =
                 (code *)(**(code **)(lVar1 + 0x388))
                                   (in_stack_00000048,uVar8,&DAT_180493f72,
                                    "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                                   );
          }
          pcVar3 = DAT_1804b5a08;
          pcVar4 = DAT_1804b59f0;
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000356,1);
          lVar1 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar1 + 0x390);
          if (DAT_1804b4d30 == 0) {
            uVar9 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
            in_stack_000000f0 =
                 (code *)(**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
            lVar12 = 0;
            LOCK();
            bVar13 = DAT_1804b4d30 != 0;
            lVar11 = (longlong)in_stack_000000f0;
            if (bVar13) {
              lVar12 = DAT_1804b4d30;
              lVar11 = DAT_1804b4d30;
            }
            DAT_1804b4d30 = lVar11;
            UNLOCK();
            if (bVar13) {
              (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,in_stack_000000f0);
              in_stack_000000f0 = (code *)lVar12;
            }
          }
          else {
            in_stack_000000f0 = (code *)DAT_1804b4d30;
          }
          lVar12 = DAT_1804b4d38;
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001000,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar2)(in_stack_00000048,in_stack_000000f0,lVar12,uVar9,uVar10);
          uVar8 = (*pcStack0000000000000060)
                            (in_stack_00000048,in_stack_00000088,pcVar3,pcVar4,uVar8);
          DAT_1804b45e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b45e0,in_stack_000000a0);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2bea);
        (*pcVar2)(in_stack_00000048,uVar9,0,uVar10);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                           (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                            in_stack_00000068 % 0x943d7 ^ in_stack_000000c8 ^ 0x82f944c48bfa7ee7);
        (*pcVar2)(in_stack_00000048,uVar9,1,uVar10);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000000d8);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar7 == '\0') {
          if ((in_stack_00000140 != '\0') && (in_stack_00000118 != '\0')) {
            return;
          }
          return;
        }
      }
    }
  }
  return;
}

