package net.augustus.modules.player;

import java.awt.Color;
import java.util.Locale;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.clickgui.components.TextFieldComponent;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.events.listeners.SprintListener;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.options.GuiMoveScreenOption;
import net.augustus.utils.MoveUtil;
import net.augustus.events.EventSafeWalk;
import net.augustus.events.listeners.SafeWalkListener;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventSprint;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C16PacketClientStatus;

public class GuiMove extends Module implements SprintListener, MoveInputListener, UpdateListener, PacketListener, MoveEntityListener, SafeWalkListener {
   private final MultiOptionSetting guis;
   public BooleanValue noInvPacket;
   private final TimeHelper timeHelper;
   private GuiMoveScreenOption activeScreenOption;

   public GuiMove() {
       super("GuiMove", "BenutzeroberflächenBewegung", new Color(11, 143, 125), Categorys.PLAYER);
      this.guis = new MultiOptionSetting(this, "Guis", GuiMoveScreenOption.values());
      this.timeHelper = new TimeHelper();
      this.activeScreenOption = null;

      for (GuiMoveScreenOption guiMoveScreenOption : GuiMoveScreenOption.values()) {
         String str = guiMoveScreenOption.getName().toLowerCase(Locale.ROOT);
         SettingGroup settingGroup = new SettingGroup(this, guiMoveScreenOption.getName()).visibleWhen(guiMoveScreenOption::isSelected);
         GuiMoveScreenOption.setModeSetting(guiMoveScreenOption, (StringValue)new StringValue(this, guiMoveScreenOption.getName() + "Mode", "Basic", new String[]{"Basic", "Resprint", "Intave"}, settingGroup).withDisplayName("Mode"));
         GuiMoveScreenOption.setSneakSetting(guiMoveScreenOption, (BooleanValue)new BooleanValue(this, guiMoveScreenOption.getName() + "Sneak", false, settingGroup).withDisplayName("Sneak in " + str));
         GuiMoveScreenOption.setJumpSetting(guiMoveScreenOption, (BooleanValue)new BooleanValue(this, guiMoveScreenOption.getName() + "Jump", false, settingGroup).withDisplayName("Jump in " + str));
         if (guiMoveScreenOption == GuiMoveScreenOption.INVENTORY) {
            this.noInvPacket = (BooleanValue)new BooleanValue(this, "NoInvPacket", false, settingGroup).withDisplayName("No inventory open packet");
         }

         GuiMoveScreenOption.setRotateSetting(guiMoveScreenOption, (BooleanValue)new BooleanValue(this, guiMoveScreenOption.getName() + "Rotate", false, settingGroup).withDisplayName("Rotate in " + str));
         BooleanValue booleanValue9 = (BooleanValue)new BooleanValue(this, guiMoveScreenOption.getName() + "RotateOnlyAtClick", false, settingGroup).withDisplayName("Rotate only while click in " + str);
         BooleanValue rotateSetting = GuiMoveScreenOption.getRotateSetting(guiMoveScreenOption);
         GuiMoveScreenOption.setRotateOnlyAtClickSetting(guiMoveScreenOption, (BooleanValue)booleanValue9.withVisibility(() -> rotateSetting.getValue()));
         GuiMoveScreenOption.setMoveOnlyOnGroundSetting(guiMoveScreenOption, (BooleanValue)new BooleanValue(this, guiMoveScreenOption.getName() + "MoveOnlyOnGround", false, settingGroup).withDisplayName("Move only on ground in " + str));
         GuiMoveScreenOption.setSneakMotionWhileMovingSetting(guiMoveScreenOption, (BooleanValue)new BooleanValue(this, guiMoveScreenOption.getName() + "SneakMotionWhileMovement", false, settingGroup).withDisplayName("Sneak motion while moving in " + str));
         StringValue setting = (StringValue)new StringValue(
            this, guiMoveScreenOption.getName() + "Sprint mode", "None", new String[]{"None", "Legit", "AllDirection", "AllDirectionMotion"}, settingGroup
         ).withDisplayName("Sprint mode");
         GuiMoveScreenOption.setSprintModeSetting(guiMoveScreenOption, setting);
         GuiMoveScreenOption.setSprintJumpBoostSetting(guiMoveScreenOption, (BooleanValue)new BooleanValue(this, guiMoveScreenOption.getName() + "Jump sprint", false, settingGroup).withDisplayName("Sprint jump boost motion"));
         GuiMoveScreenOption.setForwardMotionMultiplierSetting(guiMoveScreenOption, new DoubleValue(this, guiMoveScreenOption.getName() + "MotionMultiplierForward", 1.0, 0.0, 2.0, 2, settingGroup).named("Motion multiplier forward in " + str));
         GuiMoveScreenOption.setDiagonalMotionMultiplierSetting(guiMoveScreenOption, new DoubleValue(this, guiMoveScreenOption.getName() + "MotionMultiplierDiagonal", 1.0, 0.0, 2.0, 2, settingGroup).named("Motion multiplier diagonal in " + str));
      }
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(36);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(33);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.activeScreenOption == GuiMoveScreenOption.OTHER_SCREENS) {
         GuiMoveScreenOption.getRotateOnlyAtClickSetting(this.activeScreenOption).setValue(true);
      }

      if (this.getMode().equals("Resprint") && this.canMoveInGui()) {
if (this.isValidKeyCode(mc.gameSettings.keyBindForward.getKeyCode())) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), this.isKeyDownSafe(mc.gameSettings.keyBindForward.getKeyCode()));
         }

         if (this.isValidKeyCode(mc.gameSettings.keyBindBack.getKeyCode())) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), this.isKeyDownSafe(mc.gameSettings.keyBindBack.getKeyCode()));
         }

         if (this.isValidKeyCode(mc.gameSettings.keyBindRight.getKeyCode())) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindRight.getKeyCode(), this.isKeyDownSafe(mc.gameSettings.keyBindRight.getKeyCode()));
         }

         if (this.isValidKeyCode(mc.gameSettings.keyBindLeft.getKeyCode())) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindLeft.getKeyCode(), this.isKeyDownSafe(mc.gameSettings.keyBindLeft.getKeyCode()));
         }

         if (this.isSneakEnabled()) {
            if (this.isValidKeyCode(mc.gameSettings.keyBindSneak.getKeyCode())) {
               KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), this.isKeyDownSafe(mc.gameSettings.keyBindSneak.getKeyCode()));
            }
         }
if (this.isJumpEnabled() && this.isValidKeyCode(mc.gameSettings.keyBindJump.getKeyCode())) {
KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), this.isKeyDownSafe(mc.gameSettings.keyBindJump.getKeyCode()));
} else if (this.isValidKeyCode(mc.gameSettings.keyBindJump.getKeyCode())) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), false);
         }
}

      if (this.canMoveInGui()) {
         if (this.isSneakMotionWhileMoving()) {
            event.setMoveForward((float)((double)event.getMoveForward() * 0.3));
            event.setMoveStrafe((float)((double)event.getMoveStrafe() * 0.3));
         }
      }

      if (this.canMoveInGui() && this.getMode().equals("Intave")) {
         event.setSneak(true);
      }
   }

   @Override
   public void onPreUpdate() {
      int n;
      this.activeScreenOption = this.resolveScreenOption(mc.currentScreen);
      if (!this.canMoveInGui()) {
         this.timeHelper.reset();
      } else {
         switch (this.getMode()) {
            case "Intave":
               if (!this.timeHelper.reached(300L)) {
                  break;
               }
            case "Basic":
               if (this.isValidKeyCode(mc.gameSettings.keyBindForward.getKeyCode())) {
                  KeyBinding keyBindForward = mc.gameSettings.keyBindForward;
                  n = mc.gameSettings.keyBindForward.getKeyCode();
                  keyBindForward.setPressed(this.isKeyDownSafe(n));
               }

               if (this.isValidKeyCode(mc.gameSettings.keyBindBack.getKeyCode())) {
                  KeyBinding keyBindBack = mc.gameSettings.keyBindBack;
                  n = mc.gameSettings.keyBindBack.getKeyCode();
                  keyBindBack.setPressed(this.isKeyDownSafe(n));
               }

               if (this.isValidKeyCode(mc.gameSettings.keyBindRight.getKeyCode())) {
                  KeyBinding keyBindRight = mc.gameSettings.keyBindRight;
                  n = mc.gameSettings.keyBindRight.getKeyCode();
                  keyBindRight.setPressed(this.isKeyDownSafe(n));
               }

               if (this.isValidKeyCode(mc.gameSettings.keyBindLeft.getKeyCode())) {
                  KeyBinding keyBindLeft = mc.gameSettings.keyBindLeft;
                  n = mc.gameSettings.keyBindLeft.getKeyCode();
                  keyBindLeft.setPressed(this.isKeyDownSafe(n));
               }

               if (this.isValidKeyCode(mc.gameSettings.keyBindSprint.getKeyCode())) {
                  KeyBinding keyBindSprint = mc.gameSettings.keyBindSprint;
                  n = mc.gameSettings.keyBindSprint.getKeyCode();
                  keyBindSprint.setPressed(this.isKeyDownSafe(n));
               }

               if (this.isValidKeyCode(mc.gameSettings.keyBindJump.getKeyCode())) {
                  if (this.isJumpEnabled()) {
                     KeyBinding keyBindJump = mc.gameSettings.keyBindJump;
                     n = mc.gameSettings.keyBindJump.getKeyCode();
                     keyBindJump.setPressed(this.isKeyDownSafe(n));
                  } else {
                     mc.gameSettings.keyBindJump.setPressed(false);
                  }
               }

               if (this.isSneakEnabled()) {
                  if (this.isValidKeyCode(mc.gameSettings.keyBindSneak.getKeyCode())) {
                     KeyBinding keyBindSneak = mc.gameSettings.keyBindSneak;
                     n = mc.gameSettings.keyBindSneak.getKeyCode();
                     keyBindSneak.setPressed(this.isKeyDownSafe(n));
                  }
               }
         }
      }
   }

   private boolean isValidKeyCode(int n) {
      return n > 0 && n < 256;
   }

   private boolean isKeyDownSafe(int n) {

      try {
         return KeyboardInputUtil.isKeyDown(n);
      } catch (Exception e) {
         return false;
      }
   }

   @Override
   public void onSprint(EventSprint event) {
      if (this.canMoveInGui()) {
         event.setSprintKeyDown(!this.getSprintMode().equals("None"));
         event.setForceSprint(this.getSprintMode().equals("AllDirection") && (!this.getMode().equals("Resprint") || mc.previousScreen != null));
      }
   }

   @Override
   public void onJumpSprint(EventSprint event) {
      if (this.canMoveInGui()) {
         event.setOmniJumpSprint(this.isSprintJumpBoostEnabled() && MoveUtil.isMoving());
      }
   }

   @Override
   public void onMotionSprint(EventSprint event) {
      if (this.canMoveInGui()) {
         event.setMotionSprint(this.getSprintMode().equals("AllDirectionMotion") && (!this.getMode().equals("Resprint") || mc.previousScreen != null));
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C16PacketClientStatus && this.noInvPacket.getValue()) {
         if (((C16PacketClientStatus)packet).getStatus() == C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT) {
            event.cancel();
         }
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (this.canMoveInGui() && mc.currentScreen != null) {
         double d;
         if (RotationUtil.isNearCardinalYaw(mc.thePlayer.rotationYaw)) {
            d = this.getForwardMotionMultiplier();
         } else {
            d = this.getDiagonalMotionMultiplier();
         }

         double d2 = d;
         if (this.getMode().equals("Intave")) {
            if (this.timeHelper.reached(300L) && mc.thePlayer.onGround) {
               d2 = RotationUtil.isNearCardinalYaw((float)Math.toDegrees(MoveUtil.getDirection(mc.thePlayer.rotationYaw))) ? 2.72 : 1.0;
            }
         }

         event.setX(event.getX() * d2);
         event.setZ(event.getZ() * d2);
      }
   }

   @Override
   public void onPreSafeWalk(EventSafeWalk event) {
      if (this.canMoveInGui() && this.getMode().equals("Intave")) {
         event.setDisableSneakSafeWalk(true);
      }
   }

   public boolean canRotateInGui() {
      return this.isRotateEnabled() && this.canMoveInGui();
   }

   public boolean canMoveInGui() {
      return mc.currentScreen != null
         && (mc.thePlayer != null && mc.thePlayer.onGround || !this.isMoveOnlyOnGround())
         && (
            !GuiScreen.NO_TOGGLE_SCREENS.contains(mc.currentScreen) && !(mc.currentScreen instanceof ClickGuiScreen)
               || mc.currentScreen instanceof ClickGuiScreen && !TextFieldComponent.anyFieldFocused
         )
         && this.activeScreenOption != null
         && this.activeScreenOption.isSelected();
   }

   private GuiMoveScreenOption resolveScreenOption(GuiScreen screen) {
      return screen == null
         ? null
         : (
            screen instanceof GuiInventory
               ? GuiMoveScreenOption.INVENTORY
               : (screen instanceof GuiContainer ? GuiMoveScreenOption.CONTAINER : (screen instanceof GuiGameOver ? GuiMoveScreenOption.DEATH_SCREEN : GuiMoveScreenOption.OTHER_SCREENS))
         );
   }

   private boolean isMoveOnlyOnGround() {
      return this.activeScreenOption != null && GuiMoveScreenOption.getMoveOnlyOnGroundSetting(this.activeScreenOption).getValue();
   }

   private String getMode() {
      return this.activeScreenOption == null ? "" : GuiMoveScreenOption.getModeSetting(this.activeScreenOption).getSelectedValue();
   }

   private boolean isSneakEnabled() {
      return this.activeScreenOption != null && GuiMoveScreenOption.getSneakSetting(this.activeScreenOption).getValue();
   }

   private boolean isJumpEnabled() {
      return this.activeScreenOption != null && GuiMoveScreenOption.getJumpSetting(this.activeScreenOption).getValue();
   }

   private boolean isRotateEnabled() {
      return this.activeScreenOption != null && GuiMoveScreenOption.getRotateSetting(this.activeScreenOption).getValue();
   }

   public boolean isRotateOnlyWhileClicking() {
      return this.activeScreenOption != null && GuiMoveScreenOption.getRotateOnlyAtClickSetting(this.activeScreenOption).getValue();
   }

   private boolean isSneakMotionWhileMoving() {
      return this.activeScreenOption != null && GuiMoveScreenOption.getSneakMotionWhileMovingSetting(this.activeScreenOption).getValue();
   }

   private String getSprintMode() {
      return this.activeScreenOption == null ? "" : GuiMoveScreenOption.getSprintModeSetting(this.activeScreenOption).getSelectedValue();
   }

   private boolean isSprintJumpBoostEnabled() {
      return this.activeScreenOption != null && GuiMoveScreenOption.getSprintJumpBoostSetting(this.activeScreenOption).getValue();
   }

   private double getForwardMotionMultiplier() {
      return this.activeScreenOption == null ? 1.0 : GuiMoveScreenOption.getForwardMotionMultiplierSetting(this.activeScreenOption).getValue();
   }

   private double getDiagonalMotionMultiplier() {
      return this.activeScreenOption == null ? 1.0 : GuiMoveScreenOption.getDiagonalMotionMultiplierSetting(this.activeScreenOption).getValue();
   }


}
