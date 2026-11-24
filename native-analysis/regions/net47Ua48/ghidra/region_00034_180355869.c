/* Synthetic CFG slice from function at 0x180333830; outgoing edges were patched to RET in a temporary project. */
/* region=34 entry=180355869 blocks=57 maxInstructions=50000 */

/* cut edge: 0x180355869 -> 0x1803351d6 */
/* cut edge: 0x18035588f -> 0x180359cdb */
/* cut edge: 0x1803558c6 -> 0x180359d16 */
/* cut edge: 0x180355928 -> 0x180359d67 */
/* cut edge: 0x180355938 -> 0x180359da0 */
/* cut edge: 0x180355a21 -> 0x1803351d6 */
/* cut edge: 0x180355b40 -> 0x1803351d6 */
/* cut edge: 0x180355b58 -> 0x18035a68f */
/* cut edge: 0x180355b87 -> 0x18035a6c5 */
/* cut edge: 0x180355be9 -> 0x18035a716 */
/* cut edge: 0x180355bf9 -> 0x18035a750 */
/* cut edge: 0x180355ce0 -> 0x1803351d6 */
/* cut edge: 0x180355dda -> 0x1803351d6 */
/* cut edge: 0x180355e00 -> 0x180358e37 */
/* cut edge: 0x180355e27 -> 0x180358e6d */
/* cut edge: 0x180355e89 -> 0x180358ebe */
/* cut edge: 0x180355e99 -> 0x180358ef8 */
/* cut edge: 0x180355f78 -> 0x1803351d6 */
/* cut edge: 0x1803560a0 -> 0x1803351d6 */
/* cut edge: 0x1803560b8 -> 0x180359dd7 */
/* cut edge: 0x1803560e7 -> 0x180359e0d */
/* cut edge: 0x180356149 -> 0x180359e5e */
/* cut edge: 0x180356159 -> 0x180359e98 */
/* cut edge: 0x180356240 -> 0x1803351d6 */
/* cut edge: 0x18035632e -> 0x1803351d6 */
/* cut edge: 0x180356354 -> 0x180359ecf */
/* cut edge: 0x180356383 -> 0x180359f0a */
/* cut edge: 0x1803563e5 -> 0x180359f5b */
/* cut edge: 0x1803563fd -> 0x180359f94 */
/* cut edge: 0x1803564e6 -> 0x1803351d6 */
/* cut edge: 0x1803565d8 -> 0x18035689a */
/* cut edge: 0x1803565f0 -> 0x180356892 */
/* cut edge: 0x180356600 -> 0x1803351d6 */
/* cut edge: 0x180356618 -> 0x18035a787 */
/* cut edge: 0x180356647 -> 0x18035a7bd */
/* cut edge: 0x1803566a9 -> 0x18035a80e */
/* cut edge: 0x1803566b9 -> 0x18035a848 */
/* cut edge: 0x1803567a0 -> 0x1803351d6 */
/* cut edge: 0x1803567a0 -> 0x180356892 */
/* target 0x1803351d6 original byte 0x90 replaced by RET */
/* target 0x180356892 original byte 0x44 replaced by RET */
/* target 0x18035689a original byte 0x48 replaced by RET */
/* target 0x180358e37 original byte 0x48 replaced by RET */
/* target 0x180358e6d original byte 0x48 replaced by RET */
/* target 0x180358ebe original byte 0x48 replaced by RET */
/* target 0x180358ef8 original byte 0x48 replaced by RET */
/* target 0x180359cdb original byte 0x48 replaced by RET */
/* target 0x180359d16 original byte 0x48 replaced by RET */
/* target 0x180359d67 original byte 0x48 replaced by RET */
/* target 0x180359da0 original byte 0x48 replaced by RET */
/* target 0x180359dd7 original byte 0x48 replaced by RET */
/* target 0x180359e0d original byte 0x48 replaced by RET */
/* target 0x180359e5e original byte 0x48 replaced by RET */
/* target 0x180359e98 original byte 0x48 replaced by RET */
/* target 0x180359ecf original byte 0x48 replaced by RET */
/* target 0x180359f0a original byte 0x48 replaced by RET */
/* target 0x180359f5b original byte 0x48 replaced by RET */
/* target 0x180359f94 original byte 0x48 replaced by RET */
/* target 0x18035a68f original byte 0x48 replaced by RET */
/* target 0x18035a6c5 original byte 0x48 replaced by RET */
/* target 0x18035a716 original byte 0x48 replaced by RET */
/* target 0x18035a750 original byte 0x48 replaced by RET */
/* target 0x18035a787 original byte 0x48 replaced by RET */
/* target 0x18035a7bd original byte 0x48 replaced by RET */
/* target 0x18035a80e original byte 0x48 replaced by RET */
/* target 0x18035a848 original byte 0x48 replaced by RET */


code * region_00034_180355869(void)

{
  longlong lVar1;
  longlong lVar2;
  longlong lVar3;
  uint uVar4;
  code *pcVar5;
  undefined8 uVar6;
  code *pcVar7;
  code *pcVar8;
  undefined8 uVar9;
  undefined8 uVar10;
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
  code *in_stack_000000b8;
  longlong in_stack_000000c8;
  undefined8 in_stack_000000d0;
  undefined8 in_stack_00000110;
  
  pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
  lVar3 = DAT_1804b59f8;
  uVar10 = DAT_1804b59f0;
  if ((char)pcVar5 == '\0') {
    if (DAT_1804b2380 == 0) {
      pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b59f8 == 0) {
        return pcVar5;
      }
      uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000070e,1);
      lVar2 = DAT_1804b4d38;
      lVar1 = DAT_1804b4d30;
      pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
      if (DAT_1804b4d30 == 0) {
        return pcVar7;
      }
      if (DAT_1804b4d38 == 0) {
        return pcVar7;
      }
      pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                 (in_stack_00000048,&stack0x00001be8,5);
      if (DAT_1804b4d40 == 0) {
        return pcVar8;
      }
      uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
      (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
      uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar6);
      DAT_1804b2380 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
      (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
    }
    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,DAT_1804b2380,in_stack_00000078);
    uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
    pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
    (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
    pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
    uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                      (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000a8);
    (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
    uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                       (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
    (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
    pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
    if (((char)pcVar5 == '\0') &&
       (pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar3 = DAT_1804b59f8,
       uVar10 = DAT_1804b59f0, (char)pcVar5 == '\0')) {
      if (DAT_1804b2390 == 0) {
        pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b59f8 == 0) {
          return pcVar5;
        }
        uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x0000070a,1);
        lVar2 = DAT_1804b4d38;
        lVar1 = DAT_1804b4d30;
        pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
        if (DAT_1804b4d30 == 0) {
          return pcVar7;
        }
        if (DAT_1804b4d38 == 0) {
          return pcVar7;
        }
        pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                   (in_stack_00000048,&stack0x00001bc8,5);
        if (DAT_1804b4d40 == 0) {
          return pcVar8;
        }
        uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
        (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
        uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar6);
        DAT_1804b2390 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
        (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
      }
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,DAT_1804b2390,in_stack_00000078);
      uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0);
      pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x4c73);
      (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
      pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
      uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                        (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_000000d0);
      (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
      uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                         (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
      uVar4 = (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
      pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
      if ((char)pcVar5 == '\0') {
        if (in_stack_000000c8 == 0) {
          return in_stack_000000b8;
        }
        pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))();
        lVar3 = DAT_1804b59f8;
        uVar10 = DAT_1804b59f0;
        if ((uVar4 & (uint)in_stack_000000b8) == 0) {
          if ((char)pcVar5 == '\0') {
            if (DAT_1804b23a8 == 0) {
              pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59f8 == 0) {
                return pcVar5;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000704,1);
              lVar2 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar7;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar7;
              }
              pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x00001b98,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar8;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058)
              ;
              (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
              uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar6);
              DAT_1804b23a8 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b23a8,in_stack_00000078);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
            (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000000a8);
            (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
            (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
            pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar5 == '\0') {
              return pcVar5;
            }
          }
        }
        else if ((char)pcVar5 == '\0') {
          if (DAT_1804b2398 == 0) {
            pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b59f8 == 0) {
              return pcVar5;
            }
            uVar6 = (**(code **)(*in_stack_00000048 + 0x518))(in_stack_00000048,&stack0x00000708,1);
            lVar2 = DAT_1804b4d38;
            lVar1 = DAT_1804b4d30;
            pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
            if (DAT_1804b4d30 == 0) {
              return pcVar7;
            }
            if (DAT_1804b4d38 == 0) {
              return pcVar7;
            }
            pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                       (in_stack_00000048,&stack0x00001bb8,5);
            if (DAT_1804b4d40 == 0) {
              return pcVar8;
            }
            uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058);
            (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
            uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar6);
            DAT_1804b2398 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
            (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
          }
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,DAT_1804b2398,in_stack_00000078);
          uVar6 = (**(code **)(*in_stack_00000048 + 0x560))(in_stack_00000048,2,in_stack_00000088,0)
          ;
          pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x604f);
          (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
          pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
          uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                            (in_stack_00000048,in_stack_00000098,in_stack_00000090,in_stack_00000110
                            );
          (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
          uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                             (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
          (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
          pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
          if (((char)pcVar5 == '\0') &&
             (pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(), lVar3 = DAT_1804b59f8,
             uVar10 = DAT_1804b59f0, (char)pcVar5 == '\0')) {
            if (DAT_1804b23a0 == 0) {
              pcVar5 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b59f8 == 0) {
                return (code *)0x0;
              }
              uVar6 = (**(code **)(*in_stack_00000048 + 0x518))
                                (in_stack_00000048,&stack0x00000706,1);
              lVar2 = DAT_1804b4d38;
              lVar1 = DAT_1804b4d30;
              pcVar7 = *(code **)(*in_stack_00000048 + 0x390);
              if (DAT_1804b4d30 == 0) {
                return pcVar7;
              }
              if (DAT_1804b4d38 == 0) {
                return pcVar7;
              }
              pcVar8 = (code *)(**(code **)(*in_stack_00000048 + 0x518))
                                         (in_stack_00000048,&stack0x00001ba8,5);
              if (DAT_1804b4d40 == 0) {
                return pcVar8;
              }
              uVar9 = (**(code **)(*in_stack_00000048 + 0x110))(in_stack_00000048,in_stack_00000058)
              ;
              (*pcVar7)(in_stack_00000048,lVar1,lVar2,pcVar8,uVar9);
              uVar10 = (*pcVar5)(in_stack_00000048,in_stack_00000058,lVar3,uVar10,uVar6);
              DAT_1804b23a0 = (**(code **)(*in_stack_00000048 + 0xa8))(in_stack_00000048,uVar10);
              (**(code **)(*in_stack_00000048 + 0xb8))(in_stack_00000048,uVar10);
            }
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,DAT_1804b23a0,in_stack_00000078);
            uVar6 = (**(code **)(*in_stack_00000048 + 0x560))
                              (in_stack_00000048,2,in_stack_00000088,0);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000068,in_stack_00000060,0x5a30);
            (*pcVar5)(in_stack_00000048,uVar6,0,uVar9);
            pcVar5 = *(code **)(*in_stack_00000048 + 0x570);
            uVar9 = (**(code **)(*in_stack_00000048 + 0x390))
                              (in_stack_00000048,in_stack_00000098,in_stack_00000090,
                               in_stack_000000a8);
            (*pcVar5)(in_stack_00000048,uVar6,1,uVar9);
            uVar10 = (**(code **)(*in_stack_00000048 + 0x110))
                               (in_stack_00000048,uVar10,in_stack_00000080,uVar6);
            (**(code **)(*in_stack_00000048 + 0x188))(in_stack_00000048,uVar10,in_stack_00000070);
            pcVar5 = (code *)(**(code **)(*in_stack_00000048 + 0x720))(in_stack_00000048);
            if ((char)pcVar5 == '\0') {
              return pcVar5;
            }
          }
        }
      }
    }
  }
  return pcVar5;
}

