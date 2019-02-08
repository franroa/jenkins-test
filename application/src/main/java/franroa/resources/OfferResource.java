package franroa.resources;

import franroa.dto.OfferRequest;
import franroa.dto.OfferResponse;
import franroa.core.Offer;
import franroa.transformers.OfferTransformer;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/offers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OfferResource {
    @POST
    public Response store(OfferRequest request) {
        Offer offer = Offer.buildFromRequest(request);

        OfferResponse response = OfferTransformer.transform(offer);

        return Response.ok().entity(response).build();
    }
}
