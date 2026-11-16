package client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class FileDirectoryHelper {
   public static ArrayList resolveArrayListByText(String text) {
      try {
         return resolveArrayList(new File(text));
      } catch (Throwable throwable) {
         return new ArrayList();
      }
   }

   public static ArrayList resolveArrayList(String text) {
      if (text.isEmpty()) {
         ArrayList list = new ArrayList();

         for (File file : File.listRoots()) {
            list.add(new SplineEvaluator(file.getAbsolutePath(), "", 0L, true));
         }

         return list;
      } else {
         try {
            ArrayList list2 = new ArrayList();
            File file2 = new File(text);
            if (!file2.isDirectory()) {
               ArrayList list3 = new ArrayList();
               list3.add(new SplineEvaluator("File is not a directory", "", 0L, false));
               return list3;
            } else {
               for (File file3 : file2.listFiles()) {
                  list2.add(new SplineEvaluator(file3.getAbsolutePath(), file3.getName(), file3.length(), file3.isDirectory()));
               }

               return list2;
            }
         } catch (Throwable throwable) {
            ArrayList list4 = new ArrayList();
            list4.add(new SplineEvaluator(throwable.getMessage(), throwable.getLocalizedMessage(), 0L, false));
            return list4;
         }
      }
   }

   public static ArrayList resolveArrayList2(String text) {
      try {
         ArrayList list = new ArrayList();
         File file = new File(text);
         if (!file.isDirectory()) {
            ArrayList list2 = new ArrayList();
            list2.add(new SplineEvaluator("File is not a directory", "", 0L, false));
            return list2;
         } else {
            for (File file2 : (Iterable<File>)(Iterable<?>)resolveArrayList(file)) {
               list.add(new SplineEvaluator(file2.getAbsolutePath(), file2.getName(), file2.length(), file2.isDirectory()));
            }

            return list;
         }
      } catch (Throwable throwable) {
         ArrayList list3 = new ArrayList();
         list3.add(new SplineEvaluator(throwable.getMessage(), throwable.getLocalizedMessage(), 0L, false));
         return list3;
      }
   }

   public static ArrayList resolveArrayList(File file) {
      ArrayList list = null;
      Stack stack = null;
      File file2 = null;
      File[] fileArray = null;
      File[] fileArray2 = null;
      int count = 0;
      int count2 = 0;
      Object entry = null;
      long value = 0x77091268f8a81ef1L;
      long value2 = 0x21e55426fe276155L;

      while (true) {
         while (true) {
            int count3 = (int)(value % 16L);
            if (count3 != 11) {
               if (count3 == 14) {
                  if (!file2.isDirectory()) {
                     long value3 = (value ^ -0x304586184425e991L) - 0x298c7dbc7508241dL;
                     long value4 = (value2 ^ -0x1bfa048361f7ee52L) - 0x79d9aa2d7a0ade23L;
                     list.add(file2);
                     value = (value3 ^ -0x32707f93e22891faL) - 0x77524f67733eac37L;
                     value2 = (value4 ^ 0x40fcb727ccad5f6fL) + 0x426db414ba9cf658L;
                  } else {
                     value = (value ^ 0x2d9a987d39701900L) - 0xb1349cd79e08c79L;
                     value2 = (value2 ^ 0x1fc90d119e23857eL) + 0x45f42f7a68a2e25L;
                  }
                  continue;
               }

               if (count3 == 9) {
                  file2 = (File)stack.pop();
                  value = (value ^ 0x2d013c0fa4cce72bL) - 0x1da8d3e119bdcb34L;
                  value2 = (value2 ^ -0x490f5969a5c10348L) + 0x5ee949db4ce6b4b3L;
                  continue;
               }

               if (count3 == 10) {
                  stack.push(entry);
                  value = (value ^ -0x16e5efd45e65a859L) - 0x68b42a429814ed59L;
                  value2 = (value2 ^ -0xf007edb6c252ee5L) + 0x7785ae5ac616c4a2L;
                  continue;
               }

               if (count3 == 15) {
                  break;
               }

               long value5;
               long value6;
               searchBlock: {
                  if (count3 != 4) {
                     if (count3 == 13) {
                        if (fileArray != null) {
                           value = (value ^ -0x4f30ba0bd4a91d0fL) + 0x52ce340021a22230L;
                           value2 = (value2 ^ 0x75b139a762055d01L) - 0x57aa5d8c28cfb996L;
                           continue;
                        }

                        value5 = (value ^ -0x189137ed03c0f89bL) - 0x28e1d7a3f44f89f4L;
                        value6 = (value2 ^ -0x7acc5198dda41e2cL) - 0x3248e808890db14aL;
                        break searchBlock;
                     }

                     if (count3 == 3) {
                        count2 = 0;
                        value = (value ^ -0x7a4ec488b517ad7fL) - 0x6357d429100d2efaL;
                        value2 = (value2 ^ 0x39db36cb8ec5da9fL) - 0x9bebc0d6082d896L;
                        continue;
                     }

                     if (count3 == 1) {
                        list = new ArrayList();
                        value = (value ^ 0x1ad2c47ab62cf01cL) - 0x5ff57f903d1e5606L;
                        value2 = (value2 ^ -0x1f7e3627a4b8131aL) - 0x11823a3edf6d6b9eL;
                        continue;
                     }

                     if (count3 == 0) {
                        count = fileArray2.length;
                        value = (value ^ 0x4abfb22237f8f4a2L) - 0x55eec686636287fL;
                        value2 = (value2 ^ -0x1c3d42a5c6f983adL) - 0x3d1edfa1ecae9b64L;
                        continue;
                     }

                     if (count3 == 6) {
                        stack.push(file);
                        value = (value ^ 0x471f36b40d790590L) - 0x5d2331996a23da97L;
                        value2 = (value2 ^ -0x617860f405cb09e2L) - 0x43eab79c1593393dL;
                        continue;
                     }

                     if (count3 == 2) {
                        entry = fileArray2[count2];
                        value = (value ^ -0x19297100b12cc482L) - 0x445f5d2cb160c2a2L;
                        value2 = (value2 ^ -0x22868dd30a3a005L) + 0xf669bf51d64e33aL;
                        continue;
                     }

                     if (count3 == 12) {
                        fileArray2 = fileArray;
                        value = (value ^ -0x3b83ccd8d55f9f9eL) - 0x7417b8a608451c1eL;
                        value2 = (value2 ^ -0x305c1083f9aa5d47L) - 0x576ebdf433d601e7L;
                        continue;
                     }

                     if (count3 == 7) {
                        stack = new Stack();
                        value = (value ^ 0x257e2e2e70dbed27L) + 0x7c7bf5bdc903c86L;
                        value2 = (value2 ^ 0x13ad22120f160389L) - 0x28536f967cd385cL;
                        continue;
                     }

                     if (count3 == 5) {
                        fileArray = file2.listFiles();
                        value = (value ^ 0x18085ca0b83d7d8aL) - 0x807874398e7d02L;
                        value2 = (value2 ^ -0x179576fbdd3fba76L) + 0x2501ad2d67eb540cL;
                        continue;
                     }
                  } else {
                     count2++;
                     value = (value ^ -0x68b9674f4a841925L) + 0x48858f1137791ae9L;
                     value2 = (value2 ^ 0x6a6493a5c25a168eL) + 0x2a798c8c446a0885L;
                  }

                  if (count2 < count) {
                     value = (value ^ -0x5dad71543711d7d7L) - 0x7c2f8fb47e3dc33fL;
                     value2 = (value2 ^ -0x382defc18b3c8103L) + 0x61c079a04cc53708L;
                     continue;
                  }

                  value5 = (value ^ 0x1fd83f61da9ee5cbL) + 0x10f7e9400f962c21L;
                  value6 = (value2 ^ 0x958f3ac5731dea4L) + 0x841746426c0be51L;
               }

               value = (value5 ^ -0x3184de300a830eaaL) - 0x7ac5eec60ae816e7L;
               value2 = (value6 ^ -0x3f31766802ca82cL) - 0x60e2879c943d020fL;
            }

            value = (value ^ 0x6544fa97d05fdfeaL) - 0x334eab75d0c8d492L;
            value2 = (value2 ^ -0x5e84a2fdbf956f84L) + 0x7030c2ca568a4067L;
            break;
         }

         if (stack.isEmpty()) {
            return list;
         }

         value = (value ^ -0x7e1f49eae461185cL) - 0x402fc4196edb7d82L;
         value2 = (value2 ^ 0x145c8833f92f54a4L) + 0x1acf770c904d3eaL;
      }
   }

   public static byte[] resolveByteArray(InputStream inputStream, int count) throws IOException {
      int count2 = 0;
      byte[] byteValueArray = null;
      int count3 = 0;
      int count4 = 0;
      int count5 = 0;
      short shortValue = 0;
      long value = 0x2edb0129ce06be99L;
      long value2 = 0x3cb2ce39a4849df2L;

      byte[] byteValueArray2;
      while (true) {
         int count6 = (int)(value % 18L);
         if (count6 == 3) {
            count2 = count5;
            value = (value ^ -0x224e3d7868e51b56L) + 0x38f9c0330fbdbe16L;
            value2 = (value2 ^ 0x55f42c5de9a55e29L) - 0x7115abc818dea02L;
         } else if (count6 == 10) {
            count3 = 0;
            value = (value ^ 0x4e8222eba9c1297fL) + 0x544f6373ed9003f6L;
            value2 = (value2 ^ 0x776c53c7713ac78fL) + 0x3c2c7adb68b66fdeL;
         } else if (count6 == 8) {
            count3 += count4;
            value = (value ^ -0x2476baee3b0c2446L) - 0x4b2b657e8cefecf2L;
            value2 = (value2 ^ -0x6eae46fb2146a3ebL) - 0x7a1beac626a31d71L;
         } else if (count6 != 17) {
            if (count6 == 15) {
               shortValue = 8192;
               value = (value ^ 0x3ee3329313577852L) - 0x1d92e4d93543e76bL;
               value2 = (value2 ^ -0x38ee6b20d9b101e5L) + 0x6888ac421032044L;
            } else if (count6 != 9) {
               searchBlock: {
                  long value3;
                  long value4;
                  if (count6 != 4) {
                     if (count6 != 6) {
                        if (count6 == 13) {
                           count5 = 2147483639;
                           value = (value ^ -0xc922d74d941e7e2L) - 0x7b8affd9d78ee4dcL;
                           value2 = (value2 ^ -0x1da49199c742da26L) + 0x7201d914b5fe4393L;
                           continue;
                        }

                        if (count6 != 16) {
                           if (count6 == 2) {
                              count2 = Math.max(count2 << 1, shortValue);
                              value = (value ^ -0x2e1f9ae834da1e8bL) - 0x6dd253f51cd7361cL;
                              value2 = (value2 ^ 0xeeff982f657f4ebL) - 0x4e0d27ed912e3129L;
                              continue;
                           }

                           if (count6 == 14) {
                              throw new OutOfMemoryError("Required array size too large");
                           }

                           if (count6 == 7) {
                              count2 = count;
                              value = (value ^ 0x69cc17e70a6e61e5L) - 0x96f11896808fe26L;
                              value2 = (value2 ^ 0x3343c8905cf1697L) + 0x79110759563eca32L;
                              continue;
                           }

                           if (count6 == 11) {
                              value = (value ^ 0x48848b7d55667929L) - 0x493566a269910721L;
                              value2 = (value2 ^ -0x3996b53dfcd7b7a4L) - 0x6884499cb9fe4888L;
                              break searchBlock;
                           }

                           if (count6 == 5) {
                              byteValueArray2 = byteValueArray;
                              break;
                           }

                           if (count6 == 0) {
                              byteValueArray = new byte[count2];
                              value = (value ^ -0x144bdc4dde24d30L) - 0x5506c2b17708804eL;
                              value2 = (value2 ^ -0x2f5d45264a8d2817L) + 0xb116384a25d573eL;
                              continue;
                           }

                           if (count6 == 1) {
                              break searchBlock;
                           }

                           value = (value ^ 0xf8b292fd4216f6dL) + 0x88aca7846e48a81L;
                           value2 = (value2 ^ -0x2ef57ae1f470876aL) - 0x28cb23d05862340bL;
                        } else {
                           value = (value ^ 0x79abe4f70910b99eL) - 0x24d753f29fbbddd4L;
                           value2 = (value2 ^ -0x3fb89fd14f8299f7L) - 0x394da083d740d131L;
                        }
                     }

                     if ((count4 = inputStream.read(byteValueArray, count3, count2 - count3)) > 0) {
                        value = (value ^ -0x21e2b567d5aacc27L) - 0x75b5e97d704a8a0bL;
                        value2 = (value2 ^ -0x20f18a440d1f2769L) - 0x292a2a4db87e0aedL;
                        continue;
                     }

                     long value5 = (value ^ 0x101e07acf105e439L) + 0x3e94b8e207a9614dL;
                     long value6 = (value2 ^ -0x76767a9031ca5068L) - 0x7d777cc4ca8d395eL;
                     if (count4 >= 0) {
                        value = (value5 ^ -0x32a4f1f68cf5fc17L) - 0x3f42e825bc9a9ac0L;
                        value2 = (value6 ^ -0x31e1443d6102b945L) - 0x3ba0be9d4f62a159L;
                        continue;
                     }

                     value3 = (value5 ^ 0x556a58a78cbed522L) - 0x13acd17237e29b7eL;
                     value4 = (value6 ^ 0x2d9461e510af2c7aL) - 0x2ba6f7791a1c2bc7L;
                  } else {
                     value3 = (value ^ 0x52dbeff79e542e5aL) - 0x32591750d0823192L;
                     value4 = (value2 ^ 0x731025dab2ef3b6eL) - 0x24ee31939b0bdd44L;
                  }

                  if (count2 != count3) {
                     byteValueArray2 = Arrays.copyOf(byteValueArray, count3);
                     break;
                  }

                  value = (value3 ^ 0x6f74965ee7cb58dbL) - 0x66f99c0c3254a8b4L;
                  value2 = (value4 ^ 0x3da29d901fe3d238L) + 0x1c9f68d4acd11719L;
                  continue;
               }

               byteValueArray = Arrays.copyOf(byteValueArray, count2);
               value = (value ^ 0x5149e79c42b6921L) + 0xee420dc588bfa1L;
               value2 = (value2 ^ 0x2239851ca80f5c34L) - 0x28965286a4b71852L;
            } else {
               byteValueArray[count3++] = (byte)count4;
               value = (value ^ -0x4660c157112b4d6eL) + 0x6598b510128cd5f9L;
               value2 = (value2 ^ -0x6c0f425ba5753c97L) - 0x2586342acb78da75L;
            }
         } else if ((count4 = inputStream.read()) >= 0) {
            long value7 = (value ^ 0xcb5c8693e181a21L) + 0x247dc2c794619724L;
            long value8 = (value2 ^ -0x70d81471b96bbd99L) + 0x1d4b6f3f55a4fe60L;
            if (count2 > count5 - count2) {
               long value9 = (value7 ^ -0x62d80b2b4634ec6dL) + 0x2d603a1e3eeed1f4L;
               long value10 = (value8 ^ -0x3addab58be3e5172L) - 0x722ff6848a155f16L;
               if (count2 != count5) {
                  value = (value9 ^ 0x12f6dd637525e0f4L) - 0x48eaf540277e83eL;
                  value2 = (value10 ^ -0x7c6d331a323b39fdL) - 0x72935d560e7a3224L;
               } else {
                  value = (value9 ^ 0x51d232f75eaa5582L) + 0x5c26c7a8ae403b9L;
                  value2 = (value10 ^ 0x1579f68627a64041L) + 0x7052bc4553ef0536L;
               }
            } else {
               value = (value7 ^ 0x79a2c475fff53079L) + 0x264da3c766aec565L;
               value2 = (value8 ^ -0x4f6c82a5576f0035L) - 0x17138dd3efc68991L;
            }
         } else {
            value = (value ^ 0x4f555d82a0833a09L) + 0x66dd2da222dcb74cL;
            value2 = (value2 ^ -0x489275dde41481eeL) + 0x3511d09b927fc26bL;
         }
      }

      return byteValueArray2;
   }

   public static ExtraStringHolder resolveExtraStringHolder(File file, long value, int count, int count2, byte[] byteValueArray) {
      try {
         long length = file.length();
         if (length > value) {
            return new ExtraStringHolder("File too large (" + length + " bytes)", file.getAbsolutePath(), count, count2, byteValueArray);
         } else {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] byteValueArray2 = resolveByteArray(inputStream, 4096);
            inputStream.close();
            return new ExtraStringHolder(file.getAbsolutePath(), file.getName(), byteValueArray2, count, count2, byteValueArray);
         }
      } catch (Throwable throwable) {
         return new ExtraStringHolder(throwable.getMessage(), "", count, count2, byteValueArray);
      }
   }
}
