package org.edu.miu.cs590de.productservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-service", url = "http://localhost:8900")
public interface SockServiceClient {
    int getStockQuantity(@RequestParam("productNumber") String productNumber);
}
