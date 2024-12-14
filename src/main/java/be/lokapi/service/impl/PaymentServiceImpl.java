package be.lokapi.service.impl;

import be.lokapi.entity.Payment;
import be.lokapi.repository.IPaymentRepository;
import be.lokapi.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private IPaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        payment.setCreationDate(LocalDate.now());
        payment.setUpdateDate(LocalDate.now());
        payment.setDeleteDate(null);
        return paymentRepository.save(payment);
    }
    @Override
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByDate(LocalDate date) {
        return null;//paymentRepository.getPaymentsByDate(date);
    }

    @Override
    public List<Payment> getPaymentsByOwnerId(Long ownerId) {
        return null;//paymentRepository.getPaymentsByOwnerId(ownerId);
    }

    @Override
    public List<Payment> getPaymentsByTenantId(Long tenantId) {
        return null;//paymentRepository.getPaymentsByTenantId(tenantId);
    }

    @Override
    public Payment updatePaymentById(Long paymentId) {
        return updatePayment(getPaymentById(paymentId));
    }
    @Override
    public Payment deletePaymentById(Long paymentId) {
        return deletePayment(getPaymentById(paymentId));
    }
    @Override
    public Payment updatePayment(Payment payment) {
        payment.setUpdateDate(LocalDate.now());
        return paymentRepository.save(payment);
    }
    @Override
    public Payment deletePayment(Payment payment) {
        payment.setUpdateDate(LocalDate.now());
        payment.setDeleteDate(LocalDate.now());
        return paymentRepository.save(payment);
    }
}
