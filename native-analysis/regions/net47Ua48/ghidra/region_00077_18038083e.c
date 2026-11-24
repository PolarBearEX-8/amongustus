/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=77 entry=18038083e blocks=48 maxInstructions=50000 */

/* cut edge: 0x18038083e -> 0x1803351d6 */
/* cut edge: 0x180380885 -> 0x1803351d6 */
/* cut edge: 0x1803808c9 -> 0x1803351d6 */
/* cut edge: 0x180380917 -> 0x1803351d6 */
/* cut edge: 0x180380a7f -> 0x1803351d6 */
/* cut edge: 0x180380b6c -> 0x1803351d6 */
/* cut edge: 0x180380cf7 -> 0x1803351d6 */
/* cut edge: 0x180380df0 -> 0x1803351d6 */
/* cut edge: 0x180380e39 -> 0x1803351d6 */
/* cut edge: 0x180380e86 -> 0x1803351d6 */
/* cut edge: 0x180380ee6 -> 0x1803351d6 */
/* cut edge: 0x180380eeb -> 0x1803351d6 */
/* cut edge: 0x180381053 -> 0x1803351d6 */
/* cut edge: 0x180381140 -> 0x1803351d6 */
/* cut edge: 0x18038116f -> 0x1803351d6 */
/* cut edge: 0x1803811d0 -> 0x1803351d6 */
/* cut edge: 0x180381223 -> 0x1803351d6 */
/* cut edge: 0x180381267 -> 0x1803351d6 */
/* cut edge: 0x1803812bb -> 0x1803351d6 */
/* cut edge: 0x1803812f2 -> 0x1803351d6 */
/* cut edge: 0x18038133e -> 0x1803351d6 */
/* cut edge: 0x18038139c -> 0x1803747e5 */
/* cut edge: 0x1803813d4 -> 0x1803351d6 */
/* cut edge: 0x1803813d9 -> 0x1803351d6 */
/* cut edge: 0x180381421 -> 0x1803351d6 */
/* cut edge: 0x18038145e -> 0x1803351d6 */
/* cut edge: 0x1803815e6 -> 0x1803351d6 */
/* cut edge: 0x1803816ce -> 0x1803351d6 */
/* cut edge: 0x1803816fd -> 0x1803351d6 */
/* cut edge: 0x1803817ca -> 0x18038191c */
/* cut edge: 0x1803817da -> 0x18038191c */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x1803747e5 original byte 0x48 replaced by RET */
/* target 0x18038191c original byte 0x48 replaced by RET */


void region_00077_18038083e(void)

{
  ulonglong uVar1;
  longlong lVar2;
  code *pcVar3;
  code *pcVar4;
  byte bVar5;
  uint uVar6;
  char cVar7;
  int iVar8;
  undefined4 uVar9;
  uint uVar10;
  undefined8 *puVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  undefined8 uVar17;
  undefined4 unaff_ESI;
  int iVar18;
  ulonglong uVar19;
  undefined4 uVar20;
  longlong *in_stack_00000048;
  byte bStack0000000000000050;
  undefined7 uStack0000000000000051;
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
  undefined4 uStack00000000000000b0;
  code *in_stack_000000b8;
  undefined8 in_stack_000000c0;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e0;
  undefined8 in_stack_00000100;
  undefined8 in_stack_00000120;
  undefined8 in_stack_00000130;
  int iStack00000000000001b0;
  int in_stack_00000200;
  undefined8 in_stack_00000230;
  undefined8 in_stack_00000238;
  
  (**(code **)(*in_stack_00000048 + 0x658))
            (in_stack_00000048,in_stack_000000a0,3,1,&stack0x00000050);
  uVar20 = _bStack0000000000000050;
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 != '\0') {
    return;
  }
  _bStack0000000000000050 = uVar20;
  (**(code **)(*in_stack_00000048 + 0x698))
            (in_stack_00000048,in_stack_000000a0,unaff_ESI,1,&stack0x00000050);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 != '\0') {
    return;
  }
  _bStack0000000000000050 = CONCAT44(uStack0000000000000051._3_4_,uStack00000000000000b0);
  puVar11 = (undefined8 *)&stack0x00000050;
  (**(code **)(*in_stack_00000048 + 0x698))(in_stack_00000048,in_stack_000000a0,3,1,puVar11);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 != '\0') {
    return;
  }
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar7 != '\0') {
    return;
  }
  if (DAT_1804b2aa8 == 0) {
    lVar2 = *in_stack_00000048;
    _uStack00000000000000b0 = *(code **)(lVar2 + 0x390);
    in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
    in_stack_000000c0 = DAT_1804b59f0;
    puVar11 = (undefined8 *)(**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000544,1);
    lVar2 = *in_stack_00000048;
    pcVar3 = *(code **)(lVar2 + 0x390);
    uVar12 = FUN_1800024f0(in_stack_00000048);
    uVar13 = FUN_180002550(in_stack_00000048);
    uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000d98,5);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
    uVar15 = FUN_1800025a0(in_stack_00000048);
    uVar16 = in_stack_00000058;
    uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
    (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
    uVar16 = (*_uStack00000000000000b0)
                       (in_stack_00000048,uVar16,in_stack_000000b8,in_stack_000000c0,puVar11);
    DAT_1804b2aa8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar16);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar16);
  }
  _bStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2aa8,in_stack_00000078);
  uVar16 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
  (*pcVar3)(in_stack_00000048,uVar16,0,uVar12);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
  (*pcVar3)(in_stack_00000048,uVar16,1,uVar12);
  uVar16 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar16);
  iVar8 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar16,in_stack_00000070);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 != '\0') {
    return;
  }
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar7 != '\0') {
    return;
  }
  _uStack00000000000000b0 = (code *)(ulonglong)(uint)(iVar8 * in_stack_00000200);
  uVar19 = 0;
  while( true ) {
    uVar20 = (undefined4)((ulonglong)puVar11 >> 0x20);
    if (DAT_1804b2ab0 == 0) {
      lVar2 = *in_stack_00000048;
      in_stack_000000b8 = *(code **)(lVar2 + 0x390);
      in_stack_000000c0 = FUN_180333550(in_stack_00000048);
      in_stack_000000d8 = DAT_1804b59f0;
      in_stack_00000100 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000542,1);
      lVar2 = *in_stack_00000048;
      pcVar3 = *(code **)(lVar2 + 0x390);
      uVar12 = FUN_1800024f0(in_stack_00000048);
      uVar13 = FUN_180002550(in_stack_00000048);
      uVar14 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000d88,5);
      pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
      uVar15 = FUN_1800025a0(in_stack_00000048);
      uVar16 = in_stack_00000058;
      uVar15 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar15);
      (*pcVar3)(in_stack_00000048,uVar12,uVar13,uVar14,uVar15);
      uVar12 = in_stack_00000100;
      uVar16 = (*in_stack_000000b8)
                         (in_stack_00000048,uVar16,in_stack_000000c0,in_stack_000000d8,
                          in_stack_00000100);
      uVar20 = (undefined4)((ulonglong)uVar12 >> 0x20);
      DAT_1804b2ab0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar16);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar16);
    }
    _bStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b2ab0,in_stack_00000078);
    uVar16 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
    (*pcVar3)(in_stack_00000048,uVar16,0,uVar12);
    pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
    uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
    (*pcVar3)(in_stack_00000048,uVar16,1,uVar12);
    uVar16 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar16);
    iVar8 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar16,in_stack_00000070);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 != '\0') {
      return;
    }
    if ((longlong)iVar8 <= (longlong)uVar19) break;
    _bStack0000000000000050 = (uint)uVar19;
    (**(code **)(*in_stack_00000048 + 0x658))
              (in_stack_00000048,in_stack_000000a0,uVar19 & 0xffffffff,1,&stack0x00000050);
    uVar10 = _bStack0000000000000050;
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 != '\0') {
      return;
    }
    (**(code **)(*in_stack_00000048 + 0x658))
              (in_stack_00000048,in_stack_00000130,(int)_uStack00000000000000b0 + (uint)uVar19,1,
               &stack0x00000050);
    uVar6 = _bStack0000000000000050;
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 != '\0') {
      return;
    }
    _bStack0000000000000050 = CONCAT44(uStack0000000000000051._3_4_,uVar6 ^ uVar10);
    puVar11 = (undefined8 *)&stack0x00000050;
    (**(code **)(*in_stack_00000048 + 0x698))
              (in_stack_00000048,in_stack_000000a0,uVar19 & 0xffffffff,1,puVar11);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 != '\0') {
      return;
    }
    uVar19 = uVar19 + 1;
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
    if (cVar7 != '\0') {
      return;
    }
  }
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))();
  if (cVar7 != '\0') {
    return;
  }
  if (DAT_1804b2ab8 == 0) {
    lVar2 = *in_stack_00000048;
    _uStack00000000000000b0 = *(code **)(lVar2 + 0x390);
    in_stack_000000b8 = (code *)FUN_180333550(in_stack_00000048);
    in_stack_000000c0 = DAT_1804b59f0;
    uVar12 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000540,1);
    lVar2 = *in_stack_00000048;
    pcVar3 = *(code **)(lVar2 + 0x390);
    uVar13 = FUN_1800024f0(in_stack_00000048);
    uVar14 = FUN_180002550(in_stack_00000048);
    uVar15 = (**(code **)(lVar2 + 0x518))(in_stack_00000048,&stack0x00000d78,5);
    pcVar4 = *(code **)(*in_stack_00000048 + 0x110);
    uVar17 = FUN_1800025a0(in_stack_00000048);
    uVar16 = in_stack_00000058;
    uVar17 = (*pcVar4)(in_stack_00000048,in_stack_00000058,uVar17);
    (*pcVar3)(in_stack_00000048,uVar13,uVar14,uVar15,uVar17);
    uVar16 = (*_uStack00000000000000b0)
                       (in_stack_00000048,uVar16,in_stack_000000b8,in_stack_000000c0,uVar12);
    uVar20 = (undefined4)((ulonglong)uVar12 >> 0x20);
    DAT_1804b2ab8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar16);
    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar16);
  }
  _bStack0000000000000050 =
       (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,DAT_1804b2ab8,in_stack_00000078);
  uVar16 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x583d);
  (*pcVar3)(in_stack_00000048,uVar16,0,uVar12);
  pcVar3 = *(code **)(*in_stack_00000048 + 0x570);
  uVar12 = (**(code **)(*in_stack_00000048 + 0x390))
                     (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000e0);
  (*pcVar3)(in_stack_00000048,uVar16,1,uVar12);
  uVar16 = (**(code **)(*in_stack_00000048 + 0x110))
                     (in_stack_00000048,_bStack0000000000000050,in_stack_00000080,uVar16);
  uVar9 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar16,in_stack_00000070);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 != '\0') {
    return;
  }
  iVar8 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000238);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 != '\0') {
    return;
  }
  iVar8 = iVar8 - iStack00000000000001b0;
  pcVar3 = *(code **)(*in_stack_00000048 + 0x408);
  uVar16 = FUN_18001eb70(in_stack_00000048);
  uVar12 = FUN_18001ebd0(in_stack_00000048);
  uVar10 = (*pcVar3)(in_stack_00000048,uVar16,uVar12,uVar9,CONCAT44(uVar20,iVar8));
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 != '\0') {
    return;
  }
  if (0 < (int)uVar10) {
    uVar19 = 0;
    do {
      uVar1 = _iStack00000000000001b0 + uVar19;
      (**(code **)(*in_stack_00000048 + 0x640))
                (in_stack_00000048,in_stack_00000238,uVar1 & 0xffffffff,1,&stack0x00000050);
      bVar5 = bStack0000000000000050;
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 != '\0') {
        return;
      }
      (**(code **)(*in_stack_00000048 + 0x658))
                (in_stack_00000048,in_stack_000000a0,uVar19 & 0xffffffff,1,&stack0x00000050);
      uVar16 = _bStack0000000000000050;
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 != '\0') {
        return;
      }
      _bStack0000000000000050 = CONCAT71(uStack0000000000000051,bVar5 ^ (byte)uVar16);
      (**(code **)(*in_stack_00000048 + 0x680))
                (in_stack_00000048,in_stack_00000230,uVar1 & 0xffffffff,1,&stack0x00000050);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 != '\0') {
        return;
      }
      uVar19 = uVar19 + 1;
    } while (uVar10 != uVar19);
  }
  iVar8 = (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000120);
  cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
  if (cVar7 == '\0') {
    do {
      if (iVar8 < 1) break;
      iVar8 = iVar8 + -1;
      _bStack0000000000000050 = CONCAT44(uStack0000000000000051._3_4_,iVar8);
      (**(code **)(*in_stack_00000048 + 0x640))
                (in_stack_00000048,in_stack_00000120,iVar8,1,&stack0x00000050);
      bVar5 = bStack0000000000000050;
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 != '\0') {
        return;
      }
      iVar18 = (uint)bVar5 * 0x1000000 + 0x1000000;
      _bStack0000000000000050 = CONCAT44(uStack0000000000000051._3_4_,iVar18 >> 0x18);
      _bStack0000000000000050 = CONCAT71(uStack0000000000000051,(char)((uint)iVar18 >> 0x18));
      (**(code **)(*in_stack_00000048 + 0x680))
                (in_stack_00000048,in_stack_00000120,iVar8,1,&stack0x00000050);
      cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar7 != '\0') {
        return;
      }
    } while (iVar18 == 0);
    _iStack00000000000001b0 = _iStack00000000000001b0 + 0x10;
    (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,in_stack_00000238);
    cVar7 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar7 == '\0') {
      return;
    }
  }
  return;
}

