/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=32 entry=180353888 blocks=55 maxInstructions=50000 */

/* cut edge: 0x180353888 -> 0x1803351d6 */
/* cut edge: 0x1803538ae -> 0x180359311 */
/* cut edge: 0x1803538dd -> 0x18035934c */
/* cut edge: 0x18035393f -> 0x18035939d */
/* cut edge: 0x180353952 -> 0x1803593d6 */
/* cut edge: 0x180353a36 -> 0x1803351d6 */
/* cut edge: 0x180353b40 -> 0x1803351d6 */
/* cut edge: 0x180353b58 -> 0x18035a1b7 */
/* cut edge: 0x180353b87 -> 0x18035a1ed */
/* cut edge: 0x180353be9 -> 0x18035a23e */
/* cut edge: 0x180353bf9 -> 0x18035a278 */
/* cut edge: 0x180353ce0 -> 0x1803351d6 */
/* cut edge: 0x180353dda -> 0x1803351d6 */
/* cut edge: 0x180353e00 -> 0x180358b49 */
/* cut edge: 0x180353e27 -> 0x180358b7f */
/* cut edge: 0x180353e89 -> 0x180358bd0 */
/* cut edge: 0x180353e99 -> 0x180358c0a */
/* cut edge: 0x180353f78 -> 0x1803351d6 */
/* cut edge: 0x18035408f -> 0x1803351d6 */
/* cut edge: 0x1803540a7 -> 0x18035940d */
/* cut edge: 0x1803540d6 -> 0x180359448 */
/* cut edge: 0x180354123 -> 0x180359497 */
/* cut edge: 0x180354143 -> 0x1803594d1 */
/* cut edge: 0x180354227 -> 0x1803351d6 */
/* cut edge: 0x180354315 -> 0x1803351d6 */
/* cut edge: 0x18035433b -> 0x180359508 */
/* cut edge: 0x18035436a -> 0x180359543 */
/* cut edge: 0x1803543cc -> 0x180359594 */
/* cut edge: 0x1803543dc -> 0x1803595cd */
/* cut edge: 0x1803544c5 -> 0x1803351d6 */
/* cut edge: 0x1803545b7 -> 0x180354862 */
/* cut edge: 0x1803545d0 -> 0x1803351d6 */
/* cut edge: 0x1803545e8 -> 0x18035a2af */
/* cut edge: 0x18035460f -> 0x18035a2e5 */
/* cut edge: 0x180354671 -> 0x18035a336 */
/* cut edge: 0x180354681 -> 0x18035a370 */
/* cut edge: 0x180354768 -> 0x1803351d6 */
/* cut edge: 0x180354768 -> 0x18035485a */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18035485a original byte 0x44 replaced by RET */
/* target 0x180354862 original byte 0x48 replaced by RET */
/* target 0x180358b49 original byte 0x48 replaced by RET */
/* target 0x180358b7f original byte 0x48 replaced by RET */
/* target 0x180358bd0 original byte 0x48 replaced by RET */
/* target 0x180358c0a original byte 0x48 replaced by RET */
/* target 0x180359311 original byte 0x48 replaced by RET */
/* target 0x18035934c original byte 0x48 replaced by RET */
/* target 0x18035939d original byte 0x48 replaced by RET */
/* target 0x1803593d6 original byte 0x48 replaced by RET */
/* target 0x18035940d original byte 0x48 replaced by RET */
/* target 0x180359448 original byte 0x48 replaced by RET */
/* target 0x180359497 original byte 0x48 replaced by RET */
/* target 0x1803594d1 original byte 0x48 replaced by RET */
/* target 0x180359508 original byte 0x48 replaced by RET */
/* target 0x180359543 original byte 0x48 replaced by RET */
/* target 0x180359594 original byte 0x48 replaced by RET */
/* target 0x1803595cd original byte 0x48 replaced by RET */
/* target 0x18035a1b7 original byte 0x48 replaced by RET */
/* target 0x18035a1ed original byte 0x48 replaced by RET */
/* target 0x18035a23e original byte 0x48 replaced by RET */
/* target 0x18035a278 original byte 0x48 replaced by RET */
/* target 0x18035a2af original byte 0x48 replaced by RET */
/* target 0x18035a2e5 original byte 0x48 replaced by RET */
/* target 0x18035a336 original byte 0x48 replaced by RET */
/* target 0x18035a370 original byte 0x48 replaced by RET */


void region_00032_180353888(void)

{
  code *pcVar1;
  code *pcVar2;
  longlong lVar3;
  longlong lVar4;
  longlong lVar5;
  char cVar6;
  uint uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  longlong *in_stack_00000048;
  undefined8 in_stack_00000058;
  undefined8 in_stack_00000060;
  undefined8 in_stack_00000068;
  undefined8 in_stack_00000070;
  undefined8 in_stack_00000078;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000d0;
  uint in_stack_00000100;
  undefined8 in_stack_00000110;
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  lVar5 = DAT_1804b59f8;
  uVar11 = DAT_1804b59f0;
  if (cVar6 == '\0') {
    if (DAT_1804b2320 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b59f8 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000726,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001ca8,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
      uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
      DAT_1804b2320 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
    }
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2320,in_stack_00000078);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
    (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
    (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
    (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070);
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar5 = DAT_1804b59f8;
      uVar11 = DAT_1804b59f0;
      if (cVar6 == '\0') {
        if (DAT_1804b2330 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b59f8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000722,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001c88,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
          DAT_1804b2330 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2330,in_stack_00000078);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar11,in_stack_00000070);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 == '\0') {
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          lVar5 = DAT_1804b59f8;
          uVar11 = DAT_1804b59f0;
          if ((uVar7 & in_stack_00000100) == 0) {
            if (cVar6 == '\0') {
              if (DAT_1804b2348 == 0) {
                pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b59f8 == 0) {
                  return;
                }
                uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x0000071c,1);
                lVar4 = DAT_1804b4d38;
                lVar3 = DAT_1804b4d30;
                pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                if (DAT_1804b4d30 == 0) {
                  return;
                }
                if (DAT_1804b4d38 == 0) {
                  return;
                }
                uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                  (in_stack_00000048,&stack0x00001c58,5);
                if (DAT_1804b4d40 == 0) {
                  return;
                }
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,in_stack_00000058);
                (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
                DAT_1804b2348 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
              }
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b2348,in_stack_00000078);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_00000088,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
              (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                 in_stack_000000a8);
              (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
              uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
              (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070);
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar6 == '\0') {
                return;
              }
            }
          }
          else if (cVar6 == '\0') {
            if (DAT_1804b2338 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59f8 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000720,1);
              lVar4 = DAT_1804b4d38;
              lVar3 = DAT_1804b4d30;
              pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return;
              }
              if (DAT_1804b4d38 == 0) {
                return;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00001c78,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,in_stack_00000058);
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
              uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
              DAT_1804b2338 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
            }
            uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b2338,in_stack_00000078);
            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
            (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_00000110);
            (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
            uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
            (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070);
            cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar6 == '\0') {
              cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
              lVar5 = DAT_1804b59f8;
              uVar11 = DAT_1804b59f0;
              if (cVar6 == '\0') {
                if (DAT_1804b2340 == 0) {
                  pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
                  if (DAT_1804b59f8 == 0) {
                    return;
                  }
                  uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                    (in_stack_00000048,&stack0x0000071e,1);
                  lVar4 = DAT_1804b4d38;
                  lVar3 = DAT_1804b4d30;
                  pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
                  if (DAT_1804b4d30 == 0) {
                    return;
                  }
                  if (DAT_1804b4d38 == 0) {
                    return;
                  }
                  uVar9 = (**(code **)(*in_stack_00000048 + 0x518))
                                    (in_stack_00000048,&stack0x00001c68,5);
                  if (DAT_1804b4d40 == 0) {
                    return;
                  }
                  uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                     (in_stack_00000048,in_stack_00000058);
                  (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
                  uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
                  DAT_1804b2340 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
                }
                uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,DAT_1804b2340,in_stack_00000078);
                uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                  (in_stack_00000048,2,in_stack_00000088,0);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
                (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                                   in_stack_000000a8);
                (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
                (**(code **)(*in_stack_00000048 + 0x188))
                          (in_stack_00000048,uVar11,in_stack_00000070);
                cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (cVar6 == '\0') {
                  return;
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

