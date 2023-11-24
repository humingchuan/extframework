import humc.lab.ext.core.proxy.ProxyFactoryByJDK;
import humc.lab.ext.demo.MyBizObj;
import humc.lab.ext.demo.NameSpi;
import humc.lab.ext.demo.NameSpiImpl1;
import humc.lab.ext.demo.NameSpiImpl2;


/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-20 22:31
 * @description
 */
public class Main2 {
    public static void main(String[] args) {
        new Main2().test2();
    }

    public void test2() {
        NameSpiImpl1 instance = NameSpiImpl1.INSTANCE;
        NameSpiImpl2 instance2 = NameSpiImpl2.INSTANCE;

        NameSpi spi = ProxyFactoryByJDK.Companion.proxy(NameSpi.class);
        MyBizObj bizObj = new MyBizObj("hi");

        spi.first(it -> {
            it.enrichName(bizObj);
            return null;
        });

        spi.enrichName(bizObj);
        System.out.println(bizObj.getName());
    }
}
