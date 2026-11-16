package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import client.util.WMAZOA32EnchantmentManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class BlockBreakState {
   public final boolean enabled;
   public final boolean enabled2;
   public final Random random = new Random();
   public final World world;
   public final double value;
   public final double value2;
   public final double value3;
   public final Entity entity;
   public final float factor;
   public final List list = Lists.newArrayList();
   public final Map map = Maps.newHashMap();

   public BlockBreakState(World world2, Entity entity2, double posX, double posY, double posZ, float factor2, boolean flag, boolean flag2, List list2) {
      this(world2, entity2, posX, posY, posZ, factor2, flag, flag2);
      this.list.addAll(list2);
   }

   public void setEnabled(boolean enabled3) {
      this.world
         .applyText(
            this.value,
            this.value2,
            this.value3,
            "random.explode",
            4.0F,
            (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F
         );
      if (this.factor >= 2.0F && this.enabled2) {
         this.world
            .applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.EXPLOSION_HUGE, this.value, this.value2, this.value3, 1.0, 0.0, 0.0, new int[0]);
      } else {
         this.world
            .applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.EXPLOSION_LARGE, this.value, this.value2, this.value3, 1.0, 0.0, 0.0, new int[0]);
      }

      if (this.enabled2) {
         for (BlockPos blockPos : (Iterable<BlockPos>)(Iterable<?>)this.list) {
            Block block = this.world.getBlockState(blockPos).getBlock();
            if (enabled3) {
               double offset = (double)((float)blockPos.getX() + this.world.random.nextFloat());
               double offset2 = (double)((float)blockPos.getY() + this.world.random.nextFloat());
               double offset3 = (double)((float)blockPos.getZ() + this.world.random.nextFloat());
               double offset4 = offset - this.value;
               double offset5 = offset2 - this.value2;
               double offset6 = offset3 - this.value3;
               double offset7 = (double)MathHelper.sqrt_double(offset4 * offset4 + offset5 * offset5 + offset6 * offset6);
               double offset8 = offset4 / offset7;
               double offset9 = offset5 / offset7;
               double offset10 = offset6 / offset7;
               double offset11 = 0.5 / (offset7 / (double)this.factor + 0.1) * (double)(this.world.random.nextFloat() * this.world.random.nextFloat() + 0.3F);
               double offset12 = offset8 * offset11;
               double offset13 = offset9 * offset11;
               double offset14 = offset10 * offset11;
               this.world
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.EXPLOSION_NORMAL,
                     (offset + this.value * 1.0) / 2.0,
                     (offset2 + this.value2 * 1.0) / 2.0,
                     (offset3 + this.value3 * 1.0) / 2.0,
                     offset12,
                     offset13,
                     offset14,
                     new int[0]
                  );
               this.world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset, offset2, offset3, offset12, offset13, offset14, new int[0]);
            }

            if (block.getMaterial() != Material.coreItemEntry) {
               if (block.processAction(this)) {
                  block.applyWorld3(this.world, blockPos, this.world.getBlockState(blockPos), 1.0F / this.factor, 0);
               }

               this.world.matchesBlockPos(blockPos, Blocks.air.getDefaultState(), 3);
               block.onNeighborBlockChange(this.world, blockPos, this);
            }
         }
      }

      if (this.enabled) {
         for (BlockPos blockPos2 : (Iterable<BlockPos>)(Iterable<?>)this.list) {
            if (this.world.getBlockState(blockPos2).getBlock().getMaterial() == Material.coreItemEntry
               && this.world.getBlockState(blockPos2.down()).getBlock().isState18()
               && this.random.nextInt(3) == 0) {
               this.world.matchesBlockPos(blockPos2, Blocks.blockFire.getDefaultState());
            }
         }
      }
   }

   public List getList() {
      return this.list;
   }

   public BlockBreakState(World world2, Entity entity2, double posX, double posY, double posZ, float factor2, boolean flag, boolean flag2) {
      this.world = world2;
      this.entity = entity2;
      this.factor = factor2;
      this.value = posX;
      this.value2 = posY;
      this.value3 = posZ;
      this.enabled = flag;
      this.enabled2 = flag2;
   }

   public BlockBreakState(World world2, Entity entity2, double posX, double posY, double posZ, float factor2, List list2) {
      this(world2, entity2, posX, posY, posZ, factor2, false, true, list2);
   }

   public void clear() {
      this.list.clear();
   }

   public void updateState() {
      HashSet set = Sets.newHashSet();

      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            for (int index3 = 0; index3 < 16; index3++) {
               if (index == 0 || index == 15 || index2 == 0 || index2 == 15 || index3 == 0 || index3 == 15) {
                  double offset = (double)((float)index / 15.0F * 2.0F - 1.0F);
                  double offset2 = (double)((float)index2 / 15.0F * 2.0F - 1.0F);
                  double offset3 = (double)((float)index3 / 15.0F * 2.0F - 1.0F);
                  double offset4 = Math.sqrt(offset * offset + offset2 * offset2 + offset3 * offset3);
                  double offset5 = offset / offset4;
                  double offset6 = offset2 / offset4;
                  double offset7 = offset3 / offset4;
                  float factor2 = this.factor * (0.7F + this.world.random.nextFloat() * 0.6F);
                  double value4 = this.value;
                  double value5 = this.value2;

                  for (double value6 = this.value3; factor2 > 0.0F; factor2 -= 0.22500001F) {
                     BlockPos blockPos = new BlockPos(value4, value5, value6);
                     IBlockState blockState = this.world.getBlockState(blockPos);
                     if (blockState.getBlock().getMaterial() != Material.coreItemEntry) {
                        float factor3 = this.entity != null
                           ? this.entity.computeFactor(this, this.world, blockPos, blockState)
                           : blockState.getBlock().processAction((Entity)null);
                        factor2 -= (factor3 + 0.3F) * 0.3F;
                     }

                     if (factor2 > 0.0F && (this.entity == null || this.entity.matchesBlockBreakState(this, this.world, blockPos, blockState, factor2))) {
                        set.add(blockPos);
                     }

                     value4 += offset5 * 0.3F;
                     value5 += offset6 * 0.3F;
                     value6 += offset7 * 0.3F;
                  }
               }
            }
         }
      }

      this.list.addAll(set);
      float factor4 = this.factor * 2.0F;
      int count = MathHelper.ceiling_double_int(this.value - (double)factor4 - 1.0);
      int count2 = MathHelper.ceiling_double_int(this.value + (double)factor4 + 1.0);
      int count3 = MathHelper.ceiling_double_int(this.value2 - (double)factor4 - 1.0);
      int count4 = MathHelper.ceiling_double_int(this.value2 + (double)factor4 + 1.0);
      int count5 = MathHelper.ceiling_double_int(this.value3 - (double)factor4 - 1.0);
      int count6 = MathHelper.ceiling_double_int(this.value3 + (double)factor4 + 1.0);
      List list2 = this.world
         .resolveListByEntity(this.entity, new AxisAlignedBB((double)count, (double)count3, (double)count5, (double)count2, (double)count4, (double)count6));

      for (int index4 = 0; index4 < list2.size(); index4++) {
         Entity entity2 = (Entity)list2.get(index4);
         if (!entity2.canExecute4()) {
            double offset8 = entity2.getDouble4(this.value, this.value2, this.value3) / (double)factor4;
            if (offset8 <= 1.0) {
               double offset9 = entity2.posX - this.value;
               double offset10 = entity2.posY + (double)entity2.getHeight2() - this.value2;
               double offset11 = entity2.posZ - this.value3;
               double offset12 = (double)MathHelper.sqrt_double(offset9 * offset9 + offset10 * offset10 + offset11 * offset11);
               if (offset12 != 0.0) {
                  double offset13 = offset9 / offset12;
                  double offset14 = offset10 / offset12;
                  double offset15 = offset11 / offset12;
                  double scaleFactor = (double)this.world.getScaleFactor(this.value, this.value2, this.value3, entity2.getBoundingBox2());
                  double offset16 = (1.0 - offset8) * scaleFactor;
                  entity2.matchesDamageSource(DamageSource.resolveDamageSource(this), (float)((int)((offset16 * offset16 + offset16) / 2.0 * 8.0 * (double)factor4 + 1.0)));
                  double offset17 = WMAZOA32EnchantmentManager.computeValueByEntity(entity2, offset16);
                  entity2.motionX += offset13 * offset17;
                  entity2.motionY += offset14 * offset17;
                  entity2.motionZ += offset15 * offset17;
                  if (entity2 instanceof EntityPlayer && !((EntityPlayer)entity2).invulnerableManager.enabled) {
                     this.map.put((EntityPlayer)entity2, new Vec3(offset13 * offset16, offset14 * offset16, offset15 * offset16));
                  }
               }
            }
         }
      }
   }

   public EntityLivingBase getEntityLivingBase() {
      return this.entity == null
         ? null
         : (
            this.entity instanceof EntityPrimedtnt
               ? ((EntityPrimedtnt)this.entity).getEntityLivingBase()
               : (this.entity instanceof EntityLivingBase ? (EntityLivingBase)this.entity : null)
         );
   }

   public Map getMap() {
      return this.map;
   }
}
