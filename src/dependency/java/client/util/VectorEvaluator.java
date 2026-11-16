package client.util;

import client.setting.MovementNumberProperty;
import net.minecraft.block.BlockTrappedChest;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class VectorEvaluator extends IInventoryExtensionAdapter {
   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      if (this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
         this.resolveEntityItem(Item.processAction2(Blocks.blockTrappedChest), 1, 0.0F);
      }
   }

   @Override
   public MovementNumberProperty getFURNACE() {
      return MovementNumberProperty.CHEST;
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new AdvancedContainerAdapter(inventoryPlayer, this, player);
   }

   @Override
   public IBlockState getIBlockState() {
      return Blocks.blockTrappedChest.getDefaultState().resolveIBlockState(BlockTrappedChest.autoWalkPath, EnumFacing.NORTH);
   }

   @Override
   public int processAction4() {
      return 27;
   }

   @Override
   public String getLabelText2() {
      return "minecraft:chest";
   }

   @Override
   public int getCount4() {
      return 8;
   }

   public VectorEvaluator(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }

   public VectorEvaluator(World world) {
      super(world);
   }
}
