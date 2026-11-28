package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.PotionParticleListener;
import net.augustus.events.EventPotionParticle;
import net.augustus.settings.StringValue;
import net.minecraft.client.entity.EntityPlayerSP;

public class NoPotionParticles extends Module implements PotionParticleListener {
   private final StringValue particlesToRemoveFrom;

   public NoPotionParticles() {
       super("NoPotionParticles", "KeineTränkePartikel", new Color(66, 245, 161), Categorys.RENDER);
      this.particlesToRemoveFrom = new StringValue(this, "Particles to remove from", "Myself", new String[]{"Myself", "OtherEntities", "AllEntities"});
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(65);
   }

   public void onPotionParticle(EventPotionParticle event) {
      boolean flag = event.getEntity() instanceof EntityPlayerSP;
      if (this.isAllEntitiesMode() || flag && this.isMyselfMode() || !flag && this.isOtherEntitiesMode()) {
         event.setCancelled(true);
      }
   }

   private boolean isMyselfMode() {
      return this.particlesToRemoveFrom.getSelectedValue().equals("Myself");
   }

   private boolean isOtherEntitiesMode() {
      return this.particlesToRemoveFrom.getSelectedValue().equals("OtherEntities");
   }

   private boolean isAllEntitiesMode() {
      return this.particlesToRemoveFrom.getSelectedValue().equals("AllEntities");
   }

}
