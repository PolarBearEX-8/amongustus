package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.utils.player.InventoryUtil;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.BooleanValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class AutoEat extends Module implements ClickListener, PacketListener, SilentSlotListener {
   private final TimeHelper timeHelper;
   private int foodHotbarSlot;
   private int previousFoodHotbarSlot;
   private final RandomizedRangeSettings hunger;
   private final BooleanValue useGapple;
   private final BooleanValue silentSlotSwitching;
   private final BooleanValue notWhileKillAura;
   private final BooleanValue notWhileScaffold;

   public AutoEat() {
       super("AutoEat", "AutomatischEssen", new Color(252, 186, 3), Categorys.PLAYER);
      this.timeHelper = new TimeHelper();
      this.foodHotbarSlot = -1;
      this.previousFoodHotbarSlot = -1;
      this.hunger = (new RandomizedRangeSettings(this, "Hunger", 20.0, 20.0, 0.0, 20.0, 0)).withoutNotInARow();
      this.useGapple = new BooleanValue(this, "UseGapple", false);
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      this.notWhileKillAura = new BooleanValue(this, "NotWhileKillAura", false);
      this.notWhileScaffold = new BooleanValue(this, "NotWhileScaffold", true);
   }

   public void onEnable() {
      this.hunger.nextValue();
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(34);
   }

   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S07PacketRespawn || packet instanceof S08PacketPlayerPosLook) {
         this.timeHelper.reset();
      }
   }

   public void onClick(EventClick event) {
      if (mc.thePlayer.inventory.currentItem == this.foodHotbarSlot || this.foodHotbarSlot <= -1) {
         if (this.foodHotbarSlot >= 0 && mc.isNotInGui() && mc.getTicksWithoutScreen() > 3) {
            event.setRightMouseDown(true);
         } else if (this.previousFoodHotbarSlot >= 0) {
            this.hunger.nextValue();
         }
      }
   }

   public void onPostSilentSlot() {
      this.previousFoodHotbarSlot = this.foodHotbarSlot;
      this.foodHotbarSlot = -1;
      if (this.shouldEat()) {
         double d = -100.0;
         double d2 = -100.0;
         int n = -1;
         int n2 = -1;

         for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i + 36).getStack();
            if (stack != null && stack.getItem() instanceof ItemFood) {
               ItemFood item = (ItemFood)stack.getItem();
               double itemScore = InventoryUtil.getItemScore(stack);
               if (item.equals(Items.golden_apple)) {
                  if (itemScore > d2) {
                     d2 = itemScore;
                     n2 = i;
                  }
               } else if (itemScore > d) {
                  d = itemScore;
                  n = i;
               }
            }
         }

         this.foodHotbarSlot = n == -1 && this.useGapple.getValue() ? n2 : n;
         if (!this.timeHelper.reached(250L)) {
            this.foodHotbarSlot = -1;
         }

         if (this.foodHotbarSlot != -1) {
            SilentSlotManager.requestSlot(this, (byte)3, this.foodHotbarSlot, this.silentSlotSwitching.getValue());
         }
      }
   }

   private boolean shouldEat() {
      return mc.thePlayer.getFoodStats().getFoodLevel() < this.hunger.getLastIntValue()
         && mc.thePlayer.ticksExisted > 20
         && mc.isNotInGui()
         && mc.getTicksWithoutScreen() > 3
         && (!ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null || !this.notWhileKillAura.getValue())
         && (!ClientManagers.moduleManager.scaffold.isToggled() || !this.notWhileScaffold.getValue());
   }

}
