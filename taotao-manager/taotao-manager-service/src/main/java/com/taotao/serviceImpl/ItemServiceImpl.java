package com.taotao.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemByid(long itemId) {
		TbItemExample tbItemExample = new TbItemExample();
		Criteria criteria = tbItemExample.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(tbItemExample);
		if (list != null && list.size() > 0) {
			TbItem tbItem = list.get(0);
			return tbItem;
		}
		return null;
	}

}
