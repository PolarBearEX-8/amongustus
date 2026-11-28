package net.minecraft.item;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFishFood extends ItemFood {
   private final boolean cooked;

   public ItemFishFood(boolean flag) {
      super(0, 0.0F, false);
      this.cooked = flag;
   }

   @Override
   public float getSaturationModifier(ItemStack stack) {
      ItemFishFood.FishType fishType = ItemFishFood.FishType.byItemStack(stack);
      return this.cooked && fishType.canCook() ? fishType.getCookedSaturationModifier() : fishType.getUncookedSaturationModifier();
   }

   @Override
   public String getUnlocalizedName(ItemStack stack) {
      ItemFishFood.FishType fishType = ItemFishFood.FishType.byItemStack(stack);
      return this.getUnlocalizedName() + "." + fishType.getUnlocalizedName() + "." + (this.cooked && fishType.canCook() ? "cooked" : "raw");
   }

   @Override
   public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
      for (ItemFishFood.FishType fishType : ItemFishFood.FishType.values()) {
         if (!this.cooked || fishType.canCook()) {
            list.add(new ItemStack(this, 1, fishType.getMetadata()));
         }
      }
   }

   @Override
   public int getHealAmount(ItemStack stack) {
      ItemFishFood.FishType fishType = ItemFishFood.FishType.byItemStack(stack);
      return this.cooked && fishType.canCook() ? fishType.getCookedHealAmount() : fishType.getUncookedHealAmount();
   }

   @Override
   public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
      if (ItemFishFood.FishType.byItemStack(stack) == ItemFishFood.FishType.PUFFERFISH) {
         player.addPotionEffect(new PotionEffect(Potion.poison.id, 1200, 3));
         player.addPotionEffect(new PotionEffect(Potion.hunger.id, 300, 2));
         player.addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 1));
      }

      super.onFoodEaten(stack, world, player);
   }

   @Override
   public String getPotionEffect(ItemStack stack) {
      return ItemFishFood.FishType.byItemStack(stack) == ItemFishFood.FishType.PUFFERFISH ? "+0-1+2+3+13&4-4" : null;
   }


   public enum FishType {
      COD(0, "cod", 2, 0.1F, 5, 0.6F),
      SALMON(1, "salmon", 2, 0.1F, 6, 0.8F),
      CLOWNFISH(2, "clownfish", 1, 0.1F),
      PUFFERFISH(3, "pufferfish", 1, 0.1F);

   private static final Map META_LOOKUP = Maps.newHashMap();
      private final int meta;
      private final String unlocalizedName;
      private final int uncookedHealAmount;
      private final float uncookedSaturationModifier;
      private final int cookedHealAmount;
      private final float cookedSaturationModifier;
      private boolean cookable = false;
   public int getUncookedHealAmount() {
         return this.uncookedHealAmount;
      }

      private FishType(int n, String str, int n2, float f) {
         this.meta = n;
         this.unlocalizedName = str;
         this.uncookedHealAmount = n2;
         this.uncookedSaturationModifier = f;
         this.cookedHealAmount = 0;
         this.cookedSaturationModifier = 0.0F;
         this.cookable = false;
      }

      private FishType(int n, String str, int n2, float f, int n3, float f2) {
         this.meta = n;
         this.unlocalizedName = str;
         this.uncookedHealAmount = n2;
         this.uncookedSaturationModifier = f;
         this.cookedHealAmount = n3;
         this.cookedSaturationModifier = f2;
         this.cookable = true;
      }

      public float getUncookedSaturationModifier() {
         return this.uncookedSaturationModifier;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      public static FishType byMetadata(int n) {
         FishType fishType = (FishType)META_LOOKUP.get(n);
         return fishType == null ? COD : fishType;
      }

      public float getCookedSaturationModifier() {
         return this.cookedSaturationModifier;
      }

      public static FishType byItemStack(ItemStack stack) {
         return stack.getItem() instanceof ItemFishFood ? byMetadata(stack.getMetadata()) : COD;
      }

      public int getCookedHealAmount() {
         return this.cookedHealAmount;
      }

      public boolean canCook() {
         return this.cookable;
      }

      public int getMetadata() {
         return this.meta;
      }

      static {
         for (FishType fishType : values()) {
            META_LOOKUP.put(fishType.getMetadata(), fishType);
         }
      }
   }
}
