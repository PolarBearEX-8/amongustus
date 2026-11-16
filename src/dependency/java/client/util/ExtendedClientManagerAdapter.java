package client.util;

import client.render.AuxiliaryRendererLivingEntityAdapter;
import net.minecraft.client.renderer.RendererLivingEntity;

public class ExtendedClientManagerAdapter extends GeometryFilter {
   public final AuxiliaryRendererLivingEntityAdapter auxiliaryRendererLivingEntityAdapter;

   @Override
   public void updateState() {
      this.passiveFloatEntry2 = new FloatEntryDispatcher(0.5F);
      this.passiveFloatEntry = new FloatEntryDispatcher(1.0F);
   }

   public ExtendedClientManagerAdapter(AuxiliaryRendererLivingEntityAdapter auxiliaryRendererLivingEntityAdapter2, RendererLivingEntity rendererLivingEntity) {
      super(rendererLivingEntity);
      this.auxiliaryRendererLivingEntityAdapter = auxiliaryRendererLivingEntityAdapter2;
   }
}
