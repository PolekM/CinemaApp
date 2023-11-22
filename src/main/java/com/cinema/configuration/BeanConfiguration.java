package com.cinema.configuration;

import com.cinema.dto.movie.MovieWriteDto;
import com.cinema.dto.seat.SeatSaveDto;
import com.cinema.entity.Movie;
import com.cinema.entity.Room;
import com.cinema.entity.Seat;
import com.cinema.entity.Species;
import com.cinema.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    private final RoomRepository roomRepository;

    @Autowired
    public BeanConfiguration(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<SeatSaveDto, Seat> propertyMapper = modelMapper.createTypeMap(SeatSaveDto.class, Seat.class);
        propertyMapper.addMappings(mapping -> mapping.skip(Seat::setSeatId));

        return modelMapper;
    }
}
