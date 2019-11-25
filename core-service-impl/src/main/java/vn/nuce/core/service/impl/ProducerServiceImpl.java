package vn.nuce.core.service.impl;

import vn.nuce.core.dto.ProducerDTO;
import vn.nuce.core.persistence.entity.ProducerEntity;
import vn.nuce.core.service.ProducerService;
import vn.nuce.core.service.utils.SingletonDaoUtil;
import vn.nuce.core.utils.ProducerBeanUtil;

import java.util.ArrayList;
import java.util.List;

public class ProducerServiceImpl implements ProducerService {
    public List<ProducerDTO> findAllProducer() {
        List<ProducerDTO> list = new ArrayList<ProducerDTO>();
        List<ProducerEntity> entities = SingletonDaoUtil.getProducerDaoInstance().findAll();
        for(ProducerEntity item : entities) {
            ProducerDTO dto = ProducerBeanUtil.entityToDto(item);
            list.add(dto);
        }
        return list;
    }
}
