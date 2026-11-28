package net.augustus.modules.misc;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S40PacketDisconnect;
import org.lwjgl.input.Mouse;

public class AutoPlay extends Module implements LoadWorldListener, ClickListener, TickListener, RenderListener, PacketListener, SilentSlotListener {
   private final StringValue mode;
   private final StringValue gamemodeMMC;
   private final StringValue gamemodeKit1v1;
   private final StringValue gamemodeGamster;
   public final BooleanValue removeEscape;
   public final BooleanValue cancelSound;
   private final BooleanValue silentSlotSwitching;
   private final DoubleValue hotbarItemID;
   private final DoubleValue chestslot;
   private final BooleanValue setSlotAfterRespawn;
   private final RandomizedRangeSettings delayToSetSlowAfterRespawn;
   private final DoubleValue slotToSetAfterRespawn;
   private final TimeHelper chestClickTimer;
   private final TimeHelper pikaPlayAgainTimer;
   private final TimeHelper itemUseTimer;
   private int ticksSinceRespawn;
   private float savedMasterVolume;
   public float[] savedRotations;
   private int randomChestClickDelay;
   private boolean autoGambleCompleted;
   private final HashMap gamsterPlayCommands;

   public AutoPlay() {
       super("AutoPlay", "AutoSpielen", Color.green, Categorys.MISC);
      this.mode = (StringValue)(new StringValue(
            this,
            "Mode",
            "MinemenRanked",
            new String[]{
               "MinemenUnranked",
               "MinemenRanked",
               "PikaSkywars",
               "HypixelNormal",
               "HypixelInsane",
               "GommeKit1v1Ranked",
               "GommeKit1v1Unranked",
               "Kitpvp",
               "PvpDojo",
               "UniversocraftSkywars",
               "AutoGamblePVPDojo",
               "Gamster"
            }
         ))
         .markVisualOnly();
      this.gamemodeMMC = (StringValue)((StringValue)((StringValue)new StringValue(this, "GamemodeMMC", "Sumo", new String[]{"Sumo", "Boxing", "Parkour"}).withDisplayName("Gamemode")).withVisibility(() -> this.isMinemenModeSelected())).markVisualOnly();
      this.gamemodeKit1v1 = (StringValue)((StringValue)((StringValue)new StringValue(this, "GamemodeKit1v1", "OnlySword", new String[]{"UHC", "OnlySword", "Soup", "SG", "SW", "QSG"}).withDisplayName("Gamemode")).withVisibility(() -> this.isGommeKit1v1Selected())).markVisualOnly();
      StringValue setting = new StringValue(
         this,
         "GamemodeGamster",
         "SkyWarsSolo",
         new String[]{"SkyWarsSolo", "SkyWarsTeams", "SkyWarsLucky", "BedWarsSolo", "BedWarsDuos", "BedWarsTrios", "BedWarsTeams"}
      );
      this.gamemodeGamster = (StringValue)((StringValue)setting.withVisibility(() -> this.isGamsterModeSelected())).markVisualOnly();
      this.removeEscape = (BooleanValue)(new BooleanValue(this, "RemoveEscape", false)).markVisualOnly();
      this.cancelSound = (BooleanValue)(new BooleanValue(this, "CancelSound", false)).markVisualOnly();
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      this.hotbarItemID = (DoubleValue)(new DoubleValue(this, "HotbarItemID", 54.0, 0.0, 2267.0, 0)).markVisualOnly();
      this.chestslot = (DoubleValue)new DoubleValue(this, "Chestslot", 0.0, 0.0, 54.0, 0).visibleWhen(() -> this.isChestSlotVisible()).markVisualOnly();
      this.setSlotAfterRespawn = (BooleanValue)(new BooleanValue(this, "SetSlotAfterRespawn", false)).markVisualOnly();
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "DelayToSetSlowAfterRespawn", 5.0, 10.0, 0.0, 20.0, 0)).withoutNotInARow();
      BooleanValue setSlotAfterRespawn2 = this.setSlotAfterRespawn;
      BooleanValue booleanValue = setSlotAfterRespawn2;
      this.delayToSetSlowAfterRespawn = (RandomizedRangeSettings)((RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> booleanValue.getValue())).markAllVisualOnly();
      DoubleValue slotToSetAfterRespawnSetting = new DoubleValue(this, "SlotToSetAfterRespawn", 0.0, 0.0, 8.0, 0);
      setSlotAfterRespawn2 = this.setSlotAfterRespawn;
      BooleanValue booleanValue2 = setSlotAfterRespawn2;
      this.slotToSetAfterRespawn = (DoubleValue)slotToSetAfterRespawnSetting.visibleWhen(() -> booleanValue2.getValue()).markVisualOnly();
      this.chestClickTimer = new TimeHelper();
      this.pikaPlayAgainTimer = new TimeHelper();
      this.itemUseTimer = new TimeHelper();
      this.ticksSinceRespawn = 0;
      this.savedRotations = new float[2];
      this.randomChestClickDelay = 0;
      this.autoGambleCompleted = false;
      this.gamsterPlayCommands = new HashMap();
      this.gamsterPlayCommands.put("SkyWarsSolo", "/play sw-solo");
      this.gamsterPlayCommands.put("SkyWarsTeams", "/play sw-teams");
      this.gamsterPlayCommands.put("SkyWarsLucky", "/play sw-lucky");
      this.gamsterPlayCommands.put("BedWarsSolo", "/play bw-solo");
      this.gamsterPlayCommands.put("BedWarsDuos", "/play bw-duo");
      this.gamsterPlayCommands.put("BedWarsTrios", "/play bw-trio");
      this.gamsterPlayCommands.put("BedWarsTeams", "/play bw-teams");
   }

   @Override
   public void onEnable() {
      this.savedMasterVolume = mc.gameSettings.getSoundLevel(SoundCategory.MASTER);
      if (this.cancelSound.getValue()) {
         mc.gameSettings.setSoundLevel(SoundCategory.MASTER, 0.0F);
      }

      this.autoGambleCompleted = false;
   }

   @Override
   public void onDisable() {
      mc.gameSettings.setSoundLevel(SoundCategory.MASTER, this.savedMasterVolume);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (mc.currentScreen instanceof ClickGuiScreen && Mouse.isGrabbed()) {
         Mouse.setGrabbed(false);
      }

      if (mc.currentScreen instanceof GuiIngameMenu && this.removeEscape.getValue()) {
         mc.currentScreen = (GuiScreen)null;
         mc.thePlayer.setSpoofedScreen(null);
         mc.inGameHasFocus = true;
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.autoGambleCompleted = false;
      this.delayToSetSlowAfterRespawn.nextValue();
      this.ticksSinceRespawn = 0;
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S08PacketPlayerPosLook) {
         S08PacketPlayerPosLook packet2 = (S08PacketPlayerPosLook)packet;
         if (mc.thePlayer.getDistance(packet2.getX(), packet2.getY(), packet2.getZ()) > 30.0) {
            this.delayToSetSlowAfterRespawn.nextValue();
            this.ticksSinceRespawn = 0;
         }
      }
   }

   @Override
   public void onPacketReceiveNetty(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S40PacketDisconnect) {
         mc.mouseHelper.ungrabMouseCursor();
      }

      if (packet instanceof S02PacketChat) {
         String unformattedText = ((S02PacketChat)packet).getChatComponent().getUnformattedText();
         if (this.mode.getSelectedValue().equals("HypixelNormal")) {
            if (unformattedText.contains("You won! Want to play again? Click here!")) {
               mc.thePlayer.sendChatMessage("/play solo_normal");
            }

            if (unformattedText.contains("You died!")) {
               mc.thePlayer.sendChatMessage("/play solo_normal");
            }
         }

         if (this.mode.getSelectedValue().equals("HypixelInsane")) {
            if (unformattedText.contains("You won! Want to play again? Click here!")) {
               mc.thePlayer.sendChatMessage("/play solo_insane");
            }

            if (unformattedText.contains("You died!")) {
               mc.thePlayer.sendChatMessage("/play solo_insane");
            }
         }

         if (this.mode.getSelectedValue().equals("UniversocraftSkywars") && unformattedText.contains("Jugar de nuevo - Salir")) {
            mc.thePlayer.sendChatMessage("/skywars random");
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (mc.isNotInGui()) {
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(mc.thePlayer.inventory.currentItem + 36).getStack();
         if (stack != null) {
            if (stack.getDisplayName().contains("Unranked") && this.mode.getSelectedValue().equals("MinemenUnranked")) {
               event.addRightClicks((byte)1);
            }

            if (!stack.getDisplayName().contains("Unranked") && stack.getDisplayName().contains("Ranked") && this.mode.getSelectedValue().equals("MinemenRanked")) {
               event.addRightClicks((byte)1);
            }

            if (stack.getDisplayName().contains("Play Again") && this.mode.getSelectedValue().equals("MinemenUnranked")) {
               event.addRightClicks((byte)1);
            }

            if (stack.getDisplayName().contains("Play Again")) {
               if (this.pikaPlayAgainTimer.reached(6000L) && this.mode.getSelectedValue().equals("PikaSkywars")) {
                  event.addRightClicks((byte)1);
                  this.pikaPlayAgainTimer.reset();
               }
            }

            if (this.mode.getSelectedValue().equals("GommeKit1v1Ranked")) {
               if (this.matchesGommeKitItemName(stack.getDisplayName()) || stack.getItem().equals(Items.blaze_rod)) {
                  if (this.itemUseTimer.reached((long)DefaultRandomGenerator.instance.nextInt((short)150, (short)250))) {
                     event.addRightClicks((byte)1);
                     this.itemUseTimer.reset();
                  }
               }
            }

            if (this.mode.getSelectedValue().equals("GommeKit1v1Unranked")) {
               if (this.matchesGommeKitItemName(stack.getDisplayName()) || stack.getItem().equals(Items.blaze_rod)) {
                  if (this.itemUseTimer.reached((long)DefaultRandomGenerator.instance.nextInt((short)150, (short)250))) {
                     event.addLeftClicks((byte)1);
                     this.itemUseTimer.reset();
                  }
               }
            }

            if (this.mode.getSelectedValue().equalsIgnoreCase("kitpvp")) {
               if (this.itemUseTimer.reached((long)DefaultRandomGenerator.instance.nextInt((short)1000, (short)2000)) && Item.getItemById((int)this.hotbarItemID.getValue()) == stack.getItem()) {
                  event.addRightClicks((byte)1);
                  this.itemUseTimer.reset();
               }
            }

            if (this.mode.getSelectedValue().equals("AutoGamblePVPDojo") && !this.autoGambleCompleted && SilentSlotManager.isOwnedBy(this)) {
               if (this.itemUseTimer.reached((long)DefaultRandomGenerator.instance.nextInt((short)1000, (short)2000)) && stack.getDisplayName().contains("Collection")) {
                  event.addRightClicks((byte)1);
                  this.itemUseTimer.reset();
               }
            }
         }
      }
   }

   @Override
   public void onPreSilentSlot() {
      if (mc.isNotInGui() && this.setSlotAfterRespawn.getValue()) {
         if (this.ticksSinceRespawn == Math.max(0, this.delayToSetSlowAfterRespawn.getLastIntValue())) {
            SilentSlotManager.realHotbarSlot = mc.thePlayer.inventory.currentItem = (int)this.slotToSetAfterRespawn.getValue(true);
         }

         this.ticksSinceRespawn++;
      }
   }

   @Override
   public void onPostSilentSlot() {
      if (mc.isNotInGui()) {
         for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
            ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack != null) {
               Item item = stack.getItem();
               String displayName2 = stack.getDisplayName();
               switch (this.mode.getSelectedValue()) {
                  case "MinemenUnranked":
                     if (displayName2.contains("Unranked")) {
                        SilentSlotManager.requestSlot(this, (byte)20, i - 36, this.silentSlotSwitching.getValue());
                     } else if (displayName2.contains("Play Again")) {
                        SilentSlotManager.requestSlot(this, (byte)20, i - 36, this.silentSlotSwitching.getValue());
                     }
                     break;
                  case "MinemenRanked":
                     if (!displayName2.contains("Unranked") && stack.getDisplayName().contains("Ranked")) {
                        SilentSlotManager.requestSlot(this, (byte)20, i - 36, this.silentSlotSwitching.getValue());
                     }
                     break;
                  case "PikaSkywars":
                     if (displayName2.contains("Play Again")) {
                        SilentSlotManager.requestSlot(this, (byte)20, i - 36, this.silentSlotSwitching.getValue());
                     }
                     break;
                  case "GommeKit1v1Ranked":
                  case "GommeKit1v1Unranked":
                     if (this.matchesGommeKitItemName(displayName2)) {
                        SilentSlotManager.requestSlot(this, (byte)20, i - 36, this.silentSlotSwitching.getValue());
                     }

                     if (item.equals(Items.blaze_rod)) {
                        SilentSlotManager.requestSlot(this, (byte)20, i - 36, this.silentSlotSwitching.getValue());
                     }
                     break;
                  case "AutoGamblePVPDojo":
                     if (displayName2.contains("Collection") && !this.autoGambleCompleted) {
                        SilentSlotManager.requestSlot(this, (byte)20, i - 36, this.silentSlotSwitching.getValue());
                     }
                     break;
                  case "Gamster":
                     this.handleGamsterPlayAgainItem(stack, item);
               }
            }
         }
      }
   }

   @Override
   public void onEarlyTick() {
      if (mc.isNotInGui()) {
         this.chestClickTimer.reset();
         this.randomChestClickDelay = DefaultRandomGenerator.instance.nextInt((byte)0, (short)150);
      } else if (mc.currentScreen instanceof GuiChest) {
         long l = (long)(350 + this.randomChestClickDelay);
         if (this.chestClickTimer.reached(l)) {
            GuiChest currentScreen = (GuiChest)mc.currentScreen;
            ItemStack stack = currentScreen.inventorySlots.getSlot(8).getStack();
            boolean flag = false;

            for (int i = 0; i < currentScreen.inventorySlots.inventorySlots.size(); i++) {
               ItemStack stack2 = currentScreen.inventorySlots.getSlot(i).getStack();
               if ((double)i == this.chestslot.getValue() && stack2 != null && this.mode.getSelectedValue().equals("Kitpvp")) {
                  this.clickChestSlot(i);
                  break;
               }

               if (stack2 != null) {
                  boolean flag2 = false;
                  switch (this.mode.getSelectedValue()) {
                     case "MinemenUnranked":
                        if (stack2.getDisplayName().contains(this.gamemodeMMC.getSelectedValue())) {
                           System.out.println(stack2 + " " + this.gamemodeMMC.getSelectedValue());
                           this.clickChestSlot(i);
                           flag2 = true;
                        }
                        break;
                     case "MinemenRanked":
                        if (stack2.getDisplayName().contains(this.gamemodeMMC.getSelectedValue())) {
                           this.clickChestSlot(i);
                           flag2 = true;
                        }
                        break;
                     case "GamsterKitpvp":
                        if (stack2.getItem() == Items.diamond_sword) {
                           this.clickChestSlot(i);
                           flag2 = true;
                        }
                        break;
                     case "PvpDojo":
                        if (stack2.getItem() == Items.stone_sword || stack2.getItem() == Items.nether_star) {
                           this.clickChestSlot(i);
                           flag2 = true;
                        }
                        break;
                     case "AutoGamblePVPDojo":
                        if (stack2.getDisplayName().contains("Collection") && i < 36) {
                           flag = true;
                           this.clickChestSlot(i);
                        }
                  }

                  if (flag2) {
                     break;
                  }
               }
            }

            if (this.mode.getSelectedValue().equals("AutoGamblePVPDojo") && !flag && stack != null && stack.getItem().equals(Items.sign)) {
               this.autoGambleCompleted = true;
            }
         }
      }
   }

   private void handleGamsterPlayAgainItem(ItemStack stack, Item item) {
      if (Items.paper.equals(item) && stack.getDisplayName().contains("Play")) {
         if (stack.getDisplayName().contains("Again")) {
            String str = (String)this.gamsterPlayCommands.get(this.gamemodeGamster.getSelectedValue());
            if (str == null) {
               return;
            }

            mc.thePlayer.sendChatMessage(str);
         }
      }
   }

   private void clickChestSlot(int n) {
      GuiChest currentScreen = (GuiChest)mc.currentScreen;
      Slot slot = currentScreen.inventorySlots.getSlot(n);

      try {
         currentScreen.mouseClicked(slot.xDisplayPosition + 2 + currentScreen.guiLeft, slot.yDisplayPosition + 2 + currentScreen.guiTop, 0);
      } catch (IOException e) {
         e.printStackTrace();
      }

      this.chestClickTimer.reset();
   }

   private boolean matchesGommeKitItemName(String str) {
      switch (this.gamemodeKit1v1.getSelectedValue()) {
         case "UHC":
            return str.equals("§7Kit: §f§eUHC");
         case "OnlySword":
            return str.equals("§7Kit: §f§bOnly Sword");
         case "Soup":
            return str.equals("§7Kit: §f§cSOUP");
         case "SG":
            return str.equals("§7Kit: §f§aSG");
         case "SW":
            return str.equals("§7Kit: §f§4SW");
         case "QSG":
            return str.equals("§6Season-Kit: §f§5QSG");
         default:
            return false;
      }
   }

   public boolean shouldSuppressEscapeMenu() {
      return this.isToggled() && this.removeEscape.getValue();
   }

   private boolean isChestSlotVisible() {
      return this.mode.getSelectedValue().equalsIgnoreCase("Kitpvp");
   }

   private boolean isGamsterModeSelected() {
      return this.mode.getSelectedValue().equals("Gamster");
   }

   private boolean isGommeKit1v1Selected() {
      return this.mode.getSelectedValue().equals("GommeKit1v1Ranked") || this.mode.getSelectedValue().equals("GommeKit1v1Unranked");
   }

   private boolean isMinemenModeSelected() {
      return this.mode.getSelectedValue().equals("MinemenUnranked") || this.mode.getSelectedValue().equals("MinemenRanked");
   }

}
