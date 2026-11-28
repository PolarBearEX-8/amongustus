package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAITasks {
   private static final Logger LOGGER = LogManager.getLogger();
   private List taskEntries = Lists.newArrayList();
   private List executingTaskEntries = Lists.newArrayList();
   private final Profiler theProfiler;
   private int tickCount;
   private int tickRate = 3;

   public void addTask(int n, EntityAIBase entityAIBase) {
      this.taskEntries.add(new EntityAITasks.EntityAITaskEntry(this, n, entityAIBase));
   }

   public void removeTask(EntityAIBase entityAIBase) {
      Iterator it = this.taskEntries.iterator();

      while (it.hasNext()) {
         EntityAITasks.EntityAITaskEntry entityAITaskEntry = (EntityAITasks.EntityAITaskEntry)it.next();
         EntityAIBase action = entityAITaskEntry.action;
         if (action == entityAIBase) {
            if (this.executingTaskEntries.contains(entityAITaskEntry)) {
               action.resetTask();
               this.executingTaskEntries.remove(entityAITaskEntry);
            }

            it.remove();
         }
      }
   }

   private boolean areTasksCompatible(EntityAITasks.EntityAITaskEntry entityAITaskEntry, EntityAITasks.EntityAITaskEntry entityAITaskEntry2) {
      return (entityAITaskEntry.action.getMutexBits() & entityAITaskEntry2.action.getMutexBits()) == 0;
   }

   private boolean canUse(EntityAITasks.EntityAITaskEntry entityAITaskEntry) {
      for (EntityAITasks.EntityAITaskEntry entityAITaskEntry2 : (Iterable<EntityAITasks.EntityAITaskEntry>)(this.taskEntries)) {
         if (entityAITaskEntry2 != entityAITaskEntry) {
            if (entityAITaskEntry.priority >= entityAITaskEntry2.priority) {
               if (!this.areTasksCompatible(entityAITaskEntry, entityAITaskEntry2) && this.executingTaskEntries.contains(entityAITaskEntry2)) {
                  return false;
               }
            } else if (!entityAITaskEntry2.action.isInterruptible() && this.executingTaskEntries.contains(entityAITaskEntry2)) {
               return false;
            }
         }
      }

      return true;
   }

   public EntityAITasks(Profiler profiler) {
      this.theProfiler = profiler;
   }

   public void onUpdateTasks() {
      this.theProfiler.startSection("goalSetup");
      if (this.tickCount++ % this.tickRate == 0) {
         for (EntityAITasks.EntityAITaskEntry entityAITaskEntry : (Iterable<EntityAITasks.EntityAITaskEntry>)(this.taskEntries)) {
            if (this.executingTaskEntries.contains(entityAITaskEntry)) {
               if (this.canUse(entityAITaskEntry) && this.canContinue(entityAITaskEntry)) {
                  continue;
               }

               entityAITaskEntry.action.resetTask();
               this.executingTaskEntries.remove(entityAITaskEntry);
            }

            if (this.canUse(entityAITaskEntry) && entityAITaskEntry.action.shouldExecute()) {
               entityAITaskEntry.action.startExecuting();
               this.executingTaskEntries.add(entityAITaskEntry);
            }
         }
      } else {
         Iterator it = this.executingTaskEntries.iterator();

         while (it.hasNext()) {
            EntityAITasks.EntityAITaskEntry entityAITaskEntry2 = (EntityAITasks.EntityAITaskEntry)it.next();
            if (!this.canContinue(entityAITaskEntry2)) {
               entityAITaskEntry2.action.resetTask();
               it.remove();
            }
         }
      }

      this.theProfiler.endSection();
      this.theProfiler.startSection("goalTick");

      for (EntityAITasks.EntityAITaskEntry entityAITaskEntry3 : (Iterable<EntityAITasks.EntityAITaskEntry>)(this.executingTaskEntries)) {
         entityAITaskEntry3.action.updateTask();
      }

      this.theProfiler.endSection();
   }

   private boolean canContinue(EntityAITasks.EntityAITaskEntry entityAITaskEntry) {
      return entityAITaskEntry.action.continueExecuting();
   }


   public static class EntityAITaskEntry {
      public EntityAIBase action;
      public int priority;
      public final EntityAITasks aiTasks;

      public EntityAITaskEntry(EntityAITasks entityAITasks, int n, EntityAIBase entityAIBase) {
         this.aiTasks = entityAITasks;
         this.priority = n;
         this.action = entityAIBase;
      }
   }
}
