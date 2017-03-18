package com.taotao.test;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;

public class TestClass {
	
	private Logger logger1 = LoggerFactory.getLogger(TestClass.class);
	
	private ApplicationContext ac;
	
	@Before
	public void init() {
		this.ac = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
	}
	
	@Test
	public void testApplicationContextDao() {
		logger1.debug("进入test");
		logger1.info("haha");
		DruidDataSource dds = ac.getBean("dataSource", DruidDataSource.class);
		System.out.println(dds);
		SqlSessionFactory sqlSession = ac.getBean("sqlSessionFactory", SqlSessionFactory.class);
		System.out.println(sqlSession);
		try {
			int a = 1/0;
		} catch (Exception e) {
			logger1.error("程序出现异常", e);
		}
		MapperScannerConfigurer ms = ac.getBean("mapperScanner", MapperScannerConfigurer.class);
		System.out.println(ms);
	}
	
	@Test
	public void testDao() {
		TbItemMapper itemMapper = ac.getBean("tbItemMapper", TbItemMapper.class);
		System.out.println(itemMapper);
		DruidDataSource dds = ac.getBean("dataSource", DruidDataSource.class);
		System.out.println(dds);
		TbItemExample tbItemExample = new TbItemExample();
		Criteria criteria = tbItemExample.createCriteria();
		criteria.andIdEqualTo(536563L);
		
		List<TbItem> list = itemMapper.selectByExample(tbItemExample);
		if (list != null && list.size() > 0) {
			TbItem tbItem = list.get(0);
			System.out.println(tbItem);
		}
		//System.out.println(list);
	}
}
