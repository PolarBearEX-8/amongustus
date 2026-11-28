package net.augustus.modules.render;

import net.minecraft.client.renderer.RenderGlobal;

import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.options.XRayBlockOption;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.events.listeners.NightVisionListener;
import net.augustus.events.EventNightVision;
import net.augustus.events.listeners.TickListener;
import net.augustus.modules.render.helper.XRayRenderMode;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;

public class XRay extends Module implements NightVisionListener, TickListener {
   public static boolean enabled = false;
   private static BooleanValue antiXray;
   private static BooleanValue simple;
   private static DoubleValue minimumFreeSpace;
   private static boolean lastAntiXrayEnabled;
   private static boolean lastSimpleAntiXrayEnabled;
   private static int lastMinFreeSpace;
   private static HashMap blockOptions = new HashMap();
   private boolean reloadRenderersPending;

   public XRay() {
       super("XRay", "Röntgenstrahl", Color.BLUE, Categorys.RENDER);
      this.reloadRenderersPending = false;

      for (ResourceLocation location : (Iterable<ResourceLocation>)(Block.blockRegistry.getKeys())) {
         String resourcePath = location.getResourcePath();
         Block blockFromName = Block.getBlockFromName(resourcePath);
         blockOptions.put(blockFromName, new XRayBlockOption(this, resourcePath, blockFromName instanceof BlockOre || blockFromName instanceof BlockLiquid, null));
      }

      XRayBlockOption[] xRayBlockOptions = (XRayBlockOption[])blockOptions.values().toArray(new XRayBlockOption[0]);
      Arrays.sort(xRayBlockOptions, Comparator.comparing(XRayBlockOption::getName));
      new MultiOptionSetting(this, "Visible Blocks", xRayBlockOptions);
      antiXray = new BooleanValue(this, "Anti-Xray", false);
      BooleanValue booleanValue = new BooleanValue(this, "Simple", true);
      BooleanValue booleanValue2 = antiXray;
      simple = (BooleanValue)booleanValue.withVisibility(() -> booleanValue2.getValue());
      minimumFreeSpace = new DoubleValue(this, "Minimum free space", 1.0, 1.0, 10.0, 0).visibleWhen(() -> isMinFreeSpaceVisible());
   }

   @Override
   public void onEnable() {
      enabled = true;
      mc.renderGlobal.loadRenderers();
      this.snapshotAntiXraySettings();
   }

   @Override
   public void onDisable() {
      enabled = false;
      ((RenderGlobal)null).loadRenderers();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(22);
      this.toggleEventSubscription(40);
   }

   public static XRayRenderMode getBlockRenderMode(Block block, IBlockState state, BlockPos pos) {
      XRayBlockOption xRayBlockOption = (XRayBlockOption)blockOptions.get(block);
      if (xRayBlockOption == null) {
         return XRayRenderMode.HIDE;
      } else if (!xRayBlockOption.selected) {
         return XRayRenderMode.HIDE;
      } else if (antiXray.getValue()) {
         if (simple.getValue()) {
            return XRayRenderMode.NOTHING;
         } else {
            int value = (int)minimumFreeSpace.getValue();
            if (hasTranslucentExposure(pos, EnumFacing.UP, value)) {
               return XRayRenderMode.FORCE_VISIBLE;
            } else {
               if (hasTranslucentExposure(pos, EnumFacing.DOWN, value)) {
                  return XRayRenderMode.FORCE_VISIBLE;
               } else {
                  if (hasTranslucentExposure(pos, EnumFacing.EAST, value)) {
                     return XRayRenderMode.FORCE_VISIBLE;
                  } else {
                     if (hasTranslucentExposure(pos, EnumFacing.WEST, value)) {
                        return XRayRenderMode.FORCE_VISIBLE;
                     } else {
                        if (hasTranslucentExposure(pos, EnumFacing.NORTH, value)) {
                           return XRayRenderMode.FORCE_VISIBLE;
                        } else {
                           return hasTranslucentExposure(pos, EnumFacing.SOUTH, value) ? XRayRenderMode.FORCE_VISIBLE : XRayRenderMode.HIDE;
                        }
                     }
                  }
               }
            }
         }
      } else {
         return XRayRenderMode.FORCE_VISIBLE;
      }
   }

   private static boolean hasTranslucentExposure(BlockPos pos, EnumFacing facing, int n) {

      for (int i = 1; i <= n; i++) {
         int n2 = pos.getY() + i * facing.getFrontOffsetY();
         if (n2 < 0 || n2 > 255) {
            return false;
         }

         int n3 = pos.getX() + facing.getFrontOffsetX() * i;
         int n4 = pos.getZ() + facing.getFrontOffsetZ() * i;
         Chunk chunkFromBlockCoords = mc.theWorld.getChunkFromBlockCoords(n3, n4);
         if (chunkFromBlockCoords.isEmpty()) {
            return false;
         }

         if (!mc.theWorld.getBlockStateFromChunk(chunkFromBlockCoords, n3, pos.getY() + facing.getFrontOffsetY() * i, n4).getBlock().isTranslucent()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public void onNightVision(EventNightVision event) {
      event.setEnabled(true);
   }

   @Override
   public void onFogNightVision(EventNightVision event) {
      event.setEnabled(true);
   }

   @Override
   public void onEarlyTick() {
      if (this.reloadRenderersPending) {
         this.reloadRenderersPending = false;
         mc.renderGlobal.loadRenderers();
      }

      if (antiXray.getValue() != lastAntiXrayEnabled || lastAntiXrayEnabled && (lastSimpleAntiXrayEnabled != simple.getValue() || !lastSimpleAntiXrayEnabled && (double)lastMinFreeSpace != minimumFreeSpace.getValue())) {
         mc.renderGlobal.loadRenderers();
         this.snapshotAntiXraySettings();
      }
   }

   private void snapshotAntiXraySettings() {
      lastMinFreeSpace = (int)minimumFreeSpace.getValue();
      lastAntiXrayEnabled = antiXray.getValue();
      lastSimpleAntiXrayEnabled = simple.getValue();
   }

   private static boolean isMinFreeSpaceVisible() {
      return antiXray.getValue() && !simple.getValue();
   }

   public static boolean setReloadRenderersPending(XRay xRay, boolean flag) {
      return xRay.reloadRenderersPending = flag;
   }


}
