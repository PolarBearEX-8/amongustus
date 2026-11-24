/* Synthetic CFG slice from net/40U.49(J)V; outgoing edges were patched to RET in a temporary project. */
/* region=273 entry=180130221 blocks=30 maxInstructions=100000 */

/* cut edge: 0x180130221 -> 0x18014d3db */
/* cut edge: 0x180130318 -> 0x18014d3db */
/* cut edge: 0x18013032e -> 0x18014d3db */
/* cut edge: 0x1801304e4 -> 0x18014d3db */
/* cut edge: 0x1801305db -> 0x18014d3db */
/* cut edge: 0x1801305f1 -> 0x18014d3db */
/* cut edge: 0x1801307ad -> 0x18014d3db */
/* cut edge: 0x1801308a2 -> 0x18014d3db */
/* cut edge: 0x1801308b8 -> 0x18014d3db */
/* cut edge: 0x180130a7f -> 0x18014d3db */
/* cut edge: 0x180130b7b -> 0x180132b7b */
/* cut edge: 0x180130ba7 -> 0x1801253d7 */
/* cut edge: 0x180130bc0 -> 0x18013db45 */
/* cut edge: 0x180130bdf -> 0x18014d3db */
/* cut edge: 0x180130c0a -> 0x18014d3db */
/* cut edge: 0x180130c20 -> 0x18014d3db */
/* cut edge: 0x180130de3 -> 0x18014d3db */
/* cut edge: 0x180130ec7 -> 0x18014d3db */
/* cut edge: 0x180130edd -> 0x18014d3db */
/* cut edge: 0x1801310c9 -> 0x18014d3db */
/* cut edge: 0x1801310c9 -> 0x180131209 */
/* target 0x1801253d7 original byte 0x48 replaced by RET */
/* target 0x180131209 original byte 0x48 replaced by RET */
/* target 0x180132b7b original byte 0x31 replaced by RET */
/* target 0x18013db45 original byte 0x48 replaced by RET */
/* target 0x18014d3db original byte 0xf replaced by RET */


void region_00273_180130221(undefined8 param_1,undefined8 param_2)

{
  code *pcVar1;
  code *pcVar2;
  code *pcVar3;
  longlong lVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  ulonglong uVar13;
  longlong lVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
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
  undefined8 in_stack_00000138;
  undefined8 in_stack_000055d8;
  
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,param_2,in_stack_000000b0);
  uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x17c);
  (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
  uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                    (in_stack_00000048,in_stack_00000098,in_stack_000000b8);
  (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
  uVar6 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,uVar6,in_stack_000000a8,uVar7)
  ;
  (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar5 == '\0') {
    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar5 == '\0') {
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
      if (cVar5 == '\0') {
        if (DAT_1804ac9c0 == 0) {
          lVar14 = *in_stack_00000048;
          pcVar1 = *(code **)(lVar14 + 0x390);
          uVar7 = FUN_18014d730(in_stack_00000048);
          uVar6 = DAT_1804b5208;
          uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009d0,1);
          lVar14 = *in_stack_00000048;
          pcVar2 = *(code **)(lVar14 + 0x390);
          uVar9 = FUN_1800024f0(in_stack_00000048);
          uVar10 = FUN_180002550(in_stack_00000048);
          uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004228,5);
          pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
          uVar12 = FUN_1800025a0(in_stack_00000048);
          uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
          (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
          uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
          DAT_1804ac9c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804ac9c0,in_stack_000000b0);
        uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_000000a0,0);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x14a1);
        (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
        pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                           (longlong)in_stack_00000058 % 0xa8fd6 ^ in_stack_000000c0 ^
                           0x6d879e4e13625afa);
        (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
        uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar6,in_stack_000000d8);
        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if (cVar5 == '\0') {
          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
          if (cVar5 == '\0') {
            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
            if (cVar5 == '\0') {
              if (DAT_1804ac9c8 == 0) {
                lVar14 = *in_stack_00000048;
                pcVar1 = *(code **)(lVar14 + 0x390);
                uVar7 = FUN_18014d730(in_stack_00000048);
                uVar6 = DAT_1804b5208;
                uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009ce,1);
                lVar14 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar14 + 0x390);
                uVar9 = FUN_1800024f0(in_stack_00000048);
                uVar10 = FUN_180002550(in_stack_00000048);
                uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004218,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar12 = FUN_1800025a0(in_stack_00000048);
                uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                DAT_1804ac9c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,DAT_1804ac9c8,in_stack_000000b0);
              uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                (in_stack_00000048,2,in_stack_000000a0,0);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0x2ac4);
              (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
              pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
              uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                 (longlong)in_stack_00000058 % 0x570db ^ in_stack_000000c0 ^
                                 0x4d8bc03a1fdbfdaf);
              (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
              uVar13 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                 (in_stack_00000048,uVar6,in_stack_000000d8);
              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar5 == '\0') {
                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                if (cVar5 == '\0') {
                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                  if (cVar5 == '\0') {
                    if (DAT_1804ac9d0 == 0) {
                      lVar14 = *in_stack_00000048;
                      pcVar1 = *(code **)(lVar14 + 0x390);
                      uVar7 = FUN_18014d730(in_stack_00000048);
                      uVar6 = DAT_1804b5208;
                      uVar8 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x000009cc,1);
                      lVar14 = *in_stack_00000048;
                      pcVar2 = *(code **)(lVar14 + 0x390);
                      uVar9 = FUN_1800024f0(in_stack_00000048);
                      uVar10 = FUN_180002550(in_stack_00000048);
                      uVar11 = (**(code **)(lVar14 + 0x518))(in_stack_00000048,&stack0x00004208,5);
                      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                      uVar12 = FUN_1800025a0(in_stack_00000048);
                      uVar12 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar12);
                      (*pcVar2)(in_stack_00000048,uVar9,uVar10,uVar11,uVar12);
                      uVar6 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar7,uVar6,uVar8);
                      DAT_1804ac9d0 =
                           (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar6);
                      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar6);
                    }
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,DAT_1804ac9d0,in_stack_000000b0);
                    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))
                                      (in_stack_00000048,2,in_stack_000000a0,0);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_000000c8,in_stack_000000d0,0xde0);
                    (*pcVar1)(in_stack_00000048,uVar7,0,uVar8);
                    pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                                      (in_stack_00000048,in_stack_00000098,in_stack_000000b8,
                                       (longlong)in_stack_00000058 % 0xce97b ^ in_stack_000000c0 ^
                                       0x2d3e79318978e2b7);
                    (*pcVar1)(in_stack_00000048,uVar7,1,uVar8);
                    uVar6 = (**(code **)(*in_stack_00000048 + 0x110))
                                      (in_stack_00000048,uVar6,in_stack_000000a8,uVar7);
                    lVar14 = (**(code **)(*in_stack_00000048 + 0x1a0))
                                       (in_stack_00000048,uVar6,in_stack_000000d8);
                    cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                    if (cVar5 == '\0') {
                      lVar14 = (in_stack_00000058 ^ uVar13) - lVar14;
                      if ((longlong)in_stack_000000c0 < 1) {
                        return;
                      }
                      if (in_stack_000000f8 == 0) {
                        return;
                      }
                      if (DAT_1804b5338 == 0) {
                        return;
                      }
                      uVar6 = (**(code **)(*in_stack_00000048 + 0x2f8))
                                        (in_stack_00000048,in_stack_00000138);
                      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                      if (cVar5 == '\0') {
                        cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                        if (cVar5 == '\0') {
                          cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                          if (cVar5 == '\0') {
                            if (DAT_1804ac9d8 == 0) {
                              lVar4 = *in_stack_00000048;
                              pcVar1 = *(code **)(lVar4 + 0x390);
                              uVar8 = FUN_18001ed20(in_stack_00000048);
                              uVar7 = DAT_1804b5208;
                              uVar9 = (**(code **)(lVar4 + 0x518))
                                                (in_stack_00000048,&stack0x000009ca,1);
                              lVar4 = *in_stack_00000048;
                              pcVar2 = *(code **)(lVar4 + 0x390);
                              uVar10 = FUN_1800024f0(in_stack_00000048);
                              uVar11 = FUN_180002550(in_stack_00000048);
                              uVar12 = (**(code **)(lVar4 + 0x518))
                                                 (in_stack_00000048,&stack0x00006c70,0x16);
                              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                              uVar15 = FUN_1800025a0(in_stack_00000048);
                              uVar15 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar15);
                              (*pcVar2)(in_stack_00000048,uVar10,uVar11,uVar12,uVar15);
                              uVar7 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar8,uVar7,
                                                uVar9);
                              DAT_1804ac9d8 =
                                   (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar7)
                              ;
                              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar7);
                            }
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,DAT_1804ac9d8,in_stack_000000b0);
                            uVar8 = (**(code **)(*in_stack_00000048 + 0x560))
                                              (in_stack_00000048,2,in_stack_000000a0,0);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_000000c8,in_stack_000000d0
                                               ,0x5d97);
                            (*pcVar1)(in_stack_00000048,uVar8,0,uVar9);
                            pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                                              (in_stack_00000048,in_stack_00000098,in_stack_000000b8
                                               ,lVar14 % 0x82b64 ^ in_stack_000000c0 ^
                                                0x582b87a1a481c069);
                            (*pcVar1)(in_stack_00000048,uVar8,1,uVar9);
                            uVar7 = (**(code **)(*in_stack_00000048 + 0x110))
                                              (in_stack_00000048,uVar7,in_stack_000000a8,uVar8);
                            cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                            if (cVar5 == '\0') {
                              cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                              if (cVar5 == '\0') {
                                cVar5 = (**(code **)(*in_stack_00000048 + 0x720))();
                                if (cVar5 == '\0') {
                                  if (DAT_1804ac9e0 == 0) {
                                    lVar4 = *in_stack_00000048;
                                    pcVar1 = *(code **)(lVar4 + 0x390);
                                    uVar9 = FUN_18001e7d0(in_stack_00000048);
                                    uVar8 = DAT_1804b5208;
                                    uVar10 = (**(code **)(lVar4 + 0x518))
                                                       (in_stack_00000048,&stack0x000009c8,1);
                                    lVar4 = *in_stack_00000048;
                                    pcVar2 = *(code **)(lVar4 + 0x390);
                                    uVar11 = FUN_1800024f0(in_stack_00000048);
                                    uVar12 = FUN_180002550(in_stack_00000048);
                                    uVar15 = (**(code **)(lVar4 + 0x518))
                                                       (in_stack_00000048,&stack0x00007c20,0x2a);
                                    pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                                    uVar16 = FUN_1800025a0(in_stack_00000048);
                                    uVar16 = (*pcVar3)(in_stack_00000048,in_stack_00000090,uVar16);
                                    (*pcVar2)(in_stack_00000048,uVar11,uVar12,uVar15,uVar16);
                                    uVar8 = (*pcVar1)(in_stack_00000048,in_stack_00000090,uVar9,
                                                      uVar8,uVar10);
                                    DAT_1804ac9e0 =
                                         (**(code **)(*in_stack_00000048 + 0xa8))
                                                   (in_stack_00000048,uVar8);
                                    (**(code **)(*in_stack_00000048 + 0xb8))
                                              (in_stack_00000048,uVar8);
                                  }
                                  uVar8 = (**(code **)(*in_stack_00000048 + 0x110))
                                                    (in_stack_00000048,DAT_1804ac9e0,
                                                     in_stack_000000b0);
                                  uVar9 = (**(code **)(*in_stack_00000048 + 0x560))
                                                    (in_stack_00000048,5,in_stack_000000a0,0);
                                  (**(code **)(*in_stack_00000048 + 0x570))
                                            (in_stack_00000048,uVar9,0,uVar6);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000098,
                                                     in_stack_000000b8,in_stack_000055d8);
                                  (*pcVar1)(in_stack_00000048,uVar9,1,uVar6);
                                  (**(code **)(*in_stack_00000048 + 0x570))
                                            (in_stack_00000048,uVar9,2,uVar7);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000098,
                                                     in_stack_000000b8,
                                                     lVar14 % 0x549e1 ^ 0x1619b6bc8f9d9a54);
                                  (*pcVar1)(in_stack_00000048,uVar9,3,uVar6);
                                  pcVar1 = *(code **)(*in_stack_00000048 + 0x570);
                                  uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                                    (in_stack_00000048,in_stack_00000098,
                                                     in_stack_000000b8,in_stack_000000c0);
                                  (*pcVar1)(in_stack_00000048,uVar9,4,uVar6);
                                  (**(code **)(*in_stack_00000048 + 0x110))
                                            (in_stack_00000048,uVar8,in_stack_000000a8,uVar9);
                                  cVar5 = (**(code **)(*in_stack_00000048 + 0x720))
                                                    (in_stack_00000048);
                                  if (cVar5 == '\0') {
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

