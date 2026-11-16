package client.util;

public interface InterpolationValidator {
   void applyIndex(int count, boolean flag);

   void applyIndex(int count, float factor);

   void applyText(int count, String text);
}
