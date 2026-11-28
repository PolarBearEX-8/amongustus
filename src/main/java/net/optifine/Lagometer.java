package net.optifine;

import net.augustus.ui.GuiIngameHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.profiler.Profiler;
import net.optifine.config.Config;
import net.optifine.util.MemoryMonitor;
import org.lwjgl.opengl.GL11;

public class Lagometer {
   private static Minecraft mc;
   private static GameSettings gameSettings;
   private static Profiler profiler;
   public static boolean active = false;
   public static LagometerTimerNano timerTick = new LagometerTimerNano();
   public static LagometerTimerNano timerScheduledExecutables = new LagometerTimerNano();
   public static LagometerTimerNano timerChunkUpload = new LagometerTimerNano();
   public static LagometerTimerNano timerChunkUpdate = new LagometerTimerNano();
   public static LagometerTimerNano timerVisibility = new LagometerTimerNano();
   public static LagometerTimerNano timerTerrain = new LagometerTimerNano();
   public static LagometerTimerNano timerServer = new LagometerTimerNano();
   private static long[] timesFrame = new long[512];
   private static long[] timesTick = new long[512];
   private static long[] timesScheduledExecutables = new long[512];
   private static long[] timesChunkUpload = new long[512];
   private static long[] timesChunkUpdate = new long[512];
   private static long[] timesVisibility = new long[512];
   private static long[] timesTerrain = new long[512];
   private static long[] timesServer = new long[512];
   private static boolean[] gcs = new boolean[512];
   private static int numRecordedFrameTimes = 0;
   private static long prevFrameTimeNano = -1L;
   private static long renderTimeNano = 0L;

   private static long renderTimeDivider(int n, int n2, long l, int n3, int n4, int n5, float f, WorldRenderer worldRenderer) {
      long l2 = l / 200000L;
      if (l2 < 3L) {
         return 0L;
      } else {
         worldRenderer.pos((double)((float)n + 0.5F), (double)(f - (float)l2 + 0.5F), 0.0).color(n3, n4, n5, 255).endVertex();
         worldRenderer.pos((double)((float)n2 + 0.5F), (double)(f - (float)l2 + 0.5F), 0.0).color(n3, n4, n5, 255).endVertex();
         return l2;
      }
   }

   public static void updateLagometer() {
      if (mc == null) {
         mc = Minecraft.getMinecraft();
         gameSettings = mc.gameSettings;
         profiler = mc.mcProfiler;
      }

      if (gameSettings.showDebugInfo && gameSettings.ofLagometer) {
         active = true;
         long l = System.nanoTime();
         if (prevFrameTimeNano == -1L) {
            prevFrameTimeNano = l;
         } else {
            int n = numRecordedFrameTimes & timesFrame.length - 1;
            numRecordedFrameTimes++;
            boolean isGcEvent = MemoryMonitor.isGcEvent();
            timesFrame[n] = l - prevFrameTimeNano - renderTimeNano;
            timesTick[n] = timerTick.timeNano;
            timesScheduledExecutables[n] = timerScheduledExecutables.timeNano;
            timesChunkUpload[n] = timerChunkUpload.timeNano;
            timesChunkUpdate[n] = timerChunkUpdate.timeNano;
            timesVisibility[n] = timerVisibility.timeNano;
            timesTerrain[n] = timerTerrain.timeNano;
            timesServer[n] = timerServer.timeNano;
            gcs[n] = isGcEvent;
            LagometerTimerNano.reset(timerTick);
            LagometerTimerNano.reset(timerScheduledExecutables);
            LagometerTimerNano.reset(timerVisibility);
            LagometerTimerNano.reset(timerChunkUpdate);
            LagometerTimerNano.reset(timerChunkUpload);
            LagometerTimerNano.reset(timerTerrain);
            LagometerTimerNano.reset(timerServer);
            prevFrameTimeNano = System.nanoTime();
         }
      } else {
         active = false;
         prevFrameTimeNano = -1L;
      }
   }

   private static long renderTime(int n, long l, int n2, int n3, int n4, float f, WorldRenderer worldRenderer) {
      long l2 = l / 200000L;
      if (l2 < 3L) {
         return 0L;
      } else {
         worldRenderer.pos((double)((float)n + 0.5F), (double)(f - (float)l2 + 0.5F), 0.0).color(n2, n3, n4, 255).endVertex();
         worldRenderer.pos((double)((float)n + 0.5F), (double)(f + 0.5F), 0.0).color(n2, n3, n4, 255).endVertex();
         return l2;
      }
   }

   public static void showLagometer(ScaledResolution sr) {
      if (gameSettings != null && gameSettings.ofLagometer) {
         long l = System.nanoTime();
         GlStateManager.clear(256);
         GlStateManager.matrixMode(5889);
         GlStateManager.pushMatrix();
         GlStateManager.enableColorMaterial();
         GlStateManager.loadIdentity();
         GlStateManager.ortho(0.0, (double)mc.displayWidth, (double)mc.displayHeight, 0.0, 1000.0, 3000.0);
         GlStateManager.matrixMode(5888);
         GlStateManager.pushMatrix();
         GlStateManager.loadIdentity();
         GlStateManager.translate(0.0F, 0.0F, -2000.0F);
         GL11.glLineWidth(1.0F);
         GlStateManager.disableTexture2D();
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         worldRenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);

         for (int i = 0; i < timesFrame.length; i++) {
            int n = (i - numRecordedFrameTimes & timesFrame.length - 1) * 100 / timesFrame.length;
            n += 155;
            float displayHeight = (float)mc.displayHeight;
            long l2 = 0L;
            if (gcs[i]) {
               renderTime(i, timesFrame[i], n, n / 2, 0, displayHeight, worldRenderer);
            } else {
               renderTime(i, timesFrame[i], n, n, n, displayHeight, worldRenderer);
               displayHeight -= (float)renderTime(i, timesServer[i], n / 2, n / 2, n / 2, displayHeight, worldRenderer);
               displayHeight -= (float)renderTime(i, timesTerrain[i], 0, n, 0, displayHeight, worldRenderer);
               displayHeight -= (float)renderTime(i, timesVisibility[i], n, n, 0, displayHeight, worldRenderer);
               displayHeight -= (float)renderTime(i, timesChunkUpdate[i], n, 0, 0, displayHeight, worldRenderer);
               displayHeight -= (float)renderTime(i, timesChunkUpload[i], n, 0, n, displayHeight, worldRenderer);
               displayHeight -= (float)renderTime(i, timesScheduledExecutables[i], 0, 0, n, displayHeight, worldRenderer);
               float f = displayHeight - (float)renderTime(i, timesTick[i], 0, n, n, displayHeight, worldRenderer);
            }
         }

         renderTimeDivider(0, timesFrame.length, 33333333L, 196, 196, 196, (float)mc.displayHeight, worldRenderer);
         renderTimeDivider(0, timesFrame.length, 16666666L, 196, 196, 196, (float)mc.displayHeight, worldRenderer);
         instance.draw();
         GlStateManager.enableTexture2D();
         int n2 = mc.displayHeight - 80;
         int n3 = mc.displayHeight - 160;
         mc.fontRendererObj.drawString("30", 2, n3 + 1, -8947849);
         mc.fontRendererObj.drawString("30", 1, n3, -3881788);
         mc.fontRendererObj.drawString("60", 2, n2 + 1, -8947849);
         mc.fontRendererObj.drawString("60", 1, n2, -3881788);
         GlStateManager.matrixMode(5889);
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.enableTexture2D();
         float f2 = 1.0F - (float)((double)(System.currentTimeMillis() - MemoryMonitor.getStartTimeMs()) / 1000.0);
         f2 = Config.limit3(f2, 0.0F, 1.0F);
         int n7 = ((int)(170.0F + f2 * 85.0F)) << 16 | ((int)(100.0F + f2 * 55.0F)) << 8 | ((int)(10.0F + f2 * 10.0F));
         int n8 = 512 / sr.getScaleFactor() + 2;
         int n9 = mc.displayHeight / sr.getScaleFactor() - 8;
         GuiIngameHook ingameGUI = mc.ingameGUI;
         GuiIngame.drawRect(n8 - 1, n9 - 1, n8 + 50, n9 + 10, -1605349296);
         mc.fontRendererObj.drawString(" " + MemoryMonitor.getAllocationRateMb() + " MB/s", n8, n9, n7);
         renderTimeNano = System.nanoTime() - l;
      }
   }

   public static boolean isActive() {
      return active;
   }
}
