package com.project.order.order;

import com.project.order.customer.CustomerClient;
import com.project.order.exception.BusinessException;
import com.project.order.orderLine.OrderLineRequest;
import com.project.order.orderLine.OrderLineService;
import com.project.order.product.ProductClient;
import com.project.order.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    //    roadmap for the orderservice
//    1 check customer
    public Long createOrder(OrderRequest request) {
        var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(() -> new BusinessException("Cannot create order:: NoCustomerExist with this id:"))
        productClient.purchaseProducts(request.products());
//    3 contiude order
        var order = orderRepository.save(mapper.toOrder(request));
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity())
            );
        }
        // todo start payment process


    }


//    2 purchase the product --,product-ms
//    4 continue orderline
//    5 start payment process
}
