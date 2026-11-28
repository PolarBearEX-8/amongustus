package net.augustus.commands.impl;



import net.augustus.commands.Command;
import net.augustus.utils.MoveUtil;
public class HClipCommand extends Command {

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (clip forwards and sidewards through walls)");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (args.length == 3) {
         double d = Double.parseDouble(args[1]);
         double d2 = Double.parseDouble(args[2]);
         if (mc.theWorld != null) {
            if (mc.thePlayer != null) {
               mc.thePlayer.setPosition(mc.thePlayer.posX + d, mc.thePlayer.posY, mc.thePlayer.posZ + d2);
            }
         }

         this.sendChat("hclipped");
      } else if (args.length == 2) {
         double d3 = Double.parseDouble(args[1]);
         double d4 = Math.toRadians((double)MoveUtil.getDirectionDegrees(false));
         double d5 = -(Math.sin(d4) * d3);
         double d6 = Math.cos(d4) * d3;
         if (mc.theWorld != null && mc.thePlayer != null) {
            mc.thePlayer.setPosition(mc.thePlayer.posX + d5, mc.thePlayer.posY, mc.thePlayer.posZ + d6);
         }
      } else {
         this.sendChat("§7" + this.getCommand() + " [§2DistanceX§7] [§2DistanceZ§7] or");
         this.sendChat("§7" + this.getCommand() + " [§2DistanceXZ§7]");
      }
   }

   public HClipCommand() {
      super("hclip");
   }

}
