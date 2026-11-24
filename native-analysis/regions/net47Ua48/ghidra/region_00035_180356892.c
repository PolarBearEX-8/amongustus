/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=35 entry=180356892 blocks=54 maxInstructions=50000 */

/* cut edge: 0x18035689a -> 0x1803351d6 */
/* cut edge: 0x1803568c0 -> 0x180358f2f */
/* cut edge: 0x1803568e7 -> 0x180358f65 */
/* cut edge: 0x180356949 -> 0x180358fb6 */
/* cut edge: 0x180356959 -> 0x180358ff0 */
/* cut edge: 0x180356a38 -> 0x1803351d6 */
/* cut edge: 0x180356b4d -> 0x1803351d6 */
/* cut edge: 0x180356b65 -> 0x180359604 */
/* cut edge: 0x180356b8c -> 0x18035963a */
/* cut edge: 0x180356bee -> 0x18035968b */
/* cut edge: 0x180356bfe -> 0x1803596c5 */
/* cut edge: 0x180356cdd -> 0x1803351d6 */
/* cut edge: 0x180356dcb -> 0x1803351d6 */
/* cut edge: 0x180356df1 -> 0x1803596fc */
/* cut edge: 0x180356e20 -> 0x180359737 */
/* cut edge: 0x180356e82 -> 0x180359788 */
/* cut edge: 0x180356e92 -> 0x1803597c1 */
/* cut edge: 0x180356f74 -> 0x1803351d6 */
/* cut edge: 0x180357080 -> 0x1803351d6 */
/* cut edge: 0x180357098 -> 0x18035a3a7 */
/* cut edge: 0x1803570bf -> 0x18035a3dd */
/* cut edge: 0x180357121 -> 0x18035a42e */
/* cut edge: 0x180357131 -> 0x18035a468 */
/* cut edge: 0x180357210 -> 0x1803351d6 */
/* cut edge: 0x180357306 -> 0x1803351d6 */
/* cut edge: 0x180357334 -> 0x180359027 */
/* cut edge: 0x18035735b -> 0x18035905d */
/* cut edge: 0x1803573bd -> 0x1803590ae */
/* cut edge: 0x1803573cd -> 0x1803590e8 */
/* cut edge: 0x1803574b5 -> 0x1803351d6 */
/* cut edge: 0x1803575a2 -> 0x180357b10 */
/* cut edge: 0x1803575c4 -> 0x1803351d6 */
/* cut edge: 0x1803575dc -> 0x1803597f9 */
/* cut edge: 0x18035760b -> 0x18035982f */
/* cut edge: 0x18035766d -> 0x180359880 */
/* cut edge: 0x18035767d -> 0x1803598ba */
/* cut edge: 0x18035776d -> 0x1803351d6 */
/* cut edge: 0x18035776d -> 0x18035785b */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x18035785b original byte 0x48 replaced by RET */
/* target 0x180357b10 original byte 0x84 replaced by RET */
/* target 0x180358f2f original byte 0x48 replaced by RET */
/* target 0x180358f65 original byte 0x48 replaced by RET */
/* target 0x180358fb6 original byte 0x48 replaced by RET */
/* target 0x180358ff0 original byte 0x48 replaced by RET */
/* target 0x180359027 original byte 0x48 replaced by RET */
/* target 0x18035905d original byte 0x48 replaced by RET */
/* target 0x1803590ae original byte 0x48 replaced by RET */
/* target 0x1803590e8 original byte 0x48 replaced by RET */
/* target 0x180359604 original byte 0x48 replaced by RET */
/* target 0x18035963a original byte 0x48 replaced by RET */
/* target 0x18035968b original byte 0x48 replaced by RET */
/* target 0x1803596c5 original byte 0x48 replaced by RET */
/* target 0x1803596fc original byte 0x48 replaced by RET */
/* target 0x180359737 original byte 0x48 replaced by RET */
/* target 0x180359788 original byte 0x48 replaced by RET */
/* target 0x1803597c1 original byte 0x48 replaced by RET */
/* target 0x1803597f9 original byte 0x48 replaced by RET */
/* target 0x18035982f original byte 0x48 replaced by RET */
/* target 0x180359880 original byte 0x48 replaced by RET */
/* target 0x1803598ba original byte 0x48 replaced by RET */
/* target 0x18035a3a7 original byte 0x48 replaced by RET */
/* target 0x18035a3dd original byte 0x48 replaced by RET */
/* target 0x18035a42e original byte 0x48 replaced by RET */
/* target 0x18035a468 original byte 0x48 replaced by RET */


void region_00035_180356892(void)

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
  uint in_stack_000000b0;
  uint in_stack_000000c0;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_00000110;
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  lVar5 = DAT_1804b59f8;
  uVar11 = DAT_1804b59f0;
  if (cVar6 == '\0') {
    if (DAT_1804b23b0 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b59f8 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000702,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001b88,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
      uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
      DAT_1804b23b0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
    }
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b23b0,in_stack_00000078);
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
    uVar7 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070);
    cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar6 == '\0') {
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar5 = DAT_1804b59f8;
      uVar11 = DAT_1804b59f0;
      if ((uVar7 & in_stack_000000c0) == 0) {
        if (cVar6 != '\0') {
          return;
        }
        if (DAT_1804b23c8 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b59f8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000006fc,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001b58,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
          DAT_1804b23c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b23c8,in_stack_00000078);
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
      }
      else {
        if (cVar6 != '\0') {
          return;
        }
        if (DAT_1804b23b8 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b59f8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000700,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001b78,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
          DAT_1804b23b8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b23b8,in_stack_00000078);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
        (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110);
        (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
        (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070);
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar6 != '\0') {
          return;
        }
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        lVar5 = DAT_1804b59f8;
        uVar11 = DAT_1804b59f0;
        if (cVar6 != '\0') {
          return;
        }
        if (DAT_1804b23c0 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b59f8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000006fe,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001b68,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
          DAT_1804b23c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b23c0,in_stack_00000078);
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
      }
      if ((cVar6 == '\0') &&
         (cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(), lVar5 = DAT_1804b59f8,
         uVar11 = DAT_1804b59f0, cVar6 == '\0')) {
        if (DAT_1804b23d0 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b59f8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x000006fa,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001b48,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
          DAT_1804b23d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b23d0,in_stack_00000078);
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
          if ((uVar7 & in_stack_000000b0) == 0) {
            return;
          }
          if (cVar6 == '\0') {
            if (DAT_1804b23d8 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59f8 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x000006f8,1);
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
                                (in_stack_00000048,&stack0x00001b38,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,in_stack_00000058);
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
              uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
              DAT_1804b23d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
            }
            uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b23d8,in_stack_00000078);
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
              return;
            }
          }
        }
      }
    }
  }
  return;
}

