package com.belajarJDBC.jdbcTemplate.Repository;

import com.belajarJDBC.jdbcTemplate.Entity.Invoice_header;

public interface InvoiceRepository {
    Invoice_header getInvoice(int id);
    int save(Invoice_header invoice_header);
}
