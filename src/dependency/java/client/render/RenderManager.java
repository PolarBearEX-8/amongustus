package client.render;

import client.Client;
import client.ClientGlobals;
import client.event.PreMotionConsumer;
import client.module.Module;
import client.setting.GameSettings;
import client.util.AdapterManager;
import client.util.AdvancedResourceLocationEntryAdapter;
import client.util.AimProfiler;
import client.util.AimTracker;
import client.util.AltResourceLocationEntry;
import client.util.AltResourceLocationEntryAdapter;
import client.util.AuxiliaryHeadMainManager;
import client.util.AuxiliaryResourceLocationEntryAdapter;
import client.util.AuxiliaryResourceLocationEntryBridge;
import client.util.BaseResourceLocationEntry;
import client.util.BaseResourceLocationEntryAdapter;
import client.util.CommonResourceLocationEntry;
import client.util.CommonResourceLocationEntryAdapter;
import client.util.CompositeResourceLocationEntry;
import client.util.CustomResourceLocationEntryAdapter;
import client.util.DefaultResourceLocationEntry;
import client.util.DefaultVertexFormats;
import client.util.EnhancedResourceLocationEntry;
import client.util.EnhancedResourceLocationEntryAdapter;
import client.util.ExtendedResourceLocationEntryAdapter;
import client.util.ExtraResourceLocationEntry;
import client.util.ExtraResourceLocationEntryAdapter;
import client.util.GenericResourceLocationEntry;
import client.util.HUIHRVManager;
import client.util.HeadMainManager;
import client.util.IBlockState;
import client.util.InternalFloatEntryBridge;
import client.util.InternalResourceLocationEntryAdapter;
import client.util.InternalResourceLocationEntryBridge;
import client.util.ManagedResourceLocationEntry;
import client.util.NativeResourceLocationEntry;
import client.util.PreEndHandManager;
import client.util.PrimaryResourceLocationEntry;
import client.util.ResourceLocationEntryAdapter;
import client.util.ResourceLocationEntryBridge;
import client.util.ResourceLocationEntryHandler;
import client.util.SecondaryResourceLocationEntryAdapter;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SharedResourceLocationEntry;
import client.util.SmoothMonitor;
import client.util.SmoothObserver;
import client.util.SmoothTracker;
import client.util.SpecialHashMapEntry;
import client.util.SpecialResourceLocationEntry;
import client.util.SpecialResourceLocationEntryAdapter;
import client.util.StandardResourceLocationEntryAdapter;
import client.util.SupplementalFloatEntry;
import client.util.SupplementalResourceLocationEntry;
import client.util.SupplementalResourceLocationEntryAdapter;
import client.util.TargetSelector;
import client.util.TertiaryResourceLocationEntryAdapter;
import client.util.VectorCalculator;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import net.minecraft.block.BlockBed;
import net.minecraft.block.Blocks;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityBat;
import net.minecraft.entity.EntityBlaze;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.EntityCavespider;
import net.minecraft.entity.EntityChicken;
import net.minecraft.entity.EntityCow;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.entity.EntityEndercrystal;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.entity.EntityEndermite;
import net.minecraft.entity.EntityEyeofendersignal;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.entity.EntityFireball;
import net.minecraft.entity.EntityFireworksrocketentity;
import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityGiant;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLavaslime;
import net.minecraft.entity.EntityLeashknot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMushroomcow;
import net.minecraft.entity.EntityOzelot;
import net.minecraft.entity.EntityPainting;
import net.minecraft.entity.EntityPig;
import net.minecraft.entity.EntityPigzombie;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.entity.EntityRabbit;
import net.minecraft.entity.EntitySheep;
import net.minecraft.entity.EntitySilverfish;
import net.minecraft.entity.EntitySkeleton;
import net.minecraft.entity.EntitySlime;
import net.minecraft.entity.EntitySmallfireball;
import net.minecraft.entity.EntitySnowball;
import net.minecraft.entity.EntitySnowman;
import net.minecraft.entity.EntitySpider;
import net.minecraft.entity.EntitySquid;
import net.minecraft.entity.EntityThrownegg;
import net.minecraft.entity.EntityThrownenderpearl;
import net.minecraft.entity.EntityThrownexpbottle;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.EntityVillagergolem;
import net.minecraft.entity.EntityWitch;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.entity.EntityWitherskull;
import net.minecraft.entity.EntityWolf;
import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.EntityZombie;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.CompositeItemEntry;
import net.minecraft.item.DiamondDagger;
import net.minecraft.item.DiamondHammer;
import net.minecraft.item.DiamondOre;
import net.minecraft.item.DiamondRod;
import net.minecraft.item.DiamondTalisman;
import net.minecraft.item.DiamondWeapon;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RenderManager {
   public Map entryMap = Maps.newHashMap();
   public Map entryMap2 = Maps.newHashMap();
   public RenderPlayer rendererLivingEntityAdapter;
   public FontRenderer fontRenderer;
   public double renderPosX;
   public double renderPosY;
   public double renderPosZ;
   public TextureManager textureManager;
   public World world;
   public Entity entity;
   public Entity entity2;
   public float scaleFactor;
   public float scaleFactor7;
   public GameSettings gameSettings;
   public double viewerPosX;
   public double viewerPosZ;
   public double viewerPosY;
   public boolean state = false;
   public boolean state1 = true;
   public boolean state5 = false;
   public EntitySkinLocation resourceLocationEntry = null;

   public void setState1(boolean state2) {
      this.state1 = state2;
   }

   private void applyEntityByEntity(Entity entity3, double posX, double posY, double posZ, float factor, float factor2, boolean flag) {
      if (!PreEndHandManager.enabled12) {
         GlStateManager.depthMask(false);
         GlStateManager.enableTexture2D();
         GlStateManager.applyState3();
         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         float factor3 = entity3.width / 2.0F;
         AxisAlignedBB boundingBox = entity3.getBoundingBox2();
         RenderGlobal.applyAxisAlignedBB(
            new AxisAlignedBB(
               boundingBox.minX - entity3.posX + posX,
               boundingBox.minY - entity3.posY + posY,
               boundingBox.minZ - entity3.posZ + posZ,
               boundingBox.maxX - entity3.posX + posX,
               boundingBox.maxY - entity3.posY + posY,
               boundingBox.maxZ - entity3.posZ + posZ
            ),
            255,
            255,
            255,
            255
         );
         if (entity3 instanceof EntityLivingBase && !ClientGlobals.moduleManager.legacyAnimations.canExecute7()) {
            RenderGlobal.applyAxisAlignedBB(
               new AxisAlignedBB(
                  posX - (double)factor3,
                  posY + (double)entity3.getHeight2() - 0.01F,
                  posZ - (double)factor3,
                  posX + (double)factor3,
                  posY + (double)entity3.getHeight2() + 0.01F,
                  posZ + (double)factor3
               ),
               255,
               0,
               0,
               255
            );
         }

         if (flag && !ClientGlobals.moduleManager.legacyAnimations.canExecute7()) {
            Tessellator tessellator = Tessellator.getTessellator();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            Vec3 vec3 = entity3.getVec36(factor2);
            worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry3);
            worldRenderer.getWorldRenderer(posX, posY + (double)entity3.getHeight2(), posZ).resolveWorldRenderer(0, 0, 255, 255).updateState();
            worldRenderer.getWorldRenderer(posX + vec3.xCoord * 2.0, posY + (double)entity3.getHeight2() + vec3.yCoord * 2.0, posZ + vec3.zCoord * 2.0)
               .resolveWorldRenderer(0, 0, 255, 255)
               .updateState();
            tessellator.updateState();
         }

         GlStateManager.disableTexture2D();
         GlStateManager.applyState13();
         GlStateManager.enableLighting();
         GlStateManager.enableBlend();
         GlStateManager.depthMask(true);
      }
   }

   public void setState5(boolean state2) {
      this.state5 = state2;
   }

   public void applyWorldByWorld(World world2, FontRenderer fontRenderer2, Entity entity3, Entity entity4, GameSettings gameSettings2, float factor) {
      this.world = world2;
      this.gameSettings = gameSettings2;
      this.entity = entity3;
      this.entity2 = entity4;
      this.fontRenderer = fontRenderer2;
      if (entity3 instanceof EntityLivingBase && ((EntityLivingBase)entity3).isState5()) {
         IBlockState blockState = world2.getBlockState(new BlockPos(entity3));
         if (blockState.getBlock() == Blocks.bed) {
            int indexOffset = ((EnumFacing)blockState.getComparable(BlockBed.autoWalkPath)).getIndexOffset();
            this.scaleFactor = (float)(indexOffset * 90 + 180);
            this.scaleFactor7 = 0.0F;
         }
      } else {
         this.scaleFactor = entity3.prevRotationYaw + (entity3.rotationYaw - entity3.prevRotationYaw) * factor;
         this.scaleFactor7 = entity3.prevRotationPitch + (entity3.rotationPitch - entity3.prevRotationPitch) * factor;
      }

      if (gameSettings2.indexOffset64 == 2) {
         this.scaleFactor += 180.0F;
      }

      this.viewerPosX = entity3.lastTickPosX + (entity3.posX - entity3.lastTickPosX) * (double)factor;
      this.viewerPosZ = entity3.lastTickPosY + (entity3.posY - entity3.lastTickPosY) * (double)factor;
      this.viewerPosY = entity3.lastTickPosZ + (entity3.posZ - entity3.lastTickPosZ) * (double)factor;
   }

   public FontRenderer getFontRenderer() {
      return this.fontRenderer;
   }

   public void applyEntityByEntity(Entity entity3, float factor) {
      double offset = entity3.lastTickPosX + (entity3.posX - entity3.lastTickPosX) * (double)factor;
      double offset2 = entity3.lastTickPosY + (entity3.posY - entity3.lastTickPosY) * (double)factor;
      double offset3 = entity3.lastTickPosZ + (entity3.posZ - entity3.lastTickPosZ) * (double)factor;
      EntitySkinLocation entitySkinLocation = this.resolveEntitySkinLocation(entity3);
      if (entitySkinLocation != null && this.textureManager != null) {
         int count = entity3.computeCount(factor);
         int count2 = count % 65536;
         int count3 = count / 65536;
         OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count2 / 1.0F, (float)count3 / 1.0F);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         entitySkinLocation.applyEntity(entity3, offset - this.renderPosX, offset2 - this.renderPosY, offset3 - this.renderPosZ);
      }
   }

   public boolean isState1() {
      return this.state1;
   }

   public double getRenderPosX() {
      return this.renderPosX;
   }

   public boolean renderEntity(Entity entity3, double posX, double posY, double posZ, float factor, float factor2) {
      return this.matchesEntity(entity3, posX, posY, posZ, factor, factor2, false);
   }

   public Map unmodifiableMap() {
      return Collections.unmodifiableMap(this.entryMap2);
   }

   public void setScaleFactor(float scaleFactor2) {
      this.scaleFactor = scaleFactor2;
   }

   public EntitySkinLocation resolveEntitySkinLocation(Class clazz) {
      EntitySkinLocation entitySkinLocation = (EntitySkinLocation)this.entryMap.get(clazz);
      if (entitySkinLocation == null && clazz != Entity.class) {
         entitySkinLocation = this.resolveEntitySkinLocation(clazz.getSuperclass());
         this.entryMap.put(clazz, entitySkinLocation);
      }

      return entitySkinLocation;
   }

   public double getDistanceToCamera(double posX, double posY, double posZ) {
      double offset = posX - this.viewerPosX;
      double offset2 = posY - this.viewerPosZ;
      double offset3 = posZ - this.viewerPosY;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public void setRenderPosition(double posX, double posY, double posZ) {
      this.renderPosX = posX;
      this.renderPosY = posY;
      this.renderPosZ = posZ;
   }

   public boolean renderEntitySimple(Entity entity3, float factor) {
      return this.renderEntityWithPosYaw(entity3, factor, false);
   }

   public void setEntryMap(Map entryMap3) {
      this.entryMap = entryMap3;
   }

   public double getRenderPosY() {
      return this.renderPosY;
   }

   public boolean isState5() {
      return this.state5;
   }

   public boolean matchesEntityByEntity(Entity entity3, PreMotionConsumer preMotionConsumer, double posX, double posY, double posZ) {
      EntitySkinLocation entitySkinLocation = this.resolveEntitySkinLocation(entity3);
      return entitySkinLocation != null && entitySkinLocation.matchesEntityByEntity(entity3, preMotionConsumer, posX, posY, posZ);
   }

   public Map getEntryMap() {
      return this.entryMap;
   }

   public double getRenderPosZ() {
      return this.renderPosZ;
   }

   public void setWorld(World world2) {
      this.world = world2;
   }

   public boolean renderEntityWithPosYaw(Entity entity3, float factor, boolean flag) {
      if (entity3.ticksExisted == 0) {
         entity3.lastTickPosX = entity3.posX;
         entity3.lastTickPosY = entity3.posY;
         entity3.lastTickPosZ = entity3.posZ;
      }

      double offset = entity3.lastTickPosX + (entity3.posX - entity3.lastTickPosX) * (double)factor;
      double offset2 = entity3.lastTickPosY + (entity3.posY - entity3.lastTickPosY) * (double)factor;
      double offset3 = entity3.lastTickPosZ + (entity3.posZ - entity3.lastTickPosZ) * (double)factor;
      float factor2 = entity3.prevRotationYaw + (entity3.rotationYaw - entity3.prevRotationYaw) * factor;
      int count = entity3.computeCount(factor);
      if (entity3.canExecute2()) {
         count = 15728880;
      }

      int count2 = count % 65536;
      int count3 = count / 65536;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count2 / 1.0F, (float)count3 / 1.0F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      return this.matchesEntity(entity3, offset - this.renderPosX, offset2 - this.renderPosY, offset3 - this.renderPosZ, factor2, factor, flag);
   }

   public void setState(boolean state2) {
      this.state = state2;
   }

   public EntitySkinLocation resolveEntitySkinLocation(Entity entity3) {
      if (!(entity3 instanceof AbstractClientPlayer)) {
         return this.resolveEntitySkinLocation(entity3.getClass());
      } else {
         String text;
         if (((Module)ClientGlobals.moduleManager.customSkin).isToggled() && entity3 instanceof EntityPlayerSP) {
            text = ClientGlobals.moduleManager.customSkin.settingSkinMode.getSelected();
         } else {
            text = ((AbstractClientPlayer)entity3).getTextAsText();
         }

         RenderPlayer renderPlayer = (RenderPlayer)this.entryMap2.get(text);
         return renderPlayer != null ? renderPlayer : this.rendererLivingEntityAdapter;
      }
   }

   public boolean matchesEntity(Entity entity3, double posX, double posY, double posZ, float factor, float factor2, boolean flag) {
      EntitySkinLocation entitySkinLocation = null;
      Client.getClient().getClass();
      if (entity3 instanceof EntityPlayer && SpecialHashMapEntry.resolveNativeStringEntryByEntityPlayer((EntityPlayer)entity3, 2) != null) {
         return true;
      } else {
         try {
            entitySkinLocation = this.resolveEntitySkinLocation(entity3);
            if (entitySkinLocation != null && this.textureManager != null) {
               try {
                  if (entitySkinLocation instanceof RendererLivingEntity) {
                     ((RendererLivingEntity)entitySkinLocation).setEnabled4(this.state);
                  }

                  if (AdapterManager.isEnabled()) {
                     this.resourceLocationEntry = entitySkinLocation;
                  }

                  entitySkinLocation.applyEntity(entity3, posX, posY, posZ, factor, factor2);
               } catch (Throwable throwable) {
                  throw new SecondaryRuntimeExceptionAdapter(CrashReport.resolveCrashReport(throwable, "Rendering entity in world"));
               }

               try {
                  if (!this.state) {
                     entitySkinLocation.applyEntity7(entity3, posX, posY, posZ, factor, factor2);
                  }
               } catch (Throwable throwable2) {
                  throw new SecondaryRuntimeExceptionAdapter(CrashReport.resolveCrashReport(throwable2, "Post-rendering entity in world"));
               }

               boolean flag2 = true;
               if ((this.state5 || !(flag2 = !((Module)Client.getClient().getModuleManager().arrowHitboxes).isToggled()) && entity3 instanceof EntityArrow)
                  && !entity3.isActive12()
                  && !flag) {
                  try {
                     this.applyEntityByEntity(entity3, posX, posY, posZ, factor, factor2, flag2);
                  } catch (Throwable throwable3) {
                     throw new SecondaryRuntimeExceptionAdapter(CrashReport.resolveCrashReport(throwable3, "Rendering entity hitbox in world"));
                  }
               }
            } else if (this.textureManager != null) {
               return false;
            }

            return true;
         } catch (Throwable throwable4) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable4, "Rendering entity in world");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Entity being rendered");
            entity3.applyCrashReportCategory(crashReportCategory);
            CrashReportCategory crashReportCategory2 = crashReport.resolveCrashReportCategory("Renderer details");
            crashReportCategory2.add("Assigned renderer", entitySkinLocation);
            crashReportCategory2.add("Location", CrashReportCategory.resolveText(posX, posY, posZ));
            crashReportCategory2.add("Rotation", factor);
            crashReportCategory2.add("Delta", factor2);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }
   }

   public RenderManager(TextureManager textureManager2, RenderItem renderItem) {
      this.textureManager = textureManager2;
      this.entryMap.put(EntityCavespider.class, new ResourceLocationEntryBridge(this));
      this.entryMap.put(EntitySpider.class, new EnhancedResourceLocationEntry(this));
      this.entryMap.put(EntityPig.class, new BloomFilter(this, new AimProfiler(), 0.7F));
      this.entryMap.put(EntitySheep.class, new VertexArrayRenderer(this, new SupplementalFloatEntry(), 0.7F));
      this.entryMap.put(EntityCow.class, new CommonResourceLocationEntry(this, new AimTracker(), 0.7F));
      this.entryMap.put(EntityMushroomcow.class, new SharedResourceLocationEntry(this, new AimTracker(), 0.7F));
      this.entryMap.put(EntityWolf.class, new ManagedResourceLocationEntry(this, new DiamondTalisman(), 0.5F));
      this.entryMap.put(EntityChicken.class, new ParticleBatchRenderer(this, new DiamondDagger(), 0.3F));
      this.entryMap.put(EntityOzelot.class, new PostProcessFilter(this, new HeadMainManager(), 0.4F));
      this.entryMap.put(EntityRabbit.class, new NativeResourceLocationEntry(this, new AuxiliaryHeadMainManager(), 0.3F));
      this.entryMap.put(EntitySilverfish.class, new CompositeResourceLocationEntry(this));
      this.entryMap.put(EntityEndermite.class, new TriangleFanRenderer(this));
      this.entryMap.put(EntityCreeper.class, new QuadBatchRenderer(this));
      this.entryMap.put(EntityEnderman.class, new GeometryPipeline(this));
      this.entryMap.put(EntitySnowman.class, new PrimitiveBatchRenderer(this));
      this.entryMap.put(EntitySkeleton.class, new InternalResourceLocationEntryBridge(this));
      this.entryMap.put(EntityWitch.class, new MeshBufferRenderer(this));
      this.entryMap.put(EntityBlaze.class, new LineLoopRenderer(this));
      this.entryMap.put(EntityPigzombie.class, new AuxiliaryResourceLocationEntryBridge(this));
      this.entryMap.put(EntityZombie.class, new ResourceLocationEntryHandler(this));
      this.entryMap.put(EntitySlime.class, new DefaultResourceLocationEntry(this, new DiamondRod(16), 0.25F));
      this.entryMap.put(EntityLavaslime.class, new DepthBufferFilter(this));
      this.entryMap.put(EntityGiant.class, new PointSpriteRenderer(this, new InternalFloatEntryBridge(), 0.5F, 6.0F));
      this.entryMap.put(EntityGhast.class, new PrimaryResourceLocationEntry(this));
      this.entryMap.put(EntitySquid.class, new TextureAtlasRenderer(this, new DiamondOre(), 0.7F));
      this.entryMap.put(EntityVillager.class, new BaseResourceLocationEntry(this));
      this.entryMap.put(EntityVillagergolem.class, new SpecialResourceLocationEntry(this));
      this.entryMap.put(EntityBat.class, new GenericResourceLocationEntry(this));
      this.entryMap.put(EntityGuardian.class, new SupplementalResourceLocationEntry(this));
      this.entryMap.put(EntityEnderdragon.class, new AltResourceLocationEntry(this));
      this.entryMap.put(EntityEndercrystal.class, new StandardResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityWitherboss.class, new ExtraResourceLocationEntry(this));
      this.entryMap.put(Entity.class, new ResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityPainting.class, new AdvancedResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityItemframe.class, new SupplementalResourceLocationEntryAdapter(this, renderItem));
      this.entryMap.put(EntityLeashknot.class, new TertiaryResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityArrow.class, new SecondaryResourceLocationEntryAdapter(this));
      this.entryMap.put(EntitySnowball.class, new AltResourceLocationEntryAdapter(this, Items.snowball, renderItem));
      this.entryMap.put(EntityThrownenderpearl.class, new AltResourceLocationEntryAdapter(this, Items.enderPearl, renderItem));
      this.entryMap.put(EntityEyeofendersignal.class, new AltResourceLocationEntryAdapter(this, Items.enderEye, renderItem));
      this.entryMap.put(EntityThrownegg.class, new AltResourceLocationEntryAdapter(this, Items.egg, renderItem));
      this.entryMap.put(EntityThrownpotion.class, new SmoothTracker(this, renderItem));
      this.entryMap.put(EntityThrownexpbottle.class, new AltResourceLocationEntryAdapter(this, Items.experienceBottle, renderItem));
      this.entryMap.put(EntityFireworksrocketentity.class, new AltResourceLocationEntryAdapter(this, Items.fireworks, renderItem));
      this.entryMap.put(EntityFireball.class, new CustomResourceLocationEntryAdapter(this, 2.0F));
      this.entryMap.put(EntitySmallfireball.class, new CustomResourceLocationEntryAdapter(this, 0.5F));
      this.entryMap.put(EntityWitherskull.class, new CommonResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityItem.class, new InternalResourceLocationEntryAdapter(this, renderItem));
      this.entryMap.put(EntityXporb.class, new BaseResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityPrimedtnt.class, new EnhancedResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityFallingsand.class, new TargetSelector(this));
      this.entryMap.put(EntityArmorstand.class, new AuxiliaryRendererLivingEntityAdapter(this));
      this.entryMap.put(VectorCalculator.class, new SmoothObserver(this));
      this.entryMap.put(DiamondWeapon.class, new SmoothMonitor(this));
      this.entryMap.put(EntityMinecart.class, new ExtraResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityBoat.class, new AuxiliaryResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityFishHook.class, new ExtendedResourceLocationEntryAdapter(this));
      this.entryMap.put(EntityHorse.class, new EffectPipeline(this, new DiamondHammer(), 0.75F));
      this.entryMap.put(HUIHRVManager.class, new SpecialResourceLocationEntryAdapter(this));
      this.rendererLivingEntityAdapter = new RenderPlayer(this);
      this.entryMap2.put("default", this.rendererLivingEntityAdapter);
      this.entryMap2.put("slim", new RenderPlayer(this, true));
      CompositeItemEntry.applyMap(this.entryMap2);
   }
}
