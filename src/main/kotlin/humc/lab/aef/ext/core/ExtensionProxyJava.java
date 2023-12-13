//package humc.lab.ext.core;
//
//import humc.lab.ext.core.api.cfg.Extension;
//import humc.lab.ext.core.invoker.InvokerFacade;
//import kotlin.jvm.functions.Function1;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.List;
//
///**
// * @author: humingchuan
// * @date: 2023-11-17 22:39
// * @description
// */
//public abstract class ExtensionProxyJava<E extends Extension<E>> implements Extension<E> {
//
//    @Nullable
//    @Override
//    public <T> T first(@NotNull Function1<? super E, ? extends T> callable) {
//        return InvokerFacade.Companion.first((Function1<E, T>) callable::invoke, getCode());
//    }
//
//    @Nullable
//    @Override
//    public <T> T firstNonNull(Function1<? super E, ? extends T> callable) {
//        return InvokerFacade.Companion.firstNonNull((Function1<E, T>) callable::invoke, getCode());
//    }
//
//    @Nullable
//    @Override
//    public <T> T all(Function1<? super E, ? extends T> callable) {
//        return InvokerFacade.Companion.all((Function1<E, T>) callable::invoke, getCode());
//    }
//
//    @Nullable
//    @Override
//    public <T> T allUntil(@NotNull Function1<? super T, Boolean> checker, Function1<? super E, ? extends T> callable) {
//        return InvokerFacade.Companion.allUntil(checker, (Function1<E, T>) callable::invoke, getCode());
//    }
//
//    @NotNull
//    @Override
//    public <T> List<T> allResult(@NotNull Function1<? super E, ? extends T> callable) {
//        return InvokerFacade.Companion.allResult((Function1<E, T>) callable::invoke, getCode());
//
//    }
//}