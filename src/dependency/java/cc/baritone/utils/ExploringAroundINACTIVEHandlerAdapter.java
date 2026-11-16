package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandlerAdapter;
import cc.baritone.api.InternalINACTIVEHandlerExtension;
import java.nio.file.Path;
import net.minecraft.util.BlockPos;

public final class ExploringAroundINACTIVEHandlerAdapter extends INACTIVEHandlerAdapter implements InternalINACTIVEHandlerExtension {
   public BlockPos blockPos;
   public SystemListener systemListener;
   public int count;

   @Override
   public String getText() {
      return "Exploring around "
         + this.blockPos
         + ", distance completed "
         + this.count
         + ", currently going to "
         + new DepthFirstCoordinator(this.resolveSecondaryListenerArray(this.blockPos, this.getSystemListener()));
   }

   public SystemListener getSystemListener() {
      Object entry;
      if (this.systemListener != null) {
         entry = new AStarController(this, this.systemListener, new AStarMonitor(this, null), null);
      } else {
         entry = new AStarMonitor(this, null);
      }

      return (SystemListener)entry;
   }

   @Override
   public void applyPath(Path path, boolean flag) throws Exception {
      this.systemListener = new AStarNavigator(this, path, flag, null);
   }

   private static SecondaryListener[] resolveSecondaryListenerArray(int count2) {
      return new SecondaryListener[count2];
   }

   private static SecondaryListener resolveSecondaryListener(int count2, int count3) {
      return (SecondaryListener)((Integer)BaritoneManager.getSettings().exploreMaintainY.dataRef == -1
         ? new DiagonalEvaluator(count2, count3)
         : new HeuristicPolicy(count2, count3));
   }

   @Override
   public void updateState() {
      this.blockPos = null;
   }

   @Override
   public boolean isEnabled() {
      return this.blockPos != null;
   }

   private static SecondaryListener resolveSecondaryListener(BlockPos blockPos2) {
      return resolveSecondaryListener(blockPos2.getX(), blockPos2.getZ());
   }

   @Override
   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      if (flag) {
         this.applyText("Failed");
         if ((Boolean)BaritoneManager.getSettings().notificationOnExploreFinished.dataRef) {
            this.applyText("Exploration failed", true);
         }

         this.updateState();
         return null;
      } else {
         SystemListener systemListener2 = this.getSystemListener();
         if (!(Boolean)BaritoneManager.getSettings().disableCompletionCheck.dataRef && systemListener2.getCount() == 0) {
            this.applyText("Explored all chunks");
            if ((Boolean)BaritoneManager.getSettings().notificationOnExploreFinished.dataRef) {
               this.applyText("Explored all chunks", false);
            }

            this.updateState();
            return null;
         } else {
            SecondaryListener[] secondaryListenerArray = this.resolveSecondaryListenerArray(this.blockPos, systemListener2);
            if (secondaryListenerArray == null) {
               this.applyTextByText("awaiting region load from disk");
               return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
            } else {
               return new Listener5Entry(new DepthFirstCoordinator(secondaryListenerArray), HeuristicCoordinator.FORCE_REVALIDATE_GOAL_AND_PATH);
            }
         }
      }
   }

   public static BaritoneManager resolveBaritoneManager(ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter) {
      return exploringAroundINACTIVEHandlerAdapter.baritoneManager;
   }

   @Override
   public void applyIndex(int count2, int count3) {
      this.blockPos = new BlockPos(count2, 0, count3);
      this.count = 0;
   }

   private SecondaryListener[] resolveSecondaryListenerArray(BlockPos blockPos2, SystemListener systemListener2) {
      int count2 = blockPos2.getX() >> 4;
      int count3 = blockPos2.getZ() >> 4;
      int count4 = Math.min(systemListener2.getCount(), (Integer)BaritoneManager.getSettings().exploreChunkSetMinimumSize.dataRef);
      java.util.ArrayList list = new java.util.ArrayList();
      int dataRef = (Integer)BaritoneManager.getSettings().worldExploringChunkOffset.dataRef;
      int count5 = this.count;

      while (true) {
         for (int index = -count5; index <= count5; index++) {
            int count6 = count5 - Math.abs(index);
            int count7 = 0;

            while (count7 < 2) {
               int count8 = (count7 * 2 - 1) * count6;
               if (Math.abs(index) + Math.abs(count8) != count5) {
                  throw new IllegalStateException();
               }

               switch (AStarEngine.countArray[systemListener2.resolveDepthFirstPlanner(count2 + index, count3 + count8).ordinal()]) {
                  case 1:
                     return null;
                  case 2:
                  default:
                     int count9 = (count2 + index << 4) + 8;
                     int count10 = (count3 + count8 << 4) + 8;
                     int count11 = dataRef << 4;
                     int count12;
                     if (index < 0) {
                        count12 = count9 - count11;
                     } else {
                        count12 = count9 + count11;
                     }

                     int count13;
                     if (count8 < 0) {
                        count13 = count10 - count11;
                     } else {
                        count13 = count10 + count11;
                     }

                     list.add(new BlockPos(count12, 0, count13));
                  case 3:
                     count7++;
               }
            }
         }

         if (count5 % 10 == 0) {
            count4 = Math.min(systemListener2.getCount(), (Integer)BaritoneManager.getSettings().exploreChunkSetMinimumSize.dataRef);
         }

         if (list.size() >= count4) {
            return (SecondaryListener[])list.stream()
               .map((item -> ExploringAroundINACTIVEHandlerAdapter.resolveSecondaryListener((BlockPos)item)))
               .toArray(ExploringAroundINACTIVEHandlerAdapter::resolveSecondaryListenerArray);
         }

         if (list.isEmpty()) {
            this.count = count5 + 1;
         }

         count5++;
      }
   }

   public ExploringAroundINACTIVEHandlerAdapter(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
