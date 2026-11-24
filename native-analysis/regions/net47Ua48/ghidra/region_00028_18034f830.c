/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=28 entry=18034f830 blocks=64 maxInstructions=50000 */

/* cut edge: 0x18034f9a5 -> 0x1803351d6 */
/* cut edge: 0x18034fa9b -> 0x1803351d6 */
/* cut edge: 0x18034fadf -> 0x1803351d6 */
/* cut edge: 0x18034fb23 -> 0x18034f820 */
/* cut edge: 0x18034fb3d -> 0x1803351d6 */
/* cut edge: 0x18034fcf9 -> 0x18035c06c */
/* cut edge: 0x18034fd14 -> 0x1803351d6 */
/* cut edge: 0x18034fec2 -> 0x1803351d6 */
/* cut edge: 0x18034ffd7 -> 0x1803351d6 */
/* cut edge: 0x18034fffd -> 0x1803351d6 */
/* cut edge: 0x180350041 -> 0x1803351d6 */
/* cut edge: 0x180350080 -> 0x1803351d6 */
/* cut edge: 0x1803500de -> 0x1803351d6 */
/* cut edge: 0x180350215 -> 0x1803351d6 */
/* cut edge: 0x180350260 -> 0x1803351d6 */
/* cut edge: 0x1803502a7 -> 0x1803351d6 */
/* cut edge: 0x1803502ee -> 0x1803351d6 */
/* cut edge: 0x180350314 -> 0x18035ac17 */
/* cut edge: 0x18035033b -> 0x18035ac4d */
/* cut edge: 0x18035039d -> 0x18035ac9e */
/* cut edge: 0x1803503ad -> 0x18035acd8 */
/* cut edge: 0x18035048c -> 0x1803351d6 */
/* cut edge: 0x180350579 -> 0x1803351d6 */
/* cut edge: 0x1803505bd -> 0x1803351d6 */
/* cut edge: 0x180350604 -> 0x1803351d6 */
/* cut edge: 0x18035062a -> 0x18035ad0f */
/* cut edge: 0x180350651 -> 0x18035ad45 */
/* cut edge: 0x1803506b3 -> 0x18035ad96 */
/* cut edge: 0x1803506c3 -> 0x18035add0 */
/* cut edge: 0x1803507a2 -> 0x1803351d6 */
/* cut edge: 0x1803507a2 -> 0x180350890 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18034f820 original byte 0x48 replaced by RET */
/* target 0x180350890 original byte 0x48 replaced by RET */
/* target 0x18035ac17 original byte 0x48 replaced by RET */
/* target 0x18035ac4d original byte 0x48 replaced by RET */
/* target 0x18035ac9e original byte 0x48 replaced by RET */
/* target 0x18035acd8 original byte 0x48 replaced by RET */
/* target 0x18035ad0f original byte 0x48 replaced by RET */
/* target 0x18035ad45 original byte 0x48 replaced by RET */
/* target 0x18035ad96 original byte 0x48 replaced by RET */
/* target 0x18035add0 original byte 0x48 replaced by RET */
/* target 0x18035c06c original byte 0x84 replaced by RET */


void region_00028_18034f830(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  ulonglong uVar4;
  longlong lVar5;
  char cVar6;
  int iVar7;
  int iVar8;
  undefined4 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  longlong lVar13;
  longlong lVar14;
  ulonglong uVar15;
  int unaff_R12D;
  bool bVar16;
  longlong *in_stack_00000048;
  undefined4 uStack0000000000000050;
  undefined4 uStack0000000000000054;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  code *in_stack_000000b0;
  code *in_stack_000000b8;
  code *in_stack_000000c0;
  ulonglong in_stack_000000c8;
  code *pcStack00000000000000d0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  code *in_stack_00000100;
  ulonglong in_stack_00000110;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00000130;
  ulonglong in_stack_000001b8;
  undefined8 in_stack_000001c0;
  ulonglong in_stack_000001f8;
  int in_stack_00000200;
  ulonglong in_stack_00000210;
  
  lVar1 = *in_stack_00000048;
  pcStack00000000000000d0 = *(code **)(lVar1 + 0x390);
  if (DAT_1804b59f8 == (code *)0x0) {
    uVar10 = FUN_1803334a0(in_stack_00000048);
    DAT_1804b59f8 =
         (code *)(**(code **)(lVar1 + 0x388))
                           (in_stack_00000048,uVar10,&DAT_180493f0a,
                            "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                           );
  }
  pcVar3 = DAT_1804b59f8;
  in_stack_000000b0 = DAT_1804b59f0;
  in_stack_000000b8 = (code *)(**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000074e,1);
  lVar1 = *in_stack_00000048;
  in_stack_000000c0 = *(code **)(lVar1 + 0x390);
  lVar14 = DAT_1804b4d30;
  if (DAT_1804b4d30 == 0) {
    uVar10 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
    lVar14 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
    lVar13 = 0;
    LOCK();
    bVar16 = DAT_1804b4d30 != 0;
    lVar5 = lVar14;
    if (bVar16) {
      lVar13 = DAT_1804b4d30;
      lVar5 = DAT_1804b4d30;
    }
    DAT_1804b4d30 = lVar5;
    UNLOCK();
    if (bVar16) {
      (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar14);
      lVar14 = lVar13;
    }
  }
  if (DAT_1804b4d38 == 0) {
    uVar10 = FUN_1800024f0(in_stack_00000048);
    DAT_1804b4d38 =
         (**(code **)(*in_stack_00000048 + 0x388))
                   (in_stack_00000048,uVar10,"fromMethodDescriptorString",
                    "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;");
  }
  lVar13 = DAT_1804b4d38;
  uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001de8,5);
  lVar1 = *in_stack_00000048;
  pcVar2 = *(code **)(lVar1 + 0x110);
  if (DAT_1804b4d40 == 0) {
    uVar12 = FUN_18000fbd0(in_stack_00000048);
    DAT_1804b4d40 =
         (**(code **)(lVar1 + 0x108))
                   (in_stack_00000048,uVar12,"getClassLoader","()Ljava/lang/ClassLoader;");
  }
  uVar12 = in_stack_00000058;
  uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000058,DAT_1804b4d40);
  (*in_stack_000000c0)(in_stack_00000048,lVar14,lVar13,uVar10,uVar11);
  uVar10 = (*pcStack00000000000000d0)
                     (in_stack_00000048,uVar12,pcVar3,in_stack_000000b0,in_stack_000000b8);
  DAT_1804b2280 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
  _uStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2280,in_stack_00000078);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
  (*pcVar3)(in_stack_00000048,uVar10,0,uVar12);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
  (*pcVar3)(in_stack_00000048,uVar10,1,uVar12);
  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar10);
  iVar7 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar6 == '\0') {
    if (unaff_R12D < iVar7) {
      (**(code **)(*in_stack_00000048 + 0x658))
                (in_stack_00000048,in_stack_00000130,unaff_R12D,1,&stack0x00000050);
      uVar9 = uStack0000000000000050;
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar6 == '\0') {
        _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar9);
        (**(code **)(*in_stack_00000048 + 0x698))
                  (in_stack_00000048,in_stack_000000a0,unaff_R12D,1,&stack0x00000050);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar6 == '\0') && (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar6 == '\0'))
        {
          return;
        }
      }
    }
    else {
      uVar15 = in_stack_000000c8 ^ 0x213f1d525a90dc48;
      in_stack_00000210 = in_stack_000000c8 ^ 0x190a0fbc7df4347f;
      in_stack_000001b8 = in_stack_000000c8 ^ 0x66196909fb02bc6e;
      in_stack_000001f8 = in_stack_000000c8 ^ 0x544cf874561d16cb;
      pcStack00000000000000d0 = (code *)(in_stack_000000c8 ^ 0x3775bb288fd83d82);
      in_stack_00000110 = in_stack_000000c8 ^ 0x24363e9aec7590d1;
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (in_stack_00000200 < 2) {
        return;
      }
      in_stack_00000120 = 0;
      in_stack_000001c0 = 0;
      if (cVar6 == '\0') {
        iVar7 = 0;
        do {
          if (DAT_1804b2288 == 0) {
            lVar1 = *in_stack_00000048;
            in_stack_000000b0 = *(code **)(lVar1 + 0x390);
            if (DAT_1804b59f8 == (code *)0x0) {
              uVar10 = FUN_1803334a0(in_stack_00000048);
              DAT_1804b59f8 =
                   (code *)(**(code **)(lVar1 + 0x388))
                                     (in_stack_00000048,uVar10,&DAT_180493f0a,
                                      "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                                     );
            }
            in_stack_000000c0 = DAT_1804b59f0;
            in_stack_000000b8 = DAT_1804b59f8;
            in_stack_000000d8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000074c,1);
            lVar1 = *in_stack_00000048;
            in_stack_00000100 = *(code **)(lVar1 + 0x390);
            lVar14 = DAT_1804b4d30;
            if (DAT_1804b4d30 == 0) {
              uVar10 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
              lVar14 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              lVar13 = 0;
              LOCK();
              bVar16 = DAT_1804b4d30 != 0;
              lVar5 = lVar14;
              if (bVar16) {
                lVar13 = DAT_1804b4d30;
                lVar5 = DAT_1804b4d30;
              }
              DAT_1804b4d30 = lVar5;
              UNLOCK();
              if (bVar16) {
                (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar14);
                lVar14 = lVar13;
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
            lVar13 = DAT_1804b4d38;
            uVar10 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00001dd8,5);
            lVar1 = *in_stack_00000048;
            pcVar3 = *(code **)(lVar1 + 0x110);
            if (DAT_1804b4d40 == 0) {
              uVar12 = FUN_18000fbd0(in_stack_00000048);
              DAT_1804b4d40 =
                   (**(code **)(lVar1 + 0x108))
                             (in_stack_00000048,uVar12,"getClassLoader","()Ljava/lang/ClassLoader;")
              ;
            }
            uVar12 = in_stack_00000058;
            uVar11 = (*pcVar3)(in_stack_00000048,in_stack_00000058,DAT_1804b4d40);
            (*in_stack_00000100)(in_stack_00000048,lVar14,lVar13,uVar10,uVar11);
            uVar10 = (*in_stack_000000b0)
                               (in_stack_00000048,uVar12,in_stack_000000b8,in_stack_000000c0,
                                in_stack_000000d8);
            DAT_1804b2288 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
          }
          _uStack0000000000000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2288,in_stack_00000078);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
          (*pcVar3)(in_stack_00000048,uVar10,0,uVar12);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_000000e0);
          (*pcVar3)(in_stack_00000048,uVar10,1,uVar12);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar10);
          iVar8 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar10,in_stack_00000070);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 != '\0') {
            return;
          }
          lVar1 = *in_stack_00000048;
          if (iVar8 <= iVar7) {
            (**(code **)(lVar1 + 0x658))(in_stack_00000048,in_stack_000000a0,1,1,&stack0x00000050);
            in_stack_000000b0 = (code *)CONCAT44(in_stack_000000b0._4_4_,uStack0000000000000050);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 != '\0') {
              return;
            }
            (**(code **)(*in_stack_00000048 + 0x658))
                      (in_stack_00000048,in_stack_000000a0,5,1,&stack0x00000050);
            uVar9 = uStack0000000000000050;
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 != '\0') {
              return;
            }
            _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar9);
            (**(code **)(*in_stack_00000048 + 0x698))
                      (in_stack_00000048,in_stack_000000a0,1,1,&stack0x00000050);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 != '\0') {
              return;
            }
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            pcVar3 = DAT_1804b59f8;
            if (cVar6 != '\0') {
              return;
            }
            if (DAT_1804b2290 == 0) {
              in_stack_000000b8 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59f8 == (code *)0x0) {
                return;
              }
              in_stack_000000c0 = DAT_1804b59f0;
              in_stack_000000d8 =
                   (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000074a,1);
              lVar14 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              in_stack_00000100 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar12 = (**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00001dc8,5);
              uVar10 = in_stack_00000058;
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,in_stack_00000058);
              (*in_stack_00000100)(in_stack_00000048,lVar1,lVar14,uVar12,uVar11);
              uVar10 = (*in_stack_000000b8)
                                 (in_stack_00000048,uVar10,pcVar3,in_stack_000000c0,
                                  in_stack_000000d8);
              DAT_1804b2290 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2290,in_stack_00000078);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_00000088,0);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2d6c);
            (*pcVar3)(in_stack_00000048,uVar10,0,uVar12);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_00000098,in_stack_00000090,uVar15);
            (*pcVar3)(in_stack_00000048,uVar10,1,uVar12);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar10);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar10,in_stack_00000070);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 != '\0') {
              return;
            }
            (**(code **)(*in_stack_00000048 + 0x658))
                      (in_stack_00000048,in_stack_000000a0,uVar9,1,&stack0x00000050);
            uVar9 = uStack0000000000000050;
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 != '\0') {
              return;
            }
            _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar9);
            (**(code **)(*in_stack_00000048 + 0x698))
                      (in_stack_00000048,in_stack_000000a0,5,1,&stack0x00000050);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 != '\0') {
              return;
            }
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
            pcVar3 = DAT_1804b59f8;
            if (cVar6 != '\0') {
              return;
            }
            if (DAT_1804b2298 == 0) {
              in_stack_000000b8 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59f8 == (code *)0x0) {
                return;
              }
              in_stack_000000c0 = DAT_1804b59f0;
              in_stack_000000d8 =
                   (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000748,1);
              lVar14 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              in_stack_00000100 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar12 = (**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00001db8,5);
              uVar10 = in_stack_00000058;
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,in_stack_00000058);
              (*in_stack_00000100)(in_stack_00000048,lVar1,lVar14,uVar12,uVar11);
              uVar10 = (*in_stack_000000b8)
                                 (in_stack_00000048,uVar10,pcVar3,in_stack_000000c0,
                                  in_stack_000000d8);
              DAT_1804b2298 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            _uStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2298,in_stack_00000078);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_00000088,0);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x2d6c);
            (*pcVar3)(in_stack_00000048,uVar10,0,uVar12);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
            uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                               (in_stack_00000048,in_stack_00000098,in_stack_00000090,uVar15);
            (*pcVar3)(in_stack_00000048,uVar10,1,uVar12);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,_uStack0000000000000050,in_stack_00000080,uVar10);
            (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 != '\0') {
              return;
            }
            return;
          }
          pcVar3 = *(code **)(lVar1 + 0x488);
          if (DAT_1804b5ac0 == 0) {
            uVar10 = FUN_1803334a0(in_stack_00000048);
            DAT_1804b5ac0 =
                 (**(code **)(lVar1 + 0x480))
                           (in_stack_00000048,uVar10,&DAT_1804a5865,&DAT_1804a5807);
          }
          uVar10 = (*pcVar3)(in_stack_00000048,in_stack_00000058,DAT_1804b5ac0);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 != '\0') {
            return;
          }
          (**(code **)(*in_stack_00000048 + 0x658))
                    (in_stack_00000048,in_stack_000000a0,iVar7,1,&stack0x00000050);
          uVar4 = _uStack0000000000000050;
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 != '\0') {
            return;
          }
          (**(code **)(*in_stack_00000048 + 0x658))
                    (in_stack_00000048,uVar10,uVar4 & 0xffffffff,1,&stack0x00000050);
          uVar9 = uStack0000000000000050;
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 != '\0') {
            return;
          }
          _uStack0000000000000050 = CONCAT44(uStack0000000000000054,uVar9);
          (**(code **)(*in_stack_00000048 + 0x698))
                    (in_stack_00000048,in_stack_000000a0,iVar7,1,&stack0x00000050);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar6 != '\0') {
            return;
          }
          iVar7 = iVar7 + 1;
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        } while (cVar6 == '\0');
      }
    }
  }
  return;
}

