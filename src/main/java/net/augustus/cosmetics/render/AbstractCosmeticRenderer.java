package net.augustus.cosmetics.render;

import net.augustus.cosmetics.MoreCosmeticsCore;
import net.augustus.cosmetics.model.CosmeticModelLoader;
import java.util.HashMap;

public abstract class AbstractCosmeticRenderer {
   public CosmeticModelLoader modelLoader;
   public HashMap playerRenderLayers;

   public AbstractCosmeticRenderer() {
       super();
      this.modelLoader = MoreCosmeticsCore.getInstance().getModelLoader();
      this.playerRenderLayers = new HashMap();
   }

   public abstract void registerRenderLayers();



}
