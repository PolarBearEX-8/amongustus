/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=80 entry=180383910 blocks=31 maxInstructions=50000 */

/* cut edge: 0x180383910 -> 0x1803351d6 */
/* cut edge: 0x180383a14 -> 0x1803351d6 */
/* cut edge: 0x180383b81 -> 0x1803351d6 */
/* cut edge: 0x180383c72 -> 0x1803351d6 */
/* cut edge: 0x180383cbe -> 0x1803351d6 */
/* cut edge: 0x180383e39 -> 0x1803351d6 */
/* cut edge: 0x180383f26 -> 0x1803837b2 */
/* cut edge: 0x180383f4a -> 0x1803351d6 */
/* cut edge: 0x180383f4f -> 0x1803351d6 */
/* cut edge: 0x1803840ce -> 0x1803351d6 */
/* cut edge: 0x1803841b6 -> 0x1803351d6 */
/* cut edge: 0x18038435c -> 0x1803351d6 */
/* cut edge: 0x180384449 -> 0x18038499f */
/* cut edge: 0x180384460 -> 0x1803351d6 */
/* cut edge: 0x1803845cd -> 0x1803351d6 */
/* cut edge: 0x1803846be -> 0x1803351d6 */
/* cut edge: 0x18038470a -> 0x1803351d6 */
/* cut edge: 0x180384720 -> 0x180384885 */
/* cut edge: 0x180384730 -> 0x180384885 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803837b2 original byte 0x48 replaced by RET */
/* target 0x180384885 original byte 0x48 replaced by RET */
/* target 0x18038499f original byte 0x84 replaced by RET */


void region_00080_180383910(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  longlong lVar2;
  code *pcVar3;
  char cVar4;
  byte bVar5;
  int iVar6;
  int iVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  ulonglong uVar13;
  int unaff_R15D;
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
  undefined8 in_stack_000000a0;
  code *in_stack_000000b0;
  ulonglong in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d0;
  code *in_stack_000000f0;
  undefined8 in_stack_000000f8;
  code *in_stack_00000110;
  undefined8 in_stack_00000118;
  undefined8 in_stack_00000158;
  undefined8 in_stack_00000180;
  undefined8 in_stack_000001c0;
  
  in_stack_00000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_00000078);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x3979);
  (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c0);
  (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                    (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar8);
  iVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070);
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar4 == '\0') {
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (unaff_R15D < iVar6) {
      if (cVar4 == '\0') {
        uVar13 = in_stack_000000c8 ^ 0x126b9f9cf63a6a04;
        if (DAT_1804b2b40 == 0) {
          lVar2 = *in_stack_00000048;
          in_stack_000000b0 = *(code **)(lVar2 + 0x390);
          in_stack_000000c0 = FUN_180333550(in_stack_00000048);
          in_stack_000000f0 = DAT_1804b59f0;
          in_stack_000000f8 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000051e,1);
          lVar2 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar2 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000c68,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar8 = in_stack_00000058;
          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
          (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar8 = (*in_stack_000000b0)
                            (in_stack_00000048,uVar8,in_stack_000000c0,in_stack_000000f0,
                             in_stack_000000f8);
          DAT_1804b2b40 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
        }
        in_stack_00000050 =
             (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2b40,in_stack_00000078);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1af9);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,uVar13);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar8);
        iVar6 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar8,in_stack_00000070)
        ;
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar4 == '\0') && (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0'))
        {
          in_stack_000000c0 = in_stack_000000c8 ^ 0x5af37c25f74e3104;
          in_stack_000000b0 = (code *)in_stack_00000180;
          if (DAT_1804b2b48 == 0) {
            lVar2 = *in_stack_00000048;
            in_stack_000000f0 = *(code **)(lVar2 + 0x390);
            in_stack_000000f8 = FUN_180333550(in_stack_00000048);
            in_stack_00000110 = DAT_1804b59f0;
            in_stack_00000118 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000051c,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000c58,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar12 = FUN_1800025a0(in_stack_00000048);
            uVar8 = in_stack_00000058;
            uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
            (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
            uVar8 = (*in_stack_000000f0)
                              (in_stack_00000048,uVar8,in_stack_000000f8,in_stack_00000110,
                               in_stack_00000118);
            DAT_1804b2b48 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          }
          in_stack_00000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2b48,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x40c0);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000c0
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar8);
          iVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar8,in_stack_00000070);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (iVar6 < iVar7) {
              return;
            }
            if (cVar4 == '\0') {
              if (DAT_1804b2b50 == 0) {
                lVar2 = *in_stack_00000048;
                in_stack_000000f0 = *(code **)(lVar2 + 0x390);
                in_stack_000000f8 = FUN_1803335f0(in_stack_00000048);
                in_stack_00000110 = DAT_1804b59f0;
                in_stack_00000118 =
                     (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x0000051a,1);
                lVar2 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar2 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000c48,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar8 = in_stack_00000058;
                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar8 = (*in_stack_000000f0)
                                  (in_stack_00000048,uVar8,in_stack_000000f8,in_stack_00000110,
                                   in_stack_00000118);
                DAT_1804b2b50 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
              }
              in_stack_00000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2b50,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5f01);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000d0);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar8);
              bVar5 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                (in_stack_00000048,uVar8,in_stack_000001c0);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                in_stack_00000050 =
                     CONCAT71(in_stack_00000050._1_7_,bVar5 & (byte)in_stack_000000b0);
                (**(code **)(*in_stack_00000048 + 0x680))
                          (in_stack_00000048,in_stack_000000a0,iVar6,1,&stack0x00000050);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if ((cVar4 == '\0') &&
                   (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
                  if (DAT_1804b2b58 == 0) {
                    lVar2 = *in_stack_00000048;
                    in_stack_000000f0 = *(code **)(lVar2 + 0x390);
                    in_stack_000000f8 = FUN_180333550(in_stack_00000048);
                    in_stack_00000110 = DAT_1804b59f0;
                    in_stack_00000118 =
                         (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000518,1);
                    lVar2 = *in_stack_00000048;
                    pcVar1 = *(code **)(lVar2 + 0x390);
                    uVar9 = FUN_1800024f0(in_stack_00000048);
                    uVar10 = FUN_180002550(in_stack_00000048);
                    uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000c38,5);
                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar12 = FUN_1800025a0(in_stack_00000048);
                    uVar8 = in_stack_00000058;
                    uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
                    (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                    uVar8 = (*in_stack_000000f0)
                                      (in_stack_00000048,uVar8,in_stack_000000f8,in_stack_00000110,
                                       in_stack_00000118);
                    DAT_1804b2b58 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
                  }
                  return;
                }
              }
            }
          }
        }
      }
    }
    else if (cVar4 == '\0') {
      if (DAT_1804b2b30 == 0) {
        lVar2 = *in_stack_00000048;
        in_stack_000000f0 = *(code **)(lVar2 + 0x390);
        in_stack_000000f8 = FUN_1803335f0(in_stack_00000048);
        in_stack_00000110 = DAT_1804b59f0;
        in_stack_00000118 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000522,1);
        lVar2 = *in_stack_00000048;
        pcVar1 = *(code **)(lVar2 + 0x390);
        uVar9 = FUN_1800024f0(in_stack_00000048);
        uVar10 = FUN_180002550(in_stack_00000048);
        uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000c88,5);
        pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
        uVar12 = FUN_1800025a0(in_stack_00000048);
        uVar8 = in_stack_00000058;
        uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
        (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
        uVar8 = (*in_stack_000000f0)
                          (in_stack_00000048,uVar8,in_stack_000000f8,in_stack_00000110,
                           in_stack_00000118);
        DAT_1804b2b30 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
      }
      in_stack_00000050 =
           (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,DAT_1804b2b30,in_stack_00000078);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5f01);
      (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
      (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar8);
      bVar5 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar8,in_stack_000001c0);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        in_stack_00000050 = CONCAT71(in_stack_00000050._1_7_,bVar5 & (byte)in_stack_000000b0);
        (**(code **)(*in_stack_00000048 + 0x680))
                  (in_stack_00000048,in_stack_000000a0,unaff_R15D,1,&stack0x00000050);
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((cVar4 == '\0') && (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0'))
        {
          if (DAT_1804b2b38 == 0) {
            lVar2 = *in_stack_00000048;
            in_stack_000000f0 = *(code **)(lVar2 + 0x390);
            in_stack_000000f8 = FUN_180333550(in_stack_00000048);
            in_stack_00000110 = DAT_1804b59f0;
            in_stack_00000118 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000520,1);
            lVar2 = *in_stack_00000048;
            pcVar1 = *(code **)(lVar2 + 0x390);
            uVar9 = FUN_1800024f0(in_stack_00000048);
            uVar10 = FUN_180002550(in_stack_00000048);
            uVar11 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000c78,5);
            pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
            uVar12 = FUN_1800025a0(in_stack_00000048);
            uVar8 = in_stack_00000058;
            uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000058,uVar12);
            (*pcVar1)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
            uVar8 = (*in_stack_000000f0)
                              (in_stack_00000048,uVar8,in_stack_000000f8,in_stack_00000110,
                               in_stack_00000118);
            DAT_1804b2b38 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar8);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar8);
          }
          in_stack_00000050 =
               (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2b38,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x1193);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000158
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,in_stack_00000050,in_stack_00000080,uVar8);
          bVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar8,in_stack_00000070);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            in_stack_000000b0 = (code *)((longlong)in_stack_000000b0 >> (bVar5 & 0x3f));
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar4 == '\0') {
              return;
            }
          }
        }
      }
    }
  }
  return;
}

