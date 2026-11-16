package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.AuxiliaryListener10Extension;
import cc.baritone.utils.BreadthFirstRegistry;
import cc.baritone.utils.BreadthFirstResolver;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.HeuristicEstimator;
import cc.baritone.utils.NoValidEntitiesInRangeClientManager;
import client.util.SkippingEntityWithIdManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.entity.Entity;

public class FollowCommand extends ExtendedItemEntry {
   private static boolean isInstance(Entity entity, Class clazz) {
      return clazz.isInstance(entity);
   }

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      if (depthFirstManager.isActiveAsFlag()) {
         return new HeuristicEstimator()
            .resolveHeuristicEstimator(BreadthFirstResolver.class)
            .resolveHeuristicEstimator(BreadthFirstRegistry.class)
            .resolveHeuristicEstimatorByText(depthFirstManager.getText())
            .getStream();
      } else {
         AuxiliaryListener10Extension auxiliaryListener10Extension;
         try {
            auxiliaryListener10Extension = ((BreadthFirstRegistry)depthFirstManager.resolveEnum3(BreadthFirstRegistry.class)).auxiliaryListener10Extension;
         } catch (NullPointerException nullPointerException) {
            return Stream.empty();
         }

         while (depthFirstManager.size(2)) {
            if (depthFirstManager.resolveTargetByAuxiliaryListene(auxiliaryListener10Extension) == null) {
               return Stream.empty();
            }

            depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
         }

         return depthFirstManager.resolveStream(auxiliaryListener10Extension);
      }
   }

   private static boolean matchesList(List list, Entity entity) {
      return list.stream().anyMatch(clazz -> FollowCommand.isInstance(entity, (Class)clazz));
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The follow command tells Baritone to follow certain kinds of entities.",
         "",
         "Usage:",
         "> follow entities - Follows all entities.",
         "> follow entity <entity1> <entity2> <...> - Follow certain entities (for example 'skeleton', 'horse' etc.)",
         "> follow players - Follow players",
         "> follow player <username1> <username2> <...> - Follow certain players"
      );
   }

   @Override
   public String getLabelText2() {
      return "Follow entity things";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndex2(1);
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();
      BreadthFirstResolver breadthFirstResolver;
      if (depthFirstManager.isActiveAsFlag()) {
         this.activeListener.getAuxiliaryListener().setPredicate((breadthFirstResolver = (BreadthFirstResolver)depthFirstManager.resolveEnum3(BreadthFirstResolver.class)).predicate);
      } else {
         depthFirstManager.applyIndex2(2);
         breadthFirstResolver = null;
         BreadthFirstRegistry breadthFirstRegistry = (BreadthFirstRegistry)depthFirstManager.resolveEnum3(BreadthFirstRegistry.class);

         while (depthFirstManager.isActive()) {
            Object entry = depthFirstManager.resolveTarget2(breadthFirstRegistry.auxiliaryListener10Extension);
            if (entry instanceof Class) {
               list2.add((Class)entry);
            } else if (entry != null) {
               list.add((Entity)entry);
            }
         }

         this.activeListener.getAuxiliaryListener().setPredicate(list2.isEmpty() ? list::contains : entity -> FollowCommand.matchesList(list2, (Entity)entity));
      }

      if (breadthFirstResolver != null) {
         this.applyText(String.format("Following all %s", breadthFirstResolver.name().toLowerCase(Locale.US)));
      } else if (list2.isEmpty()) {
         if (list.isEmpty()) {
            throw new NoValidEntitiesInRangeClientManager();
         }

         this.applyText("Following these entities:");
         list.stream().map(entity -> ((Entity)entity).toString()).forEach(line -> this.applyText((String)line));
      } else {
         this.applyText("Following these types of entities:");
         list2.stream().map((item -> SkippingEntityWithIdManager.get((Class)item))).map(Objects::requireNonNull).forEach(line -> this.applyText((String)line));
      }
   }

   public FollowCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"follow"});
   }
}
