package net.minecraft.client.settings;

import client.Client;
import client.setting.GameSettings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.item.AltItemArrayEntry;
import net.minecraft.util.StatCollector;

public class KeyBinding implements Comparable {
   public static final List keybindArray = Lists.newArrayList();
   public static final AltItemArrayEntry hash = new AltItemArrayEntry();
   public static final AltItemArrayEntry defaultHash = new AltItemArrayEntry();
   public static final ArrayList pressedKeybinds = new ArrayList();
   public static final Set keybindSet = Sets.newHashSet();
   public final String keyDescription;
   public final int keyCodeDefault;
   public final String keyCategory;
   public int keyCode;
   public boolean pressed;
   public int pressTime;

   public int getKeyCodeDefault() {
      return this.keyCodeDefault;
   }

   private void unpressKey() {
      this.pressTime = 0;
      this.pressed = false;
   }

   public boolean isKeyDown() {
      return this.pressed;
   }

   public void setKeyCode(int keyCode2) {
      this.keyCode = keyCode2;
   }

   public String getKeyCategory() {
      return this.keyCategory;
   }

   public static void setKeyBindState(int count, boolean flag) {
      if (count != 0) {
         KeyBinding keyBinding = (KeyBinding)hash.resolveTargetByIndex(count);
         if (keyBinding != null) {
            keyBinding.pressed = flag;
            if (defaultHash.checkIndex(count)) {
               pressedKeybinds.remove(keyBinding);
               if (flag) {
                  pressedKeybinds.add(keyBinding);
               }
            }
         }
      }
   }

   public static void unPressAllKeys() {
      hash.updateState();

      for (KeyBinding keyBinding : (Iterable<KeyBinding>)(Iterable<?>)keybindArray) {
         hash.applyTarget(keyBinding.keyCode, keyBinding);
      }

      updateMovementKeyStates();
   }

   public static ArrayList getPressedKeybinds() {
      return pressedKeybinds;
   }

   public static void resetKeyBindingArrayAndHash() {
      Client.getClient().getModuleManager().saveMoveKeys.savedKeys.clear();

      for (KeyBinding keyBinding : (Iterable<KeyBinding>)(Iterable<?>)keybindArray) {
         Client.getClient().getModuleManager().saveMoveKeys.applyScorePlayerTeam(keyBinding);
         keyBinding.unpressKey();
      }

      pressedKeybinds.clear();
   }

   public int getPressTime() {
      return this.pressTime;
   }

   public static void updateMovementKeyStates() {
      GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
      if (gameSettings != null) {
         defaultHash.updateState();
         pressedKeybinds.clear();

         for (KeyBinding keyBinding : (Iterable<KeyBinding>)(Iterable<?>)keybindArray) {
            int keyCode2 = keyBinding.keyCode;
            if (keyCode2 == gameSettings.keyBindForward.getKeyCode()
               || keyCode2 == gameSettings.keyBindBack.getKeyCode()
               || keyCode2 == gameSettings.keyBindLeft.getKeyCode()
               || keyCode2 == gameSettings.keyBindRight.getKeyCode()) {
               defaultHash.applyTarget(keyBinding.keyCode, keyBinding);
            }
         }
      }
   }

   public int getKeyCode() {
      return this.keyCode;
   }

   public int compareToKeyBinding(KeyBinding keyBinding) {
      int count = StatCollector.getText(this.keyCategory, new Object[0]).compareTo(StatCollector.getText(keyBinding.keyCategory, new Object[0]));
      if (count == 0) {
         count = StatCollector.getText(this.keyDescription, new Object[0]).compareTo(StatCollector.getText(keyBinding.keyDescription, new Object[0]));
      }

      return count;
   }

   public static Set getKeybinds() {
      return keybindSet;
   }

   public static void onTick(int count) {
      if (count != 0) {
         KeyBinding keyBinding = (KeyBinding)hash.resolveTargetByIndex(count);
         if (keyBinding != null) {
            keyBinding.pressTime++;
         }
      }
   }

   public void setPressTime(int pressTime2) {
      this.pressTime = pressTime2;
   }

   public boolean isPressed() {
      if (this.pressTime == 0) {
         return false;
      } else {
         this.pressTime--;
         return true;
      }
   }

   public String getKeyDescription() {
      return this.keyDescription;
   }

   public KeyBinding(String text, int count, String text2) {
      this.keyDescription = text;
      this.keyCode = count;
      this.keyCodeDefault = count;
      this.keyCategory = text2;
      keybindArray.add(this);
      hash.applyTarget(count, this);
      keybindSet.add(text2);
   }

   @Override
   public int compareTo(Object entry) {
      return this.compareToKeyBinding((KeyBinding)entry);
   }
}
