package client.util;

import java.io.PrintStream;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFire;
import net.minecraft.block.Blocks;
import net.minecraft.item.IronOrb;
import net.minecraft.item.IronOre;
import net.minecraft.item.IronRod;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.VirtualItemEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class STDERRManager {
   public static final PrintStream printStream = System.out;
   public static boolean enabled = false;
   public static final Logger logger = LogManager.getLogger();

   private static void updateState() {
      System.setErr(new ManagerPrintStream("STDERR", System.err));
      System.setOut(new ManagerPrintStream("STDOUT", printStream));
   }

   public static void println(String text) {
      printStream.println(text);
   }

   public static void updateState2() {
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.arrow, new AuxiliaryClientManagerTracker());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.egg, new InternalClientManagerProcessor());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.snowball, new CurveDispatcher());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.experienceBottle, new ClientManagerWrapper());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.potion, new VirtualItemEntry());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.spawnEgg, new CurveObserver());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.fireworks, new CurvePredictor());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.fireCharge, new CurveScheduler());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.boat, new IronRod());
      IronOre ironOre = new IronOre();
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.lavaBucket, ironOre);
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.waterBucket, ironOre);
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.bucket, new IronOrb());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.flintAndSteel, new CurveProfiler());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.dye, new CurveSynchronizer());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Item.processAction2(Blocks.block135), new GameTntPrimedManager());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Items.skull, new SkullOwnerManager());
      BlockDispenser.addingDuplicateKeyManagerAdapter.applyTarget(Item.processAction2(Blocks.block146), new CurveSystem());
   }

   public static boolean isEnabled() {
      return enabled;
   }

   public static void updateState3() {
      if (!enabled) {
         enabled = true;
         if (logger.isDebugEnabled()) {
            updateState();
         }

         Block.updateState();
         BlockFire.updateState2();
         Item.updateState();
         StatCraftItemManager.updateState2();
         updateState2();
      }
   }
}
