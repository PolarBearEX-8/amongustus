package net.minecraft.item;

import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemArmor extends Item {
   private static final int[] maxDamageArray = new int[]{11, 16, 15, 13};
   public static final String[] EMPTY_SLOT_NAMES = new String[]{
      "minecraft:items/empty_armor_slot_helmet",
      "minecraft:items/empty_armor_slot_chestplate",
      "minecraft:items/empty_armor_slot_leggings",
      "minecraft:items/empty_armor_slot_boots"
   };
   private static final IBehaviorDispenseItem dispenserBehavior = new ItemArmor.DefaultBehaviorDefaultDispenseItem();
   public final int armorType;
   public final int damageReduceAmount;
   public final int renderIndex;
   private final ItemArmor.ArmorMaterial material;

   @Override
   public int getItemEnchantability() {
      return this.material.getEnchantability();
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      int n = EntityLiving.getArmorPosition(stack) - 1;
      if (player.getCurrentArmor(n) == null) {
         player.setCurrentItemOrArmor(n, stack.copy());
         stack.stackSize = 0;
      }

      return stack;
   }

   @Override
   public boolean getIsRepairable(ItemStack stack, ItemStack stack2) {
      return this.material.getRepairItem() == stack2.getItem() ? true : super.getIsRepairable(stack, stack2);
   }

   @Override
   public int getColorFromItemStack(ItemStack stack, int n) {
      if (n > 0) {
         return 16777215;
      } else {
         int color = this.getColor(stack);
         if (color < 0) {
            color = 16777215;
         }

         return color;
      }
   }

   public ItemArmor(ItemArmor.ArmorMaterial armorMaterial, int n, int n2) {
      this.material = armorMaterial;
      this.armorType = n2;
      this.renderIndex = n;
      this.damageReduceAmount = armorMaterial.getDamageReductionAmount(n2);
      this.setMaxDamage(armorMaterial.getDurability(n2));
      this.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabCombat);
      BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
   }

   public boolean hasColor(ItemStack stack) {
      return this.material != ItemArmor.ArmorMaterial.LEATHER
         ? false
         : (
            !stack.hasTagCompound()
               ? false
               : (!stack.getTagCompound().hasKey("display", 10) ? false : stack.getTagCompound().getCompoundTag("display").hasKey("color", 3))
         );
   }

   public void removeColor(ItemStack stack) {
      if (this.material == ItemArmor.ArmorMaterial.LEATHER) {
         NBTTagCompound tagCompound = stack.getTagCompound();
         if (tagCompound != null) {
            NBTTagCompound compoundTag = tagCompound.getCompoundTag("display");
            if (compoundTag.hasKey("color")) {
               compoundTag.removeTag("color");
            }
         }
      }
   }

   public static int[] accessMaxDamageArray() {
      return maxDamageArray;
   }

   public void setColor(ItemStack stack, int n) {
      if (this.material != ItemArmor.ArmorMaterial.LEATHER) {
         throw new UnsupportedOperationException("Can't dye non-leather!");
      } else {
         NBTTagCompound tagCompound = stack.getTagCompound();
         if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            stack.setTagCompound(tagCompound);
         }

         NBTTagCompound compoundTag = tagCompound.getCompoundTag("display");
         if (!tagCompound.hasKey("display", 10)) {
            tagCompound.setTag("display", compoundTag);
         }

         compoundTag.setInteger("color", n);
      }
   }

   public int getColor(ItemStack stack) {
      if (this.material != ItemArmor.ArmorMaterial.LEATHER) {
         return -1;
      } else {
         NBTTagCompound tagCompound = stack.getTagCompound();
         if (tagCompound != null) {
            NBTTagCompound compoundTag = tagCompound.getCompoundTag("display");
            if (compoundTag != null && compoundTag.hasKey("color", 3)) {
               return compoundTag.getInteger("color");
            }
         }

         return 10511680;
      }
   }

   public ItemArmor.ArmorMaterial getArmorMaterial() {
      return this.material;
   }


   public static final class DefaultBehaviorDefaultDispenseItem extends BehaviorDefaultDispenseItem {
      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         BlockPos pos = blockSource.getBlockPos().offset(BlockDispenser.getFacing(blockSource.getBlockMetadata()));
         int x = pos.getX();
         int y = pos.getY();
         int z = pos.getZ();
         AxisAlignedBB box = new AxisAlignedBB((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1));
         List entitiesWithinAABB = blockSource.getWorld()
            .getEntitiesWithinAABB(EntityLivingBase.class, box, Predicates.and(EntitySelectors.NOT_SPECTATING, new EntitySelectors.ArmoredMob(stack)));
         if (entitiesWithinAABB.size() > 0) {
            EntityLivingBase entity = (EntityLivingBase)entitiesWithinAABB.get(0);
            int n = entity instanceof EntityPlayer ? 1 : 0;
            int armorPosition = EntityLiving.getArmorPosition(stack);
            ItemStack stack2 = stack.copy();
            stack2.stackSize = 1;
            entity.setCurrentItemOrArmor(armorPosition - n, stack2);
            if (entity instanceof EntityLiving) {
               ((EntityLiving)entity).setEquipmentDropChance(armorPosition, 2.0F);
            }

            stack.stackSize--;
            return stack;
         } else {
            return super.dispenseStack(blockSource, stack);
         }
      }
   }

   public enum ArmorMaterial {
      LEATHER("leather", 5, new int[]{1, 3, 2, 1}, 15),
      CHAIN("chainmail", 15, new int[]{2, 5, 4, 1}, 12),
      IRON("iron", 15, new int[]{2, 6, 5, 2}, 9),
      GOLD("gold", 7, new int[]{2, 5, 3, 1}, 25),
      DIAMOND("diamond", 33, new int[]{3, 8, 6, 3}, 10);

   private final String name;
      private final int maxDamageFactor;
      private final int[] damageReductionAmountArray;
      private final int enchantability;
   public int getEnchantability() {
         return this.enchantability;
      }

      public String getName() {
         return this.name;
      }

      public Item getRepairItem() {
         return this == LEATHER
            ? Items.leather
            : (this == CHAIN ? Items.iron_ingot : (this == GOLD ? Items.gold_ingot : (this == IRON ? Items.iron_ingot : (this == DIAMOND ? Items.diamond : null))));
      }

      private ArmorMaterial(String str, int n, int[] ints, int n2) {
         this.name = str;
         this.maxDamageFactor = n;
         this.damageReductionAmountArray = ints;
         this.enchantability = n2;
      }

      public int getDamageReductionAmount(int n) {
         return this.damageReductionAmountArray[n];
      }

      public int getDurability(int n) {
         return ItemArmor.accessMaxDamageArray()[n] * this.maxDamageFactor;
      }
   }
}
