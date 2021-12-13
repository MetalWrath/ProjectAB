package com.ancient.projectab.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "gifClient",
        url = "https://api.giphy.com/v1/gifs/")
public interface FeignGif {

    @GetMapping("random?api_key=g9SdMTGB1FVBQbChMRjX7h9z9AIn95hq&tag=rich")
    public String getRich();

    @GetMapping("random?api_key=g9SdMTGB1FVBQbChMRjX7h9z9AIn95hq&tag=broke")
    public String getBroke();


}
