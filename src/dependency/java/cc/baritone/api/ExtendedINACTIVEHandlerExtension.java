package cc.baritone.api;

import cc.baritone.utils.HeuristicSystem;
import cc.baritone.utils.PrimaryCustom;
import java.util.stream.Stream;
import net.minecraft.block.Block;

public interface ExtendedINACTIVEHandlerExtension extends INACTIVEHandler {
   default void applyBlockArray(int count, Block[] blockArray) {
      this.applyHeuristicSystem(
         count, new HeuristicSystem(Stream.of(blockArray).map(PrimaryCustom::new).toArray(ExtendedINACTIVEHandlerExtension::resolvePrimaryCustomArray))
      );
   }

   default void applyPrimaryCustomArray(int count, PrimaryCustom[] primaryCustomArray) {
      this.applyHeuristicSystem(count, new HeuristicSystem(primaryCustomArray));
   }

   default void applyBlockArray(Block[] blockArray) {
      this.applyBlockArray(0, blockArray);
   }

   default void applyPrimaryCustomArray(PrimaryCustom[] primaryCustomArray) {
      this.applyPrimaryCustomArray(0, primaryCustomArray);
   }

   void applyTextArray(int count, String[] textArray);

   static PrimaryCustom[] resolvePrimaryCustomArray(int count) {
      return new PrimaryCustom[count];
   }

   default void applyTextArray(String[] textArray) {
      this.applyTextArray(0, textArray);
   }

   default void applyHeuristicSystem(HeuristicSystem heuristicSystem) {
      this.applyHeuristicSystem(0, heuristicSystem);
   }

   void applyHeuristicSystem(int count, HeuristicSystem heuristicSystem);

   default void updateState2() {
      this.updateState();
   }
}
