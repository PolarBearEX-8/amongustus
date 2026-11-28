package net.minecraft.client.settings;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IntHashMap;

public class KeyBinding implements Comparable {
   public static final List keybindArray = Lists.newArrayList();
   private static final IntHashMap hash = new IntHashMap();
   private static final IntHashMap movementHash = new IntHashMap();
   private static final ArrayList pressedMovementKeys = new ArrayList();
   private static final Set keybindSet = Sets.newHashSet();
   private final String keyDescription;
   private final int keyCodeDefault;
   private final String keyCategory;
   private int keyCode;
   private boolean pressed;
   private int pressTime;

   @Override
   public int compareTo(Object other) {
      return this.compareTo((KeyBinding)other);
   }

   public String getKeyCategory() {
      return this.keyCategory;
   }

   public static void unPressAllKeys() {
      unPressKeysExcept(null);
   }

   public static void setKeyBindState(int n, boolean flag) {
      if (n != 0) {
         KeyBinding keyBinding = (KeyBinding)hash.lookup(n);
         if (keyBinding != null) {
            keyBinding.setPressed(flag);
         }
      }
   }

   public int getKeyCodeDefault() {
      return this.keyCodeDefault;
   }

   public static void unPressKeysExcept(Set set) {
      Augustus.getInstance().getModuleManager().saveMoveKeys.savedKeyStates.clear();

      for (KeyBinding keyBinding : (Iterable<KeyBinding>)(keybindArray)) {
         if (set == null || !set.contains(keyBinding.keyCode)) {
            Augustus.getInstance().getModuleManager().saveMoveKeys.saveKeyState(keyBinding);
            keyBinding.unpressKey();
         }
      }

      pressedMovementKeys.clear();
   }

   public static void rebuildMovementKeyMap() {
      GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
      if (gameSettings != null) {
         movementHash.clearMap();
         pressedMovementKeys.clear();

         for (KeyBinding keyBinding : (Iterable<KeyBinding>)(keybindArray)) {
            int keyCode2 = keyBinding.keyCode;
            if (keyCode2 == gameSettings.keyBindForward.getKeyCode() || keyCode2 == gameSettings.keyBindBack.getKeyCode() || keyCode2 == gameSettings.keyBindLeft.getKeyCode() || keyCode2 == gameSettings.keyBindRight.getKeyCode()) {
               movementHash.addKey(keyBinding.keyCode, keyBinding);
            }
         }
      }
   }

   public String getKeyDescription() {
      return this.keyDescription;
   }

   private void unpressKey() {
      this.pressTime = 0;
      this.setPressed(false);
   }

   public KeyBinding(String str, int n, String str2) {
      this.keyDescription = str;
      this.keyCode = n;
      this.keyCodeDefault = n;
      this.keyCategory = str2;
      keybindArray.add(this);
      hash.addKey(n, this);
      keybindSet.add(str2);
   }

   public static Set getKeybinds() {
      return keybindSet;
   }

   public void setPressTime(int n) {
      this.pressTime = n;
   }

   public static void resetKeyBindingArrayAndHash() {
      hash.clearMap();

      for (KeyBinding keyBinding : (Iterable<KeyBinding>)(keybindArray)) {
         hash.addKey(keyBinding.keyCode, keyBinding);
      }

      rebuildMovementKeyMap();
   }

   public int compareTo(KeyBinding other) {
      int n = I18n.format(this.keyCategory, new Object[0]).compareTo(I18n.format(other.keyCategory, new Object[0]));
      if (n == 0) {
         n = I18n.format(this.keyDescription, new Object[0]).compareTo(I18n.format(other.keyDescription, new Object[0]));
      }

      return n;
   }

   public static void onTick(int n) {
      if (n != 0) {
         KeyBinding keyBinding = (KeyBinding)hash.lookup(n);
         if (keyBinding != null) {
            keyBinding.pressTime++;
         }
      }
   }

   public int getPressTime() {
      return this.pressTime;
   }

   public static ArrayList getPressedMovementKeys() {
      return pressedMovementKeys;
   }

   public void setKeyCode(int n) {
      this.keyCode = n;
   }

   public void setPressed(boolean flag) {
      this.pressed = flag;
      if (movementHash.containsItem(this.keyCode)) {
         pressedMovementKeys.remove(this);
         if (flag) {
            pressedMovementKeys.add(this);
         }
      }
   }

   public boolean isPressed() {
      if (this.pressTime == 0) {
         return false;
      } else {
         this.pressTime--;
         return true;
      }
   }

   public int getKeyCode() {
      return this.keyCode;
   }

   public boolean isKeyDown() {
      return this.pressed;
   }
}
