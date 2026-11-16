package cc.baritone.utils;

import java.util.regex.Pattern;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;

public enum ManagedCustom implements AuxiliaryListener10Extension {
   INSTANCE;

   public static Pattern pattern = Pattern.compile("(?:[a-z0-9_.-]+:)?[a-z0-9/_.-]*");
   public static final ManagedCustom[] ENUM_VALUES = values();

   public Block resolveBlock(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      ResourceLocation location = new ResourceLocation(heuristicSampler.getDepthFirstManager().getText());
      Block block;
      if ((block = (Block)Block.iterableExtensionBridge.getObject(location)) == Blocks.air) {
         throw new IllegalArgumentException("no block found by that id");
      } else {
         return block;
      }
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return this.resolveBlock(heuristicSampler);
   }

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      String text = heuristicSampler.getDepthFirstManager().getText();
      return !pattern.matcher(text).matches()
         ? Stream.empty()
         : new HeuristicEstimator()
            .concat(Block.iterableExtensionBridge.getSet().stream().map(Object::toString))
            .resolveHeuristicEstimator(text)
            .getHeuristicEstimator2()
            .getStream();
   }
}
