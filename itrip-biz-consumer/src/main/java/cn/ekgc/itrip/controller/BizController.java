package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.pojo.entity.AreaDic;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.pojo.vo.Dto;
import cn.ekgc.itrip.transport.AreaDicTransport;
import cn.ekgc.itrip.transport.LabelDicTranspor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.DoubleToIntFunction;

/**
 * 主功能控制中心
 * @author wang
 * @version 3.1.0
 * @since 2019-12-16
 */
@RestController("bizController")
@RequestMapping("/biz")
public class BizController extends BaseController {
	@Autowired
	private AreaDicTransport areaDicTransport;
	@Autowired
	private LabelDicTranspor labelDicTranspor;

	/**
	 * 查询国内、国外的热门城市(1:国内 2:国外)
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/hotel/queryhotcity/{isChina}", method = RequestMethod.GET)
	public Dto<Object> queryHotCityListByIsChina(@PathVariable("isChina") Integer isChina)
			throws Exception {
		Dto<Object> result = new Dto<Object>();
		List<AreaDic> areaDicList = areaDicTransport.getHotCityListByIsChina(isChina);

		result.setData(areaDicList);
		result.setSuccess("true");
		return result;
	}

	/**
	 * 获取酒店特色(用于查询页列表)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/hotel/queryhotelfeature", method = RequestMethod.GET)
	public Dto<Object> queryHotelFeatureList() throws Exception {
		Dto<Object> result = new Dto<Object>();
		List<LabelDic> labelDicList = labelDicTranspor.getHotelFeatureList();

		result.setData(labelDicList);
		result.setSuccess("true");
		return result;
	}

	/**
	 * 根据城市查询商圈
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/hotel/querytradearea/{cityId}", method = RequestMethod.GET)
	public Dto<Object> queryTradeAreaListByCityId(@PathVariable("cityId") Integer cityId)
			throws Exception {
		Dto<Object> result = new Dto<Object>();
		List<AreaDic> tradeDicList = areaDicTransport.getTradeAreaListByCityId(cityId);

		result.setData(tradeDicList);
		result.setSuccess("true");
		return result;
	}

	/**
	 * 根据酒店ID查询酒店图片
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/hotel/getimg/{targetId}", method = RequestMethod.GET)
	public Dto<Object> getHotelImageByTargetId(@PathVariable("targetId") Integer targetId)
			throws Exception {
		Dto<Object> result = new Dto<Object>();

		return result;
	}



}
