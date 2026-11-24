/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=60 entry=180417583 blocks=51 maxInstructions=50000 */

/* cut edge: 0x180417583 -> 0x180473ae8 */
/* cut edge: 0x180417599 -> 0x180473ae8 */
/* cut edge: 0x180417604 -> 0x180430c4a */
/* cut edge: 0x18041762d -> 0x180430c7d */
/* cut edge: 0x180417697 -> 0x180430cce */
/* cut edge: 0x1804176a7 -> 0x180430d07 */
/* cut edge: 0x18041778d -> 0x180473ae8 */
/* cut edge: 0x180417899 -> 0x180436c35 */
/* cut edge: 0x1804178ba -> 0x180473ae8 */
/* cut edge: 0x1804178c2 -> 0x180473ae8 */
/* cut edge: 0x18041792a -> 0x1804330af */
/* cut edge: 0x18041794e -> 0x1804330e2 */
/* cut edge: 0x1804179b8 -> 0x180433133 */
/* cut edge: 0x1804179c8 -> 0x18043316c */
/* cut edge: 0x180417aa9 -> 0x180473ae8 */
/* cut edge: 0x180417b9e -> 0x180473ae8 */
/* cut edge: 0x180417bb4 -> 0x180473ae8 */
/* cut edge: 0x180417c21 -> 0x1804331a3 */
/* cut edge: 0x180417c45 -> 0x1804331d6 */
/* cut edge: 0x180417cac -> 0x180433224 */
/* cut edge: 0x180417cc4 -> 0x18043325e */
/* cut edge: 0x180417da7 -> 0x180473ae8 */
/* cut edge: 0x180417e9e -> 0x180473ae8 */
/* cut edge: 0x180417eb4 -> 0x180473ae8 */
/* cut edge: 0x180417f24 -> 0x180433295 */
/* cut edge: 0x180417f48 -> 0x1804332c8 */
/* cut edge: 0x180417fb2 -> 0x180433319 */
/* cut edge: 0x180417fc2 -> 0x180433352 */
/* cut edge: 0x1804180a3 -> 0x180473ae8 */
/* cut edge: 0x180418198 -> 0x180473ae8 */
/* cut edge: 0x1804181ae -> 0x180473ae8 */
/* cut edge: 0x180418219 -> 0x180433389 */
/* cut edge: 0x18041823d -> 0x1804333bc */
/* cut edge: 0x1804182a4 -> 0x18043340a */
/* cut edge: 0x1804182bc -> 0x180433443 */
/* cut edge: 0x18041839f -> 0x180473ae8 */
/* cut edge: 0x180418496 -> 0x1804378ed */
/* cut edge: 0x1804184aa -> 0x180473ae8 */
/* cut edge: 0x1804184c0 -> 0x180473ae8 */
/* cut edge: 0x1804184d6 -> 0x1804186c1 */
/* cut edge: 0x1804184d6 -> 0x180418542 */
/* target 0x180418542 original byte 0x48 replaced by RET */
/* target 0x1804186c1 original byte 0x48 replaced by RET */
/* target 0x180430c4a original byte 0x48 replaced by RET */
/* target 0x180430c7d original byte 0x48 replaced by RET */
/* target 0x180430cce original byte 0x48 replaced by RET */
/* target 0x180430d07 original byte 0x48 replaced by RET */
/* target 0x1804330af original byte 0x48 replaced by RET */
/* target 0x1804330e2 original byte 0x48 replaced by RET */
/* target 0x180433133 original byte 0x48 replaced by RET */
/* target 0x18043316c original byte 0x48 replaced by RET */
/* target 0x1804331a3 original byte 0x48 replaced by RET */
/* target 0x1804331d6 original byte 0x48 replaced by RET */
/* target 0x180433224 original byte 0x48 replaced by RET */
/* target 0x18043325e original byte 0x48 replaced by RET */
/* target 0x180433295 original byte 0x48 replaced by RET */
/* target 0x1804332c8 original byte 0x48 replaced by RET */
/* target 0x180433319 original byte 0x48 replaced by RET */
/* target 0x180433352 original byte 0x48 replaced by RET */
/* target 0x180433389 original byte 0x48 replaced by RET */
/* target 0x1804333bc original byte 0x48 replaced by RET */
/* target 0x18043340a original byte 0x48 replaced by RET */
/* target 0x180433443 original byte 0x48 replaced by RET */
/* target 0x180436c35 original byte 0x84 replaced by RET */
/* target 0x1804378ed original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


code * region_00060_180417583(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  code *pcVar4;
  undefined8 uVar5;
  code *pcVar6;
  code *pcVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  longlong lVar10;
  ulonglong unaff_R14;
  longlong *in_stack_00000048;
  ulonglong in_stack_00000058;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  longlong in_stack_00000118;
  undefined8 in_stack_000001a8;
  short in_stack_00004280;
  
  pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  if (((char)pcVar4 == '\0') &&
     (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar10 = DAT_1804b5a08,
     uVar9 = DAT_1804b59f0, (char)pcVar4 == '\0')) {
    if (DAT_1804b41c0 == 0) {
      pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b5a08 == 0) {
        return (code *)0x0;
      }
      uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000045e,1);
      lVar2 = DAT_1804b4d38;
      lVar1 = DAT_1804b4d30;
      pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar6;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar6;
      }
      pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00001730,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar7;
      }
      uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
      (*pcVar6)(in_stack_00000048,lVar1,lVar2,pcVar7,uVar8);
      uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar10,uVar9,uVar5);
      DAT_1804b41c0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
    }
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,DAT_1804b41c0,in_stack_000000a0);
    uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5d60);
    (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                       (longlong)in_stack_00000058 % 0x71000 ^ in_stack_000000c8 ^
                       0xa71fad57a47ee610);
    (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                      (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
    lVar10 = (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
    pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if ((char)pcVar4 == '\0') {
      lVar10 = lVar10 + (unaff_R14 ^ in_stack_00000058);
      if (in_stack_00000118 == 0) {
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if (in_stack_000001a8._4_4_ < 4) {
          return pcVar4;
        }
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        lVar1 = DAT_1804b5a08;
        uVar9 = DAT_1804b59f0;
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        if (DAT_1804b49c8 == 0) {
          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return (code *)0x0;
          }
          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000256,1);
          lVar3 = DAT_1804b4d38;
          lVar2 = DAT_1804b4d30;
          pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar6;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar6;
          }
          pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00000b60,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar7;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
          uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar9,uVar5);
          DAT_1804b49c8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b49c8,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2813);
        (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           lVar10 % 0x61c1a ^ in_stack_000000c8 ^ 0x35715860cf801ad1);
        (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        lVar1 = DAT_1804b5a08;
        uVar9 = DAT_1804b59f0;
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        if (DAT_1804b49d0 == 0) {
          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return (code *)0x0;
          }
          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000254,1);
          lVar3 = DAT_1804b4d38;
          lVar2 = DAT_1804b4d30;
          pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar6;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar6;
          }
          pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00000b50,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar7;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
          uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar9,uVar5);
          DAT_1804b49d0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b49d0,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5400);
        (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           lVar10 % 0xb04a5 ^ in_stack_000000c8 ^ 0x484d59307b081537);
        (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        lVar1 = DAT_1804b5a08;
        uVar9 = DAT_1804b59f0;
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        if (DAT_1804b49d8 == 0) {
          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return (code *)0x0;
          }
          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000252,1);
          lVar3 = DAT_1804b4d38;
          lVar2 = DAT_1804b4d30;
          pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar6;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar6;
          }
          pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00000b40,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar7;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
          uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar9,uVar5);
          DAT_1804b49d8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b49d8,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2475);
        (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           lVar10 % 0x31dde ^ in_stack_000000c8 ^ 0x1820b22e4131b27e);
        (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        lVar1 = DAT_1804b5a08;
        uVar9 = DAT_1804b59f0;
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        if (DAT_1804b49e0 == 0) {
          pcVar4 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b5a08 == 0) {
            return (code *)0x0;
          }
          uVar5 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000250,1);
          lVar3 = DAT_1804b4d38;
          lVar2 = DAT_1804b4d30;
          pcVar6 = *(code **)(*in_stack_00000048 + 0x390);
          if (DAT_1804b4d30 == 0) {
            return pcVar6;
          }
          if (DAT_1804b4d38 == 0) {
            return pcVar6;
          }
          pcVar7 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                     (in_stack_00000048,&stack0x00000b30,5);
          if (DAT_1804b4d40 == 0) {
            return pcVar7;
          }
          uVar8 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000088);
          (*pcVar6)(in_stack_00000048,lVar2,lVar3,pcVar7,uVar8);
          uVar9 = (*pcVar4)(in_stack_00000048,in_stack_00000088,lVar1,uVar9,uVar5);
          DAT_1804b49e0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar9);
          (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar9);
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,DAT_1804b49e0,in_stack_000000a0);
        uVar5 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x5a13);
        (*pcVar4)(in_stack_00000048,uVar5,0,uVar8);
        pcVar4 = *(code **)(*in_stack_00000048 + 0x570);
        uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                          (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                           lVar10 % 0xd756c ^ in_stack_000000c8 ^ 0x3a23299c34c3d737);
        (*pcVar4)(in_stack_00000048,uVar5,1,uVar8);
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))
                          (in_stack_00000048,uVar9,in_stack_000000a8,uVar5);
        (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar9,in_stack_000000d8);
        pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
        if ((char)pcVar4 != '\0') {
          return pcVar4;
        }
        if (in_stack_00004280 == 0) {
          return pcVar4;
        }
      }
      pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
      if (((char)pcVar4 == '\0') &&
         (pcVar4 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), (char)pcVar4 == '\0')) {
        if (DAT_1804b41c8 != 0) {
          return (code *)0x8c5afd715088a3a4;
        }
        return (code *)0x8c5afd715088a3a4;
      }
    }
  }
  return pcVar4;
}

