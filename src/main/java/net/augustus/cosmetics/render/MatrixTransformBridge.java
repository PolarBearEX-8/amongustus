package net.augustus.cosmetics.render;

public interface MatrixTransformBridge {
   void pop();

   void push();

   void rotateX(float f);

   void rotateZ(float f);

   void rotate(float f, float f2, float f3);

   void translate(float f, float f2, float f3);

   void scale(float f, float f2, float f3);

   default Object getNativeStack() {
      return null;
   }

   void rotateY(float f);
}
