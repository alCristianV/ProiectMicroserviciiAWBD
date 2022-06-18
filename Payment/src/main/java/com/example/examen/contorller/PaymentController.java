package com.example.examen.contorller;

import com.example.examen.dto.CreatePaymentRequestDto;
import com.example.examen.mapper.PaymentMapper;
import com.example.examen.model.Payment;
import com.example.examen.model.Status;
import com.example.examen.model.Type;
import com.example.examen.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/payments")
@Validated
public class PaymentController {

    @Autowired
    private PaymentService service;

    @Autowired
    private PaymentMapper mapper;

    @PostMapping
    public ResponseEntity<Payment> create(
            @Valid
            @RequestBody CreatePaymentRequestDto request) {
        Payment payment = service.add(mapper.createPaymentRequestDtoToPayment(request));
        Link selfLink = linkTo(methodOn(PaymentController.class).getPayment(payment.getId())).withSelfRel();
        payment.add(selfLink);
        Link deleteLink = linkTo(methodOn(PaymentController.class).cancel(payment.getId())).withRel("cancelPayment");
        payment.add(deleteLink);
        return ResponseEntity.created(URI.create("/payments/" + payment.getId()))
                .body(payment);
    }

    @GetMapping("/{paymentId}")
    public Payment getPayment(@PathVariable Long paymentId) {
        Payment payment = service.findById(paymentId);
        Link selfLink = linkTo(methodOn(PaymentController.class).getPayment(payment.getId())).withSelfRel();
        payment.add(selfLink);
        Link deleteLink = linkTo(methodOn(PaymentController.class).cancel(payment.getId())).withRel("cancelPayment");
        payment.add(deleteLink);
        return payment;

    }

    @GetMapping( produces = {"application/hal+json"})
    public CollectionModel<Payment> get(@RequestParam(required = false) Status status,
                         @RequestParam(required = false) Type type) {
        List<Payment> payments = service.getAll(status, type);
        for (final Payment payment : payments) {
            Link selfLink = linkTo(methodOn(PaymentController.class).getPayment(payment.getId())).withSelfRel();
            payment.add(selfLink);
            Link deleteLink = linkTo(methodOn(PaymentController.class).cancel(payment.getId())).withRel("cancelPayment");
            payment.add(deleteLink);
        }
        Link link = linkTo(methodOn(PaymentController.class).get(status,type)).withSelfRel();
        CollectionModel<Payment> result = CollectionModel.of(payments, link);
        return result;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> cancel(@PathVariable long id) {
        Payment payment = service.cancel(id);
        Link selfLink = linkTo(methodOn(PaymentController.class).getPayment(payment.getId())).withSelfRel();
        payment.add(selfLink);
        Link deleteLink = linkTo(methodOn(PaymentController.class).cancel(payment.getId())).withRel("cancelPayment");
        payment.add(deleteLink);
        return ResponseEntity.ok(payment);
    }
}
