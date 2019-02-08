package franroa.resources;

import franroa.core.Offer;
import franroa.dto.OfferListResponse;
import franroa.transformers.OfferTransformer;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/offers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OfferResource {
    @GET
    public Response index() {
        List<Offer> offers = Offer.findAll();

        OfferListResponse offerListResponse = OfferTransformer.transform(offers);

        return Response.ok().build();
    }
}
