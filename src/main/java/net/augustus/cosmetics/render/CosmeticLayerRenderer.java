package net.augustus.cosmetics.render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class CosmeticLayerRenderer implements LayerRenderer {
   private PlayerCosmeticRenderer cosmeticRenderer;
   private boolean slimArms;
   private RenderPlayer playerRenderer;

   public CosmeticLayerRenderer(RenderPlayer renderPlayer, PlayerCosmeticRenderer playerCosmeticRenderer, boolean flag) {
      this.playerRenderer = renderPlayer;
      this.cosmeticRenderer = playerCosmeticRenderer;
      this.slimArms = flag;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.renderPlayerCosmetics((AbstractClientPlayer)entity, f, f2, f3, f4, f5, f6, f7);
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   public void renderPlayerCosmetics(AbstractClientPlayer clientPlayer, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.cosmeticRenderer.renderCosmetics(clientPlayer, f, f2, f4, this.playerRenderer.getPlayerModel(), this.slimArms, f3);
   }
}
