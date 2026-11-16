package cc.baritone.utils;

public interface SpecialListener {
   boolean matchesText(String text);

   String resolveText(String text);
}
