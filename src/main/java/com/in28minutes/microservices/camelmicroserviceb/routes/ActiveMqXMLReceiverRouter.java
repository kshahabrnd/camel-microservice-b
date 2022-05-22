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
public class ActiveMqXMLReceiverRouter extends RouteBuilder {

    private final MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;

    public ActiveMqXMLReceiverRouter() {
        this.myCurrencyExchangeProcessor = new MyCurrencyExchangeProcessor();
    }

    @Override
    public void configure() throws Exception {
        /*
        <?xml version="1.0" encoding="UTF-8"?>
<root>
   <id>1000</id>
   <from>USD</from>
   <to>INR</to>
   <conversionMultiple>70</conversionMultiple>
</root>
        */
        from("activemq:xml-msg-queue")
                .log("${body}")
                .unmarshal()
                .jacksonXml(CurrencyExchange.class)
                .log("${body}")
                .bean(myCurrencyExchangeProcessor)
                .to("log:received-message-from-xml-msg-queue");

    }

    class MyCurrencyExchangeProcessor {

        private Logger LOG = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class.getName());

        public void process(CurrencyExchange currencyExchange) throws Exception {
            LOG.info("currencyExchange from XML: " + currencyExchange);
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
