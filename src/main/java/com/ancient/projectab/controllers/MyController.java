package com.ancient.projectab.controllers;

import com.ancient.projectab.feign.FeignCurrency;
import com.ancient.projectab.feign.FeignGif;
import com.ancient.projectab.pojos.CurrencyPojo;
import com.ancient.projectab.pojos.GifPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class MyController {

    private final FeignCurrency feignCurrency;
    private final FeignGif feignGif;

    public MyController(FeignCurrency feignCurrency, FeignGif feignGif) {
        this.feignCurrency = feignCurrency;
        this.feignGif = feignGif;
    }


    @GetMapping("/")
    public String index(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String time = yesterday.format(formatter);

        GifPojo gifPojo = null;
        CurrencyPojo currencyPojo = null;
        CurrencyPojo currencyPojoYesterday = null;

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = feignCurrency.getCurrencyToday();
        String jsonResponseYesterday = feignCurrency.getCurrencyYesterday(time);
        try {
            currencyPojo = mapper.readValue(jsonResponse, CurrencyPojo.class);
            currencyPojoYesterday = mapper.readValue(jsonResponseYesterday, CurrencyPojo.class);

            model.addAttribute("rubToday", Double.toString(currencyPojo.getRates().getRub()));
            model.addAttribute("rubYesterday", Double.toString(currencyPojoYesterday.getRates().getRub()));

            model.addAttribute("inrToday", Double.toString(currencyPojo.getRates().getInr()));
            model.addAttribute("inrYesterday", Double.toString(currencyPojoYesterday.getRates().getInr()));

            model.addAttribute("eurToday", Double.toString(currencyPojo.getRates().getEur()));
            model.addAttribute("eurYesterday", Double.toString(currencyPojoYesterday.getRates().getEur()));

        } catch (Exception e) {
            e.printStackTrace();
        }



//        return "redirect:" + gifPojo.getData().getFinUrl();
        return "index-view";

    }

    @PostMapping("/")
    public String test1(Model model, @RequestParam(name = "currency") String cur) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String time = yesterday.format(formatter);

        GifPojo gifPojo = null;
        CurrencyPojo currencyPojo = null;
        CurrencyPojo currencyPojoYesterday = null;

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = feignCurrency.getCurrencyToday();
        String jsonResponseYesterday = feignCurrency.getCurrencyYesterday(time);
        try {
            currencyPojo = mapper.readValue(jsonResponse, CurrencyPojo.class);
            currencyPojoYesterday = mapper.readValue(jsonResponseYesterday, CurrencyPojo.class);


            if (cur.equalsIgnoreCase("RUB")) {
                model.addAttribute("curToday", Double.toString(currencyPojo.getRates().getRub()));
                model.addAttribute("curYesterday", Double.toString(currencyPojoYesterday.getRates().getRub()));

                if (currencyPojo.getRates().getRub() >= currencyPojoYesterday.getRates().getRub()) {
                    gifPojo = mapper.readValue(feignGif.getRich(), GifPojo.class);
                } else {
                    gifPojo = mapper.readValue(feignGif.getBroke(), GifPojo.class);
                }
            }

            if (cur.equalsIgnoreCase("INR")) {
                model.addAttribute("curToday", Double.toString(currencyPojo.getRates().getInr()));
                model.addAttribute("curYesterday", Double.toString(currencyPojoYesterday.getRates().getInr()));

                if (currencyPojo.getRates().getInr() >= currencyPojoYesterday.getRates().getInr()) {
                    gifPojo = mapper.readValue(feignGif.getRich(), GifPojo.class);
                } else {
                    gifPojo = mapper.readValue(feignGif.getBroke(), GifPojo.class);
                }
            }

            if (cur.equalsIgnoreCase("EUR")) {
                model.addAttribute("curToday", Double.toString(currencyPojo.getRates().getEur()));
                model.addAttribute("curYesterday", Double.toString(currencyPojoYesterday.getRates().getEur()));

                if (currencyPojo.getRates().getEur() >= currencyPojoYesterday.getRates().getEur()) {
                    gifPojo = mapper.readValue(feignGif.getRich(), GifPojo.class);
                } else {
                    gifPojo = mapper.readValue(feignGif.getBroke(), GifPojo.class);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Currency today: " + currencyPojo);
        System.out.println("Currency yesterday: " + currencyPojoYesterday);
        System.out.println(gifPojo.getData().getFinUrl());


        return "redirect:" + gifPojo.getData().getFinUrl();
//        return "index-view";
    }


}
