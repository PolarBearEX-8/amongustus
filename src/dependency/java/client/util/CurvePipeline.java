package client.util;

import client.Client;
import client.font.FontFace;
import client.gui.ClickGuiPanel;
import client.render.RenderUtils;
import java.awt.Color;
import java.io.IOException;

public class CurvePipeline {
   public String labelText3 = "";
   public int count4;
   public int count2;
   public int count3;
   public FontFace fontFace;
   public final TimeHelper timer;
   public int count7;
   public int count5;
   public final boolean enabled3;
   public String labelText2 = "";
   public int count;
   public int count9;
   public final TimeHelper timer2;
   public boolean enabled;
   public boolean enabled2;
   public String labelText = "";
   public int count8;
   public int count6;
   public final TimeHelper timer3;

   public void applyIndexByIndex(int count10, int count11, int count12) {
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public int getCount5() {
      return this.count5;
   }

   public FontFace getFontFace() {
      return this.fontFace;
   }

   public void applyIndex(int count10, int count11, int count12) {
   }

   public int getCount3() {
      return this.count3;
   }

   private void updateState() {
      this.timer2.reset();
      String text = this.enabled3 ? "onlineConfigs" : "configs";
      String labelText4 = this.labelText3;
      Client.getClient().getAltItemEntry().baseHashMapEntry.applyText(labelText4, text);
   }

   public int getCount7() {
      return this.count7;
   }

   public void applyIndex2(int count10, int count11, int count12) throws IOException {
      if (count12 == 0 && this.fontFace != null) {
         float factor = (float)(this.count3 - 5);
         float count13 = (float)this.count;
         String labelText4 = this.labelText3;
         if (RenderUtils.checkCondition(factor, count13, (float)((int)(this.fontFace.getScaleFactor(labelText4) + 10.0F)), (float)(this.count7 + 2), count10, count11)) {
            if (!this.timer.hasReached(500L) && this.timer2.hasReached(1000L)) {
               this.updateState();
            }

            this.timer.reset();
         }
      }
   }

   public void applyChar(char character, int count10) throws IOException {
   }

   public void applyFontFace(int count10, int count11, int count12, int count13, int count14, int count15, FontFace fontFace2, boolean flag) {
      this.count3 = count10;
      this.count = count11;
      this.count2 = count14;
      this.count9 = count15;
      this.count5 = count12;
      this.count7 = count13;
      this.fontFace = fontFace2;
      if (this.count4 == -1) {
         String labelText4 = this.labelText3;
         this.count4 = (int)fontFace2.getScaleFactor(labelText4);
      }

      int count16 = flag
         ? RenderUtils.processAction(Client.getClient().getColor(), 0.2, count14, count15, count10 - 5, count11, this.count4 + 10, count13 + 2)
         : RenderUtils.processAction(new Color(185, 187, 190), 0.2, count14, count15, count10 - 5, count11, this.count4 + 10, count13 + 2);
      double offset = (double)count10;
      double offset2 = (double)count11;
      double offset3 = offset;
      String labelText5 = this.labelText3;
      fontFace2.applyString8(labelText5, offset3, offset2, count16);
      this.enabled = true;
   }

   public int getCount() {
      return this.count;
   }

   public void isEmpty() {
      long value = 0x9ce6bdfce18L;
      if (this.enabled && this.labelText2 != null && this.labelText != null && !this.labelText2.isEmpty() && !this.labelText.isEmpty()) {
         float factor = (float)(this.count3 - 5);
         float count10 = (float)this.count;
         String labelText4 = this.labelText3;
         boolean flag = RenderUtils.checkCondition(
            factor, count10, this.fontFace.getScaleFactor(labelText4) + 10.0F, (float)(this.count7 + 2), this.count2, this.count9
         );
         if (flag) {
            if (!this.enabled2) {
               this.timer3.reset();
               this.count8 = -1337;
               this.count6 = -1337;
            }

            if (this.timer3.hasReached(500L)) {
               if (this.count8 == -1337 && this.count6 == -1337) {
                  this.count8 = this.count2;
                  this.count6 = this.count9;
               }

               String text = "Updated: " + this.labelText2 + " " + this.labelText;
               int count11 = this.count6;
               int count12 = this.count8;
               String text2 = text;
               ClickGuiPanel.applyFontFaceByFontFace(this.fontFace, text2, count12, count11);
            }
         }

         this.enabled2 = flag;
      }
   }

   public CurvePipeline(String text, boolean flag, String text2, String text3) {
      this.timer = new TimeHelper();
      this.timer2 = new TimeHelper();
      this.timer3 = new TimeHelper();
      this.enabled = false;
      this.count4 = -1;
      this.labelText3 = text;
      this.labelText2 = text2;
      this.labelText = text3;
      this.enabled3 = flag;
      this.count3 = 0;
      this.count = 0;
      this.count5 = 0;
      this.count7 = 0;
      this.fontFace = null;
   }
}
