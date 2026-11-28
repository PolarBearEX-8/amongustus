package net.augustus.modules.misc;

import net.minecraft.network.play.client.C03PacketPlayer;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.EventKey;
import net.augustus.ClientManagers;
import net.augustus.settings.KeybindSetting;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.events.listeners.KeyListener;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.settings.options.AutoLeaveAfkConditionOption;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.events.EventMoveInput;
import net.augustus.render.RenderUtil;
import net.augustus.Augustus;
import net.augustus.events.listeners.SoundListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.EventSound;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.Packet;

public class AutoLeave extends Module implements KeyListener, RenderListener, LoadWorldListener, TickListener, SoundListener, MoveInputListener, PacketListener {
   public final BooleanValue calculateWithHit;
   public final DoubleValue time;
   public final BooleanValue displayTime;
   public final DoubleValue xCoord;
   public final DoubleValue yCoord;
   public final BooleanValue autoLeaveIfHeatsLow;
   public final DoubleValue hearts;
   public final BooleanValue leaveOnDeath;
   private final BooleanValue leaveIfAfk;
   private final MultiOptionSetting afkConditions;
   private final DoubleValue afktimerS;
   private final BooleanValue resetAfkTimerOnWorldchange;
   public final BooleanValue leaveIfStaffArrives;
   public final BooleanValue leaveIfInLava;
   public final BooleanValue leaveIfInWater;
   public final BooleanValue leaveOnKey;
   public final KeybindSetting key;
   public final StringValue mode;
   private final TimeHelper movementAfkTimer;
   private final TimeHelper rotationAfkTimer;
   private final TimeHelper otherKeyAfkTimer;
   private boolean leftDueToAfk;
   private long lastCombatHitTimeMs;
   private final TimeHelper fallDamageSoundTimer;
   private boolean wasDead;
   private final TimeHelper staffLeaveCooldownTimer;

   public AutoLeave() {
       super("AutoLeave", "AutomatischesVerlassen", new Color(50, 168, 82), Categorys.MISC);
      this.calculateWithHit = (BooleanValue)(new BooleanValue(this, "CalculateWithHit", true)).markVisualOnly();
      DoubleValue timeSetting = new DoubleValue(this, "Time", 12000.0, 0.0, 20000.0, 0);
      BooleanValue calculateWithHit2 = this.calculateWithHit;
      BooleanValue booleanValue = calculateWithHit2;
      this.time = (DoubleValue)timeSetting.visibleWhen(() -> booleanValue.getValue()).markVisualOnly();
      BooleanValue booleanValue2 = new BooleanValue(this, "DisplayTime", true);
      calculateWithHit2 = this.calculateWithHit;
      BooleanValue booleanValue3 = calculateWithHit2;
      this.displayTime = (BooleanValue)((BooleanValue)booleanValue2.withVisibility(() -> booleanValue3.getValue())).markVisualOnly();
      this.xCoord = (DoubleValue)new DoubleValue(this, "X-Coord", 100.0, 0.0, 100.0, 2).visibleWhen(() -> this.isTimerXCoordVisible()).markVisualOnly();
      this.yCoord = (DoubleValue)new DoubleValue(this, "Y-Coord", 100.0, 0.0, 100.0, 2).visibleWhen(() -> this.isTimerYCoordVisible()).markVisualOnly();
      this.autoLeaveIfHeatsLow = (BooleanValue)((BooleanValue)new BooleanValue(this, "AutoLeaveIfHeatsLow", false).withDisplayName("AutoLeaveIfHeatsLow")).markVisualOnly();
      DoubleValue heartsSetting = new DoubleValue(this, "Hearts", 2.0, 0.0, 20.0, 0);
      calculateWithHit2 = this.autoLeaveIfHeatsLow;
      BooleanValue booleanValue5 = calculateWithHit2;
      this.hearts = (DoubleValue)heartsSetting.visibleWhen(() -> booleanValue5.getValue()).markVisualOnly();
      this.leaveOnDeath = (BooleanValue)(new BooleanValue(this, "LeaveOnDeath", false)).markVisualOnly();
      this.leaveIfAfk = (BooleanValue)(new BooleanValue(this, "LeaveIfAfk", false)).markVisualOnly();
      MultiOptionSetting multiOptionSetting = new MultiOptionSetting(this, "AfkConditions", AutoLeaveAfkConditionOption.values());
      calculateWithHit2 = this.leaveIfAfk;
      BooleanValue booleanValue6 = calculateWithHit2;
      this.afkConditions = (MultiOptionSetting)multiOptionSetting.visibleWhen(() -> booleanValue6.getValue()).markVisualOnly();
      DoubleValue afkTimerSSetting = new DoubleValue(this, "AfkTimer (s)", 30.0, 0.0, 120.0, 0);
      calculateWithHit2 = this.leaveIfAfk;
      BooleanValue booleanValue7 = calculateWithHit2;
      this.afktimerS = (DoubleValue)afkTimerSSetting.visibleWhen(() -> booleanValue7.getValue()).markVisualOnly();
      BooleanValue booleanValue8 = new BooleanValue(this, "Reset AfkTimer on worldchange", false);
      calculateWithHit2 = this.leaveIfAfk;
      BooleanValue booleanValue9 = calculateWithHit2;
      this.resetAfkTimerOnWorldchange = (BooleanValue)((BooleanValue)booleanValue8.withVisibility(() -> booleanValue9.getValue())).markVisualOnly();
      this.leaveIfStaffArrives = (BooleanValue)((BooleanValue)new BooleanValue(this, "LeaveIfStaffArrives", false).withVisibility(() -> isStaffDetectorEnabled())).markVisualOnly();
      this.leaveIfInLava = (BooleanValue)(new BooleanValue(this, "LeaveIfInLava", false)).markVisualOnly();
      this.leaveIfInWater = (BooleanValue)(new BooleanValue(this, "LeaveIfInWater", false)).markVisualOnly();
      this.leaveOnKey = (BooleanValue)(new BooleanValue(this, "LeaveOnKey", false)).markVisualOnly();
      KeybindSetting keybindSetting = new KeybindSetting(this, "Key");
      calculateWithHit2 = this.leaveOnKey;
      BooleanValue booleanValue11 = calculateWithHit2;
      this.key = (KeybindSetting)keybindSetting.visibleWhen(() -> booleanValue11.getValue()).markVisualOnly();
      this.mode = (StringValue)(new StringValue(this, "Mode", "Lobby", new String[]{"Lobby", "Server"})).markVisualOnly();
      this.movementAfkTimer = new TimeHelper();
      this.rotationAfkTimer = new TimeHelper();
      this.otherKeyAfkTimer = new TimeHelper();
      this.leftDueToAfk = false;
      this.lastCombatHitTimeMs = System.currentTimeMillis();
      this.fallDamageSoundTimer = new TimeHelper();
      this.wasDead = false;
      this.staffLeaveCooldownTimer = new TimeHelper();
   }

   @Override
   public void onEnable() {
      this.wasDead = false;
      this.leftDueToAfk = false;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(14);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(17);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(35);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(26);
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (this.calculateWithHit.getValue() && this.displayTime.getValue()) {
         double d = (double)((int)(System.currentTimeMillis() - this.lastCombatHitTimeMs));
         String str = NumberFormatUtil.formatDecimal(d / 1000.0, 1);
         FontRenderer fontRendererObj = mc.fontRendererObj;
         int stringWidth = fontRendererObj.getStringWidth(str);
         int n = fontRendererObj.FONT_HEIGHT - 1;
         fontRendererObj.drawString(str, (int)((double)(sr.getScaledWidth() - stringWidth) / 100.0 * this.xCoord.getValue()), (int)((double)(sr.getScaledHeight() - n) / 100.0 * this.yCoord.getValue()), d > this.time.getValue() ? Color.GREEN.getRGB() : Color.RED.getRGB());
      }

      RenderUtil.resetGlColorState();
   }

   @Override
   public void onSound(EventSound event) {
      if (MathStatisticsUtil.isWithinTolerance(event.getX(), mc.thePlayer.posX, 0.01)
         && MathStatisticsUtil.isWithinTolerance(event.getY(), mc.thePlayer.posY, 0.01)
         && MathStatisticsUtil.isWithinTolerance(event.getZ(), mc.thePlayer.posZ, 0.01)) {
         if (event.getSoundName().equals("game.player.hurt.fall.small") || event.getSoundName().equals("game.player.hurt.fall.big")) {
            this.fallDamageSoundTimer.reset();
         }

         if (event.getSoundName().equals("game.player.hurt")) {
            if (this.fallDamageSoundTimer.reached(100L)) {
               this.lastCombatHitTimeMs = System.currentTimeMillis();
            }
         }
      }
   }

   @Override
   public void onEarlyTick() {
      if (this.autoLeaveIfHeatsLow.getValue() && (double)mc.thePlayer.getCurrentHealth() <= this.hearts.getValue()) {
         this.tryLeaveIfCombatSafe();
      }

      if (this.leaveIfInLava.getValue() && mc.thePlayer.isInLava()) {
         this.tryLeaveIfCombatSafe();
      }

      if (this.leaveIfInWater.getValue() && mc.thePlayer.isInWater()) {
         this.tryLeaveIfCombatSafe();
      }

      if (this.leaveIfStaffArrives.getValue() && ClientManagers.moduleManager.staffDetector.isToggled() && ClientManagers.moduleManager.staffDetector.hasConfirmedStaff()) {
         if (this.staffLeaveCooldownTimer.reached(1000L)) {
            this.executeLeave();
            this.staffLeaveCooldownTimer.reset();
         }
      }

      if (mc.thePlayer.isDead && !this.wasDead && this.leaveOnDeath.getValue()) {
         this.executeLeave();
      }

      if (this.leaveIfAfk.getValue()) {
         long afkTimeoutMs = (long)((int)this.afktimerS.getValue() * 1000);
         boolean isAfk = (AutoLeaveAfkConditionOption.isOptionSelected(AutoLeaveAfkConditionOption.ROTATIONS) && this.rotationAfkTimer.reached(afkTimeoutMs))
            || (AutoLeaveAfkConditionOption.isOptionSelected(AutoLeaveAfkConditionOption.MOVEMENT) && this.movementAfkTimer.reached(afkTimeoutMs))
            || (AutoLeaveAfkConditionOption.isOptionSelected(AutoLeaveAfkConditionOption.OTHER_KEY_INPUTS) && this.otherKeyAfkTimer.reached(afkTimeoutMs));
         if (isAfk) {
            if (!this.leftDueToAfk) {
               this.executeLeave();
               this.leftDueToAfk = true;
            }
         } else {
            this.leftDueToAfk = false;
         }
      }

      this.wasDead = mc.thePlayer.isDead;
   }

   @Override
   public void onToggleKey(EventKey event) {
      int keyCode = event.getKeyCode();
      if (this.leaveOnKey.getValue() && keyCode == this.key.getKeyCode()) {
         this.tryLeaveIfCombatSafe();
      }

      if (keyCode != mc.gameSettings.keyBindForward.getKeyCode()
         && keyCode != mc.gameSettings.keyBindBack.getKeyCode()
         && keyCode != mc.gameSettings.keyBindLeft.getKeyCode()
         && keyCode != mc.gameSettings.keyBindRight.getKeyCode()
         && keyCode != mc.gameSettings.keyBindJump.getKeyCode()
         && keyCode != mc.gameSettings.keyBindSneak.getKeyCode()) {
         this.otherKeyAfkTimer.reset();
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C03PacketPlayer.C05PacketPlayerLook || packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
         this.rotationAfkTimer.reset();
      }
   }

   @Override
   public void onPostMoveInput(EventMoveInput event) {
      if (event.getMoveForward() != 0.0F || event.getMoveStrafe() != 0.0F || event.isSneak() || event.isJump()) {
         this.movementAfkTimer.reset();
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.lastCombatHitTimeMs = System.currentTimeMillis();
      if (this.resetAfkTimerOnWorldchange.getValue()) {
         this.rotationAfkTimer.reset();
         this.movementAfkTimer.reset();
         this.otherKeyAfkTimer.reset();
      }
   }

   private void tryLeaveIfCombatSafe() {
      if ((double)((int)(System.currentTimeMillis() - this.lastCombatHitTimeMs)) > this.time.getValue() || !this.calculateWithHit.getValue()) {
         this.executeLeave();
         this.lastCombatHitTimeMs = System.currentTimeMillis();
      }
   }

   private void executeLeave() {
      switch (this.mode.getSelectedValue()) {
         case "Lobby":
            mc.thePlayer.sendChatMessage("/hub");
            break;
         case "Server":
            mc.isGamePaused = true;
            boolean flag2 = mc.isIntegratedServerRunning();
            mc.theWorld.sendQuittingDisconnectingPacket();
            mc.loadWorld((WorldClient)null);
            if (flag2) {
               mc.displayGuiScreen(new GuiMainMenu());
            } else {
               mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
            }
      }
   }

   private static boolean isStaffDetectorEnabled() {
      return Augustus.getInstance().getModuleManager().staffDetector.isToggled();
   }

   private boolean isTimerYCoordVisible() {
      return this.calculateWithHit.getValue() && this.displayTime.getValue();
   }

   private boolean isTimerXCoordVisible() {
      return this.calculateWithHit.getValue() && this.displayTime.getValue();
   }


}
