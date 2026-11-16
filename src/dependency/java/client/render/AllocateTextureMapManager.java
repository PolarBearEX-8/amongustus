package client.render;

import client.event.BridgeListener;
import client.util.BuildManager;
import client.util.DistanceTransformer;
import client.util.ExtendedSetEntry;
import client.util.ListenerAdapter26;
import client.util.ListenerAdapter27;
import client.util.PreEndHandManager;
import client.util.ServiceStringEntry;
import client.util.SmoothDispatcher;
import client.util.SpecialLoggerEntry;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class AllocateTextureMapManager {
   public static final int count4 = 1048576;
   public static ByteBuffer byteBuffer = BufferUtils.createByteBuffer(4194304);
   public static IntBuffer intBuffer = byteBuffer.asIntBuffer();
   public static int[] countArray = new int[1048576];
   public static final int count3 = 0;
   public static final int count = -8421377;
   public static final int count2 = 0;
   public static Map map = new HashMap();

   public static void applyListenerAdapter27(
      ListenerAdapter27 listenerAdapter, String text, int[][] countMatrix, int count5, int count6, int count7, int count8, boolean flag, boolean flag2
   ) {
      DistanceTransformer distanceTransformer = listenerAdapter.distanceTransformer;
      SpecialLoggerEntry.applyIndexArray(countMatrix, count5, count6, count7, count8, flag, flag2);
      if (PreEndHandManager.enabled15) {
         int[][] countMatrix2 = resolveIndexArray(listenerAdapter, text + "_n", count5, count6, countMatrix.length, false, -8421377);
         GlStateManager.setActiveTexture(distanceTransformer.count3);
         SpecialLoggerEntry.applyIndexArray(countMatrix2, count5, count6, count7, count8, flag, flag2);
      }

      if (PreEndHandManager.enabled37) {
         int[][] countMatrix3 = resolveIndexArray(listenerAdapter, text + "_s", count5, count6, countMatrix.length, false, 0);
         GlStateManager.setActiveTexture(distanceTransformer.count2);
         SpecialLoggerEntry.applyIndexArray(countMatrix3, count5, count6, count7, count8, flag, flag2);
      }

      GlStateManager.setActiveTexture(distanceTransformer.count);
   }

   public static void applyExtendedSetEntry(int count5, int count6, int count7, int count8, ExtendedSetEntry extendedSetEntry, ListenerAdapter27 listenerAdapter) {
      ShadersManager.applyText("allocateTextureMap " + count6 + " " + count7 + " " + count8 + " ");
      listenerAdapter.count7 = count7;
      listenerAdapter.count8 = count8;
      DistanceTransformer distanceTransformer = resolveDistanceTransformer(listenerAdapter);
      SpecialLoggerEntry.applyIndex(distanceTransformer.count, count6, count7, count8);
      if (PreEndHandManager.enabled15) {
         SpecialLoggerEntry.applyIndex(distanceTransformer.count3, count6, count7, count8);
      }

      if (PreEndHandManager.enabled37) {
         SpecialLoggerEntry.applyIndex(distanceTransformer.count2, count6, count7, count8);
      }

      GlStateManager.setActiveTexture(count5);
   }

   public static int[] resolveIndexArray(int count5, int count6) {
      int[] countArray2 = new int[count5 * 3];
      Arrays.fill(countArray2, 0, count5, count6);
      Arrays.fill(countArray2, count5, count5 * 2, -8421377);
      Arrays.fill(countArray2, count5 * 2, count5 * 3, 0);
      return countArray2;
   }

   public static void applyDistanceTransformer(DistanceTransformer distanceTransformer, int[] countArray2, int count5, int count6, int count7, int count8, boolean flag, boolean flag2) {
      int count9 = count5 * count6;
      IntBuffer buffer = resolveIntBuffer(count9);
      ((Buffer)buffer).clear();
      buffer.put(countArray2, 0, count9);
      ((Buffer)buffer).position(0).limit(count9);
      GlStateManager.setActiveTexture(distanceTransformer.count);
      GL11.glTexParameteri(3553, 10241, 9728);
      GL11.glTexParameteri(3553, 10240, 9728);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexSubImage2D(3553, 0, count7, count8, count5, count6, 32993, 33639, buffer);
      if (countArray2.length == count9 * 3) {
         ((Buffer)buffer).clear();
         ((Buffer)buffer.put(countArray2, count9, count9)).position(0);
         ((Buffer)buffer).position(0).limit(count9);
      }

      GlStateManager.setActiveTexture(distanceTransformer.count3);
      GL11.glTexParameteri(3553, 10241, 9728);
      GL11.glTexParameteri(3553, 10240, 9728);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexSubImage2D(3553, 0, count7, count8, count5, count6, 32993, 33639, buffer);
      if (countArray2.length == count9 * 3) {
         ((Buffer)buffer).clear();
         buffer.put(countArray2, count9 * 2, count9);
         ((Buffer)buffer).position(0).limit(count9);
      }

      GlStateManager.setActiveTexture(distanceTransformer.count2);
      GL11.glTexParameteri(3553, 10241, 9728);
      GL11.glTexParameteri(3553, 10240, 9728);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexSubImage2D(3553, 0, count7, count8, count5, count6, 32993, 33639, buffer);
      GlStateManager.enableState5(33984);
   }

   public static void applyDistanceTransformerByDistanceTransfor(DistanceTransformer distanceTransformer) {
      applyIndex(distanceTransformer.count3, distanceTransformer.count2);
   }

   public static int computeCountByIndex(int count5) {
      int count6 = count5 - 1;
      int count7 = count6 | count6 >> 1;
      int count8 = count7 | count7 >> 2;
      int count9 = count8 | count8 >> 4;
      int count10 = count9 | count9 >> 8;
      return (count10 | count10 >> 16) + 1;
   }

   public static void applyIndex(int count5, int count6) {
      if (PreEndHandManager.enabled31 && GlStateManager.getStateInt10() == 33984) {
         GlStateManager.enableState5(33986);
         GlStateManager.setActiveTexture(count5);
         GlStateManager.enableState5(33987);
         GlStateManager.setActiveTexture(count6);
         GlStateManager.enableState5(33984);
      }
   }

   public static void applyIndexArray(int[] countArray2, int count5, int count6, int count7) {
      Math.min(count6, count7);
      int count8 = count5;
      int count9 = count6;
      int count10 = count7;
      int count11 = 0;
      int count12 = 0;

      int count13;
      for (count13 = 0; count9 > 1 && count10 > 1; count8 = count11) {
         count11 = count8 + count9 * count10;
         count12 = count9 / 2;
         count10 /= 2;

         for (int index = 0; index < count10; index++) {
            int count14 = count11 + index * count12;
            int count15 = count8 + index * 2 * count9;

            for (int index2 = 0; index2 < count12; index2++) {
               countArray2[count14 + index2] = computeCountByIndex(
                  countArray2[count15 + index2 * 2], countArray2[count15 + index2 * 2 + 1], countArray2[count15 + count9 + index2 * 2], countArray2[count15 + count9 + index2 * 2 + 1]
               );
            }
         }

         count13++;
         count9 = count12;
      }

      while (count13 > 0) {
         int count16 = count6 >> --count13;
         int count17 = count7 >> count13;
         int count18 = count11 - count16 * count17;
         int count19 = count18;

         for (int index3 = 0; index3 < count17; index3++) {
            for (int index4 = 0; index4 < count16; index4++) {
               if (countArray2[count19] == 0) {
                  countArray2[count19] = countArray2[count11 + index3 / 2 * count12 + index4 / 2] & 16777215;
               }

               count19++;
            }
         }

         count11 = count18;
         count12 = count16;
      }
   }

   public static void applyIndex(int count5) {
      applyDistanceTransformer((DistanceTransformer)map.get(count5));
   }

   public static ResourceLocation resolveResourceLocation(ResourceLocation location, String text) {
      if (location == null) {
         return null;
      } else {
         String text2 = location.getText1().split(".png")[0];
         return new ResourceLocation(location.getText(), text2 + "_" + text + ".png");
      }
   }

   public static void applyIndexArray(int[][] countMatrix, int count5, int count6, int count7, int count8, int count9) {
      IntBuffer buffer = resolveIntBuffer(count5 * count6);
      int length = countMatrix.length;
      int count10 = 0;
      int count11 = count5;
      int count12 = count6;
      int count13 = count7;

      for (int index = count8; count11 > 0 && count12 > 0 && count10 < length; count10++) {
         int count14 = count11 * count12;
         int[] countArray2 = countMatrix[count10];
         ((Buffer)buffer).clear();
         if (countArray2.length >= count14 * (count9 + 1)) {
            ((Buffer)buffer.put(countArray2, count14 * count9, count14)).position(0).limit(count14);
            GL11.glTexSubImage2D(3553, count10, count13, index, count11, count12, 32993, 33639, buffer);
         }

         count11 >>= 1;
         count12 >>= 1;
         count13 >>= 1;
         index >>= 1;
      }

      ((Buffer)buffer).clear();
   }

   public static void applySmoothDispatcher(SmoothDispatcher smoothDispatcher, IResourceManager iResourceManager, List list) {
      int count5 = 0;
      int count6 = 0;
      int count7 = 0;
      int[] countArray2 = null;

      for (Object element : list) {
         if (element != null) {
            try {
               ResourceLocation location = new ResourceLocation((String)element);
               BufferedImage bufferedImage = ImageIO.read(iResourceManager.resolveIResourceByResourceLocation(location).getInputStream());
               if (count7 == 0) {
                  count5 = bufferedImage.getWidth();
                  count6 = bufferedImage.getHeight();
                  count7 = count5 * count6;
                  countArray2 = resolveIndexArray(count7, 0);
               }

               int[] countArray3 = resolveIndexArray(count7 * 3);
               bufferedImage.getRGB(0, 0, count5, count6, countArray3, 0, count5);
               applyIResourceManagerByIResourceManager(iResourceManager, location, count5, count6, countArray3);

               for (int index = 0; index < count7; index++) {
                  int count8 = countArray3[index] >>> 24 & 0xFF;
                  countArray2[count7 * 0 + index] = computeCount(countArray3[count7 * 0 + index], countArray2[count7 * 0 + index], count8);
                  countArray2[count7 * 1 + index] = computeCount(countArray3[count7 * 1 + index], countArray2[count7 * 1 + index], count8);
                  countArray2[count7 * 2 + index] = computeCount(countArray3[count7 * 2 + index], countArray2[count7 * 2 + index], count8);
               }
            } catch (IOException ioException) {
               ioException.printStackTrace();
            }
         }
      }

      applyDistanceTransformer(smoothDispatcher.getDistanceTransformer(), countArray2, count5, count6, false, false);
   }

   public static int[] resolveIndexArray(int count5) {
      if (countArray == null) {
         countArray = new int[1048576];
      }

      if (countArray.length < count5) {
         countArray = new int[computeCountByIndex(count5)];
      }

      return countArray;
   }

   public static void applyIResourceManagerByIResourceManager(IResourceManager iResourceManager, ResourceLocation location, int count5, int count6, int[] countArray2) {
      if (PreEndHandManager.enabled15) {
         applyIResourceManagerByIResourceManager(iResourceManager, resolveResourceLocation(location, "n"), count5, count6, countArray2, count5 * count6, -8421377);
      }

      if (PreEndHandManager.enabled37) {
         applyIResourceManagerByIResourceManager(iResourceManager, resolveResourceLocation(location, "s"), count5, count6, countArray2, count5 * count6 * 2, 0);
      }
   }

   public static int computeCount(int count5, int count6, int count7) {
      int count8 = 255 - count7;
      return ((count5 >>> 24 & 0xFF) * count7 + (count6 >>> 24 & 0xFF) * count8) / 255 << 24
         | ((count5 >>> 16 & 0xFF) * count7 + (count6 >>> 16 & 0xFF) * count8) / 255 << 16
         | ((count5 >>> 8 & 0xFF) * count7 + (count6 >>> 8 & 0xFF) * count8) / 255 << 8
         | ((count5 >>> 0 & 0xFF) * count7 + (count6 >>> 0 & 0xFF) * count8) / 255 << 0;
   }

   public static IntBuffer resolveIntBuffer(int count5) {
      if (intBuffer.capacity() < count5) {
         byteBuffer = BufferUtils.createByteBuffer(computeCountByIndex(count5) * 4);
         intBuffer = byteBuffer.asIntBuffer();
      }

      return intBuffer;
   }

   public static int computeCount(int count5) {
      int count6 = count5;
      int count7 = 0;
      if ((count5 & -65536) != 0) {
         count7 += 16;
         count6 = count5 >> 16;
      }

      if ((count6 & 0xFF00) != 0) {
         count7 += 8;
         count6 >>= 8;
      }

      if ((count6 & 240) != 0) {
         count7 += 4;
         count6 >>= 4;
      }

      if ((count6 & 6) != 0) {
         count7 += 2;
         count6 >>= 2;
      }

      if ((count6 & 2) != 0) {
         count7++;
      }

      return count7;
   }

   public static void renderElement() {
      BridgeListener bridgeListener = Minecraft.getMinecraft().getTextureManager().get(ListenerAdapter27.resourceLocation);
      if (bridgeListener != null) {
         DistanceTransformer distance = bridgeListener.getDistanceTransformer();
         GlStateManager.setActiveTexture(distance.count);
         GL11.glTexParameteri(3553, 10241, PreEndHandManager.countArray5[PreEndHandManager.count7]);
         GL11.glTexParameteri(3553, 10240, PreEndHandManager.countArray6[PreEndHandManager.count29]);
         GlStateManager.setActiveTexture(distance.count3);
         GL11.glTexParameteri(3553, 10241, PreEndHandManager.countArray5[PreEndHandManager.count17]);
         GL11.glTexParameteri(3553, 10240, PreEndHandManager.countArray6[PreEndHandManager.count44]);
         GlStateManager.setActiveTexture(distance.count2);
         GL11.glTexParameteri(3553, 10241, PreEndHandManager.countArray5[PreEndHandManager.count22]);
         GL11.glTexParameteri(3553, 10240, PreEndHandManager.countArray6[PreEndHandManager.count16]);
         GlStateManager.setActiveTexture(0);
      }
   }

   public static void applyIndex(int count5, int count6, int count7) {
      if (PreEndHandManager.enabled31 && GlStateManager.getStateInt10() == 33984) {
         GlStateManager.enableState5(33986);
         GlStateManager.setActiveTexture(count6);
         GlStateManager.enableState5(33987);
         GlStateManager.setActiveTexture(count7);
         GlStateManager.enableState5(33984);
      }

      GlStateManager.setActiveTexture(count5);
   }

   public static void applyDistanceTransformer(DistanceTransformer distanceTransformer) {
      if (PreEndHandManager.enabled31 && GlStateManager.getStateInt10() == 33984) {
         if (PreEndHandManager.enabled15) {
            GlStateManager.enableState5(33986);
            GlStateManager.setActiveTexture(distanceTransformer.count3);
         }

         if (PreEndHandManager.enabled37) {
            GlStateManager.enableState5(33987);
            GlStateManager.setActiveTexture(distanceTransformer.count2);
         }

         GlStateManager.enableState5(33984);
      }

      GlStateManager.setActiveTexture(distanceTransformer.count);
   }

   public static void applyIndexArray(int count5, int[] countArray2, int count6, int count7, DynamicTexture dynamicTexture) {
      DistanceTransformer distance = dynamicTexture.getDistanceTransformer();
      GlStateManager.setActiveTexture(distance.count);
      applyIndexArray(countArray2, count6, count7, 0, 0, 0);
      GlStateManager.setActiveTexture(distance.count3);
      applyIndexArray(countArray2, count6, count7, 0, 0, 1);
      GlStateManager.setActiveTexture(distance.count2);
      applyIndexArray(countArray2, count6, count7, 0, 0, 2);
      GlStateManager.setActiveTexture(distance.count);
   }

   public static int computeCount(int count5, int count6, int count7, int count8) {
      return ((count5 >>> 24 & 0xFF) + (count6 >>> 24 & 0xFF) + (count7 >>> 24 & 0xFF) + (count8 >>> 24 & 0xFF) + 2) / 4 << 24
         | ((count5 >>> 16 & 0xFF) + (count6 >>> 16 & 0xFF) + (count7 >>> 16 & 0xFF) + (count8 >>> 16 & 0xFF) + 2) / 4 << 16
         | ((count5 >>> 8 & 0xFF) + (count6 >>> 8 & 0xFF) + (count7 >>> 8 & 0xFF) + (count8 >>> 8 & 0xFF) + 2) / 4 << 8
         | ((count5 >>> 0 & 0xFF) + (count6 >>> 0 & 0xFF) + (count7 >>> 0 & 0xFF) + (count8 >>> 0 & 0xFF) + 2) / 4 << 0;
   }

   private static boolean matchesIResourceManager(IResourceManager iResourceManager, ResourceLocation location, int count5, int count6, int[] countArray2, int count7) {
      if (location == null) {
         return false;
      } else {
         try {
            BufferedImage bufferedImage = ImageIO.read(iResourceManager.resolveIResourceByResourceLocation(location).getInputStream());
            if (bufferedImage == null) {
               return false;
            } else if (bufferedImage.getWidth() == count5 && bufferedImage.getHeight() == count6) {
               bufferedImage.getRGB(0, 0, count5, count6, countArray2, count7, count5);
               return true;
            } else {
               return false;
            }
         } catch (IOException ioException) {
            return false;
         }
      }
   }

   public static void applyBridgeListener(BridgeListener bridgeListener) {
      bridgeListener.getCount();
      applyDistanceTransformer(bridgeListener.getDistanceTransformer());
      if (GlStateManager.getStateInt10() == 33984) {
         int count5 = PreEndHandManager.count4;
         int count6 = PreEndHandManager.count38;
         if (bridgeListener instanceof ListenerAdapter27) {
            PreEndHandManager.count4 = ((ListenerAdapter27)bridgeListener).count7;
            PreEndHandManager.count38 = ((ListenerAdapter27)bridgeListener).count8;
         } else {
            PreEndHandManager.count4 = 0;
            PreEndHandManager.count38 = 0;
         }

         if (PreEndHandManager.count4 != count5 || PreEndHandManager.count38 != count6) {
            PreEndHandManager.extendedStringHandler.applyIndex(PreEndHandManager.count4, PreEndHandManager.count38);
         }
      }
   }

   public static void applyServiceStringEntry(ServiceStringEntry serviceStringEntry, int[] countArray2) {
   }

   public static void applyDynamicTexture(int count5, int count6, int count7, DynamicTexture dynamicTexture) {
      DistanceTransformer distance = dynamicTexture.getDistanceTransformer();
      int[] countArray2 = dynamicTexture.getCountArray();
      int count8 = count6 * count7;
      Arrays.fill(countArray2, count8, count8 * 2, -8421377);
      Arrays.fill(countArray2, count8 * 2, count8 * 3, 0);
      SpecialLoggerEntry.applyIndex(distance.count, count6, count7);
      SpecialLoggerEntry.applyFlag(false, false);
      SpecialLoggerEntry.setEnabledByFlag(false);
      SpecialLoggerEntry.applyIndex(distance.count3, count6, count7);
      SpecialLoggerEntry.applyFlag(false, false);
      SpecialLoggerEntry.setEnabledByFlag(false);
      SpecialLoggerEntry.applyIndex(distance.count2, count6, count7);
      SpecialLoggerEntry.applyFlag(false, false);
      SpecialLoggerEntry.setEnabledByFlag(false);
      GlStateManager.setActiveTexture(distance.count);
   }

   private static void applyIResourceManagerByIResourceManager(IResourceManager iResourceManager, ResourceLocation location, int count5, int count6, int[] countArray2, int count7, int count8) {
      if (!matchesIResourceManager(iResourceManager, location, count5, count6, countArray2, count7)) {
         Arrays.fill(countArray2, count7, count7 + count5 * count6, count8);
      }
   }

   public static void applyIndexArray(int[] countArray2, int count5, int count6, int count7, int count8, int count9) {
      int count10 = count5 * count6;
      IntBuffer buffer = resolveIntBuffer(count10);
      ((Buffer)buffer).clear();
      int count11 = count9 * count10;
      if (countArray2.length >= count11 + count10) {
         ((Buffer)buffer.put(countArray2, count11, count10)).position(0).limit(count10);
         GL11.glTexSubImage2D(3553, 0, count7, count8, count5, count6, 32993, 33639, buffer);
         ((Buffer)buffer).clear();
      }
   }

   public static int[][] resolveIndexArray(int[][] countMatrix, int count5, int count6, int count7) {
      int length = countMatrix.length;
      int[][] countMatrix2 = new int[length][];

      for (int index = 0; index < length; index++) {
         int[] countArray2 = countMatrix[index];
         if (countArray2 != null) {
            int count8 = (count5 >> index) * (count6 >> index);
            int[] countArray3 = new int[count8 * 3];
            countMatrix2[index] = countArray3;
            int count9 = countArray2.length / 3;
            int count10 = count8 * count7;
            System.arraycopy(countArray2, count10, countArray3, 0, count8);
            int count11 = count10 + count9;
            int count12 = 0 + count8;
            System.arraycopy(countArray2, count11, countArray3, count12, count8);
            int count13 = count11 + count9;
            int count14 = count12 + count8;
            System.arraycopy(countArray2, count13, countArray3, count14, count8);
         }
      }

      return countMatrix2;
   }

   public static int[] resolveIndexArrayByIndex(int count5) {
      int[] countArray2 = new int[count5 * 3];
      Arrays.fill(countArray2, 0, count5, 0);
      Arrays.fill(countArray2, count5, count5 * 2, -8421377);
      Arrays.fill(countArray2, count5 * 2, count5 * 3, 0);
      return countArray2;
   }

   public static void applyIndexArray2(int[] countArray2, int count5, int count6, int count7) {
   }

   public static IntBuffer resolveIntBuffer(int count5, int count6) {
      resolveIndexArray(count5);
      resolveIntBuffer(count5);
      Arrays.fill(countArray, 0, count5, count6);
      intBuffer.put(countArray, 0, count5);
      return intBuffer;
   }

   public static BufferedImage resolveBufferedImage(ResourceLocation location) {
      try {
         if (!BuildManager.matchesResourceLocation3(location)) {
            return null;
         } else {
            InputStream inputStream = BuildManager.resolveInputStream(location);
            if (inputStream == null) {
               return null;
            } else {
               BufferedImage bufferedImage = ImageIO.read(inputStream);
               inputStream.close();
               return bufferedImage;
            }
         }
      } catch (IOException ioException) {
         return null;
      }
   }

   public static int[][] resolveIndexArray(int count5, int count6, int[][] countMatrix) {
      for (int index = 1; index <= count5; index++) {
         if (countMatrix[index] == null) {
            int count7 = count6 >> index;
            int count8 = count7 * 2;
            int[] countArray2 = countMatrix[index - 1];
            int[] countArray3 = countMatrix[index] = new int[count7 * count7];

            for (int index2 = 0; index2 < count7; index2++) {
               for (int index3 = 0; index3 < count7; index3++) {
                  int count9 = index2 * 2 * count8 + index3 * 2;
                  countArray3[index2 * count7 + index3] = computeCount(countArray2[count9], countArray2[count9 + 1], countArray2[count9 + count8], countArray2[count9 + count8 + 1]);
               }
            }
         }
      }

      return countMatrix;
   }

   public static void applyListenerAdapter26(ListenerAdapter26 listenerAdapter, int count5) {
      DistanceTransformer distanceTransformer = listenerAdapter.distanceTransformer;
      if (distanceTransformer != null) {
         listenerAdapter.distanceTransformer = null;
         map.remove(distanceTransformer.count);
         GlStateManager.deleteTexture(distanceTransformer.count3);
         GlStateManager.deleteTexture(distanceTransformer.count2);
         if (distanceTransformer.count != count5) {
            ShadersManager.applyText3("Error : MultiTexID.base mismatch: " + distanceTransformer.count + ", texid: " + count5);
            GlStateManager.deleteTexture(distanceTransformer.count);
         }
      }
   }

   public static int[][] resolveIndexArray(ListenerAdapter27 listenerAdapter, String text, int count5, int count6, int count7, boolean flag, int count8) {
      DistanceTransformer distanceTransformer = listenerAdapter.distanceTransformer;
      int[][] countMatrix = new int[count7][];
      int[] countArray2;
      countMatrix[0] = countArray2 = new int[count5 * count6];
      boolean flag2 = false;
      BufferedImage bufferedImage = resolveBufferedImage(listenerAdapter.resolveResourceLocation(new ResourceLocation(text)));
      if (bufferedImage != null) {
         int width = bufferedImage.getWidth();
         bufferedImage.getHeight();
         if (width + (flag ? 16 : 0) == count5) {
            flag2 = true;
            bufferedImage.getRGB(0, 0, width, width, countArray2, 0, width);
         }
      }

      if (!flag2) {
         Arrays.fill(countArray2, count8);
      }

      GlStateManager.setActiveTexture(distanceTransformer.count2);
      return resolveIndexArray(countMatrix.length - 1, count5, countMatrix);
   }

   public static void applyIndexArrayByIndexArray(int[] countArray2, int count5, int count6, int count7) {
      Math.min(count6, count7);
      int count8 = count5;
      int count9 = count6;
      int count10 = count7;
      int count11 = 0;
      int count12 = 0;

      int count13;
      for (count13 = 0; count9 > 1 && count10 > 1; count8 = count11) {
         count11 = count8 + count9 * count10;
         count12 = count9 / 2;
         count10 /= 2;

         for (int index = 0; index < count10; index++) {
            int count14 = count11 + index * count12;
            int count15 = count8 + index * 2 * count9;

            for (int index2 = 0; index2 < count12; index2++) {
               countArray2[count14 + index2] = computeCount(
                  countArray2[count15 + index2 * 2], countArray2[count15 + index2 * 2 + 1], countArray2[count15 + count9 + index2 * 2], countArray2[count15 + count9 + index2 * 2 + 1]
               );
            }
         }

         count13++;
         count9 = count12;
      }

      while (count13 > 0) {
         int count16 = count6 >> --count13;
         int count17 = count7 >> count13;
         int count18 = count11 - count16 * count17;
         int count19 = count18;

         for (int index3 = 0; index3 < count17; index3++) {
            for (int index4 = 0; index4 < count16; index4++) {
               if (countArray2[count19] == 0) {
                  countArray2[count19] = countArray2[count11 + index3 / 2 * count12 + index4 / 2] & 16777215;
               }

               count19++;
            }
         }

         count11 = count18;
         count12 = count16;
      }
   }

   public static boolean matchesIndexArray(int[] countArray2, int count5, int count6) {
      int count7 = count5 * count6;
      if (countArray2[0] >>> 24 == 255 && countArray2[count7 - 1] == 0) {
         return true;
      } else {
         for (int index = 0; index < count7; index++) {
            int count8 = countArray2[index] >>> 24;
            if (count8 != 0 && count8 != 255) {
               return true;
            }
         }

         return false;
      }
   }

   public static DistanceTransformer resolveDistanceTransformer(ListenerAdapter26 listenerAdapter) {
      DistanceTransformer distanceTransformer = listenerAdapter.distanceTransformer;
      if (distanceTransformer == null) {
         int count5 = listenerAdapter.getCount();
         distanceTransformer = (DistanceTransformer)map.get(count5);
         if (distanceTransformer == null) {
            distanceTransformer = new DistanceTransformer(count5, GL11.glGenTextures(), GL11.glGenTextures());
            map.put(count5, distanceTransformer);
         }

         listenerAdapter.distanceTransformer = distanceTransformer;
      }

      return distanceTransformer;
   }

   public static void applyDistanceTransformer(DistanceTransformer distanceTransformer, int[] countArray2, int count5, int count6, boolean flag, boolean flag2) {
      int count7 = flag ? 9729 : 9728;
      int count8 = flag2 ? '\u812f' : 10497;
      int count9 = count5 * count6;
      IntBuffer buffer = resolveIntBuffer(count9);
      ((Buffer)buffer).clear();
      ((Buffer)buffer.put(countArray2, 0, count9)).position(0).limit(count9);
      GlStateManager.setActiveTexture(distanceTransformer.count);
      GL11.glTexImage2D(3553, 0, 6408, count5, count6, 0, 32993, 33639, buffer);
      GL11.glTexParameteri(3553, 10241, count7);
      GL11.glTexParameteri(3553, 10240, count7);
      GL11.glTexParameteri(3553, 10242, count8);
      GL11.glTexParameteri(3553, 10243, count8);
      ((Buffer)buffer.put(countArray2, count9, count9)).position(0).limit(count9);
      GlStateManager.setActiveTexture(distanceTransformer.count3);
      GL11.glTexImage2D(3553, 0, 6408, count5, count6, 0, 32993, 33639, buffer);
      GL11.glTexParameteri(3553, 10241, count7);
      GL11.glTexParameteri(3553, 10240, count7);
      GL11.glTexParameteri(3553, 10242, count8);
      GL11.glTexParameteri(3553, 10243, count8);
      ((Buffer)buffer.put(countArray2, count9 * 2, count9)).position(0).limit(count9);
      GlStateManager.setActiveTexture(distanceTransformer.count2);
      GL11.glTexImage2D(3553, 0, 6408, count5, count6, 0, 32993, 33639, buffer);
      GL11.glTexParameteri(3553, 10241, count7);
      GL11.glTexParameteri(3553, 10240, count7);
      GL11.glTexParameteri(3553, 10242, count8);
      GL11.glTexParameteri(3553, 10243, count8);
      GlStateManager.setActiveTexture(distanceTransformer.count);
   }

   public static int computeCountByIndex(int count5, int count6, int count7, int count8) {
      int count9 = count5 >>> 24 & 0xFF;
      int count10 = count6 >>> 24 & 0xFF;
      int count11 = count7 >>> 24 & 0xFF;
      int count12 = count8 >>> 24 & 0xFF;
      int count13 = count9 + count10 + count11 + count12;
      int count14 = (count13 + 2) / 4;
      if (count13 == 0) {
         count13 = 4;
         count9 = 1;
         count10 = 1;
         count11 = 1;
         count12 = 1;
      }

      int count15 = (count13 + 1) / 2;
      return count14 << 24
         | ((count5 >>> 16 & 0xFF) * count9 + (count6 >>> 16 & 0xFF) * count10 + (count7 >>> 16 & 0xFF) * count11 + (count8 >>> 16 & 0xFF) * count12 + count15) / count13 << 16
         | ((count5 >>> 8 & 0xFF) * count9 + (count6 >>> 8 & 0xFF) * count10 + (count7 >>> 8 & 0xFF) * count11 + (count8 >>> 8 & 0xFF) * count12 + count15) / count13 << 8
         | ((count5 >>> 0 & 0xFF) * count9 + (count6 >>> 0 & 0xFF) * count10 + (count7 >>> 0 & 0xFF) * count11 + (count8 >>> 0 & 0xFF) * count12 + count15) / count13 << 0;
   }

   public static BridgeListener getBridgeListener() {
      DynamicTexture dynamicTexture = new DynamicTexture(1, 1);
      dynamicTexture.getCountArray()[0] = -1;
      dynamicTexture.updateState2();
      return dynamicTexture;
   }

   public static int computeCountByBufferedImage(
      int count5, BufferedImage bufferedImage, boolean flag, boolean flag2, IResourceManager iResourceManager, ResourceLocation location, DistanceTransformer distanceTransformer
   ) {
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();
      int[] countArray2 = resolveIndexArray(width * height * 3);
      bufferedImage.getRGB(0, 0, width, height, countArray2, 0, width);
      applyIResourceManagerByIResourceManager(iResourceManager, location, width, height, countArray2);
      applyDistanceTransformer(distanceTransformer, countArray2, width, height, flag, flag2);
      return count5;
   }

   public static int[][] resolveIndexArray(ServiceStringEntry serviceStringEntry, int[][] countMatrix, int count5, int count6) {
      return countMatrix;
   }

   public static void applyIndexArray(int[] countArray2, int count5, int count6, int count7, int count8) {
      int count9 = 0;
      int count10 = count5;
      int count11 = count6;
      int count12 = count7;

      for (int index = count8; count10 > 0 && count11 > 0; index /= 2) {
         GL11.glCopyTexSubImage2D(3553, count9, count12, index, 0, 0, count10, count11);
         count9++;
         count10 /= 2;
         count11 /= 2;
         count12 /= 2;
      }
   }
}
