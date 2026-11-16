package client.setting;

import client.ClientGlobals;
import client.event.SupplementalListener;
import client.font.FontFace;
import client.module.Module;
import client.render.RenderUtils;
import client.util.BooleanSupplier;
import client.util.InternalListenerCoordinator;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;
import org.lwjgl.input.Keyboard;

public class StringSetting extends Setting {
   public int count11;
   public int count12;
   public boolean enabled2;
   public int count13;
   public int count10;
   public final InternalListenerCoordinator internalListenerCoordinator = new InternalListenerCoordinator(this, "K", 0);

   private boolean matchesSetting2(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   private boolean matchesSetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter11().resolveJsonObject(this);
   }

   public StringSetting resolveStringSetting(String text) {
      this.setText4(text);
      return this;
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) throws IOException {
      super.checkIndex(count, count2, count3);
      boolean flag = false;
      if (RenderUtils.checkCondition((float)this.count11, (float)this.count12, (float)this.count13, (float)this.count10, count, count2) && !this.enabled2) {
         this.enabled2 = true;
         this.state5 = true;
         flag = true;
      } else if (this.enabled2) {
         int count4 = this.count13 + 20;
         int count5 = this.count10;
         int count6 = count4;
         int count7 = this.count12;
         int count8 = this.count11;
         if (this.checkIndex(count8, count7, count6, count5, count, count2) && (count3 == 0 || count3 == 1) || count3 > 1) {
            int count9 = count3 + 10000;
            this.processAction(count9);
         }

         this.enabled2 = false;
         this.state5 = false;
      }

      return flag;
   }

   @Override
   public Setting getSetting(String text) {
      return this.resolveStringSetting(text);
   }

   @Override
   public void updateState() {
      this.internalListenerCoordinator.updateState();
   }

   private boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      return count5 >= count && count6 >= count2 && count5 < count + count3 && count6 < count2 + count4;
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter11().applyPostMotionInterceptor(this, jsonObject);
   }

   public StringSetting withBooleanSupplier2(BooleanSupplier booleanSupplier) {
      this.booleanSupplier = booleanSupplier;
      return this;
   }

   public void processAction(int count) {
      InternalListenerCoordinator internalListenerCoordinator2 = this.internalListenerCoordinator;
      Integer count2 = count;
      internalListenerCoordinator2.applyObject2(count2);
   }

   public int getInt3() {
      return (Integer)this.internalListenerCoordinator.getValue();
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      if (this.enabled2) {
         InternalListenerCoordinator internalListenerCoordinator2 = this.internalListenerCoordinator;
         Integer count2 = count == 1 ? -1 : count;
         internalListenerCoordinator2.applyObject2(count2);
         this.enabled2 = false;
         this.state5 = false;
      }
   }

   @Override
   public void updateState3() {
      FontFace fontFace = this.fontFace;
      String text = this.getString() + ": ";
      int scaleFactor = (int)fontFace.getScaleFactor(text);
      this.applyIndex(scaleFactor);
   }

   @Override
   public int getCount10() {
      return 27;
   }

   @Override
   public Setting getSetting(BooleanSupplier booleanSupplier) {
      return this.withBooleanSupplier2(booleanSupplier);
   }

   @Override
   public void applyFontFace(int count, int count2, int count3, int count4, int count5, int count6, FontFace fontFace, boolean flag) {
      super.applyFontFace(count, count2, count3, count4, count5, count6, fontFace, flag);
      if (flag) {
         String text = this.getString()
            + ": "
            + (this.enabled2 ? "..." : (this.getInt3() >= 10000 ? "Mouse" + (this.getInt3() - 10000) : Keyboard.getKeyName(Math.max(this.getInt3(), 0))));
         this.count11 = count3 - 1;
         this.count12 = count4;
         this.count10 = (int)fontFace.getFontSize() + 1;
         this.count13 = (int)fontFace.getScaleFactor(text) + 4;
         double offset = (double)count3;
         double offset2 = (double)count4;
         Color color = new Color(185, 187, 190);
         int count7 = this.count10;
         int count8 = this.count13;
         int count9 = this.count12;
         int count14 = this.count11;
         double offset3 = 0.2;
         Color color2 = color;
         int count15 = this.computeCount(color2, offset3, count, count2, count14, count9, count8, count7);
         double offset4 = offset2;
         double offset5 = offset;
         fontFace.applyString8(text, offset5, offset4, count15);
      }
   }

   @Override
   public void updateState15() {
      this.setState1(true);
      super.updateState15();
   }

   public StringSetting(Module module, String text, SettingGroup settingGroup) {
      super(module, text);
      ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
      settingGroup.getItemList3().removeIf((item -> this.matchesSetting2((Setting)item)));
      settingGroup.getItemList3().add(this);
   }

   public StringSetting(Module module, String text) {
      super(module, text);
      ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
      this.getModule().getSettings().removeIf((item -> this.matchesSetting((Setting)item)));
      this.getModule().getSettings().add(this);
   }
}
