package com.example.examen.repository;

import com.example.examen.model.Payment;
import com.example.examen.model.Status;
import com.example.examen.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository  extends CrudRepository<Payment, Long> {

    List<Payment> findAllByStatusAndType(Status status, Type type);

    List<Payment> findAllByStatus(Status status);

    List<Payment> findAllByType(Type type);

}
