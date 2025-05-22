package gui4me.utils;

public class Link {
    private String url;
    private String text;

    public Link(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUlr() {
        return url;
    }

    public void setUlr(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
