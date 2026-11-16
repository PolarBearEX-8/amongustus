package net.minecraft.item;

import client.event.PreMotionBroadcaster;
import client.util.CouldNotFindBoneHandler;
import client.util.CouldNotLoadAnimationManager;
import client.util.ErrorParsingSManager;
import client.util.FunctionEntry;
import client.util.MainControllerManager;
import client.util.SecondaryStringArrayEntry;

public class ItemEntry20Entry {
   public static final DefaultItemEntry defaultItemEntry = new DefaultItemEntry();
   public static final CouldNotLoadAnimationManager couldNotLoadAnimationManager = new CouldNotLoadAnimationManager();
   public static final ErrorParsingSManager errorParsingSManager = new ErrorParsingSManager();

   public static void updateState() {
      SecondaryStringArrayEntry.applyDefaultItemEntry(defaultItemEntry);
      FunctionEntry.add((item -> ItemEntry20Entry.resolveCouldNotFindBoneHandler((PreMotionBroadcaster)item)));
   }

   private static CouldNotFindBoneHandler resolveCouldNotFindBoneHandler(PreMotionBroadcaster preMotionBroadcaster) {
      return preMotionBroadcaster instanceof MainControllerManager ? ((MainControllerManager)preMotionBroadcaster).getCouldNotFindBoneHandlerAdapter() : null;
   }
}
