package net.augustus.clickgui;

import net.augustus.Augustus;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.TextWrapper;
import net.augustus.render.RenderUtil;
import java.awt.Color;
import java.util.List;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import org.lwjgl.opengl.Display;

public class TooltipRenderer implements MC {
   private static final Color BACKGROUND_COLOR = new Color(40, 39, 39);
   private static final int DEFAULT_MAX_WIDTH = Math.min(Display.getWidth() / 2, Display.getWidth());
   private static final Color BORDER_COLOR = Color.BLACK;
   private static final int PADDING = 5;
   private static final int LINE_SPACING = 2;

   public static void drawTooltip(CustomFontUtil customFontUtil, String str, int n, int n2) {
      drawTooltip(customFontUtil, str, n, n2, DEFAULT_MAX_WIDTH);
   }

   public static void drawTooltip(CustomFontUtil customFontUtil, String str, int n, int n2, int n3) {
      if (!Augustus.getInstance().getClickGui().getMainWindow().isHoveringModuleContextMenu()) {
         if (str != null && !str.isEmpty()) {
            int width = Display.getWidth();
            int height = Display.getHeight();
            List list = TextWrapper.wrapText(str, n3, customFontUtil);
            if (!list.isEmpty()) {
               int n4 = 0;

               for (String str2 : (Iterable<String>)(list)) {
                  int stringWidth = (int)customFontUtil.getStringWidth(str2);
                  if (stringWidth > n4) {
                     n4 = stringWidth;
                  }
               }

               int n5 = n4 + 10;
               int fontHeight = (int)customFontUtil.getFontHeight();
               int n6 = list.size() * fontHeight + (list.size() - 1) * 2 + 10;
               int n7 = n + 15;
               int n8 = n2;
               if (n7 + n5 > width) {
                  n7 = n - n5 - 10;
                  if (n7 < 0) {
                     n7 = 5;
                  }
               }

               if (n2 + n6 > height) {
                  n8 = height - n6 - 5;
                  if (n8 < 0) {
                     n8 = 5;
                  }
               }

               RenderUtil.drawRect(n7, n8, n7 + n5, n8 + n6, BACKGROUND_COLOR.getRGB());
               RenderUtil.drawRectOutline((double)n7, (double)n8, (double)(n7 + n5), (double)(n8 + n6), 1.0F, BORDER_COLOR);
               int n9 = n8 + 5;

               for (String str3 : (Iterable<String>)(list)) {
                  customFontUtil.drawString(str3, (double)(n7 + 5), (double)n9, Color.WHITE.getRGB());
                  n9 += fontHeight + 2;
               }
            }
         }
      }
   }
}
