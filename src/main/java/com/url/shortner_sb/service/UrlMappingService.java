package com.url.shortner_sb.service;

import com.url.shortner_sb.dto.ClickEventDTO;
import com.url.shortner_sb.dto.UrlMappingDTO;
import com.url.shortner_sb.model.ClickEvent;
import com.url.shortner_sb.model.UrlMapping;
import com.url.shortner_sb.model.User;
import com.url.shortner_sb.repository.ClickEventRepository;
import com.url.shortner_sb.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UrlMappingService {
    private UrlMappingRepository urlMappingRepository;
    private ClickEventRepository clickEventRepository;
    public UrlMappingService(UrlMappingRepository urlMappingRepository,ClickEventRepository clickEventRepository){
        this.urlMappingRepository=urlMappingRepository;
        this.clickEventRepository =clickEventRepository;
    }

    public UrlMappingDTO createShortUrl(String originalUrl, User user) {
        String shortUrl=generateShortUrl();
        UrlMapping urlMapping=new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping savedUrlMapping=urlMappingRepository.save(urlMapping);
        return converToDto(savedUrlMapping);
    }
    private UrlMappingDTO converToDto(UrlMapping urlMapping){
        UrlMappingDTO urlMappingDTO=new UrlMappingDTO();
        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
        urlMappingDTO.setClickCount(urlMapping.getClickCount());
        urlMappingDTO.setCreatedDate(urlMapping.getCreatedDate());
        urlMappingDTO.setUsername(urlMapping.getUser().getUsername());
        return urlMappingDTO;
    }
    private String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random=new Random();
        StringBuilder shortUrl=new StringBuilder(8);
        for(int i=0;i<8;i++){
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }

    public List<UrlMappingDTO> getUrlsByUser(User user) {
        return urlMappingRepository.findByUser(user).stream()
                .map(this::converToDto)
                .toList();
    }

    public List<ClickEventDTO> getClickEventByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping=urlMappingRepository.findByShortUrl(shortUrl);
        if(urlMapping != null){
            return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping,start,end).stream()
                    .collect(Collectors.groupingBy(click->click.getClickDate().toLocalDate(),Collectors.counting()))
                    .entrySet().stream()
                    .map(entry->{
                        ClickEventDTO clickEventDTO=new ClickEventDTO();
                        clickEventDTO.setClickDate(entry.getKey());
                        clickEventDTO.setCount(entry.getValue());
                        return clickEventDTO;
                    })
                    .collect(Collectors.toList());

        }
        return null;
        //meaning
        /*
        here we are transforming the value that we are getting from repositories
        into List that is we want as
        [
         {
         "clickDate" : "24-12-01".
         "count" : 10
         }
         {
         "clickDate" : "25-12-01".
         "count" : 20
         }
        ] this is into list but we are getting into repo fomat so we need to change into list
        we get the data by findbyurlmappingurlandclickdate between start and end date
        we grouping the data by date over here
        after grouping the date we counting the indivisual groups
        once we get the data we are transforming the data by map function into ClickEventDTO type
         then we are changing the data in the list form by collecting tolist

        */
    }

    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end) {
        List<UrlMapping> urlMappings=urlMappingRepository.findByUser(user);
        List<ClickEvent> clickEvents=clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMappings,start.atStartOfDay(),end.plusDays(1).atStartOfDay());

        return clickEvents.stream()
                .collect(Collectors.groupingBy(click->click.getClickDate().toLocalDate(),Collectors.counting()));

    }

    public UrlMapping getOriginalUrl(String shortUrl) {
        UrlMapping urlMapping=urlMappingRepository.findByShortUrl(shortUrl);
        if(urlMapping!=null){
            urlMapping.setClickCount(urlMapping.getClickCount()+1);
            urlMappingRepository.save(urlMapping);

            //Record click events indivisual;
            ClickEvent clickEvent=new ClickEvent();
            clickEvent.setClickDate(LocalDateTime.now());
            clickEvent.setUrlMapping(urlMapping);
            clickEventRepository.save(clickEvent);
        }
        return urlMapping;
    }
}
