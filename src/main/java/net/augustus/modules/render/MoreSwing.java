package net.augustus.modules.render;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.utils.input.MouseInputUtil;
import net.augustus.settings.BooleanValue;

public class MoreSwing extends Module implements ClickListener {
   private final BooleanValue whileKillAura;
   private final BooleanValue whileAutoClicker;
   private final BooleanValue whileLeftClick;

   public MoreSwing() {
       super("MoreSwing", "MehrSchwingen", new Color(50, 168, 164), Categorys.RENDER);
      this.whileKillAura = new BooleanValue(this, "WhileKillAura", true);
      this.whileAutoClicker = new BooleanValue(this, "WhileAutoClicker", true);
      this.whileLeftClick = new BooleanValue(this, "WhileLeftClick", false);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7, 1000000);
   }

   public void onClick(EventClick event) {
      if (this.shouldTriggerExtraSwing() && (!mc.thePlayer.isSwingInProgress || mc.thePlayer.swingProgressInt >= mc.thePlayer.getArmSwingAnimationEnd() / 2 || mc.thePlayer.swingProgressInt < 0)) {
         mc.thePlayer.swingProgressInt = -1;
         mc.thePlayer.isSwingInProgress = true;
      }
   }

   private boolean shouldTriggerExtraSwing() {
      return mc.isNotInGui()
         && !mc.thePlayer.isBlocking()
         && (
            this.whileKillAura.getValue()
                  && ClientManagers.moduleManager.killAura.isToggled()
                  && !ClientManagers.moduleManager.killAura.noSwing.getValue()
                  && ClientManagers.moduleManager.killAura.target != null
                  && (
                     (double)mc.thePlayer.getDistanceToEntityBox(ClientManagers.moduleManager.killAura.target) < ClientManagers.moduleManager.killAura.preAimRange.getLastValue()
                           && (double)mc.thePlayer.getDistanceToEntityBox(ClientManagers.moduleManager.killAura.target) < ClientManagers.moduleManager.killAura.preHitRange.getLastValue()
                           && ClientManagers.moduleManager.killAura.preHit.getValue()
                        || mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
                  )
               || this.whileAutoClicker.getValue()
                  && ClientManagers.moduleManager.autoClicker.isToggled()
                  && (!ClientManagers.moduleManager.autoClicker.onlyOnClick.getValue() || MouseInputUtil.isButtonDown(0))
               || this.whileLeftClick.getValue() && MouseInputUtil.isButtonDown(0)
         );
   }

}
