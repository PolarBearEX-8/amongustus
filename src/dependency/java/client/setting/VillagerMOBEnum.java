package client.setting;

public enum VillagerMOBEnum implements NamedOption {
    PLAYER("Player"),
    MOB("Mob"),
    ANIMAL("Animal"),
    VILLAGER("Villager"),
    INVISIBLE("Invisible"),
    DEAD("Dead");

   public final String labelText;
   public boolean enabled = true;
   public static final VillagerMOBEnum[] ENUM_VALUES = new VillagerMOBEnum[]{PLAYER, MOB, ANIMAL, VILLAGER, INVISIBLE, DEAD};

   private VillagerMOBEnum(String text) {
      this.labelText = text;
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

   public static boolean matchesVillagerMOBEnum(VillagerMOBEnum villagerMOBEnum) {
      return villagerMOBEnum.enabled;
   }


}
