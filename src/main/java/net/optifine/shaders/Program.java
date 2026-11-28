package net.optifine.shaders;

import java.nio.IntBuffer;
import java.util.Arrays;
import net.optifine.shaders.config.RenderScale;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;

public class Program {
   private final int index;
   private final String name;
   private final ProgramStage programStage;
   private final Program programBackup;
   private GlAlphaState alphaState;
   private GlBlendState blendState;
   private RenderScale renderScale;
   private final Boolean[] buffersFlip = new Boolean[8];
   private int id;
   private int ref;
   private String drawBufSettings;
   private IntBuffer drawBuffers;
   private IntBuffer drawBuffersBuffer;
   private int compositeMipmapSetting;
   private int countInstances;
   private final boolean[] toggleColorTextures = new boolean[8];

   public void copyFrom(Program program) {
      this.id = program.getId();
      this.alphaState = program.getAlphaState();
      this.blendState = program.getBlendState();
      this.renderScale = program.getRenderScale();
      System.arraycopy(program.getBuffersFlip(), 0, this.buffersFlip, 0, this.buffersFlip.length);
      this.drawBufSettings = program.getDrawBufSettings();
      this.drawBuffers = program.getDrawBuffers();
      this.compositeMipmapSetting = program.getCompositeMipmapSetting();
      this.countInstances = program.getCountInstances();
      System.arraycopy(program.getToggleColorTextures(), 0, this.toggleColorTextures, 0, this.toggleColorTextures.length);
   }

   public Program(int n, String str, ProgramStage programStage2, Program program) {
      this.index = n;
      this.name = str;
      this.programStage = programStage2;
      this.programBackup = program;
   }

   public Boolean[] getBuffersFlip() {
      return this.buffersFlip;
   }

   public String getRealProgramName() {
      if (this.id == 0) {
         return "none";
      } else {
         Program program;
         for (program = this; program.getRef() != this.id; program = program.getProgramBackup()) {
            if (program.getProgramBackup() == null || program.getProgramBackup() == program) {
               return "unknown";
            }
         }

         return program.getName();
      }
   }

   public GlBlendState getBlendState() {
      return this.blendState;
   }

   public boolean[] getToggleColorTextures() {
      return this.toggleColorTextures;
   }

   public int getIndex() {
      return this.index;
   }

   public GlAlphaState getAlphaState() {
      return this.alphaState;
   }

   public void setDrawBuffers(IntBuffer intBuffer) {
      this.drawBuffers = intBuffer;
   }

   public int getId() {
      return this.id;
   }

   public void setCountInstances(int n) {
      this.countInstances = n;
   }

   public String getName() {
      return this.name;
   }

   public Program getProgramBackup() {
      return this.programBackup;
   }

   public void setBlendState(GlBlendState glBlendState) {
      this.blendState = glBlendState;
   }

   public IntBuffer getDrawBuffersBuffer() {
      return this.drawBuffersBuffer;
   }

   public void setRenderScale(RenderScale renderScale2) {
      this.renderScale = renderScale2;
   }

   public int getRef() {
      return this.ref;
   }

   public void resetId() {
      this.id = 0;
      this.ref = 0;
   }

   public void resetConfiguration() {
      this.drawBufSettings = null;
      this.compositeMipmapSetting = 0;
      this.countInstances = 0;
      if (this.drawBuffersBuffer == null) {
         this.drawBuffersBuffer = Shaders.nextIntBuffer(8);
      }
   }

   public int getCountInstances() {
      return this.countInstances;
   }

   @Override
   public String toString() {
      return "name: " + this.name + ", id: " + this.id + ", ref: " + this.ref + ", real: " + this.getRealProgramName();
   }

   public void setId(int n) {
      this.id = n;
   }

   public String getDrawBufSettings() {
      return this.drawBufSettings;
   }

   public void setAlphaState(GlAlphaState glAlphaState) {
      this.alphaState = glAlphaState;
   }

   public void resetProperties() {
      this.alphaState = null;
      this.blendState = null;
      this.renderScale = null;
      Arrays.fill(this.buffersFlip, null);
   }

   public RenderScale getRenderScale() {
      return this.renderScale;
   }

   public ProgramStage getProgramStage() {
      return this.programStage;
   }

   public void setDrawBufSettings(String str) {
      this.drawBufSettings = str;
   }

   public int getCompositeMipmapSetting() {
      return this.compositeMipmapSetting;
   }

   public void setRef(int n) {
      this.ref = n;
   }

   public Program(int n, String str, ProgramStage programStage2, boolean flag) {
      this.index = n;
      this.name = str;
      this.programStage = programStage2;
      this.programBackup = flag ? this : null;
   }

   public IntBuffer getDrawBuffers() {
      return this.drawBuffers;
   }

   public void setCompositeMipmapSetting(int n) {
      this.compositeMipmapSetting = n;
   }
}
