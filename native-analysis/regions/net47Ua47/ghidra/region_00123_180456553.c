/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=123 entry=180456553 blocks=64 maxInstructions=50000 */

/* cut edge: 0x180456553 -> 0x180473ae8 */
/* cut edge: 0x180456745 -> 0x180473ae8 */
/* cut edge: 0x180456837 -> 0x180473ae8 */
/* cut edge: 0x18045684d -> 0x180473ae8 */
/* cut edge: 0x180456a36 -> 0x180473ae8 */
/* cut edge: 0x180456b28 -> 0x180473ae8 */
/* cut edge: 0x180456b3e -> 0x180473ae8 */
/* cut edge: 0x180456d27 -> 0x180473ae8 */
/* cut edge: 0x180456e1c -> 0x180473ae8 */
/* cut edge: 0x180456e32 -> 0x180473ae8 */
/* cut edge: 0x18045702b -> 0x180473ae8 */
/* cut edge: 0x180457128 -> 0x1804539a0 */
/* cut edge: 0x180457137 -> 0x1804564f0 */
/* cut edge: 0x18045753d -> 0x180457548 */
/* target 0x1804539a0 original byte 0x48 replaced by RET */
/* target 0x1804564f0 original byte 0x45 replaced by RET */
/* target 0x180457548 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00123_180456553(void)

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
  longlong in_stack_00000118;
  short in_stack_00004280;
  
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar7 == '\0') {
    if (DAT_1804b4498 == 0) {
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
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003a8,1);
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
      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000011d0,5);
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
      DAT_1804b4498 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
    }
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b4498,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x3e9e);
    (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       in_stack_00000068 % 0x3f22a ^ in_stack_000000c8 ^ 0xe4b309d7d0c2c2fa);
    (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
       (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
      if (DAT_1804b44a0 == 0) {
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
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003a6,1);
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
        uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000011c0,5);
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
        DAT_1804b44a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
      }
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b44a0,in_stack_000000a0);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x516c);
      (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                         in_stack_00000068 % 0xa1204 ^ in_stack_000000c8 ^ 0x800a39b3915e4aa7);
      (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0'))
         && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
        if (DAT_1804b44a8 == 0) {
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
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003a4,1);
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
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000011b0,5);
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
          DAT_1804b44a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b44a8,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x1059);
        (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           in_stack_00000068 % 0x36b2b ^ in_stack_000000c8 ^ 0xc9d46adccea6257c);
        (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')
            ) && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
          if (DAT_1804b44b0 == 0) {
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
            uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000003a2,1);
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
            uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000011a0,5);
            lVar1 = *in_stack_00000048;
            pcVar4 = *(code **)(lVar1 + 0x110);
            if (DAT_1804b4d40 == 0) {
              uVar10 = FUN_18000fbd0(in_stack_00000048);
              DAT_1804b4d40 =
                   (**(code **)(lVar1 + 0x108))
                             (in_stack_00000048,uVar10,"getClassLoader","()Ljava/lang/ClassLoader;")
              ;
            }
            uVar10 = (*pcVar4)(in_stack_00000048,in_stack_00000088,DAT_1804b4d40);
            (*pcVar3)(in_stack_00000048,lVar13,lVar12,uVar9,uVar10);
            uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000088,lVar5,uVar11,uVar8);
            DAT_1804b44b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b44b0,in_stack_000000a0);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
          ;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x4536);
          (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                             in_stack_00000068 % 0x5f3d5 ^ in_stack_000000c8 ^ 0xb033c687286da132);
          (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
          (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 == '\0') {
            if (in_stack_00000118 == 0) {
              return;
            }
            if (in_stack_00004280 == 0) {
              return;
            }
            return;
          }
        }
      }
    }
  }
  return;
}

