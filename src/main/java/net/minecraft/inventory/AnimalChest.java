package net.minecraft.inventory;

import net.minecraft.util.IChatComponent;

public class AnimalChest extends InventoryBasic {
   public AnimalChest(String str, int n) {
      super(str, false, n);
   }

   public AnimalChest(IChatComponent component, int n) {
      super(component, n);
   }
}
