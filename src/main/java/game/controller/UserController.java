package game.controller;

import game.dto.UserDto;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

/**
 * @author ruslan.gramatic
 */
public interface UserController {
    Response loginUser(UserDto user);
    Response logoutUser(Cookie cookie);
    Response createNewUser(UserDto user);
}
