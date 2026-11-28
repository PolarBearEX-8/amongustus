package net.minecraft.client.renderer;

import net.minecraft.client.settings.GameSettings;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.optifine.config.Config;
import org.lwjgl.opengl.ARBCopyBuffer;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.ARBMultitexture;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTBlendFuncSeparate;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GLContext;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorIdentifier;

public class OpenGlHelper {
   public static boolean nvidia;
   public static boolean ati;
   public static int GL_FRAMEBUFFER;
   public static int GL_RENDERBUFFER;
   public static int GL_COLOR_ATTACHMENT0;
   public static int GL_DEPTH_ATTACHMENT;
   public static int GL_FRAMEBUFFER_COMPLETE;
   public static int GL_FB_INCOMPLETE_ATTACHMENT;
   public static int GL_FB_INCOMPLETE_MISS_ATTACH;
   public static int GL_FB_INCOMPLETE_DRAW_BUFFER;
   public static int GL_FB_INCOMPLETE_READ_BUFFER;
   private static int framebufferType;
   public static boolean framebufferSupported;
   private static boolean shadersAvailable;
   private static boolean arbShaders;
   public static int GL_LINK_STATUS;
   public static int GL_COMPILE_STATUS;
   public static int GL_VERTEX_SHADER;
   public static int GL_FRAGMENT_SHADER;
   private static boolean arbMultitexture;
   public static int defaultTexUnit;
   public static int lightmapTexUnit;
   public static int GL_TEXTURE2;
   private static boolean arbTextureEnvCombine;
   public static int GL_COMBINE;
   public static int GL_INTERPOLATE;
   public static int GL_PRIMARY_COLOR;
   public static int GL_CONSTANT;
   public static int GL_PREVIOUS;
   public static int GL_COMBINE_RGB;
   public static int GL_SOURCE0_RGB;
   public static int GL_SOURCE1_RGB;
   public static int GL_SOURCE2_RGB;
   public static int GL_OPERAND0_RGB;
   public static int GL_OPERAND1_RGB;
   public static int GL_OPERAND2_RGB;
   public static int GL_COMBINE_ALPHA;
   public static int GL_SOURCE0_ALPHA;
   public static int GL_SOURCE1_ALPHA;
   public static int GL_SOURCE2_ALPHA;
   public static int GL_OPERAND0_ALPHA;
   public static int GL_OPERAND1_ALPHA;
   public static int GL_OPERAND2_ALPHA;
   private static boolean openGL14;
   public static boolean extBlendFuncSeparate;
   public static boolean openGL21;
   public static boolean shadersSupported;
   private static String logText = "";
   private static String cpu;
   public static boolean vboSupported;
   public static boolean vboSupportedAti;
   private static boolean arbVbo;
   public static int GL_ARRAY_BUFFER;
   public static int GL_STATIC_DRAW;
   public static float lastBrightnessX = 0.0F;
   public static float lastBrightnessY = 0.0F;
   public static boolean openGL31;
   public static boolean vboRegions;
   public static int GL_COPY_READ_BUFFER;
   public static int GL_COPY_WRITE_BUFFER;
   public static final int GL_QUADS = 7;
   public static final int GL_TRIANGLES = 4;

   public static void glUniform2(int n, IntBuffer intBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniform2ARB(n, intBuffer);
      } else {
         GL20.glUniform2(n, intBuffer);
      }
   }

   public static void glUniform4(int n, IntBuffer intBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniform4ARB(n, intBuffer);
      } else {
         GL20.glUniform4(n, intBuffer);
      }
   }

   public static void glUniform3(int n, FloatBuffer floatBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniform3ARB(n, floatBuffer);
      } else {
         GL20.glUniform3(n, floatBuffer);
      }
   }

   public static boolean areShadersSupported() {
      return shadersSupported;
   }

   public static void glBindFramebuffer(int n, int n2) {
      if (framebufferSupported) {
         switch (framebufferType) {
            case 0:
               GL30.glBindFramebuffer(n, n2);
               break;
            case 1:
               ARBFramebufferObject.glBindFramebuffer(n, n2);
               break;
            case 2:
               EXTFramebufferObject.glBindFramebufferEXT(n, n2);
         }
      }
   }

   public static void glBindBuffer(int n, int n2) {
      if (arbVbo) {
         ARBVertexBufferObject.glBindBufferARB(n, n2);
      } else {
         GL15.glBindBuffer(n, n2);
      }
   }

   public static void glShaderSource(int n, ByteBuffer buf) {
      if (arbShaders) {
         ARBShaderObjects.glShaderSourceARB(n, buf);
      } else {
         GL20.glShaderSource(n, buf);
      }
   }

   public static int glCreateProgram() {
      return arbShaders ? ARBShaderObjects.glCreateProgramObjectARB() : GL20.glCreateProgram();
   }

   public static void glDeleteProgram(int n) {
      if (arbShaders) {
         ARBShaderObjects.glDeleteObjectARB(n);
      } else {
         GL20.glDeleteProgram(n);
      }
   }

   public static int glCreateShader(int n) {
      return arbShaders ? ARBShaderObjects.glCreateShaderObjectARB(n) : GL20.glCreateShader(n);
   }

   public static void glUniform1i(int n, int n2) {
      if (arbShaders) {
         ARBShaderObjects.glUniform1iARB(n, n2);
      } else {
         GL20.glUniform1i(n, n2);
      }
   }

   public static String glGetShaderInfoLog(int n, int n2) {
      return arbShaders ? ARBShaderObjects.glGetInfoLogARB(n, n2) : GL20.glGetShaderInfoLog(n, n2);
   }

   public static void glDeleteShader(int n) {
      if (arbShaders) {
         ARBShaderObjects.glDeleteObjectARB(n);
      } else {
         GL20.glDeleteShader(n);
      }
   }

   public static void glFramebufferTexture2D(int n, int n2, int n3, int n4, int n5) {
      if (framebufferSupported) {
         switch (framebufferType) {
            case 0:
               GL30.glFramebufferTexture2D(n, n2, n3, n4, n5);
               break;
            case 1:
               ARBFramebufferObject.glFramebufferTexture2D(n, n2, n3, n4, n5);
               break;
            case 2:
               EXTFramebufferObject.glFramebufferTexture2DEXT(n, n2, n3, n4, n5);
         }
      }
   }

   public static void glAttachShader(int n, int n2) {
      if (arbShaders) {
         ARBShaderObjects.glAttachObjectARB(n, n2);
      } else {
         GL20.glAttachShader(n, n2);
      }
   }

   public static void glUniform1(int n, FloatBuffer floatBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniform1ARB(n, floatBuffer);
      } else {
         GL20.glUniform1(n, floatBuffer);
      }
   }

   public static void glLinkProgram(int n) {
      if (arbShaders) {
         ARBShaderObjects.glLinkProgramARB(n);
      } else {
         GL20.glLinkProgram(n);
      }
   }

   public static String getCpu() {
      return cpu == null ? "<unknown>" : cpu;
   }

   public static void setActiveTexture(int n) {
      if (arbMultitexture) {
         ARBMultitexture.glActiveTextureARB(n);
      } else {
         GL13.glActiveTexture(n);
      }
   }

   public static void glFramebufferRenderbuffer(int n, int n2, int n3, int n4) {
      if (framebufferSupported) {
         switch (framebufferType) {
            case 0:
               GL30.glFramebufferRenderbuffer(n, n2, n3, n4);
               break;
            case 1:
               ARBFramebufferObject.glFramebufferRenderbuffer(n, n2, n3, n4);
               break;
            case 2:
               EXTFramebufferObject.glFramebufferRenderbufferEXT(n, n2, n3, n4);
         }
      }
   }

   public static int glGenFramebuffers() {
      if (!framebufferSupported) {
         return -1;
      } else {
         switch (framebufferType) {
            case 0:
               return GL30.glGenFramebuffers();
            case 1:
               return ARBFramebufferObject.glGenFramebuffers();
            case 2:
               return EXTFramebufferObject.glGenFramebuffersEXT();
            default:
               return -1;
         }
      }
   }

   public static int glCheckFramebufferStatus(int n) {
      if (!framebufferSupported) {
         return -1;
      } else {
         switch (framebufferType) {
            case 0:
               return GL30.glCheckFramebufferStatus(n);
            case 1:
               return ARBFramebufferObject.glCheckFramebufferStatus(n);
            case 2:
               return EXTFramebufferObject.glCheckFramebufferStatusEXT(n);
            default:
               return -1;
         }
      }
   }

   public static void glUniform4(int n, FloatBuffer floatBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniform4ARB(n, floatBuffer);
      } else {
         GL20.glUniform4(n, floatBuffer);
      }
   }

   public static void glBufferData(int n, ByteBuffer buf, int n2) {
      if (arbVbo) {
         ARBVertexBufferObject.glBufferDataARB(n, buf, n2);
      } else {
         GL15.glBufferData(n, buf, n2);
      }
   }

   public static void glDeleteRenderbuffers(int n) {
      if (framebufferSupported) {
         switch (framebufferType) {
            case 0:
               GL30.glDeleteRenderbuffers(n);
               break;
            case 1:
               ARBFramebufferObject.glDeleteRenderbuffers(n);
               break;
            case 2:
               EXTFramebufferObject.glDeleteRenderbuffersEXT(n);
         }
      }
   }

   public static int glGetProgrami(int n, int n2) {
      return arbShaders ? ARBShaderObjects.glGetObjectParameteriARB(n, n2) : GL20.glGetProgrami(n, n2);
   }

   public static void glRenderbufferStorage(int n, int n2, int n3, int n4) {
      if (framebufferSupported) {
         switch (framebufferType) {
            case 0:
               GL30.glRenderbufferStorage(n, n2, n3, n4);
               break;
            case 1:
               ARBFramebufferObject.glRenderbufferStorage(n, n2, n3, n4);
               break;
            case 2:
               EXTFramebufferObject.glRenderbufferStorageEXT(n, n2, n3, n4);
         }
      }
   }

   public static int glGenBuffers() {
      return arbVbo ? ARBVertexBufferObject.glGenBuffersARB() : GL15.glGenBuffers();
   }

   public static String glGetProgramInfoLog(int n, int n2) {
      return arbShaders ? ARBShaderObjects.glGetInfoLogARB(n, n2) : GL20.glGetProgramInfoLog(n, n2);
   }

   public static void glUniform1(int n, IntBuffer intBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniform1ARB(n, intBuffer);
      } else {
         GL20.glUniform1(n, intBuffer);
      }
   }

   public static int glGetAttribLocation(int n, CharSequence text) {
      return arbShaders ? ARBVertexShader.glGetAttribLocationARB(n, text) : GL20.glGetAttribLocation(n, text);
   }

   public static void glBufferData2(int n, long l, int n2) {
      if (arbVbo) {
         ARBVertexBufferObject.glBufferDataARB(n, l, n2);
      } else {
         GL15.glBufferData(n, l, n2);
      }
   }

   public static void glUniformMatrix3(int n, boolean flag, FloatBuffer floatBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniformMatrix3ARB(n, flag, floatBuffer);
      } else {
         GL20.glUniformMatrix3(n, flag, floatBuffer);
      }
   }

   public static void glCompileShader(int n) {
      if (arbShaders) {
         ARBShaderObjects.glCompileShaderARB(n);
      } else {
         GL20.glCompileShader(n);
      }
   }

   public static int glGetShaderi(int n, int n2) {
      return arbShaders ? ARBShaderObjects.glGetObjectParameteriARB(n, n2) : GL20.glGetShaderi(n, n2);
   }

   public static int glGetUniformLocation(int n, CharSequence text) {
      return arbShaders ? ARBShaderObjects.glGetUniformLocationARB(n, text) : GL20.glGetUniformLocation(n, text);
   }

   public static String getLogText() {
      return logText;
   }

   public static void glCopyBufferSubData(int n, int n2, long l, long l2, long l3) {
      if (openGL31) {
         GL31.glCopyBufferSubData(n, n2, l, l2, l3);
      } else {
         ARBCopyBuffer.glCopyBufferSubData(n, n2, l, l2, l3);
      }
   }

   public static void glDeleteBuffers(int n) {
      if (arbVbo) {
         ARBVertexBufferObject.glDeleteBuffersARB(n);
      } else {
         GL15.glDeleteBuffers(n);
      }
   }

   public static void glDeleteFramebuffers(int n) {
      if (framebufferSupported) {
         switch (framebufferType) {
            case 0:
               GL30.glDeleteFramebuffers(n);
               break;
            case 1:
               ARBFramebufferObject.glDeleteFramebuffers(n);
               break;
            case 2:
               EXTFramebufferObject.glDeleteFramebuffersEXT(n);
         }
      }
   }

   public static boolean useVbo() {
      return !Config.isMultiTexture() && (!Config.isRenderRegions() || vboRegions) && vboSupported && Minecraft.getMinecraft().gameSettings.useVbo;
   }

   public static void glBindRenderbuffer(int n, int n2) {
      if (framebufferSupported) {
         switch (framebufferType) {
            case 0:
               GL30.glBindRenderbuffer(n, n2);
               break;
            case 1:
               ARBFramebufferObject.glBindRenderbuffer(n, n2);
               break;
            case 2:
               EXTFramebufferObject.glBindRenderbufferEXT(n, n2);
         }
      }
   }

   public static void glUniformMatrix2(int n, boolean flag, FloatBuffer floatBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniformMatrix2ARB(n, flag, floatBuffer);
      } else {
         GL20.glUniformMatrix2(n, flag, floatBuffer);
      }
   }

   public static void glUniform2(int n, FloatBuffer floatBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniform2ARB(n, floatBuffer);
      } else {
         GL20.glUniform2(n, floatBuffer);
      }
   }

   public static void glBlendFunc(int n, int n2, int n3, int n4) {
      if (openGL14) {
         if (extBlendFuncSeparate) {
            EXTBlendFuncSeparate.glBlendFuncSeparateEXT(n, n2, n3, n4);
         } else {
            GL14.glBlendFuncSeparate(n, n2, n3, n4);
         }
      } else {
         GL11.glBlendFunc(n, n2);
      }
   }

   public static void glUseProgram(int n) {
      if (arbShaders) {
         ARBShaderObjects.glUseProgramObjectARB(n);
      } else {
         GL20.glUseProgram(n);
      }
   }

   public static void setLightmapTextureCoords(int n, float f, float f2) {
      if (arbMultitexture) {
         ARBMultitexture.glMultiTexCoord2fARB(n, f, f2);
      } else {
         GL13.glMultiTexCoord2f(n, f, f2);
      }

      if (n == lightmapTexUnit) {
         lastBrightnessX = f;
         lastBrightnessY = f2;
      }
   }

   public static void glUniform3(int n, IntBuffer intBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniform3ARB(n, intBuffer);
      } else {
         GL20.glUniform3(n, intBuffer);
      }
   }

   public static void initializeTextures() {
      Config.initDisplay();
      ContextCapabilities capabilities = GLContext.getCapabilities();
      arbMultitexture = capabilities.GL_ARB_multitexture && !capabilities.OpenGL13;
      arbTextureEnvCombine = capabilities.GL_ARB_texture_env_combine && !capabilities.OpenGL13;
      openGL31 = capabilities.OpenGL31;
      GL_COPY_READ_BUFFER = 36662;
      GL_COPY_WRITE_BUFFER = 36663;
      boolean flag = openGL31 || capabilities.GL_ARB_copy_buffer;
      boolean openGL142 = capabilities.OpenGL14;
      vboRegions = flag && openGL142;
      if (!vboRegions) {
         ArrayList list = new ArrayList();
         if (!flag) {
            list.add("OpenGL 1.3, ARB_copy_buffer");
         }

         if (!openGL142) {
            list.add("OpenGL 1.4");
         }

         String str = "VboRegions not supported, missing: " + Config.listToString(list);
         Config.dbg(str);
         logText = logText + str + "\n";
      }

      if (arbMultitexture) {
         logText = logText + "Using ARB_multitexture.\n";
         defaultTexUnit = 33984;
         lightmapTexUnit = 33985;
         GL_TEXTURE2 = 33986;
      } else {
         logText = logText + "Using GL 1.3 multitexturing.\n";
         defaultTexUnit = 33984;
         lightmapTexUnit = 33985;
         GL_TEXTURE2 = 33986;
      }

      if (arbTextureEnvCombine) {
         logText = logText + "Using ARB_texture_env_combine.\n";
         GL_COMBINE = 34160;
         GL_INTERPOLATE = 34165;
         GL_PRIMARY_COLOR = 34167;
         GL_CONSTANT = 34166;
         GL_PREVIOUS = 34168;
         GL_COMBINE_RGB = 34161;
         GL_SOURCE0_RGB = 34176;
         GL_SOURCE1_RGB = 34177;
         GL_SOURCE2_RGB = 34178;
         GL_OPERAND0_RGB = 34192;
         GL_OPERAND1_RGB = 34193;
         GL_OPERAND2_RGB = 34194;
         GL_COMBINE_ALPHA = 34162;
         GL_SOURCE0_ALPHA = 34184;
         GL_SOURCE1_ALPHA = 34185;
         GL_SOURCE2_ALPHA = 34186;
         GL_OPERAND0_ALPHA = 34200;
         GL_OPERAND1_ALPHA = 34201;
         GL_OPERAND2_ALPHA = 34202;
      } else {
         logText = logText + "Using GL 1.3 texture combiners.\n";
         GL_COMBINE = 34160;
         GL_INTERPOLATE = 34165;
         GL_PRIMARY_COLOR = 34167;
         GL_CONSTANT = 34166;
         GL_PREVIOUS = 34168;
         GL_COMBINE_RGB = 34161;
         GL_SOURCE0_RGB = 34176;
         GL_SOURCE1_RGB = 34177;
         GL_SOURCE2_RGB = 34178;
         GL_OPERAND0_RGB = 34192;
         GL_OPERAND1_RGB = 34193;
         GL_OPERAND2_RGB = 34194;
         GL_COMBINE_ALPHA = 34162;
         GL_SOURCE0_ALPHA = 34184;
         GL_SOURCE1_ALPHA = 34185;
         GL_SOURCE2_ALPHA = 34186;
         GL_OPERAND0_ALPHA = 34200;
         GL_OPERAND1_ALPHA = 34201;
         GL_OPERAND2_ALPHA = 34202;
      }

      extBlendFuncSeparate = capabilities.GL_EXT_blend_func_separate && !capabilities.OpenGL14;
      openGL14 = capabilities.OpenGL14 || capabilities.GL_EXT_blend_func_separate;
      framebufferSupported = openGL14 && (capabilities.GL_ARB_framebuffer_object || capabilities.GL_EXT_framebuffer_object || capabilities.OpenGL30);
      if (framebufferSupported) {
         logText = logText + "Using framebuffer objects because ";
         if (capabilities.OpenGL30) {
            logText = logText + "OpenGL 3.0 is supported and separate blending is supported.\n";
            framebufferType = 0;
            GL_FRAMEBUFFER = 36160;
            GL_RENDERBUFFER = 36161;
            GL_COLOR_ATTACHMENT0 = 36064;
            GL_DEPTH_ATTACHMENT = 36096;
            GL_FRAMEBUFFER_COMPLETE = 36053;
            GL_FB_INCOMPLETE_ATTACHMENT = 36054;
            GL_FB_INCOMPLETE_MISS_ATTACH = 36055;
            GL_FB_INCOMPLETE_DRAW_BUFFER = 36059;
            GL_FB_INCOMPLETE_READ_BUFFER = 36060;
         } else if (capabilities.GL_ARB_framebuffer_object) {
            logText = logText + "ARB_framebuffer_object is supported and separate blending is supported.\n";
            framebufferType = 1;
            GL_FRAMEBUFFER = 36160;
            GL_RENDERBUFFER = 36161;
            GL_COLOR_ATTACHMENT0 = 36064;
            GL_DEPTH_ATTACHMENT = 36096;
            GL_FRAMEBUFFER_COMPLETE = 36053;
            GL_FB_INCOMPLETE_MISS_ATTACH = 36055;
            GL_FB_INCOMPLETE_ATTACHMENT = 36054;
            GL_FB_INCOMPLETE_DRAW_BUFFER = 36059;
            GL_FB_INCOMPLETE_READ_BUFFER = 36060;
         } else if (capabilities.GL_EXT_framebuffer_object) {
            logText = logText + "EXT_framebuffer_object is supported.\n";
            framebufferType = 2;
            GL_FRAMEBUFFER = 36160;
            GL_RENDERBUFFER = 36161;
            GL_COLOR_ATTACHMENT0 = 36064;
            GL_DEPTH_ATTACHMENT = 36096;
            GL_FRAMEBUFFER_COMPLETE = 36053;
            GL_FB_INCOMPLETE_MISS_ATTACH = 36055;
            GL_FB_INCOMPLETE_ATTACHMENT = 36054;
            GL_FB_INCOMPLETE_DRAW_BUFFER = 36059;
            GL_FB_INCOMPLETE_READ_BUFFER = 36060;
         }
      } else {
         logText = logText + "Not using framebuffer objects because ";
         logText = logText + "OpenGL 1.4 is " + (capabilities.OpenGL14 ? "" : "not ") + "supported, ";
         logText = logText + "EXT_blend_func_separate is " + (capabilities.GL_EXT_blend_func_separate ? "" : "not ") + "supported, ";
         logText = logText + "OpenGL 3.0 is " + (capabilities.OpenGL30 ? "" : "not ") + "supported, ";
         logText = logText + "ARB_framebuffer_object is " + (capabilities.GL_ARB_framebuffer_object ? "" : "not ") + "supported, and ";
         logText = logText + "EXT_framebuffer_object is " + (capabilities.GL_EXT_framebuffer_object ? "" : "not ") + "supported.\n";
      }

      openGL21 = capabilities.OpenGL21;
      shadersAvailable = openGL21 || capabilities.GL_ARB_vertex_shader && capabilities.GL_ARB_fragment_shader && capabilities.GL_ARB_shader_objects;
      logText = logText + "Shaders are " + (shadersAvailable ? "" : "not ") + "available because ";
      if (shadersAvailable) {
         if (capabilities.OpenGL21) {
            logText = logText + "OpenGL 2.1 is supported.\n";
            arbShaders = false;
            GL_LINK_STATUS = 35714;
            GL_COMPILE_STATUS = 35713;
            GL_VERTEX_SHADER = 35633;
            GL_FRAGMENT_SHADER = 35632;
         } else {
            logText = logText + "ARB_shader_objects, ARB_vertex_shader, and ARB_fragment_shader are supported.\n";
            arbShaders = true;
            GL_LINK_STATUS = 35714;
            GL_COMPILE_STATUS = 35713;
            GL_VERTEX_SHADER = 35633;
            GL_FRAGMENT_SHADER = 35632;
         }
      } else {
         logText = logText + "OpenGL 2.1 is " + (capabilities.OpenGL21 ? "" : "not ") + "supported, ";
         logText = logText + "ARB_shader_objects is " + (capabilities.GL_ARB_shader_objects ? "" : "not ") + "supported, ";
         logText = logText + "ARB_vertex_shader is " + (capabilities.GL_ARB_vertex_shader ? "" : "not ") + "supported, and ";
         logText = logText + "ARB_fragment_shader is " + (capabilities.GL_ARB_fragment_shader ? "" : "not ") + "supported.\n";
      }

      shadersSupported = framebufferSupported && shadersAvailable;
      String str2 = GL11.glGetString(7936).toLowerCase();
      nvidia = str2.contains("nvidia");
      arbVbo = !capabilities.OpenGL15 && capabilities.GL_ARB_vertex_buffer_object;
      vboSupported = capabilities.OpenGL15 || arbVbo;
      logText = logText + "VBOs are " + (vboSupported ? "" : "not ") + "available because ";
      if (vboSupported) {
         if (arbVbo) {
            logText = logText + "ARB_vertex_buffer_object is supported.\n";
            GL_STATIC_DRAW = 35044;
            GL_ARRAY_BUFFER = 34962;
         } else {
            logText = logText + "OpenGL 1.5 is supported.\n";
            GL_STATIC_DRAW = 35044;
            GL_ARRAY_BUFFER = 34962;
         }
      }

      ati = str2.contains("ati");
      if (ati) {
         if (vboSupported) {
            vboSupportedAti = true;
         } else {
            GameSettings.Options.RENDER_DISTANCE.setValueMax(16.0F);
         }
      }

      try {
         CentralProcessor processor = new SystemInfo().getHardware().getProcessor();
         ProcessorIdentifier processorIdentifier = processor.getProcessorIdentifier();
         cpu = String.format("%dx %s", processor.getPhysicalProcessors().size(), processorIdentifier.getName()).replaceAll("\\s+", " ");
      } catch (Throwable e) {
         e.printStackTrace();
      }
   }

   public static void glUniformMatrix4(int n, boolean flag, FloatBuffer floatBuffer) {
      if (arbShaders) {
         ARBShaderObjects.glUniformMatrix4ARB(n, flag, floatBuffer);
      } else {
         GL20.glUniformMatrix4(n, flag, floatBuffer);
      }
   }

   public static int glGenRenderbuffers() {
      if (!framebufferSupported) {
         return -1;
      } else {
         switch (framebufferType) {
            case 0:
               return GL30.glGenRenderbuffers();
            case 1:
               return ARBFramebufferObject.glGenRenderbuffers();
            case 2:
               return EXTFramebufferObject.glGenRenderbuffersEXT();
            default:
               return -1;
         }
      }
   }

   public static void glBufferSubData(int n, long l, ByteBuffer buf) {
      if (arbVbo) {
         ARBVertexBufferObject.glBufferSubDataARB(n, l, buf);
      } else {
         GL15.glBufferSubData(n, l, buf);
      }
   }

   public static void setClientActiveTexture(int n) {
      if (arbMultitexture) {
         ARBMultitexture.glClientActiveTextureARB(n);
      } else {
         GL13.glClientActiveTexture(n);
      }
   }

   public static boolean isFramebufferEnabled() {
      return Config.isFastRender() ? false : (Config.isAntialiasing() ? false : framebufferSupported && Minecraft.getMinecraft().gameSettings.fboEnable);
   }
}
