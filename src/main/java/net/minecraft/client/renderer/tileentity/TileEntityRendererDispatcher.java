package net.minecraft.client.renderer.tileentity;

import com.google.common.collect.Maps;
import java.util.Map;
import net.augustus.modules.render.XRay;
import net.augustus.modules.render.helper.XRayRenderMode;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.optifine.EmissiveTextures;

public class TileEntityRendererDispatcher {
   public Map mapSpecialRenderers = Maps.newHashMap();
   public static TileEntityRendererDispatcher instance = new TileEntityRendererDispatcher();
   public FontRenderer fontRenderer;
   public TileEntity tileEntityRendered;
   private Tessellator batchBuffer = new Tessellator(2097152);
   private boolean drawingBatch = false;
   public static double staticPlayerX;
   public static double staticPlayerY;
   public static double staticPlayerZ;
   public TextureManager renderEngine;
   public World worldObj;
   public Entity entity;
   public float entityYaw;
   public float entityPitch;
   public double entityX;
   public double entityY;
   public double entityZ;

   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f) {
      this.renderTileEntityAt(tileEntity, d, d2, d3, f, -1);
   }

   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      TileEntitySpecialRenderer specialRenderer = this.getSpecialRenderer(tileEntity);
      if (specialRenderer != null) {
         try {
            this.tileEntityRendered = tileEntity;
            specialRenderer.renderTileEntityAt(tileEntity, d, d2, d3, f, n);
            this.tileEntityRendered = null;
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Rendering Block Entity");
            tileEntity.addInfoToCrashReport(crashReport.makeCategory("Block Entity Details"));
            throw new ReportedException(crashReport);
         }
      }
   }

   public void preDrawBatch() {
      this.batchBuffer.getWorldRenderer().begin(7, DefaultVertexFormats.BLOCK);
      this.drawingBatch = true;
   }

   public void setWorld(World world) {
      this.worldObj = world;
   }

   public TileEntitySpecialRenderer getSpecialRenderer(TileEntity tileEntity) {
      return tileEntity != null && !tileEntity.isInvalid() ? this.getSpecialRendererByClass(tileEntity.getClass()) : null;
   }

   public TileEntitySpecialRenderer getSpecialRendererByClass(Class clazz) {
      TileEntitySpecialRenderer renderer = (TileEntitySpecialRenderer)this.mapSpecialRenderers.get(clazz);
      if (renderer == null && clazz != TileEntity.class) {
         renderer = this.getSpecialRendererByClass(clazz.getSuperclass());
         this.mapSpecialRenderers.put(clazz, renderer);
      }

      return renderer;
   }

   public void drawBatch(int n) {
      this.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableBlend();
      GlStateManager.disableCull();
      if (Minecraft.isAmbientOcclusionEnabled()) {
         GlStateManager.shadeModel(7425);
      } else {
         GlStateManager.shadeModel(7424);
      }

      if (n > 0) {
         this.batchBuffer.getWorldRenderer().sortVertexData((float)staticPlayerX, (float)staticPlayerY, (float)staticPlayerZ);
      }

      this.batchBuffer.draw();
      RenderHelper.enableStandardItemLighting();
      this.drawingBatch = false;
   }

   public FontRenderer getFontRenderer() {
      return this.fontRenderer;
   }

   public void renderTileEntity(TileEntity tileEntity, float f, int n) {
      if (tileEntity.getDistanceSq(this.entityX, this.entityY, this.entityZ) < tileEntity.getMaxRenderDistanceSquared()) {
         if (XRay.enabled) {
            Block blockType = tileEntity.getBlockType();
            BlockPos pos = tileEntity.getPos();
            if (XRay.getBlockRenderMode(blockType, (IBlockState)null, pos) == XRayRenderMode.HIDE) {
               return;
            }
         }

         RenderHelper.enableStandardItemLighting();
         int combinedLight = this.worldObj.getCombinedLight(tileEntity.getPos(), 0);
         int n2 = combinedLight % 65536;
         int n3 = combinedLight / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n2 / 1.0F, (float)n3 / 1.0F);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         BlockPos pos2 = tileEntity.getPos();
         if (!this.worldObj.isBlockLoaded(pos2, false)) {
            return;
         }

         if (EmissiveTextures.isActive()) {
            EmissiveTextures.beginRender();
         }

         this.renderTileEntityAt(tileEntity, (double)pos2.getX() - staticPlayerX, (double)pos2.getY() - staticPlayerY, (double)pos2.getZ() - staticPlayerZ, f, n);
         if (EmissiveTextures.isActive()) {
            if (EmissiveTextures.hasEmissive()) {
               EmissiveTextures.beginRenderEmissive();
               this.renderTileEntityAt(tileEntity, (double)pos2.getX() - staticPlayerX, (double)pos2.getY() - staticPlayerY, (double)pos2.getZ() - staticPlayerZ, f, n);
               EmissiveTextures.endRenderEmissive();
            }

            EmissiveTextures.endRender();
         }
      }
   }

   private TileEntityRendererDispatcher() {
      this.mapSpecialRenderers.put(TileEntitySign.class, new TileEntitySignRenderer());
      this.mapSpecialRenderers.put(TileEntityMobSpawner.class, new TileEntityMobSpawnerRenderer());
      this.mapSpecialRenderers.put(TileEntityPiston.class, new TileEntityPistonRenderer());
      this.mapSpecialRenderers.put(TileEntityChest.class, new TileEntityChestRenderer());
      this.mapSpecialRenderers.put(TileEntityEnderChest.class, new TileEntityEnderChestRenderer());
      this.mapSpecialRenderers.put(TileEntityEnchantmentTable.class, new TileEntityEnchantmentTableRenderer());
      this.mapSpecialRenderers.put(TileEntityEndPortal.class, new TileEntityEndPortalRenderer());
      this.mapSpecialRenderers.put(TileEntityBeacon.class, new TileEntityBeaconRenderer());
      this.mapSpecialRenderers.put(TileEntitySkull.class, new TileEntitySkullRenderer());
      this.mapSpecialRenderers.put(TileEntityBanner.class, new TileEntityBannerRenderer());

      for (TileEntitySpecialRenderer renderer : (Iterable<TileEntitySpecialRenderer>)(this.mapSpecialRenderers.values())) {
         renderer.setRendererDispatcher(this);
      }
   }

   public void cacheActiveRenderInfo(World world, TextureManager textureManager, FontRenderer fontRenderer2, Entity entity2, float f) {
      if (this.worldObj != world) {
         this.setWorld(world);
      }

      this.renderEngine = textureManager;
      this.entity = entity2;
      this.fontRenderer = fontRenderer2;
      this.entityYaw = entity2.prevRotationYaw + (entity2.rotationYaw - entity2.prevRotationYaw) * f;
      this.entityPitch = entity2.prevRotationPitch + (entity2.rotationPitch - entity2.prevRotationPitch) * f;
      this.entityX = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)f;
      this.entityY = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)f;
      this.entityZ = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)f;
   }
}
