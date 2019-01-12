package cn.yusei.jvm.instruction;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.cast.D2Instructions.D2F;
import cn.yusei.jvm.instruction.cast.D2Instructions.D2I;
import cn.yusei.jvm.instruction.cast.D2Instructions.D2L;
import cn.yusei.jvm.instruction.cast.F2Instructions.F2D;
import cn.yusei.jvm.instruction.cast.F2Instructions.F2I;
import cn.yusei.jvm.instruction.cast.F2Instructions.F2L;
import cn.yusei.jvm.instruction.cast.I2Instructions.I2D;
import cn.yusei.jvm.instruction.cast.I2Instructions.I2F;
import cn.yusei.jvm.instruction.cast.I2Instructions.I2L;
import cn.yusei.jvm.instruction.cast.L2Instructions.L2D;
import cn.yusei.jvm.instruction.cast.L2Instructions.L2F;
import cn.yusei.jvm.instruction.cast.L2Instructions.L2I;
import cn.yusei.jvm.instruction.compare.CmpInstructions.DCMPG;
import cn.yusei.jvm.instruction.compare.CmpInstructions.DCMPL;
import cn.yusei.jvm.instruction.compare.CmpInstructions.FCMPG;
import cn.yusei.jvm.instruction.compare.CmpInstructions.FCMPL;
import cn.yusei.jvm.instruction.compare.CmpInstructions.LCMP;
import cn.yusei.jvm.instruction.compare.IfAcmpInstructions.IF_ACMPEQ;
import cn.yusei.jvm.instruction.compare.IfAcmpInstructions.IF_ACMPNE;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPEQ;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPGE;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPGT;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPLE;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPLT;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPNE;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFEQ;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFGE;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFGT;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFLE;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFLT;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFNE;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ACONST_NULL;
import cn.yusei.jvm.instruction.constant.ConstInstructions.DCONST_0;
import cn.yusei.jvm.instruction.constant.ConstInstructions.DCONST_1;
import cn.yusei.jvm.instruction.constant.ConstInstructions.FCONST_0;
import cn.yusei.jvm.instruction.constant.ConstInstructions.FCONST_1;
import cn.yusei.jvm.instruction.constant.ConstInstructions.FCONST_2;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_0;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_1;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_2;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_3;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_4;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_5;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_M1;
import cn.yusei.jvm.instruction.constant.ConstInstructions.LCONST_0;
import cn.yusei.jvm.instruction.constant.ConstInstructions.LCONST_1;
import cn.yusei.jvm.instruction.constant.NOP;
import cn.yusei.jvm.instruction.constant.PushInstructions.BIPUSH;
import cn.yusei.jvm.instruction.constant.PushInstructions.SIPUSH;
import cn.yusei.jvm.instruction.control.GOTO;
import cn.yusei.jvm.instruction.control.SwitchInstructions.LOOKUP_SWITCH;
import cn.yusei.jvm.instruction.control.SwitchInstructions.TABLE_SWITCH;
import cn.yusei.jvm.instruction.extend.GOTO_W;
import cn.yusei.jvm.instruction.extend.IfNullInstructions.IFNONNULL;
import cn.yusei.jvm.instruction.extend.IfNullInstructions.IFNULL;
import cn.yusei.jvm.instruction.extend.UnsupportedOpCodeError;
import cn.yusei.jvm.instruction.extend.WIDE;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD_3;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD_3;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD_3;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD_3;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD_3;
import cn.yusei.jvm.instruction.math.AddInstructions.DADD;
import cn.yusei.jvm.instruction.math.AddInstructions.FADD;
import cn.yusei.jvm.instruction.math.AddInstructions.IADD;
import cn.yusei.jvm.instruction.math.AddInstructions.LADD;
import cn.yusei.jvm.instruction.math.AndInstructions.IAND;
import cn.yusei.jvm.instruction.math.AndInstructions.LAND;
import cn.yusei.jvm.instruction.math.DivInstructions.DDIV;
import cn.yusei.jvm.instruction.math.DivInstructions.FDIV;
import cn.yusei.jvm.instruction.math.DivInstructions.IDIV;
import cn.yusei.jvm.instruction.math.DivInstructions.LDIV;
import cn.yusei.jvm.instruction.math.IINC;
import cn.yusei.jvm.instruction.math.MulInstructions.DMUL;
import cn.yusei.jvm.instruction.math.MulInstructions.FMUL;
import cn.yusei.jvm.instruction.math.MulInstructions.IMUL;
import cn.yusei.jvm.instruction.math.MulInstructions.LMUL;
import cn.yusei.jvm.instruction.math.OrInstructions.IOR;
import cn.yusei.jvm.instruction.math.OrInstructions.LOR;
import cn.yusei.jvm.instruction.math.RemInstructions.DREM;
import cn.yusei.jvm.instruction.math.RemInstructions.FREM;
import cn.yusei.jvm.instruction.math.RemInstructions.IREM;
import cn.yusei.jvm.instruction.math.RemInstructions.LREM;
import cn.yusei.jvm.instruction.math.ShInstructions.ISHL;
import cn.yusei.jvm.instruction.math.ShInstructions.ISHR;
import cn.yusei.jvm.instruction.math.ShInstructions.IUSHR;
import cn.yusei.jvm.instruction.math.ShInstructions.LSHL;
import cn.yusei.jvm.instruction.math.ShInstructions.LSHR;
import cn.yusei.jvm.instruction.math.ShInstructions.LUSHR;
import cn.yusei.jvm.instruction.math.SubInstructions.DSUB;
import cn.yusei.jvm.instruction.math.SubInstructions.FSUB;
import cn.yusei.jvm.instruction.math.SubInstructions.ISUB;
import cn.yusei.jvm.instruction.math.SubInstructions.LSUB;
import cn.yusei.jvm.instruction.math.XorInstructions.IXOR;
import cn.yusei.jvm.instruction.math.XorInstructions.LXOR;
import cn.yusei.jvm.instruction.reference.CHECK_CAST;
import cn.yusei.jvm.instruction.reference.GET_FIELD;
import cn.yusei.jvm.instruction.reference.GET_STATIC;
import cn.yusei.jvm.instruction.reference.INSTANCE_OF;
import cn.yusei.jvm.instruction.reference.LdcInstructions.LDC;
import cn.yusei.jvm.instruction.reference.LdcInstructions.LDC2_W;
import cn.yusei.jvm.instruction.reference.LdcInstructions.LDC_W;
import cn.yusei.jvm.instruction.reference.NEW;
import cn.yusei.jvm.instruction.reference.PUT_FIELD;
import cn.yusei.jvm.instruction.reference.PUT_STATIC;
import cn.yusei.jvm.instruction.stack.DupInstructions.DUP;
import cn.yusei.jvm.instruction.stack.DupInstructions.DUP2;
import cn.yusei.jvm.instruction.stack.DupInstructions.DUP2_X1;
import cn.yusei.jvm.instruction.stack.DupInstructions.DUP2_X2;
import cn.yusei.jvm.instruction.stack.DupInstructions.DUP_X1;
import cn.yusei.jvm.instruction.stack.DupInstructions.DUP_X2;
import cn.yusei.jvm.instruction.stack.PopInstructions.POP;
import cn.yusei.jvm.instruction.stack.PopInstructions.POP2;
import cn.yusei.jvm.instruction.stack.SWAP;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE_3;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE_3;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE_3;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE_3;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE_3;

public final class InstructionFactory {

	private InstructionFactory() {
	}

	private static class NoOperandInstructionsHolder {
		static NoOperandInstruction nop = new NOP();
		static NoOperandInstruction aconst_null = new ACONST_NULL();
		static NoOperandInstruction iconst_m1 = new ICONST_M1();
		static NoOperandInstruction iconst_0 = new ICONST_0();
		static NoOperandInstruction iconst_1 = new ICONST_1();
		static NoOperandInstruction iconst_2 = new ICONST_2();
		static NoOperandInstruction iconst_3 = new ICONST_3();
		static NoOperandInstruction iconst_4 = new ICONST_4();
		static NoOperandInstruction iconst_5 = new ICONST_5();
		static NoOperandInstruction lconst_0 = new LCONST_0();
		static NoOperandInstruction lconst_1 = new LCONST_1();
		static NoOperandInstruction fconst_0 = new FCONST_0();
		static NoOperandInstruction fconst_1 = new FCONST_1();
		static NoOperandInstruction fconst_2 = new FCONST_2();
		static NoOperandInstruction dconst_0 = new DCONST_0();
		static NoOperandInstruction dconst_1 = new DCONST_1();
		static NoOperandInstruction iload_0 = new ILOAD_0();
		static NoOperandInstruction iload_1 = new ILOAD_1();
		static NoOperandInstruction iload_2 = new ILOAD_2();
		static NoOperandInstruction iload_3 = new ILOAD_3();
		static NoOperandInstruction lload_0 = new LLOAD_0();
		static NoOperandInstruction lload_1 = new LLOAD_1();
		static NoOperandInstruction lload_2 = new LLOAD_2();
		static NoOperandInstruction lload_3 = new LLOAD_3();
		static NoOperandInstruction fload_0 = new FLOAD_0();
		static NoOperandInstruction fload_1 = new FLOAD_1();
		static NoOperandInstruction fload_2 = new FLOAD_2();
		static NoOperandInstruction fload_3 = new FLOAD_3();
		static NoOperandInstruction dload_0 = new DLOAD_0();
		static NoOperandInstruction dload_1 = new DLOAD_1();
		static NoOperandInstruction dload_2 = new DLOAD_2();
		static NoOperandInstruction dload_3 = new DLOAD_3();
		static NoOperandInstruction aload_0 = new ALOAD_0();
		static NoOperandInstruction aload_1 = new ALOAD_1();
		static NoOperandInstruction aload_2 = new ALOAD_2();
		static NoOperandInstruction aload_3 = new ALOAD_3();
		// static NoOperandsInstruction iaload = new IALOAD();
		// static NoOperandsInstruction laload = new LALOAD();
		// static NoOperandsInstruction faload = new FALOAD();
		// static NoOperandsInstruction daload = new DALOAD();
		// static NoOperandsInstruction aaload = new AALOAD();
		// static NoOperandsInstruction baload = new BALOAD();
		// static NoOperandsInstruction caload = new CALOAD();
		// static NoOperandsInstruction saload = new SALOAD();
		static NoOperandInstruction istore_0 = new ISTORE_0();
		static NoOperandInstruction istore_1 = new ISTORE_1();
		static NoOperandInstruction istore_2 = new ISTORE_2();
		static NoOperandInstruction istore_3 = new ISTORE_3();
		static NoOperandInstruction lstore_0 = new LSTORE_0();
		static NoOperandInstruction lstore_1 = new LSTORE_1();
		static NoOperandInstruction lstore_2 = new LSTORE_2();
		static NoOperandInstruction lstore_3 = new LSTORE_3();
		static NoOperandInstruction fstore_0 = new FSTORE_0();
		static NoOperandInstruction fstore_1 = new FSTORE_1();
		static NoOperandInstruction fstore_2 = new FSTORE_2();
		static NoOperandInstruction fstore_3 = new FSTORE_3();
		static NoOperandInstruction dstore_0 = new DSTORE_0();
		static NoOperandInstruction dstore_1 = new DSTORE_1();
		static NoOperandInstruction dstore_2 = new DSTORE_2();
		static NoOperandInstruction dstore_3 = new DSTORE_3();
		static NoOperandInstruction astore_0 = new ASTORE_0();
		static NoOperandInstruction astore_1 = new ASTORE_1();
		static NoOperandInstruction astore_2 = new ASTORE_2();
		static NoOperandInstruction astore_3 = new ASTORE_3();
		// static NoOperandsInstruction iastore = new IASTORE();
		// static NoOperandsInstruction lastore = new LASTORE();
		// static NoOperandsInstruction fastore = new FASTORE();
		// static NoOperandsInstruction dastore = new DASTORE();
		// static NoOperandsInstruction aastore = new AASTORE();
		// static NoOperandsInstruction bastore = new BASTORE();
		// static NoOperandsInstruction castore = new CASTORE();
		// static NoOperandsInstruction sastore = new SASTORE();
		static NoOperandInstruction pop = new POP();
		static NoOperandInstruction pop2 = new POP2();
		static NoOperandInstruction dup = new DUP();
		static NoOperandInstruction dup_x1 = new DUP_X1();
		static NoOperandInstruction dup_x2 = new DUP_X2();
		static NoOperandInstruction dup2 = new DUP2();
		static NoOperandInstruction dup2_x1 = new DUP2_X1();
		static NoOperandInstruction dup2_x2 = new DUP2_X2();
		static NoOperandInstruction swap = new SWAP();
		static NoOperandInstruction iadd = new IADD();
		static NoOperandInstruction ladd = new LADD();
		static NoOperandInstruction fadd = new FADD();
		static NoOperandInstruction dadd = new DADD();
		static NoOperandInstruction isub = new ISUB();
		static NoOperandInstruction lsub = new LSUB();
		static NoOperandInstruction fsub = new FSUB();
		static NoOperandInstruction dsub = new DSUB();
		static NoOperandInstruction imul = new IMUL();
		static NoOperandInstruction lmul = new LMUL();
		static NoOperandInstruction fmul = new FMUL();
		static NoOperandInstruction dmul = new DMUL();
		static NoOperandInstruction idiv = new IDIV();
		static NoOperandInstruction ldiv = new LDIV();
		static NoOperandInstruction fdiv = new FDIV();
		static NoOperandInstruction ddiv = new DDIV();
		static NoOperandInstruction irem = new IREM();
		static NoOperandInstruction lrem = new LREM();
		static NoOperandInstruction frem = new FREM();
		static NoOperandInstruction drem = new DREM();
		// static NoOperandsInstruction ineg = new INEG();
		// static NoOperandsInstruction lneg = new LNEG();
		// static NoOperandsInstruction fneg = new FNEG();
		// static NoOperandsInstruction dneg = new DNEG();
		static NoOperandInstruction ishl = new ISHL();
		static NoOperandInstruction lshl = new LSHL();
		static NoOperandInstruction ishr = new ISHR();
		static NoOperandInstruction lshr = new LSHR();
		static NoOperandInstruction iushr = new IUSHR();
		static NoOperandInstruction lushr = new LUSHR();
		static NoOperandInstruction iand = new IAND();
		static NoOperandInstruction land = new LAND();
		static NoOperandInstruction ior = new IOR();
		static NoOperandInstruction lor = new LOR();
		static NoOperandInstruction ixor = new IXOR();
		static NoOperandInstruction lxor = new LXOR();
		static NoOperandInstruction i2l = new I2L();
		static NoOperandInstruction i2f = new I2F();
		static NoOperandInstruction i2d = new I2D();
		static NoOperandInstruction l2i = new L2I();
		static NoOperandInstruction l2f = new L2F();
		static NoOperandInstruction l2d = new L2D();
		static NoOperandInstruction f2i = new F2I();
		static NoOperandInstruction f2l = new F2L();
		static NoOperandInstruction f2d = new F2D();
		static NoOperandInstruction d2i = new D2I();
		static NoOperandInstruction d2l = new D2L();
		static NoOperandInstruction d2f = new D2F();
		// static NoOperandsInstruction i2b = new I2B();
		// static NoOperandsInstruction i2c = new I2C();
		// static NoOperandsInstruction i2s = new I2S();
		static NoOperandInstruction lcmp = new LCMP();
		static NoOperandInstruction fcmpl = new FCMPL();
		static NoOperandInstruction fcmpg = new FCMPG();
		static NoOperandInstruction dcmpl = new DCMPL();
		static NoOperandInstruction dcmpg = new DCMPG();
		// static NoOperandsInstruction ireturn = new IRETURN();
		// static NoOperandsInstruction lreturn = new LRETURN();
		// static NoOperandsInstruction freturn = new FRETURN();
		// static NoOperandsInstruction dreturn = new DRETURN();
		// static NoOperandsInstruction areturn = new ARETURN();
		// static NoOperandsInstruction _return = new RETURN();
		// static NoOperandsInstruction arraylength = new ARRAY_LENGTH();
		// static NoOperandsInstruction athrow = new ATHROW();
		// static NoOperandsInstruction monitorenter = new MONITOR_ENTER();
		// static NoOperandsInstruction monitorexit = new MONITOR_EXIT();
		// static NoOperandsInstruction invoke_native = new INVOKE_NATIVE();
	}

	public static Instruction createInstruction(int opCode) {
		switch (opCode) {
		case 0x00:
			return NoOperandInstructionsHolder.nop;
		case 0x01:
			return NoOperandInstructionsHolder.aconst_null;
		case 0x02:
			return NoOperandInstructionsHolder.iconst_m1;
		case 0x03:
			return NoOperandInstructionsHolder.iconst_0;
		case 0x04:
			return NoOperandInstructionsHolder.iconst_1;
		case 0x05:
			return NoOperandInstructionsHolder.iconst_2;
		case 0x06:
			return NoOperandInstructionsHolder.iconst_3;
		case 0x07:
			return NoOperandInstructionsHolder.iconst_4;
		case 0x08:
			return NoOperandInstructionsHolder.iconst_5;
		case 0x09:
			return NoOperandInstructionsHolder.lconst_0;
		case 0x0a:
			return NoOperandInstructionsHolder.lconst_1;
		case 0x0b:
			return NoOperandInstructionsHolder.fconst_0;
		case 0x0c:
			return NoOperandInstructionsHolder.fconst_1;
		case 0x0d:
			return NoOperandInstructionsHolder.fconst_2;
		case 0x0e:
			return NoOperandInstructionsHolder.dconst_0;
		case 0x0f:
			return NoOperandInstructionsHolder.dconst_1;
		case 0x10:
			return new BIPUSH();
		case 0x11:
			return new SIPUSH();
		 case 0x12:
		 return new LDC();
		 case 0x13:
		 return new LDC_W();
		 case 0x14:
		 return new LDC2_W();
		case 0x15:
			return new ILOAD();
		case 0x16:
			return new LLOAD();
		case 0x17:
			return new FLOAD();
		case 0x18:
			return new DLOAD();
		case 0x19:
			return new ALOAD();
		case 0x1a:
			return NoOperandInstructionsHolder.iload_0;
		case 0x1b:
			return NoOperandInstructionsHolder.iload_1;
		case 0x1c:
			return NoOperandInstructionsHolder.iload_2;
		case 0x1d:
			return NoOperandInstructionsHolder.iload_3;
		case 0x1e:
			return NoOperandInstructionsHolder.lload_0;
		case 0x1f:
			return NoOperandInstructionsHolder.lload_1;
		case 0x20:
			return NoOperandInstructionsHolder.lload_2;
		case 0x21:
			return NoOperandInstructionsHolder.lload_3;
		case 0x22:
			return NoOperandInstructionsHolder.fload_0;
		case 0x23:
			return NoOperandInstructionsHolder.fload_1;
		case 0x24:
			return NoOperandInstructionsHolder.fload_2;
		case 0x25:
			return NoOperandInstructionsHolder.fload_3;
		case 0x26:
			return NoOperandInstructionsHolder.dload_0;
		case 0x27:
			return NoOperandInstructionsHolder.dload_1;
		case 0x28:
			return NoOperandInstructionsHolder.dload_2;
		case 0x29:
			return NoOperandInstructionsHolder.dload_3;
		case 0x2a:
			return NoOperandInstructionsHolder.aload_0;
		case 0x2b:
			return NoOperandInstructionsHolder.aload_1;
		case 0x2c:
			return NoOperandInstructionsHolder.aload_2;
		case 0x2d:
			return NoOperandInstructionsHolder.aload_3;
		// case 0x2e:
		// return NoOperandInstructionsHolder.iaload;
		// case 0x2f:
		// return NoOperandInstructionsHolder.laload;
		// case 0x30:
		// return NoOperandInstructionsHolder.faload;
		// case 0x31:
		// return NoOperandInstructionsHolder.daload;
		// case 0x32:
		// return NoOperandInstructionsHolder.aaload;
		// case 0x33:
		// return NoOperandInstructionsHolder.baload;
		// case 0x34:
		// return NoOperandInstructionsHolder.caload;
		// case 0x35:
		// return NoOperandInstructionsHolder.saload;
		case 0x36:
			return new ISTORE();
		case 0x37:
			return new LSTORE();
		case 0x38:
			return new FSTORE();
		case 0x39:
			return new DSTORE();
		case 0x3a:
			return new ASTORE();
		case 0x3b:
			return NoOperandInstructionsHolder.istore_0;
		case 0x3c:
			return NoOperandInstructionsHolder.istore_1;
		case 0x3d:
			return NoOperandInstructionsHolder.istore_2;
		case 0x3e:
			return NoOperandInstructionsHolder.istore_3;
		case 0x3f:
			return NoOperandInstructionsHolder.lstore_0;
		case 0x40:
			return NoOperandInstructionsHolder.lstore_1;
		case 0x41:
			return NoOperandInstructionsHolder.lstore_2;
		case 0x42:
			return NoOperandInstructionsHolder.lstore_3;
		case 0x43:
			return NoOperandInstructionsHolder.fstore_0;
		case 0x44:
			return NoOperandInstructionsHolder.fstore_1;
		case 0x45:
			return NoOperandInstructionsHolder.fstore_2;
		case 0x46:
			return NoOperandInstructionsHolder.fstore_3;
		case 0x47:
			return NoOperandInstructionsHolder.dstore_0;
		case 0x48:
			return NoOperandInstructionsHolder.dstore_1;
		case 0x49:
			return NoOperandInstructionsHolder.dstore_2;
		case 0x4a:
			return NoOperandInstructionsHolder.dstore_3;
		case 0x4b:
			return NoOperandInstructionsHolder.astore_0;
		case 0x4c:
			return NoOperandInstructionsHolder.astore_1;
		case 0x4d:
			return NoOperandInstructionsHolder.astore_2;
		case 0x4e:
			return NoOperandInstructionsHolder.astore_3;
		// case 0x4f:
		// return NoOperandInstructionsHolder.iastore;
		// case 0x50:
		// return NoOperandInstructionsHolder.lastore;
		// case 0x51:
		// return NoOperandInstructionsHolder.fastore;
		// case 0x52:
		// return NoOperandInstructionsHolder.dastore;
		// case 0x53:
		// return NoOperandInstructionsHolder.aastore;
		// case 0x54:
		// return NoOperandInstructionsHolder.bastore;
		// case 0x55:
		// return NoOperandInstructionsHolder.castore;
		// case 0x56:
		// return NoOperandInstructionsHolder.sastore;
		case 0x57:
			return NoOperandInstructionsHolder.pop;
		case 0x58:
			return NoOperandInstructionsHolder.pop2;
		case 0x59:
			return NoOperandInstructionsHolder.dup;
		case 0x5a:
			return NoOperandInstructionsHolder.dup_x1;
		case 0x5b:
			return NoOperandInstructionsHolder.dup_x2;
		case 0x5c:
			return NoOperandInstructionsHolder.dup2;
		case 0x5d:
			return NoOperandInstructionsHolder.dup2_x1;
		case 0x5e:
			return NoOperandInstructionsHolder.dup2_x2;
		case 0x5f:
			return NoOperandInstructionsHolder.swap;
		case 0x60:
			return NoOperandInstructionsHolder.iadd;
		case 0x61:
			return NoOperandInstructionsHolder.ladd;
		case 0x62:
			return NoOperandInstructionsHolder.fadd;
		case 0x63:
			return NoOperandInstructionsHolder.dadd;
		case 0x64:
			return NoOperandInstructionsHolder.isub;
		case 0x65:
			return NoOperandInstructionsHolder.lsub;
		case 0x66:
			return NoOperandInstructionsHolder.fsub;
		case 0x67:
			return NoOperandInstructionsHolder.dsub;
		case 0x68:
			return NoOperandInstructionsHolder.imul;
		case 0x69:
			return NoOperandInstructionsHolder.lmul;
		case 0x6a:
			return NoOperandInstructionsHolder.fmul;
		case 0x6b:
			return NoOperandInstructionsHolder.dmul;
		case 0x6c:
			return NoOperandInstructionsHolder.idiv;
		case 0x6d:
			return NoOperandInstructionsHolder.ldiv;
		case 0x6e:
			return NoOperandInstructionsHolder.fdiv;
		case 0x6f:
			return NoOperandInstructionsHolder.ddiv;
		case 0x70:
			return NoOperandInstructionsHolder.irem;
		case 0x71:
			return NoOperandInstructionsHolder.lrem;
		case 0x72:
			return NoOperandInstructionsHolder.frem;
		case 0x73:
			return NoOperandInstructionsHolder.drem;
		// case 0x74:
		// return NoOperandInstructionsHolder.ineg;
		// case 0x75:
		// return NoOperandInstructionsHolder.lneg;
		// case 0x76:
		// return NoOperandInstructionsHolder.fneg;
		// case 0x77:
		// return NoOperandInstructionsHolder.dneg;
		case 0x78:
			return NoOperandInstructionsHolder.ishl;
		case 0x79:
			return NoOperandInstructionsHolder.lshl;
		case 0x7a:
			return NoOperandInstructionsHolder.ishr;
		case 0x7b:
			return NoOperandInstructionsHolder.lshr;
		case 0x7c:
			return NoOperandInstructionsHolder.iushr;
		case 0x7d:
			return NoOperandInstructionsHolder.lushr;
		case 0x7e:
			return NoOperandInstructionsHolder.iand;
		case 0x7f:
			return NoOperandInstructionsHolder.land;
		case 0x80:
			return NoOperandInstructionsHolder.ior;
		case 0x81:
			return NoOperandInstructionsHolder.lor;
		case 0x82:
			return NoOperandInstructionsHolder.ixor;
		case 0x83:
			return NoOperandInstructionsHolder.lxor;
		case 0x84:
			return new IINC();
		case 0x85:
			return NoOperandInstructionsHolder.i2l;
		case 0x86:
			return NoOperandInstructionsHolder.i2f;
		case 0x87:
			return NoOperandInstructionsHolder.i2d;
		case 0x88:
			return NoOperandInstructionsHolder.l2i;
		case 0x89:
			return NoOperandInstructionsHolder.l2f;
		case 0x8a:
			return NoOperandInstructionsHolder.l2d;
		case 0x8b:
			return NoOperandInstructionsHolder.f2i;
		case 0x8c:
			return NoOperandInstructionsHolder.f2l;
		case 0x8d:
			return NoOperandInstructionsHolder.f2d;
		case 0x8e:
			return NoOperandInstructionsHolder.d2i;
		case 0x8f:
			return NoOperandInstructionsHolder.d2l;
		case 0x90:
			return NoOperandInstructionsHolder.d2f;
		// case 0x91:
		// return NoOperandInstructionsHolder.i2b;
		// case 0x92:
		// return NoOperandInstructionsHolder.i2c;
		// case 0x93:
		// return NoOperandInstructionsHolder.i2s;
		 case 0x94:
		 return NoOperandInstructionsHolder.lcmp;
		case 0x95:
			return NoOperandInstructionsHolder.fcmpl;
		case 0x96:
			return NoOperandInstructionsHolder.fcmpg;
		case 0x97:
			return NoOperandInstructionsHolder.dcmpl;
		case 0x98:
			return NoOperandInstructionsHolder.dcmpg;
		case 0x99:
			return new IFEQ();
		case 0x9a:
			return new IFNE();
		case 0x9b:
			return new IFLT();
		case 0x9c:
			return new IFGE();
		case 0x9d:
			return new IFGT();
		case 0x9e:
			return new IFLE();
		case 0x9f:
			return new IF_ICMPEQ();
		case 0xa0:
			return new IF_ICMPNE();
		case 0xa1:
			return new IF_ICMPLT();
		case 0xa2:
			return new IF_ICMPGE();
		case 0xa3:
			return new IF_ICMPGT();
		case 0xa4:
			return new IF_ICMPLE();
		case 0xa5:
			return new IF_ACMPEQ();
		case 0xa6:
			return new IF_ACMPNE();
		case 0xa7:
			return new GOTO();
		// case 0xa8:
		// return new JSR();
		// case 0xa9:
		// return new RET();
		case 0xaa:
			return new TABLE_SWITCH();
		case 0xab:
			return new LOOKUP_SWITCH();
		// case 0xac:
		// return NoOperandInstructionsHolder.ireturn;
		// case 0xad:
		// return NoOperandInstructionsHolder.lreturn;
		// case 0xae:
		// return NoOperandInstructionsHolder.freturn;
		// case 0xaf:
		// return NoOperandInstructionsHolder.dreturn;
		// case 0xb0:
		// return NoOperandInstructionsHolder.areturn;
		// case 0xb1:
		// return NoOperandInstructionsHolder._return;
		 case 0xb2:
		 return new GET_STATIC();
		 case 0xb3:
		 return new PUT_STATIC();
		 case 0xb4:
		 return new GET_FIELD();
		 case 0xb5:
		 return new PUT_FIELD();
		// case 0xb6:
		// return new INVOKE_VIRTUAL();
		// case 0xb7:
		// return new INVOKE_SPECIAL();
		// case 0xb8:
		// return new INVOKE_STATIC();
		// case 0xb9:
		// return new INVOKE_INTERFACE();
		// case 0xba:
		// return new INVOKE_DYNAMIC();
		 case 0xbb:
		 return new NEW();
		// case 0xbc:
		// return new NEW_ARRAY();
		// case 0xbd:
		// return new ANEW_ARRAY();
		// case 0xbe:
		// return NoOperandInstructionsHolder.arraylength;
		// case 0xbf:
		// return NoOperandInstructionsHolder.athrow;
		 case 0xc0:
		 return new CHECK_CAST();
		 case 0xc1:
		 return new INSTANCE_OF();
		// case 0xc2:
		// return NoOperandInstructionsHolder.monitorenter;
		// case 0xc3:
		// return NoOperandInstructionsHolder.monitorexit;
		case 0xc4:
			return new WIDE();
		// case 0xc5:
		// return new MULTI_ANEW_ARRAY();
		case 0xc6:
			return new IFNULL();
		case 0xc7:
			return new IFNONNULL();
		case 0xc8:
			return new GOTO_W();
		// case 0xc9:
		// return new JSR_W();
		// case 0xca: todo breakpoint;
		// case 0xfe:
		// return NoOperandInstructionsHolder.invoke_native; // impdep1
		// case 0xff:
		// return new BOOTSTRAP(); // impdep2
		default:
			throw new UnsupportedOpCodeError(opCode + "");
		}
	}

}
