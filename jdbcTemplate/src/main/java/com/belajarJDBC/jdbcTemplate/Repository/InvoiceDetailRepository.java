package com.belajarJDBC.jdbcTemplate.Repository;

import com.belajarJDBC.jdbcTemplate.Entity.Invoice_details;
import com.belajarJDBC.jdbcTemplate.Entity.Invoice_header;

import java.util.List;
import java.util.Optional;

public interface InvoiceDetailRepository {
    List<Invoice_details> getInvoiceDetails(int invoice_id);
}