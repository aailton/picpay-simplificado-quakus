package tech.ailtonalves.picpay.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferResponse {

    private UUID id;
    private Long payerId;
    private Long payeeId;
    private String authorizationStatus;
    private BigDecimal amount;
    private String timestamp;

    public TransferResponse(UUID id, Long payerId, Long payeeId, String authorizationStatus, BigDecimal bigDecimal, String timestamp) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.authorizationStatus = authorizationStatus;
        this.amount = bigDecimal;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public String getAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(String authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
