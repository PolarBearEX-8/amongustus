package baritone.api;

import baritone.api.behavior.ILookBehavior;
import baritone.api.behavior.IPathingBehavior;
import baritone.api.cache.IWorldProvider;
import baritone.api.command.manager.ICommandManager;
import baritone.api.event.listener.IEventBus;
import baritone.api.pathing.calc.IPathingControlManager;
import baritone.api.process.IBuilderProcess;
import baritone.api.process.ICustomGoalProcess;
import baritone.api.process.IExploreProcess;
import baritone.api.process.IFarmProcess;
import baritone.api.process.IFollowProcess;
import baritone.api.process.IGetToBlockProcess;
import baritone.api.process.IMineProcess;
import baritone.api.selection.ISelectionManager;
import baritone.api.utils.IPlayerContext;
import baritone.api.utils.IInputOverrideHandler;

public interface IBaritone {
   IMineProcess getMineProcess();

   ICommandManager getCommandManager();

   IEventBus getGameEventHandler();

   IBuilderProcess getBuilderProcess();

   IWorldProvider getWorldProvider();

   ICustomGoalProcess getCustomGoalProcess();

   IGetToBlockProcess getGetToBlockProcess();

   IFarmProcess getFarmProcess();

   IExploreProcess getExploreProcess();

   IInputOverrideHandler getInputOverrideHandler();

   IPathingControlManager getPathingControlManager();

   ISelectionManager getSelectionManager();

   IPlayerContext getPlayerContext();

   ILookBehavior getLookBehavior();

   IPathingBehavior getPathingBehavior();

   void openClick();

   IFollowProcess getFollowProcess();
}
