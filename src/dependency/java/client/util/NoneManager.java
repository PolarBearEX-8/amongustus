package client.util;

import client.setting.SharedOptionType;
import java.nio.IntBuffer;
import java.util.Arrays;
import net.minecraft.client.renderer.GlAlphaState;

public class NoneManager {
   public final int count4;
   public final String labelText;
   public final SharedOptionType sharedOptionType;
   public final NoneManager noneManager;
   public GlAlphaState glAlphaState;
   public EnabledManager enabledManager;
   public SplineMonitor splineMonitor;
   public final Boolean[] itemArray = new Boolean[8];
   public int count;
   public int count3;
   public String labelText2;
   public IntBuffer intBuffer;
   public IntBuffer intBuffer2;
   public int count5;
   public int count2;
   public final boolean[] enabledArray = new boolean[8];

   public int getCount3() {
      return this.count3;
   }

   public EnabledManager getEnabledManager() {
      return this.enabledManager;
   }

   public Boolean[] getItemArray() {
      return this.itemArray;
   }

   public void setCount(int count6) {
      this.count = count6;
   }

   public NoneManager getNoneManager() {
      return this.noneManager;
   }

   public IntBuffer getIntBuffer2() {
      return this.intBuffer2;
   }

   public boolean[] getEnabledArray() {
      return this.enabledArray;
   }

   public void setCount2(int count6) {
      this.count2 = count6;
   }

   public int getCount4() {
      return this.count4;
   }

   public int getCount() {
      return this.count;
   }

   public void setLabelText2(String labelText3) {
      this.labelText2 = labelText3;
   }

   public void fill() {
      this.glAlphaState = null;
      this.enabledManager = null;
      this.splineMonitor = null;
      Arrays.fill(this.itemArray, null);
   }

   public String getText() {
      if (this.count == 0) {
         return "none";
      } else {
         NoneManager noneManager2;
         for (noneManager2 = this; noneManager2.getCount3() != this.count; noneManager2 = noneManager2.getNoneManager()) {
            if (noneManager2.getNoneManager() == null || noneManager2.getNoneManager() == noneManager2) {
               return "unknown";
            }
         }

         return noneManager2.getLabelText();
      }
   }

   public SharedOptionType getSharedOptionType() {
      return this.sharedOptionType;
   }

   public void setCount5(int count6) {
      this.count5 = count6;
   }

   public void setCount3(int count6) {
      this.count3 = count6;
   }

   public void updateState() {
      this.count = 0;
      this.count3 = 0;
   }

   public int getCount5() {
      return this.count5;
   }

   public void setEnabledManager(EnabledManager enabledManager2) {
      this.enabledManager = enabledManager2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void updateState2() {
      this.labelText2 = null;
      this.count5 = 0;
      this.count2 = 0;
      if (this.intBuffer2 == null) {
         this.intBuffer2 = PreEndHandManager.resolveIntBuffer(8);
      }
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public void applyNoneManager(NoneManager noneManager2) {
      this.count = noneManager2.getCount();
      this.glAlphaState = noneManager2.getGlAlphaState();
      this.enabledManager = noneManager2.getEnabledManager();
      this.splineMonitor = noneManager2.getSplineMonitor();
      System.arraycopy(noneManager2.getItemArray(), 0, this.itemArray, 0, this.itemArray.length);
      this.labelText2 = noneManager2.getLabelText2();
      this.intBuffer = noneManager2.getIntBuffer();
      this.count5 = noneManager2.getCount5();
      this.count2 = noneManager2.getCount2();
      System.arraycopy(noneManager2.getEnabledArray(), 0, this.enabledArray, 0, this.enabledArray.length);
   }

   public IntBuffer getIntBuffer() {
      return this.intBuffer;
   }

   public void setSplineMonitor(SplineMonitor splineMonitor2) {
      this.splineMonitor = splineMonitor2;
   }

   public SplineMonitor getSplineMonitor() {
      return this.splineMonitor;
   }

   public void setIntBuffer(IntBuffer intBuffer3) {
      this.intBuffer = intBuffer3;
   }

   public int getCount2() {
      return this.count2;
   }

   public void setGlAlphaState(GlAlphaState glAlphaState2) {
      this.glAlphaState = glAlphaState2;
   }

   public GlAlphaState getGlAlphaState() {
      return this.glAlphaState;
   }

   public NoneManager(int count6, String text, SharedOptionType sharedOptionType2, boolean flag) {
      this.count4 = count6;
      this.labelText = text;
      this.sharedOptionType = sharedOptionType2;
      this.noneManager = flag ? this : null;
   }

   public NoneManager(int count6, String text, SharedOptionType sharedOptionType2, NoneManager noneManager2) {
      this.count4 = count6;
      this.labelText = text;
      this.sharedOptionType = sharedOptionType2;
      this.noneManager = noneManager2;
   }

   @Override
   public String toString() {
      return "name: " + this.labelText + ", id: " + this.count + ", ref: " + this.count3 + ", real: " + this.getText();
   }
}
