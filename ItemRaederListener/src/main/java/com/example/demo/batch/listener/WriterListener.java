package com.example.demo.batch.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import com.example.demo.batch.model.Employee;

@Component
public class WriterListener implements ItemWriteListener<Employee>
{
	@Override
	public void beforeWrite(List<? extends Employee> list) 
	{
		
	}

	@Override
	public void afterWrite(List<? extends Employee> list)
	{
		 System.out.println("after write");
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Employee> list)
	{
		System.out.println("on write error : " + exception.getMessage());
	}

}
