package net.guymage.client.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import net.guymage.api.model.race.Race;

/**
 * Interface REST pour les {@link Race}
 *
 * @author guymage
 *
 */
public interface IRaceService extends RestService {

	@GET
	@Path("/race")
	public void getAllRaces(MethodCallback<List<Race>> callback);
}
