package net.minecraft.entity;

import client.event.SystemListener;
import client.util.AdvancedClientManagerAdapter;
import client.util.BaseClientManagerAdapter;
import client.util.ExtendedClassEntry;
import client.util.HitBoxBuilder;
import client.util.PathfindManagerAdapter;
import client.util.VectorPipeline;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.PassiveItemEntry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySnowman extends HitBoxBuilder implements SystemListener {
   @Override
   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      EntitySnowball entitySnowball = new EntitySnowball(this.worldObj, this);
      double offset = livingEntity.posY + (double)livingEntity.getHeight2() - 1.1F;
      double offset2 = livingEntity.posX - this.posX;
      double offset3 = offset - entitySnowball.posY;
      double offset4 = livingEntity.posZ - this.posZ;
      float factor2 = MathHelper.sqrt_double(offset2 * offset2 + offset4 * offset4) * 0.2F;
      entitySnowball.applyDouble(offset2, offset3 + (double)factor2, offset4, 1.6F, 12.0F);
      this.applyText("random.bow", 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
      this.worldObj.matchesEntity(entitySnowball);
   }

   @Override
   public float getHeight2() {
      return 1.7F;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(4.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.2F);
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(16);

      for (int index = 0; index < count2; index++) {
         this.resolveEntityItem(Items.snowball, 1);
      }
   }

   @Override
   public Item getGunpowder() {
      return Items.snowball;
   }

   @Override
   public void updateState33() {
      super.updateState33();
      if (!this.worldObj.state) {
         int count = MathHelper.ceiling_double_int(this.posX);
         int count2 = MathHelper.ceiling_double_int(this.posY);
         int count3 = MathHelper.ceiling_double_int(this.posZ);
         if (this.isActive11()) {
            this.matchesDamageSource(DamageSource.damageSource, 1.0F);
         }

         if (this.worldObj.resolveBiomeIDIsOutOfBoundsManager(new BlockPos(count, 0, count3)).computeFactor(new BlockPos(count, count2, count3)) > 1.0F) {
            this.matchesDamageSource(DamageSource.damageSource9, 1.0F);
         }

         for (int index = 0; index < 4; index++) {
            int count4 = MathHelper.ceiling_double_int(this.posX + (double)((float)(index % 2 * 2 - 1) * 0.25F));
            int count5 = MathHelper.ceiling_double_int(this.posY);
            int count6 = MathHelper.ceiling_double_int(this.posZ + (double)((float)(index / 2 % 2 * 2 - 1) * 0.25F));
            BlockPos blockPos = new BlockPos(count4, count5, count6);
            if (this.worldObj.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry
               && this.worldObj.resolveBiomeIDIsOutOfBoundsManager(new BlockPos(count4, 0, count6)).computeFactor(blockPos) < 0.8F
               && Blocks.block74.processAction7(this.worldObj, blockPos)) {
               this.worldObj.matchesBlockPos(blockPos, Blocks.block74.getDefaultState());
            }
         }
      }
   }

   public EntitySnowman(World world) {
      super(world);
      this.applyFloat(0.7F, 1.9F);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.goalSetupManager.add(1, new PassiveItemEntry(this, 1.25, 20, 10.0F));
      this.goalSetupManager.add(2, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(3, new BaseClientManagerAdapter(this, EntityPlayer.class, 6.0F));
      this.goalSetupManager.add(4, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new ExtendedClassEntry(this, EntityMob.class, 10, true, false, VectorPipeline.predicate2));
   }
}
