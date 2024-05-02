package com.sublinks.sublinksapi.api.sublinks.v1.roles.mappers;

import com.sublinks.sublinksapi.api.lemmy.v3.utils.DateUtils;
import com.sublinks.sublinksapi.authorization.entities.Role;
import com.sublinks.sublinksapi.authorization.services.RoleAuthorizingService;
import java.util.Date;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
    RoleAuthorizingService.class})
public abstract class RoleMapper implements
    Converter<Role, com.sublinks.sublinksapi.api.sublinks.v1.roles.models.Role> {

  @Override
  @Mapping(target = "key", source = "role.name")
  @Mapping(target = "name", source = "role.name")
  @Mapping(target = "description", source = "role.description")
  @Mapping(target = "isActive", source = "role.isActive")
  @Mapping(target = "isExpired", source = "role", qualifiedByName = "is_expired")
  @Mapping(target = "expiresAt", source = "role.expiresAt", dateFormat = DateUtils.FRONT_END_DATE_FORMAT)
  @Mapping(target = "createdAt", source = "role.createdAt", dateFormat = DateUtils.FRONT_END_DATE_FORMAT)
  @Mapping(target = "updatedAt", source = "role.updaetedAt", dateFormat = DateUtils.FRONT_END_DATE_FORMAT)
  public abstract com.sublinks.sublinksapi.api.sublinks.v1.roles.models.Role convert(
      @Nullable Role role);

  @Named("is_expired")
  Boolean mapIsExpired(Role role) {

    return new Date().after(role.getExpiresAt());
  }
}