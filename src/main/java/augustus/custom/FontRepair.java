package augustus.custom;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;

/**
 * Draw antialiased glyphs without discarding their edge pixels; restore every changed GL state.
 */
public final class FontRepair {
    private FontRepair() {}

    public static void prepare(Object value) {
        UnicodeFont font = (UnicodeFont) value;
        // Keep antialiased edges away from adjacent atlas cells. Compensate the
        // added padding so text width, row spacing and mouse hitboxes stay aligned.
        font.setPaddingTop(2);
        font.setPaddingBottom(2);
        font.setPaddingLeft(2);
        font.setPaddingRight(2);
        font.setPaddingAdvanceX(-4);
        font.setPaddingAdvanceY(-4);
    }

    public static float draw(Object font, float scale, String text, float x, float y, int argb) {
        text = MenuNotice.filter(text);
        if (text == null || text.isEmpty()) return 0.0F;
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();
        try {
            GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            ((UnicodeFont) font).drawString(x * scale, y * scale, text, new Color(argb));
        } finally {
            GL11.glPopMatrix();
            GL11.glPopAttrib();
        }
        return x * scale;
    }
}
