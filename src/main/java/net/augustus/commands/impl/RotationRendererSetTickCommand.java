package net.augustus.commands.impl;

import net.augustus.Augustus;
import net.augustus.commands.Command;
import net.augustus.utils.ChatUtil;
import net.augustus.modules.misc.RotationRenderer;

public class RotationRendererSetTickCommand extends Command {

   public void commandAction(String[] args, String rawMessage) {
      if (args.length < 2) {
         ChatUtil.sendChat(".rrsettick [tick]");
      } else {
         RotationRenderer rotationRenderer = Augustus.getInstance().getModuleManager().rotationRenderer;
         if (!rotationRenderer.isToggled()) {
            ChatUtil.sendChat("RotationRenderer isn't enabled");
         } else {
            try {
               int n = Integer.parseInt(args[1]);
               if (rotationRenderer.recordedRotations.size() <= n) {
                  ChatUtil.sendChat("There are only " + rotationRenderer.recordedRotations.size() + " ticks available");
                  return;
               }

               rotationRenderer.playbackIndex = (double)n;
               ChatUtil.sendChat("Set tick to " + n);
            } catch (NumberFormatException e) {
               ChatUtil.sendChat("Tick must be an integer!");
            }
         }
      }
   }

   public void helpMessage() {
      ChatUtil.sendChat("§c" + this.getCommand() + "§7 (sets the tick inside the rotationrenderer)");
   }

   public RotationRendererSetTickCommand(int n, short s, short s2) {
      super(".rrsettick");
   }

}
