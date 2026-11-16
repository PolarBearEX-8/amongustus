package client.setting;

public enum ItemStackCommonType {
    COMMON(ClientSettingsBlackType.WHITE, "Common"),
    UNCOMMON(ClientSettingsBlackType.YELLOW, "Uncommon"),
    RARE(ClientSettingsBlackType.AQUA, "Rare"),
    EPIC(ClientSettingsBlackType.LIGHT_PURPLE, "Epic");

   public final ClientSettingsBlackType rarityColor;
   public final String rarityName;

   private ItemStackCommonType(ClientSettingsBlackType clientSettingsBlackType, String text) {
      this.rarityColor = clientSettingsBlackType;
      this.rarityName = text;
   }


}
