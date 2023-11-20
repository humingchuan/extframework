//package humc.lab.ext.facade;
//
//import humc.lab.ext.core.ExtensionProxyJava;
//import humc.lab.ext.demo.MyBizObj;
//import humc.lab.ext.demo.NameSpi;
//import kotlin.jvm.functions.Function1;
//import org.jetbrains.annotations.NotNull;
//
///**
// * @author: humingchuan
// * @date: 2023-11-19 14:34
// * @description
// */
//class NameSpiProxy3 extends ExtensionProxyJava<NameSpi> implements NameSpi {
//    @Override
//    public void enrichName(MyBizObj obj) {
//        first(new Function1<NameSpi, Void>() {
//            @Override
//            public Void invoke(NameSpi nameSpi) {
//                nameSpi.enrichName(obj);
//                return null;
//            }
//        });
//    }
//
//    @NotNull
//    @Override
//    public String getCode() {
//        return NameSpi.super.getCode();
//    }
//}