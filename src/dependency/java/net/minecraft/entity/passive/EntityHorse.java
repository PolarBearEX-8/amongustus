package net.minecraft.entity.passive;

import client.event.AuxiliaryListener;
import client.event.ExtendedListener;
import client.event.PostRenderConsumer;
import client.event.PostUpdateDispatcher;
import client.setting.AttackEffectsExplosionNormalType;
import client.util.AdvancedClientManagerAdapter;
import client.util.AdvancedCustom;
import client.util.BaseClientManagerAdapter;
import client.util.CommonClientManagerAdapter;
import client.util.DoMobLootClientManager;
import client.util.HitBoxBuffer;
import client.util.IInventoryBridge;
import client.util.NativeDoubleEntry;
import client.util.PathfindManagerAdapter;
import client.util.RotationFilter;
import client.util.SharedClientManagerAdapter;
import client.util.StepManager;
import client.util.SupplementalClientManagerAdapter;
import client.util.SupplementalPredicateAdapter;
import client.util.UUIDEntryAdapter;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.GlobalItemEntry;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHorse extends HitBoxBuffer implements PostUpdateDispatcher {
   public static final Predicate predicate = new SupplementalPredicateAdapter();
   public static final ExtendedListener extendedListener = new NativeDoubleEntry((ExtendedListener)null, "horse.jumpStrength", 0.7, 0.0, 2.0)
      .withLabelText2("Jump Strength")
      .withEnabled(true);
   public static final String[] stringArray5 = new String[]{
      null,
      "textures/entity/horse/armor/horse_armor_iron.png",
      "textures/entity/horse/armor/horse_armor_gold.png",
      "textures/entity/horse/armor/horse_armor_diamond.png"
   };
   public static final String[] stringArray6 = new String[]{"", "meo", "goo", "dio"};
   public static final int[] countArray = new int[]{0, 5, 7, 11};
   public static final String[] stringArray7 = new String[]{
      "textures/entity/horse/horse_white.png",
      "textures/entity/horse/horse_creamy.png",
      "textures/entity/horse/horse_chestnut.png",
      "textures/entity/horse/horse_brown.png",
      "textures/entity/horse/horse_black.png",
      "textures/entity/horse/horse_gray.png",
      "textures/entity/horse/horse_darkbrown.png"
   };
   public static final String[] stringArray4 = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
   public static final String[] stringArray3 = new String[]{
      null,
      "textures/entity/horse/horse_markings_white.png",
      "textures/entity/horse/horse_markings_whitefield.png",
      "textures/entity/horse/horse_markings_whitedots.png",
      "textures/entity/horse/horse_markings_blackdots.png"
   };
   public static final String[] stringArray2 = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
   public int count34;
   public int count36;
   public int count32;
   public int count35;
   public int count41;
   public boolean enabled31;
   public IInventoryBridge iInventoryBridge;
   public boolean enabled33;
   public int count33;
   public float factor60;
   public boolean enabled29;
   public float factor63;
   public float factor58;
   public float factor59;
   public float factor55;
   public float factor48;
   public float factor57;
   public int count39;
   public String labelText2;
   public String[] stringArray = new String[3];
   public boolean enabled28 = false;

   public void setEnabled10(boolean enabled) {
      AttackEffectsExplosionNormalType attackEffectsExplosionNormalType = enabled ? AttackEffectsExplosionNormalType.HEART : AttackEffectsExplosionNormalType.SMOKE_NORMAL;

      for (int index = 0; index < 7; index++) {
         double offset = this.random.nextGaussian() * 0.02;
         double offset2 = this.random.nextGaussian() * 0.02;
         double offset3 = this.random.nextGaussian() * 0.02;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               attackEffectsExplosionNormalType,
               this.posX + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
               this.posY + 0.5 + (double)(this.random.nextFloat() * this.height),
               this.posZ + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
               offset,
               offset2,
               offset3,
               new int[0]
            );
      }
   }

   @Override
   public boolean isActive26() {
      return !this.isActive37() && super.isActive26();
   }

   private float getFactor4() {
      return 15.0F + (float)this.random.nextInt(8) + (float)this.random.nextInt(9);
   }

   @Override
   public float getFactor() {
      return 0.8F;
   }

   @Override
   public void updateState17() {
      super.updateState17();
      if (this.factor55 > 0.0F) {
         float factor = MathHelper.abs(this.scaleFactor8 * (float) Math.PI / 180.0F);
         float factor2 = MathHelper.cos(this.scaleFactor8 * (float) Math.PI / 180.0F);
         float factor3 = 0.7F * this.factor55;
         float factor4 = 0.15F * this.factor55;
         this.entity
            .processDouble2(
               this.posX + (double)(factor3 * factor),
               this.posY + this.getValueAsDouble() + this.entity.getValue() + (double)factor4,
               this.posZ - (double)(factor3 * factor2)
            );
         if (this.entity instanceof EntityLivingBase) {
            ((EntityLivingBase)this.entity).scaleFactor8 = this.scaleFactor8;
         }
      }
   }

   public float computeFactor5(float factor) {
      return this.factor57 + (this.factor48 - this.factor57) * factor;
   }

   private double getValue4() {
      return (0.45F + this.random.nextDouble() * 0.3 + this.random.nextDouble() * 0.3 + this.random.nextDouble() * 0.3) * 0.25;
   }

   @Override
   public boolean matchesItemStackByItemStack(ItemStack stack) {
      return false;
   }

   private int computeCountByItemStack(ItemStack stack) {
      if (stack == null) {
         return 0;
      } else {
         Item item = stack.getItem();
         return item == Items.ironHorseArmor ? 1 : (item == Items.goldenHorseArmor ? 2 : (item == Items.diamondHorseArmor ? 3 : 0));
      }
   }

   private boolean checkIndex2(int count) {
      return (this.dataWatcher.computeCount(16) & count) != 0;
   }

   @Override
   public String getText9() {
      this.updateState56();
      int count = this.getCount12();
      return count == 3
         ? "mob.horse.zombie.death"
         : (count == 4 ? "mob.horse.skeleton.death" : (count != 1 && count != 2 ? "mob.horse.death" : "mob.horse.donkey.death"));
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      StepManager stepManager = block.stepManager20;
      if (this.worldObj.getBlockState(blockPos.up()).getBlock() == Blocks.block74) {
         stepManager = Blocks.block74.stepManager20;
      }

      if (!block.getMaterial().isActive2()) {
         int count = this.getCount12();
         if (this.entity != null && count != 1 && count != 2) {
            this.count39++;
            if (this.count39 > 5 && this.count39 % 3 == 0) {
               this.applyText("mob.horse.gallop", stepManager.getFactor2() * 0.15F, stepManager.getFactor());
               if (count == 0 && this.random.nextInt(10) == 0) {
                  this.applyText("mob.horse.breathe", stepManager.getFactor2() * 0.6F, stepManager.getFactor());
               }
            } else if (this.count39 <= 5) {
               this.applyText("mob.horse.wood", stepManager.getFactor2() * 0.15F, stepManager.getFactor());
            }
         } else if (stepManager == Block.stepManager19) {
            this.applyText("mob.horse.wood", stepManager.getFactor2() * 0.15F, stepManager.getFactor());
         } else {
            this.applyText("mob.horse.soft", stepManager.getFactor2() * 0.15F, stepManager.getFactor());
         }
      }
   }

   @Override
   public float getHeight2() {
      return this.height;
   }

   @Override
   public int getCount4() {
      return countArray[this.getCount13()];
   }

   private void applyEntityPlayer5(EntityPlayer player) {
      player.rotationYaw = this.rotationYaw;
      player.rotationPitch = this.rotationPitch;
      this.setEnabled12(false);
      this.setEnabled11(false);
      if (!this.worldObj.state) {
         player.setEntity8(this);
      }
   }

   @Override
   public String getText() {
      if (this.processAction()) {
         return this.getText2();
      } else {
         switch (this.getCount12()) {
            case 0:
            default:
               return I18n.getString2("entity.horse.name");
            case 1:
               return I18n.getString2("entity.donkey.name");
            case 2:
               return I18n.getString2("entity.mule.name");
            case 3:
               return I18n.getString2("entity.zombiehorse.name");
            case 4:
               return I18n.getString2("entity.skeletonhorse.name");
         }
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.setEnabled12(tagCompound.matchesText("EatingHaystack"));
      this.setEnabled9(tagCompound.matchesText("Bred"));
      this.setEnabled7(tagCompound.matchesText("ChestedHorse"));
      this.setEnabled33(tagCompound.matchesText("HasReproduced"));
      this.applyIndex12(tagCompound.computeCount("Type"));
      this.applyIndex10(tagCompound.computeCount("Variant"));
      this.setCount33(tagCompound.computeCount("Temper"));
      this.setEnabled8(tagCompound.matchesText("Tame"));
      String text;
      if (tagCompound.matchesText("OwnerUUID", 8)) {
         text = tagCompound.resolveText("OwnerUUID");
      } else {
         text = AdvancedCustom.resolveText(tagCompound.resolveText("Owner"));
      }

      if (text.length() > 0) {
         this.applyText(text);
      }

      AuxiliaryListener auxiliaryListener = this.getAdvancedMapEntry().get("Speed");
      if (auxiliaryListener != null) {
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(auxiliaryListener.getValue() * 0.25);
      }

      if (this.isActive32()) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Items", 10);
         this.updateState51();

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
            int count = nBTTagCompound.computeByteCode("Slot") & 255;
            if (count >= 2 && count < this.iInventoryBridge.processAction4()) {
               this.iInventoryBridge.processAction(count, ItemStack.resolveItemStack(nBTTagCompound));
            }
         }
      }

      if (tagCompound.matchesText("ArmorItem", 10)) {
         ItemStack stack = ItemStack.resolveItemStack(tagCompound.getNBTTagCompound("ArmorItem"));
         if (stack != null && matchesItem(stack.getItem())) {
            this.iInventoryBridge.processAction(1, stack);
         }
      }

      if (tagCompound.matchesText("SaddleItem", 10)) {
         ItemStack stack2 = ItemStack.resolveItemStack(tagCompound.getNBTTagCompound("SaddleItem"));
         if (stack2 != null && stack2.getItem() == Items.saddle) {
            this.iInventoryBridge.processAction(0, stack2);
         }
      } else if (tagCompound.matchesText("Saddle")) {
         this.iInventoryBridge.processAction(0, new ItemStack(Items.saddle));
      }

      this.updateState58();
   }

   public int computeCountByIndex(int count) {
      int intItem = MathHelper.getInt12(this.getCount33() + count, 0, this.getCount10());
      this.setCount33(intItem);
      return intItem;
   }

   public String getTextAsText() {
      if (this.labelText2 == null) {
         this.updateState50();
      }

      return this.labelText2;
   }

   @Override
   public boolean matchesHitBoxBuffer(HitBoxBuffer hitBoxBuffer) {
      if (hitBoxBuffer == this) {
         return false;
      } else if (hitBoxBuffer.getClass() != this.getClass()) {
         return false;
      } else {
         EntityHorse entityHorse = (EntityHorse)hitBoxBuffer;
         if (this.isActive41() && entityHorse.isActive41()) {
            int count = this.getCount12();
            int count2 = entityHorse.getCount12();
            return count == count2 || count == 0 && count2 == 1 || count == 1 && count2 == 0;
         } else {
            return false;
         }
      }
   }

   @Override
   public boolean matchesItemStack(int count, ItemStack stack) {
      if (count == 499 && this.isActive46()) {
         if (stack == null && this.isActive32()) {
            this.setEnabled7(false);
            this.updateState51();
            return true;
         }

         if (stack != null && stack.getItem() == Item.processAction2(Blocks.blockTrappedChest) && !this.isActive32()) {
            this.setEnabled7(true);
            this.updateState51();
            return true;
         }
      }

      int count2 = count - 400;
      if (count2 >= 0 && count2 < 2 && count2 < this.iInventoryBridge.processAction4()) {
         if (count2 == 0 && stack != null && stack.getItem() != Items.saddle) {
            return false;
         } else if (count2 == 1 && (stack != null && !matchesItem(stack.getItem()) || !this.isActive44())) {
            return false;
         } else {
            this.iInventoryBridge.processAction(count2, stack);
            this.updateState58();
            return true;
         }
      } else {
         int count3 = count - 500 + 2;
         if (count3 >= 2 && count3 < this.iInventoryBridge.processAction4()) {
            this.iInventoryBridge.processAction(count3, stack);
            return true;
         } else {
            return false;
         }
      }
   }

   public float computeFactor3(float factor) {
      return this.factor58 + (this.factor63 - this.factor58) * factor;
   }

   public void applyItemStack(ItemStack stack) {
      this.dataWatcher.applyTargetByTarget(22, this.computeCountByItemStack(stack));
      this.updateState57();
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAdvancedMapEntry().resolveAuxiliaryListenerByExtendedListener(extendedListener);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(53.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.225F);
   }

   public void setEnabled12(boolean enabled) {
      this.setEnabled4(enabled);
   }

   public double getValue3() {
      return this.getAuxiliaryListener(extendedListener).getDouble();
   }

   @Override
   public void applyInventoryLargeChest(InventoryLargeChest inventoryLargeChest) {
      int count = this.getCount13();
      boolean active = this.isActive34();
      this.updateState58();
      if (this.ticksExisted > 20) {
         if (count == 0 && count != this.getCount13()) {
            this.applyText("mob.horse.armor", 0.5F, 1.0F);
         } else if (count != this.getCount13()) {
            this.applyText("mob.horse.armor", 0.5F, 1.0F);
         }

         if (!active && this.isActive34()) {
            this.applyText("mob.horse.leather", 0.5F, 1.0F);
         }
      }
   }

   private void applyEntityByEntity(Entity entity, IInventoryBridge iInventoryBridge2) {
      if (iInventoryBridge2 != null && !this.worldObj.state) {
         for (int index = 0; index < iInventoryBridge2.processAction4(); index++) {
            ItemStack stack = iInventoryBridge2.processAction3(index);
            if (stack != null) {
               this.resolveEntityItem(stack, 0.0F);
            }
         }
      }
   }

   public String[] getTextArray() {
      if (this.labelText2 == null) {
         this.updateState50();
      }

      return this.stringArray;
   }

   @Override
   public void processFloat2(float factor, float factor2) {
      if (factor > 1.0F) {
         this.applyText("mob.horse.land", 0.4F, 1.0F);
      }

      int count = MathHelper.computeCount((factor * 0.5F - 3.0F) * factor2);
      if (count > 0) {
         this.matchesDamageSource(DamageSource.damageSource11, (float)count);
         if (this.entity != null) {
            this.entity.matchesDamageSource(DamageSource.damageSource11, (float)count);
         }

         Block block = this.worldObj.getBlockState(new BlockPos(this.posX, this.posY - 0.2 - (double)this.prevRotationYaw, this.posZ)).getBlock();
         if (block.getMaterial() != Material.coreItemEntry && !this.isActive7()) {
            StepManager stepManager = block.stepManager20;
            this.worldObj.applyEntityByEntity(this, stepManager.getText(), stepManager.getFactor2() * 0.5F, stepManager.getFactor() * 0.75F);
         }
      }
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      EntityHorse entityHorse = (EntityHorse)uUIDEntryAdapter;
      EntityHorse entityHorse2 = new EntityHorse(this.worldObj);
      int count = this.getCount12();
      int count2 = entityHorse.getCount12();
      int count3 = 0;
      if (count == count2) {
         count3 = count;
      } else if (count == 0 && count2 == 1 || count == 1 && count2 == 0) {
         count3 = 2;
      }

      if (count3 == 0) {
         int count4 = this.random.nextInt(9);
         int count5;
         if (count4 < 4) {
            count5 = this.getCount11() & 0xFF;
         } else if (count4 < 8) {
            count5 = entityHorse.getCount11() & 0xFF;
         } else {
            count5 = this.random.nextInt(7);
         }

         int count6 = this.random.nextInt(5);
         int count7;
         if (count6 < 2) {
            count7 = count5 | this.getCount11() & 0xFF00;
         } else if (count6 < 4) {
            count7 = count5 | entityHorse.getCount11() & 0xFF00;
         } else {
            count7 = count5 | this.random.nextInt(5) << 8 & 0xFF00;
         }

         entityHorse2.applyIndex10(count7);
      }

      entityHorse2.applyIndex12(count3);
      double offset = this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).getValue()
         + uUIDEntryAdapter.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).getValue()
         + (double)this.getFactor4();
      entityHorse2.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(offset / 3.0);
      double offset2 = this.getAuxiliaryListener(extendedListener).getValue() + uUIDEntryAdapter.getAuxiliaryListener(extendedListener).getValue() + this.getValue2();
      entityHorse2.getAuxiliaryListener(extendedListener).applyDouble(offset2 / 3.0);
      double offset3 = this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getValue()
         + uUIDEntryAdapter.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getValue()
         + this.getValue4();
      entityHorse2.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(offset3 / 3.0);
      return entityHorse2;
   }

   public EntityHorse resolveEntityHorseByEntity(Entity entity, double offset) {
      double offset2 = Double.MAX_VALUE;
      Entity entity2 = null;

      for (Entity entity3 : (Iterable<Entity>)(Iterable<?>)this.worldObj.getEntitiesInAABBexcluding(entity, entity.getBoundingBox2().contract(offset, offset, offset), predicate)) {
         double doubleItem = entity3.getDouble3(entity.posX, entity.posY, entity.posZ);
         if (doubleItem < offset2) {
            entity2 = entity3;
            offset2 = doubleItem;
         }
      }

      return (EntityHorse)entity2;
   }

   public void applyText(String text) {
      this.dataWatcher.applyTargetByTarget(21, text);
   }

   private void applyIndex2(int count, boolean flag) {
      int count2 = this.dataWatcher.computeCount(16);
      if (flag) {
         this.dataWatcher.applyTargetByTarget(16, count2 | count);
      } else {
         this.dataWatcher.applyTargetByTarget(16, count2 & ~count);
      }
   }

   @Override
   public boolean isActive9() {
      return this.entity == null;
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 7) {
         this.setEnabled10(true);
      } else if (byteValue == 6) {
         this.setEnabled10(false);
      } else {
         super.processByte(byteValue);
      }
   }

   @Override
   public Item getGunpowder() {
      boolean flag = this.random.nextInt(4) == 0;
      int count = this.getCount12();
      return count == 4 ? Items.bone : (count == 3 ? (flag ? null : Items.rottenFlesh) : Items.leather);
   }

   public void applyEntityPlayer7(EntityPlayer player) {
      if (!this.worldObj.state && (this.entity == null || this.entity == player) && this.isActive43()) {
         this.iInventoryBridge.applyText(this.getText());
         player.applyEntityHorse(this, this.iInventoryBridge);
      }
   }

   @Override
   public void setEnabled4(boolean enabled) {
      this.applyIndex2(32, enabled);
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() == Items.spawnEgg) {
         return super.matchesEntityPlayer6(player);
      } else if (!this.isActive43() && this.isActive37()) {
         return false;
      } else if (this.isActive43() && this.isActive33() && player.isSprinting()) {
         this.applyEntityPlayer7(player);
         return true;
      } else if (this.isActive36() && this.entity != null) {
         return super.matchesEntityPlayer6(player);
      } else {
         if (slot != null) {
            boolean flag = false;
            if (this.isActive44()) {
               byte byteValue = -1;
               if (slot.getItem() == Items.ironHorseArmor) {
                  byteValue = 1;
               } else if (slot.getItem() == Items.goldenHorseArmor) {
                  byteValue = 2;
               } else if (slot.getItem() == Items.diamondHorseArmor) {
                  byteValue = 3;
               }

               if (byteValue >= 0) {
                  if (!this.isActive43()) {
                     this.updateState59();
                     return true;
                  }

                  this.applyEntityPlayer7(player);
                  return true;
               }
            }

            if (!this.isActive37()) {
               float factor = 0.0F;
               short shortValue = 0;
               byte byteValue2 = 0;
               if (slot.getItem() == Items.wheat) {
                  factor = 2.0F;
                  shortValue = 20;
                  byteValue2 = 3;
               } else if (slot.getItem() == Items.sugar) {
                  factor = 1.0F;
                  shortValue = 30;
                  byteValue2 = 3;
               } else if (Block.resolveBlock(slot.getItem()) == Blocks.block137) {
                  factor = 20.0F;
                  shortValue = 180;
               } else if (slot.getItem() == Items.apple) {
                  factor = 3.0F;
                  shortValue = 60;
                  byteValue2 = 3;
               } else if (slot.getItem() == Items.goldenCarrot) {
                  factor = 4.0F;
                  shortValue = 60;
                  byteValue2 = 5;
                  if (this.isActive43() && this.getCount9() == 0) {
                     flag = true;
                     this.applyEntityPlayer(player);
                  }
               } else if (slot.getItem() == Items.goldenApple) {
                  factor = 10.0F;
                  shortValue = 240;
                  byteValue2 = 10;
                  if (this.isActive43() && this.getCount9() == 0) {
                     flag = true;
                     this.applyEntityPlayer(player);
                  }
               }

               if (this.getFloat2() < this.getFloat() && factor > 0.0F) {
                  this.applyFloatByFloat(factor);
                  flag = true;
               }

               if (!this.isActive33() && shortValue > 0) {
                  this.applyIndex9(shortValue);
                  flag = true;
               }

               if (byteValue2 > 0 && (flag || !this.isActive43()) && byteValue2 < this.getCount10()) {
                  flag = true;
                  this.computeCountByIndex(byteValue2);
               }

               if (flag) {
                  this.updateState52();
               }
            }

            if (!this.isActive43() && !flag) {
               if (slot != null && slot.processAction(player, this)) {
                  return true;
               }

               this.updateState59();
               return true;
            }

            if (!flag && this.isActive46() && !this.isActive32() && slot.getItem() == Item.processAction2(Blocks.blockTrappedChest)) {
               this.setEnabled7(true);
               this.applyText("mob.chickenplop", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
               flag = true;
               this.updateState51();
            }

            if (!flag && this.isActive36() && !this.isActive34() && slot.getItem() == Items.saddle) {
               this.applyEntityPlayer7(player);
               return true;
            }

            if (flag) {
               if (!player.invulnerableManager.enabled2 && --slot.animationsToGo == 0) {
                  player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
               }

               return true;
            }
         }

         if (!this.isActive36() || this.entity != null) {
            return super.matchesEntityPlayer6(player);
         } else if (slot != null && slot.processAction(player, this)) {
            return true;
         } else {
            this.applyEntityPlayer5(player);
            return true;
         }
      }
   }

   @Override
   public void applyFloat4(float factor) {
      if (factor > 6.0F && this.isActive42()) {
         this.setEnabled12(false);
      }
   }

   public float computeFactor4(float factor) {
      return this.factor55 + (this.factor59 - this.factor55) * factor;
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      Object entry = super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
      int count = 0;
      int count2;
      if (entry instanceof RotationFilter) {
         count2 = ((RotationFilter)entry).count2;
         count = ((RotationFilter)entry).count & 0xFF | this.random.nextInt(5) << 8;
      } else {
         if (this.random.nextInt(10) == 0) {
            count2 = 1;
         } else {
            int count3 = this.random.nextInt(7);
            int count4 = this.random.nextInt(5);
            count2 = 0;
            count = count3 | count4 << 8;
         }

         entry = new RotationFilter(count2, count);
      }

      this.applyIndex12(count2);
      this.applyIndex10(count);
      if (this.random.nextInt(5) == 0) {
         this.applyIndex8(-24000);
      }

      if (count2 != 4 && count2 != 3) {
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble((double)this.getFactor4());
         if (count2 == 0) {
            this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(this.getValue4());
         } else {
            this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.175F);
         }
      } else {
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(15.0);
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.2F);
      }

      if (count2 != 2 && count2 != 1) {
         this.getAuxiliaryListener(extendedListener).applyDouble(this.getValue2());
      } else {
         this.getAuxiliaryListener(extendedListener).applyDouble(0.5);
      }

      this.applyFloat(this.getFloat());
      return (PostRenderConsumer)entry;
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      if (!this.worldObj.state) {
         this.updateState54();
      }
   }

   @Override
   public String getText3() {
      this.updateState56();
      if (this.random.nextInt(10) == 0 && !this.isActive20()) {
         this.updateState53();
      }

      int count = this.getCount12();
      return count == 3
         ? "mob.horse.zombie.idle"
         : (count == 4 ? "mob.horse.skeleton.idle" : (count != 1 && count != 2 ? "mob.horse.idle" : "mob.horse.donkey.idle"));
   }

   public static boolean matchesItem(Item item) {
      return item == Items.ironHorseArmor || item == Items.goldenHorseArmor || item == Items.diamondHorseArmor;
   }

   public boolean matchesEntityPlayer12(EntityPlayer player) {
      this.applyText(player.getEntityUniqueID().toString());
      this.setEnabled8(true);
      return true;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("EatingHaystack", this.isActive42());
      tagCompound.applyText("ChestedHorse", this.isActive32());
      tagCompound.applyText("HasReproduced", this.isEnabled33());
      tagCompound.applyText("Bred", this.isActive38());
      tagCompound.put("Type", this.getCount12());
      tagCompound.put("Variant", this.getCount11());
      tagCompound.put("Temper", this.getCount33());
      tagCompound.applyText("Tame", this.isActive43());
      tagCompound.put("OwnerUUID", this.getText4());
      if (this.isActive32()) {
         NBTTagList nBTTagList = new NBTTagList();

         for (int index = 2; index < this.iInventoryBridge.processAction4(); index++) {
            ItemStack stack = this.iInventoryBridge.processAction3(index);
            if (stack != null) {
               NBTTagCompound tagCompound2 = new NBTTagCompound();
               tagCompound2.put("Slot", (byte)index);
               stack.resolveNBTTagCompound(tagCompound2);
               nBTTagList.applyLISTManager(tagCompound2);
            }
         }

         tagCompound.applyText("Items", nBTTagList);
      }

      if (this.iInventoryBridge.processAction3(1) != null) {
         tagCompound.applyText("ArmorItem", this.iInventoryBridge.processAction3(1).resolveNBTTagCompound(new NBTTagCompound()));
      }

      if (this.iInventoryBridge.processAction3(0) != null) {
         tagCompound.applyText("SaddleItem", this.iInventoryBridge.processAction3(0).resolveNBTTagCompound(new NBTTagCompound()));
      }
   }

   @Override
   public String getText10() {
      this.updateState56();
      if (this.random.nextInt(3) == 0) {
         this.updateState53();
      }

      int count = this.getCount12();
      return count == 3 ? "mob.horse.zombie.hit" : (count == 4 ? "mob.horse.skeleton.hit" : (count != 1 && count != 2 ? "mob.horse.hit" : "mob.horse.donkey.hit"));
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      Entity entity = damageSource.getEntity();
      return this.entity != null && this.entity.equals(entity) ? false : super.matchesDamageSource(damageSource, factor);
   }

   @Override
   public void applyFloat2(float factor, float factor2) {
      if (this.entity != null && this.entity instanceof EntityLivingBase && this.isActive34()) {
         this.prevRotationYaw = this.rotationYaw = this.entity.rotationYaw;
         this.rotationPitch = this.entity.rotationPitch * 0.5F;
         this.processFloat4(this.rotationYaw, this.rotationPitch);
         this.scaleFactor12 = this.scaleFactor8 = this.rotationYaw;
         float factor3 = ((EntityLivingBase)this.entity).scaleFactor24 * 0.5F;
         float scaleFactor = ((EntityLivingBase)this.entity).scaleFactor3;
         if (scaleFactor <= 0.0F) {
            scaleFactor *= 0.25F;
            this.count39 = 0;
         }

         if (this.onGround && this.factor60 == 0.0F && this.isActive39() && !this.enabled29) {
            factor3 = 0.0F;
            scaleFactor = 0.0F;
         }

         if (this.factor60 > 0.0F && !this.isEnabled31() && this.onGround) {
            this.motionY = this.getValue3() * (double)this.factor60;
            if (this.checkPotion(Potion.potion9)) {
               this.motionY = this.motionY + (double)((float)(this.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
            }

            this.setEnabled31(true);
            this.enabled4 = true;
            if (scaleFactor > 0.0F) {
               float factor4 = MathHelper.abs(this.rotationYaw * (float) Math.PI / 180.0F);
               float factor5 = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F);
               this.motionX = this.motionX + (double)(-0.4F * factor4 * this.factor60);
               this.motionZ = this.motionZ + (double)(0.4F * factor5 * this.factor60);
               this.applyText("mob.horse.jump", 0.4F, 1.0F);
            }

            this.factor60 = 0.0F;
         }

         this.stepHeight = 1.0F;
         this.scaleFactor10 = this.getScaleFactor6() * 0.1F;
         if (!this.worldObj.state) {
            this.setScaleFactor6((float)this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getDouble());
            super.applyFloat2(factor3, scaleFactor);
         }

         if (this.onGround) {
            this.factor60 = 0.0F;
            this.setEnabled31(false);
         }

         this.factor9 = this.factor11;
         double offset = this.posX - this.prevPosX;
         double offset2 = this.posZ - this.prevPosZ;
         float factor6 = MathHelper.sqrt_double(offset * offset + offset2 * offset2) * 4.0F;
         if (factor6 > 1.0F) {
            factor6 = 1.0F;
         }

         this.factor11 = this.factor11 + (factor6 - this.factor11) * 0.4F;
         this.factor14 = this.factor14 + this.factor11;
      } else {
         this.stepHeight = 0.5F;
         this.scaleFactor10 = 0.02F;
         super.applyFloat2(factor, factor2);
      }
   }

   private double getValue2() {
      return 0.4F + this.random.nextDouble() * 0.2 + this.random.nextDouble() * 0.2 + this.random.nextDouble() * 0.2;
   }

   @Override
   public int getCount6() {
      return 6;
   }

   @Override
   public void setEnabled6(boolean enabled) {
      if (enabled) {
         this.applyFloat5(this.getFactor3());
      } else {
         this.applyFloat5(1.0F);
      }
   }

   @Override
   public int getCount7() {
      return 400;
   }

   private void updateState51() {
      IInventoryBridge iInventoryBridge2 = this.iInventoryBridge;
      this.iInventoryBridge = new IInventoryBridge("HorseChest", this.getCount14());
      this.iInventoryBridge.applyText(this.getText());
      if (iInventoryBridge2 != null) {
         iInventoryBridge2.remove(this);
         int count = Math.min(iInventoryBridge2.processAction4(), this.iInventoryBridge.processAction4());

         for (int index = 0; index < count; index++) {
            ItemStack stack = iInventoryBridge2.processAction3(index);
            if (stack != null) {
               this.iInventoryBridge.processAction(index, stack.getItemStack());
            }
         }
      }

      this.iInventoryBridge.applyPostUpdateDispatcher(this);
      this.updateState58();
   }

   public void updateState54() {
      this.applyEntityByEntity(this, this.iInventoryBridge);
      this.updateState55();
   }

   public void updateState55() {
      if (!this.worldObj.state && this.isActive32()) {
         this.resolveEntityItem(Item.processAction2(Blocks.blockTrappedChest), 1);
         this.setEnabled7(false);
      }
   }

   private void updateState53() {
      if (!this.worldObj.state) {
         this.count32 = 1;
         this.setEnabled11(true);
      }
   }

   private void updateState58() {
      if (!this.worldObj.state) {
         this.setEnabled13(this.iInventoryBridge.processAction3(0) != null);
         if (this.isActive44()) {
            this.applyItemStack(this.iInventoryBridge.processAction3(1));
         }
      }
   }

   public void updateState59() {
      this.updateState53();
      String text = this.getText11();
      if (text != null) {
         this.applyText(text, this.getFactor(), this.getFactorAsFloat());
      }
   }

   private void updateState60() {
      this.count35 = 1;
   }

   private void updateState52() {
      this.updateState56();
      if (!this.isActive7()) {
         this.worldObj.applyEntityByEntity(this, "eating", 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
      }
   }

   private void updateState57() {
      this.labelText2 = null;
   }

   private void updateState56() {
      if (!this.worldObj.state) {
         this.count36 = 1;
         this.applyIndex2(128, true);
      }
   }

   private void updateState50() {
      this.labelText2 = "horse/";
      this.stringArray[0] = null;
      this.stringArray[1] = null;
      this.stringArray[2] = null;
      int count = this.getCount12();
      int count2 = this.getCount11();
      if (count == 0) {
         int count3 = count2 & 0xFF;
         int count4 = (count2 & 0xFF00) >> 8;
         if (count3 >= stringArray7.length) {
            this.enabled28 = false;
            return;
         }

         this.stringArray[0] = stringArray7[count3];
         this.labelText2 = this.labelText2 + stringArray4[count3];
         if (count4 >= stringArray3.length) {
            this.enabled28 = false;
            return;
         }

         this.stringArray[1] = stringArray3[count4];
         this.labelText2 = this.labelText2 + stringArray2[count4];
      } else {
         this.stringArray[0] = "";
         this.labelText2 = this.labelText2 + "_" + count + "_";
      }

      int count5 = this.getCount13();
      if (count5 >= stringArray5.length) {
         this.enabled28 = false;
      } else {
         this.stringArray[2] = stringArray5[count5];
         this.labelText2 = this.labelText2 + stringArray6[count5];
         this.enabled28 = true;
      }
   }

   public void setEnabled33(boolean enabled) {
      this.enabled33 = enabled;
   }

   public void applyIndex10(int count) {
      this.dataWatcher.applyTargetByTarget(20, count);
      this.updateState57();
   }

   public void setEnabled31(boolean enabled) {
      this.enabled31 = enabled;
   }

   @Override
   public boolean isActive20() {
      return this.entity != null && this.isActive34() ? true : this.isActive42() || this.isActive39();
   }

   public void setCount33(int count) {
      this.count33 = count;
   }

   public String getText11() {
      this.updateState56();
      this.updateState53();
      int count = this.getCount12();
      return count != 3 && count != 4 ? (count != 1 && count != 2 ? "mob.horse.angry" : "mob.horse.donkey.angry") : null;
   }

   public void applyIndex12(int count) {
      this.dataWatcher.applyTargetByTarget(19, (byte)count);
      this.updateState57();
   }

   public void applyIndex11(int count) {
      int count2 = count;
      if (this.isActive34()) {
         if (count < 0) {
            count2 = 0;
         } else {
            this.enabled29 = true;
            this.updateState53();
         }

         if (count2 >= 90) {
            this.factor60 = 1.0F;
         } else {
            this.factor60 = 0.4F + 0.4F * (float)count2 / 90.0F;
         }
      }
   }

   public String getText4() {
      return this.dataWatcher.resolveText(21);
   }

   public int getCount10() {
      return 100;
   }

   public boolean isActive39() {
      return this.checkIndex2(64);
   }

   public boolean isActive42() {
      return this.checkIndex2(32);
   }

   public boolean isActive40() {
      return this.isActive37() || this.getCount12() == 2;
   }

   public boolean isActive43() {
      return this.checkIndex2(2);
   }

   public boolean isEnabled28() {
      return this.enabled28;
   }

   public boolean isActive32() {
      return this.checkIndex2(8);
   }

   public boolean isEnabled31() {
      return this.enabled31;
   }

   public int getCount33() {
      return this.count33;
   }

   public boolean isActive36() {
      return this.isActive33();
   }

   public boolean isActive37() {
      int count = this.getCount12();
      return count == 3 || count == 4;
   }

   @Override
   public boolean canExecute9() {
      return false;
   }

   public void setEnabled9(boolean enabled) {
      this.applyIndex2(16, enabled);
   }

   public boolean isActive38() {
      return this.checkIndex2(16);
   }

   public boolean isActive34() {
      return this.checkIndex2(4);
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.worldObj.state && this.dataWatcher.isEnabled2()) {
         this.dataWatcher.updateState();
         this.updateState57();
      }

      if (this.count36 > 0 && ++this.count36 > 30) {
         this.count36 = 0;
         this.applyIndex2(128, false);
      }

      if (!this.worldObj.state && this.count32 > 0 && ++this.count32 > 20) {
         this.count32 = 0;
         this.setEnabled11(false);
      }

      if (this.count35 > 0 && ++this.count35 > 8) {
         this.count35 = 0;
      }

      if (this.count41 > 0) {
         this.count41++;
         if (this.count41 > 300) {
            this.count41 = 0;
         }
      }

      this.factor58 = this.factor63;
      if (this.isActive42()) {
         this.factor63 = this.factor63 + (1.0F - this.factor63) * 0.4F + 0.05F;
         if (this.factor63 > 1.0F) {
            this.factor63 = 1.0F;
         }
      } else {
         this.factor63 = this.factor63 + ((0.0F - this.factor63) * 0.4F - 0.05F);
         if (this.factor63 < 0.0F) {
            this.factor63 = 0.0F;
         }
      }

      this.factor55 = this.factor59;
      if (this.isActive39()) {
         this.factor58 = this.factor63 = 0.0F;
         this.factor59 = this.factor59 + (1.0F - this.factor59) * 0.4F + 0.05F;
         if (this.factor59 > 1.0F) {
            this.factor59 = 1.0F;
         }
      } else {
         this.enabled29 = false;
         this.factor59 = this.factor59 + ((0.8F * this.factor59 * this.factor59 * this.factor59 - this.factor59) * 0.6F - 0.05F);
         if (this.factor59 < 0.0F) {
            this.factor59 = 0.0F;
         }
      }

      this.factor57 = this.factor48;
      if (this.checkIndex2(128)) {
         this.factor48 = this.factor48 + (1.0F - this.factor48) * 0.7F + 0.05F;
         if (this.factor48 > 1.0F) {
            this.factor48 = 1.0F;
         }
      } else {
         this.factor48 = this.factor48 + ((0.0F - this.factor48) * 0.7F - 0.05F);
         if (this.factor48 < 0.0F) {
            this.factor48 = 0.0F;
         }
      }
   }

   public boolean isActive44() {
      return this.getCount12() == 0;
   }

   private boolean isActive41() {
      return this.entity == null
         && this.ridingEntity == null
         && this.isActive43()
         && this.isActive33()
         && !this.isActive40()
         && this.getFloat2() >= this.getFloat()
         && this.isActive31();
   }

   public int getCount11() {
      return this.dataWatcher.computeCount(20);
   }

   public int getCount13() {
      return this.dataWatcher.computeCount(22);
   }

   public boolean isActive45() {
      int count = MathHelper.ceiling_double_int(this.posX);
      int count2 = MathHelper.ceiling_double_int(this.posZ);
      this.worldObj.resolveBiomeIDIsOutOfBoundsManager(new BlockPos(count, 0, count2));
      return true;
   }

   public void setEnabled11(boolean enabled) {
      if (enabled) {
         this.setEnabled12(false);
      }

      this.applyIndex2(64, enabled);
   }

   public boolean isEnabled33() {
      return this.enabled33;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, 0);
      this.dataWatcher.applyTarget(19, (byte)0);
      this.dataWatcher.applyTarget(20, 0);
      this.dataWatcher.applyTarget(21, String.valueOf(""));
      this.dataWatcher.applyTarget(22, 0);
   }

   public int getCount12() {
      return this.dataWatcher.computeByteCode(19);
   }

   public boolean isActive46() {
      int count = this.getCount12();
      return count == 2 || count == 1;
   }

   private int getCount14() {
      int count = this.getCount12();
      return this.isActive32() && (count == 1 || count == 2) ? 17 : 2;
   }

   public boolean isActive35() {
      return this.getCount12() == 0 || this.getCount13() > 0;
   }

   public boolean isActive33() {
      return !this.isActive16();
   }

   public void setEnabled8(boolean enabled) {
      this.applyIndex2(2, enabled);
   }

   public float getFactor3() {
      return 0.5F;
   }

   public void setEnabled13(boolean enabled) {
      this.applyIndex2(4, enabled);
   }

   @Override
   public void updateState33() {
      if (this.random.nextInt(200) == 0) {
         this.updateState60();
      }

      super.updateState33();
      if (!this.worldObj.state) {
         if (this.random.nextInt(900) == 0 && this.indexOffset29 == 0) {
            this.applyFloatByFloat(1.0F);
         }

         if (!this.isActive42()
            && this.entity == null
            && this.random.nextInt(300) == 0
            && this.worldObj
                  .getBlockState(
                     new BlockPos(
                        MathHelper.ceiling_double_int(this.posX),
                        MathHelper.ceiling_double_int(this.posY) - 1,
                        MathHelper.ceiling_double_int(this.posZ)
                     )
                  )
                  .getBlock()
               == Blocks.blockGrass) {
            this.setEnabled12(true);
         }

         if (this.isActive42() && ++this.count34 > 50) {
            this.count34 = 0;
            this.setEnabled12(false);
         }

         if (this.isActive38() && !this.isActive33() && !this.isActive42()) {
            EntityHorse entityHorse = this.resolveEntityHorseByEntity(this, 16.0);
            if (entityHorse != null && this.getCoordinate(entityHorse) > 4.0) {
               this.pathfindManager.resolveInterpolationConverter(entityHorse);
            }
         }
      }
   }

   @Override
   public boolean nextInt() {
      this.isActive45();
      return super.nextInt();
   }

   public void setEnabled7(boolean enabled) {
      this.applyIndex2(8, enabled);
   }

   public EntityHorse(World world) {
      super(world);
      this.applyFloat(1.4F, 1.6F);
      this.state4 = false;
      this.setEnabled7(false);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.goalSetupManager.add(0, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(1, new CommonClientManagerAdapter(this, 1.2));
      this.goalSetupManager.add(1, new SharedClientManagerAdapter(this, 1.2));
      this.goalSetupManager.add(2, new DoMobLootClientManager(this, 1.0));
      this.goalSetupManager.add(4, new GlobalItemEntry(this, 1.0));
      this.goalSetupManager.add(6, new AdvancedClientManagerAdapter(this, 0.7));
      this.goalSetupManager.add(7, new BaseClientManagerAdapter(this, EntityPlayer.class, 6.0F));
      this.goalSetupManager.add(8, new ComplexItemEntry(this));
      this.updateState51();
   }
}
