package com.qjx.sample.service;

import com.qjx.sample.metrics.service.ApplicationService;
import org.springframework.stereotype.Service;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/21
 * @author <others>
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String query(Integer id) {
        return id + "";
    }
}
