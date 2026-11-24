/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=65 entry=180060120 blocks=62 maxInstructions=100000 */

/* cut edge: 0x180060120 -> 0x18014d3db */
/* cut edge: 0x180060149 -> 0x18014d3db */
/* cut edge: 0x18006015f -> 0x18014d3db */
/* cut edge: 0x18006033b -> 0x18014d3db */
/* cut edge: 0x180060435 -> 0x18014d3db */
/* cut edge: 0x18006044b -> 0x18014d3db */
/* cut edge: 0x180060634 -> 0x18014d3db */
/* cut edge: 0x180060729 -> 0x18014d3db */
/* cut edge: 0x18006073f -> 0x18014d3db */
/* cut edge: 0x18006091d -> 0x18014d3db */
/* cut edge: 0x180060a12 -> 0x18014d3db */
/* cut edge: 0x180060a28 -> 0x18014d3db */
/* cut edge: 0x180060b2d -> 0x180061151 */
/* cut edge: 0x180060c0b -> 0x18014d3db */
/* cut edge: 0x180060d02 -> 0x180045538 */
/* cut edge: 0x180060d16 -> 0x180044575 */
/* cut edge: 0x180060d25 -> 0x1801253fe */
/* cut edge: 0x180060d2a -> 0x180060110 */
/* cut edge: 0x180060d5f -> 0x180060110 */
/* target 0x180044575 original byte 0x48 replaced by RET */
/* target 0x180045538 original byte 0x48 replaced by RET */
/* target 0x180060110 original byte 0x4c replaced by RET */
/* target 0x180061151 original byte 0x48 replaced by RET */
/* target 0x1801253fe original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00065_180060120(void)

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
  code *unaff_RBX;
  bool bVar14;
  longlong *in_stack_00000048;
  longlong in_stack_00000058;
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
  
  (*unaff_RBX)(in_stack_00000048);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
     (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
    if (DAT_1804ad028 == 0) {
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
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000082c,1);
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
      uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003858,5);
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
      DAT_1804ad028 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
    }
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804ad028,in_stack_000000b0);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2301);
    (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                       in_stack_00000058 % 0xddf14 ^ in_stack_000000c0 ^ 0x5eddd0066427b0fc);
    (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
    (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) &&
       (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
      if (DAT_1804ad030 == 0) {
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
        uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000082a,1);
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
        uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003848,5);
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
        DAT_1804ad030 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
      }
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804ad030,in_stack_000000b0);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x4259);
      (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
      pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                         in_stack_00000058 % 0xafb98 ^ in_stack_000000c0 ^ 0x1ca436c78d1f3164);
      (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
      uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
      (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0'))
         && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
        if (DAT_1804ad038 == 0) {
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
          uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000828,1);
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
          uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003838,5);
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
          DAT_1804ad038 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804ad038,in_stack_000000b0);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x6a17);
        (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
        pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           in_stack_00000058 % 0xa4a1a ^ in_stack_000000c0 ^ 0x4aa46eb06123f23a);
        (*pcVar2)(in_stack_00000048,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar11,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar11,in_stack_000000d8);
        cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (((cVar7 == '\0') && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')
            ) && (cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar7 == '\0')) {
          if (DAT_1804ad040 == 0) {
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
            uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000826,1);
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
            uVar9 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003828,5);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
            (*pcVar3)(in_stack_00000048,lVar13,lVar12,uVar9,uVar10);
            uVar11 = (*pcVar2)(in_stack_00000048,in_stack_00000090,lVar5,uVar11,uVar8);
            DAT_1804ad040 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804ad040,in_stack_000000b0);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x60ec);
          (*pcVar2)(in_stack_00000048,uVar8,0,uVar9);
          pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                             in_stack_00000058 % 0xdbb8c ^ in_stack_000000c0 ^ 0x27bb9317a9417b3a);
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
  }
  return;
}

