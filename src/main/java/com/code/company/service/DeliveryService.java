package com.code.company.service;

import com.code.company.JPA.DeliveryRepository;
import com.code.company.entity.DeliveryNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public DeliveryNote findById(Long id) throws Exception {
        return deliveryRepository.findById(id).orElseThrow(()-> new Exception("Delivery note not found!"));
    }

    public void add(DeliveryNote deliveryNote) {
        deliveryRepository.save(deliveryNote);
    }

    @Transactional
    public void update(Long id, DeliveryNote updated) throws Exception{
        deliveryRepository.findById(id).map(deliveryNote -> {
            deliveryNote.setDate(updated.getDate());
            deliveryNote.setStaff(updated.getStaff());
            deliveryNote.setPackageDetails(updated.getPackageDetails());
            return deliveryRepository.save(deliveryNote);
        }).orElseThrow(() -> new Exception("Order id not found!"));
    }

    public Page<DeliveryNote> findAll(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

    public void delete(Long id) {
        deliveryRepository.deleteById(id);
    }
}
