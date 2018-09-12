package game.controller.impl;

import game.controller.RoomController;
import game.dto.AccountRoomDto;
import game.dto.RoomDto;
import game.service.RoomService;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ruslan.gramatic
 */
@Path("/room")
public class RoomControllerImpl implements RoomController {

    @Inject
    public RoomService roomService;

    @GET
    @Path("list")
    public List<RoomDto> getAllRoomList() {
        List<RoomDto> roomList = roomService.getRoomList();
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, roomList.toString());
        return roomList;
    }

    @GET
    @Path("account/list")
    public List<AccountRoomDto> getAccountRoomList() {
        List<AccountRoomDto> accountRoomDtoList = roomService.getAccountRoomList();
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, accountRoomDtoList.toString());
        return accountRoomDtoList; //TODO: we need to return this list with correct data
        //TODO: DONE?
    }

    @GET
    @Path("/join/{roomId}/{accountId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response joinRoom(@PathParam("roomId") Integer roomId, @PathParam("accountId") Integer accountId) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"room=" + roomId);
        roomService.joinRoom(roomId, accountId);
        return Response.status(200).entity("User Entered").build();
    }

    @GET
    @Path("/leave/{roomId}/{accountId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response leaveRoom(@PathParam("roomId") Integer roomId, @PathParam("accountId") Integer accountId) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"room=" + roomId);
        roomService.leaveRoom(roomId, accountId);
        return Response.status(200).entity("User Left").build();
    }
}
