/* Synthetic CFG slice from function at 0x180298d70; outgoing edges were patched to RET in a temporary project. */
/* region=54 entry=1802cedb9 blocks=58 maxInstructions=50000 */

/* cut edge: 0x1802cee3e -> 0x180333186 */
/* cut edge: 0x1802cef33 -> 0x180333186 */
/* cut edge: 0x1802cef49 -> 0x180333186 */
/* cut edge: 0x1802cefb4 -> 0x1802e444a */
/* cut edge: 0x1802cefdd -> 0x1802e4482 */
/* cut edge: 0x1802cf039 -> 0x1802e44d0 */
/* cut edge: 0x1802cf04e -> 0x1802e4509 */
/* cut edge: 0x1802cf12d -> 0x180333186 */
/* cut edge: 0x1802cf224 -> 0x1802f6830 */
/* cut edge: 0x1802cf238 -> 0x180333186 */
/* cut edge: 0x1802cf24e -> 0x180333186 */
/* cut edge: 0x1802cf2bd -> 0x1802e2fec */
/* cut edge: 0x1802cf2e6 -> 0x1802e3024 */
/* cut edge: 0x1802cf342 -> 0x1802e3075 */
/* cut edge: 0x1802cf357 -> 0x1802e30ae */
/* cut edge: 0x1802cf435 -> 0x180333186 */
/* cut edge: 0x1802cf52a -> 0x180333186 */
/* cut edge: 0x1802cf540 -> 0x180333186 */
/* cut edge: 0x1802cf5b3 -> 0x1802e34d2 */
/* cut edge: 0x1802cf5dc -> 0x1802e350a */
/* cut edge: 0x1802cf638 -> 0x1802e3558 */
/* cut edge: 0x1802cf64d -> 0x1802e3591 */
/* cut edge: 0x1802cf727 -> 0x180333186 */
/* cut edge: 0x1802cf873 -> 0x180333186 */
/* cut edge: 0x1802cf889 -> 0x180333186 */
/* cut edge: 0x1802cf8f1 -> 0x1802e39ad */
/* cut edge: 0x1802cf915 -> 0x1802e39e0 */
/* cut edge: 0x1802cf973 -> 0x1802e3a31 */
/* cut edge: 0x1802cf983 -> 0x1802e3a6a */
/* cut edge: 0x1802cfa58 -> 0x180333186 */
/* cut edge: 0x1802cfb4d -> 0x180333186 */
/* cut edge: 0x1802cfb63 -> 0x180333186 */
/* cut edge: 0x1802cfbd6 -> 0x1802e3e7c */
/* cut edge: 0x1802cfc02 -> 0x1802e3eb4 */
/* cut edge: 0x1802cfc5b -> 0x1802e3f02 */
/* cut edge: 0x1802cfc70 -> 0x1802e3f3b */
/* cut edge: 0x1802cfd4a -> 0x180333186 */
/* cut edge: 0x1802cfd4a -> 0x1802cfe41 */
/* target 0x1802cfe41 original byte 0x48 replaced by RET */
/* target 0x1802e2fec original byte 0x48 replaced by RET */
/* target 0x1802e3024 original byte 0x48 replaced by RET */
/* target 0x1802e3075 original byte 0x48 replaced by RET */
/* target 0x1802e30ae original byte 0x48 replaced by RET */
/* target 0x1802e34d2 original byte 0x48 replaced by RET */
/* target 0x1802e350a original byte 0x48 replaced by RET */
/* target 0x1802e3558 original byte 0x48 replaced by RET */
/* target 0x1802e3591 original byte 0x48 replaced by RET */
/* target 0x1802e39ad original byte 0x48 replaced by RET */
/* target 0x1802e39e0 original byte 0x48 replaced by RET */
/* target 0x1802e3a31 original byte 0x48 replaced by RET */
/* target 0x1802e3a6a original byte 0x48 replaced by RET */
/* target 0x1802e3e7c original byte 0x48 replaced by RET */
/* target 0x1802e3eb4 original byte 0x48 replaced by RET */
/* target 0x1802e3f02 original byte 0x48 replaced by RET */
/* target 0x1802e3f3b original byte 0x48 replaced by RET */
/* target 0x1802e444a original byte 0x48 replaced by RET */
/* target 0x1802e4482 original byte 0x48 replaced by RET */
/* target 0x1802e44d0 original byte 0x48 replaced by RET */
/* target 0x1802e4509 original byte 0x48 replaced by RET */
/* target 0x1802f6830 original byte 0x48 replaced by RET */
/* target 0x180333186 original byte 0x48 replaced by RET */


void region_00054_1802cedb9(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  ulonglong uVar10;
  undefined8 uVar11;
  longlong lVar12;
  code *unaff_R15;
  longlong *in_stack_00000040;
  code *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000068;
  ulonglong in_stack_00000070;
  code *in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  ulonglong in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_00000108;
  longlong in_stack_00000110;
  
  (*unaff_R15)(in_stack_00000040,in_stack_00000080);
  (*in_stack_00000078)(in_stack_00000040);
  uVar7 = (*in_stack_00000048)
                    (in_stack_00000040,in_stack_00000080,in_stack_00000068,in_stack_00000108,
                     in_stack_00000058);
  DAT_1804b17d0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
  (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                    (in_stack_00000040,DAT_1804b17d0,in_stack_000000a0);
  uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x7e5c);
  (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
  pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
  uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                    (in_stack_00000040,in_stack_00000088,in_stack_00000098);
  (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
  uVar7 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,uVar7,in_stack_000000a8,uVar8)
  ;
  uVar10 = (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
  cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
  if (((cVar6 == '\0') && (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar6 == '\0')) &&
     (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), lVar12 = DAT_1804b5a08,
     uVar7 = DAT_1804b59f0, cVar6 == '\0')) {
    if (DAT_1804b17d8 == 0) {
      pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000356,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000014b8,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar11 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
      (*pcVar2)(in_stack_00000040,lVar3,lVar4,uVar9,uVar11);
      uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar12,uVar7,uVar8);
      DAT_1804b17d8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
      (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
    }
    uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                      (in_stack_00000040,DAT_1804b17d8,in_stack_000000a0);
    uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x62d8);
    (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                      (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                       (longlong)in_stack_00000070 % 0x8c603 ^ in_stack_000000b0 ^
                       0xbfbeddfc6d5dba53);
    (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
    uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                      (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
    lVar12 = (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
    cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
    if (cVar6 == '\0') {
      lVar12 = lVar12 + (uVar10 ^ in_stack_00000070);
      if (in_stack_00000110 == 0) {
        return;
      }
      cVar6 = (**(code **)(*in_stack_00000040 + 0x720))();
      if ((cVar6 == '\0') &&
         (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), lVar3 = DAT_1804b5a08,
         uVar7 = DAT_1804b59f0, cVar6 == '\0')) {
        if (DAT_1804b17e0 == 0) {
          pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000354,1);
          lVar5 = DAT_1804b4d38;
          lVar4 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x000014a8,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar11 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
          (*pcVar2)(in_stack_00000040,lVar4,lVar5,uVar9,uVar11);
          uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar3,uVar7,uVar8);
          DAT_1804b17e0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
          (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
        }
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,DAT_1804b17e0,in_stack_000000a0);
        uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1317);
        (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                          (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                           lVar12 % 0x3c45c ^ in_stack_000000b0 ^ 0xb0900afd2092e0a2);
        (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
        uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                          (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
        (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
        cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
        if (((cVar6 == '\0') && (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar6 == '\0')
            ) && (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), lVar3 = DAT_1804b5a08,
                 uVar7 = DAT_1804b59f0, cVar6 == '\0')) {
          if (DAT_1804b17e8 == 0) {
            pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
            if (DAT_1804b5a08 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00000352,1);
            lVar5 = DAT_1804b4d38;
            lVar4 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar9 = (**(code **)(*in_stack_00000040 + 0x518))(in_stack_00000040,&stack0x00001498,5);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar11 = (**(code **)(*in_stack_00000040 + 0x110))(in_stack_00000040,in_stack_00000080);
            (*pcVar2)(in_stack_00000040,lVar4,lVar5,uVar9,uVar11);
            uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar3,uVar7,uVar8);
            DAT_1804b17e8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
            (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
          }
          uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                            (in_stack_00000040,DAT_1804b17e8,in_stack_000000a0);
          uVar8 = (**(code **)(*in_stack_00000040 + 0x560))(in_stack_00000040,2,in_stack_00000090,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x1641);
          (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                            (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                             lVar12 % 0x4060d ^ in_stack_000000b0 ^ 0x8741a9988813c4ac);
          (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
          uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                            (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
          (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
          cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
          if (((cVar6 == '\0') &&
              (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar6 == '\0')) &&
             (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), lVar3 = DAT_1804b5a08,
             uVar7 = DAT_1804b59f0, cVar6 == '\0')) {
            if (DAT_1804b17f0 == 0) {
              pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
              if (DAT_1804b5a08 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                (in_stack_00000040,&stack0x00000350,1);
              lVar5 = DAT_1804b4d38;
              lVar4 = DAT_1804b4d30;
              pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                (in_stack_00000040,&stack0x00001488,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                 (in_stack_00000040,in_stack_00000080);
              (*pcVar2)(in_stack_00000040,lVar4,lVar5,uVar9,uVar11);
              uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar3,uVar7,uVar8);
              DAT_1804b17f0 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
              (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
            }
            uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,DAT_1804b17f0,in_stack_000000a0);
            uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                              (in_stack_00000040,2,in_stack_00000090,0);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x719a);
            (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                              (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                               lVar12 % 0x10ffb ^ in_stack_000000b0 ^ 0xecdd3e6ec63594f5);
            (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
            uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                              (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
            (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
            cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
            if (((cVar6 == '\0') &&
                (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), cVar6 == '\0')) &&
               (cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(), lVar3 = DAT_1804b5a08,
               uVar7 = DAT_1804b59f0, cVar6 == '\0')) {
              if (DAT_1804b17f8 == 0) {
                pcVar1 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b5a08 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x0000034e,1);
                lVar5 = DAT_1804b4d38;
                lVar4 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000040 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000040 + 0x518))
                                  (in_stack_00000040,&stack0x00001478,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar11 = (**(code **)(*in_stack_00000040 + 0x110))
                                   (in_stack_00000040,in_stack_00000080);
                (*pcVar2)(in_stack_00000040,lVar4,lVar5,uVar9,uVar11);
                uVar7 = (*pcVar1)(in_stack_00000040,in_stack_00000080,lVar3,uVar7,uVar8);
                DAT_1804b17f8 = (**(code **)(*in_stack_00000040 + 0xa8))(in_stack_00000040,uVar7);
                (**(code **)(*in_stack_00000040 + 0xb8))(in_stack_00000040,uVar7);
              }
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,DAT_1804b17f8,in_stack_000000a0);
              uVar8 = (**(code **)(*in_stack_00000040 + 0x560))
                                (in_stack_00000040,2,in_stack_00000090,0);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_000000b8,in_stack_000000c0,0x2ad9);
              (*pcVar1)(in_stack_00000040,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000040 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000040 + 0x390))
                                (in_stack_00000040,in_stack_00000088,in_stack_00000098,
                                 lVar12 % 0xeb102 ^ in_stack_000000b0 ^ 0x82171868ff1ac3f8);
              (*pcVar1)(in_stack_00000040,uVar8,1,uVar9);
              uVar7 = (**(code **)(*in_stack_00000040 + 0x110))
                                (in_stack_00000040,uVar7,in_stack_000000a8,uVar8);
              (**(code **)(*in_stack_00000040 + 0x1a0))(in_stack_00000040,uVar7,in_stack_000000d0);
              cVar6 = (**(code **)(*in_stack_00000040 + 0x720))(in_stack_00000040);
              if (cVar6 == '\0') {
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

