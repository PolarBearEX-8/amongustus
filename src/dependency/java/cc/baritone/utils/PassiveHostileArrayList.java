package cc.baritone.utils;

import com.google.common.collect.Multimap;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class PassiveHostileArrayList extends java.util.ArrayList {
   public final MinecraftSplineCalculator minecraftSplineCalculator;

   public PassiveHostileArrayList(MinecraftSplineCalculator minecraftSplineCalculator2) throws Error {
      super();
      this.minecraftSplineCalculator = minecraftSplineCalculator2;

      try {
         int count4 = 0;
         Multimap multimap = AStarCoordinator.resolveMultimap(29596, 61735, (char)45557);
         java.util.ArrayList list = new java.util.ArrayList(multimap.keySet());
         list.remove("Passive");
         list.remove("Hostile");
         list.remove("Other");
         Collections.sort(list);
         list.add(0, "Hostile");
         list.add(1, "Passive");
         list.add("Other");

         for (String text : (Iterable<String>)(Object)(list)) {
            WdlGuiPreUpdateDispatcherBridge wdlGuiPreUpdateDispatcherBridge = new WdlGuiPreUpdateDispatcherBridge(this.minecraftSplineCalculator, text);
            this.add(wdlGuiPreUpdateDispatcherBridge);
            java.util.ArrayList list2 = new java.util.ArrayList(multimap.get(text));
            Collections.sort(list2);

            for (String text2 : (Iterable<String>)(Object)(list2)) {
               this.add(new EntityEnabledPreUpdateDispatcher(this.minecraftSplineCalculator, wdlGuiPreUpdateDispatcherBridge, text2));
               int index = UserEntityGuiScreen.resolveFontRenderer(this.minecraftSplineCalculator.userEntityGuiScreen).getIndex(text2);
               if (index > count4) {
                  count4 = index;
               }
            }
         }

         MinecraftSplineCalculator.computeCountByMinecraftSplineC(this.minecraftSplineCalculator, count4);
         MinecraftSplineCalculator.computeCount(this.minecraftSplineCalculator, MinecraftSplineCalculator.computeCount(this.minecraftSplineCalculator) + 255);
      } catch (Exception exception) {
         LoggerEntry.applyAltListener(OptionType.ERROR, "wdl.messages.generalError.failedToSetUpEntityUI", new Object[]{exception});
         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
      }
   }
}
