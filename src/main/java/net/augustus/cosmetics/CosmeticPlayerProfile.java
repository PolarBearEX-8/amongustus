package net.augustus.cosmetics;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.labymod.ModelData;

public class CosmeticPlayerProfile {
   private float heightOffset;
   private UUID playerUuid;
   private CloakData cloakData;
   private Map cosmetics;

   public boolean isCosmeticActive(Integer num) {
      return this.getCosmetics().containsKey(num) && ((ModelData)this.getCosmetics().get(num)).isActive();
   }

   public boolean hasCosmetics() {
      return this.cosmetics != null && !this.cosmetics.isEmpty();
   }

   public UUID getPlayerUuid() {
      return this.playerUuid;
   }

   public boolean hasActiveCloak() {
      return this.cloakData.hasActiveCloak();
   }

   public CosmeticPlayerProfile(long l, UUID uuid) {
      this.cloakData = new CloakData();
      this.heightOffset = 0.0F;
      this.playerUuid = uuid;
   }

   public void setHeightOffset(float f) {
      this.heightOffset = f;
   }

   public void resetHeightOffset() {
      this.heightOffset = 0.0F;
   }

   public void setCloakData(CloakData cloakData2) {
      this.cloakData = cloakData2;
   }

   public CloakData getCloakData() {
      return this.cloakData;
   }

   public float getHeightOffset() {
      return this.heightOffset;
   }

   public Map getCosmetics() {
      if (this.cosmetics == null) {
         this.cosmetics = new ConcurrentHashMap();
      }

      return this.cosmetics;
   }
}
