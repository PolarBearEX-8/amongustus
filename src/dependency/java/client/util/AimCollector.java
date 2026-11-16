package client.util;

public enum AimCollector {
    ANVIL,
    BEACON,
    BREWING_STAND,
    CHEST,
    CRAFTING,
    DISPENSER,
    ENCHANTMENT,
    FURNACE,
    HOPPER,
    HORSE,
    VILLAGER,
    SHULKER_BOX,
    CREATIVE,
    INVENTORY;

   public static final AimCollector[] VALUES = values();
   public static final AimCollector[] ENUM_VALUES = new AimCollector[]{
      ANVIL, BEACON, BREWING_STAND, CHEST, CRAFTING, DISPENSER, ENCHANTMENT, FURNACE, HOPPER, HORSE, VILLAGER, SHULKER_BOX, CREATIVE, INVENTORY
   };

}
