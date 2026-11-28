package net.augustus.utils.combat;


import net.augustus.settings.options.EntityTargetFilterOption;
import net.augustus.utils.target.TargetConditionOperator;
import net.augustus.utils.target.TargetFilterPreset;
public class TargetFilterPresets {

   public static EntityTargetFilter createRenderFilter() {
      EntityTargetFilterOption[] entityTargetFilterOptions = new EntityTargetFilterOption[11];
      entityTargetFilterOptions[0] = TargetFilterPreset.MYSELF.createOption(false);
      entityTargetFilterOptions[1] = TargetFilterPreset.PLAYER.createOption(true);
      entityTargetFilterOptions[2] = TargetFilterPreset.MOB.createOption(true);
      entityTargetFilterOptions[3] = TargetFilterPreset.ANIMAL.createOption(false);
      entityTargetFilterOptions[4] = TargetFilterPreset.VILLAGER.createOption(true);
      entityTargetFilterOptions[5] = TargetFilterPreset.ARMOR_STAND.createOption(false);
      entityTargetFilterOptions[6] = TargetFilterPreset.TEAMMATE.createOption(true);
      entityTargetFilterOptions[7] = TargetFilterPreset.FRIEND.createOption(true);
      entityTargetFilterOptions[8] = TargetFilterPreset.ANTI_BOT.createOption(false);
      entityTargetFilterOptions[9] = TargetFilterPreset.INVISIBLE.createOption(true);
      entityTargetFilterOptions[10] = TargetFilterPreset.SLEEPING.createOption(true);
      return createFilter(entityTargetFilterOptions);
   }

   public static EntityTargetFilter createRenderFilterWithSelf() {
      EntityTargetFilterOption[] entityTargetFilterOptions = new EntityTargetFilterOption[11];
      entityTargetFilterOptions[0] = TargetFilterPreset.MYSELF.createOption(false).withVisible(true);
      entityTargetFilterOptions[1] = TargetFilterPreset.PLAYER.createOption(true);
      entityTargetFilterOptions[2] = TargetFilterPreset.MOB.createOption(true);
      entityTargetFilterOptions[3] = TargetFilterPreset.ANIMAL.createOption(false);
      entityTargetFilterOptions[4] = TargetFilterPreset.VILLAGER.createOption(true);
      entityTargetFilterOptions[5] = TargetFilterPreset.ARMOR_STAND.createOption(false);
      entityTargetFilterOptions[6] = TargetFilterPreset.TEAMMATE.createOption(true);
      entityTargetFilterOptions[7] = TargetFilterPreset.FRIEND.createOption(true);
      entityTargetFilterOptions[8] = TargetFilterPreset.ANTI_BOT.createOption(false);
      entityTargetFilterOptions[9] = TargetFilterPreset.INVISIBLE.createOption(true);
      entityTargetFilterOptions[10] = TargetFilterPreset.SLEEPING.createOption(true);
      return createFilter(entityTargetFilterOptions);
   }

   public static EntityTargetFilter createFilter(EntityTargetFilterOption[] entityTargetFilterOptions) {
      EntityTargetFilterOption[] entityTargetFilterOptions2 = new EntityTargetFilterOption[entityTargetFilterOptions.length];
      TargetConditionOperator or = TargetConditionOperator.OR;

      for (int i = 0; i < entityTargetFilterOptions.length; i++) {
         EntityTargetFilterOption entityTargetFilterOption = entityTargetFilterOptions[i];
         if (entityTargetFilterOption.operator == TargetConditionOperator.OR && or == TargetConditionOperator.AND) {
            throw new RuntimeException("Wrong init order for the targets. Or conditions have to be first!");
         }

         entityTargetFilterOptions2[i] = entityTargetFilterOption;
         or = entityTargetFilterOption.operator;
      }

      return new EntityTargetFilter(entityTargetFilterOptions2);
   }

   public static EntityTargetFilter createCombatFilter() {
      EntityTargetFilterOption[] entityTargetFilterOptions = new EntityTargetFilterOption[13];
      entityTargetFilterOptions[0] = TargetFilterPreset.MYSELF.createOption(false);
      entityTargetFilterOptions[1] = TargetFilterPreset.PLAYER.createOption(true);
      entityTargetFilterOptions[2] = TargetFilterPreset.MOB.createOption(true);
      entityTargetFilterOptions[3] = TargetFilterPreset.ANIMAL.createOption(false);
      entityTargetFilterOptions[4] = TargetFilterPreset.VILLAGER.createOption(false);
      entityTargetFilterOptions[5] = TargetFilterPreset.ARMOR_STAND.createOption(false);
      entityTargetFilterOptions[6] = TargetFilterPreset.TEAMMATE.createOption(false);
      entityTargetFilterOptions[7] = TargetFilterPreset.FRIEND.createOption(false);
      entityTargetFilterOptions[8] = TargetFilterPreset.SHOP.createOption(false);
      entityTargetFilterOptions[9] = TargetFilterPreset.ANTI_BOT.createOption(false);
      entityTargetFilterOptions[10] = TargetFilterPreset.INVISIBLE.createOption(true);
      entityTargetFilterOptions[11] = TargetFilterPreset.SLEEPING.createOption(true);
      entityTargetFilterOptions[12] = TargetFilterPreset.DEAD.createOption(true);
      return createFilter(entityTargetFilterOptions);
   }

}
