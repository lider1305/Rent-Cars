package by.pvt.service.impl;

import by.pvt.pojo.StatusOfOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
public class StatusOfOrderService extends BaseService<StatusOfOrder> {
}
