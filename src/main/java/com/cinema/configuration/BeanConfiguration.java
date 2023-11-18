package com.cinema.configuration;

import com.cinema.dto.movie.MovieWriteDto;
import com.cinema.entity.Movie;
import com.cinema.entity.Species;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}
}
