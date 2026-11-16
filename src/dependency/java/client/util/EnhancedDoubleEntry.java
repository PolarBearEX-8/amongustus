package client.util;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.MovementNumberProperty;
import net.minecraft.block.BlockLitFurnace;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EnhancedDoubleEntry extends EntityMinecart {
   public int count14;
   public double value26;
   public double value30;

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      if (!damageSource.isEnabled5() && this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
         this.resolveEntityItem(new ItemStack(Blocks.block39, 1), 0.0F);
      }
   }

   @Override
   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() == Items.coal) {
         if (!player.invulnerableManager.enabled2 && --slot.animationsToGo == 0) {
            player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
         }

         this.count14 += 3600;
      }

      this.value26 = this.posX - player.posX;
      this.value30 = this.posZ - player.posZ;
      return true;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.value26 = tagCompound.computeValue("PushX");
      this.value30 = tagCompound.computeValue("PushZ");
      this.count14 = tagCompound.processValue("Fuel");
   }

   @Override
   public MovementNumberProperty getFURNACE() {
      return MovementNumberProperty.FURNACE;
   }

   @Override
   public double getValue2() {
      return 0.2;
   }

   public boolean isActive16() {
      return (this.dataWatcher.computeByteCode(16) & 1) != 0;
   }

   @Override
   public IBlockState getIBlockState() {
      return (this.isActive16() ? Blocks.block88 : Blocks.block39).getDefaultState().resolveIBlockState(BlockLitFurnace.autoWalkPath, EnumFacing.NORTH);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("PushX", this.value26);
      tagCompound.put("PushZ", this.value30);
      tagCompound.put("Fuel", (short)this.count14);
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, IBlockState blockState) {
      super.applyBlockPos(blockPos, blockState);
      double offset = this.value26 * this.value26 + this.value30 * this.value30;
      if (offset > 1.0E-4 && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001) {
         double offset2 = (double)MathHelper.sqrt_double(offset);
         this.value26 /= offset2;
         this.value30 /= offset2;
         if (this.value26 * this.motionX + this.value30 * this.motionZ < 0.0) {
            this.value26 = 0.0;
            this.value30 = 0.0;
         } else {
            double offset3 = offset2 / this.getValue2();
            this.value26 *= offset3;
            this.value30 *= offset3;
         }
      }
   }

   @Override
   public void updateState3() {
      double offset = this.value26 * this.value26 + this.value30 * this.value30;
      if (offset > 1.0E-4) {
         double offset2 = (double)MathHelper.sqrt_double(offset);
         this.value26 /= offset2;
         this.value30 /= offset2;
         double offset3 = 1.0;
         this.motionX *= 0.8F;
         this.motionY *= 0.0;
         this.motionZ *= 0.8F;
         this.motionX = this.motionX + this.value26 * offset3;
         this.motionZ = this.motionZ + this.value30 * offset3;
      } else {
         this.motionX *= 0.98F;
         this.motionY *= 0.0;
         this.motionZ *= 0.98F;
      }

      super.updateState3();
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.count14 > 0) {
         this.count14--;
      }

      if (this.count14 <= 0) {
         this.value26 = this.value30 = 0.0;
      }

      this.setEnabled6(this.count14 > 0);
      if (this.isActive16() && this.random.nextInt(4) == 0) {
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.SMOKE_LARGE, this.posX, this.posY + 0.8, this.posZ, 0.0, 0.0, 0.0, new int[0]
            );
      }
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)0);
   }

   public void setEnabled6(boolean enabled) {
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(16, (byte)(this.dataWatcher.computeByteCode(16) | 1));
      } else {
         this.dataWatcher.applyTargetByTarget(16, (byte)(this.dataWatcher.computeByteCode(16) & -2));
      }
   }

   public EnhancedDoubleEntry(World world) {
      super(world);
   }

   public EnhancedDoubleEntry(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }
}
