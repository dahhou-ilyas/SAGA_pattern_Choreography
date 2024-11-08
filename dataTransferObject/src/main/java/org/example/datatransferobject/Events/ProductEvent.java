package org.example.datatransferobject.Events;

public class ProductEvent {
    private Long orderId;
    private Long prodId;
    private int prodqnt;
    private ProductStockState stockAvailability;

    public ProductEvent(Long orderId, Long prodId, int prodqnt, ProductStockState stockAvailability) {
        this.orderId = orderId;
        this.prodId = prodId;
        this.prodqnt = prodqnt;
        this.stockAvailability = stockAvailability;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public int getProdqnt() {
        return prodqnt;
    }

    public void setProdqnt(int prodqnt) {
        this.prodqnt = prodqnt;
    }

    public ProductStockState getStockAvailability() {
        return stockAvailability;
    }

    public void setStockAvailability(ProductStockState stockAvailability) {
        this.stockAvailability = stockAvailability;
    }
}
