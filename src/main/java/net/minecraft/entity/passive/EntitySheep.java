package net.minecraft.entity.passive;

import net.minecraft.inventory.Container;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySheep extends EntityAnimal {
   private final InventoryCrafting inventoryCrafting = new InventoryCrafting(new EntitySheep.DefaultContainer(this), 2, 1);
   private static final Map DYE_TO_RGB = Maps.newEnumMap(EnumDyeColor.class);
   private int sheepTimer;
   private EntityAIEatGrass entityAIEatGrass = new EntityAIEatGrass(this);

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, new Byte((byte)0));
   }

   @Override
   public void eatGrassBonus() {
      this.setSheared(false);
      if (this.isChild()) {
         this.addGrowth(60);
      }
   }

   @Override
   public void playStepSound(BlockPos pos, Block block) {
      this.playSound("mob.sheep.step", 0.15F, 1.0F);
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 10) {
         this.sheepTimer = 40;
      } else {
         super.handleStatusUpdate(b);
      }
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      entityLivingData = super.onInitialSpawn(difficultyInstance, entityLivingData);
      this.setFleeceColor(getRandomSheepColor(this.entityWorld.rand));
      return entityLivingData;
   }

   @Override
   public EntityAgeable createAgeableChild(EntityAgeable entityAgeable) {
      return this.createChild(entityAgeable);
   }

   public float getHeadRotationPointY(float f) {
      return this.sheepTimer <= 0
         ? 0.0F
         : (
            this.sheepTimer >= 4 && this.sheepTimer <= 36
               ? 1.0F
               : (this.sheepTimer < 4 ? ((float)this.sheepTimer - f) / 4.0F : -((float)(this.sheepTimer - 40) - f) / 4.0F)
         );
   }

   public EnumDyeColor getFleeceColor() {
      return EnumDyeColor.byMetadata(this.dataWatcher.getWatchableObjectByte(16) & 15);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setBoolean("Sheared", this.getSheared());
      tag.setByte("Color", (byte)this.getFleeceColor().getMetadata());
   }

   private EnumDyeColor getDyeColorMixFromParents(EntityAnimal entityAnimal, EntityAnimal entityAnimal2) {
      int dyeDamage = ((EntitySheep)entityAnimal).getFleeceColor().getDyeDamage();
      int dyeDamage2 = ((EntitySheep)entityAnimal2).getFleeceColor().getDyeDamage();
      this.inventoryCrafting.getStackInSlot(0).setItemDamage(dyeDamage);
      this.inventoryCrafting.getStackInSlot(1).setItemDamage(dyeDamage2);
      ItemStack stack = CraftingManager.getInstance().findMatchingRecipe(this.inventoryCrafting, ((EntitySheep)entityAnimal).entityWorld);
      int n;
      if (stack != null && stack.getItem() == Items.dye) {
         n = stack.getMetadata();
      } else {
         n = this.entityWorld.rand.nextBoolean() ? dyeDamage : dyeDamage2;
      }

      return EnumDyeColor.byDyeDamage(n);
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23F);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setSheared(tag.getBoolean("Sheared"));
      this.setFleeceColor(EnumDyeColor.byMetadata(tag.getByte("Color")));
   }

   public EntitySheep createChild(EntityAgeable entityAgeable) {
      EntitySheep entitySheep = (EntitySheep)entityAgeable;
      EntitySheep entitySheep2 = new EntitySheep(this.entityWorld);
      entitySheep2.setFleeceColor(this.getDyeColorMixFromParents(this, entitySheep));
      return entitySheep2;
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      if (!this.getSheared()) {
         this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, this.getFleeceColor().getMetadata()), 0.0F);
      }

      int n2 = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + n);

      for (int i = 0; i < n2; i++) {
         if (this.isBurning()) {
            this.dropItem(Items.cooked_mutton, 1);
         } else {
            this.dropItem(Items.mutton, 1);
         }
      }
   }

   public boolean getSheared() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 16) != 0;
   }

   public void setFleeceColor(EnumDyeColor enumDyeColor) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      this.dataWatcher.updateObject(16, (byte)(watchableObjectByte & 240 | enumDyeColor.getMetadata() & 15));
   }

   @Override
   public boolean interact(EntityPlayer player) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (currentItem != null && currentItem.getItem() == Items.shears && !this.getSheared() && !this.isChild()) {
         if (!this.entityWorld.isRemote) {
            this.setSheared(true);
            int n = 1 + this.rand.nextInt(3);

            for (int i = 0; i < n; i++) {
               EntityItem entityItem = this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, this.getFleeceColor().getMetadata()), 1.0F);
               entityItem.motionY = entityItem.motionY + (double)(this.rand.nextFloat() * 0.05F);
               entityItem.motionX = entityItem.motionX + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
               entityItem.motionZ = entityItem.motionZ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
            }
         }

         currentItem.damageItem(1, player);
         this.playSound("mob.sheep.shear", 1.0F, 1.0F);
      }

      return super.interact(player);
   }

   public float getHeadRotationAngleX(float f) {
      if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
         float f2 = ((float)(this.sheepTimer - 4) - f) / 32.0F;
         return (float) (Math.PI / 5) + 0.2199115F * MathHelper.sin(f2 * 28.7F);
      } else {
         return this.sheepTimer > 0 ? (float) (Math.PI / 5) : this.rotationPitch / (180.0F / (float)Math.PI);
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.sheep.say";
   }

   @Override
   public String getLivingSound() {
      return "mob.sheep.say";
   }

   @Override
   public String getDeathSound() {
      return "mob.sheep.say";
   }

   static {
      DYE_TO_RGB.put(EnumDyeColor.WHITE, new float[]{1.0F, 1.0F, 1.0F});
      DYE_TO_RGB.put(EnumDyeColor.ORANGE, new float[]{0.85F, 0.5F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.MAGENTA, new float[]{0.7F, 0.3F, 0.85F});
      DYE_TO_RGB.put(EnumDyeColor.LIGHT_BLUE, new float[]{0.4F, 0.6F, 0.85F});
      DYE_TO_RGB.put(EnumDyeColor.YELLOW, new float[]{0.9F, 0.9F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.LIME, new float[]{0.5F, 0.8F, 0.1F});
      DYE_TO_RGB.put(EnumDyeColor.PINK, new float[]{0.95F, 0.5F, 0.65F});
      DYE_TO_RGB.put(EnumDyeColor.GRAY, new float[]{0.3F, 0.3F, 0.3F});
      DYE_TO_RGB.put(EnumDyeColor.SILVER, new float[]{0.6F, 0.6F, 0.6F});
      DYE_TO_RGB.put(EnumDyeColor.CYAN, new float[]{0.3F, 0.5F, 0.6F});
      DYE_TO_RGB.put(EnumDyeColor.PURPLE, new float[]{0.5F, 0.25F, 0.7F});
      DYE_TO_RGB.put(EnumDyeColor.BLUE, new float[]{0.2F, 0.3F, 0.7F});
      DYE_TO_RGB.put(EnumDyeColor.BROWN, new float[]{0.4F, 0.3F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.GREEN, new float[]{0.4F, 0.5F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.RED, new float[]{0.6F, 0.2F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.BLACK, new float[]{0.1F, 0.1F, 0.1F});
   }

   public EntitySheep(World world) {
      super(world);
      this.setSize(0.9F, 1.3F);
      ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIPanic(this, 1.25));
      this.tasks.addTask(2, new EntityAIMate(this, 1.0));
      this.tasks.addTask(3, new EntityAITempt(this, 1.1, Items.wheat, false));
      this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1));
      this.tasks.addTask(5, this.entityAIEatGrass);
      this.tasks.addTask(6, new EntityAIWander(this, 1.0));
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.inventoryCrafting.setInventorySlotContents(0, new ItemStack(Items.dye, 1, 0));
      this.inventoryCrafting.setInventorySlotContents(1, new ItemStack(Items.dye, 1, 0));
   }

   @Override
   public float getEyeHeight() {
      return 0.95F * this.height;
   }

   public void setSheared(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      if (flag) {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte | 16));
      } else {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte & -17));
      }
   }

   @Override
   public void onLivingUpdate() {
      if (this.entityWorld.isRemote) {
         this.sheepTimer = Math.max(0, this.sheepTimer - 1);
      }

      super.onLivingUpdate();
   }

   @Override
   public Item getDropItem() {
      return Item.getItemFromBlock(Blocks.wool);
   }

   @Override
   public void updateAITasks() {
      this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
      super.updateAITasks();
   }

   public static EnumDyeColor getRandomSheepColor(Random rand2) {
      int n = rand2.nextInt(100);
      return n < 5
         ? EnumDyeColor.BLACK
         : (
            n < 10
               ? EnumDyeColor.GRAY
               : (n < 15 ? EnumDyeColor.SILVER : (n < 18 ? EnumDyeColor.BROWN : (rand2.nextInt(500) == 0 ? EnumDyeColor.PINK : EnumDyeColor.WHITE)))
         );
   }

   public static float[] getDyeRgb(EnumDyeColor enumDyeColor) {
      return (float[])DYE_TO_RGB.get(enumDyeColor);
   }


   public static class DefaultContainer extends Container {
      public final EntitySheep sheep;

      @Override
      public boolean canInteractWith(EntityPlayer player) {
         return false;
      }

      public DefaultContainer(EntitySheep entitySheep) {
         this.sheep = entitySheep;
      }
   }
}
