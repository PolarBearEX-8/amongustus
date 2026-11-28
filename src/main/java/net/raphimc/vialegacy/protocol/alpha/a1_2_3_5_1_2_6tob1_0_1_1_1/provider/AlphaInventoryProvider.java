package net.raphimc.vialegacy.protocol.alpha.a1_2_3_5_1_2_6tob1_0_1_1_1.provider;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.minecraft.item.Item;
import com.viaversion.viaversion.api.platform.providers.Provider;
import net.raphimc.vialegacy.protocol.alpha.a1_2_3_5_1_2_6tob1_0_1_1_1.storage.InventoryStorage;

public abstract class AlphaInventoryProvider implements Provider {
   public abstract boolean usesInventoryTracker();

   public Item getHandItem(UserConnection user) {
      InventoryStorage inventoryStorage = (InventoryStorage)user.get(InventoryStorage.class);
      return this.getMainInventoryItems(user)[inventoryStorage.selectedHotbarSlot];
   }

   public abstract Item[] getMainInventoryItems(UserConnection userConnection);

   public abstract Item[] getCraftingInventoryItems(UserConnection userConnection);

   public abstract Item[] getArmorInventoryItems(UserConnection userConnection);

   public abstract Item[] getContainerItems(UserConnection userConnection);

   public abstract void addToInventory(UserConnection userConnection, Item item);
}
