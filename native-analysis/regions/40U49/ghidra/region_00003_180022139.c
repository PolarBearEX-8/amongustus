/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=3 entry=180022139 blocks=71 maxInstructions=100000 */

/* cut edge: 0x180022139 -> 0x18014d3db */
/* cut edge: 0x18002215f -> 0x18014d2bb */
/* cut edge: 0x18002218b -> 0x18014d2f3 */
/* cut edge: 0x1800221e4 -> 0x18014d344 */
/* cut edge: 0x1800221f7 -> 0x18014d37e */
/* cut edge: 0x1800222ed -> 0x18014d3db */
/* cut edge: 0x18002246b -> 0x18014d3db */
/* cut edge: 0x1800224a0 -> 0x18013cf0e */
/* cut edge: 0x1800224c4 -> 0x18013cf44 */
/* cut edge: 0x180022520 -> 0x18013cf95 */
/* cut edge: 0x180022530 -> 0x18013cfce */
/* cut edge: 0x180022606 -> 0x18014d3db */
/* cut edge: 0x180022754 -> 0x18014d3db */
/* cut edge: 0x1800227b1 -> 0x180025dd9 */
/* cut edge: 0x1800227d5 -> 0x180025e11 */
/* cut edge: 0x18002282a -> 0x180025e62 */
/* cut edge: 0x180022849 -> 0x180025e9b */
/* cut edge: 0x180022921 -> 0x18014d3db */
/* cut edge: 0x180022a50 -> 0x18014d3db */
/* cut edge: 0x180022a76 -> 0x180026606 */
/* cut edge: 0x180022a95 -> 0x18002663c */
/* cut edge: 0x180022ae7 -> 0x18002668d */
/* cut edge: 0x180022b06 -> 0x1800266c6 */
/* cut edge: 0x180022bd9 -> 0x18014d3db */
/* cut edge: 0x180022cdd -> 0x180023c83 */
/* cut edge: 0x180022ced -> 0x1800239fb */
/* cut edge: 0x180022cfb -> 0x18008a226 */
/* cut edge: 0x180022d2a -> 0x1800266fd */
/* cut edge: 0x180022e0a -> 0x18014d3db */
/* cut edge: 0x180022e28 -> 0x18014d3db */
/* cut edge: 0x180022e3e -> 0x18014d3db */
/* cut edge: 0x180022ea6 -> 0x18002674e */
/* cut edge: 0x180022eca -> 0x180026781 */
/* cut edge: 0x180022f2b -> 0x1800267d2 */
/* cut edge: 0x180022f3e -> 0x18002680b */
/* cut edge: 0x180023019 -> 0x18014d3db */
/* cut edge: 0x180023110 -> 0x18014d3db */
/* cut edge: 0x180023110 -> 0x180023126 */
/* target 0x180023126 original byte 0x48 replaced by RET */
/* target 0x1800239fb original byte 0x48 replaced by RET */
/* target 0x180023c83 original byte 0x84 replaced by RET */
/* target 0x180025dd9 original byte 0x48 replaced by RET */
/* target 0x180025e11 original byte 0x48 replaced by RET */
/* target 0x180025e62 original byte 0x48 replaced by RET */
/* target 0x180025e9b original byte 0x48 replaced by RET */
/* target 0x180026606 original byte 0x48 replaced by RET */
/* target 0x18002663c original byte 0x48 replaced by RET */
/* target 0x18002668d original byte 0x48 replaced by RET */
/* target 0x1800266c6 original byte 0x48 replaced by RET */
/* target 0x1800266fd original byte 0x48 replaced by RET */
/* target 0x18002674e original byte 0x48 replaced by RET */
/* target 0x180026781 original byte 0x48 replaced by RET */
/* target 0x1800267d2 original byte 0x48 replaced by RET */
/* target 0x18002680b original byte 0x48 replaced by RET */
/* target 0x18008a226 original byte 0x48 replaced by RET */
/* target 0x18013cf0e original byte 0x48 replaced by RET */
/* target 0x18013cf44 original byte 0x48 replaced by RET */
/* target 0x18013cf95 original byte 0x48 replaced by RET */
/* target 0x18013cfce original byte 0x48 replaced by RET */
/* target 0x18014d2bb original byte 0x48 replaced by RET */
/* target 0x18014d2f3 original byte 0x48 replaced by RET */
/* target 0x18014d344 original byte 0x48 replaced by RET */
/* target 0x18014d37e original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00003_180022139(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  char cVar4;
  int iVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  longlong lVar10;
  longlong lVar11;
  longlong lVar12;
  int iVar13;
  ulonglong uVar14;
  undefined4 unaff_R14D;
  bool bVar15;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000078;
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
  int iStack00000000000000e8;
  undefined8 in_stack_00000108;
  undefined8 in_stack_00000148;
  undefined8 in_stack_00000170;
  uint in_stack_00000188;
  undefined8 in_stack_00000de0;
  undefined8 in_stack_00005738;
  undefined8 in_stack_00005740;
  undefined8 in_stack_00005748;
  
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
  uVar9 = DAT_1804b5208;
  lVar10 = DAT_1804b5200;
  if (cVar4 == '\0') {
    if (DAT_1804ac1c8 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5200 == 0) {
        return;
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000bd2,1);
      lVar12 = DAT_1804b4d38;
      lVar11 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00005db0,0x19);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
      (*pcVar2)(in_stack_00000048,lVar11,lVar12,uVar7,uVar8);
      uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar10,uVar9,uVar6);
      DAT_1804ac1c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804ac1c8,in_stack_000000b0);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,5,in_stack_000000a0,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,unaff_R14D);
    (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
    (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar6,1);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00000de0);
    (*pcVar1)(in_stack_00000048,uVar6,2,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,0x162e0c65c1e7d598);
    (*pcVar1)(in_stack_00000048,uVar6,3,uVar7);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_000000c0);
    (*pcVar1)(in_stack_00000048,uVar6,4,uVar7);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
    lVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((cVar4 == '\0') &&
       (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), lVar11 = DAT_1804b52e8,
       uVar9 = DAT_1804b5208, cVar4 == '\0')) {
      if (DAT_1804ac1d0 == 0) {
        pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b52e8 == 0) {
          return;
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000bd0,1);
        lVar3 = DAT_1804b4d38;
        lVar12 = DAT_1804b4d30;
        pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return;
        }
        if (DAT_1804b4d38 == 0) {
          return;
        }
        uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000050b8,5);
        if (DAT_1804b4d40 == 0) {
          return;
        }
        uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
        (*pcVar2)(in_stack_00000048,lVar12,lVar3,uVar7,uVar8);
        uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar6);
        DAT_1804ac1d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,DAT_1804ac1d0,in_stack_000000b0);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2d81);
      (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
      pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
      uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005740);
      (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                        (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
      _iStack00000000000000e8 =
           (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
      cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar4 == '\0') {
        if ((in_stack_00000078 == 0x8000000000000000) &&
           (_iStack00000000000000e8 == 0xffffffffffffffff)) {
          _iStack00000000000000e8 = 0;
        }
        else if ((_iStack00000000000000e8 | in_stack_00000078) >> 0x20 == 0) {
          _iStack00000000000000e8 =
               (in_stack_00000078 & 0xffffffff) % (_iStack00000000000000e8 & 0xffffffff);
        }
        else {
          _iStack00000000000000e8 = (longlong)in_stack_00000078 % (longlong)_iStack00000000000000e8;
        }
        cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
        lVar11 = DAT_1804b5258;
        uVar9 = DAT_1804b5208;
        if (cVar4 == '\0') {
          uVar14 = _iStack00000000000000e8 & 0xffffffff;
          if (DAT_1804ac1d8 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b5258 == 0) {
              return;
            }
            uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000bce,1);
            lVar3 = DAT_1804b4d38;
            lVar12 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar7 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000050a8,5);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000090);
            (*pcVar2)(in_stack_00000048,lVar12,lVar3,uVar7,uVar8);
            uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar6);
            DAT_1804ac1d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,DAT_1804ac1d8,in_stack_000000b0);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5f32);
          (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_000000b8,in_stack_00005748
                            );
          (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                            (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
          iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                            (in_stack_00000048,uVar9,in_stack_00000108);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            if (iVar5 == iStack00000000000000e8) {
              uVar14 = (ulonglong)in_stack_00000188;
              iVar5 = 0;
            }
            else {
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
              lVar11 = DAT_1804b5258;
              uVar9 = DAT_1804b5208;
              if (cVar4 != '\0') {
                return;
              }
              if (DAT_1804ac1e0 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b5258 == 0) {
                  return;
                }
                uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00000bcc,1);
                lVar3 = DAT_1804b4d38;
                lVar12 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00005098,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000090);
                (*pcVar2)(in_stack_00000048,lVar12,lVar3,uVar7,uVar8);
                uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar6);
                DAT_1804ac1e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ac1e0,in_stack_000000b0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x5efd);
              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 in_stack_00005738);
              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
              iVar5 = (**(code **)(*in_stack_00000048 + 0x188))
                                (in_stack_00000048,uVar9,in_stack_00000108);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 != '\0') {
                return;
              }
              if (in_stack_00000170._7_1_ != '\0') {
                if (in_stack_00000148._3_1_ == '\0') {
                  return;
                }
                if (iVar5 == iStack00000000000000e8) {
                  return;
                }
                return;
              }
            }
            if (DAT_1804b55b0 == 0) {
              return;
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,uVar14,DAT_1804b55b0,0);
            if (0 < (int)uVar14) {
              iVar13 = 0;
              do {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x560);
                lVar11 = DAT_1804b5348;
                if (DAT_1804b5348 == 0) {
                  uVar6 = (**(code **)(*in_stack_00000048 + 0x30))(in_stack_00000048,"net/47ns");
                  lVar11 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                  lVar12 = 0;
                  LOCK();
                  bVar15 = DAT_1804b5348 != 0;
                  lVar3 = lVar11;
                  if (bVar15) {
                    lVar12 = DAT_1804b5348;
                    lVar3 = DAT_1804b5348;
                  }
                  DAT_1804b5348 = lVar3;
                  UNLOCK();
                  if (bVar15) {
                    (**(code **)(*in_stack_00000048 + 0xb0))(in_stack_00000048,lVar11);
                    lVar11 = lVar12;
                  }
                }
                (*pcVar1)(in_stack_00000048,iVar5,lVar11,0);
                (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar9,iVar13);
                iVar13 = iVar13 + 1;
              } while ((int)uVar14 != iVar13);
            }
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (((cVar4 == '\0') &&
                (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) &&
               (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), lVar11 = DAT_1804b52e8,
               uVar9 = DAT_1804b5208, cVar4 == '\0')) {
              if (DAT_1804ae480 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b52e8 == 0) {
                  return;
                }
                uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x0000030a,1);
                lVar3 = DAT_1804b4d38;
                lVar12 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar7 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00001558,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                  (in_stack_00000048,in_stack_00000090);
                (*pcVar2)(in_stack_00000048,lVar12,lVar3,uVar7,uVar8);
                uVar9 = (*pcVar1)(in_stack_00000048,in_stack_00000090,lVar11,uVar9,uVar6);
                DAT_1804ae480 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ae480,in_stack_000000b0);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x61c1);
              (*pcVar1)(in_stack_00000048,uVar6,0,uVar7);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 lVar10 % 879999 ^ in_stack_000000c0 ^ 0x36b26da40c37c5ae);
              (*pcVar1)(in_stack_00000048,uVar6,1,uVar7);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar9,in_stack_000000a8,uVar6);
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if ((cVar4 == '\0') &&
                 (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
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

