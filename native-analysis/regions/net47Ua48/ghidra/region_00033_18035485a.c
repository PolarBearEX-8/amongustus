/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=33 entry=18035485a blocks=58 maxInstructions=50000 */

/* cut edge: 0x180354862 -> 0x1803351d6 */
/* cut edge: 0x180354888 -> 0x180358c41 */
/* cut edge: 0x1803548af -> 0x180358c77 */
/* cut edge: 0x180354911 -> 0x180358cc8 */
/* cut edge: 0x180354921 -> 0x180358d02 */
/* cut edge: 0x180354a08 -> 0x1803351d6 */
/* cut edge: 0x180354b2b -> 0x1803351d6 */
/* cut edge: 0x180354b43 -> 0x1803599ef */
/* cut edge: 0x180354b6a -> 0x180359a25 */
/* cut edge: 0x180354bcc -> 0x180359a76 */
/* cut edge: 0x180354bdc -> 0x180359ab0 */
/* cut edge: 0x180354cc3 -> 0x1803351d6 */
/* cut edge: 0x180354db1 -> 0x1803351d6 */
/* cut edge: 0x180354dd7 -> 0x180359ae7 */
/* cut edge: 0x180354e06 -> 0x180359b22 */
/* cut edge: 0x180354e68 -> 0x180359b73 */
/* cut edge: 0x180354e78 -> 0x180359bac */
/* cut edge: 0x180354f61 -> 0x1803351d6 */
/* cut edge: 0x180355080 -> 0x1803351d6 */
/* cut edge: 0x180355098 -> 0x18035a597 */
/* cut edge: 0x1803550bf -> 0x18035a5cd */
/* cut edge: 0x180355121 -> 0x18035a61e */
/* cut edge: 0x180355131 -> 0x18035a658 */
/* cut edge: 0x180355218 -> 0x1803351d6 */
/* cut edge: 0x180355312 -> 0x1803351d6 */
/* cut edge: 0x180355338 -> 0x180358d39 */
/* cut edge: 0x18035535f -> 0x180358d74 */
/* cut edge: 0x1803553ac -> 0x180358dc6 */
/* cut edge: 0x1803553d4 -> 0x180358e00 */
/* cut edge: 0x1803554b3 -> 0x1803351d6 */
/* cut edge: 0x1803555a5 -> 0x180355b30 */
/* cut edge: 0x1803555b4 -> 0x180355b40 */
/* cut edge: 0x1803555db -> 0x1803351d6 */
/* cut edge: 0x1803555f3 -> 0x180359be3 */
/* cut edge: 0x180355622 -> 0x180359c19 */
/* cut edge: 0x180355684 -> 0x180359c6a */
/* cut edge: 0x180355694 -> 0x180359ca4 */
/* cut edge: 0x18035577b -> 0x1803351d6 */
/* cut edge: 0x18035577b -> 0x180355869 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180355869 original byte 0x48 replaced by RET */
/* target 0x180355b30 original byte 0x48 replaced by RET */
/* target 0x180355b40 original byte 0x84 replaced by RET */
/* target 0x180358c41 original byte 0x48 replaced by RET */
/* target 0x180358c77 original byte 0x48 replaced by RET */
/* target 0x180358cc8 original byte 0x48 replaced by RET */
/* target 0x180358d02 original byte 0x48 replaced by RET */
/* target 0x180358d39 original byte 0x48 replaced by RET */
/* target 0x180358d74 original byte 0x48 replaced by RET */
/* target 0x180358dc6 original byte 0x48 replaced by RET */
/* target 0x180358e00 original byte 0x48 replaced by RET */
/* target 0x1803599ef original byte 0x48 replaced by RET */
/* target 0x180359a25 original byte 0x48 replaced by RET */
/* target 0x180359a76 original byte 0x48 replaced by RET */
/* target 0x180359ab0 original byte 0x48 replaced by RET */
/* target 0x180359ae7 original byte 0x48 replaced by RET */
/* target 0x180359b22 original byte 0x48 replaced by RET */
/* target 0x180359b73 original byte 0x48 replaced by RET */
/* target 0x180359bac original byte 0x48 replaced by RET */
/* target 0x180359be3 original byte 0x48 replaced by RET */
/* target 0x180359c19 original byte 0x48 replaced by RET */
/* target 0x180359c6a original byte 0x48 replaced by RET */
/* target 0x180359ca4 original byte 0x48 replaced by RET */
/* target 0x18035a597 original byte 0x48 replaced by RET */
/* target 0x18035a5cd original byte 0x48 replaced by RET */
/* target 0x18035a61e original byte 0x48 replaced by RET */
/* target 0x18035a658 original byte 0x48 replaced by RET */


void region_00033_18035485a(void)

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
  uint in_stack_000000b8;
  longlong in_stack_000000c8;
  undefined8 in_stack_000000d0;
  uint in_stack_00000100;
  undefined8 in_stack_00000110;
  
  cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
  lVar5 = DAT_1804b59f8;
  uVar11 = DAT_1804b59f0;
  if (cVar6 == '\0') {
    if (DAT_1804b2350 == 0) {
      pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b59f8 == 0) {
        return;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000071a,1);
      lVar4 = DAT_1804b4d38;
      lVar3 = DAT_1804b4d30;
      pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return;
      }
      if (DAT_1804b4d38 == 0) {
        return;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001c48,5);
      if (DAT_1804b4d40 == 0) {
        return;
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
      (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
      uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
      DAT_1804b2350 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
    }
    uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2350,in_stack_00000078);
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
      if (in_stack_000000c8 != 0) {
        cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
        lVar5 = DAT_1804b59f8;
        uVar11 = DAT_1804b59f0;
        if ((in_stack_00000100 & uVar7) == 0) {
          if (cVar6 != '\0') {
            return;
          }
          if (DAT_1804b2368 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59f8 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000714,1);
            lVar4 = DAT_1804b4d38;
            lVar3 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001c18,5);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
            uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
            DAT_1804b2368 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2368,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8
                            );
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
          if (DAT_1804b2358 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59f8 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000718,1);
            lVar4 = DAT_1804b4d38;
            lVar3 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001c38,5);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
            uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
            DAT_1804b2358 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2358,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110
                            );
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
          if (DAT_1804b2360 == 0) {
            pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59f8 == 0) {
              return;
            }
            uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000716,1);
            lVar4 = DAT_1804b4d38;
            lVar3 = DAT_1804b4d30;
            pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return;
            }
            if (DAT_1804b4d38 == 0) {
              return;
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001c28,5);
            if (DAT_1804b4d40 == 0) {
              return;
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
            uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
            DAT_1804b2360 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
          }
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2360,in_stack_00000078);
          uVar8 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
          (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
          pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8
                            );
          (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
          uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar11,in_stack_00000080,uVar8);
          (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar11,in_stack_00000070);
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        }
        if (cVar6 != '\0') {
          return;
        }
      }
      cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
      lVar5 = DAT_1804b59f8;
      uVar11 = DAT_1804b59f0;
      if (cVar6 == '\0') {
        if (DAT_1804b2370 == 0) {
          pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b59f8 == 0) {
            return;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000712,1);
          lVar4 = DAT_1804b4d38;
          lVar3 = DAT_1804b4d30;
          pcVar2 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return;
          }
          if (DAT_1804b4d38 == 0) {
            return;
          }
          uVar9 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00001c08,5);
          if (DAT_1804b4d40 == 0) {
            return;
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
          (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
          uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
          DAT_1804b2370 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
        }
        uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b2370,in_stack_00000078);
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
          if (in_stack_000000c8 == 0) {
            return;
          }
          cVar6 = (**(code **)(*in_stack_00000048 + 0x720))();
          lVar5 = DAT_1804b59f8;
          uVar11 = DAT_1804b59f0;
          if ((uVar7 & in_stack_000000b8) == 0) {
            return;
          }
          if (cVar6 == '\0') {
            if (DAT_1804b2378 == 0) {
              pcVar1 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59f8 == 0) {
                return;
              }
              uVar8 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000710,1);
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
                                (in_stack_00000048,&stack0x00001bf8,5);
              if (DAT_1804b4d40 == 0) {
                return;
              }
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,in_stack_00000058);
              (*pcVar2)(in_stack_00000048,lVar3,lVar4,uVar9,uVar10);
              uVar11 = (*pcVar1)(in_stack_00000048,in_stack_00000058,lVar5,uVar11,uVar8);
              DAT_1804b2378 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar11);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar11);
            }
            uVar11 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b2378,in_stack_00000078);
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

