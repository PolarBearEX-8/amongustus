package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import net.augustus.modules.Module;
import net.augustus.events.EventPlayerView;
import net.augustus.ClientManagers;
import net.augustus.settings.ColorSetting;
import net.augustus.events.listeners.PlayerViewListener;
import net.augustus.Augustus;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.player.PlayerItemsLayer;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class RenderManager {
   private Map entityRenderMap = Maps.newHashMap();
   private Map skinMap = Maps.newHashMap();
   private RenderPlayer playerRenderer;
   private FontRenderer textRenderer;
   private double renderPosX;
   private double renderPosY;
   private double renderPosZ;
   public TextureManager renderEngine;
   public World worldObj;
   public Entity livingPlayer;
   public Entity pointedEntity;
   public float playerViewY;
   public float playerViewX;
   public GameSettings options;
   public double viewerPosX;
   public double viewerPosY;
   public double viewerPosZ;
   private boolean renderOutlines = false;
   private boolean renderShadow = true;
   private boolean debugBoundingBox = false;
   public Render renderRender = null;

   public RenderManager(TextureManager textureManager, RenderItem renderItem) {
      this.renderEngine = textureManager;
      this.entityRenderMap.put(EntityCaveSpider.class, new RenderCaveSpider(this));
      this.entityRenderMap.put(EntitySpider.class, new RenderSpider(this));
      this.entityRenderMap.put(EntityPig.class, new RenderPig(this, new ModelPig(), 0.7F));
      this.entityRenderMap.put(EntitySheep.class, new RenderSheep(this, new ModelSheep2(), 0.7F));
      this.entityRenderMap.put(EntityCow.class, new RenderCow(this, new ModelCow(), 0.7F));
      this.entityRenderMap.put(EntityMooshroom.class, new RenderMooshroom(this, new ModelCow(), 0.7F));
      this.entityRenderMap.put(EntityWolf.class, new RenderWolf(this, new ModelWolf(), 0.5F));
      this.entityRenderMap.put(EntityChicken.class, new RenderChicken(this, new ModelChicken(), 0.3F));
      this.entityRenderMap.put(EntityOcelot.class, new RenderOcelot(this, new ModelOcelot(), 0.4F));
      this.entityRenderMap.put(EntityRabbit.class, new RenderRabbit(this, new ModelRabbit(), 0.3F));
      this.entityRenderMap.put(EntitySilverfish.class, new RenderSilverfish(this));
      this.entityRenderMap.put(EntityEndermite.class, new RenderEndermite(this));
      this.entityRenderMap.put(EntityCreeper.class, new RenderCreeper(this));
      this.entityRenderMap.put(EntityEnderman.class, new RenderEnderman(this));
      this.entityRenderMap.put(EntitySnowman.class, new RenderSnowMan(this));
      this.entityRenderMap.put(EntitySkeleton.class, new RenderSkeleton(this));
      this.entityRenderMap.put(EntityWitch.class, new RenderWitch(this));
      this.entityRenderMap.put(EntityBlaze.class, new RenderBlaze(this));
      this.entityRenderMap.put(EntityPigZombie.class, new RenderPigZombie(this));
      this.entityRenderMap.put(EntityZombie.class, new RenderZombie(this));
      this.entityRenderMap.put(EntitySlime.class, new RenderSlime(this, new ModelSlime(16), 0.25F));
      this.entityRenderMap.put(EntityMagmaCube.class, new RenderMagmaCube(this));
      this.entityRenderMap.put(EntityGiantZombie.class, new RenderGiantZombie(this, new ModelZombie(), 0.5F, 6.0F));
      this.entityRenderMap.put(EntityGhast.class, new RenderGhast(this));
      this.entityRenderMap.put(EntitySquid.class, new RenderSquid(this, new ModelSquid(), 0.7F));
      this.entityRenderMap.put(EntityVillager.class, new RenderVillager(this));
      this.entityRenderMap.put(EntityIronGolem.class, new RenderIronGolem(this));
      this.entityRenderMap.put(EntityBat.class, new RenderBat(this));
      this.entityRenderMap.put(EntityGuardian.class, new RenderGuardian(this));
      this.entityRenderMap.put(EntityDragon.class, new RenderDragon(this));
      this.entityRenderMap.put(EntityEnderCrystal.class, new RenderEnderCrystal(this));
      this.entityRenderMap.put(EntityWither.class, new RenderWither(this));
      this.entityRenderMap.put(Entity.class, new RenderEntity(this));
      this.entityRenderMap.put(EntityPainting.class, new RenderPainting(this));
      this.entityRenderMap.put(EntityItemFrame.class, new RenderItemFrame(this, renderItem));
      this.entityRenderMap.put(EntityLeashKnot.class, new RenderLeashKnot(this));
      this.entityRenderMap.put(EntityArrow.class, new RenderArrow(this));
      this.entityRenderMap.put(EntitySnowball.class, new RenderSnowball(this, Items.snowball, renderItem));
      this.entityRenderMap.put(EntityEnderPearl.class, new RenderSnowball(this, Items.ender_pearl, renderItem));
      this.entityRenderMap.put(EntityEnderEye.class, new RenderSnowball(this, Items.ender_eye, renderItem));
      this.entityRenderMap.put(EntityEgg.class, new RenderSnowball(this, Items.egg, renderItem));
      this.entityRenderMap.put(EntityPotion.class, new RenderPotion(this, renderItem));
      this.entityRenderMap.put(EntityExpBottle.class, new RenderSnowball(this, Items.experience_bottle, renderItem));
      this.entityRenderMap.put(EntityFireworkRocket.class, new RenderSnowball(this, Items.fireworks, renderItem));
      this.entityRenderMap.put(EntityLargeFireball.class, new RenderFireball(this, 2.0F));
      this.entityRenderMap.put(EntitySmallFireball.class, new RenderFireball(this, 0.5F));
      this.entityRenderMap.put(EntityWitherSkull.class, new RenderWitherSkull(this));
      this.entityRenderMap.put(EntityItem.class, new RenderEntityItem(this, renderItem));
      this.entityRenderMap.put(EntityXPOrb.class, new RenderXPOrb(this));
      this.entityRenderMap.put(EntityTNTPrimed.class, new RenderTNTPrimed(this));
      this.entityRenderMap.put(EntityFallingBlock.class, new RenderFallingBlock(this));
      this.entityRenderMap.put(EntityArmorStand.class, new ArmorStandRenderer(this));
      this.entityRenderMap.put(EntityMinecartTNT.class, new RenderTntMinecart(this));
      this.entityRenderMap.put(EntityMinecartMobSpawner.class, new RenderMinecartMobSpawner(this));
      this.entityRenderMap.put(EntityMinecart.class, new RenderMinecart(this));
      this.entityRenderMap.put(EntityBoat.class, new RenderBoat(this));
      this.entityRenderMap.put(EntityFishHook.class, new RenderFish(this));
      this.entityRenderMap.put(EntityHorse.class, new RenderHorse(this, new ModelHorse(), 0.75F));
      this.entityRenderMap.put(EntityLightningBolt.class, new RenderLightningBolt(this));
      this.playerRenderer = new RenderPlayer(this);
      this.skinMap.put("default", this.playerRenderer);
      this.skinMap.put("slim", new RenderPlayer(this, true));
      PlayerItemsLayer.register(this.skinMap);
   }

   public void setRenderPosition(double d, double d2, double d3) {
      this.renderPosX = d;
      this.renderPosY = d2;
      this.renderPosZ = d3;
   }

   public Render getEntityClassRenderObject(Class clazz) {
      Render render = (Render)this.entityRenderMap.get(clazz);
      if (render == null && clazz != Entity.class) {
         render = this.getEntityClassRenderObject(clazz.getSuperclass());
         this.entityRenderMap.put(clazz, render);
      }

      return render;
   }

   public Render getEntityRenderObject(Entity entity) {
      if (!(entity instanceof AbstractClientPlayer)) {
         return this.getEntityClassRenderObject(entity.getClass());
      } else {
         String str;
         if (ClientManagers.moduleManager.customSkin.isToggled() && entity instanceof EntityPlayerSP) {
            str = ClientManagers.moduleManager.customSkin.skinMode.getSelectedValue();
         } else {
            str = ((AbstractClientPlayer)entity).getSkinType();
         }

         RenderPlayer renderPlayer = (RenderPlayer)this.skinMap.get(str);
         return renderPlayer != null ? renderPlayer : this.playerRenderer;
      }
   }

   public void cacheActiveRenderInfo(World world, FontRenderer fontRenderer, Entity entity, Entity entity2, GameSettings gameSettings, float f) {
      this.worldObj = world;
      this.options = gameSettings;
      this.livingPlayer = entity;
      this.pointedEntity = entity2;
      this.textRenderer = fontRenderer;
      if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isPlayerSleeping()) {
         IBlockState blockState = world.getBlockState(new BlockPos(entity));
         if (blockState.getBlock() == Blocks.bed) {
            this.playerViewY = (float)(((EnumFacing)blockState.getValue(BlockBed.FACING)).getHorizontalIndex() * 90 + 180);
            this.playerViewX = 0.0F;
         }
      } else {
         this.playerViewY = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f;
         this.playerViewX = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f;
         EventPlayerView event = PlayerViewListener.onPlayerView(this.playerViewX, this.playerViewY, f);
         this.playerViewX = event.getPlayerViewX();
         this.playerViewY = event.getPlayerViewY();
      }

      if (gameSettings.thirdPersonView == 2) {
         this.playerViewY += 180.0F;
      }

      this.viewerPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f;
      this.viewerPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f;
      this.viewerPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f;
   }

   public void setPlayerViewY(float f) {
      this.playerViewY = f;
   }

   public boolean isRenderShadow() {
      return this.renderShadow;
   }

   public void setRenderShadow(boolean flag) {
      this.renderShadow = flag;
   }

   public void setDebugBoundingBox(boolean flag) {
      this.debugBoundingBox = flag;
   }

   public boolean isDebugBoundingBox() {
      return this.debugBoundingBox;
   }

   public boolean renderEntitySimple(Entity entity, float f) {
      return this.renderEntityStatic(entity, f, false);
   }

   public boolean shouldRender(Entity entity, ICamera camera, double d, double d2, double d3) {
      Render entityRenderObject = this.getEntityRenderObject(entity);
      return entityRenderObject != null && entityRenderObject.shouldRender(entity, camera, d, d2, d3);
   }

   public boolean renderEntityStatic(Entity entity, float f, boolean flag) {
      if (entity.ticksExisted == 0) {
         entity.lastTickPosX = entity.posX;
         entity.lastTickPosY = entity.posY;
         entity.lastTickPosZ = entity.posZ;
      }

      double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f;
      double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f;
      double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f;
      float f2 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f;
      int brightnessForRender = entity.getBrightnessForRender(f);
      if (entity.isBurning()) {
         brightnessForRender = 15728880;
      }

      int n = brightnessForRender % 65536;
      int n2 = brightnessForRender / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n / 1.0F, (float)n2 / 1.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      return this.doRenderEntity(entity, d - this.renderPosX, d2 - this.renderPosY, d3 - this.renderPosZ, f2, f, flag);
   }

   public void renderWitherSkull(Entity entity, float f) {
      double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f;
      double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f;
      double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f;
      Render entityRenderObject = this.getEntityRenderObject(entity);
      if (entityRenderObject != null && this.renderEngine != null) {
         int brightnessForRender = entity.getBrightnessForRender(f);
         int n = brightnessForRender % 65536;
         int n2 = brightnessForRender / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n / 1.0F, (float)n2 / 1.0F);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         entityRenderObject.renderName(entity, d - this.renderPosX, d2 - this.renderPosY, d3 - this.renderPosZ);
      }
   }

   public boolean renderEntityWithPosYaw(Entity entity, double d, double d2, double d3, float f, float f2) {
      return this.doRenderEntity(entity, d, d2, d3, f, f2, false);
   }

   public boolean doRenderEntity(Entity entity, double d, double d2, double d3, float f, float f2, boolean flag) {
      Render render = null;

      try {
         render = this.getEntityRenderObject(entity);
         if (render != null && this.renderEngine != null) {
            try {
               if (render instanceof RendererLivingEntity) {
                  ((RendererLivingEntity)render).setRenderOutlines(this.renderOutlines);
               }

               if (CustomEntityModels.isActive()) {
                  this.renderRender = render;
               }

               render.doRender(entity, d, d2, d3, f, f2);
            } catch (Throwable e) {
               throw new ReportedException(CrashReport.makeCrashReport(e, "Rendering entity in world"));
            }

            try {
               if (!this.renderOutlines) {
                  render.doRenderShadowAndFire(entity, d, d2, d3, f, f2);
               }
            } catch (Throwable e2) {
               throw new ReportedException(CrashReport.makeCrashReport(e2, "Post-rendering entity in world"));
            }

            boolean flag2 = false;
            boolean flag3 = true;
            int n = 255;
            int n2 = 255;
            int n3 = 255;
            int n4 = 255;
            if ((this.debugBoundingBox || (flag2 = !(flag3 = !((Module)Augustus.getInstance().getModuleManager().arrowHitboxes).isToggled()) && entity instanceof EntityArrow))
               && !entity.isInvisible()
               && !flag) {
               if (flag2) {
                  ColorSetting boxColor = Augustus.getInstance().getModuleManager().arrowHitboxes.boxColor;
                  boxColor.getColorInt((byte)0);
                  n = boxColor.getRed();
                  n2 = boxColor.getGreen();
                  n3 = boxColor.getBlue();
                  n4 = boxColor.getAlpha();
                  GL11.glLineWidth((float)Augustus.getInstance().getModuleManager().arrowHitboxes.lineWidth.getValue());
               }

               try {
                  this.renderDebugBoundingBox(entity, d, d2, d3, f, f2, flag3, n, n2, n3, n4);
               } catch (Throwable e3) {
                  throw new ReportedException(CrashReport.makeCrashReport(e3, "Rendering entity hitbox in world"));
               }

               if (flag2) {
                  GL11.glLineWidth(2.0F);
               }
            }
         } else if (this.renderEngine != null) {
            return false;
         }

         return true;
      } catch (Throwable e4) {
         CrashReport crashReport = CrashReport.makeCrashReport(e4, "Rendering entity in world");
         entity.addEntityCrashInfo(crashReport.makeCategory("Entity being rendered"));
         CrashReportCategory crashReportCategory2 = crashReport.makeCategory("Renderer details");
         crashReportCategory2.addCrashSection("Assigned renderer", render);
         crashReportCategory2.addCrashSection("Location", CrashReportCategory.getCoordinateInfo(d, d2, d3));
         crashReportCategory2.addCrashSection("Rotation", f);
         crashReportCategory2.addCrashSection("Delta", f2);
         throw new ReportedException(crashReport);
      }
   }

   private void renderDebugBoundingBox(Entity entity, double d, double d2, double d3, float f, float f2, boolean flag, int n, int n2, int n3, int n4) {
      if (!Shaders.isShadowPass) {
         GlStateManager.depthMask(false);
         GlStateManager.disableTexture2D();
         GlStateManager.disableLighting();
         GlStateManager.disableCull();
         GlStateManager.disableBlend();
         float f3 = entity.width / 2.0F;
         AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
         RenderGlobal.drawOutlinedBoundingBox(
            entityBoundingBox.minX - entity.posX + d,
            entityBoundingBox.minY - entity.posY + d2,
            entityBoundingBox.minZ - entity.posZ + d3,
            entityBoundingBox.maxX - entity.posX + d,
            entityBoundingBox.maxY - entity.posY + d2,
            entityBoundingBox.maxZ - entity.posZ + d3,
            n,
            n2,
            n3,
            n4
         );
         if (entity instanceof EntityLivingBase && !ClientManagers.moduleManager.legacyAnimations.isLegacyDebugHitBoxEnabled()) {
            float f4 = 0.01F;
            RenderGlobal.drawOutlinedBoundingBox(
               d - (double)f3,
               d2 + (double)entity.getEyeHeight() - 0.009999999776482582,
               d3 - (double)f3,
               d + (double)f3,
               d2 + (double)entity.getEyeHeight() + 0.009999999776482582,
               d3 + (double)f3,
               255,
               0,
               0,
               255
            );
         }

         if (flag && !ClientManagers.moduleManager.legacyAnimations.isLegacyDebugHitBoxEnabled()) {
            Tessellator instance = Tessellator.getInstance();
            WorldRenderer worldRenderer = instance.getWorldRenderer();
            Vec3 look = entity.getLook(f2);
            worldRenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos(d, d2 + (double)entity.getEyeHeight(), d3).color(0, 0, 255, 255).endVertex();
            worldRenderer.pos(d + look.xCoord * 2.0, d2 + (double)entity.getEyeHeight() + look.yCoord * 2.0, d3 + look.zCoord * 2.0)
               .color(0, 0, 255, 255)
               .endVertex();
            instance.draw();
         }

         GlStateManager.enableTexture2D();
         GlStateManager.enableLighting();
         GlStateManager.enableCull();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
      }
   }

   public void set(World world) {
      this.worldObj = world;
   }

   public double getDistanceToCamera(double d, double d2, double d3) {
      double d4 = d - this.viewerPosX;
      double d5 = d2 - this.viewerPosY;
      double d6 = d3 - this.viewerPosZ;
      return d4 * d4 + d5 * d5 + d6 * d6;
   }

   public FontRenderer getFontRenderer() {
      return this.textRenderer;
   }

   public void setRenderOutlines(boolean flag) {
      this.renderOutlines = flag;
   }

   public Map getEntityRenderMap() {
      return this.entityRenderMap;
   }

   public void setEntityRenderMap(Map map) {
      this.entityRenderMap = map;
   }

   public Map getSkinMap() {
      return Collections.unmodifiableMap(this.skinMap);
   }

   public double getRenderPosX() {
      return this.renderPosX;
   }

   public double getRenderPosY() {
      return this.renderPosY;
   }

   public double getRenderPosZ() {
      return this.renderPosZ;
   }
}
