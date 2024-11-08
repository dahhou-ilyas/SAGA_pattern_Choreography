package org.example.datatransferobject.Events;

public class OrderEvent {
    private Long orderId;
    private Long prodId;
    private int prodqnt;

    public OrderEvent(Long orderId, Long prodId, int prodqnt) {
        this.orderId = orderId;
        this.prodId = prodId;
        this.prodqnt = prodqnt;
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
}
