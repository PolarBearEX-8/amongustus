package client.setting;

public enum KillAuraWeaponSlot implements NamedOption {
    SLOT1("Slot1"),
    SLOT2("Slot2"),
    SLOT3("Slot3"),
    SLOT4("Slot4"),
    SLOT5("Slot5"),
    SLOT6("Slot6"),
    SLOT7("Slot7"),
    SLOT8("Slot8"),
    SLOT9("Slot9");

   public final String labelText;
   public boolean enabled;
   public static final KillAuraWeaponSlot[] ENUM_VALUES = new KillAuraWeaponSlot[]{SLOT1, SLOT2, SLOT3, SLOT4, SLOT5, SLOT6, SLOT7, SLOT8, SLOT9};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private KillAuraWeaponSlot(String text) {
      this.labelText = text;
      this.enabled = true;
   }


}
