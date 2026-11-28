package net.augustus.modules.combat;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.EventClick;
import net.augustus.Augustus;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.item.ItemSword;

public class TntBlock extends Module implements ClickListener {
   private final DoubleValue minDamageToBlock;

   public TntBlock() {
       super("TntBlock", "TntBlockierung", new Color(252, 147, 91), Categorys.COMBAT);
      this.minDamageToBlock = new DoubleValue(this, "MinDamageToBlock", 0.0, 0.0, 20.0, 0);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
   }

   public void onClick(EventClick event) {

      for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (entity instanceof EntityTNTPrimed
            && (double)entity.getDistanceToEntity(mc.thePlayer) < 8.2
            && mc.thePlayer.getHeldItem() != null
            && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword) {
            EntityTNTPrimed entityTNTPrimed = (EntityTNTPrimed)entity;
            double d = Augustus.getInstance().getModuleManager().tntESP.calculateExplosionDamage(entityTNTPrimed);
            if (entityTNTPrimed.fuse < 4 && d >= this.minDamageToBlock.getValue()) {
               event.setRightMouseDown(true);
               break;
            }
         }
      }
   }

}
