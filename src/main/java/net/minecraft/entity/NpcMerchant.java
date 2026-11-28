package net.minecraft.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class NpcMerchant implements IMerchant {
   private InventoryMerchant theMerchantInventory;
   private EntityPlayer customer;
   private MerchantRecipeList recipeList;
   private IChatComponent name;

   @Override
   public MerchantRecipeList getRecipes(EntityPlayer player) {
      return this.recipeList;
   }

   public NpcMerchant(EntityPlayer player, IChatComponent component) {
      this.customer = player;
      this.name = component;
      this.theMerchantInventory = new InventoryMerchant(player, this);
   }

   @Override
   public EntityPlayer getCustomer() {
      return this.customer;
   }

   @Override
   public void useRecipe(MerchantRecipe merchantRecipe) {
      merchantRecipe.incrementToolUses();
   }

   @Override
   public void verifySellingItem(ItemStack stack) {
   }

   @Override
   public void setRecipes(MerchantRecipeList merchantRecipeList) {
      this.recipeList = merchantRecipeList;
   }

   @Override
   public void setCustomer(EntityPlayer player) {
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.name != null ? this.name : new ChatComponentTranslation("entity.Villager.name", new Object[0]));
   }
}
