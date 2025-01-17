package com.pembekalan.xsisacademy.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pembekalan.xsisacademy.dto.request.PublisherRequestDto;
import com.pembekalan.xsisacademy.dto.response.PublisherResponseDto;
import com.pembekalan.xsisacademy.entity.Publisher;
import com.pembekalan.xsisacademy.repository.PublisherRepository;
import com.pembekalan.xsisacademy.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    PublisherRepository publisherRepository;

    private ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public List<PublisherResponseDto> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        List<PublisherResponseDto> publisherResponseDtos = publishers.stream()
                .map(publisher -> modelMapper().map(publisher, PublisherResponseDto.class))
                .collect(Collectors.toList());

        return publisherResponseDtos;
    }

    @Override
    public PublisherResponseDto getPublisherById(Integer id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        PublisherResponseDto publisherResponseDto = modelMapper().map(publisher, PublisherResponseDto.class);
        return publisherResponseDto;
    }

    @Override
    public Publisher savePublisher(PublisherRequestDto publisherRequestDto) {
        Publisher publisher = modelMapper().map(publisherRequestDto, Publisher.class);
        return publisherRepository.save(publisher);
    }

    @Override
    public PublisherResponseDto editPublisher(PublisherRequestDto publisherRequestDto, Integer id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);

        publisher.setName(publisherRequestDto.getName());
        publisher.setAddress(publisherRequestDto.getAddress());

        publisherRepository.save(publisher);

        return getPublisherById(id);
    }

    @Override
    public void deletePublisherById(Integer id) {
        publisherRepository.deleteById(id);
    }
}