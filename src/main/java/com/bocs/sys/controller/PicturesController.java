package com.bocs.sys.controller;

import com.bocs.core.base.AbstractController;
import com.bocs.sys.model.Pictures;
import com.bocs.sys.service.PicturesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <p>
 * 客户房屋照片  前端控制器
 * </p>
 *
 * @author SongQi
 * @since 2017-09-09
 */
@RestController
@RequestMapping("/pictures")
@Api(value = "客户房屋照片接口", description = "客户房屋照片接口")
public class PicturesController extends AbstractController<Pictures> {

	@Autowired
	private PicturesService picturesService;

	@ApiOperation(value = "删除图片")
	@RequiresPermissions(".delete")
	@PostMapping("/removePic")
	public Object removePic(ModelMap modelMap, Long id){
		picturesService.delete(id);
		return setSuccessModelMap(modelMap);
	}

	@PostMapping("/uploadPic")
	public Object uploadPic(@RequestParam("file") MultipartFile file, String customerId, String picType) {
		ModelMap modelMap = new ModelMap();
		if (StringUtils.isNotEmpty(customerId) && StringUtils.isNotEmpty(picType)) {
			Long picId = picturesService.updatePic(file, customerId, picType);
			modelMap.addAttribute("picId", picId);
		}
		return setSuccessModelMap(modelMap);
	}

	@RequestMapping("/viewimage/{id}")
	public void viewPic(@PathVariable(name = "id") Long id, HttpServletResponse response) {
		File file = picturesService.getPicFile(id);

		if(file != null){
			response.setContentType("img/jpeg");
			response.setCharacterEncoding("utf-8");
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(file);
				os = response.getOutputStream();
				byte[] b = new byte[2048];
				int length;
				while ((length = is.read(b)) > 0) {
					os.write(b, 0, length);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(os != null){
						os.close();
					}
					if(is != null){
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}