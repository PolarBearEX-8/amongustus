package net.minecraft.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public interface IMerchant {
   IChatComponent getDisplayName();

   EntityPlayer getCustomer();

   void setRecipes(MerchantRecipeList merchantRecipeList);

   void setCustomer(EntityPlayer player);

   void verifySellingItem(ItemStack stack);

   void useRecipe(MerchantRecipe merchantRecipe);

   MerchantRecipeList getRecipes(EntityPlayer player);
}
