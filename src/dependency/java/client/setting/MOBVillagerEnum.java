package client.setting;

public enum MOBVillagerEnum implements NamedOption {
    PLAYER("Player", true),
    MOB("Mob", false),
    ANIMAL("Animal", false),
    VILLAGER("Villager", false),
    ARMOR_STAND("ArmorStand", false),
    INVISIBLE("Invisible", false);

   public final String labelText;
   public boolean enabled;
   public static final MOBVillagerEnum[] ENUM_VALUES = new MOBVillagerEnum[]{PLAYER, MOB, ANIMAL, VILLAGER, ARMOR_STAND, INVISIBLE};

   private MOBVillagerEnum(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static boolean matchesMOBVillagerEnum(MOBVillagerEnum mOBVillagerEnum) {
      return mOBVillagerEnum.enabled;
   }


}
