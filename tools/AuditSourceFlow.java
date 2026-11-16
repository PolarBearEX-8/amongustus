import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/** Checks original binary contracts, retained JNI declarations and added linkage. */
public final class AuditSourceFlow {
    public static void main(String[] args)throws Exception{
        AuditSourceCandidate.load(args[0]);AuditSourceCandidate.load(args[1]);if(args.length>4)AuditSourceCandidate.load(args[4]);
        List<String> report=new ArrayList<String>();int count=0;
        try(JarFile original=new JarFile(args[1]);JarFile input=new JarFile(args[2])){
            Enumeration<JarEntry> entries=input.entries();while(entries.hasMoreElements()){
                JarEntry entry=entries.nextElement();if(!entry.getName().endsWith(".class"))continue;String name=entry.getName().substring(0,entry.getName().length()-6);
                ClassNode before=AuditSourceCandidate.read(original,name),after=AuditSourceCandidate.read(input,name);
                if(before.access!=after.access||!Objects.equals(before.superName,after.superName)||!before.interfaces.equals(after.interfaces))throw new AssertionError("Hierarchy/access changed "+name+" "+before.access+" / "+after.access);
                Set<String> f1=new TreeSet<String>(),f2=new TreeSet<String>(),m1=new TreeSet<String>(),m2=new TreeSet<String>();
                for(FieldNode field:before.fields)f1.add(field.name+field.desc+":"+field.access+":"+field.value);
                for(FieldNode field:after.fields)f2.add(field.name+field.desc+":"+field.access+":"+field.value);
                if(!f1.equals(f2))throw new AssertionError("Field metadata changed "+name+" "+f1+" / "+f2);
                for(MethodNode method:before.methods)m1.add(method.name+method.desc+":"+(method.access&~Opcodes.ACC_SYNTHETIC));
                for(MethodNode method:after.methods)if(!RestoreSourceBridges.bridge(method.name)&&!method.name.startsWith("recovery$flow$"))m2.add(method.name+method.desc+":"+(method.access&~Opcodes.ACC_SYNTHETIC));
                if(!m1.equals(m2)){Set<String> removed=new TreeSet<String>(m1);removed.removeAll(m2);Set<String> added=new TreeSet<String>(m2);added.removeAll(m1);throw new AssertionError("Method contract changed "+name+" missing="+removed+" added="+added);}
                AuditSourceCandidate.headers.put(name,before);Set<String> missing=AuditSourceCandidate.references(before);AuditSourceCandidate.headers.put(name,after);Set<String> newer=AuditSourceCandidate.references(after);newer.removeAll(missing);
                if(!newer.isEmpty())throw new AssertionError("New unresolved links "+name+" "+newer);
                report.add("PASS "+name+" fields="+f1.size()+" original_methods="+m1.size()+" java_flow_methods="+(after.methods.size()-before.methods.size())+" original_abi=true new_unresolved_links=0");count++;
            }
        }
        report.add("AUDITED_FLOW_CLASSES="+count+" gameplay_equivalence=false");Files.write(Paths.get(args[3]),report,StandardCharsets.UTF_8);System.out.println(report.get(report.size()-1));
    }
}
