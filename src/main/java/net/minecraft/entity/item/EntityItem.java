package net.minecraft.entity.item;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityItem extends Entity {
   private static final Logger LOGGER = LogManager.getLogger();
   private int age;
   private int delayBeforeCanPickup;
   private int health = 5;
   private String thrower;
   private String owner;
   public float hoverStart = (float)(Math.random() * Math.PI * 2.0);

   public void setInfinitePickupDelay() {
      this.delayBeforeCanPickup = 32767;
   }

   public int getAge() {
      return this.age;
   }

   public EntityItem(World world) {
      super(world);
      this.setSize(0.25F, 0.25F);
      this.setEntityItemStack(new ItemStack(Blocks.air, 0));
   }

   public void setThrower(String str) {
      this.thrower = str;
   }

   public EntityItem(World world, double d, double d2, double d3) {
      super(world);
      this.setSize(0.25F, 0.25F);
      this.setPosition(d, d2, d3);
      this.rotationYaw = (float)(Math.random() * 360.0);
      this.motionX = (double)((float)(Math.random() * 0.2F - 0.1F));
      this.motionY = 0.2F;
      this.motionZ = (double)((float)(Math.random() * 0.2F - 0.1F));
   }

   public ItemStack getEntityItem() {
      ItemStack stack = this.getDataWatcher().getWatchableObjectItemStack(10);
      return stack == null ? new ItemStack(Blocks.stone) : stack;
   }

   @Override
   public void entityInit() {
      this.getDataWatcher().addObjectByDataType(10, 5);
   }

   public void setDefaultPickupDelay() {
      this.delayBeforeCanPickup = 10;
   }

   public boolean cannotPickup() {
      return this.delayBeforeCanPickup > 0;
   }

   public EntityItem(World world, double d, double d2, double d3, ItemStack stack) {
      this(world, d, d2, d3);
      this.setEntityItemStack(stack);
   }

   @Override
   public void onCollideWithPlayer(EntityPlayer player) {
      if (!this.entityWorld.isRemote) {
         ItemStack entityItem = this.getEntityItem();
         int stackSize = entityItem.stackSize;
         if (this.delayBeforeCanPickup == 0
            && (this.owner == null || 6000 - this.age <= 200 || this.owner.equals(player.getName()))
            && player.inventory.addItemStackToInventory(entityItem)) {
            if (entityItem.getItem() == Item.getItemFromBlock(Blocks.log)) {
               player.triggerAchievement(AchievementList.mineWood);
            }

            if (entityItem.getItem() == Item.getItemFromBlock(Blocks.log2)) {
               player.triggerAchievement(AchievementList.mineWood);
            }

            if (entityItem.getItem() == Items.leather) {
               player.triggerAchievement(AchievementList.killCow);
            }

            if (entityItem.getItem() == Items.diamond) {
               player.triggerAchievement(AchievementList.diamonds);
            }

            if (entityItem.getItem() == Items.blaze_rod) {
               player.triggerAchievement(AchievementList.blazeRod);
            }

            if (entityItem.getItem() == Items.diamond && this.getThrower() != null) {
               EntityPlayer playerEntityByName = this.entityWorld.getPlayerEntityByName(this.getThrower());
               if (playerEntityByName != null && playerEntityByName != player) {
                  playerEntityByName.triggerAchievement(AchievementList.diamondsToYou);
               }
            }

            if (!this.isSilent()) {
               this.entityWorld.playSoundAtEntity(player, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            }

            player.onItemPickup(this, stackSize);
            if (entityItem.stackSize <= 0) {
               this.setDead();
            }
         }
      }
   }

   private void searchForOtherItemsNearby() {
      for (EntityItem entityItem : (Iterable<EntityItem>)(this.entityWorld.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().expand(0.5, 0.0, 0.5)))) {
         this.combineItems(entityItem);
      }
   }

   @Override
   public void travelToDimension(int n) {
      super.travelToDimension(n);
      if (!this.entityWorld.isRemote) {
         this.searchForOtherItemsNearby();
      }
   }

   public void setNoPickupDelay() {
      this.delayBeforeCanPickup = 0;
   }

   public void setNoDespawn() {
      this.age = -6000;
   }

   public void setEntityItemStack(ItemStack stack) {
      this.getDataWatcher().updateObject(10, stack);
      this.getDataWatcher().setObjectWatched(10);
   }

   public String getOwner() {
      return this.owner;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setShort("Health", (short)((byte)this.health));
      tag.setShort("Age", (short)this.age);
      tag.setShort("PickupDelay", (short)this.delayBeforeCanPickup);
      if (this.getThrower() != null) {
         tag.setString("Thrower", this.thrower);
      }

      if (this.getOwner() != null) {
         tag.setString("Owner", this.owner);
      }

      if (this.getEntityItem() != null) {
         tag.setTag("Item", this.getEntityItem().writeToNBT(new NBTTagCompound()));
      }
   }

   @Override
   public boolean canAttackWithItem() {
      return false;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      this.health = tag.getShort("Health") & 255;
      this.age = tag.getShort("Age");
      if (tag.hasKey("PickupDelay")) {
         this.delayBeforeCanPickup = tag.getShort("PickupDelay");
      }

      if (tag.hasKey("Owner")) {
         this.owner = tag.getString("Owner");
      }

      if (tag.hasKey("Thrower")) {
         this.thrower = tag.getString("Thrower");
      }

      this.setEntityItemStack(ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Item")));
      if (this.getEntityItem() == null) {
         this.setDead();
      }
   }

   @Override
   public boolean handleWaterMovement() {
      if (this.entityWorld.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.water, this)) {
         if (!this.inWater && !this.firstUpdate) {
            this.resetHeight();
         }

         this.inWater = true;
      } else {
         this.inWater = false;
      }

      return this.inWater;
   }

   public void setOwner(String str) {
      this.owner = str;
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else if (this.getEntityItem() != null && this.getEntityItem().getItem() == Items.nether_star && damageSource.isExplosion()) {
         return false;
      } else {
         this.setBeenAttacked();
         this.health = (int)((float)this.health - f);
         if (this.health <= 0) {
            this.setDead();
         }

         return false;
      }
   }

   @Override
   public void dealFireDamage(int n) {
      this.attackEntityFrom(DamageSource.inFire, (float)n);
   }

   public void makeFakeItem() {
      this.setInfinitePickupDelay();
      this.age = 5999;
   }

   private boolean combineItems(EntityItem entityItem) {
      if (entityItem == this) {
         return false;
      } else if (entityItem.isEntityAlive() && this.isEntityAlive()) {
         ItemStack entityItem2 = this.getEntityItem();
         ItemStack entityItem3 = entityItem.getEntityItem();
         if (this.delayBeforeCanPickup == 32767 || entityItem.delayBeforeCanPickup == 32767) {
            return false;
         } else if (this.age != -32768 && entityItem.age != -32768) {
            if (entityItem3.getItem() != entityItem2.getItem()) {
               return false;
            } else if (entityItem3.hasTagCompound() ^ entityItem2.hasTagCompound()) {
               return false;
            } else if (entityItem3.hasTagCompound() && !entityItem3.getTagCompound().equals(entityItem2.getTagCompound())) {
               return false;
            } else if (entityItem3.getItem() == null) {
               return false;
            } else if (entityItem3.getItem().getHasSubtypes() && entityItem3.getMetadata() != entityItem2.getMetadata()) {
               return false;
            } else if (entityItem3.stackSize < entityItem2.stackSize) {
               return entityItem.combineItems(this);
            } else if (entityItem3.stackSize + entityItem2.stackSize > entityItem3.getMaxStackSize()) {
               return false;
            } else {
               entityItem3.stackSize = entityItem3.stackSize + entityItem2.stackSize;
               entityItem.delayBeforeCanPickup = Math.max(entityItem.delayBeforeCanPickup, this.delayBeforeCanPickup);
               entityItem.age = Math.min(entityItem.age, this.age);
               entityItem.setEntityItemStack(entityItem3);
               this.setDead();
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public void onUpdate() {
      if (this.getEntityItem() == null) {
         this.setDead();
      } else {
         super.onUpdate();
         if (this.delayBeforeCanPickup > 0 && this.delayBeforeCanPickup != 32767) {
            this.delayBeforeCanPickup--;
         }

         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.motionY -= 0.04F;
         this.noClip = this.pushOutOfBlocks(this.posX, (this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0, this.posZ);
         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         if (((int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ) || this.ticksExisted % 25 == 0) {
            if (this.entityWorld.getBlockState(new BlockPos(this)).getBlock().getMaterial() == Material.lava) {
               this.motionY = 0.2F;
               this.motionX = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
               this.motionZ = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
               this.playSound("random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
            }

            if (!this.entityWorld.isRemote) {
               this.searchForOtherItemsNearby();
            }
         }

         float f = 0.98F;
         if (this.onGround) {
            f = this.entityWorld
                  .getBlockState(
                     new BlockPos(
                        MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ)
                     )
                  )
                  .getBlock()
                  .slipperiness
               * 0.98F;
         }

         this.motionX *= (double)f;
         this.motionY *= 0.98F;
         this.motionZ *= (double)f;
         if (this.onGround) {
            this.motionY *= -0.5;
         }

         if (this.age != -32768) {
            this.age++;
         }

         this.handleWaterMovement();
         if (!this.entityWorld.isRemote && this.age >= 6000) {
            this.setDead();
         }
      }
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.getCustomNameTag() : StatCollector.translateToLocal("item." + this.getEntityItem().getUnlocalizedName());
   }

   public void setPickupDelay(int n) {
      this.delayBeforeCanPickup = n;
   }

   public void setAgeToCreativeDespawnTime() {
      this.age = 4800;
   }

   public String getThrower() {
      return this.thrower;
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }
}
