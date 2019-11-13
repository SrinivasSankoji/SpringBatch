package com.example.demo.batch.skip;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class JobSkipPolicy implements SkipPolicy
{

	@Override
	public boolean shouldSkip(Throwable t, int failedCount) throws SkipLimitExceededException 
	{
		return (failedCount>=1)?true:false;
	}

}
