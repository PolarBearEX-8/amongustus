package baritone.utils;

import baritone.api.utils.IPlayerContext;
import net.augustus.modules.player.AutoTool;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.EventClick;
import net.augustus.events.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.MovingObjectPosition;

public final class BlockBreakHelper implements ClickListener {
   private final IPlayerContext ctx;
   private Minecraft mc = Minecraft.getMinecraft();
   private boolean didBreakLastTick;
   private int lastBreakTick;

   public void stopBreakingBlock() {
      this.didBreakLastTick = false;
   }

   public BlockBreakHelper(IPlayerContext playerContext) {
      this.ctx = playerContext;
      EventManager.getEventBus().subscribe(7, this);
   }

   public void tick(boolean flag) {
      MovingObjectPosition hit = this.ctx.objectMouseOver();
      boolean flag2 = hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK;
      AutoTool.forceToolSwitch = this.didBreakLastTick = flag && flag2;
   }

   public void onClick(EventClick event) {
      boolean didBreakLastTick2 = this.didBreakLastTick;
      if (this.mc.playerController.isHittingBlock || this.lastBreakTick > this.mc.thePlayer.ticksExisted - 2 || this.lastBreakTick < this.mc.thePlayer.ticksExisted - 4) {
         if (didBreakLastTick2) {
            event.setOverrideBlockBreak(true);
            event.setBreakingBlock(true);
            this.lastBreakTick = this.mc.thePlayer.ticksExisted;
         }

         if (didBreakLastTick2 && !(this.mc.currentScreen instanceof GuiContainer)) {
            this.mc.leftClickCounter = (byte)0;
            event.setForceBreakBlock(true);
         }
      }
   }
}
