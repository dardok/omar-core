package omar.core

import grails.artefact.Enhances
import org.grails.core.artefact.ControllerArtefactHandler


/**
 * Created by gpotts on 3/1/16.
 */
@Enhances(ControllerArtefactHandler.TYPE)
trait BaseUrlTrait
{
   String getBaseUrl()
   {
      URL url = new URL (request.requestURL.toString())

      String serverURL = grailsApplication.config.grails.serverURL

      if (!serverURL) {
         def forwardedProto = request.getHeader("x-forwarded-proto")
         def protocol = (forwardedProto == null) ? url.protocol : forwardedProto;

         serverURL = "${protocol}://${url.host}${url.port>0?':'+url.port:''}${request.contextPath?:''}"

      }

      serverURL
   }
}
