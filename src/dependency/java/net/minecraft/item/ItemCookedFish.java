package net.minecraft.item;

import client.setting.CombatWeightProperty;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCookedFish extends ItemFood {
   public final boolean enabled3;

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (CombatWeightProperty combatWeightProperty : CombatWeightProperty.values()) {
         if (!this.enabled3 || combatWeightProperty.isEnabled()) {
            list.add(new ItemStack(this, 1, combatWeightProperty.getCount2()));
         }
      }
   }

   @Override
   public void applyItemStack2(ItemStack stack, World world, EntityPlayer player) {
      if (CombatWeightProperty.resolveCombatWeightProperty(stack) == CombatWeightProperty.PUFFERFISH) {
         player.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion24.indexOffset3, 1200, 3));
         player.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion29.indexOffset3, 300, 2));
         player.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion15.indexOffset3, 300, 1));
      }

      super.applyItemStack2(stack, world, player);
   }

   @Override
   public String getText(ItemStack stack) {
      CombatWeightProperty combatWeightProperty = CombatWeightProperty.resolveCombatWeightProperty(stack);
      return this.getString() + "." + combatWeightProperty.getLabelText() + "." + (this.enabled3 && combatWeightProperty.isEnabled() ? "cooked" : "raw");
   }

   @Override
   public String processAction3(ItemStack stack) {
      return CombatWeightProperty.resolveCombatWeightProperty(stack) == CombatWeightProperty.PUFFERFISH ? "+0-1+2+3+13&4-4" : null;
   }

   @Override
   public float computeFactor(ItemStack stack) {
      CombatWeightProperty combatWeightProperty = CombatWeightProperty.resolveCombatWeightProperty(stack);
      return this.enabled3 && combatWeightProperty.isEnabled() ? combatWeightProperty.getFactor2() : combatWeightProperty.getFactor();
   }

   @Override
   public int computeCount(ItemStack stack) {
      CombatWeightProperty combatWeightProperty = CombatWeightProperty.resolveCombatWeightProperty(stack);
      return this.enabled3 && combatWeightProperty.isEnabled() ? combatWeightProperty.getCount3() : combatWeightProperty.getCount();
   }

   public ItemCookedFish(boolean flag) {
      super(0, 0.0F, false);
      this.enabled3 = flag;
   }
}
