package net.minecraft.block;

import client.Client;
import client.module.Module;
import client.setting.BlockSolidType;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockWeb extends Block {
   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      if (!((Module)Client.getClient().getModuleManager().noWeb).isToggled()
         || !Client.getClient().getModuleManager().noWeb.settingModes.getSelected().equals("Ignore")) {
         entity.updateState14();
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.string;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockWeb() {
      super(Material.material15);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
