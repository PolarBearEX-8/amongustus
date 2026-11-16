package client.util;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.labymod.ModelData;

public class UUIDControllerHandler {
   public float factor;
   public UUID uUID;
   public Map map;
   public CloakEnabledController cloakEnabledController = new CloakEnabledController();

   public boolean matchesInteger(Integer count) {
      CosmeticItemRegistry.getIndexArray();
      return this.getMap().containsKey(count) && ((ModelData)this.getMap().get(count)).isActive();
   }

   public CloakEnabledController getCloakEnabledController() {
      return this.cloakEnabledController;
   }

   public UUIDControllerHandler(UUID uuid) {
      CosmeticItemRegistry.getIndexArray();
      this.factor = 0.0F;
      this.uUID = uuid;
   }

   public Map getMap() {
      CosmeticItemRegistry.getIndexArray();
      if (this.map == null) {
         this.map = new ConcurrentHashMap();
      }

      return this.map;
   }

   public float getFactor() {
      return this.factor;
   }

   public boolean isEmpty() {
      CosmeticItemRegistry.getIndexArray();
      return this.map != null && !this.map.isEmpty();
   }

   public UUID getUUID() {
      return this.uUID;
   }

   public boolean isActive() {
      return this.cloakEnabledController.isActive();
   }

   public void setFactor(float factor2) {
      this.factor = factor2;
   }

   public void updateState() {
      this.factor = 0.0F;
   }

   public void setCloakEnabledController(CloakEnabledController cloakEnabledController2) {
      this.cloakEnabledController = cloakEnabledController2;
   }
}
