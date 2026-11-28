package net.augustus.modules.fun;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.ClientManagers;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.settings.ModeSetting;
import net.augustus.settings.TextInputSetting;
import net.augustus.settings.DynamicSettingList;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.modules.modes.spammer.RandomAlphanumericSpammerMode;
import net.augustus.modules.modes.spammer.SpammerRandomMode;
import net.augustus.modules.modes.spammer.RandomNumbersSpammerMode;
import net.augustus.commands.CommandManager;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.BooleanValue;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.network.NetworkPlayerInfo;

public class Spammer extends Module implements TickListener {
   private final RandomizedRangeSettings tickDelay;
   private final ModeSetting randomMode;
   private final BooleanValue stopSpammingIfYouAreAlone;
   private final BooleanValue inInventory;
   public final DynamicSettingList messages;
   private int tickCounter;
   private int blockedCommandAttempts;
   private Set usedPlayerNames;

   public Spammer() {
       super("Spammer", "Vollmüller", new Color(77, 112, 107), Categorys.FUN);
      this.tickDelay = (new RandomizedRangeSettings(this, "TickDelay", 20.0, 40.0, 0.0, 200.0, 0)).withoutNotInARow();
      BiFunction[] biFunctions = new BiFunction[]{RandomNumbersSpammerMode.factory(), RandomAlphanumericSpammerMode.factory()};
      this.randomMode = new ModeSetting(this, "RandomMode", "RandomAlphanumeric", biFunctions);
      this.stopSpammingIfYouAreAlone = new BooleanValue(this, "Stop spamming if you are alone", true);
      this.inInventory = new BooleanValue(this, "In inventory", false);
      this.messages = new DynamicSettingList(this, "Messages", n -> this.createMessageSetting(n), 1, 50).withInitialCount((byte)1);
      this.tickCounter = 0;
      this.blockedCommandAttempts = 0;
      this.usedPlayerNames = new HashSet();
   }

   @Override
   public void onEnable() {
      this.tickDelay.nextValue();
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onPreTick() {
      this.tickCounter++;
      if (mc.thePlayer != null
         && !mc.thePlayer.isDead
         && !(mc.currentScreen instanceof GuiGameOver)
         && mc.theWorld != null
         && mc.thePlayer.ticksExisted > 10
         && this.canSendInCurrentScreen()
         && this.tickCounter > this.tickDelay.getLastIntValue()
         && (!this.isPlayerListEmpty() || !this.stopSpammingIfYouAreAlone.getValue())) {
         this.tickCounter = 0;
         SpammerRandomMode abstractSubMode = (SpammerRandomMode)this.randomMode.getAbstractSubMode();
         if (abstractSubMode != null) {
            this.sendNextMessage(abstractSubMode.generateRandomSuffix());
         }

         this.tickDelay.nextValue();
      }
   }

   private void sendNextMessage(String str) {
      if (this.messages.getEntryCount() != 0) {
         TextInputSetting textInputSetting = (TextInputSetting)this.messages.nextEntry();
         if (textInputSetting != null) {
            this.blockedCommandAttempts = 0;
            String message = this.replacePlaceholders(textInputSetting.getValue(), str);
            mc.thePlayer.sendChatMessage(message);
         }
      }
   }

   private String replacePlaceholders(String str, String str2) {
      str = str.replace("#player", this.pickRandomPlayerName());
      return str.replace("#random", str2);
   }

   private String pickRandomPlayerName() {
      Collection values = mc.getNetHandler().getPlayerInfoMap2().values();
      Collection items = (Collection)values.stream().filter(obj -> {
         return obj != null && ((NetworkPlayerInfo)obj).getGameProfile() != null && !this.usedPlayerNames.contains(((NetworkPlayerInfo)obj).getGameProfile().getName());
      }).collect(Collectors.toList());
      if (items.isEmpty() && !values.isEmpty()) {
         items = values;
      }

      int n2 = DefaultRandomGenerator.instance.nextInt((byte)0, items.size() - 1);
      int n3 = 0;
      String str = "";

      for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(values)) {
         if (n3 == n2) {
            str = networkPlayerInfo.getGameProfile().getName();
            break;
         }

         n3++;
      }

      return str;
   }

   private boolean isPlayerListEmpty() {
      return mc.getNetHandler().getPlayerInfoMap().isEmpty();
   }

   private boolean canSendInCurrentScreen() {
      return this.inInventory.getValue() || mc.isNotInGui();
   }

   private TextInputSetting createMessageSetting(int n) {
      return new TextInputSetting(this, "Message_" + n, "Augustus on top! #player #random");
   }





}
