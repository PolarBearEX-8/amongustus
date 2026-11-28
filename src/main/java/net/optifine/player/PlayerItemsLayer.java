package net.optifine.player;

import java.util.Map;
import java.util.Set;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.config.Config;

public class PlayerItemsLayer implements LayerRenderer {
   private RenderPlayer renderPlayer = null;

   public PlayerItemsLayer(RenderPlayer renderPlayer2) {
      this.renderPlayer = renderPlayer2;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.renderEquippedItems(entity, f7, f3);
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   public static void register(Map map) {
      Set keys = map.keySet();
      boolean flag = false;

      for (Object obj : keys) {
         Object obj2 = map.get(obj);
         if (obj2 instanceof RenderPlayer) {
            RenderPlayer renderPlayer2 = (RenderPlayer)obj2;
            renderPlayer2.addLayer(new PlayerItemsLayer(renderPlayer2));
            flag = true;
         }
      }

      if (!flag) {
         Config.warn("PlayerItemsLayer not registered");
      }
   }

   public void renderEquippedItems(EntityLivingBase entity, float f, float f2) {
      if (Config.isShowCapes() && entity instanceof AbstractClientPlayer) {
         AbstractClientPlayer clientPlayer = (AbstractClientPlayer)entity;
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableRescaleNormal();
         GlStateManager.enableCull();
         PlayerConfigurations.renderPlayerItems(this.renderPlayer.getPlayerModel(), clientPlayer, f, f2);
         GlStateManager.disableCull();
      }
   }
}
