package com.lostandfound.service.goodsworkers;


import com.jd.platform.async.executor.timer.SystemClock;
import com.lostandfound.mapper.GoodsMapper;
import com.lostandfound.pojo.Goods;
import com.lostandfound.pojo.User;
import com.jd.platform.async.callback.ICallback;
import com.jd.platform.async.callback.IWorker;
import com.jd.platform.async.worker.WorkResult;
import com.jd.platform.async.wrapper.WorkerWrapper;
import com.lostandfound.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wuweifeng wrote on 2019-11-20.
 */
@Service
public class goodsWorker2 implements IWorker<String, List<Goods>>, ICallback<String, List<Goods>> {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public List<Goods> action(String object, Map<String, WorkerWrapper> allWrappers) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<Goods>();
    }

    @Override
    public List<Goods> defaultValue() {
        return new ArrayList<Goods>();
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }


    @Override
    public void result(boolean success, String param, WorkResult<List<Goods>> workResult) {
        if (success) {
            System.out.println("callback worker1 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker1 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}
