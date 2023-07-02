package com.qjx.sample.service;

import com.qjx.sample.metrics.service.ApplicationService;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/21
 * @author <others>
 */
public interface UserService extends ApplicationService {
    String query(Integer id);
}
