package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotations;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityArmorStand extends EntityLivingBase {
   private static final Rotations DEFAULT_HEAD_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
   private static final Rotations DEFAULT_BODY_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
   private static final Rotations DEFAULT_LEFTARM_ROTATION = new Rotations(-10.0F, 0.0F, -10.0F);
   private static final Rotations DEFAULT_RIGHTARM_ROTATION = new Rotations(-15.0F, 0.0F, 10.0F);
   private static final Rotations DEFAULT_LEFTLEG_ROTATION = new Rotations(-1.0F, 0.0F, -1.0F);
   private static final Rotations DEFAULT_RIGHTLEG_ROTATION = new Rotations(1.0F, 0.0F, 1.0F);
   private final ItemStack[] contents = new ItemStack[5];
   private boolean canInteract;
   private long punchCooldown;
   private int disabledSlots;
   private boolean wasMarker;
   private Rotations headRotation = DEFAULT_HEAD_ROTATION;
   private Rotations bodyRotation = DEFAULT_BODY_ROTATION;
   private Rotations leftArmRotation = DEFAULT_LEFTARM_ROTATION;
   private Rotations rightArmRotation = DEFAULT_RIGHTARM_ROTATION;
   private Rotations leftLegRotation = DEFAULT_LEFTLEG_ROTATION;
   private Rotations rightLegRotation = DEFAULT_RIGHTLEG_ROTATION;

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.entityWorld.isRemote) {
         return false;
      } else if (DamageSource.outOfWorld.equals(damageSource)) {
         this.setDead();
         return false;
      } else if (this.isEntityInvulnerable(damageSource) || this.canInteract || this.hasMarker()) {
         return false;
      } else if (damageSource.isExplosion()) {
         this.dropContents();
         this.setDead();
         return false;
      } else if (DamageSource.inFire.equals(damageSource)) {
         if (!this.isBurning()) {
            this.setFire(5);
         } else {
            this.damageArmorStand(0.15F);
         }

         return false;
      } else if (DamageSource.onFire.equals(damageSource) && this.getCurrentHealth() > 0.5F) {
         this.damageArmorStand(4.0F);
         return false;
      } else {
         boolean flag = "arrow".equals(damageSource.getDamageType());
         if (!"player".equals(damageSource.getDamageType()) && !flag) {
            return false;
         } else {
            if (damageSource.getSourceOfDamage() instanceof EntityArrow) {
               damageSource.getSourceOfDamage().setDead();
            }

            if (damageSource.getEntity() instanceof EntityPlayer && !((EntityPlayer)damageSource.getEntity()).capabilities.allowEdit) {
               return false;
            } else if (damageSource.isCreativePlayer()) {
               this.playParticles();
               this.setDead();
               return false;
            } else {
               long totalWorldTime = this.entityWorld.getTotalWorldTime();
               if (totalWorldTime - this.punchCooldown > 5L && !flag) {
                  this.punchCooldown = totalWorldTime;
               } else {
                  this.dropBlock();
                  this.playParticles();
                  this.setDead();
               }

               return false;
            }
         }
      }
   }

   public Rotations getLeftArmRotation() {
      return this.leftArmRotation;
   }

   @Override
   public boolean canBeCollidedWith() {
      return super.canBeCollidedWith() && !this.hasMarker();
   }

   @Override
   public boolean interactAt(EntityPlayer player, Vec3 vec) {
      if (this.hasMarker()) {
         return false;
      } else if (!this.entityWorld.isRemote && !player.isSpectator()) {
         byte b = 0;
         ItemStack currentEquippedItem = player.getCurrentEquippedItem();
         boolean flag = currentEquippedItem != null;
         if (flag && currentEquippedItem.getItem() instanceof ItemArmor) {
            ItemArmor item = (ItemArmor)currentEquippedItem.getItem();
            if (item.armorType == 3) {
               b = 1;
            } else if (item.armorType == 2) {
               b = 2;
            } else if (item.armorType == 1) {
               b = 3;
            } else if (item.armorType == 0) {
               b = 4;
            }
         }

         if (flag && (currentEquippedItem.getItem() == Items.skull || currentEquippedItem.getItem() == Item.getItemFromBlock(Blocks.pumpkin))) {
            b = 4;
         }

         double d = 0.1;
         double d2 = 0.9;
         double d3 = 0.4;
         double d4 = 1.6;
         byte b2 = 0;
         boolean isSmall = this.isSmall();
         double d5 = isSmall ? vec.yCoord * 2.0 : vec.yCoord;
         if (d5 >= 0.1 && d5 < 0.1 + (isSmall ? 0.8 : 0.45) && this.contents[1] != null) {
            b2 = 1;
         } else if (d5 >= 0.9 + (isSmall ? 0.3 : 0.0) && d5 < 0.9 + (isSmall ? 1.0 : 0.7) && this.contents[3] != null) {
            b2 = 3;
         } else if (d5 >= 0.4 && d5 < 0.4 + (isSmall ? 1.0 : 0.8) && this.contents[2] != null) {
            b2 = 2;
         } else if (d5 >= 1.6 && this.contents[4] != null) {
            b2 = 4;
         }

         boolean flag2 = this.contents[b2] != null;
         if ((this.disabledSlots & 1 << b2) != 0 || (this.disabledSlots & 1 << b) != 0) {
            b2 = b;
            if ((this.disabledSlots & 1 << b) != 0) {
               if ((this.disabledSlots & 1) != 0) {
                  return true;
               }

               b2 = 0;
            }
         }

         if (flag && b == 0 && !this.getShowArms()) {
            return true;
         } else {
            if (flag) {
               this.swapItem(player, b);
            } else if (flag2) {
               this.swapItem(player, b2);
            }

            return true;
         }
      } else {
         return true;
      }
   }

   @Override
   public void moveEntityWithHeading(float f, float f2) {
      if (!this.hasNoGravity()) {
         super.moveEntityWithHeading(f, f2);
      }
   }

   @Override
   public void setCurrentItemOrArmor(int n, ItemStack stack) {
      this.contents[n] = stack;
   }

   @Override
   public ItemStack[] getInventory() {
      return this.contents;
   }

   public EntityArmorStand(World world) {
      super(world);
      this.setSilent(true);
      this.noClip = this.hasNoGravity();
      this.setSize(0.5F, 1.975F);
   }

   @Override
   public ItemStack getCurrentArmor(int n) {
      return this.contents[n + 1];
   }

   @Override
   public void updatePotionMetadata() {
      this.setInvisible(this.canInteract);
   }

   public boolean hasNoBasePlate() {
      return (this.dataWatcher.getWatchableObjectByte(10) & 8) != 0;
   }

   @Override
   public boolean isInRangeToRenderDist(double d) {
      double d2 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0;
      if (Double.isNaN(d2) || d2 == 0.0) {
         d2 = 4.0;
      }

      d2 *= 64.0;
      return d < d2 * d2;
   }

   private void setShowArms(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(10);
      if (flag) {
         watchableObjectByte = (byte)(watchableObjectByte | 4);
      } else {
         watchableObjectByte = (byte)(watchableObjectByte & -5);
      }

      this.dataWatcher.updateObject(10, watchableObjectByte);
   }

   private void dropContents() {
      for (int i = 0; i < this.contents.length; i++) {
         if (this.contents[i] != null && this.contents[i].stackSize > 0) {
            if (this.contents[i] != null) {
               Block.spawnAsEntity(this.entityWorld, new BlockPos(this).up(), this.contents[i]);
            }

            this.contents[i] = null;
         }
      }
   }

   @Override
   public void collideWithEntity(Entity entity) {
   }

   @Override
   public boolean replaceItemInInventory(int n, ItemStack stack) {
      int n2;
      if (n == 99) {
         n2 = 0;
      } else {
         n2 = n - 100 + 1;
         if (n2 < 0 || n2 >= this.contents.length) {
            return false;
         }
      }

      if (stack == null || EntityLiving.getArmorPosition(stack) == n2 || n2 == 4 && stack.getItem() instanceof ItemBlock) {
         this.setCurrentItemOrArmor(n2, stack);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean canBePushed() {
      return false;
   }

   private void setNoBasePlate(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(10);
      if (flag) {
         watchableObjectByte = (byte)(watchableObjectByte | 8);
      } else {
         watchableObjectByte = (byte)(watchableObjectByte & -9);
      }

      this.dataWatcher.updateObject(10, watchableObjectByte);
   }

   private void writePoseToNBT(NBTTagCompound tag) {
      NBTTagList tagList = tag.getTagList("Head", 5);
      if (tagList.tagCount() > 0) {
         this.setHeadRotation(new Rotations(tagList));
      } else {
         this.setHeadRotation(DEFAULT_HEAD_ROTATION);
      }

      NBTTagList tagList2 = tag.getTagList("Body", 5);
      if (tagList2.tagCount() > 0) {
         this.setBodyRotation(new Rotations(tagList2));
      } else {
         this.setBodyRotation(DEFAULT_BODY_ROTATION);
      }

      NBTTagList tagList3 = tag.getTagList("LeftArm", 5);
      if (tagList3.tagCount() > 0) {
         this.setLeftArmRotation(new Rotations(tagList3));
      } else {
         this.setLeftArmRotation(DEFAULT_LEFTARM_ROTATION);
      }

      NBTTagList tagList4 = tag.getTagList("RightArm", 5);
      if (tagList4.tagCount() > 0) {
         this.setRightArmRotation(new Rotations(tagList4));
      } else {
         this.setRightArmRotation(DEFAULT_RIGHTARM_ROTATION);
      }

      NBTTagList tagList5 = tag.getTagList("LeftLeg", 5);
      if (tagList5.tagCount() > 0) {
         this.setLeftLegRotation(new Rotations(tagList5));
      } else {
         this.setLeftLegRotation(DEFAULT_LEFTLEG_ROTATION);
      }

      NBTTagList tagList6 = tag.getTagList("RightLeg", 5);
      if (tagList6.tagCount() > 0) {
         this.setRightLegRotation(new Rotations(tagList6));
      } else {
         this.setRightLegRotation(DEFAULT_RIGHTLEG_ROTATION);
      }
   }

   @Override
   public void onKillCommand() {
      this.setDead();
   }

   public boolean hasMarker() {
      return (this.dataWatcher.getWatchableObjectByte(10) & 16) != 0;
   }

   public Rotations getRightArmRotation() {
      return this.rightArmRotation;
   }

   private void swapItem(EntityPlayer player, int n) {
      ItemStack stack = this.contents[n];
      if ((stack == null || (this.disabledSlots & 1 << n + 8) == 0) && (stack != null || (this.disabledSlots & 1 << n + 16) == 0)) {
         int currentItem = player.inventory.currentItem;
         ItemStack stackInSlot = player.inventory.getStackInSlot(currentItem);
         if (player.capabilities.isCreativeMode && (stack == null || stack.getItem() == Item.getItemFromBlock(Blocks.air)) && stackInSlot != null) {
            ItemStack stack2 = stackInSlot.copy();
            stack2.stackSize = 1;
            this.setCurrentItemOrArmor(n, stack2);
         } else if (stackInSlot == null || stackInSlot.stackSize <= 1) {
            this.setCurrentItemOrArmor(n, stackInSlot);
            player.inventory.setInventorySlotContents(currentItem, stack);
         } else if (stack == null) {
            ItemStack stack3 = stackInSlot.copy();
            stack3.stackSize = 1;
            this.setCurrentItemOrArmor(n, stack3);
            stackInSlot.stackSize--;
         }
      }
   }

   public void setLeftArmRotation(Rotations rotations) {
      this.leftArmRotation = rotations;
      this.dataWatcher.updateObject(13, rotations);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.contents.length; i++) {
         NBTTagCompound tag2 = new NBTTagCompound();
         if (this.contents[i] != null) {
            this.contents[i].writeToNBT(tag2);
         }

         tagList.appendTag(tag2);
      }

      tag.setTag("Equipment", tagList);
      if (this.getAlwaysRenderNameTag() && (this.getCustomNameTag() == null || this.getCustomNameTag().length() == 0)) {
         tag.setBoolean("CustomNameVisible", this.getAlwaysRenderNameTag());
      }

      tag.setBoolean("Invisible", this.isInvisible());
      tag.setBoolean("Small", this.isSmall());
      tag.setBoolean("ShowArms", this.getShowArms());
      tag.setInteger("DisabledSlots", this.disabledSlots);
      tag.setBoolean("NoGravity", this.hasNoGravity());
      tag.setBoolean("NoBasePlate", this.hasNoBasePlate());
      if (this.hasMarker()) {
         tag.setBoolean("Marker", this.hasMarker());
      }

      tag.setTag("Pose", this.readPoseFromNBT());
   }

   private void damageArmorStand(float f) {
      float currentHealth = this.getCurrentHealth();
      currentHealth -= f;
      if (currentHealth <= 0.5F) {
         this.dropContents();
         this.setDead();
      } else {
         this.setHealth(currentHealth);
      }
   }

   @Override
   public ItemStack getHeldItem() {
      return this.contents[0];
   }

   private void setNoGravity(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(10);
      if (flag) {
         watchableObjectByte = (byte)(watchableObjectByte | 2);
      } else {
         watchableObjectByte = (byte)(watchableObjectByte & -3);
      }

      this.dataWatcher.updateObject(10, watchableObjectByte);
   }

   @Override
   public boolean isImmuneToExplosions() {
      return this.isInvisible();
   }

   public Rotations getLeftLegRotation() {
      return this.leftLegRotation;
   }

   @Override
   public void setInvisible(boolean flag) {
      this.canInteract = flag;
      super.setInvisible(flag);
   }

   public void setLeftLegRotation(Rotations rotations) {
      this.leftLegRotation = rotations;
      this.dataWatcher.updateObject(15, rotations);
   }

   @Override
   public boolean isChild() {
      return this.isSmall();
   }

   public Rotations getBodyRotation() {
      return this.bodyRotation;
   }

   public Rotations getHeadRotation() {
      return this.headRotation;
   }

   @Override
   public float getEyeHeight() {
      return this.isChild() ? this.height * 0.5F : this.height * 0.9F;
   }

   public void setRightArmRotation(Rotations rotations) {
      this.rightArmRotation = rotations;
      this.dataWatcher.updateObject(14, rotations);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      if (tag.hasKey("Equipment", 9)) {
         NBTTagList tagList = tag.getTagList("Equipment", 10);

         for (int i = 0; i < this.contents.length; i++) {
            this.contents[i] = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(i));
         }
      }

      this.setInvisible(tag.getBoolean("Invisible"));
      this.setSmall(tag.getBoolean("Small"));
      this.setShowArms(tag.getBoolean("ShowArms"));
      this.disabledSlots = tag.getInteger("DisabledSlots");
      this.setNoGravity(tag.getBoolean("NoGravity"));
      this.setNoBasePlate(tag.getBoolean("NoBasePlate"));
      this.setMarker(tag.getBoolean("Marker"));
      this.wasMarker = !this.hasMarker();
      this.noClip = this.hasNoGravity();
      this.writePoseToNBT(tag.getCompoundTag("Pose"));
   }

   public boolean isSmall() {
      return (this.dataWatcher.getWatchableObjectByte(10) & 1) != 0;
   }

   private void dropBlock() {
      Block.spawnAsEntity(this.entityWorld, new BlockPos(this), new ItemStack(Items.armor_stand));
      this.dropContents();
   }

   private void setMarker(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(10);
      if (flag) {
         watchableObjectByte = (byte)(watchableObjectByte | 16);
      } else {
         watchableObjectByte = (byte)(watchableObjectByte & -17);
      }

      this.dataWatcher.updateObject(10, watchableObjectByte);
   }

   @Override
   public ItemStack getEquipmentInSlot(int n) {
      return this.contents[n];
   }

   public EntityArmorStand(World world, double d, double d2, double d3) {
      this(world);
      this.setPosition(d, d2, d3);
   }

   @Override
   public boolean isServerWorld() {
      return super.isServerWorld() && !this.hasNoGravity();
   }

   public boolean getShowArms() {
      return (this.dataWatcher.getWatchableObjectByte(10) & 4) != 0;
   }

   @Override
   public float updateDistance(float f, float f2) {
      this.prevRenderYawOffset = this.prevRotationYaw;
      this.renderYawOffset = this.rotationYaw;
      return 0.0F;
   }

   private void playParticles() {
      if (this.entityWorld instanceof WorldServer) {
         ((WorldServer)this.entityWorld)
            .spawnParticle(
               EnumParticleTypes.BLOCK_DUST,
               this.posX,
               this.posY + (double)this.height / 1.5,
               this.posZ,
               10,
               (double)(this.width / 4.0F),
               (double)(this.height / 4.0F),
               (double)(this.width / 4.0F),
               0.05,
               new int[]{Block.getStateId(Blocks.planks.getDefaultState())}
            );
      }
   }

   private void updateBoundingBox(boolean flag) {
      double posX2 = this.posX;
      double posY2 = this.posY;
      double posZ2 = this.posZ;
      if (flag) {
         this.setSize(0.5F, 1.975F);
      } else {
         this.setSize(0.0F, 0.0F);
      }

      this.setPosition(posX2, posY2, posZ2);
   }

   private NBTTagCompound readPoseFromNBT() {
      NBTTagCompound tag = new NBTTagCompound();
      if (!DEFAULT_HEAD_ROTATION.equals(this.headRotation)) {
         tag.setTag("Head", this.headRotation.writeToNBT());
      }

      if (!DEFAULT_BODY_ROTATION.equals(this.bodyRotation)) {
         tag.setTag("Body", this.bodyRotation.writeToNBT());
      }

      if (!DEFAULT_LEFTARM_ROTATION.equals(this.leftArmRotation)) {
         tag.setTag("LeftArm", this.leftArmRotation.writeToNBT());
      }

      if (!DEFAULT_RIGHTARM_ROTATION.equals(this.rightArmRotation)) {
         tag.setTag("RightArm", this.rightArmRotation.writeToNBT());
      }

      if (!DEFAULT_LEFTLEG_ROTATION.equals(this.leftLegRotation)) {
         tag.setTag("LeftLeg", this.leftLegRotation.writeToNBT());
      }

      if (!DEFAULT_RIGHTLEG_ROTATION.equals(this.rightLegRotation)) {
         tag.setTag("RightLeg", this.rightLegRotation.writeToNBT());
      }

      return tag;
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(10, (byte)0);
      this.dataWatcher.addObject(11, DEFAULT_HEAD_ROTATION);
      this.dataWatcher.addObject(12, DEFAULT_BODY_ROTATION);
      this.dataWatcher.addObject(13, DEFAULT_LEFTARM_ROTATION);
      this.dataWatcher.addObject(14, DEFAULT_RIGHTARM_ROTATION);
      this.dataWatcher.addObject(15, DEFAULT_LEFTLEG_ROTATION);
      this.dataWatcher.addObject(16, DEFAULT_RIGHTLEG_ROTATION);
   }

   public void setBodyRotation(Rotations rotations) {
      this.bodyRotation = rotations;
      this.dataWatcher.updateObject(12, rotations);
   }

   public void setHeadRotation(Rotations rotations) {
      this.headRotation = rotations;
      this.dataWatcher.updateObject(11, rotations);
   }

   public Rotations getRightLegRotation() {
      return this.rightLegRotation;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      Rotations rotations = this.dataWatcher.getWatchableObjectRotations(11);
      if (!this.headRotation.equals(rotations)) {
         this.setHeadRotation(rotations);
      }

      Rotations rotations2 = this.dataWatcher.getWatchableObjectRotations(12);
      if (!this.bodyRotation.equals(rotations2)) {
         this.setBodyRotation(rotations2);
      }

      Rotations rotations3 = this.dataWatcher.getWatchableObjectRotations(13);
      if (!this.leftArmRotation.equals(rotations3)) {
         this.setLeftArmRotation(rotations3);
      }

      Rotations rotations4 = this.dataWatcher.getWatchableObjectRotations(14);
      if (!this.rightArmRotation.equals(rotations4)) {
         this.setRightArmRotation(rotations4);
      }

      Rotations rotations5 = this.dataWatcher.getWatchableObjectRotations(15);
      if (!this.leftLegRotation.equals(rotations5)) {
         this.setLeftLegRotation(rotations5);
      }

      Rotations rotations6 = this.dataWatcher.getWatchableObjectRotations(16);
      if (!this.rightLegRotation.equals(rotations6)) {
         this.setRightLegRotation(rotations6);
      }

      boolean flag = this.hasMarker();
      if (!this.wasMarker && flag) {
         this.updateBoundingBox(false);
      } else {
         if (!this.wasMarker || flag) {
            return;
         }

         this.updateBoundingBox(true);
      }

      this.wasMarker = flag;
   }

   public void setRightLegRotation(Rotations rotations) {
      this.rightLegRotation = rotations;
      this.dataWatcher.updateObject(16, rotations);
   }

   @Override
   public void collideWithNearbyEntities() {
      List list = this.entityWorld.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
      if (list != null && !list.isEmpty()) {
         for (int i = 0; i < list.size(); i++) {
            Entity entity = (Entity)list.get(i);
            if (entity instanceof EntityMinecart
               && ((EntityMinecart)entity).getMinecartType() == EntityMinecart.EnumMinecartType.RIDEABLE
               && this.getDistanceSqToEntity(entity) <= 0.2) {
               entity.applyEntityCollision(this);
            }
         }
      }
   }

   private void setSmall(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(10);
      if (flag) {
         watchableObjectByte = (byte)(watchableObjectByte | 1);
      } else {
         watchableObjectByte = (byte)(watchableObjectByte & -2);
      }

      this.dataWatcher.updateObject(10, watchableObjectByte);
   }

   public boolean hasNoGravity() {
      return (this.dataWatcher.getWatchableObjectByte(10) & 2) != 0;
   }
}
