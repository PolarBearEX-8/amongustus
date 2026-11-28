package net.augustus.modules.render;

import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.settings.options.BlockMarkerRenderOption;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.options.BlockMarkerBlockOption;
import net.augustus.modules.render.blockmarker.MarkedBlockCoord;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.TextInputSetting;
import net.augustus.settings.ActionSetting;
import net.augustus.modules.render.blockmarker.MarkedBlockGroup;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.modules.render.blockmarker.BlockMarkerProfile;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public class BlockMarker extends Module implements RenderListener {
   private static final String PROFILES_DIR = ClientPaths.CLIENT_DIR + File.separator + "blockmarker";
   private StringValue scripts;
   private final MultiOptionSetting renderMode;
   private final DoubleValue renderDistance;
   private final ColorSetting color2;
   private final BlurShadowSettings blurShadowSettings;
   private final ActionSetting openFolder;
   private final TextInputSetting nameOfNewScript;
   private final DoubleValue radiusToFill;
   private BlockMarkerProfile blockMarkerProfile;
   private final HashMap blockOptions;

   public BlockMarker() {
       super("BlockMarker", "Block Markierer", new Color(121, 242, 236), Categorys.RENDER);
      StringValue setting = new StringValue(this, "Scripts", "", new String[0]);
      SettingChangeListener listener = () -> {
         String selectedValue = this.scripts.getSelectedValue();
         if (!selectedValue.isEmpty()) {
            BlockMarkerProfile blockMarkerProfile2 = this.loadProfile(selectedValue);
            if (blockMarkerProfile2 != null) {
               this.blockMarkerProfile = blockMarkerProfile2;
            } else {
               this.sendChatMessage("§cFailed to load " + selectedValue + ".json to BlockMarker");
            }
         }
      };
      this.scripts = (StringValue)setting.withChangeListener(listener);
      this.renderMode = new MultiOptionSetting(this, "RenderMode", BlockMarkerRenderOption.values());
      this.renderDistance = new DoubleValue(this, "Render distance", 30.0, 0.0, 200.0, 0);
      this.color2 = new ColorSetting(this, "Color", new Color(54, 250, 202, 100));
      this.blurShadowSettings = (new BlurShadowSettings(this)).disableBlurByDefault();
      this.openFolder = new ActionSetting(this, "Open Folder", () -> openProfilesFolder());
      this.blockMarkerProfile = null;
      this.blockOptions = new HashMap();
      ClientFileUtil.ensureDirectory(PROFILES_DIR);
      this.refreshProfileList();
      this.populateBlockOptions(this.blockOptions);
      BlockMarkerBlockOption[] blockMarkerBlockOptions = (BlockMarkerBlockOption[])this.blockOptions.values().toArray(new BlockMarkerBlockOption[0]);
      Arrays.sort(blockMarkerBlockOptions, Comparator.comparing(blockMarkerBlockOption -> blockMarkerBlockOption.getName()));
      SettingGroup creationTabGroup = new SettingGroup(this, "Creation tab", true);
      this.nameOfNewScript = new TextInputSetting(this, "Name of new script", "ExampleName", creationTabGroup);
      new MultiOptionSetting(this, "Blocks to add to script", blockMarkerBlockOptions, creationTabGroup);
      this.radiusToFill = new DoubleValue(this, "Radius to fill", 100.0, 0.0, 200.0, 0, creationTabGroup);
      new ActionSetting(this, "Create new script", () -> this.createNewProfile(), creationTabGroup);
      new ActionSetting(this, "Add to selected script", () -> this.addBlocksToSelectedProfile(), creationTabGroup);
      new ActionSetting(this, "Delete selected script", () -> this.deleteSelectedProfile(), creationTabGroup);
   }

   @Override
   public void onEnable() {
      this.refreshProfileList();
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.blockMarkerProfile != null) {
         RenderUtil.begin3DRender();
         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderActiveProfilePass(shaderRenderPass));
         RenderUtil.end3DRender();
      }
   }

   private void renderActiveProfilePass(ShaderRenderPass shaderRenderPass) {
      List blockGroups = BlockMarkerProfile.getBlockGroups(this.blockMarkerProfile);
      int n = 0;

      for (int i = 0; i < blockGroups.size(); i++) {
         n = this.renderMarkedBlockGroup((MarkedBlockGroup)blockGroups.get(i), n);
      }
   }

   private void populateBlockOptions(HashMap map) {

      for (ResourceLocation location : (Iterable<ResourceLocation>)(Block.blockRegistry.getKeys())) {
         String resourcePath = location.getResourcePath();
         Block blockFromName = Block.getBlockFromName(resourcePath);
         map.put(blockFromName, new BlockMarkerBlockOption(this, resourcePath, blockFromName));
      }
   }

   private int renderMarkedBlockGroup(MarkedBlockGroup markedBlockGroup, int n) {
      double renderPosX = mc.getRenderManager().getRenderPosX();
      double renderPosY = mc.getRenderManager().getRenderPosY();
      double renderPosZ = mc.getRenderManager().getRenderPosZ();
      double value = this.renderDistance.getValue();
      Block block = MarkedBlockGroup.getBlock(markedBlockGroup);
      boolean flag = BlockMarkerRenderOption.NOT_SAME_NOT_AIR.isSelected() && BlockMarkerRenderOption.SAME_BLOCK.isSelected() && BlockMarkerRenderOption.NOT_SAME_AIR.isSelected();

      for (int i = 0; i < MarkedBlockGroup.getCoords(markedBlockGroup).size(); i++) {
         MarkedBlockCoord markedBlockCoord = (MarkedBlockCoord)MarkedBlockGroup.getCoords(markedBlockGroup).get(i);
         if (mc.thePlayer.getDistanceSq((double)MarkedBlockCoord.getX(markedBlockCoord), (double)MarkedBlockCoord.getY(markedBlockCoord), (double)MarkedBlockCoord.getZ(markedBlockCoord)) < value * value) {
            if (!flag) {
               if (!this.shouldRenderMarkedCoord(block, markedBlockCoord)) {
                  continue;
               }
            }

            RenderUtil.setGlColor(this.color2.getColorInt(n));
            RenderUtil.drawFilledBox(
               (double)MarkedBlockCoord.getX(markedBlockCoord) - renderPosX,
               (double)MarkedBlockCoord.getY(markedBlockCoord) - renderPosY,
               (double)MarkedBlockCoord.getZ(markedBlockCoord) - renderPosZ,
               (double)(MarkedBlockCoord.getX(markedBlockCoord) + 1) - renderPosX,
               (double)(MarkedBlockCoord.getY(markedBlockCoord) + 1) - renderPosY,
               (double)(MarkedBlockCoord.getZ(markedBlockCoord) + 1) - renderPosZ
            );
            n++;
         }
      }

      return n;
   }

   private boolean shouldRenderMarkedCoord(Block block, MarkedBlockCoord markedBlockCoord) {
      Block block2 = mc.theWorld.getBlockState2(MarkedBlockCoord.getX(markedBlockCoord), MarkedBlockCoord.getY(markedBlockCoord), MarkedBlockCoord.getZ(markedBlockCoord)).getBlock();
      if (block2.equals(block) && BlockMarkerRenderOption.SAME_BLOCK.isSelected()) {
         return true;
      } else {
         return !block2.equals(block) && BlockMarkerRenderOption.NOT_SAME_AIR.isSelected() && block2.equals(Blocks.air)
            ? true
            : !block2.equals(block) && BlockMarkerRenderOption.NOT_SAME_NOT_AIR.isSelected() && !block2.equals(Blocks.air);
      }
   }

   private void refreshProfileList() {
      this.scripts.setOptions(this.getAvailableProfileNames());
   }

   public String[] getAvailableProfileNames() {
      java.util.ArrayList list = new java.util.ArrayList();
      File[] files = this.listProfileJsonFiles();
      if (files != null) {
         for (File file : files) {
            String name2 = file.getName();
            list.add(name2.substring(0, name2.length() - 5));
         }

         list.sort(String.CASE_INSENSITIVE_ORDER);
      }

      return (String[])list.toArray(new String[0]);
   }

   public BlockMarkerProfile loadProfile(String str) {
      str = str + ".json";
      File file = new File(PROFILES_DIR + File.separator + str);
      BlockMarkerProfile blockMarkerProfile2 = null;
      if (file.exists()) {
         JsonObject json = ClientFileUtil.readJsonObject(file.getParentFile().getAbsolutePath(), str, (Runnable)null);

         try {
            blockMarkerProfile2 = this.parseProfileJson(json);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      return blockMarkerProfile2;
   }

   private void saveProfile(BlockMarkerProfile blockMarkerProfile2) {
      JsonObject json = new JsonObject();
      BlockMarkerProfile.serializeProfile(blockMarkerProfile2, json);
      ClientFileUtil.writeJsonObject(PROFILES_DIR, BlockMarkerProfile.getName(blockMarkerProfile2) + ".json", json);
   }

   private BlockMarkerProfile buildNewProfileFromWorld() {
      BlockMarkerProfile blockMarkerProfile2 = new BlockMarkerProfile(this, this.nameOfNewScript.getValue());
      this.scanAndMergeIntoProfile(blockMarkerProfile2, (int)Math.max(0.0, this.radiusToFill.getValue()));
      return blockMarkerProfile2;
   }

   private BlockMarkerProfile appendWorldBlocksToActiveProfile() {
      if (this.blockMarkerProfile == null) {
         return null;
      } else {
         this.scanAndMergeIntoProfile(this.blockMarkerProfile, (int)Math.max(0.0, this.radiusToFill.getValue()));
         return this.blockMarkerProfile;
      }
   }

   private void scanAndMergeIntoProfile(BlockMarkerProfile blockMarkerProfile2, int n) {
      Map map = this.createSelectedBlockGroups();
      this.scanWorldBlocksInRadius(map, n);
      if (BlockMarkerProfile.getBlockGroups(blockMarkerProfile2).isEmpty()) {
         BlockMarkerProfile.getBlockGroups(blockMarkerProfile2).addAll(map.values());
      } else {
         this.mergeBlockGroupsIntoProfile(blockMarkerProfile2, map.values());
      }
   }

   private void mergeBlockGroupsIntoProfile(BlockMarkerProfile blockMarkerProfile2, Collection items) {
      HashMap map = new HashMap();

      for (MarkedBlockGroup markedBlockGroup : (Iterable<MarkedBlockGroup>)(BlockMarkerProfile.getBlockGroups(blockMarkerProfile2))) {
         map.put(MarkedBlockGroup.getBlockName(markedBlockGroup), markedBlockGroup);
      }

      for (MarkedBlockGroup markedBlockGroup2 : (Iterable<MarkedBlockGroup>)(items)) {
         MarkedBlockGroup markedBlockGroup3 = (MarkedBlockGroup)map.get(MarkedBlockGroup.getBlockName(markedBlockGroup2));
         if (markedBlockGroup3 != null) {
            this.mergeUniqueBlockCoords(MarkedBlockGroup.getCoords(markedBlockGroup2), MarkedBlockGroup.getCoords(markedBlockGroup3));
         } else {
            BlockMarkerProfile.getBlockGroups(blockMarkerProfile2).add(markedBlockGroup2);
         }
      }
   }

   private void mergeUniqueBlockCoords(List list, List list2) {
      LinkedList list3 = new LinkedList();

      for (MarkedBlockCoord markedBlockCoord : (Iterable<MarkedBlockCoord>)(list)) {
         boolean flag = false;

         for (MarkedBlockCoord markedBlockCoord2 : (Iterable<MarkedBlockCoord>)(list2)) {
            if (MarkedBlockCoord.matchesCoord(markedBlockCoord, markedBlockCoord2)) {
               flag = true;
               break;
            }
         }

         if (!flag) {
            list3.add(markedBlockCoord);
         }
      }

      list2.addAll(list3);
   }

   private Map createSelectedBlockGroups() {
      HashMap map = new HashMap();

      for (BlockMarkerBlockOption blockMarkerBlockOption : (Iterable<BlockMarkerBlockOption>)(this.blockOptions.values())) {
         if (blockMarkerBlockOption.isSelected()) {
            Block block = BlockMarkerBlockOption.getBlock(blockMarkerBlockOption);
            map.put(block, new MarkedBlockGroup(this, ((ResourceLocation)Block.blockRegistry.getNameForObject(block)).getResourcePath(), block));
         }
      }

      return map;
   }

   private void scanWorldBlocksInRadius(Map map, int n) {
      int posX = (int)mc.thePlayer.posX;
      int posY = (int)mc.thePlayer.posY;
      int posZ = (int)mc.thePlayer.posZ;
      int n2 = Math.max(0, posY - n);
      int n3 = Math.min(255, posY + n);

      for (int i = -n + posX; i <= n + posX; i++) {
         for (int j = n2; j <= n3; j++) {
            for (int k = -n + posZ; k <= n + posZ; k++) {
               MarkedBlockGroup markedBlockGroup = (MarkedBlockGroup)map.get(mc.theWorld.getBlockState2(i, j, k).getBlock());
               if (markedBlockGroup != null) {
                  MarkedBlockGroup.getCoords(markedBlockGroup).add(new MarkedBlockCoord(this, i, j, k));
               }
            }
         }
      }
   }

   private BlockMarkerProfile parseProfileJson(JsonObject json) {
      BlockMarkerProfile blockMarkerProfile2 = null;
      if (json != null) {
         try {
            String asString = json.get("Name").getAsString();
            blockMarkerProfile2 = new BlockMarkerProfile(this, asString);
            BlockMarkerProfile.deserializeProfile(blockMarkerProfile2, json.getAsJsonObject("Blocks"));
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      return blockMarkerProfile2;
   }

   public File[] listProfileJsonFiles() {
      File file = new File(PROFILES_DIR);
      if (!file.exists()) {
         return null;
      } else {
         return file.listFiles(file2 -> {
            return file2.isFile() && file2.exists() && file2.getName().endsWith(".json");
         });
      }
   }

   private void deleteSelectedProfile() {
      if (this.blockMarkerProfile != null) {
         File file = new File(PROFILES_DIR + File.separator + BlockMarkerProfile.getName(this.blockMarkerProfile) + ".json");
         if (file.exists()) {
            ClientFileUtil.moveToTrashOrDelete(file);
            this.blockMarkerProfile = null;
         }
      } else {
         this.sendChatMessage("§4No script selected to delete. Please select one.");
      }

      this.refreshProfileList();
   }

   private void addBlocksToSelectedProfile() {
      BlockMarkerProfile blockMarkerProfile2 = this.appendWorldBlocksToActiveProfile();
      if (blockMarkerProfile2 == null) {
         this.sendChatMessage("§4No script selected to add blocks to. Please create a new one.");
      } else {
         this.saveProfile(blockMarkerProfile2);
      }

      this.refreshProfileList();
   }

   private void createNewProfile() {
      if (!this.nameOfNewScript.getValue().isEmpty()) {
         this.saveProfile(this.buildNewProfileFromWorld());
         this.refreshProfileList();
      }
   }

   private static void openProfilesFolder() {
      ClientFileUtil.openFolder(PROFILES_DIR);
   }

}
