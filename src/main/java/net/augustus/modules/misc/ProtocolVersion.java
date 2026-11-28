package net.augustus.modules.misc;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.DoubleValue;

public class ProtocolVersion extends Module {
   public final DoubleValue protocolVersionVal;

   public ProtocolVersion() {
       super("ProtocolVersion", "ProtokollVersion", true, Color.RED, Categorys.MISC);
      this.protocolVersionVal = (DoubleValue)new DoubleValue(this, "ProtocolVersionVal", 47.0, 0.0, 0.0, 0).visibleWhen(() -> false).withChangeListener(() -> this.applySelectedProtocolVersion());
      this.setToggled(true);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   private void applySelectedProtocolVersion() {

      for (com.viaversion.viaversion.api.protocol.version.ProtocolVersion protocolVersion : com.viaversion.viaversion.api.protocol.version.ProtocolVersion.getReversedProtocols()) {
         if ((double)protocolVersion.getVersion() == this.protocolVersionVal.getValue()) {
            ViaAugustus.targetProtocolVersion = protocolVersion;
            return;
         }
      }
   }

}
