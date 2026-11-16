package net.minecraft.entity;

import client.event.PostRenderConsumer;
import client.setting.LocalOptionType;
import client.util.AdvancedClientManagerAdapter;
import client.util.AuxiliaryContainerAdapter;
import client.util.BaseClientManagerAdapter;
import client.util.CommonClientManagerAdapter;
import client.util.ContainerCraftingIInventory;
import client.util.DoMobLootClientManager;
import client.util.ExtendedManager;
import client.util.HitBoxBuffer;
import client.util.InternalPredicateEntry;
import client.util.PathfindManagerAdapter;
import client.util.StandardClientManagerAdapter;
import client.util.SupplementalClientManagerAdapter;
import client.util.UUIDEntryAdapter;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.GlobalItemEntry;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySheep extends HitBoxBuffer {
   public final ContainerCraftingIInventory containerCraftingIInventory = new ContainerCraftingIInventory(new AuxiliaryContainerAdapter(this), 2, 1);
   public static final Map map2 = Maps.newEnumMap(LocalOptionType.class);
   public int count32;
   public InternalPredicateEntry internalPredicateEntry = new InternalPredicateEntry(this);

   public static LocalOptionType resolveLocalOptionType(Random random) {
      int count = random.nextInt(100);
      return count < 5
         ? LocalOptionType.BLACK
         : (
            count < 10
               ? LocalOptionType.GRAY
               : (
                  count < 15
                     ? LocalOptionType.SILVER
                     : (count < 18 ? LocalOptionType.BROWN : (random.nextInt(500) == 0 ? LocalOptionType.PINK : LocalOptionType.WHITE))
               )
         );
   }

   @Override
   public String getText9() {
      return "mob.sheep.say";
   }

   public float computeFactor3(float factor) {
      return this.count32 <= 0
         ? 0.0F
         : (
            this.count32 >= 4 && this.count32 <= 36
               ? 1.0F
               : (this.count32 < 4 ? ((float)this.count32 - factor) / 4.0F : -((float)(this.count32 - 40) - factor) / 4.0F)
         );
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.sheep.step", 0.15F, 1.0F);
   }

   public LocalOptionType getLocalOptionType() {
      return LocalOptionType.resolveLocalOptionType(this.dataWatcher.computeByteCode(16) & 15);
   }

   @Override
   public float getHeight2() {
      return 0.95F * this.height;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.setEnabled7(tagCompound.matchesText("Sheared"));
      this.applyLocalOptionType(LocalOptionType.resolveLocalOptionType(tagCompound.computeByteCode("Color")));
   }

   private LocalOptionType resolveLocalOptionType(HitBoxBuffer hitBoxBuffer, HitBoxBuffer hitBoxBuffer2) {
      int count = ((EntitySheep)hitBoxBuffer).getLocalOptionType().getCount2();
      int count2 = ((EntitySheep)hitBoxBuffer2).getLocalOptionType().getCount2();
      this.containerCraftingIInventory.processAction3(0).applyIndexByIndex(count);
      this.containerCraftingIInventory.processAction3(1).applyIndexByIndex(count2);
      ItemStack stack = ExtendedManager.getExtendedManager().resolveItemStack(this.containerCraftingIInventory, ((EntitySheep)hitBoxBuffer).worldObj);
      int count3;
      if (stack != null && stack.getItem() == Items.dye) {
         count3 = stack.getIndexOffset();
      } else {
         count3 = this.worldObj.random.nextBoolean() ? count : count2;
      }

      return LocalOptionType.resolveLocalOptionTypeByIndex(count3);
   }

   public EntitySheep resolveEntitySheep(UUIDEntryAdapter uUIDEntryAdapter) {
      EntitySheep entitySheep = (EntitySheep)uUIDEntryAdapter;
      EntitySheep entitySheep2 = new EntitySheep(this.worldObj);
      entitySheep2.applyLocalOptionType(this.resolveLocalOptionType(this, entitySheep));
      return entitySheep2;
   }

   public void applyLocalOptionType(LocalOptionType localOptionType) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue & 240 | localOptionType.getCount() & 15));
   }

   public static float[] get(LocalOptionType localOptionType) {
      return (float[])map2.get(localOptionType);
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(8.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.23F);
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      if (!this.isActive32()) {
         this.resolveEntityItem(new ItemStack(Item.processAction2(Blocks.wool), 1, this.getLocalOptionType().getCount()), 0.0F);
      }

      int count2 = this.random.nextInt(2) + 1 + this.random.nextInt(1 + count);

      for (int index = 0; index < count2; index++) {
         if (this.canExecute2()) {
            this.resolveEntityItem(Items.cookedMutton, 1);
         } else {
            this.resolveEntityItem(Items.mutton, 1);
         }
      }
   }

   public float computeFactor4(float factor) {
      if (this.count32 > 4 && this.count32 <= 36) {
         float factor2 = ((float)(this.count32 - 4) - factor) / 32.0F;
         return (float) (Math.PI / 5) + 0.2199115F * MathHelper.abs(factor2 * 28.7F);
      } else {
         return this.count32 > 0 ? (float) (Math.PI / 5) : this.rotationPitch / (180.0F / (float)Math.PI);
      }
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntitySheep(uUIDEntryAdapter);
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 10) {
         this.count32 = 40;
      } else {
         super.processByte(byteValue);
      }
   }

   @Override
   public Item getGunpowder() {
      return Item.processAction2(Blocks.wool);
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() == Items.shears && !this.isActive32() && !this.isActive16()) {
         if (!this.worldObj.state) {
            this.setEnabled7(true);
            int count = 1 + this.random.nextInt(3);

            for (int index = 0; index < count; index++) {
               EntityItem entityItem = this.resolveEntityItem(new ItemStack(Item.processAction2(Blocks.wool), 1, this.getLocalOptionType().getCount()), 1.0F);
               entityItem.motionY = entityItem.motionY + (double)(this.random.nextFloat() * 0.05F);
               entityItem.motionX = entityItem.motionX + (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
               entityItem.motionZ = entityItem.motionZ + (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
            }
         }

         slot.applyEntityLivingBaseByEntityLivingBase(1, player);
         this.applyText("mob.sheep.shear", 1.0F, 1.0F);
      }

      return super.matchesEntityPlayer6(player);
   }

   @Override
   public String getText3() {
      return "mob.sheep.say";
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      PostRenderConsumer postRenderConsumer2 = super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
      this.applyLocalOptionType(resolveLocalOptionType(this.worldObj.random));
      return postRenderConsumer2;
   }

   @Override
   public String getText10() {
      return "mob.sheep.say";
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Sheared", this.isActive32());
      tagCompound.put("Color", (byte)this.getLocalOptionType().getCount());
   }

   @Override
   public void updateState6() {
      this.setEnabled7(false);
      if (this.isActive16()) {
         this.applyIndex9(60);
      }
   }

   public boolean isActive32() {
      return (this.dataWatcher.computeByteCode(16) & 16) != 0;
   }

   @Override
   public void updateState3() {
      this.count32 = this.internalPredicateEntry.getCount2();
      super.updateState3();
   }

   public void setEnabled7(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue | 16));
      } else {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue & -17));
      }
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, new Byte((byte)0));
   }

   @Override
   public void updateState33() {
      if (this.worldObj.state) {
         this.count32 = Math.max(0, this.count32 - 1);
      }

      super.updateState33();
   }

   public EntitySheep(World world) {
      super(world);
      this.applyFloat(0.9F, 1.3F);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.goalSetupManager.add(0, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(1, new CommonClientManagerAdapter(this, 1.25));
      this.goalSetupManager.add(2, new DoMobLootClientManager(this, 1.0));
      this.goalSetupManager.add(3, new StandardClientManagerAdapter(this, 1.1, Items.wheat, false));
      this.goalSetupManager.add(4, new GlobalItemEntry(this, 1.1));
      this.goalSetupManager.add(5, this.internalPredicateEntry);
      this.goalSetupManager.add(6, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(7, new BaseClientManagerAdapter(this, EntityPlayer.class, 6.0F));
      this.goalSetupManager.add(8, new ComplexItemEntry(this));
      this.containerCraftingIInventory.processAction(0, new ItemStack(Items.dye, 1, 0));
      this.containerCraftingIInventory.processAction(1, new ItemStack(Items.dye, 1, 0));
   }

   static {
      map2.put(LocalOptionType.WHITE, new float[]{1.0F, 1.0F, 1.0F});
      map2.put(LocalOptionType.ORANGE, new float[]{0.85F, 0.5F, 0.2F});
      map2.put(LocalOptionType.MAGENTA, new float[]{0.7F, 0.3F, 0.85F});
      map2.put(LocalOptionType.LIGHT_BLUE, new float[]{0.4F, 0.6F, 0.85F});
      map2.put(LocalOptionType.YELLOW, new float[]{0.9F, 0.9F, 0.2F});
      map2.put(LocalOptionType.LIME, new float[]{0.5F, 0.8F, 0.1F});
      map2.put(LocalOptionType.PINK, new float[]{0.95F, 0.5F, 0.65F});
      map2.put(LocalOptionType.GRAY, new float[]{0.3F, 0.3F, 0.3F});
      map2.put(LocalOptionType.SILVER, new float[]{0.6F, 0.6F, 0.6F});
      map2.put(LocalOptionType.CYAN, new float[]{0.3F, 0.5F, 0.6F});
      map2.put(LocalOptionType.PURPLE, new float[]{0.5F, 0.25F, 0.7F});
      map2.put(LocalOptionType.BLUE, new float[]{0.2F, 0.3F, 0.7F});
      map2.put(LocalOptionType.BROWN, new float[]{0.4F, 0.3F, 0.2F});
      map2.put(LocalOptionType.GREEN, new float[]{0.4F, 0.5F, 0.2F});
      map2.put(LocalOptionType.RED, new float[]{0.6F, 0.2F, 0.2F});
      map2.put(LocalOptionType.BLACK, new float[]{0.1F, 0.1F, 0.1F});
   }
}
