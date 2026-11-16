package augustus.custom;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public final class RoundedRect {
    private static final ThreadLocal<Buffers> BUFFERS = ThreadLocal.withInitial(Buffers::new);

    private RoundedRect() {
    }

    private static double[] pixelPosition(Buffers buffers, double x, double y) {
        FloatBuffer model = buffers.model;
        FloatBuffer projection = buffers.projection;
        double eyeX = model.get(0) * x + model.get(4) * y + model.get(12);
        double eyeY = model.get(1) * x + model.get(5) * y + model.get(13);
        double eyeZ = model.get(2) * x + model.get(6) * y + model.get(14);
        double eyeW = model.get(3) * x + model.get(7) * y + model.get(15);

        double clipX = projection.get(0) * eyeX + projection.get(4) * eyeY + projection.get(8) * eyeZ + projection.get(12) * eyeW;
        double clipY = projection.get(1) * eyeX + projection.get(5) * eyeY + projection.get(9) * eyeZ + projection.get(13) * eyeW;
        double clipW = projection.get(3) * eyeX + projection.get(7) * eyeY + projection.get(11) * eyeZ + projection.get(15) * eyeW;

        if (Math.abs(clipW) < 1e-10) {
            return new double[] { x, y };
        }
        return new double[] {
            clipX / clipW * buffers.viewport.get(2) * 0.5,
            clipY / clipW * buffers.viewport.get(3) * 0.5
        };
    }

    public static void draw(float left, float top, float right, float bottom, float radius, int argb) {
        drawCorners(left, top, right, bottom, radius, argb, true, true, true, true);
    }

    public static void drawSize(double x, double y, double width, double height, double radius, Color color) {
        draw((float) x, (float) y, (float) (x + width), (float) (y + height), (float) radius, color.getRGB());
    }

    public static void drawCorners(float x1, float y1, float x2, float y2, float radius, int argb,
                                   boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight) {
        if (!Float.isFinite(x1) || !Float.isFinite(y1) || !Float.isFinite(x2) || !Float.isFinite(y2) || !Float.isFinite(radius)) {
            return;
        }

        float left = Math.min(x1, x2);
        float right = Math.max(x1, x2);
        float top = Math.min(y1, y2);
        float bottom = Math.max(y1, y2);

        if (left == right || top == bottom || (argb >>> 24) == 0) {
            return;
        }

        double effectiveRadius = Math.max(0, Math.min(radius, Math.min(right - left, bottom - top) * 0.5));
        Buffers buffers = BUFFERS.get();
        buffers.model.clear();
        buffers.projection.clear();
        buffers.viewport.clear();

        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, buffers.model);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, buffers.projection);
        GL11.glGetInteger(GL11.GL_VIEWPORT, buffers.viewport);

        double[] center = pixelPosition(buffers, (left + right) * 0.5, (top + bottom) * 0.5);
        double[] dx = pixelPosition(buffers, (left + right) * 0.5 + 1.0, (top + bottom) * 0.5);
        double[] dy = pixelPosition(buffers, (left + right) * 0.5, (top + bottom) * 0.5 + 1.0);

        double scaleX = Math.max(0.001, Math.hypot(dx[0] - center[0], dx[1] - center[1]));
        double scaleY = Math.max(0.001, Math.hypot(dy[0] - center[0], dy[1] - center[1]));

        // Stencil writes ignore fragment alpha. A transparent AA fringe would still
        // replace stencil bits and let blur/bloom leak outside the visible outline.
        boolean stencilWrite = GL11.glIsEnabled(GL11.GL_STENCIL_TEST)
            && GL11.glGetInteger(GL11.GL_STENCIL_WRITEMASK) != 0
            && (GL11.glGetInteger(GL11.GL_STENCIL_FAIL) != GL11.GL_KEEP
                || GL11.glGetInteger(GL11.GL_STENCIL_PASS_DEPTH_FAIL) != GL11.GL_KEEP
                || GL11.glGetInteger(GL11.GL_STENCIL_PASS_DEPTH_PASS) != GL11.GL_KEEP);

        double featherX = stencilWrite ? 0.0 : Math.min(0.5 / scaleX, (right - left) * 0.25);
        double featherY = stencilWrite ? 0.0 : Math.min(0.5 / scaleY, (bottom - top) * 0.25);

        int segments = Math.max(8, Math.min(64, (int) Math.ceil(Math.PI * 0.5 * Math.sqrt(Math.max(1.0, effectiveRadius * Math.max(scaleX, scaleY)) / 0.1))));
        int capacity = (segments + 1) * 4;
        int points = 0;
        boolean[] rounded = { bottomRight, topRight, topLeft, bottomLeft };
        double[] innerX = new double[capacity];
        double[] innerY = new double[capacity];
        double[] outerX = new double[capacity];
        double[] outerY = new double[capacity];

        for (int corner = 0; corner < 4; corner++) {
            if (!rounded[corner]) {
                double cornerX = (corner < 2) ? right : left;
                double cornerY = (corner == 0 || corner == 3) ? bottom : top;
                double signX = (corner < 2) ? 1.0 : -1.0;
                double signY = (corner == 0 || corner == 3) ? 1.0 : -1.0;
                innerX[points] = cornerX - signX * featherX;
                innerY[points] = cornerY - signY * featherY;
                outerX[points] = cornerX + signX * featherX;
                outerY[points] = cornerY + signY * featherY;
                points++;
                continue;
            }
            double cornerCenterX = (corner < 2) ? (right - effectiveRadius) : (left + effectiveRadius);
            double cornerCenterY = (corner == 0 || corner == 3) ? (bottom - effectiveRadius) : (top + effectiveRadius);
            for (int step = 0; step <= segments; step++, points++) {
                double angle = Math.toRadians(corner * 90.0 + step * 90.0 / segments);
                double sin = Math.sin(angle);
                double cos = Math.cos(angle);
                innerX[points] = cornerCenterX + sin * Math.max(0.0, effectiveRadius - featherX);
                innerY[points] = cornerCenterY + cos * Math.max(0.0, effectiveRadius - featherY);
                outerX[points] = cornerCenterX + sin * (effectiveRadius + featherX);
                outerY[points] = cornerCenterY + cos * (effectiveRadius + featherY);
            }
        }

        float red = ((argb >> 16) & 255) / 255.0f;
        float green = ((argb >> 8) & 255) / 255.0f;
        float blue = (argb & 255) / 255.0f;
        float alpha = (argb >>> 24) / 255.0f;

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        try {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_BLEND);
            GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glColor4f(red, green, blue, alpha);

            if (effectiveRadius == 0.0) {
                GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2d(left, top);
                GL11.glVertex2d(right, top);
                GL11.glVertex2d(right, bottom);
                GL11.glVertex2d(left, bottom);
                GL11.glEnd();
                return;
            }

            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            GL11.glVertex2d((left + right) * 0.5, (top + bottom) * 0.5);
            for (int i = 0; i <= points; i++) {
                GL11.glVertex2d(innerX[i % points], innerY[i % points]);
            }
            GL11.glEnd();

            if (stencilWrite) {
                return;
            }

            GL11.glDepthMask(false);
            GL11.glBegin(GL11.GL_QUAD_STRIP);
            for (int i = 0; i <= points; i++) {
                int index = i % points;
                GL11.glColor4f(red, green, blue, alpha);
                GL11.glVertex2d(innerX[index], innerY[index]);
                GL11.glColor4f(red, green, blue, 0.0f);
                GL11.glVertex2d(outerX[index], outerY[index]);
            }
            GL11.glEnd();
        } finally {
            GL11.glPopAttrib();
        }
    }

    private static final class Buffers {
        final FloatBuffer model = BufferUtils.createFloatBuffer(16);
        final FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        final IntBuffer viewport = BufferUtils.createIntBuffer(16);
    }
}
