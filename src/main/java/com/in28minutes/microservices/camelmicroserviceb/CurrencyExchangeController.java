package com.in28minutes.microservices.camelmicroserviceb;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Shahabuddin Khan
 */
@RestController
public class CurrencyExchangeController {

    private Logger LOG = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange findConversionValue(
            @PathVariable String from, @PathVariable String to) {
        LOG.info("Request received: from="+from +", to:"+to);
        CurrencyExchange result = new CurrencyExchange(10000L, from, to, BigDecimal.TEN);
        LOG.info("Returning result:"+result);
        return result;
    }

}
