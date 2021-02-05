package be.fooda.backend.store.service.mapper;


import be.fooda.backend.store.model.create.ScheduleCreate;
import be.fooda.backend.store.model.entity.ScheduleEntity;
import be.fooda.backend.store.model.update.ScheduleUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface ScheduleMapper {

    ScheduleEntity toEntity(ScheduleCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScheduleEntity toEntity(ScheduleUpdate source, @MappingTarget ScheduleEntity target);


    ScheduleCreate toCreate(ScheduleEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScheduleUpdate toUpdate(ScheduleEntity source, @MappingTarget ScheduleUpdate target);


}
