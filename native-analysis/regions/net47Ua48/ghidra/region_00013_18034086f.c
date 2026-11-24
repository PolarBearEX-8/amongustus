/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=13 entry=18034086f blocks=60 maxInstructions=50000 */

/* cut edge: 0x18034086f -> 0x1803351d6 */
/* cut edge: 0x18034095c -> 0x1803351d6 */
/* cut edge: 0x180340b24 -> 0x1803351d6 */
/* cut edge: 0x180340c18 -> 0x1803351d6 */
/* cut edge: 0x180340c65 -> 0x18033f702 */
/* cut edge: 0x180340c90 -> 0x1803406bf */
/* cut edge: 0x180340cc0 -> 0x18033f702 */
/* cut edge: 0x180340cce -> 0x1803351d6 */
/* cut edge: 0x180340e83 -> 0x1803351d6 */
/* cut edge: 0x180340f70 -> 0x18033f6f0 */
/* cut edge: 0x180340fbc -> 0x1803351d6 */
/* cut edge: 0x180340fc1 -> 0x180340125 */
/* cut edge: 0x180340ffc -> 0x180340172 */
/* cut edge: 0x180341032 -> 0x180340172 */
/* cut edge: 0x18034104e -> 0x180340192 */
/* cut edge: 0x180341088 -> 0x1803401ec */
/* cut edge: 0x1803410c0 -> 0x1803403de */
/* cut edge: 0x1803410fb -> 0x18034042b */
/* cut edge: 0x180341131 -> 0x18034042b */
/* cut edge: 0x18034114d -> 0x180340453 */
/* cut edge: 0x180341187 -> 0x1803404ac */
/* cut edge: 0x1803411be -> 0x18034071c */
/* cut edge: 0x1803411f9 -> 0x180340769 */
/* cut edge: 0x18034122f -> 0x180340769 */
/* cut edge: 0x180341248 -> 0x180340789 */
/* cut edge: 0x180341282 -> 0x1803407e2 */
/* cut edge: 0x1803414be -> 0x1803351d6 */
/* cut edge: 0x180341510 -> 0x1803aab70 */
/* cut edge: 0x180341549 -> 0x1803351d6 */
/* cut edge: 0x180341711 -> 0x1803351d6 */
/* cut edge: 0x180341711 -> 0x1803418b2 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18033f6f0 original byte 0x41 replaced by RET */
/* target 0x18033f702 original byte 0x48 replaced by RET */
/* target 0x180340125 original byte 0x4c replaced by RET */
/* target 0x180340172 original byte 0x48 replaced by RET */
/* target 0x180340192 original byte 0x48 replaced by RET */
/* target 0x1803401ec original byte 0x49 replaced by RET */
/* target 0x1803403de original byte 0x4c replaced by RET */
/* target 0x18034042b original byte 0x48 replaced by RET */
/* target 0x180340453 original byte 0x48 replaced by RET */
/* target 0x1803404ac original byte 0x48 replaced by RET */
/* target 0x1803406bf original byte 0x4c replaced by RET */
/* target 0x18034071c original byte 0x4c replaced by RET */
/* target 0x180340769 original byte 0x48 replaced by RET */
/* target 0x180340789 original byte 0x48 replaced by RET */
/* target 0x1803407e2 original byte 0x48 replaced by RET */
/* target 0x1803418b2 original byte 0x48 replaced by RET */
/* target 0x1803aab70 original byte 0x49 replaced by RET */


void region_00013_18034086f(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  longlong lVar3;
  byte bVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  longlong lVar9;
  longlong lVar10;
  ulonglong unaff_R12;
  uint uVar11;
  ulonglong uVar12;
  bool bVar13;
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
  uint in_stack_000000a0;
  code *in_stack_000000a8;
  undefined8 in_stack_000000b0;
  undefined8 in_stack_000000c8;
  code *in_stack_000000d0;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_000000e8;
  ulonglong in_stack_00000118;
  code *in_stack_00000130;
  int in_stack_00000148;
  undefined8 in_stack_00000178;
  undefined8 in_stack_000001c0;
  
  in_stack_00000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1193);
  (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000178);
  (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar6);
  bVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    uVar11 = in_stack_000000a0 >> (bVar4 & 0x1f);
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      if (DAT_1804b1f78 == 0) {
        lVar2 = *in_stack_00000048;
        in_stack_000000a8 = *(code **)(lVar2 + 0x390);
        if (DAT_1804b59f8 == (code *)0x0) {
          uVar6 = FUN_1803334a0(in_stack_00000048);
          DAT_1804b59f8 =
               (code *)(**(code **)(lVar2 + 0x388))
                                 (in_stack_00000048,uVar6,&DAT_180493f0a,
                                  "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                                 );
        }
        uVar6 = DAT_1804b59f0;
        in_stack_000000d0 = DAT_1804b59f8;
        uVar7 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000810,1);
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        lVar10 = DAT_1804b4d30;
        if (DAT_1804b4d30 == 0) {
          uVar8 = (**(code **)(lVar2 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType");
          lVar10 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          lVar9 = 0;
          LOCK();
          bVar13 = DAT_1804b4d30 != 0;
          lVar3 = lVar10;
          if (bVar13) {
            lVar9 = DAT_1804b4d30;
            lVar3 = DAT_1804b4d30;
          }
          DAT_1804b4d30 = lVar3;
          UNLOCK();
          if (bVar13) {
            (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar10);
            lVar10 = lVar9;
          }
        }
        in_stack_000000e0 = uVar6;
        in_stack_000000b0 = uVar7;
        in_stack_00000130 = pcVar1;
        if (DAT_1804b4d38 == 0) {
          uVar6 = FUN_1800024f0(in_stack_00000048);
          DAT_1804b4d38 =
               (**(code **)(*in_stack_00000048 + 0x388))
                         (in_stack_00000048,uVar6,"fromMethodDescriptorString",
                          "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;"
                         );
        }
        lVar9 = DAT_1804b4d38;
        uVar6 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00002298,5);
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x110);
        if (DAT_1804b4d40 == 0) {
          uVar7 = FUN_18000fbd0(in_stack_00000048);
          DAT_1804b4d40 =
               (**(code **)(lVar2 + 0x108))
                         (in_stack_00000048,uVar7,"getClassLoader","()Ljava/lang/ClassLoader;");
        }
        uVar7 = in_stack_00000058;
        uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,DAT_1804b4d40);
        (*in_stack_00000130)(in_stack_00000048,lVar10,lVar9,uVar6,uVar8);
        uVar6 = (*in_stack_000000a8)
                          (in_stack_00000048,uVar7,in_stack_000000d0,in_stack_000000e0,
                           in_stack_000000b0);
        DAT_1804b1f78 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        unaff_R12 = 0;
      }
      in_stack_00000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b1f78,in_stack_00000078);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000001c0);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar6);
      bVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar6,in_stack_00000070);
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        in_stack_00000050 = CONCAT71(in_stack_00000050._1_7_,bVar4 & (byte)uVar11);
        (**(code **)(*in_stack_00000048 + 0x680))
                  (in_stack_00000048,in_stack_000000e8,in_stack_00000118,1,&stack0x00000050);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          uVar11 = (int)in_stack_00000118 + 1;
          uVar12 = in_stack_00000118 & unaff_R12;
          if (in_stack_00000148 <= (int)((uint)uVar12 | uVar11)) {
            return;
          }
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048,in_stack_000000c8);
          if (cVar5 == '\0') {
            if (DAT_1804b1f80 == 0) {
              lVar2 = *in_stack_00000048;
              in_stack_000000d0 = *(code **)(lVar2 + 0x390);
              if (DAT_1804b59f8 == (code *)0x0) {
                uVar6 = FUN_1803334a0(in_stack_00000048);
                DAT_1804b59f8 =
                     (code *)(**(code **)(lVar2 + 0x388))
                                       (in_stack_00000048,uVar6,&DAT_180493f0a,
                                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                                       );
              }
              in_stack_000000e0 = DAT_1804b59f0;
              in_stack_000000a8 = DAT_1804b59f8;
              in_stack_000000b0 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000080e,1)
              ;
              lVar2 = *in_stack_00000048;
              in_stack_00000130 = *(code **)(lVar2 + 0x390);
              lVar10 = DAT_1804b4d30;
              if (DAT_1804b4d30 == 0) {
                uVar6 = (**(code **)(lVar2 + 0x30))(in_stack_00000048,"java/lang/invoke/MethodType")
                ;
                lVar10 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                lVar9 = 0;
                LOCK();
                bVar13 = DAT_1804b4d30 != 0;
                lVar3 = lVar10;
                if (bVar13) {
                  lVar9 = DAT_1804b4d30;
                  lVar3 = DAT_1804b4d30;
                }
                DAT_1804b4d30 = lVar3;
                UNLOCK();
                if (bVar13) {
                  (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar10);
                  lVar10 = lVar9;
                }
              }
              if (DAT_1804b4d38 == 0) {
                uVar6 = FUN_1800024f0(in_stack_00000048);
                DAT_1804b4d38 =
                     (**(code **)(*in_stack_00000048 + 0x388))
                               (in_stack_00000048,uVar6,"fromMethodDescriptorString",
                                "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/invoke/MethodType;"
                               );
              }
              lVar9 = DAT_1804b4d38;
              uVar6 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00002288,5);
              lVar2 = *in_stack_00000048;
              pcVar1 = *(code **)(lVar2 + 0x110);
              if (DAT_1804b4d40 == 0) {
                uVar7 = FUN_18000fbd0(in_stack_00000048);
                DAT_1804b4d40 =
                     (**(code **)(lVar2 + 0x108))
                               (in_stack_00000048,uVar7,"getClassLoader","()Ljava/lang/ClassLoader;"
                               );
              }
              uVar7 = in_stack_00000058;
              uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000058,DAT_1804b4d40);
              (*in_stack_00000130)(in_stack_00000048,lVar10,lVar9,uVar6,uVar8);
              uVar6 = (*in_stack_000000d0)
                                (in_stack_00000048,uVar7,in_stack_000000a8,in_stack_000000e0,
                                 in_stack_000000b0);
              DAT_1804b1f80 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
            }
            in_stack_00000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b1f80,in_stack_00000078);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
            (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000001c0);
            (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                              (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar6);
            bVar4 = (**(code **)(*in_stack_00000048 + 0x188))
                              (in_stack_00000048,uVar6,in_stack_00000070);
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar5 == '\0') {
              in_stack_00000050 = CONCAT71(in_stack_00000050._1_7_,bVar4 & (byte)in_stack_000000a0);
              (**(code **)(*in_stack_00000048 + 0x680))
                        (in_stack_00000048,in_stack_000000e8,uVar12 & 0xffffffff | (ulonglong)uVar11
                         ,1,&stack0x00000050);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                return;
              }
            }
          }
        }
      }
    }
  }
  return;
}

