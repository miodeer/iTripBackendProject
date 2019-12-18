package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.AreaDic;

import java.util.List;

/**
 * 区域字典业务层接口
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
public interface AreaDicService {
	/**
	 * 查询国内、国外的热门城市(1:国内 2:国外)
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getHotCityListByIsChina(Integer isChina) throws Exception;

	/**
	 * 根据城市查询商圈
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getTradeAreaListByCityId(Integer cityId) throws Exception;
}
