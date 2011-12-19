package com.jojoart.web;

import com.jojoart.dao.ImageVersionDao;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.ImageVersion;
import com.sun.xml.internal.ws.api.server.ResourceInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 19/12/2011
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping(value = "/image")
public class ImageStreamController {

    ImageVersionDao imageVersionDao;

    @RequestMapping(value = "/{imageId}/{type}", method = RequestMethod.GET)
    public void stream(
            final HttpServletResponse response,
            @PathVariable Long imageId,
            @PathVariable String type) throws IOException {

        ImageVersion imageVersion = imageVersionDao.findByTypeAndImage(ImageType.valueOf(type), new Image(imageId));

        response.setContentType(imageVersion.getImage().getMimeType());
        response.setContentLength(imageVersion.getImageBlob().length);

        response.getOutputStream().write(imageVersion.getImageBlob());
        response.getOutputStream().flush();
    }

    @Autowired
    public void setImageVersionDao(ImageVersionDao imageVersionDao) {
        this.imageVersionDao = imageVersionDao;
    }
}
