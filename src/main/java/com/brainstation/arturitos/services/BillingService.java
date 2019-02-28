package com.brainstation.arturitos.services;

import com.brainstation.arturitos.utils.MyExeption;
import com.stripe.model.Customer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class BillingService {
    Charge charge;

    public boolean makeTransaction(float ammount, String token) throws MyExeption {
        Stripe.apiKey = "sk_test_9yst18BzhQ7a5HvZeoXm3yKL";
        int ammountToFee = (int) (ammount * 100);
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", ammountToFee);
        chargeMap.put("currency", "usd");
        chargeMap.put("customer", token); // obtained via Stripe.js
        try {
            Charge charge = Charge.create(chargeMap);
            return true;
        } catch (StripeException e) {
            throw new MyExeption("Error making transaction");
        }
    }

    public String createUser(String token) throws MyExeption {
        try {
            Stripe.apiKey = "sk_test_9yst18BzhQ7a5HvZeoXm3yKL";
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("source", token);
            Customer customer = null;
            customer = Customer.create(chargeParams);
            return customer.getId();
        } catch (StripeException e) {
            throw new MyExeption("Error Creating card");
        }
    }
}
