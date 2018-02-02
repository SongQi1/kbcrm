package com.bocs.sys.controller;

import com.bocs.core.base.AbstractController;
import com.bocs.core.exception.BusinessException;
import com.bocs.core.util.InstanceUtil;
import com.bocs.core.util.WebUtil;
import com.bocs.sys.model.CustomerDecorationForm;
import com.bocs.sys.model.Pictures;
import com.bocs.sys.service.CustomerDecorationFormService;
import com.bocs.sys.service.PicturesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户装修信息表  前端控制器
 * </p>
 *
 * @author SongQi
 * @since 2017-09-09
 */
@RestController
@RequestMapping("/decorationForm")
@Api(value = "客户装修信息表接口", description = "客户装修信息表接口")
public class CustomerDecorationFormController extends AbstractController<CustomerDecorationForm> {


	@Autowired
	private CustomerDecorationFormService customerDecorationFormService;
	@Autowired
	private PicturesService picturesService;


	@ApiOperation(value = "查询客户装修信息表")
	@RequiresPermissions(".read")
	@PutMapping(value = "/read/list")
	public Object query(ModelMap modelMap, Map<String, Object> param) {
		return super.query(modelMap, param);
	}

	@ApiOperation(value = "客户装修信息表详情")
	@RequiresPermissions(".read")
	@RequestMapping(value = "/view/{id}")
	public Object get(@PathVariable(name = "id") long id) {
		CustomerDecorationForm decorationForm = customerDecorationFormService.selectById(id);
		ModelAndView modelAndView = new ModelAndView("/customer/decoration_form_view", "decorationForm", decorationForm);

		if(decorationForm != null){
			Map<String, List<Long>> map = getPics(decorationForm.getId());
			modelAndView.addObject("entrancePicIdList", map.get("entrancePicIdList"));
			modelAndView.addObject("unitPicIdList", map.get("unitPicIdList"));
			modelAndView.addObject("doorPicIdList", map.get("doorPicIdList"));
		}
		return modelAndView;
	}


	@ApiOperation(value = "修改客户装修信息表")
	@RequiresPermissions(".update")
	@PostMapping("/update")
	public Object update(ModelMap modelMap, CustomerDecorationForm decorationForm) {
		long currentUser = WebUtil.getCurrentUser();
		if(decorationForm.getId() == null){
			CustomerDecorationForm param = new CustomerDecorationForm();
			param.setPhone(decorationForm.getPhone());
			CustomerDecorationForm formDB = customerDecorationFormService.selectOne(param);
			if(formDB != null){
				throw new BusinessException("已存在相同手机号码。");
			}
		}
		if(decorationForm.getHasfloorHeating() == null){
			decorationForm.setHasfloorHeating("no");
		}
		decorationForm.setCreateBy(currentUser);
		customerDecorationFormService.update(decorationForm);
		return setSuccessModelMap(modelMap);
	}


	@PostMapping("/delete")
	@ApiOperation(value = "删除客户装修信息表")
	@RequiresPermissions(".delete")
	public Object delete(Long id) {
		customerDecorationFormService.deleteCustomer(id);
		return setSuccessModelMap();
	}

	@RequestMapping("/myRegisterFormList")
	public Object myRegisterFormList(){
		Long userId = WebUtil.getCurrentUser();
		Map<String, Object> param = InstanceUtil.newHashMap();
		param.put("createBy", userId);
		List<CustomerDecorationForm> myRegistFormList = customerDecorationFormService.queryList(param);
		return new ModelAndView("/customer/my_decoration_register_form_list", "registerFormList", myRegistFormList);
	}

	//页面跳转
	@RequestMapping("/addFormPage1")
	public ModelAndView addFormPage1(Long id){
		CustomerDecorationForm customerDecorationForm = null;
		if(id != null){
			customerDecorationForm = customerDecorationFormService.queryById(id);
		}
		return new ModelAndView("/customer/customer","decorationForm", customerDecorationForm);
	}

	@RequestMapping("/addFormPage2/{id}")
	public ModelAndView addFormPage2(@PathVariable Long id){
		CustomerDecorationForm customerDecorationForm = null;
		if(id != null){
			customerDecorationForm = customerDecorationFormService.queryById(id);
		}
		return new ModelAndView("/customer/house","decorationForm",customerDecorationForm);
	}

	@RequestMapping("/addFormPage3/{id}")
	public ModelAndView addFormPage3(@PathVariable Long id){
		CustomerDecorationForm customerDecorationForm = null;
		if(id != null){
			customerDecorationForm = customerDecorationFormService.queryById(id);
		}
		return new ModelAndView("/customer/electricHeatingMaterial","decorationForm",customerDecorationForm);
	}
	@RequestMapping("/addFormPage4/{id}")
	public ModelAndView addFormPage4(@PathVariable Long id){
		CustomerDecorationForm customerDecorationForm = null;
		if(id != null){
			customerDecorationForm = customerDecorationFormService.queryById(id);
			ModelAndView modelAndView = new ModelAndView("/customer/customerPhoto","decorationForm",customerDecorationForm);
			if(customerDecorationForm != null){
				Map<String, List<Long>> map = getPics(customerDecorationForm.getId());
				modelAndView.addObject("entrancePicIdList", map.get("entrancePicIdList"));
				modelAndView.addObject("unitPicIdList", map.get("unitPicIdList"));
				modelAndView.addObject("doorPicIdList", map.get("doorPicIdList"));
			}
			return modelAndView;
		}
		return null;
	}

	@RequestMapping("/addSuccessPage")
	public ModelAndView addSuccessPage(Long id){
		CustomerDecorationForm customerDecorationForm = customerDecorationFormService.queryById(id);
		return new ModelAndView("/customer/add_success", "decorationForm",customerDecorationForm);
	}


	/**
	 * 获取图片
	 * @param customerId
	 * @return
	 */
	private Map<String, List<Long>> getPics(Long customerId){
		Map<String,List<Long>> picMap = InstanceUtil.newHashMap();
		Map<String, Object> param = InstanceUtil.newHashMap();
		param.put("customerId", customerId);
		List<Pictures> pictureList = picturesService.queryList(param);
		if(pictureList != null && pictureList.size() > 0){

			//分捡图片
			List<Long> entrancePicIdList = InstanceUtil.newArrayList();
			List<Long> unitPicIdList = InstanceUtil.newArrayList();
			List<Long> doorPicIdList = InstanceUtil.newArrayList();
			for(Pictures picture : pictureList){
				//entrance(小区正门),unit(单元),door(门牌)
				if("entrance".equals(picture.getPictureType())){
					entrancePicIdList.add(picture.getId());
				}else if("unit".equals(picture.getPictureType())){
					unitPicIdList.add(picture.getId());
				}else if("door".equals(picture.getPictureType())){
					doorPicIdList.add(picture.getId());
				}
			}
			picMap.put("entrancePicIdList", entrancePicIdList);
			picMap.put("unitPicIdList", unitPicIdList);
			picMap.put("doorPicIdList", doorPicIdList);
		}
		return picMap;

	}
}