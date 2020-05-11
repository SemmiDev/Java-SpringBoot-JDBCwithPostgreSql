package com.belajarJDBC.jdbcTemplate.Entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Invoice_header {
    private Long invoice_id;
    private String invoice_to;
    private String email;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal total;

    public void setInvoice_details(List<Invoice_details> invoice_details) {
        this.invoice_details = invoice_details;
    }

    private List<Invoice_details> invoice_details;

    public Invoice_header(Long invoice_id, String invoice_to, String email, BigDecimal subtotal, BigDecimal discount, BigDecimal total) {
        this.invoice_id = invoice_id;
        this.invoice_to = invoice_to;
        this.email = email;
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
    }
}
