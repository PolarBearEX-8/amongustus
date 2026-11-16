package client.util;

import client.event.CommandsGenericUsageListener;
import client.event.PreUpdateBroadcaster;
import client.setting.ClientSettingsBlackType;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class ListenerAdapter29 extends CommandsGenericUsageListener implements PreUpdateBroadcaster {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, ComparableExtension comparableExtension, int count, String text, Object[] entryArray) {
      boolean flag = true;
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      if (!iCommandSender.isActive()) {
         flag = false;
      }

      BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(
         "chat.type.admin", new Object[]{iCommandSender.getText(), new BaseManagerStyleIChatComponentAdapter(text, entryArray)}
      );
      baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY);
      baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withMemberRef5(Boolean.TRUE);
      if (flag) {
         for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable<?>)vanillaRunnable.getMovingManager().getList()) {
            if (player != iCommandSender && vanillaRunnable.getMovingManager().matchesGameProfileByGameProfile(player.getGameProfile()) && comparableExtension.matchesICommandSender(iCommandSender)) {
               boolean flag2 = iCommandSender instanceof VanillaRunnable && VanillaRunnable.getVanillaRunnable().isActive3();
               boolean flag3 = iCommandSender instanceof DampenerMatcher && VanillaRunnable.getVanillaRunnable().isActive6();
               if (flag2 || flag3 || !(iCommandSender instanceof DampenerMatcher) && !(iCommandSender instanceof VanillaRunnable)) {
                  player.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
               }
            }
         }
      }

      if (iCommandSender != vanillaRunnable && vanillaRunnable.worldServerArray[0].getDoFireTickManager().matchesText("logAdminCommands")) {
         vanillaRunnable.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
      }

      boolean flag4 = vanillaRunnable.worldServerArray[0].getDoFireTickManager().matchesText("sendCommandFeedback");
      if (iCommandSender instanceof VectorDispatcher) {
         flag4 = ((VectorDispatcher)iCommandSender).isEnabled();
      }

      if ((count & 1) != 1 && flag4 || iCommandSender instanceof VanillaRunnable) {
         iCommandSender.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter(text, entryArray));
      }
   }

   public ListenerAdapter29() {
      this.resolveComparableExtension(new TertiaryListener87EntryAdapter());
      this.resolveComparableExtension(new CustomListener87EntryAdapter());
      this.resolveComparableExtension(new CommonListener87EntryAdapter());
      this.resolveComparableExtension(new RayTraceBuilder());
      this.resolveComparableExtension(new NativeListener87EntryAdapter());
      this.resolveComparableExtension(new RayTracePredictor());
      this.resolveComparableExtension(new RayTraceSelector());
      this.resolveComparableExtension(new RayTraceCollector());
      this.resolveComparableExtension(new CompositeListener87EntryAdapter());
      this.resolveComparableExtension(new SupplementalListener87EntryAdapter());
      this.resolveComparableExtension(new CustomMapEntry());
      this.resolveComparableExtension(new RayTraceCoordinator());
      this.resolveComparableExtension(new BaseListener87EntryAdapter());
      this.resolveComparableExtension(new ManagedListener87EntryAdapter());
      this.resolveComparableExtension(new RayTraceRanker());
      this.resolveComparableExtension(new AltListener87EntryAdapter());
      this.resolveComparableExtension(new AdvancedListener87EntryAdapter());
      this.resolveComparableExtension(new RayTraceTracker());
      this.resolveComparableExtension(new PrimaryListener87EntryAdapter());
      this.resolveComparableExtension(new AuxiliaryListener87EntryAdapter());
      this.resolveComparableExtension(new ExtraListener87EntryAdapter());
      this.resolveComparableExtension(new RayTraceScheduler());
      this.resolveComparableExtension(new DynamicListener87EntryAdapter());
      this.resolveComparableExtension(new RayTraceDispatcher());
      this.resolveComparableExtension(new Listener87EntryAdapter());
      this.resolveComparableExtension(new SharedListener87EntryAdapter());
      this.resolveComparableExtension(new RayTraceService());
      this.resolveComparableExtension(new DefaultListener87EntryAdapter());
      this.resolveComparableExtension(new SecondaryListener87EntryAdapter());
      this.resolveComparableExtension(new StandardListener87EntryAdapter());
      this.resolveComparableExtension(new EnhancedListener87EntryAdapter());
      this.resolveComparableExtension(new RayTraceMonitor());
      this.resolveComparableExtension(new GenericListener87EntryAdapter());
      this.resolveComparableExtension(new SpecialListener87EntryAdapter());
      this.resolveComparableExtension(new RayTraceCounter());
      this.resolveComparableExtension(new RayTraceProfiler());
      this.resolveComparableExtension(new CoreListener87EntryAdapter());
      this.resolveComparableExtension(new InternalListener87EntryAdapter());
      this.resolveComparableExtension(new StaticListener87EntryAdapter());
      this.resolveComparableExtension(new RayTraceEngine());
      this.resolveComparableExtension(new ExtendedListener87EntryAdapter());
      this.resolveComparableExtension(new ExtraLoggerEntry());
      this.resolveComparableExtension(new RayTraceSorter());
      if (VanillaRunnable.getVanillaRunnable().isActive5()) {
         this.resolveComparableExtension(new RayTraceObserver());
         this.resolveComparableExtension(new RayTraceFactory());
         this.resolveComparableExtension(new BezierStack());
         this.resolveComparableExtension(new RayTraceProvider());
         this.resolveComparableExtension(new RayTraceWatcher());
         this.resolveComparableExtension(new RayTraceRegistry());
         this.resolveComparableExtension(new RayTraceSynchronizer());
         this.resolveComparableExtension(new RayTracePool());
         this.resolveComparableExtension(new RayTraceDirector());
         this.resolveComparableExtension(new RayTraceAggregator());
         this.resolveComparableExtension(new RayTraceSystem());
         this.resolveComparableExtension(new RayTraceQueue());
         this.resolveComparableExtension(new RayTraceAccumulator());
         this.resolveComparableExtension(new BezierSystem());
         this.resolveComparableExtension(new RayTraceStack());
      } else {
         this.resolveComparableExtension(new RayTraceEstimator());
      }

      Listener87Entry.setPreUpdateBroadcaster(this);
   }
}
