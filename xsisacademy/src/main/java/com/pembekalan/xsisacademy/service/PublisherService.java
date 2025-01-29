package com.pembekalan.xsisacademy.service;

import java.util.List;

import com.pembekalan.xsisacademy.dto.request.PublisherRequestDto;
import com.pembekalan.xsisacademy.dto.response.PublisherResponseDto;
import com.pembekalan.xsisacademy.entity.Publisher;

public interface PublisherService {

    List<PublisherResponseDto> getAllPublishers();

    PublisherResponseDto getPublisherById(Integer id);

    Publisher savePublisher(PublisherRequestDto publisherRequestDto);

    PublisherResponseDto editPublisher(PublisherRequestDto publisherRequestDto, Integer id);

    void deletePublisherById(Integer id);
}
