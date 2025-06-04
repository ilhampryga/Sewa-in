package adapter;

public class BarangPesan {
    private String seller;
    private String description;
    private String price;
    private String totalPrice;
    private int imageResId;

    public BarangPesan(String seller, String description, String price, String totalPrice, int imageResId) {
        this.seller = seller;
        this.description = description;
        this.price = price;
        this.totalPrice = totalPrice;
        this.imageResId = imageResId;
    }

    public String getSeller() { return seller; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public String getTotalPrice() { return totalPrice; }
    public int getImageResId() { return imageResId; }
}
