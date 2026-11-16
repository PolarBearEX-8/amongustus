package net.minecraft.block;

import client.Client;
import client.module.Module;
import client.util.IBlockState;
import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import client.util.VClipCollisionFilter;
import java.util.HashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.item.DiamondAxe;
import net.minecraft.item.DiamondHoe;
import net.minecraft.item.DiamondSword;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class BlockCache implements MinecraftAccessor {
   public static final HashSet entrySet = new HashSet();
   public static final HashSet entrySet2 = new HashSet();

   public static boolean checkBlockPos(BlockPos blockPos) {
      return checkCondition((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
   }

   public static boolean checkBlockPos2(BlockPos blockPos) {
      IBlockState blockState = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos);
      Block block = blockState.getBlock();
      return block.processAction(MinecraftAccessor.minecraft.theWorld, blockState, blockPos.getX(), blockPos.getY(), blockPos.getZ()) != null
         && block.getCoordOffset6() > 0.2
         && MinecraftAccessor.minecraft.thePlayer.motionY > -0.8;
   }

   public static boolean checkBlockPos3(BlockPos blockPos) {
      IBlockState blockState = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos);
      return blockState.getBlock().processAction(MinecraftAccessor.minecraft.theWorld, blockState, blockPos.getX(), blockPos.getY(), blockPos.getZ()) != null;
   }

   public static boolean checkItemStack(ItemStack stack) {
      return checkItemStack2(stack, false);
   }

   public static boolean canExecute() {
      return ((Module)Client.getClient().getModuleManager().scaffold).isToggled();
   }

   public static boolean matchesBlockPos(BlockPos blockPos) {
      return Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock() instanceof BlockAir;
   }

   public static boolean checkItemStack2(ItemStack stack, boolean flag) {
      if (stack == null) {
         return false;
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemBlock
            && !(item instanceof DiamondAxe)
            && !(item instanceof DiamondHoe)
            && !(item instanceof DiamondSword)
            && !(item instanceof ItemBanner)
            && !(item instanceof ItemFlintAndSteel)) {
            for (Item item2 : (Iterable<Item>)(Iterable)(entrySet2)) {
               if (item.equals(item2) && (!flag || !item.equals(Item.processAction(145)))) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public static boolean processAction(Block block) {
      return entrySet.contains(block.getClass());
   }

   public static boolean checkCondition(double posX, double posY, double posZ) {
      Block block = Minecraft.getMinecraft().theWorld.getIBlockState2(posX, posY, posZ).getBlock();
      return !(block instanceof BlockLiquid) && !(block instanceof BlockAir) && !(block instanceof AdvancedBlockAdapter) && !processAction(block);
   }

   static {
      entrySet.add(BlockAnvil.class);
      entrySet.add(BlockBeacon.class);
      entrySet.add(BlockBed.class);
      entrySet.add(BlockBrewingStand.class);
      entrySet.add(InternalBlockAdapter.class);
      entrySet.add(BlockCake.class);
      entrySet.add(BlockCauldron.class);
      entrySet.add(BlockTrappedChest.class);
      entrySet.add(BlockDaylightDetectorInverted.class);
      entrySet.add(BlockDispenser.class);
      entrySet.add(BlockDarkOakDoor.class);
      entrySet.add(BlockDragonEgg.class);
      entrySet.add(BlockEnchantingTable.class);
      entrySet.add(BlockEnderChest.class);
      entrySet.add(BlockAcaciaFenceGate.class);
      entrySet.add(BlockLitFurnace.class);
      entrySet.add(BlockHopper.class);
      entrySet.add(BlockJukebox.class);
      entrySet.add(BlockLever.class);
      entrySet.add(BlockNoteblock.class);
      entrySet.add(BlockPoweredComparator.class);
      entrySet.add(BlockPoweredRepeater.class);
      entrySet.add(VClipCollisionFilter.class);
      entrySet.add(BlockIronTrapdoor.class);
      entrySet.add(BlockCraftingTable.class);
      entrySet.add(BlockCommandBlock.class);
      entrySet2.add(Item.processAction(86));
      entrySet2.add(Item.processAction(138));
      entrySet2.add(Item.processAction(137));
      entrySet2.add(Item.processAction(91));
      entrySet2.add(Item.processAction(30));
      entrySet2.add(Item.processAction(66));
      entrySet2.add(Item.processAction(58));
      entrySet2.add(Item.processAction(116));
      entrySet2.add(Item.processAction(158));
      entrySet2.add(Item.processAction(23));
      entrySet2.add(Item.processAction(6));
      entrySet2.add(Item.processAction(54));
      entrySet2.add(Item.processAction(146));
      entrySet2.add(Item.processAction(130));
      entrySet2.add(Item.processAction(26));
      entrySet2.add(Item.processAction(50));
      entrySet2.add(Item.processAction(76));
      entrySet2.add(Item.processAction(46));
      entrySet2.add(Item.processAction(37));
      entrySet2.add(Item.processAction(77));
      entrySet2.add(Item.processAction(38));
      entrySet2.add(Item.processAction(12));
      entrySet2.add(Item.processAction(13));
      entrySet2.add(Item.processAction(157));
      entrySet2.add(Item.processAction(390));
      entrySet2.add(Item.processAction(65));
      entrySet2.add(Item.processAction(175));
      entrySet2.add(Item.processAction(425));
      entrySet2.add(Item.processAction(81));
      entrySet2.add(Item.processAction(96));
      entrySet2.add(Item.processAction(165));
      entrySet2.add(Item.processAction(70));
      entrySet2.add(Item.processAction(72));
      entrySet2.add(Item.processAction(147));
      entrySet2.add(Item.processAction(148));
      entrySet2.add(Item.processAction(106));
      entrySet2.add(Item.processAction(111));
      entrySet2.add(Item.processAction(69));
      entrySet2.add(Item.processAction(40));
      entrySet2.add(Item.processAction(39));
      entrySet2.add(Item.processAction(101));
      entrySet2.add(Item.processAction(102));
      entrySet2.add(Item.processAction(160));
      entrySet2.add(Item.processAction(107));
      entrySet2.add(Item.processAction(131));
      entrySet2.add(Item.processAction(143));
      entrySet2.add(Item.processAction(151));
      entrySet2.add(Item.processAction(154));
      entrySet2.add(Item.processAction(167));
      entrySet2.add(Item.processAction(183));
      entrySet2.add(Item.processAction(184));
      entrySet2.add(Item.processAction(185));
      entrySet2.add(Item.processAction(186));
      entrySet2.add(Item.processAction(187));
      entrySet2.add(Item.processAction(31));
      entrySet2.add(Item.processAction(32));
      entrySet2.add(Item.processAction(61));
      entrySet2.add(Item.processAction(84));
      entrySet2.add(Item.processAction(85));
      entrySet2.add(Item.processAction(113));
      entrySet2.add(Item.processAction(120));
      entrySet2.add(Item.processAction(145));
      entrySet2.add(Item.processAction(171));
      entrySet2.add(Item.processAction(188));
      entrySet2.add(Item.processAction(189));
      entrySet2.add(Item.processAction(190));
      entrySet2.add(Item.processAction(191));
      entrySet2.add(Item.processAction(192));
      entrySet2.add(Item.processAction(27));
      entrySet2.add(Item.processAction(28));
      entrySet2.add(Item.processAction(139));
   }
}
