package cc.baritone.utils;

import cc.baritone.api.AuxiliaryItemEntry;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.ClientSettingsShowTextType;
import client.setting.CombatStateProperty;
import client.util.ClickEventActionManager;
import client.util.HoverEventActionManager;
import client.util.IBlockState;
import net.minecraft.block.BlockBed;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

public class BedItemEntry extends AuxiliaryItemEntry {
   @Override
   public void forEach(SecondaryCustom secondaryCustom) {
      if ((Boolean)BaritoneManager.getSettings().doBedWaypoints.dataRef) {
         if (secondaryCustom.getHeuristicSelector() == HeuristicSelector.USE) {
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = BetterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(secondaryCustom.getBlockPos());
            IBlockState blockState = HeuristicManager.resolveIBlockState(this.staticListener, betterBlockPosXSYSZSBlockPos);
            if (blockState.getBlock() instanceof BlockBed) {
               if (blockState.getComparable(BlockBed.immutableSetEntry) == CombatStateProperty.FOOT) {
                  betterBlockPosXSYSZSBlockPos = betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos((EnumFacing)blockState.getComparable(BlockBed.autoWalkPath));
               }

               if (!this.baritoneManager
                  .getReadmeTxtManager()
                  .getDepthFirstEvaluator()
                  .getHeuristicHandler()
                  .resolveSet(BreadthFirstSystem.BED)
                  .stream()
                  .map(listener -> ((ModularListener)listener).getBetterBlockPosXSYSZSBlockPos())
                  .filter(betterBlockPosXSYSZSBlockPos::equals)
                  .findFirst()
                  .isPresent()) {
                  this.baritoneManager
                     .getReadmeTxtManager()
                     .getDepthFirstEvaluator()
                     .getHeuristicHandler()
                     .applyModularListenerByModularListener(new ExtendedCustom("bed", BreadthFirstSystem.BED, betterBlockPosXSYSZSBlockPos));
               }
            }
         }
      }
   }

   @Override
   public void forEach() {
      if ((Boolean)BaritoneManager.getSettings().doDeathWaypoints.dataRef) {
         ExtendedCustom extendedCustom = new ExtendedCustom("death", BreadthFirstSystem.DEATH, this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS());
         this.baritoneManager.getReadmeTxtManager().getDepthFirstEvaluator().getHeuristicHandler().applyModularListenerByModularListener(extendedCustom);
         ChatComponentText chatComponentText = new ChatComponentText("Death position saved.");
         chatComponentText.getStyleHasParentManager()
            .withClientSettingsBlackType(ClientSettingsBlackType.WHITE)
            .getStyleHasParentManager(new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, new ChatComponentText("Click to goto death")))
            .getStyleHasParentManager(
               new ClickEventActionManager(
                  ClientSettingsOpenUrlType.RUN_COMMAND,
                  String.format("%s%s goto %s @ %d", BreadthFirstHandler.labelText, "wp", extendedCustom.getBreadthFirstSystem().getText(), extendedCustom.getTimestamp())
               )
            );
         DRNHandler.dRNHandler.applyIChatComponentArray(new IChatComponent[]{chatComponentText});
      }
   }

   public BedItemEntry(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
