package client.util;

import client.network.CosmeticsNetworkHandler;
import java.util.concurrent.ConcurrentHashMap;

public class SessionTokenManager {
   public static int count;
   public static final ConcurrentHashMap concurrentHashMap;

   public static synchronized void updateState3() {
      long value2 = 0x54e0f32d12ceb1d8L;

      while (true) {
         if ((int)(value2 % 4L) != 0) {
            if (CosmeticsNetworkHandler.isToggled()) {
               CosmeticsNetworkHandler simpleChannelInboundHandlerAdapter = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
               SupplementalByteArrayEntry supplementalByteArrayEntry = new SupplementalByteArrayEntry(SupplementalLongEntry.byteIndexArray, false);
               simpleChannelInboundHandlerAdapter.updatePostMotionProcessor(supplementalByteArrayEntry);
               return;
            }
            break;
         }

         if (SupplementalLongEntry.byteIndexArray.length < 32) {
            break;
         }

         value2 = (value2 ^ -0x15f172ada23c64eaL) + 0x6811f6ccafc5143fL;
      }
   }

   public static synchronized void applyTextByText(String text) {
      int count2 = 0;
      ListenerDispatcher listenerDispatcher = null;
      long value = 0x163d68284fef2942L;
      long value2 = 0x404a114cdcd9d925L;

      while (true) {
         int count3 = (int)(value % 7L);
         if (count3 != 5) {
            if (count3 != 3) {
               if (count3 != 4) {
                  if (count3 != 1) {
                     if (count3 == 6) {
                        listenerDispatcher = new ListenerDispatcher(SupplementalLongEntry.byteIndexArray, text, count2);
                        value = (value ^ -0xbea94a3375012edL) + 0x61aca2b773a80d1L;
                        value2 = (value2 ^ 0x4e1e5c73635c7b22L) + 0x516d216b3e99f218L;
                        continue;
                     }

                     if (SupplementalLongEntry.byteIndexArray.length >= 32) {
                        long value3 = (value ^ -0x53a9696f29c9a001L) - 0x75fbc8ce6db4a0b9L;
                        long value4 = (value2 ^ 0x5f3cfff2ad92d3feL) + 0x7e7c78757c754c02L;
                        count2 = getCount();
                        value = (value3 ^ 0x27c8dede353c10afL) - 0x5570f77f0a947352L;
                        value2 = (value4 ^ 0xbe0c6a1742a99c7L) - 0x7dddc35728d80d88L;
                        continue;
                     }
                  }

                  return;
               }

               concurrentHashMap.put(count2, new Pair(System.currentTimeMillis(), listenerDispatcher));
               value = (value ^ -0x232f86212cca7aefL) + 0x634cccd38e80c6daL;
               value2 = (value2 ^ -0x2fd5c2f415be706eL) + 0x1baa8a45ad0137d9L;
               continue;
            }
         } else {
            if (!CosmeticsNetworkHandler.isToggled()) {
               return;
            }

            CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().updatePostMotionProcessor(listenerDispatcher);
         }

         return;
      }
   }

   public static synchronized void applyText(String text) {
      int count2 = 0;
      InternalListenerProcessor internalListenerProcessor = null;
      long value = 0x45c2efa495c19c97L;
      long value2 = 0x3b2e39b3ab2c5a6dL;

      while (true) {
         int count3 = (int)(value % 7L);
         if (count3 == 5) {
            internalListenerProcessor = new InternalListenerProcessor(SupplementalLongEntry.byteIndexArray, text, count2);
            value = (value ^ 0xf4c508de822cd57L) + 0x271cd47d71115c4dL;
            value2 = (value2 ^ -0x16fb017d8e69224eL) - 0x25076f9e95ce8fe7L;
         } else if (count3 != 2) {
            if (count3 == 0) {
               break;
            }

            if (count3 == 4) {
               if (CosmeticsNetworkHandler.isToggled()) {
                  CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().updatePostMotionProcessor(internalListenerProcessor);
                  return;
               }
               break;
            }

            concurrentHashMap.put(count2, new Pair(System.currentTimeMillis(), internalListenerProcessor));
            value = (value ^ 0x49d174b5bfdf5c8fL) + 0x12f371dc90386ddeL;
            value2 = (value2 ^ 0x3574f29d5659e178L) - 0x776b3951f082de6cL;
         } else {
            if (SupplementalLongEntry.byteIndexArray.length < 32) {
               return;
            }

            long value3 = (value ^ -0x176a6882d5faa8e6L) - 0x2e1f701185074aeaL;
            long value4 = (value2 ^ -0x19a07baaa3d38931L) - 0x3bc20a3d1db2622eL;
            count2 = getCount();
            value = (value3 ^ 0xb4c875ba656916aL) - 0x2c685f52ec920ee3L;
            value2 = (value4 ^ 0x59e1430f82b9d0e3L) - 0x16ace082fdfa3453L;
         }
      }
   }

   public static synchronized void updateState() {
      int count2 = 0;
      AdvancedByteArrayEntry advancedByteArrayEntry = null;
      long value = 0x64e3e47b0d6f335bL;
      long value2 = -0x6dd328e9096bf132L;

      while (true) {
         int count3 = (int)(value % 7L);
         if (count3 != 5) {
            if (count3 != 3) {
               if (count3 != 2) {
                  if (CosmeticsNetworkHandler.isToggled()) {
                     CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().updatePostMotionProcessor(advancedByteArrayEntry);
                     return;
                  }

                  return;
               }

               advancedByteArrayEntry = new AdvancedByteArrayEntry(SupplementalLongEntry.byteIndexArray, count2);
               value = (value ^ 0x7626e724ab21c63L) + 0xc09262b5335874bL;
               value2 = (value2 ^ -0x5163383b3a8c5f67L) - 0x24a6f10979523a93L;
            } else {
               concurrentHashMap.put(count2, new Pair(System.currentTimeMillis(), advancedByteArrayEntry));
               value = (value ^ -0x3b9438a84ac8120cL) + 0x72933436ca518dccL;
               value2 = (value2 ^ -0x130b612a05f49a56L) + 0x5a29b9867f806a0eL;
            }
         } else {
            if (SupplementalLongEntry.byteIndexArray.length < 32) {
               return;
            }

            long value3 = (value ^ 0x2b35a29cfb2b3f88L) - 0x2b50fe66c9b2b121L;
            long value4 = (value2 ^ -0x33de06204d1e51e6L) - 0x462dd3901db8d9b0L;
            count2 = getCount();
            value = (value3 ^ 0x26dbd096bc72cbbfL) + 0x6160a8a84303c9a9L;
            value2 = (value4 ^ 0x20e6700be02e7c55L) - 0x54edb7a7fc7e2c6dL;
         }
      }
   }

   public static synchronized void applyFlag(boolean flag, int count2) {
      int count3 = 0;
      AdvancedListenerAdapter advancedListenerAdapter = null;
      long value = 0x5e98a5649e6fb3e5L;
      long value2 = -0x4702fa105dc1b3fL;

      while (true) {
         int count4 = (int)(value % 7L);
         if (count4 == 6) {
            break;
         }

         if (count4 != 3) {
            if (count4 == 5) {
               if (CosmeticsNetworkHandler.isToggled()) {
                  CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().updatePostMotionProcessor(advancedListenerAdapter);
                  return;
               }

               return;
            }

            if (count4 != 4) {
               if (SupplementalLongEntry.byteIndexArray.length < 32) {
                  break;
               }

               long value3 = (value ^ -0x51f6ab1ece2e29eaL) + 0x77be4445783cb117L;
               long value4 = (value2 ^ 0x7d45488fb9a4f201L) + 0x344307549111ea7L;
               count3 = getCount();
               value = (value3 ^ -0x42a13e131ecac2dfL) + 0x46280dbf5a4f9b55L;
               value2 = (value4 ^ 0x230189349e3fde54L) - 0x766e7e202ae4bc80L;
            } else {
               concurrentHashMap.put(count3, new Pair(System.currentTimeMillis(), advancedListenerAdapter));
               value = (value ^ -0xf6ae654db3312a7L) + 0x73031ce2f4df22b0L;
               value2 = (value2 ^ -0x5887f405265eb870L) - 0x55a4a1ddee317271L;
            }
         } else {
            advancedListenerAdapter = new AdvancedListenerAdapter(SupplementalLongEntry.byteIndexArray, flag, count2, count3);
            value = (value ^ -0x1315c1f1f9ec03e3L) + 0x6f8e9edcfe7885dbL;
            value2 = (value2 ^ -0x537789a53ae3027fL) - 0x62bb197077604616L;
         }
      }
   }

   public static void applyText2(String text) {
      if (SupplementalLongEntry.byteIndexArray.length >= 32 && CosmeticsNetworkHandler.isToggled()) {
         CosmeticsNetworkHandler simpleChannelInboundHandlerAdapter = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
         ManagedListenerAdapter managedListenerAdapter = new ManagedListenerAdapter(SupplementalLongEntry.byteIndexArray, text, "");
         simpleChannelInboundHandlerAdapter.updatePostMotionProcessor(managedListenerAdapter);
      }
   }

   public static synchronized void applyText3(String text) {
      int count2 = 0;
      ManagedStringEntry managedStringEntry = null;
      long value = 0x123c6fab77e1ce04L;
      long value2 = -0x3ea04d2b8e72445dL;

      while (true) {
         int count3 = (int)(value % 7L);
         if (count3 == 3) {
            break;
         }

         if (count3 == 2) {
            if (CosmeticsNetworkHandler.isToggled()) {
               CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().updatePostMotionProcessor(managedStringEntry);
               return;
            }

            return;
         }

         if (count3 != 4) {
            if (count3 != 6) {
               concurrentHashMap.put(count2, new Pair(System.currentTimeMillis(), managedStringEntry));
               value = (value ^ -0x40d0c5d44ed9b8acL) - 0x665c8e232c6d87daL;
               value2 = (value2 ^ 0x62b06cc66306ee11L) - 0x6c905cdde96bf265L;
            } else {
               if (SupplementalLongEntry.byteIndexArray.length < 32) {
                  break;
               }

               long value3 = (value ^ -0x61fde62092af69bbL) - 0x51f42d2b34e05f38L;
               long value4 = (value2 ^ -0x419ff04e952f6042L) + 0x4a9580b86276aa6L;
               count2 = getCount();
               value = (value3 ^ -0x292afc4654cd6427L) + 0x517b253f75c2174fL;
               value2 = (value4 ^ -0x5c81dae1ff91bc6cL) + 0xc4f1d287abbe397L;
            }
         } else {
            managedStringEntry = new ManagedStringEntry(SupplementalLongEntry.byteIndexArray, text, count2);
            value = (value ^ 0x1945d9b53765cc43L) - 0x244a95f2c56019ebL;
            value2 = (value2 ^ -0x3d72e17cd7c17b1dL) + 0x1010a130ebe6cc15L;
         }
      }
   }

   public static void applyText(String text, String text2) {
      long value = 621364498651L;
      long value2 = 0x59838f8af8d8a41aL;

      while (true) {
         if ((int)(value2 % 4L) == 3) {
            if (CosmeticsNetworkHandler.isToggled()) {
               CosmeticsNetworkHandler simpleChannelInboundHandlerAdapter = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
               ManagedListenerAdapter managedListenerAdapter = new ManagedListenerAdapter(SupplementalLongEntry.byteIndexArray, text2, text);
               simpleChannelInboundHandlerAdapter.updatePostMotionProcessor(managedListenerAdapter);
               return;
            }
            break;
         }

         if (SupplementalLongEntry.byteIndexArray.length < 32) {
            break;
         }

         value2 = (value2 ^ 0x50699ea9f6f5a37eL) + 0x8dde6ff676ca5cfL;
      }
   }

   public static synchronized boolean matchesText(String text, int count2) {
      int shortValue = 0;
      int count3 = 0;
      long value = 0x6a669e91514e2d0bL;
      long value2 = -0xce412a1fb3c2354L;

      while (true) {
         int count4 = (int)(value % 8L);
         if (count4 != 1) {
            searchBlock: {
               long value3;
               long value4;
               String text2;
               if (count4 != 0) {
                  if (count4 == 2) {
                     if (count3 >= 4) {
                        break;
                     }

                     value = (value ^ 0x2d23caf0bbfa1091L) + 0xd757ed63799d294L;
                     value2 = (value2 ^ 0x6fbb8020595ef77aL) - 0x49da17d7b7a8b083L;
                     continue;
                  }

                  if (count4 == 5) {
                     break searchBlock;
                  }

                  if (count4 == 3) {
                     if (!CosmeticsNetworkHandler.isToggled()) {
                        return false;
                     }

                     value = (value ^ 0x53d44d23ad1b9488L) - 0x8c4e396b93b778fL;
                     value2 = (value2 ^ 0x172e7c4430137cbbL) - 0x742b9af0eed65a28L;
                     continue;
                  }

                  if (count4 != 7) {
                     shortValue = 0;
                     value = (value ^ 0x3a01b60af2f13555L) + 0x20215850586e74e0L;
                     value2 = (value2 ^ -0x1d463c2ff6dd64cfL) - 0x38f685eb90960a31L;
                     continue;
                  }

                  if (text.length() <= 1800) {
                     value = (value ^ -0x6c5c917945e64efaL) - 0x5decf34fbd137211L;
                     value2 = (value2 ^ 0x715fffbe1e0b434dL) - 0x560b86cef0d8b95bL;
                     continue;
                  }

                  value3 = (value ^ 0x6df544eb7fa2882bL) + 0x53c936d37f81c4c4L;
                  value4 = (value2 ^ -0x3fb749203b26742L) + 0x253bac60ace17274L;
                  text2 = text.substring(shortValue, Math.min(text.length() - 1, shortValue + 1799));
               } else {
                  text2 = text;
                  value3 = (value ^ -0x7cd32d2d2b7c687dL) - 0x7f2bcecd4c818883L;
                  value4 = (value2 ^ -0x46c45413ac77f6ebL) - 0x59279763ad87f8f5L;
               }

               String text3 = text2;
               shortValue += 1800;
               count3++;
               int count5 = getCount();
               EnhancedListenerAdapter enhancedListenerAdapter = new EnhancedListenerAdapter(text3, count5, count2);
               concurrentHashMap.put(count5, new Pair(System.currentTimeMillis(), enhancedListenerAdapter));
               if (!CosmeticsNetworkHandler.isToggled()) {
                  return false;
               }

               long value5 = (value3 ^ 0xddef38161799a08L) + 0x3a2127f5f2869f8L;
               long value6 = (value4 ^ -0x605465741f0823d3L) - 0x7e9786866036442dL;
               CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().updatePostMotionProcessor(enhancedListenerAdapter);
               value = (value5 ^ 0x1e3dbb4a10e401c4L) - 0x6b80ab716edd70fL;
               value2 = (value6 ^ -0x22263bdcc8ad1ce2L) + 0x631b4ffc00dd0a0eL;
            }

            if (shortValue >= text.length()) {
               break;
            }

            value = (value ^ -0x2b804423b87752c0L) - 0x79872d60421d9983L;
            value2 = (value2 ^ -0x2e339a8c4ff25a54L) - 0x5302fcae8c7d4fddL;
         } else {
            count3 = 0;
            value = (value ^ -0x3a22d7f59ffaa7L) - 0x768411c51a76bdb3L;
            value2 = (value2 ^ 0x65b7356e8cd60053L) + 0x1420550eb47a429cL;
         }
      }

      return true;
   }

   public static synchronized void updateState2() {
      if (SupplementalLongEntry.byteIndexArray.length >= 32 && CosmeticsNetworkHandler.isToggled()) {
         CosmeticsNetworkHandler simpleChannelInboundHandlerAdapter = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
         SupplementalByteArrayEntry supplementalByteArrayEntry = new SupplementalByteArrayEntry(SupplementalLongEntry.byteIndexArray, true);
         simpleChannelInboundHandlerAdapter.updatePostMotionProcessor(supplementalByteArrayEntry);
      }
   }

   private static synchronized int getCount() {
      return count++;
   }

   public static synchronized void applyText(String text, String text2, String text3, int count2, boolean flag) {
      int count3 = 0;
      InternalByteArrayEntry internalByteArrayEntry = null;
      long value = 0x63f831cbd74974eL;
      long value2 = -0x3349da0675f41894L;

      while (true) {
         int count4 = (int)(value % 7L);
         if (count4 != 3) {
            if (count4 != 4) {
               if (count4 == 5) {
                  if (CosmeticsNetworkHandler.isToggled()) {
                     CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().updatePostMotionProcessor(internalByteArrayEntry);
                     return;
                  }

                  return;
               }

               internalByteArrayEntry = new InternalByteArrayEntry(SupplementalLongEntry.byteIndexArray, text, text2, text3, count2, flag, count3);
               value = (value ^ -0x2ed5d571cac8bc12L) - 0x6aed67b684853005L;
               value2 = (value2 ^ -0x624745a72cd2d9d9L) + 0x738b0a84964d6695L;
            } else {
               concurrentHashMap.put(count3, new Pair(System.currentTimeMillis(), internalByteArrayEntry));
               value = (value ^ 0x5fc3e6e06562c23L) - 0x18840cadcd29bfceL;
               value2 = (value2 ^ -0x77542be8fed56b55L) - 0x12b0592b26bf9cb8L;
            }
         } else {
            if (SupplementalLongEntry.byteIndexArray.length < 32) {
               return;
            }

            long value3 = (value ^ -0x5b610795f4cddca5L) + 0x6f8f42f0533f6d4eL;
            long value4 = (value2 ^ -0x5417d0c7dd95d696L) + 0x6d22c84f087bc2b6L;
            count3 = getCount();
            value = (value3 ^ -0x64f6d79538c81053L) - 0x4f987187e5292802L;
            value2 = (value4 ^ -0x75830198c1e85f28L) - 0x14bcfc4d28979075L;
         }
      }
   }

   public static void applyText(String text, String text2, String text3) {
      byte byteValue = 0;
      if (SupplementalLongEntry.byteIndexArray.length >= 32 && CosmeticsNetworkHandler.isToggled()) {
         CosmeticsNetworkHandler simpleChannelInboundHandlerAdapter = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
         ActiveStringEntry activeStringEntry = new ActiveStringEntry(SupplementalLongEntry.byteIndexArray, text, text2, text3);
         simpleChannelInboundHandlerAdapter.updatePostMotionProcessor(activeStringEntry);
      }
   }

   static {
      for (long index = 0x2177689d4258c2a7L; (int)(index % 3L) != 0; index = (index ^ -0x4017eb3fdc175a1L) + 0x7113151e2ca8857aL) {
         count = 0;
      }

      concurrentHashMap = new ConcurrentHashMap();
   }
}
