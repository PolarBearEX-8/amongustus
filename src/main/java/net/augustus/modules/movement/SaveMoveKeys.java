package net.augustus.modules.movement;

import java.awt.Color;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.TuplePair;
import net.augustus.events.listeners.PacketListener;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;

public class SaveMoveKeys extends Module implements PacketListener {
   public final HashMap savedKeyStates;
   private int ticksSinceGuiClose;
   private final RandomizedRangeSettings loadKeyDelay;

   public SaveMoveKeys() {
       super("SaveMoveKeys", "SchlüsselSpeicherung", new Color(235, 64, 52), Categorys.MOVEMENT);
      this.savedKeyStates = new HashMap();
      this.ticksSinceGuiClose = 0;
      this.loadKeyDelay = (new RandomizedRangeSettings(this, "LoadKeyDelay", 1.0, 3.0, 0.0, 5.0, 0)).withoutNotInARow();
   }

   public void onEnable() {
      this.ticksSinceGuiClose = 0;
   }

   public void onDisable() {
      this.savedKeyStates.clear();
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   public void saveKeyState(KeyBinding keyBinding) {
      if (this.isToggled()) {
         if (keyBinding.getKeyDescription().equals("key.forward")
            || keyBinding.getKeyDescription().equals("key.left")
            || keyBinding.getKeyDescription().equals("key.back")
            || keyBinding.getKeyDescription().equals("key.right")
            || keyBinding.getKeyDescription().equals("key.jump")) {
            this.savedKeyStates.put(keyBinding, new TuplePair(keyBinding.getPressTime(), keyBinding.isKeyDown()));
         }
      }
   }

   public void restoreKeyStates() {
      if (this.isToggled() && this.ticksSinceGuiClose == this.loadKeyDelay.getLastIntValue() && mc.currentScreen == null) {
         this.ticksSinceGuiClose = 10000;
         this.loadKeyDelay.nextValue();

         for (KeyBinding keyBinding : (Iterable<KeyBinding>)(KeyBinding.keybindArray)) {
            TuplePair tuplePair = (TuplePair)this.savedKeyStates.get(keyBinding);
            if (tuplePair != null && KeyboardInputUtil.isKeyDown(keyBinding.getKeyCode())) {
               keyBinding.setPressed((Boolean)tuplePair.getSecond());
               keyBinding.setPressTime((Integer)tuplePair.getFirst());
            }
         }
      }
   }

   public void incrementInGameTicks() {
      if (mc.currentScreen == null) {
         this.ticksSinceGuiClose++;
      }
   }

   public void resetInGameTicks() {
      this.ticksSinceGuiClose = 0;
   }

   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S07PacketRespawn) {
         this.savedKeyStates.clear();
      }
   }

}
