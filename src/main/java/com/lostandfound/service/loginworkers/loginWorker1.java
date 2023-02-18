package com.lostandfound.service.loginworkers;


import com.jd.platform.async.executor.timer.SystemClock;
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
 * @author wuweifeng wrote on 2019-11-20.
 */
@Service
public class loginWorker1 implements IWorker<String, User>, ICallback<String, User> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User action(String email, Map<String, WorkerWrapper> allWrappers) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        User user = userMapper.loadUserByUseremail(email);
        if (user == null /*|| !user.getPassword().equals(password)*/){
            return null;
        }

        return user;
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
