package cc.baritone.api;

import cc.baritone.utils.PrimaryCustom;
import net.minecraft.block.Block;

public interface AuxiliaryINACTIVEHandlerExtension extends INACTIVEHandler {
   void applyPrimaryCustom(PrimaryCustom primaryCustom);

   default void applyBlock(Block block) {
      this.applyPrimaryCustom(new PrimaryCustom(block));
   }

   boolean isActiveAsFlag();
}
