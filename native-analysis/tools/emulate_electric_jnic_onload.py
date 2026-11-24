import angr
claripy=angr.claripy
from angr.calling_conventions import SimCCMicrosoftAMD64
from pathlib import Path
import argparse
import hashlib
ap=argparse.ArgumentParser()
ap.add_argument("dll",type=Path)
ap.add_argument("seed",type=Path)
ap.add_argument("output",type=Path)
args=ap.parse_args()
DLL=str(args.dll)
SEED=args.seed.read_bytes()
if len(SEED)!=64: raise ValueError("expected 64-byte JNICLoader ByteBuffer")
p=angr.Project(DLL,auto_load_libs=False)
print('imagebase',hex(p.loader.main_object.mapped_base),'entry',hex(p.loader.main_object.entry))
print('malloc',p.loader.find_symbol('malloc'))
# Heap and JNI facade memory addresses live above the image.
VM=0x70000000; VMT=0x70001000; ENV=0x70002000; ENVT=0x70003000
BUF=0x70004000; OUT=0x70005000
HOOKBASE=0x70010000
class GetEnv(angr.SimProcedure):
 def run(self,vm,out,version):
  self.state.memory.store(out,claripy.BVV(ENV,64),endness='Iend_LE')
  return claripy.BVV(0,32)
class FindClass(angr.SimProcedure):
 def run(self,env,name): return claripy.BVV(0x70006000,64)
class GetStaticFieldID(angr.SimProcedure):
 def run(self,env,cls,name,sig): return claripy.BVV(0x70007000,64)
class GetStaticObjectField(angr.SimProcedure):
 def run(self,env,cls,fid): return claripy.BVV(0x70008000,64)
class GetDirectBufferAddress(angr.SimProcedure):
 def run(self,env,obj): return claripy.BVV(BUF,64)
class Stop(angr.SimProcedure):
 def run(self): self.exit(claripy.BVV(0,64))
for i,proc in enumerate((GetEnv,FindClass,GetStaticFieldID,GetStaticObjectField,GetDirectBufferAddress)):
 p.hook(HOOKBASE+i*0x10,proc())
p.hook(HOOKBASE+0x1000,Stop())
# Place the JavaVM and JNIEnv function tables.
s=p.factory.blank_state(add_options={angr.options.UNICORN})
s.memory.store(VM,claripy.BVV(VMT,64),endness='Iend_LE')
s.memory.store(ENV,claripy.BVV(ENVT,64),endness='Iend_LE')
for table,off,addr in ((VMT,0x30,HOOKBASE),(ENVT,0x30,HOOKBASE+0x10),(ENVT,0x480,HOOKBASE+0x20),(ENVT,0x488,HOOKBASE+0x30),(ENVT,0x730,HOOKBASE+0x40)):
 s.memory.store(table+off,claripy.BVV(addr,64),endness='Iend_LE')
s.memory.store(BUF,SEED)
cc=SimCCMicrosoftAMD64(p.arch)
st=p.factory.call_state(p.loader.main_object.get_symbol('JNI_OnLoad').rebased_addr,VM,cc=cc,base_state=s,ret_addr=HOOKBASE+0x1000)
sm=p.factory.simulation_manager(st)
for step in range(50000):
 if not sm.active: break
 sm.step()
 if step%2000==0: print('step',step,'active',len(sm.active),'dead',len(sm.deadended))
 if sm.deadended: break
if not sm.deadended: print('stopped',sm.errored[:1], 'active',len(sm.active)); raise SystemExit(2)
f=sm.deadended[0]
ret=f.solver.eval(f.regs.rax)
print('return',hex(ret))
# Ghidra global DAT_18011a080 is an RVA-derived VA in the preferred image range.
# Find it by extracting the image bytes whose pointer relocation resolves there, then use its known VA.
global_addr=0x18011a080
ptr=f.solver.eval(f.memory.load(global_addr,8,endness='Iend_LE'))
print('state_table_ptr',hex(ptr))
out=f.solver.eval(f.memory.load(ptr,0x4c92),cast_to=bytes)
args.output.write_bytes(out)
print('state_bytes',len(out),'sha256',hashlib.sha256(out).hexdigest())
for off,masks in ((0x1484,[0x1f,0x32]),(0x14b6,[0x3d,0x84]),(0x14d9,[0x57,0x8d])):
 print(hex(off),bytes(out[off+i]^m for i,m in enumerate(masks)))
