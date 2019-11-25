package vn.nuce.core.service;

import vn.nuce.core.dto.ProducerDTO;

import java.util.List;

public interface ProducerService {
    List<ProducerDTO> findAllProducer();
}
