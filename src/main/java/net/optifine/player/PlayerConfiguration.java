package net.optifine.player;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.optifine.config.Config;

public class PlayerConfiguration {
   private PlayerItemModel[] playerItemModels = new PlayerItemModel[0];
   private boolean initialized = false;

   public PlayerItemModel[] getPlayerItemModels() {
      return this.playerItemModels;
   }

   public void addPlayerItemModel(PlayerItemModel playerItemModel) {
      this.playerItemModels = (PlayerItemModel[])Config.addObjectToArray(this.playerItemModels, playerItemModel);
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public void setInitialized(boolean flag) {
      this.initialized = flag;
   }

   public void renderPlayerItems(ModelBiped modelBiped, AbstractClientPlayer clientPlayer, float f, float f2) {
      if (this.initialized) {
         for (int i = 0; i < this.playerItemModels.length; i++) {
            this.playerItemModels[i].render(modelBiped, clientPlayer, f, f2);
         }
      }
   }
}
