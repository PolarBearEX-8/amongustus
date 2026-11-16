package cc.baritone.utils;

import augustus.modules.player.AutoTool;
import client.event.ClickListener;
import client.event.EventBusManager;
import client.event.EventClick;
import client.util.MovingObjectType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.MovingObjectPosition;

public final class ClickListenerAdapter implements ClickListener {
   public final StaticListener staticListener;
   public Minecraft mc = Minecraft.getMinecraft();
   public boolean enabled;
   public int count;

   @Override
   public void onClick(EventClick event) {
      boolean enabled2 = this.enabled;
      if (this.mc.playerController.state || this.count > this.mc.thePlayer.ticksExisted - 2 || this.count < this.mc.thePlayer.ticksExisted - 4) {
         if (enabled2) {
            event.setState5(true);
            event.setState4(true);
            this.count = this.mc.thePlayer.ticksExisted;
         }

         if (enabled2 && !(this.mc.currentScreen instanceof GuiContainer)) {
            this.mc.leftClickCounter = 0;
            event.setState6(true);
         }
      }
   }

   public void setEnabled(boolean enabled2) {
      MovingObjectPosition movingObjectPosition = this.staticListener.getMovingObjectPosition();
      boolean flag = movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.BLOCK;
      AutoTool.activeState = this.enabled = enabled2 && flag;
   }

   public void updateState() {
      this.enabled = false;
   }

   public ClickListenerAdapter(StaticListener staticListener2) {
      this.staticListener = staticListener2;
      EventBusManager.getDietrichEvents2().subscribe(7, this);
   }
}
