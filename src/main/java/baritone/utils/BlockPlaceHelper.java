package baritone.utils;

import baritone.Baritone;
import baritone.api.utils.IPlayerContext;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.MovingObjectPosition;

public class BlockPlaceHelper {
   private final IPlayerContext ctx;
   private int rightClickTimer;

   public void tick(boolean flag) {
      if (this.rightClickTimer > 0) {
         this.rightClickTimer--;
      } else {
         MovingObjectPosition hit = this.ctx.objectMouseOver();
         if (flag && hit != null && hit.getBlockPos() != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            this.rightClickTimer = (Integer)Baritone.settings().rightClickSpeed.value;
            if (this.ctx
               .playerController()
               .processRightClickBlock(
                  this.ctx.player(), (WorldClient)this.ctx.world(), this.ctx.player().getCurrentEquippedItem(), hit.getBlockPos(), hit.sideHit, hit.hitVec
               )) {
               this.ctx.player().swingItem();
            } else if (this.ctx.player().getHeldItem().getItem() == null
               || !this.ctx.playerController().processRightClick(this.ctx.player(), this.ctx.world(), this.ctx.player().getCurrentEquippedItem())) {
               ;
            }
         }
      }
   }

   public BlockPlaceHelper(IPlayerContext playerContext) {
      this.ctx = playerContext;
   }
}
