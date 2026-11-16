package net.minecraft.client.renderer;

import client.event.PostMotionBroadcaster;
import client.render.WorldRenderer;
import client.setting.AttackEffectsExplosionNormalType;
import client.util.AdvancedListenerBridge;
import client.util.AimBuffer;
import client.util.AimMatcher;
import client.util.AimSerializer;
import client.util.AngleCollector;
import client.util.AngleCoordinator;
import client.util.AngleDispatcher;
import client.util.AuxiliaryIntBufferEntry;
import client.util.AuxiliaryListenerResolver;
import client.util.BaseListenerBridge;
import client.util.BezierCalculator;
import client.util.BezierChecker;
import client.util.BezierParser;
import client.util.BuildManager;
import client.util.CommonListenerBridge;
import client.util.ComplexClientManager;
import client.util.CompositeClientManager;
import client.util.CurveAdapter;
import client.util.CustomListenerBridge;
import client.util.DefaultVertexFormats;
import client.util.DistanceCollector;
import client.util.ExtendedListenerDispatcher;
import client.util.FovDispatcher;
import client.util.FovPicker;
import client.util.FovPool;
import client.util.FovSystem;
import client.util.GeometryChecker;
import client.util.GeometryEvaluator;
import client.util.GlobalClientManager;
import client.util.HitBoxBridge;
import client.util.IBlockState;
import client.util.IndirectClientManager;
import client.util.InternalListenerTracker;
import client.util.InterpolationProvider;
import client.util.InterpolationRegistry;
import client.util.InterpolationSerializer;
import client.util.InterpolationTransformer;
import client.util.InterpolationWriter;
import client.util.ListenerAdapter19;
import client.util.ListenerAdapter27;
import client.util.PassiveClientManager;
import client.util.SecondaryListenerDispatcher;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SpecialListenerBridge;
import client.util.StandardListenerBridge;
import client.util.TargetProfiler;
import client.util.TargetTracker;
import client.util.TertiaryListenerHandler;
import client.util.VectorAdapter;
import client.util.VectorPredictor;
import client.util.VirtualClientManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBoggedAdapter;
import net.minecraft.entity.EntityEvokerAdapter;
import net.minecraft.entity.EntityHuskAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EffectRenderer {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/particle/particles.png");
   public World world;
   public final List[][] listArray = new List[4][];
   public final List list = Lists.newArrayList();
   public final TextureManager textureManager;
   public final Random random = new Random();
   public final Map map = Maps.newHashMap();

   public void applyEntityByEntity(Entity entity, float factor) {
      float factor2 = AuxiliaryIntBufferEntry.getFactor4();
      float factor3 = AuxiliaryIntBufferEntry.getFactor();
      float factor4 = AuxiliaryIntBufferEntry.getFactor2();
      float factor5 = AuxiliaryIntBufferEntry.getFactor3();
      float factor6 = AuxiliaryIntBufferEntry.getFactor5();
      TertiaryEntityAdapter.value8 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)factor;
      TertiaryEntityAdapter.value6 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)factor;
      TertiaryEntityAdapter.value7 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)factor;
      GlStateManager.disableBlend();
      GlStateManager.glStateAction3(770, 771);
      GlStateManager.alphaFunc(516, 0.003921569F);
      boolean flag = AuxiliaryIntBufferEntry.resolveBlock(this.world, entity, factor).getMaterial() == Material.coreItemEntry1;

      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 2; index2++) {
            if (!this.listArray[index][index2].isEmpty()) {
               switch (index2) {
                  case 0:
                     GlStateManager.depthMask(false);
                     break;
                  case 1:
                     GlStateManager.depthMask(true);
               }

               switch (index) {
                  case 0:
                  default:
                     this.textureManager.applyResourceLocation3(resourceLocation);
                     break;
                  case 1:
                     this.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
               }

               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
               Tessellator tessellator = Tessellator.getTessellator();
               WorldRenderer worldRenderer = tessellator.getWorldRenderer();
               worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry8);

               for (int index3 = 0; index3 < this.listArray[index][index2].size(); index3++) {
                  TertiaryEntityAdapter tertiaryEntityAdapter = (TertiaryEntityAdapter)this.listArray[index][index2].get(index3);

                  try {
                     if (flag || !(tertiaryEntityAdapter instanceof EntityEvokerAdapter)) {
                        tertiaryEntityAdapter.applyWorldRendererByWorldRenderer(worldRenderer, entity, factor, factor2, factor6, factor3, factor4, factor5);
                     }
                  } catch (Throwable throwable) {
                     CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Rendering Particle");
                     CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Particle being rendered");
                     crashReportCategory.applyText("Particle", new AngleCollector(this, tertiaryEntityAdapter));
                     crashReportCategory.applyText("Particle Type", new FovPool(this, index));
                     throw new SecondaryRuntimeExceptionAdapter(crashReport);
                  }
               }

               tessellator.updateState();
            }
         }
      }

      GlStateManager.depthMask(true);
      GlStateManager.enableBlend();
      GlStateManager.alphaFunc(516, 0.1F);
   }

   public void applyBlockPos(BlockPos blockPos, IBlockState blockState) {
      if (blockState.getBlock().getMaterial() != Material.coreItemEntry) {
         blockState = blockState.getBlock().processAction(blockState, this.world, blockPos.getX(), blockPos.getY(), blockPos.getZ());

         for (int index = 0; index < 4; index++) {
            for (int index2 = 0; index2 < 4; index2++) {
               for (int index3 = 0; index3 < 4; index3++) {
                  double offset = (double)blockPos.getX() + ((double)index + 0.5) / (double)4;
                  double offset2 = (double)blockPos.getY() + ((double)index2 + 0.5) / (double)4;
                  double offset3 = (double)blockPos.getZ() + ((double)index3 + 0.5) / (double)4;
                  this.applyTertiaryEntityAdapter2(
                     new EntityBoggedAdapter(
                           this.world,
                           offset,
                           offset2,
                           offset3,
                           offset - (double)blockPos.getX() - 0.5,
                           offset2 - (double)blockPos.getY() - 0.5,
                           offset3 - (double)blockPos.getZ() - 0.5,
                           blockState
                        )
                        .resolveEntityBoggedAdapter(blockPos)
                  );
               }
            }
         }
      }
   }

   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      PostMotionBroadcaster postMotionBroadcaster = (PostMotionBroadcaster)this.map.get(count);
      if (postMotionBroadcaster != null) {
         TertiaryEntityAdapter tertiaryEntityAdapter = postMotionBroadcaster.resolveTertiaryEntityAdapter(count, this.world, posX, posX2, posX3, posX4, posY, posZ, countArray);
         if (tertiaryEntityAdapter != null) {
            this.applyTertiaryEntityAdapter2(tertiaryEntityAdapter);
            return tertiaryEntityAdapter;
         }
      }

      return null;
   }

   private void applyList(List list2) {
      ArrayList list3 = Lists.newArrayList();
      long timestamp = System.currentTimeMillis();
      int size = list2.size();

      for (int index = 0; index < list2.size(); index++) {
         TertiaryEntityAdapter tertiaryEntityAdapter = (TertiaryEntityAdapter)list2.get(index);
         this.applyTertiaryEntityAdapterByTertiaryEntityAd(tertiaryEntityAdapter);
         if (tertiaryEntityAdapter.isDead) {
            list3.add(tertiaryEntityAdapter);
         }

         size--;
         if (System.currentTimeMillis() > timestamp + 20L) {
            break;
         }
      }

      if (size > 0) {
         int count = size;

         for (Iterator iterator = list2.iterator(); iterator.hasNext() && count > 0; count--) {
            ((TertiaryEntityAdapter)iterator.next()).updateState9();
            iterator.remove();
         }
      }

      list2.removeAll(list3);
   }

   private void applyTertiaryEntityAdapterByTertiaryEntityAd(TertiaryEntityAdapter tertiaryEntityAdapter) {
      try {
         tertiaryEntityAdapter.updateState();
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Ticking Particle");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Particle being ticked");
         int count = tertiaryEntityAdapter.getCount4();
         crashReportCategory.applyText("Particle", new BezierParser(this, tertiaryEntityAdapter));
         crashReportCategory.applyText("Particle Type", new CurveAdapter(this, count));
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   private void applyIndex(int count) {
      for (int index = 0; index < 2; index++) {
         this.applyList(this.listArray[count][index]);
      }
   }

   private void applyTertiaryEntityAdapter(TertiaryEntityAdapter tertiaryEntityAdapter, int count, int count2) {
      for (int index = 0; index < 4; index++) {
         if (this.listArray[index][count].contains(tertiaryEntityAdapter)) {
            this.listArray[index][count].remove(tertiaryEntityAdapter);
            this.listArray[index][count2].add(tertiaryEntityAdapter);
         }
      }
   }

   public void applyTertiaryEntityAdapter(TertiaryEntityAdapter tertiaryEntityAdapter) {
      this.applyTertiaryEntityAdapter(tertiaryEntityAdapter, 0, 1);
   }

   public void applyBlockPos(BlockPos blockPos, EnumFacing enumFacing) {
      IBlockState blockState = this.world.getBlockState(blockPos);
      Block block = blockState.getBlock();
      if (block.processAction13() != -1) {
         int coordX = blockPos.getX();
         int coordY = blockPos.getY();
         int coordZ = blockPos.getZ();
         float factor = 0.1F;
         double offset = (double)coordX
            + this.random.nextDouble() * (block.getCoordOffset9() - block.getCoordOffset() - (double)(factor * 2.0F))
            + (double)factor
            + block.getCoordOffset();
         double offset2 = (double)coordY
            + this.random.nextDouble() * (block.getCoordOffset6() - block.getCoordOffset11() - (double)(factor * 2.0F))
            + (double)factor
            + block.getCoordOffset11();
         double offset3 = (double)coordZ
            + this.random.nextDouble() * (block.getCoordOffset15() - block.getCoordOffset2() - (double)(factor * 2.0F))
            + (double)factor
            + block.getCoordOffset2();
         if (enumFacing == EnumFacing.DOWN) {
            offset2 = (double)coordY + block.getCoordOffset11() - (double)factor;
         }

         if (enumFacing == EnumFacing.UP) {
            offset2 = (double)coordY + block.getCoordOffset6() + (double)factor;
         }

         if (enumFacing == EnumFacing.NORTH) {
            offset3 = (double)coordZ + block.getCoordOffset2() - (double)factor;
         }

         if (enumFacing == EnumFacing.SOUTH) {
            offset3 = (double)coordZ + block.getCoordOffset15() + (double)factor;
         }

         if (enumFacing == EnumFacing.WEST) {
            offset = (double)coordX + block.getCoordOffset() - (double)factor;
         }

         if (enumFacing == EnumFacing.EAST) {
            offset = (double)coordX + block.getCoordOffset9() + (double)factor;
         }

         this.applyTertiaryEntityAdapter2(
            new EntityBoggedAdapter(this.world, offset, offset2, offset3, 0.0, 0.0, 0.0, blockState)
               .resolveEntityBoggedAdapter(blockPos)
               .resolveTertiaryEntityAdapter(0.2F)
               .resolveTertiaryEntityAdapterByFloat(0.6F)
         );
      }
   }

   public void applyTertiaryEntityAdapter3(TertiaryEntityAdapter tertiaryEntityAdapter) {
      this.applyTertiaryEntityAdapter(tertiaryEntityAdapter, 1, 0);
   }

   private void updateState2() {
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.EXPLOSION_NORMAL.getIndex(), new InterpolationSerializer());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.WATER_BUBBLE.getIndex(), new AimBuffer());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.WATER_SPLASH.getIndex(), new CompositeClientManager());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.WATER_WAKE.getIndex(), new AimSerializer());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.WATER_DROP.getIndex(), new BezierChecker());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SUSPENDED.getIndex(), new ListenerAdapter19());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SUSPENDED_DEPTH.getIndex(), new GeometryEvaluator());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.CRIT.getIndex(), new FovSystem());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.CRIT_MAGIC.getIndex(), new InterpolationRegistry());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SMOKE_NORMAL.getIndex(), new AdvancedListenerBridge());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SMOKE_LARGE.getIndex(), new TargetTracker());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SPELL.getIndex(), new GlobalClientManager());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SPELL_INSTANT.getIndex(), new ComplexClientManager());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SPELL_MOB.getIndex(), new InterpolationProvider());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SPELL_MOB_AMBIENT.getIndex(), new SpecialListenerBridge());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SPELL_WITCH.getIndex(), new VectorAdapter());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.DRIP_WATER.getIndex(), new VirtualClientManager());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.DRIP_LAVA.getIndex(), new AuxiliaryListenerResolver());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.VILLAGER_ANGRY.getIndex(), new TertiaryListenerHandler());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.VILLAGER_HAPPY.getIndex(), new DistanceCollector());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.TOWN_AURA.getIndex(), new GeometryEvaluator());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.NOTE.getIndex(), new BezierCalculator());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.PORTAL.getIndex(), new FovDispatcher());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.ENCHANTMENT_TABLE.getIndex(), new CommonListenerBridge());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.FLAME.getIndex(), new IndirectClientManager());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.LAVA.getIndex(), new StandardListenerBridge());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.FOOTSTEP.getIndex(), new GeometryChecker());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.CLOUD.getIndex(), new CustomListenerBridge());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.REDSTONE.getIndex(), new VectorPredictor());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SNOWBALL.getIndex(), new InterpolationWriter());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SNOW_SHOVEL.getIndex(), new BaseListenerBridge());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.SLIME.getIndex(), new PassiveClientManager());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.HEART.getIndex(), new SecondaryListenerDispatcher());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.BARRIER.getIndex(), new AngleCoordinator());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.ITEM_CRACK.getIndex(), new InternalListenerTracker());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.BLOCK_CRACK.getIndex(), new InterpolationTransformer());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.BLOCK_DUST.getIndex(), new AimMatcher());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.EXPLOSION_HUGE.getIndex(), new FovPicker());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.EXPLOSION_LARGE.getIndex(), new ExtendedListenerDispatcher());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.FIREWORKS_SPARK.getIndex(), new AngleDispatcher());
      this.applyPostMotionBroadcaster(AttackEffectsExplosionNormalType.MOB_APPEARANCE.getIndex(), new TargetProfiler());
   }

   public void applyEntity3(Entity entity, float factor) {
      float factor2 = MathHelper.cos(entity.rotationYaw * (float) (Math.PI / 180.0));
      float factor3 = MathHelper.abs(entity.rotationYaw * (float) (Math.PI / 180.0));
      float factor4 = -factor3 * MathHelper.abs(entity.rotationPitch * (float) (Math.PI / 180.0));
      float factor5 = factor2 * MathHelper.abs(entity.rotationPitch * (float) (Math.PI / 180.0));
      float factor6 = MathHelper.cos(entity.rotationPitch * (float) (Math.PI / 180.0));

      for (int index = 0; index < 2; index++) {
         List list2 = this.listArray[3][index];
         if (!list2.isEmpty()) {
            WorldRenderer worldRenderer = Tessellator.getTessellator().getWorldRenderer();

            for (int index2 = 0; index2 < list2.size(); index2++) {
               ((TertiaryEntityAdapter)list2.get(index2)).applyWorldRendererByWorldRenderer(worldRenderer, entity, factor, factor2, factor6, factor3, factor4, factor5);
            }
         }
      }
   }

   public void applyEntity3(Entity entity, AttackEffectsExplosionNormalType attackEffectsExplosionNormalType) {
      this.list.add(new HitBoxBridge(this.world, entity, attackEffectsExplosionNormalType));
   }

   public void applyPostMotionBroadcaster(int count, PostMotionBroadcaster postMotionBroadcaster) {
      this.map.put(count, postMotionBroadcaster);
   }

   public void applyWorldByWorld(World world2) {
      this.world = world2;

      for (int index = 0; index < 4; index++) {
         for (int index2 = 0; index2 < 2; index2++) {
            this.listArray[index][index2].clear();
         }
      }

      this.list.clear();
   }

   public void applyTertiaryEntityAdapter2(TertiaryEntityAdapter tertiaryEntityAdapter) {
      if (tertiaryEntityAdapter != null && (!(tertiaryEntityAdapter instanceof EntityHuskAdapter) || BuildManager.isActive())) {
         int count = tertiaryEntityAdapter.getCount4();
         int count2 = tertiaryEntityAdapter.getFactor9() != 1.0F ? 0 : 1;
         if (this.listArray[count][count2].size() >= 4000) {
            this.listArray[count][count2].remove(0);
         }

         this.listArray[count][count2].add(tertiaryEntityAdapter);
      }
   }

   public void updateState() {
      for (int index = 0; index < 4; index++) {
         this.applyIndex(index);
      }

      ArrayList list2 = Lists.newArrayList();

      for (Object hitBoxBridgeObj : this.list) {
         HitBoxBridge hitBoxBridge = (HitBoxBridge)hitBoxBridgeObj;
         hitBoxBridge.updateState();
         if (hitBoxBridge.isDead) {
            list2.add(hitBoxBridge);
         }
      }

      this.list.removeAll(list2);
   }

   public void applyEntity4(Entity entity, AttackEffectsExplosionNormalType attackEffectsExplosionNormalType, float factor) {
      this.list.add(new HitBoxBridge(this.world, entity, attackEffectsExplosionNormalType, factor));
   }

   public String getText() {
      int count = 0;

      for (int index = 0; index < 4; index++) {
         for (int index2 = 0; index2 < 2; index2++) {
            count += this.listArray[index][index2].size();
         }
      }

      return "" + count;
   }

   public EffectRenderer(World world2, TextureManager textureManager2) {
      this.world = world2;
      this.textureManager = textureManager2;

      for (int index = 0; index < 4; index++) {
         this.listArray[index] = new List[2];

         for (int index2 = 0; index2 < 2; index2++) {
            this.listArray[index][index2] = Lists.newArrayList();
         }
      }

      this.updateState2();
   }
}
