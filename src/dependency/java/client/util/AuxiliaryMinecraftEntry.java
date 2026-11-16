package client.util;

import client.gui.TexturesGuiIconsPngManager;
import client.setting.ClientSettingsBlackType;
import client.setting.CombatConfigProperty;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.GoldenOre;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class AuxiliaryMinecraftEntry extends TexturesGuiIconsPngManager {
   public final Minecraft mc;
   public final FontRenderer fontRenderer;
   public String labelText = null;
   public List list2 = null;
   public List list = null;
   public long timestamp = 0L;
   public long timestamp2 = 0L;

   public List getList() {
      long value = Runtime.getRuntime().maxMemory();
      long value2 = Runtime.getRuntime().totalMemory();
      long value3 = Runtime.getRuntime().freeMemory();
      long value4 = value2 - value3;
      ArrayList list3 = Lists.newArrayList(
         new String[]{
            String.format("Java: %s %dbit", System.getProperty("java.version"), this.mc.isEnabled2() ? 64 : 32),
            String.format("Mem: % 2d%% %03d/%03dMB", value4 * 100L / value, computeTimestamp(value4), computeTimestamp(value)),
            String.format("Allocated: % 2d%% %03dMB", value2 * 100L / value, computeTimestamp(value2)),
            "",
            String.format("CPU: %s", OpenGlHelper.getText()),
            "",
            String.format("Display: %dx%d (%s)", Display.getWidth(), Display.getHeight(), GL11.glGetString(7936)),
            GL11.glGetString(7937),
            GL11.glGetString(7938)
         }
      );
      long timestamp3 = TargetRegistry.getTimestamp();
      long timestampAsTime = TargetRegistry.getTimestampAsTime();
      String text = "Native: " + computeTimestamp(timestamp3) + "/" + computeTimestamp(timestampAsTime) + "MB";
      list3.add(4, text);
      list3.set(5, "GC: " + CoreLongEntry.getTimestampAsTime() + "MB/s");
      if (!this.isActive()
         && this.mc.objectMouseOver != null
         && this.mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK
         && this.mc.objectMouseOver.getBlockPos() != null) {
         BlockPos blockPos = this.mc.objectMouseOver.getBlockPos();
         IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
         if (this.mc.theWorld.getInfoManager() != InfoManager.infoManager4) {
            blockState = blockState.getBlock().processAction(blockState, this.mc.theWorld, blockPos.getX(), blockPos.getY(), blockPos.getZ());
         }

         list3.add("");
         list3.add(String.valueOf(Block.iterableExtensionBridge.getObject2(blockState.getBlock())));
         UnmodifiableIterator iterator = blockState.getImmutableMap().entrySet().iterator();

         while (iterator.hasNext()) {
            Entry entry = (Entry)iterator.next();
            String text2 = ((Comparable)entry.getValue()).toString();
            if (entry.getValue() == Boolean.TRUE) {
               text2 = ClientSettingsBlackType.GREEN + text2;
            } else if (entry.getValue() == Boolean.FALSE) {
               text2 = ClientSettingsBlackType.RED + text2;
            }

            list3.add(((IProperty)entry.getKey()).getLabelText() + ": " + text2);
         }
      }

      return list3;
   }

   private void updateState2() {
      GlStateManager.enableDepth();
      Profiler profilerAsProfiler = this.mc.getProfilerAsProfiler();
      int count = profilerAsProfiler.getCount2();
      int count2 = profilerAsProfiler.getCount3();
      long[] timestampArray = profilerAsProfiler.getTimestampArray();
      ScaledResolution scaledResolution = new ScaledResolution(this.mc);
      int count3 = 0;
      processAction(0, scaledResolution.getScaledHeight() - 60, 240, scaledResolution.getScaledHeight(), -1873784752);

      while (count != count2) {
         int count4 = profilerAsProfiler.computeCount(timestampArray[count], 30);
         int count5 = this.computeCount(MathHelper.getInt12(count4, 0, 60), 0, 30, 60);
         this.applyIndexByIndex(count3, scaledResolution.getScaledHeight(), scaledResolution.getScaledHeight() - count4, count5);
         count3++;
         count = profilerAsProfiler.computeCount(count + 1);
      }

      processAction(1, scaledResolution.getScaledHeight() - 30 + 1, 14, scaledResolution.getScaledHeight() - 30 + 10, -1873784752);
      this.fontRenderer.getIndex("60", 2, scaledResolution.getScaledHeight() - 30 + 2, 14737632);
      this.applyIndex(0, 239, scaledResolution.getScaledHeight() - 30, -1);
      processAction(1, scaledResolution.getScaledHeight() - 60 + 1, 14, scaledResolution.getScaledHeight() - 60 + 10, -1873784752);
      this.fontRenderer.getIndex("30", 2, scaledResolution.getScaledHeight() - 60 + 2, 14737632);
      this.applyIndex(0, 239, scaledResolution.getScaledHeight() - 60, -1);
      this.applyIndex(0, 239, scaledResolution.getScaledHeight() - 1, -1);
      this.applyIndexByIndex(0, scaledResolution.getScaledHeight() - 60, scaledResolution.getScaledHeight(), -1);
      this.applyIndexByIndex(239, scaledResolution.getScaledHeight() - 60, scaledResolution.getScaledHeight(), -1);
      if (this.mc.gameSettings.indexOffset35 <= 120) {
         this.applyIndex(0, 239, scaledResolution.getScaledHeight() - 60 + this.mc.gameSettings.indexOffset35 / 2, -16711681);
      }

      GlStateManager.disableDepth();
   }

   public void applyScaledResolutionByScaledResolution(ScaledResolution scaledResolution) {
      List list3 = this.list;
      if (list3 == null || System.currentTimeMillis() > this.timestamp2) {
         list3 = this.getList();
         this.list = list3;
         this.timestamp2 = System.currentTimeMillis() + 100L;
      }

      for (int index = 0; index < list3.size(); index++) {
         String text = (String)list3.get(index);
         if (!Strings.isNullOrEmpty(text)) {
            int indexOffset = this.fontRenderer.indexOffset;
            int index2 = this.fontRenderer.getIndex(text);
            int count = scaledResolution.getScaledWidth() - 2 - index2;
            int count2 = 2 + indexOffset * index;
            processAction(count - 1, count2 - 1, count + index2 + 1, count2 + indexOffset - 1, -1873784752);
            this.fontRenderer.getIndex(text, count, count2, 14737632);
         }
      }
   }

   private static long computeTimestamp(long value) {
      return value / 1024L / 1024L;
   }

   private boolean isActive() {
      return this.mc.thePlayer.onDisable() || this.mc.gameSettings.state22;
   }

   private int computeCount(int count, int count2, int count3, int count4) {
      return count < count3
         ? this.computeCount(-16711936, -256, (float)count / (float)count3)
         : this.computeCount(-256, -65536, (float)(count - count3) / (float)(count4 - count3));
   }

   public void updateState() {
      List list3 = this.list2;
      if (list3 == null || System.currentTimeMillis() > this.timestamp) {
         list3 = this.getListAsList();
         this.list2 = list3;
         this.timestamp = System.currentTimeMillis() + 100L;
      }

      for (int index = 0; index < list3.size(); index++) {
         String text = (String)list3.get(index);
         if (!Strings.isNullOrEmpty(text)) {
            int indexOffset = this.fontRenderer.indexOffset;
            int index2 = this.fontRenderer.getIndex(text);
            int count = 2 + indexOffset * index;
            processAction(1, count - 1, 2 + index2 + 1, count + indexOffset - 1, -1873784752);
            this.fontRenderer.getIndex(text, 2, count, 14737632);
         }
      }
   }

   public void applyScaledResolution3(ScaledResolution scaledResolution) {
      this.mc.uNKNOWNManager.applyTextByText("debug");
      GlStateManager.pushMatrix();
      this.updateState();
      this.applyScaledResolutionByScaledResolution(scaledResolution);
      GlStateManager.popMatrix();
      if (this.mc.gameSettings.state47 && !this.mc.gameSettings.state80) {
         this.updateState2();
      }

      this.mc.uNKNOWNManager.updateState();
   }

   public List getListAsList() {
      BlockPos blockPos = new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getBoundingBox2().minY, this.mc.getRenderViewEntity().posZ);
      if (!Objects.equals(this.mc.labelText4, this.labelText)) {
         StringBuffer builder = new StringBuffer(this.mc.labelText4);
         int count = BuildManager.getCount9();
         int count2 = this.mc.labelText4.indexOf(" fps ");
         if (count2 >= 0) {
            builder.insert(count2, "/" + count);
         }

         if (BuildManager.isActiveAsFlag()) {
            builder.append(" sf");
         }

         if (BuildManager.canExecute3()) {
            builder.append(" fr");
         }

         if (BuildManager.canExecute2()) {
            builder.append(" af");
         }

         if (BuildManager.canExecute4()) {
            builder.append(" aa");
         }

         if (BuildManager.isActive55()) {
            builder.append(" reg");
         }

         if (BuildManager.canExecute()) {
            builder.append(" sh");
         }

         Minecraft mc2 = this.mc;
         String text = builder.toString();
         mc2.labelText4 = text;
         this.labelText = this.mc.labelText4;
      }

      StringBuilder builder2 = new StringBuilder();
      ListenerAdapter27 listenerAdapter = BuildManager.getListenerAdapter27();
      builder2.append(", A: ");
      if (BitSetHolder.isActive()) {
         builder2.append(listenerAdapter.getCount9() + AnimManager.getCount2());
         builder2.append("/");
      }

      builder2.append(listenerAdapter.size() + AnimManager.getCount());
      String text2 = builder2.toString();
      if (this.isActive()) {
         return Lists.newArrayList(
            new String[]{
               "Minecraft 1.8.9 (" + this.mc.getLabelText3() + "/" + VanillaManager.getText() + ")",
               this.mc.labelText4,
               this.mc.renderGlobal.getTextAsText(),
               this.mc.renderGlobal.getText(),
               "P: " + this.mc.effectRenderer.getText() + ". T: " + this.mc.theWorld.getText() + text2,
               this.mc.theWorld.getTextAsText(),
               "",
               String.format("Chunk-relative: %d %d %d", blockPos.getX() & 15, blockPos.getY() & 15, blockPos.getZ() & 15)
            }
         );
      } else {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         EnumFacing enumFacing = renderViewEntity.getEnumFacing2();
         String text3 = "Invalid";
         switch (TargetBridge.countArray[enumFacing.ordinal()]) {
            case 1:
               text3 = "Towards negative Z";
               break;
            case 2:
               text3 = "Towards positive Z";
               break;
            case 3:
               text3 = "Towards negative X";
               break;
            case 4:
               text3 = "Towards positive X";
         }

         ArrayList list3 = Lists.newArrayList(
            new String[]{
               "Minecraft 1.8.9 (" + this.mc.getLabelText3() + "/" + VanillaManager.getText() + ")",
               this.mc.labelText4,
               this.mc.renderGlobal.getTextAsText(),
               this.mc.renderGlobal.getText(),
               "P: " + this.mc.effectRenderer.getText() + ". T: " + this.mc.theWorld.getText() + text2,
               this.mc.theWorld.getTextAsText(),
               "",
               String.format(
                  "XYZ: %.3f / %.5f / %.3f",
                  this.mc.getRenderViewEntity().posX,
                  this.mc.getRenderViewEntity().getBoundingBox2().minY,
                  this.mc.getRenderViewEntity().posZ
               ),
               String.format("Block: %d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ()),
               String.format(
                  "Chunk: %d %d %d in %d %d %d", blockPos.getX() & 15, blockPos.getY() & 15, blockPos.getZ() & 15, blockPos.getX() >> 4, blockPos.getY() >> 4, blockPos.getZ() >> 4
               ),
               String.format("Facing: %s (%s) (%.1f / %.1f)", enumFacing, text3, MathHelper.sqrt_float(renderViewEntity.rotationYaw), MathHelper.sqrt_float(renderViewEntity.rotationPitch))
            }
         );
         if (this.mc.theWorld != null && this.mc.theWorld.checkBlockPos12(blockPos)) {
            AlertNotificationSound loggerEntry = this.mc.theWorld.getLoggerEntry(blockPos);
            list3.add("Biome: " + loggerEntry.resolveBiomeIDIsOutOfBoundsManager(blockPos.getX(), blockPos.getZ(), this.mc.theWorld.getInvalidBiomeIdManager()).labelText);
            list3.add(
               "Light: "
                  + loggerEntry.computeCount(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0)
                  + " ("
                  + loggerEntry.computeCount(CombatConfigProperty.SKY, blockPos.getX(), blockPos.getY(), blockPos.getZ())
                  + " sky, "
                  + loggerEntry.computeCount(CombatConfigProperty.BLOCK, blockPos.getX(), blockPos.getY(), blockPos.getZ())
                  + " block)"
            );
            GoldenOre goldenOre = this.mc.theWorld.resolveGoldenOre(blockPos);
            if (this.mc.isGamePaused() && this.mc.getIntegratedServer() != null) {
               EntityPlayerAdapter entityPlayerAdapter = this.mc.getIntegratedServer().getMovingManager().get(this.mc.thePlayer.getEntityUniqueID());
               if (entityPlayerAdapter != null) {
                  GoldenOre goldenOre2 = this.mc.getIntegratedServer().resolveGoldenOreByWorld(entityPlayerAdapter.worldObj, new BlockPos(entityPlayerAdapter));
                  if (goldenOre2 != null) {
                     goldenOre = goldenOre2;
                  }
               }
            }

            list3.add(String.format("Local Difficulty: %.2f (Day %d)", goldenOre.getFactorAsFloat(), this.mc.theWorld.getTimestampAsTime() / 24000L));
         }

         if (this.mc.entityRenderer != null && this.mc.entityRenderer.isActive()) {
            list3.add("Shader: " + this.mc.entityRenderer.getPassManager().getLabelText());
         }

         if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK && this.mc.objectMouseOver.getBlockPos() != null) {
            BlockPos blockPos2 = this.mc.objectMouseOver.getBlockPos();
            list3.add(String.format("Looking at: %d %d %d", blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()));
         }

         return list3;
      }
   }

   private int computeCount(int count, int count2, float factor) {
      int count3 = count >> 24 & 0xFF;
      int count4 = count >> 16 & 0xFF;
      int count5 = count >> 8 & 0xFF;
      int count6 = count & 0xFF;
      int count7 = count2 >> 24 & 0xFF;
      int count8 = count2 >> 16 & 0xFF;
      int count9 = count2 >> 8 & 0xFF;
      int count10 = count2 & 0xFF;
      int intItem = MathHelper.getInt12((int)((float)count3 + (float)(count7 - count3) * factor), 0, 255);
      int intItem2 = MathHelper.getInt12((int)((float)count4 + (float)(count8 - count4) * factor), 0, 255);
      int intItem3 = MathHelper.getInt12((int)((float)count5 + (float)(count9 - count5) * factor), 0, 255);
      int intItem4 = MathHelper.getInt12((int)((float)count6 + (float)(count10 - count6) * factor), 0, 255);
      return intItem << 24 | intItem2 << 16 | intItem3 << 8 | intItem4;
   }

   public AuxiliaryMinecraftEntry(Minecraft mc2) {
      this.mc = mc2;
      this.fontRenderer = mc2.fontRendererObj;
   }
}
