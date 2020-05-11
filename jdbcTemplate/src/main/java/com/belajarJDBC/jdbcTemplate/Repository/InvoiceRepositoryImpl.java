package com.belajarJDBC.jdbcTemplate.Repository;

import com.belajarJDBC.jdbcTemplate.Entity.Invoice_details;
import com.belajarJDBC.jdbcTemplate.Entity.Invoice_header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("invoiceRespository")
public class InvoiceRepositoryImpl implements InvoiceRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("InvoiceDetailRespository")
    private InvoiceDetailRepoImpl invoiceDetailRepo;

    @Override
    public Invoice_header getInvoice(int id) {
        Invoice_header ih = jdbcTemplate.queryForObject(
                "select * from public.invoice_header where invoice_id =" + id,
                (rs,rowNum) -> new Invoice_header(
                        rs.getLong("invoice_id"),
                        rs.getString("invoice_to"),
                        rs.getString("email"),
                        rs.getBigDecimal("subtotal"),
                        rs.getBigDecimal("discount"),
                        rs.getBigDecimal("total")
                )
        );

        ih.setInvoice_details(invoiceDetailRepo.getInvoiceDetails(id));
        return ih;
    }

    @Override
    @Transactional
    public int save(Invoice_header invoice_header) {
        String sql = "INSERT INTO public.invoice_header (invoice_to, email, subtotal, discount, total) VALUES ( ?, ?, ?, ?, ?)";
        String sql_invoice_details = "INSERT INTO public.invoice_details (invoice_id,product_name, qty, total_price) VALUES ( ?, ?, ?, ?)";

        KeyHolder kh = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"invoice_id"});
            ps.setString(1, invoice_header.getInvoice_to());
            ps.setString(2, invoice_header.getEmail());
            ps.setBigDecimal(3, invoice_header.getSubtotal());
            ps.setBigDecimal(4, invoice_header.getDiscount());
            ps.setBigDecimal(5, invoice_header.getTotal());
            return ps;
        }, kh);


        List<Invoice_details> id = invoice_header.getInvoice_details();
        id.forEach(invoice_detail ->{
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql_invoice_details, new String[]{"detail_id"});
                ps.setInt(1, (Integer) kh.getKey());
                ps.setString(2, invoice_detail.getProduct_name());
                ps.setLong(3, invoice_detail.getQty());
                ps.setBigDecimal(4, invoice_detail.getTotal_price());
                return ps;
            });
        });

        return result;
    }
}
