package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PressKeyAuxiliaryCustom implements AuxiliaryCustom {
   public final List list = Lists.newArrayList();

   @Override
   public IChatComponent getIChatComponent() {
      return new ChatComponentText("Press a key to select a command, and again to use it.");
   }

   public PressKeyAuxiliaryCustom() {
      this.list.add(new TeleportPlayerCurveTracker());
      this.list.add(new SelectTeamAuxiliaryCustom());
   }

   @Override
   public List getList() {
      return this.list;
   }
}
