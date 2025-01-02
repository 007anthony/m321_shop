package ch.bbw.ap.shop.usermanager.mapper;

import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.UserCreate;
import ch.bbw.ap.shop.usermanager.model.request.UserEdit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserCreate user);

    @Mapping(target = "username", source = "userEdit.username")
    @Mapping(target = "email", source = "userEdit.email")
    User merge(UserEdit userEdit, User user);
}
