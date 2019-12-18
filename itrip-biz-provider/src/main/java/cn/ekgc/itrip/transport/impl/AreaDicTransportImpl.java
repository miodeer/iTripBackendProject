package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.pojo.entity.AreaDic;
import cn.ekgc.itrip.service.AreaDicService;
import cn.ekgc.itrip.transport.AreaDicTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 区域字典传输层实现类
 * @author wang
 * @version 3.1.0
 * @since 2019-12-17
 */
@RestController("areaDicTransport")
@RequestMapping("/area")
public class AreaDicTransportImpl implements AreaDicTransport {
	@Autowired
	private AreaDicService areaDicService;

	/**
	 * 查询国内、国外的热门城市(1:国内 2:国外)
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryHotCityByIsChina", method = RequestMethod.POST)
	public List<AreaDic> getHotCityListByIsChina(@RequestParam Integer isChina) throws Exception {
		return areaDicService.getHotCityListByIsChina(isChina);
	}

	/**
	 * 根据城市查询商圈
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTradeAreaByCityId", method = RequestMethod.POST)
	public List<AreaDic> getTradeAreaListByCityId(Integer cityId) throws Exception {
		return areaDicService.getTradeAreaListByCityId(cityId);
	}
}
