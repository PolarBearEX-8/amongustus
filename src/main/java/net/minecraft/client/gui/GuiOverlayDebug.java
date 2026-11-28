package net.minecraft.client.gui;

import net.minecraft.util.MovingObjectPosition;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import net.optifine.SmartAnimations;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.FrameTimer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.optifine.TextureAnimations;
import net.optifine.config.Config;
import net.optifine.util.MemoryMonitor;
import net.optifine.util.NativeMemory;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class GuiOverlayDebug extends Gui {
   private final Minecraft mc;
   private final FontRenderer fontRenderer;
   private String debugOF = null;
   private List debugInfoLeft = null;
   private List debugInfoRight = null;
   private long updateInfoLeftTimeMs = 0L;
   private long updateInfoRightTimeMs = 0L;

   public List getDebugInfoRight() {
      long l = Runtime.getRuntime().maxMemory();
      long l2 = Runtime.getRuntime().totalMemory();
      long l4 = l2 - Runtime.getRuntime().freeMemory();
      ArrayList list = Lists.newArrayList(
         new String[]{
            String.format("Java: %s %dbit", System.getProperty("java.version"), this.mc.isJava64bit() ? 64 : 32),
            String.format("Mem: % 2d%% %03d/%03dMB", l4 * 100L / l, bytesToMb(l4), bytesToMb(l)),
            String.format("Allocated: % 2d%% %03dMB", l2 * 100L / l, bytesToMb(l2)),
            "",
            String.format("CPU: %s", OpenGlHelper.getCpu()),
            "",
            String.format("Display: %dx%d (%s)", Display.getWidth(), Display.getHeight(), GL11.glGetString(7936)),
            GL11.glGetString(7937),
            GL11.glGetString(7938)
         }
      );
      long bufferAllocated = NativeMemory.getBufferAllocated();
      long bufferMaximum = NativeMemory.getBufferMaximum();
      list.add(4, "Native: " + bytesToMb(bufferAllocated) + "/" + bytesToMb(bufferMaximum) + "MB");
      list.set(5, "GC: " + MemoryMonitor.getAllocationRateMb() + "MB/s");
      if (!this.isReducedDebug() && this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.mc.objectMouseOver.getBlockPos() != null) {
         BlockPos blockPos = this.mc.objectMouseOver.getBlockPos();
         IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
         if (this.mc.theWorld.getWorldType() != WorldType.DEBUG_WORLD) {
            blockState = blockState.getBlock().getActualState(blockState, this.mc.theWorld, blockPos.getX(), blockPos.getY(), blockPos.getZ());
         }

         list.add("");
         list.add(String.valueOf(Block.blockRegistry.getNameForObject(blockState.getBlock())));
         UnmodifiableIterator it = blockState.getProperties().entrySet().iterator();

         while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            Object str2 = ((Comparable)entry.getValue()).toString();
            if (entry.getValue() == Boolean.TRUE) {
               str2 = EnumChatFormatting.GREEN.toString() + str2;
            } else if (entry.getValue() == Boolean.FALSE) {
               str2 = EnumChatFormatting.RED.toString() + str2;
            }

            list.add(((IProperty)entry.getKey()).getName() + ": " + str2);
         }
      }

      return list;
   }

   public void renderDebugInfo(ScaledResolution sr) {
      this.mc.mcProfiler.startSection("debug");
      GlStateManager.pushMatrix();
      this.renderDebugInfoLeft();
      this.renderDebugInfoRight(sr);
      GlStateManager.popMatrix();
      if (this.mc.gameSettings.showLagometer && !this.mc.gameSettings.ofLagometer) {
         this.renderLagometer();
      }

      this.mc.mcProfiler.endSection();
   }

   private boolean isReducedDebug() {
      return this.mc.thePlayer.hasReducedDebug() || this.mc.gameSettings.reducedDebugInfo;
   }

   private int getFrameColor(int n, int n2, int n3, int n4) {
      return n < n3 ? this.blendColors(-16711936, -256, (float)n / (float)n3) : this.blendColors(-256, -65536, (float)(n - n3) / (float)(n4 - n3));
   }

   public void renderDebugInfoLeft() {
      List debugInfoLeft2 = this.debugInfoLeft;
      if (debugInfoLeft2 == null || System.currentTimeMillis() > this.updateInfoLeftTimeMs) {
         debugInfoLeft2 = this.call();
         this.debugInfoLeft = debugInfoLeft2;
         this.updateInfoLeftTimeMs = System.currentTimeMillis() + 100L;
      }

      for (int i = 0; i < debugInfoLeft2.size(); i++) {
         String str = (String)debugInfoLeft2.get(i);
         if (!Strings.isNullOrEmpty(str)) {
            int fontheight = this.fontRenderer.FONT_HEIGHT;
            int stringWidth = this.fontRenderer.getStringWidth(str);
            int n = 2 + fontheight * i;
            drawRect(1, n - 1, 2 + stringWidth + 1, n + fontheight - 1, -1873784752);
            this.fontRenderer.drawString(str, 2, n, 14737632);
         }
      }
   }

   private static long bytesToMb(long l) {
      return l / 1024L / 1024L;
   }

   public List call() {
      BlockPos pos = new BlockPos(
         this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ
      );
      if (!Objects.equals(this.mc.debug, this.debugOF)) {
         StringBuffer sb = new StringBuffer(this.mc.debug);
         int fpsMin = Config.getFpsMin();
         int n = this.mc.debug.indexOf(" fps ");
         if (n >= 0) {
            sb.insert(n, "/" + fpsMin);
         }

         if (Config.isSmoothFps()) {
            sb.append(" sf");
         }

         if (Config.isFastRender()) {
            sb.append(" fr");
         }

         if (Config.isAnisotropicFiltering()) {
            sb.append(" af");
         }

         if (Config.isAntialiasing()) {
            sb.append(" aa");
         }

         if (Config.isRenderRegions()) {
            sb.append(" reg");
         }

         if (Config.isShaderPackLoaded()) {
            sb.append(" sh");
         }

         this.mc.debug = sb.toString();
         this.debugOF = this.mc.debug;
      }

      StringBuilder sb2 = new StringBuilder();
      TextureMap textureMap = Config.getTextureMap();
      sb2.append(", A: ");
      if (SmartAnimations.isActive()) {
         sb2.append(textureMap.getCountAnimationsActive() + TextureAnimations.getCountAnimationsActive());
         sb2.append("/");
      }

      sb2.append(textureMap.getCountAnimations() + TextureAnimations.getCountAnimations());
      String str2 = sb2.toString();
      if (this.isReducedDebug()) {
         return Lists.newArrayList(
            new String[]{
               "Minecraft 1.8.9 (" + this.mc.getVersion() + "/" + ClientBrandRetriever.getClientModName() + ")",
               this.mc.debug,
               this.mc.renderGlobal.getDebugInfoRenders(),
               this.mc.renderGlobal.getDebugInfoEntities(),
               "P: " + this.mc.effectRenderer.getStatistics() + ". T: " + this.mc.theWorld.getDebugLoadedEntities() + str2,
               this.mc.theWorld.getProviderName(),
               "",
               String.format("Chunk-relative: %d %d %d", pos.getX() & 15, pos.getY() & 15, pos.getZ() & 15)
            }
         );
      } else {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         short s = 31979;
         EnumFacing horizontalFacing = renderViewEntity.getHorizontalFacing();
         String str3 = "Invalid";
         switch (horizontalFacing) {
            case NORTH:
               str3 = "Towards negative Z";
               break;
            case SOUTH:
               str3 = "Towards positive Z";
               break;
            case WEST:
               str3 = "Towards negative X";
               break;
            case EAST:
               str3 = "Towards positive X";
         }

         ArrayList list = Lists.newArrayList(
            new String[]{
               "Minecraft 1.8.9 (" + this.mc.getVersion() + "/" + ClientBrandRetriever.getClientModName() + ")",
               this.mc.debug,
               this.mc.renderGlobal.getDebugInfoRenders(),
               this.mc.renderGlobal.getDebugInfoEntities(),
               "P: " + this.mc.effectRenderer.getStatistics() + ". T: " + this.mc.theWorld.getDebugLoadedEntities() + str2,
               this.mc.theWorld.getProviderName(),
               "",
               String.format(
                  "XYZ: %.3f / %.5f / %.3f",
                  this.mc.getRenderViewEntity().posX,
                  this.mc.getRenderViewEntity().getEntityBoundingBox().minY,
                  this.mc.getRenderViewEntity().posZ
               ),
               String.format("Block: %d %d %d", pos.getX(), pos.getY(), pos.getZ()),
               String.format(
                  "Chunk: %d %d %d in %d %d %d", pos.getX() & 15, pos.getY() & 15, pos.getZ() & 15, pos.getX() >> 4, pos.getY() >> 4, pos.getZ() >> 4
               ),
               String.format(
                  "Facing: %s (%s) (%.1f / %.1f)",
                  horizontalFacing,
                  str3,
                  MathHelper.wrapAngleTo180_float(renderViewEntity.rotationYaw),
                  MathHelper.wrapAngleTo180_float(renderViewEntity.rotationPitch)
               )
            }
         );
         if (this.mc.theWorld != null && this.mc.theWorld.isBlockLoaded(pos)) {
            Chunk chunkFromBlockCoords = this.mc.theWorld.getChunkFromBlockCoords(pos);
            list.add("Biome: " + chunkFromBlockCoords.getBiome(pos.getX(), pos.getZ(), this.mc.theWorld.getWorldChunkManager()).biomeName);
            list.add(
               "Light: "
                  + chunkFromBlockCoords.getLightSubtracted(pos.getX(), pos.getY(), pos.getZ(), 0)
                  + " ("
                  + chunkFromBlockCoords.getLightFor(EnumSkyBlock.SKY, pos.getX(), pos.getY(), pos.getZ())
                  + " sky, "
                  + chunkFromBlockCoords.getLightFor(EnumSkyBlock.BLOCK, pos.getX(), pos.getY(), pos.getZ())
                  + " block)"
            );
            DifficultyInstance difficultyForLocation = this.mc.theWorld.getDifficultyForLocation(pos);
            if (this.mc.isIntegratedServerRunning() && this.mc.getIntegratedServer() != null) {
               EntityPlayerMP playerByUUID = this.mc.getIntegratedServer().getConfigurationManager().getPlayerByUUID(this.mc.thePlayer.getUniqueID());
               if (playerByUUID != null) {
                  DifficultyInstance difficultyAsync = this.mc.getIntegratedServer().getDifficultyAsync(playerByUUID.entityWorld, new BlockPos(playerByUUID));
                  if (difficultyAsync != null) {
                     difficultyForLocation = difficultyAsync;
                  }
               }
            }

            list.add(String.format("Local Difficulty: %.2f (Day %d)", difficultyForLocation.getAdditionalDifficulty(), this.mc.theWorld.getWorldTime() / 24000L));
         }

         if (this.mc.entityRenderer != null && this.mc.entityRenderer.isShaderActive()) {
            list.add("Shader: " + this.mc.entityRenderer.getShaderGroup().getShaderGroupName());
         }

         if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.mc.objectMouseOver.getBlockPos() != null) {
            BlockPos blockPos = this.mc.objectMouseOver.getBlockPos();
            list.add(String.format("Looking at: %d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ()));
         }

         return list;
      }
   }

   public GuiOverlayDebug(Minecraft mc2) {
      this.mc = mc2;
      this.fontRenderer = mc2.fontRendererObj;
   }

   private int blendColors(int n, int n2, float f) {
      int n3 = n >> 24 & 0xFF;
      int n4 = n >> 16 & 0xFF;
      int n5 = n >> 8 & 0xFF;
      int n6 = n & 0xFF;
      int n7 = n2 >> 24 & 0xFF;
      int n8 = n2 >> 16 & 0xFF;
      int n9 = n2 >> 8 & 0xFF;
      int n10 = n2 & 0xFF;
      return MathHelper.clamp_int((int)((float)n3 + (float)(n7 - n3) * f), 0, 255) << 24 | MathHelper.clamp_int((int)((float)n4 + (float)(n8 - n4) * f), 0, 255) << 16 | MathHelper.clamp_int((int)((float)n5 + (float)(n9 - n5) * f), 0, 255) << 8 | MathHelper.clamp_int((int)((float)n6 + (float)(n10 - n6) * f), 0, 255);
   }

   public void renderDebugInfoRight(ScaledResolution sr) {
      List debugInfoRight2 = this.debugInfoRight;
      if (debugInfoRight2 == null || System.currentTimeMillis() > this.updateInfoRightTimeMs) {
         debugInfoRight2 = this.getDebugInfoRight();
         this.debugInfoRight = debugInfoRight2;
         this.updateInfoRightTimeMs = System.currentTimeMillis() + 100L;
      }

      for (int i = 0; i < debugInfoRight2.size(); i++) {
         String str = (String)debugInfoRight2.get(i);
         if (!Strings.isNullOrEmpty(str)) {
            int fontheight = this.fontRenderer.FONT_HEIGHT;
            int stringWidth = this.fontRenderer.getStringWidth(str);
            int n = sr.getScaledWidth() - 2 - stringWidth;
            int n2 = 2 + fontheight * i;
            drawRect(n - 1, n2 - 1, n + stringWidth + 1, n2 + fontheight - 1, -1873784752);
            this.fontRenderer.drawString(str, n, n2, 14737632);
         }
      }
   }

   private void renderLagometer() {
      GlStateManager.disableDepth();
      FrameTimer frameTimer = this.mc.getFrameTimer();
      int lastIndex = frameTimer.getLastIndex();
      int index = frameTimer.getIndex();
      long[] frames = frameTimer.getFrames();
      ScaledResolution sr = new ScaledResolution(this.mc);
      int n = lastIndex;
      int n2 = 0;
      drawRect(0, sr.getScaledHeight() - 60, 240, sr.getScaledHeight(), -1873784752);

      while (n != index) {
         int lagometerValue = frameTimer.getLagometerValue(frames[n], 30);
         int frameColor = this.getFrameColor(MathHelper.clamp_int(lagometerValue, 0, 60), 0, 30, 60);
         this.drawVerticalLine(n2, sr.getScaledHeight(), sr.getScaledHeight() - lagometerValue, frameColor);
         n2++;
         n = frameTimer.parseIndex(n + 1);
      }

      drawRect(1, sr.getScaledHeight() - 30 + 1, 14, sr.getScaledHeight() - 30 + 10, -1873784752);
      this.fontRenderer.drawString("60", 2, sr.getScaledHeight() - 30 + 2, 14737632);
      this.drawHorizontalLine(0, 239, sr.getScaledHeight() - 30, -1);
      drawRect(1, sr.getScaledHeight() - 60 + 1, 14, sr.getScaledHeight() - 60 + 10, -1873784752);
      this.fontRenderer.drawString("30", 2, sr.getScaledHeight() - 60 + 2, 14737632);
      this.drawHorizontalLine(0, 239, sr.getScaledHeight() - 60, -1);
      this.drawHorizontalLine(0, 239, sr.getScaledHeight() - 1, -1);
      this.drawVerticalLine(0, sr.getScaledHeight() - 60, sr.getScaledHeight(), -1);
      this.drawVerticalLine(239, sr.getScaledHeight() - 60, sr.getScaledHeight(), -1);
      if (this.mc.gameSettings.limitFramerate <= 120) {
         this.drawHorizontalLine(0, 239, sr.getScaledHeight() - 60 + this.mc.gameSettings.limitFramerate / 2, -16711681);
      }

      GlStateManager.enableDepth();
   }

}
