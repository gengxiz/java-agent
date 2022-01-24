- javaagent 技术，其实是字节码技术



- 程序运行后，连接
```java

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class TestAgentMain {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        //获取当前系统中所有 运行中的 虚拟机
        System.out.println("running JVM start ");
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            //如果虚拟机的名称为 xxx 则 该虚拟机为目标虚拟机，获取该虚拟机的 pid
            //然后加载 agent.jar 发送给该虚拟机
            System.out.println(vmd.displayName());
            if (vmd.displayName().equals("jpush.Application")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("D:\\dev\\GitSpace\\java-agent\\target\\java-agent-1.0-SNAPSHOT.jar");
                virtualMachine.detach();
            }
        }
    }

}

```



参考
1. https://www.cnblogs.com/rickiyang/p/11368932.html
2. https://cloud.tencent.com/developer/article/1602026
