package ch.bbw.ap.shop.usermanager.mapper;

import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.UserCreate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public User map(UserCreate user);
}
