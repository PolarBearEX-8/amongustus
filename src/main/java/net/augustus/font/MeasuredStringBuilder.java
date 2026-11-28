package net.augustus.font;

import net.augustus.utils.TimeHelper;

public class MeasuredStringBuilder {
   private StringBuilder builder = new StringBuilder();
   private final CustomFontUtil font;
   private String cachedText;
   private int cachedWidth;

   public MeasuredStringBuilder insert2(int n, char[] chars) {
      this.builder.insert(n, chars);
      return this;
   }

   public MeasuredStringBuilder insert9(int n, double d) {
      this.builder.insert(n, d);
      return this;
   }

   public MeasuredStringBuilder insert(int n, boolean flag) {
      this.builder.insert(n, flag);
      return this;
   }

   public CharSequence subSequence(int n, int n2) {
      return this.builder.subSequence(n, n2);
   }

   public int lastIndexOf(String str, int n) {
      return this.builder.lastIndexOf(str, n);
   }

   public MeasuredStringBuilder insert7(int n, long l) {
      this.builder.insert(n, l);
      return this;
   }

   public MeasuredStringBuilder append7(Object obj) {
      this.builder.append(obj);
      return this;
   }

   @Override
   public String toString() {
      return this.builder.toString();
   }

   public int indexOf(String str, int n) {
      return this.builder.indexOf(str, n);
   }

   public MeasuredStringBuilder append4(CharSequence text) {
      this.builder.append(text);
      return this;
   }

   public MeasuredStringBuilder append9(char ch) {
      this.builder.append(ch);
      return this;
   }

   public int length() {
      return this.builder.length();
   }

   public String substring(int n, int n2) {
      return this.builder.substring(n, n2);
   }

   public MeasuredStringBuilder append6(boolean flag) {
      this.builder.append(flag);
      return this;
   }

   public int getCachedWidth() {
      return this.cachedWidth;
   }

   public MeasuredStringBuilder append5(StringBuffer sb) {
      this.builder.append(sb);
      return this;
   }

   public MeasuredStringBuilder deleteCharAt(int n) {
      this.builder.deleteCharAt(n);
      return this;
   }

   public MeasuredStringBuilder append(int n) {
      this.builder.append(n);
      return this;
   }

   public MeasuredStringBuilder insert3(int n, float f) {
      this.builder.insert(n, f);
      return this;
   }

   public MeasuredStringBuilder append(CharSequence text, int n, int n2) {
      this.builder.append(text, n, n2);
      return this;
   }

   public MeasuredStringBuilder append8(String str) {
      this.builder.append(str);
      return this;
   }

   public MeasuredStringBuilder delete(int n, int n2) {
      this.builder.delete(n, n2);
      return this;
   }

   public int lastIndexOf(String str) {
      return this.builder.lastIndexOf(str);
   }

   public MeasuredStringBuilder reverse() {
      this.builder.reverse();
      return this;
   }

   public MeasuredStringBuilder append3(long l) {
      this.builder.append(l);
      return this;
   }

   public MeasuredStringBuilder insert5(int n, int n2) {
      this.builder.insert(n, n2);
      return this;
   }

   public MeasuredStringBuilder(CustomFontUtil customFontUtil, CharSequence text) {
      this.font = customFontUtil;
      this.builder = new StringBuilder(text);
   }

   public int indexOf(String str) {
      return this.builder.indexOf(str);
   }

   public MeasuredStringBuilder insert8(int n, char ch) {
      this.builder.insert(n, ch);
      return this;
   }

   public MeasuredStringBuilder append2(char[] chars) {
      this.builder.append(chars);
      return this;
   }

   public String getCachedText() {
      return this.cachedText;
   }

   public MeasuredStringBuilder replace(int n, int n2, String str) {
      this.builder.replace(n, n2, str);
      return this;
   }

   public MeasuredStringBuilder insert(int n, char[] chars, int n2, int n3) {
      this.builder.insert(n, chars, n2, n3);
      return this;
   }

   public MeasuredStringBuilder(CustomFontUtil customFontUtil) {
      this.font = customFontUtil;
      this.builder = new StringBuilder();
   }

   public MeasuredStringBuilder insert6(int n, CharSequence text) {
      this.builder.insert(n, text);
      return this;
   }

   public MeasuredStringBuilder append2(char[] chars, int n, int n2) {
      this.builder.append(chars, n, n2);
      return this;
   }

   public MeasuredStringBuilder insert10(int n, Object obj) {
      this.builder.insert(n, obj);
      return this;
   }

   public MeasuredStringBuilder(CustomFontUtil customFontUtil, int n) {
      this.font = customFontUtil;
      this.builder = new StringBuilder(n);
   }

   public MeasuredStringBuilder append10(double d) {
      this.builder.append(d);
      return this;
   }

   public MeasuredStringBuilder insert4(int n, String str) {
      this.builder.insert(n, str);
      return this;
   }

   public MeasuredStringBuilder(CustomFontUtil customFontUtil, String str) {
      this.font = customFontUtil;
      this.builder = new StringBuilder(str);
   }

   public String substring(int n) {
      return this.builder.substring(n);
   }

   public void updateCache() {
      this.cachedText = this.builder.toString();
      String cachedText2 = this.cachedText;
      this.cachedWidth = (int)this.font.getStringWidth(cachedText2);
   }

   public boolean isEmpty() {
      return this.builder.length() == 0;
   }

   public MeasuredStringBuilder append11(float f) {
      this.builder.append(f);
      return this;
   }

   public MeasuredStringBuilder appendCodePoint(int n) {
      this.builder.appendCodePoint(n);
      return this;
   }

   public MeasuredStringBuilder insert2(int n, CharSequence text, int n2, int n3) {
      this.builder.insert(n, text, n2, n3);
      return this;
   }
}
