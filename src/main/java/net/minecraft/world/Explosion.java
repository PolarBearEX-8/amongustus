package net.minecraft.world;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Explosion {
   private final boolean isFlaming;
   private final boolean isSmoking;
   private final Random explosionRNG = new Random();
   private final World worldObj;
   private final double explosionX;
   private final double explosionY;
   private final double explosionZ;
   private final Entity exploder;
   private final float explosionSize;
   private final List affectedBlockPositions = Lists.newArrayList();
   private final Map playerKnockbackMap = Maps.newHashMap();

   public void doExplosionB(boolean flag) {
      this.worldObj
         .playSoundEffect(
            this.explosionX,
            this.explosionY,
            this.explosionZ,
            "random.explode",
            4.0F,
            (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F
         );
      if (this.explosionSize >= 2.0F && this.isSmoking) {
         this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.explosionX, this.explosionY, this.explosionZ, 1.0, 0.0, 0.0, new int[0]);
      } else {
         this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.explosionX, this.explosionY, this.explosionZ, 1.0, 0.0, 0.0, new int[0]);
      }

      if (this.isSmoking) {
         for (BlockPos pos : (Iterable<BlockPos>)(this.affectedBlockPositions)) {
            Block block = this.worldObj.getBlockState(pos).getBlock();
            if (flag) {
               double d = (double)((float)pos.getX() + this.worldObj.rand.nextFloat());
               double d2 = (double)((float)pos.getY() + this.worldObj.rand.nextFloat());
               double d3 = (double)((float)pos.getZ() + this.worldObj.rand.nextFloat());
               double d4 = d - this.explosionX;
               double d5 = d2 - this.explosionY;
               double d6 = d3 - this.explosionZ;
               double d7 = (double)MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6);
               d4 /= d7;
               d5 /= d7;
               d6 /= d7;
               double d8 = 0.5 / (d7 / (double)this.explosionSize + 0.1);
               d8 *= (double)(this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
               d4 *= d8;
               d5 *= d8;
               d6 *= d8;
               this.worldObj
                  .spawnParticle(
                     EnumParticleTypes.EXPLOSION_NORMAL,
                     (d + this.explosionX * 1.0) / 2.0,
                     (d2 + this.explosionY * 1.0) / 2.0,
                     (d3 + this.explosionZ * 1.0) / 2.0,
                     d4,
                     d5,
                     d6,
                     new int[0]
                  );
               this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d2, d3, d4, d5, d6, new int[0]);
            }

            if (block.getMaterial() != Material.air) {
               if (block.canDropFromExplosion(this)) {
                  block.dropBlockAsItemWithChance(this.worldObj, pos, this.worldObj.getBlockState(pos), 1.0F / this.explosionSize, 0);
               }

               this.worldObj.setBlockState(pos, Blocks.air.getDefaultState(), 3);
               block.onBlockDestroyedByExplosion(this.worldObj, pos, this);
            }
         }
      }

      if (this.isFlaming) {
         for (BlockPos pos2 : (Iterable<BlockPos>)(this.affectedBlockPositions)) {
            if (this.worldObj.getBlockState(pos2).getBlock().getMaterial() == Material.air
               && this.worldObj.getBlockState(pos2.down()).getBlock().isFullBlock()
               && this.explosionRNG.nextInt(3) == 0) {
               this.worldObj.setBlockState(pos2, Blocks.fire.getDefaultState());
            }
         }
      }
   }

   public EntityLivingBase getExplosivePlacedBy() {
      return this.exploder == null
         ? null
         : (
            this.exploder instanceof EntityTNTPrimed
               ? ((EntityTNTPrimed)this.exploder).getTntPlacedBy()
               : (this.exploder instanceof EntityLivingBase ? (EntityLivingBase)this.exploder : null)
         );
   }

   public Explosion(World world, Entity entity, double d, double d2, double d3, float f, List list) {
      this(world, entity, d, d2, d3, f, false, true, list);
   }

   public void doExplosionA() {
      HashSet set = Sets.newHashSet();

      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            for (int k = 0; k < 16; k++) {
               if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15) {
                  double d = (double)((float)i / 15.0F * 2.0F - 1.0F);
                  double d2 = (double)((float)j / 15.0F * 2.0F - 1.0F);
                  double d3 = (double)((float)k / 15.0F * 2.0F - 1.0F);
                  double d4 = Math.sqrt(d * d + d2 * d2 + d3 * d3);
                  d /= d4;
                  d2 /= d4;
                  d3 /= d4;
                  float f = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
                  double explosionX2 = this.explosionX;
                  double explosionY2 = this.explosionY;
                  double explosionZ2 = this.explosionZ;

                  for (float m = 0.3F; f > 0.0F; f -= 0.22500001F) {
                     BlockPos pos = new BlockPos(explosionX2, explosionY2, explosionZ2);
                     IBlockState blockState = this.worldObj.getBlockState(pos);
                     if (blockState.getBlock().getMaterial() != Material.air) {
                        float f2 = this.exploder != null
                           ? this.exploder.getExplosionResistance(this, this.worldObj, pos, blockState)
                           : blockState.getBlock().getExplosionResistance((Entity)null);
                        f -= (f2 + 0.3F) * 0.3F;
                     }

                     if (f > 0.0F && (this.exploder == null || this.exploder.verifyExplosion(this, this.worldObj, pos, blockState, f))) {
                        set.add(pos);
                     }

                     explosionX2 += d * 0.3F;
                     explosionY2 += d2 * 0.3F;
                     explosionZ2 += d3 * 0.3F;
                  }
               }
            }
         }
      }

      this.affectedBlockPositions.addAll(set);
      float f3 = this.explosionSize * 2.0F;
      int n = MathHelper.floor_double(this.explosionX - (double)f3 - 1.0);
      int n2 = MathHelper.floor_double(this.explosionX + (double)f3 + 1.0);
      int n3 = MathHelper.floor_double(this.explosionY - (double)f3 - 1.0);
      int n4 = MathHelper.floor_double(this.explosionY + (double)f3 + 1.0);
      int n5 = MathHelper.floor_double(this.explosionZ - (double)f3 - 1.0);
      int n6 = MathHelper.floor_double(this.explosionZ + (double)f3 + 1.0);
      List list = this.worldObj
         .getEntitiesWithinAABBExcludingEntity(
            this.exploder, new AxisAlignedBB((double)n, (double)n3, (double)n5, (double)n2, (double)n4, (double)n6)
         );

      for (int idx = 0; idx < list.size(); idx++) {
         Entity entity = (Entity)list.get(idx);
         if (!entity.isImmuneToExplosions()) {
            double d5 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)f3;
            if (d5 <= 1.0) {
               double d6 = entity.posX - this.explosionX;
               double d7 = entity.posY + (double)entity.getEyeHeight() - this.explosionY;
               double d8 = entity.posZ - this.explosionZ;
               double d9 = (double)MathHelper.sqrt_double(d6 * d6 + d7 * d7 + d8 * d8);
               if (d9 != 0.0) {
                  d6 /= d9;
                  d7 /= d9;
                  d8 /= d9;
                  double blockDensity = (double)this.worldObj.getBlockDensity(this.explosionX, this.explosionY, this.explosionZ, entity.getEntityBoundingBox());
                  double d10 = (1.0 - d5) * blockDensity;
                  entity.attackEntityFrom(DamageSource.setExplosionSource(this), (float)((int)((d10 * d10 + d10) / 2.0 * 8.0 * (double)f3 + 1.0)));
                  double blastDamageReduction = EnchantmentProtection.getBlastDamageReduction(entity, d10);
                  entity.motionX += d6 * blastDamageReduction;
                  entity.motionY += d7 * blastDamageReduction;
                  entity.motionZ += d8 * blastDamageReduction;
                  if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.disableDamage) {
                     this.playerKnockbackMap.put((EntityPlayer)entity, new Vec3(d6 * d10, d7 * d10, d8 * d10));
                  }
               }
            }
         }
      }
   }

   public void clearAffectedBlockPositions() {
      this.affectedBlockPositions.clear();
   }

   public Explosion(World world, Entity entity, double d, double d2, double d3, float f, boolean flag, boolean flag2, List list) {
      this(world, entity, d, d2, d3, f, flag, flag2);
      this.affectedBlockPositions.addAll(list);
   }

   public Map getPlayerKnockbackMap() {
      return this.playerKnockbackMap;
   }

   public List getAffectedBlockPositions() {
      return this.affectedBlockPositions;
   }

   public Explosion(World world, Entity entity, double d, double d2, double d3, float f, boolean flag, boolean flag2) {
      this.worldObj = world;
      this.exploder = entity;
      this.explosionSize = f;
      this.explosionX = d;
      this.explosionY = d2;
      this.explosionZ = d3;
      this.isFlaming = flag;
      this.isSmoking = flag2;
   }
}
