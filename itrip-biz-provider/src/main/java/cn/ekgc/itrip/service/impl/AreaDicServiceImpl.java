package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.AreaDicDao;
import cn.ekgc.itrip.pojo.entity.AreaDic;
import cn.ekgc.itrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区域字典业务层接口实现类
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
@Service("areaDicService")
@Transactional
public class AreaDicServiceImpl implements AreaDicService {
	@Autowired
	private AreaDicDao areaDicDao;

	/**
	 * 查询国内、国外的热门城市(1:国内 2:国外)
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	public List<AreaDic> getHotCityListByIsChina(Integer isChina) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		// 国内/外热门城市
		queryMap.put("isChina", isChina);
		queryMap.put("isHot", 1);
		// 处于激活状态
		queryMap.put("isActivated", 1);

		List<AreaDic> areaDicList = areaDicDao.findAreaDisListByQuery(queryMap);
		return areaDicList;
	}

	/**
	 * 根据城市查询商圈
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	public List<AreaDic> getTradeAreaListByCityId(Integer cityId) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", cityId);
		// 处于激活状态
		queryMap.put("isActivated", 1);
		// 是商圈
		queryMap.put("isTradingArea", 1);
		List<AreaDic> areaDicList = areaDicDao.findAreaDisListByQuery(queryMap);
		return null;
	}
}
