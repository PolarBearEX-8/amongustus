/* Synthetic CFG slice from function at 0x1803db540; outgoing edges were patched to RET in a temporary project. */
/* region=133 entry=180460655 blocks=34 maxInstructions=50000 */

/* cut edge: 0x180460655 -> 0x180473ae8 */
/* cut edge: 0x18046066b -> 0x180473ae8 */
/* cut edge: 0x180460840 -> 0x180473ae8 */
/* cut edge: 0x18046096b -> 0x180473ae8 */
/* cut edge: 0x1804609b9 -> 0x1804620a2 */
/* cut edge: 0x1804609cf -> 0x180473ae8 */
/* cut edge: 0x1804609e2 -> 0x180473ae8 */
/* cut edge: 0x180460baa -> 0x180473ae8 */
/* cut edge: 0x180460c9c -> 0x180473ae8 */
/* cut edge: 0x180460cb2 -> 0x180473ae8 */
/* cut edge: 0x180460e77 -> 0x180473ae8 */
/* cut edge: 0x180460f69 -> 0x180473ae8 */
/* cut edge: 0x180460f7f -> 0x180473ae8 */
/* cut edge: 0x180461144 -> 0x180473ae8 */
/* cut edge: 0x180461239 -> 0x180473ae8 */
/* cut edge: 0x18046124f -> 0x180473ae8 */
/* cut edge: 0x180461419 -> 0x180462bb3 */
/* cut edge: 0x180461485 -> 0x180473ae8 */
/* cut edge: 0x1804614b1 -> 0x180473ae8 */
/* cut edge: 0x1804614db -> 0x180462c58 */
/* cut edge: 0x180461532 -> 0x180473ae8 */
/* cut edge: 0x180461537 -> 0x180473ae8 */
/* cut edge: 0x180461537 -> 0x180461566 */
/* target 0x180461566 original byte 0xb0 replaced by RET */
/* target 0x1804620a2 original byte 0x48 replaced by RET */
/* target 0x180462bb3 original byte 0xff replaced by RET */
/* target 0x180462c58 original byte 0x48 replaced by RET */
/* target 0x180473ae8 original byte 0x90 replaced by RET */


void region_00133_180460655(void)

{
  longlong lVar1;
  code *pcVar2;
  code *pcVar3;
  char cVar4;
  char cVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  ulonglong uVar11;
  int iVar12;
  undefined8 *puVar13;
  undefined4 uVar14;
  longlong *in_stack_00000048;
  char cStack0000000000000050;
  undefined8 in_stack_00000058;
  code *in_stack_00000060;
  longlong in_stack_00000068;
  undefined8 in_stack_00000080;
  undefined8 in_stack_00000088;
  undefined8 in_stack_00000090;
  undefined8 in_stack_00000098;
  undefined8 in_stack_000000a0;
  undefined8 in_stack_000000a8;
  code *in_stack_000000b0;
  undefined8 in_stack_000000b8;
  undefined8 in_stack_000000c0;
  ulonglong in_stack_000000c8;
  undefined8 in_stack_000000d8;
  undefined8 in_stack_000000e8;
  undefined8 in_stack_000000f0;
  undefined8 in_stack_000000f8;
  int in_stack_00000108;
  char in_stack_00000118;
  undefined8 in_stack_00000148;
  undefined8 in_stack_00002f78;
  
  cVar4 = (**(code **)(*in_stack_00000048 + 0x720))();
  if ((cVar4 == '\0') && (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
    uVar11 = in_stack_00000068 % 0x3e407;
    if (DAT_1804b4628 == 0) {
      lVar1 = *in_stack_00000048;
      in_stack_000000b0 = *(code **)(lVar1 + 0x390);
      in_stack_000000e8 = FUN_180333500(in_stack_00000048);
      in_stack_000000f8 = DAT_1804b59f0;
      in_stack_000000f0 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000344,1);
      lVar1 = *in_stack_00000048;
      pcVar2 = *(code **)(lVar1 + 0x390);
      uVar6 = FUN_1800024f0(in_stack_00000048);
      uVar7 = FUN_180002550(in_stack_00000048);
      uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00003200,0x19);
      pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
      uVar9 = FUN_1800025a0(in_stack_00000048);
      uVar10 = in_stack_00000088;
      uVar9 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar9);
      (*pcVar2)(in_stack_00000048,uVar6,uVar7,uVar8,uVar9);
      uVar10 = (*in_stack_000000b0)
                         (in_stack_00000048,uVar10,in_stack_000000e8,in_stack_000000f8,
                          in_stack_000000f0);
      DAT_1804b4628 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
    }
    _cStack0000000000000050 =
         (**(code **)(*in_stack_00000048 + 0x110))
                   (in_stack_00000048,DAT_1804b4628,in_stack_000000a0);
    uVar7 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,4,in_stack_00000090,0);
    (**(code **)(*in_stack_00000048 + 0x570))(in_stack_00000048,uVar7,0,in_stack_00000058);
    uVar6 = in_stack_00000098;
    uVar10 = in_stack_00000080;
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000080,in_stack_00000098,in_stack_00002f78);
    (*pcVar2)(in_stack_00000048,uVar7,1,uVar8);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar8 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,uVar10,uVar6,uVar11 ^ 0x5055e0919dcbd591);
    (*pcVar2)(in_stack_00000048,uVar7,2,uVar8);
    pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x390))
                       (in_stack_00000048,uVar10,uVar6,in_stack_000000c8);
    (*pcVar2)(in_stack_00000048,uVar7,3,uVar10);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,_cStack0000000000000050,in_stack_000000a8,uVar7);
    cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (cVar4 == '\0') {
      puVar13 = (undefined8 *)&stack0x00000050;
      (**(code **)(*in_stack_00000048 + 0x640))(in_stack_00000048,uVar10,0,1,puVar13);
      uVar14 = (undefined4)((ulonglong)puVar13 >> 0x20);
      cVar4 = cStack0000000000000050;
      iVar12 = (int)cStack0000000000000050;
      cVar5 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if (cVar5 == '\0') {
        if (in_stack_00000118 == '\0') {
          uVar6 = (**(code **)(*in_stack_00000048 + 0x580))
                            (in_stack_00000048,iVar12 - in_stack_00000108);
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (cVar4 == '\0') {
            (**(code **)(*in_stack_00000048 + 0x558))(in_stack_00000048,uVar6);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (cVar4 == '\0') {
              pcVar2 = *(code **)(*in_stack_00000048 + 0x468);
              uVar6 = FUN_1803dab10(in_stack_00000048);
              (*pcVar2)(in_stack_00000048,in_stack_00000148,uVar6,uVar10,CONCAT44(uVar14,1));
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (cVar4 == '\0') {
                return;
              }
            }
          }
        }
        else {
          if (cVar4 == '\0') {
            return;
          }
          in_stack_00000058 = uVar10;
          cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if ((cVar4 == '\0') &&
             (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
            uVar11 = in_stack_00000068 % 0x1b1f5 ^ in_stack_000000c8;
            if (DAT_1804b4630 == 0) {
              lVar1 = *in_stack_00000048;
              in_stack_000000b0 = *(code **)(lVar1 + 0x390);
              in_stack_000000e8 = FUN_1803335f0(in_stack_00000048);
              in_stack_000000f8 = DAT_1804b59f0;
              in_stack_000000f0 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000342,1)
              ;
              lVar1 = *in_stack_00000048;
              pcVar2 = *(code **)(lVar1 + 0x390);
              uVar6 = FUN_1800024f0(in_stack_00000048);
              uVar7 = FUN_180002550(in_stack_00000048);
              uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000f90,5);
              pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
              uVar9 = FUN_1800025a0(in_stack_00000048);
              uVar10 = in_stack_00000088;
              uVar9 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar9);
              (*pcVar2)(in_stack_00000048,uVar6,uVar7,uVar8,uVar9);
              uVar10 = (*in_stack_000000b0)
                                 (in_stack_00000048,uVar10,in_stack_000000e8,in_stack_000000f8,
                                  in_stack_000000f0);
              DAT_1804b4630 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            _cStack0000000000000050 =
                 (**(code **)(*in_stack_00000048 + 0x110))
                           (in_stack_00000048,DAT_1804b4630,in_stack_000000a0);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                               (in_stack_00000048,2,in_stack_00000090,0);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x306f);
            (*pcVar2)(in_stack_00000048,uVar10,0,uVar6);
            pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                               uVar11 ^ 0xfcd0ba183299e07c);
            (*pcVar2)(in_stack_00000048,uVar10,1,uVar6);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,_cStack0000000000000050,in_stack_000000a8,uVar10);
            (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
            cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if (((cVar4 == '\0') &&
                (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) &&
               (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
              uVar11 = in_stack_00000068 % 0xd8401 ^ in_stack_000000c8;
              if (DAT_1804b4638 == 0) {
                lVar1 = *in_stack_00000048;
                in_stack_000000b0 = *(code **)(lVar1 + 0x390);
                in_stack_000000e8 = FUN_1803335f0(in_stack_00000048);
                in_stack_000000f8 = DAT_1804b59f0;
                in_stack_000000f0 =
                     (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000340,1);
                lVar1 = *in_stack_00000048;
                pcVar2 = *(code **)(lVar1 + 0x390);
                uVar6 = FUN_1800024f0(in_stack_00000048);
                uVar7 = FUN_180002550(in_stack_00000048);
                uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000f80,5);
                pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                uVar9 = FUN_1800025a0(in_stack_00000048);
                uVar10 = in_stack_00000088;
                uVar9 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar9);
                (*pcVar2)(in_stack_00000048,uVar6,uVar7,uVar8,uVar9);
                uVar10 = (*in_stack_000000b0)
                                   (in_stack_00000048,uVar10,in_stack_000000e8,in_stack_000000f8,
                                    in_stack_000000f0);
                DAT_1804b4638 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
              }
              _cStack0000000000000050 =
                   (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b4638,in_stack_000000a0);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                 (in_stack_00000048,2,in_stack_00000090,0);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x2fd3);
              (*pcVar2)(in_stack_00000048,uVar10,0,uVar6);
              pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
              uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                 uVar11 ^ 0xc09bba240b794b16);
              (*pcVar2)(in_stack_00000048,uVar10,1,uVar6);
              uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,_cStack0000000000000050,in_stack_000000a8,uVar10
                                 );
              (**(code **)(*in_stack_00000048 + 0x1a0))(in_stack_00000048,uVar10,in_stack_000000d8);
              cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
              if (((cVar4 == '\0') &&
                  (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) &&
                 (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
                uVar11 = in_stack_00000068 % 0x66e68 ^ in_stack_000000c8;
                if (DAT_1804b4640 == 0) {
                  lVar1 = *in_stack_00000048;
                  in_stack_000000b0 = *(code **)(lVar1 + 0x390);
                  in_stack_000000e8 = FUN_1803335f0(in_stack_00000048);
                  in_stack_000000f8 = DAT_1804b59f0;
                  in_stack_000000f0 =
                       (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000033e,1);
                  lVar1 = *in_stack_00000048;
                  pcVar2 = *(code **)(lVar1 + 0x390);
                  uVar6 = FUN_1800024f0(in_stack_00000048);
                  uVar7 = FUN_180002550(in_stack_00000048);
                  uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000f70,5);
                  pcVar3 = *(code **)(*in_stack_00000048 + 0x110);
                  uVar9 = FUN_1800025a0(in_stack_00000048);
                  uVar10 = in_stack_00000088;
                  uVar9 = (*pcVar3)(in_stack_00000048,in_stack_00000088,uVar9);
                  (*pcVar2)(in_stack_00000048,uVar6,uVar7,uVar8,uVar9);
                  uVar10 = (*in_stack_000000b0)
                                     (in_stack_00000048,uVar10,in_stack_000000e8,in_stack_000000f8,
                                      in_stack_000000f0);
                  DAT_1804b4640 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10)
                  ;
                  (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                }
                _cStack0000000000000050 =
                     (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b4640,in_stack_000000a0);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x560))
                                   (in_stack_00000048,2,in_stack_00000090,0);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_000000b8,in_stack_000000c0,0x23b2);
                (*pcVar2)(in_stack_00000048,uVar10,0,uVar6);
                pcVar2 = *(code **)(*in_stack_00000048 + 0x570);
                uVar6 = (**(code **)(*in_stack_00000048 + 0x390))
                                  (in_stack_00000048,in_stack_00000080,in_stack_00000098,
                                   uVar11 ^ 0x909aa27c131eb2ba);
                (*pcVar2)(in_stack_00000048,uVar10,1,uVar6);
                uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                                   (in_stack_00000048,_cStack0000000000000050,in_stack_000000a8,
                                    uVar10);
                (**(code **)(*in_stack_00000048 + 0x1a0))
                          (in_stack_00000048,uVar10,in_stack_000000d8);
                cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
                if (((cVar4 == '\0') &&
                    (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) &&
                   (cVar4 = (**(code **)(*in_stack_00000048 + 0x720))(), cVar4 == '\0')) {
                  if (DAT_1804b4648 == 0) {
                    lVar1 = *in_stack_00000048;
                    in_stack_000000b0 = *(code **)(lVar1 + 0x390);
                    in_stack_000000e8 = FUN_1803335f0(in_stack_00000048);
                    in_stack_000000f8 = DAT_1804b59f0;
                    in_stack_000000f0 =
                         (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x0000033c,1);
                    lVar1 = *in_stack_00000048;
                    in_stack_00000060 = *(code **)(lVar1 + 0x390);
                    uVar6 = FUN_1800024f0(in_stack_00000048);
                    uVar7 = FUN_180002550(in_stack_00000048);
                    uVar8 = (**(code **)(lVar1 + 0x518))(in_stack_00000048,&stack0x00000f60,5);
                    pcVar2 = *(code **)(*in_stack_00000048 + 0x110);
                    uVar9 = FUN_1800025a0(in_stack_00000048);
                    uVar10 = in_stack_00000088;
                    uVar9 = (*pcVar2)(in_stack_00000048,in_stack_00000088,uVar9);
                    (*in_stack_00000060)(in_stack_00000048,uVar6,uVar7,uVar8,uVar9);
                    uVar10 = (*in_stack_000000b0)
                                       (in_stack_00000048,uVar10,in_stack_000000e8,in_stack_000000f8
                                        ,in_stack_000000f0);
                    DAT_1804b4648 =
                         (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
                    (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
                  }
                  _cStack0000000000000050 =
                       (**(code **)(*in_stack_00000048 + 0x110))
                                 (in_stack_00000048,DAT_1804b4648,in_stack_000000a0);
                  (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000090,0)
                  ;
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

