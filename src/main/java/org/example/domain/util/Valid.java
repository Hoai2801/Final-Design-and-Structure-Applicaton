package org.example.domain.util;

import org.example.domain.entities.models.RequestModel;

public class Valid {

    public static boolean valid(RequestModel req) {
        return req.getFullName() != null && req.getCustomerType() != null && req.getNationality() != null && req.getCustomerId() > 0 && req.getQuantity() > 0 && req.getQuota() >= 0 && req.getPrice() > 0;
    }
}
