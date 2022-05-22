package com.in28minutes.microservices.camelmicroserviceb.routes;

import com.in28minutes.microservices.camelmicroserviceb.CurrencyExchange;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Shahabuddin Khan
 */
@Component
public class ActiveMqReceiverRouter extends RouteBuilder {

    private final MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;

    public ActiveMqReceiverRouter() {
        this.myCurrencyExchangeProcessor = new MyCurrencyExchangeProcessor();
    }

    @Override
    public void configure() throws Exception {
        //{   "id": 1000,   "from": "USD",   "to": "INR",   "conversionMultiple": 70 }
        from("activemq:my-activemq-queue")
                .log("${body}")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .log("${body}")
                .bean(myCurrencyExchangeProcessor)
                .to("log:received-message-from-active-mq");

    }

    class MyCurrencyExchangeProcessor {

        private Logger LOG = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class.getName());

        public void process(CurrencyExchange currencyExchange) throws Exception {
            LOG.info("currencyExchange: " + currencyExchange);
//            LOG.info("exchange.getExchangeId: "  + exchange.getExchangeId());
//            LOG.info("exchange.getFromRouteId: "  + exchange.getFromRouteId());
//            LOG.info("exchange.getAllProperties: "  + exchange.getAllProperties());
//            LOG.info("exchange.getIn: "  + exchange.getIn());
//            LOG.info("exchange.getContext: "  + exchange.getContext());
//            LOG.info("exchange.getCreated: "  + exchange.getCreated());
//            LOG.info("exchange.getFromRouteId: "  + exchange.getFromEndpoint());
//            LOG.info("exchange.getFromRouteId: "  + exchange.getUnitOfWork());

        }

    }

}
