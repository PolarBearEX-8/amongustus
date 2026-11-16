package client.util;

import client.event.GenerationListener;
import client.event.PreMotionProcessor;
import client.setting.CoreOptionType;
import client.setting.CustomOptionType;
import client.setting.IndirectOptionType;
import client.setting.LocalOptionType;
import client.setting.MovementProperty;
import client.setting.SpecialOptionType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ExtendedManager {
   public static final ExtendedManager extendedManager = new ExtendedManager();
   public final List list = Lists.newArrayList();

   public ItemStack resolveItemStack(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      for (PreMotionProcessor preMotionProcessor : (Iterable<PreMotionProcessor>) (this.list)) {
         if (preMotionProcessor.matchesContainerCraftingIInventor(containerCraftingIInventory, world)) {
            return preMotionProcessor.resolveItemStack(containerCraftingIInventory);
         }
      }

      return null;
   }

   public ItemStack[] resolveItemStackArrayByContainerCraftin(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      for (PreMotionProcessor preMotionProcessor : (Iterable<PreMotionProcessor>) (this.list)) {
         if (preMotionProcessor.matchesContainerCraftingIInventor(containerCraftingIInventory, world)) {
            return preMotionProcessor.resolveItemStackArray(containerCraftingIInventory);
         }
      }

      ItemStack[] stackArray = new ItemStack[containerCraftingIInventory.processAction4()];

      for (int index = 0; index < stackArray.length; index++) {
         stackArray[index] = containerCraftingIInventory.processAction3(index);
      }

      return stackArray;
   }

   public void applyItemStack(ItemStack stack, Object[] entryArray) {
      ArrayList list2 = Lists.newArrayList();

      for (Object element : entryArray) {
         if (element instanceof ItemStack) {
            list2.add(((ItemStack)element).getItemStack());
         } else if (element instanceof Item) {
            list2.add(new ItemStack((Item)element));
         } else {
            if (!(element instanceof Block)) {
               throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + element.getClass().getName() + "!");
            }

            list2.add(new ItemStack((Block)element));
         }
      }

      this.list.add(new AuxiliaryListenerDispatcher(stack, list2));
   }

   public InternalListenerProvider resolveInternalListenerProvider(ItemStack stack, Object[] entryArray) {
      String text = "";
      int count = 0;
      int count2 = 0;
      int count3 = 0;
      if (entryArray[0] instanceof String[]) {
         String[] textArray = (String[])entryArray[count++];

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            count3++;
            count2 = text2.length();
            text = text + text2;
         }
      } else {
         while (entryArray[count] instanceof String) {
            String text3 = (String)entryArray[count++];
            count3++;
            count2 = text3.length();
            text = text + text3;
         }
      }

      HashMap map;
      for (map = Maps.newHashMap(); count < entryArray.length; count += 2) {
         Character character = (Character)entryArray[count];
         ItemStack stack2 = null;
         if (entryArray[count + 1] instanceof Item) {
            stack2 = new ItemStack((Item)entryArray[count + 1]);
         } else if (entryArray[count + 1] instanceof Block) {
            stack2 = new ItemStack((Block)entryArray[count + 1], 1, 32767);
         } else if (entryArray[count + 1] instanceof ItemStack) {
            stack2 = (ItemStack)entryArray[count + 1];
         }

         map.put(character, stack2);
      }

      ItemStack[] stackArray = new ItemStack[count2 * count3];

      for (int index2 = 0; index2 < count2 * count3; index2++) {
         char character2 = text.charAt(index2);
         if (map.containsKey(character2)) {
            stackArray[index2] = ((ItemStack)map.get(character2)).getItemStack();
         } else {
            stackArray[index2] = null;
         }
      }

      InternalListenerProvider internalListenerProvider = new InternalListenerProvider(count2, count3, stackArray, stack);
      this.list.add(internalListenerProvider);
      return internalListenerProvider;
   }

   public static ExtendedManager getExtendedManager() {
      return extendedManager;
   }

   public void add(PreMotionProcessor preMotionProcessor) {
      this.list.add(preMotionProcessor);
   }

   public List getList() {
      return this.list;
   }

   private ExtendedManager() {
      new AuxiliaryXXXManager().applyExtendedManager(this);
      new TertiaryStringArrayEntry().applyExtendedManager(this);
      new CoreInternalManager().applyExtendedManager(this);
      new CPMManager().applyExtendedManager(this);
      new CustomManager().applyExtendedManager(this);
      new XXXManager().applyExtendedManager(this);
      new TertiaryManager().applyExtendedManager(this);
      this.list.add(new ExternalClientManager());
      this.list.add(new GenerationListener());
      this.list.add(new ExtendedListenerProvider());
      this.list.add(new MapIsScalingListenerAdapter());
      this.list.add(new ExplosionManager());
      this.list.add(new ListenerAdapter9());
      new SecondaryManager().applyExtendedManager(this);
      this.resolveInternalListenerProvider(new ItemStack(Items.paper, 3), new Object[]{"###", '#', Items.reeds});
      this.applyItemStack(new ItemStack(Items.book, 1), new Object[]{Items.paper, Items.paper, Items.paper, Items.leather});
      this.applyItemStack(
         new ItemStack(Items.writableBook, 1), new Object[]{Items.book, new ItemStack(Items.dye, 1, LocalOptionType.BLACK.getCount2()), Items.feather}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block111, 3),
         new Object[]{"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, MovementProperty.OAK.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block27, 3),
         new Object[]{"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, MovementProperty.BIRCH.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block38, 3),
         new Object[]{"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, MovementProperty.SPRUCE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block87, 3),
         new Object[]{"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, MovementProperty.JUNGLE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block97, 3),
         new Object[]{"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, 4 + MovementProperty.ACACIA.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block147, 3),
         new Object[]{"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, 4 + MovementProperty.DARK_OAK.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block63, 6, SpecialOptionType.NORMAL.getCount()), new Object[]{"###", "###", '#', Blocks.block65}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block63, 6, SpecialOptionType.MOSSY.getCount()), new Object[]{"###", "###", '#', Blocks.block131}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block134, 6), new Object[]{"###", "###", '#', Blocks.block86});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block117, 1),
         new Object[]{"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, MovementProperty.OAK.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block73, 1),
         new Object[]{"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, MovementProperty.BIRCH.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block43, 1),
         new Object[]{"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, MovementProperty.SPRUCE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block31, 1),
         new Object[]{"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, MovementProperty.JUNGLE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block116, 1),
         new Object[]{"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, 4 + MovementProperty.ACACIA.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block101, 1),
         new Object[]{"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(Blocks.block142, 1, 4 + MovementProperty.DARK_OAK.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block114, 1), new Object[]{"###", "#X#", "###", '#', Blocks.block142, 'X', Items.diamond});
      this.resolveInternalListenerProvider(new ItemStack(Items.lead, 2), new Object[]{"~~ ", "~O ", "  ~", '~', Items.string, 'O', Items.slimeBall});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block143, 1), new Object[]{"###", "#X#", "###", '#', Blocks.block142, 'X', Items.redstone});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block8, 1), new Object[]{"###", "XXX", "###", '#', Blocks.block142, 'X', Items.book});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block61, 1), new Object[]{"##", "##", '#', Items.snowball});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block74, 6), new Object[]{"###", '#', Blocks.block61});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block123, 1), new Object[]{"##", "##", '#', Items.clayBall});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block100, 1), new Object[]{"##", "##", '#', Items.brick});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block77, 1), new Object[]{"##", "##", '#', Items.glowstoneDust});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block17, 1), new Object[]{"##", "##", '#', Items.quartz});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.wool, 1), new Object[]{"##", "##", '#', Items.string});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block135, 1), new Object[]{"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.blockSand});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter6, 6, IndirectOptionType.COBBLESTONE.getCount()), new Object[]{"###", '#', Blocks.block65}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter6, 6, IndirectOptionType.STONE.getCount()),
         new Object[]{"###", '#', new ItemStack(Blocks.stone, CustomOptionType.STONE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter6, 6, IndirectOptionType.SAND.getCount()), new Object[]{"###", '#', Blocks.block136}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter6, 6, IndirectOptionType.BRICK.getCount()), new Object[]{"###", '#', Blocks.block100}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter6, 6, IndirectOptionType.SMOOTHBRICK.getCount()), new Object[]{"###", '#', Blocks.block42}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter6, 6, IndirectOptionType.NETHERBRICK.getCount()), new Object[]{"###", '#', Blocks.block86}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter6, 6, IndirectOptionType.QUARTZ.getCount()), new Object[]{"###", '#', Blocks.block17}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter4, 6, CoreOptionType.RED_SANDSTONE.getCount()), new Object[]{"###", '#', Blocks.block119}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter2, 6, 0), new Object[]{"###", '#', new ItemStack(Blocks.block142, 1, MovementProperty.OAK.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter2, 6, MovementProperty.BIRCH.getCount()),
         new Object[]{"###", '#', new ItemStack(Blocks.block142, 1, MovementProperty.BIRCH.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter2, 6, MovementProperty.SPRUCE.getCount()),
         new Object[]{"###", '#', new ItemStack(Blocks.block142, 1, MovementProperty.SPRUCE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter2, 6, MovementProperty.JUNGLE.getCount()),
         new Object[]{"###", '#', new ItemStack(Blocks.block142, 1, MovementProperty.JUNGLE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter2, 6, 4 + MovementProperty.ACACIA.getCount() - 4),
         new Object[]{"###", '#', new ItemStack(Blocks.block142, 1, 4 + MovementProperty.ACACIA.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.extraBlockAdapter2, 6, 4 + MovementProperty.DARK_OAK.getCount() - 4),
         new Object[]{"###", '#', new ItemStack(Blocks.block142, 1, 4 + MovementProperty.DARK_OAK.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block81, 3), new Object[]{"# #", "###", "# #", '#', Items.stick});
      this.resolveInternalListenerProvider(
         new ItemStack(Items.woodenDoor, 3), new Object[]{"##", "##", "##", '#', new ItemStack(Blocks.block142, 1, MovementProperty.OAK.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Items.spruceDoor, 3), new Object[]{"##", "##", "##", '#', new ItemStack(Blocks.block142, 1, MovementProperty.SPRUCE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Items.birchDoor, 3), new Object[]{"##", "##", "##", '#', new ItemStack(Blocks.block142, 1, MovementProperty.BIRCH.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Items.jungleDoor, 3), new Object[]{"##", "##", "##", '#', new ItemStack(Blocks.block142, 1, MovementProperty.JUNGLE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Items.acaciaDoor, 3), new Object[]{"##", "##", "##", '#', new ItemStack(Blocks.block142, 1, MovementProperty.ACACIA.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Items.darkOakDoor, 3), new Object[]{"##", "##", "##", '#', new ItemStack(Blocks.block142, 1, MovementProperty.DARK_OAK.getCount())}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block30, 2), new Object[]{"###", "###", '#', Blocks.block142});
      this.resolveInternalListenerProvider(new ItemStack(Items.ironDoor, 3), new Object[]{"##", "##", "##", '#', Items.ironIngot});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block14, 1), new Object[]{"##", "##", '#', Items.ironIngot});
      this.resolveInternalListenerProvider(new ItemStack(Items.sign, 3), new Object[]{"###", "###", " X ", '#', Blocks.block142, 'X', Items.stick});
      this.resolveInternalListenerProvider(
         new ItemStack(Items.cake, 1), new Object[]{"AAA", "BEB", "CCC", 'A', Items.milkBucket, 'B', Items.sugar, 'C', Items.wheat, 'E', Items.egg}
      );
      this.resolveInternalListenerProvider(new ItemStack(Items.sugar, 1), new Object[]{"#", '#', Items.reeds});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block142, 4, MovementProperty.OAK.getCount()),
         new Object[]{"#", '#', new ItemStack(Blocks.block140, 1, MovementProperty.OAK.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block142, 4, MovementProperty.SPRUCE.getCount()),
         new Object[]{"#", '#', new ItemStack(Blocks.block140, 1, MovementProperty.SPRUCE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block142, 4, MovementProperty.BIRCH.getCount()),
         new Object[]{"#", '#', new ItemStack(Blocks.block140, 1, MovementProperty.BIRCH.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block142, 4, MovementProperty.JUNGLE.getCount()),
         new Object[]{"#", '#', new ItemStack(Blocks.block140, 1, MovementProperty.JUNGLE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block142, 4, 4 + MovementProperty.ACACIA.getCount() - 4),
         new Object[]{"#", '#', new ItemStack(Blocks.block104, 1, MovementProperty.ACACIA.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block142, 4, 4 + MovementProperty.DARK_OAK.getCount() - 4),
         new Object[]{"#", '#', new ItemStack(Blocks.block104, 1, MovementProperty.DARK_OAK.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(new ItemStack(Items.stick, 4), new Object[]{"#", "#", '#', Blocks.block142});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block21, 4), new Object[]{"X", "#", 'X', Items.coal, '#', Items.stick});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block21, 4), new Object[]{"X", "#", 'X', new ItemStack(Items.coal, 1, 1), '#', Items.stick});
      this.resolveInternalListenerProvider(new ItemStack(Items.bowl, 4), new Object[]{"# #", " # ", '#', Blocks.block142});
      this.resolveInternalListenerProvider(new ItemStack(Items.glassBottle, 3), new Object[]{"# #", " # ", '#', Blocks.block68});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block13, 16), new Object[]{"X X", "X#X", "X X", 'X', Items.ironIngot, '#', Items.stick});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block50, 6), new Object[]{"X X", "X#X", "XRX", 'X', Items.goldIngot, 'R', Items.redstone, '#', Items.stick}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block106, 6), new Object[]{"XSX", "X#X", "XSX", 'X', Items.ironIngot, '#', Blocks.block12, 'S', Items.stick}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block40, 6), new Object[]{"X X", "X#X", "XRX", 'X', Items.ironIngot, 'R', Items.redstone, '#', Blocks.block23}
      );
      this.resolveInternalListenerProvider(new ItemStack(Items.minecart, 1), new Object[]{"# #", "###", '#', Items.ironIngot});
      this.resolveInternalListenerProvider(new ItemStack(Items.cauldron, 1), new Object[]{"# #", "# #", "###", '#', Items.ironIngot});
      this.resolveInternalListenerProvider(new ItemStack(Items.brewingStand, 1), new Object[]{" B ", "###", '#', Blocks.block65, 'B', Items.blazeRod});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block120, 1), new Object[]{"A", "B", 'A', Blocks.block146, 'B', Blocks.block21});
      this.resolveInternalListenerProvider(new ItemStack(Items.chestMinecart, 1), new Object[]{"A", "B", 'A', Blocks.blockTrappedChest, 'B', Items.minecart});
      this.resolveInternalListenerProvider(new ItemStack(Items.furnaceMinecart, 1), new Object[]{"A", "B", 'A', Blocks.block39, 'B', Items.minecart});
      this.resolveInternalListenerProvider(new ItemStack(Items.tntMinecart, 1), new Object[]{"A", "B", 'A', Blocks.block135, 'B', Items.minecart});
      this.resolveInternalListenerProvider(new ItemStack(Items.hopperMinecart, 1), new Object[]{"A", "B", 'A', Blocks.blockHopper, 'B', Items.minecart});
      this.resolveInternalListenerProvider(new ItemStack(Items.boat, 1), new Object[]{"# #", "###", '#', Blocks.block142});
      this.resolveInternalListenerProvider(new ItemStack(Items.bucket, 1), new Object[]{"# #", " # ", '#', Items.ironIngot});
      this.resolveInternalListenerProvider(new ItemStack(Items.flowerPot, 1), new Object[]{"# #", " # ", '#', Items.brick});
      this.applyItemStack(new ItemStack(Items.flintAndSteel, 1), new Object[]{new ItemStack(Items.ironIngot, 1), new ItemStack(Items.flint, 1)});
      this.resolveInternalListenerProvider(new ItemStack(Items.bread, 1), new Object[]{"###", '#', Items.wheat});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block3, 4), new Object[]{"#  ", "## ", "###", '#', new ItemStack(Blocks.block142, 1, MovementProperty.OAK.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block118, 4), new Object[]{"#  ", "## ", "###", '#', new ItemStack(Blocks.block142, 1, MovementProperty.BIRCH.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block85, 4), new Object[]{"#  ", "## ", "###", '#', new ItemStack(Blocks.block142, 1, MovementProperty.SPRUCE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block18, 4), new Object[]{"#  ", "## ", "###", '#', new ItemStack(Blocks.block142, 1, MovementProperty.JUNGLE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block34, 4),
         new Object[]{"#  ", "## ", "###", '#', new ItemStack(Blocks.block142, 1, 4 + MovementProperty.ACACIA.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block5, 4),
         new Object[]{"#  ", "## ", "###", '#', new ItemStack(Blocks.block142, 1, 4 + MovementProperty.DARK_OAK.getCount() - 4)}
      );
      this.resolveInternalListenerProvider(new ItemStack(Items.fishingRod, 1), new Object[]{"  #", " #X", "# X", '#', Items.stick, 'X', Items.string});
      this.resolveInternalListenerProvider(new ItemStack(Items.carrotOnAStick, 1), new Object[]{"# ", " X", '#', Items.fishingRod, 'X', Items.carrot});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block125, 4), new Object[]{"#  ", "## ", "###", '#', Blocks.block65});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block109, 4), new Object[]{"#  ", "## ", "###", '#', Blocks.block100});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block130, 4), new Object[]{"#  ", "## ", "###", '#', Blocks.block42});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block24, 4), new Object[]{"#  ", "## ", "###", '#', Blocks.block86});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block122, 4), new Object[]{"#  ", "## ", "###", '#', Blocks.block136});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block72, 4), new Object[]{"#  ", "## ", "###", '#', Blocks.block119});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block15, 4), new Object[]{"#  ", "## ", "###", '#', Blocks.block17});
      this.resolveInternalListenerProvider(new ItemStack(Items.painting, 1), new Object[]{"###", "#X#", "###", '#', Items.stick, 'X', Blocks.wool});
      this.resolveInternalListenerProvider(new ItemStack(Items.itemFrame, 1), new Object[]{"###", "#X#", "###", '#', Items.stick, 'X', Items.leather});
      this.resolveInternalListenerProvider(new ItemStack(Items.goldenApple, 1, 0), new Object[]{"###", "#X#", "###", '#', Items.goldIngot, 'X', Items.apple});
      this.resolveInternalListenerProvider(new ItemStack(Items.goldenApple, 1, 1), new Object[]{"###", "#X#", "###", '#', Blocks.block35, 'X', Items.apple});
      this.resolveInternalListenerProvider(new ItemStack(Items.goldenCarrot, 1, 0), new Object[]{"###", "#X#", "###", '#', Items.goldNugget, 'X', Items.carrot});
      this.resolveInternalListenerProvider(new ItemStack(Items.speckledMelon, 1), new Object[]{"###", "#X#", "###", '#', Items.goldNugget, 'X', Items.melon});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block48, 1), new Object[]{"X", "#", '#', Blocks.block65, 'X', Items.stick});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.blockTripwireHook, 2), new Object[]{"I", "S", "#", '#', Blocks.block142, 'S', Items.stick, 'I', Items.ironIngot}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block12, 1), new Object[]{"X", "#", '#', Items.stick, 'X', Items.redstone});
      this.resolveInternalListenerProvider(
         new ItemStack(Items.repeater, 1),
         new Object[]{"#X#", "III", '#', Blocks.block12, 'X', Items.redstone, 'I', new ItemStack(Blocks.stone, 1, CustomOptionType.STONE.getCount())}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Items.comparator, 1),
         new Object[]{" # ", "#X#", "III", '#', Blocks.block12, 'X', Items.quartz, 'I', new ItemStack(Blocks.stone, 1, CustomOptionType.STONE.getCount())}
      );
      this.resolveInternalListenerProvider(new ItemStack(Items.clock, 1), new Object[]{" # ", "#X#", " # ", '#', Items.goldIngot, 'X', Items.redstone});
      this.resolveInternalListenerProvider(new ItemStack(Items.compass, 1), new Object[]{" # ", "#X#", " # ", '#', Items.ironIngot, 'X', Items.redstone});
      this.resolveInternalListenerProvider(new ItemStack(Items.map, 1), new Object[]{"###", "#X#", "###", '#', Items.paper, 'X', Items.compass});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block75, 1), new Object[]{"#", '#', new ItemStack(Blocks.stone, 1, CustomOptionType.STONE.getCount())}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block59, 1), new Object[]{"#", '#', Blocks.block142});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block23, 1), new Object[]{"##", '#', new ItemStack(Blocks.stone, 1, CustomOptionType.STONE.getCount())}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block94, 1), new Object[]{"##", '#', Blocks.block142});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block132, 1), new Object[]{"##", '#', Items.ironIngot});
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block26, 1), new Object[]{"##", '#', Items.goldIngot});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block129, 1), new Object[]{"###", "#X#", "#R#", '#', Blocks.block65, 'X', Items.bow, 'R', Items.redstone}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block69, 1), new Object[]{"###", "# #", "#R#", '#', Blocks.block65, 'R', Items.redstone});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.blockPiston2, 1),
         new Object[]{"TTT", "#X#", "#R#", '#', Blocks.block65, 'X', Items.ironIngot, 'R', Items.redstone, 'T', Blocks.block142}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.blockPiston, 1), new Object[]{"S", "P", 'S', Items.slimeBall, 'P', Blocks.blockPiston2});
      this.resolveInternalListenerProvider(new ItemStack(Items.bed, 1), new Object[]{"###", "XXX", '#', Blocks.wool, 'X', Blocks.block142});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.block102, 1), new Object[]{" B ", "D#D", "###", '#', Blocks.block113, 'B', Items.book, 'D', Items.diamond}
      );
      this.resolveInternalListenerProvider(new ItemStack(Blocks.block51, 1), new Object[]{"III", " i ", "iii", 'I', Blocks.block16, 'i', Items.ironIngot});
      this.resolveInternalListenerProvider(new ItemStack(Items.leather), new Object[]{"##", "##", '#', Items.rabbitHide});
      this.applyItemStack(new ItemStack(Items.enderEye, 1), new Object[]{Items.enderPearl, Items.blazePowder});
      this.applyItemStack(new ItemStack(Items.fireCharge, 3), new Object[]{Items.gunpowder, Items.blazePowder, Items.coal});
      this.applyItemStack(new ItemStack(Items.fireCharge, 3), new Object[]{Items.gunpowder, Items.blazePowder, new ItemStack(Items.coal, 1, 1)});
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.blockDaylightDetectorInverted),
         new Object[]{"GGG", "QQQ", "WWW", 'G', Blocks.block68, 'Q', Items.quartz, 'W', Blocks.extraBlockAdapter2}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Blocks.blockHopper), new Object[]{"I I", "ICI", " I ", 'I', Items.ironIngot, 'C', Blocks.blockTrappedChest}
      );
      this.resolveInternalListenerProvider(
         new ItemStack(Items.armorStand, 1),
         new Object[]{"///", " / ", "/_/", '/', Items.stick, '_', new ItemStack(Blocks.extraBlockAdapter6, 1, IndirectOptionType.STONE.getCount())}
      );
      Collections.sort(this.list, new ComparatorHandler(this));
   }
}
