package client.util;

import augustus.modules.render.XRay;
import client.render.EmissiveTexturesManager;
import client.setting.XRayHideType;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityAirportal;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnchanttable;
import net.minecraft.tileentity.TileEntityEnderchest;
import net.minecraft.tileentity.TileEntityMobspawner;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class StandardMapEntry {
   public Map map = Maps.newHashMap();
   public static StandardMapEntry standardMapEntry = new StandardMapEntry();
   public FontRenderer fontRenderer;
   public TileEntity tileEntity;
   public Tessellator tessellator = new Tessellator(2097152);
   public boolean enabled = false;
   public static double value5;
   public static double value3;
   public static double value4;
   public TextureManager textureManager;
   public World world;
   public Entity entity;
   public float factor2;
   public float factor;
   public double value2;
   public double value6;
   public double value;

   public void setWorld(World world2) {
      this.world = world2;
   }

   public void applyTileEntity(TileEntity tileEntity2, float factor3, int count) {
      if (tileEntity2.computeValue(this.value2, this.value6, this.value) < tileEntity2.getValue()) {
         if (XRay.activeState3) {
            Block block = tileEntity2.getBlock();
            BlockPos blockPos = tileEntity2.getBlockPos();
            Object entry = null;
            if (XRay.getXRayHideType(block, (IBlockState)entry, blockPos) == XRayHideType.HIDE) {
               return;
            }
         }

         RenderHelper.enableStandardItemLighting();
         int count2 = this.world.computeCount(tileEntity2.getBlockPos(), 0);
         int count3 = count2 % 65536;
         int count4 = count2 / 65536;
         OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count3 / 1.0F, (float)count4 / 1.0F);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         BlockPos blockPos2 = tileEntity2.getBlockPos();
         if (!this.world.matchesBlockPos2(blockPos2, false)) {
            return;
         }

         if (EmissiveTexturesManager.isEnabled2()) {
            EmissiveTexturesManager.renderElement4();
         }

         this.applyTileEntity(tileEntity2, (double)blockPos2.getX() - value5, (double)blockPos2.getY() - value3, (double)blockPos2.getZ() - value4, factor3, count);
         if (EmissiveTexturesManager.isEnabled2()) {
            if (EmissiveTexturesManager.isEnabled4()) {
               EmissiveTexturesManager.renderElement();
               this.applyTileEntity(tileEntity2, (double)blockPos2.getX() - value5, (double)blockPos2.getY() - value3, (double)blockPos2.getZ() - value4, factor3, count);
               EmissiveTexturesManager.renderElement3();
            }

            EmissiveTexturesManager.renderElement2();
         }
      }
   }

   public void applyWorld(World world2, TextureManager textureManager2, FontRenderer fontRenderer2, Entity entity2, float factor3) {
      if (this.world != world2) {
         this.setWorld(world2);
      }

      this.textureManager = textureManager2;
      this.entity = entity2;
      this.fontRenderer = fontRenderer2;
      this.factor2 = entity2.prevRotationYaw + (entity2.rotationYaw - entity2.prevRotationYaw) * factor3;
      this.factor = entity2.prevRotationPitch + (entity2.rotationPitch - entity2.prevRotationPitch) * factor3;
      this.value2 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)factor3;
      this.value6 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)factor3;
      this.value = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)factor3;
   }

   public void applyTileEntity(TileEntity tileEntity2, double posX, double posY, double posZ, float factor3) {
      this.applyTileEntity(tileEntity2, posX, posY, posZ, factor3, -1);
   }

   public ResourceLocationArrayHolder resolveResourceLocationArrayHolder(Class clazz) {
      ResourceLocationArrayHolder resourceLocationArrayHolder = (ResourceLocationArrayHolder)this.map.get(clazz);
      if (resourceLocationArrayHolder == null && clazz != TileEntity.class) {
         resourceLocationArrayHolder = this.resolveResourceLocationArrayHolder(clazz.getSuperclass());
         this.map.put(clazz, resourceLocationArrayHolder);
      }

      return resourceLocationArrayHolder;
   }

   public void updateState() {
      this.tessellator.getWorldRenderer().updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry5);
      this.enabled = true;
   }

   public void applyTileEntity(TileEntity tileEntity2, double posX, double posY, double posZ, float factor3, int count) {
      ResourceLocationArrayHolder resourceLocationArrayHolder = this.resolveResourceLocationArrayHolder(tileEntity2);
      if (resourceLocationArrayHolder != null) {
         try {
            this.tileEntity = tileEntity2;
            resourceLocationArrayHolder.applyTileEntity(tileEntity2, posX, posY, posZ, factor3, count);
            this.tileEntity = null;
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Rendering Block Entity");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Block Entity Details");
            tileEntity2.applyCrashReportCategory(crashReportCategory);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }
   }

   public void applyIndex(int count) {
      this.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.glStateAction3(770, 771);
      GlStateManager.disableBlend();
      GlStateManager.disableAlpha();
      if (Minecraft.isActive()) {
         GlStateManager.glShadeModel(7425);
      } else {
         GlStateManager.glShadeModel(7424);
      }

      if (count > 0) {
         this.tessellator.getWorldRenderer().applyFloatByFloat((float)value5, (float)value3, (float)value4);
      }

      this.tessellator.updateState();
      RenderHelper.enableStandardItemLighting();
      this.enabled = false;
   }

   public FontRenderer getFontRendererAsFontRenderer() {
      return this.fontRenderer;
   }

   public ResourceLocationArrayHolder resolveResourceLocationArrayHolder(TileEntity tileEntity2) {
      return tileEntity2 != null && !tileEntity2.isEnabled() ? this.resolveResourceLocationArrayHolder(tileEntity2.getClass()) : null;
   }

   private StandardMapEntry() {
      this.map.put(TileEntitySign.class, new SecondaryResourceLocationArrayHandler());
      this.map.put(TileEntityMobspawner.class, new AuxiliaryResourceLocationArrayHandler());
      this.map.put(TileEntityPiston.class, new StandardResourceLocationArrayHandler());
      this.map.put(TileEntityChest.class, new ExtendedResourceLocationArrayHandler());
      this.map.put(TileEntityEnderchest.class, new AdvancedResourceLocationArrayHandler());
      this.map.put(TileEntityEnchanttable.class, new InternalResourceLocationArrayHandler());
      this.map.put(TileEntityAirportal.class, new ResourceLocationArrayHandler());
      this.map.put(TileEntityBeacon.class, new CustomResourceLocationArrayHandler());
      this.map.put(TileEntitySkull.class, new BaseResourceLocationArrayHandler());
      this.map.put(TileEntityBanner.class, new TertiaryResourceLocationArrayHandler());
      Iterator iterator = this.map.values().iterator();

      while (iterator.hasNext()) {
         ((ResourceLocationArrayHolder)iterator.next()).setStandardMapEntry(this);
      }
   }
}
