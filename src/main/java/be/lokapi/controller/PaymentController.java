package be.lokapi.controller;

import be.lokapi.api.PaymentsApi;
import be.lokapi.model.PaymentDTO;
import be.lokapi.service.IPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments/")
public class PaymentController implements PaymentsApi {

    private IPaymentService paymentService;
    @Override
    public ResponseEntity<PaymentDTO> createPayment(PaymentDTO paymentDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePayment(Long paymentId) {
        return null;
    }

    @Override
    public ResponseEntity<PaymentDTO> getPaymentById(Long paymentId) {
        return null;
    }

    @Override
    public ResponseEntity<List<PaymentDTO>> getPayments() {
        return null;
    }

    @Override
    public ResponseEntity<List<PaymentDTO>> getPaymentsByDate(LocalDate date) {
        return null;
    }

    @Override
    public ResponseEntity<List<PaymentDTO>> getPaymentsByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<PaymentDTO>> getPaymentsByTenantId(Long tenantId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updatePayment(Long paymentId) {
        return null;
    }
}
