package net.minecraft.client.renderer;

import client.setting.FactoryOptionType;
import client.util.BuildManager;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
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
   public static boolean enabled11;
   public static boolean enabled16;
   public static int count36;
   public static int count13;
   public static int count17;
   public static int count7;
   public static int count23;
   public static int count24;
   public static int count12;
   public static int count11;
   public static int count3;
   public static int count19;
   public static boolean enabled;
   public static boolean enabled14;
   public static boolean enabled15;
   public static int count9;
   public static int count34;
   public static int count27;
   public static int count25;
   public static boolean enabled9;
   public static int lightmapTexUnit;
   public static int defaultTexUnit;
   public static int count4;
   public static boolean enabled8;
   public static int count5;
   public static int count33;
   public static int count8;
   public static int count29;
   public static int count22;
   public static int count39;
   public static int count20;
   public static int count21;
   public static int count6;
   public static int count28;
   public static int count31;
   public static int count10;
   public static int count37;
   public static int count15;
   public static int count35;
   public static int count32;
   public static int count26;
   public static int count40;
   public static int count;
   public static boolean enabled6;
   public static boolean enabled13;
   public static boolean enabled12;
   public static boolean enabled4;
   public static String labelText = "";
   public static String labelText2;
   public static boolean enabled10;
   public static boolean enabled5;
   public static boolean enabled2;
   public static int count14;
   public static int count2;
   public static float factor = 0.0F;
   public static float factor2 = 0.0F;
   public static boolean enabled7;
   public static boolean enabled3;
   public static int count18;
   public static int count30;
   public static final int count16 = 7;
   public static final int count38 = 4;

   public static void applyFloatBufferByFloatBuffer(int count41, boolean flag, FloatBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniformMatrix3ARB(count41, flag, buffer);
      } else {
         GL20.glUniformMatrix3(count41, flag, buffer);
      }
   }

   public static int getCount2() {
      return enabled2 ? ARBVertexBufferObject.glGenBuffersARB() : GL15.glGenBuffers();
   }

   public static void applyIndex6(int count41) {
      if (enabled9) {
         ARBMultitexture.glActiveTextureARB(count41);
      } else {
         GL13.glActiveTexture(count41);
      }
   }

   public static void applyIndex2(int count41, int count42, int count43, int count44) {
      if (enabled6) {
         if (enabled13) {
            EXTBlendFuncSeparate.glBlendFuncSeparateEXT(count41, count42, count43, count44);
         } else {
            GL14.glBlendFuncSeparate(count41, count42, count43, count44);
         }
      } else {
         GL11.glBlendFunc(count41, count42);
      }
   }

   public static String getLabelText() {
      return labelText;
   }

   public static void applyIndex(int count41, int count42, long value, long value2, long value3) {
      if (enabled7) {
         GL31.glCopyBufferSubData(count41, count42, value, value2, value3);
      } else {
         ARBCopyBuffer.glCopyBufferSubData(count41, count42, value, value2, value3);
      }
   }

   public static void applyIndex(int count41, long value, int count42) {
      if (enabled2) {
         ARBVertexBufferObject.glBufferDataARB(count41, value, count42);
      } else {
         GL15.glBufferData(count41, value, count42);
      }
   }

   public static String resolveTextByIndex(int count41, int count42) {
      return enabled15 ? ARBShaderObjects.glGetInfoLogARB(count41, count42) : GL20.glGetShaderInfoLog(count41, count42);
   }

   public static void applyIndex(int count41, float factor3, float factor4) {
      if (enabled9) {
         ARBMultitexture.glMultiTexCoord2fARB(count41, factor3, factor4);
      } else {
         GL13.glMultiTexCoord2f(count41, factor3, factor4);
      }

      if (count41 == defaultTexUnit) {
         factor = factor3;
         factor2 = factor4;
      }
   }

   public static void applyIndex2(int count41) {
      if (enabled15) {
         ARBShaderObjects.glCompileShaderARB(count41);
      } else {
         GL20.glCompileShader(count41);
      }
   }

   public static void applyByteBuffer(int count41, long value, ByteBuffer buffer) {
      if (enabled2) {
         ARBVertexBufferObject.glBufferSubDataARB(count41, value, buffer);
      } else {
         GL15.glBufferSubData(count41, value, buffer);
      }
   }

   public static boolean isEnabled4() {
      return enabled4;
   }

   public static boolean isActive() {
      return !BuildManager.isActive46() && (!BuildManager.isActive55() || enabled3) && enabled10 && Minecraft.getMinecraft().gameSettings.state112;
   }

   public static void applyIntBufferByIntBuffer(int count41, IntBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniform4ARB(count41, buffer);
      } else {
         GL20.glUniform4(count41, buffer);
      }
   }

   public static void applyFloatBuffer(int count41, FloatBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniform2ARB(count41, buffer);
      } else {
         GL20.glUniform2(count41, buffer);
      }
   }

   public static void applyIndexByIndex(int count41, int count42) {
      if (enabled15) {
         ARBShaderObjects.glUniform1iARB(count41, count42);
      } else {
         GL20.glUniform1i(count41, count42);
      }
   }

   public static void applyIndexByIndex(int count41) {
      if (enabled) {
         switch (count19) {
            case 0:
               GL30.glDeleteFramebuffers(count41);
               break;
            case 1:
               ARBFramebufferObject.glDeleteFramebuffers(count41);
               break;
            case 2:
               EXTFramebufferObject.glDeleteFramebuffersEXT(count41);
         }
      }
   }

   public static int getCount() {
      if (!enabled) {
         return -1;
      } else {
         switch (count19) {
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

   public static int computeCount(int count41) {
      return enabled15 ? ARBShaderObjects.glCreateShaderObjectARB(count41) : GL20.glCreateShader(count41);
   }

   public static void applyIntBuffer(int count41, IntBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniform1ARB(count41, buffer);
      } else {
         GL20.glUniform1(count41, buffer);
      }
   }

   public static void applyIndex(int count41, int count42) {
      if (enabled) {
         switch (count19) {
            case 0:
               GL30.glBindRenderbuffer(count41, count42);
               break;
            case 1:
               ARBFramebufferObject.glBindRenderbuffer(count41, count42);
               break;
            case 2:
               EXTFramebufferObject.glBindRenderbufferEXT(count41, count42);
         }
      }
   }

   public static void applyIndex(int count41, int count42, int count43, int count44) {
      if (enabled) {
         switch (count19) {
            case 0:
               GL30.glFramebufferRenderbuffer(count41, count42, count43, count44);
               break;
            case 1:
               ARBFramebufferObject.glFramebufferRenderbuffer(count41, count42, count43, count44);
               break;
            case 2:
               EXTFramebufferObject.glFramebufferRenderbufferEXT(count41, count42, count43, count44);
         }
      }
   }

   public static void applyIndex(int count41) {
      if (enabled15) {
         ARBShaderObjects.glDeleteObjectARB(count41);
      } else {
         GL20.glDeleteProgram(count41);
      }
   }

   public static void applyByteBuffer(int count41, ByteBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glShaderSourceARB(count41, buffer);
      } else {
         GL20.glShaderSource(count41, buffer);
      }
   }

   public static int computeCount(int count41, int count42) {
      return enabled15 ? ARBShaderObjects.glGetObjectParameteriARB(count41, count42) : GL20.glGetProgrami(count41, count42);
   }

   public static int computeCount(int count41, CharSequence text) {
      return enabled15 ? ARBShaderObjects.glGetUniformLocationARB(count41, text) : GL20.glGetUniformLocation(count41, text);
   }

   public static void applyFloatBufferByFloatBuffer(int count41, FloatBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniform1ARB(count41, buffer);
      } else {
         GL20.glUniform1(count41, buffer);
      }
   }

   public static String resolveText(int count41, int count42) {
      return enabled15 ? ARBShaderObjects.glGetInfoLogARB(count41, count42) : GL20.glGetProgramInfoLog(count41, count42);
   }

   public static void applyIndex(int count41, int count42, int count43, int count44, int count45) {
      if (enabled) {
         switch (count19) {
            case 0:
               GL30.glFramebufferTexture2D(count41, count42, count43, count44, count45);
               break;
            case 1:
               ARBFramebufferObject.glFramebufferTexture2D(count41, count42, count43, count44, count45);
               break;
            case 2:
               EXTFramebufferObject.glFramebufferTexture2DEXT(count41, count42, count43, count44, count45);
         }
      }
   }

   public static boolean isActiveAsFlag() {
      return BuildManager.canExecute3() ? false : (BuildManager.canExecute4() ? false : enabled && Minecraft.getMinecraft().gameSettings.state96);
   }

   public static void applyIndex4(int count41) {
      if (enabled9) {
         ARBMultitexture.glClientActiveTextureARB(count41);
      } else {
         GL13.glClientActiveTexture(count41);
      }
   }

   public static void updateState() {
      BuildManager.updateState();
      ContextCapabilities capabilities = GLContext.getCapabilities();
      enabled9 = capabilities.GL_ARB_multitexture && !capabilities.OpenGL13;
      enabled8 = capabilities.GL_ARB_texture_env_combine && !capabilities.OpenGL13;
      enabled7 = capabilities.OpenGL31;
      count18 = 36662;
      count30 = 36663;
      boolean flag = enabled7 || capabilities.GL_ARB_copy_buffer;
      boolean flag2 = capabilities.OpenGL14;
      enabled3 = flag && flag2;
      if (!enabled3) {
         ArrayList list = new ArrayList();
         if (!flag) {
            list.add("OpenGL 1.3, ARB_copy_buffer");
         }

         if (!flag2) {
            list.add("OpenGL 1.4");
         }

         String text = "VboRegions not supported, missing: " + BuildManager.resolveText(list);
         BuildManager.applyText4(text);
         labelText = labelText + text + "\n";
      }

      if (enabled9) {
         labelText = labelText + "Using ARB_multitexture.\n";
         lightmapTexUnit = 33984;
         defaultTexUnit = 33985;
         count4 = 33986;
      } else {
         labelText = labelText + "Using GL 1.3 multitexturing.\n";
         lightmapTexUnit = 33984;
         defaultTexUnit = 33985;
         count4 = 33986;
      }

      if (enabled8) {
         labelText = labelText + "Using ARB_texture_env_combine.\n";
         count5 = 34160;
         count33 = 34165;
         count8 = 34167;
         count29 = 34166;
         count22 = 34168;
         count39 = 34161;
         count20 = 34176;
         count21 = 34177;
         count6 = 34178;
         count28 = 34192;
         count31 = 34193;
         count10 = 34194;
         count37 = 34162;
         count15 = 34184;
         count35 = 34185;
         count32 = 34186;
         count26 = 34200;
         count40 = 34201;
         count = 34202;
      } else {
         labelText = labelText + "Using GL 1.3 texture combiners.\n";
         count5 = 34160;
         count33 = 34165;
         count8 = 34167;
         count29 = 34166;
         count22 = 34168;
         count39 = 34161;
         count20 = 34176;
         count21 = 34177;
         count6 = 34178;
         count28 = 34192;
         count31 = 34193;
         count10 = 34194;
         count37 = 34162;
         count15 = 34184;
         count35 = 34185;
         count32 = 34186;
         count26 = 34200;
         count40 = 34201;
         count = 34202;
      }

      enabled13 = capabilities.GL_EXT_blend_func_separate && !capabilities.OpenGL14;
      enabled6 = capabilities.OpenGL14 || capabilities.GL_EXT_blend_func_separate;
      enabled = enabled6 && (capabilities.GL_ARB_framebuffer_object || capabilities.GL_EXT_framebuffer_object || capabilities.OpenGL30);
      if (enabled) {
         labelText = labelText + "Using framebuffer objects because ";
         if (capabilities.OpenGL30) {
            labelText = labelText + "OpenGL 3.0 is supported and separate blending is supported.\n";
            count19 = 0;
            count36 = 36160;
            count13 = 36161;
            count17 = 36064;
            count7 = 36096;
            count23 = 36053;
            count24 = 36054;
            count12 = 36055;
            count11 = 36059;
            count3 = 36060;
         } else if (capabilities.GL_ARB_framebuffer_object) {
            labelText = labelText + "ARB_framebuffer_object is supported and separate blending is supported.\n";
            count19 = 1;
            count36 = 36160;
            count13 = 36161;
            count17 = 36064;
            count7 = 36096;
            count23 = 36053;
            count12 = 36055;
            count24 = 36054;
            count11 = 36059;
            count3 = 36060;
         } else if (capabilities.GL_EXT_framebuffer_object) {
            labelText = labelText + "EXT_framebuffer_object is supported.\n";
            count19 = 2;
            count36 = 36160;
            count13 = 36161;
            count17 = 36064;
            count7 = 36096;
            count23 = 36053;
            count12 = 36055;
            count24 = 36054;
            count11 = 36059;
            count3 = 36060;
         }
      } else {
         labelText = labelText + "Not using framebuffer objects because ";
         labelText = labelText + "OpenGL 1.4 is " + (capabilities.OpenGL14 ? "" : "not ") + "supported, ";
         labelText = labelText + "EXT_blend_func_separate is " + (capabilities.GL_EXT_blend_func_separate ? "" : "not ") + "supported, ";
         labelText = labelText + "OpenGL 3.0 is " + (capabilities.OpenGL30 ? "" : "not ") + "supported, ";
         labelText = labelText + "ARB_framebuffer_object is " + (capabilities.GL_ARB_framebuffer_object ? "" : "not ") + "supported, and ";
         labelText = labelText + "EXT_framebuffer_object is " + (capabilities.GL_EXT_framebuffer_object ? "" : "not ") + "supported.\n";
      }

      enabled12 = capabilities.OpenGL21;
      enabled14 = enabled12 || capabilities.GL_ARB_vertex_shader && capabilities.GL_ARB_fragment_shader && capabilities.GL_ARB_shader_objects;
      labelText = labelText + "Shaders are " + (enabled14 ? "" : "not ") + "available because ";
      if (enabled14) {
         if (capabilities.OpenGL21) {
            labelText = labelText + "OpenGL 2.1 is supported.\n";
            enabled15 = false;
            count9 = 35714;
            count34 = 35713;
            count27 = 35633;
            count25 = 35632;
         } else {
            labelText = labelText + "ARB_shader_objects, ARB_vertex_shader, and ARB_fragment_shader are supported.\n";
            enabled15 = true;
            count9 = 35714;
            count34 = 35713;
            count27 = 35633;
            count25 = 35632;
         }
      } else {
         labelText = labelText + "OpenGL 2.1 is " + (capabilities.OpenGL21 ? "" : "not ") + "supported, ";
         labelText = labelText + "ARB_shader_objects is " + (capabilities.GL_ARB_shader_objects ? "" : "not ") + "supported, ";
         labelText = labelText + "ARB_vertex_shader is " + (capabilities.GL_ARB_vertex_shader ? "" : "not ") + "supported, and ";
         labelText = labelText + "ARB_fragment_shader is " + (capabilities.GL_ARB_fragment_shader ? "" : "not ") + "supported.\n";
      }

      enabled4 = enabled && enabled14;
      String text2 = GL11.glGetString(7936).toLowerCase();
      enabled11 = text2.contains("nvidia");
      enabled2 = !capabilities.OpenGL15 && capabilities.GL_ARB_vertex_buffer_object;
      enabled10 = capabilities.OpenGL15 || enabled2;
      labelText = labelText + "VBOs are " + (enabled10 ? "" : "not ") + "available because ";
      if (enabled10) {
         if (enabled2) {
            labelText = labelText + "ARB_vertex_buffer_object is supported.\n";
            count2 = 35044;
            count14 = 34962;
         } else {
            labelText = labelText + "OpenGL 1.5 is supported.\n";
            count2 = 35044;
            count14 = 34962;
         }
      }

      enabled16 = text2.contains("ati");
      if (enabled16) {
         if (enabled10) {
            enabled5 = true;
         } else {
            FactoryOptionType.RENDER_DISTANCE.setFactor2(16.0F);
         }
      }

      try {
         CentralProcessor processor = new SystemInfo().getHardware().getProcessor();
         ProcessorIdentifier processorIdentifier = processor.getProcessorIdentifier();
         labelText2 = String.format("%dx %s", processor.getPhysicalProcessors().size(), processorIdentifier.getName()).replaceAll("\\s+", " ");
      } catch (Throwable throwable) {
         throwable.printStackTrace();
      }
   }

   public static void applyIndex4(int count41, int count42) {
      if (enabled15) {
         ARBShaderObjects.glAttachObjectARB(count41, count42);
      } else {
         GL20.glAttachShader(count41, count42);
      }
   }

   public static void applyFloatBuffer2(int count41, boolean flag, FloatBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniformMatrix2ARB(count41, flag, buffer);
      } else {
         GL20.glUniformMatrix2(count41, flag, buffer);
      }
   }

   public static void applyIndex8(int count41) {
      if (enabled15) {
         ARBShaderObjects.glUseProgramObjectARB(count41);
      } else {
         GL20.glUseProgram(count41);
      }
   }

   public static void applyIndex9(int count41) {
      if (enabled2) {
         ARBVertexBufferObject.glDeleteBuffersARB(count41);
      } else {
         GL15.glDeleteBuffers(count41);
      }
   }

   public static int getCount3() {
      return enabled15 ? ARBShaderObjects.glCreateProgramObjectARB() : GL20.glCreateProgram();
   }

   public static void applyIntBuffer3(int count41, IntBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniform2ARB(count41, buffer);
      } else {
         GL20.glUniform2(count41, buffer);
      }
   }

   public static void applyFloatBuffer3(int count41, FloatBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniform3ARB(count41, buffer);
      } else {
         GL20.glUniform3(count41, buffer);
      }
   }

   public static int computeCountByCharSequence(int count41, CharSequence text) {
      return enabled15 ? ARBVertexShader.glGetAttribLocationARB(count41, text) : GL20.glGetAttribLocation(count41, text);
   }

   public static int getCountAsIndex() {
      if (!enabled) {
         return -1;
      } else {
         switch (count19) {
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

   public static void applyIndex3(int count41, int count42) {
      if (enabled) {
         switch (count19) {
            case 0:
               GL30.glBindFramebuffer(count41, count42);
               break;
            case 1:
               ARBFramebufferObject.glBindFramebuffer(count41, count42);
               break;
            case 2:
               EXTFramebufferObject.glBindFramebufferEXT(count41, count42);
         }
      }
   }

   public static void applyIndexByIndex(int count41, int count42, int count43, int count44) {
      if (enabled) {
         switch (count19) {
            case 0:
               GL30.glRenderbufferStorage(count41, count42, count43, count44);
               break;
            case 1:
               ARBFramebufferObject.glRenderbufferStorage(count41, count42, count43, count44);
               break;
            case 2:
               EXTFramebufferObject.glRenderbufferStorageEXT(count41, count42, count43, count44);
         }
      }
   }

   public static void applyFloatBuffer(int count41, boolean flag, FloatBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniformMatrix4ARB(count41, flag, buffer);
      } else {
         GL20.glUniformMatrix4(count41, flag, buffer);
      }
   }

   public static void applyIndex5(int count41) {
      if (enabled15) {
         ARBShaderObjects.glDeleteObjectARB(count41);
      } else {
         GL20.glDeleteShader(count41);
      }
   }

   public static String getText() {
      return labelText2 == null ? "<unknown>" : labelText2;
   }

   public static int computeCountByIndex(int count41) {
      if (!enabled) {
         return -1;
      } else {
         switch (count19) {
            case 0:
               return GL30.glCheckFramebufferStatus(count41);
            case 1:
               return ARBFramebufferObject.glCheckFramebufferStatus(count41);
            case 2:
               return EXTFramebufferObject.glCheckFramebufferStatusEXT(count41);
            default:
               return -1;
         }
      }
   }

   public static int computeCountByIndex(int count41, int count42) {
      return enabled15 ? ARBShaderObjects.glGetObjectParameteriARB(count41, count42) : GL20.glGetShaderi(count41, count42);
   }

   public static void applyIndex7(int count41) {
      if (enabled) {
         switch (count19) {
            case 0:
               GL30.glDeleteRenderbuffers(count41);
               break;
            case 1:
               ARBFramebufferObject.glDeleteRenderbuffers(count41);
               break;
            case 2:
               EXTFramebufferObject.glDeleteRenderbuffersEXT(count41);
         }
      }
   }

   public static void applyFloatBuffer2(int count41, FloatBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniform4ARB(count41, buffer);
      } else {
         GL20.glUniform4(count41, buffer);
      }
   }

   public static void applyByteBuffer(int count41, ByteBuffer buffer, int count42) {
      if (enabled2) {
         ARBVertexBufferObject.glBufferDataARB(count41, buffer, count42);
      } else {
         GL15.glBufferData(count41, buffer, count42);
      }
   }

   public static void applyIntBuffer2(int count41, IntBuffer buffer) {
      if (enabled15) {
         ARBShaderObjects.glUniform3ARB(count41, buffer);
      } else {
         GL20.glUniform3(count41, buffer);
      }
   }

   public static void applyIndex2(int count41, int count42) {
      if (enabled2) {
         ARBVertexBufferObject.glBindBufferARB(count41, count42);
      } else {
         GL15.glBindBuffer(count41, count42);
      }
   }

   public static void applyIndex3(int count41) {
      if (enabled15) {
         ARBShaderObjects.glLinkProgramARB(count41);
      } else {
         GL20.glLinkProgram(count41);
      }
   }
}
