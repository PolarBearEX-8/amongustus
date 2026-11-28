package baritone.behavior;

import baritone.Baritone;
import baritone.api.cache.IWaypoint;
import baritone.api.cache.Waypoint;
import baritone.api.event.events.BlockInteractEvent;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.api.command.IBaritoneChatControl;
import baritone.api.cache.WaypointTag;
import baritone.utils.BlockStateInterface;
import java.util.Set;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

public class WaypointBehavior extends Behavior {

   public WaypointBehavior(Baritone baritone2) {
      super(baritone2);
   }

   @Override
   public void onBlockInteract(BlockInteractEvent blockInteractEvent) {
      if ((Boolean)Baritone.settings().doBedWaypoints.value) {
         if (blockInteractEvent.getType() == BlockInteractEvent.Type.USE) {
            BetterBlockPos betterBlockPos = BetterBlockPos.from(blockInteractEvent.getPos());
            IBlockState state = BlockStateInterface.get(this.ctx, betterBlockPos);
            if (state.getBlock() instanceof BlockBed) {
               if (state.getValue(BlockBed.PART) == BlockBed.EnumPartType.FOOT) {
                  betterBlockPos = betterBlockPos.offset2((EnumFacing)state.getValue(BlockBed.FACING));
               }

               if (!this.baritone.getWorldProvider2().getCurrentWorld2().getWaypoints().getByTag(WaypointTag.BED).stream().map(obj -> ((IWaypoint)obj).getLocation()).filter(betterBlockPos::equals).findFirst().isPresent()) {
                  this.baritone.getWorldProvider2().getCurrentWorld2().getWaypoints().addWaypoint(new Waypoint("bed", WaypointTag.BED, betterBlockPos));
               }
            }
         }
      }
   }

   @Override
   public void onPlayerDeath() {
      if ((Boolean)Baritone.settings().doDeathWaypoints.value) {
         Waypoint waypoint = new Waypoint("death", WaypointTag.DEATH, this.ctx.playerFeet());
         this.baritone.getWorldProvider2().getCurrentWorld2().getWaypoints().addWaypoint(waypoint);
         ChatComponentText chatComponentText = new ChatComponentText("Death position saved.");
         chatComponentText.getChatStyle()
            .setColor(EnumChatFormatting.WHITE)
            .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to goto death")))
            .setChatClickEvent(
               new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("%s%s goto %s @ %d", IBaritoneChatControl.FORCE_COMMAND_PREFIX, "wp", waypoint.getTag().getName(), waypoint.getCreationTimestamp()))
            );
         Helper.HELPER.logDirect(new IChatComponent[]{chatComponentText});
      }
   }
}
