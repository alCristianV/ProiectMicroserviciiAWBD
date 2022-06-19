package com.example.examen.service;

import com.example.examen.exception.PaymentCancelledException;
import com.example.examen.exception.PaymentNotExistException;
import com.example.examen.model.CurrencyValues;
import com.example.examen.model.Payment;
import com.example.examen.model.Status;
import com.example.examen.model.Type;
import com.example.examen.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService service;
    @Mock
    private PaymentRepository repository;

    private static Optional<Payment> payment;

    @BeforeAll
    public static void setup() {
        payment = Optional.of(new Payment(1L, Type.ONLINE, "Cristi", 10.0, Status.NEW, CurrencyValues.EURO));
    }

    @Test
    @DisplayName("Add new Payment Success Test")
    public void testAddSuccess() {
        // arrange
        when(repository.save(payment.get())).thenReturn(payment.get());


        // act
        Payment actualPayment = service.add(payment.get());

        // assert
        assertEquals(payment.get(), actualPayment);
        assertEquals(payment.get().getId(), actualPayment.getId());
        assertEquals(payment.get().getStatus(), actualPayment.getStatus());
        assertEquals(payment.get().getAmount(), actualPayment.getAmount());
        assertEquals(payment.get().getCustomer(), actualPayment.getCustomer());
        assertEquals(payment.get().getType(), actualPayment.getType());
    }

    @Test
    @DisplayName("Test Get All Payments By Status And Type Success")
    public void testGetAllPaymentsByStatusAndType() {
        // arrange
        when(repository.findAllByStatusAndType(payment.get().getStatus(), payment.get().getType())).thenReturn(List.of(payment.get()));

        // act
        List<Payment> payments = service.getAll(payment.get().getStatus(), payment.get().getType());

        // assert
        assertEquals(List.of(payment.get()), payments);
        assertEquals(1, payments.size());
        assertEquals(payment.get(), payments.get(0));
        assertEquals(payment.get().getId(), payments.get(0).getId());
        assertEquals(payment.get().getStatus(), payments.get(0).getStatus());
        assertEquals(payment.get().getAmount(), payments.get(0).getAmount());
        assertEquals(payment.get().getCustomer(), payments.get(0).getCustomer());
        assertEquals(payment.get().getType(), payments.get(0).getType());
    }

    @Test
    @DisplayName("Test Get All Payments By Status Success")
    public void testGetAllPaymentsByStatus() {
        // arrange
        when(repository.findAllByStatus(payment.get().getStatus())).thenReturn(List.of(payment.get()));

        // act
        List<Payment> payments = service.getAll(payment.get().getStatus(), null);

        // assert
        assertEquals(List.of(payment.get()), payments);
        assertEquals(1, payments.size());
        assertEquals(payment.get(), payments.get(0));
        assertEquals(payment.get().getId(), payments.get(0).getId());
        assertEquals(payment.get().getStatus(), payments.get(0).getStatus());
        assertEquals(payment.get().getAmount(), payments.get(0).getAmount());
        assertEquals(payment.get().getCustomer(), payments.get(0).getCustomer());
        assertEquals(payment.get().getType(), payments.get(0).getType());
    }

    @Test
    @DisplayName("Test Get All Payments By Type Success")
    public void testGetAllPaymentsByType() {
        // arrange
        when(repository.findAllByType(payment.get().getType())).thenReturn(List.of(payment.get()));

        // act
        List<Payment> payments = service.getAll(null, payment.get().getType());

        // assert
        assertEquals(List.of(payment.get()), payments);
        assertEquals(1, payments.size());
        assertEquals(payment.get(), payments.get(0));
        assertEquals(payment.get().getId(), payments.get(0).getId());
        assertEquals(payment.get().getStatus(), payments.get(0).getStatus());
        assertEquals(payment.get().getAmount(), payments.get(0).getAmount());
        assertEquals(payment.get().getCustomer(), payments.get(0).getCustomer());
        assertEquals(payment.get().getType(), payments.get(0).getType());
    }

    @Test
    @DisplayName("Test Get All Payments")
    public void testGetAllPayments() {
        // arrange
        when(repository.findAll()).thenReturn(List.of(payment.get()));

        // act
        List<Payment> payments = service.getAll(null, null);

        // assert
        assertEquals(List.of(payment.get()), payments);
        assertEquals(1, payments.size());
        assertEquals(payment.get(), payments.get(0));
        assertEquals(payment.get().getId(), payments.get(0).getId());
        assertEquals(payment.get().getStatus(), payments.get(0).getStatus());
        assertEquals(payment.get().getAmount(), payments.get(0).getAmount());
        assertEquals(payment.get().getCustomer(), payments.get(0).getCustomer());
        assertEquals(payment.get().getType(), payments.get(0).getType());
    }

    @Test
    @DisplayName("Test Cancel Payment PaymentNotExistException")
    public void testCancelPaymentNotExist() {
        // arrange
        when(repository.findById(payment.get().getId())).thenReturn(Optional.empty());

        // act & assert
        assertThrows(PaymentNotExistException.class,
                () -> service.cancel(payment.get().getId()));
    }

    @Test
    @DisplayName("Test Cancel Payment PaymentCancelledException")
    public void testCancelPaymentAlreadyCancelled() {
        // arrange
        Payment cancelledPayment = new Payment(2L, Type.ONLINE, "Cristi", 10.0, Status.CANCELLED, CurrencyValues.RON);
        when(repository.findById(cancelledPayment.getId())).thenReturn(Optional.of(cancelledPayment));

        // act & assert
        assertThrows(PaymentCancelledException.class,
                () -> service.cancel(cancelledPayment.getId()));
    }

    @Test
    @DisplayName("Test Cancel Payment Success")
    public void testCancelPaymentSuccess() {
        // arrange
        when(repository.findById(payment.get().getId())).thenReturn(payment);

        // act
        Payment actualPayment = service.cancel(payment.get().getId());

        // assert
        assertEquals(payment.get(), actualPayment);
        assertEquals(payment.get().getId(), actualPayment.getId());
        assertEquals(Status.CANCELLED, actualPayment.getStatus());
        assertEquals(payment.get().getAmount(), actualPayment.getAmount());
        assertEquals(payment.get().getCustomer(), actualPayment.getCustomer());
        assertEquals(payment.get().getType(), actualPayment.getType());

    }
}
