package com.nohit.jira_project.service.Impl;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.nohit.jira_project.model.*;
import com.nohit.jira_project.repository.*;
import com.nohit.jira_project.service.*;

import lombok.extern.slf4j.*;

@Service
@Slf4j
public class DonHangServiceImpl implements DonHangService {
    @Autowired
    private DonHangRepository donHangRepository;

    @Override
    public Iterable<DonHang> getDsDonHang() {
        log.info("Fetching all don_hang");
        return donHangRepository.findAll();
    }

    @Override
    public DonHang getDonHang(int id) {
        log.info("Fetching don_hang with id {}", id);
        return donHangRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDonHang(DonHang donHang) {
        log.info("Saving don_hang with id: {}", donHang.getId());
        donHangRepository.save(donHang);
    }

    @Override
    public void deleteDonHang(int id) {
        log.info("Deleting don_hang with id: {}", id);
        donHangRepository.deleteById(id);
    }
}
