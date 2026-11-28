package net.augustus.modules.movement;

import java.awt.Color;
import java.util.OptionalInt;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.player.ContainerSlotQuery;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;

public class BowBoost extends Module implements TickListener, SilentSlotListener, ClickListener {

   public BowBoost() {
       super("BowBoost", "BogenBeschleunigung", new Color(200, 217, 227), Categorys.MOVEMENT);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
   }

   public void onEarlyTick() {
   }

   public void onPostSilentSlot() {
   }

   public void onClick(EventClick event) {
   }

   private int findBowSlot() {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
      containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
      OptionalInt optionalInt = containerSlotQuery.filterItemExact((Item)Items.bow).findFirstSlotIndex();
      return optionalInt.isPresent() ? optionalInt.getAsInt() : -1;
   }

   public boolean hasArrows() {
      return ContainerSlotQuery.of(mc.thePlayer.inventoryContainer).hasAnyOfItem(Items.arrow);
   }

}
