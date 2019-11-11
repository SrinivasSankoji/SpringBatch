package com.example.demo.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.batch.model.OneLakh;
import com.example.demo.batch.repository.OneLakhRepository;

@Component
public class OneLakhDBWriter implements ItemWriter<OneLakh>
{
	
	private static final Logger logger = LoggerFactory.getLogger(OneLakhDBWriter.class);
	
	@Autowired
	OneLakhRepository oneLakhRepository;

	@Override
	public void write(List<? extends OneLakh> oneLakhList) throws Exception 
	{
		oneLakhRepository.saveAll(oneLakhList);
	    logger.info("{} employees saved in database", oneLakhList.size());
	}

}
