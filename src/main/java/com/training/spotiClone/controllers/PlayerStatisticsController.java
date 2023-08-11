package com.training.spotiClone.controllers;

import com.training.spotiClone.dao.User;
import com.training.spotiClone.dao.UserType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

@RestController
public class PlayerStatisticsController extends BaseController {
    private static final double AD_PROBABILITY = 0.1;
    private static final BigDecimal AD_PRICE = BigDecimal.valueOf(0.01);
    private static final BigDecimal PRICE_PER_SUBSCRIPTION = BigDecimal.valueOf(5.0);
    private static BigDecimal adProfit = BigDecimal.ZERO;

    public void playAd(User user) {
        if(UserType.PREMIUM.equals(user.getType())) {
            return;
        }

        boolean playAdd = new Random().nextDouble() <= AD_PROBABILITY;
        if(playAdd) {
            adProfit = adProfit.add(AD_PRICE);
        }
    }

    public BigDecimal getTotalSubscriptionProfit() {
        int numberOfSubscribers = getUserRepository().getPremiumUsers().size();
        return PRICE_PER_SUBSCRIPTION.multiply(BigDecimal.valueOf(numberOfSubscribers));
    }

    @GetMapping("/profit/total")
    public BigDecimal totalProfit() {
        return getTotalSubscriptionProfit().add(adProfit);
    }
}
