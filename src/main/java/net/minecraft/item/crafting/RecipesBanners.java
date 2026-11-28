package net.minecraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.world.World;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

public class RecipesBanners {

   public void addRecipes(CraftingManager craftingManager) {
      for (EnumDyeColor enumDyeColor : EnumDyeColor.values()) {
         craftingManager.addRecipe(
            new ItemStack(Items.banner, 1, enumDyeColor.getDyeDamage()),
            new Object[]{"###", "###", " | ", '#', new ItemStack(Blocks.wool, 1, enumDyeColor.getMetadata()), '|', Items.stick}
         );
      }

      craftingManager.addRecipe(new RecipesBanners.RecipeDuplicatePattern(null));
      craftingManager.addRecipe(new RecipesBanners.RecipeAddPattern(null));
   }


   public static class InnerEntry {
   }

   public static class RecipeAddPattern implements IRecipe {

      private TileEntityBanner.EnumBannerPattern matchPattern(InventoryCrafting inventoryCrafting) {
         for (TileEntityBanner.EnumBannerPattern enumBannerPattern : TileEntityBanner.EnumBannerPattern.values()) {
            if (enumBannerPattern.hasValidCrafting()) {
               boolean flag = true;
               if (enumBannerPattern.hasCraftingStack()) {
                  boolean flag2 = false;
                  boolean flag3 = false;

                  for (int i = 0; i < inventoryCrafting.getSizeInventory() && flag; i++) {
                     ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
                     if (stackInSlot != null && stackInSlot.getItem() != Items.banner) {
                        if (stackInSlot.getItem() == Items.dye) {
                           if (flag3) {
                              flag = false;
                              break;
                           }

                           flag3 = true;
                        } else {
                           if (flag2 || !stackInSlot.isItemEqual(enumBannerPattern.getCraftingStack())) {
                              flag = false;
                              break;
                           }

                           flag2 = true;
                        }
                     }
                  }

                  if (!flag2) {
                     flag = false;
                  }
               } else if (inventoryCrafting.getSizeInventory() == enumBannerPattern.getCraftingLayers().length * enumBannerPattern.getCraftingLayers()[0].length()) {
                  int n = -1;

                  for (int j = 0; j < inventoryCrafting.getSizeInventory() && flag; j++) {
                     int n2 = j / 3;
                     int n3 = j % 3;
                     ItemStack stackInSlot2 = inventoryCrafting.getStackInSlot(j);
                     if (stackInSlot2 != null && stackInSlot2.getItem() != Items.banner) {
                        if (stackInSlot2.getItem() != Items.dye) {
                           flag = false;
                           break;
                        }

                        if (n != -1 && n != stackInSlot2.getMetadata()) {
                           flag = false;
                           break;
                        }

                        if (enumBannerPattern.getCraftingLayers()[n2].charAt(n3) == ' ') {
                           flag = false;
                           break;
                        }

                        n = stackInSlot2.getMetadata();
                     } else if (enumBannerPattern.getCraftingLayers()[n2].charAt(n3) != ' ') {
                        flag = false;
                        break;
                     }
                  }
               } else {
                  flag = false;
               }

               if (flag) {
                  return enumBannerPattern;
               }
            }
         }

         return null;
      }

      @Override
      public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
         ItemStack stack = null;

         for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.getItem() == Items.banner) {
               stack = stackInSlot.copy();
               stack.stackSize = 1;
               break;
            }
         }

         TileEntityBanner.EnumBannerPattern enumBannerPattern = this.matchPattern(inventoryCrafting);
         if (enumBannerPattern != null) {
            int n = 0;

            for (int j = 0; j < inventoryCrafting.getSizeInventory(); j++) {
               ItemStack stackInSlot2 = inventoryCrafting.getStackInSlot(j);
               if (stackInSlot2 != null && stackInSlot2.getItem() == Items.dye) {
                  n = stackInSlot2.getMetadata();
                  break;
               }
            }

            NBTTagCompound subCompound = stack.getSubCompound("BlockEntityTag", true);
            NBTTagList tagList = null;
            if (subCompound.hasKey("Patterns", 9)) {
               tagList = subCompound.getTagList("Patterns", 10);
            } else {
               tagList = new NBTTagList();
               subCompound.setTag("Patterns", tagList);
            }

            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("Pattern", enumBannerPattern.getPatternID());
            tag.setInteger("Color", n);
            tagList.appendTag(tag);
         }

         return stack;
      }

      @Override
      public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {
         ItemStack[] stacks = new ItemStack[inventoryCrafting.getSizeInventory()];

         for (int i = 0; i < stacks.length; i++) {
            ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.getItem().hasContainerItem()) {
               stacks[i] = new ItemStack(stackInSlot.getItem().getContainerItem());
            }
         }

         return stacks;
      }

      private RecipeAddPattern() {
      }

      @Override
      public boolean matches(InventoryCrafting inventoryCrafting, World world) {
         boolean flag = false;

         for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.getItem() == Items.banner) {
               if (flag) {
                  return false;
               }

               if (TileEntityBanner.getPatterns(stackInSlot) >= 6) {
                  return false;
               }

               flag = true;
            }
         }

         return !flag ? false : this.matchPattern(inventoryCrafting) != null;
      }

      @Override
      public ItemStack getRecipeOutput() {
         return null;
      }

      public RecipeAddPattern(RecipesBanners.InnerEntry obj) {
         this();
      }

      @Override
      public int getRecipeSize() {
         return 10;
      }
   }

   public static class RecipeDuplicatePattern implements IRecipe {
      @Override
      public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
         for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
            if (stackInSlot != null && TileEntityBanner.getPatterns(stackInSlot) > 0) {
               ItemStack stack = stackInSlot.copy();
               stack.stackSize = 1;
               return stack;
            }
         }

         return null;
      }

      @Override
      public ItemStack getRecipeOutput() {
         return null;
      }

      public RecipeDuplicatePattern(RecipesBanners.InnerEntry obj) {
         this();
      }

      @Override
      public boolean matches(InventoryCrafting inventoryCrafting, World world) {
         ItemStack stack = null;
         ItemStack stack2 = null;

         for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
            if (stackInSlot != null) {
               if (stackInSlot.getItem() != Items.banner) {
                  return false;
               }

               if (stack != null && stack2 != null) {
                  return false;
               }

               int baseColor = TileEntityBanner.getBaseColor(stackInSlot);
               boolean flag = TileEntityBanner.getPatterns(stackInSlot) > 0;
               if (stack != null) {
                  if (flag) {
                     return false;
                  }

                  if (baseColor != TileEntityBanner.getBaseColor(stack)) {
                     return false;
                  }

                  stack2 = stackInSlot;
               } else if (stack2 != null) {
                  if (!flag) {
                     return false;
                  }

                  if (baseColor != TileEntityBanner.getBaseColor(stack2)) {
                     return false;
                  }

                  stack = stackInSlot;
               } else if (flag) {
                  stack = stackInSlot;
               } else {
                  stack2 = stackInSlot;
               }
            }
         }

         return stack != null && stack2 != null;
      }

      @Override
      public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {
         ItemStack[] stacks = new ItemStack[inventoryCrafting.getSizeInventory()];

         for (int i = 0; i < stacks.length; i++) {
            ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
            if (stackInSlot != null) {
               if (stackInSlot.getItem().hasContainerItem()) {
                  stacks[i] = new ItemStack(stackInSlot.getItem().getContainerItem());
               } else if (stackInSlot.hasTagCompound() && TileEntityBanner.getPatterns(stackInSlot) > 0) {
                  stacks[i] = stackInSlot.copy();
                  stacks[i].stackSize = 1;
               }
            }
         }

         return stacks;
      }

      @Override
      public int getRecipeSize() {
         return 2;
      }

      private RecipeDuplicatePattern() {
      }
   }
}
