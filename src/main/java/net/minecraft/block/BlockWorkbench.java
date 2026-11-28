package net.minecraft.block;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockWorkbench extends Block {
   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         player.displayGui(new BlockWorkbench.InterfaceCraftingTable(world, pos));
         player.triggerAchievement(StatList.craftingTableInteraction);
      }

      return true;
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   public BlockWorkbench() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }


   public static class InterfaceCraftingTable implements IInteractionObject {
      private final World world;
      private final BlockPos position;

      public InterfaceCraftingTable(World world2, BlockPos pos) {
         this.world = world2;
         this.position = pos;
      }

      @Override
      public String getName() {
         return null;
      }

      @Override
      public String getGuiID() {
         return "minecraft:crafting_table";
      }

      @Override
      public IChatComponent getDisplayName() {
         return new ChatComponentTranslation(Blocks.crafting_table.getUnlocalizedName() + ".name", new Object[0]);
      }

      @Override
      public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
         return new ContainerWorkbench(inventoryPlayer, this.world, this.position);
      }

      @Override
      public boolean hasCustomName() {
         return false;
      }
   }
}
