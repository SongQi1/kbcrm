package com.bocs.sys.service;

import com.bocs.core.base.BaseService;
import com.bocs.core.util.InstanceUtil;
import com.bocs.sys.mapper.*;
import com.bocs.sys.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 客户装修信息表 服务实现类
 * </p>
 *
 * @author SongQi
 * @since 2017-09-09
 */
@Service
public class CustomerDecorationFormService extends BaseService<CustomerDecorationForm>{

    @Autowired
    private BuildingTypeMapper buildingTypeMapper;
    @Autowired
    private DecorationDegreeMapper decorationDegreeMapper;
    @Autowired
    private DecorationTimeMapper decorationTimeMapper;
    @Autowired
    private DecorationStyleMapper decorationStyleMapper;
    @Autowired
    private InfoChannelMapper infoChannelMapper;
    @Autowired
    private NumberOfPeopleMapper numberOfPeopleMapper;
    @Autowired
    private HouseTypeMapper houseTypeMapper;
    @Autowired
    private PicturesMapper picturesMapper;



    public CustomerDecorationForm selectById(long id) {
        CustomerDecorationForm decorationForm = mapper.selectById(id);
        if(decorationForm != null){
            BuildingType buildingType = buildingTypeMapper.selectById(decorationForm.getBuildingTypeId());
            if(buildingType != null){
                decorationForm.setBuildingType(buildingType.getName());
            }
            HouseType houseType = houseTypeMapper.selectById(decorationForm.getHouseTypeId());
            if(houseType != null){
                decorationForm.setHouseType(houseType.getName());
            }
            DecorationStyle decorationStyle = decorationStyleMapper.selectById(decorationForm.getDecorationStyleId());
            if(decorationStyle != null){
                decorationForm.setDecorationStyle(decorationStyle.getName());
            }

            DecorationDegree decorationDegree = decorationDegreeMapper.selectById(decorationForm.getDecorateDegreeId());
            if(decorationDegree != null){
                decorationForm.setDecorateDegree(decorationDegree.getName());
            }

            DecorationTime decorationTime = decorationTimeMapper.selectById(decorationForm.getDecorationTimeId());
            if(decorationTime != null){
                decorationForm.setDecorationTime(decorationTime.getName());
            }

            InfoChannel infoChannel = infoChannelMapper.selectById(decorationForm.getInfoChannelId());
            if(infoChannel != null){
                decorationForm.setInfoChannel(infoChannel.getName());
            }

            NumberOfPeople numberOfPeople = numberOfPeopleMapper.selectById(decorationForm.getNumberOfPeopleId());
            if(numberOfPeople != null){
                decorationForm.setNumberOfPeople(numberOfPeople.getName());
            }
        }
        return decorationForm;
    }

    public void deleteCustomer(Long id) {
        mapper.deleteById(id);
        //删除图片
        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("customerId", id);
        picturesMapper.deleteByMap(param);
    }
}
