package net.raphimc.vialegacy.api.remapper;

import com.viaversion.nbt.tag.CompoundTag;
import com.viaversion.nbt.tag.ListTag;
import com.viaversion.nbt.tag.StringTag;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.minecraft.item.HashedItem;
import com.viaversion.viaversion.api.minecraft.item.Item;
import com.viaversion.viaversion.api.protocol.Protocol;
import com.viaversion.viaversion.api.protocol.packet.ClientboundPacketType;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.protocol.packet.ServerboundPacketType;
import com.viaversion.viaversion.api.protocol.remapper.PacketHandlers;
import com.viaversion.viaversion.api.rewriter.ItemRewriter;
import com.viaversion.viaversion.api.rewriter.RewriterBase;
import com.viaversion.viaversion.api.type.Type;
import com.viaversion.viaversion.api.type.Types;
import com.viaversion.viaversion.libs.fastutil.objects.ObjectArrayList;
import com.viaversion.viaversion.libs.fastutil.objects.ObjectList;
import com.viaversion.viaversion.libs.fastutil.objects.ObjectListIterator;
import java.util.Objects;

public abstract class LegacyItemRewriter<C extends ClientboundPacketType, S extends ServerboundPacketType, P extends Protocol<C, ?, ?, S>>
   extends RewriterBase<P>
   implements ItemRewriter<P> {
   final ObjectList<LegacyItemRewriter.RewriteEntry> rewriteEntries = new ObjectArrayList();
   final ObjectList<LegacyItemRewriter.NonExistentEntry> nonExistentItems = new ObjectArrayList();
   protected final String protocolName;
   final Type<Item> itemType;
   final Type<Item> mappedItemType;
   final Type<Item[]> itemArrayType;
   final Type<Item[]> mappedItemArrayType;

   public LegacyItemRewriter(P protocol, String protocolName, Type<Item> itemType, Type<Item[]> itemArrayType) {
      this(protocol, protocolName, itemType, itemArrayType, itemType, itemArrayType);
   }

   public LegacyItemRewriter(
      P protocol, String protocolName, Type<Item> itemType, Type<Item[]> itemArrayType, Type<Item> mappedItemType, Type<Item[]> mappedItemArrayType
   ) {
      super(protocol);
      this.protocolName = protocolName;
      this.itemType = itemType;
      this.itemArrayType = itemArrayType;
      this.mappedItemType = mappedItemType;
      this.mappedItemArrayType = mappedItemArrayType;
   }

   public void addRemappedItem(int oldItemId, int newItemId, String newItemName) {
      this.addRemappedItem(oldItemId, newItemId, -1, newItemName);
   }

   public void addRemappedItem(int oldItemId, int newItemId, int newItemMeta, String newItemName) {
      this.addRemappedItem(oldItemId, -1, newItemId, newItemMeta, newItemName);
   }

   protected void addRemappedItem(int oldItemId, int oldItemMeta, int newItemId, int newItemMeta, String newItemName) {
      this.rewriteEntries.add(new LegacyItemRewriter.RewriteEntry(oldItemId, (short)oldItemMeta, newItemId, (short)newItemMeta, newItemName));
   }

   public void addNonExistentItem(int itemId, int itemMeta) {
      this.nonExistentItems.add(new LegacyItemRewriter.NonExistentEntry(itemId, (short)itemMeta));
   }

   public void addNonExistentItem(int itemId, int startItemMeta, int endItemMeta) {
      for (int i = startItemMeta; i <= endItemMeta; i++) {
         this.nonExistentItems.add(new LegacyItemRewriter.NonExistentEntry(itemId, (short)i));
      }
   }

   public void addNonExistentItems(int... itemIds) {
      for (int itemId : itemIds) {
         this.nonExistentItems.add(new LegacyItemRewriter.NonExistentEntry(itemId, (short)-1));
      }
   }

   public void addNonExistentItemRange(int startItemId, int endItemId) {
      for (int i = startItemId; i <= endItemId; i++) {
         this.nonExistentItems.add(new LegacyItemRewriter.NonExistentEntry(i, (short)-1));
      }
   }

   public void registerCreativeInventoryAction(S packetType) {
      this.protocol.registerServerbound(packetType, new PacketHandlers() {
         public void register() {
            this.map(Types.SHORT);
            this.handler(wrapper -> LegacyItemRewriter.this.handleServerboundItem(wrapper));
         }
      });
   }

   public Item handleItemToClient(UserConnection user, Item item) {
      if (item == null) {
         return null;
      } else {
         ObjectListIterator it = this.rewriteEntries.iterator();

         while (it.hasNext()) {
            LegacyItemRewriter.RewriteEntry rewriteEntry = (LegacyItemRewriter.RewriteEntry)it.next();
            if (rewriteEntry.rewrites(item)) {
               this.setRemappedNameRead(item, rewriteEntry.newItemName);
               if (rewriteEntry.newItemMeta != -1) {
                  item.setData(rewriteEntry.newItemMeta);
               }

               item.setIdentifier(rewriteEntry.newItemID);
            }
         }

         return item;
      }
   }

   public Item handleItemToServer(UserConnection user, Item item) {
      if (item == null) {
         return null;
      } else {
         ObjectListIterator it = this.nonExistentItems.iterator();

         while (it.hasNext()) {
            if (((LegacyItemRewriter.NonExistentEntry)it.next()).rewrites(item)) {
               item.setIdentifier(1);
               item.setData((short)0);
               return item;
            }
         }

         this.setRemappedTagWrite(item);
         return item;
      }
   }

   public HashedItem handleHashedItem(UserConnection connection, HashedItem item) {
      throw new UnsupportedOperationException();
   }

   public Type<Item> itemType() {
      return this.itemType;
   }

   public Type<Item[]> itemArrayType() {
      return this.itemArrayType;
   }

   public Type<Item> mappedItemType() {
      return this.mappedItemType;
   }

   public Type<Item[]> mappedItemArrayType() {
      return this.mappedItemArrayType;
   }

   public String nbtTagName() {
      String simpleName = this.protocol.getClass().getSimpleName();
      return "VL|" + simpleName;
   }

   void handleClientboundItem(PacketWrapper wrapper) {
      Item item = this.handleItemToClient(wrapper.user(), (Item)wrapper.read(this.itemType));
      wrapper.write(this.mappedItemType, item);
   }

   void handleServerboundItem(PacketWrapper wrapper) {
      Item item = this.handleItemToServer(wrapper.user(), (Item)wrapper.read(this.mappedItemType));
      wrapper.write(this.itemType, item);
   }

   void setRemappedNameRead(Item item, String name) {
      CompoundTag viaLegacyTag = new CompoundTag();
      viaLegacyTag.putInt("Id", item.identifier());
      viaLegacyTag.putShort("Meta", item.data());
      CompoundTag tag = item.tag();
      if (tag == null) {
         tag = new CompoundTag();
         item.setTag(tag);
         viaLegacyTag.putBoolean("RemoveTag", true);
      }

      tag.put(this.nbtTagName(), viaLegacyTag);
      CompoundTag display = tag.getCompoundTag("display");
      if (display == null) {
         display = new CompoundTag();
         tag.put("display", display);
         viaLegacyTag.putBoolean("RemoveDisplayTag", true);
      }

      if (display.contains("Name")) {
         ListTag<StringTag> lore = display.getListTag("Lore", StringTag.class);
         if (lore == null) {
            lore = new ListTag(StringTag.class);
            display.put("Lore", lore);
            viaLegacyTag.putBoolean("RemoveLore", true);
         }

         String protocolName2 = this.protocolName;
         int n = item.identifier();
         lore.add(new StringTag("§r " + protocolName2 + " Item ID: " + n + " (" + name + ")"));
         viaLegacyTag.putBoolean("RemoveLastLore", true);
      } else {
         String protocolName3 = this.protocolName;
         display.putString("Name", "§r" + protocolName3 + " " + name);
         viaLegacyTag.putBoolean("RemoveDisplayName", true);
      }
   }

   void setRemappedTagWrite(Item item) {
      CompoundTag tag = item.tag();
      if (tag != null) {
         CompoundTag viaLegacyTag = (CompoundTag)tag.removeUnchecked(this.nbtTagName());
         if (viaLegacyTag != null) {
            item.setIdentifier(viaLegacyTag.getNumberTag("Id").asInt());
            item.setData(viaLegacyTag.getNumberTag("Meta").asShort());
            if (viaLegacyTag.contains("RemoveLastLore")) {
               ListTag<StringTag> lore = tag.getCompoundTag("display").getListTag("Lore", StringTag.class);
               lore.remove(lore.size() - 1);
            }

            if (viaLegacyTag.contains("RemoveLore")) {
               tag.getCompoundTag("display").remove("Lore");
            }

            if (viaLegacyTag.contains("RemoveDisplayName")) {
               tag.getCompoundTag("display").remove("Name");
            }

            if (viaLegacyTag.contains("RemoveDisplayTag")) {
               tag.remove("display");
            }

            if (viaLegacyTag.contains("RemoveTag")) {
               item.setTag(null);
            }
         }
      }
   }

   private static final class NonExistentEntry {
      final int itemId;
      final short itemMeta;

      NonExistentEntry(int itemId, short itemMeta) {
         this.itemId = itemId;
         this.itemMeta = itemMeta;
      }

      public boolean rewrites(Item item) {
         return item.identifier() == this.itemId && (this.itemMeta == -1 || this.itemMeta == item.data());
      }

      public int itemId() {
         return this.itemId;
      }

      public short itemMeta() {
         return this.itemMeta;
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof LegacyItemRewriter.NonExistentEntry)) {
            return false;
         } else {
            LegacyItemRewriter.NonExistentEntry nonExistentEntry = (LegacyItemRewriter.NonExistentEntry)obj;
            return this.itemId == nonExistentEntry.itemId && this.itemMeta == nonExistentEntry.itemMeta;
         }
      }

      @Override
      public int hashCode() {
         return (0 * 31 + Integer.hashCode(this.itemId)) * 31 + Short.hashCode(this.itemMeta);
      }

      @Override
      public String toString() {
         return String.format("%s[itemId=%s, itemMeta=%s]", this.getClass().getSimpleName(), Integer.toString(this.itemId), Short.toString(this.itemMeta));
      }
   }

   private static final class RewriteEntry {
      final int oldItemID;
      final short oldItemMeta;
      final int newItemID;
      final short newItemMeta;
      final String newItemName;

      RewriteEntry(int oldItemID, short oldItemMeta, int newItemID, short newItemMeta, String newItemName) {
         this.oldItemID = oldItemID;
         this.oldItemMeta = oldItemMeta;
         this.newItemID = newItemID;
         this.newItemMeta = newItemMeta;
         this.newItemName = newItemName;
      }

      public boolean rewrites(Item item) {
         return item.identifier() == this.oldItemID && (this.oldItemMeta == -1 || this.oldItemMeta == item.data());
      }

      public int oldItemID() {
         return this.oldItemID;
      }

      public short oldItemMeta() {
         return this.oldItemMeta;
      }

      public int newItemID() {
         return this.newItemID;
      }

      public short newItemMeta() {
         return this.newItemMeta;
      }

      public String newItemName() {
         return this.newItemName;
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof LegacyItemRewriter.RewriteEntry)) {
            return false;
         } else {
            LegacyItemRewriter.RewriteEntry rewriteEntry = (LegacyItemRewriter.RewriteEntry)obj;
            return this.oldItemID == rewriteEntry.oldItemID
               && this.oldItemMeta == rewriteEntry.oldItemMeta
               && this.newItemID == rewriteEntry.newItemID
               && this.newItemMeta == rewriteEntry.newItemMeta
               && Objects.equals(this.newItemName, rewriteEntry.newItemName);
         }
      }

      @Override
      public int hashCode() {
         return (
                  (((0 * 31 + Integer.hashCode(this.oldItemID)) * 31 + Short.hashCode(this.oldItemMeta)) * 31 + Integer.hashCode(this.newItemID)) * 31
                     + Short.hashCode(this.newItemMeta)
               )
               * 31
            + Objects.hashCode(this.newItemName);
      }

      @Override
      public String toString() {
         return String.format(
            "%s[oldItemID=%s, oldItemMeta=%s, newItemID=%s, newItemMeta=%s, newItemName=%s]",
            this.getClass().getSimpleName(),
            Integer.toString(this.oldItemID),
            Short.toString(this.oldItemMeta),
            Integer.toString(this.newItemID),
            Short.toString(this.newItemMeta),
            Objects.toString(this.newItemName)
         );
      }
   }
}
