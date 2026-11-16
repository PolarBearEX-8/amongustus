package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.RepairContainer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class DampenerReader implements Listener63Extension {
   public final World world;
   public final BlockPos blockPos;

   @Override
   public boolean processAction() {
      return false;
   }

   @Override
   public String getLabelText2() {
      return "minecraft:anvil";
   }

   @Override
   public String getText() {
      return "anvil";
   }

   @Override
   public IChatComponent getChatComponent() {
      return new BaseManagerStyleIChatComponentAdapter(Blocks.block51.getTextAsText() + ".name", new Object[0]);
   }

   public DampenerReader(World world2, BlockPos blockPos2) {
      this.world = world2;
      this.blockPos = blockPos2;
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new RepairContainer(inventoryPlayer, this.world, this.blockPos, player);
   }
}
