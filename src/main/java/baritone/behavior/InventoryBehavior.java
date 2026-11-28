package baritone.behavior;

import baritone.Baritone;
import baritone.api.event.events.TickEvent;
import baritone.api.utils.Helper;
import baritone.utils.ToolSet;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public final class InventoryBehavior extends Behavior implements Helper {
   public int ticksSinceLastInventoryMove;
   public int[] lastTickRequestedMove;

   public boolean swapWithHotBar(int n, Predicate predicate) {
      OptionalInt validHotbarSlot = this.getValidHotbarSlot(predicate);
      return !validHotbarSlot.isPresent() || this.requestSwapWithHotBar(n, validHotbarSlot.getAsInt());
   }

   public InventoryBehavior(Baritone baritone2) {
      super(baritone2);
   }

   public boolean throwaway(boolean flag, Predicate<ItemStack> predicate) {
      return this.throwaway(flag, predicate, (Boolean)Baritone.settings().allowInventory.value);
   }

   public OptionalInt getValidHotbarSlot(Predicate predicate) {
      ArrayList list = new ArrayList();

      for (int j = 1; j < 8; j++) {
         if (this.ctx.player().inventory.mainInventory[j] == null && !predicate.test(j)) {
            list.add(j);
         }
      }

      if (list.isEmpty()) {
         for (int k = 1; k < 8; k++) {
            if (!predicate.test(k)) {
               list.add(k);
            }
         }
      }

      return list.isEmpty() ? OptionalInt.empty() : OptionalInt.of((Integer)list.get(new Random().nextInt(list.size())));
   }

   private int bestToolAgainst(Block block, Class clazz) {
      int n = -1;
      double d = -1.0;

      for (int j = 0; j < this.ctx.player().inventory.mainInventory.length; j++) {
         ItemStack stack = this.ctx.player().inventory.mainInventory[j];
         if (stack != null
            && (!(Boolean)Baritone.settings().itemSaver.value || stack.getItemDamage() < stack.getMaxDamage() || stack.getMaxDamage() <= 1)
            && clazz.isInstance(stack.getItem())) {
            double d2 = ToolSet.calculateSpeedVsBlock(stack, block.getDefaultState());
            if (d2 > d) {
               d = d2;
               n = j;
            }
         }
      }

      return n;
   }

   public boolean hasGenericThrowaway() {
      for (Item item : (Iterable<Item>)((List)Baritone.settings().acceptableThrowawayItems.value)) {
         if (this.throwaway(false, stack -> stack != null && item.equals(stack.getItem()))) {
            return true;
         }
      }

      return false;
   }

   public boolean throwaway(boolean flag, int n, int n2, int n3) {
      IBlockState state = this.baritone.getBuilderProcess2().placeAt(n, n2, n3, this.baritone.bsi.get0(n, n2, n3));
      if (state != null
         && this.throwaway(
            flag,
            stack -> stack.getItem() instanceof ItemBlock
                  && state.equals(
                     ((ItemBlock)stack.getItem())
                        .getBlock()
                        .onBlockPlaced(
                           this.ctx.world(),
                           this.ctx.playerFeet(),
                           EnumFacing.UP,
                           (float)this.ctx.player().posX,
                           (float)this.ctx.player().posY,
                           (float)this.ctx.player().posZ,
                           stack.getItem().getMetadata(stack.getMetadata()),
                           this.ctx.player()
                        )
                  )
         )) {
         return true;
      } else if (state != null
         && this.throwaway(flag, stack2 -> stack2.getItem() instanceof ItemBlock && ((ItemBlock)stack2.getItem()).getBlock().equals(state.getBlock()))) {
         return true;
      } else {
         for (Item item : (Iterable<Item>)((List)Baritone.settings().acceptableThrowawayItems.value)) {
            if (this.throwaway(flag, stack3 -> item.equals(stack3.getItem()))) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public void onTick(TickEvent tickEvent) {
      if ((Boolean)Baritone.settings().allowInventory.value) {
         if (tickEvent.getType() != TickEvent.Type.OUT) {
            if (this.ctx.player().openContainer == this.ctx.player().inventoryContainer) {
               this.ticksSinceLastInventoryMove++;
               if (this.firstValidThrowaway() >= 9) {
                  this.requestSwapWithHotBar(this.firstValidThrowaway(), 8);
               }

               int n = this.bestToolAgainst(Blocks.stone, ItemPickaxe.class);
               if (n >= 9) {
                  this.requestSwapWithHotBar(n, 0);
               }

               if (this.lastTickRequestedMove != null) {
                  this.logDebug("Remembering to move " + this.lastTickRequestedMove[0] + " " + this.lastTickRequestedMove[1] + " from a previous tick");
                  this.requestSwapWithHotBar(this.lastTickRequestedMove[0], this.lastTickRequestedMove[1]);
               }
            }
         }
      }
   }

   private boolean requestSwapWithHotBar(int n, int n2) {
      this.lastTickRequestedMove = new int[]{n, n2};
      if (this.ticksSinceLastInventoryMove < (Integer)Baritone.settings().ticksBetweenInventoryMoves.value) {
         this.logDebug("Inventory move requested but delaying " + this.ticksSinceLastInventoryMove + " " + Baritone.settings().ticksBetweenInventoryMoves.value);
         return false;
      } else if ((Boolean)Baritone.settings().inventoryMoveOnlyIfStationary.value && !this.baritone.getInventoryPauserProcess().stationaryForInventoryMove()) {
         this.logDebug("Inventory move requested but delaying until stationary");
         return false;
      } else {
         this.ctx.playerController().windowClick(this.ctx.player().inventoryContainer.windowId, n < 9 ? n + 36 : n, n2, 2, this.ctx.player());
         this.ticksSinceLastInventoryMove = 0;
         this.lastTickRequestedMove = null;
         return true;
      }
   }

   private int firstValidThrowaway() {
      for (int j = 0; j < this.ctx.player().inventory.mainInventory.length; j++) {
         ItemStack stack = this.ctx.player().inventory.mainInventory[j];
         if (stack != null && ((List)Baritone.settings().acceptableThrowawayItems.value).contains(stack.getItem())) {
            return j;
         }
      }

      return -1;
   }

   public boolean throwaway(boolean flag, Predicate predicate, boolean flag2) {
      EntityPlayerSP player = this.ctx.player();
      ItemStack[] mainInventory = player.inventory.mainInventory;

      for (int j = 0; j < 9; j++) {
         ItemStack stack = mainInventory[j];
         if (stack != null && stack.getItem() != null && predicate.test(stack)) {
            if (flag) {
               player.inventory.currentItem = j;
            }

            return true;
         }
      }

      if (flag2) {
         for (int k = 9; k < 36; k++) {
            if (predicate.test(mainInventory[k])) {
               if (flag) {
                  this.requestSwapWithHotBar(k, 7);
                  player.inventory.currentItem = 7;
               }

               return true;
            }
         }
      }

      return false;
   }

}
