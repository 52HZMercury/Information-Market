package com.lostandfound.service.goodsworkers;


import com.jd.platform.async.executor.timer.SystemClock;
import com.lostandfound.mapper.GoodsMapper;
import com.lostandfound.pojo.User;
import com.jd.platform.async.callback.ICallback;
import com.jd.platform.async.callback.IWorker;
import com.jd.platform.async.worker.WorkResult;
import com.jd.platform.async.wrapper.WorkerWrapper;
import com.lostandfound.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 */
@Service
public class goodsWorker3 implements IWorker<String, String>, ICallback<String, String> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String action(String id, Map<String, WorkerWrapper> allWrappers) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String ticket = userMapper.loadticketByUserid(id);
        if (ticket == null /*|| !user.getPassword().equals(password)*/){
            return null;
        }

        return ticket;
    }


    @Override
    public String defaultValue() {
        return new String("default String");
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }


    @Override
    public void result(boolean success, String param, WorkResult<String> workResult) {
        if (success) {
            System.out.println("callback worker1 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker1 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}
