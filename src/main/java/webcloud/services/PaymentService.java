package webcloud.services;

import webcloud.entity.TransactionalDetails;

public interface PaymentService {
    Long doPayment(TransactionalDetails payment);

    TransactionalDetails getPaymentDetailsByOrderId(Long orderId);
}
