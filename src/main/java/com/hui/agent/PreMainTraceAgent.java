package com.hui.agent;

import com.hui.agent.transformer.CostTransformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class PreMainTraceAgent {
    /**
     * jvm 参数形式启动，运行此方法
     *
     * manifest需要配置属性Premain-Class
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain suc : " );
        inst.addTransformer(new CostTransformer(), true);
    }
    /**
     * 动态 attach 方式启动，运行此方法
     *
     * manifest需要配置属性Agent-Class
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agent suc---");
//        inst.addTransformer(new AgentAttachTransformer(), true);
    }

    static class AgentAttachTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("attach premain load Class:" + className);
            return classfileBuffer;
        }
    }

    static class DefineTransformer implements ClassFileTransformer{

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("premain load Class:" + className);
            return classfileBuffer;
        }
    }
}
