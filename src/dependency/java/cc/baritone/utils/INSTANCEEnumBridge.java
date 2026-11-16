package cc.baritone.utils;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.entity.player.EntityPlayer;

public enum INSTANCEEnumBridge implements AuxiliaryListener10Extension {
    INSTANCE;

   public static final INSTANCEEnumBridge[] ENUM_VALUES = new INSTANCEEnumBridge[]{INSTANCE};

   private static boolean matchesText(String text, EntityPlayer player) {
      return player.getText().equalsIgnoreCase(text);
   }

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return new HeuristicEstimator()
         .concat(resolveList(heuristicSampler).stream().map(player -> ((EntityPlayer)player).getText()))
         .resolveHeuristicEstimatorByText(heuristicSampler.getDepthFirstManager().getText())
         .getHeuristicEstimator2()
         .getStream();
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return this.resolveEntityPlayer(heuristicSampler);
   }

   public EntityPlayer resolveEntityPlayer(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      String text = heuristicSampler.getDepthFirstManager().getText();
      return (EntityPlayer)resolveList(heuristicSampler).stream().filter(player -> matchesText(text, (EntityPlayer)player)).findFirst().orElse(null);
   }

   private static List resolveList(HeuristicSampler heuristicSampler) {
      return heuristicSampler.getActiveListener().getStaticListener().getWorld().itemList7;
   }


}
