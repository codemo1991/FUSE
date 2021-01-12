package com.xiaoniu.fuse.core.filter.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.xiaoniu.fuse.core.constant.GlobalConstant;
import com.xiaoniu.fuse.core.command.dubbo.FuseDubboCommand;
import com.xiaoniu.fuse.core.config.FuseHystrixConfig;
import com.xiaoniu.fuse.core.context.FuseContext;

/**
 * Created by Chris on 2017/9/24.
 */
@Activate(group = Constants.CONSUMER)
public class FuseDubboFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String fullMethodName = invocation.getInvoker().getInterface().getName() + GlobalConstant.CLASS_PATH_DECOLLATOR + invocation.getMethodName();
        FuseHystrixConfig config = FuseContext.methodNameMapConfig.get(fullMethodName);
        if (config == null)
            return invoker.invoke(invocation);
        FuseDubboCommand command = new FuseDubboCommand(invoker, invocation, config);
        return command.execute();
    }
}
