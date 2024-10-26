package com.example.ereserve.Controllers;

import com.example.ereserve.Client.StripeClient;
import com.example.ereserve.DTO.CustomerDTO;
import com.example.ereserve.DTO.CustomerResponseDTO;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private StripeClient stripeClient;

    @PostMapping("/create")
    public CustomerResponseDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            // Use the DTO to create a Stripe customer
            Customer customer = stripeClient.createCustomer(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPhone());

            // Create and return a response DTO
            CustomerResponseDTO responseDTO = new CustomerResponseDTO();
            responseDTO.setId(customer.getId());
            responseDTO.setName(customer.getName());
            responseDTO.setEmail(customer.getEmail());
            responseDTO.setPhone(customer.getPhone());

            return responseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
