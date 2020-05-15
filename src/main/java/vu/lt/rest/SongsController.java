package vu.lt.rest;


import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Song;
import vu.lt.persistence.SongsDAO;
import vu.lt.rest.contracts.SongDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/songs")
public class SongsController {

    @Inject
    @Setter @Getter
    private SongsDAO songsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Song song = songsDAO.findOne(id);
        if (song == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        SongDto songDto = new SongDto();
        songDto.setName(song.getName());
        songDto.setTempo(song.getTempo());
        songDto.setArtistStageName(song.getArtist().getStageName());

        return Response.ok(songDto).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Song create(Song s) {
        songsDAO.persist(s);
        return s;
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer songId, SongDto songData) {
        try {
            Song existingSong = songsDAO.findOne(songId);
            if (existingSong == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingSong.setName(songData.getName());
            existingSong.setTempo(songData.getTempo());
            songsDAO.update(existingSong);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
