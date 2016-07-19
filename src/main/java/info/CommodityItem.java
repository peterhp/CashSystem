package info;

/**
 * Created by Sora on 2016/7/19.
 */
public class CommodityItem {

    private Commodity commodity;
    private int quantity = 0;

    public CommodityItem(Commodity commodity) {
        this.commodity = commodity;
    }

    public CommodityItem(Commodity commodity, int quantity) {
        this.commodity = commodity;
        this.quantity = quantity;
    }

    public void add(int quantity) {
        this.quantity += quantity;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public int getQuantity() {
        return quantity;
    }
}
