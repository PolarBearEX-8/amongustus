package client.util;

import client.render.WorldRenderer;
import client.setting.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class MBSManager {
   public static Minecraft mc;
   public static GameSettings gameSettings;
   public static UNKNOWNManager uNKNOWNManager;
   public static boolean enabled = false;
   public static NativeLongEntry nativeLongEntry3 = new NativeLongEntry();
   public static NativeLongEntry nativeLongEntry = new NativeLongEntry();
   public static NativeLongEntry nativeLongEntry6 = new NativeLongEntry();
   public static NativeLongEntry nativeLongEntry7 = new NativeLongEntry();
   public static NativeLongEntry nativeLongEntry2 = new NativeLongEntry();
   public static NativeLongEntry nativeLongEntry5 = new NativeLongEntry();
   public static NativeLongEntry nativeLongEntry4 = new NativeLongEntry();
   public static long[] timestampArray2 = new long[512];
   public static long[] timestampArray7 = new long[512];
   public static long[] timestampArray3 = new long[512];
   public static long[] timestampArray4 = new long[512];
   public static long[] timestampArray6 = new long[512];
   public static long[] timestampArray8 = new long[512];
   public static long[] timestampArray5 = new long[512];
   public static long[] timestampArray = new long[512];
   public static boolean[] enabledArray = new boolean[512];
   public static int count = 0;
   public static long timestamp2 = -1L;
   public static long timestamp = 0L;

   public static boolean isEnabled() {
      return enabled;
   }

   public static void applyScaledResolutionByScaledResolution(ScaledResolution scaledResolution) {
      if (gameSettings != null && gameSettings.state80) {
         long timestamp3 = System.nanoTime();
         GlStateManager.glClear(256);
         GlStateManager.matrixMode(5889);
         GlStateManager.pushMatrix();
         GlStateManager.applyState6();
         GlStateManager.loadIdentity();
         GlStateManager.glStateAction(0.0, (double)mc.displayWidth, (double)mc.displayHeight, 0.0, 1000.0, 3000.0);
         GlStateManager.matrixMode(5888);
         GlStateManager.pushMatrix();
         GlStateManager.loadIdentity();
         GlStateManager.translate(0.0F, 0.0F, -2000.0F);
         GL11.glLineWidth(1.0F);
         GlStateManager.enableTexture2D();
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         worldRenderer.updateAuxiliaryLoggerEntry(1, DefaultVertexFormats.auxiliaryLoggerEntry3);

         for (int index = 0; index < timestampArray2.length; index++) {
            int count2 = (index - count & timestampArray2.length - 1) * 100 / timestampArray2.length + 155;
            float displayHeight = (float)mc.displayHeight;
            if (enabledArray[index]) {
               computeTimestamp(index, timestampArray2[index], count2, count2 / 2, 0, displayHeight, worldRenderer);
            } else {
               computeTimestamp(index, timestampArray2[index], count2, count2, count2, displayHeight, worldRenderer);
               float factor = displayHeight - (float)computeTimestamp(index, timestampArray[index], count2 / 2, count2 / 2, count2 / 2, displayHeight, worldRenderer);
               float factor2 = factor - (float)computeTimestamp(index, timestampArray5[index], 0, count2, 0, factor, worldRenderer);
               float factor3 = factor2 - (float)computeTimestamp(index, timestampArray8[index], count2, count2, 0, factor2, worldRenderer);
               float factor4 = factor3 - (float)computeTimestamp(index, timestampArray6[index], count2, 0, 0, factor3, worldRenderer);
               float factor5 = factor4 - (float)computeTimestamp(index, timestampArray4[index], count2, 0, count2, factor4, worldRenderer);
               float factor6 = factor5 - (float)computeTimestamp(index, timestampArray3[index], 0, 0, count2, factor5, worldRenderer);
               computeTimestamp(index, timestampArray7[index], 0, count2, count2, factor6, worldRenderer);
            }
         }

         computeTimestamp(0, timestampArray2.length, 33333333L, 196, 196, 196, (float)mc.displayHeight, worldRenderer);
         computeTimestamp(0, timestampArray2.length, 16666666L, 196, 196, 196, (float)mc.displayHeight, worldRenderer);
         tessellator.updateState();
         GlStateManager.disableTexture2D();
         int count3 = mc.displayHeight - 80;
         int count4 = mc.displayHeight - 160;
         mc.fontRendererObj.getIndex("30", 2, count4 + 1, -8947849);
         mc.fontRendererObj.getIndex("30", 1, count4, -3881788);
         mc.fontRendererObj.getIndex("60", 2, count3 + 1, -8947849);
         mc.fontRendererObj.getIndex("60", 1, count3, -3881788);
         GlStateManager.matrixMode(5889);
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.disableTexture2D();
         float factor7 = BuildManager.computeFactor(1.0F - (float)((double)(System.currentTimeMillis() - CoreLongEntry.getTimestamp4()) / 1000.0), 0.0F, 1.0F);
         int count5 = (int)(170.0F + factor7 * 85.0F);
         int count6 = (int)(100.0F + factor7 * 55.0F);
         int count7 = (int)(10.0F + factor7 * 10.0F);
         int count8 = count5 << 16 | count6 << 8 | count7;
         int count9 = 512 / scaledResolution.getScaleFactor() + 2;
         int count10 = mc.displayHeight / scaledResolution.getScaleFactor() - 8;
         StandardResourceLocationEntry.processAction(count9 - 1, count10 - 1, count9 + 50, count10 + 10, -1605349296);
         mc.fontRendererObj.getIndex(" " + CoreLongEntry.getTimestampAsTime() + " MB/s", count9, count10, count8);
         timestamp = System.nanoTime() - timestamp3;
      }
   }

   private static long computeTimestamp(int count2, int count3, long value, int count4, int count5, int count6, float factor, WorldRenderer worldRenderer) {
      long value2 = value / 200000L;
      if (value2 < 3L) {
         return 0L;
      } else {
         worldRenderer.getWorldRenderer((double)((float)count2 + 0.5F), (double)(factor - (float)value2 + 0.5F), 0.0)
            .resolveWorldRenderer(count4, count5, count6, 255)
            .updateState();
         worldRenderer.getWorldRenderer((double)((float)count3 + 0.5F), (double)(factor - (float)value2 + 0.5F), 0.0)
            .resolveWorldRenderer(count4, count5, count6, 255)
            .updateState();
         return value2;
      }
   }

   public static void updateState() {
      if (mc == null) {
         mc = Minecraft.getMinecraft();
         gameSettings = mc.gameSettings;
         uNKNOWNManager = mc.uNKNOWNManager;
      }

      if (gameSettings.state14 && gameSettings.state80) {
         enabled = true;
         long timestamp3 = System.nanoTime();
         if (timestamp2 == -1L) {
            timestamp2 = timestamp3;
         } else {
            int count2 = count & timestampArray2.length - 1;
            count++;
            boolean enabled2 = CoreLongEntry.isEnabled();
            timestampArray2[count2] = timestamp3 - timestamp2 - timestamp;
            timestampArray7[count2] = nativeLongEntry3.timestamp;
            timestampArray3[count2] = nativeLongEntry.timestamp;
            timestampArray4[count2] = nativeLongEntry6.timestamp;
            timestampArray6[count2] = nativeLongEntry7.timestamp;
            timestampArray8[count2] = nativeLongEntry2.timestamp;
            timestampArray5[count2] = nativeLongEntry5.timestamp;
            timestampArray[count2] = nativeLongEntry4.timestamp;
            enabledArray[count2] = enabled2;
            NativeLongEntry.applyNativeLongEntry(nativeLongEntry3);
            NativeLongEntry.applyNativeLongEntry(nativeLongEntry);
            NativeLongEntry.applyNativeLongEntry(nativeLongEntry2);
            NativeLongEntry.applyNativeLongEntry(nativeLongEntry7);
            NativeLongEntry.applyNativeLongEntry(nativeLongEntry6);
            NativeLongEntry.applyNativeLongEntry(nativeLongEntry5);
            NativeLongEntry.applyNativeLongEntry(nativeLongEntry4);
            timestamp2 = System.nanoTime();
         }
      } else {
         enabled = false;
         timestamp2 = -1L;
      }
   }

   private static long computeTimestamp(int count2, long value, int count3, int count4, int count5, float factor, WorldRenderer worldRenderer) {
      long value2 = value / 200000L;
      if (value2 < 3L) {
         return 0L;
      } else {
         worldRenderer.getWorldRenderer((double)((float)count2 + 0.5F), (double)(factor - (float)value2 + 0.5F), 0.0)
            .resolveWorldRenderer(count3, count4, count5, 255)
            .updateState();
         worldRenderer.getWorldRenderer((double)((float)count2 + 0.5F), (double)(factor + 0.5F), 0.0).resolveWorldRenderer(count3, count4, count5, 255).updateState();
         return value2;
      }
   }
}
