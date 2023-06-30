package webcloud.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import webcloud.entity.TransactionalDetails;
import webcloud.exeptions.PaymentServiceException;
import webcloud.repositories.TransDetailRepo;
import webcloud.services.PaymentService;

import java.time.Instant;

@Service
@AllArgsConstructor
public class PaymentServiceImp
        implements PaymentService {

    private final TransDetailRepo paymentRepo;
    @Override
    public Long doPayment(TransactionalDetails payment) {

        var paymentDetails = TransactionalDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(payment.getPaymentMode())
                .paymentStatus("SUCCESS")
                .orderId(payment.getOrderId())
                .referenceNumber(payment.getReferenceNumber())
                .amount(payment.getAmount())
                .build();
        paymentRepo.save(paymentDetails);
        return paymentDetails.getId();
    }

    @Override
    public TransactionalDetails getPaymentDetailsByOrderId(Long orderId) {
        return paymentRepo.findByOrderId(orderId)
                .orElseThrow(PaymentServiceException::new);
    }
}
