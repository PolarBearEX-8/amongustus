/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=12 entry=18033f85e blocks=61 maxInstructions=50000 */

/* cut edge: 0x18033f85e -> 0x1803351d6 */
/* cut edge: 0x18033fa25 -> 0x1803351d6 */
/* cut edge: 0x18033fb13 -> 0x1803351d6 */
/* cut edge: 0x18033fce1 -> 0x1803351d6 */
/* cut edge: 0x18033fdce -> 0x18033f770 */
/* cut edge: 0x18033fe33 -> 0x18033f7e3 */
/* cut edge: 0x18033fe68 -> 0x18033f7e3 */
/* cut edge: 0x18033fe84 -> 0x18033f7f3 */
/* cut edge: 0x1803400c0 -> 0x1803351d6 */
/* cut edge: 0x1803400ee -> 0x180340fc1 */
/* cut edge: 0x180340125 -> 0x180340ffc */
/* cut edge: 0x180340172 -> 0x18034104e */
/* cut edge: 0x180340192 -> 0x180341088 */
/* cut edge: 0x180340284 -> 0x1803351d6 */
/* cut edge: 0x180340371 -> 0x1803351d6 */
/* cut edge: 0x1803403a7 -> 0x1803410c0 */
/* cut edge: 0x1803403de -> 0x1803410fb */
/* cut edge: 0x18034042b -> 0x18034114d */
/* cut edge: 0x180340453 -> 0x180341187 */
/* cut edge: 0x180340549 -> 0x1803351d6 */
/* cut edge: 0x18034063d -> 0x1803351d6 */
/* cut edge: 0x18034068a -> 0x180340cc0 */
/* cut edge: 0x1803406bf -> 0x1803351d6 */
/* cut edge: 0x1803406dd -> 0x18034086f */
/* cut edge: 0x1803406ed -> 0x1803411be */
/* cut edge: 0x18034071c -> 0x1803411f9 */
/* cut edge: 0x180340769 -> 0x180341248 */
/* cut edge: 0x180340789 -> 0x180341282 */
/* cut edge: 0x1803407e2 -> 0x18034086f */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18033f770 original byte 0x48 replaced by RET */
/* target 0x18033f7e3 original byte 0x4c replaced by RET */
/* target 0x18033f7f3 original byte 0x48 replaced by RET */
/* target 0x18034086f original byte 0x48 replaced by RET */
/* target 0x180340cc0 original byte 0x44 replaced by RET */
/* target 0x180340fc1 original byte 0x48 replaced by RET */
/* target 0x180340ffc original byte 0x48 replaced by RET */
/* target 0x18034104e original byte 0x48 replaced by RET */
/* target 0x180341088 original byte 0x48 replaced by RET */
/* target 0x1803410c0 original byte 0x48 replaced by RET */
/* target 0x1803410fb original byte 0x48 replaced by RET */
/* target 0x18034114d original byte 0x48 replaced by RET */
/* target 0x180341187 original byte 0x48 replaced by RET */
/* target 0x1803411be original byte 0x48 replaced by RET */
/* target 0x1803411f9 original byte 0x48 replaced by RET */
/* target 0x180341248 original byte 0x48 replaced by RET */
/* target 0x180341282 original byte 0x48 replaced by RET */


void region_00012_18033f85e(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  code *pcVar4;
  longlong lVar5;
  longlong lVar6;
  char cVar7;
  char cVar8;
  char cVar9;
  byte bVar10;
  byte bVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  longlong lVar16;
  longlong lVar17;
  undefined8 uVar18;
  ulonglong unaff_R12;
  int unaff_R13D;
  uint uVar19;
  bool bVar20;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000050;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  ulonglong in_stack_000000a0;
  code *in_stack_000000a8;
  undefined8 in_stack_000000c8;
  char in_stack_000000d0;
  undefined8 in_stack_000000d8;
  int in_stack_000000e0;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000118;
  int in_stack_00000148;
  undefined8 in_stack_00000180;
  undefined8 in_stack_000001c0;
  
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar7 == '\0') {
    if (DAT_1804b1f50 == 0) {
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      if (DAT_1804b59f8 == 0) {
        uVar15 = FUN_1803334a0(in_stack_00000048);
        DAT_1804b59f8 =
             (**(code **)(lVar1 + 0x388))
                       (in_stack_00000048,uVar15,&DAT_180493f0a,
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                       );
      }
      lVar5 = DAT_1804b59f8;
      uVar15 = DAT_1804b59f0;
      uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000081a,1);
      lVar1 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar1 + 0x390);
      lVar17 = DAT_1804b4d30;
      if (DAT_1804b4d30 == 0) {
        uVar13 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
        lVar17 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
        lVar16 = 0;
        LOCK();
        bVar20 = DAT_1804b4d30 != 0;
        lVar6 = lVar17;
        if (bVar20) {
          lVar16 = DAT_1804b4d30;
          lVar6 = DAT_1804b4d30;
        }
        DAT_1804b4d30 = lVar6;
        UNLOCK();
        if (bVar20) {
          (**(code **)(*in_stack_00000048 + 0xb0))();
          lVar17 = lVar16;
        }
      }
      if (DAT_1804b4d38 == 0) {
        uVar13 = FUN_1800024f0(in_stack_00000048);
        DAT_1804b4d38 =
             (**(code **)(*in_stack_00000048 + 0x388))
                       (in_stack_00000048,uVar13,"fromMethodDescriptorString",
                        "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;");
      }
      lVar16 = DAT_1804b4d38;
      uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000022e8,5);
      lVar1 = *in_stack_00000048;
      pcVar4 = *(code **)(lVar1 + 0x110);
      if (DAT_1804b4d40 == 0) {
        uVar18 = FUN_18000fbd0(in_stack_00000048);
        DAT_1804b4d40 =
             (**(code **)(lVar1 + 0x108))
                       (in_stack_00000048,uVar18,"getClassLoader","()Ljava/lang/ClassLoader;");
      }
      uVar18 = in_stack_00000058;
      unaff_R12 = 0xffffffff00000000;
      uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,DAT_1804b4d40);
      (*pcVar3)(in_stack_00000048,lVar17,lVar16,uVar13,uVar14);
      uVar15 = (*pcVar2)(in_stack_00000048,uVar18,lVar5,uVar15,uVar12);
      DAT_1804b1f50 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
    }
    in_stack_00000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b1f50,in_stack_00000078);
    uVar15 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x7089);
    (*pcVar2)(in_stack_00000048,uVar15,0,uVar12);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d8);
    (*pcVar2)(in_stack_00000048,uVar15,1,uVar12);
    uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar15);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar15,in_stack_00000070);
    cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((cVar8 == '\0') && (cVar8 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar8 == '\0')) {
      if (DAT_1804b1f58 == 0) {
        lVar1 = *in_stack_00000048;
        pcVar2 = *(code **)(lVar1 + 0x390);
        if (DAT_1804b59f8 == 0) {
          uVar15 = FUN_1803334a0(in_stack_00000048);
          DAT_1804b59f8 =
               (**(code **)(lVar1 + 0x388))
                         (in_stack_00000048,uVar15,&DAT_180493f0a,
                          "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                         );
        }
        lVar5 = DAT_1804b59f8;
        uVar15 = DAT_1804b59f0;
        uVar12 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000818,1);
        lVar1 = *in_stack_00000048;
        pcVar3 = *(code **)(lVar1 + 0x390);
        lVar17 = DAT_1804b4d30;
        if (DAT_1804b4d30 == 0) {
          uVar13 = (**(code **)(lVar1 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
          lVar17 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar13);
          lVar16 = 0;
          LOCK();
          bVar20 = DAT_1804b4d30 != 0;
          lVar6 = lVar17;
          if (bVar20) {
            lVar16 = DAT_1804b4d30;
            lVar6 = DAT_1804b4d30;
          }
          DAT_1804b4d30 = lVar6;
          UNLOCK();
          if (bVar20) {
            (**(code **)(*in_stack_00000048 + 0xb0))();
            lVar17 = lVar16;
          }
        }
        if (DAT_1804b4d38 == 0) {
          uVar13 = FUN_1800024f0(in_stack_00000048);
          DAT_1804b4d38 =
               (**(code **)(*in_stack_00000048 + 0x388))
                         (in_stack_00000048,uVar13,"fromMethodDescriptorString",
                          "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;"
                         );
        }
        lVar16 = DAT_1804b4d38;
        uVar13 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x000022d8,5);
        lVar1 = *in_stack_00000048;
        pcVar4 = *(code **)(lVar1 + 0x110);
        if (DAT_1804b4d40 == 0) {
          uVar18 = FUN_18000fbd0(in_stack_00000048);
          DAT_1804b4d40 =
               (**(code **)(lVar1 + 0x108))
                         (in_stack_00000048,uVar18,"getClassLoader","()Ljava/lang/ClassLoader;");
        }
        uVar18 = in_stack_00000058;
        uVar14 = (*pcVar4)(in_stack_00000048,in_stack_00000058,DAT_1804b4d40);
        (*pcVar3)(in_stack_00000048,lVar17,lVar16,uVar13,uVar14);
        uVar15 = (*pcVar2)(in_stack_00000048,uVar18,lVar5,uVar15,uVar12);
        DAT_1804b1f58 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
        unaff_R12 = 0xffffffff00000000;
      }
      in_stack_00000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b1f58,in_stack_00000078);
      uVar15 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a10);
      (*pcVar2)(in_stack_00000048,uVar15,0,uVar12);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                         (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000100);
      (*pcVar2)(in_stack_00000048,uVar15,1,uVar12);
      uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar15);
      cVar8 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar15,in_stack_00000070);
      cVar9 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar9 == '\0') {
        in_stack_000000a0 =
             in_stack_000000a0 & unaff_R12 |
             (ulonglong)
             ((uint)in_stack_000000a0 |
             in_stack_000000e0 << (cVar7 - cVar8 * in_stack_000000d0 & 0x1fU));
        if (unaff_R13D + 1 < 4) {
          return;
        }
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048,in_stack_000000c8);
        lVar1 = DAT_1804b59f8;
        uVar15 = DAT_1804b59f0;
        if (cVar7 == '\0') {
          if (DAT_1804b1f60 == 0) {
            in_stack_000000a8 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59f8 == 0) {
              return;
            }
            uVar12 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000816,1)
            ;
            lVar17 = DAT_1804b4d38;
            lVar5 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar18 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000022c8,5)
            ;
            uVar13 = in_stack_00000058;
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar14 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar2)(in_stack_00000048,lVar5,lVar17,uVar18,uVar14);
            uVar15 = (*in_stack_000000a8)(in_stack_00000048,uVar13,lVar1,uVar15,uVar12);
            DAT_1804b1f60 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
          }
          in_stack_00000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b1f60,in_stack_00000078);
          uVar15 = (**(code **)(*in_stack_00000048 + 0x560))
                             (in_stack_00000048,2,in_stack_00000088,0);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
          (*pcVar2)(in_stack_00000048,uVar15,0,uVar12);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                             (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                              in_stack_00000180);
          (*pcVar2)(in_stack_00000048,uVar15,1,uVar12);
          uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar15);
          bVar10 = (**(code **)(*in_stack_00000048 + 0x188))
                             (in_stack_00000048,uVar15,in_stack_00000070);
          cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar7 == '\0') {
            uVar19 = (uint)in_stack_000000a0;
            cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
            lVar1 = DAT_1804b59f8;
            uVar15 = DAT_1804b59f0;
            if (cVar7 == '\0') {
              if (DAT_1804b1f68 == 0) {
                in_stack_000000a8 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b59f8 == 0) {
                  return;
                }
                uVar12 = (**(code **)(*in_stack_00000048 + 0x518))
                                   (in_stack_00000048,&stack0x00000814,1);
                lVar17 = DAT_1804b4d38;
                lVar5 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar18 = (**(code **)(*in_stack_00000048 + 0x518))
                                   (in_stack_00000048,&stack0x000022b8,5);
                uVar13 = in_stack_00000058;
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000058);
                (*pcVar2)(in_stack_00000048,lVar5,lVar17,uVar18,uVar14);
                uVar15 = (*in_stack_000000a8)(in_stack_00000048,uVar13,lVar1,uVar15,uVar12);
                DAT_1804b1f68 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
              }
              in_stack_00000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b1f68,in_stack_00000078);
              uVar15 = (**(code **)(*in_stack_00000048 + 0x560))
                                 (in_stack_00000048,2,in_stack_00000088,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
              (*pcVar2)(in_stack_00000048,uVar15,0,uVar12);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                                 (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                  in_stack_000001c0);
              (*pcVar2)(in_stack_00000048,uVar15,1,uVar12);
              uVar15 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar15);
              bVar11 = (**(code **)(*in_stack_00000048 + 0x188))
                                 (in_stack_00000048,uVar15,in_stack_00000070);
              cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar7 == '\0') {
                in_stack_00000050 =
                     CONCAT71(in_stack_00000050._1_7_,bVar11 & (byte)(uVar19 >> (bVar10 & 0x1f)));
                (**(code **)(*in_stack_00000048 + 0x680))
                          (in_stack_00000048,in_stack_000000e8,in_stack_00000118,1,&stack0x00000050)
                ;
                cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar7 == '\0') {
                  if (in_stack_00000148 <= (int)in_stack_00000118 + 1) {
                    return;
                  }
                  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))
                                    (in_stack_00000048,in_stack_000000c8);
                  lVar1 = DAT_1804b59f8;
                  uVar15 = DAT_1804b59f0;
                  if (cVar7 == '\0') {
                    if (DAT_1804b1f70 == 0) {
                      in_stack_000000a8 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b59f8 == 0) {
                        return;
                      }
                      uVar12 = (**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x00000812,1);
                      lVar17 = DAT_1804b4d38;
                      lVar5 = DAT_1804b4d30;
                      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                      if (DAT_1804b4d30 == 0) {
                        return;
                      }
                      if (DAT_1804b4d38 == 0) {
                        return;
                      }
                      uVar18 = (**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x000022a8,5);
                      uVar13 = in_stack_00000058;
                      if (DAT_1804b4d40 == 0) {
                        return;
                      }
                      uVar14 = (**(code **)(*in_stack_00000048 + 0x110))
                                         (in_stack_00000048,in_stack_00000058);
                      (*pcVar2)(in_stack_00000048,lVar5,lVar17,uVar18,uVar14);
                      uVar15 = (*in_stack_000000a8)(in_stack_00000048,uVar13,lVar1,uVar15,uVar12);
                      DAT_1804b1f70 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar15);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar15);
                    }
                    return;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  return;
}

