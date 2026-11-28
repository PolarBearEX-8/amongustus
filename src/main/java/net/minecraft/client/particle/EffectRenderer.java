package net.minecraft.client.particle;

import java.util.concurrent.Callable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.optifine.config.Config;

public class EffectRenderer {
   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
   public World worldObj;
   private final List[][] fxLayers = new List[4][];
   private final List particleEmitters = Lists.newArrayList();
   private final TextureManager renderer;
   private final Random rand = new Random();
   private final Map particleTypes = Maps.newHashMap();

   public void moveToNoAlphaLayer(EntityFX entityFX) {
      this.moveToLayer(entityFX, 0, 1);
   }

   public void emitParticleAtEntity(Entity entity, EnumParticleTypes enumParticleTypes, float f) {
      this.particleEmitters.add(new EntityParticleEmitter(this.worldObj, entity, enumParticleTypes, f));
   }

   public EntityFX spawnEffectParticle(int n, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
      IParticleFactory particleFactory = (IParticleFactory)this.particleTypes.get(n);
      if (particleFactory != null) {
         EntityFX entityFX = particleFactory.getEntityFX(n, this.worldObj, d, d2, d3, d4, d5, d6, ints);
         if (entityFX != null) {
            this.addEffect(entityFX);
            return entityFX;
         }
      }

      return null;
   }

   public EffectRenderer(World world, TextureManager textureManager) {
      this.worldObj = world;
      this.renderer = textureManager;

      for (int i = 0; i < 4; i++) {
         this.fxLayers[i] = new List[2];

         for (int j = 0; j < 2; j++) {
            this.fxLayers[i][j] = Lists.newArrayList();
         }
      }

      this.registerVanillaParticles();
   }

   private void updateEffectAlphaLayer(List list) {
      ArrayList list2 = Lists.newArrayList();
      long l = System.currentTimeMillis();
      int size = list.size();

      for (int i = 0; i < list.size(); i++) {
         EntityFX entityFX = (EntityFX)list.get(i);
         this.tickParticle(entityFX);
         if (entityFX.isDead) {
            list2.add(entityFX);
         }

         size--;
         if (System.currentTimeMillis() > l + 20L) {
            break;
         }
      }

      if (size > 0) {
         int n = size;

         for (Iterator j = list.iterator(); j.hasNext() && n > 0; n--) {
            ((EntityFX)j.next()).setDead();
            j.remove();
         }
      }

      list.removeAll(list2);
   }

   public void addBlockDestroyEffects(BlockPos pos, IBlockState state) {
      if (state.getBlock().getMaterial() != Material.air) {
         state = state.getBlock().getActualState(state, this.worldObj, pos.getX(), pos.getY(), pos.getZ());
         byte b = 4;

         for (int i = 0; i < b; i++) {
            for (int j = 0; j < b; j++) {
               for (int k = 0; k < b; k++) {
                  double d = (double)pos.getX() + ((double)i + 0.5) / (double)b;
                  double d2 = (double)pos.getY() + ((double)j + 0.5) / (double)b;
                  double d3 = (double)pos.getZ() + ((double)k + 0.5) / (double)b;
                  this.addEffect(
                     new EntityDiggingFX(
                           this.worldObj,
                           d,
                           d2,
                           d3,
                           d - (double)pos.getX() - 0.5,
                           d2 - (double)pos.getY() - 0.5,
                           d3 - (double)pos.getZ() - 0.5,
                           state
                        )
                        .setBlockPos(pos)
                  );
               }
            }
         }
      }
   }

   private void registerVanillaParticles() {
      this.registerParticle(EnumParticleTypes.EXPLOSION_NORMAL.getParticleID(), new EntityExplodeFX.Factory());
      this.registerParticle(EnumParticleTypes.WATER_BUBBLE.getParticleID(), new EntityBubbleFX.Factory());
      this.registerParticle(EnumParticleTypes.WATER_SPLASH.getParticleID(), new EntitySplashFX.Factory());
      this.registerParticle(EnumParticleTypes.WATER_WAKE.getParticleID(), new EntityFishWakeFX.Factory());
      this.registerParticle(EnumParticleTypes.WATER_DROP.getParticleID(), new EntityRainFX.Factory());
      this.registerParticle(EnumParticleTypes.SUSPENDED.getParticleID(), new EntitySuspendFX.Factory());
      this.registerParticle(EnumParticleTypes.SUSPENDED_DEPTH.getParticleID(), new EntityAuraFX.Factory());
      this.registerParticle(EnumParticleTypes.CRIT.getParticleID(), new EntityCrit2FX.Factory());
      this.registerParticle(EnumParticleTypes.CRIT_MAGIC.getParticleID(), new EntityCrit2FX.MagicFactory());
      this.registerParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleID(), new EntitySmokeFX.Factory());
      this.registerParticle(EnumParticleTypes.SMOKE_LARGE.getParticleID(), new EntityCritFX.Factory());
      this.registerParticle(EnumParticleTypes.SPELL.getParticleID(), new EntitySpellParticleFX.Factory());
      this.registerParticle(EnumParticleTypes.SPELL_INSTANT.getParticleID(), new EntitySpellParticleFX.InstantFactory());
      this.registerParticle(EnumParticleTypes.SPELL_MOB.getParticleID(), new EntitySpellParticleFX.MobFactory());
      this.registerParticle(EnumParticleTypes.SPELL_MOB_AMBIENT.getParticleID(), new EntitySpellParticleFX.AmbientMobFactory());
      this.registerParticle(EnumParticleTypes.SPELL_WITCH.getParticleID(), new EntitySpellParticleFX.WitchFactory());
      this.registerParticle(EnumParticleTypes.DRIP_WATER.getParticleID(), new EntityDropParticleFX.WaterFactory());
      this.registerParticle(EnumParticleTypes.DRIP_LAVA.getParticleID(), new EntityDropParticleFX.LavaFactory());
      this.registerParticle(EnumParticleTypes.VILLAGER_ANGRY.getParticleID(), new EntityHeartFX.AngryVillagerFactory());
      this.registerParticle(EnumParticleTypes.VILLAGER_HAPPY.getParticleID(), new EntityAuraFX.HappyVillagerFactory());
      this.registerParticle(EnumParticleTypes.TOWN_AURA.getParticleID(), new EntityAuraFX.Factory());
      this.registerParticle(EnumParticleTypes.NOTE.getParticleID(), new EntityNoteFX.Factory());
      this.registerParticle(EnumParticleTypes.PORTAL.getParticleID(), new EntityPortalFX.Factory());
      this.registerParticle(EnumParticleTypes.ENCHANTMENT_TABLE.getParticleID(), new EntityEnchantmentTableParticleFX.EnchantmentTable());
      this.registerParticle(EnumParticleTypes.FLAME.getParticleID(), new EntityFlameFX.Factory());
      this.registerParticle(EnumParticleTypes.LAVA.getParticleID(), new EntityLavaFX.Factory());
      this.registerParticle(EnumParticleTypes.FOOTSTEP.getParticleID(), new EntityFootStepFX.Factory());
      this.registerParticle(EnumParticleTypes.CLOUD.getParticleID(), new EntityCloudFX.Factory());
      this.registerParticle(EnumParticleTypes.REDSTONE.getParticleID(), new EntityReddustFX.Factory());
      this.registerParticle(EnumParticleTypes.SNOWBALL.getParticleID(), new EntityBreakingFX.SnowballFactory());
      this.registerParticle(EnumParticleTypes.SNOW_SHOVEL.getParticleID(), new EntitySnowShovelFX.Factory());
      this.registerParticle(EnumParticleTypes.SLIME.getParticleID(), new EntityBreakingFX.SlimeFactory());
      this.registerParticle(EnumParticleTypes.HEART.getParticleID(), new EntityHeartFX.Factory());
      this.registerParticle(EnumParticleTypes.BARRIER.getParticleID(), new Barrier.Factory());
      this.registerParticle(EnumParticleTypes.ITEM_CRACK.getParticleID(), new EntityBreakingFX.Factory());
      this.registerParticle(EnumParticleTypes.BLOCK_CRACK.getParticleID(), new EntityDiggingFX.Factory());
      this.registerParticle(EnumParticleTypes.BLOCK_DUST.getParticleID(), new EntityBlockDustFX.Factory());
      this.registerParticle(EnumParticleTypes.EXPLOSION_HUGE.getParticleID(), new EntityHugeExplodeFX.Factory());
      this.registerParticle(EnumParticleTypes.EXPLOSION_LARGE.getParticleID(), new EntityLargeExplodeFX.Factory());
      this.registerParticle(EnumParticleTypes.FIREWORKS_SPARK.getParticleID(), new EntityFirework.Factory());
      this.registerParticle(EnumParticleTypes.MOB_APPEARANCE.getParticleID(), new MobAppearance.Factory());
   }

   public void moveToAlphaLayer(EntityFX entityFX) {
      this.moveToLayer(entityFX, 1, 0);
   }

   public void renderParticles(Entity entity, float f) {
      float rotationX = ActiveRenderInfo.getRotationX();
      float rotationZ = ActiveRenderInfo.getRotationZ();
      float rotationYZ = ActiveRenderInfo.getRotationYZ();
      float rotationXY = ActiveRenderInfo.getRotationXY();
      float rotationXZ = ActiveRenderInfo.getRotationXZ();
      EntityFX.interpPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f;
      EntityFX.interpPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f;
      EntityFX.interpPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f;
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.alphaFunc(516, 0.003921569F);
      boolean flag = ActiveRenderInfo.getBlockAtEntityViewpoint(this.worldObj, entity, f).getMaterial() == Material.water;

      for (int i = 0; i < 3; i++) {
         int n = i;

         for (int j = 0; j < 2; j++) {
            if (!this.fxLayers[n][j].isEmpty()) {
               switch (j) {
                  case 0:
                     GlStateManager.depthMask(false);
                     break;
                  case 1:
                     GlStateManager.depthMask(true);
               }

               switch (n) {
                  case 0:
                  default:
                     this.renderer.bindTexture(particleTextures);
                     break;
                  case 1:
                     this.renderer.bindTexture(TextureMap.locationBlocksTexture);
               }

               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               Tessellator instance = Tessellator.getInstance();
               WorldRenderer worldRenderer = instance.getWorldRenderer();
               worldRenderer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);

               for (int k = 0; k < this.fxLayers[n][j].size(); k++) {
                  EntityFX entityFX = (EntityFX)this.fxLayers[n][j].get(k);

                  try {
                     if (flag || !(entityFX instanceof EntitySuspendFX)) {
                        entityFX.renderParticle(worldRenderer, entity, f, rotationX, rotationXZ, rotationZ, rotationYZ, rotationXY);
                     }
                  } catch (Throwable e) {
                     CrashReport crashReport = CrashReport.makeCrashReport(e, "Rendering Particle");
                     CrashReportCategory crashReportCategory = crashReport.makeCategory("Particle being rendered");
                     crashReportCategory.addCrashSectionCallable("Particle", new EffectRenderer.ValueCallable3(this, entityFX));
                     crashReportCategory.addCrashSectionCallable("Particle Type", new EffectRenderer.ValueCallable4(this, n));
                     throw new ReportedException(crashReport);
                  }
               }

               instance.draw();
            }
         }
      }

      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
      GlStateManager.alphaFunc(516, 0.1F);
   }

   public void updateEffects() {
      for (int i = 0; i < 4; i++) {
         this.updateEffectLayer(i);
      }

      ArrayList list = Lists.newArrayList();

      for (EntityParticleEmitter entityParticleEmitter : (Iterable<EntityParticleEmitter>)(this.particleEmitters)) {
         entityParticleEmitter.onUpdate();
         if (entityParticleEmitter.isDead) {
            list.add(entityParticleEmitter);
         }
      }

      this.particleEmitters.removeAll(list);
   }

   private void updateEffectLayer(int n) {
      for (int i = 0; i < 2; i++) {
         this.updateEffectAlphaLayer(this.fxLayers[n][i]);
      }
   }

   public void renderLitParticles(Entity entity, float f) {
      float f2 = (float) (Math.PI / 180.0);
      float f3 = MathHelper.cos(entity.rotationYaw * (float) (Math.PI / 180.0));
      float f4 = MathHelper.sin(entity.rotationYaw * (float) (Math.PI / 180.0));
      float f5 = -f4 * MathHelper.sin(entity.rotationPitch * (float) (Math.PI / 180.0));
      float f6 = f3 * MathHelper.sin(entity.rotationPitch * (float) (Math.PI / 180.0));
      float f7 = MathHelper.cos(entity.rotationPitch * (float) (Math.PI / 180.0));

      for (int i = 0; i < 2; i++) {
         List list = this.fxLayers[3][i];
         if (!list.isEmpty()) {
            WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();

            for (int j = 0; j < list.size(); j++) {
               ((EntityFX)list.get(j)).renderParticle(worldRenderer, entity, f, f3, f7, f4, f5, f6);
            }
         }
      }
   }

   public void emitParticleAtEntity(Entity entity, EnumParticleTypes enumParticleTypes) {
      this.particleEmitters.add(new EntityParticleEmitter(this.worldObj, entity, enumParticleTypes));
   }

   public void addBlockHitEffects(BlockPos pos, EnumFacing facing) {
      IBlockState blockState = this.worldObj.getBlockState(pos);
      Block block = blockState.getBlock();
      if (block.getRenderType() != -1) {
         int x = pos.getX();
         int y = pos.getY();
         int z = pos.getZ();
         float f = 0.1F;
         double d = (double)x
            + this.rand.nextDouble() * (block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() - (double)(f * 2.0F))
            + (double)f
            + block.getBlockBoundsMinX();
         double d2 = (double)y
            + this.rand.nextDouble() * (block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() - (double)(f * 2.0F))
            + (double)f
            + block.getBlockBoundsMinY();
         double d3 = (double)z
            + this.rand.nextDouble() * (block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() - (double)(f * 2.0F))
            + (double)f
            + block.getBlockBoundsMinZ();
         if (facing == EnumFacing.DOWN) {
            d2 = (double)y + block.getBlockBoundsMinY() - (double)f;
         }

         if (facing == EnumFacing.UP) {
            d2 = (double)y + block.getBlockBoundsMaxY() + (double)f;
         }

         if (facing == EnumFacing.NORTH) {
            d3 = (double)z + block.getBlockBoundsMinZ() - (double)f;
         }

         if (facing == EnumFacing.SOUTH) {
            d3 = (double)z + block.getBlockBoundsMaxZ() + (double)f;
         }

         if (facing == EnumFacing.WEST) {
            d = (double)x + block.getBlockBoundsMinX() - (double)f;
         }

         if (facing == EnumFacing.EAST) {
            d = (double)x + block.getBlockBoundsMaxX() + (double)f;
         }

         this.addEffect(
            new EntityDiggingFX(this.worldObj, d, d2, d3, 0.0, 0.0, 0.0, blockState)
               .setBlockPos(pos)
               .multiplyVelocity(0.2F)
               .multipleParticleScaleBy(0.6F)
         );
      }
   }

   private void tickParticle(EntityFX entityFX) {
      try {
         entityFX.onUpdate();
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Ticking Particle");
         CrashReportCategory crashReportCategory = crashReport.makeCategory("Particle being ticked");
         int fxLayer = entityFX.getFXLayer();
         crashReportCategory.addCrashSectionCallable("Particle", new EffectRenderer.ValueCallable(this, entityFX));
         crashReportCategory.addCrashSectionCallable("Particle Type", new EffectRenderer.ValueCallable2(this, fxLayer));
         throw new ReportedException(crashReport);
      }
   }

   public void clearEffects(World world) {
      this.worldObj = world;

      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 2; j++) {
            this.fxLayers[i][j].clear();
         }
      }

      this.particleEmitters.clear();
   }

   public String getStatistics() {
      int n = 0;

      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 2; j++) {
            n += this.fxLayers[i][j].size();
         }
      }

      return "" + n;
   }

   private void moveToLayer(EntityFX entityFX, int n, int n2) {
      for (int i = 0; i < 4; i++) {
         if (this.fxLayers[i][n].contains(entityFX)) {
            this.fxLayers[i][n].remove(entityFX);
            this.fxLayers[i][n2].add(entityFX);
         }
      }
   }

   public void addEffect(EntityFX entityFX) {
      if (entityFX != null && (!(entityFX instanceof EntityFirework.SparkFX) || Config.isFireworkParticles())) {
         int fxLayer = entityFX.getFXLayer();
         int n = entityFX.getAlpha() != 1.0F ? 0 : 1;
         if (this.fxLayers[fxLayer][n].size() >= 4000) {
            this.fxLayers[fxLayer][n].remove(0);
         }

         this.fxLayers[fxLayer][n].add(entityFX);
      }
   }

   public void registerParticle(int n, IParticleFactory particleFactory) {
      this.particleTypes.put(n, particleFactory);
   }


   public static class ValueCallable implements Callable {
      public final EntityFX particle;
      public final EffectRenderer effectRenderer;

      public ValueCallable(EffectRenderer effectRenderer2, EntityFX entityFX) {
         this.effectRenderer = effectRenderer2;
         this.particle = entityFX;
      }

      @Override
      public Object call() throws Exception {
         return this.getParticleInfo();
      }

      public String getParticleInfo() throws Exception {
         return this.particle.toString();
      }
   }

   public static class ValueCallable2 implements Callable {
      public final int layer;
      public final EffectRenderer effectRenderer;

      public String getParticleLayerName() throws Exception {
         return this.layer == 0 ? "MISC_TEXTURE" : (this.layer == 1 ? "TERRAIN_TEXTURE" : (this.layer == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + this.layer));
      }

      @Override
      public Object call() throws Exception {
         return this.getParticleLayerName();
      }

      public ValueCallable2(EffectRenderer effectRenderer2, int n) {
         this.effectRenderer = effectRenderer2;
         this.layer = n;
      }
   }

   public static class ValueCallable3 implements Callable {
      public final EntityFX particle;
      public final EffectRenderer effectRenderer;

      public String getParticleInfo() throws Exception {
         return this.particle.toString();
      }

      public ValueCallable3(EffectRenderer effectRenderer2, EntityFX entityFX) {
         this.effectRenderer = effectRenderer2;
         this.particle = entityFX;
      }

      @Override
      public Object call() throws Exception {
         return this.getParticleInfo();
      }
   }

   public static class ValueCallable4 implements Callable {
      public final int layer;
      public final EffectRenderer effectRenderer;

      public String getParticleLayerName() throws Exception {
         return this.layer == 0 ? "MISC_TEXTURE" : (this.layer == 1 ? "TERRAIN_TEXTURE" : (this.layer == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + this.layer));
      }

      @Override
      public Object call() throws Exception {
         return this.getParticleLayerName();
      }

      public ValueCallable4(EffectRenderer effectRenderer2, int n) {
         this.effectRenderer = effectRenderer2;
         this.layer = n;
      }
   }
}
