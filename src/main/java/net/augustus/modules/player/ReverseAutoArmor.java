package net.augustus.modules.player;

import java.awt.Color;
import java.io.IOException;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.settings.RangeSetting;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C16PacketClientStatus;

public class ReverseAutoArmor extends Module implements TickListener {
   private final DoubleValue startDelay;
   public final RangeSetting delay;
   public final BooleanValue drop;
   public final BooleanValue bot;
   private final TimeHelper actionTimer;
   private final TimeHelper inventoryOpenTimer;
   private long nextActionDelayMs;
   private boolean openedByBot;

   public ReverseAutoArmor() {
       super("ReverseAutoArmor", "RüstungAusziehen", new Color(252, 186, 3), Categorys.PLAYER);
      this.startDelay = new DoubleValue(this, "StartDelay", 200.0, 0.0, 400.0, 0);
      this.delay = new RangeSetting(this, "Delay", 70.0, 120.0, 0.0, 400.0, 0);
      this.drop = new BooleanValue(this, "Drop", false);
      this.bot = new BooleanValue(this, "Bot", false);
      this.actionTimer = new TimeHelper();
      this.inventoryOpenTimer = new TimeHelper();
      this.openedByBot = false;
   }

   public void onEnable() {
      if (mc.thePlayer != null && mc.theWorld != null && Augustus.getInstance().getModuleManager() != null && Augustus.getInstance().getModuleManager().invManager.isToggled()) {
         this.sendChatMessage("ReverseAutoArmor only works when InvManager is disabled!");
      }

      this.nextActionDelayMs = (long)DefaultRandomGenerator.instance.nextInt(this.delay.getMinValue(), this.delay.getMaxValue());
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   public void onEarlyTick() {
      if (mc.currentScreen instanceof GuiInventory && !ClientManagers.moduleManager.invManager.isToggled()) {
         if (this.inventoryOpenTimer.reached(this.startDelay.getValue())) {
            boolean flag = true;

            for (int i = 5; i < 9; i++) {
               if (((Slot)mc.thePlayer.inventoryContainer.inventorySlots.get(i)).getStack() != null) {
                  flag = false;
                  long nextActionDelayMs2 = this.nextActionDelayMs;
                  if (this.actionTimer.reached(nextActionDelayMs2)) {
                     this.nextActionDelayMs = (long)DefaultRandomGenerator.instance.nextInt(this.delay.getMinValue(), this.delay.getMaxValue());
                     if (this.drop.getValue()) {
                        mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, i, 1, 4, mc.thePlayer);
                        if (this.nextActionDelayMs != 0L) {
                           return;
                        }
                     } else {
                        GuiInventory currentScreen = (GuiInventory)mc.currentScreen;
                        Slot slot = currentScreen.inventorySlots.getSlot(i);

                        try {
                           currentScreen.forceShiftClick = true;
                           currentScreen.mouseClicked(slot.xDisplayPosition + 2 + currentScreen.guiLeft, slot.yDisplayPosition + 2 + currentScreen.guiTop, 0);
                        } catch (IOException e) {
                           throw new RuntimeException(e);
                        }

                        if (this.nextActionDelayMs != 0L) {
                           return;
                        }
                     }

                     this.actionTimer.reset();
                  }
               }
            }

            if (this.bot.getValue() && flag && this.openedByBot) {
               mc.displayGuiScreen((GuiScreen)null);
               this.openedByBot = false;
            }
         }
      } else {
         this.inventoryOpenTimer.reset();
         if (mc.currentScreen instanceof GuiInventory && this.openedByBot) {
            mc.displayGuiScreen((GuiScreen)null);
            this.openedByBot = false;
         }
      }

      if (this.bot.getValue() && mc.thePlayer.ticksExisted == 10 && mc.isNotInGui()) {
         for (int j = 5; j < 9; j++) {
            if (((Slot)mc.thePlayer.inventoryContainer.inventorySlots.get(j)).getStack() != null) {
               mc.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
               mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
               this.openedByBot = true;
               break;
            }
         }
      }
   }

}
