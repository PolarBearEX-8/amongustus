package net.optifine.shaders;

import java.util.ArrayList;
import java.util.List;

public class Programs {
   private List programs = new ArrayList();
   private Program programNone = this.make("", ProgramStage.NONE, true);

   public Program[] getPrograms(Program program, Program program2) {
      int index = program.getIndex();
      int index2 = program2.getIndex();
      if (index > index2) {
         int n = index;
         index = index2;
         index2 = n;
      }

      Program[] programs2 = new Program[index2 - index + 1];

      for (int i = 0; i < programs2.length; i++) {
         programs2[i] = (Program)this.programs.get(index + i);
      }

      return programs2;
   }

   public String[] getProgramNames() {
      String[] strings = new String[this.programs.size()];

      for (int i = 0; i < strings.length; i++) {
         strings[i] = ((Program)this.programs.get(i)).getName();
      }

      return strings;
   }

   @Override
   public String toString() {
      return this.programs.toString();
   }

   public int getCount() {
      return this.programs.size();
   }

   public Program makeGbuffers(String str, Program program) {
      return this.make2(str, ProgramStage.GBUFFERS, program);
   }

   public Program[] makeComposites(String str, int n) {
      Program[] programs2 = new Program[n];

      for (int i = 0; i < n; i++) {
         programs2[i] = this.makeComposite(i == 0 ? str : str + i);
      }

      return programs2;
   }

   public Program makeComposite(String str) {
      return this.make2(str, ProgramStage.COMPOSITE, this.programNone);
   }

   public Program makeDeferred(String str) {
      return this.make2(str, ProgramStage.DEFERRED, this.programNone);
   }

   public Program[] makeDeferreds(String str, int n) {
      Program[] programs2 = new Program[n];

      for (int i = 0; i < n; i++) {
         programs2[i] = this.makeDeferred(i == 0 ? str : str + i);
      }

      return programs2;
   }

   public Program[] getPrograms() {
      return (Program[])this.programs.toArray(new Program[this.programs.size()]);
   }

   public Program getProgramNone() {
      return this.programNone;
   }

   public Program getProgram(String str) {
      if (str == null) {
         return null;
      } else {
         for (int i = 0; i < this.programs.size(); i++) {
            Program program = (Program)this.programs.get(i);
            if (program.getName().equals(str)) {
               return program;
            }
         }

         return null;
      }
   }

   public Program makeVirtual(String str) {
      return this.make(str, ProgramStage.NONE, true);
   }

   private Program make(String str, ProgramStage programStage, boolean flag) {
      int size = this.programs.size();
      Program program = new Program(size, str, programStage, flag);
      this.programs.add(program);
      return program;
   }

   public Program makeShadow(String str, Program program) {
      return this.make2(str, ProgramStage.SHADOW, program);
   }

   public Program make2(String str, ProgramStage programStage, Program program) {
      int size = this.programs.size();
      Program program2 = new Program(size, str, programStage, program);
      this.programs.add(program2);
      return program2;
   }
}
