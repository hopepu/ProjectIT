package com.project.it.service;

import com.project.it.domain.InfoComputer;
import com.project.it.dto.InfoComputerDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;
import com.project.it.repository.InfoComputerRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class InfoComputerServiceImpl implements InfoComputerService{
    private final InfoComputerRepository repoComInfo;
    private final ModelMapper modelMapper;

    @Override
    public Long register(InfoComputerDTO infoComputerDTO) {
        InfoComputer entity = InfoComputer.builder()
                .productName(infoComputerDTO.getProductName())
                .maker(infoComputerDTO.getMaker())
                .type(infoComputerDTO.getType())
                .cpu(infoComputerDTO.getCpu())
                .gpu(infoComputerDTO.getGpu())
                .ram(infoComputerDTO.getRam())
                .osType(infoComputerDTO.getOsType())
                .capacity(infoComputerDTO.getCapacity())
                .price(infoComputerDTO.getPrice())
                .build();
        repoComInfo.save(entity);
        return entity.getCino();
    }

    @Override
    public InfoComputerDTO getOne(Long cino) {
        Optional<InfoComputer> result = repoComInfo.findById(cino);
        InfoComputer infoComputer = result.orElseThrow(EntityExistsException::new);
        InfoComputerDTO dto = modelMapper.map(infoComputer, InfoComputerDTO.class);
        return dto;
    }

    @Override
    public PageResponseDTO<InfoComputerDTO> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() -1, pageRequestDTO.getSize(), Sort.by("cino").descending());
        Page<InfoComputer> result = repoComInfo.searchAll(pageable);
        List<InfoComputerDTO> dtoList = result.get().map(entity->{
            InfoComputerDTO dto = modelMapper.map(entity, InfoComputerDTO.class);
            return dto;
        }).collect(Collectors.toList());
        long totalCount = result.getTotalElements();
        return PageResponseDTO.<InfoComputerDTO>withAll().dtoList(dtoList).pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
    }


    @Override
    public void remove(Long cino) {
        repoComInfo.deleteById(cino);
    }
}
