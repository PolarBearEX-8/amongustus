package net.augustus.utils.player;

import net.augustus.utils.InventorySlotEntry;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.augustus.utils.TimeHelper;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerSlotQuery {
   private int endSlot;
   private static final Predicate<InventorySlotEntry> NON_NULL_STACK;
   private static final Predicate<InventorySlotEntry> EMPTY_SLOT;
   private int startSlot;
   private Predicate<InventorySlotEntry> slotPredicate;
   private static final Predicate<InventorySlotEntry> NON_EMPTY_STACK;
   private final Container container;

   public boolean hasAnyNonEmpty() {
      return this.streamEntries().anyMatch(NON_EMPTY_STACK);
   }

   public ContainerSlotQuery nonEmpty() {
      this.slotPredicate = this.slotPredicate.and(NON_EMPTY_STACK);
      return this;
   }

   public boolean noneMatchStackAndSlot(BiPredicate biPredicate) {
      return this.streamEntries().noneMatch(inventorySlotEntry -> biPredicate.test(inventorySlotEntry.stack, inventorySlotEntry.slotIndex));
   }

   public boolean hasNoneOfItem(Item item) {
      return this.streamEntries().noneMatch(inventorySlotEntry -> {
         return inventorySlotEntry.stack != null && inventorySlotEntry.stack.getItem().equals(item);
      });
   }

   public boolean allMatchItem(Item item) {
      return this.streamEntries().allMatch(inventorySlotEntry -> {
         return inventorySlotEntry.stack != null && inventorySlotEntry.stack.getItem().equals(item);
      });
   }

   public ContainerSlotQuery fromSlot(int n) {
      this.startSlot = Math.max(0, n);
      return this;
   }

   public ContainerSlotQuery filterStackAndSlot(BiPredicate biPredicate) {
      Objects.requireNonNull(biPredicate, "filter");
      this.slotPredicate = this.slotPredicate.and(inventorySlotEntry -> biPredicate.test(inventorySlotEntry.stack, inventorySlotEntry.slotIndex));
      return this;
   }

   public boolean noneMatchItem(Predicate predicate) {
      return this.streamEntries().noneMatch(inventorySlotEntry -> {
         return predicate.test(inventorySlotEntry.stack != null ? inventorySlotEntry.stack.getItem() : null);
      });
   }

   public boolean anyMatchStackAndSlot(BiPredicate biPredicate) {
      return this.streamEntries().anyMatch(inventorySlotEntry -> biPredicate.test(inventorySlotEntry.stack, inventorySlotEntry.slotIndex));
   }

   private Stream streamStacks() {
      return this.streamEntries().map(inventorySlotEntry -> inventorySlotEntry.stack);
   }

   public boolean hasNoEmptySlot() {
      return this.streamEntries().noneMatch(EMPTY_SLOT);
   }

   private IntStream streamSlotIndices() {
      return this.streamEntries().mapToInt(inventorySlotEntry -> inventorySlotEntry.slotIndex);
   }

   public boolean allMatchItemPredicate(Predicate predicate) {
      return this.streamEntries().allMatch(inventorySlotEntry -> {
         return predicate.test(inventorySlotEntry.stack != null ? inventorySlotEntry.stack.getItem() : null);
      });
   }

   public ContainerSlotQuery filterItem(Predicate predicate) {
      Objects.requireNonNull(predicate, "filter");
      this.slotPredicate = this.slotPredicate.and(inventorySlotEntry -> {
         return predicate.test(inventorySlotEntry.stack != null ? inventorySlotEntry.stack.getItem() : null);
      });
      return this;
   }

   public Optional findFirstSlot() {
      return this.streamSlots().findFirst();
   }

   public Optional findFirstStack() {
      return this.streamStacks().findFirst();
   }

   public boolean hasAnyOfItem(Item item) {
      return this.streamEntries().anyMatch(inventorySlotEntry -> {
         return inventorySlotEntry.stack != null && inventorySlotEntry.stack.getItem().equals(item);
      });
   }

   public ContainerSlotQuery emptyOnly() {
      this.slotPredicate = this.slotPredicate.and(EMPTY_SLOT);
      return this;
   }

   private Stream<InventorySlotEntry> streamEntries() {
      return IntStream.range(this.startSlot, this.endSlot).<InventorySlotEntry>mapToObj(n -> {
         Slot slot = this.container.getSlot(n);
         ItemStack stack = slot.getStack();
         return new InventorySlotEntry(n, slot, stack, null);
      }).filter(this.slotPredicate);
   }

   public boolean allNonEmpty() {
      return this.streamEntries().allMatch(NON_EMPTY_STACK);
   }

   public List toStackList() {
      return (List)(this.streamStacks().collect(Collectors.toList()));
   }

   public ContainerSlotQuery toSlot(int n) {
      this.endSlot = Math.min(n, this.container.inventorySlots.size());
      return this;
   }

   public OptionalInt findFirstSlotIndex() {
      return this.streamSlotIndices().findFirst();
   }

   public ContainerSlotQuery nonEmptyOrNonNull(boolean flag) {
      this.slotPredicate = this.slotPredicate.and(flag ? NON_NULL_STACK : NON_EMPTY_STACK);
      return this;
   }

   public long count() {
      return this.streamEntries().count();
   }

   public static ContainerSlotQuery of(Container container2) {
      return new ContainerSlotQuery(container2);
   }

   static {
      EMPTY_SLOT = inventorySlotEntry -> inventorySlotEntry.stack == null;
      NON_EMPTY_STACK = inventorySlotEntry2 -> {
         return inventorySlotEntry2.stack != null && inventorySlotEntry2.stack.stackSize > 0;
      };
      NON_NULL_STACK = inventorySlotEntry3 -> inventorySlotEntry3.stack != null;
   }

   public List toSlotList() {
      return (List)(this.streamSlots().collect(Collectors.toList()));
   }

   private ContainerSlotQuery(Container container2) {
      this.container = Objects.requireNonNull(container2, "container");
      this.startSlot = 0;
      this.endSlot = container2.inventorySlots.size();
      this.slotPredicate = inventorySlotEntry -> true;
   }

   private Stream streamSlots() {
      return this.streamEntries().map(inventorySlotEntry -> inventorySlotEntry.slot);
   }

   public boolean hasNoNonEmpty() {
      return this.streamEntries().noneMatch(NON_EMPTY_STACK);
   }

   public List toSlotIndexList() {
      return this.streamSlotIndices().boxed().collect(Collectors.toList());
   }

   public boolean allEmpty() {
      return this.streamEntries().allMatch(EMPTY_SLOT);
   }

   public boolean allMatchStackAndSlot(BiPredicate biPredicate) {
      return this.streamEntries().allMatch(inventorySlotEntry -> biPredicate.test(inventorySlotEntry.stack, inventorySlotEntry.slotIndex));
   }

   public boolean anyMatchItem(Predicate predicate) {
      return this.streamEntries().anyMatch(inventorySlotEntry -> {
         return predicate.test(inventorySlotEntry.stack != null ? inventorySlotEntry.stack.getItem() : null);
      });
   }

   public boolean hasAnyEmptySlot() {
      return this.streamEntries().anyMatch(EMPTY_SLOT);
   }

   public ContainerSlotQuery filterItemExact(Item item) {
      Objects.requireNonNull(item, "filter");
      this.slotPredicate = this.slotPredicate.and(inventorySlotEntry -> {
         return inventorySlotEntry.stack != null && inventorySlotEntry.stack.getItem().equals(item);
      });
      return this;
   }

}
