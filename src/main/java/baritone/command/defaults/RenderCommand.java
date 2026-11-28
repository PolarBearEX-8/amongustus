package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RenderCommand extends Command {

   public RenderCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"render"});
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList("The render command fixes glitched chunk rendering without having to reload all of them.", "", "Usage:", "> render");
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      BetterBlockPos betterBlockPos = this.ctx.playerFeet();
      int n = (this.ctx.getMc().gameSettings.renderDistanceChunks + 1) * 16;
      int n2 = betterBlockPos.x2 - n;
      int n3 = betterBlockPos.z2 - n;
      int n4 = betterBlockPos.x2 + n;
      this.ctx.getMc().renderGlobal.markBlockRangeForRenderUpdate(n2, 0, n3, n4, 255, betterBlockPos.z2 + n);
      this.logDirect2("Done");
   }

   @Override
   public String getShortDesc() {
      return "Fix glitched chunks";
   }
}
