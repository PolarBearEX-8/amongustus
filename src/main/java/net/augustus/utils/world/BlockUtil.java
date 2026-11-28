package net.augustus.utils.world;

import net.augustus.Augustus;
import java.util.HashSet;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockNote;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemSnow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class BlockUtil implements MC {
   private static final HashSet INVALID_SCAFFOLD_ITEMS = new HashSet();
   private static final HashSet INTERACTABLE_BLOCKS = new HashSet();

   public static boolean isScaffoldActiveAndValid() {
      return Augustus.getInstance().getModuleManager().scaffold.isToggled();
   }

   public static boolean hasCollisionBox(int n, int n2, int n3) {
      IBlockState blockState2 = mc.theWorld.getBlockState2(n, n2, n3);
      return blockState2.getBlock().getCollisionBoundingBox(mc.theWorld, blockState2, n, n2, n3) != null;
   }

   static {
      INTERACTABLE_BLOCKS.add(BlockAnvil.class);
      INTERACTABLE_BLOCKS.add(BlockBeacon.class);
      INTERACTABLE_BLOCKS.add(BlockBed.class);
      INTERACTABLE_BLOCKS.add(BlockBrewingStand.class);
      INTERACTABLE_BLOCKS.add(BlockButton.class);
      INTERACTABLE_BLOCKS.add(BlockCake.class);
      INTERACTABLE_BLOCKS.add(BlockCauldron.class);
      INTERACTABLE_BLOCKS.add(BlockChest.class);
      INTERACTABLE_BLOCKS.add(BlockDaylightDetector.class);
      INTERACTABLE_BLOCKS.add(BlockDispenser.class);
      INTERACTABLE_BLOCKS.add(BlockDoor.class);
      INTERACTABLE_BLOCKS.add(BlockDragonEgg.class);
      INTERACTABLE_BLOCKS.add(BlockEnchantmentTable.class);
      INTERACTABLE_BLOCKS.add(BlockEnderChest.class);
      INTERACTABLE_BLOCKS.add(BlockFenceGate.class);
      INTERACTABLE_BLOCKS.add(BlockFurnace.class);
      INTERACTABLE_BLOCKS.add(BlockHopper.class);
      INTERACTABLE_BLOCKS.add(BlockJukebox.class);
      INTERACTABLE_BLOCKS.add(BlockLever.class);
      INTERACTABLE_BLOCKS.add(BlockNote.class);
      INTERACTABLE_BLOCKS.add(BlockRedstoneComparator.class);
      INTERACTABLE_BLOCKS.add(BlockRedstoneRepeater.class);
      INTERACTABLE_BLOCKS.add(BlockSign.class);
      INTERACTABLE_BLOCKS.add(BlockTrapDoor.class);
      INTERACTABLE_BLOCKS.add(BlockWorkbench.class);
      INTERACTABLE_BLOCKS.add(BlockCommandBlock.class);
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(86));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(138));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(137));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(91));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(30));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(66));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(58));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(116));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(158));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(23));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(6));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(54));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(146));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(130));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(26));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(50));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(76));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(46));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(37));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(77));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(38));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(12));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(13));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(157));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(390));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(65));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(175));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(425));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(81));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(96));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(165));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(70));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(72));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(147));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(148));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(106));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(111));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(69));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(40));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(39));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(101));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(102));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(160));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(107));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(131));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(143));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(151));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(154));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(167));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(183));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(184));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(185));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(186));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(187));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(31));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(32));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(61));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(84));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(85));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(113));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(120));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(145));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(171));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(188));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(189));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(190));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(191));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(192));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(27));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(28));
      INVALID_SCAFFOLD_ITEMS.add(Item.getItemById(139));
   }

   public static boolean hasCollisionBox(BlockPos pos) {
      IBlockState blockState = mc.theWorld.getBlockState(pos);
      return blockState.getBlock().getCollisionBoundingBox(mc.theWorld, blockState, pos.getX(), pos.getY(), pos.getZ()) != null;
   }

   public static boolean isSolidBlock(double d, double d2, double d3) {
      Block block = Minecraft.getMinecraft().theWorld.getBlockState(d, d2, d3).getBlock();
      return !(block instanceof BlockLiquid) && !(block instanceof BlockAir) && !(block instanceof BlockContainer) && !isInteractableBlock(block);
   }

   public static boolean isValidScaffoldBlock(ItemStack stack) {
      return isValidScaffoldBlock(stack, false);
   }

   public static boolean isWalkableBlock(BlockPos pos) {
      IBlockState blockState = mc.theWorld.getBlockState(pos);
      Block block = blockState.getBlock();
      return block.getCollisionBoundingBox(mc.theWorld, blockState, pos.getX(), pos.getY(), pos.getZ()) != null && block.getBlockBoundsMaxY() > 0.2 && mc.thePlayer.motionY > -0.8;
   }

   public static boolean isValidScaffoldBlock(ItemStack stack, boolean flag) {
      if (stack == null) {
         return false;
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemBlock
            && !(item instanceof ItemSlab)
            && !(item instanceof ItemLeaves)
            && !(item instanceof ItemSnow)
            && !(item instanceof ItemBanner)
            && !(item instanceof ItemFlintAndSteel)) {
            for (Item item2 : (Iterable<Item>)(INVALID_SCAFFOLD_ITEMS)) {
               if (item.equals(item2) && (!flag || !item.equals(Item.getItemById(145)))) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      }
   }


   public static boolean isSolidBlock(BlockPos pos) {
      return isSolidBlock((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
   }

   public static boolean isAirBlock(BlockPos pos) {
      return Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock() instanceof BlockAir;
   }

   public static boolean isInteractableBlock(Block block) {
      return INTERACTABLE_BLOCKS.contains(block.getClass());
   }

}
