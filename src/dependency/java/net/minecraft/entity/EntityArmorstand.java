package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.MovementNumberProperty;
import client.util.ManagedFloatEntry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityArmorstand extends EntityLivingBase {
   public static final ManagedFloatEntry managedFloatEntry9 = new ManagedFloatEntry(0.0F, 0.0F, 0.0F);
   public static final ManagedFloatEntry managedFloatEntry3 = new ManagedFloatEntry(0.0F, 0.0F, 0.0F);
   public static final ManagedFloatEntry managedFloatEntry4 = new ManagedFloatEntry(-10.0F, 0.0F, -10.0F);
   public static final ManagedFloatEntry managedFloatEntry11 = new ManagedFloatEntry(-15.0F, 0.0F, 10.0F);
   public static final ManagedFloatEntry managedFloatEntry12 = new ManagedFloatEntry(-1.0F, 0.0F, -1.0F);
   public static final ManagedFloatEntry managedFloatEntry2 = new ManagedFloatEntry(1.0F, 0.0F, 1.0F);
   public final ItemStack[] itemStackArray2 = new ItemStack[5];
   public boolean enabled21;
   public long timestamp2;
   public int count23;
   public boolean enabled22;
   public ManagedFloatEntry managedFloatEntry = managedFloatEntry9;
   public ManagedFloatEntry managedFloatEntry8 = managedFloatEntry3;
   public ManagedFloatEntry managedFloatEntry7 = managedFloatEntry4;
   public ManagedFloatEntry managedFloatEntry6 = managedFloatEntry11;
   public ManagedFloatEntry managedFloatEntry10 = managedFloatEntry12;
   public ManagedFloatEntry managedFloatEntry5 = managedFloatEntry2;

   public void applyManagedFloatEntry3(ManagedFloatEntry managedFloatEntry13) {
      this.managedFloatEntry5 = managedFloatEntry13;
      this.dataWatcher.applyTargetByTarget(16, managedFloatEntry13);
   }

   @Override
   public boolean checkDouble(double offset) {
      double offset2 = this.getBoundingBox2().getValue() * 4.0;
      if (Double.isNaN(offset2) || offset2 == 0.0) {
         offset2 = 4.0;
      }

      double offset3 = offset2 * 64.0;
      return offset < offset3 * offset3;
   }

   @Override
   public void applyEntity9(Entity entity) {
   }

   public void applyManagedFloatEntryByManagedFloatEntr(ManagedFloatEntry managedFloatEntry13) {
      this.managedFloatEntry8 = managedFloatEntry13;
      this.dataWatcher.applyTargetByTarget(12, managedFloatEntry13);
   }

   @Override
   public ItemStack getItemStack() {
      return this.itemStackArray2[0];
   }

   @Override
   public float getHeight2() {
      return this.isActive16() ? this.height * 0.5F : this.height * 0.9F;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("Equipment", 9)) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Equipment", 10);

         for (int index = 0; index < this.itemStackArray2.length; index++) {
            this.itemStackArray2[index] = ItemStack.resolveItemStack(extendedLoggerEntry.getNBTTagCompound(index));
         }
      }

      this.setEnabledByFlag(tagCompound.matchesText("Invisible"));
      this.setEnabled6(tagCompound.matchesText("Small"));
      this.setEnabled7(tagCompound.matchesText("ShowArms"));
      this.count23 = tagCompound.computeCount("DisabledSlots");
      this.setEnabled10(tagCompound.matchesText("NoGravity"));
      this.setEnabled5(tagCompound.matchesText("NoBasePlate"));
      this.setEnabled9(tagCompound.matchesText("Marker"));
      this.enabled22 = !this.isActive25();
      this.noClip = this.isActive26();
      NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("Pose");
      this.applyNBTTagCompound5(nBTTagCompound);
   }

   @Override
   public boolean matchesItemStack(int count, ItemStack stack) {
      int count2;
      if (count == 99) {
         count2 = 0;
      } else {
         count2 = count - 100 + 1;
         if (count2 < 0 || count2 >= this.itemStackArray2.length) {
            return false;
         }
      }

      if (stack == null || EntityMob.computeCount(stack) == count2 || count2 == 4 && stack.getItem() instanceof ItemBlock) {
         this.applyItemStack(count2, stack);
         return true;
      } else {
         return false;
      }
   }

   public void applyManagedFloatEntry(ManagedFloatEntry managedFloatEntry13) {
      this.managedFloatEntry10 = managedFloatEntry13;
      this.dataWatcher.applyTargetByTarget(15, managedFloatEntry13);
   }

   @Override
   public void setEnabledByFlag(boolean enabledByFlag) {
      this.enabled21 = enabledByFlag;
      super.setEnabledByFlag(enabledByFlag);
   }

   private void applyEntityPlayerByEntityPlayer(EntityPlayer player, int count) {
      ItemStack stack = this.itemStackArray2[count];
      if ((stack == null || (this.count23 & 1 << count + 8) == 0) && (stack != null || (this.count23 & 1 << count + 16) == 0)) {
         int currentItem = player.inventoryPlayer.currentItem;
         ItemStack stack2 = player.inventoryPlayer.processAction3(currentItem);
         if (player.invulnerableManager.enabled2 && (stack == null || stack.getItem() == Item.processAction2(Blocks.air)) && stack2 != null) {
            ItemStack stack3 = stack2.getItemStack();
            stack3.animationsToGo = 1;
            this.applyItemStack(count, stack3);
         } else if (stack2 == null || stack2.animationsToGo <= 1) {
            this.applyItemStack(count, stack2);
            player.inventoryPlayer.processAction(currentItem, stack);
         } else if (stack == null) {
            ItemStack stack4 = stack2.getItemStack();
            stack4.animationsToGo = 1;
            this.applyItemStack(count, stack4);
            stack2.animationsToGo--;
         }
      }
   }

   @Override
   public void applyItemStack(int count, ItemStack stack) {
      this.itemStackArray2[count] = stack;
   }

   @Override
   public boolean isActive18() {
      return super.isActive18() && !this.isActive26();
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player, Vec3 vec) {
      if (this.isActive25()) {
         return false;
      } else if (!this.worldObj.state && !player.isActive23()) {
         byte byteValue = 0;
         ItemStack stack = player.getHeldItem();
         boolean flag = stack != null;
         if (flag && stack.getItem() instanceof ItemGoldenBoots) {
            ItemGoldenBoots item = (ItemGoldenBoots)stack.getItem();
            if (item.indexOffset3 == 3) {
               byteValue = 1;
            } else if (item.indexOffset3 == 2) {
               byteValue = 2;
            } else if (item.indexOffset3 == 1) {
               byteValue = 3;
            } else if (item.indexOffset3 == 0) {
               byteValue = 4;
            }
         }

         if (flag && (stack.getItem() == Items.skull || stack.getItem() == Item.processAction2(Blocks.block146))) {
            byteValue = 4;
         }

         byte byteValue2 = 0;
         boolean active = this.isActive24();
         double offset = active ? vec.yCoord * 2.0 : vec.yCoord;
         if (offset >= 0.1 && offset < 0.1 + (active ? 0.8 : 0.45) && this.itemStackArray2[1] != null) {
            byteValue2 = 1;
         } else if (offset >= 0.9 + (active ? 0.3 : 0.0) && offset < 0.9 + (active ? 1.0 : 0.7) && this.itemStackArray2[3] != null) {
            byteValue2 = 3;
         } else if (offset >= 0.4 && offset < 0.4 + (active ? 1.0 : 0.8) && this.itemStackArray2[2] != null) {
            byteValue2 = 2;
         } else if (offset >= 1.6 && this.itemStackArray2[4] != null) {
            byteValue2 = 4;
         }

         boolean flag2 = this.itemStackArray2[byteValue2] != null;
         if ((this.count23 & 1 << byteValue2) != 0 || (this.count23 & 1 << byteValue) != 0) {
            byteValue2 = byteValue;
            if ((this.count23 & 1 << byteValue) != 0) {
               if ((this.count23 & 1) != 0) {
                  return true;
               }

               byteValue2 = 0;
            }
         }

         if (flag && byteValue == 0 && !this.isActive27()) {
            return true;
         } else {
            if (flag) {
               this.applyEntityPlayerByEntityPlayer(player, byteValue);
            } else if (flag2) {
               this.applyEntityPlayerByEntityPlayer(player, byteValue2);
            }

            return true;
         }
      } else {
         return true;
      }
   }

   public ManagedFloatEntry getManagedFloatEntry10() {
      return this.managedFloatEntry10;
   }

   @Override
   public boolean isActive9() {
      return false;
   }

   @Override
   public ItemStack[] getItemStackArray2() {
      return this.itemStackArray2;
   }

   public ManagedFloatEntry getManagedFloatEntry6() {
      return this.managedFloatEntry6;
   }

   private void applyNBTTagCompound5(NBTTagCompound tagCompound) {
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Head", 5);
      if (extendedLoggerEntry.getInt() > 0) {
         this.applyManagedFloatEntry2(new ManagedFloatEntry(extendedLoggerEntry));
      } else {
         this.applyManagedFloatEntry2(managedFloatEntry9);
      }

      NBTTagList extendedLoggerEntry2 = tagCompound.getExtendedLoggerEntry("Body", 5);
      if (extendedLoggerEntry2.getInt() > 0) {
         this.applyManagedFloatEntryByManagedFloatEntr(new ManagedFloatEntry(extendedLoggerEntry2));
      } else {
         this.applyManagedFloatEntryByManagedFloatEntr(managedFloatEntry3);
      }

      NBTTagList extendedLoggerEntry3 = tagCompound.getExtendedLoggerEntry("LeftArm", 5);
      if (extendedLoggerEntry3.getInt() > 0) {
         this.applyManagedFloatEntry5(new ManagedFloatEntry(extendedLoggerEntry3));
      } else {
         this.applyManagedFloatEntry5(managedFloatEntry4);
      }

      NBTTagList extendedLoggerEntry4 = tagCompound.getExtendedLoggerEntry("RightArm", 5);
      if (extendedLoggerEntry4.getInt() > 0) {
         this.applyManagedFloatEntry4(new ManagedFloatEntry(extendedLoggerEntry4));
      } else {
         this.applyManagedFloatEntry4(managedFloatEntry11);
      }

      NBTTagList extendedLoggerEntry5 = tagCompound.getExtendedLoggerEntry("LeftLeg", 5);
      if (extendedLoggerEntry5.getInt() > 0) {
         this.applyManagedFloatEntry(new ManagedFloatEntry(extendedLoggerEntry5));
      } else {
         this.applyManagedFloatEntry(managedFloatEntry12);
      }

      NBTTagList extendedLoggerEntry6 = tagCompound.getExtendedLoggerEntry("RightLeg", 5);
      if (extendedLoggerEntry6.getInt() > 0) {
         this.applyManagedFloatEntry3(new ManagedFloatEntry(extendedLoggerEntry6));
      } else {
         this.applyManagedFloatEntry3(managedFloatEntry2);
      }
   }

   public void applyManagedFloatEntry5(ManagedFloatEntry managedFloatEntry13) {
      this.managedFloatEntry7 = managedFloatEntry13;
      this.dataWatcher.applyTargetByTarget(13, managedFloatEntry13);
   }

   @Override
   public ItemStack resolveItemStack(int count) {
      return this.itemStackArray2[count];
   }

   private void applyFloat4(float factor) {
      float factor2 = this.getFloat2() - factor;
      if (factor2 <= 0.5F) {
         this.updateState4();
         this.updateState9();
      } else {
         this.applyFloat(factor2);
      }
   }

   public ManagedFloatEntry getManagedFloatEntry() {
      return this.managedFloatEntry;
   }

   public ManagedFloatEntry getManagedFloatEntry5() {
      return this.managedFloatEntry5;
   }

   @Override
   public float computeFactor(float factor, float factor2) {
      this.factor12 = this.prevRotationYaw;
      this.scaleFactor8 = this.rotationYaw;
      return 0.0F;
   }

   private NBTTagCompound getNBTTagCompoundAsNBTTagCompound() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      if (!managedFloatEntry9.equals(this.managedFloatEntry)) {
         tagCompound.applyText("Head", this.managedFloatEntry.getNBTTagList());
      }

      if (!managedFloatEntry3.equals(this.managedFloatEntry8)) {
         tagCompound.applyText("Body", this.managedFloatEntry8.getNBTTagList());
      }

      if (!managedFloatEntry4.equals(this.managedFloatEntry7)) {
         tagCompound.applyText("LeftArm", this.managedFloatEntry7.getNBTTagList());
      }

      if (!managedFloatEntry11.equals(this.managedFloatEntry6)) {
         tagCompound.applyText("RightArm", this.managedFloatEntry6.getNBTTagList());
      }

      if (!managedFloatEntry12.equals(this.managedFloatEntry10)) {
         tagCompound.applyText("LeftLeg", this.managedFloatEntry10.getNBTTagList());
      }

      if (!managedFloatEntry2.equals(this.managedFloatEntry5)) {
         tagCompound.applyText("RightLeg", this.managedFloatEntry5.getNBTTagList());
      }

      return tagCompound;
   }

   @Override
   public void applyFloat2(float factor, float factor2) {
      if (!this.isActive26()) {
         super.applyFloat2(factor, factor2);
      }
   }

   @Override
   public boolean isActive16() {
      return this.isActive24();
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      NBTTagList nBTTagList = new NBTTagList();

      for (int index = 0; index < this.itemStackArray2.length; index++) {
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         if (this.itemStackArray2[index] != null) {
            this.itemStackArray2[index].resolveNBTTagCompound(tagCompound2);
         }

         nBTTagList.applyLISTManager(tagCompound2);
      }

      tagCompound.applyText("Equipment", nBTTagList);
      if (this.isActive6() && (this.getText2() == null || this.getText2().length() == 0)) {
         tagCompound.applyText("CustomNameVisible", this.isActive6());
      }

      tagCompound.applyText("Invisible", this.isActive12());
      tagCompound.applyText("Small", this.isActive24());
      tagCompound.applyText("ShowArms", this.isActive27());
      tagCompound.put("DisabledSlots", this.count23);
      tagCompound.applyText("NoGravity", this.isActive26());
      tagCompound.applyText("NoBasePlate", this.isActive23());
      if (this.isActive25()) {
         tagCompound.applyText("Marker", this.isActive25());
      }

      tagCompound.applyText("Pose", this.getNBTTagCompoundAsNBTTagCompound());
   }

   public ManagedFloatEntry getManagedFloatEntry7() {
      return this.managedFloatEntry7;
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.worldObj.state) {
         return false;
      } else if (DamageSource.damageSource10.equals(damageSource)) {
         this.updateState9();
         return false;
      } else if (this.matchesDamageSource(damageSource) || this.enabled21 || this.isActive25()) {
         return false;
      } else if (damageSource.isEnabled5()) {
         this.updateState4();
         this.updateState9();
         return false;
      } else if (DamageSource.damageSource8.equals(damageSource)) {
         if (!this.canExecute2()) {
            this.applyIndexByIndex(5);
         } else {
            this.applyFloat4(0.15F);
         }

         return false;
      } else if (DamageSource.damageSource9.equals(damageSource) && this.getFloat2() > 0.5F) {
         this.applyFloat4(4.0F);
         return false;
      } else {
         boolean flag = "arrow".equals(damageSource.getLabelText());
         if (!"player".equals(damageSource.getLabelText()) && !flag) {
            return false;
         } else {
            if (damageSource.getEntityAsEntity() instanceof EntityArrow) {
               damageSource.getEntityAsEntity().updateState9();
            }

            if (damageSource.getEntity() instanceof EntityPlayer && !((EntityPlayer)damageSource.getEntity()).invulnerableManager.state1) {
               return false;
            } else if (damageSource.isActive()) {
               this.updateState5();
               this.updateState9();
               return false;
            } else {
               long timestamp = this.worldObj.getTimestamp2();
               if (timestamp - this.timestamp2 > 5L && !flag) {
                  this.timestamp2 = timestamp;
               } else {
                  this.updateState3();
                  this.updateState5();
                  this.updateState9();
               }

               return false;
            }
         }
      }
   }

   public void applyManagedFloatEntry4(ManagedFloatEntry managedFloatEntry13) {
      this.managedFloatEntry6 = managedFloatEntry13;
      this.dataWatcher.applyTargetByTarget(14, managedFloatEntry13);
   }

   @Override
   public ItemStack getItemStack3(int count) {
      return this.itemStackArray2[count + 1];
   }

   @Override
   public void updateState15() {
      this.updateState9();
   }

   public void applyManagedFloatEntry2(ManagedFloatEntry managedFloatEntry13) {
      this.managedFloatEntry = managedFloatEntry13;
      this.dataWatcher.applyTargetByTarget(11, managedFloatEntry13);
   }

   @Override
   public void updateState28() {
      this.setEnabledByFlag(this.enabled21);
   }

   public ManagedFloatEntry getManagedFloatEntry8() {
      return this.managedFloatEntry8;
   }

   @Override
   public boolean isActive5() {
      return super.isActive5() && !this.isActive25();
   }

   private void setEnabled7(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(10);
      byte byteValue2;
      if (enabled) {
         byteValue2 = (byte)(byteValue | 4);
      } else {
         byteValue2 = (byte)(byteValue & -5);
      }

      this.dataWatcher.applyTargetByTarget(10, byteValue2);
   }

   @Override
   public boolean canExecute4() {
      return this.isActive12();
   }

   private void updateState4() {
      for (int index = 0; index < this.itemStackArray2.length; index++) {
         if (this.itemStackArray2[index] != null && this.itemStackArray2[index].animationsToGo > 0) {
            if (this.itemStackArray2[index] != null) {
               Block.applyWorld(this.worldObj, new BlockPos(this).up(), this.itemStackArray2[index]);
            }

            this.itemStackArray2[index] = null;
         }
      }
   }

   public boolean isActive25() {
      return (this.dataWatcher.computeByteCode(10) & 16) != 0;
   }

   private void setEnabled10(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(10);
      byte byteValue2;
      if (enabled) {
         byteValue2 = (byte)(byteValue | 2);
      } else {
         byteValue2 = (byte)(byteValue & -3);
      }

      this.dataWatcher.applyTargetByTarget(10, byteValue2);
   }

   public boolean isActive26() {
      return (this.dataWatcher.computeByteCode(10) & 2) != 0;
   }

   private void setEnabled6(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(10);
      byte byteValue2;
      if (enabled) {
         byteValue2 = (byte)(byteValue | 1);
      } else {
         byteValue2 = (byte)(byteValue & -2);
      }

      this.dataWatcher.applyTargetByTarget(10, byteValue2);
   }

   public boolean isActive24() {
      return (this.dataWatcher.computeByteCode(10) & 1) != 0;
   }

   private void updateState5() {
      if (this.worldObj instanceof WorldServer) {
         ((WorldServer)this.worldObj)
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.BLOCK_DUST,
               this.posX,
               this.posY + (double)this.height / 1.5,
               this.posZ,
               10,
               (double)(this.width / 4.0F),
               (double)(this.height / 4.0F),
               (double)(this.width / 4.0F),
               0.05,
               new int[]{Block.processAction5(Blocks.block142.getDefaultState())}
            );
      }
   }

   private void updateState3() {
      Block.applyWorld(this.worldObj, new BlockPos(this), new ItemStack(Items.armorStand));
      this.updateState4();
   }

   @Override
   public void updateState() {
      super.updateState();
      ManagedFloatEntry managedFloatEntry13 = this.dataWatcher.resolveManagedFloatEntry(11);
      if (!this.managedFloatEntry.equals(managedFloatEntry13)) {
         this.applyManagedFloatEntry2(managedFloatEntry13);
      }

      ManagedFloatEntry managedFloatEntry14 = this.dataWatcher.resolveManagedFloatEntry(12);
      if (!this.managedFloatEntry8.equals(managedFloatEntry14)) {
         this.applyManagedFloatEntryByManagedFloatEntr(managedFloatEntry14);
      }

      ManagedFloatEntry managedFloatEntry15 = this.dataWatcher.resolveManagedFloatEntry(13);
      if (!this.managedFloatEntry7.equals(managedFloatEntry15)) {
         this.applyManagedFloatEntry5(managedFloatEntry15);
      }

      ManagedFloatEntry managedFloatEntry16 = this.dataWatcher.resolveManagedFloatEntry(14);
      if (!this.managedFloatEntry6.equals(managedFloatEntry16)) {
         this.applyManagedFloatEntry4(managedFloatEntry16);
      }

      ManagedFloatEntry managedFloatEntry17 = this.dataWatcher.resolveManagedFloatEntry(15);
      if (!this.managedFloatEntry10.equals(managedFloatEntry17)) {
         this.applyManagedFloatEntry(managedFloatEntry17);
      }

      ManagedFloatEntry managedFloatEntry18 = this.dataWatcher.resolveManagedFloatEntry(16);
      if (!this.managedFloatEntry5.equals(managedFloatEntry18)) {
         this.applyManagedFloatEntry3(managedFloatEntry18);
      }

      boolean active = this.isActive25();
      if (!this.enabled22 && active) {
         this.setEnabled8(false);
      } else {
         if (!this.enabled22 || active) {
            return;
         }

         this.setEnabled8(true);
      }

      this.enabled22 = active;
   }

   public boolean isActive27() {
      return (this.dataWatcher.computeByteCode(10) & 4) != 0;
   }

   private void setEnabled8(boolean enabled) {
      double value = this.posX;
      double value2 = this.posY;
      double value3 = this.posZ;
      if (enabled) {
         this.applyFloat(0.5F, 1.975F);
      } else {
         this.applyFloat(0.0F, 0.0F);
      }

      this.processDouble2(value, value2, value3);
   }

   private void setEnabled5(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(10);
      byte byteValue2;
      if (enabled) {
         byteValue2 = (byte)(byteValue | 8);
      } else {
         byteValue2 = (byte)(byteValue & -9);
      }

      this.dataWatcher.applyTargetByTarget(10, byteValue2);
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(10, (byte)0);
      this.dataWatcher.applyTarget(11, managedFloatEntry9);
      this.dataWatcher.applyTarget(12, managedFloatEntry3);
      this.dataWatcher.applyTarget(13, managedFloatEntry4);
      this.dataWatcher.applyTarget(14, managedFloatEntry11);
      this.dataWatcher.applyTarget(15, managedFloatEntry12);
      this.dataWatcher.applyTarget(16, managedFloatEntry2);
   }

   private void setEnabled9(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(10);
      byte byteValue2;
      if (enabled) {
         byteValue2 = (byte)(byteValue | 16);
      } else {
         byteValue2 = (byte)(byteValue & -17);
      }

      this.dataWatcher.applyTargetByTarget(10, byteValue2);
   }

   @Override
   public void updateState35() {
      List list = this.worldObj.resolveListByEntity(this, this.getBoundingBox2());
      if (list != null && !list.isEmpty()) {
         for (int index = 0; index < list.size(); index++) {
            Entity entity = (Entity)list.get(index);
            if (entity instanceof EntityMinecart && ((EntityMinecart)entity).getFURNACE() == MovementNumberProperty.RIDEABLE && this.getCoordinate(entity) <= 0.2) {
               entity.applyEntity(this);
            }
         }
      }
   }

   public EntityArmorstand(World world, double posX, double posY, double posZ) {
      this(world);
      this.processDouble2(posX, posY, posZ);
   }

   public EntityArmorstand(World world) {
      super(world);
      this.setEnabled3(true);
      this.noClip = this.isActive26();
      this.applyFloat(0.5F, 1.975F);
   }

   public boolean isActive23() {
      return (this.dataWatcher.computeByteCode(10) & 8) != 0;
   }
}
