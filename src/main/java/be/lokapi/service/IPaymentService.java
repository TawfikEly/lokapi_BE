package be.lokapi.service;

import be.lokapi.entity.Payment;

import java.time.LocalDate;
import java.util.List;

public interface IPaymentService {
    public Payment createPayment(Payment payment);
    public Payment getPaymentById(Long paymentId);
    public List<Payment> getPayments();
    public List<Payment> getPaymentsByDate(LocalDate date);
    public List<Payment> getPaymentsByOwnerId(Long ownerId);
    public List<Payment> getPaymentsByTenantId(Long tenantId);
    public Payment updatePaymentById(Long paymentId);
    public Payment deletePaymentById(Long paymentId);
    public Payment updatePayment(Payment payment);
    public Payment deletePayment(Payment payment);

}