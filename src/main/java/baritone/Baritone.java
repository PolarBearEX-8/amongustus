package baritone;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.Settings;
import baritone.api.behavior.IBehavior;
import baritone.api.behavior.ILookBehavior;
import baritone.api.behavior.IPathingBehavior;
import baritone.api.cache.IWorldProvider;
import baritone.api.command.manager.ICommandManager;
import baritone.api.event.listener.IEventBus;
import baritone.api.pathing.calc.IPathingControlManager;
import baritone.api.process.IBaritoneProcess;
import baritone.api.process.IBuilderProcess;
import baritone.api.process.ICustomGoalProcess;
import baritone.api.process.IExploreProcess;
import baritone.api.process.IFarmProcess;
import baritone.api.process.IFollowProcess;
import baritone.api.process.IGetToBlockProcess;
import baritone.api.process.IMineProcess;
import baritone.api.selection.ISelectionManager;
import baritone.api.utils.IPlayerContext;
import baritone.behavior.InventoryBehavior;
import baritone.behavior.LookBehavior;
import baritone.behavior.PathingBehavior;
import baritone.behavior.WaypointBehavior;
import baritone.cache.WorldProvider;
import baritone.command.manager.CommandManager;
import baritone.event.GameEventHandler;
import baritone.process.InventoryPauserProcess;
import baritone.utils.player.BaritonePlayerContext;
import baritone.api.utils.IInputOverrideHandler;
import baritone.process.BackfillProcess;
import baritone.process.BuilderProcess;
import baritone.process.CustomGoalProcess;
import baritone.process.ExploreProcess;
import baritone.process.FarmProcess;
import baritone.process.FollowProcess;
import baritone.process.GetToBlockProcess;
import baritone.process.MineProcess;
import baritone.selection.SelectionManager;
import baritone.utils.BlockStateInterface;
import baritone.utils.GuiClick;
import baritone.utils.InputOverrideHandler;
import baritone.utils.PathingControlManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import net.minecraft.client.Minecraft;

public class Baritone implements IBaritone {
   private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
   private final Minecraft mc;
   private final Path directory;
   private final GameEventHandler gameEventHandler;
   private final PathingBehavior pathingBehavior;
   private final LookBehavior lookBehavior;
   private final InventoryBehavior inventoryBehavior;
   private final InputOverrideHandler inputOverrideHandler;
   private final FollowProcess followProcess;
   private final MineProcess mineProcess;
   private final GetToBlockProcess getToBlockProcess;
   private final CustomGoalProcess customGoalProcess;
   private final BuilderProcess builderProcess;
   private final ExploreProcess exploreProcess;
   private final FarmProcess farmProcess;
   private final InventoryPauserProcess inventoryPauserProcess;
   private final PathingControlManager pathingControlManager;
   private final SelectionManager selectionManager;
   private final CommandManager commandManager;
   private final IPlayerContext playerContext;
   private final WorldProvider worldProvider;
   public BlockStateInterface bsi;

   @Override
   public IGetToBlockProcess getGetToBlockProcess() {
      return this.getGetToBlockProcess2();
   }

   public SelectionManager getSelectionManager2() {
      return this.selectionManager;
   }

   public static Executor getThreadPool() {
      return threadPool;
   }

   @Override
   public ILookBehavior getLookBehavior() {
      return this.getLookBehavior2();
   }

   @Override
   public ICustomGoalProcess getCustomGoalProcess() {
      return this.getCustomGoalProcess2();
   }

   public void registerBehavior(IBehavior behavior) {
      this.gameEventHandler.registerEventListener(behavior);
   }

   public InventoryBehavior getInventoryBehavior() {
      return this.inventoryBehavior;
   }

   @Override
   public IBuilderProcess getBuilderProcess() {
      return this.getBuilderProcess2();
   }

   public MineProcess getMineProcess2() {
      return this.mineProcess;
   }

   public LookBehavior getLookBehavior2() {
      return this.lookBehavior;
   }

   public FollowProcess getFollowProcess2() {
      return this.followProcess;
   }

   public ExploreProcess getExploreProcess2() {
      return this.exploreProcess;
   }

   @Override
   public ISelectionManager getSelectionManager() {
      return this.getSelectionManager2();
   }

   public WorldProvider getWorldProvider2() {
      return this.worldProvider;
   }

   public BuilderProcess getBuilderProcess2() {
      return this.builderProcess;
   }

   @Override
   public IPathingBehavior getPathingBehavior() {
      return this.getPathingBehavior2();
   }

   public InputOverrideHandler getInputOverrideHandler2() {
      return this.inputOverrideHandler;
   }

   public IBaritoneProcess registerProcess(Function<Baritone, ? extends IBaritoneProcess> function) {
      IBaritoneProcess baritoneProcess = (IBaritoneProcess)function.apply(this);
      this.pathingControlManager.registerProcess(baritoneProcess);
      return baritoneProcess;
   }

   public GetToBlockProcess getGetToBlockProcess2() {
      return this.getToBlockProcess;
   }

   @Override
   public IFollowProcess getFollowProcess() {
      return this.getFollowProcess2();
   }

   @Override
   public IMineProcess getMineProcess() {
      return this.getMineProcess2();
   }

   @Override
   public IExploreProcess getExploreProcess() {
      return this.getExploreProcess2();
   }

   @Override
   public void openClick() {
      new Thread(() -> {
         try {
            Thread.sleep(100L);
            Minecraft mc2 = this.mc;
            Runnable runnable = () -> {
               this.mc.displayGuiScreen(new GuiClick());
            };
            mc2.addScheduledTask(runnable);
         } catch (Exception e) {
         }
      }).start();
   }

   public CommandManager getCommandManager2() {
      return this.commandManager;
   }

   public CustomGoalProcess getCustomGoalProcess2() {
      return this.customGoalProcess;
   }

   @Override
   public IPathingControlManager getPathingControlManager() {
      return this.getPathingControlManager2();
   }

   public PathingBehavior getPathingBehavior2() {
      return this.pathingBehavior;
   }

   public InventoryPauserProcess getInventoryPauserProcess() {
      return this.inventoryPauserProcess;
   }

   public IBehavior registerBehavior2(Function<Baritone, ? extends IBehavior> function) {
      IBehavior behavior = (IBehavior)function.apply(this);
      this.registerBehavior(behavior);
      return behavior;
   }

   @Override
   public IFarmProcess getFarmProcess() {
      return this.getFarmProcess2();
   }

   @Override
   public IEventBus getGameEventHandler() {
      return this.gameEventHandler;
   }

   public Path getDirectory() {
      return this.directory;
   }

   public static Settings settings() {
      return BaritoneAPI.getSettings();
   }

   public PathingControlManager getPathingControlManager2() {
      return this.pathingControlManager;
   }

   @Override
   public IInputOverrideHandler getInputOverrideHandler() {
      return this.getInputOverrideHandler2();
   }

   public FarmProcess getFarmProcess2() {
      return this.farmProcess;
   }

   @Override
   public IWorldProvider getWorldProvider() {
      return this.getWorldProvider2();
   }

   public Baritone(Minecraft mc2) {
      this.mc = mc2;
      this.gameEventHandler = new GameEventHandler(this);
      this.directory = mc2.mcDataDir.toPath().resolve("baritone");
      if (!Files.exists(this.directory)) {
         try {
            Files.createDirectories(this.directory);
         } catch (IOException e) {
         }
      }

      this.playerContext = new BaritonePlayerContext(this, mc2);
      this.lookBehavior = (LookBehavior)this.registerBehavior2(LookBehavior::new);
      this.pathingBehavior = (PathingBehavior)this.registerBehavior2(PathingBehavior::new);
      this.inventoryBehavior = (InventoryBehavior)this.registerBehavior2(InventoryBehavior::new);
      this.inputOverrideHandler = (InputOverrideHandler)this.registerBehavior2(InputOverrideHandler::new);
      this.registerBehavior2(WaypointBehavior::new);
      this.pathingControlManager = new PathingControlManager(this);
      this.followProcess = (FollowProcess)this.registerProcess(FollowProcess::new);
      this.mineProcess = (MineProcess)this.registerProcess(MineProcess::new);
      this.customGoalProcess = (CustomGoalProcess)this.registerProcess(CustomGoalProcess::new);
      this.getToBlockProcess = (GetToBlockProcess)this.registerProcess(GetToBlockProcess::new);
      this.builderProcess = (BuilderProcess)this.registerProcess(BuilderProcess::new);
      this.exploreProcess = (ExploreProcess)this.registerProcess(ExploreProcess::new);
      this.farmProcess = (FarmProcess)this.registerProcess(FarmProcess::new);
      this.inventoryPauserProcess = (InventoryPauserProcess)this.registerProcess(InventoryPauserProcess::new);
      this.registerProcess(BackfillProcess::new);
      this.worldProvider = new WorldProvider(this);
      this.selectionManager = new SelectionManager(this);
      this.commandManager = new CommandManager(this);
   }

   @Override
   public IPlayerContext getPlayerContext() {
      return this.playerContext;
   }

   @Override
   public ICommandManager getCommandManager() {
      return this.getCommandManager2();
   }

}
