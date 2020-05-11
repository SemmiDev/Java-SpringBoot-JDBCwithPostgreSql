package com.belajarJDBC.jdbcTemplate.Controller;

import com.belajarJDBC.jdbcTemplate.Entity.Invoice_header;
import com.belajarJDBC.jdbcTemplate.Repository.InvoiceRepositoryImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InvoiceController {
    @Autowired
    @Qualifier("invoiceRespository")
    private InvoiceRepositoryImpl invoiceRepository;

    @GetMapping("/invoice")
    public Invoice_header getInvoiceById(@RequestParam("id") int id){
        return invoiceRepository.getInvoice(id);
    }

    @PostMapping("/invoice")
    public int saveInvoice(@Valid @RequestBody Invoice_header invoice_header){
        return invoiceRepository.save(invoice_header);
    }

}
