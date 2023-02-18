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
public class goodsWorker1 implements IWorker<String, User>, ICallback<String, User> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User action(String id, Map<String, WorkerWrapper> allWrappers) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        User User = userMapper.loadUserByUserid(id);
        if (User == null /*|| !user.getPassword().equals(password)*/){
            return null;
        }

        return User;
    }


    @Override
    public User defaultValue() {
        return new User("default User");
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }


    @Override
    public void result(boolean success, String param, WorkResult<User> workResult) {
        if (success) {
            System.out.println("callback worker1 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker1 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}
