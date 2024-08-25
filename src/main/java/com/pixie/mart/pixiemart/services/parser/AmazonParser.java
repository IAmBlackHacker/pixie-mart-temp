package com.pixie.mart.pixiemart.services.parser;

import com.pixie.mart.pixiemart.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class AmazonParser {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.";
    private final Random random = new Random();

    private String parseCycleInfoFromCycleUrl(String cycleUrl) throws IOException {
        Document document = Jsoup.connect(cycleUrl).userAgent(USER_AGENT + random.nextInt())
                .referrer("https://www.amazon.in").get();
        Map<String, Object> overviewInfoMap = new HashMap<>();

        overviewInfoMap.put("name", document.select("#productTitle").text());
        overviewInfoMap.put("price", document.select(
                "span.a-price.aok-align-center.reinventPricePriceToPayMargin.priceToPay > span:nth-child(2) > span.a-price-whole")
                .text());
        overviewInfoMap.put("mrp",
                document.select(
                        "span.a-size-small.a-color-secondary.aok-align-center.basisPrice > span > span.a-offscreen")
                        .text());

        // IMAGES
        List<String> images = new ArrayList<>();
        for (Element element : document.select("span.a-button-text > img")) {
            String imageUrl = element.attr("src");
            if (imageUrl.contains("._SS40_")) {
                images.add(imageUrl.replace("._SS40_", ""));
            }
        }
        overviewInfoMap.put("images", images);

        // FEATURES
        for (Element element : document.select("#productOverview_feature_div > div > table > tbody > tr")) {
            List<String> stringMap = element.select("td").eachText();
            String key = stringMap.get(0).strip().replace(" ", "");
            key = key.substring(0, 1).toLowerCase() + key.substring(1);
            overviewInfoMap.put(key, stringMap.get(1).strip());
        }

        // SPECS BULLETS
        List<String> overviewInfo = new ArrayList<>();
        for (Element element : document.select("#feature-bullets > ul > li")) {
            overviewInfo.add(element.text());
        }
        overviewInfoMap.put("specifications", overviewInfo);

        // TECHNICAL DETAILS
        for (Element element : document.select("#productDetails_techSpec_section_1 > tbody > tr")) {
            String key = element.select("th").text().strip().replace(" ", "");
            key = key.substring(0, 1).toLowerCase() + key.substring(1);
            overviewInfoMap.put(key, element.select("td").text().strip());
        }

        // ADDITIONAL INFO
        for (Element element : document.select("#productDetails_detailBullets_sections1 > tbody > tr")) {
            String key = element.select("th").text().strip().replace(" ", "");
            key = key.substring(0, 1).toLowerCase() + key.substring(1);
            overviewInfoMap.put(key, element.select("td").text().strip());
        }

        return GsonUtil.toJsonString(overviewInfoMap).replace("‎", "");
    }
}
