package game.controller;

import game.dto.AccountResourceDto;


import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author ruslan.gramatic
 */
public interface AccountResourceController {
    void clearAccountResourceList(Integer accountId);
    List<AccountResourceDto> getAccountResourceList(Integer accountId);
}
