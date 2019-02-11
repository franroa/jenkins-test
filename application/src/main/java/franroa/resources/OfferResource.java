package franroa.resources;

import franroa.api.OfferListResponse;
import franroa.api.OfferRequest;
import franroa.api.OfferResponse;
import franroa.core.Model;
import franroa.core.Offer;
import franroa.jobs.DeleteOfferJob;
import franroa.transformers.OfferTransformer;
import org.slf4j.LoggerFactory;


import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;

@Path("/v1/offers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OfferResource {
    @POST
    public Response store(@Valid OfferRequest request) {
        Offer offer = Offer.buildFromRequest(request);

        new DeleteOfferJob()
                .setOfferId(offer.getLongId())
                .dispatch(Timestamp.valueOf(request.expires_at));

        OfferResponse response = OfferTransformer.transform(offer);

        return Response.created(URI.create("/v1/offers/" + offer.getLongId())).entity(response).build();
    }

    @GET
    public Response index() {
        List<Offer> offers = Offer.findAll();

        OfferListResponse response = OfferTransformer.transform(offers);

        return Response.ok().entity(response).build();
    }

    @GET
    @Path("{id}")
    public Response show(@PathParam("id") Long id) {
        Offer offer = Model.findOrFail(Offer.class, id);

        OfferResponse response = OfferTransformer.transform(offer);

        return Response.ok().entity(response).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        Offer offer = Model.findOrFail(Offer.class, id);

        offer.delete();

        return Response.ok().build();
    }
}
