package client.util;

import client.render.PointSpriteRenderer;
import net.minecraft.client.renderer.RendererLivingEntity;

public class SecondaryClientManagerAdapter extends GeometryFilter {
   public final PointSpriteRenderer pointSpriteRenderer;

   @Override
   public void updateState() {
      this.passiveFloatEntry2 = new InternalFloatEntryBridge(0.5F, true);
      this.passiveFloatEntry = new InternalFloatEntryBridge(1.0F, true);
   }

   public SecondaryClientManagerAdapter(PointSpriteRenderer pointSpriteRenderer2, RendererLivingEntity rendererLivingEntity) {
      super(rendererLivingEntity);
      this.pointSpriteRenderer = pointSpriteRenderer2;
   }
}
