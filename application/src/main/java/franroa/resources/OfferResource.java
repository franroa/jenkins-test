package franroa.resources;

import franroa.api.OfferListResponse;
import franroa.api.OfferRequest;
import franroa.api.OfferResponse;
import franroa.core.Offer;
import franroa.transformers.OfferTransformer;
import org.eclipse.jetty.http.HttpStatus;


import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/offers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OfferResource {
    @POST
    public Response store(@Valid OfferRequest request) {
        Offer offer = Offer.buildFromRequest(request);

        OfferResponse response = OfferTransformer.transform(offer);

        return Response.ok().entity(response).build();
    }

    @GET
    public Response index() {
        List<Offer> offers = Offer.findAll();

        OfferListResponse response = OfferTransformer.transform(offers);

        return Response.status(HttpStatus.OK_200).entity(response).build();
    }
}
