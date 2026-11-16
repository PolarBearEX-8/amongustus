package net.minecraft.entity;

import client.event.PostRenderConsumer;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.GameSettingsPeacefulType;
import client.util.AchievementTheEnd2Manager;
import client.util.BuildManager;
import client.util.CanSeeManager;
import client.util.GoalSetupManager;
import client.util.HitBoxAdapter;
import client.util.InternalClientManagerRegistry;
import client.util.InvalidOperationManager;
import client.util.LvlManager;
import client.util.PathfindManager;
import client.util.PathfindManagerAdapter;
import client.util.SecondaryClientManager;
import client.util.VectorPipeline;
import java.util.UUID;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Items;
import net.minecraft.item.PrimaryItemEntry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityMob extends EntityLivingBase {
   public final GoalSetupManager goalSetupManager;
   public final GoalSetupManager goalSetupManager2;
   public int count25;
   public int count26;
   public EntityPillagerAdapter entityPillagerAdapter;
   public PrimaryItemEntry primaryItemEntry;
   public PathfindManager pathfindManager;
   public float[] factorArray = new float[5];
   public final EntityRavagerAdapter entityRavagerAdapter;
   public final AuxiliaryEntityLivingBaseEntry auxiliaryEntityLivingBaseEntry;
   public EntityLivingBase entityLivingBase;
   public final CanSeeManager canSeeManager;
   public final ItemStack[] itemStackArray2 = new ItemStack[5];
   public boolean enabled22;
   public boolean enabled21;
   public boolean enabled23;
   public Entity entity4;
   public NBTTagCompound nBTTagCompound;
   public UUID uUID2 = null;
   public String labelText = null;

   public boolean isActive26() {
      return !this.isEnabled23() && !(this instanceof VectorPipeline);
   }

   public void applyEntityItem(EntityItem entityItem) {
      ItemStack stack = entityItem.getItemStack();
      int count = computeCount(stack);
      if (count > -1) {
         boolean flag = true;
         ItemStack stack2 = this.resolveItemStack(count);
         if (stack2 != null) {
            if (count == 0) {
               if (stack.getItem() instanceof ItemSword && !(stack2.getItem() instanceof ItemSword)) {
                  flag = true;
               } else if (stack.getItem() instanceof ItemSword && stack2.getItem() instanceof ItemSword) {
                  ItemSword item = (ItemSword)stack.getItem();
                  ItemSword item2 = (ItemSword)stack2.getItem();
                  if (item.getFactor() != item2.getFactor()) {
                     flag = item.getFactor() > item2.getFactor();
                  } else {
                     flag = stack.getIndexOffset() > stack2.getIndexOffset() || stack.canExecute() && !stack2.canExecute();
                  }
               } else if (stack.getItem() instanceof ItemBow && stack2.getItem() instanceof ItemBow) {
                  flag = stack.canExecute() && !stack2.canExecute();
               } else {
                  flag = false;
               }
            } else if (stack.getItem() instanceof ItemGoldenBoots && !(stack2.getItem() instanceof ItemGoldenBoots)) {
               flag = true;
            } else if (stack.getItem() instanceof ItemGoldenBoots && stack2.getItem() instanceof ItemGoldenBoots) {
               ItemGoldenBoots item3 = (ItemGoldenBoots)stack.getItem();
               ItemGoldenBoots item4 = (ItemGoldenBoots)stack2.getItem();
               if (item3.indexOffset4 != item4.indexOffset4) {
                  flag = item3.indexOffset4 > item4.indexOffset4;
               } else {
                  flag = stack.getIndexOffset() > stack2.getIndexOffset() || stack.canExecute() && !stack2.canExecute();
               }
            } else {
               flag = false;
            }
         }

         if (flag && this.matchesItemStack(stack)) {
            if (stack2 != null && this.random.nextFloat() - 0.1F < this.factorArray[count]) {
               this.resolveEntityItem(stack2, 0.0F);
            }

            if (stack.getItem() == Items.diamond && entityItem.getLabelText() != null) {
               EntityPlayer player = this.worldObj.resolveEntityPlayerByText(entityItem.getLabelText());
               if (player != null) {
                  player.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter3);
               }
            }

            this.applyItemStack(count, stack);
            this.factorArray[count] = 2.0F;
            this.enabled21 = true;
            this.applyEntity2(entityItem, 1);
            entityItem.updateState9();
         }
      }
   }

   public void applyEntity(Entity entity, boolean flag) {
      this.enabled23 = true;
      this.entity4 = entity;
      if (!this.worldObj.state && flag && this.worldObj instanceof WorldServer) {
         ((WorldServer)this.worldObj).getSecondaryLoggerEntry().applyEntity3(this, new S1BPacketEntityAttach(1, this, this.entity4));
      }
   }

   @Override
   public SecondaryClientManager getSecondaryClientManager() {
      UUID entityUniqueID = this.getEntityUniqueID();
      if (this.uUID2 != entityUniqueID) {
         this.uUID2 = entityUniqueID;
         this.labelText = entityUniqueID.toString();
      }

      return this.worldObj.getThePlayerNameManager().getScorePlayerTeam(this.labelText);
   }

   @Override
   public void applyFlagByFlag(boolean flag, int count) {
      for (int index = 0; index < this.getItemStackArray2().length; index++) {
         ItemStack stack = this.resolveItemStack(index);
         boolean flag2 = this.factorArray[index] > 1.0F;
         if (stack != null && (flag || flag2) && this.random.nextFloat() - (float)count * 0.01F < this.factorArray[index]) {
            if (!flag2 && stack.isActive3()) {
               int count2 = Math.max(stack.processAction() - 25, 1);
               int count3 = stack.processAction() - this.random.nextInt(this.random.nextInt(count2) + 1);
               if (count3 > count2) {
                  count3 = count2;
               }

               if (count3 < 1) {
                  count3 = 1;
               }

               stack.applyIndexByIndex(count3);
            }

            this.resolveEntityItem(stack, 0.0F);
         }
      }
   }

   public Entity getEntity6() {
      return this.entity4;
   }

   @Override
   public final boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      if (this.isEnabled23() && this.getEntity6() == player) {
         this.applyFlag(true, !player.invulnerableManager.enabled2);
         return true;
      } else {
         ItemStack slot = player.inventoryPlayer.getCurrentItem();
         if (slot != null && slot.getItem() == Items.lead && this.isActive26()) {
            if (!(this instanceof HitBoxAdapter) || !((HitBoxAdapter)this).isActive32()) {
               this.applyEntity(player, true);
               slot.animationsToGo--;
               return true;
            }

            if (((HitBoxAdapter)this).matchesEntityLivingBase3(player)) {
               this.applyEntity(player, true);
               slot.animationsToGo--;
               return true;
            }
         }

         return this.matchesEntityPlayer6(player) || super.matchesEntityPlayerByEntityPlayer(player);
      }
   }

   public void applyEntityByEntity(Entity entity, float factor, float factor2) {
      double offset = entity.posX - this.posX;
      double offset2 = entity.posZ - this.posZ;
      double offset3;
      if (entity instanceof EntityLivingBase) {
         EntityLivingBase livingEntity = (EntityLivingBase)entity;
         offset3 = livingEntity.posY + (double)livingEntity.getHeight2() - (this.posY + (double)this.getHeight2());
      } else {
         offset3 = (entity.getBoundingBox2().minY + entity.getBoundingBox2().maxY) / 2.0 - (this.posY + (double)this.getHeight2());
      }

      double offset4 = (double)MathHelper.sqrt_double(offset * offset + offset2 * offset2);
      float factor3 = (float)(MathHelper.getDouble6(offset2, offset) * 180.0 / Math.PI) - 90.0F;
      float factor4 = (float)(-(MathHelper.getDouble6(offset3, offset4) * 180.0 / Math.PI));
      this.rotationPitch = this.computeFactor(this.rotationPitch, factor4, factor2);
      this.rotationYaw = this.computeFactor(this.rotationYaw, factor3, factor);
   }

   @Override
   public int nextInt(EntityPlayer player) {
      if (this.count26 > 0) {
         int count = this.count26;
         ItemStack[] stack = this.getItemStackArray2();

         for (int index = 0; index < stack.length; index++) {
            if (stack[index] != null && this.factorArray[index] <= 1.0F) {
               count += 1 + this.random.nextInt(3);
            }
         }

         return count;
      } else {
         return this.count26;
      }
   }

   public boolean matchesClass(Class clazz) {
      return clazz != EntityGhast.class;
   }

   @Override
   public ItemStack getItemStack() {
      return this.itemStackArray2[0];
   }

   public EntityRavagerAdapter getEntityRavagerAdapter() {
      return this.entityRavagerAdapter;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("CanPickUpLoot", 1)) {
         this.setEnabled22(tagCompound.matchesText("CanPickUpLoot"));
      }

      this.enabled21 = tagCompound.matchesText("PersistenceRequired");
      if (tagCompound.matchesText("Equipment", 9)) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Equipment", 10);

         for (int index = 0; index < this.itemStackArray2.length; index++) {
            this.itemStackArray2[index] = ItemStack.resolveItemStack(extendedLoggerEntry.getNBTTagCompound(index));
         }
      }

      if (tagCompound.matchesText("DropChances", 9)) {
         NBTTagList extendedLoggerEntry2 = tagCompound.getExtendedLoggerEntry("DropChances", 5);

         for (int index2 = 0; index2 < extendedLoggerEntry2.getInt(); index2++) {
            this.factorArray[index2] = extendedLoggerEntry2.computeFactor(index2);
         }
      }

      this.enabled23 = tagCompound.matchesText("Leashed");
      if (this.enabled23 && tagCompound.matchesText("Leash", 10)) {
         this.nBTTagCompound = tagCompound.getNBTTagCompound("Leash");
      }

      this.setEnabled5(tagCompound.matchesText("NoAI"));
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

      if (stack != null && computeCount(stack) != count2 && (count2 != 4 || !(stack.getItem() instanceof ItemBlock))) {
         return false;
      } else {
         this.applyItemStack(count2, stack);
         return true;
      }
   }

   public PrimaryItemEntry getPrimaryItemEntry() {
      return this.primaryItemEntry;
   }

   public static Item resolveItem(int count, int count2) {
      switch (count) {
         case 4:
            if (count2 == 0) {
               return Items.leatherHelmet;
            } else if (count2 == 1) {
               return Items.goldenHelmet;
            } else if (count2 == 2) {
               return Items.chainmailHelmet;
            } else if (count2 == 3) {
               return Items.ironHelmet;
            } else if (count2 == 4) {
               return Items.diamondHelmet;
            }
         case 3:
            if (count2 == 0) {
               return Items.leatherChestplate;
            } else if (count2 == 1) {
               return Items.goldenChestplate;
            } else if (count2 == 2) {
               return Items.chainmailChestplate;
            } else if (count2 == 3) {
               return Items.ironChestplate;
            } else if (count2 == 4) {
               return Items.diamondChestplate;
            }
         case 2:
            if (count2 == 0) {
               return Items.leatherLeggings;
            } else if (count2 == 1) {
               return Items.goldenLeggings;
            } else if (count2 == 2) {
               return Items.chainmailLeggings;
            } else if (count2 == 3) {
               return Items.ironLeggings;
            } else if (count2 == 4) {
               return Items.diamondLeggings;
            }
         case 1:
            if (count2 == 0) {
               return Items.leatherBoots;
            } else if (count2 == 1) {
               return Items.goldenBoots;
            } else if (count2 == 2) {
               return Items.chainmailBoots;
            } else if (count2 == 3) {
               return Items.ironBoots;
            } else if (count2 == 4) {
               return Items.diamondBoots;
            }
         default:
            return null;
      }
   }

   @Override
   public int getCount3() {
      if (this.getEntityLivingBase4() == null) {
         return 3;
      } else {
         int count = (int)(this.getFloat2() - this.getFloat() * 0.33F) - (3 - this.worldObj.getGameSettingsPeacefulType().getIndex()) * 4;
         if (count < 0) {
            count = 0;
         }

         return count + 3;
      }
   }

   @Override
   public void applyItemStack(int count, ItemStack stack) {
      this.itemStackArray2[count] = stack;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAdvancedMapEntry().resolveAuxiliaryListenerByExtendedListener(SharedMonsterAttributes.extendedListener4).applyDouble(16.0);
   }

   public boolean isActive23() {
      return this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2(), this)
         && this.worldObj.getList2(this, this.getBoundingBox2()).isEmpty()
         && !this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2());
   }

   @Override
   public boolean isActive18() {
      return super.isActive18() && !this.isActive25();
   }

   public void applyGoldenOre(GoldenOre goldenOre) {
      if (this.random.nextFloat() < 0.15F * goldenOre.getFactor()) {
         int count = this.random.nextInt(2);
         float factor = this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD ? 0.1F : 0.25F;
         if (this.random.nextFloat() < 0.095F) {
            count++;
         }

         if (this.random.nextFloat() < 0.095F) {
            count++;
         }

         if (this.random.nextFloat() < 0.095F) {
            count++;
         }

         for (int index = 3; index >= 0; index--) {
            ItemStack stack = this.getItemStack3(index);
            if (index < 3 && this.random.nextFloat() < factor) {
               break;
            }

            if (stack == null) {
               Item item = resolveItem(index + 1, count);
               if (item != null) {
                  this.applyItemStack(index + 1, new ItemStack(item));
               }
            }
         }
      }
   }

   public PathfindManager getPathfindManager() {
      return this.pathfindManager;
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      Item gunpowder = this.getGunpowder();
      if (gunpowder != null) {
         int count2 = this.random.nextInt(3);
         if (count > 0) {
            count2 += this.random.nextInt(count + 1);
         }

         for (int index = 0; index < count2; index++) {
            this.resolveEntityItem(gunpowder, 1);
         }
      }
   }

   public static int computeCount(ItemStack stack) {
      if (stack.getItem() == Item.processAction2(Blocks.block146) || stack.getItem() == Items.skull) {
         return 4;
      } else {
         if (stack.getItem() instanceof ItemGoldenBoots) {
            switch (((ItemGoldenBoots)stack.getItem()).indexOffset3) {
               case 0:
                  return 4;
               case 1:
                  return 3;
               case 2:
                  return 2;
               case 3:
                  return 1;
            }
         }

         return 0;
      }
   }

   public void applyIndex(int count, float factor) {
      this.factorArray[count] = factor;
   }

   public void applyGoldenOreByGoldenOre(GoldenOre goldenOre) {
      float factor = goldenOre.getFactor();
      if (this.getItemStack() != null && this.random.nextFloat() < 0.25F * factor) {
         LvlManager.resolveItemStack(this.random, this.getItemStack(), (int)(5.0F + factor * (float)this.random.nextInt(18)));
      }

      for (int index = 0; index < 4; index++) {
         ItemStack stack = this.getItemStack3(index);
         if (stack != null && this.random.nextFloat() < 0.5F * factor) {
            LvlManager.resolveItemStack(this.random, stack, (int)(5.0F + factor * (float)this.random.nextInt(18)));
         }
      }
   }

   @Override
   public ItemStack[] getItemStackArray2() {
      return this.itemStackArray2;
   }

   public Item getGunpowder() {
      return null;
   }

   public EntityLivingBase getEntityLivingBase4() {
      return this.entityLivingBase;
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 20) {
         this.updateState44();
      } else {
         super.processByte(byteValue);
      }
   }

   public PathfindManager resolvePathfindManager(World world) {
      return new PathfindManagerAdapter(this, world);
   }

   public void setFactor30(float factor) {
      this.scaleFactor3 = factor;
   }

   public void setEntityLivingBase(EntityLivingBase entityLivingBase2) {
      this.entityLivingBase = entityLivingBase2;
   }

   @Override
   public ItemStack resolveItemStack(int count) {
      return this.itemStackArray2[count];
   }

   public boolean matchesEntityPlayer6(EntityPlayer player) {
      return false;
   }

   public EntityPillagerAdapter getEntityPillagerAdapter() {
      return this.entityPillagerAdapter;
   }

   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4)
         .applyInvalidOperationManager(new InvalidOperationManager("Random spawn bonus", this.random.nextGaussian() * 0.05, 1));
      return postRenderConsumer;
   }

   public String getText3() {
      return null;
   }

   public CanSeeManager getCanSeeManager() {
      return this.canSeeManager;
   }

   @Override
   public float computeFactor(float factor, float factor2) {
      this.auxiliaryEntityLivingBaseEntry.updateState();
      return factor2;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("CanPickUpLoot", this.isEnabled22());
      tagCompound.applyText("PersistenceRequired", this.enabled21);
      NBTTagList nBTTagList = new NBTTagList();

      for (int index = 0; index < this.itemStackArray2.length; index++) {
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         if (this.itemStackArray2[index] != null) {
            this.itemStackArray2[index].resolveNBTTagCompound(tagCompound2);
         }

         nBTTagList.applyLISTManager(tagCompound2);
      }

      tagCompound.applyText("Equipment", nBTTagList);
      NBTTagList nBTTagList2 = new NBTTagList();

      for (int index2 = 0; index2 < this.factorArray.length; index2++) {
         nBTTagList2.applyLISTManager(new InternalClientManagerRegistry(this.factorArray[index2]));
      }

      tagCompound.applyText("DropChances", nBTTagList2);
      tagCompound.applyText("Leashed", this.enabled23);
      if (this.entity4 != null) {
         NBTTagCompound tagCompound3 = new NBTTagCompound();
         if (this.entity4 instanceof EntityLivingBase) {
            tagCompound3.put("UUIDMost", this.entity4.getEntityUniqueID().getMostSignificantBits());
            tagCompound3.put("UUIDLeast", this.entity4.getEntityUniqueID().getLeastSignificantBits());
         } else if (this.entity4 instanceof CustomEntityAdapter) {
            BlockPos blockPos = ((CustomEntityAdapter)this.entity4).getBlockPos2();
            tagCompound3.put("X", blockPos.getX());
            tagCompound3.put("Y", blockPos.getY());
            tagCompound3.put("Z", blockPos.getZ());
         }

         tagCompound.applyText("Leash", tagCompound3);
      }

      if (this.isActive25()) {
         tagCompound.applyText("NoAI", this.isActive25());
      }
   }

   @Override
   public ItemStack getItemStack3(int count) {
      return this.itemStackArray2[count + 1];
   }

   public void applyFlag(boolean flag, boolean flag2) {
      if (this.enabled23) {
         this.enabled23 = false;
         this.entity4 = null;
         if (!this.worldObj.state && flag2) {
            this.resolveEntityItem(Items.lead, 1);
         }

         if (!this.worldObj.state && flag && this.worldObj instanceof WorldServer) {
            ((WorldServer)this.worldObj).getSecondaryLoggerEntry().applyEntity3(this, new S1BPacketEntityAttach(1, this, null));
         }
      }
   }

   private float computeFactor(float posX, float posY, float posZ) {
      float factor = MathHelper.sqrt_float(posY - posX);
      if (factor > posZ) {
         factor = posZ;
      }

      if (factor < -posZ) {
         factor = -posZ;
      }

      return posX + factor;
   }

   @Override
   public void setScaleFactor6(float scaleFactor) {
      super.setScaleFactor6(scaleFactor);
      this.setFactor30(scaleFactor);
   }

   public boolean matchesItemStack(ItemStack stack) {
      return true;
   }

   public int getCount6() {
      return 4;
   }

   public int getCount7() {
      return 80;
   }

   @Override
   public final void updateState38() {
      this.indexOffset14++;
      this.worldObj.uNKNOWNManager.applyTextByText("checkDespawn");
      this.updateState4();
      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("sensing");
      this.canSeeManager.updateState();
      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("targetSelector");
      this.goalSetupManager2.updateState();
      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("goalSelector");
      this.goalSetupManager.updateState();
      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("navigation");
      this.pathfindManager.updateState3();
      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("mob tick");
      this.updateState3();
      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("controls");
      this.worldObj.uNKNOWNManager.applyTextByText("move");
      this.entityPillagerAdapter.updateState();
      this.worldObj.uNKNOWNManager.applyText("look");
      this.entityRavagerAdapter.updateState();
      this.worldObj.uNKNOWNManager.applyText("jump");
      this.primaryItemEntry.updateState2();
      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.updateState();
   }

   public void updateState44() {
      if (this.worldObj.state) {
         for (int index = 0; index < 20; index++) {
            double offset = this.random.nextGaussian() * 0.02;
            double offset2 = this.random.nextGaussian() * 0.02;
            double offset3 = this.random.nextGaussian() * 0.02;
            double offset4 = 10.0;
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.EXPLOSION_NORMAL,
                  this.posX + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width - offset * offset4,
                  this.posY + (double)(this.random.nextFloat() * this.height) - offset2 * offset4,
                  this.posZ + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width - offset3 * offset4,
                  offset,
                  offset2,
                  offset3,
                  new int[0]
               );
         }
      } else {
         this.worldObj.applyEntity(this, (byte)20);
      }
   }

   public void updateState42() {
      if (this.nBTTagCompound != null) {
         this.updateState45();
      }

      if (this.enabled23) {
         if (!this.canExecute()) {
            this.applyFlag(true, true);
         }

         if (this.entity4 == null || this.entity4.isDead) {
            this.applyFlag(true, true);
         }
      }
   }

   public void updateState6() {
   }

   public void updateState3() {
   }

   private void updateState46() {
      this.indexOffset14++;
      if (this instanceof EntityMonster && this.computeFactor(1.0F) > 0.5F) {
         this.indexOffset14 += 2;
      }

      this.updateState4();
   }

   public void updateState43() {
      String text = this.getText3();
      if (text != null) {
         this.applyText(text, this.getFactor(), this.getFactorAsFloat());
      }
   }

   private void updateState45() {
      if (this.enabled23 && this.nBTTagCompound != null) {
         if (this.nBTTagCompound.matchesText("UUIDMost", 4) && this.nBTTagCompound.matchesText("UUIDLeast", 4)) {
            UUID uuid = new UUID(this.nBTTagCompound.computeTimestamp("UUIDMost"), this.nBTTagCompound.computeTimestamp("UUIDLeast"));

            for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>)(Iterable<?>)this.worldObj.resolveList(EntityLivingBase.class, this.getBoundingBox2().offset(10.0, 10.0, 10.0))) {
               if (livingEntity.getEntityUniqueID().equals(uuid)) {
                  this.entity4 = livingEntity;
                  break;
               }
            }
         } else if (this.nBTTagCompound.matchesText("X", 99) && this.nBTTagCompound.matchesText("Y", 99) && this.nBTTagCompound.matchesText("Z", 99)) {
            BlockPos blockPos = new BlockPos(this.nBTTagCompound.computeCount("X"), this.nBTTagCompound.computeCount("Y"), this.nBTTagCompound.computeCount("Z"));
            EntityLeashknot entityLeashknot = EntityLeashknot.resolveEntityLeashknot3(this.worldObj, blockPos);
            if (entityLeashknot == null) {
               entityLeashknot = EntityLeashknot.resolveEntityLeashknotByWorld(this.worldObj, blockPos);
            }

            this.entity4 = entityLeashknot;
         } else {
            this.applyFlag(false, true);
         }
      }

      this.nBTTagCompound = null;
   }

   public void updateState5() {
      this.enabled21 = true;
   }

   public void updateState4() {
      if (this.enabled21) {
         this.indexOffset14 = 0;
      } else {
         EntityPlayer player = this.worldObj.resolveEntityPlayerByEntity(this, -1.0);
         if (player != null) {
            double offset = player.posX - this.posX;
            double offset2 = player.posY - this.posY;
            double offset3 = player.posZ - this.posZ;
            double offset4 = offset * offset + offset2 * offset2 + offset3 * offset3;
            if (this.isActive24() && offset4 > 16384.0) {
               this.updateState9();
            }

            if (this.indexOffset14 > 600 && this.random.nextInt(800) == 0 && offset4 > 1024.0 && this.isActive24()) {
               this.updateState9();
            } else if (offset4 < 1024.0) {
               this.indexOffset14 = 0;
            }
         }
      }
   }

   public int getCount8() {
      return 40;
   }

   public boolean isActive25() {
      return this.dataWatcher.computeByteCode(15) != 0;
   }

   public boolean isEnabled22() {
      return this.enabled22;
   }

   @Override
   public void updateState10() {
      super.updateState10();
      this.worldObj.uNKNOWNManager.applyTextByText("mobBaseTick");
      if (this.canExecute() && this.random.nextInt(1000) < this.count25++) {
         this.count25 = -this.getCount7();
         this.updateState43();
      }

      this.worldObj.uNKNOWNManager.updateState();
   }

   public void setEnabled5(boolean enabled) {
      this.dataWatcher.applyTargetByTarget(15, (byte)(enabled ? 1 : 0));
   }

   public boolean isEnabled21() {
      return this.enabled21;
   }

   @Override
   public void updateState() {
      if (BuildManager.isActive52() && this.isActive28()) {
         this.updateState46();
      } else {
         super.updateState();
         if (!this.worldObj.state) {
            this.updateState42();
         }
      }
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(15, (byte)0);
   }

   public void setEnabled22(boolean enabled) {
      this.enabled22 = enabled;
   }

   public boolean isActive24() {
      return true;
   }

   public boolean isActive27() {
      return false;
   }

   public float getFactor2() {
      return 1.0F;
   }

   private boolean isActive28() {
      if (this.isActive16()) {
         return false;
      } else if (this.hurtTime > 0) {
         return false;
      } else if (this.ticksExisted < 20) {
         return false;
      } else {
         World worldObj = this.getWorldObj();
         if (worldObj == null) {
            return false;
         } else if (worldObj.itemList7.size() != 1) {
            return false;
         } else {
            Entity entity = (Entity)worldObj.itemList7.get(0);
            double offset = Math.max(Math.abs(this.posX - entity.posX) - 16.0, 0.0);
            double offset2 = Math.max(Math.abs(this.posZ - entity.posZ) - 16.0, 0.0);
            double offset3 = offset * offset + offset2 * offset2;
            return !this.checkDouble(offset3);
         }
      }
   }

   public boolean isEnabled23() {
      return this.enabled23;
   }

   @Override
   public void updateState33() {
      super.updateState33();
      this.worldObj.uNKNOWNManager.applyTextByText("looting");
      if (!this.worldObj.state && this.isEnabled22() && !this.enabled17 && this.worldObj.getDoFireTickManager().matchesText("mobGriefing")) {
         for (EntityItem entityItem : (Iterable<EntityItem>)(Iterable<?>)this.worldObj.resolveList(EntityItem.class, this.getBoundingBox2().offset(1.0, 0.0, 1.0))) {
            if (!entityItem.isDead && entityItem.getItemStack() != null && !entityItem.isActive16()) {
               this.applyEntityItem(entityItem);
            }
         }
      }

      this.worldObj.uNKNOWNManager.updateState();
   }

   public boolean nextInt() {
      return true;
   }

   public EntityMob(World world) {
      super(world);
      this.goalSetupManager = new GoalSetupManager(world != null && world.uNKNOWNManager != null ? world.uNKNOWNManager : null);
      this.goalSetupManager2 = new GoalSetupManager(world != null && world.uNKNOWNManager != null ? world.uNKNOWNManager : null);
      this.entityRavagerAdapter = new EntityRavagerAdapter(this);
      this.entityPillagerAdapter = new EntityPillagerAdapter(this);
      this.primaryItemEntry = new PrimaryItemEntry(this);
      this.auxiliaryEntityLivingBaseEntry = new AuxiliaryEntityLivingBaseEntry(this);
      this.pathfindManager = this.resolvePathfindManager(world);
      this.canSeeManager = new CanSeeManager(this);

      for (int index = 0; index < this.factorArray.length; index++) {
         this.factorArray[index] = 0.085F;
      }

      this.getEntityUniqueID().getLeastSignificantBits();
   }
}
