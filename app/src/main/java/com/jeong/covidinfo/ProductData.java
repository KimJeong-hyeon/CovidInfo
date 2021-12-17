package com.jeong.covidinfo;

public class ProductData implements Comparable<ProductData> {
    private Integer img;
    private String link;
    private String name;
    private Float rate;

    ProductData(Integer img, String link, String name, Float rate){
        this.img = img;
        this.link = link;
        this.name = name;
        this.rate = rate;
    }

    public Integer getImg() { return img; }
    public String getLink() { return link; }
    public String getName() { return name; }
    public Float getRate() { return rate; }

    public void setImg(Integer img) { this.img = img; }
    public void setLink(String link) { this.link = link; }
    public void setName(String name) { this.name = name; }
    public void setRate(Float rate) { this.rate = rate; }

    @Override
    public int compareTo(ProductData o) {
        if (this.rate > o.rate){
            return -1;   // 두 객체의 자리가 바뀐다
        }else if (this.rate == o.rate){
            return 0;    // 자리 변동x
        }else
        return 1;        // 자리 변동x
    }
}
