package com.mww.gecco.book;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author rook
 * @date 2016-4-9 上午10:50:29
 */
public class SaveCategoryPipeline implements Pipeline<IndexPage> {


	public void process(IndexPage bean) {
		List<CategoryType> categoryTypes = bean.getCategoryTypes();
		for (CategoryType categoryType : categoryTypes) {
			categoryType.setCreateDate(new Date());
			HttpRequest sub = bean.getRequest().subRequest(categoryType.getUrl()+"1.htm");
			sub.addParameter("tag", ""+categoryType.getId());
			SchedulerContext.into(sub);
		}
	}
}
