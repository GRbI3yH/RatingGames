package ru.game.rate.main.service.dtoConverter;

import ru.game.rate.main.service.domain.SystemRequirements;
import ru.game.rate.main.service.dto.SystemRequirementsDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SystemRequirementsConverter {

    public SystemRequirements toEntity(SystemRequirementsDto dto){
        SystemRequirements systemRequirements = new SystemRequirements();
        systemRequirements.setCpu(dto.cpu);
        systemRequirements.setDiskSpace(dto.diskSpace);
        systemRequirements.setRam(dto.ram);
        systemRequirements.setType(dto.type);
        systemRequirements.setVideoCard(dto.videoCard);
        return systemRequirements;
    }

    public SystemRequirementsDto toDto(SystemRequirements entity){
        SystemRequirementsDto systemRequirementsDto = new SystemRequirementsDto();
        systemRequirementsDto.cpu = entity.getCpu();
        systemRequirementsDto.diskSpace = entity.getDiskSpace();
        systemRequirementsDto.ram = entity.getRam();
        systemRequirementsDto.type = entity.getType();
        systemRequirementsDto.videoCard = entity.getVideoCard();
        return systemRequirementsDto;
    }

    public List<SystemRequirementsDto> toListDto(List<SystemRequirements> entity){
        return entity.stream().map(this::toDto).collect(toList());
    }

    public List<SystemRequirements> toListEntity(List<SystemRequirementsDto> dto){
        return dto.stream().map(this::toEntity).collect(toList());
    }
}
