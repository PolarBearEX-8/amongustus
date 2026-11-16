package client.util;

import client.setting.GameSettingsPeacefulType;
import client.setting.WorldSettingsGameType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public class RandomSeedManagerAdapter extends RandomSeedManager {
   public final RandomSeedManager randomSeedManager;

   @Override
   public boolean isEnabled4() {
      return this.randomSeedManager.isEnabled4();
   }

   @Override
   public void setEnabled6(boolean enabled) {
   }

   @Override
   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.randomSeedManager.getWorldSettingsGameType();
   }

   @Override
   public void setCount6(int count) {
   }

   @Override
   public int getCount10() {
      return this.randomSeedManager.getCount10();
   }

   @Override
   public void setTimestamp2(long timestamp) {
   }

   public RandomSeedManagerAdapter(RandomSeedManager randomSeedManager2) {
      this.randomSeedManager = randomSeedManager2;
   }

   @Override
   public void setInfoManager(InfoManager infoManager) {
   }

   @Override
   public NBTTagCompound resolveNBTTagCompound(NBTTagCompound tagCompound) {
      return this.randomSeedManager.resolveNBTTagCompound(tagCompound);
   }

   @Override
   public void setGameSettingsPeacefulType2(GameSettingsPeacefulType gameSettingsPeacefulType) {
   }

   @Override
   public NBTTagCompound getNBTTagCompoundAsNBTTagCompound() {
      return this.randomSeedManager.getNBTTagCompoundAsNBTTagCompound();
   }

   @Override
   public void setLabelText(String labelText) {
   }

   @Override
   public long getTimestamp2() {
      return this.randomSeedManager.getTimestamp2();
   }

   @Override
   public int getCount2() {
      return this.randomSeedManager.getCount2();
   }

   @Override
   public long getTimestamp4() {
      return this.randomSeedManager.getTimestamp4();
   }

   @Override
   public boolean isEnabled6() {
      return this.randomSeedManager.isEnabled6();
   }

   @Override
   public boolean isEnabled7() {
      return this.randomSeedManager.isEnabled7();
   }

   @Override
   public InfoManager getInfoManager() {
      return this.randomSeedManager.getInfoManager();
   }

   @Override
   public DoFireTickManager getDoFireTickManager() {
      return this.randomSeedManager.getDoFireTickManager();
   }

   @Override
   public void setEnabled5(boolean enabled) {
   }

   @Override
   public void setCount7(int count) {
   }

   @Override
   public long getTimestamp3() {
      return this.randomSeedManager.getTimestamp3();
   }

   @Override
   public boolean isEnabled3() {
      return this.randomSeedManager.isEnabled3();
   }

   @Override
   public void setCount5(int count) {
   }

   @Override
   public void setCount10(int count) {
   }

   @Override
   public int getCount4() {
      return this.randomSeedManager.getCount4();
   }

   @Override
   public String getLabelText() {
      return this.randomSeedManager.getLabelText();
   }

   @Override
   public int getCount5() {
      return this.randomSeedManager.getCount5();
   }

   @Override
   public int getCount6() {
      return this.randomSeedManager.getCount6();
   }

   @Override
   public int getCount7() {
      return this.randomSeedManager.getCount7();
   }

   @Override
   public NBTTagCompound getNBTTagCompound() {
      return this.randomSeedManager.getNBTTagCompound();
   }

   @Override
   public void setEnabled(boolean enabled) {
   }

   @Override
   public boolean isEnabled() {
      return this.randomSeedManager.isEnabled();
   }

   @Override
   public boolean isEnabled2() {
      return this.randomSeedManager.isEnabled2();
   }

   @Override
   public void applyBlockPos(BlockPos blockPos) {
   }

   @Override
   public void setEnabled4(boolean enabled) {
   }

   @Override
   public void setCount2(int count) {
   }

   @Override
   public long getTimestamp() {
      return this.randomSeedManager.getTimestamp();
   }

   @Override
   public boolean isEnabled5() {
      return this.randomSeedManager.isEnabled5();
   }

   @Override
   public void setCount4(int count) {
   }

   @Override
   public void setEnabled3(boolean enabled) {
   }

   @Override
   public long getTimestamp5() {
      return this.randomSeedManager.getTimestamp5();
   }

   @Override
   public GameSettingsPeacefulType getGameSettingsPeacefulType2() {
      return this.randomSeedManager.getGameSettingsPeacefulType2();
   }

   @Override
   public void applyTime(long value) {
   }
}
