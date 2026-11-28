package net.augustus.modules.render;

import net.minecraft.util.LongHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.awt.Color;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.HashSet;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.events.EventChunkLoad;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.render.RenderUtil;
import net.augustus.events.listeners.ChunkLoadListener;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.EventBlockChange;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.BlockChangeListener;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.world.chunk.Chunk;
import org.lwjgl.opengl.GL11;

public class MiniMap extends Module implements TickListener, RenderListener, PacketListener, BlockChangeListener, LoadWorldListener, ChunkLoadListener {
   private final DoubleValue height;
   private final DoubleValue posX;
   private final DoubleValue posY;
   private final DoubleValue size;
   private final DoubleValue scale;
   private final BlurShadowSettings blurShadowSettings;
   private final Long2ObjectMap chunkMapCache;
   private int renderX;
   private int renderY;
   private int[][] pixelColors;
   private ByteBuffer textureBuffer;
   private int textureId;
   private int maxHeight;

   public MiniMap() {
       super("MiniMap", "TomTom", new Color(92, 214, 98), Categorys.RENDER);
      this.height = new DoubleValue(this, "Height", 255.0, 0.0, 255.0, 0);
      this.posX = new DoubleValue(this, "PosX", 0.0, 0.0, 100.0, 2);
      this.posY = new DoubleValue(this, "PosY", 20.0, 0.0, 100.0, 2);
      this.size = new DoubleValue(this, "Size", 200.0, 10.0, 400.0, 0);
      this.scale = new DoubleValue(this, "Scale", 100.0, 50.0, 400.0, 2);
      this.blurShadowSettings = (new BlurShadowSettings(this)).removeBlurSetting().withCustomShadowColor(Color.black);
      this.chunkMapCache = new Long2ObjectOpenHashMap();
      this.renderX = 5;
      this.renderY = 5;
      this.pixelColors = (int[][])null;
      this.textureBuffer = null;
      this.textureId = -1;
      this.maxHeight = 255;
   }

   @Override
   public void onEnable() {
      if (mc.theWorld != null) {
         this.scanLoadedChunks();
      }
   }

   private void scanLoadedChunks() {
      if (mc.theWorld.getChunkProvider() instanceof ChunkProviderClient) {
         ChunkProviderClient chunkProvider = (ChunkProviderClient)mc.theWorld.getChunkProvider();
         HashSet set = new HashSet();
         LongHashMap.Entry[] hashArray = chunkProvider.chunkMapping.hashArray;
         int length = hashArray.length;

         for (int i = 0; i < length; i++) {
            for (LongHashMap.Entry j = hashArray[i]; j != null; j = j.nextEntry) {
               if (set.add(j)) {
                  Chunk value = (Chunk)j.getValue();
                  this.scanChunkColumn(value.xPosition << 4, value.zPosition << 4);
               }
            }
         }
      }
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(55);
      this.toggleEventSubscription(56);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      int value = (int)this.size.getValue();
      this.renderX = (int)((double)(mc.displayWidth - (value + 2)) * (this.posX.getValue() / 100.0)) + 1;
      this.renderY = (int)((double)(mc.displayHeight - (value + 2)) * (this.posY.getValue() / 100.0)) + 1;
      BlurShadowManager.enableOneShotPass(false, this.blurShadowSettings.isShadowActive());
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderMinimapPass(sr, value, shaderRenderPass));
      RenderUtil.resetGlColorState();
   }

   @Override
   public void onBlockChange(EventBlockChange event) {
      if (mc.theWorld != null) {
         int x = event.getX();
         int y = event.getY();
         int z = event.getZ();
         int n = x & 15;
         int n2 = z & 15;
         int[][][] orCreateChunkData = this.getOrCreateChunkData(x, z);
         if (y >= orCreateChunkData[1][n][n2]) {
            MapColor solidMapColor = this.getSolidMapColor(event.getBlockState());
            if (solidMapColor == null) {
               Chunk chunkFromChunkCoords = mc.theWorld.getChunkFromChunkCoords(x, z);

               for (int i = Math.min(this.maxHeight, chunkFromChunkCoords.getPrecipitationHeightY(x, z)); i >= 0; i--) {
                  IBlockState blockStateFromChunk = mc.theWorld.getBlockStateFromChunk(chunkFromChunkCoords, x, i, z);
                  Block block = blockStateFromChunk.getBlock();
                  if (block.getMaterial() != Material.air && !(block instanceof BlockBarrier)) {
                     orCreateChunkData[1][n][n2] = i;
                     orCreateChunkData[0][n][n2] = blockStateFromChunk.getBlock().getMapColor(blockStateFromChunk).colorValue;
                     return;
                  }
               }

               orCreateChunkData[1][n][n2] = -1;
               orCreateChunkData[0][n][n2] = -8477711;
            } else {
               orCreateChunkData[1][n][n2] = y;
               orCreateChunkData[0][n][n2] = solidMapColor.colorValue;
            }
         }
      }
   }

   @Override
   public void onPostTick() {
      if (mc.thePlayer != null) {
         if ((int)this.height.getValue() != this.maxHeight && mc.thePlayer.ticksExisted % 5 == 0) {
            this.maxHeight = (int)this.height.getValue();
            this.clearMapCache();
            this.scanLoadedChunks();
         }

         this.updateMinimapTexture((int)this.scale.getValue());
      }
   }

   @Override
   public void onChunkLoad(EventChunkLoad event) {
      this.scanChunkColumn(event.getChunkX() << 4, event.getChunkZ() << 4);
   }

   private void scanChunkColumn(int n, int n2) {
      int[][][] orCreateChunkData = this.getOrCreateChunkData(n, n2);
      Chunk chunkFromBlockCoords = mc.theWorld.getChunkFromBlockCoords(n, n2);

      for (int i = 0; i < 16; i++) {
         outerLoop:
         for (int j = 0; j < 16; j++) {
            for (int k = Math.min(this.maxHeight, chunkFromBlockCoords.getPrecipitationHeightY(n + i, n2 + j)); k >= 0; k--) {
               IBlockState blockStateFromChunk = mc.theWorld.getBlockStateFromChunk(chunkFromBlockCoords, n + i, k, n2 + j);
               Block block = blockStateFromChunk.getBlock();
               if (block.getMaterial() != Material.air && !(block instanceof BlockBarrier)) {
                  orCreateChunkData[1][i][j] = k;
                  orCreateChunkData[0][i][j] = blockStateFromChunk.getBlock().getMapColor(blockStateFromChunk).colorValue;
                  continue outerLoop;
               }
            }

            orCreateChunkData[1][i][j] = -1;
            orCreateChunkData[0][i][j] = -8477711;
         }
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.clearMapCache();
   }

   private void updateMinimapTexture(int n) {
      if (this.textureId == -1) {
         this.textureId = this.createMapTexture(n, n);
      }

      if (this.pixelColors == null || this.textureBuffer == null || this.pixelColors.length != n) {
         this.allocateTextureBuffers(n);
         GlStateManager.deleteTexture(this.textureId);
         this.textureId = this.createMapTexture(n, n);
      }

      this.populatePixelGrid(n, this.pixelColors);
      int[][] pixelColors3 = this.pixelColors;
      int textureId2 = this.textureId;
      this.uploadPixelGridToTexture(this.textureBuffer, textureId2, pixelColors3, n, n);
   }

   public int[][][] getOrCreateChunkData(int n, int n2) {
      synchronized (this.chunkMapCache) {
         n >>= 4;
         n2 >>= 4;
         int[][][] intsArrs = (int[][][])this.chunkMapCache.get((long)n & 4294967295L | ((long)n2 & 4294967295L) << 32);
         if (intsArrs == null) {
            intsArrs = new int[2][16][16];
            this.putChunkData(n, n2, intsArrs);
         }

         return intsArrs;
      }
   }

   public void putChunkData(int n, int n2, int[][][] intsArrs) {
      this.chunkMapCache.put((long)n & 4294967295L | ((long)n2 & 4294967295L) << 32, intsArrs);
   }

   private MapColor getSolidMapColor(IBlockState state) {
      Block block = state.getBlock();
      return block.getMaterial() != Material.air && !(block instanceof BlockBarrier) ? block.getMapColor(state) : null;
   }

   private void drawMinimapTexture(int n) {
      GlStateManager.enableTexture2D();
      GlStateManager.bindTexture(this.textureId);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)this.renderX, (double)(this.renderY + n), 0.0).tex(0.0, 1.0).endVertex();
      worldRenderer.pos((double)(this.renderX + n), (double)(this.renderY + n), 0.0).tex(1.0, 1.0).endVertex();
      worldRenderer.pos((double)(this.renderX + n), (double)this.renderY, 0.0).tex(1.0, 0.0).endVertex();
      worldRenderer.pos((double)this.renderX, (double)this.renderY, 0.0).tex(0.0, 0.0).endVertex();
      instance.draw();
   }

   private void allocateTextureBuffers(int n) {
      this.pixelColors = new int[n][n];
      this.textureBuffer = ByteBuffer.allocateDirect(n * n * 4);
   }

   private void clearMapCache() {
      this.pixelColors = (int[][])null;
      this.textureBuffer = null;
      synchronized (this.chunkMapCache) {
         this.chunkMapCache.clear();
      }
   }

   private void populatePixelGrid(int n, int[][] intsArr) {
      int n2 = (int)mc.thePlayer.posX - n / 2;
      int n3 = (int)mc.thePlayer.posZ - n / 2;
      int n4 = n / 16 + 2;
      int n6 = -(n2 & 15);
      int n8 = -(n3 & 15);

      for (int i = -1; i < n4; i++) {
         for (int j = -1; j < n4; j++) {
            int n9 = n2 + i * 16;
            int n10 = n3 + j * 16;
            int[][] intsArr2 = this.getOrCreateChunkData(n9, n10)[0];

            for (int k = 0; k < 16; k++) {
               for (int m = 0; m < 16; m++) {
                  int n11 = intsArr2[k][m];
                  int n12 = n9 + k - n2 + n6;
                  int n13 = n10 + m - n3 + n8;
                  if (n12 < n && n12 >= 0 && n13 < n && n13 >= 0) {
                     intsArr[n12][n13] = n11;
                  }
               }
            }
         }
      }
   }

   private int createMapTexture(int n, int n2) {
      int n3 = GlStateManager.generateTexture();
      GlStateManager.bindTexture(n3);
      GL11.glTexImage2D(3553, 0, 6408, n, n2, 0, 6408, 5121, (ByteBuffer)null);
      GL11.glTexParameteri(3553, 10241, 9728);
      GL11.glTexParameteri(3553, 10240, 9728);
      return n3;
   }

   private void uploadPixelGridToTexture(ByteBuffer buf, int n, int[][] intsArr, int n2, int n3) {

      for (int i = 0; i < n3; i++) {
         for (int j = 0; j < n2; j++) {
            int n4 = intsArr[j][i];
            int n5 = n4 >> 16 & 0xFF;
            int n6 = n4 >> 8 & 0xFF;
            int n7 = n4 & 0xFF;
            int n8 = n4 != 0 ? 255 : 0;
            buf.put((byte)n5);
            buf.put((byte)n6);
            buf.put((byte)n7);
            buf.put((byte)n8);
            intsArr[j][i] = 0;
         }
      }

      ((Buffer)buf).flip();
      GlStateManager.bindTexture(n);
      GL11.glTexSubImage2D(3553, 0, 0, 0, n2, n3, 6408, 5121, buf);
      ((Buffer)buf).clear();
   }

   private void renderMinimapPass(ScaledResolution sr, int n, ShaderRenderPass shaderRenderPass) {
      GlStateManager.resetColor();
      RenderUtil.setGlColorRgba(255, 255, 255, 255);
      GlStateManager.pushMatrix();
      GlStateManager.scale(1.0F / (float)sr.getScaleFactor(), 1.0F / (float)sr.getScaleFactor(), 1.0F);
      int n2 = this.renderX - 1;
      int n3 = this.renderY - 1;
      int n4 = this.renderX + n + 1;
      int n5 = this.renderY + n + 1;
      RenderUtil.drawRect(n2, n3, n4, n5, this.blurShadowSettings.resolvePassColor(shaderRenderPass, -267119596));
      n4 = this.renderX + n;
      n5 = this.renderY + n;
      RenderUtil.drawRect(this.renderX, this.renderY, n4, n5, this.blurShadowSettings.resolvePassColor(shaderRenderPass, -15461356));
      RenderUtil.setGlColorRgba(255, 255, 255, 255);
      this.drawMinimapTexture(n);
      RenderUtil.drawRect(
         this.renderX + n / 2 - 1, this.renderY + n / 2 - 1, this.renderX + n / 2 + 1, this.renderY + n / 2 + 1, Color.RED.getRGB()
      );
      GlStateManager.popMatrix();
      GlStateManager.resetColor();
      RenderUtil.setGlColorRgba(255, 255, 255, 255);
   }

}
