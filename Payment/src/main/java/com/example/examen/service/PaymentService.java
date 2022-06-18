package com.example.examen.service;

import com.example.examen.exception.PaymentCancelledException;
import com.example.examen.exception.PaymentNotExistException;
import com.example.examen.model.Payment;
import com.example.examen.model.Status;
import com.example.examen.model.Type;
import com.example.examen.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public List<Payment> getAll(Status status, Type type) {
        if (status != null) {
            if (type != null) {
                return repository.findAllByStatusAndType(status, type);
            }
            return repository.findAllByStatus(status);
        }
        if (type != null) {
            return repository.findAllByType(type);
        }
        return (List<Payment>) repository.findAll();
    }

    public Payment add(Payment payment) {
        return repository.save(payment);
    }

    public Payment cancel(long id) {
        Optional<Payment> payment = repository.findById(id);
        if(payment.isEmpty()){
            throw new PaymentNotExistException();
        }
        if(payment.get().getStatus().equals(Status.CANCELLED)){
            throw new PaymentCancelledException();
        }

        payment.get().setStatus(Status.CANCELLED);
        repository.save(payment.get());
        return payment.get();
    }

    public Payment findById(Long id) {
        Optional<Payment> paymentOptional = repository.findById(id);
        if (! paymentOptional.isPresent())
            throw new PaymentNotExistException();
        return paymentOptional.get();
    }

}
