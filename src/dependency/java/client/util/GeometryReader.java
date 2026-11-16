package client.util;

import net.minecraft.client.renderer.RendererLivingEntity;

public class GeometryReader extends GeometryFilter {
   @Override
   public void updateState() {
      this.passiveFloatEntry2 = new AimEstimator(0.5F, 0.0F, true);
      this.passiveFloatEntry = new AimEstimator(1.0F, 0.0F, true);
   }

   public GeometryReader(RendererLivingEntity rendererLivingEntity) {
      super(rendererLivingEntity);
   }
}
