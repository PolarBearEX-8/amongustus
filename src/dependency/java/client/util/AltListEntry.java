package client.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.client.shader.ShaderUniformFloat;

public class AltListEntry {
   public final List list = new ArrayList();

   public StringHandler add4(String text) {
      StringHandler stringHandler = new StringHandler(text);
      this.list.add(stringHandler);
      return stringHandler;
   }

   public void updateState() {
      for (int index = 0; index < this.list.size(); index++) {
         ((ShaderUniform)this.list.get(index)).updateState5();
      }
   }

   public AuxiliaryFloatArrayHolder add2(String text) {
      AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder = new AuxiliaryFloatArrayHolder(text);
      this.list.add(auxiliaryFloatArrayHolder);
      return auxiliaryFloatArrayHolder;
   }

   public ExtendedStringHandler addByText(String text) {
      ExtendedStringHandler extendedStringHandler = new ExtendedStringHandler(text);
      this.list.add(extendedStringHandler);
      return extendedStringHandler;
   }

   public InternalStringHandler add(String text) {
      InternalStringHandler internalStringHandler = new InternalStringHandler(text);
      this.list.add(internalStringHandler);
      return internalStringHandler;
   }

   public ShaderUniformFloat add3(String text) {
      ShaderUniformFloat shaderUniformFloat = new ShaderUniformFloat(text);
      this.list.add(shaderUniformFloat);
      return shaderUniformFloat;
   }

   public AuxiliaryStringHandler add6(String text) {
      AuxiliaryStringHandler auxiliaryStringHandler = new AuxiliaryStringHandler(text);
      this.list.add(auxiliaryStringHandler);
      return auxiliaryStringHandler;
   }

   public FloatArrayHolder add5(String text) {
      FloatArrayHolder floatArrayHolder = new FloatArrayHolder(text);
      this.list.add(floatArrayHolder);
      return floatArrayHolder;
   }

   public void applyIndex(int count) {
      for (int index = 0; index < this.list.size(); index++) {
         ((ShaderUniform)this.list.get(index)).applyIndexByIndex(count);
      }
   }
}
