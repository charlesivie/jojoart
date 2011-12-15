package com.jojoart.service;

import com.jojoart.dao.ImageDao;
import com.jojoart.dao.ImageVersionDao;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.ImageVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.imgscalr.Scalr.resize;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/12/2011
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ImageServiceImpl implements ImageService {

    private ImageDao imageDao;
    private ImageVersionDao imageVersionDao;

    @Transactional
    public void resizeAndStoreImage(Image image, MultipartFile multipartFile) throws IOException {

        if (image.getId() > 0) {
            imageDao.update(image);
        } else {
            imageDao.create(image);
        }

        if (multipartFile != null) {

            List<ImageVersion> imageVersions =  imageVersionDao.getAllImageVersions(image);

            for (ImageVersion imageVersion:imageVersions){
                imageVersionDao.delete(imageVersion);
            }

            for (ImageType imageType : ImageType.values()) {
                byte[] bytes = getResizedBytes(multipartFile, image.getName(), imageType.getMaxSize());
                imageVersionDao.create(new ImageVersion(bytes, imageType.getMaxSize(), image));
            }
        }
    }
    
    protected byte[] getResizedBytes(MultipartFile multipartFile, String name, int maxWidth) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        bufferedImage = resize(bufferedImage, maxWidth);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, name+"_"+maxWidth, baos);
        return baos.toByteArray();
    }

    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Autowired
    public void setImageVersionDao(ImageVersionDao imageVersionDao) {
        this.imageVersionDao = imageVersionDao;
    }
}
