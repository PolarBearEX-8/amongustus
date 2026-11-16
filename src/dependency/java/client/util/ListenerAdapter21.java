package client.util;

import client.event.CompositeListener;
import client.event.GenericListener;
import client.setting.MovementThresholdProperty;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListenerAdapter21 implements IChunkProvider {
   public static final Logger logger = LogManager.getLogger();
   public Set set = Collections.newSetFromMap(new ConcurrentHashMap());
   public AlertNotificationSound alertNotificationSound;
   public IChunkProvider iChunkProvider;
   public GenericListener genericListener;
   public boolean enabled = true;
   public InternalFloatEntry internalFloatEntry = new InternalFloatEntry();
   public List list = Lists.newArrayList();
   public WorldServer worldServer;

   @Override
   public void applyIChunkProvider(IChunkProvider iChunkProvider2, int count, int count2) {
      AlertNotificationSound alertNotificationSound2 = this.resolveAlertNotificationSound(count, count2);
      if (!alertNotificationSound2.isEnabled7()) {
         alertNotificationSound2.updateState7();
         if (this.iChunkProvider != null) {
            this.iChunkProvider.applyIChunkProvider(iChunkProvider2, count, count2);
            alertNotificationSound2.updateState5();
         }
      }
   }

   @Override
   public void updateState() {
      if (this.genericListener != null) {
         this.genericListener.updateState();
      }
   }

   private AlertNotificationSound resolveAlertNotificationSound2(int count, int count2) {
      if (this.genericListener == null) {
         return null;
      } else {
         try {
            AlertNotificationSound alertNotificationSound2 = this.genericListener.resolveAlertNotificationSound(this.worldServer, count, count2);
            if (alertNotificationSound2 != null) {
               alertNotificationSound2.setTimestamp2(this.worldServer.getTimestamp2());
               if (this.iChunkProvider != null) {
                  this.iChunkProvider.applyAlertNotificationSound(alertNotificationSound2, count, count2);
               }
            }

            return alertNotificationSound2;
         } catch (Exception exception) {
            logger.error("Couldn't load chunk", exception);
            return null;
         }
      }
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(int count, int count2) {
      AlertNotificationSound alertNotificationSound2 = (AlertNotificationSound)this.internalFloatEntry.resolveTarget(InterpolationCollector.computeTimestamp(count, count2));
      return alertNotificationSound2 == null
         ? (!this.worldServer.isState3() && !this.enabled ? this.alertNotificationSound : this.resolveAlertNotificationSoundByIndex(count, count2))
         : alertNotificationSound2;
   }

   public void updateState2() {
      for (Object alertNotificationSound2_o : this.list) {
         AlertNotificationSound alertNotificationSound2 = (AlertNotificationSound)alertNotificationSound2_o;
         this.applyIndex(alertNotificationSound2.indexOffset1, alertNotificationSound2.indexOffset);
      }
   }

   @Override
   public boolean matchesIChunkProvider(IChunkProvider iChunkProvider2, AlertNotificationSound alertNotificationSound2, int count, int count2) {
      if (this.iChunkProvider != null && this.iChunkProvider.matchesIChunkProvider(iChunkProvider2, alertNotificationSound2, count, count2)) {
         this.resolveAlertNotificationSound(count, count2).updateState5();
         return true;
      } else {
         return false;
      }
   }

   private void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound2) {
      if (this.genericListener != null) {
         try {
            alertNotificationSound2.setTimestamp2(this.worldServer.getTimestamp2());
            this.genericListener.applyWorld(this.worldServer, alertNotificationSound2);
         } catch (IOException ioException) {
            logger.error("Couldn't save chunk", ioException);
         } catch (ExceptionAdapter exception) {
            logger.error("Couldn't save chunk; already in use by another instance of Minecraft?", exception);
         }
      }
   }

   public List getList() {
      return this.list;
   }

   public void applyIndex(int count, int count2) {
      if (this.worldServer.secondaryFloatArrayEntry.isActiveAsFlag()) {
         if (!this.worldServer.checkIndex(count, count2)) {
            this.set.add(InterpolationCollector.computeTimestamp(count, count2));
         }
      } else {
         this.set.add(InterpolationCollector.computeTimestamp(count, count2));
      }
   }

   @Override
   public List resolveList(MovementThresholdProperty movementThresholdProperty, BlockPos blockPos) {
      return this.iChunkProvider.resolveList(movementThresholdProperty, blockPos);
   }

   @Override
   public void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound2, int count, int count2) {
   }

   @Override
   public int size() {
      return this.internalFloatEntry.getCount2();
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(BlockPos blockPos) {
      return this.resolveAlertNotificationSound(blockPos.getX() >> 4, blockPos.getZ() >> 4);
   }

   @Override
   public String getText() {
      return "ServerChunkCache: " + this.internalFloatEntry.getCount2() + " Drop: " + this.set.size();
   }

   @Override
   public BlockPos resolveBlockPos(World world, String text, BlockPos blockPos) {
      return this.iChunkProvider.resolveBlockPos(world, text, blockPos);
   }

   @Override
   public boolean isActive() {
      if (!this.worldServer.enabled8) {
         for (int index = 0; index < 100; index++) {
            if (!this.set.isEmpty()) {
               Long value = (Long)this.set.iterator().next();
               AlertNotificationSound alertNotificationSound2 = (AlertNotificationSound)this.internalFloatEntry.resolveTarget(value);
               if (alertNotificationSound2 != null) {
                  alertNotificationSound2.updateState2();
                  this.applyAlertNotificationSound(alertNotificationSound2);
                  this.applyAlertNotificationSoundByAlertNotificatio(alertNotificationSound2);
                  this.internalFloatEntry.resolveTargetByTime(value);
                  this.list.remove(alertNotificationSound2);
               }

               this.set.remove(value);
            }
         }

         if (this.genericListener != null) {
            this.genericListener.updateState2();
         }
      }

      return this.iChunkProvider.isActive();
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.worldServer.enabled8;
   }

   public AlertNotificationSound resolveAlertNotificationSoundByIndex(int count, int count2) {
      long value = InterpolationCollector.computeTimestamp(count, count2);
      this.set.remove(value);
      AlertNotificationSound alertNotificationSound2 = (AlertNotificationSound)this.internalFloatEntry.resolveTarget(value);
      if (alertNotificationSound2 == null) {
         alertNotificationSound2 = this.resolveAlertNotificationSound2(count, count2);
         if (alertNotificationSound2 == null) {
            if (this.iChunkProvider == null) {
               alertNotificationSound2 = this.alertNotificationSound;
            } else {
               try {
                  alertNotificationSound2 = this.iChunkProvider.resolveAlertNotificationSound(count, count2);
               } catch (Throwable throwable) {
                  CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Exception generating new chunk");
                  CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Chunk to be generated");
                  crashReportCategory.add("Location", String.format("%d,%d", count, count2));
                  crashReportCategory.add("Position hash", value);
                  crashReportCategory.add("Generator", this.iChunkProvider.getText());
                  throw new SecondaryRuntimeExceptionAdapter(crashReport);
               }
            }
         }

         this.internalFloatEntry.applyTarget(value, alertNotificationSound2);
         this.list.add(alertNotificationSound2);
         alertNotificationSound2.updateState6();
         alertNotificationSound2.applyIChunkProvider(this, this, count, count2);
      }

      return alertNotificationSound2;
   }

   public ListenerAdapter21(WorldServer worldServer2, GenericListener genericListener2, IChunkProvider iChunkProvider2) {
      this.alertNotificationSound = new LoggerEntryAdapter(worldServer2, 0, 0);
      this.worldServer = worldServer2;
      this.genericListener = genericListener2;
      this.iChunkProvider = iChunkProvider2;
   }

   @Override
   public boolean checkIndex(int count, int count2) {
      return this.internalFloatEntry.checkTime(InterpolationCollector.computeTimestamp(count, count2));
   }

   private void applyAlertNotificationSoundByAlertNotificatio(AlertNotificationSound alertNotificationSound2) {
      if (this.genericListener != null) {
         try {
            this.genericListener.applyWorldByWorld(this.worldServer, alertNotificationSound2);
         } catch (Exception exception) {
            logger.error("Couldn't save entities", exception);
         }
      }
   }

   @Override
   public boolean matchesCompositeListener(boolean flag, CompositeListener compositeListener) {
      int count = 0;
      ArrayList list2 = Lists.newArrayList(this.list);

      for (int index = 0; index < list2.size(); index++) {
         AlertNotificationSound alertNotificationSound2 = (AlertNotificationSound)list2.get(index);
         if (flag) {
            this.applyAlertNotificationSoundByAlertNotificatio(alertNotificationSound2);
         }

         if (alertNotificationSound2.checkFlag(flag)) {
            this.applyAlertNotificationSound(alertNotificationSound2);
            alertNotificationSound2.setEnabledByFlag(false);
            if (++count == 24 && !flag) {
               return false;
            }
         }
      }

      return true;
   }
}
