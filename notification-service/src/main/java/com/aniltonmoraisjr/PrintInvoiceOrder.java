package com.aniltonmoraisjr;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PrintInvoiceOrder implements NotifyStrategy<OrderEvent>{

  @Override
  public void notify(OrderEvent object) {
    String notification = """
      <xml>
        <valor>%s</valor>
        <cliente>
          <nome>%s</nome>
          <cpf>%s</cpf>
          <telefone>%s</telefone>
          <endereco>%s</endereco>
        </cliente>
      </xml>
    """.formatted(object.orderValue(), object.name(), object.cpf(), object.phone(), object.address());
    System.out.println(notification);
  }

  @Override
  public void notify(OrderEvent object, String from, String to) {

  }
}
