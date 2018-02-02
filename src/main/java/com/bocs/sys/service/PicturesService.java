package com.bocs.sys.service;

import com.bocs.core.base.BaseService;
import com.bocs.core.util.DateUtil;
import com.bocs.core.util.PropertiesUtil;
import com.bocs.sys.model.Pictures;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * <p>
 * 客户房屋照片 服务实现类
 * </p>
 *
 * @author SongQi
 * @since 2017-09-09
 */
@Service
public class PicturesService extends BaseService<Pictures> {

    /**
     * 上传图片
     *
     * @param file
     * @param customerId
     * @param picType
     * @return
     */
    public Long updatePic(MultipartFile file, String customerId, String picType) {
        //保存图片至服务器
        String rootPath = PropertiesUtil.getString("pic.store.path");
        if(! rootPath.endsWith(File.separator)){
            rootPath += File.separator;
        }
        String fileDir = DateUtil.getDateTime("yyyyMMdd") + File.separator + DateUtil.getDateTime("HHmmss") + file.getOriginalFilename();
        String imgPath = rootPath + fileDir;
        File imgFile = new File(imgPath);
        File parent = imgFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try {
            file.transferTo(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pictures customerHousePictures = new Pictures();
        customerHousePictures.setCustomerId(Long.parseLong(customerId));
        customerHousePictures.setPictureType(picType);
        customerHousePictures.setPictureUrl(fileDir);
        customerHousePictures.setCreateTime(new Date());
        mapper.insert(customerHousePictures);
        return customerHousePictures.getId();
    }

    /**
     * 查找图片
     * @param fileId
     * @return
     */
    public File getPicFile(Long fileId) {
        Pictures picture = mapper.selectById(fileId);
        if(picture != null){
            String rootPath = PropertiesUtil.getString("pic.store.path");
            if(! rootPath.endsWith(File.separator)){
                rootPath += File.separator;
            }

            String fileDir =  picture.getPictureUrl();

            String imgPath = rootPath + fileDir;
            File imgFile = new File(imgPath);
            return imgFile;
        }
        return null;
    }
}
