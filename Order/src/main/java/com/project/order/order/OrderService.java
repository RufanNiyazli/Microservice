package com.project.order.order;

import com.project.order.customer.CustomerClient;
import com.project.order.exception.BusinessException;
import com.project.order.kafka.OrderConfirmation;
import com.project.order.kafka.OrderProducer;
import com.project.order.orderLine.OrderLineRequest;
import com.project.order.orderLine.OrderLineService;
import com.project.order.payment.PaymentClient;
import com.project.order.payment.PaymentRequest;
import com.project.order.product.ProductClient;
import com.project.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;
    private final OrderProducer orderProducer;

    //    roadmap for the orderservice
//    1 check customer
    public Long createOrder(OrderRequest request) {
        var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(() -> new BusinessException("Cannot create order:: NoCustomerExist with this id:"));
        var purchasedProducts = productClient.purchaseProducts(request.products());
//    3 continiue order
        var order = orderRepository.save(mapper.toOrder(request));
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity())
            );
        }
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);


        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
        ));
        // todo start payment process

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream().map(mapper::fromOrder)
                .collect(Collectors.toList());

    }
    public OrderResponse findById(Long id){
        return orderRepository.findById(id).map(mapper::fromOrder).orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }

//    2 purchase the product --,product-ms
//    4 continue orderline
//    5 start payment process
}
