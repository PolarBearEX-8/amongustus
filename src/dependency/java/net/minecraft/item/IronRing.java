package net.minecraft.item;

import client.setting.CombatWeightProperty;
import client.setting.LocalOptionType;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStonebrick;
import net.minecraft.block.Blocks;

public class IronRing {
   public static final IronRing ironRing = new IronRing();
   public Map map2 = Maps.newHashMap();
   public Map map = Maps.newHashMap();

   public ItemStack resolveItemStack(ItemStack stack) {
      for (Entry entry : (Iterable<Entry>) (Iterable) (this.map2.entrySet())) {
         if (this.matchesItemStack(stack, (ItemStack)entry.getKey())) {
            return (ItemStack)entry.getValue();
         }
      }

      return null;
   }

   public Map getMap2() {
      return this.map2;
   }

   public void applyItemStack(ItemStack stack, ItemStack stack2, float factor) {
      this.map2.put(stack, stack2);
      this.map.put(stack2, factor);
   }

   public void applyItem(Item item, ItemStack stack, float factor) {
      this.applyItemStack(new ItemStack(item, 1, 32767), stack, factor);
   }

   public float computeFactor(ItemStack stack) {
      for (Entry entry : (Iterable<Entry>) (Iterable) (this.map.entrySet())) {
         if (this.matchesItemStack(stack, (ItemStack)entry.getKey())) {
            return (Float)entry.getValue();
         }
      }

      return 0.0F;
   }

   private boolean matchesItemStack(ItemStack stack, ItemStack stack2) {
      return stack2.getItem() == stack.getItem() && (stack2.getIndexOffset() == 32767 || stack2.getIndexOffset() == stack.getIndexOffset());
   }

   public static IronRing getIronRing() {
      return ironRing;
   }

   public void applyBlock(Block block, ItemStack stack, float factor) {
      this.applyItem(Item.processAction2(block), stack, factor);
   }

   private IronRing() {
      this.applyBlock(Blocks.block103, new ItemStack(Items.ironIngot), 0.7F);
      this.applyBlock(Blocks.block121, new ItemStack(Items.goldIngot), 1.0F);
      this.applyBlock(Blocks.block44, new ItemStack(Items.diamond), 1.0F);
      this.applyBlock(Blocks.blockSand, new ItemStack(Blocks.block68), 0.1F);
      this.applyItem(Items.porkchop, new ItemStack(Items.cookedPorkchop), 0.35F);
      this.applyItem(Items.beef, new ItemStack(Items.cookedBeef), 0.35F);
      this.applyItem(Items.chicken, new ItemStack(Items.cookedChicken), 0.35F);
      this.applyItem(Items.rabbit, new ItemStack(Items.cookedRabbit), 0.35F);
      this.applyItem(Items.mutton, new ItemStack(Items.cookedMutton), 0.35F);
      this.applyBlock(Blocks.block65, new ItemStack(Blocks.stone), 0.1F);
      this.applyItemStack(new ItemStack(Blocks.block42, 1, BlockStonebrick.count), new ItemStack(Blocks.block42, 1, BlockStonebrick.count2), 0.1F);
      this.applyItem(Items.clayBall, new ItemStack(Items.brick), 0.3F);
      this.applyBlock(Blocks.block123, new ItemStack(Blocks.block98), 0.35F);
      this.applyBlock(Blocks.blockCactus, new ItemStack(Items.dye, 1, LocalOptionType.GREEN.getCount2()), 0.2F);
      this.applyBlock(Blocks.block140, new ItemStack(Items.coal, 1, 1), 0.15F);
      this.applyBlock(Blocks.block104, new ItemStack(Items.coal, 1, 1), 0.15F);
      this.applyBlock(Blocks.block70, new ItemStack(Items.emerald), 1.0F);
      this.applyItem(Items.potato, new ItemStack(Items.bakedPotato), 0.35F);
      this.applyBlock(Blocks.block96, new ItemStack(Items.netherbrick), 0.1F);
      this.applyItemStack(new ItemStack(Blocks.block11, 1, 1), new ItemStack(Blocks.block11, 1, 0), 0.15F);

      for (CombatWeightProperty combatWeightProperty : CombatWeightProperty.values()) {
         if (combatWeightProperty.isEnabled()) {
            this.applyItemStack(new ItemStack(Items.fish, 1, combatWeightProperty.getCount2()), new ItemStack(Items.cookedFish, 1, combatWeightProperty.getCount2()), 0.35F);
         }
      }

      this.applyBlock(Blocks.block89, new ItemStack(Items.coal), 0.1F);
      this.applyBlock(Blocks.block25, new ItemStack(Items.redstone), 0.7F);
      this.applyBlock(Blocks.block47, new ItemStack(Items.dye, 1, LocalOptionType.BLUE.getCount2()), 0.2F);
      this.applyBlock(Blocks.block4, new ItemStack(Items.quartz), 0.2F);
   }
}
