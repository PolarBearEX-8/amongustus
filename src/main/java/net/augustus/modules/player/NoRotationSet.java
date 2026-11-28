package net.augustus.modules.player;

import java.awt.Color;
import java.util.Set;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.Augustus;
import net.augustus.settings.StringValue;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class NoRotationSet extends Module {
   public final StringValue mode;

   public NoRotationSet() {
       super("NoRotationSet", "KeineRotationssetzung", new Color(26, 108, 135), Categorys.PLAYER);
      this.mode = new StringValue(this, "Mode", "Default", new String[]{"Default", "Test"});
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
   }

   public static boolean isRelativeZeroTeleport(S08PacketPlayerPosLook packet) {
      if (Augustus.getInstance().getModuleManager().noRotationSet.mode.getSelectedValue().equalsIgnoreCase("Test") && Augustus.getInstance().getModuleManager().noRotationSet.isToggled()) {
         Set flags = packet.getFlags();
         return packet.getX() == 0.0
            && packet.getY() == 0.0
            && packet.getZ() == 0.0
            && packet.getYaw() == 0.0F
            && packet.getPitch() == 0.0F
            && flags.contains(S08PacketPlayerPosLook.EnumFlags.X)
            && flags.contains(S08PacketPlayerPosLook.EnumFlags.Y)
            && flags.contains(S08PacketPlayerPosLook.EnumFlags.Z)
            && flags.contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)
            && flags.contains(S08PacketPlayerPosLook.EnumFlags.X_ROT);
      } else {
         return false;
      }
   }

}
