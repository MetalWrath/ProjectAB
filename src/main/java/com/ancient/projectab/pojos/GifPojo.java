package com.ancient.projectab.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GifPojo {

    private Data data;

    public GifPojo() {
    }
    @JsonCreator
    public GifPojo(@JsonProperty("data") Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GifPojo{" +
                "data=" + data +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private String finUrl;

        public Data() {
        }
        @JsonCreator
        public Data(@JsonProperty("embed_url") String finUrl) {
            this.finUrl = finUrl;
        }

        public String getFinUrl() {
            return finUrl;
        }

        public void setFinUrl(String finUrl) {
            this.finUrl = finUrl;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "embed_url='" + finUrl + '\'' +
                    '}';
        }
    }
}

