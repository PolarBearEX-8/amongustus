package net.minecraft.item;

import client.event.PreMotionDispatcher;
import client.render.RenderPlayer;
import client.util.AuxiliaryFloatEntryBridge;
import client.util.FailedToLoadMaskFromManagerEntryAdapter;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;

public class FactoryItemEntry implements PreMotionDispatcher {
   public FailedToLoadMaskFromManagerEntryAdapter failedToLoadMaskFromManagerEntryAdapter;
   public boolean enabled;
   public RenderPlayer renderPlayer;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      AbstractClientPlayer player = (AbstractClientPlayer)livingEntity;
      this.applyAbstractClientPlayer(player, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   public void applyAbstractClientPlayer(AbstractClientPlayer player, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      FailedToLoadMaskFromManagerEntryAdapter failedToLoadMaskFromManagerEntryAdapter2 = this.failedToLoadMaskFromManagerEntryAdapter;
      AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge = this.renderPlayer.getAuxiliaryFloatEntryBridge();
      boolean enabled2 = this.enabled;
      AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge2 = auxiliaryFloatEntryBridge;
      failedToLoadMaskFromManagerEntryAdapter2.computeFactor(player, (byte)0, posX, posX2, posX4, auxiliaryFloatEntryBridge2, enabled2, posX3);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public FactoryItemEntry(RenderPlayer renderPlayer2, FailedToLoadMaskFromManagerEntryAdapter failedToLoadMaskFromManagerEntryAdapter2, boolean flag) {
      this.renderPlayer = renderPlayer2;
      this.failedToLoadMaskFromManagerEntryAdapter = failedToLoadMaskFromManagerEntryAdapter2;
      this.enabled = flag;
   }
}
