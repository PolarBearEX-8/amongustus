package cc.baritone.utils;

import cc.Settings;
import cc.baritone.api.AuxiliaryINACTIVEHandlerExtension;
import cc.baritone.api.ExtendedINACTIVEHandlerExtension;
import cc.baritone.api.INACTIVEHandler;
import cc.baritone.api.INACTIVEHandlerExtension;
import cc.baritone.api.InternalINACTIVEHandlerExtension;
import cc.baritone.api.InventoryPauserINACTIVEHandlerAdapter;
import cc.baritone.api.ItemEntry;
import cc.baritone.pathing.PathingCompleteINACTIVEHandlerAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import net.minecraft.client.Minecraft;

public class BaritoneManager implements ActiveListener {
   public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
   public final Minecraft mc;
   public final Path path;
   public final ListenerDispatcher listenerDispatcher;
   public final AlreadyDoingItManager alreadyDoingItManager;
   public final TickListenerAdapter tickListenerAdapter;
   public final RememberingToMoveManager rememberingToMoveManager;
   public final MapEntry mapEntry;
   public final FollowingListener followingListener;
   public final MineINACTIVEHandlerAdapter mineINACTIVEHandlerAdapter;
   public final ExploringRandomlyToFindManager exploringRandomlyToFindManager;
   public final PathingCompleteINACTIVEHandlerAdapter pathingCompleteINACTIVEHandlerAdapter;
   public final BuilderPausedSchematicsHandler builderPausedSchematicsHandler;
   public final ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter;
   public final FarmingListener farmingListener;
   public final InventoryPauserINACTIVEHandlerAdapter inventoryPauserINACTIVEHandlerAdapter;
   public final ListenerHandler listenerHandler;
   public final ListenerBridge listenerBridge;
   public final ItemEntry itemEntry;
   public final StaticListener staticListener;
   public final ReadmeTxtManager readmeTxtManager;
   public HeuristicManager heuristicManager;

   @Override
   public GenericListener getGenericListener() {
      return this.getItemEntry();
   }

   @Override
   public InternalINACTIVEHandlerExtension getInternalINACTIVEHandlerExten() {
      return this.getExploringAroundINACTIVEHandlerAdapter();
   }

   public static Settings getSettings() {
      return SettingsTxtManager.getSettings();
   }

   @Override
   public ExtendedINACTIVEHandlerExtension getExtendedINACTIVEHandlerExten() {
      return this.getMineINACTIVEHandlerAdapter();
   }

   private void updateState2() {
      try {
         Thread.sleep(100L);
         Minecraft mc2 = this.mc;
         Runnable task = this::updateState;
         mc2.resolveListenableFuture(task);
      } catch (Exception exception) {
      }
   }

   public MineINACTIVEHandlerAdapter getMineINACTIVEHandlerAdapter() {
      return this.mineINACTIVEHandlerAdapter;
   }

   public RememberingToMoveManager getRememberingToMoveManager() {
      return this.rememberingToMoveManager;
   }

   @Override
   public Listener getListener() {
      return this.getListenerBridge();
   }

   @Override
   public AuxiliaryINACTIVEHandlerExtension getAuxiliaryINACTIVEHandlerExte() {
      return this.getExploringRandomlyToFindManager();
   }

   @Override
   public LocalListener getLocalListener() {
      return this.getListenerHandler();
   }

   @Override
   public StaticListener getStaticListener() {
      return this.staticListener;
   }

   public ListenerBridge getListenerBridge() {
      return this.listenerBridge;
   }

   public ListenerHandler getListenerHandler() {
      return this.listenerHandler;
   }

   @Override
   public AuxiliaryListener getAuxiliaryListener() {
      return this.getFollowingListener();
   }

   public MapEntry getMapEntry() {
      return this.mapEntry;
   }

   private void updateState() {
      Minecraft mc2 = this.mc;
      HeuristicService heuristicService = new HeuristicService();
      mc2.displayGuiScreen(heuristicService);
   }

   public void applyHeuristicRecorder(HeuristicRecorder heuristicRecorder) {
      this.listenerDispatcher.add(heuristicRecorder);
   }

   @Override
   public CompositeListener getCompositeListener() {
      return this.listenerDispatcher;
   }

   public Path getPath() {
      return this.path;
   }

   public ReadmeTxtManager getReadmeTxtManager() {
      return this.readmeTxtManager;
   }

   public HeuristicRecorder resolveHeuristicRecorder(Function mapper) {
      HeuristicRecorder heuristicRecorder = (HeuristicRecorder)mapper.apply(this);
      this.applyHeuristicRecorder(heuristicRecorder);
      return heuristicRecorder;
   }

   public ExploringAroundINACTIVEHandlerAdapter getExploringAroundINACTIVEHandlerAdapter() {
      return this.exploringAroundINACTIVEHandlerAdapter;
   }

   @Override
   public SupplementalListener getSupplementalListener() {
      return this.getReadmeTxtManager();
   }

   public AlreadyDoingItManager getAlreadyDoingItManager() {
      return this.alreadyDoingItManager;
   }

   public ExploringRandomlyToFindManager getExploringRandomlyToFindManager() {
      return this.exploringRandomlyToFindManager;
   }

   public PathingCompleteINACTIVEHandlerAdapter getPathingCompleteINACTIVEHandlerAdapter() {
      return this.pathingCompleteINACTIVEHandlerAdapter;
   }

   public ItemEntry getItemEntry() {
      return this.itemEntry;
   }

   @Override
   public void start() {
      new Thread(this::updateState2).start();
   }

   public BuilderPausedSchematicsHandler getBuilderPausedSchematicsHandler() {
      return this.builderPausedSchematicsHandler;
   }

   @Override
   public SchematicsHandler getSchematicsHandler() {
      return this.getBuilderPausedSchematicsHandler();
   }

   @Override
   public CoreListener getCoreListener() {
      return this.getAlreadyDoingItManager();
   }

   @Override
   public GlobalListener getGlobalListener() {
      return this.getTickListenerAdapter();
   }

   public static Executor getExecutor() {
      return threadPoolExecutor;
   }

   @Override
   public PassiveListener getPassiveListener() {
      return this.getMapEntry();
   }

   @Override
   public CustomListener getCustomListener() {
      return this.getFarmingListener();
   }

   @Override
   public INACTIVEHandlerExtension getINACTIVEHandlerExtension() {
      return this.getPathingCompleteINACTIVEHandlerAdapter();
   }

   public FarmingListener getFarmingListener() {
      return this.farmingListener;
   }

   public TickListenerAdapter getTickListenerAdapter() {
      return this.tickListenerAdapter;
   }

   public FollowingListener getFollowingListener() {
      return this.followingListener;
   }

   public INACTIVEHandler resolveINACTIVEHandler(Function mapper) {
      INACTIVEHandler iNACTIVEHandler = (INACTIVEHandler)mapper.apply(this);
      this.listenerHandler.applyINACTIVEHandler(iNACTIVEHandler);
      return iNACTIVEHandler;
   }

   public InventoryPauserINACTIVEHandlerAdapter getInventoryPauserINACTIVEHandlerAdapter() {
      return this.inventoryPauserINACTIVEHandlerAdapter;
   }

   public BaritoneManager(Minecraft mc2) {
      this.mc = mc2;
      this.listenerDispatcher = new ListenerDispatcher(this);
      this.path = mc2.mcDataDir.toPath().resolve("baritone");
      if (!Files.exists(this.path)) {
         try {
            Files.createDirectories(this.path);
         } catch (IOException ioException) {
         }
      }

      this.staticListener = new ListenerRegistry(this, mc2);
      this.tickListenerAdapter = (TickListenerAdapter)this.resolveHeuristicRecorder((item -> new TickListenerAdapter((BaritoneManager)item)));
      this.alreadyDoingItManager = (AlreadyDoingItManager)this.resolveHeuristicRecorder((item -> new AlreadyDoingItManager((BaritoneManager)item)));
      this.rememberingToMoveManager = (RememberingToMoveManager)this.resolveHeuristicRecorder((item -> new RememberingToMoveManager((BaritoneManager)item)));
      this.mapEntry = (MapEntry)this.resolveHeuristicRecorder((item -> new MapEntry((BaritoneManager)item)));
      this.resolveHeuristicRecorder((item -> new BedItemEntry((BaritoneManager)item)));
      this.listenerHandler = new ListenerHandler(this);
      this.followingListener = (FollowingListener)this.resolveINACTIVEHandler((item -> new FollowingListener((BaritoneManager)item)));
      this.mineINACTIVEHandlerAdapter = (MineINACTIVEHandlerAdapter)this.resolveINACTIVEHandler((item -> new MineINACTIVEHandlerAdapter((BaritoneManager)item)));
      this.pathingCompleteINACTIVEHandlerAdapter = (PathingCompleteINACTIVEHandlerAdapter)this.resolveINACTIVEHandler(
         (item -> new PathingCompleteINACTIVEHandlerAdapter((BaritoneManager)item))
      );
      this.exploringRandomlyToFindManager = (ExploringRandomlyToFindManager)this.resolveINACTIVEHandler((item -> new ExploringRandomlyToFindManager((BaritoneManager)item)));
      this.builderPausedSchematicsHandler = (BuilderPausedSchematicsHandler)this.resolveINACTIVEHandler((item -> new BuilderPausedSchematicsHandler((BaritoneManager)item)));
      this.exploringAroundINACTIVEHandlerAdapter = (ExploringAroundINACTIVEHandlerAdapter)this.resolveINACTIVEHandler(
         (item -> new ExploringAroundINACTIVEHandlerAdapter((BaritoneManager)item))
      );
      this.farmingListener = (FarmingListener)this.resolveINACTIVEHandler((item -> new FarmingListener((BaritoneManager)item)));
      this.inventoryPauserINACTIVEHandlerAdapter = (InventoryPauserINACTIVEHandlerAdapter)this.resolveINACTIVEHandler(
         (item -> new InventoryPauserINACTIVEHandlerAdapter((BaritoneManager)item))
      );
      this.resolveINACTIVEHandler((item -> new BackfillManager((BaritoneManager)item)));
      this.readmeTxtManager = new ReadmeTxtManager(this);
      this.listenerBridge = new ListenerBridge(this);
      this.itemEntry = new ItemEntry(this);
   }
}
