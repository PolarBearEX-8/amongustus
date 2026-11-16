package cc.baritone.utils;

import client.util.DampenerProvider;
import client.util.SkippingEntityWithIdManager;
import java.util.stream.Stream;
import net.minecraft.util.ResourceLocation;

public enum InstanceTypeImpl implements AuxiliaryListener10Extension {
   INSTANCE;

   public static final InstanceTypeImpl[] ENUM_VALUES = values();

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return new HeuristicEstimator()
         .concat(SkippingEntityWithIdManager.getList().stream().map(Object::toString))
         .resolveHeuristicEstimator(heuristicSampler.getDepthFirstManager().getText())
         .getHeuristicEstimator2()
         .getStream();
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return this.resolveClass(heuristicSampler);
   }

   public Class resolveClass(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      ResourceLocation location = new ResourceLocation(heuristicSampler.getDepthFirstManager().getText());

      Class clazz;
      try {
         clazz = (Class)SkippingEntityWithIdManager.map.get(location);
      } catch (NoSuchFieldError noSuchFieldError) {
         try {
            Class[] clazzArray = new Class[]{ResourceLocation.class};
            clazz = (Class)SkippingEntityWithIdManager.class
               .getMethod(DampenerProvider.b("getClass", SkippingEntityWithIdManager.class, clazzArray), clazzArray)
               .invoke(null, location);
         } catch (Exception exception) {
            throw new RuntimeException("EntityList.REGISTRY does not exist and failed to call the Forge-replacement method", exception);
         }
      }

      if (clazz == null) {
         throw new IllegalArgumentException("no entity found by that id");
      } else {
         return clazz;
      }
   }
}
