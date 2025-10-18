package com.aniltonmoraisjr;

import java.math.BigDecimal;

public record OrderEvent(String name, String cpf, String phone, String address, BigDecimal orderValue) {

}
